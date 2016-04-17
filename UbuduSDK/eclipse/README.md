## Adding the Ubudu SDK framework to your Eclipse project

#### Instruction for Eclipse projects:

Starting to use the Ubudu SDK on Android app is very simple. Have a look at the `UbuduSDKDemo` in the directory for a complete example.

Your first need to include all the required librairies into your project.

1. Firstly add by drag&drop the newest version of `ubudu-sdk.jar` and `ubudu-beacon-sdk.jar` into libs folder in your project.

2. In the same way add also following libs (versions can be higher):

	- android-support-v4.jar
	- gson-2.6.2.jar
	- ormlite-android-4.48.jar
	- ormlite-core-4.48.jar

	These libraries can be downloaded in the following web-sites: 
	- [OrmLite](http://ormlite.com)
	- [Google GSON](https://code.google.com/p/google-gson/)
	- [Volley](https://android.googlesource.com/platform/frameworks/volley/)

3. The next step is prepare two other projects in your workspace:

	- [google-play-services-lib](http://developer.android.com/google/play-services/setup.html)
	- [Volley](https://android.googlesource.com/platform/frameworks/volley)

You can add them into your project as references by pressing alt + enter or clicking right mouse button on your project and selecting “Properties” and go into Android section.

### Changes required in the Manifest File
Ubudu-SDK requires to work specific permissions, activities, receivers and services. Add them into *AndroidManifest.xml* in your project.

#### Permissions required
``` xml
    <uses-sdk android:minSdkVersion="15" android:targetSdkVersion="18" />

    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
	<uses-permission android:name="android.permission.BLUETOOTH"/>
	<uses-permission android:name="android.permission.BLUETOOTH_ADMIN"/>
	<uses-permission android:name="android.permission.INTERNET" />
	<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
	<uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED"/>
	<uses-permission android:name="com.google.android.gms.permission.ACTIVITY_RECOGNITION"/>
  ```

#### Activities, receivers and services:
In application section add:

<!-- BEGIN UbuduSDK stuff -->

<activity android:name="com.ubudu.sdk.WebActivity" />

        <receiver android:name="com.ubudu.sdk.service.UbuduBootReceiver">
        
            <intent-filter>
                <action android:name="android.intent.action.BOOT_COMPLETED" />
            </intent-filter>
            
        </receiver>

        <service
            android:name="com.ubudu.sdk.service.UbuduService"
            android:enabled="true"
            android:exported="false">
            
            <intent-filter>
                <action android:name="com.ubudu.sdk.service.UbuduService.action.DISPLAY_WEB_PAGE" />
                <action android:name="com.ubudu.sdk.service.UbuduService.action.OPEN_SAMSUNG_WALLET" />
                <action android:name="com.ubudu.sdk.service.UbuduService.action.OPEN_DEEP_LINK" />
            </intent-filter>
            
        </service>
        
        <service
            android:name="com.ubudu.beacon.service.UbuduBeaconService"
            android:enabled="true"
            android:exported="false"
            android:isolatedProcess="false" />
            
        <service
            android:name="com.ubudu.beacon.IBeaconIntentProcessor"
            android:enabled="true"
            android:exported="false"
            android:isolatedProcess="false">
            
            <meta-data
                android:name="background"
                android:value="true" />
                
            <intent-filter android:priority="1">
                <action android:name="com.ubudu.sdk.beacon.internal.action.IBeaconIntentProcessor" />
            </intent-filter>
            
        </service>

<!-- END UbuduSDK stuff -->


