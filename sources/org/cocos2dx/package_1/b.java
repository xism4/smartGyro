package org.cocos2dx.package_1;

final class b implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ int k;

    b(int i, String str, String str2) {
        this.k = i;
        this.a = str;
        this.b = str2;
    }

    public void run() {
        Cocos2dxWebView $r5 = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.k);
        if ($r5 != null) {
            $r5.loadDataWithBaseURL(this.a, this.b, (String) null, (String) null, (String) null);
        }
    }
}
