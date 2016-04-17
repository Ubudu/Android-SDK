package com.ubudu.ubudu_sdk_eclipse_demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.ubudu.sdk.UbuduBeacon;
import com.ubudu.sdk.UbuduBeaconManager;
import com.ubudu.sdk.UbuduGeofenceManager;
import com.ubudu.sdk.UbuduRangedBeaconsNotifier;
import com.ubudu.sdk.UbuduSDK;

import java.util.Iterator;
import java.util.List;

public class MainActivity extends FragmentActivity implements TextOutput {
	
	// Set your own app namespace here
   	private static final String NAMESPACE = "5c457c751d92a4c9d14284ec5aaeba25ed1fcb05";

	private UbuduPagerAdapter mUbuduPagerAdapter;
	private ViewPager mViewPager;
    private TextView mOutputText;

	private UbuduSDK mUbuduSdk;
	
	private UbuduBeaconManager mBeaconManager;
	private UbuduGeofenceManager mGeofenceManager;

    private DemoAreaDelegate mAreaDelegate;


	@Override
	protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_main);

        mUbuduPagerAdapter = new UbuduPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mUbuduPagerAdapter);
		mOutputText = (TextView) findViewById(R.id.outputText);
		mOutputText.setMovementMethod(new ScrollingMovementMethod());
		
		mUbuduSdk = UbuduSDK.getSharedInstance(getApplicationContext());
		mUbuduSdk.setNamespace(NAMESPACE);
        mUbuduSdk.setFileLogEnabled(true);

        mAreaDelegate = new DemoAreaDelegate(this);

		mGeofenceManager = mUbuduSdk.getGeofenceManager();
        mGeofenceManager.setAreaDelegate(mAreaDelegate);

		mBeaconManager = mUbuduSdk.getBeaconManager();
        mBeaconManager.setEnableAutomaticUserNotificationSending(true);
        mBeaconManager.setAreaDelegate(mAreaDelegate);

        mBeaconManager.setRangedBeaconsNotifier(new UbuduRangedBeaconsNotifier() {

            @Override
            public void didRangeBeacons(final List<UbuduBeacon> rangedBeacons) {
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run() {
                        android.util.Log.e("", "beacons ranged: " + rangedBeacons.size());
                        Iterator<UbuduBeacon> iter = rangedBeacons.iterator();
                        while (iter.hasNext()) {
                            UbuduBeacon b = iter.next();
                            printf("name: " + b.name() + ", rssi: " + b.rssi() + ", minor: " + b.minor() + ", major: " + b.major());
                        }
                    }
                });
            }
        });


    }

    public UbuduSDK getUbuduSDK() {
        return mUbuduSdk;
    }

    public UbuduBeaconManager getBeaconManager() {
        return mBeaconManager;
    }

    public UbuduGeofenceManager getGeofenceManager() {
        return mGeofenceManager;
    }

    public DemoAreaDelegate getAreaDelegate() {
        return mAreaDelegate;
    }
	
	public TextOutput getOutput() {
		return this;
	}

    @Override
    public void printf(String formatControl, Object... arguments) {
        final String newText = String.format(formatControl + "\n", arguments);
        synchronized (mOutputText) {
            mOutputText.append(newText);
            final Layout layout = mOutputText.getLayout();
            if (layout != null) {
                final int scrollAmount = layout.getLineTop(mOutputText.getLineCount()) - mOutputText.getHeight();
                mOutputText.scrollTo(0, ((0 < scrollAmount) ? scrollAmount : 0));
            }
        }
    }

}
