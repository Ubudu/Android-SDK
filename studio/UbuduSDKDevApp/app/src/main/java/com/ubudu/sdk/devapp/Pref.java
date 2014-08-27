package com.ubudu.sdk.devapp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import android.content.Context;
import java.lang.String;
import android.content.SharedPreferences;

public class Pref
{
  public static final String PREF_FILE = "com.ubudu.sdk.devapp.preferences";
  protected SharedPreferences mSharedPref;
  public Pref(Context ctx){
    mSharedPref=ctx.getSharedPreferences(PREF_FILE,Context.MODE_PRIVATE);
  }

  public String getPreferenceString(String name,String defaultString){
    return mSharedPref.getString(name,defaultString);
  }
  
  public String getPreferenceString(String name){
    return getPreferenceString(name,"");
  }
  
  public boolean getPreferenceBoolean(String name,boolean defaultBoolean){
    return mSharedPref.getBoolean(name,defaultBoolean);
  }
  
  public boolean getPreferenceBoolean(String name){
    return getPreferenceBoolean(name,false);
  }
  
  public int getPreferenceInt(String name,int defaultInt){
    return mSharedPref.getInt(name,defaultInt);
  }
  
  public int getPreferenceInt(String name){
    return getPreferenceInt(name,0);
  }
  
  public Set<String> getPreferenceStringSet(String name,Set<String> defaultSet){
    return mSharedPref.getStringSet(name,defaultSet);
  }

  public void setPreference(String name,String value){
    SharedPreferences.Editor editor=mSharedPref.edit();
    editor.putString(name,value);
    editor.commit();
  }
  
  public void setPreference(String name,boolean value){
    SharedPreferences.Editor editor=mSharedPref.edit();
    editor.putBoolean(name,value);
    editor.commit();
  }
  
  public void setPreference(String name,int value){
    SharedPreferences.Editor editor=mSharedPref.edit();
    editor.putInt(name,value);
    editor.commit();
  }

  public void setPreference(String name,Set<String> value){
    SharedPreferences.Editor editor=mSharedPref.edit();
    editor.putStringSet(name,value);
    editor.commit();
  }

  public Set<String> mapToSet(java.util.Map<String,String> map){
    HashSet<String> set=new HashSet<String>(map.size());
    for(java.util.Map.Entry<String,String> entry:map.entrySet()){
      set.add(String.format("%s=%s",entry.getKey(),entry.getValue()));
    }
    return set;
  }

  public java.util.Map<String,String> setToMap(Set<String> set){
    HashMap<String,String> map=new HashMap<String,String>(set.size());
    for(String entry:set){
      int equal=entry.indexOf("=");
      if(equal<0){
        map.put(entry,"");
      }else{
        map.put(entry.substring(0,equal),entry.substring(equal+1));
      }
    }
    return map;
  }

}
