package org.cocos2dx.package_1;

class MainActivity$14 implements Runnable {
    final /* synthetic */ Cocos2dxGLSurfaceView a;
    final /* synthetic */ int b;

    MainActivity$14(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView, int i) {
        this.a = cocos2dxGLSurfaceView;
        this.b = i;
    }

    public void run() {
        this.a.mCocos2dxRenderer.handleKeyUp(this.b);
    }
}
