package org.cocos2dx.package_1;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout.LayoutParams;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import org.cocos2dx.lib.Cocos2dxWebViewHelper;

public class Cocos2dxWebView
  extends WebView
{
  private static final String TAG = Cocos2dxWebViewHelper.class.getSimpleName();
  private String mJSScheme;
  private int mViewTag;
  
  public Cocos2dxWebView(Context paramContext)
  {
    this(paramContext, -1);
  }
  
  public Cocos2dxWebView(Context paramContext, int paramInt)
  {
    super(paramContext);
    mViewTag = paramInt;
    mJSScheme = "";
    setFocusable(true);
    setFocusableInTouchMode(true);
    getSettings().setSupportZoom(false);
    getSettings().setDomStorageEnabled(true);
    getSettings().setJavaScriptEnabled(true);
    try
    {
      paramContext = getClass();
      paramContext = paramContext.getMethod("removeJavascriptInterface", new Class[] { String.class });
      paramContext.invoke(this, new Object[] { "searchBoxJavaBridge_" });
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    Log.d(TAG, "This API level do not support `removeJavascriptInterface`");
    setWebViewClient(new a());
    setWebChromeClient(new WebChromeClient());
    setOnKeyListener(new Settings.1(this));
  }
  
  public static native void KeyDownBack();
  
  public void setJavascriptInterfaceScheme(String paramString)
  {
    if (paramString == null) {
      paramString = "";
    }
    mJSScheme = paramString;
  }
  
  public void setScalesPageToFit(boolean paramBoolean)
  {
    getSettings().setSupportZoom(paramBoolean);
  }
  
  public void setWebViewRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(-2, -2);
    leftMargin = paramInt1;
    topMargin = paramInt2;
    width = paramInt3;
    height = paramInt4;
    gravity = 51;
    setLayoutParams(localLayoutParams);
  }
  
  class a
    extends WebViewClient
  {
    a() {}
    
    public void onPageFinished(WebView paramWebView, String paramString)
    {
      super.onPageFinished(paramWebView, paramString);
      ((Cocos2dxActivity)getContext()).runOnGLThread(new CordovaWebViewClient.1(this, paramString));
    }
    
    public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
    {
      super.onReceivedError(paramWebView, paramInt, paramString1, paramString2);
      ((Cocos2dxActivity)getContext()).runOnGLThread(new DroidGap.3(this, paramString2));
    }
    
    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      paramWebView = (Cocos2dxActivity)getContext();
      try
      {
        localObject1 = URI.create(paramString);
        if (localObject1 == null) {
          break label74;
        }
        localObject1 = ((URI)localObject1).getScheme();
        localObject2 = Cocos2dxWebView.this;
        boolean bool = ((String)localObject1).equals(mJSScheme);
        if (!bool) {
          break label74;
        }
        paramWebView.runOnGLThread(new Progress(this, paramString));
        return true;
      }
      catch (Exception localException)
      {
        Object localObject1;
        Object localObject2;
        label74:
        for (;;) {}
      }
      Log.d(Cocos2dxWebView.TAG, "Failed to create URI from url");
      localObject1 = new boolean[1];
      localObject1[0] = 1;
      localObject2 = new CountDownLatch(1);
      paramWebView.runOnGLThread(new l((CountDownLatch)localObject2, (boolean[])localObject1, mViewTag, paramString));
      try
      {
        ((CountDownLatch)localObject2).await();
      }
      catch (InterruptedException paramWebView)
      {
        for (;;) {}
      }
      Log.d(Cocos2dxWebView.TAG, "'shouldOverrideUrlLoading' failed");
      return localObject1[0];
    }
  }
}
