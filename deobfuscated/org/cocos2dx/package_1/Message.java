package org.cocos2dx.package_1;

import android.util.SparseArray;
import android.view.View;

final class Message
  implements Runnable
{
  Message(int paramInt, boolean paramBoolean) {}
  
  public void run()
  {
    Cocos2dxWebView localCocos2dxWebView = (Cocos2dxWebView)Cocos2dxWebViewHelper.access$200().get(id);
    if (localCocos2dxWebView != null)
    {
      int i;
      if (c) {
        i = 0;
      } else {
        i = 8;
      }
      localCocos2dxWebView.setVisibility(i);
    }
  }
}
