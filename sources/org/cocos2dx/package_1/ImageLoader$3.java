package org.cocos2dx.package_1;

class ImageLoader$3 implements Runnable {
    final /* synthetic */ Cocos2dxGLSurfaceView val$holder;

    ImageLoader$3(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView) {
        this.val$holder = cocos2dxGLSurfaceView;
    }

    public void run() {
        this.val$holder.mCocos2dxRenderer.handleDeleteBackward();
    }
}
