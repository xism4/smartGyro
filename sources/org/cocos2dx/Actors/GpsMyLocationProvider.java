package org.cocos2dx.Actors;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

class GpsMyLocationProvider implements LocationListener {
    final /* synthetic */ AppActivity lon;

    GpsMyLocationProvider(AppActivity appActivity) {
        this.lon = appActivity;
    }

    public void onLocationChanged(Location location) {
        if (location != null) {
            this.lon.lat = Double.valueOf(location.getLatitude());
            this.lon.lon = Double.valueOf(location.getLongitude());
            float $f0 = location.getBearing();
            float $f1 = location.getSpeed();
            double $d0 = location.getAltitude();
            Log.d("get_gps", "纬度:" + String.valueOf(this.lon.lat));
            Log.d("get_gps", "经度:" + String.valueOf(this.lon.lon));
            Log.d("get_gps", "方向:" + String.valueOf($f0));
            Log.d("get_gps", "速度:" + String.valueOf($f1));
            Log.d("get_gps", "海拔:" + String.valueOf($d0));
            return;
        }
        Log.d("los", "经度/纬度:未知");
    }

    public void onProviderDisabled(String str) {
        Log.d("onProviderDisabled", "provider:" + str);
    }

    public void onProviderEnabled(String str) {
        Log.d("onProviderEnabled", "provider:" + str);
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
        Log.d("onStatusChanged", "provider:" + str);
    }
}
