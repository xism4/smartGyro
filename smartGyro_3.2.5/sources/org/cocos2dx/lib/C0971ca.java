package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.ca */
class C0971ca implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2636a;

    /* renamed from: b */
    final /* synthetic */ float f2637b;

    /* renamed from: c */
    final /* synthetic */ float f2638c;

    /* renamed from: d */
    final /* synthetic */ Cocos2dxGLSurfaceView f2639d;

    C0971ca(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView, int i, float f, float f2) {
        this.f2639d = cocos2dxGLSurfaceView;
        this.f2636a = i;
        this.f2637b = f;
        this.f2638c = f2;
    }

    public void run() {
        this.f2639d.mCocos2dxRenderer.handleActionDown(this.f2636a, this.f2637b, this.f2638c);
    }
}
