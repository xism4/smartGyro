package org.cocos2dx.package_1;

final class e implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ int k;
    final /* synthetic */ String l;

    e(int i, String str, String str2, String str3, String str4) {
        this.k = i;
        this.c = str;
        this.l = str2;
        this.a = str3;
        this.b = str4;
    }

    public void run() {
        Cocos2dxWebView $r7 = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.k);
        if ($r7 != null) {
            $r7.loadDataWithBaseURL(this.c, this.l, this.a, this.b, (String) null);
        }
    }
}
