package com.ubudu.ubudu_sdk_studio_demo;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesUtil;

public class GeofenceFragment extends UbuduBaseFragment implements View.OnClickListener {

	private static final int ASK_GEOLOCATION_PERMISSION_REQUEST = 0;

	private RelativeLayout mActiveSpot;
	private Map mMap;

	public static GeofenceFragment newInstance() {
		return new GeofenceFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.geofence_fragment, container, false);

        MainActivity mainActivity = (MainActivity) getActivity();

        super.create(mainActivity.getGeofenceManager(),
                (TextView) view.findViewById(R.id.startB),
                (TextView) view.findViewById(R.id.infoLabel),
                mainActivity.getOutput());

		mActiveSpot = (RelativeLayout) view.findViewById(R.id.activeSpot);
		mActiveSpot.setOnClickListener(this);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
				&& ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ASK_GEOLOCATION_PERMISSION_REQUEST);
			return view;
		}
		initMapView();
		return view;
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
		switch (requestCode) {
			case ASK_GEOLOCATION_PERMISSION_REQUEST: {
				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
					initMapView();
			}
		}
	}

	private void initMapView() {
		MainActivity mainActivity = (MainActivity) getActivity();
		mMap = new Map(mainActivity, R.id.map);
		mMap.updateLocationOnMap();
		mainActivity.getAreaDelegate().setMap(mMap);
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
    public String getManagerAreasType() {
        return "geofences";
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
            case R.id.activeSpot: {
                if (!isScanning()) {
                    if (!checkIsGpsEnabled()) {
                        showGpsAlertDialog();
                        return;
                    }
                    startScanning();
                } else {
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
		LocationManager manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

		if (manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) &&
            (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)|| !manager.isProviderEnabled(LocationManager.GPS_PROVIDER))) {
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
			GooglePlayServicesUtil.getErrorDialog(result, getActivity(), 0, new GooglePlayCancelListener(getActivity()));
		}
	}
}
