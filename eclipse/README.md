<!-- -*- mode:markdown;coding:utf-8 -*- -->

# Android-SDK

## Ubudu contextual interactions SDK for Android

For general information and usage, see Android-SDK/README.md

### Adding the Ubudu SDK framework to your Eclipse project

#### Instruction for Eclipse projects:

Starting to use the Ubudu SDK on Android app is very simple. Have a look at the ubudu-demo-app2 in the directory for a complete example.

Your first need to include all the required librairies into your project.

1. Firstly add by drag&drop the newest version of *ubudu-sdk.jar* into libs folder in your project.

![Eclipse project content](/eclipse/__media-files/images/image_1.jpg) 

2. In the same way add also following libs (versions can be higher):

- android-support-v4.jar
- gson-2.2.4.jar
- ormlite-android-4.48.jar
- ormlite-core-4.48.jar

These libraries can be downloaded in the following web-sites: 
- [OrmLite](http://ormlite.com)
- [Google GSON](https://code.google.com/p/google-gson/)

After this, your project should looks like:

![Eclipse project content](/eclipse/__media-files/images/image_2.jpg) 

3. The next step is prepare two other projects in your workspace:

- [google-play-services-lib](http://developer.android.com/google/play-services/setup.html)
- [Volley](https://android.googlesource.com/platform/frameworks/volley)

You can add them into your project as references by pressing alt + enter or clicking right mouse button on your project and selecting “Properties” and go into Android section.

![Eclipse project content](/eclipse/__media-files/images/image_3.jpg)

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
    <uses-permission android:name="android.permission.WAKE_LOCK" />
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

<!-- ======================================================== -->

