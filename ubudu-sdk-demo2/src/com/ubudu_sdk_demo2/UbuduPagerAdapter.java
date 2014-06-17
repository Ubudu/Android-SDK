package com.ubudu_sdk_demo2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class UbuduPagerAdapter extends FragmentPagerAdapter {

	public final static String[] mFragmentsNames = {"beacons", "geofences"};
	
	public UbuduPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int arg0) {
		switch (arg0) {
		case 0:
			return BeaconFragment.newInstance();
		case 1:
			return GeofenceFragment.newInstance();
		default:
			return null;
		}
	}

	@Override
	public int getCount() {
		return mFragmentsNames.length;
	}
}
