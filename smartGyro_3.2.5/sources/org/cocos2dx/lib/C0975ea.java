package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.ea */
class C0975ea implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int[] f2646a;

    /* renamed from: b */
    final /* synthetic */ float[] f2647b;

    /* renamed from: c */
    final /* synthetic */ float[] f2648c;

    /* renamed from: d */
    final /* synthetic */ Cocos2dxGLSurfaceView f2649d;

    C0975ea(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView, int[] iArr, float[] fArr, float[] fArr2) {
        this.f2649d = cocos2dxGLSurfaceView;
        this.f2646a = iArr;
        this.f2647b = fArr;
        this.f2648c = fArr2;
    }

    public void run() {
        this.f2649d.mCocos2dxRenderer.handleActionMove(this.f2646a, this.f2647b, this.f2648c);
    }
}
