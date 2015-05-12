`UbuduSDK` User Manual - version 1.5.1
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
<col width="13%" />
<col width="29%" />
<col width="43%" />
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
<td align="left">Added setWifiBleFixDisabled for disabling fix for wifi &amp; ble ble issue. Fix bug with user's tags.</td>
</tr>
<tr class="even">
<td align="left"><p>1.4.0</p>
<blockquote>
<h3>|</h3>
<h4>|</h4>
</blockquote></td>
<td align="left"><p>2014-11-13</p>
<blockquote>
<h3>|</h3>
<h4>|</h4>
</blockquote></td>
<td align="left"><p>Tomasz Ziolkowski</p>
<blockquote>
<p>|Regi |UUID |the |diff |the</p>
</blockquote></td>
<td align="left">Add setRegionExitMinDelay setter to set delay after which delegate get notified about exiting region ons are defined by proximiy . That means all beacons with same proximityUUID and erent major/minor belongs to shame region.</td>
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
<td align="left"><ul>
<li>Improve Stability of the SDK.</li>
</ul>
- Add medumFar and highFar proximities</td>
</tr>
<tr class="odd">
<td align="left">1.4.9</td>
<td align="left">2015-04-02</td>
<td align="left">Tomasz Ziolkowski</td>
<td align="left"><ul>
<li>Improve rssi measurements.</li>
<li>Fix bug with starting sdk</li>
</ul></td>
</tr>
<tr class="even">
<td align="left">1.4.10</td>
<td align="left">2015-04-09</td>
<td align="left">Tomasz Ziolkowski</td>
<td align="left"><ul>
<li>Fix getting native device from UbuduBeacon.</li>
</ul></td>
</tr>
<tr class="odd">
<td align="left"><p>1.5.0</p>
<blockquote>
<p>|</p>
</blockquote></td>
<td align="left"><p>2015-04-17</p>
<blockquote>
<p>|</p>
</blockquote></td>
<td align="left"><p>Tomasz Ziolkowski</p>
<blockquote>
<p>|Deep linking</p>
</blockquote></td>
<td align="left">Stability improvements and bug fixes. Reduced verbosity of logcat/logs. Lower frequency to send async logged event to server. actions. Optimisation of user tags management.</td>
</tr>
<tr class="even">
<td align="left"><p>1.5.1</p>
<blockquote>
<p>|</p>
</blockquote></td>
<td align="left"><p>2015-05-12</p>
<blockquote>
<p>|</p>
</blockquote></td>
<td align="left"><p>Tomasz Ziolkowski</p>
<blockquote>
<p>|secured beac</p>
</blockquote></td>
<td align="left"><ul>
<li>Improve averaging rssi</li>
<li>Fix for relative proximity</li>
</ul>
- Add API for reseting global event counter - Fix bug while working with ons.</td>
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
      public UbuduUltrasoundManager getUltrasoundManager(){…}


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

:

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

    package com.ubudu.sdk;

    public interface UbuduEvent 
    {
      public static final int ENTERED=1;
      public static final int EXITED=2;

      public int eventKind();
      public UbuduArea area();
      public UbuduNotification notification();
      public void setNotification(UbuduNotification newNotification);
    }

###### UbuduAreaDelegate

The messages to the delegate can be sent from a different thread than the main thread.

When the ubudu-sdk calls the delegate, it catches all the exceptions, and logs them as errors; it then proceeds normally.

Note: Each manager can have also a specialized delegate with covariant argument types. When a specialized delegate is set, it shadows the area delegate, which is then ignored.

:

###### UbuduAreaManager

:

##### Geofence Classes and Interfaces

###### UbuduGeofence

    package com.ubudu.sdk;

    public interface UbuduGeofence extends UbuduArea
    ;

      /**
       *
       * The latitude of the center of the geofence, in degree (-90.0° to +90.0°).
       *
       */
      public double centerLatitude();

      /**
       *
       * The longitude of the center of the geofence, in degree (-180.0° to +180.0°).
       *
       */
      public double centerLongitude();

      /**
       *
       * The radius of geofence, in meter (0.0 m to 40075017.0 m).
       *
       */
      public double radius();


      /**
       *
       * When the geofence is active (cf. UbuduManager.areaIsActive()),
       * this method return the native geofence object.
       *
       */
      public com.google.android.gms.location.Geofence nativeGeofence();

    }

###### UbuduGeofenceEvent

    package com.ubudu.sdk;

    public inteface UbuduGeofenceEvent extends UbuduEvent
    {
      /**
       * Returns the event area in the right covariant class.
       */
      public UbuduGeofence geofence();
    }

    /*
     Invariant:

     ev.geofence()==ev.area()

     */

###### UbuduGeofenceDelegate

    package com.ubudu.sdk;

    import java.net.URL;


    /**
     *
     * The UbuduSDK sends the application the following messages:
     *
     */
    public interface UbuduGeofenceDelegate
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
       * geofence entered event (area): This is a raw event.  An action
       * may not be taken by the SDK according to the rules.
       *
       */
      public void areaEntered(UbuduGeofence enteredArea);

      /**
       *
       * geofence exited event (area): This is a raw event.  An action
       * may not be taken by the SDK according to the rules.
       *
       */
      public void areaExited(UbuduGeofence exitedArea);

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
      public void ruleFiredForEvent(UbuduGeofenceEvent event);

      /**
       *
       * Area notification (notification)  when automatic user
       * notification sending is disallowed, the SDK sends this message to
       * the application, to let it send the _`notifications` or otherwise deal
       * with it.
       * The event.notification is set, and event.notification.payload() contains the payload.
       *
       */
      public void notifyUserForEvent(UbuduGeofenceEvent event);

    }

###### UbuduGeofenceManager

:

##### Proximity Beacon Classes and Interfaces

###### UbuduBeaconRegion

:

###### UbuduBeacon

:

###### UbuduBeaconRegionEvent

    package com.ubudu.sdk;

    public interface UbuduBeaconRegionEvent extends UbuduEvent
    {

      /**
       *
       * For type.
       * The beaconRegion is the area that has been activated or
       * deactivated by this event.
       *
       */
      public UbuduBeaconRegion beaconRegion();


      /**
       *
       * The beacon object provides the specific data of the detected
       * beacon.
       *
       */
      public UbuduBeacon beacon();
    }


    /*
     Invariant:

     ((ev.beaconRegion()==ev.area())
     && (ev.beaconRegion().proximityUUID().equals(ev.beacon().proximityUUID()))
     && ((ev.beaconRegion().major()<0) || (ev.beaconRegion().major==ev.beacon().major()))
     && ((ev.beaconRegion().minor()<0) || (ev.beaconRegion().minor==ev.beacon().minor())))

     */

###### UbuduBeaconRegionDelegate

:

###### UbuduBeaconManager

:

##### Ultrasound Code Detector Classes and Interfaces

###### UbuduUltrasoundArea

    package com.ubudu.sdk;

    public interface UbuduUltrasoundArea extends UbuduArea
    {

      /**
       * An area that expects any code will return nil.
       */
      public java.util.List<java.lang.Byte> expectedCode();

      /**
       * Default reliability is 0.2
       */
      public double requiredReliability();

    }

###### UbuduUltrasound

    package com.ubudu.sdk;

    public interface UbuduUltrasound
    {

      /**
       * The region that detected this ultrasound.
       */
      public UbuduUltrasoundArea area();

      public java.util.List<java.lang.Byte> detectedCode();

      public double reliability();

    }

###### UbuduUltrasoundEvent

    package com.ubudu.sdk;


    public interface UbuduUltrasoundEvent extends UbuduEvent
    {

      /**
       * The area, with the right covariant class.
       */
      public UbuduUltrasoundArea ultrasoundArea();

      /**
       *
       * The ultrasound object provides the specific data of the detected
       * ultrasound code.
       *
       */
      public UbuduUltrasound ultrasound();

    }

    /*
     Invariant:

     (ev.ultrasoundArea==ev.area)
     && (ev.area==ev.ultrasound.area)

     */

###### UbuduUltrasoundDelegate

The messages to the delegate can be sent from a different thread than the main thread.

When the ubudu-sdk calls the delegate, it catches all the exceptions, and logs them as errors; it then proceeds normally.

    package com.ubudu.sdk;

    public interface UbuduUltrasoundDelegate
    {

      /**
       *
       * Signals that listening on the microphone has started.
       *
       * The delegate will receive messages from the UbuduAreaDelegate
       * protocol when codes are detected, until the delegate is changed, or
       * listening is stopped in which case the delegate receives a
       * listeningStoppedByDetector: message.
       *
       * NOTE: This method will be called from the detector thread.  The
       * delegate should go back to the main thread if it needs to.
       *
       */
      public void listeningStartedByDetector(UbuduUltrasoundManager detector);


      /**
       *
       * Signals that listening on the microphone has stopped.
       *
       * NOTE: This method will be called from the detector thread.  The
       * delegate should go back to the main thread if it needs to.
       *
       */
      public void listeningStoppedByDetector(UbuduUltrasoundManager detector);

    }

###### UbuduUltrasoundManager

    package com.ubudu.sdk;

    /**
     *
     * UbuduUltrasoundManager let the application access to the ultrasound
     * code detector of the Ubudu SDK. 
     *
     * Note: until we provide the API to let the application create areas
     * and rules, areas() will return a list of a single
     * UbuduUltrasoundArea with a single area that expects any code at a
     * default reliability, with a single default on_entry rule.
     *
     */
    public interface UbuduUltrasoundManager extends UbuduAreaManager
    {

      /**
       *
       * An UbuduUltrasoundManager instance has two delegates: an
       * areaDelegate,  and an ultrasoundDelegate. They could be the same
       * object, if it implements both protocols, but the manager must
       * keep two references.
       *
       */
      public void setUltrasoundDelegate(UbuduUltrasoundDelegate ultrasoundDelegate);
      public UbuduAreaDelegate ultrasoundDelegate();


      /**
       *
       * This is the time remaining before the end of listening duration.
       * While remainingTime>0, isListening can be YES.
       *
       * (expressed in millisecond).
       *
       */
      public long remainingTime();

      /**
       *
       * Whether the detector is currently receiving sound from the microphone.
       * NOTE: when listening is started for a long duration, microphone
       * capture may be intermitent. cf. -remainingTime.
       *
       */
      public boolean isListening();


      /**
       *
       * Detector Parameters:
       * May be set before starting.
       * Changes while remainingTime>0 are ignored until next listening period.
       *
       * samplingRate        audio sampling rate (Hz); default 44100,
       *                     allowed values: 192000, 176400, 96000, 88200,
       *                     48000, 44100, 32000, 22050, 16000, 11025, 8000.
       * codeLength          expected watermarking payload length.
       * carrierFrequency    expected carrier signal starting frequency (Hz).
       * fastScanMode        fast scan mode (0 or 1).
       * allowNotReliable    allow detecting even not reliable watermarks (0 or 1).
       * carrierThreshold    minimal carrier threshold (0.0 - 1.0, default 0.2).
       *
       */
      public long samplingRate();
      public void setSamplingrate(long newSamplingrate);
      public long codeLength();
      public void setCodelength(long newCodelength);
      public long carrierFrequency();
      public void setCarrierfrequency(long newCarrierfrequency);
      public long fastScanMode();
      public void setFastscanmode(long newFastscanmode);
      public long allowNotReliable();
      public void setAllownotreliable(long newAllownotreliable);
      public double carrierThreshold();
      public void setCarrierthreshold(double newCarrierthreshold);


      /**
       *
       * listeningDuration is the maximum time listening
       * should last, during each period (in millisecond).
       *
       * Listening occurs for a minimum time, and beyond is bounded by
       * listeningDuration is.  See the remainingTime property.  When
       * listening for long durations, the actual sound capture should be
       * configured to be intermitent.  See the isListening property.
       * 
       */
      public long listeningDuration();
      public void setListeningduration(long newListeningduration);

      /**
       *
       * period is the duration of a listening/not listening cycle (in
       * millisecond).  The duration of the listening part of the cycle is
       * given by listeningDuration.
       *
       */
      public long period();
      public void setPeriod(long newPeriod);


      /**
       * minimumReliability code received with a reliability below this
       * minimum will be ignored.
       */
      public double minimumReliability();
      public void setMinimumreliability(double newMinimumreliability);

      /**
       * when an error occurs during detection, it is reported here.
       */
      public java.lang.Error error();





      /**
       * An utility method. 
       */
      public static java.util.List<java.lang.Byte> dataFromHexadecimalString(String string);



      /**
       *
       * Inserts a UbuduUltrasoundArea in the list of areas.
       *
       * url must have "ubudu-geous" as scheme, and must have a parameterString
       * containing the following parameters:
       * 
       * id: the regionId of the fence.
       * code: the expected ultrasound code (in hexadecimal).
       * url: the url to go to when the ultrasound code is detected.
       * notification: (optional) the text of a notification for delayed url opening.
       * 
       */
      public void expectAreaAtURL(java.lang.URL url);


      /**
       * set listeningDuration and minimumReliability and call start.
       *
       * Starts a background thread that listens to ultrasounds captured on
       * the microphone, and detects in them a code. 
       *
       * If this message is send while remainingTime>0, then a new duration and
       * minimumReliability are set, and the listening goes on.
       *
       */
      public void startListeningForDurationWithinPeriodWithMinimumReliability(android.content.Context clientContext,
                                                                              long listeningDuration,
                                                                              long period,
                                                                              double minimumReliability);

    }

##### Class Diagram

![image](ubudu-sdk-class-diagram.png)

