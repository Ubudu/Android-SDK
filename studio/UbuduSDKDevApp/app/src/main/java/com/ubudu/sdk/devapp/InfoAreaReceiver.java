// -*- mode:java;coding:utf-8 -*-
//****************************************************************************
//FILE:               InfoAreaReceiver.java
//LANGUAGE:           java
//SYSTEM:             Android
//USER-INTERFACE:     Android
//DESCRIPTION
//    
//    A Broadcast recever to receive information from the SDK.
//    
//AUTHORS
//    <PJB> Pascal J. Bourguignon <pjb@informatimago.com>
//MODIFICATIONS
//    2014-08-27 <PJB> Added this header.
//BUGS
//    TODO: I'm not sure it's used anymore; check the SDK sources…
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

import com.ubudu.sdk.UbuduArea;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

public class InfoAreaReceiver extends BroadcastReceiver {
  @SuppressWarnings("unused")
  private static final String TAG = "ubudu.InfoAreaReceiver";

  TextOutput mOutput;


  public InfoAreaReceiver(TextOutput output){
    mOutput=output;
  }
  @Override
  public void onReceive(Context context,Intent intent){
    Bundle extras=intent.getExtras();
    if(extras!=null){
      if (!TextUtils.isEmpty(extras.getString("server_url")))
      {
        mOutput.printf("UbuduSDK Sent server notification url: %s\n",extras.getString("server_url"));
      }
      if (extras.getSerializable("area_entered") != null)
      {
        UbuduArea area = (UbuduArea) extras.getSerializable("area_entered");
        mOutput.printf("UbuduSDK Entered area: %s\n",area.id());
      }
    }
  }
            
}

