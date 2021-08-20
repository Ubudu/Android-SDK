package com.ubudu.ubudu_sdk_studio_demo;

import com.ubudu.sdk.UbuduSDK;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BeaconFragment extends UbuduBaseFragment implements OnClickListener{

	private static final int ASK_GEOLOCATION_PERMISSION_REQUEST = 0;

	private RelativeLayout mActiveSpot;
    private RelativeLayout mSendLogsView;
    private RelativeLayout mClearLogsView;

	public static BeaconFragment newInstance() {
		return new BeaconFragment();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.beacon_fragment, container, false);
		
		super.create(((MainActivity)getActivity()).getBeaconManager(),
                (TextView) view.findViewById(R.id.startB),
                (TextView) view.findViewById(R.id.infoLabel),
                ((MainActivity) getActivity()).getOutput());
		
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

    @Override
    public String getManagerAreasType() {
        return "beacons";
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
	public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
		switch (requestCode) {
			case ASK_GEOLOCATION_PERMISSION_REQUEST: {
				if (!isScanning()) {
					startScanning();
				} else {
					stopScanning();
				}
			}
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.activeSpot:
			{
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
						&& ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
					requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ASK_GEOLOCATION_PERMISSION_REQUEST);
					return;
				}
				if (!isScanning()) {
					startScanning();
				} else {
                    stopScanning();
				}
                break;
			}
            case R.id.sendLogsView:
            {
                String[] TO = {};

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");

                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "UDUDU DEV SDK LOGS");

                UbuduSDK sdk = ((MainActivity)getActivity()).getUbuduSDK();
                String logs = sdk.debugFileContent();
                emailIntent.putExtra(Intent.EXTRA_TEXT, logs);

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send email..."));
                    getActivity().finish();
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity().getApplicationContext(), "No email client installed.", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.clearLogsView:
            {
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.getUbuduSDK().clearDebugFile(getActivity());
                break;
            }
		}
	}
}
