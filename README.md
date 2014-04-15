Android-SDK
===========

Ubudu contextual interactions SDK for Android

### System and hardware requirements
####For Geofencing :
- Android >=4.3

####For beacons related features :
- A Bluetooth Low Energy (BLE) radio, present in some devices as part of Bluetooth 4.0
- A working BLE API that allows third-party app code to access BLE. This is present in some Android 4.3+ devices.
- List of potentially supported devices (OS v4.3+ only)
    + HTC One, Max, Mini, M8 
    + LG G2, G Pro2, G Flex, Vu3.0
    + Motorola Moto G, Moto X
    + Motorola Droid RAZR M, RAZR HD, RAZR Maxx HD 
    + Motorola Droid Ultra, Maxx, Mini (OS v4.4+ only)
    + LGE Nexus 4, 5, 7 (2013)
    + Samsung Galaxy S3, S3 Mini, S4, S4 Mini, S4 Active, S5
    + Samsung Galaxy Note 2, Note 3, Note 10.1
    + Sony Xperia Z, Z1, Z1 Compact
    + Sony Xperia Tablet Z, Ultra, ZR, ZL
    + Sony Xperia SP, T, TX, V

### Adding the Ubudu SDK framework to your project

Starting to use the Ubudu SDK on Android app is very simple. Have a look at theubudu-demo-app2 in the directory for a complete example.
#### Instruction for Eclipse projects:
Your first need to include all the required librairies into your project.

1. Firstly add by drag&drop the *ubudu-sdk.1.0.1.jar* into libs folder in your project.

![Eclipse project content](/__media-files/images/image_1.jpg) 

2. In the same way add also following libs (versions can be higher):

- gson-2.2.4.jar
- ormlite-android-4.48.jar
- ormlite-core-4.48.jar

These libraries can be downloaded in the following web-sites: 
- [OrmLite](http://ormlite.com)
- [Google GSON](https://code.google.com/p/google-gson/)

After this, your project should looks like:

![Eclipse project content](/__media-files/images/image_2.jpg) 

3. The next step is prepare two other projects in your workspace:

- [google-play-services-lib](http://developer.android.com/google/play-services/setup.html)
- [Volley](https://android.googlesource.com/platform/frameworks/volley)

You can add them into your project as references by pressing alt + enter or clicking right mouse button on your project and selecting “Properties” and go into Android section.

![Eclipse project content](/__media-files/images/image_3.jpg)

### Changes required in the Manifest File
Ubudu-SDK requires to work specific permissions, activities, receivers and services. Add them into *AndroidManifest.xml* in your project.

#### Permissions required
``` xml
    <uses-sdk android:minSdkVersion="15" android:targetSdkVersion="18" />

    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.BLUETOOTH" />
    <uses-permission android:name="android.permission.BLUETOOTH_ADMIN" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
  ```

#### Activities, receivers and services:
In application section add:
``` xml
<!-- BEGIN UbuduSDK stuff -->

<receiver android:name=".service.UbuduBootReceiver" >
<intent-filter>
<action android:name="android.intent.action.BOOT_COMPLETED" />
</intent-filter>
</receiver>

<activity android:name="com.ubudu.sdk.WebActivity" />

<service
android:name="com.ubudu.sdk.service.UbuduService"
android:enabled="true"
android:exported="true" >
</service>
<!-- the following should be coallesced eventually into the above service... -->
<service
android:name="com.ubudu.network.ibeacon.service.IBeaconService"
android:enabled="true"
android:exported="false"
android:isolatedProcess="false" />
<service
android:name="com.ubudu.network.ibeacon.IBeaconIntentProcessor"
android:enabled="true"
android:exported="false"
android:isolatedProcess="false" >
<meta-data
android:name="background"
android:value="true" />

<intent-filter android:priority="1" >
<action android:name="com.ubudu.sdk.beacon.internal.action.IBeaconIntentProcessor" />
</intent-filter>
</service>

<!-- END UbuduSDK stuff -->
```


