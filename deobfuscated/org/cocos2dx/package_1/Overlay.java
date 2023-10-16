package org.cocos2dx.package_1;

import android.util.SparseArray;
import android.view.View;

final class Overlay
  implements Runnable
{
  Overlay(int paramInt, boolean paramBoolean) {}
  
  public void run()
  {
    Cocos2dxEditBox localCocos2dxEditBox = (Cocos2dxEditBox)Cocos2dxEditBoxHelper.access$400().get(c);
    if (localCocos2dxEditBox != null)
    {
      int i;
      if (b) {
        i = 0;
      } else {
        i = 8;
      }
      localCocos2dxEditBox.setVisibility(i);
    }
  }
}
