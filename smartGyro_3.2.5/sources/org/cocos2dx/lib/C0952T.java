package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.T */
class C0952T implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2589a;

    /* renamed from: b */
    final /* synthetic */ Cocos2dxGLSurfaceView f2590b;

    C0952T(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView, int i) {
        this.f2590b = cocos2dxGLSurfaceView;
        this.f2589a = i;
    }

    public void run() {
        this.f2590b.mCocos2dxRenderer.handleKeyDown(this.f2589a);
    }
}
