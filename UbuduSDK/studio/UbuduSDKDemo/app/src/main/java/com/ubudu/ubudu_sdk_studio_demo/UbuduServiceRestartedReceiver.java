package com.ubudu.ubudu_sdk_studio_demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ubudu.sdk.UbuduBeaconManager;
import com.ubudu.sdk.UbuduSDK;

/**
 * Created by zioolek on 16.07.15.
 */
public class UbuduServiceRestartedReceiver extends BroadcastReceiver{

    private static final String NAMESPACE = "ed2f594c2eb20f3a1213e387af53cd86fa1f70e0";
    private static final String TAG = "UbuduServiceRestartedReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        UbuduSDK mUbuduSdk = UbuduSDK.getSharedInstance(context);
        mUbuduSdk.setNamespace(NAMESPACE);

        DemoAreaDelegate delegate = new DemoAreaDelegate();
        UbuduBeaconManager mBeaconManager = mUbuduSdk.getBeaconManager();
        mBeaconManager.setAreaDelegate(delegate);
        mBeaconManager.setEnableAutomaticUserNotificationSending(false);

        android.util.Log.e(TAG, "UbuduServiceRestartedReceiver task completed. Delegate: "+delegate.toString());

    }

}
