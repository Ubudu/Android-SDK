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
