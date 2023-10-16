package android.support.v7.view.menu;

import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewTreeObserver;

class MenuPopupHelper
  implements View.OnAttachStateChangeListener
{
  MenuPopupHelper(f paramF) {}
  
  public void onViewAttachedToWindow(View paramView) {}
  
  public void onViewDetachedFromWindow(View paramView)
  {
    Object localObject = mMenu.x;
    if (localObject != null)
    {
      if (!((ViewTreeObserver)localObject).isAlive()) {
        mMenu.x = paramView.getViewTreeObserver();
      }
      localObject = mMenu;
      x.removeGlobalOnLayoutListener(t);
    }
    paramView.removeOnAttachStateChangeListener(this);
  }
}
