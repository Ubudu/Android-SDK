;;;; -*- mode:emacs-lisp;coding:utf-8 -*-
;;;;**************************************************************************
;;;;FILE:               generate.el
;;;;LANGUAGE:           emacs lisp
;;;;SYSTEM:             POSIX
;;;;USER-INTERFACE:     NONE
;;;;DESCRIPTION
;;;;    
;;;;    This file generates the dto (Data Transfer Object) classes for
;;;;    the Ubudu API JSON structures.
;;;;    
;;;;AUTHORS
;;;;    <PJB> Pascal J. Bourguignon <pjb@informatimago.com>
;;;;MODIFICATIONS
;;;;    2014-08-27 <PJB> Added this header.
;;;;BUGS
;;;;LEGAL
;;;;    ubudu-public
;;;;    
;;;;    Copyright (c) 2011-2014, UBUDU SAS
;;;;    All rights reserved.
;;;;    
;;;;    Redistribution and use in source and binary forms, with or without
;;;;    modification, are permitted provided that the following conditions are met:
;;;;    
;;;;    * Redistributions of source code must retain the above copyright notice, this
;;;;      list of conditions and the following disclaimer.
;;;;    
;;;;    * Redistributions in binary form must reproduce the above copyright notice,
;;;;      this list of conditions and the following disclaimer in the documentation
;;;;      and/or other materials provided with the distribution.
;;;;    
;;;;    THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
;;;;    AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
;;;;    IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
;;;;    DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
;;;;    FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
;;;;    DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
;;;;    SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
;;;;    CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
;;;;    OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
;;;;    OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
;;;;**************************************************************************

;;;;---------------------------------------------------------------------
;;; Skip to the Entities section to add/edit the dto entities.
;;;---------------------------------------------------------------------
(setq lexical-binding t)
(setq-default indent-tabs-mode nil)
(require 'cl)
;;;---------------------------------------------------------------------

(defmacro defparameter (symbol &optional initvalue docstring)
  `(progn
     (defvar ,symbol nil ,docstring)
     (setq   ,symbol ,initvalue)))

(defparameter *dirpath* default-directory "Where the java files will be generated.")
(defparameter *java-current-package* 'com.ubudu.sdk.devapp)
(message "dirpath= %S" *dirpath*)

;;;---------------------------------------------------------------------

(defparameter *java-operators* '(+ - * / < > <= >= == ! && ||))

(defparameter *java-predefined-classes* '((java.lang . Object)
                                          (java.lang . String)
                                          (java.lang . Boolean)
                                          (java.lang . Integer)
                                          (java.lang . Double)
                                          (java.util . Date)
                                          (java.util . Vector)))



(defun java-in-package (package)
  (setf *java-current-package* package)
  (insert (format "package %s;\n\n" *java-current-package*)))

(defun java-import (full-qualified-class-name)
  (insert (format "import %s;\n" full-qualified-class-name)))

(defun* java-class (class-name &key superclass interfaces throws import-thunk body-thunk)
  (when import-thunk (funcall import-thunk))
  (insert (format  "public class %s" class-name))
  (when superclass
    (insert (format " extends %s" superclass)))
  (when interfaces
    (insert (format " implements %s" (join (mapcar (function symbol-name) interfaces) ","))))
  (when throws
    (insert (format " throws %s" (join throws ","))))
  (insert "{\n")
  (when body-thunk (funcall body-thunk))
  (insert "\n}\n"))

(defun java-parameters (parameters)
  (insert (format "(%s)" (join (mapcar (lambda (parameter)
                                         (destructuring-bind (name type) parameter
                                           (format "%s %s" (prepare-type type) name)))
                                       parameters)
                               ","))))

(defun java-expression (expression)
  (if (atom expression)
    (format "%s" expression)
    (let ((op (first expression))
          (args (rest expression)))
      (cond
       ((member op *java-operators*)
        (if (endp (rest args))
          (format "(%s%s)" op (java-expression (first args)))
          (format "(%s)" (join (mapcar (function java-expression) args)
                               (format "%s" op)))))
       ((eq op '\.)
        (java-send (first args) (second args) (cddr args)))
       (t
        (java-send nil op args))))))

(defun java-arguments (arguments)
  (insert (format "(%s)" (join (mapcar (function java-expression) arguments) ","))))

(defun arguments-from-parameters (parameters)
  (mapcar (lambda (parameter)
            (destructuring-bind (name type) parameter
              name))
          parameters))

(defun java-send (recipient message arguments)
  (insert (if recipient
             (format "%s.%s" recipient message)
             (format "%s" message)))
  (java-arguments arguments))


(defun java-constructor (class-name parameters)
  (insert (format "public %s" class-name))
  (java-parameters parameters)
  (insert "{" "\n")
  (java-send nil 'super (arguments-from-parameters parameters)) (insert ";" "\n")
  (insert "}" "\n"))



(defun java-class-package (class)
  (car (rassoc class *java-predefined-classes*)))

(defun java-fully-qualified-class (class)
  (intern (format "%s.%s" (or (java-class-package class)
                              *java-current-package*)
                  class)))

;; (java-fully-qualified-class 'Integer) java\.lang\.Integer
;; (java-fully-qualified-class 'Geofence) com\.example\.Geofence

(defun prepare-type (type)
  (if (atom type)
    type
    (format "%s<%s>" (first type) (join (mapcar (function prin1-to-string) (rest type)) ","))))

(defun* generate-java-class (file-name package-name class-name &key superclass interfaces throws fields java)
  (save-excursion
    (find-file file-name)
    (erase-buffer)
    (insert "// -*- mode:java; coding:utf-8 -*-" "\n")
    (insert "// Generated automatically by generate.el" "\n" "\n")
    (java-in-package package-name)
    (dolist (class (remove-duplicates (append (when superclass (list superclass))
                                              interfaces
                                              (mapcan (lambda (field)
                                                        (if (atom (second field))
                                                          (list (second field))
                                                          (copy-list (second field))))
                                                      fields))))
      (unless (find ?. (symbol-name class))
       (java-import (java-fully-qualified-class class))))

    (java-class class-name
                :superclass superclass
                :interfaces interfaces
                :throws throws
                :import-thunk (lambda ()
                                (java-import 'com.google.gson.annotations.SerializedName))
                :body-thunk   (lambda ()
                                (java-constructor class-name '())
                                (dolist (field fields)
                                  (destructuring-bind (name type) field
                                    (let ((ptype  (prepare-type type)))
                                      (insert (format "@SerializedName(\"%s\")" name) "\n")
                                      (insert (format "public %s %s;" ptype name) "\n"))))
                                (mapcar (function insert) java)))
    (save-buffer 0)
    (kill-buffer)))



(defmacro define-entity (class &rest fields-and-java)
  (let ((class-name (if (atom class)
                        class
                        (first class)))
        (superclass (if (atom class)
                        'Object
                        (second (assoc :superclass (rest class)))))
        (interfaces (if (atom class)
                        '()
                        (rest (assoc :interfaces (rest class)))))
        (fields '())
        (java   '()))
    (while fields-and-java
      (if (eq :java (first fields-and-java))
          (setf java (append java (list (second fields-and-java)))
                fields-and-java (cddr fields-and-java))
          (setf fields (append fields (list (pop fields-and-java))))))
    `(generate-java-class ,(format "%s%s.java" *dirpath* class-name)
                          *java-current-package* ',class-name
                          :superclass ',superclass
                          :interfaces ',interfaces
                          :fields ',fields
                          :java ',java)))


(define-entity (UApplication (:superclass Object) (:interfaces java.lang.Comparable))
  (url String)
  (anti_hacking_protocol String)
  (service_proximity_uuid String)
  (secure_proximity_uuid String)
  (normal_proximity_uuid String)
  (environment String)
  (namespace_uid String)
  (name String)
  (id Integer)
  :java "
  public int compareTo(Object other){
    if(other instanceof UApplication){
      return this.name.compareTo(((UApplication)other).name);
    }else{
      return -1;
    }
  }
")


;;;; THE END ;;;;
