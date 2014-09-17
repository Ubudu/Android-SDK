<!-- -*- mode:markdown;coding:utf-8 -*- -->

# Android-SDK

## Ubudu contextual interactions SDK for Android


For information on pricing, features, examples and our fantastic
i-beacon compatible beacons please check our web-site
[http://www.ubudu.com](http://www.ubudu.com). It is totally free to
develop with Ubudu SDKs and we only charge usage. 

### System and hardware requirements

#### For Geofencing
- Android >=4.3

#### For beacons related features
- A Bluetooth Low Energy (BLE) radio, present in some devices as part of Bluetooth 4.0
- A working BLE API that allows third-party app code to access BLE. This is present in some Android 4.3+ devices.
- List of potentially supported devices (OS v4.3+ only)
    + HTC One, Max, Mini, M8 
    + LG G2, G Pro2, G Flex, Vu3.0
    + Motorola Moto G, Moto X
    + Motorola Droid RAZR M, RAZR HD, RAZR Maxx HD 
    + Motorola Droid Ultra, Maxx, Mini (OS v4.4+ only)
    + LGE Nexus 4\*, 5\*, 7 (2013)
    + Samsung Galaxy S3\*, S3 Mini, S4\*, S4 Mini, S4 Active, S5
    + Samsung Galaxy Note 2, Note 3, Note 10.1
    + Sony Xperia Z, Z1, Z1 Compact
    + Sony Xperia Tablet Z, Ultra, ZR, ZL
    + Sony Xperia SP, T, TX, V

\* tested devices. Nexus 4 has well known problems when Wifi and BLE operate simultanesouly :-(

### Instruction for Eclipse projects

See [Android-SDK/eclipse/README.md](/eclipse/README.md)

### Instruction for Android Studio projects

See [Android-SDK/studio/README.md](/studio/README.md)


### Usage instructions

To start using `UbuduSDK` use following code:

The `com.ubudu.sdk.UbuduSDK` class has a shared instance that is the
root of the API.
```java
    UbuduSDK sdk=UbuduSDK.getSharedInstance(context);
```
#### Namespace

The namespace is a UUID corresponding to the application, provided by
the the Ubudu contextual interaction manager web site.

```java
    String namespace="00112233445566778899aabbccddeeff";
    sdk.setNamespace(namespace);
```

This should be set before starting the managers (see below).

#### User information

The application may send to the server user information, which allows
the server to filter geofences and beacons on user properties and
tags.

```java
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
    sdk.setUserInformation(user);
```
#### Logging to file

It would be useful feature during development. If you encounter any problems you can send us logs so we can help you solve the problem.

To enable logging to file use:

```java		
UbuduSDK sdk = UbuduSDK.getSharedInstance(getApplicationContext());
sdk.setFileLogEnabled(true);
```

To get logs use:

```java
String logs = sdk.debugFileContent();
```

To clear log file use:

```java
sdk.clearDebugFile(getActivity().getApplicationContext());
```

You can check the example in ubudu-sdk-demo2 (eclipse) or UbuduSDKDemo (Android Studio).

#### Managers

The `UbuduSDK` instance provides methods to obtain the *managers*, each of which deals with
a different kind of areas: geofences, or bluetooth LE beacons.  If the
kind of areas is not available on the device, then `null` is returned
instead of a manager.  The three manager classes share a common
superclass, `com.ubudu.sdk.UbuduAreaManager`, and each deal with
covariant subclasses.

```java
    UbuduGeofenceManager geofenceManager=sdk.getGeofenceManager();
    UbuduBeacoManager    beaconManager=sdk.getBeaconManager();
```

The application can ask each manager to start or stop monitoring its
class of areas:

```java
    geofenceManager.start(context); 
    beaconManager.start(context);
```
To stop monitoring the areas:

```java
    geofenceManager.stop(context);
    beaconManager.stop(context);
```

When a manager is monitoring areas, it goes on doing so in the
background, even when the application is not active.

When an entered or exited, a notification is posted, or when the
application is active, the action is performed as configured on the
Ubudu contextual interaction manager web site.


#### Delegate

The application may configure delegate objects to intercept the processing and notifications upon area entered or exited events.

There are several delegate interfaces, each used by the corresponding manager class: :

    UbuduAreaDelegate             UbuduAreaManager
    UbuduBeaconRegionDelegate     UbuduBeaconManager
    UbuduGeofenceDelegate         UbuduGeofenceManager

They are identical, only with covariant parameters.

An `UbuduAreaDelegate` can be configured with the
`com.ubudu.sdk.UbuduAreaManager#setAreaDelegate` method, for all the
managers, but receiving generic parameters `com.ubudu.sdk.UbuduArea`.

You may also configure a specific delegate with a specific manager,
`com.ubudu.sdk.UbuduGeofenceManager#setGeofenceDelegate`,
`com.ubudu.sdk.UbuduBeaconManager#setBeaconDelegate`, or
`com.ubudu.sdk.UbuduUltrasoundManager#setUltrasoundDelegate`. When a
manager specific delegate is configured, that manager doesn't use the
`UbuduAreaDelegate` configured with `setAreaDelegate`.


When implementing a delegate, all the method of the interface must be
implemented.

You may use a specific covariant delegate:

```java
    public class MyGeofenceDelegate implements UbuduGeofenceDelegate {
     …
    };
    
    geofenceManager.setGeofenceDelegate(new MyGeofenceDelegate());
```

or you may use a generic area delegate for all the managers:


```java
    public class MyAreaDelegate implements UbuduAreaDelegate {
     …
    };

    MyAreaDelegate areaDelegate=new MyAreaDelegate();
    geofenceManager.setAreaDelegate(areaDelegate);
    beaconManager.setAreaDelegate(areaDelegate);
```

In this case, while the delegate will still receive objects of the
right covariant subclasses, the parameters will be typed as the
superclass.


##### Description of the delegate protocol

The messages to the delegate can be sent from a different thread than
the main thread.

When the ubudu-sdk calls the delegate, it catches all the exceptions,
and logs them as errors; it then proceeds normally.

The messages `positionChanged`, `areaEntered` and `areaExited` are
sent to inform the delegate of those occurences.  This doesn't mean
that an action will taken, since this depends on the rules configured
for the area.

```java
  public void positionChanged(android.location.Location newPosition);

  /**
   * area entered event (area): This is a raw event.
   * An action may not be taken by the SDK according to the rules.
   */
  public void areaEntered(… enteredArea);

  /**
   * area exited event (area): This is a raw event.
   * An action may not be taken by the SDK according to the rules.
   */
  public void areaExited(… exitedArea);
```


###### Server notification

When area is entered or exited, if so configured, a server can be
notified and it may enable or disable further processing of the rule.
The application can disable this automatic server notification and
handle it itself thru the delegate.

The manager methods:

```java
  /**
   * allow/disallow automatic server notifications sending (allowed by
   * default).
   */
  public void setEnableAutomaticServerNotificationSending(boolean enable);
  public boolean automaticServerNotificationSendingIsEnabled();
```

let you disable the automatic server notification.  When disabled,
this delegate method is called instead:

```java
  /**
   * server notification (url): when  automatic server notifications
   * sending is disallowed, the SDK sends this message to the application
   * to let it notify the server thru the given url, or be notified.
   * The delegate must return true to allow the SDK continue
   * processing the actions, or false to abord processing the actions.
   */
  public boolean notifyServer(java.net.URL notificationServerUrl);
```

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

###### User notification

Finally, when a rule is fired that is configured to display a web page
or open a Samsung Wallet ticket, the action is performed if the
application is active, or a notification is posted if it is in the
background.

However, this automatic user notification can be disabled, and the
application can handle it thru the delegate.

Those manager methods let the application enable or disable this
automatic notification:

```java
  /**
   * Allow/disallow automatic user notification sending (allowed by default).
   * The user notifications have a text (or a SDK provided default text), and
   * embed an url to be open and/or a PassBook url to be open when the user
   * selects the notification.
   */
  public void setEnableAutomaticUserNotificationSending(boolean enable);
  public boolean automaticUserNotificationSendingIsEnabled();
```

When it is disabled, this delegate method is called to perform the action:

```java
  /**
   * Area notification (notification)  when automatic user
   * notification sending is disallowed, the SDK sends this message to
   * the application, to let it send the _`notifications` or otherwise deal
   * with it.
   */
  public void notifyUserForEvent(UbuduEvent event);
```

The application may react however it wants to this event.  If the
normal processing is wanted, it can call one of the UbuduSDK methods:

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
<td align="left">
<p>false</p>
</td>
<td align="left">
<p>null</p>
</td>
<td align="left">
<p>actions can't be taken</p>
</td>
</tr>
<tr class="even">
<td align="left">
<p>false</p>
</td>
<td align="left">
<p>delegate</p>
</td>
<td align="left">
<p>actions are forwared to the delegate</p>
</td>
</tr>
<tr class="odd">
<td align="left">
<p>true</p>
</td>
<td align="left">
<p>null</p>
</td>
<td align="left">
<p>actions are taken automatically</p>
</td>
</tr>
<tr class="even">
<td align="left">
<p>true</p>
</td>
<td align="left">
<p>delegate</p>
</td>
<td align="left">
<p>actions are taken automatically</p>
</td>
</tr>
</tbody>
</table>


#### Adding map into your project

In ubudu-demo-app2 there is completed example how to use map for
support geofences. You have to only generate and change map API key in
AndroidManifest.xml file. To achieve this follow Google Developers
guide:  
- [How to get Google Maps API key](https://developers.google.com/maps/documentation/android/start#get_an_android_certificate_and_the_google_maps_api_key)


## Documentation

- [User Manual](/ubudu-sdk-user-manual.md)
- [Specifications](/ubudu-sdk-specifications.md)
