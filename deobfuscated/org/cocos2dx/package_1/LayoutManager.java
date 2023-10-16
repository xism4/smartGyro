package org.cocos2dx.package_1;

import android.util.SparseArray;

final class LayoutManager
  implements Runnable
{
  LayoutManager(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {}
  
  public void run()
  {
    Cocos2dxEditBox localCocos2dxEditBox = (Cocos2dxEditBox)Cocos2dxEditBoxHelper.access$400().get(a);
    if (localCocos2dxEditBox != null) {
      localCocos2dxEditBox.setEditBoxViewRect(c, b, d, e);
    }
  }
}
