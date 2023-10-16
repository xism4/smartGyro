package com.org.android.view;

import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.org.provider.R.id;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.WeakHashMap;

class f
{
  private static final ArrayList<WeakReference<View>> m = new ArrayList();
  private WeakReference<KeyEvent> a = null;
  private WeakHashMap<View, Boolean> b = null;
  private SparseArray<WeakReference<View>> f = null;
  
  f() {}
  
  static f a(View paramView)
  {
    f localF2 = (f)paramView.getTag(R.id.tag_unhandled_key_event_manager);
    f localF1 = localF2;
    if (localF2 == null)
    {
      localF1 = new f();
      paramView.setTag(R.id.tag_unhandled_key_event_manager, localF1);
    }
    return localF1;
  }
  
  private void a()
  {
    Object localObject = b;
    if (localObject != null) {
      ((WeakHashMap)localObject).clear();
    }
    if (m.isEmpty()) {
      return;
    }
    ArrayList localArrayList = m;
    for (;;)
    {
      int i;
      try
      {
        if (b == null) {
          b = new WeakHashMap();
        }
        i = m.size() - 1;
        if (i >= 0)
        {
          localObject = (View)((WeakReference)m.get(i)).get();
          if (localObject == null)
          {
            m.remove(i);
          }
          else
          {
            b.put(localObject, Boolean.TRUE);
            localObject = ((View)localObject).getParent();
            if ((localObject instanceof View))
            {
              b.put((View)localObject, Boolean.TRUE);
              localObject = ((ViewParent)localObject).getParent();
              continue;
            }
          }
        }
        else
        {
          return;
        }
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
      i -= 1;
    }
  }
  
  private View b(View paramView, KeyEvent paramKeyEvent)
  {
    Object localObject = b;
    if (localObject != null)
    {
      if (!((WeakHashMap)localObject).containsKey(paramView)) {
        return null;
      }
      if ((paramView instanceof ViewGroup))
      {
        localObject = (ViewGroup)paramView;
        int i = ((ViewGroup)localObject).getChildCount() - 1;
        while (i >= 0)
        {
          View localView = b(((ViewGroup)localObject).getChildAt(i), paramKeyEvent);
          if (localView != null) {
            return localView;
          }
          i -= 1;
        }
      }
      if (c(paramView, paramKeyEvent)) {
        return paramView;
      }
    }
    return null;
  }
  
  private boolean c(View paramView, KeyEvent paramKeyEvent)
  {
    ArrayList localArrayList = (ArrayList)paramView.getTag(R.id.tag_unhandled_key_listeners);
    if (localArrayList != null)
    {
      int i = localArrayList.size() - 1;
      while (i >= 0)
      {
        if (((l)localArrayList.get(i)).onUnhandledKeyEvent(paramView, paramKeyEvent)) {
          return true;
        }
        i -= 1;
      }
    }
    return false;
  }
  
  private SparseArray d()
  {
    if (f == null) {
      f = new SparseArray();
    }
    return f;
  }
  
  boolean a(KeyEvent paramKeyEvent)
  {
    Object localObject1 = a;
    if ((localObject1 != null) && (((WeakReference)localObject1).get() == paramKeyEvent)) {
      return false;
    }
    a = new WeakReference(paramKeyEvent);
    Object localObject2 = null;
    SparseArray localSparseArray = d();
    localObject1 = localObject2;
    if (paramKeyEvent.getAction() == 1)
    {
      int i = localSparseArray.indexOfKey(paramKeyEvent.getKeyCode());
      localObject1 = localObject2;
      if (i >= 0)
      {
        localObject1 = (WeakReference)localSparseArray.valueAt(i);
        localSparseArray.removeAt(i);
      }
    }
    localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = (WeakReference)localSparseArray.get(paramKeyEvent.getKeyCode());
    }
    if (localObject2 != null)
    {
      localObject1 = (View)((WeakReference)localObject2).get();
      if ((localObject1 != null) && (ViewCompat.isAttachedToWindow((View)localObject1)))
      {
        c((View)localObject1, paramKeyEvent);
        return true;
      }
    }
    else
    {
      return false;
    }
    return true;
  }
  
  boolean a(View paramView, KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent.getAction() == 0) {
      a();
    }
    paramView = b(paramView, paramKeyEvent);
    if (paramKeyEvent.getAction() == 0)
    {
      int i = paramKeyEvent.getKeyCode();
      if ((paramView != null) && (!KeyEvent.isModifierKey(i))) {
        d().put(i, new WeakReference(paramView));
      }
    }
    return paramView != null;
  }
}
