<!-- -*- mode:markdown;coding:utf-8 -*- -->

# Android Ubudu-SDK

## Add the dependencies

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
        compile('com.ubudu.sdk:ubudu-sdk:2.2.2@aar') {
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
   and compile them with your application.### The Android Manifest File.

The Ubudu SDK requires specific permissions, activities, receivers and
services.  They're added automatically by the Gradle AndroidManifest
merging feature.  You can find the AndroidManifest.xml file in the
ubudu-sdk-*.aar zip archive, which you can find in the local maven
cache in ~/.m2/caches/modules-2/ or in the
[Ubudu Nexus Repository](http://nexus.ubudu.com:8081/nexus/content/groups/public//com/ubudu/sdk/ubudu-sdk/).

## Ubudu SDK Demo 

A little demonstration application for the Ubudu SDK.  You should
configure your own namespace in the MainActivity.java before
compiling.


## Ubudu SDK Dev App

This is the application used by Ubudu SDK developers to exercise the
Ubudu SDK.

It is not a typical example for a usual Ubudu SDK application, but it
may help developers to test out the SDK.

Usage:

1. Select Authenticate in the menu, and enter your OOAUTH2 access token.

2. click on Save.

3. there's a little bug so you'll have to remove the virtual keyboard
   so the view is updated with the list of applications you have
   configured.

4. click on the application (namespace) you want to use.

5. Select "Show Logs" in the menu.

6. Select "Select Beacons" in the menu.

7. Enter or exit the area of a beacon.

8. Use the "List the Beacons" menu to get the list of monitored (M)
   and active (A) beacons.

<!-- ======================================================== -->

