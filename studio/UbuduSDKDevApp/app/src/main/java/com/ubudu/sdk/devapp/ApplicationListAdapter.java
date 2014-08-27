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
