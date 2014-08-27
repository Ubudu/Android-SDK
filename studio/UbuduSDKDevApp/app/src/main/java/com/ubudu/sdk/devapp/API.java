package com.ubudu.sdk.devapp;

import android.net.http.AndroidHttpClient;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ubudu.sdk.devapp.UApplication;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Byte;
import java.lang.Runnable;
import java.lang.String;
import java.lang.Thread;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

public class API
{
  protected String mBaseUrl;
  protected String mOoauthAccessToken;
  
  public final String userAgent="com.ubudu.sdk.devapp/1.0";

  public API(String baseUrl,String ooauthAccessToken){
    mBaseUrl=baseUrl;
    mOoauthAccessToken=ooauthAccessToken;
  }


  public interface ApplicationCallback{
    public void receivedApplications(Collection<UApplication> applications);
  }

  public String applicationsUrl(){
    return String.format("%s/u_applications.json?access_token=%s",mBaseUrl,mOoauthAccessToken);
  }

  public void queryApplications(final ApplicationCallback callback){
    new Thread(new Runnable(){
        public void run(){
          try{
            HttpGet request=new HttpGet(API.this.applicationsUrl());
            AndroidHttpClient client=AndroidHttpClient.newInstance(userAgent);
            try{
              HttpResponse response=client.execute(request);
              InputStream resultStream=AndroidHttpClient.getUngzippedContent(response.getEntity());
              ArrayList<Byte> buffer=new ArrayList<Byte>(4096);
              int octet=resultStream.read();
              while(0<=octet){
                buffer.add((byte)octet);
                octet=resultStream.read();
              }
              int size=buffer.size();
              byte[] bytes=new byte[size];
              for(int i=0;i<size;i++){
                bytes[i]=buffer.get(i);
              }
              String json=new String(bytes,"UTF-8");
              Gson gson = new Gson();
              Type collectionType = new TypeToken<Collection<UApplication>>(){}.getType();
              Collection<UApplication> uapplications=gson.fromJson(json,collectionType);
              callback.receivedApplications(uapplications);
            }finally{
              client.close();
            }
          }catch(IOException exception){
            Log.e("Ubudu API",String.format("Caught exception %s",exception));
          }
        }
      },"query u_applications").start();
  }
  
}
