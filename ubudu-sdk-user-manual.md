`UbuduSDK` User Manual - version 1.9.8
======================================

Introduction
------------

This is the user manual of the `UbuduSDK`.

This SDK contains several components:

-   Ubudu Geofence SDK,
-   Ubudu Proxmity Beacon SDK (Bluetooth).

Modifications
-------------

<table>
<colgroup>
<col width="13%" />
<col width="15%" />
<col width="28%" />
<col width="42%" />
</colgroup>
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
<tr class="odd">
<td align="left">1.2.0</td>
<td align="left">2014-09-16</td>
<td align="left">Tomasz Ziolkowski</td>
<td align="left">Added setFileLogEnabled flag for enabling getting / clearing logs.</td>
</tr>
<tr class="even">
<td align="left">1.2.1</td>
<td align="left">2014-09-18</td>
<td align="left">Tomasz Ziolkowski</td>
<td align="left">Added support for custom baseURL.</td>
</tr>
<tr class="odd">
<td align="left">1.2.3</td>
<td align="left">2014-10-06</td>
<td align="left">Tomasz Ziolkowski</td>
<td align="left">Fix WiFi &amp; BLE issues.</td>
</tr>
<tr class="even">
<td align="left">1.2.5</td>
<td align="left">2014-10-17</td>
<td align="left">Tomasz Ziolkowski</td>
<td align="left">Support for continous proximities</td>
</tr>
<tr class="odd">
<td align="left">1.3.0</td>
<td align="left">2014-11-06</td>
<td align="left">Tomasz Ziolkowski</td>
<td align="left">Added setWifiBleFixDisabled for disabling fix for wifi &amp; ble ble issue. Fix bug with user's tags</td>
</tr>
<tr class="odd">
<td align="left">1.4.0</td>
<td align="left">2014-11-13</td>
<td align="left">Tomasz Ziolkowski</td>
<td align="left"><p>Add setRegionExitMinDelay setter to set delay after which delegate get notified about exiting region.</p> Regions are defined by proximiy UUID. That means all beacons with the same proximityUUID and different major/minor belongs to same region.</p></td>
</tr>
<tr class="odd">
<td align="left">1.4.1</td>
<td align="left">2014-11-19</td>
<td align="left">Tomasz Ziolkowski</td>
<td align="left">Bug fixes</td>
</tr>
<tr class="even">
<td align="left">1.4.2</td>
<td align="left">2014-12-09</td>
<td align="left">Tomasz Ziolkowski</td>
<td align="left">Proximity accuracy improved.</td>
</tr>
<tr class="odd">
<td align="left">1.4.3</td>
<td align="left">2014-12-23</td>
<td align="left">Tomasz Ziolkowski</td>
<td align="left">Add max events count/periods for rules and for app. Add some more log events.</td>
</tr>
<tr class="even">
<td align="left">1.4.4</td>
<td align="left">2015-01-13</td>
<td align="left">Tomasz Ziolkowski</td>
<td align="left">Add min/max events count/periods for groups. Minor changes</td>
</tr>
<tr class="odd">
<td align="left">1.4.5</td>
<td align="left">2015-01-22</td>
<td align="left">Jean-Baptiste Quesney</td>
<td align="left">Critical bug fix which prevented the SDK from working</td>
</tr>
<tr class="even">
<td align="left">1.4.6</td>
<td align="left">2015-02-09</td>
<td align="left">Tomasz Ziolkowski</td>
<td align="left">Fix a bug causes reset limit counters. Fix region behaviour.</td>
</tr>
<tr class="odd">
<td align="left">1.4.7</td>
<td align="left">2015-02-12</td>
<td align="left">Tomasz Ziolkowski</td>
<td align="left">Fix group and region behaviour. Update log events.</td>
</tr>
<tr class="even">
<td align="left">1.4.8</td>
<td align="left">2015-03-17</td>
<td align="left">Tomasz Ziolkowski</td>
<td align="left">Improve Stability of the SDK. Add medumFar and highFar proximities</td>
</tr>
<tr class="odd">
<td align="left">1.4.9</td>
<td align="left">2015-04-02</td>
<td align="left">Tomasz Ziolkowski</td>
<td align="left">Improve rssi measurements. Fix bug with starting sdk</td>
</tr>
<tr class="even">
<td align="left">1.4.10</td>
<td align="left">2015-04-09</td>
<td align="left">Tomasz Ziolkowski</td>
<td align="left"><dl>
<dt>Fix getting native device from</dt>
<dd><p>UbuduBeacon.</p>
</dd>
</dl></td>
</tr>
<tr class="odd">
<td align="left">1.5.0</td>
<td align="left">2015-04-17</td>
<td align="left">Tomasz Ziolkowski</td>
<td align="left">Deep linking. Stability improvements and bug fixes. Reduced verbosity of logcat/logs. Lower frequency to send async logged event to server. actions. Optimisation of user tags management.</td>
</tr>
<tr class="odd">
<td align="left">1.6.0</td>
<td align="left">2015-05-12</td>
<td align="left">Tomasz Ziolkowski</td>
<td align="left"><p>Fix bug while working with secured beacons.</p> <p>Add API for between log setting period events.</p><p>Improve rssi averaging.</p> <p>Fix for relative proximity.</p> <p>Add API for reseting global event counter.</p> <p>Reduced battery consumption.</p></td>
</tr>
<tr class="odd">
<td align="left">1.7.0</td>
<td align="left">2015-05-19</td>
<td align="left">Tomasz Ziolkowski</td>
<td align="left"><p>Multiline notifications.</p><p>Mesh (beta).</p><p>Indoor Location (beta).</p> <p>Bug fixes.</p></td>
</tr>
<tr class="odd">
<td align="left">1.7.1</td>
<td align="left">2015-05-29</td>
<td align="left">Tomasz Ziolkowski</td>
<td align="left"><p>No toasts from ubudu sdk.</p><p>Custom alertTitle for notification.</p><p>Minor fixes.</p></td>
</tr>
<tr class="odd">
<td align="left">1.7.2</td>
<td align="left">2015-05-29</td>
<td align="left">Tomasz Ziolkowski</td>
<td align="left"><p>Bug fixes.</p></td>
</tr>
<tr class="odd">
<td align="left">1.7.3</td>
<td align="left">2015-06-09</td>
<td align="left">Tomasz Ziolkowski</td>
<td align="left"><p>Fix for uuid bug.</p><p>Restart Ubudu service after app killing.</p><p>Fix UI freeze while starting the beacon manager.</p><p>Other bug fixes.</p></td>
</tr>
<tr class="odd">
<td align="left">1.8.0</td>
<td align="left">2015-06-30</td>
<td align="left">Michal Gasztold</td>
<td align="left"><p>Performance improvements for namespaces with high number of interaction rules.</p><p>Other bug fixes.</p></td>
</tr>
<tr class="odd">
<td align="left">1.8.1</td>
<td align="left">2015-07-01</td>
<td align="left">Michal Gasztold</td>
<td align="left"><p>Fixed matching regions to beacon.</p></td>
</tr>
<tr class="odd">
<td align="left">1.8.2</td>
<td align="left">2015-07-08</td>
<td align="left">Michal Gasztold</td>
<td align="left"><p>Fix scanning periods custom settings</p>
<p>Added methods to customize rules fetching period</p>
<p>Fixed custom notification mechanism after app is killed</p></td>
</tr>
<tr class="odd">
<td align="left">1.8.3</td>
<td align="left">2015-07-14</td>
<td align="left">Michal Gasztold</td>
<td align="left"><p>Custom notification fix when the app is killed</p>
<p>Minor API update for indoor location and mesh</p></td>
</tr>
<tr class="odd">
<td align="left">1.8.4</td>
<td align="left">2015-07-23</td>
<td align="left">Michal Gasztold</td>
<td align="left"><p>Fix for rare issue with fetching rules</p>
<p>Fix stopping a beacon manager</p>
<p>Minor Indoor Location improvements</p>
<p>Minor API updates</p></td>
</tr>
<tr class="odd">
<td align="left">1.8.5</td>
<td align="left">2015-08-05</td>
<td align="left">Michal Gasztold</td>
<td align="left"><p>Fix of Indoor Location not always starting</p>
<p>Stability improvements</p></td>
</tr>
<tr class="odd">
<td align="left">1.9.1</td>
<td align="left">2015-09-09</td>
<td align="left">Michal Gasztold</td>
<td align="left">
<p>Indoor Location Georeference functionality</p>
<p>Indoor Location delegate methods fixes</p>
<p>Bug fixes and stability improvements</p>
</td>
</tr>
<tr class="odd">
<td align="left">1.9.2</td>
<td align="left">2015-09-10</td>
<td align="left">Tomasz Ziolkowski</td>
<td align="left">
<p>Mesh fix while for sending message without ACK</p>
</td>
</tr>
<tr class="odd">
<td align="left">1.9.3</td>
<td align="left">2015-09-29</td>
<td align="left">Michal Gasztold</td>
<td align="left">
<p>Indoor Location:</p>
<p>* polygon zones handling</p>
<p>* public path finding method</p>
<p>* enhanced location accuracy and stability</p>
<p>* geographical coordinates are now returned in degrees</p>
<p>* map overlay image url is available from within json</p>
</td>
</tr>
<tr class="odd">
<td align="left">1.9.4</td>
<td align="left">2015-09-29</td>
<td align="left">Michal Gasztold</td>
<td align="left">
<p>Indoor Location:</p>
<p>* polygon distance calculation fix</p>
</td>
</tr>
<tr class="odd">
<td align="left">1.9.5</td>
<td align="left">2015-10-19</td>
<td align="left">Michal Gasztold</td>
<td align="left">
<p>Added motion filtering for indoor location stability</p>
<p>Added ranged beacon notifier API method for both proximity and indoor location that allows to see beacons currently being ranged by the SDK</p>
<p>Added API method allowing to get a JSONObject of indoor location map for saving purpose</p>
<p>Added API methods for customizing ranging/monitoring scan periods within indoor location manager</p>
<p>Bug fixes and stability improvements</p>
</td>
</tr>
<tr class="odd">
<td align="left">1.9.6</td>
<td align="left">2015-10-19</td>
<td align="left">Michal Gasztold</td>
<td align="left">
<p>Android manifest fix</p>
</td>
</tr>
<tr class="odd">
<td align="left">1.9.7</td>
<td align="left">2015-11-06</td>
<td align="left">Michal Gasztold</td>
<td align="left">
<p>Contextual Interactions SDK:</p>
<p>* added support for new maximum distance medium-far feature available in the manager platform</p>
<p>* active regions are now cleared after SDK has been stopped</p>
<p>* added public method `com.ubudu.sdk.UbuduSDK.resetAllEventsCounters()` that allows to reset all events counters</p>
<p>Indoor Location SDK:</p>
<p>* indoor location map data processing before start is now much faster</p>
<p>* delegate methods are called also after the very first position calculation</p>
<p>Other:</p>
<p>* fixed release .pom file notes</p>
<p>* bugs and stability improvements</p>
</td>
</tr>
<tr class="odd">
<td align="left">1.9.8</td>
<td align="left">2015-11-09</td>
<td align="left">Michal Gasztold</td>
<td align="left">
<p>Fixed an issue of Contextual Interactions SDK start being delayed in case when Indoor Location SDK has been started short moment before</p>
</td>
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
-   Tomasz Ziolkowski \<<tomasz.ziolkowski@ubudu.com>\>
-   Michal Gasztold \<<michal.gasztold@ubudu.biz>\>

Legal status:

Copyright ©2013,2014 ubudu SAS, All right reserved

Table of Contents
-----------------

`UbuduSDK` for Android
----------------------

The `UbuduSDK` library to use in all applications connecting to Ubudu geofences and bluetooth LE beacons for geomarketing services for Android platform.

##### Getting started

This section will contain information regarding adding the `UbuduSDK` to any host application along with necessary project configuration which are required by the `UbuduSDK`.

###### Add the dependencies

The `UbuduSDK` requires the following dependent libraries:

-   google-play-services\_lib (4.0.30),
-   volley (1.0)

###### Add the `UbuduSDK` jar file

Add the `ubudu-sdk-|VERSION|.jar` file to your project libs/ subdirectory.

###### Define permissions to your `AndroidManifest.xml` file.

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

###### Add activities, receivers and services to your `AndroidManifest.xml` file.

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

##### Usage instructions

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

##### Design principle of the `UbuduSDK` API

The `com.ubudu.sdk.UbuduSDK` class has a shared instance that is the root of the API. It provides methods to obtain the *managers*, each of which deals with a different kind of areas: geofences, bluetooth LE beacons areas. If the kind of areas is not available on the device, then `null` is returned instead of a manager.

The three manager classes share a common superclass, `com.ubudu.sdk.UbuduAreaManager`, and each deal with covariant subclasses.

    public class UbuduSDK extends Object 
    {
      public static UbuduSDK getSharedInstance(){…}

      public UbuduGeofenceManager   getGeofenceManager(){…}
      public UbuduBeaconManager     getBeaconManager(){…}

      // …
    }

###### Settings

####### `com.ubudu.sdk.UbuduSDK` settings

**TBD**

####### `com.ubudu.sdk.UbuduUser` settings

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

####### `com.ubudu.sdk.UbuduAreaManager` settings

**TBD**

Note: the manager settings are specific to each manager: ie. you can have different settings for geofences than for beacons.

####### `com.ubudu.sdk.UbuduGeofenceManager` specific settings

**TBD**

####### `com.ubudu.sdk.UbuduBeaconManager` specific settings

**TBD**

###### Delegate

The application may configure delegate objects to intercept the processing and notifications upon area entered or exited events.

There are four delegate interfaces, each used by the corresponding manager class: :

    UbuduAreaDelegate             UbuduAreaManager
    UbuduBeaconRegionDelegate     UbuduBeaconManager
    UbuduGeofenceDelegate         UbuduGeofenceManager

They are identical, only with covariant parameters.

An `UbuduAreaDelegate` can be configured with the `com.ubudu.sdk.UbuduAreaManager#setAreaDelegate` method, for all the managers, but receiving generic parameters `com.ubudu.sdk.UbuduArea`.

You may also configure a specific delegate with a specific manager, `com.ubudu.sdk.UbuduGeofenceManager#setGeofenceDelegate` or `com.ubudu.sdk.UbuduBeaconManager#setBeaconDelegate`. When a manager specific delegate is configured, that manager doesn't use the UbuduAreaDelegate configured with `setAreaDelegate`.

####### Description of the delegate protocol

**TBD**

###### Operation modes

<table>
<colgroup>
<col width="34%" />
<col width="19%" />
<col width="46%" />
</colgroup>
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

###### Lifecycles

**TBD**

####### Examples

**TBD**

Ubudu SDK - Android API
-----------------------

##### General Classes and Interfaces

###### UbuduSDK

    package com.ubudu.sdk;

    /**
     *
     * UbuduSDK is entry point to the Ubudu SDK.
     *
     * It provides access to the specific managers,  unless a
     * manager is not installed or not supported on the device (then
     * null is returned). 
     *
     */

    public class UbuduSDK
    {
      /**
       * Returns the version number of the SDK, as a string major.minor.maintainance
       */
      public static String getVersion(){…}


      /**
       * Application get access to the UbuduSDK thru this static method.
       * The first time it is called, the service is bound to the clientContext.
       * When the application is done with the SDK (eg. in onDestroy), it
       * should send the release() message to unbind the service.
       */
      public static UbuduSDK getSharedInstance(android.content.Context clientContext){…}
      public void release(android.content.Context clientContext){…}

      /**
       * When an android.intent.action.BOOT_COMPLETED intent action is received, 
       * this method can be used to reset the SDK in the same state it was before
       * the boot. 
       */
      public void startFromBoot(android.content.Context clientContext){…}


      /**
       *
       * Sets the namespace of the application. 
       *
       */
      public String namespace(){…}
      public void setNamespace(String namespace){…}

      /**
       *
       * Sets the user information.  It is recorded in the preferences, and
       * sent to the server when registering.
       *
       */
      public abstract UbuduUser userInformation();
      public abstract void setUserInformation(UbuduUser user);


      /**
       *
       * Get the various managers.
       *
       */
      public UbuduGeofenceManager   getGeofenceManager(){…}
      public UbuduBeaconManager     getBeaconManager(){…}


     /**
      * THe UbuduSDK limits the number of notification it sends to the user per day.
      * The counter is reset at midnight, local time.
      *
      * The application can set the maximum daily number of notification
      * the UbuduSDK is allowed to send.
      *
      */
      public void setMaximumDailyNumberOfNotificationsAllowed(int newMaximum){…}
      public int maximumDailyNumberOfNotificationsAllowed(){…}

     /**
       * The number of notification already sent since midnight, local time.
       */
      public int globalDailyNumberOfNotifications(){…}


      /**
       *
       * displayWebPage: will start an activity to fetch the web page display it.
       * openSamsungWallet: will forward the samsungWalletURL to the Samsung
       * Wallet application (if available).
       *
       * When a manager of the UbuduSDK has
       * automaticUserNotificationSendingIsEnabled set to true, and the
       * application is active, then the manager calls directly those
       * methods instead of sending user notifications.
       *
       */
      public void displayWebPage(java.net.URL webPageURL,android.content.Context clientContext){…}
      public void openSamsungWallet(java.net.URL samsungWalletURL,android.content.Context clientContext){…}
    }

###### UbuduUser

    package com.ubudu.sdk;

    /**
    *
    * UbuduUser instances hold user information.
    *
    * It lets the application specify its own user identification,
    * properties and tags, which are used for filtering.
    *
    */

    public interface UbuduUser
    {
      /** 
      *
      * Custom User ID.
      *
      * Typically you use this property to establish a link between the
      * Ubudu users managed bu the SDK and the back-office and your users
      * that exist within you information system.  When you set this
      * property after the SDK has been started, a request is made to the
      * back-office to update the user information.
      *
      */
      public String userId();

      /**
      *
      * Custom user properties.
      *
      * You can use this property to attach custom properties to your
      * users. These values are sent to the back-office.  When you set this
      * property after the SDK has been started, a request is made to the
      * back-office to update the user information.
      *
      * NOTE: The keys "ext_id" and "tags" are reserved by the SDK. If you
      * set them they may be overwritten and never sent to the back-office.
      *
      */
      public java.util.Map<String,String> properties();


      /**
      *
      * User tags.
      *
      * Tags are specific properties which can be used to filter and
      * categorize users.  In the back-office you can define conditions for
      * your actions that depend on the tags assigned to a user.  When you
      * set this property after the SDK has been started, a request is made
      * to the back-office to update the user information.
      *
      */
      public java.util.Collection<String> tags();

    };

###### UbuduOpenInterval

    package com.ubudu.sdk;

    public interface UbuduOpenInterval
    {

      /**
       *
       * Opening and closing times are given symbolically as a day of the
       * week and hour:minute in the local time zone.
       *
       * The same UbuduInterval may represent a different offsets from
       * midnight depending on the timezone and the given week.  
       *
       */
      public interface UbuduInterval
      {

        public enum IntervalType {
          WEEK, BREAKS, SPECIFIC
        }

        public IntervalType intervalType();

        /** 
         *
         * Day of week , from java.util.Calendar.SUNDAY to
         * java.util.Calendar.SATURDAY 
         *
         */
        public int day();

        /**
         *
         * Hour (0..23).
         *
         */
        public int openHour();

        /**
         *
         * Minute (0 .. 59).
         *
         */
        public int openMinute();


        /**
         *
         * Hour (0..23).
         *
         */
        public int closeHour();

        /**
         *
         * Minute (0 .. 59).
         *
         */
        public int closeMinute();


        /**
         *
         * Return the date time corresponding of this ubudu time in week, for
         * the given week (0..53) of the year, in the given timezone.
         *
         * See: http://en.wikipedia.org/wiki/ISO_week_date
         *
         * Remember: the first week of the year (1) starts on Sunday and
         * contains the first Thirsday of the year.  Therefore, a Friday and a
         * Saturday may belong to the week previous the week #1 of the year
         * (week #0 = week #52 or #53 of previous year) when the year starts on a
         * Friday or Saturday.  And similarly, the last few days of the year
         * may be beyond the 52nd week, when the year started with a Thirsday,
         * thus belonging to the week #53 or #54 = week #1 of next year.
         *
         *
         * If the given year doesn't start on a Friday or Saturday, then
         * week==0 is forbidden.
         *
         */
        public java.util.GregorianCalendar timeInWeekYearTimezone(int weekNumber,int year,java.util.TimeZone timezone);


        /**
         * 
         * Return the date time corresponding of this ubudu time in week, for
         * the given week (0..53) of the year, in the given timezone.
         * 
         * See: http://en.wikipedia.org/wiki/ISO_week_date
         * 
         * Remember: the first week of the year (1) starts on Sunday and
         * contains the first Thirsday of the year. Therefore, a Friday and a
         * Saturday may belong to the week previous the week #1 of the year
         * (week #0 = week #52 or #53 of previous year) when the year starts on
         * a Friday or Saturday. And similarly, the last few days of the year
         * may be beyond the 52nd week, when the year started with a Thirsday,
         * thus belonging to the week #53 or #54 = week #1 of next year.
         * 
         * 
         * If the given year doesn't start on a Friday or Saturday, then week==0
         * is forbidden.
         * 
         */
        public Calendar getOpenTime(java.util.TimeZone timeZone);
        public Calendar getCloseTime(java.util.TimeZone timeZone);

      }

      public boolean isWithinOpenHours(java.util.Date datetime);

      public List<UbuduInterval> openingDays();
      public List<UbuduInterval> breakDays();
      public List<UbuduInterval> specificDays();

    }

###### UbuduRule

    package com.ubudu.sdk;

    public interface UbuduRule
    {

      public interface Antecedant
      {
        public String trigger();

        public boolean hasNoMaximumEventCount();
        public int minimumEventCount();

        public boolean hasNoMaximumGroupEventCount();
        public int maximumEventCount();

        public boolean hasNoMinimumEventCount();
        public int minimumGroupEventCount();

        public boolean hasNoMinimumGroupEventCount();
        public int maximumGroupEventCount();

        public int latchTime();


        /**
         *
         * Only for UbuduBeaconRegions:
         *
         * PROXIMITY_ANY : no maximum
         *
         * PROXIMITY_IMMEDIATE : beacons with IMMEDIATE proximity are taken into account.
         *
         * PROXIMITY_NEAR : beacons with NEAR, IMMEDIATE proximity are taken into account.
         *
         * PROXIMITY_FAR : beacons with FAR, NEAR, and IMMEDIATE proximity are taken into account.,
         *
         * We assume that beacons detected with PROXIMITY_UNKNOWN are actually too weak, ie. beyond far.
         *
         */
        public static final int PROXIMITY_ANY=0;
        public static final int PROXIMITY_IMMEDIATE = 1;
        public static final int PROXIMITY_NEAR = 2;
        public static final int PROXIMITY_FAR = 3;

        public int maximumProximity();
        public boolean hasNoMaximumProximity();

      }

      public interface Action 
      {
        public boolean hasServerNotificationUrlTemplate();
        public String serverNotificationUrlTemplate();
        public String message();
        public String webPageUrlTemplate();
        public String passbookUrlTemplate();
      }

      public String id();
      public Antecedant antecedant();
      public Action action();
    }

###### UbuduArea

    package com.ubudu.sdk;

    public interface UbuduArea
    {

      /**
       *
       *
       * The ID of the area.  Notice: it is unique only amongst a specific
       * subclass of UbuduArea; eg. a UbuduGeofence and a
       * UbuduBeaconRegion may have the same id().
       *
       */
      public String id();


      public String name();
      public String address();

      public boolean hasGroupId();
      public String groupId();

      public java.util.TimeZone timezone();

      public java.util.Date startDatetime();
      public java.util.Date endDatetime();
      public java.util.Date lastUpdatedDatetime();

      public java.util.List<UbuduOpenInterval> schedule();
      public java.util.List<UbuduRule> rules();

    }

###### UbuduNotification

    package com.ubudu.sdk;

    public interface UbuduNotification 
    {
      public String title();
      public String shortText(); // This is notify_user.alertBody
      public String iconName();

      /**
      * Returns a JSONObject containing a field named "payload" which contains the payload.
      */
      public org.json.JSONObject payload();

      public java.net.URL webPageUrl();
      public java.net.URL passbookUrl();
      // ((null==webPageUrl())||(null==passbookUrl()))

    }

###### UbuduEvent

:

###### UbuduAreaDelegate

The messages to the delegate can be sent from a different thread than the main thread.

When the ubudu-sdk calls the delegate, it catches all the exceptions, and logs them as errors; it then proceeds normally.

Note: Each manager can have also a specialized delegate with covariant argument types. When a specialized delegate is set, it shadows the area delegate, which is then ignored.

    package com.ubudu.sdk;

    /**
     *
     * The UbuduSDK send the application the following messages:
     *
     */
    public interface UbuduAreaDelegate
    {

      /**
      *
      * When the manager fails to start, the delegate receives statusChanged(SERVICE_UNAVAILABLE)
      * When it started successfully, the delegate receives statusChanged(SERVICE_STARTED)
      * When it stops, the delegate receives statusChanged(SERVICE_STOPPED)
      *
      * If there is no delegate, if if the statusChanged method returns
      * false, then a Toast message is displayed.
      *
      */
      public static final int SERVICE_UNAVAILABLE=0;
      public static final int SERVICE_STARTED=1;
      public static final int SERVICE_STOPPED=2;
      public boolean statusChanged(int change);


      /**
       *
       * position changed (new position)
       *
       */
      public void positionChanged(android.location.Location newPosition);

      /**
       *
       * area entered event (area): This is a raw event.  An action
       * may not be taken by the SDK according to the rules.
       *
       */
      public void areaEntered(UbuduArea enteredArea);

      /**
       *
       * area exited event (area): This is a raw event.  An action
       * may not be taken by the SDK according to the rules.
       *
       */
      public void areaExited(UbuduArea exitedArea);

      /**
       *
       * server notification (url): when  automatic server notifications
       * sending is disallowed, the SDK sends this message to the application
       * to let it notify the server thru the given url, or be notified.
       * The delegate must return true to allow the SDK continue
       * processing the actions, or false to abord processing the actions.
       *
       */
      public boolean notifyServer(java.net.URL notificationServerUrl);


      /**
       *
       * This message is sent to the delegate when the rule antecedant are
       * all fullfilled after the server notification has been sent, and
       * before the actions are taken.  It is possible no action will be taken
       * (either because there's none, or because of other constraints
       * preventing them to be taken).
       * The event.notification is set, and event.notification.payload() contains the payload.
       *
       */
      public void ruleFiredForEvent(UbuduEvent event);

      /**
       *
       * Area notification (notification)  when automatic user
       * notification sending is disallowed, the SDK sends this message to
       * the application, to let it send the _`notifications` or otherwise deal
       * with it.
       * The event.notification is set, and event.notification.payload() contains the payload.
       *
       */
      public void notifyUserForEvent(UbuduEvent event);

    }

###### UbuduAreaManager

    package com.ubudu.sdk;


    public interface UbuduAreaManager 
    {

      /**
       *
       * The maximum number of notifications the user can receive each day
       * (from 00:00:00 to 23:59:59).
       *
       */
      public int maximumNumberOfNotificationsByDay();
      public void setMaximumNumberOfNotificationsByDay(int maximum);


      /**
       *
       * The delegate.
       *
       */
      public void setAreaDelegate(UbuduAreaDelegate areaDelegate);
      public UbuduAreaDelegate areaDelegate();


      /**
       *
       * Start and stop the area monitoring.
       *
       * When stopped, no background activity occurs.
       *
       * start returns null on success, or an error object if it can't start.
       */
      public java.lang.Error start(android.content.Context clientContext);
      public void stop(android.content.Context clientContext);
      public boolean isMonitoring();


      /**
       *
       * Allow/disallow automatic user notification sending (allowed by
       * default).  The user notifications have a text (or a SDK provided
       * default text), and embed an url to be open and/or a PassBook url to
       * be open when the user selects the notification.
       *
       */
      public void setEnableAutomaticUserNotificationSending(boolean enable);
      public boolean automaticUserNotificationSendingIsEnabled();

      /**
       *
       * allow/disallow automatic server notifications sending (allowed by default).
       *
       */
      public void setEnableAutomaticServerNotificationSending(boolean enable);
      public boolean automaticServerNotificationSendingIsEnabled();

      /**
       *
       * allow/disallow trace message logging (Disabled by default).
       *
       */
      public void setEnableTraceMessageLogging(boolean enable);
      public boolean traceMessageLoggingIsEnabled();


      /**
       * 
       * the current position as known by the SDK.
       *
       */
      public android.location.Location currentPosition();


      /**
       *
       * the list of areas.
       *
       */
      public java.util.List<UbuduArea> areas();


      /**
       *
       * determine if an area is "monitored" (when the current position is
       * close enough of the area, and the current time is within the
       * start/end dates and scheduled open times).
       *
       */
      public boolean areaIsMonitored(UbuduArea area);


      /**
       * 
       * determine if an area is "active" (when the current position and
       * current time is 'inside').
       *
       */
      public boolean areaIsActive(UbuduArea area);


      /**
       *
       * send the predefined user notification for an active area (to be
       * used when automatic sending is not allowed, upon reception of an
       * event indicating this geofence is activated or deactivated).
       * 
       */
      public void notifyUserForEvent(UbuduEvent event);


    }

##### Geofence Classes and Interfaces

###### UbuduGeofence

:

###### UbuduGeofenceEvent

:

###### UbuduGeofenceDelegate

:

###### UbuduGeofenceManager

    package com.ubudu.sdk;


    /**
     * UbuduGeofenceManager let the application access to the geofencing
     * features of the Ubudu SDK.  It provides access to the specific
     * sub-managers.
     */
    public interface UbuduGeofenceManager extends UbuduAreaManager
    ;

      /**
       *
       * The list of geofences.
       *
       * This is the same list as returned by areas(), but with the proper
       * covariant type.
       *
       */
      public java.util.List<UbuduGeofence> geofences();

    }

##### Proximity Beacon Classes and Interfaces

###### UbuduBeaconRegion

    package com.ubudu.sdk;

    public interface UbuduBeaconRegion extends UbuduArea
    {

      /**
       * The proximityUUID of the beacons being targeted.
       */
      public String proximityUUID();


      /**
       * The major of the beacons being targeted.  May be null if any major is accepted.
       * (br.major()==null => br.minor()==null)
       */
      public Integer major();

      /**
       * The minor of the beacons being targeted.  May be null if any minor is accepted.
       */
      public Integer minor();

    }

###### UbuduBeacon

    package com.ubudu.sdk;

    /**
     * Instances of UbuduBeacon represent actual beacons detected.
     */
    public interface UbuduBeacon
    {

      /**
       * The proximityUUID of the detected device.
       */
      public String proximityUUID();

      /**
       * The major of detected device
       */
      public int major();

      /**
       * The minor of the detected device.
       */
      public int minor();

      /**
       * The RSSI in dBm of the detected device.
       */
      public double rssi();

      /**
       * The maximum (closest) promixity detected for the beacon.
       */
      public int detectedProximity();
      public static final int PROXIMITY_UNKNOWN=UbuduRule.Antecedant.PROXIMITY_ANY;
      public static final int PROXIMITY_IMMEDIATE=UbuduRule.Antecedant.PROXIMITY_IMMEDIATE;
      public static final int PROXIMITY_NEAR=UbuduRule.Antecedant.PROXIMITY_NEAR;
      public static final int PROXIMITY_FAR=UbuduRule.Antecedant.PROXIMITY_FAR;


      /**
       * The native device detected.
       */
      public android.bluetooth.BluetoothDevice nativeDevice();

    }

###### UbuduBeaconRegionEvent

:

###### UbuduBeaconRegionDelegate

    package com.ubudu.sdk;

    import java.net.URL;


    /**
     *
     * The UbuduSDK sends the application the following messages:
     *
     */
    public interface UbuduBeaconRegionDelegate
    {

      /**
      *
      * When the manager fails to start, the delegate receives statusChanged(SERVICE_UNAVAILABLE)
      * When it started successfully, the delegate receives statusChanged(SERVICE_STARTED)
      * When it stops, the delegate receives statusChanged(SERVICE_STOPPED)
      *
      * If there is no delegate, if if the statusChanged method returns
      * false, then a Toast message is displayed.
      *
      */
      public static final int SERVICE_UNAVAILABLE=0;
      public static final int SERVICE_STARTED=1;
      public static final int SERVICE_STOPPED=2;
      public boolean statusChanged(int change);

      /**
       *
       * position changed (new position)
       *
       */
      public void positionChanged(android.location.Location newPosition);

      /**
       *
       * beacon region entered event (area): This is a raw event.  An action
       * may not be taken by the SDK according to the rules.
       *
       */
      public void areaEntered(UbuduBeaconRegion enteredArea);

      /**
       *
       * beacon region exited event (area): This is a raw event.  An action
       * may not be taken by the SDK according to the rules.
       *
       */
      public void areaExited(UbuduBeaconRegion exitedArea);

      /**
       *
       * server notification (url): when  automatic server notifications
       * sending is disallowed, the SDK sends this message to the application
       * to let it notify the server thru the given url.
       *
       */
      public boolean notifyServer(URL notificationServerUrl);


      /**
       *
       * This message is sent to the delegate when the rule antecedant are
       * all fullfilled after the server notification has been sent, and
       * before the actions are taken.  It is possible no action is taken
       * (either because there's none, or because of other constraints
       * preventing them to be taken).
       * The event.notification is set, and event.notification.payload() contains the payload.
       *
       */
      public void ruleFiredForEvent(UbuduBeaconRegionEvent event);

      /**
       *
       * Area notification (notification)  when automatic user
       * notification sending is disallowed, the SDK sends this message to
       * the application, to let it send the _`notifications` or otherwise deal
       * with it.
       * The event.notification is set, and event.notification.payload() contains the payload.
       *
       */
      public void notifyUserForEvent(UbuduBeaconRegionEvent event);

    }

###### UbuduBeaconManager

    package com.ubudu.sdk;


    public interface UbuduBeaconManager extends UbuduAreaManager
    ;

      /**
       *
       * The ProximityUUID selects the beacons specific to the application.
       *
       */
      public void setProximityUUID(String aProximityUUID);
      public String proximityUUID();



      /**
       *
       * The list of beacon regions.
       *
       * This is the same list as returned by areas() but with the proper
       * covariant type.
       *
       */
      public java.util.List<UbuduBeaconRegion> beaconRegions();


    }

##### Class Diagram

![image](ubudu-sdk-class-diagram.png)

