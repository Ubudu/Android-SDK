Ubudu-SDK Changelog:
-------------

<table>
<colgroup>
<col width="12%" />
<col width="14%" />
<col width="16%" />
<col width="56%" />
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
<td align="left">1.0</td>
<td align="left">2013-10-01</td>
<td align="left">PJB</td>
<td align="left">Created.</td>
</tr>
<tr class="even">
<td align="left">1.0.1</td>
<td align="left">2013-10-02</td>
<td align="left">PJB</td>
<td align="left">Integred FK's comments.</td>
</tr>
<tr class="odd">
<td align="left">1.0.2</td>
<td align="left">2013-10-04</td>
<td align="left">PJB</td>
<td align="left">Integred answers to questions.</td>
</tr>
<tr class="even">
<td align="left">1.0.3</td>
<td align="left">2013-10-07</td>
<td align="left">PJB</td>
<td align="left">Added iBeacon Advertisement Messages section.</td>
</tr>
<tr class="odd">
<td align="left">1.0.4</td>
<td align="left">2013-10-09</td>
<td align="left">PJB</td>
<td align="left">Use java.util.Date instead of java.util.Calendar.</td>
</tr>
<tr class="even">
<td align="left">1.0.5</td>
<td align="left">2013-10-09</td>
<td align="left">PJB</td>
<td align="left">Added context parameter to start and stop on android.</td>
</tr>
<tr class="odd">
<td align="left">1.0.6</td>
<td align="left">2013-10-23</td>
<td align="left">PJB</td>
<td align="left">Split out UbuduIOSSDK, and changes to the server section.</td>
</tr>
<tr class="even">
<td align="left">1.0.7</td>
<td align="left">2013-11-04</td>
<td align="left">PJB</td>
<td align="left">Updated and precised the rule/action specification.</td>
</tr>
<tr class="odd">
<td align="left">1.0.8</td>
<td align="left">2013-11-08</td>
<td align="left">PJB</td>
<td align="left">Added UbuduSDK displayWebPage and openPassbook/SamsungWallet utility methods. Refined description of user notifications processing.</td>
</tr>
<tr class="even">
<td align="left">1.0.9</td>
<td align="left">2013-11-21</td>
<td align="left">PJB</td>
<td align="left">Changed the schedule data structure to match the Ruby library used on the server.</td>
</tr>
<tr class="odd">
<td align="left">1.0.10</td>
<td align="left">2013-11-25</td>
<td align="left">PJB</td>
<td align="left"><ul>
<li>renamed version field to api_version in the request.</li>
<li>changed the structure of timezone field.</li>
<li>Added algorithm for opening time schedule.</li>
</ul></td>
</tr>
<tr class="even">
<td align="left">1.0.11</td>
<td align="left">2013-12-30</td>
<td align="left">PJB</td>
<td align="left"><ul>
<li>added the udid field to api requests.</li>
</ul></td>
</tr>
<tr class="odd">
<td align="left">1.0.12</td>
<td align="left">2014-04-24</td>
<td align="left">PJB</td>
<td align="left"><ul>
<li>added specification of JSON payload in notification message.</li>
</ul></td>
</tr>
<tr class="even">
<td align="left">1.0.13</td>
<td align="left">2014-08-13</td>
<td align="left">PJB</td>
<td align="left"><ul>
<li>added the UbuduUser interface.</li>
</ul></td>
</tr>
<tr class="odd">
<td align="left">1.1.0</td>
<td align="left">2014-08-25</td>
<td align="left">PJB</td>
<td align="left">Added UbuduUser interface.</td>
</tr>
<tr class="even">
<td align="left">1.1.1</td>
<td align="left">2014-08-26</td>
<td align="left">PJB</td>
<td align="left">Added anti hacking protocol configuration from the server.</td>
</tr>
<tr class="odd">
<td align="left">1.1.2</td>
<td align="left">2014-08-28</td>
<td align="left">PJB</td>
<td align="left">Added statusChange() delegate method.</td>
</tr>
<tr class="even">
<td align="left">1.2.0</td>
<td align="left">2014-09-16</td>
<td align="left">TZ</td>
<td align="left">Added setFileLogEnabled flag for enabling getting / clearing logs.</td>
</tr>
<tr class="odd">
<td align="left">1.2.1</td>
<td align="left">2014-09-18</td>
<td align="left">TZ</td>
<td align="left">Added support for custom baseURL.</td>
</tr>
<tr class="even">
<td align="left">1.2.3</td>
<td align="left">2014-10-06</td>
<td align="left">TZ</td>
<td align="left">Fix for WiFi &amp; BLE issues.</td>
</tr>
<tr class="odd">
<td align="left">1.2.5</td>
<td align="left">2014-10-17</td>
<td align="left">TZ</td>
<td align="left">Support for continous proximities.</td>
</tr>
<tr class="even">
<td align="left">1.3.0</td>
<td align="left">2014-11-06</td>
<td align="left">TZ</td>
<td align="left">Added setWifiBleFixDisabled for disabling fix for wifi &amp; ble ble issue. Fix bug with user's tags.</td>
</tr>
<tr class="odd">
<td align="left">1.4.0</td>
<td align="left">2014-11-13</td>
<td align="left">Tomasz Ziolkowski</td>
<td align="left"><p>Add setRegionExitMinDelay setter to set delay after which delegate get notified about exiting region.</p> Regions are defined by proximiy UUID. That means all beacons with the same proximityUUID and different major/minor belongs to same region.</p></td>
</tr>
<tr class="even">
<td align="left">1.4.1</td>
<td align="left">2014-11-19</td>
<td align="left">TZ</td>
<td align="left">Bug fixes</td>
</tr>
<tr class="odd">
<td align="left">1.4.2</td>
<td align="left">2014-12-09</td>
<td align="left">TZ</td>
<td align="left">Proximity accuracy improved</td>
</tr>
<tr class="even">
<td align="left">1.4.3</td>
<td align="left">2014-12-23</td>
<td align="left">TZ</td>
<td align="left">Add min/max events count/periods for rules and for the app. Add some more log events.</td>
</tr>
<tr class="odd">
<td align="left">1.4.5</td>
<td align="left">2015-01-13</td>
<td align="left">JBQ</td>
<td align="left"><dl>
<dt>Bug due to Dexguard config which was making</dt>
<dd><p>the SDK unusable.</p>
</dd>
</dl></td>
</tr>
<tr class="even">
<td align="left">1.4.6</td>
<td align="left">2015-02-09</td>
<td align="left">TZ</td>
<td align="left">Fix a bug causes reset limit counters. Fix region behaviour.</td>
</tr>
<tr class="odd">
<td align="left">1.4.7</td>
<td align="left">2015-02-12</td>
<td align="left">TZ</td>
<td align="left">Fix group and regions behaviour. Update for log events.</td>
</tr>
<tr class="even">
<td align="left">1.4.8</td>
<td align="left">2015-03-17</td>
<td align="left">TZ</td>
<td align="left"><ul>
<li>Improve stability of the SDK.</li>
<li>Add mediumFar and highFar proximities</li>
</ul></td>
</tr>
<tr class="odd">
<td align="left">1.4.9</td>
<td align="left">2015-04-02</td>
<td align="left">TZ</td>
<td align="left"><ul>
<li>Improve rsi measurements.</li>
<li>Fix bug with starting sdk.</li>
</ul></td>
</tr>
<tr class="even">
<td align="left">1.4.10</td>
<td align="left">2015-04-09</td>
<td align="left">TZ</td>
<td align="left">Fix getting native device from UbuduBeacon.</td>
</tr>
<tr class="odd">
<td align="left">1.5.0</td>
<td align="left">2015-04-17</td>
<td align="left">TZ</td>
<td align="left">Stability improvements and bug fixes Reduced verbosity of logcat/logs. Lower frequency to send async logged event to server. Deep linking actions. Optimisation of user tags management tags management.</td>
</tr>
<tr class="odd">
<td align="left">1.6.0</td>
<td align="left">2015-05-12</td>
<td align="left">TZ</td>
<td align="left"><p>Fix bug while working with secured beacons.</p> <p>Add API for between log setting period events.</p><p>Improve rssi averaging.</p> <p>Fix for relative proximity.</p> <p>Add API for reseting global event counter.</p> <p>Reduced battery consumption.</p></td>
</tr>
<tr class="odd">
<td align="left">1.7.0</td>
<td align="left">2015-05-19</td>
<td align="left">TZ</td>
<td align="left"><p>Multiline notifications.</p><p>Mesh (beta).</p><p>Indoor Location (beta).</p> <p>Bug fixes.</p></td>
</tr>
<tr class="odd">
<td align="left">1.7.1</td>
<td align="left">2015-05-29</td>
<td align="left">TZ</td>
<td align="left"><p>No toasts from ubudu sdk.</p><p>Custom alertTitle for notification.</p><p>Minor fixes.</p></td>
</tr>
<tr class="odd">
<td align="left">1.7.2</td>
<td align="left">2015-05-29</td>
<td align="left">TZ</td>
<td align="left"><p>Bug fixes.</p></td>
</tr>
<tr class="odd">
<td align="left">1.7.3</td>
<td align="left">2015-06-09</td>
<td align="left">TZ</td>
<td align="left"><p>Fix for uuid bug.</p><p>Restart Ubudu service after app killing.</p><p>Fix UI freeze while starting the beacon manager.</p><p>Other bug fixes.</p></td>
</tr>
<tr class="odd">
<td align="left">1.8.0</td>
<td align="left">2015-06-30</td>
<td align="left">MG</td>
<td align="left"><p>Performance improvements for namespaces with high number of interaction rules.</p><p>Other bug fixes.</p></td>
</tr>
<tr class="odd">
<td align="left">1.8.1</td>
<td align="left">2015-07-01</td>
<td align="left">MG</td>
<td align="left"><p>Fixed matching regions to beacon.</p></td>
</tr>
<tr class="odd">
<td align="left">1.8.2</td>
<td align="left">2015-07-08</td>
<td align="left">MG</td>
<td align="left"><p>Fix scanning periods custom settings</p>
<p>Added methods to customize rules fetching period</p>
<p>Fixed custom notification mechanism after app is killed</p></td>
</tr>
<tr class="odd">
<td align="left">1.8.3</td>
<td align="left">2015-07-14</td>
<td align="left">MG</td>
<td align="left"><p>Custom notification fix when the app is killed</p>
<p>Minor API update for indoor location and mesh</p></td>
</tr>
<tr class="odd">
<td align="left">1.8.4</td>
<td align="left">2015-07-23</td>
<td align="left">MG</td>
<td align="left"><p>Fix for rare issue with fetching rules</p>
<p>Fix stopping a beacon manager</p>
<p>Minor Indoor Location improvements</p>
<p>Minor API updates</p></td>
</tr>
<tr class="odd">
<td align="left">1.8.5</td>
<td align="left">2015-08-05</td>
<td align="left">MG</td>
<td align="left"><p>Fix of Indoor Location not always starting</p>
<p>Stability improvements</p></td>
</tr>
<tr class="odd">
<td align="left">1.9.0</td>
<td align="left">2015-09-07</td>
<td align="left">MG</td>
<td align="left">
<p>New beacon service updated for Lollipop</p>
<p>Updated indoor location delegate</p>
<p>Geofences updated for Google Play Services v7.8</p><p>Improved and updated ubudu mesh</p>
<p>Enhance manager logs handling</p>
<p>Improved rules triggering after latch time</p>
<p>Fixed bug of rules not always being fetched when Ubudu SDK was initially started in offline mode</p>
<p>Bug fixes and stability improvements</p>
</td>
</tr>
<tr class="odd">
<td align="left">1.9.1</td>
<td align="left">2015-09-09</td>
<td align="left">MG</td>
<td align="left">
<p>Indoor Location Georeference functionality</p>
<p>Indoor Location delegate methods fixes</p>
<p>Bug fixes and stability improvements</p>
</td>
</tr>
<tr class="odd">
<td align="left">1.9.2</td>
<td align="left">2015-09-10</td>
<td align="left">TZ</td>
<td align="left">
<p>Mesh fix while for sending message without ACK</p>
</td>
</tr>
<tr class="odd">
<td align="left">1.9.3</td>
<td align="left">2015-09-29</td>
<td align="left">MG</td>
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
<td align="left">MG</td>
<td align="left">
<p>Indoor Location:</p>
<p>* polygon distance calculation fix</p>
</td>
</tr>
<tr class="odd">
<td align="left">1.9.5</td>
<td align="left">2015-10-19</td>
<td align="left">MG</td>
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
<td align="left">MG</td>
<td align="left">
<p>Android manifest fix</p>
</td>
</tr>
<tr class="odd">
<td align="left">1.9.7</td>
<td align="left">2015-11-06</td>
<td align="left">MG</td>
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
<td align="left">MG</td>
<td align="left">
<p>Fixed an issue of Contextual Interactions SDK start being delayed in case when Indoor Location SDK has been started short moment before</p>
</td>
</tr>
<tr class="odd">
<td align="left">1.9.9</td>
<td align="left">2015-11-10</td>
<td align="left">MG</td>
<td align="left">
<p>Indoor Location:</p>
<p>* Fixed a bug of map fetching retry mechanism</p>
</td>
</tr>
<tr class="odd">
<td align="left">1.9.10</td>
<td align="left">2015-11-12</td>
<td align="left">MG</td>
<td align="left">
<p>Fixed an issue of server notification title not being overriden.</p>
</td>
</tr>
<tr class="odd">
<td align="left">1.10.0</td>
<td align="left">2015-12-01</td>
<td align="left">MG</td>
<td align="left">
<p>Issues fixed:</p>
<p>* doubled contextual rules fetching</p>
<p>* server notification title not being set</p>
<p>* receiving double mesh messages</p>
<p>Improvements:</p>
<p>* removed `com.google.android.gms.permission.ACTIVITY_RECOGNITION` from the manifest. If developer wants to take advantage of the additional motion sensor filtering within the Indoor Location SDK (improves position stability) this permission must be manually added to the app's manifest.</p>
<p>Features added:</p>
<p>* new API method for decrementing and reseting rule counter when the developer does not want to perform action to the user during custom event handling</p>
<p>* logic alignments with the new features related to contextual rules introduced in the manager platform</p>
<p>* Changed `UbuduAreaDelegate` method `notifyUserForEvent(UbuduEvent event)` from void type to boolean.</p>
<p>* Added new `UbuduAreaDelegate` method `void notifyUserForEvent(UbuduEvent event, UbuduAreaDelegateEventHandlingResponseListener responseListener)` for custom event handling in which the decision about performing action to the user is to be delayed.</p>
</td>
</tr>
<tr class="odd">
<td align="left">2.0.0</td>
<td align="left">2016-01-06</td>
<td align="left">MG</td>
<td align="left">
<p>Improvements:</p>
<p>* greatly improved SDK init time</p>
<p>* bug fixes and stability improvements</p>
<p>Features added:</p>
<p>* removed Indoor Location API and Mesh API. These packages are now available at separate repositories</p>
</td>
</tr>
<tr class="odd">
<td align="left">2.0.1</td>
<td align="left">2016-01-15</td>
<td align="left">MG</td>
<td align="left">
<p>Features added:</p>
<p>* changed public API pattern so the methods parameters names are understandable,</p>
<p>* added API for adding custom http request headers for server notification</p>
<p>Issues addressed:</p>
<p>* UbuduService is now properly destroyed and removed from the application processes when stop is called from beacon/geofence managers</p>
</td>
</tr>
<tr class="odd">
<td align="left">2.0.2</td>
<td align="left">2016-02-10</td>
<td align="left">MG</td>
<td align="left">
<p>Issues addressed:</p>
<p>* fixed db issues when migrating from version 1.9.+ to version >=2.0.0</p>
<p>* fixed ANR when calling resetAllEventsCounters() method</p>
<p>* fixed rare crash when using a dump json log file feature</p>
<p>* fixed rare issue when beacon monitoring was not started when another scanning Ubudu library was working in paralel</p>
<p>* fixed issue when log "uBeacon monitoring started." was shown after asking manager to stop</p>
</td>
</tr>
<tr class="odd">
<td align="left">2.0.3</td>
<td align="left">2016-02-12</td>
<td align="left">MG</td>
<td align="left">
<p>Improvements:</p>
<p>* stripped down google-play-services dependency</p>
<p>Issues addressed:</p>
<p>* fixed some migration issues</p>
</td>
</tr>
<tr class="odd">
<td align="left">2.0.6</td>
<td align="left">2016-02-17</td>
<td align="left">MG</td>
<td align="left">
<p>Improvements:</p>
<p>* Got rid of unnecessary permissions</p>
<p>Issues addressed:</p>
<p>* minor bug fixes related to switching between applications (namespaces)</p>
<p>* minor fixes related to BLE scanning</p>
</td>
</tr>
<tr class="odd">
<td align="left">2.0.7</td>
<td align="left">2016-02-25</td>
<td align="left">MG</td>
<td align="left">
<p>Features added:</p>
<p>* added big icon style automatic notifications handling ( http://developer.android.com/reference/android/app/Notification.BigPictureStyle.html ). Big icon url must be defined in the rule in the Ubudu Manager Platform. Example: <img src="bigIconNotificationExample.png" height="400" /></p>
<p>Issues addressed:</p>
<p>* fix few NPE crashes</p>
<p>* fix lack of permissions related crashes on Android M</p>
</td>
</tr>
<tr class="odd">
<td align="left">2.0.10</td>
<td align="left">2016-03-05</td>
<td align="left">MG</td>
<td align="left">
<p>Features added:</p>
<p>* added timestamp to UbuduBeacon object</p>
<p>Improvements:</p>
<p>* stabilized ranged beacon notifier output. Beacons do not dissapear immediately if they are not detected in a single scan</p>
<p>Issues addressed:</p>
<p>* fixed issue with switching between namespaces when sometimes the SDK stopped responding and could not be restarted</p>
</td>
</tr>
<tr class="odd">
<td align="left">2.1.2</td>
<td align="left">2016-03-17</td>
<td align="left">MG</td>
<td align="left">
<p>Features added:</p>
<p>* model specific distance estimation that improves the accuracy of device-beacon distance estimation</p>
<p>Improvements:</p>
<p>* improved custom event handling by adding additional UbuduCustomEventHandlingAreaDelegate interface that is an extension of the default UbuduAreaDelegate</p>
<p>Issues addressed:</p>
<p>* fixed triggering rules at medium-far and high-far proximities with custom distance treshold</p>
</td>
</tr>
<tr class="odd">
<td align="left">2.2.0</td>
<td align="left">2016-03-22</td>
<td align="left">MG</td>
<td align="left">
<p>API changes (details can be found in the updated <a href="ubudu-sdk-user-manual.md">manual</a>) :</p>
<p>* Changed the API for custom handling the events.</p>
<p>* removed UbuduGeofenceDelegate interface. UbuduAreaDelegate is the interface to be used both for geofences and beacons.</p>
<p>* removed context argument from area managers start/stop methods</p>
<p>Improvements:</p>
<p>* updated documentation and JavaDoc</p>
<p>Issues addressed:</p>
<p>* fixed geofences not triggering events</p>
<p>* open_notif type logs missing in the application statistics when custom handling the events</p>
</td>
</tr>
<tr class="odd">
<td align="left">2.2.1</td>
<td align="left">2016-03-22</td>
<td align="left">MG</td>
<td align="left">
<p>Improvements:</p>
<p>* UbuduEvent and few other API interfaces are now parcelable to improve the event custom handling in an Android app (from one activity to another etc)</p>
</td>
</tr>
<tr class="odd">
<td align="left">2.2.2</td>
<td align="left">2016-04-13</td>
<td align="left">MG</td>
<td align="left">
<p>Issues addressed:</p>
<p>* Fixed beacon distance calculation when distance was sometimes < 0m</p>
</td>
</tr>
<tr class="odd">
<td align="left">2.2.3</td>
<td align="left">2016-04-17</td>
<td align="left">MG</td>
<td align="left">
<p>Features added:</p>
<p>* added new methods to beacon/geofence manager which allow to execute actions of a particular event (like opening a web url in the default WebView) or just notify the SDK about event being custom handled on the app's side so proper statistic logs are posted to the back office. Please check the manual for details.</p>
<p>Issues addressed:</p>
<p>* Fixed SDK crashing on init because of an java.lang.NoClassDefFoundError</p>
</td>
</tr>
<tr class="odd">
<td align="left">2.2.4</td>
<td align="left">2016-04-22</td>
<td align="left">MG</td>
<td align="left">
<p>Issues addressed:</p>
<p>* Fixed a very serious bug that caused a significant growth of the app's total usage of internal memory over time</p>
</td>
</tr>
<tr class="odd">
<td align="left">2.2.5</td>
<td align="left">2016-04-29</td>
<td align="left">MG</td>
<td align="left">
<p>Features added:</p>
<p>* added Advertising ID support</p>
<p>* added support for new analytics logs related to device bluetooth state and location/notifications permissions</p>
<p>Improvements:</p>
<p>* removed Context argument from SDK's release method</p>
<p>* changed the deprecated `android.text.format.Time` class to java.util.Date object in the UbuduSDK `nextFetchTime()` and `setNextFetchTime` methods</p>
<p>* minor fixes and improvements</p>
<p>Issues addressed:</p>
<p>* fixed an issue with Ormlite's OpenHelperManager that could not be used in the app using Ubudu SDK</p>
<p>* fixed crashes occuring when app using Ubudu SDK is installed on pre API 18 devices</p>
</td>
</tr>
<tr class="odd">
<td align="left">2.2.6</td>
<td align="left">2016-05-24</td>
<td align="left">MG</td>
<td align="left">
<p>Issues addressed:</p>
<p>* fixed issues causing problems when building the final app with proguard</p>
</td>
</tr>

<tr class="odd">
<td align="left">2.2.7</td>
<td align="left">2016-08-09</td>
<td align="left">MG</td>
<td align="left">
<p>Issues addressed:</p>
<p>* fixed ANRs happening on Android M when scanning is triggered while app does not have geolocation permissions</p>
</td>
</tr>

<tr class="odd">
<td align="left">2.2.8</td>
<td align="left">2016-09-12</td>
<td align="left">MG</td>
<td align="left">
<p>Improvements:</p>
<p>* added callback to com.ubudu.sdk.UbuduSDK#setUserInformation method so the app can be notified about the result</p>
<p>* full UbuduArea object is passed into areaEntered delegate method</p>
<p>* WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE is now added to the SDK's default WebView so the pages with mixed content are properly displayed</p>
<p>Issues addressed:</p>
<p>* fixed crash occuring when com.ubudu.sdk.UbuduBeaconRegion toString() method was called</p>
<p>* fixed user tags not being properly passed to web api</p>
<p>* fixed rare crash happening on some Samsung devices related to lack of android.permission.BLUETOOTH permission</p>
<p>* delegate's methods areaEnterd and areaExited are now called on a main thread (now there is no need to create separate Handler on the app's side)</p>
</td>
</tr>

<tr class="odd">
<td align="left">2.3.0</td>
<td align="left">2016-11-15</td>
<td align="left">MG</td>
<td align="left">
<p>Improvements:</p>
<p>* updated REST API library </p>
<p>* replace palceholders also in alertTitle and custom payload</p>
<p>* updated Google Play Location Services to version 9.8.0</p>
<p>API changes:</p>
<p>* UbuduRegisteringUserCallback renamed to UbuduCompletionCallback</p>
</td>
</tr>

<tr class="odd">
<td align="left">2.4.0</td>
<td align="left">2016-11-24</td>
<td align="left">MG</td>
<td align="left">
<p>Features added:</p>
<ul><li>rich notifications handling</li>
<li>notifications pop out effect to rich notifications and background notifications</li></ul>
<p>Issues addressed:</p>
<ul><li>fixed issue of logs not always being posted to server properly</li>
<li>fixed geofence rules not being triggered due to position updates being delayed to much</li>
<li>fixed pending status bar notifications not being cancelled when app is killed</li>
</ul>
<p>API changes:</p>
<ul><li>new delegate method: `void notificationActionTriggeredForEvent(UbuduEvent event, String actionIdentifier)` for handling rich notification action buttons click events</li>
<li>renamed beacon/geofence manager methods:<br />
- `executeActionsForEvent ` to `executeDefaultActionsForEvent`<br />
- `actionsCustomExecutedForEvent` to `defaultActionsExecutedForEvent`</li>
</ul>
</td>
</tr>

<tr class="odd">
<td align="left">2.4.1</td>
<td align="left">2016-11-25</td>
<td align="left">MG</td>
<td align="left">
<p>Issues addressed:</p>
<ul><li>fixed permissions and bluetooth state analytic logs syntax</li>
</ul>
</td>
</tr>

<tr class="odd">
<td align="left">2.4.2</td>
<td align="left">2016-12-15</td>
<td align="left">MG</td>
<td align="left">
<p>Features added:</p>
<ul><li>Way to handle rich notification actions after app has been killed. New intent called `com.ubudu.sdk.service.UbuduService.action.ACTION_SERVICE_RESTARTED_WITH_EVENT` is now broadcasted when rich notification custom action is clicked while the app is killed. Mobile app should register a broadcast receiver for this intent to process the custom action with developer's own logic. See <a href="https://github.com/Ubudu/Android-SDK/wiki/Rich-notifications">wiki article</a> for more details.</li></ul>
<p>Improvements:</p>
<ul><li>Advertising ID is now not posted for analytics until the app asks for it by calling `enableAdvertisingIdCollection(true)` method on `com.ubudu.UbuduSDK`instance</li></ul>
<p>Issues addressed:</p>
<ul>
<li>rich notificatios actions not being passed to the delegate properly</li>
<li>rare crash occuring when SDK release() method is called</li>
<li>multiple `returning user` log posted at the SDK start</li>
</ul>
</td>
</tr>

<tr class="odd">
<td align="left">2.5.0</td>
<td align="left">2017-01-19</td>
<td align="left">MG</td>
<td align="left">
<p>Features added:</p>
<ul><li>None</li></ul>
<p>Improvements:</p>
<ul><li>Analytics logs sending improved</li></ul>
<ul><li>When notification has no title and body the application name is used as a title of the background notification</li></ul>
<p>Issues addressed:</p>
<ul>
<li>Group rules limits not working properly</li>
<li>Crash related to BroadcastReceiver registration</li>
<li>Replacing placeholders in the rule custom payload</li>
<li>Issues related to controlling geofence and beacon managers auto restart</li>
</ul>
</td>
</tr>

<tr class="odd">
<td align="left">2.5.1</td>
<td align="left">2017-01-23</td>
<td align="left">MG</td>
<td align="left">
<p>Issues addressed:</p>
<ul>
<li>Rules limits not working properly</li>
<li>Crash related to BroadcastReceiver registration</li>
</ul>
</td>
</tr>

<tr class="odd">
<td align="left">2.5.3</td>
<td align="left">2017-01-26</td>
<td align="left">MG</td>
<td align="left">
<p>Features added:</o>
<ul>
<li>beacon battery level API</li>
</ul>
<p>Issues addressed:</p>
<ul>
<li>service auto restart not working</li>
<li>placeholders not filled for geofence rules</li>
<li>crashes</li>
</ul>
</td>
</tr>

<tr class="odd">
<td align="left">2.5.4</td>
<td align="left">2017-02-01</td>
<td align="left">MG</td>
<td align="left">
<p>Issues addressed:</p>
<ul>
<li>global limits being respected even if a rule is not to be counted for global limits</li>
<li>event counters limits not being updated properly in the local database after new data is fetched from cloud</li>
</ul>
</td>
</tr>

</tbody>
</table>

Authors:

-   FK: François Kruta \<<francois.kruta@ubudu.com>\>
-   PJB: Pascal Bourguignon \<<pascal.bourguignon@ubudu.com>\>
-   TZ: Tomasz Ziolkowski \<<tomasz.ziolkowski@ubudu.com>\>
-   MG: Michal Gasztold \<<michal.gasztold@ubudu.biz>\>
