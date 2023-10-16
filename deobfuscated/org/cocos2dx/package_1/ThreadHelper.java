package org.cocos2dx.package_1;

import android.util.SparseArray;
import android.webkit.WebView;

final class ThreadHelper
  implements Runnable
{
  ThreadHelper(int paramInt) {}
  
  public void run()
  {
    Cocos2dxWebView localCocos2dxWebView = (Cocos2dxWebView)Cocos2dxWebViewHelper.access$200().get(time);
    if (localCocos2dxWebView != null) {
      localCocos2dxWebView.reload();
    }
  }
}
