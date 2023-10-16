package org.cocos2dx.package_1;

import android.util.SparseArray;
import android.webkit.WebSettings;
import android.webkit.WebView;

final class Label
  implements Runnable
{
  Label(int paramInt, boolean paramBoolean, String paramString) {}
  
  public void run()
  {
    Cocos2dxWebView localCocos2dxWebView = (Cocos2dxWebView)Cocos2dxWebViewHelper.access$200().get(text);
    if (localCocos2dxWebView != null)
    {
      WebSettings localWebSettings = localCocos2dxWebView.getSettings();
      int i;
      if (c) {
        i = 2;
      } else {
        i = -1;
      }
      localWebSettings.setCacheMode(i);
      localCocos2dxWebView.loadUrl(a);
    }
  }
}
