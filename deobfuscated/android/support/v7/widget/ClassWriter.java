package android.support.v7.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnHoverListener;
import android.view.View.OnLongClickListener;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;
import com.org.android.view.Type;
import com.org.android.view.ViewCompat;

class ClassWriter
  implements View.OnLongClickListener, View.OnHoverListener, View.OnAttachStateChangeListener
{
  private static ClassWriter a;
  private static ClassWriter b;
  private final Runnable c = new AgendaListView.2(this);
  private final Runnable e = new MonthListView.1(this);
  private final View h;
  private int i;
  private int j;
  private final CharSequence k;
  private final int l;
  private a v;
  private boolean w;
  
  private ClassWriter(View paramView, CharSequence paramCharSequence)
  {
    h = paramView;
    k = paramCharSequence;
    l = Type.getSize(ViewConfiguration.get(h.getContext()));
    b();
    h.setOnLongClickListener(this);
    h.setOnHoverListener(this);
  }
  
  private static void a(ClassWriter paramClassWriter)
  {
    ClassWriter localClassWriter = b;
    if (localClassWriter != null) {
      localClassWriter.c();
    }
    b = paramClassWriter;
    paramClassWriter = b;
    if (paramClassWriter != null) {
      paramClassWriter.init();
    }
  }
  
  public static void a(View paramView, CharSequence paramCharSequence)
  {
    ClassWriter localClassWriter = b;
    if ((localClassWriter != null) && (h == paramView)) {
      a(null);
    }
    if (TextUtils.isEmpty(paramCharSequence))
    {
      paramCharSequence = a;
      if ((paramCharSequence != null) && (h == paramView)) {
        paramCharSequence.a();
      }
      paramView.setOnLongClickListener(null);
      paramView.setLongClickable(false);
      paramView.setOnHoverListener(null);
      return;
    }
    new ClassWriter(paramView, paramCharSequence);
  }
  
  private boolean a(MotionEvent paramMotionEvent)
  {
    int m = (int)paramMotionEvent.getX();
    int n = (int)paramMotionEvent.getY();
    if ((Math.abs(m - i) <= l) && (Math.abs(n - j) <= l)) {
      return false;
    }
    i = m;
    j = n;
    return true;
  }
  
  private void b()
  {
    i = Integer.MAX_VALUE;
    j = Integer.MAX_VALUE;
  }
  
  private void c()
  {
    h.removeCallbacks(e);
  }
  
  private void init()
  {
    h.postDelayed(e, ViewConfiguration.getLongPressTimeout());
  }
  
  void a()
  {
    if (a == this)
    {
      a = null;
      a localA = v;
      if (localA != null)
      {
        localA.a();
        v = null;
        b();
        h.removeOnAttachStateChangeListener(this);
      }
      else
      {
        Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
      }
    }
    if (b == this) {
      a(null);
    }
    h.removeCallbacks(c);
  }
  
  void a(boolean paramBoolean)
  {
    if (!ViewCompat.isAttachedToWindow(h)) {
      return;
    }
    a(null);
    ClassWriter localClassWriter = a;
    if (localClassWriter != null) {
      localClassWriter.a();
    }
    a = this;
    w = paramBoolean;
    v = new a(h.getContext());
    v.a(h, i, j, w, k);
    h.addOnAttachStateChangeListener(this);
    long l1;
    if (w)
    {
      l1 = 2500L;
    }
    else
    {
      if ((ViewCompat.a(h) & 0x1) == 1) {
        l1 = 3000L;
      } else {
        l1 = 15000L;
      }
      l1 -= ViewConfiguration.getLongPressTimeout();
    }
    h.removeCallbacks(c);
    h.postDelayed(c, l1);
  }
  
  public boolean onHover(View paramView, MotionEvent paramMotionEvent)
  {
    if ((v != null) && (w)) {
      return false;
    }
    paramView = (AccessibilityManager)h.getContext().getSystemService("accessibility");
    if ((paramView.isEnabled()) && (paramView.isTouchExplorationEnabled())) {
      return false;
    }
    int m = paramMotionEvent.getAction();
    if (m != 7)
    {
      if (m != 10) {
        return false;
      }
      b();
      a();
      return false;
    }
    if ((h.isEnabled()) && (v == null) && (a(paramMotionEvent))) {
      a(this);
    }
    return false;
  }
  
  public boolean onLongClick(View paramView)
  {
    i = (paramView.getWidth() / 2);
    j = (paramView.getHeight() / 2);
    a(true);
    return true;
  }
  
  public void onViewAttachedToWindow(View paramView) {}
  
  public void onViewDetachedFromWindow(View paramView)
  {
    a();
  }
}
