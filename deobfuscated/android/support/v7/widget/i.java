package android.support.v7.widget;

import android.content.Context;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.h;
import android.view.View;
import com.org.v4.util.R.attr;

class i
  extends h
{
  public i(ActionMenuPresenter paramActionMenuPresenter, Context paramContext, MenuBuilder paramMenuBuilder, View paramView, boolean paramBoolean)
  {
    super(paramContext, paramMenuBuilder, paramView, paramBoolean, R.attr.actionOverflowMenuStyle);
    b(8388613);
    a(mPopupPresenterCallback);
  }
  
  protected void b()
  {
    if (ActionMenuPresenter.onCloseMenu(b) != null) {
      ActionMenuPresenter.inflate(b).close();
    }
    b.mOverflowPopup = null;
    super.b();
  }
}
