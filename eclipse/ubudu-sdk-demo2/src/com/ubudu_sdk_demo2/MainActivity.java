package com.ubudu_sdk_demo2;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.ubudu.sdk.UbuduAreaDelegate;
import com.ubudu.sdk.UbuduBeaconManager;
import com.ubudu.sdk.UbuduGeofenceManager;
import com.ubudu.sdk.UbuduSDK;

public class MainActivity extends FragmentActivity implements TextOutput {
	
	// put here your namespace
	private static final String NAMESPACE = "ed2f594c2eb20f3a1213e387af53cd86fa1f70e0"; // Proximities, Geofence - UbuduTest2
	// private static final String NAMESPACE = "b79fc953ff1755d09314225fb81fad49a4ee7b2c";	// Groups
	// private static final String NAMESPACE = "71b309965b54bdd8d3594a1253fb0da408154982"; // triggers
	private UbuduPagerAdapter mUbuduPagerAdapter;
	private ViewPager mViewPager;

	private UbuduSDK mUbuduSdk;
	
	private UbuduBeaconManager mBeaconManager;
	private UbuduGeofenceManager mGeofenceManager;
	private InfoAreaReceiver mInfoAreaReceiver;
	private UbuduAreaDelegate mAreaDelegate;
	
	private TextView mOutputText;
	
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_main);
		
		mOutputText = (TextView) findViewById(R.id.outputText);
		mOutputText.setMovementMethod(new ScrollingMovementMethod());
		
		mUbuduSdk = UbuduSDK.getSharedInstance(getApplicationContext());
		mUbuduSdk.setNamespace(NAMESPACE);
		mUbuduSdk.setFileLogEnabled(true);
		
		mBeaconManager = mUbuduSdk.getBeaconManager();
		mGeofenceManager = mUbuduSdk.getGeofenceManager();
		mBeaconManager = mUbuduSdk.getBeaconManager();
		
		mBeaconManager.setAreaDelegate(getAreaDelegate());
		
		mUbuduPagerAdapter = new UbuduPagerAdapter(getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mUbuduPagerAdapter);
	}
	
	public void setAreaDelegate(Map map) {
		mAreaDelegate = new DemoAreaDelegate(this, map);
		
		mBeaconManager.setAreaDelegate(getAreaDelegate());
		mGeofenceManager.setAreaDelegate(getAreaDelegate());
	}
	
	public UbuduBeaconManager getBeaconManager() {
		return mBeaconManager;
	}
	
	public UbuduGeofenceManager getGeofenceManager() {
		return mGeofenceManager;
	}
	
	public InfoAreaReceiver getInfoAreaReceiver() {
		return mInfoAreaReceiver;
	}
	
	public UbuduAreaDelegate getAreaDelegate() {
		return mAreaDelegate;
	}
	
	public UbuduSDK getUbuduSDK() {
		return mUbuduSdk;
	}
	
	public TextOutput getOutput() {
		return this;
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
			if (mInfoAreaReceiver == null) {
				mInfoAreaReceiver = new InfoAreaReceiver(this);
				registerReceiver(mInfoAreaReceiver, new IntentFilter("com.ubudu.sdk.notify"));
				this.printf("Registered info receiver.\n");
			}
		}
	}

	private void unregisterInfoReceiver() {
		synchronized (this) {
			if (mInfoAreaReceiver != null) {
				unregisterReceiver(mInfoAreaReceiver);
				mInfoAreaReceiver = null;
				this.printf("Unregistered info receiver.\n");
			}
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		registerInfoReceiver();
		
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		unregisterInfoReceiver();
	}
	
	
}
