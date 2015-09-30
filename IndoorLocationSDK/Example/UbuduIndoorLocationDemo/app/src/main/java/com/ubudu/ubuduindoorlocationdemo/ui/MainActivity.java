// -*- mode:java;coding:utf-8 -*-
//****************************************************************************
//FILE:               MainActivity.java
//LANGUAGE:           java
//SYSTEM:             Android
//USER-INTERFACE:     Android
//DESCRIPTION
//    
//    The Ubudu Indoor Location SDK main activity
//    
//AUTHORS
//    <MG> Michal Gasztold <michal.gasztold@ubudu.biz>
//MODIFICATIONS
//    2014-09-08 <MG> Added this header.
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
package com.ubudu.ubuduindoorlocationdemo.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ubudu.indoorlocation.UbuduIndoorLocationManager;
import com.ubudu.indoorlocation.UbuduZone;
import com.ubudu.sdk.UbuduSDK;
import com.ubudu.ubuduindoorlocationdemo.utils.DelegateAppInterface;
import com.ubudu.ubuduindoorlocationdemo.delegate.IndoorLocationDelegate;
import com.ubudu.ubuduindoorlocationdemo.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends FragmentActivity implements DelegateAppInterface {

    private static final int MAX_LINE_COUNT = 100;

    List<String> logs = new ArrayList<String>();

	private UbuduPagerAdapter mUbuduPagerAdapter;
	private ViewPager mViewPager;
    private TextView mOutputText;

    private UbuduIndoorLocationManager mIndoorLocationManager;

    private IndoorLocationDelegate mIndoorLocationDelegate;

	@Override
	protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_main);

        mUbuduPagerAdapter = new UbuduPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mUbuduPagerAdapter);
		mOutputText = (TextView) findViewById(R.id.outputText);
		mOutputText.setMovementMethod(new ScrollingMovementMethod());

        mIndoorLocationDelegate = new IndoorLocationDelegate(this);

        mIndoorLocationManager = UbuduSDK.getSharedInstance(getApplicationContext()).getIndoorLocationManager();
        mIndoorLocationManager.setIndoorLocationDelegate(mIndoorLocationDelegate);
        mIndoorLocationManager.loadMapWithKey("e55e79c03849013362a51ec7cc7aaf9f");
	}

    public UbuduIndoorLocationManager getmIndoorLocationManager(){return mIndoorLocationManager;}

    public IndoorLocationDelegate getIndoorLocationDelegate() {
        return mIndoorLocationDelegate;
    }

	public DelegateAppInterface getOutput() {
		return this;
	}

    @Override
    public void printf(String formatControl, Object... arguments) {
        final String newText = String.format(formatControl + "\n", arguments);
        Date d = new Date();
        String log = "[" + d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds() + "] " + newText;
        logs.add(log);

        if(logs.size()>MAX_LINE_COUNT) logs.remove(0);

        String logString = "";
        for (String str : logs) {
            logString += str + "\n";
        }

        synchronized (mOutputText) {
            mOutputText.setText(logString);
            final Layout layout = mOutputText.getLayout();
            if (layout != null) {
                final int scrollAmount = layout.getLineTop(mOutputText.getLineCount()) - mOutputText.getHeight();
                mOutputText.scrollTo(0, ((0 < scrollAmount) ? scrollAmount : 0));
            }
        }
    }

    @Override
    public Context context() {
        return this.getApplicationContext();
    }

    @Override
    public void tellAppILStarted() {
        IndoorLocationFragment.getInstance().started();
        printf("Indoor Location started successfully");
    }

    @Override
    public void tellAppILStartFailed() {
        IndoorLocationFragment.getInstance().stopped();
        printf("Indoor Location failed to start. Check the internet connection and bluetooth settings");
    }

    @Override
    public void highlightZones(List<UbuduZone> list) {
        IndoorLocationFragment.getInstance().highlightZones(list);
    }

    public void notifyMapOverlayFetched() {
        IndoorLocationFragment.dismissDialog();
    }

    public void notifyMapOverlayOutOfMemoryException() {
        IndoorLocationFragment.dismissDialog();
        MaterialDialog dialog;
        dialog = new MaterialDialog.Builder(this)
                .content(getResources().getString(R.string.map_overaly_oom))
                .autoDismiss(false)
                .cancelable(false)
                .positiveText(getResources().getString(R.string.ok))
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        dialog.dismiss();
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                    }
                })
                .show();
    }
}
