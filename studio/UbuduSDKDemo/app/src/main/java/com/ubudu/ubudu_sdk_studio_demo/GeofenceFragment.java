// -*- mode:java;coding:utf-8 -*-
//****************************************************************************
//FILE:               GeofenceFragment.java
//LANGUAGE:           java
//SYSTEM:             Android
//USER-INTERFACE:     Android
//DESCRIPTION
//    
//    Displays geofences on a map.
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

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesUtil;

public class GeofenceFragment extends UbuduFragment implements View.OnClickListener {

	private RelativeLayout mActiveSpot;
	private Map mMap;

	public static GeofenceFragment newInstance() {
		return new GeofenceFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.geofence_fragment, container, false);

		create((TextView) view.findViewById(R.id.startB), (TextView) view
				.findViewById(R.id.infoLabel), ((MainActivity) getActivity()).getOutput());

		mActiveSpot = (RelativeLayout) view.findViewById(R.id.activeSpot);
		mActiveSpot.setOnClickListener(this);

		mMap = new Map(getActivity(), R.id.map);
		mMap.updateLocationOnMap();

		((MainActivity) getActivity()).setAreaDelegate(mMap);

		return view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		checkGooglePlayServices();
	}

	@Override
	public int getMode() {
		return GEOFENCE;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.activeSpot: {
			if (!isGeofencesScanningActive()) {
				if (!checkIsGpsEnabled()) {
					showGpsAlertDialog();
					return;
				}
				getTextOutput().printf("Start searching for geofences\n");
				Error e = ((MainActivity) getActivity()).getGeofenceManager().start(getActivity());
				startScanning(e);
			} else {
				((MainActivity) getActivity()).getGeofenceManager().stop(getActivity());
				stopScanning();
			}
		}
		}
	}

	private void showGpsAlertDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage("Please enable location with high accuracy or battery saving mode");
		builder.setPositiveButton("OK", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				getActivity().startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
			}
		});
		builder.setNegativeButton("CANCEL", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	/**
	 * LocationClient uses Google Location Service to location estimates. Google
	 * Location Service working only with High accuracy and Battery saving
	 * location mode. Before start getting locations we have to enable one of
	 * these modes
	 * 
	 * @return true - when location is enabled
	 */
	private boolean checkIsGpsEnabled() {
		LocationManager manager = (LocationManager) getActivity().getSystemService(
				Context.LOCATION_SERVICE);

		if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)
				&& manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
				|| (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER))
				&& manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
			return true;
		}
		return false;
	}

	private class GooglePlayCancelListener implements DialogInterface.OnCancelListener {
		protected Activity activity;

		public GooglePlayCancelListener(Activity activity) {
			this.activity = activity;
		}

		public void onCancel(DialogInterface dialog) {
			activity.finish();
		}
	};

	public void checkGooglePlayServices() {
		int result = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity());
		if (com.google.android.gms.common.ConnectionResult.SUCCESS != result) {
			GooglePlayServicesUtil.getErrorDialog(result, getActivity(), 0,
					new GooglePlayCancelListener(getActivity()));
		}
	}
}
