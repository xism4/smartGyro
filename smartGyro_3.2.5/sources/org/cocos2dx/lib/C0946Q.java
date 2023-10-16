package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.Q */
class C0946Q implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int[] f2567a;

    /* renamed from: b */
    final /* synthetic */ float[] f2568b;

    /* renamed from: c */
    final /* synthetic */ float[] f2569c;

    /* renamed from: d */
    final /* synthetic */ Cocos2dxGLSurfaceView f2570d;

    C0946Q(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView, int[] iArr, float[] fArr, float[] fArr2) {
        this.f2570d = cocos2dxGLSurfaceView;
        this.f2567a = iArr;
        this.f2568b = fArr;
        this.f2569c = fArr2;
    }

    public void run() {
        this.f2570d.mCocos2dxRenderer.handleActionCancel(this.f2567a, this.f2568b, this.f2569c);
    }
}
