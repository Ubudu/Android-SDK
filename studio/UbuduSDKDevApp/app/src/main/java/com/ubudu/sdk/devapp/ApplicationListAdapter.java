// -*- mode:java;coding:utf-8 -*-
//****************************************************************************
//FILE:               API.java
//LANGUAGE:           java
//SYSTEM:             Android
//USER-INTERFACE:     Android
//DESCRIPTION
//    
//    Displays the list of ubudu contextual application (namespaces)
//    and let the user select one.
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

import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.content.Context;
import java.util.List;
import com.ubudu.sdk.devapp.UApplication;
import android.widget.ArrayAdapter;
import com.ubudu.sdk.devapp.ApplicationListAdapter;
import com.ubudu.sdk.UbuduSDK;
import java.lang.String;
import java.util.Collections;

public class ApplicationListAdapter extends ArrayAdapter<UApplication> {
     
  private List<UApplication> itemList;
  private Context context;
         
  public ApplicationListAdapter(List<UApplication> itemList, Context ctx) {
    super(ctx,R.layout.list_item_application,itemList);
    this.itemList = itemList;
    this.context = ctx;       
  }
     
  public int getCount() {
    return (itemList==null)?0:itemList.size();
  }
 
  public UApplication getItem(int position){
    return(itemList==null)?null:itemList.get(position);
  }
 
  public long getItemId(int position) {
    return(itemList==null)?0:itemList.get(position).hashCode();
  }
 
  @Override
  public View getView(int position, View convertView, ViewGroup parent){
    if(convertView==null){
      LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      convertView=inflater.inflate(R.layout.list_item_application, null);
    }
    UApplication c = itemList.get(position);
    UbuduSDK sdk=UbuduSDK.getSharedInstance(context);
    String mark=(sdk.namespace().equals(c.namespace_uid))?"✓":" ";
    TextView nameText = (TextView)convertView.findViewById(R.id.textview_name);
    nameText.setText(mark+c.name);
    TextView namespaceText = (TextView)convertView.findViewById(R.id.textview_namespace);
    namespaceText.setText(c.namespace_uid);
    return convertView;         
  }
 
  public List<UApplication> getItemList() {
    return itemList;
  }
 
  public void setItemList(List<UApplication> itemList) {
    Collections.sort(itemList);
    this.itemList = itemList;
  }
     
}
