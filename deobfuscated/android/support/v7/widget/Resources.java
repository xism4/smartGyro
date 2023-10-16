package android.support.v7.widget;

import android.view.View;
import android.view.ViewParent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

class Resources
{
  static InputConnection validate(InputConnection paramInputConnection, EditorInfo paramEditorInfo, View paramView)
  {
    if ((paramInputConnection != null) && (hintText == null)) {
      for (paramView = paramView.getParent(); (paramView instanceof View); paramView = paramView.getParent()) {
        if ((paramView instanceof Field))
        {
          hintText = ((Field)paramView).getMessage();
          return paramInputConnection;
        }
      }
    }
    return paramInputConnection;
  }
}
