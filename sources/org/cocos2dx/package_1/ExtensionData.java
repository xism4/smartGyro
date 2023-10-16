package org.cocos2dx.package_1;

final class ExtensionData implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ int b;
    final /* synthetic */ int c;
    final /* synthetic */ int d;
    final /* synthetic */ int e;

    ExtensionData(int i, int i2, int i3, int i4, int i5) {
        this.a = i;
        this.c = i2;
        this.b = i3;
        this.d = i4;
        this.e = i5;
    }

    public void run() {
        Cocos2dxWebView $r3 = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.a);
        if ($r3 != null) {
            $r3.setWebViewRect(this.c, this.b, this.d, this.e);
        }
    }
}
