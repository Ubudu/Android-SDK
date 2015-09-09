// -*- mode:java;coding:utf-8 -*-
//****************************************************************************
//FILE:               GeofenceFragment.java
//LANGUAGE:           java
//SYSTEM:             Android
//USER-INTERFACE:     Android
//DESCRIPTION
//
//    Displays a google map and handles displaying the given indoor location position.
//
//AUTHORS
//    <MG> Michal Gasztold <michal.gasztold@ubudu.biz>
//MODIFICATIONS
//    2014-09-08 <MG> Added this header.
//BUGS
//LEGAL
//    ubudu-public
//
//    Copyright (c) 2011-2015, UBUDU SAS
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
package com.ubudu.ubuduindoorlocationdemo.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.GeomagneticField;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Map implements SensorEventListener,GoogleMap.OnMapLoadedCallback {
    protected GoogleMap mMap;

    protected Activity mActivity;

    GroundOverlayOptions mapOptions;
    private Canvas canvas;
    private Bitmap.Config conf;
    private Paint color;
    private Bitmap bmp;

    private int zoom = 18;
    private Marker mCurrentLocationMarker;
    private float mDeclination;

    LatLng middle = new LatLng(52.2205744679, 21.0099331142);
    LatLngBounds mapBounds;

    public Map(Activity activity, int fragmentId) {
        mActivity = activity;
        mMap = ((MapFragment) (mActivity.getFragmentManager().findFragmentById(fragmentId))).getMap();
        mMap.setOnMapLoadedCallback(this);
    }

    public void setMapImageBounds(LatLng southWest, LatLng northEast){
        mapBounds = new LatLngBounds(
                southWest,       // South west image corner
                northEast);      // North east image corner
        middle = new LatLng(southWest.latitude+(northEast.latitude-southWest.latitude)/2, southWest.longitude+ (northEast.longitude-southWest.longitude)/2);

        mapOptions = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromAsset("ggrecttransparent.png"))
                .positionFromBounds(mapBounds)
                .transparency(0.5f);
        mMap.addGroundOverlay(mapOptions);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(middle,zoom));
    }

    @Override
    public void onMapLoaded() {
        if (mMap != null) {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            initMarker();
        }
    }

    private void initMarker() {
        //init paint
        color = new Paint();
        conf = Bitmap.Config.ARGB_8888;
        bmp = Bitmap.createBitmap(40, 40, conf);
        canvas = new Canvas(bmp);
        // paint circle
        color.setColor(Color.parseColor("#ffffff"));
        canvas.drawCircle(20, 20, 20, color);
        color.setColor(Color.parseColor("#4285F4"));
        canvas.drawCircle(20, 20, 16, color);
    }

    LatLng lastLocation;
    public void setLocationOnMap(double latitude, double longitude) {
        if (mMap != null) {
            lastLocation = new LatLng(latitude, longitude);
            synchronized (this) {
                if (mCurrentLocationMarker == null) {
                    mCurrentLocationMarker = mMap.addMarker(new MarkerOptions()
                            .position(lastLocation).title("Current Position")
                            .icon(BitmapDescriptorFactory.fromBitmap(bmp)));
                } else
                    mCurrentLocationMarker.setPosition(lastLocation);
            }
            updateCamera();//mMap.animateCamera(CameraUpdateFactory.newLatLng(lastLocation));
        }
        GeomagneticField field = new GeomagneticField(
                (float) latitude,
                (float) longitude,
                (float) 0,
                System.currentTimeMillis()
        );
        // getDeclination returns degrees
        mDeclination = field.getDeclination();
    }

    private List<Polyline> polylines = new ArrayList<Polyline>();
    public void highlightZone(LatLng southWest, LatLng northEast, String name) {
        // Instantiates a new Polyline object and adds points to define a rectangle
        PolylineOptions rectOptions = new PolylineOptions()
                .color(Color.MAGENTA)
                .add(new LatLng(southWest.latitude, southWest.longitude))
                .add(new LatLng(southWest.latitude, northEast.longitude))
                .add(new LatLng(northEast.latitude, northEast.longitude))
                .add(new LatLng(northEast.latitude, southWest.longitude))
                .add(new LatLng(southWest.latitude, southWest.longitude)); // Closes the polyline.

// Get back the mutable Polyline
        polylines.add(mMap.addPolyline(rectOptions));
    }

    private float[] mRotationMatrix = new float[16];

    float bearing = 0;
    float[] orientation;
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (mCurrentLocationMarker != null) {
            if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
                SensorManager.getRotationMatrixFromVector(
                        mRotationMatrix, event.values);
                orientation = new float[3];
                SensorManager.getOrientation(mRotationMatrix, orientation);
                bearing = (float) Math.toDegrees(orientation[0]) + mDeclination;
            }
        }
    }

    private void updateCamera() {
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder()
                .target(lastLocation)
                .bearing(bearing).zoom(zoom).build()));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void clearHighlightedZones() {
        Iterator<Polyline> iter = polylines.iterator();
        while(iter.hasNext()){
            Polyline p = iter.next();
            p.remove();
            polylines.remove(p);
        }
    }
}
