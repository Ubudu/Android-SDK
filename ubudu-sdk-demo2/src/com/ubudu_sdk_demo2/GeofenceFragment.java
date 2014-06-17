package com.ubudu_sdk_demo2;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesUtil;

public class GeofenceFragment extends UbuduFragment implements OnClickListener {

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
