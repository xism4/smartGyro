package org.cocos2dx.package_1;

import android.graphics.Paint;
import java.lang.reflect.Method;

final class InitiationListener$1 implements Runnable {
    final /* synthetic */ int k;

    InitiationListener$1(int i) {
        this.k = i;
    }

    public void run() {
        Cocos2dxWebView $r4 = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.k);
        if ($r4 != null) {
            $r4.setBackgroundColor(0);
            try {
                Method $r7 = $r4.getClass().getMethod("setLayerType", new Class[]{Integer.TYPE, Paint.class});
                Object[] $r8 = new Object[2];
                $r8[0] = 1;
                $r8[1] = null;
                $r7.invoke($r4, $r8);
            } catch (Exception $r10) {
                $r10.printStackTrace();
            }
        }
    }
}
