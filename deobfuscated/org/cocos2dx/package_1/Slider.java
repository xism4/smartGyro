package org.cocos2dx.package_1;

import android.util.SparseArray;
import android.widget.TextView;

final class Slider
  implements Runnable
{
  Slider(int paramInt, String paramString) {}
  
  public void run()
  {
    Cocos2dxEditBox localCocos2dxEditBox = (Cocos2dxEditBox)Cocos2dxEditBoxHelper.access$400().get(y);
    if (localCocos2dxEditBox != null) {
      localCocos2dxEditBox.setHint(h);
    }
  }
}
