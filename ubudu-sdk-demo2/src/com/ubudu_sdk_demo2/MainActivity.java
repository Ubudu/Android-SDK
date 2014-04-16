package com.ubudu_sdk_demo2;

import com.ubudu.sdk.UbuduAreaDelegate;
import com.ubudu.sdk.UbuduBeacon;
import com.ubudu.sdk.UbuduBeaconManager;
import com.ubudu.sdk.UbuduSDK;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Layout;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity implements TextOutput {

	private TextView mInfoLabel, mOutputText;
	private ProgressBar mProgressBar;
	private Button mActionButton;

	private InfoAreaReceiver mInfoReceiver;

	private UbuduBeaconManager mBeaconManager;
	private UbuduAreaDelegate mBeaconDelegate;

	// Use your application namespace
	private static final String namespace = "71b309965b54bdd8d3594a1253fb0da408154982";

	private boolean isScanningActive = false;
	private UbuduSDK sdk;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		System.out.println("onCreate()");
		
		mInfoLabel = (TextView) findViewById(R.id.informationLabel);
		mOutputText = (TextView) findViewById(R.id.outputText);
		mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
		mActionButton = (Button) findViewById(R.id.actionButton);

		mActionButton.setOnClickListener(actionButtonListener);
		mOutputText.setMovementMethod(new ScrollingMovementMethod());

		sdk = UbuduSDK.getSharedInstance(getApplicationContext());
		sdk.setNamespace(namespace);

		mBeaconDelegate = new DemoAreaDelegate(this);
		mBeaconManager = sdk.getBeaconManager();
		mBeaconManager.setAreaDelegate(mBeaconDelegate);

		activateBluetoothIfPossible();
	}

	@Override
	public void printf(String formatControl, Object... arguments) {
		final String newText = String.format(formatControl, arguments);
		synchronized (mOutputText) {
			mOutputText.append(newText);
			final Layout layout = mOutputText.getLayout();
			if (layout != null) {
				final int scrollAmount = layout.getLineTop(mOutputText.getLineCount())
						- mOutputText.getHeight();
				mOutputText.scrollTo(0, ((0 < scrollAmount) ? scrollAmount : 0));
			}
		}
	}

	private void registerInfoReceiver() {
		synchronized (this) {
			if (mInfoReceiver == null) {
				mInfoReceiver = new InfoAreaReceiver(this);
				registerReceiver(mInfoReceiver, new IntentFilter("com.ubudu.sdk.notify"));
				this.printf("Registered info receiver.\n");
			}
		}
	}

	private void unregisterInfoReceiver() {
		synchronized (this) {
			if (mInfoReceiver != null) {
				unregisterReceiver(mInfoReceiver);
				mInfoReceiver = null;
				this.printf("Unregistered info receiver.\n");
			}
		}
	}

	private boolean activateBluetoothIfPossible() {
		BluetoothAdapter bt = BluetoothAdapter.getDefaultAdapter();
		// Check Availability of bluetooth
		if (bt == null) {
			this.printf("Bluetooth Not Available on this device.\n");
			return false;
		} else {
			if (!bt.isEnabled()) {
				bt.enable();
			}
			return true;
		}
	}

	private void startSearchingBeacons() {
		this.printf("Start searching for beacons");
		Error e = mBeaconManager.start(this);
		if (e != null) {
			this.printf("Error: %s\n", e.toString());
			isScanningActive = false;
			refreshActionButtonState();
		} else {
			isScanningActive = true;
			refreshActionButtonState();
		}
	}

	private void stopSearchingBeacons() {
		this.printf("Stop searching for beacons");
		mBeaconManager.stop(this);
		
		UbuduSDK.getSharedInstance(getApplicationContext()).release(getApplicationContext());
		
		isScanningActive = false;
		refreshActionButtonState();
	}

	private void refreshActionButtonState() {
		if (!isScanningActive) {
			mInfoLabel.setText("Press button to start searching for beacons");
			mActionButton.setText("START");
		} else {
			mInfoLabel.setText("Press button to stop searching for beacons");
			mActionButton.setText("STOP");
		}
	}
	
	public void onConfigurationChanged(android.content.res.Configuration newConfig) {
		if (isScanningActive) {
			stopSearchingBeacons();
			startSearchingBeacons();
			mInfoLabel.setText("After cofiguration changed");
		}
	};
	
	public void onContentChanged() {
		if (isScanningActive) {
			stopSearchingBeacons();
			startSearchingBeacons();
			mInfoLabel.setText("After content changed");
		}
	};
	
	OnClickListener actionButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (!isScanningActive) {
				startSearchingBeacons();
			} else {
				stopSearchingBeacons();
			}
		}
	};

	@Override
	protected void onResume() {
		super.onResume();
		registerInfoReceiver();
		
		if (sdk.getBeaconManager().isMonitoring()) {
			isScanningActive = true;
			refreshActionButtonState();
		}
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		unregisterInfoReceiver();
	}

}
