package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.ba */
class C0969ba implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2631a;

    /* renamed from: b */
    final /* synthetic */ float f2632b;

    /* renamed from: c */
    final /* synthetic */ float f2633c;

    /* renamed from: d */
    final /* synthetic */ Cocos2dxGLSurfaceView f2634d;

    C0969ba(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView, int i, float f, float f2) {
        this.f2634d = cocos2dxGLSurfaceView;
        this.f2631a = i;
        this.f2632b = f;
        this.f2633c = f2;
    }

    public void run() {
        this.f2634d.mCocos2dxRenderer.handleActionDown(this.f2631a, this.f2632b, this.f2633c);
    }
}
