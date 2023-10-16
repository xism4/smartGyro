package org.cocos2dx.package_1;

class h implements Runnable {
    final /* synthetic */ Cocos2dxGLSurfaceView a;
    final /* synthetic */ int c;
    final /* synthetic */ float s;
    final /* synthetic */ float t;

    h(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView, int i, float f, float f2) {
        this.a = cocos2dxGLSurfaceView;
        this.c = i;
        this.t = f;
        this.s = f2;
    }

    public void run() {
        this.a.mCocos2dxRenderer.handleActionDown(this.c, this.t, this.s);
    }
}
