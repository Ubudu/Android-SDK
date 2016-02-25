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
<p>New beacon service updated for Lollipop</p><p>Updated indoor location delegate</p><p>Geofences updated for Google Play Services v7.8</p><p>Improved and updated ubudu mesh</p><p>Enhance manager logs handling</p><p>Improved rules triggering after latch time</p><p>Fixed bug of rules not always being fetched when Ubudu SDK was initially started in offline mode</p><p>Bug fixes and stability improvements</p>
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
<p>* added big icon style automatic notifications handling ( http://developer.android.com/reference/android/app/Notification.BigPictureStyle.html ). Big icon url must be defined in the rule in the Ubudu Manager Platform</p>
<p>Issues addressed:</p>
<p>* fix few NPE crashes</p>
<p>* fix lack of permissions related crashes on Android M</p>
</td>
</tr>
</tbody>
</table>

Authors:

-   FK: François Kruta \<<francois.kruta@ubudu.com>\>
-   PJB: Pascal Bourguignon \<<pascal.bourguignon@ubudu.com>\>
-   TZ: Tomasz Ziolkowski \<<tomasz.ziolkowski@ubudu.com>\>
-   MG: Michal Gasztold \<<michal.gasztold@ubudu.biz>\>
