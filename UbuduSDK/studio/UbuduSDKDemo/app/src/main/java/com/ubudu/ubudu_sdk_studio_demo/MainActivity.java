// -*- mode:java;coding:utf-8 -*-
//****************************************************************************
//FILE:               MainActivity.java
//LANGUAGE:           java
//SYSTEM:             Android
//USER-INTERFACE:     Android
//DESCRIPTION
//    
//    The Ubudu SDK Dev App main activity.
//    
//AUTHORS
//    <PJB> Pascal J. Bourguignon <pjb@informatimago.com>
//MODIFICATIONS
//    2014-08-27 <PJB> Added this header.
//BUGS
//LEGAL
//    ubudu-public
//    
//    Copyright (c) 2011-2015, UBUDU SAS
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
package com.ubudu.ubudu_sdk_studio_demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.ubudu.sdk.UbuduBeacon;
import com.ubudu.sdk.UbuduBeaconManager;
import com.ubudu.sdk.UbuduGeofenceManager;
import com.ubudu.sdk.UbuduRangedBeaconsNotifier;
import com.ubudu.sdk.UbuduSDK;

import java.util.Iterator;
import java.util.List;

public class MainActivity extends FragmentActivity implements TextOutput {
	
	// Set your own app namespace here
   	private static final String NAMESPACE = "2aa646df699c0fd89d2edb862e9ed2f47fde6251";

	private UbuduPagerAdapter mUbuduPagerAdapter;
	private ViewPager mViewPager;
    private TextView mOutputText;

	private UbuduSDK mUbuduSdk;
	
	private UbuduBeaconManager mBeaconManager;
	private UbuduGeofenceManager mGeofenceManager;

    private DemoAreaDelegate mAreaDelegate;


	@Override
	protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_main);

        mUbuduPagerAdapter = new UbuduPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mUbuduPagerAdapter);
		mOutputText = (TextView) findViewById(R.id.outputText);
		mOutputText.setMovementMethod(new ScrollingMovementMethod());
		
		mUbuduSdk = UbuduSDK.getSharedInstance(getApplicationContext());
		mUbuduSdk.setNamespace(NAMESPACE);
        mUbuduSdk.setFileLogEnabled(true);

        mAreaDelegate = new DemoAreaDelegate(this);

		mGeofenceManager = mUbuduSdk.getGeofenceManager();
        mGeofenceManager.setAreaDelegate(mAreaDelegate);

		mBeaconManager = mUbuduSdk.getBeaconManager();
        mBeaconManager.setEnableAutomaticUserNotificationSending(true);
        mBeaconManager.setAreaDelegate(mAreaDelegate);

        mBeaconManager.setRangedBeaconsNotifier(new UbuduRangedBeaconsNotifier() {

            @Override
            public void didRangeBeacons(final List<UbuduBeacon> rangedBeacons) {
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run() {
                        android.util.Log.e("", "beacons ranged: " + rangedBeacons.size());
                        Iterator<UbuduBeacon> iter = rangedBeacons.iterator();
                        while (iter.hasNext()) {
                            UbuduBeacon b = iter.next();
                            printf("name: " + b.name() + ", rssi: " + b.rssi() + ", minor: " + b.minor() + ", major: " + b.major());
                        }
                    }
                });
            }
        });


    }

    public UbuduSDK getUbuduSDK() {
        return mUbuduSdk;
    }

    public UbuduBeaconManager getBeaconManager() {
        return mBeaconManager;
    }

    public UbuduGeofenceManager getGeofenceManager() {
        return mGeofenceManager;
    }

    public DemoAreaDelegate getAreaDelegate() {
        return mAreaDelegate;
    }
	
	public TextOutput getOutput() {
		return this;
	}

    @Override
    public void printf(String formatControl, Object... arguments) {
        final String newText = String.format(formatControl + "\n", arguments);
        synchronized (mOutputText) {
            mOutputText.append(newText);
            final Layout layout = mOutputText.getLayout();
            if (layout != null) {
                final int scrollAmount = layout.getLineTop(mOutputText.getLineCount()) - mOutputText.getHeight();
                mOutputText.scrollTo(0, ((0 < scrollAmount) ? scrollAmount : 0));
            }
        }
    }

}
