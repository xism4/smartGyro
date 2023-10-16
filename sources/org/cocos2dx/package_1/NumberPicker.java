package org.cocos2dx.package_1;

class NumberPicker implements Runnable {
    final /* synthetic */ float[] a;
    final /* synthetic */ float[] d;
    final /* synthetic */ Cocos2dxGLSurfaceView i;
    final /* synthetic */ int[] x;

    final class BeginSoftInputOnLongPressCommand implements Runnable {
        final /* synthetic */ int a;

        BeginSoftInputOnLongPressCommand(int i) {
            this.a = i;
        }

        public void run() {
            Cocos2dxWebView $r3 = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.a);
            if ($r3 != null) {
                $r3.goForward();
            }
        }
    }

    class PressedStateHelper implements Runnable {
        final /* synthetic */ Cocos2dxGLSurfaceView a;
        final /* synthetic */ float b;
        final /* synthetic */ int c;
        final /* synthetic */ float d;

        PressedStateHelper(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView, int i, float f, float f2) {
            this.a = cocos2dxGLSurfaceView;
            this.c = i;
            this.b = f;
            this.d = f2;
        }

        public void run() {
            this.a.mCocos2dxRenderer.handleActionUp(this.c, this.b, this.d);
        }
    }

    NumberPicker(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView, int[] iArr, float[] fArr, float[] fArr2) {
        this.i = cocos2dxGLSurfaceView;
        this.x = iArr;
        this.d = fArr;
        this.a = fArr2;
    }

    public void run() {
        this.i.mCocos2dxRenderer.handleActionMove(this.x, this.d, this.a);
    }
}
