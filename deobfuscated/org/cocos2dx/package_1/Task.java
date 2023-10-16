package org.cocos2dx.package_1;

import android.util.SparseArray;
import android.webkit.WebView;

final class Task
  implements Runnable
{
  Task(int paramInt, String paramString) {}
  
  public void run()
  {
    Cocos2dxWebView localCocos2dxWebView = (Cocos2dxWebView)Cocos2dxWebViewHelper.access$200().get(source);
    if (localCocos2dxWebView != null) {
      localCocos2dxWebView.loadUrl(url);
    }
  }
}
