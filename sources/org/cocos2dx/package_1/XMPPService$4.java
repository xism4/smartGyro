package org.cocos2dx.package_1;

class XMPPService$4 implements Runnable {
    final /* synthetic */ Cocos2dxGLSurfaceView this$0;
    final /* synthetic */ String val$u;

    XMPPService$4(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView, String str) {
        this.this$0 = cocos2dxGLSurfaceView;
        this.val$u = str;
    }

    public void run() {
        this.this$0.mCocos2dxRenderer.handleInsertText(this.val$u);
    }
}
