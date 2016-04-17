package com.ubudu.ubudu_sdk_studio_demo;

import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.ubudu.sdk.UbuduAreaManager;

public abstract class UbuduBaseFragment extends Fragment {

    public static final int BEACON = 1;
    public static final int GEOFENCE = 2;

    private UbuduAreaManager mAreaManager;

	private TextView mActionButtonStatus;
	private TextView mInfoLabel;
	private TextOutput mTextOutput;

	private boolean mScanning = false;

	/**
	 * Children classes must call this
	 */
	public void create(UbuduAreaManager areaManager, TextView actionButtonStatus, TextView infoLabel, TextOutput textOutput) {
        this.mAreaManager = areaManager;
		this.mActionButtonStatus = actionButtonStatus;
		this.mInfoLabel = infoLabel;
		this.mTextOutput = textOutput;

		// Check if manager is started. If yes refresh UI.
        if (getAreaManager().isMonitoring()) {
            mScanning = true;
        }

		refreshActionButtonState();
	}

	/**
	 * @return type of scan performed by the manager linked to this fragment
	 */
	public abstract int getMode();

    /**
     * @return Pluralized name of the type or areas handled by the manager linked to this fragment
     */
    public abstract String getManagerAreasType();

    public UbuduAreaManager getAreaManager() {
        return mAreaManager;
    }

	public TextOutput getTextOutput() {
		return mTextOutput;
	}

	public boolean isScanning() {
        return mScanning;
	}

	public void startScanning() {
        getTextOutput().printf("Start searching for " + getManagerAreasType());
        boolean started = getAreaManager().start();
        mScanning = true;
        refreshActionButtonState();
		if (!started) {
			getTextOutput().printf("Start error");
		}
	}

	public void stopScanning() {
        getTextOutput().printf("Stop searching for " + getManagerAreasType());
        getAreaManager().stop();
        mScanning = false;
        refreshActionButtonState();
	}

    private void refreshActionButtonState() {
        if (!isScanning()) {
            mInfoLabel.setText("Press button to start searching for " + getManagerAreasType());
            mActionButtonStatus.setText("Start");
        } else {
            mInfoLabel.setText("Press button to stop searching for " + getManagerAreasType());
            mActionButtonStatus.setText("Stop");
        }
    }
}
