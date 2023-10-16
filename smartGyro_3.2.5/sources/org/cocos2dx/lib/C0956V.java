package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.V */
class C0956V implements Runnable {

    /* renamed from: a */
    final /* synthetic */ String f2597a;

    /* renamed from: b */
    final /* synthetic */ Cocos2dxGLSurfaceView f2598b;

    C0956V(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView, String str) {
        this.f2598b = cocos2dxGLSurfaceView;
        this.f2597a = str;
    }

    public void run() {
        this.f2598b.mCocos2dxRenderer.handleInsertText(this.f2597a);
    }
}
