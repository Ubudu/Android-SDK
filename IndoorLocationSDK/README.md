Adding Indoor Location to mobile application
===========

Indoor Location is available inside the Ubudu SDK. Before using it the SDK must be instantiated:

	UbuduSDK mUbuduSdk = UbuduSDK.getSharedInstance(getApplicationContext());
	
Indoor Location uses the delegate pattern to communicate with the application. Delegate object must implement `com.ubudu.indoorlocation.UbuduIndoorLocationDelegate` interface which defines all the events that must be handled by the application:

	public class MyIndoorLocationDelegate implements UbuduIndoorLocationDelegate{ ... }
	
The `UbuduIndoorLocationManager` instance is available after `UbuduSDK` initialization:
	
	MyIndoorLocationDelegate myIndoorLocationDelegate = new MyIndoorLocationDelegate(getApplicationContext());
	UbuduIndoorLocationManager mIndoorLocalizationManager = mUbuduSdk.getIndoorLocalizationManager();
	
The delegate object must be then passed to the indoor location manager:

	mIndoorLocalizationManager.setIndoorLocationDelegate(myIndoorLocationDelegate);
	
To use indoor location a map must be provided. Map it must be first created in the online Ubudu manager platform. To create and configure a map:

-   go on the Ubudu manager platform,
   
-   select a venue in Venues & indoor maps,
   
-	add new venue or go to the details of one of available venues,

-	click on the Maps button and click Add,

-	configure and save all the map's information.

Once the map creation process is completed the uuid (key) of the map is used by the mobile application. To download the map in the application its uuid must be given as an argument to the following method of `UbuduIndoorLocationManager`, for example:

	mIndoorLocalizationManager.loadMapWithKey("9e2a2220d5830132a0030a824b34cee9");

After invoking the method above the map's data is automatically downloaded from the Ubudu manager platform. Now to start the indoor location the following code must be executed:

	mIndoorLocalizationManager.start();

There are two callback methods in the delegate's interface that handle `start()` result:

	void startSucceed();
	void startFailed();
	
`startFailed()` is invoked when the Indoor Location could not be started because of the network connection timeout. `startSucceed()` is invoked when Indoor Location was started successfully and the location updates will be automatically passed to the delegate's methods. 

To stop indoor location simply call:

	mIndoorLocalizationManager.stop();
	
This method stops the bluetooth monitoring related with indoor location purposes so it is important to invoke it when the location is no longer needed.