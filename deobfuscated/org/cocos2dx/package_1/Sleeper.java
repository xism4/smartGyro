package org.cocos2dx.package_1;

import android.util.SparseArray;
import android.webkit.WebView;

final class Sleeper
  implements Runnable
{
  Sleeper(int paramInt) {}
  
  public void run()
  {
    Cocos2dxWebView localCocos2dxWebView = (Cocos2dxWebView)Cocos2dxWebViewHelper.access$200().get(mSleepTime);
    if (localCocos2dxWebView != null) {
      localCocos2dxWebView.goBack();
    }
  }
}
