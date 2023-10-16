package org.cocos2dx.package_1;

import android.util.Log;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

class Folder
  implements View.OnFocusChangeListener
{
  Folder(Stream paramStream, Cocos2dxEditBox paramCocos2dxEditBox) {}
  
  public void onFocusChange(View paramView, boolean paramBoolean)
  {
    text.setTag(Boolean.valueOf(true));
    text.setChangedTextProgrammatically(Boolean.valueOf(false));
    String str;
    if (paramBoolean)
    {
      Cocos2dxEditBoxHelper.access$000().runOnGLThread(new MonthByWeekFragment.2(this));
      paramView = text;
      paramView.setSelection(paramView.getText().length());
      Cocos2dxEditBoxHelper.access$100().setEnableForceDoLayout(true);
      Cocos2dxEditBoxHelper.access$000().getGLSurfaceView().setSoftKeyboardShown(true);
      paramView = Cocos2dxEditBoxHelper.access$200();
      str = "edit box get focus";
    }
    else
    {
      text.setVisibility(8);
      paramView = new String(text.getText().toString());
      Cocos2dxEditBoxHelper.access$000().runOnGLThread(new MainActivity.3(this, paramView));
      Cocos2dxEditBoxHelper.access$000().hideVirtualButton();
      Cocos2dxEditBoxHelper.access$100().setEnableForceDoLayout(false);
      paramView = Cocos2dxEditBoxHelper.access$200();
      str = "edit box lose focus";
    }
    Log.d(paramView, str);
  }
}
