package org.cocos2dx.package_1;

import java.lang.reflect.Method;

final class FileBrowser$10 implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ float b;

    FileBrowser$10(int i, float f) {
        this.a = i;
        this.b = f;
    }

    public void run() {
        Cocos2dxWebView $r5 = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.a);
        if ($r5 != null) {
            try {
                Method $r7 = $r5.getClass().getMethod("setAlpha", new Class[]{Float.TYPE});
                Object[] $r8 = new Object[1];
                $r8[0] = Float.valueOf(this.b);
                $r7.invoke($r5, $r8);
            } catch (Exception $r10) {
                $r10.printStackTrace();
            }
        }
    }
}
