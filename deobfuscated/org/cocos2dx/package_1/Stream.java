package org.cocos2dx.package_1;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;

final class Stream
  implements Runnable
{
  Stream(float paramFloat, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {}
  
  public void run()
  {
    Cocos2dxEditBox localCocos2dxEditBox = new Cocos2dxEditBox(Cocos2dxEditBoxHelper.access$000());
    localCocos2dxEditBox.setFocusable(true);
    localCocos2dxEditBox.setFocusableInTouchMode(true);
    localCocos2dxEditBox.setInputFlag(5);
    localCocos2dxEditBox.setInputMode(6);
    localCocos2dxEditBox.setReturnType(0);
    localCocos2dxEditBox.setHintTextColor(-7829368);
    localCocos2dxEditBox.setVisibility(8);
    localCocos2dxEditBox.setBackgroundColor(0);
    localCocos2dxEditBox.setTextColor(-1);
    localCocos2dxEditBox.setSingleLine();
    localCocos2dxEditBox.setOpenGLViewScaleX(mLevel);
    localCocos2dxEditBox.setPadding(Cocos2dxEditBoxHelper.getPadding(mLevel), 0, 0, 0);
    FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(-2, -2);
    leftMargin = mOffset;
    topMargin = mType;
    width = mValue;
    height = mColor;
    gravity = 51;
    Cocos2dxEditBoxHelper.access$100().addView(localCocos2dxEditBox, localLayoutParams);
    localCocos2dxEditBox.setTag(Boolean.valueOf(false));
    localCocos2dxEditBox.addTextChangedListener(new MainActivity.2(this, localCocos2dxEditBox));
    localCocos2dxEditBox.setOnFocusChangeListener(new Folder(this, localCocos2dxEditBox));
    localCocos2dxEditBox.setOnKeyListener(new MainActivity.6(this, localCocos2dxEditBox));
    localCocos2dxEditBox.setOnEditorActionListener(new MainActivity.1(this, localCocos2dxEditBox));
    Cocos2dxEditBoxHelper.access$400().put(this$0, localCocos2dxEditBox);
  }
}
