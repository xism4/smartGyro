package org.cocos2dx.package_1;

class FlingerListView$4 implements Runnable {
    final /* synthetic */ float[] d;
    final /* synthetic */ Cocos2dxGLSurfaceView this$0;
    final /* synthetic */ float[] v;
    final /* synthetic */ int[] w;

    FlingerListView$4(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView, int[] iArr, float[] fArr, float[] fArr2) {
        this.this$0 = cocos2dxGLSurfaceView;
        this.w = iArr;
        this.v = fArr;
        this.d = fArr2;
    }

    public void run() {
        this.this$0.mCocos2dxRenderer.handleActionCancel(this.w, this.v, this.d);
    }
}
