package com.ubudu_sdk_demo2;

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
