package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.Wa */
class C0959Wa implements Runnable {

    /* renamed from: a */
    final /* synthetic */ String f2606a;

    /* renamed from: b */
    final /* synthetic */ int f2607b;

    /* renamed from: c */
    final /* synthetic */ int f2608c;

    /* renamed from: d */
    final /* synthetic */ float f2609d;

    /* renamed from: e */
    final /* synthetic */ boolean f2610e;

    C0959Wa(String str, int i, int i2, float f, boolean z) {
        this.f2606a = str;
        this.f2607b = i;
        this.f2608c = i2;
        this.f2609d = f;
        this.f2610e = z;
    }

    public void run() {
        GameControllerAdapter.nativeControllerAxisEvent(this.f2606a, this.f2607b, this.f2608c, this.f2609d, this.f2610e);
    }
}
