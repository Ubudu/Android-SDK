package com.ubudu.ubudu_sdk_studio_demo;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

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
