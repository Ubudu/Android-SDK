package com.ubudu_sdk_demo2;

import com.ubudu.sdk.UbuduSDK;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class UbuduFragment extends Fragment {

	public static final int BEACON = 1;
	public static final int GEOFENCE = 2;

	private TextView mActionButtonStatus;
	private TextView mInfoLabel;
	private TextOutput mTextOutput;

	private boolean mScanningActive = false;

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
				.isMonitoring()
				|| UbuduSDK.getSharedInstance(getActivity().getApplicationContext())
						.getGeofenceManager().isMonitoring()) {
			mScanningActive = true;
			if (getMode() == BEACON) {
				refreshActionButtonState("beacons");
			}
			if (getMode() == GEOFENCE) {
				refreshActionButtonState("geofences");
			}
		}
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

	public boolean isScanningActive() {
		return mScanningActive;
	}

	private void refreshActionButtonState(String s) {
		if (!isScanningActive()) {
			mInfoLabel.setText("Press button to start searching for " + s);
			mActionButtonStatus.setText("Start");
		} else {
			mInfoLabel.setText("Press button to stop searching for " + s);
			mActionButtonStatus.setText("Stop");
		}
	}

	public void startScanning(Error e) {

		mScanningActive = true;
		if (getMode() == BEACON) {
			refreshActionButtonState("beacons");
		}
		if (getMode() == GEOFENCE) {
			refreshActionButtonState("geofences");
		}
		if (e != null) {
			getTextOutput().printf("Error: %s\n", e.toString());
		}
	}

	public void stopScanning() {
		mScanningActive = false;
		if (getMode() == BEACON) {
			getTextOutput().printf("Stop searching for beacons\n");
			refreshActionButtonState("beacons");
		}
		if (getMode() == GEOFENCE) {
			getTextOutput().printf("Stop searching for geofences\n");
			refreshActionButtonState("geofences");
		}
	}

}
