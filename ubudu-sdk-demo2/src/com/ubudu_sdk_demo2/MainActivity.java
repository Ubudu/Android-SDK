package com.ubudu_sdk_demo2;

import com.ubudu.sdk.UbuduAreaDelegate;
import com.ubudu.sdk.UbuduBeaconManager;
import com.ubudu.sdk.UbuduGeofenceManager;
import com.ubudu.sdk.UbuduSDK;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements TextOutput {
	
	// put here your namespace
	private static final String NAMESPACE = "ed2f594c2eb20f3a1213e387af53cd86fa1f70e0";

	private UbuduPagerAdapter mUbuduPagerAdapter;
	private ViewPager mViewPager;

	private UbuduSDK mUbuduSdk;
	
	private UbuduBeaconManager mBeaconManager;
	private InfoAreaReceiver mInfoAreaReceiver;
	private UbuduAreaDelegate mAreaDelegate;
	
	private TextView mOutputText;
	
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_main);
		
		mOutputText = (TextView) findViewById(R.id.outputText);
		
		mUbuduSdk = UbuduSDK.getSharedInstance(getApplicationContext());
		mUbuduSdk.setNamespace(NAMESPACE);
		mUbuduSdk.setMaximumDailyNumberOfNotificationsAllowed(9999);
		
		mAreaDelegate = new DemoAreaDelegate(this);
		mBeaconManager = mUbuduSdk.getBeaconManager();
		
		mBeaconManager.setAreaDelegate(getAreaDelegate());
		
		mUbuduPagerAdapter = new UbuduPagerAdapter(getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mUbuduPagerAdapter);
	}
	
	public UbuduBeaconManager getBeaconManager() {
		return mBeaconManager;
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
