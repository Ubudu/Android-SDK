// -*- mode:java;coding:utf-8 -*-
//****************************************************************************
//FILE:               UbuduBaseFragment.java
//LANGUAGE:           java
//SYSTEM:             Android
//USER-INTERFACE:     Android
//DESCRIPTION
//    
//    Displays buttons to select geofences or beacons.
//    
//AUTHORS
//    <PJB> Pascal J. Bourguignon <pjb@informatimago.com>
//MODIFICATIONS
//    2014-08-27 <PJB> Added this header.
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
package com.ubudu.ubudu_sdk_studio_demo;

import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.ubudu.sdk.UbuduAreaManager;

public abstract class UbuduBaseFragment extends Fragment {

    public static final int BEACON = 1;
    public static final int GEOFENCE = 2;

    private UbuduAreaManager mAreaManager;

	private TextView mActionButtonStatus;
	private TextView mInfoLabel;
	private TextOutput mTextOutput;

	private boolean mScanning = false;

	/**
	 * Children classes must call this
	 */
	public void create(UbuduAreaManager areaManager, TextView actionButtonStatus, TextView infoLabel, TextOutput textOutput) {
        this.mAreaManager = areaManager;
		this.mActionButtonStatus = actionButtonStatus;
		this.mInfoLabel = infoLabel;
		this.mTextOutput = textOutput;

		// Check if manager is started. If yes refresh UI.
        if (getAreaManager().isMonitoring()) {
            mScanning = true;
        }

		refreshActionButtonState();
	}

	/**
	 * @return type of scan performed by the manager linked to this fragment
	 */
	public abstract int getMode();

    /**
     * @return Pluralized name of the type or areas handled by the manager linked to this fragment
     */
    public abstract String getManagerAreasType();

    public UbuduAreaManager getAreaManager() {
        return mAreaManager;
    }

	public TextOutput getTextOutput() {
		return mTextOutput;
	}

	public boolean isScanning() {
        return mScanning;
	}

	public void startScanning() {
        getTextOutput().printf("Start searching for " + getManagerAreasType());
        Error e = getAreaManager().start(getActivity());
        mScanning = true;
        refreshActionButtonState();
		if (e != null) {
			getTextOutput().printf("Error: %s\n", e.toString());
		}
	}

	public void stopScanning() {
        getTextOutput().printf("Stop searching for " + getManagerAreasType());
        getAreaManager().stop(getActivity());
        mScanning = false;
        refreshActionButtonState();
	}

    private void refreshActionButtonState() {
        if (!isScanning()) {
            mInfoLabel.setText("Press button to start searching for " + getManagerAreasType());
            mActionButtonStatus.setText("Start");
        } else {
            mInfoLabel.setText("Press button to stop searching for " + getManagerAreasType());
            mActionButtonStatus.setText("Stop");
        }
    }
}
