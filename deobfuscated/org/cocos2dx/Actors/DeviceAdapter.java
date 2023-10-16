package org.cocos2dx.Actors;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

public class DeviceAdapter
  extends BaseAdapter
{
  private Context context;
  private ArrayList<BluetoothDevice> devices;
  
  public DeviceAdapter(Context paramContext, ArrayList paramArrayList)
  {
    context = paramContext;
    devices = paramArrayList;
  }
  
  public int getCount()
  {
    ArrayList localArrayList = devices;
    if (localArrayList != null) {
      return localArrayList.size();
    }
    return 0;
  }
  
  public Object getItem(int paramInt)
  {
    return devices.get(paramInt);
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    return paramView;
  }
}