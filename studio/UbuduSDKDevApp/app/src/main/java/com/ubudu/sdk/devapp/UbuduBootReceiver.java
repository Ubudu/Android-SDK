package com.ubudu.sdk.devapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ubudu.sdk.UbuduSDK;

public class UbuduBootReceiver extends BroadcastReceiver{
  private static final String TAG ="UbuduBootReceiver";
  
  @Override
  public void onReceive(Context context,Intent intent){
    Log.d(TAG,"receive boot completed");
    
    // UbuduSDK sdk=UbuduSDK.getSharedInstance(context);
    // sdk.startFromBoot(context);
    // sdk.release(context);
    
    UbuduSDK.startFromBoot(context);
  }
  
}
