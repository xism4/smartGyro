package org.cocos2dx.package_1;

import android.util.SparseArray;

final class ExtensionData
  implements Runnable
{
  ExtensionData(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {}
  
  public void run()
  {
    Cocos2dxWebView localCocos2dxWebView = (Cocos2dxWebView)Cocos2dxWebViewHelper.access$200().get(a);
    if (localCocos2dxWebView != null) {
      localCocos2dxWebView.setWebViewRect(c, b, d, e);
    }
  }
}
