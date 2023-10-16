package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.v7.widget.Label;
import android.support.v7.widget.ListPopupWindow;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import com.org.android.view.ViewCompat;
import com.org.v4.util.R.dimen;
import com.org.v4.util.R.layout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class f
  extends d
  implements MenuPresenter, View.OnKeyListener, PopupWindow.OnDismissListener
{
  private static final int p = R.layout.abc_cascading_menu_item_layout;
  private final int A;
  final Handler B;
  private PopupWindow.OnDismissListener D;
  private final Context a;
  private boolean b;
  final List<i.a> c = new ArrayList();
  private l.a d;
  private boolean e;
  private final List<l> f = new ArrayList();
  private int g;
  private android.view.View h;
  boolean i;
  private int j;
  private int k = 0;
  private boolean l;
  private final int m;
  private final int n;
  private final android.support.v7.widget.Object o = new c(this);
  private int r;
  private final boolean s;
  final ViewTreeObserver.OnGlobalLayoutListener t = new AmbilWarnaDialog.6(this);
  android.view.View this$0;
  private final View.OnAttachStateChangeListener v = new MenuPopupHelper(this);
  ViewTreeObserver x;
  private int y = 0;
  private boolean z;
  
  public f(Context paramContext, android.view.View paramView, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    a = paramContext;
    h = paramView;
    m = paramInt1;
    n = paramInt2;
    s = paramBoolean;
    b = false;
    r = b();
    paramContext = paramContext.getResources();
    A = Math.max(getDisplayMetricswidthPixels / 2, paramContext.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
    B = new Handler();
  }
  
  private Label a()
  {
    Label localLabel = new Label(a, null, m, n);
    localLabel.a(o);
    localLabel.setOnItemClickListener(this);
    localLabel.setOnDismissListener(this);
    localLabel.a(h);
    localLabel.a(y);
    localLabel.dismiss(true);
    localLabel.show(2);
    return localLabel;
  }
  
  private MenuItem a(MenuBuilder paramMenuBuilder1, MenuBuilder paramMenuBuilder2)
  {
    int i2 = paramMenuBuilder1.size();
    int i1 = 0;
    while (i1 < i2)
    {
      MenuItem localMenuItem = paramMenuBuilder1.getItem(i1);
      if ((localMenuItem.hasSubMenu()) && (paramMenuBuilder2 == localMenuItem.getSubMenu())) {
        return localMenuItem;
      }
      i1 += 1;
    }
    return null;
  }
  
  private android.view.View a(e paramE, MenuBuilder paramMenuBuilder)
  {
    paramMenuBuilder = a(c, paramMenuBuilder);
    if (paramMenuBuilder == null) {
      return null;
    }
    ListView localListView = paramE.get();
    paramE = localListView.getAdapter();
    boolean bool = paramE instanceof HeaderViewListAdapter;
    int i1 = 0;
    int i2;
    if (bool)
    {
      paramE = (HeaderViewListAdapter)paramE;
      i2 = paramE.getHeadersCount();
      paramE = (e.a)paramE.getWrappedAdapter();
    }
    else
    {
      paramE = (e.a)paramE;
      i2 = 0;
    }
    int i3 = paramE.getCount();
    while (i1 < i3)
    {
      if (paramMenuBuilder == paramE.getItem(i1)) {
        break label104;
      }
      i1 += 1;
    }
    i1 = -1;
    label104:
    if (i1 == -1) {
      return null;
    }
    i1 = i1 + i2 - localListView.getFirstVisiblePosition();
    if (i1 >= 0)
    {
      if (i1 >= localListView.getChildCount()) {
        return null;
      }
      return localListView.getChildAt(i1);
    }
    return null;
  }
  
  private void a(MenuBuilder paramMenuBuilder)
  {
    Object localObject3 = LayoutInflater.from(a);
    Object localObject1 = new e.a(paramMenuBuilder, (LayoutInflater)localObject3, s, p);
    if ((!isShowing()) && (b)) {
      ((e.a)localObject1).a(true);
    } else if (isShowing()) {
      ((e.a)localObject1).a(d.onSubMenuSelected(paramMenuBuilder));
    }
    int i2 = d.measureContentWidth((ListAdapter)localObject1, null, a, A);
    int i1 = i2;
    Label localLabel = a();
    localLabel.setAdapter((ListAdapter)localObject1);
    localLabel.setContentWidth(i2);
    localLabel.a(y);
    if (c.size() > 0)
    {
      localObject1 = c;
      localObject1 = (e)((List)localObject1).get(((List)localObject1).size() - 1);
      localObject2 = a((e)localObject1, paramMenuBuilder);
    }
    else
    {
      localObject1 = null;
      localObject2 = null;
    }
    if (localObject2 != null)
    {
      localLabel.a(false);
      localLabel.init(null);
      i2 = add(i2);
      int i3;
      if (i2 == 1) {
        i3 = 1;
      } else {
        i3 = 0;
      }
      r = i2;
      int i4;
      if (Build.VERSION.SDK_INT >= 26)
      {
        localLabel.a((android.view.View)localObject2);
        i2 = 0;
        i4 = 0;
      }
      else
      {
        int[] arrayOfInt1 = new int[2];
        h.getLocationOnScreen(arrayOfInt1);
        int[] arrayOfInt2 = new int[2];
        ((android.view.View)localObject2).getLocationOnScreen(arrayOfInt2);
        if ((y & 0x7) == 5)
        {
          arrayOfInt1[0] += h.getWidth();
          arrayOfInt2[0] += ((android.view.View)localObject2).getWidth();
        }
        i4 = arrayOfInt2[0] - arrayOfInt1[0];
        i2 = arrayOfInt2[1] - arrayOfInt1[1];
      }
      if ((y & 0x5) == 5)
      {
        if (i3 == 0)
        {
          i1 = ((android.view.View)localObject2).getWidth();
          break label368;
        }
      }
      else
      {
        if (i3 == 0) {
          break label368;
        }
        i1 = ((android.view.View)localObject2).getWidth();
      }
      i1 = i4 + i1;
      break label373;
      label368:
      i1 = i4 - i1;
      label373:
      localLabel.setAdapter(i1);
      localLabel.show(true);
      localLabel.setVerticalOffset(i2);
    }
    else
    {
      if (e) {
        localLabel.setAdapter(g);
      }
      if (l) {
        localLabel.setVerticalOffset(j);
      }
      localLabel.setAdapter(c());
    }
    Object localObject2 = new e(localLabel, paramMenuBuilder, r);
    c.add(localObject2);
    localLabel.show();
    localObject2 = localLabel.add();
    ((android.view.View)localObject2).setOnKeyListener(this);
    if ((localObject1 == null) && (z) && (paramMenuBuilder.getValue() != null))
    {
      localObject1 = (FrameLayout)((LayoutInflater)localObject3).inflate(R.layout.abc_popup_menu_header_item_layout, (ViewGroup)localObject2, false);
      localObject3 = (TextView)((android.view.View)localObject1).findViewById(16908310);
      ((android.view.View)localObject1).setEnabled(false);
      ((TextView)localObject3).setText(paramMenuBuilder.getValue());
      ((ListView)localObject2).addHeaderView((android.view.View)localObject1, null, false);
      localLabel.show();
    }
  }
  
  private int add(int paramInt)
  {
    Object localObject = c;
    localObject = ((e)((List)localObject).get(((List)localObject).size() - 1)).get();
    int[] arrayOfInt = new int[2];
    ((android.view.View)localObject).getLocationOnScreen(arrayOfInt);
    Rect localRect = new Rect();
    this$0.getWindowVisibleDisplayFrame(localRect);
    if (r == 1)
    {
      if (arrayOfInt[0] + ((android.view.View)localObject).getWidth() + paramInt > right) {
        return 0;
      }
      return 1;
    }
    if (arrayOfInt[0] - paramInt < 0) {
      return 1;
    }
    return 0;
  }
  
  private int b()
  {
    if (ViewCompat.getLayoutDirection(h) == 1) {
      return 0;
    }
    return 1;
  }
  
  private int b(MenuBuilder paramMenuBuilder)
  {
    int i2 = c.size();
    int i1 = 0;
    while (i1 < i2)
    {
      if (paramMenuBuilder == c.get(i1)).c) {
        return i1;
      }
      i1 += 1;
    }
    return -1;
  }
  
  public void a(int paramInt)
  {
    if (k != paramInt)
    {
      k = paramInt;
      y = com.org.android.view.View.getAbsoluteGravity(paramInt, ViewCompat.getLayoutDirection(h));
    }
  }
  
  public void a(MenuBuilder paramMenuBuilder, boolean paramBoolean)
  {
    int i1 = b(paramMenuBuilder);
    if (i1 < 0) {
      return;
    }
    int i2 = i1 + 1;
    if (i2 < c.size()) {
      c.get(i2)).c.close(false);
    }
    Object localObject = (e)c.remove(i1);
    c.removeMenuPresenter(this);
    if (i)
    {
      this$0.show(null);
      this$0.dismiss(0);
    }
    this$0.dismiss();
    i2 = c.size();
    if (i2 > 0) {
      i1 = c.get(i2 - 1)).d;
    } else {
      i1 = b();
    }
    r = i1;
    if (i2 == 0)
    {
      dismiss();
      localObject = d;
      if (localObject != null) {
        ((l.a)localObject).onCloseMenu(paramMenuBuilder, true);
      }
      paramMenuBuilder = x;
      if (paramMenuBuilder != null)
      {
        if (paramMenuBuilder.isAlive()) {
          x.removeGlobalOnLayoutListener(t);
        }
        x = null;
      }
      this$0.removeOnAttachStateChangeListener(v);
      D.onDismiss();
      return;
    }
    if (paramBoolean) {
      c.get(0)).c.close(false);
    }
  }
  
  public void a(l.a paramA)
  {
    d = paramA;
  }
  
  public void a(android.view.View paramView)
  {
    if (h != paramView)
    {
      h = paramView;
      y = com.org.android.view.View.getAbsoluteGravity(k, ViewCompat.getLayoutDirection(h));
    }
  }
  
  public void a(PopupWindow.OnDismissListener paramOnDismissListener)
  {
    D = paramOnDismissListener;
  }
  
  public void a(boolean paramBoolean)
  {
    z = paramBoolean;
  }
  
  public boolean a(SubMenuBuilder paramSubMenuBuilder)
  {
    Object localObject = c.iterator();
    while (((Iterator)localObject).hasNext())
    {
      e localE = (e)((Iterator)localObject).next();
      if (paramSubMenuBuilder == c)
      {
        localE.get().requestFocus();
        return true;
      }
    }
    if (paramSubMenuBuilder.hasVisibleItems())
    {
      add(paramSubMenuBuilder);
      localObject = d;
      if (localObject != null)
      {
        ((l.a)localObject).onOpenSubMenu(paramSubMenuBuilder);
        return true;
      }
    }
    else
    {
      return false;
    }
    return true;
  }
  
  public ListView add()
  {
    if (c.isEmpty()) {
      return null;
    }
    List localList = c;
    return ((e)localList.get(localList.size() - 1)).get();
  }
  
  public void add(MenuBuilder paramMenuBuilder)
  {
    paramMenuBuilder.addMenuPresenter(this, a);
    if (isShowing())
    {
      a(paramMenuBuilder);
      return;
    }
    f.add(paramMenuBuilder);
  }
  
  public void b(int paramInt)
  {
    l = true;
    j = paramInt;
  }
  
  public void b(boolean paramBoolean)
  {
    b = paramBoolean;
  }
  
  public void c(int paramInt)
  {
    e = true;
    g = paramInt;
  }
  
  public void dismiss()
  {
    int i1 = c.size();
    if (i1 > 0)
    {
      e[] arrayOfE = (e[])c.toArray(new e[i1]);
      i1 -= 1;
      while (i1 >= 0)
      {
        e localE = arrayOfE[i1];
        if (this$0.isShowing()) {
          this$0.dismiss();
        }
        i1 -= 1;
      }
    }
  }
  
  public boolean flagActionItems()
  {
    return false;
  }
  
  protected boolean g()
  {
    return false;
  }
  
  public boolean isShowing()
  {
    return (c.size() > 0) && (c.get(0)).this$0.isShowing());
  }
  
  public void onDismiss()
  {
    int i2 = c.size();
    int i1 = 0;
    while (i1 < i2)
    {
      localE = (e)c.get(i1);
      if (!this$0.isShowing()) {
        break label53;
      }
      i1 += 1;
    }
    e localE = null;
    label53:
    if (localE != null) {
      c.close(false);
    }
  }
  
  public boolean onKey(android.view.View paramView, int paramInt, KeyEvent paramKeyEvent)
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
    if (isShowing()) {
      return;
    }
    Iterator localIterator = f.iterator();
    while (localIterator.hasNext()) {
      a((MenuBuilder)localIterator.next());
    }
    f.clear();
    this$0 = h;
    if (this$0 != null)
    {
      int i1;
      if (x == null) {
        i1 = 1;
      } else {
        i1 = 0;
      }
      x = this$0.getViewTreeObserver();
      if (i1 != 0) {
        x.addOnGlobalLayoutListener(t);
      }
      this$0.addOnAttachStateChangeListener(v);
    }
  }
  
  public void updateMenuView(boolean paramBoolean)
  {
    Iterator localIterator = c.iterator();
    while (localIterator.hasNext()) {
      d.a(((e)localIterator.next()).get().getAdapter()).notifyDataSetChanged();
    }
  }
}
