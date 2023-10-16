package android.support.v7.widget;

import android.content.Context;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.SubMenuBuilder;
import android.support.v7.view.menu.h;
import android.view.View;
import com.org.v4.util.R.attr;

class XYPlot
  extends h
{
  public XYPlot(ActionMenuPresenter paramActionMenuPresenter, Context paramContext, SubMenuBuilder paramSubMenuBuilder, View paramView)
  {
    super(paramContext, paramSubMenuBuilder, paramView, false, R.attr.actionOverflowMenuStyle);
    if (!((MenuItemImpl)paramSubMenuBuilder.getItem()).isActionButton())
    {
      paramSubMenuBuilder = mOverflowButton;
      paramContext = paramSubMenuBuilder;
      if (paramSubMenuBuilder == null) {
        paramContext = (View)ActionMenuPresenter.getMenuView(paramActionMenuPresenter);
      }
      a(paramContext);
    }
    a(mPopupPresenterCallback);
  }
  
  protected void b()
  {
    ActionMenuPresenter localActionMenuPresenter = K;
    a = null;
    e = 0;
    super.b();
  }
}
