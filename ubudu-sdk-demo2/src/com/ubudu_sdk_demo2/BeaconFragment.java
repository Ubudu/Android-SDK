package com.ubudu_sdk_demo2;

import com.ubudu.sdk.UbuduSDK;

import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BeaconFragment extends UbuduFragment implements OnClickListener{

	private RelativeLayout mActiveSpot;
	
	public static BeaconFragment newInstance() {
		return new BeaconFragment();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.beacon_fragment, container, false);
		
		create((TextView) view.findViewById(R.id.startB), (TextView) view.findViewById(R.id.infoLabel), ((MainActivity) getActivity()).getOutput());
		
		mActiveSpot = (RelativeLayout) view.findViewById(R.id.activeSpot);
		mActiveSpot.setOnClickListener(this);
		
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
				if(!isScanningActive()) {
					getTextOutput().printf("Start searching for beacons\n");
					Error e = ((MainActivity)getActivity()).getBeaconManager().start(getActivity());
					startScanning(e);
				} else {
					((MainActivity)getActivity()).getBeaconManager().stop(getActivity());
					//UbuduSDK.getSharedInstance(getActivity().getApplicationContext()).release(getActivity().getApplicationContext());
					stopScanning();
				}
			}
		}
	}
	
	
}
