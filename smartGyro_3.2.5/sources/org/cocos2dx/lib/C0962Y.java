package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.Y */
class C0962Y implements Runnable {

    /* renamed from: a */
    final /* synthetic */ float f2617a;

    /* renamed from: b */
    final /* synthetic */ float f2618b;

    /* renamed from: c */
    final /* synthetic */ float f2619c;

    /* renamed from: d */
    final /* synthetic */ long f2620d;

    C0962Y(float f, float f2, float f3, long j) {
        this.f2617a = f;
        this.f2618b = f2;
        this.f2619c = f3;
        this.f2620d = j;
    }

    public void run() {
        Cocos2dxAccelerometer.onSensorChanged(this.f2617a, this.f2618b, this.f2619c, this.f2620d);
    }
}
