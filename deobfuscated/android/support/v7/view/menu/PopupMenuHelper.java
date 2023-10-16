package android.support.v7.view.menu;

import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewTreeObserver;

class PopupMenuHelper
  implements View.OnAttachStateChangeListener
{
  PopupMenuHelper(i paramI) {}
  
  public void onViewAttachedToWindow(View paramView) {}
  
  public void onViewDetachedFromWindow(View paramView)
  {
    Object localObject = mMenu.mTreeObserver;
    if (localObject != null)
    {
      if (!((ViewTreeObserver)localObject).isAlive()) {
        mMenu.mTreeObserver = paramView.getViewTreeObserver();
      }
      localObject = mMenu;
      mTreeObserver.removeGlobalOnLayoutListener(w);
    }
    paramView.removeOnAttachStateChangeListener(this);
  }
}
