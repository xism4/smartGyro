package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.S */
class C0950S implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int[] f2580a;

    /* renamed from: b */
    final /* synthetic */ float[] f2581b;

    /* renamed from: c */
    final /* synthetic */ float[] f2582c;

    /* renamed from: d */
    final /* synthetic */ Cocos2dxGLSurfaceView f2583d;

    C0950S(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView, int[] iArr, float[] fArr, float[] fArr2) {
        this.f2583d = cocos2dxGLSurfaceView;
        this.f2580a = iArr;
        this.f2581b = fArr;
        this.f2582c = fArr2;
    }

    public void run() {
        this.f2583d.mCocos2dxRenderer.handleActionCancel(this.f2580a, this.f2581b, this.f2582c);
    }
}
