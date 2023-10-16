package org.cocos2dx.package_1;

import android.util.SparseArray;
import android.webkit.WebView;

final class b
  implements Runnable
{
  b(int paramInt, String paramString1, String paramString2) {}
  
  public void run()
  {
    Cocos2dxWebView localCocos2dxWebView = (Cocos2dxWebView)Cocos2dxWebViewHelper.access$200().get(k);
    if (localCocos2dxWebView != null) {
      localCocos2dxWebView.loadDataWithBaseURL(a, b, null, null, null);
    }
  }
}
