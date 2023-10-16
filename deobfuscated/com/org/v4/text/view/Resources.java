package com.org.v4.text.view;

import a.a.d.b.a.a.a;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v7.widget.AppCompatDrawableManager;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import com.org.android.ui.asm.Handler;
import java.util.WeakHashMap;

public final class Resources
{
  private static final ThreadLocal<TypedValue> TL_TYPED_VALUE = new ThreadLocal();
  private static final WeakHashMap<Context, SparseArray<a.a>> c = new WeakHashMap(0);
  private static final Object mutex = new Object();
  
  private static ColorStateList add(Context paramContext, int paramInt)
  {
    Object localObject = mutex;
    try
    {
      SparseArray localSparseArray = (SparseArray)c.get(paramContext);
      if ((localSparseArray != null) && (localSparseArray.size() > 0))
      {
        Item localItem = (Item)localSparseArray.get(paramInt);
        if (localItem != null)
        {
          if (d.equals(paramContext.getResources().getConfiguration()))
          {
            paramContext = c;
            return paramContext;
          }
          localSparseArray.remove(paramInt);
        }
      }
      return null;
    }
    catch (Throwable paramContext)
    {
      throw paramContext;
    }
  }
  
  private static void add(Context paramContext, int paramInt, ColorStateList paramColorStateList)
  {
    Object localObject = mutex;
    try
    {
      SparseArray localSparseArray2 = (SparseArray)c.get(paramContext);
      SparseArray localSparseArray1 = localSparseArray2;
      if (localSparseArray2 == null)
      {
        localSparseArray1 = new SparseArray();
        c.put(paramContext, localSparseArray1);
      }
      localSparseArray1.append(paramInt, new Item(paramColorStateList, paramContext.getResources().getConfiguration()));
      return;
    }
    catch (Throwable paramContext)
    {
      throw paramContext;
    }
  }
  
  private static ColorStateList create(Context paramContext, int paramInt)
  {
    if (validate(paramContext, paramInt)) {
      return null;
    }
    android.content.res.Resources localResources = paramContext.getResources();
    XmlResourceParser localXmlResourceParser = localResources.getXml(paramInt);
    try
    {
      paramContext = Handler.create(localResources, localXmlResourceParser, paramContext.getTheme());
      return paramContext;
    }
    catch (Exception paramContext)
    {
      Log.e("AppCompatResources", "Failed to inflate ColorStateList, leaving it to the framework", paramContext);
    }
    return null;
  }
  
  public static Drawable getDrawable(Context paramContext, int paramInt)
  {
    return AppCompatDrawableManager.get().getDrawable(paramContext, paramInt);
  }
  
  private static TypedValue getTypedValue()
  {
    TypedValue localTypedValue2 = (TypedValue)TL_TYPED_VALUE.get();
    TypedValue localTypedValue1 = localTypedValue2;
    if (localTypedValue2 == null)
    {
      localTypedValue1 = new TypedValue();
      TL_TYPED_VALUE.set(localTypedValue1);
    }
    return localTypedValue1;
  }
  
  public static ColorStateList show(Context paramContext, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 23) {
      return paramContext.getColorStateList(paramInt);
    }
    ColorStateList localColorStateList = add(paramContext, paramInt);
    if (localColorStateList != null) {
      return localColorStateList;
    }
    localColorStateList = create(paramContext, paramInt);
    if (localColorStateList != null)
    {
      add(paramContext, paramInt, localColorStateList);
      return localColorStateList;
    }
    return com.org.android.ui.Resources.getColorStateList(paramContext, paramInt);
  }
  
  private static boolean validate(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getResources();
    TypedValue localTypedValue = getTypedValue();
    paramContext.getValue(paramInt, localTypedValue, true);
    paramInt = type;
    return (paramInt >= 28) && (paramInt <= 31);
  }
}
