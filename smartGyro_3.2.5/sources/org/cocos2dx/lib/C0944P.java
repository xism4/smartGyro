package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.P */
class C0944P implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2558a;

    /* renamed from: b */
    final /* synthetic */ float f2559b;

    /* renamed from: c */
    final /* synthetic */ float f2560c;

    /* renamed from: d */
    final /* synthetic */ Cocos2dxGLSurfaceView f2561d;

    C0944P(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView, int i, float f, float f2) {
        this.f2561d = cocos2dxGLSurfaceView;
        this.f2558a = i;
        this.f2559b = f;
        this.f2560c = f2;
    }

    public void run() {
        this.f2561d.mCocos2dxRenderer.handleActionUp(this.f2558a, this.f2559b, this.f2560c);
    }
}
