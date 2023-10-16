package org.cocos2dx.package_1;

class FlingerListView$3 implements Runnable {
    final /* synthetic */ float a;
    final /* synthetic */ float b;
    final /* synthetic */ int c;
    final /* synthetic */ Cocos2dxGLSurfaceView this$0;

    FlingerListView$3(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView, int i, float f, float f2) {
        this.this$0 = cocos2dxGLSurfaceView;
        this.c = i;
        this.a = f;
        this.b = f2;
    }

    public void run() {
        this.this$0.mCocos2dxRenderer.handleActionUp(this.c, this.a, this.b);
    }
}
