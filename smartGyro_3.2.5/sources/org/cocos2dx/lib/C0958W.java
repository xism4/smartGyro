package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.W */
class C0958W implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Cocos2dxGLSurfaceView f2605a;

    C0958W(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView) {
        this.f2605a = cocos2dxGLSurfaceView;
    }

    public void run() {
        this.f2605a.mCocos2dxRenderer.handleDeleteBackward();
    }
}
