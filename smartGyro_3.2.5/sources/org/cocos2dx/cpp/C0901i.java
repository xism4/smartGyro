package org.cocos2dx.cpp;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

/* renamed from: org.cocos2dx.cpp.i */
class C0901i implements LocationListener {

    /* renamed from: a */
    final /* synthetic */ AppActivity f2460a;

    C0901i(AppActivity appActivity) {
        this.f2460a = appActivity;
    }

    public void onLocationChanged(Location location) {
        if (location != null) {
            this.f2460a.lat = Double.valueOf(location.getLatitude());
            this.f2460a.lng = Double.valueOf(location.getLongitude());
            float bearing = location.getBearing();
            float speed = location.getSpeed();
            double altitude = location.getAltitude();
            Log.d("get_gps", "纬度:" + String.valueOf(this.f2460a.lat));
            Log.d("get_gps", "经度:" + String.valueOf(this.f2460a.lng));
            Log.d("get_gps", "方向:" + String.valueOf(bearing));
            Log.d("get_gps", "速度:" + String.valueOf(speed));
            Log.d("get_gps", "海拔:" + String.valueOf(altitude));
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
