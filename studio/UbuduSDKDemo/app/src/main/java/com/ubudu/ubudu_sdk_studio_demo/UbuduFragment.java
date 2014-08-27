// -*- mode:java;coding:utf-8 -*-
//****************************************************************************
//FILE:               UbuduFragment.java
//LANGUAGE:           java
//SYSTEM:             Android
//USER-INTERFACE:     Android
//DESCRIPTION
//    
//    Displays buttons to select geofences or beacons.
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

import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.ubudu.sdk.UbuduSDK;

public class UbuduFragment extends Fragment {

	public static final int BEACON = 1;
	public static final int GEOFENCE = 2;

	private TextView mActionButtonStatus;
	private TextView mInfoLabel;
	private TextOutput mTextOutput;

	private boolean mBeaconsScanningActive = false;
	private boolean mGeofencesScanningActive = false;

	/**
	 * Has to be overrided by children
	 * 
	 * @param actionButtonStatus
	 * @param infoLabel
	 * @param textOutput
	 */
	public void create(TextView actionButtonStatus, TextView infoLabel, TextOutput textOutput) {
		this.mActionButtonStatus = actionButtonStatus;
		this.mInfoLabel = infoLabel;
		this.mTextOutput = textOutput;

		// Check are managers searching. If yes refresh views.
		if (UbuduSDK.getSharedInstance(getActivity().getApplicationContext()).getBeaconManager()
				.isMonitoring()) {
			mBeaconsScanningActive = true;
		}
		if (UbuduSDK.getSharedInstance(getActivity().getApplicationContext()).getGeofenceManager()
				.isMonitoring()) {
			mGeofencesScanningActive = true;
		}
		refreshActionButtonState();
	}

	/**
	 * Override by children
	 * 
	 * @return type of scan
	 */
	public int getMode() {
		return -1;
	}

	public TextView getActionButton() {
		return mActionButtonStatus;
	}

	public TextView getInfoLabel() {
		return mInfoLabel;
	}

	public TextOutput getTextOutput() {
		return mTextOutput;
	}

	public boolean isBeaconsScanningActive() {
		return mBeaconsScanningActive;
	}

	public boolean isGeofencesScanningActive() {
		return mGeofencesScanningActive;
	}

	private void refreshActionButtonState() {
		if (!isBeaconsScanningActive() && getMode() == BEACON) {
			mInfoLabel.setText("Press button to start searching for beacons");
			mActionButtonStatus.setText("Start");
		} else if (isBeaconsScanningActive() && getMode() == BEACON) {
			mInfoLabel.setText("Press button to stop searching for beacons");
			mActionButtonStatus.setText("Stop");
		}

		if (!isGeofencesScanningActive() && getMode() == GEOFENCE) {
			mInfoLabel.setText("Press button to start searching for geofences");
			mActionButtonStatus.setText("Start");
		} else if (isGeofencesScanningActive() && getMode() == GEOFENCE) {
			mInfoLabel.setText("Press button to stop searching for geofences");
			mActionButtonStatus.setText("Stop");
		}
	}

	public void startScanning(Error e) {

		if (getMode() == BEACON) {
			mBeaconsScanningActive = true;
			refreshActionButtonState();
		}
		if (getMode() == GEOFENCE) {
			mGeofencesScanningActive = true;
			refreshActionButtonState();

		}
		if (e != null) {
			getTextOutput().printf("Error: %s\n", e.toString());
		}
	}

	public void stopScanning() {

		if (getMode() == BEACON) {
			getTextOutput().printf("Stop searching for beacons\n");
			mBeaconsScanningActive = false;
			refreshActionButtonState();
		}
		if (getMode() == GEOFENCE) {
			getTextOutput().printf("Stop searching for geofences\n");
			mGeofencesScanningActive = false;
			refreshActionButtonState();
		}
	}

}
