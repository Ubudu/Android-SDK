// -*- mode:java;coding:utf-8 -*-
//****************************************************************************
//FILE:               MainActivity.java
//LANGUAGE:           java
//SYSTEM:             Android
//USER-INTERFACE:     Android
//DESCRIPTION
//    
//    This is the main activity of the Ubudu SDK Dev App.
//    
//AUTHORS
//    <PJB> Pascal J. Bourguignon <pjb@informatimago.com>
//MODIFICATIONS
//    2014-08-27 <PJB> Added this header.
//BUGS
//LEGAL
//    ubudu-public
//    
//    Copyright (c) 2011-2014, UBUDU SAS
//    All rights reserved.
//    
//    Redistribution and use in source and binary forms, with or without
//    modification, are permitted provided that the following conditions are met:
//    
//    * Redistributions of source code must retain the above copyright notice, this
//      list of conditions and the following disclaimer.
//    
//    * Redistributions in binary form must reproduce the above copyright notice,
//      this list of conditions and the following disclaimer in the documentation
//      and/or other materials provided with the distribution.
//    
//    THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
//    AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
//    IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
//    DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
//    FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
//    DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
//    SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
//    CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
//    OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
//    OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
//****************************************************************************
package com.ubudu.sdk.devapp;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Layout;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.ubudu.sdk.devapp.DemoAreaDelegate;
import com.ubudu.sdk.devapp.ErrorReceiver;
import com.ubudu.sdk.devapp.InfoAreaReceiver;
import com.ubudu.sdk.devapp.Map;
import com.ubudu.sdk.UbuduBeaconRegion;
import com.ubudu.sdk.UbuduGeofence;
import com.ubudu.sdk.UbuduAreaDelegate;
import com.ubudu.sdk.UbuduBeaconManager;
import com.ubudu.sdk.UbuduGeofenceManager;
import com.ubudu.sdk.UbuduSDK;
import com.ubudu.sdk.UbuduUser;
import java.lang.Error;
import java.lang.Object;
import java.lang.String;
import java.util.List;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ListView;
import com.ubudu.sdk.devapp.Pref;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.ubudu.sdk.devapp.UApplication;
import java.util.Collection;
import java.util.ArrayList;
import com.ubudu.sdk.devapp.ApplicationListAdapter;
import android.widget.AdapterView;
import java.util.HashSet;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.lang.StringBuilder;

interface TextOutput {
  void printf(String formatControl,Object... arguments);
}

public class MainActivity extends Activity implements TextOutput {

  // TextOutput:

  protected TextView mOutputTextView;

  public void printf(String formatControl,Object... arguments){
    final String newText=String.format(formatControl,arguments);
    synchronized(mOutputTextView){
      mOutputTextView.append(newText);
      final Layout layout=mOutputTextView.getLayout();
      if(layout!=null){
        final int scrollAmount=layout.getLineTop(mOutputTextView.getLineCount())-mOutputTextView.getHeight();
        mOutputTextView.scrollTo(0,((0<scrollAmount)?scrollAmount:0));
      }
    }
  }

  public void report(String message){
    this.printf(message+"\n");
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
  }

  // Activity:

  protected Switch geousSwitch;
  protected Switch bluetoothSwitch;
  protected ViewSwitcher mainViewSwitcher;
  protected Map mMap;
  protected UbuduGeofenceManager mGeofenceManager;
  protected UbuduBeaconManager   mBeaconManager;
  protected UbuduAreaDelegate    mGeofenceDelegate;
  protected UbuduAreaDelegate    mBeaconDelegate;
  protected boolean mStayInBackground=false;


  public boolean activateBluetoothIfPossible(){
    BluetoothAdapter bt=BluetoothAdapter.getDefaultAdapter();
    //Check Availability of bluetooth
    if(bt==null){
      report("Bluetooth Not Available on this device.");
      return false;
    }else{
      if(!bt.isEnabled()){
        bt.enable(); 
        // //use intent to first open dialog box as confiramtion
        // Intent i=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        // startActivity(i);
      }
      return true;
    }
  }

  private class GooglePlayCancelListener implements DialogInterface.OnCancelListener {
    protected MainActivity activity;
    public GooglePlayCancelListener(MainActivity activity){
      this.activity=activity;
    }
    public void onCancel(DialogInterface dialog){
      activity.finish();
    }
  };

  public void checkGooglePlayServices(){
    int result=GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
    if(com.google.android.gms.common.ConnectionResult.SUCCESS!=result){
      GooglePlayServicesUtil.getErrorDialog(result,this,0,new GooglePlayCancelListener(this));
    }
  }

  protected void moveViewTo(View view,ViewGroup dst){
    ViewGroup src=(ViewGroup)view.getParent();
    if(src!=dst){
      src.removeView(view);
      dst.addView(view);
    }
  }

  protected void showMap(){
    setContentView(mainView);
    moveViewTo(mOutputTextView,(ViewGroup)(mainView.findViewById(R.id.output_container)));
    mMap=new Map(this,R.id.map);
    mainViewSwitcher=(ViewSwitcher)this.findViewById(R.id.mainViewSwitcher);
    geousSwitch=(Switch)this.findViewById(R.id.toggleGPS);
  }

  protected void showLog(){
    setContentView(logView);
    moveViewTo(mOutputTextView,(ViewGroup)(logView.findViewById(R.id.output_container)));
    mMap=null;
    mainViewSwitcher=null;
    geousSwitch=null;
  }

  protected void showAuthentication(){
    setContentView(authenticationView);
    mMap=null;
    mainViewSwitcher=null;
    geousSwitch=null;
    displayOoauthAccessToken();
  }

  protected void showUser(){
    setContentView(userView);
    mMap=null;
    mainViewSwitcher=null;
    geousSwitch=null;
    displayUser();
  }

  Pref preferences;
  View mainView;
  View authenticationView;
  View userView;
  View logView;
  ApplicationListAdapter applicationListAdapter;
  ListView applicationListView;

  public void setNamespace(String aNamespace){
    namespace=aNamespace;
    preferences.setPreference("namespace",aNamespace);
    UbuduSDK sdk=UbuduSDK.getSharedInstance(getApplicationContext());
    sdk.setNamespace(namespace);
    applicationListAdapter.notifyDataSetChanged();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);

    LayoutInflater inflater=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    mainView=inflater.inflate(R.layout.activity_main,null);

    authenticationView=inflater.inflate(R.layout.authentication,null);
    applicationListAdapter=new ApplicationListAdapter(new ArrayList<UApplication>(),this);
    applicationListView = (ListView)authenticationView.findViewById(R.id.application_list);
    applicationListView.setAdapter(applicationListAdapter);
    applicationListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent,View view,int position,long id){
          UApplication application=applicationListAdapter.getItem(position);
          setNamespace(application.namespace_uid);
        }
      });


    userView=inflater.inflate(R.layout.user,null);

    logView=inflater.inflate(R.layout.log_view,null);
    mOutputTextView=(TextView)mainView.findViewById(R.id.outputText);
    mOutputTextView.setMovementMethod(new ScrollingMovementMethod());

    UbuduSDK sdk=UbuduSDK.getSharedInstance(getApplicationContext());
    this.printf("Ubudu SDK version : %s\n",sdk.getVersion());

    preferences=new Pref(this);
    ooauthAccessToken=preferences.getPreferenceString("ooauthAccessToken","");
    namespace=preferences.getPreferenceString("namespace","38442bb3bc0674dc4a25633bf9a638f2e9e8a0af");

    showMap();
    bluetoothSwitch=(Switch)this.findViewById(R.id.toggleBluetooth);
    mGeofenceDelegate=new DemoAreaDelegate(this,mMap);
    mBeaconDelegate = new DemoAreaDelegate(this,mMap);

    sdk.setNamespace(namespace);
    mGeofenceManager=sdk.getGeofenceManager();
    mGeofenceManager.setAreaDelegate(mGeofenceDelegate);

    mBeaconManager=sdk.getBeaconManager();
    mBeaconManager.setAreaDelegate(mBeaconDelegate); // we can use the same delegate for both.
    onResetButton(null);

    int newMaximum=1000;
    this.printf("maximum daily number of notifications allowed was %d\nSetting it to %d\n",
                sdk.maximumDailyNumberOfNotificationsAllowed(),newMaximum);
    sdk.setMaximumDailyNumberOfNotificationsAllowed(newMaximum);
    
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }


  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle item selection
    switch (item.getItemId()) {
     case R.id.action_authenticate:
       showAuthentication();
       return true;
     case R.id.action_user:
       showUser();
       return true;
     case R.id.action_geofence:
       selectGPSAdvertizing();
       return true;
     case R.id.action_beacon:
       selectBluetoothAdvertizing();
       return true;
     case R.id.show_map:
       showMap();
       return true;
     case R.id.show_log:
       showLog();
       return true;
     case R.id.list_geofences: 
       listGeofences();
       return true;
     case R.id.list_beaconregions:
       listBeaconRegions();
       return true;
     default:
       return super.onOptionsItemSelected(item);
    }
  }


  @Override
  protected void onResume(){
    super.onResume();
    checkGooglePlayServices();
    registerErrorReceiver();
    registerInfoReceiver();
    if(mGeofenceManager != null && mGeofenceDelegate != null ) {
      mGeofenceManager.setAreaDelegate(mGeofenceDelegate);
    }
    if (mBeaconManager != null && mBeaconDelegate != null ) {
      mBeaconManager.setAreaDelegate(mBeaconDelegate);
    }
  }

  @Override
  protected void onPause(){
    unregisterErrorReceiver();
    unregisterInfoReceiver();
    // clear delegate
    if(mGeofenceManager!=null){
      mGeofenceManager.setAreaDelegate(null);
    }
    
    super.onPause();
  }



  @Override
  protected void onDestroy(){
    UbuduSDK.getSharedInstance(this).release(getApplicationContext());
    super.onDestroy();
  }




  // Error Receiver:

  protected ErrorReceiver mErrorReceiver;

  protected void registerErrorReceiver(){
    synchronized(this){
      if(mErrorReceiver==null){
        mErrorReceiver=new ErrorReceiver(this);
        registerReceiver(mErrorReceiver,new IntentFilter("com.ubudu.sdk.error")); 
        this.printf("Registered error receiver.\n");
      }
    }
  }

  protected void unregisterErrorReceiver(){
    synchronized(this){
      if(mErrorReceiver!=null){
        unregisterReceiver(mErrorReceiver); 
        mErrorReceiver=null;
        this.printf("Unregistered error receiver.\n");
      }
    }
  }

  protected InfoAreaReceiver mInfoReceiver;

  protected void registerInfoReceiver(){
    synchronized(this){
      if(mInfoReceiver==null){
        mInfoReceiver=new InfoAreaReceiver(this);
        registerReceiver(mInfoReceiver,new IntentFilter("com.ubudu.sdk.notify")); 
        this.printf("Registered info receiver.\n");
      }
    }
  }

  protected void unregisterInfoReceiver(){
    synchronized(this){
      if(mInfoReceiver!=null){
        unregisterReceiver(mInfoReceiver); 
        mInfoReceiver=null;
        this.printf("Unregistered info receiver.\n");
      }
    }
  }
  
  
  // UI actions:

  public void onGPSAdvertizingSelected(View view){
    mainViewSwitcher.setDisplayedChild(1);
    mMap.updateLocationOnMap();
    selectGPSAdvertizing();
    geousSwitch.setChecked(true); // we may be called from elsewhere.
    bluetoothSwitch.setChecked(false);
  }

  public void onBluetoothAdvertizingSelected(View view){
    if(activateBluetoothIfPossible()){
      mainViewSwitcher.setDisplayedChild(1);
      selectBluetoothAdvertizing();
      geousSwitch.setChecked(false);
      bluetoothSwitch.setChecked(true); // we may be called from elsewhere.
    }
  }

  public void onGPSToggled(View view){
    if(((Switch)view).isChecked()){
      onGPSAdvertizingSelected(view);
    } else {
      mainViewSwitcher.setDisplayedChild(0);
      resetActivity();
    }
  }

  public void onBluetoothToogled(View view){
    if(((Switch)view).isChecked()){
      onBluetoothAdvertizingSelected(view);
    } else {
      mainViewSwitcher.setDisplayedChild(0);
      resetActivity();
    }
  }

  public void onResetButton(View view){
    geousSwitch.setChecked(false);
    bluetoothSwitch.setChecked(false);
    mainViewSwitcher.setDisplayedChild(0);
    resetActivity();
  }


  // gps/geofences

  public void selectGPSAdvertizing(){
    this.printf("Selecting GPS Advertizing\n");
    mGeofenceManager.setAreaDelegate(mGeofenceDelegate);
    //disable it if you want to handle notifications in delegate 
    mGeofenceManager.setEnableAutomaticUserNotificationSending(true);
    Error error=mGeofenceManager.start(this);
    if(error!=null){
      this.printf("Error: %s\n",error.toString());
    }
  }


  // bluetooth/beacons:

  public void selectBluetoothAdvertizing(){
    this.printf("Selecting Bluetooth Advertizing\n");
    mBeaconManager.setAreaDelegate(mBeaconDelegate);
    Error error=mBeaconManager.start(this);
    if(error!=null){
      this.printf("Error: %s\n",error.toString());
    }
  }


  // reset both geous and bluetooth:
  public void resetActivity(){
    this.printf("Resetting\n");
    mBeaconManager.stop(this);
    mGeofenceManager.stop(this);    
  }


  // OOAUTH Access Token (and Application Namespace)

  private String ooauthAccessToken="";
  private String namespace="";


  public void updateApplicationList(){
    ListView applicationList=(ListView)this.findViewById(R.id.application_list);
    if(applicationList==null){
      report("Error: no application_list ListView.");
      return;
    }
    API api=new API("https://manager.ubudu.com/",ooauthAccessToken);
    api.queryApplications(new API.ApplicationCallback(){
        public void receivedApplications(Collection<UApplication> applications){
          if(applications!=null){
            for(UApplication application:applications){
              MainActivity.this.printf("app %s (%s)\n",application.name,application.namespace_uid);
            }
            applicationListAdapter.setItemList(new ArrayList<UApplication>(applications));
          }
        }
      });
  }

  public void displayOoauthAccessToken(){
    EditText text=(EditText)this.findViewById(R.id.ooauth_access_token);
    if(text==null){
      report("Error: no ooauth_access_token EditText.");
      return;
    }
    text.setText(ooauthAccessToken,TextView.BufferType.EDITABLE);
    updateApplicationList();
  }


  public static boolean isHexadecimal(String text) {
    char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                         'a', 'b', 'c', 'd', 'e', 'f', 'A', 'B', 'C', 'D', 'E', 'F' };
    int hexDigitsCount = 0;
    for (char symbol : text.toCharArray()) {
      for (char hexDigit : hexDigits) {
        if (symbol == hexDigit) {
          hexDigitsCount++;
          break;
        }
      }
    }
    return hexDigitsCount == text.length();
  }

  public boolean validOoauthAccessToken(String token){
    return (token.length()==64)
        &&isHexadecimal(token);
  }

  public void onSaveOoauthAccessToken(View view){
    EditText text=(EditText)this.findViewById(R.id.ooauth_access_token);
    if(text==null){
      report("Error: no ooauth_access_token EditText.");
      return;
    }
    String token=text.getText().toString();
    if(!validOoauthAccessToken(token)){
      report("Invalid OOAuth2 access token.");
      return;      
    }
    ooauthAccessToken=token;
    preferences.setPreference("ooauthAccessToken",ooauthAccessToken);
    updateApplicationList();
  }

  public void onEraseOoauthAccessToken(View view){
    EditText text=(EditText)this.findViewById(R.id.ooauth_access_token);
    if(text==null){
      report("Error: no ooauth_access_token EditText.");
      return;
    }
    text.setText("",TextView.BufferType.EDITABLE);
  }

  public void onCancelOoauthAccessToken(View view){
    displayOoauthAccessToken();    
  }



  // User

  public void displayUser(){
    EditText textId=(EditText)this.findViewById(R.id.text_user_id);
    EditText textProperties=(EditText)this.findViewById(R.id.text_user_properties);
    EditText textTags=(EditText)this.findViewById(R.id.text_user_tags);
    if(textId==null){
      report("Error: no text_user_id EditText.");
      return;
    }
    if(textProperties==null){
      report("Error: no text_user_properties EditText.");
      return;
    }
    if(textTags==null){
      report("Error: no text_user_tags EditText.");
      return;
    }

    UbuduSDK sdk=UbuduSDK.getSharedInstance(getApplicationContext());
    UbuduUser user=sdk.userInformation();
    textId.setText(user.userId(),TextView.BufferType.EDITABLE);

    String newline="\n";
    String equal="=";

    StringBuilder propertiesBuilder=new StringBuilder();
    for(java.util.Map.Entry<String,String> entry:user.properties().entrySet()){
      propertiesBuilder.append(entry.getKey());
      propertiesBuilder.append(equal);
      propertiesBuilder.append(entry.getValue());
      propertiesBuilder.append(newline);
    }
    textProperties.setText(propertiesBuilder.toString(),TextView.BufferType.EDITABLE);

    StringBuilder tagsBuilder=new StringBuilder();
    for(String tag:user.tags()){
      tagsBuilder.append(tag);
      tagsBuilder.append(newline);
    }
    textTags.setText(tagsBuilder.toString(),TextView.BufferType.EDITABLE);
  }    

  public class User implements UbuduUser
  {
    public String userId(){
      return mID;
    }
    public java.util.Map<String,String> properties(){
      return mProperties;
    }
    public java.util.Collection<String> tags(){
      return mTags;
    }
    
    protected String mID;
    protected java.util.Map<String,String> mProperties;
    protected java.util.Collection<String> mTags;
    
    public User(String id,java.util.Map<String,String> properties,java.util.Collection<String> tags){
      mID=id;
      mProperties=properties;
      mTags=tags;
    }

  }

  public void onSaveUser(View view){
    EditText textId=(EditText)this.findViewById(R.id.text_user_id);
    EditText textProperties=(EditText)this.findViewById(R.id.text_user_properties);
    EditText textTags=(EditText)this.findViewById(R.id.text_user_tags);
    if(textId==null){
      report("Error: no text_user_id EditText.");
      return;
    }
    if(textProperties==null){
      report("Error: no text_user_properties EditText.");
      return;
    }
    if(textTags==null){
      report("Error: no text_user_tags EditText.");
      return;
    }

    String userid=textId.getText().toString();

    StringTokenizer propertiesTokenizer=new StringTokenizer(textProperties.getText().toString(),"\n");
    HashMap<String,String> properties=new HashMap<String,String>();
    while(propertiesTokenizer.hasMoreTokens()){
      String propertyToken=propertiesTokenizer.nextToken();
      int equal=propertyToken.indexOf("=");
      if(equal<0){
        properties.put(propertyToken.trim(),"");
      }else{
        properties.put(propertyToken.substring(0,equal).trim(),propertyToken.substring(equal+1).trim());
      }
    }

    StringTokenizer tagsTokenizer=new StringTokenizer(textTags.getText().toString(),"\n, ");
    HashSet<String> tags=new HashSet<String>();
    while(tagsTokenizer.hasMoreTokens()){
      String tagToken=tagsTokenizer.nextToken();
      tags.add(tagToken.trim());
    }

    User user=new User(userid,properties,tags);
    UbuduSDK sdk=UbuduSDK.getSharedInstance(getApplicationContext());
    sdk.setUserInformation(user);
  }

  public void onEraseUser(View view){
    EditText textId=(EditText)this.findViewById(R.id.text_user_id);
    EditText textProperties=(EditText)this.findViewById(R.id.text_user_properties);
    EditText textTags=(EditText)this.findViewById(R.id.text_user_tags);
    if(textId==null){
      report("Error: no text_user_id EditText.");
      return;
    }
    if(textProperties==null){
      report("Error: no text_user_properties EditText.");
      return;
    }
    if(textTags==null){
      report("Error: no text_user_tags EditText.");
      return;
    }

    textId.setText("",TextView.BufferType.EDITABLE);
    textProperties.setText("",TextView.BufferType.EDITABLE);
    textTags.setText("",TextView.BufferType.EDITABLE);
  }

  public void onCancelUser(View view){
    displayUser();    
  }




  // menu commands:

  public void listGeofences(){
    List<UbuduGeofence> areas=mGeofenceManager.geofences();
    if((areas==null)||(areas.size()==0)){
      printf("There are no geofences.\n");
    } else {
      int i=0;
      for(UbuduGeofence area:areas){
        i+=1;
        printf("%2d) %s%s  %s (lat=%f lng=%f rad=%f)\n",
               i,
               (mGeofenceManager.areaIsMonitored(area)?"M":"."),
               (mGeofenceManager.areaIsActive(area)?"A":"."),
               area.name(),
               area.centerLatitude(),area.centerLongitude(),
               area.radius());
      }
    }
  }


  public void listBeaconRegions(){
    List<UbuduBeaconRegion> areas=mBeaconManager.beaconRegions();
    if((areas==null)||(areas.size()==0)){
      printf("There are no beacon regions.\n");
    } else {
      int i=0;
      for(UbuduBeaconRegion area:areas){
        i+=1;
        printf("%2d) %s%s  %s (%s ma=%d mi=%d)\n",
               i,
               (mBeaconManager.areaIsMonitored(area)?"M":"."),
               (mBeaconManager.areaIsActive(area)?"A":"."),
               area.name(),
               area.proximityUUID(),area.major(),area.minor());
      }
    }
  }

}
