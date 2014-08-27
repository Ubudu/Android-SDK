// -*- mode:java;coding:utf-8 -*-
//****************************************************************************
//FILE:               Map.java
//LANGUAGE:           java
//SYSTEM:             Android
//USER-INTERFACE:     Android
//DESCRIPTION
//    
//    Displays a google Map.
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

import java.util.Hashtable;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;

import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;

public class Map 
{
  
  protected GoogleMap mMap;
  protected Activity mActivity;
  protected Hashtable<String,Circle> mCircles;

  public Map(Activity activity,int fragmentId){
    mActivity=activity;
    mMap=((MapFragment)(mActivity.getFragmentManager().findFragmentById(fragmentId))).getMap();
    if(mMap!=null){
      mMap.setMyLocationEnabled(true);
      mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
      mMap.setIndoorEnabled(true);
      updateLocationOnMap();
    }
    mCircles=new Hashtable<String,Circle>();
  }

  public Location getLastKnownLocation(){
    LocationManager locationManager=(LocationManager)(mActivity.getSystemService(Context.LOCATION_SERVICE));
    Criteria criteria=new Criteria();
    criteria.setAltitudeRequired(false);
    criteria.setBearingRequired(false);
    // criteria.setAccuracy(Criteria.ACCURACY_FINE);
    // criteria.setAccuracy(Criteria.ACCURACY_COARSE);
    String provider=locationManager.getBestProvider(criteria,true);
    if(provider==null){
      provider=LocationManager.GPS_PROVIDER;
    }
    return(locationManager.getLastKnownLocation(provider));
  }


  public void updateLocationOnMap(){
    if(mMap!=null){
      Location currentLocation=getLastKnownLocation();
      if(currentLocation != null){
        setLocationOnMap(currentLocation.getLatitude(),currentLocation.getLongitude());
      }
    }
  }


  private Marker mCurrentLocationMarker;

  public void setLocationOnMap(double latitude,double longitude){
    if(mMap!=null){
      LatLng location = new LatLng(latitude,longitude);
      synchronized(this){
        if(mCurrentLocationMarker==null){
          MarkerOptions markerOptions = new MarkerOptions();
          // markerOptions.icon(someNiceIcon);
          markerOptions.position(location);
          mCurrentLocationMarker=mMap.addMarker(markerOptions);
        } else {
          mCurrentLocationMarker.setPosition(location);
        }
      }
      mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
      mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
    }
  }


  public static final int UNCHANGED=0;
  public static final int OUTSIDE=1;
  public static final int INSIDE=2;

  public static int INSIDE_STROKE=Color.RED;
  public static int INSIDE_FILL=Color.MAGENTA;
  public static int OUTSIDE_STROKE=Color.BLUE;
  public static int OUTSIDE_FILL=Color.CYAN;

  public static double METER=1.0;
  public static double KM=1000.0*METER;

  public void removeCircle(String id){
    Circle old=mCircles.remove(id);
    if(old!=null){
      old.remove();
    }
  }

  public void updateCircle(String id,String name,double latitude,double longitude,double radius,int status){
    // TODO: draw the name label inside the circle?
    int stroke=OUTSIDE_STROKE;
    int fill=OUTSIDE_FILL;
    Circle old=mCircles.get(id);
    if(old!=null){
      stroke=old.getStrokeColor();
      fill=old.getFillColor();
      old.remove();
    }
    switch(status){
     case OUTSIDE:
       stroke=OUTSIDE_STROKE;
       fill=OUTSIDE_FILL;
       break;
     case INSIDE:
       stroke=INSIDE_STROKE;
       fill=INSIDE_FILL;
       break;
     default:
       // unchanged.
    }
    mCircles.put(id,mMap.addCircle(new CircleOptions()
                                   .center(new LatLng(latitude,longitude))
                                   .radius(radius*KM)
                                   .strokeWidth(1.0f)
                                   .strokeColor(stroke)
                                   .fillColor(fill)));
  }


}
