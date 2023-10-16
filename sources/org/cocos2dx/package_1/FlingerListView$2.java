package org.cocos2dx.package_1;

class FlingerListView$2 implements Runnable {
    final /* synthetic */ float[] a;
    final /* synthetic */ float[] b;
    final /* synthetic */ int[] c;
    final /* synthetic */ Cocos2dxGLSurfaceView this$0;

    FlingerListView$2(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView, int[] iArr, float[] fArr, float[] fArr2) {
        this.this$0 = cocos2dxGLSurfaceView;
        this.c = iArr;
        this.a = fArr;
        this.b = fArr2;
    }

    public void run() {
        this.this$0.mCocos2dxRenderer.handleActionMove(this.c, this.a, this.b);
    }
}
