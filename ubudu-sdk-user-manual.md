`UbuduSDK` User Manual - version 1.1.2
======================================

Introduction
------------

This is the user manual of the `UbuduSDK`.

This SDK contains several components:

-   Ubudu Geofence SDK,
-   Ubudu Proxmity Beacon SDK (Bluetooth).
-   Ubudu Ultrasound SDK,

Modifications
-------------

<table>
<col width="13%" />
<col width="13%" />
<col width="29%" />
<col width="43%" />
<thead>
<tr class="header">
<th align="left">Version</th>
<th align="left">Date</th>
<th align="left">Author</th>
<th align="left">Modifications</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td align="left">0.0</td>
<td align="left">2013-10-01</td>
<td align="left">Pascal Bourguignon</td>
<td align="left">Created stub.</td>
</tr>
<tr class="even">
<td align="left">0.1</td>
<td align="left">2014-02-07</td>
<td align="left">Pascal Bourguignon</td>
<td align="left">Added some sections TBD.</td>
</tr>
<tr class="odd">
<td align="left">0.2</td>
<td align="left">2014-02-27</td>
<td align="left">Pascal Bourguignon</td>
<td align="left">Added instructions to include the jar in an application project.</td>
</tr>
<tr class="even">
<td align="left">1.1.0</td>
<td align="left">2014-08-25</td>
<td align="left">Pascal Bourguignon</td>
<td align="left">Added UbuduUser interface.</td>
</tr>
<tr class="odd">
<td align="left">1.1.1</td>
<td align="left">2014-08-26</td>
<td align="left">Pascal Bourguignon</td>
<td align="left">Added anti hacking protocol configuration from the server.</td>
</tr>
<tr class="even">
<td align="left">1.1.2</td>
<td align="left">2014-08-28</td>
<td align="left">Pascal Bourguignon</td>
<td align="left">Added statusChange() delegate method.</td>
</tr>
</tbody>
</table>

Colophon
--------

The source of this document is written in reStructured Text format. It is in the git repository under `documentation/user-manual/user-manual.txt`.

-   \_http://docutils.sourceforge.net/rst.html
-   \_http://rst2pdf.googlecode.com/svn/trunk/doc/manual.txt

You can generate various formats from it: :

    rst2html specifications.txt    specifications.html
    rst2pdf  specifications.txt -o specifications.pdf

(cf. Makefile in the `documentation/user-manual/` directory).

Authors:

-   François Kruta \<<francois.kruta@ubudu.com>\>
-   Pascal Bourguignon \<<pascal.bourguignon@ubudu.com>\>

Legal status:

Copyright ©2013,2014 ubudu SAS, All right reserved

Table of Contents
-----------------

`UbuduSDK` for Android
----------------------

The `UbuduSDK` library to use in all applications connecting to Ubudu geofences and bluetooth LE beacons for geomarketing services for Android platform.

### Getting started

This section will contain information regarding adding the `UbuduSDK` to any host application along with necessary project configuration which are required by the `UbuduSDK`.

#### Add the dependencies

The `UbuduSDK` requires the following dependent libraries:

-   google-play-services\_lib (4.0.30),
-   volley (1.0)

#### Add the `UbuduSDK` jar file

Add the `ubudu-sdk-|VERSION|.jar` file to your project libs/ subdirectory.

#### Define permissions to your `AndroidManifest.xml` file.

Add following permisssions to manifest file of your project: :

    <uses-sdk
        android:minSdkVersion="18"
        android:targetSdkVersion="18" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
    <uses-permission android:name="android.permission.BLUETOOTH"/>
    <uses-permission android:name="android.permission.BLUETOOTH_ADMIN"/>
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
    <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED"/>

#### Add activities, receivers and services to your `AndroidManifest.xml` file.

Add following services and activities to the `AndroidManifest.xml` file of your application: :

    <receiver android:name=".service.UbuduBootReceiver">
        <intent-filter>
            <action android:name="android.intent.action.BOOT_COMPLETED"/>
        </intent-filter>
    </receiver>

    <!-- BEGIN UbuduSDK stuff -->

    <activity
        android:name="com.ubudu.sdk.WebActivity"
        />   

    <service
        android:name="com.ubudu.sdk.service.UbuduService"
        android:enabled="true"
        android:exported="true" >
      <intent-filter>
        <action android:name="com.ubudu.sdk.service.UbuduService.action.DISPLAY_WEB_PAGE" />
        <action android:name="com.ubudu.sdk.service.UbuduService.action.OPEN_SAMSUNG_WALLET" />
      </intent-filter>
    </service>

    <!-- the following should be coallesced eventually into the above service... -->

    <service
        android:name="com.ubudu.network.ibeacon.service.IBeaconService"
        android:enabled="true"
        android:exported="false"
        android:isolatedProcess="false"
        />

    <service
        android:name="com.ubudu.network.ibeacon.IBeaconIntentProcessor"
        android:enabled="true" 
        android:exported="false"
        android:isolatedProcess="false"
        >
            <meta-data android:name="background" android:value="true" />
      <intent-filter 
          android:priority="1" >
        <action android:name="com.ubudu.sdk.beacon.internal.action.IBeaconIntentProcessor"/>
      </intent-filter>
    </service>  

    <!-- END UbuduSDK stuff -->

### Usage instructions

To start using `UbuduSDK` use following code:

First get instance of `UbuduSDK`. We use singleton as there is no need of many instances of this class. :

    UbuduSDK sdk=UbuduSDK.getSharedInstance(context);

Set the application namespace :

    sdk.setNamespace(namespace);

Set delegate that handle actions from SDK :

    UbuduGeofenceManager mGeofenceManager=sdk.getGeofenceManager();
    mGeofenceManager.setAreaDelegate(someAreaDelegate);

Next start service with startGeofencing(Context ctx). From this moment application will start receiving geofences and notify user in case of proper conditions. :

    mGeofenceManager.start(context);

To stop using SDK use following code: :

    mGeofenceManager.stop(context);

Starting this command will first remove tracking any geofences that are in use by UbuduSDK and then will stop service resposible for checking parameters used to load new data.

### Design principle of the `UbuduSDK` API

The `com.ubudu.sdk.UbuduSDK` class has a shared instance that is the root of the API. It provides methods to obtain the *managers*, each of which deals with a different kind of areas: geofences, bluetooth LE beacons, ultrasound areas. If the kind of areas is not available on the device, then `null` is returned instead of a manager.

The three manager classes share a common superclass, `com.ubudu.sdk.UbuduAreaManager`, and each deal with covariant subclasses.

    public class UbuduSDK extends Object 
    {
      public static UbuduSDK getSharedInstance(){…}

      public UbuduGeofenceManager   getGeofenceManager(){…}
      public UbuduBeaconManager     getBeaconManager(){…}
      public UbuduUltrasoundManager getUltrasoundManager(){…}

      // …
    }

#### Settings

##### `com.ubudu.sdk.UbuduSDK` settings

**TBD**

##### `com.ubudu.sdk.UbuduUser` settings

The application may send to the server user information, which allows the server to filter geofences and beacons on user properties and tags.

    public class ApplicationUserInformation implements UbuduUser {
        public String userId(){
            return …;
        }
        public java.util.Map<String,String> properties(){
            return …;
        }
        public java.util.Collection<String> tags(){
            return …;
        }
    };
    ApplicationUserInformation user=new ApplicationUserInformation(…);
    UbuduSDK.getSharedInstance(context).setUserInformation(user);

##### `com.ubudu.sdk.UbuduAreaManager` settings

**TBD**

Note: the manager settings are specific to each manager: ie. you can have different settings for geofences than for beacons.

##### `com.ubudu.sdk.UbuduGeofenceManager` specific settings

**TBD**

##### `com.ubudu.sdk.UbuduBeaconManager` specific settings

**TBD**

##### `com.ubudu.sdk.UbuduUltrasoundManager` specific settings

**TBD**

#### Delegate

The application may configure delegate objects to intercept the processing and notifications upon area entered or exited events.

There are four delegate interfaces, each used by the corresponding manager class: :

    UbuduAreaDelegate             UbuduAreaManager
    UbuduBeaconRegionDelegate     UbuduBeaconManager
    UbuduGeofenceDelegate         UbuduGeofenceManager
    UbuduUltrasoundDelegate       UbuduUltrasoundManager

They are identical, only with covariant parameters.

An `UbuduAreaDelegate` can be configured with the `com.ubudu.sdk.UbuduAreaManager#setAreaDelegate` method, for all the managers, but receiving generic parameters `com.ubudu.sdk.UbuduArea`.

You may also configure a specific delegate with a specific manager, `com.ubudu.sdk.UbuduGeofenceManager#setGeofenceDelegate`, `com.ubudu.sdk.UbuduBeaconManager#setBeaconDelegate`, or `com.ubudu.sdk.UbuduUltrasoundManager#setUltrasoundDelegate`. When a manager specific delegate is configured, that manager doesn't use the UbuduAreaDelegate configured with `setAreaDelegate`.

##### Description of the delegate protocol

**TBD**

#### Operation modes

<table>
<col width="34%" />
<col width="19%" />
<col width="46%" />
<thead>
<tr class="header">
<th align="left">automatic*SendingIsEnabled</th>
<th align="left">delegate</th>
<th align="left">result</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td align="left"><blockquote>
<p>false</p>
</blockquote></td>
<td align="left"><blockquote>
<p>null</p>
</blockquote></td>
<td align="left"><blockquote>
<p>actions can't be taken</p>
</blockquote></td>
</tr>
<tr class="even">
<td align="left"><blockquote>
<p>false</p>
</blockquote></td>
<td align="left"><blockquote>
<p>delegate</p>
</blockquote></td>
<td align="left"><blockquote>
<p>actions are forwared to the delegate</p>
</blockquote></td>
</tr>
<tr class="odd">
<td align="left"><blockquote>
<p>true</p>
</blockquote></td>
<td align="left"><blockquote>
<p>null</p>
</blockquote></td>
<td align="left"><blockquote>
<p>actions are taken automatically</p>
</blockquote></td>
</tr>
<tr class="even">
<td align="left"><blockquote>
<p>true</p>
</blockquote></td>
<td align="left"><blockquote>
<p>delegate</p>
</blockquote></td>
<td align="left"><blockquote>
<p>actions are taken automatically</p>
</blockquote></td>
</tr>
</tbody>
</table>

**TBD**

#### Lifecycles

**TBD**

##### Examples

**TBD**
