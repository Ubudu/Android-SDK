// -*- mode:java; coding:utf-8 -*-
// Generated automatically by generate.el

package com.ubudu.sdk.devapp;

import java.lang.Object;
import java.lang.String;
import java.lang.Integer;
import com.google.gson.annotations.SerializedName;
public class UApplication extends Object implements java.lang.Comparable{
public UApplication(){
super();
}
@SerializedName("url")
public String url;
@SerializedName("anti_hacking_protocol")
public String anti_hacking_protocol;
@SerializedName("service_proximity_uuid")
public String service_proximity_uuid;
@SerializedName("secure_proximity_uuid")
public String secure_proximity_uuid;
@SerializedName("normal_proximity_uuid")
public String normal_proximity_uuid;
@SerializedName("environment")
public String environment;
@SerializedName("namespace_uid")
public String namespace_uid;
@SerializedName("name")
public String name;
@SerializedName("id")
public Integer id;

  public int compareTo(Object other){
    if(other instanceof UApplication){
      return this.name.compareTo(((UApplication)other).name);
    }else{
      return -1;
    }
  }

}
