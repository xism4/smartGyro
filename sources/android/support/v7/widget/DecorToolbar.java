package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.view.menu.l$a;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window;
import com.org.android.view.ViewPropertyAnimatorCompat;

public interface DecorToolbar {
    boolean canShowOverflowMenu();

    void collapseActionView();

    void dismissPopupMenus();

    Context getContext();

    int getDisplayOptions();

    int getNavigationMode();

    CharSequence getTitle();

    ViewGroup getViewGroup();

    boolean hasExpandedActionView();

    boolean hideOverflowMenu();

    void initIndeterminateProgress();

    void initProgress();

    boolean isOverflowMenuShowPending();

    boolean isOverflowMenuShowing();

    void setCollapsible(boolean z);

    void setDisplayOptions(int i);

    void setEmbeddedTabView(ScrollingTabContainerView scrollingTabContainerView);

    void setHomeButtonEnabled(boolean z);

    void setIcon(int i);

    void setIcon(Drawable drawable);

    void setLogo(int i);

    void setMenu(Menu menu, l$a l_a);

    void setMenuPrepared();

    void setVisibility(int i);

    void setWindowCallback(Window.Callback callback);

    void setWindowTitle(CharSequence charSequence);

    ViewPropertyAnimatorCompat setupAnimatorToVisibility(int i, long j);

    boolean showOverflowMenu();
}
