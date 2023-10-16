package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.da */
class C0973da implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int[] f2641a;

    /* renamed from: b */
    final /* synthetic */ float[] f2642b;

    /* renamed from: c */
    final /* synthetic */ float[] f2643c;

    /* renamed from: d */
    final /* synthetic */ Cocos2dxGLSurfaceView f2644d;

    C0973da(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView, int[] iArr, float[] fArr, float[] fArr2) {
        this.f2644d = cocos2dxGLSurfaceView;
        this.f2641a = iArr;
        this.f2642b = fArr;
        this.f2643c = fArr2;
    }

    public void run() {
        this.f2644d.mCocos2dxRenderer.handleActionMove(this.f2641a, this.f2642b, this.f2643c);
    }
}
