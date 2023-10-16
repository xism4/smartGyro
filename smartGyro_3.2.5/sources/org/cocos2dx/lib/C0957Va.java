package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.Va */
class C0957Va implements Runnable {

    /* renamed from: a */
    final /* synthetic */ String f2599a;

    /* renamed from: b */
    final /* synthetic */ int f2600b;

    /* renamed from: c */
    final /* synthetic */ int f2601c;

    /* renamed from: d */
    final /* synthetic */ boolean f2602d;

    /* renamed from: e */
    final /* synthetic */ float f2603e;

    /* renamed from: f */
    final /* synthetic */ boolean f2604f;

    C0957Va(String str, int i, int i2, boolean z, float f, boolean z2) {
        this.f2599a = str;
        this.f2600b = i;
        this.f2601c = i2;
        this.f2602d = z;
        this.f2603e = f;
        this.f2604f = z2;
    }

    public void run() {
        GameControllerAdapter.nativeControllerButtonEvent(this.f2599a, this.f2600b, this.f2601c, this.f2602d, this.f2603e, this.f2604f);
    }
}
