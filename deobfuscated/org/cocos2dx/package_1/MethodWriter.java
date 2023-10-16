package org.cocos2dx.package_1;

import android.graphics.Color;
import android.util.SparseArray;
import android.widget.TextView;

final class MethodWriter
  implements Runnable
{
  MethodWriter(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {}
  
  public void run()
  {
    Cocos2dxEditBox localCocos2dxEditBox = (Cocos2dxEditBox)Cocos2dxEditBoxHelper.access$400().get(c);
    if (localCocos2dxEditBox != null) {
      localCocos2dxEditBox.setHintTextColor(Color.argb(r, g, e, b));
    }
  }
}
