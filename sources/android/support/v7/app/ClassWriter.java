package android.support.v7.app;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;

final class ClassWriter {
    final /* synthetic */ AppCompatDelegateImplV7 a;
    private IntentFilter b;
    private TwilightManager f;
    private boolean h;
    private BroadcastReceiver x;

    ClassWriter(AppCompatDelegateImplV7 appCompatDelegateImplV7, TwilightManager twilightManager) {
        this.a = appCompatDelegateImplV7;
        this.f = twilightManager;
        this.h = twilightManager.isNight();
    }

    /* access modifiers changed from: package-private */
    public int a() {
        this.h = this.f.isNight();
        return this.h ? 2 : 1;
    }

    /* access modifiers changed from: package-private */
    public void b() {
        boolean $z1 = this.f.isNight();
        if ($z1 != this.h) {
            this.h = $z1;
            this.a.a();
        }
    }

    /* access modifiers changed from: package-private */
    public void c() {
        BroadcastReceiver $r1 = this.x;
        if ($r1 != null) {
            this.a.mContext.unregisterReceiver($r1);
            this.x = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void init() {
        c();
        if (this.x == null) {
            this.x = new ConnectionsManager$2(this);
        }
        if (this.b == null) {
            this.b = new IntentFilter();
            this.b.addAction("android.intent.action.TIME_SET");
            this.b.addAction("android.intent.action.TIMEZONE_CHANGED");
            this.b.addAction("android.intent.action.TIME_TICK");
        }
        this.a.mContext.registerReceiver(this.x, this.b);
    }
}
