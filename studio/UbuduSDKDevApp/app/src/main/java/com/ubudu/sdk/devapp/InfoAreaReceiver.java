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

