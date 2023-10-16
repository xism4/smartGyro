package android.support.p025v7.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import java.util.Calendar;
import p000a.p001a.p005c.p006a.C0041b;

/* renamed from: android.support.v7.app.C */
class C0229C {

    /* renamed from: a */
    private static C0229C f677a;

    /* renamed from: b */
    private final Context f678b;

    /* renamed from: c */
    private final LocationManager f679c;

    /* renamed from: d */
    private final C0230a f680d = new C0230a();

    /* renamed from: android.support.v7.app.C$a */
    private static class C0230a {

        /* renamed from: a */
        boolean f681a;

        /* renamed from: b */
        long f682b;

        /* renamed from: c */
        long f683c;

        /* renamed from: d */
        long f684d;

        /* renamed from: e */
        long f685e;

        /* renamed from: f */
        long f686f;

        C0230a() {
        }
    }

    C0229C(Context context, LocationManager locationManager) {
        this.f678b = context;
        this.f679c = locationManager;
    }

    /* renamed from: a */
    private Location m879a(String str) {
        try {
            if (this.f679c.isProviderEnabled(str)) {
                return this.f679c.getLastKnownLocation(str);
            }
            return null;
        } catch (Exception e) {
            Log.d("TwilightManager", "Failed to get last known location", e);
            return null;
        }
    }

    /* renamed from: a */
    static C0229C m880a(Context context) {
        if (f677a == null) {
            Context applicationContext = context.getApplicationContext();
            f677a = new C0229C(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
        }
        return f677a;
    }

    /* renamed from: a */
    private void m881a(Location location) {
        long j;
        C0230a aVar = this.f680d;
        long currentTimeMillis = System.currentTimeMillis();
        C0228B a = C0228B.m877a();
        C0228B b = a;
        b.mo960a(currentTimeMillis - 86400000, location.getLatitude(), location.getLongitude());
        long j2 = a.f674b;
        b.mo960a(currentTimeMillis, location.getLatitude(), location.getLongitude());
        boolean z = a.f676d == 1;
        long j3 = a.f675c;
        long j4 = j2;
        long j5 = a.f674b;
        long j6 = j3;
        boolean z2 = z;
        a.mo960a(86400000 + currentTimeMillis, location.getLatitude(), location.getLongitude());
        long j7 = a.f675c;
        if (j6 == -1 || j5 == -1) {
            j = 43200000 + currentTimeMillis;
        } else {
            j = (currentTimeMillis > j5 ? 0 + j7 : currentTimeMillis > j6 ? 0 + j5 : 0 + j6) + 60000;
        }
        aVar.f681a = z2;
        aVar.f682b = j4;
        aVar.f683c = j6;
        aVar.f684d = j5;
        aVar.f685e = j7;
        aVar.f686f = j;
    }

    @SuppressLint({"MissingPermission"})
    /* renamed from: b */
    private Location m882b() {
        Location location = null;
        Location a = C0041b.m138a(this.f678b, "android.permission.ACCESS_COARSE_LOCATION") == 0 ? m879a("network") : null;
        if (C0041b.m138a(this.f678b, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            location = m879a("gps");
        }
        return (location == null || a == null) ? location != null ? location : a : location.getTime() > a.getTime() ? location : a;
    }

    /* renamed from: c */
    private boolean m883c() {
        return this.f680d.f686f > System.currentTimeMillis();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo961a() {
        C0230a aVar = this.f680d;
        if (m883c()) {
            return aVar.f681a;
        }
        Location b = m882b();
        if (b != null) {
            m881a(b);
            return aVar.f681a;
        }
        Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
        int i = Calendar.getInstance().get(11);
        return i < 6 || i >= 22;
    }
}
