package android.support.v7.view.menu;

import android.support.v7.widget.ListPopupWindow;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

class a
  implements ViewTreeObserver.OnGlobalLayoutListener
{
  a(i paramI) {}
  
  public void onGlobalLayout()
  {
    if ((a.isShowing()) && (!a.a.isModal()))
    {
      View localView = a.v;
      if ((localView != null) && (localView.isShown()))
      {
        a.a.show();
        return;
      }
      a.dismiss();
    }
  }
}
