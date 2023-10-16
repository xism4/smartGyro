package com.org.android.core;

import android.os.Build.VERSION;
import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.MetricAffectingSpan;
import java.util.concurrent.Executor;

public class Segment
  implements Spannable
{
  private static final Object owner = new Object();
  private static Executor shared = null;
  private final Label b;
  private final Spannable editable;
  private final PrecomputedText mText;
  
  public PrecomputedText append()
  {
    Spannable localSpannable = editable;
    if ((localSpannable instanceof PrecomputedText)) {
      return (PrecomputedText)localSpannable;
    }
    return null;
  }
  
  public char charAt(int paramInt)
  {
    return editable.charAt(paramInt);
  }
  
  public int getSpanEnd(Object paramObject)
  {
    return editable.getSpanEnd(paramObject);
  }
  
  public int getSpanFlags(Object paramObject)
  {
    return editable.getSpanFlags(paramObject);
  }
  
  public int getSpanStart(Object paramObject)
  {
    return editable.getSpanStart(paramObject);
  }
  
  public Object[] getSpans(int paramInt1, int paramInt2, Class paramClass)
  {
    if (Build.VERSION.SDK_INT >= 28) {
      return mText.getSpans(paramInt1, paramInt2, paramClass);
    }
    return editable.getSpans(paramInt1, paramInt2, paramClass);
  }
  
  public Label getValue()
  {
    return b;
  }
  
  public int length()
  {
    return editable.length();
  }
  
  public int nextSpanTransition(int paramInt1, int paramInt2, Class paramClass)
  {
    return editable.nextSpanTransition(paramInt1, paramInt2, paramClass);
  }
  
  public void removeSpan(Object paramObject)
  {
    if (!(paramObject instanceof MetricAffectingSpan))
    {
      if (Build.VERSION.SDK_INT >= 28)
      {
        mText.removeSpan(paramObject);
        return;
      }
      editable.removeSpan(paramObject);
      return;
    }
    throw new IllegalArgumentException("MetricAffectingSpan can not be removed from PrecomputedText.");
  }
  
  public void setSpan(Object paramObject, int paramInt1, int paramInt2, int paramInt3)
  {
    if (!(paramObject instanceof MetricAffectingSpan))
    {
      if (Build.VERSION.SDK_INT >= 28)
      {
        mText.setSpan(paramObject, paramInt1, paramInt2, paramInt3);
        return;
      }
      editable.setSpan(paramObject, paramInt1, paramInt2, paramInt3);
      return;
    }
    throw new IllegalArgumentException("MetricAffectingSpan can not be set to PrecomputedText.");
  }
  
  public CharSequence subSequence(int paramInt1, int paramInt2)
  {
    return editable.subSequence(paramInt1, paramInt2);
  }
  
  public String toString()
  {
    return editable.toString();
  }
}
