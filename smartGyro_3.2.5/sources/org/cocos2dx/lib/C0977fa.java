package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.fa */
class C0977fa implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2650a;

    /* renamed from: b */
    final /* synthetic */ float f2651b;

    /* renamed from: c */
    final /* synthetic */ float f2652c;

    /* renamed from: d */
    final /* synthetic */ Cocos2dxGLSurfaceView f2653d;

    C0977fa(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView, int i, float f, float f2) {
        this.f2653d = cocos2dxGLSurfaceView;
        this.f2650a = i;
        this.f2651b = f;
        this.f2652c = f2;
    }

    public void run() {
        this.f2653d.mCocos2dxRenderer.handleActionUp(this.f2650a, this.f2651b, this.f2652c);
    }
}
