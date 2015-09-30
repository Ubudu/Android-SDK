// -*- mode:java;coding:utf-8 -*-
//****************************************************************************
//FILE:               Map.java
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
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.hardware.GeomagneticField;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.ubudu.ubuduindoorlocationdemo.ui.MainActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Map implements SensorEventListener,GoogleMap.OnMapLoadedCallback {

    private static final String OVERLAY_FILE_NAME = "mapOverlayBitmap";

    protected GoogleMap mMap;

    protected Activity mActivity;

    private LatLng lastLocation;
    private float bearing = 0;
    private float[] orientation;

    private int defaultZoom = 16;
    private Marker mCurrentLocationMarker;
    private float mDeclination;

    private List<Polygon> zonesPolygons = new ArrayList<>();
    private List<Marker> zonesMarkers = new ArrayList<>();

    private List<Polyline> pathPolyline = new ArrayList<Polyline>();
    private float[] mRotationMatrix = new float[16];
    private LatLngBounds mapBounds;
    private LatLng middle;

    public Map(Activity activity, int fragmentId) {
        mActivity = activity;
        mMap = ((MapFragment) (mActivity.getFragmentManager().findFragmentById(fragmentId))).getMap();
        mMap.setOnMapLoadedCallback(this);
    }

    public void setMapImageBounds(LatLng southWest, LatLng northEast) {
        mapBounds = new LatLngBounds(
                southWest,       // South west image corner
                northEast);      // North east image corner
        middle = new LatLng(southWest.latitude + (northEast.latitude - southWest.latitude) / 2, southWest.longitude + (northEast.longitude - southWest.longitude) / 2);
    }

    private class LoadMapOverlayFromUrlTask extends AsyncTask<String, Void, Void> {
        Bitmap bitmap;

        @Override
        protected Void doInBackground(String... params) {
            String mapUrl = params[0];
            try {
                URL url = new URL(mapUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(input);
                saveBitmapToFile(bitmap);
                input.close();
                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            processMapOverlay();
        }
    }

    private void saveBitmapToFile(Bitmap bitmap) {
        try {
            File inputFile = new File(mActivity.getApplicationContext().getFilesDir(), OVERLAY_FILE_NAME);
            inputFile.delete();

            FileOutputStream outputStream = mActivity.getApplicationContext().openFileOutput(OVERLAY_FILE_NAME, Context.MODE_APPEND);
            //bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), false);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    boolean overlaySuccess = false;
    private void processMapOverlay() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                File inputFile = new File(mActivity.getApplicationContext().getFilesDir(), OVERLAY_FILE_NAME);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                Bitmap overlayBitmap = BitmapFactory.decodeFile(inputFile.getAbsolutePath());
                if (overlayBitmap != null) {
                    while (!overlaySuccess) {
                        if(overlayBitmap.getWidth()>0 && overlayBitmap.getHeight()>0) {
                            overlayBitmap = Bitmap.createScaledBitmap(overlayBitmap
                                    , (int) (overlayBitmap.getWidth() * 0.8)
                                    , (int) (overlayBitmap.getHeight() * 0.8)
                                    , false);
                            saveBitmapToFile(overlayBitmap);
                            putMapOverlay(overlayBitmap);
                        }
                    }
                }
            }
        });
        t.start();
    }

    private void putMapOverlay(final Bitmap bitmap) {
        try {
            final GroundOverlayOptions mapOptions = new GroundOverlayOptions()
                    .image(BitmapDescriptorFactory.fromBitmap(bitmap))
                    .positionFromBounds(mapBounds)
                    .transparency(0.2f);

            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        mMap.addGroundOverlay(mapOptions);
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(middle, defaultZoom));
                        overlaySuccess = true;
                        ((MainActivity) mActivity).notifyMapOverlayFetched();
                    } catch (java.lang.OutOfMemoryError e) {
                    }
                }
            });

        } catch (java.lang.OutOfMemoryError e) {
            processMapOverlay();
        }
    }

    public void initMapOverlay(String mapUrl) {
        File inputFile = new File(mActivity.getApplicationContext().getFilesDir(), OVERLAY_FILE_NAME);
        if (inputFile.exists()) {
            processMapOverlay();
            ((MainActivity) mActivity).notifyMapOverlayFetched();
        } else {
            new LoadMapOverlayFromUrlTask().execute(mapUrl);
        }
    }

    @Override
    public void onMapLoaded() {
        if (mMap != null) {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            initPositionMarker();
            initZoneLabelMarker();
        }
    }

    private Canvas markerCanvas;
    private Bitmap.Config markerBitmapConfig;
    private Paint markerPaint;
    private Bitmap markerBitmap;
    private void initPositionMarker(){
        //init paint
        markerPaint = new Paint();
        markerBitmapConfig = Bitmap.Config.ARGB_8888;
        markerBitmap = Bitmap.createBitmap(40, 40, markerBitmapConfig);
        markerCanvas = new Canvas(markerBitmap);
        // paint circle
        markerPaint.setColor(Color.parseColor("#ffffff"));
        markerCanvas.drawCircle(20, 20, 20, markerPaint);
        markerPaint.setColor(Color.parseColor("#4285F4"));
        markerCanvas.drawCircle(20, 20, 16, markerPaint);
    }

    private Canvas zoneLabelCanvas;
    private Bitmap zoneLabelBitmap;
    private float zoneLabelBaseline;
    private Paint zonesLabelPaint;
    private void initZoneLabelMarker() {
        float textSize = 40;
        zonesLabelPaint = new Paint();
        zonesLabelPaint.setTextSize(textSize);
        zonesLabelPaint.setColor(Color.BLACK);
        zonesLabelPaint.setTextAlign(Paint.Align.LEFT);
        zoneLabelBaseline = -zonesLabelPaint.ascent(); // ascent() is negative
    }

    public void setLocationOnMap(double latitude, double longitude) {
        if (mMap != null) {
            LatLng prev = lastLocation;
            lastLocation = new LatLng(latitude, longitude);
            synchronized (this) {
                if (mCurrentLocationMarker == null) {

                    mCurrentLocationMarker = mMap.addMarker(new MarkerOptions()
                            .anchor(0.5f, 0.5f)
                            .position(lastLocation).title("Current Position")
                            .icon(BitmapDescriptorFactory.fromBitmap(markerBitmap)));
                } else
                if(prev.latitude!=lastLocation.latitude && prev.longitude!=lastLocation.longitude)
                    animateMarker(mCurrentLocationMarker,lastLocation,false);
            }
            updateCamera();
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

    public void animateMarker(final Marker marker, final LatLng toPosition,
                              final boolean hideMarker) {
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        Projection proj = mMap.getProjection();
        Point startPoint = proj.toScreenLocation(marker.getPosition());
        final LatLng startLatLng = proj.fromScreenLocation(startPoint);
        final long duration = 500;

        final Interpolator interpolator = new LinearInterpolator();

        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed
                        / duration);
                double lng = t * toPosition.longitude + (1 - t)
                        * startLatLng.longitude;
                double lat = t * toPosition.latitude + (1 - t)
                        * startLatLng.latitude;
                marker.setPosition(new LatLng(lat, lng));

                if (t < 1.0) {
                    // Post again 16ms later.
                    handler.postDelayed(this, 16);
                } else {
                    if (hideMarker) {
                        marker.setVisible(false);
                    } else {
                        marker.setVisible(true);
                    }
                }
            }
        });
    }

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
        if(mMap.getCameraPosition().zoom < defaultZoom){
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder()
                    .target(lastLocation)
                    .bearing(bearing).zoom(defaultZoom).build()));
        }else {
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder()
                    .target(lastLocation)
                    .bearing(bearing).zoom(mMap.getCameraPosition().zoom).build()));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void highlightZone(List<LatLng> coords, String name) {
        PolygonOptions rectOptions = new PolygonOptions()
                .addAll(new ArrayList<>(coords))
                .fillColor(0x80C5E1A5)
                .strokeWidth(0)
                .geodesic(false);

        // Get back the mutable Polygon
        synchronized(zonesPolygons) {
            zonesPolygons.add(mMap.addPolygon(rectOptions));
        }

        int width = (int) (zonesLabelPaint.measureText(name) + 0.5f); // round
        int height = (int) (zoneLabelBaseline + zonesLabelPaint.descent() + 0.5f);

        zoneLabelBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        zoneLabelCanvas = new Canvas(zoneLabelBitmap);
        zoneLabelCanvas.drawText(name, 0, zoneLabelBaseline, zonesLabelPaint);

        MarkerOptions options = new MarkerOptions().position(centroid(coords))
                .icon(BitmapDescriptorFactory.fromBitmap(zoneLabelBitmap));
        zonesMarkers.add(mMap.addMarker(options));
    }

    public static LatLng centroid(List<LatLng> points) {
        double[] centroid = { 0.0, 0.0 };

        for (int i = 0; i < points.size(); i++) {
            centroid[0] += points.get(i).latitude;
            centroid[1] += points.get(i).longitude;
        }

        int totalPoints = points.size();
        centroid[0] = centroid[0] / totalPoints;
        centroid[1] = centroid[1] / totalPoints;

        return new LatLng(centroid[0],centroid[1]);
    }

    public void clearHighlightedZones() {
        synchronized(zonesPolygons) {
            Iterator<Polygon> iter = zonesPolygons.iterator();
            while (iter.hasNext()) {
                Polygon p = iter.next();
                p.remove();
                iter.remove();
            }
        }
        Iterator<Marker> iter2 = zonesMarkers.iterator();
        while (iter2.hasNext()){
            Marker m = iter2.next();
            m.remove();
            iter2.remove();
        }
    }

    public void drawPath(List<LatLng> path) {
        if(pathPolyline.size()>0){
            Polyline p = pathPolyline.get(0);
            p.remove();
            pathPolyline.remove(p);
        }
        pathPolyline.add(mMap.addPolyline(new PolylineOptions()
                .addAll(path)
                .width(5)
                .color(0x803781CA)));
    }
}
