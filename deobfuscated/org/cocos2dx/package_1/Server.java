package org.cocos2dx.package_1;

import android.util.SparseArray;
import android.webkit.WebView;

final class Server
  implements Runnable
{
  Server(int paramInt, String paramString) {}
  
  public void run()
  {
    Cocos2dxWebView localCocos2dxWebView = (Cocos2dxWebView)Cocos2dxWebViewHelper.access$200().get(version);
    if (localCocos2dxWebView != null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("javascript:");
      localStringBuilder.append(title);
      localCocos2dxWebView.loadUrl(localStringBuilder.toString());
    }
  }
}
