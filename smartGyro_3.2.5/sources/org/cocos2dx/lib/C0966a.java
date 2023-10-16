package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.a */
class C0966a implements Runnable {

    /* renamed from: a */
    final /* synthetic */ boolean f2627a;

    /* renamed from: b */
    final /* synthetic */ Cocos2dxActivity f2628b;

    C0966a(Cocos2dxActivity cocos2dxActivity, boolean z) {
        this.f2628b = cocos2dxActivity;
        this.f2627a = z;
    }

    public void run() {
        this.f2628b.mGLSurfaceView.setKeepScreenOn(this.f2627a);
    }
}
