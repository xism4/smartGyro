package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.U */
class C0954U implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2593a;

    /* renamed from: b */
    final /* synthetic */ Cocos2dxGLSurfaceView f2594b;

    C0954U(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView, int i) {
        this.f2594b = cocos2dxGLSurfaceView;
        this.f2593a = i;
    }

    public void run() {
        this.f2594b.mCocos2dxRenderer.handleKeyUp(this.f2593a);
    }
}
