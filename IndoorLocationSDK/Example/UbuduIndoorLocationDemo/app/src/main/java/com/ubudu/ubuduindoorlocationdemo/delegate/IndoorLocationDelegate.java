// -*- mode:java;coding:utf-8 -*-
//****************************************************************************
//FILE:               IndoorLocationDelegate.java
//LANGUAGE:           java
//SYSTEM:             Android
//USER-INTERFACE:     Android
//DESCRIPTION
//    
//    This is the ubudu indoor location delegate.
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
package com.ubudu.ubuduindoorlocationdemo.delegate;

import android.annotation.SuppressLint;
import android.content.Context;

import com.google.android.gms.maps.model.LatLng;
import com.ubudu.indoorlocation.UbuduBeacon;
import com.ubudu.indoorlocation.UbuduCoordinates2D;
import com.ubudu.indoorlocation.UbuduIndoorLocationDelegate;
import com.ubudu.indoorlocation.UbuduIndoorLocationManager;
import com.ubudu.indoorlocation.UbuduMap;
import com.ubudu.indoorlocation.UbuduPoint;
import com.ubudu.indoorlocation.UbuduPositionUpdate;
import com.ubudu.indoorlocation.UbuduZone;
import com.ubudu.sdk.UbuduSDK;
import com.ubudu.ubuduindoorlocationdemo.utils.DelegateAppInterface;
import com.ubudu.ubuduindoorlocationdemo.utils.Map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IndoorLocationDelegate implements UbuduIndoorLocationDelegate {

    @SuppressWarnings("unused")
    private static final String TAG = "ubudu.IndoorLocationDelegate";

    private DelegateAppInterface mOutput;
    private Map mMap;
    private Context mContext;

    public UbuduPoint lastPosition,currentSmoothedPositionNavPoint;
    public List<UbuduPoint> path;
    private UbuduMap ubuduMap;

    public IndoorLocationDelegate(DelegateAppInterface output) {
        mOutput = output;
        mContext = output.context();
    }

    public void setMap(Map map) {
        mMap = map;
    }

    @SuppressLint("LongLogTag")
    @Override
    public void positionChanged(UbuduPositionUpdate ubuduPositionUpdate) {

        if(mMap!=null) {
            currentSmoothedPositionNavPoint = ubuduMap.getClosestNavigablePointFromPosition(ubuduPositionUpdate.getSmoothedPosition());
            if(lastPosition!=null){
                path = ubuduMap.path(lastPosition, currentSmoothedPositionNavPoint);
                if(path!=null) {
                    List<LatLng> pathGeoCoords = new ArrayList<>();
                    Iterator<UbuduPoint> iter = path.iterator();
                    UbuduCoordinates2D geoCoords;
                    while(iter.hasNext()){
                        geoCoords = UbuduSDK.getSharedInstance(mContext).getIndoorLocationManager().geoCoordinates(iter.next());
                        pathGeoCoords.add(new LatLng(geoCoords.latitude(), geoCoords.longitude()));
                    }
                    mMap.drawPath(pathGeoCoords);
                }
            }
            lastPosition = ubuduPositionUpdate.getClosestNavigablePoint();

            UbuduCoordinates2D geoCoords = UbuduSDK.getSharedInstance(mContext).getIndoorLocationManager().geoCoordinates(lastPosition);
            mMap.setLocationOnMap(geoCoords.latitude(), geoCoords.longitude());
        }
    }

    @Override
    public void closestBeaconChanged(UbuduPositionUpdate ubuduPositionUpdate) {
        if(ubuduPositionUpdate.getClosestBeacon()!=null)
            mOutput.printf("Closest beacon is now:\n maj:"
                    +ubuduPositionUpdate.getClosestBeacon().major()+", min:"
                    +ubuduPositionUpdate.getClosestBeacon().minor());
    }

    @Override
    public void closestZoneChanged(UbuduPositionUpdate ubuduPositionUpdate) {
        UbuduZone closestZone = ubuduPositionUpdate.getClosestZone();
        if(ubuduPositionUpdate.getClosestZone()!=null)
            mOutput.printf("Closest zone is now: "+closestZone.name()+". Distance: "
                    + UbuduSDK.getSharedInstance(mContext).getIndoorLocationManager()
                    .convertPixelDistanceToMeters(closestZone.distanceToPoint(ubuduPositionUpdate.getClosestNavigablePoint())));
    }

    @Override
    public void zonesChanged(List<UbuduZone> list) {
        mOutput.highlightZones(list);
        String result = "Zones change: ";
        if (list.size() > 0) {
            Iterator<UbuduZone> it = list.iterator();
            while (it.hasNext()) {
                result += '\n' + it.next().name();
            }
        } else {
            result += "\noutside";
        }
        mOutput.printf(result);
    }

    @Override
    public void closestNavigablePointChanged(UbuduPositionUpdate ubuduPositionUpdate) {

    }

    @Override
    public void beaconsUpdated(List<UbuduBeacon> arrayList) {

    }

    @Override
    public void startSucceed() {
        ubuduMap = UbuduSDK.getSharedInstance(mContext).getIndoorLocationManager().map();
        if(mOutput!=null){
            mOutput.tellAppILStarted();
        }
    }

    @Override
    public void startFailed() {
        if(mOutput!=null){
            mOutput.tellAppILStartFailed();
        }
    }
}
