package org.cocos2dx.package_1;

import android.content.Context;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import org.cocos2dx.lib.Cocos2dxWebViewHelper;

public class Cocos2dxWebView extends WebView {
    /* access modifiers changed from: private */
    public static final String TAG = Cocos2dxWebViewHelper.class.getSimpleName();
    /* access modifiers changed from: private */
    public String mJSScheme;
    /* access modifiers changed from: private */
    public int mViewTag;

    class a extends WebViewClient {
        a() {
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            ((Cocos2dxActivity) Cocos2dxWebView.this.getContext()).runOnGLThread(new CordovaWebViewClient$1(this, str));
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            ((Cocos2dxActivity) Cocos2dxWebView.this.getContext()).runOnGLThread(new DroidGap$3(this, str2));
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Cocos2dxActivity $r8 = (Cocos2dxActivity) Cocos2dxWebView.this.getContext();
            try {
                URI $r9 = URI.create(str);
                if ($r9 != null) {
                    if ($r9.getScheme().equals(Cocos2dxWebView.this.mJSScheme)) {
                        $r8.runOnGLThread(new Progress(this, str));
                        return true;
                    }
                }
            } catch (Exception e) {
                Log.d(Cocos2dxWebView.TAG, "Failed to create URI from url");
            }
            boolean[] $r13 = {true};
            CountDownLatch countDownLatch = new CountDownLatch(1);
            $r8.runOnGLThread(new l(countDownLatch, $r13, Cocos2dxWebView.this.mViewTag, str));
            try {
                countDownLatch.await();
            } catch (InterruptedException e2) {
                Log.d(Cocos2dxWebView.TAG, "'shouldOverrideUrlLoading' failed");
            }
            return $r13[0];
        }
    }

    public Cocos2dxWebView(Context context) {
        this(context, -1);
    }

    public Cocos2dxWebView(Context context, int i) {
        super(context);
        this.mViewTag = i;
        this.mJSScheme = "";
        setFocusable(true);
        setFocusableInTouchMode(true);
        getSettings().setSupportZoom(false);
        getSettings().setDomStorageEnabled(true);
        getSettings().setJavaScriptEnabled(true);
        try {
            getClass().getMethod("removeJavascriptInterface", new Class[]{String.class}).invoke(this, new Object[]{"searchBoxJavaBridge_"});
        } catch (Exception e) {
            Log.d(TAG, "This API level do not support `removeJavascriptInterface`");
        }
        setWebViewClient(new a());
        setWebChromeClient(new WebChromeClient());
        setOnKeyListener(new Settings$1(this));
    }

    public static native void KeyDownBack();

    public void setJavascriptInterfaceScheme(String $r1) {
        if ($r1 == null) {
            $r1 = "";
        }
        this.mJSScheme = $r1;
    }

    public void setScalesPageToFit(boolean z) {
        getSettings().setSupportZoom(z);
    }

    public void setWebViewRect(int i, int i2, int i3, int i4) {
        FrameLayout.LayoutParams $r1 = new FrameLayout.LayoutParams(-2, -2);
        $r1.leftMargin = i;
        $r1.topMargin = i2;
        $r1.width = i3;
        $r1.height = i4;
        $r1.gravity = 51;
        setLayoutParams($r1);
    }
}
