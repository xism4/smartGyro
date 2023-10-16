package org.cocos2dx.package_1;

class WalletActivity$1 implements Runnable {
    final /* synthetic */ Cocos2dxGLSurfaceView this$0;

    WalletActivity$1(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView) {
        this.this$0 = cocos2dxGLSurfaceView;
    }

    public void run() {
        this.this$0.mCocos2dxRenderer.handleOnResume();
    }
}
