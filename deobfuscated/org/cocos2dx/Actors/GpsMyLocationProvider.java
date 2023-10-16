package org.cocos2dx.Actors;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

class GpsMyLocationProvider
  implements LocationListener
{
  GpsMyLocationProvider(AppActivity paramAppActivity) {}
  
  public void onLocationChanged(Location paramLocation)
  {
    if (paramLocation != null)
    {
      lon.lat = Double.valueOf(paramLocation.getLatitude());
      lon.lon = Double.valueOf(paramLocation.getLongitude());
      float f1 = paramLocation.getBearing();
      float f2 = paramLocation.getSpeed();
      double d = paramLocation.getAltitude();
      paramLocation = new StringBuilder();
      paramLocation.append("??:");
      paramLocation.append(String.valueOf(lon.lat));
      Log.d("get_gps", paramLocation.toString());
      paramLocation = new StringBuilder();
      paramLocation.append("??:");
      paramLocation.append(String.valueOf(lon.lon));
      Log.d("get_gps", paramLocation.toString());
      paramLocation = new StringBuilder();
      paramLocation.append("??:");
      paramLocation.append(String.valueOf(f1));
      Log.d("get_gps", paramLocation.toString());
      paramLocation = new StringBuilder();
      paramLocation.append("??:");
      paramLocation.append(String.valueOf(f2));
      Log.d("get_gps", paramLocation.toString());
      paramLocation = new StringBuilder();
      paramLocation.append("??:");
      paramLocation.append(String.valueOf(d));
      Log.d("get_gps", paramLocation.toString());
      return;
    }
    Log.d("los", "??/??:??");
  }
  
  public void onProviderDisabled(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("provider:");
    localStringBuilder.append(paramString);
    Log.d("onProviderDisabled", localStringBuilder.toString());
  }
  
  public void onProviderEnabled(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("provider:");
    localStringBuilder.append(paramString);
    Log.d("onProviderEnabled", localStringBuilder.toString());
  }
  
  public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle)
  {
    paramBundle = new StringBuilder();
    paramBundle.append("provider:");
    paramBundle.append(paramString);
    Log.d("onStatusChanged", paramBundle.toString());
  }
}
