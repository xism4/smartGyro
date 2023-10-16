package org.cocos2dx.package_1;

class Replay implements Runnable {
    final /* synthetic */ Cocos2dxGLSurfaceView count;

    Replay(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView) {
        this.count = cocos2dxGLSurfaceView;
    }

    public void run() {
        this.count.mCocos2dxRenderer.handleOnPause();
    }
}
