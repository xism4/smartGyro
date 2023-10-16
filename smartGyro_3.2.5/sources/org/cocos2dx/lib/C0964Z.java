package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.Z */
class C0964Z implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Cocos2dxGLSurfaceView f2622a;

    C0964Z(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView) {
        this.f2622a = cocos2dxGLSurfaceView;
    }

    public void run() {
        this.f2622a.mCocos2dxRenderer.handleOnResume();
    }
}
