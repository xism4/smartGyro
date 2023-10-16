package android.support.p024v4.app;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.p024v4.app.C0183b;

/* renamed from: android.support.v4.app.a */
class C0182a implements Runnable {

    /* renamed from: a */
    final /* synthetic */ String[] f449a;

    /* renamed from: b */
    final /* synthetic */ Activity f450b;

    /* renamed from: c */
    final /* synthetic */ int f451c;

    C0182a(String[] strArr, Activity activity, int i) {
        this.f449a = strArr;
        this.f450b = activity;
        this.f451c = i;
    }

    public void run() {
        int[] iArr = new int[this.f449a.length];
        PackageManager packageManager = this.f450b.getPackageManager();
        String packageName = this.f450b.getPackageName();
        int length = this.f449a.length;
        for (int i = 0; i < length; i++) {
            iArr[i] = packageManager.checkPermission(this.f449a[i], packageName);
        }
        ((C0183b.C0184a) this.f450b).mo746a(this.f451c, this.f449a, iArr);
    }
}
