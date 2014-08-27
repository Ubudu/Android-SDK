// -*- mode:java;coding:utf-8 -*-
//****************************************************************************
//FILE:               Pref.java
//LANGUAGE:           java
//SYSTEM:             Android
//USER-INTERFACE:     Android
//DESCRIPTION
//    
//    Stores and retrieve application preferences.
//    
//AUTHORS
//    <PJB> Pascal J. Bourguignon <pjb@informatimago.com>
//MODIFICATIONS
//    2014-08-27 <PJB> Added this header.
//BUGS
//LEGAL
//    ubudu-public
//    
//    Copyright (c) 2011-2014, UBUDU SAS
//    All rights reserved.
//    
//    Redistribution and use in source and binary forms, with or without
//    modification, are permitted provided that the following conditions are met:
//    
//    * Redistributions of source code must retain the above copyright notice, this
//      list of conditions and the following disclaimer.
//    
//    * Redistributions in binary form must reproduce the above copyright notice,
//      this list of conditions and the following disclaimer in the documentation
//      and/or other materials provided with the distribution.
//    
//    THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
//    AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
//    IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
//    DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
//    FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
//    DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
//    SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
//    CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
//    OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
//    OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
//****************************************************************************
package com.ubudu.sdk.devapp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import android.content.Context;
import java.lang.String;
import android.content.SharedPreferences;

public class Pref
{
  public static final String PREF_FILE = "com.ubudu.sdk.devapp.preferences";
  protected SharedPreferences mSharedPref;
  public Pref(Context ctx){
    mSharedPref=ctx.getSharedPreferences(PREF_FILE,Context.MODE_PRIVATE);
  }

  public String getPreferenceString(String name,String defaultString){
    return mSharedPref.getString(name,defaultString);
  }
  
  public String getPreferenceString(String name){
    return getPreferenceString(name,"");
  }
  
  public boolean getPreferenceBoolean(String name,boolean defaultBoolean){
    return mSharedPref.getBoolean(name,defaultBoolean);
  }
  
  public boolean getPreferenceBoolean(String name){
    return getPreferenceBoolean(name,false);
  }
  
  public int getPreferenceInt(String name,int defaultInt){
    return mSharedPref.getInt(name,defaultInt);
  }
  
  public int getPreferenceInt(String name){
    return getPreferenceInt(name,0);
  }
  
  public Set<String> getPreferenceStringSet(String name,Set<String> defaultSet){
    return mSharedPref.getStringSet(name,defaultSet);
  }

  public void setPreference(String name,String value){
    SharedPreferences.Editor editor=mSharedPref.edit();
    editor.putString(name,value);
    editor.commit();
  }
  
  public void setPreference(String name,boolean value){
    SharedPreferences.Editor editor=mSharedPref.edit();
    editor.putBoolean(name,value);
    editor.commit();
  }
  
  public void setPreference(String name,int value){
    SharedPreferences.Editor editor=mSharedPref.edit();
    editor.putInt(name,value);
    editor.commit();
  }

  public void setPreference(String name,Set<String> value){
    SharedPreferences.Editor editor=mSharedPref.edit();
    editor.putStringSet(name,value);
    editor.commit();
  }

  public Set<String> mapToSet(java.util.Map<String,String> map){
    HashSet<String> set=new HashSet<String>(map.size());
    for(java.util.Map.Entry<String,String> entry:map.entrySet()){
      set.add(String.format("%s=%s",entry.getKey(),entry.getValue()));
    }
    return set;
  }

  public java.util.Map<String,String> setToMap(Set<String> set){
    HashMap<String,String> map=new HashMap<String,String>(set.size());
    for(String entry:set){
      int equal=entry.indexOf("=");
      if(equal<0){
        map.put(entry,"");
      }else{
        map.put(entry.substring(0,equal),entry.substring(equal+1));
      }
    }
    return map;
  }

}
