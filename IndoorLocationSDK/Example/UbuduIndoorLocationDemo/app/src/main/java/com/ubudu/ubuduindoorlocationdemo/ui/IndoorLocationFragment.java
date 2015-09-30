// -*- mode:java;coding:utf-8 -*-
//****************************************************************************
//FILE:               IndoorLocationFragment.java
//LANGUAGE:           java
//SYSTEM:             Android
//USER-INTERFACE:     Android
//DESCRIPTION
//    
//    Displays indoor location on a map.
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

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.model.LatLng;
import com.ubudu.indoorlocation.UbuduCoordinates2D;
import com.ubudu.indoorlocation.UbuduIndoorLocationManager;
import com.ubudu.indoorlocation.UbuduPoint;
import com.ubudu.indoorlocation.UbuduZone;
import com.ubudu.ubuduindoorlocationdemo.R;
import com.ubudu.ubuduindoorlocationdemo.utils.DelegateAppInterface;
import com.ubudu.ubuduindoorlocationdemo.utils.Map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IndoorLocationFragment extends Fragment implements View.OnClickListener {

	private static final int REQUEST_ENABLE_BT_FOR_IL = 1014;

	private RelativeLayout mActiveSpot;
	private Map mMap;

	private UbuduIndoorLocationManager mIndoorLocationManager;

	private TextView mActionButtonStatus;
	private TextView mInfoLabel;
	private DelegateAppInterface mTextOutput;

	private boolean mScanning = false;

	SensorManager mSensorManager;
	Sensor mSensor;

	private static IndoorLocationFragment instance = null;

	public static IndoorLocationFragment getInstance(){
		if(instance==null)
			instance = new IndoorLocationFragment();
		return instance;
	}

	/**
	 * Children classes must call this
	 */
	public void create(UbuduIndoorLocationManager indoorLocationManagerManager, TextView actionButtonStatus, TextView infoLabel, DelegateAppInterface textOutput) {
		this.mIndoorLocationManager = indoorLocationManagerManager;
		this.mActionButtonStatus = actionButtonStatus;
		this.mInfoLabel = infoLabel;
		this.mTextOutput = textOutput;

		// Check if manager is started. If yes refresh UI.
		if (getIndoorLocationManager().isRunning()) {
			mScanning = true;
		}

		refreshActionButtonState();
	}

	public UbuduIndoorLocationManager getIndoorLocationManager() {
		return mIndoorLocationManager;
	}

	public DelegateAppInterface getTextOutput() {
		return mTextOutput;
	}

	public boolean isScanning() {
		return mScanning;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_ENABLE_BT_FOR_IL) {
			if (BluetoothAdapter.getDefaultAdapter().getState() == BluetoothAdapter.STATE_ON) {
				if (getTextOutput() != null)
					getTextOutput().printf(getResources().getString(R.string.bt_enabled));
				startScanning();
			} else{
				if (getTextOutput() != null)
					getTextOutput().printf(getResources().getString(R.string.bt_turned_off));
			}
		}
	}

	public void startScanning() {
		if (BluetoothAdapter.getDefaultAdapter().getState() != BluetoothAdapter.STATE_ON) {
			this.activateBluetooth(REQUEST_ENABLE_BT_FOR_IL);
		} else {
			getTextOutput().printf(getResources().getString(R.string.start_il));
			getIndoorLocationManager().start();
			mScanning = true;
			refreshActionButtonState();
			startLoadingDialog();
		}
	}

	private static MaterialDialog mB;

	private void startLoadingDialog() {

		mB = new MaterialDialog.Builder(getActivity())
				.content(getResources().getString(R.string.fetching_map))
				.progress(true, 0)
				.autoDismiss(false)
				.cancelable(false)
				.show();
	}

	protected static void dismissDialog() {
		if(mB!=null)
			mB.dismiss();
	}

	public void stopScanning() {
		getTextOutput().printf(getResources().getString(R.string.il_stopped));
		getIndoorLocationManager().stop();
		mScanning = false;
		refreshActionButtonState();
	}

	public void started(){
		mScanning = true;
		refreshActionButtonState();
		mMap.setMapImageBounds(new LatLng(mIndoorLocationManager.map().bottomRightAnchorCoordinates().toDeg().latitude(),
						mIndoorLocationManager.map().topLeftAnchorCoordinates().toDeg().longitude()),
				new LatLng(mIndoorLocationManager.map().topLeftAnchorCoordinates().toDeg().latitude(),
						mIndoorLocationManager.map().bottomRightAnchorCoordinates().toDeg().longitude()));
		mB.setContent(getResources().getString(R.string.fetching_map_overlay));
		mMap.initMapOverlay(mIndoorLocationManager.map().imageUrl());
	}

	public void stopped(){
		mScanning = false;
		refreshActionButtonState();
	}

	private void refreshActionButtonState() {
		if (!isScanning()) {
			mInfoLabel.setText(getResources().getString(R.string.press_button_to_start));
			mActionButtonStatus.setText(getResources().getString(R.string.button_start));
		} else {
			mInfoLabel.setText(getResources().getString(R.string.press_button_to_stop));
			mActionButtonStatus.setText(getResources().getString(R.string.button_stop));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.indoorlocation_fragment, container, false);

		MainActivity mainActivity = (MainActivity) getActivity();

		create(mainActivity.getmIndoorLocationManager(),
				(TextView) view.findViewById(R.id.startB),
				(TextView) view.findViewById(R.id.infoLabel),
				mainActivity.getOutput());

		mActiveSpot = (RelativeLayout) view.findViewById(R.id.activeSpot);
		mActiveSpot.setOnClickListener(this);
		mMap = new Map(mainActivity, R.id.map);

		mainActivity.getIndoorLocationDelegate().setMap(mMap);

		return view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		checkGooglePlayServices();
		mSensorManager = (SensorManager) getContext().getSystemService(getContext().SENSOR_SERVICE);
		mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
            case R.id.activeSpot: {
                if (!isScanning()) {
                    startScanning();
                } else {
                    stopScanning();
                }
            }
		}
	}

	private boolean activateBluetooth(int requestCode) {
		BluetoothAdapter bt = BluetoothAdapter.getDefaultAdapter();
		// Check Availability of bluetooth
		if (bt == null) {
			if (getTextOutput() != null)
				getTextOutput().printf("Bluetooth Not Available on this device.");
			return false;
		} else {
			if (!bt.isEnabled()) {
				Intent intentBtEnabled = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
				startActivityForResult(intentBtEnabled, requestCode);
			}
			return true;
		}
	}

	public synchronized void highlightZones(List<UbuduZone> list) {
		mMap.clearHighlightedZones();
		Iterator<UbuduZone> iter = list.iterator();
		while(iter.hasNext()) {
			UbuduZone zone = iter.next();
			List<LatLng> coords = new ArrayList<LatLng>();
			Iterator<UbuduPoint> iter1 = zone.coordinates().iterator();
			while(iter1.hasNext()){
				UbuduPoint p = iter1.next();
				UbuduCoordinates2D c = mIndoorLocationManager.geoCoordinates(p);
				coords.add(new LatLng(c.latitude(),c.longitude()));
			}
			mMap.highlightZone(coords,zone.name());
		}
	}

	private class GooglePlayCancelListener implements DialogInterface.OnCancelListener {
		protected Activity activity;

		public GooglePlayCancelListener(Activity activity) {
			this.activity = activity;
		}

		public void onCancel(DialogInterface dialog) {
			activity.finish();
		}
	}

	public void checkGooglePlayServices() {
		int result = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity());
		if (com.google.android.gms.common.ConnectionResult.SUCCESS != result) {
			GooglePlayServicesUtil.getErrorDialog(result, getActivity(), 0, new GooglePlayCancelListener(getActivity()));
		}
	}

	public void onResume(){
		super.onResume();
		if(mMap!= null) {
			//init sensor
			mSensorManager.registerListener(mMap, mSensor, SensorManager.SENSOR_DELAY_UI);
		}
	}

	public void onPause(){
		super.onPause();
		if(mMap!= null)
			mSensorManager.unregisterListener(mMap);
	}
}
