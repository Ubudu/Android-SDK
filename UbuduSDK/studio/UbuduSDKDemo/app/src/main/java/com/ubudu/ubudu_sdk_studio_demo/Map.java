package com.ubudu.ubudu_sdk_studio_demo;

import java.util.Hashtable;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map {
  protected GoogleMap mMap;
  protected Activity mActivity;
  protected Hashtable<String, Circle> mCircles;

  public Map(Activity activity, int fragmentId) {
    mActivity = activity;
    ((MapFragment) (mActivity.getFragmentManager().findFragmentById(fragmentId))).getMapAsync(new OnMapReadyCallback() {
      @Override
      public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (mMap != null) {
          if (ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                  && ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
          }
          mMap.setMyLocationEnabled(true);
          mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
          mMap.setIndoorEnabled(true);
          updateLocationOnMap();
        }
        mCircles = new Hashtable<String, Circle>();
      }
    });

  }

  public Location getLastKnownLocation() {
    LocationManager locationManager = (LocationManager) (mActivity.getSystemService(Context.LOCATION_SERVICE));
    Criteria criteria = new Criteria();
    criteria.setAltitudeRequired(false);
    criteria.setBearingRequired(false);
    // criteria.setAccuracy(Criteria.ACCURACY_FINE);
    // criteria.setAccuracy(Criteria.ACCURACY_COARSE);
    String provider = locationManager.getBestProvider(criteria, true);
    if (provider == null) {
      provider = LocationManager.GPS_PROVIDER;
    }
    if (ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
      return null;
    }
    return locationManager.getLastKnownLocation(provider);
  }

  public void updateLocationOnMap() {
    if (mMap!=null) {
      Location currentLocation=getLastKnownLocation();
      if (currentLocation != null) {
        setLocationOnMap(currentLocation.getLatitude(),currentLocation.getLongitude());
      }
    }
  }

  private Marker mCurrentLocationMarker;

  public void setLocationOnMap(double latitude,double longitude) {
    if (mMap!=null) {
      LatLng location = new LatLng(latitude,longitude);
      synchronized(this) {
        if (mCurrentLocationMarker == null) {
          MarkerOptions markerOptions = new MarkerOptions();
          // markerOptions.icon(someNiceIcon);
          markerOptions.position(location);
          mCurrentLocationMarker = mMap.addMarker(markerOptions);
        } else {
          mCurrentLocationMarker.setPosition(location);
        }
      }
      CameraPosition.Builder cameraPositionBuilder = new CameraPosition.Builder().target(location).zoom(15);
      CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPositionBuilder.build());
      mMap.animateCamera(cameraUpdate);
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

  public void removeCircle(String id) {
    Circle old=mCircles.remove(id);
    if (old!=null) {
      old.remove();
    }
  }

  public void updateCircle(String id,String name,double latitude,double longitude,double radius,int status) {
    int stroke=OUTSIDE_STROKE;
    int fill=OUTSIDE_FILL;
    Circle old=mCircles.get(id);
    if (old!=null) {
      stroke=old.getStrokeColor();
      fill=old.getFillColor();
      old.remove();
    }
    switch(status) {
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
