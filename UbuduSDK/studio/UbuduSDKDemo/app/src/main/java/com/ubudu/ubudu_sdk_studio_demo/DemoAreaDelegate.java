package com.ubudu.ubudu_sdk_studio_demo;

import android.os.Handler;
import android.os.Looper;

import com.ubudu.sdk.UbuduArea;
import com.ubudu.sdk.UbuduAreaDelegate;
import com.ubudu.sdk.UbuduBeaconRegion;
import com.ubudu.sdk.UbuduBeaconRegionEvent;
import com.ubudu.sdk.UbuduEvent;
import com.ubudu.sdk.UbuduGeofence;
import com.ubudu.sdk.UbuduGeofenceEvent;

public class DemoAreaDelegate implements UbuduAreaDelegate {
    
    @SuppressWarnings("unused")
    private static final String TAG = "ubudu.DemoAreaDelegate";

    TextOutput mOutput;
    Map mMap;

    public DemoAreaDelegate(){
        this(null);
    };

    public DemoAreaDelegate(TextOutput output) {
        mOutput = output;
    }

    public void setMap(Map map) {
        mMap = map;
    }

    public boolean statusChanged(int change){
        if(mOutput != null) {
            mOutput.printf("service %s\n", ((change == UbuduAreaDelegate.SERVICE_UNAVAILABLE) ? "unavailable"
                    : (change == UbuduAreaDelegate.SERVICE_STARTED) ? "activated"
                    : (change == UbuduAreaDelegate.SERVICE_STARTING) ? "starting..."
                    : "shut down"));
        }
        return true;
    }

    private double oldLatitude = 0.0;
    private double oldLongitude = 0.0;

    public void positionChanged(final android.location.Location newPosition) {
        if (mMap == null) {
            return;
        }
        try {
            final double newLatitude = newPosition.getLatitude();
            final double newLongitude = newPosition.getLongitude();
            if ((oldLatitude != newLatitude) || (oldLongitude != newLongitude)) {
                oldLatitude = newLatitude;
                oldLongitude = newLongitude;
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run() {
                        if(mOutput != null) {
                        mOutput.printf("device moved lat=%11.7f lng=%11.7f\n",newLatitude,newLongitude);
                        mMap.setLocationOnMap(newLatitude, newLongitude);
                        }
                    }
                });
            }
        } catch (Exception e) {
        }
    }

    public void areaEntered(UbuduArea area) {
        if (area instanceof UbuduGeofence){
            areaEntered((UbuduGeofence) area);
        } else if(area instanceof UbuduBeaconRegion){
            areaEntered((UbuduBeaconRegion) area);
        }
    }

    public void areaExited(UbuduArea area) {
        if (area instanceof UbuduGeofence){
            areaExited((UbuduGeofence)area);
        } else if (area instanceof UbuduBeaconRegion){
            areaExited((UbuduBeaconRegion)area);
        }
    }

    public void areaEntered(final UbuduGeofence fence) {
        Handler refresh = new Handler(Looper.getMainLooper());
        refresh.post(new Runnable() {
            public void run() {
                if(mOutput != null) {
                mOutput.printf("geofence entered id=%3s lat=%11.7f lng=%11.7f radius=%5.2f\n  name=\"%s\"\n",
                             fence.id(),
                             fence.centerLatitude(), fence.centerLongitude(),
                             fence.radius(),
                             fence.name());
                mMap.updateCircle(fence.id(), fence.name(),
                                fence.centerLatitude(), fence.centerLongitude(),
                                fence.radius(), Map.INSIDE);
                }
            }
        });
    }

    public void areaExited(final UbuduGeofence fence) {
        Handler refresh = new Handler(Looper.getMainLooper());
        refresh.post(new Runnable() {
            public void run() {
                if(mOutput != null) {
                mOutput.printf("geofence exited  id=%3s lat=%11.7f lng=%11.7f radius=%5.2f\n  name=\"%s\"\n",
                             fence.id(),
                             fence.centerLatitude(), fence.centerLongitude(),
                             fence.radius(),
                             fence.name());
                mMap.updateCircle(fence.id(), fence.name(),
                        fence.centerLatitude(), fence.centerLongitude(),
                        fence.radius(), Map.OUTSIDE);
                }
            }
        });
    }

    public void areaEntered(final UbuduBeaconRegion beacon) {
        Handler refresh = new Handler(Looper.getMainLooper());
        refresh.post(new Runnable() {
            public void run() {
                if(mOutput != null) {
                mOutput.printf("beacon detected   in   region id=%3s \n  UUID=%s\n  major=%s minor=%s\n  name=\"%s\" \n",
                             beacon.id(),
                             beacon.proximityUUID(), beacon.major(), beacon.minor(),
                             beacon.name());
                }
            }
        });
    }

    public void areaExited(final UbuduBeaconRegion beacon) {
        Handler refresh = new Handler(Looper.getMainLooper());
        refresh.post(new Runnable() {
            public void run() {
                if(mOutput != null) {
                mOutput.printf("beacon disappeared from region id=%3s \n  UUID=%s\n  major=%s minor=%s\n  name=\"%s\" \n",
                             beacon.id(),
                             beacon.proximityUUID(), beacon.major(), beacon.minor(),
                             beacon.name());
                }
            }
        });
    }

    public boolean notifyServer(final java.net.URL notificationServerUrl) {
        Handler refresh = new Handler(Looper.getMainLooper());
        refresh.post(new Runnable() {
            public void run() {
                if(mOutput != null) {
                mOutput.printf("notifyServer %s\n",notificationServerUrl);
                }
            }
        });
        return true;
    }

    @Override
    public boolean shouldNotifyUserForEvent(UbuduEvent ubuduEvent) {
        return true;
    }

    public void ruleFiredForEvent(UbuduEvent event) {
        if (event instanceof UbuduGeofenceEvent) {
            ruleFiredForEvent((UbuduGeofenceEvent)event);
        } else if(event instanceof UbuduBeaconRegionEvent) {
            ruleFiredForEvent((UbuduBeaconRegionEvent)event);
        }
    }

    public void ruleFiredForEvent(final UbuduGeofenceEvent event) {
        Handler refresh = new Handler(Looper.getMainLooper());
        refresh.post(new Runnable() {
            public void run() {
                if(mOutput != null) {
                mOutput.printf("geofence rule fired %-7s id=%3s \n  name=\"%s\")\n",
                        ((event.eventKind()==UbuduEvent.ENTERED)?"entered":"exited"),
                        event.area().id(),
                        event.area().name());
                mOutput.printf("payload=%s\n",event.notification().payload().optJSONObject("payload").toString());
                }
            }
        });
    }

    public void ruleFiredForEvent(final UbuduBeaconRegionEvent event) {
        Handler refresh = new Handler(Looper.getMainLooper());
        refresh.post(new Runnable() {
            public void run() {
                if(mOutput != null) {
                mOutput.printf("beacon  rule fired %-7s id=%3s \n  name=\"%s\")\n",
                        ((event.eventKind()==UbuduEvent.ENTERED)?"entered":"exited"),
                        event.area().id(),
                        event.area().name());
                mOutput.printf("payload=%s\n",event.notification().payload().optJSONObject("payload").toString());
                }
            }
        });
    }

    public void notifyUserForEvent(UbuduEvent event) {
        if (event instanceof UbuduGeofenceEvent) {
            notifyUserForEvent((UbuduGeofenceEvent)event);
        } else if (event instanceof UbuduBeaconRegionEvent) {
            notifyUserForEvent((UbuduBeaconRegionEvent)event);
        }
    }

    public boolean notifyUserForEvent(final UbuduGeofenceEvent event) {
        Handler refresh = new Handler(Looper.getMainLooper());
        refresh.post(new Runnable() {
            public void run() {
                if(mOutput != null) {
                mOutput.printf("geofence notify user for event %7s id=%3s\n  name=\"%s\"\n",
                        ((event.eventKind() == UbuduEvent.ENTERED)?"entered":"exited"),
                        event.area().id(),
                        event.area().name());
                mOutput.printf("payload=%s\n",event.notification().payload().optJSONObject("payload").toString());
                }
            }
        });
        return true;
    }

    public boolean notifyUserForEvent(final UbuduBeaconRegionEvent event) {
        Handler refresh = new Handler(Looper.getMainLooper());
        refresh.post(new Runnable() {
            public void run() {
                if (mOutput != null) {
                    mOutput.printf("beacon notify user for event %7s id=%3s\n name=\"%s\"\n",
                            ((event.eventKind() == UbuduEvent.ENTERED) ? "entered" : "exited"),
                            event.area().id(),
                            event.area().name());
                    mOutput.printf("payload=%s\n web page url=%s\n", event.notification().payload().optJSONObject("payload").toString(),event.notification().webPageUrl());

                }
            }
        });
        return true;
    }
}
