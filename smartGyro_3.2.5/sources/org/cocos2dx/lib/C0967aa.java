package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.aa */
class C0967aa implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Cocos2dxGLSurfaceView f2629a;

    C0967aa(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView) {
        this.f2629a = cocos2dxGLSurfaceView;
    }

    public void run() {
        this.f2629a.mCocos2dxRenderer.handleOnPause();
    }
}
