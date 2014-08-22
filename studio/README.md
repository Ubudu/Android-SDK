<!-- -*- mode:markdown;coding:utf-8 -*- -->

# Android-SDK

## Ubudu contextual interactions SDK for Android

For general information and usage, see Android-SDK/README.md

### Adding the Ubudu SDK framework to your Android Studio project

#### Instruction for Android Studio projects:

Starting to use the Ubudu SDK with an Android Studio app is very simple.
Have a look at the UbuduSDKDemo project in the directory for a complete example.

Your first need to specify the dependency on the Ubudu SDK:

1. In the build.gradle file of your application module, add the Ubudu nexus repository:

```gradle
    repositories {
        mavenCentral()
        maven { url 'http://5.135.82.92:8081/nexus/content/groups/public/' }
    }
```

2. In the build.gradle file of your application module, add the Ubudu SDK dependency:

```gradle
    dependencies {
        compile('com.ubudu.sdk:ubudu-sdk:1.1.0-SNAPSHOT@aar') {
            transitive = true
        }
        // …
    }
```

  You may browse the
  [Ubudu Nexus Repository](http://5.135.82.92:8081/nexus/content/groups/public/com/ubudu/sdk/ubudu-sdk/)
  to see the most recent version available.
  
3. That's it.  In Android Studio, click on the "Sync Project with
   Gradle Files" button, and select "Rebuild Project"  in the Build
   menu, and it should download the Ubudu SDK and its dependencies,
   and compile them with your application. See
   [Android-SDK/README.md](/README.md) for an introduction on how to
   use the SDK.

4. You may use the gradle dependencies command to see them:

```shell
    [UbuduSDKDemo]$ ./gradlew :app:dependencies
    # …
    compile - Classpath for compiling the main sources.
    +--- com.android.support:support-v4:20.0.0
    |    \--- com.android.support:support-annotations:20.0.0
    +--- com.google.android.gms:play-services:5.2.08
    |    \--- com.android.support:support-v4:19.1.0 -> 20.0.0 (*)
    \--- com.ubudu.sdk:ubudu-sdk:1.1.0-SNAPSHOT
         +--- com.ubudu:volley:4.4.4-SNAPSHOT
         +--- com.google.android.gms:play-services:5.2.08 (*)
         +--- com.google.code.gson:gson:2.3
         \--- com.j256.ormlite:ormlite-android:4.48
              \--- com.j256.ormlite:ormlite-core:4.48
    # …
```

### The Android Manifest File.

The Ubudu SDK requires specific permissions, activities, receivers and
services.  They're added automatically by the Gradle AndroidManifest
merging feature.

<!-- ======================================================== -->

