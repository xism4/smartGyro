package org.cocos2dx.package_1;

class Session implements Runnable {
    final /* synthetic */ int lock;
    final /* synthetic */ Cocos2dxGLSurfaceView random;

    Session(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView, int i) {
        this.random = cocos2dxGLSurfaceView;
        this.lock = i;
    }

    public void run() {
        this.random.mCocos2dxRenderer.handleKeyDown(this.lock);
    }
}
