package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.Label;
import android.support.v7.widget.ListPopupWindow;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import com.org.android.view.ViewCompat;
import com.org.v4.util.R.dimen;
import com.org.v4.util.R.layout;

final class i
  extends d
  implements PopupWindow.OnDismissListener, AdapterView.OnItemClickListener, MenuPresenter, View.OnKeyListener
{
  private static final int p = R.layout.abc_popup_menu_item_layout;
  final Label a;
  private PopupWindow.OnDismissListener b;
  private final Context c;
  private boolean d;
  private final boolean e;
  private final MenuBuilder f;
  private final e.a g;
  private final int h;
  private boolean i;
  private int j;
  private l.a k;
  private boolean l;
  ViewTreeObserver mTreeObserver;
  private final View.OnAttachStateChangeListener q = new PopupMenuHelper(this);
  private final int r;
  private final int t;
  private View u;
  View v;
  final ViewTreeObserver.OnGlobalLayoutListener w = new a(this);
  private int x = 0;
  
  public i(Context paramContext, MenuBuilder paramMenuBuilder, View paramView, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    c = paramContext;
    f = paramMenuBuilder;
    e = paramBoolean;
    g = new e.a(paramMenuBuilder, LayoutInflater.from(paramContext), e, p);
    t = paramInt1;
    h = paramInt2;
    Resources localResources = paramContext.getResources();
    r = Math.max(getDisplayMetricswidthPixels / 2, localResources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
    u = paramView;
    a = new Label(c, null, t, h);
    paramMenuBuilder.addMenuPresenter(this, paramContext);
  }
  
  private boolean a()
  {
    if (isShowing()) {
      return true;
    }
    if (!i)
    {
      Object localObject = u;
      if (localObject == null) {
        return false;
      }
      v = ((View)localObject);
      a.setOnDismissListener(this);
      a.setOnItemClickListener(this);
      a.dismiss(true);
      localObject = v;
      int m;
      if (mTreeObserver == null) {
        m = 1;
      } else {
        m = 0;
      }
      mTreeObserver = ((View)localObject).getViewTreeObserver();
      if (m != 0) {
        mTreeObserver.addOnGlobalLayoutListener(w);
      }
      ((View)localObject).addOnAttachStateChangeListener(q);
      a.a((View)localObject);
      a.a(x);
      if (!l)
      {
        j = d.measureContentWidth(g, null, c, r);
        l = true;
      }
      a.setContentWidth(j);
      a.show(2);
      a.setAdapter(c());
      a.show();
      localObject = a.add();
      ((View)localObject).setOnKeyListener(this);
      if ((d) && (f.getValue() != null))
      {
        FrameLayout localFrameLayout = (FrameLayout)LayoutInflater.from(c).inflate(R.layout.abc_popup_menu_header_item_layout, (ViewGroup)localObject, false);
        TextView localTextView = (TextView)localFrameLayout.findViewById(16908310);
        if (localTextView != null) {
          localTextView.setText(f.getValue());
        }
        localFrameLayout.setEnabled(false);
        ((ListView)localObject).addHeaderView(localFrameLayout, null, false);
      }
      a.setAdapter(g);
      a.show();
      return true;
    }
    return false;
  }
  
  public void a(int paramInt)
  {
    x = paramInt;
  }
  
  public void a(MenuBuilder paramMenuBuilder, boolean paramBoolean)
  {
    if (paramMenuBuilder != f) {
      return;
    }
    dismiss();
    l.a localA = k;
    if (localA != null) {
      localA.onCloseMenu(paramMenuBuilder, paramBoolean);
    }
  }
  
  public void a(l.a paramA)
  {
    k = paramA;
  }
  
  public void a(View paramView)
  {
    u = paramView;
  }
  
  public void a(PopupWindow.OnDismissListener paramOnDismissListener)
  {
    b = paramOnDismissListener;
  }
  
  public void a(boolean paramBoolean)
  {
    d = paramBoolean;
  }
  
  public boolean a(SubMenuBuilder paramSubMenuBuilder)
  {
    if (paramSubMenuBuilder.hasVisibleItems())
    {
      Object localObject = new h(c, paramSubMenuBuilder, v, e, t, h);
      ((h)localObject).a(k);
      ((h)localObject).a(d.onSubMenuSelected(paramSubMenuBuilder));
      ((h)localObject).b(b);
      b = null;
      f.close(false);
      int n = a.getHorizontalOffset();
      int m = n;
      int i1 = a.getVerticalOffset();
      if ((Gravity.getAbsoluteGravity(x, ViewCompat.getLayoutDirection(u)) & 0x7) == 5) {
        m = n + u.getWidth();
      }
      if (((h)localObject).b(m, i1))
      {
        localObject = k;
        if (localObject != null) {
          ((l.a)localObject).onOpenSubMenu(paramSubMenuBuilder);
        }
        return true;
      }
    }
    return false;
  }
  
  public ListView add()
  {
    return a.add();
  }
  
  public void add(MenuBuilder paramMenuBuilder) {}
  
  public void b(int paramInt)
  {
    a.setVerticalOffset(paramInt);
  }
  
  public void b(boolean paramBoolean)
  {
    g.a(paramBoolean);
  }
  
  public void c(int paramInt)
  {
    a.setAdapter(paramInt);
  }
  
  public void dismiss()
  {
    if (isShowing()) {
      a.dismiss();
    }
  }
  
  public boolean flagActionItems()
  {
    return false;
  }
  
  public boolean isShowing()
  {
    return (!i) && (a.isShowing());
  }
  
  public void onDismiss()
  {
    i = true;
    f.close();
    Object localObject = mTreeObserver;
    if (localObject != null)
    {
      if (!((ViewTreeObserver)localObject).isAlive()) {
        mTreeObserver = v.getViewTreeObserver();
      }
      mTreeObserver.removeGlobalOnLayoutListener(w);
      mTreeObserver = null;
    }
    v.removeOnAttachStateChangeListener(q);
    localObject = b;
    if (localObject != null) {
      ((PopupWindow.OnDismissListener)localObject).onDismiss();
    }
  }
  
  public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramKeyEvent.getAction() == 1) && (paramInt == 82))
    {
      dismiss();
      return true;
    }
    return false;
  }
  
  public void show()
  {
    if (a()) {
      return;
    }
    throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
  }
  
  public void updateMenuView(boolean paramBoolean)
  {
    l = false;
    e.a localA = g;
    if (localA != null) {
      localA.notifyDataSetChanged();
    }
  }
}
