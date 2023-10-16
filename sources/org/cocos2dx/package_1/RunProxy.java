package org.cocos2dx.package_1;

class RunProxy implements Runnable {
    final /* synthetic */ float a;
    final /* synthetic */ float c;
    final /* synthetic */ Cocos2dxGLSurfaceView fileName;
    final /* synthetic */ int type;

    RunProxy(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView, int i, float f, float f2) {
        this.fileName = cocos2dxGLSurfaceView;
        this.type = i;
        this.a = f;
        this.c = f2;
    }

    public void run() {
        this.fileName.mCocos2dxRenderer.handleActionDown(this.type, this.a, this.c);
    }
}
