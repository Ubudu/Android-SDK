// -*- mode:java;coding:utf-8 -*-
//****************************************************************************
//FILE:               ErrorReceiver.java
//LANGUAGE:           java
//SYSTEM:             Android
//USER-INTERFACE:     Android
//DESCRIPTION
//    
//    A BroadcastReceiver to receive error from the Ubudu SDK (not used anymore).
//    
//AUTHORS
//    <PJB> Pascal J. Bourguignon <pjb@informatimago.com>
//MODIFICATIONS
//    2014-08-27 <PJB> Added this header.
//BUGS
//    TODO: this is not used anymore.  Check the sources of UbuduSDK.
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

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class ErrorReceiver extends BroadcastReceiver {
  private static final String TAG = "ubudu.ErrorReceiver";

  TextOutput mOutput;

  public ErrorReceiver(TextOutput output){
    mOutput=output;
  }

  @Override
  public void onReceive(Context context,Intent intent){
    Bundle extras=intent.getExtras();
    if(extras!=null){
      Exception exception=(Exception)extras.getSerializable("error");
      if(exception!=null){
        mOutput.printf("UbuduSDK Error: %s\n",exception.getMessage());
      }else{
        String message=(String)extras.getSerializable("errorMsg");
        if(message!=null){
          mOutput.printf("UbuduSDK Error: %s\n",message);
        } else {
          mOutput.printf("%s: Received an intent with strange extras %s\n",TAG,extras);
        }
        // Log.d(TAG, "Received error message");
        // Toast.makeText(context, "Error message " + exception.getMessage(),
        //                Toast.LENGTH_LONG).show();
      }
    } else {
      mOutput.printf("%s: Received an intent with null extras\n",TAG);
    }
  }


}
