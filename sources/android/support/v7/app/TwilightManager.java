package android.support.v7.app;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import com.org.android.ui.PermissionChecker;
import java.util.Calendar;

class TwilightManager {
    private static TwilightManager mLocation;
    private final Context mContext;
    private final LocationManager mLocationManager;
    private final TwilightState sTwilightState = new TwilightState();

    class TwilightState {
        boolean isNight;
        long nextUpdate;
        long todaySunrise;
        long todaySunset;
        long tomorrowSunrise;
        long yesterdaySunset;

        TwilightState() {
        }
    }

    TwilightManager(Context context, LocationManager locationManager) {
        this.mContext = context;
        this.mLocationManager = locationManager;
    }

    private Location getLastKnownLocation() {
        Location $r2 = null;
        Location $r3 = PermissionChecker.checkSelfPermission(this.mContext, "android.permission.ACCESS_COARSE_LOCATION") == 0 ? getLastKnownLocationForProvider("network") : null;
        if (PermissionChecker.checkSelfPermission(this.mContext, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            $r2 = getLastKnownLocationForProvider("gps");
        }
        if ($r2 == null || $r3 == null) {
            if ($r2 != null) {
                return $r2;
            }
        } else if ($r2.getTime() > $r3.getTime()) {
            return $r2;
        }
        return $r3;
    }

    static TwilightManager getLastKnownLocation(Context context) {
        if (mLocation == null) {
            Context $r0 = context.getApplicationContext();
            mLocation = new TwilightManager($r0, (LocationManager) $r0.getSystemService("location"));
        }
        return mLocation;
    }

    private Location getLastKnownLocationForProvider(String str) {
        try {
            if (this.mLocationManager.isProviderEnabled(str)) {
                return this.mLocationManager.getLastKnownLocation(str);
            }
            return null;
        } catch (Exception $r4) {
            Log.d("TwilightManager", "Failed to get last known location", $r4);
            return null;
        }
    }

    private boolean isStateValid() {
        return this.sTwilightState.nextUpdate > System.currentTimeMillis();
    }

    private void updateState(Location location) {
        long $l0;
        TwilightState $r2 = this.sTwilightState;
        long $l02 = System.currentTimeMillis();
        TwilightCalculator $r3 = TwilightCalculator.getInstance();
        $r3.calculateTwilight($l02 - 86400000, location.getLatitude(), location.getLongitude());
        long $l1 = $r3.sunset;
        $r3.calculateTwilight($l02, location.getLatitude(), location.getLongitude());
        boolean z = $r3.state == 1;
        long $l3 = $r3.sunrise;
        long $l4 = $r3.sunset;
        $r3.calculateTwilight(86400000 + $l02, location.getLatitude(), location.getLongitude());
        long $l5 = $r3.sunrise;
        if ($l3 == -1 || $l4 == -1) {
            $l0 = 43200000 + $l02;
        } else {
            $l0 = ($l02 > $l4 ? 0 + $l5 : $l02 > $l3 ? 0 + $l4 : 0 + $l3) + 60000;
        }
        $r2.isNight = z;
        $r2.yesterdaySunset = $l1;
        $r2.todaySunrise = $l3;
        $r2.todaySunset = $l4;
        $r2.tomorrowSunrise = $l5;
        $r2.nextUpdate = $l0;
    }

    /* access modifiers changed from: package-private */
    public boolean isNight() {
        TwilightState $r1 = this.sTwilightState;
        if (isStateValid()) {
            return $r1.isNight;
        }
        Location $r2 = getLastKnownLocation();
        if ($r2 != null) {
            updateState($r2);
            return $r1.isNight;
        }
        Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
        int $i0 = Calendar.getInstance().get(11);
        return $i0 < 6 || $i0 >= 22;
    }
}
