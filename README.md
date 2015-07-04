# Android-SDK


## Ubudu SDK for Android


For information on pricing, features, examples and our fantastic
iBeacon compatible beacons please check our web-site
[http://www.ubudu.com](http://www.ubudu.com). It is totally free to
develop with Ubudu SDKs and we only charge usage... above a certain threshold.

Ubudu SDK is composed of the three following components : 
 
* ubudu is a contextual interaction platform designed to help you to develop and deploy new user experiences bridging the physical and digital worlds, by the power of micro-location technology. You can find the java docs of the API here : [http://www.ubudu.com/docs/android/contextual_interactions_sdk/index.html](http://www.ubudu.com/docs/android/contextual_interactions_sdk/index.html) 

* Indoor location provides a solution for mobile devices to estimate their position within an indoor venue. The position is computed in real time and then referenced to the map of the venue. The computation is based on the signal broadcasts received from beacons placed inside the venue. Users create their maps in the ubudu Manager and attach it to a particular venue. You can find the java docs of the API here : [http://www.ubudu.com/docs/android/indoor_location_sdk/index.html](http://www.ubudu.com/docs/android/indoor_location_sdk/index.html)

* ubudu's mesh technology enables regular mobile devices (like smartphones, tablets) to exchange messages with devices which are not in direct proximity and would not be able to connect otherwise. You can find the java docs of the API here : [http://www.ubudu.com/docs/android/mesh_sdk/index.html](http://www.ubudu.com/docs/android/mesh_sdk/index.html)

### System and hardware requirements

#### For Geofencing
- Android >=4.3

#### For beacons related features
- A Bluetooth Low Energy (BLE) radio, present in some devices as part of Bluetooth 4.0
- A working BLE API that allows third-party app code to access BLE. This is present in some Android 4.3+ devices.
- List of potentially supported devices (OS v4.3+ only)
    + HTC One, Max, Mini, M8 
    + LG G2, G Pro2, G Flex, Vu3.0
    + Motorola Moto G*, Moto X
    + Motorola Droid RAZR M, RAZR HD, RAZR Maxx HD 
    + Motorola Droid Ultra, Maxx, Mini (OS v4.4+ only)
    + LGE Nexus 4\*, 5\*, 7 (2013)
    + Samsung Galaxy S3\*, S3 Mini, S4\*, S4 Mini, S4 Active, S5*
    + Samsung Galaxy Note 2, Note 3, Note 10.1
    + Sony Xperia Z, Z1, Z1 Compact
    + Sony Xperia Tablet Z, Ultra, ZR, ZL
    + Sony Xperia SP, T, TX, V

\* tested devices. Nexus 4 has well known problems when Wifi and BLE operate simultanesouly :-(. <b>The issue is fixed in version 1.2.3 :-).</b> 

### Instruction for Eclipse projects

See [UbuduSDK/eclipse/README.md](UbuduSDK/eclipse/README.md)

### Instruction for Android Studio projects

See [UbuduSDK/studio/README.md](UbuduSDK/studio/README.md)

### UbuduSDK contextual interactions
See [Getting started contextual interaction SDK](UbuduSDK/README.md)

### IndoorLocationSDK
See [Getting started indoor location SDK](IndoorLocationSDK/README.md)

### MeshSDK
See [Getting started mesh SDK](MeshSDK/README.md)
