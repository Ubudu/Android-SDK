`UbuduSDK` User Manual - version 2.2.1
======================================

Introduction
------------

This is the user manual of the `UbuduSDK`.

This SDK contains several components:

-   Ubudu Geofence SDK,
-   Ubudu Proxmity Beacon SDK (Bluetooth).


`UbuduSDK` for Android
----------------------

The `UbuduSDK` library to use in all applications connecting to Ubudu geofences and bluetooth LE beacons for geomarketing services for Android platform.

##### Getting started

This section will contain information regarding adding the `UbuduSDK` to any host application along with necessary project configuration which are required by the `UbuduSDK`.

###### Add the dependencies

Starting to use the Ubudu SDK with an Android Studio app is very simple.
Have a look at the UbuduSDKDemo project in the directory for a complete example.

Your first need to specify the dependency on the Ubudu SDK:

1) In the build.gradle file of your application module, add the Ubudu nexus repository:

```
    repositories {
        mavenCentral()
        maven { url 'http://nexus.ubudu.com:8081/nexus/content/groups/public/' }
    }
```

2) In the build.gradle file of your application module, add the Ubudu SDK dependency:

```
    dependencies {
        compile('com.ubudu.sdk:ubudu-sdk:2.2.1@aar') {
            transitive = true
        }
        // …
    }
```

  You may browse the
  [Ubudu Nexus Repository](http://nexus.ubudu.com:8081/nexus/content/groups/public//com/ubudu/sdk/ubudu-sdk/)
  to see the most recent version available.
  
3) That's it.  In Android Studio, click on the "Sync Project with
   Gradle Files" button, and select "Rebuild Project"  in the Build
   menu, and it should download the Ubudu SDK and its dependencies,
   and compile them with your application.

##### Usage instructions

To start using `UbuduSDK` use following code:

First get instance of `UbuduSDK`. We use singleton as there is no need of many instances of this class. :

    UbuduSDK sdk=UbuduSDK.getSharedInstance(context);

Set the application namespace :

    sdk.setNamespace(namespace);

Set delegate that handle actions from SDK :

	UbuduBeaconManager mBeaconManager=sdk.getBeaconManager();
    mBeaconManager.setAreaDelegate(someAreaDelegate);
    
    UbuduGeofenceManager mGeofenceManager=sdk.getGeofenceManager();
    mGeofenceManager.setAreaDelegate(someAreaDelegate);

Next start service by calling:

	mBeaconManager.start();
    mGeofenceManager.start();

From this moment application will start receiving geofences and beacons events and notify user in case of proper conditions according to the rules defined in Ubudu manager platform. To stop the SDK use following code:

	mBeaconManager.stop();
    mGeofenceManager.stop();

Starting these commands will first remove tracking any geofences and scanning for beacons and then will also stop the service taking care of all interactions.

##### Design principle of the `UbuduSDK` API

The `com.ubudu.sdk.UbuduSDK` class has a shared instance that is the root of the API. It provides methods to obtain the *managers*, each of which deals with a different kind of areas: geofences and bluetooth LE beacons areas. If the kind of areas is not available on the device, then `null` is returned instead of a manager.

The three manager classes share a common superclass, `com.ubudu.sdk.UbuduAreaManager`, and each deal with covariant subclasses. You can find details in the [JavaDoc](JavaDoc/index.html).

###### Settings

####### `com.ubudu.sdk.UbuduUser` settings

The application may send to the server a user information, which allows the server to filter geofences and beacons on user properties and tags.

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

###### Delegate

The application may implement a delegate object to intercept the processing and notifications upon area entered or exited events.

There is one delegate interface:

    UbuduAreaDelegate             UbuduAreaManager

An `UbuduAreaDelegate` can be configured with the `com.ubudu.sdk.UbuduAreaManager#setAreaDelegate` method, for all the managers. Then this delegate will receive generic `com.ubudu.sdk.UbuduArea` classes which can be casted to `UbuduBeaconRegion` or `UbuduGeofence`.

###### Rule fired

When it is determined that an area entry or exit is valid, the
delegate is informed with this method:

```java
  /**
   * This message is sent to the delegate when the rule antecedant are
   * all fullfilled after the server notification has been sent, and
   * before the actions are taken.  It is possible no action will be taken
   * (either because there's none, or because of other constraints
   * preventing them to be taken).
   * The event.notification is not set yet.
   */
  public void ruleFiredForEvent(UbuduEvent event);
```
When a rule is fired that is configured to display a web page
or open a Samsung Wallet ticket, the action is performed if the
application is active, or a notification is posted if it is in the
background.

###### Custom event handling

It is possible for the eveloper to implement a custom event handling. One might want to perform some custom actions before performing the event's actions to the user like displaying web page, opening deep link or samsung wallet. Developer might also want to maybe pop some custom dialog to ask user for permission to perform action. There is a solution for such need in the `Ubudu SDK`. 

To turn on custom event handling the following methods can to be called:

	mBeaconManager.setEnableAutomaticUserNotificationSending(false);

or

	mGeofenceManager.setEnableAutomaticUserNotificationSending(false);

This will block automatic SDK notifications like status bar notifications when app is in the background or performing the action immediately when the app is in the foreground.

Then when any event is triggered first the `boolean shouldNotifyUserForEvent(UbuduEvent event)` method is called on the delegate object. In this method the developer must decide whether to allow notifying the user about event by returning `true` or block it by returning `false`. Returning `false` will cancel processing of the event.

When `true` is returned then SDK will call `void notifyUserForEvent(UbuduEvent event)` in which developer should notify user for event in his/her own custom way. The application may react however it wants to this event.  If the normal processing is wanted, it can call one of the UbuduSDK methods:

```java
  /**
   * displayWebPage: will start an activity to fetch the web page display it.
   * openSamsungWallet: will forward the samsungWalletURL to the Samsung
   * Wallet application (if available).
   * 
   * When a manager of the UbuduSDK has
   * automaticUserNotificationSendingIsEnabled set to true, and the
   * application is active, then the manager calls directly those
   * methods instead of sending user notifications.
   */
  public void displayWebPage(URL webPageURL);
  public void displayWebPage(String webPageURL);
  public void openSamsungWallet(java.net.URL samsungWalletURL);
  public void openSamsungWallet(String samsungWalletURL);
```

When event actions should be eventually performed it is necessary to call `com.ubudu.sdk.UbuduSDK#executeActions(UbuduEvent event)`. The SDK will then perform actions (like opening web page, deeplink, samsung wallet) if any are defined. Also the `open_notif` log type will be send to server to keep statistics consistent. This log indicates that the user really opened the notification and its content was presented. 

** Note: `com.ubudu.sdk.UbuduSDK#executeActions(UbuduEvent event)` method should be always called when performing event action to the user even if there are no web url, deepling, samsung wallet actions defined!
**

If there is a need to delay performing the action to the user (e.g. wait for the user to accept the action etc) then an extended delegate interface must be implemented:

	com.ududu.sdk.UbuduDelayedCustomEventHandlingAreaDelegate extends UbuduAreaDelegate
	
This delegate interface provides an additional method:

	/**
     * @param event    event
     * @param callback callback object to notify the SDK that the user should be notified about the event.
     */
    void shouldNotifyUserForEvent(UbuduEvent event, UbuduAreaDelegateNotifyUserForEventCallback callback);

When user implements the `UbuduDelayedCustomEventHandlingAreaDelegate` instead of the default `UbuduAreaDelegate` the SDK will always call `void shouldNotifyUserForEvent(UbuduEvent event, UbuduAreaDelegateNotifyUserForEventCallback callback);` before calling `void notifyUserForEvent(UbuduEvent event)` which will happen only after calling the `UbuduAreaDelegateNotifyUserForEventCallback callback#shouldNotifyUser(boolean shouldNotify);` callback method.

###### Operation modes

<table>
<colgroup>
<col width="34%" />
<col width="19%" />
<col width="46%" />
</colgroup>
<thead>
<tr class="header">
<th align="left">automatic notification sending is enabled</th>
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

