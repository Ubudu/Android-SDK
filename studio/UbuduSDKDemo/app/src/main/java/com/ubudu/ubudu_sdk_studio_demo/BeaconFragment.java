// -*- mode:java;coding:utf-8 -*-
//****************************************************************************
//FILE:               BeaconFragment.java
//LANGUAGE:           java
//SYSTEM:             Android
//USER-INTERFACE:     Android
//DESCRIPTION
//    
//    Displays beacon informations.
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
package com.ubudu.ubudu_sdk_studio_demo;

import com.ubudu.sdk.UbuduSDK;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BeaconFragment extends UbuduFragment implements OnClickListener{

	private RelativeLayout mActiveSpot;
    private RelativeLayout mSendLogsView;
    private RelativeLayout mClearLogsView;

	public static BeaconFragment newInstance() {
		return new BeaconFragment();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.beacon_fragment, container, false);
		
		create((TextView) view.findViewById(R.id.startB), (TextView) view.findViewById(R.id.infoLabel), ((MainActivity) getActivity()).getOutput());
		
		mActiveSpot = (RelativeLayout) view.findViewById(R.id.activeSpot);
		mActiveSpot.setOnClickListener(this);
        mSendLogsView = (RelativeLayout) view.findViewById(R.id.sendLogsView);
        mSendLogsView.setOnClickListener(this);
        mClearLogsView = (RelativeLayout) view.findViewById(R.id.clearLogsView);
        mClearLogsView.setOnClickListener(this);
		
		return view;
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		activateBluetoothIfPossible(((MainActivity)getActivity()).getOutput());
	}
	
	@Override
	public int getMode() {
		return BEACON;
	}
	
	private boolean activateBluetoothIfPossible(TextOutput textOutput) {
		BluetoothAdapter bt = BluetoothAdapter.getDefaultAdapter();
		// Check Availability of bluetooth
		if (bt == null) {
			textOutput.printf("Bluetooth Not Available on this device.\n");
			return false;
		} else {
			if (!bt.isEnabled()) {
				bt.enable();
				textOutput.printf("Bluetooth enabled.\n");
			}
			return true;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.activeSpot:
			{
				if(!isBeaconsScanningActive()) {
					getTextOutput().printf("Start searching for beacons\n");
					Error e = ((MainActivity)getActivity()).getBeaconManager().start(getActivity());
					startScanning(e);
				} else {
					((MainActivity)getActivity()).getBeaconManager().stop(getActivity());
					stopScanning();
				}
                break;
			}
            case R.id.sendLogsView:
            {
                String[] TO = {"warsaw@ubudu.biz"};

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");

                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "UDUDU DEV SDK LOGS");

                UbuduSDK sdk=UbuduSDK.getSharedInstance(getActivity().getApplicationContext());
                String logs = sdk.debugFileContent();
                emailIntent.putExtra(Intent.EXTRA_TEXT, logs);

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    getActivity().finish();
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity().getApplicationContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.clearLogsView:
            {
                UbuduSDK sdk=UbuduSDK.getSharedInstance(getActivity().getApplicationContext());
                sdk.clearDebugFile(getActivity().getApplicationContext());
                break;
            }
		}
	}
	
	
}
