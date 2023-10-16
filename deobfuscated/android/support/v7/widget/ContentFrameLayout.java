package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.org.android.view.ViewCompat;

public class ContentFrameLayout
  extends FrameLayout
{
  private a mAttachListener;
  private final Rect mDecorPadding = new Rect();
  private TypedValue mFixedHeightMajor;
  private TypedValue mFixedHeightMinor;
  private TypedValue mFixedWidthMajor;
  private TypedValue mFixedWidthMinor;
  private TypedValue mMinWidthMajor;
  private TypedValue mMinWidthMinor;
  
  public ContentFrameLayout(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public ContentFrameLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public ContentFrameLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public void dispatchFitSystemWindows(Rect paramRect)
  {
    fitSystemWindows(paramRect);
  }
  
  public TypedValue getFixedHeightMajor()
  {
    if (mFixedHeightMajor == null) {
      mFixedHeightMajor = new TypedValue();
    }
    return mFixedHeightMajor;
  }
  
  public TypedValue getFixedHeightMinor()
  {
    if (mFixedHeightMinor == null) {
      mFixedHeightMinor = new TypedValue();
    }
    return mFixedHeightMinor;
  }
  
  public TypedValue getFixedWidthMajor()
  {
    if (mFixedWidthMajor == null) {
      mFixedWidthMajor = new TypedValue();
    }
    return mFixedWidthMajor;
  }
  
  public TypedValue getFixedWidthMinor()
  {
    if (mFixedWidthMinor == null) {
      mFixedWidthMinor = new TypedValue();
    }
    return mFixedWidthMinor;
  }
  
  public TypedValue getMinWidthMajor()
  {
    if (mMinWidthMajor == null) {
      mMinWidthMajor = new TypedValue();
    }
    return mMinWidthMajor;
  }
  
  public TypedValue getMinWidthMinor()
  {
    if (mMinWidthMinor == null) {
      mMinWidthMinor = new TypedValue();
    }
    return mMinWidthMinor;
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    a localA = mAttachListener;
    if (localA != null) {
      localA.onAttachedFromWindow();
    }
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    a localA = mAttachListener;
    if (localA != null) {
      localA.onDetachedFromWindow();
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    DisplayMetrics localDisplayMetrics = getContext().getResources().getDisplayMetrics();
    int i = widthPixels;
    int j = heightPixels;
    int n = 1;
    if (i < j) {
      i = 1;
    } else {
      i = 0;
    }
    int i1 = View.MeasureSpec.getMode(paramInt1);
    int m = View.MeasureSpec.getMode(paramInt2);
    Object localObject;
    float f;
    if (i1 == Integer.MIN_VALUE)
    {
      if (i != 0) {
        localObject = mFixedWidthMinor;
      } else {
        localObject = mFixedWidthMajor;
      }
      if (localObject != null)
      {
        j = type;
        if (j != 0)
        {
          if (j == 5) {}
          for (f = ((TypedValue)localObject).getDimension(localDisplayMetrics);; f = ((TypedValue)localObject).getFraction(j, j))
          {
            j = (int)f;
            break label154;
            if (j != 6) {
              break;
            }
            j = widthPixels;
          }
          j = 0;
          label154:
          if (j > 0)
          {
            localObject = mDecorPadding;
            k = View.MeasureSpec.makeMeasureSpec(Math.min(j - (left + right), View.MeasureSpec.getSize(paramInt1)), 1073741824);
            paramInt1 = 1;
            break label207;
          }
        }
      }
    }
    j = 0;
    int k = paramInt1;
    paramInt1 = j;
    label207:
    j = paramInt2;
    if (m == Integer.MIN_VALUE)
    {
      if (i != 0) {
        localObject = mFixedHeightMajor;
      } else {
        localObject = mFixedHeightMinor;
      }
      j = paramInt2;
      if (localObject != null)
      {
        m = type;
        j = paramInt2;
        if (m != 0)
        {
          if (m == 5) {}
          for (f = ((TypedValue)localObject).getDimension(localDisplayMetrics);; f = ((TypedValue)localObject).getFraction(j, j))
          {
            m = (int)f;
            break label313;
            if (m != 6) {
              break;
            }
            j = heightPixels;
          }
          m = 0;
          label313:
          j = paramInt2;
          if (m > 0)
          {
            localObject = mDecorPadding;
            j = View.MeasureSpec.makeMeasureSpec(Math.min(m - (top + bottom), View.MeasureSpec.getSize(paramInt2)), 1073741824);
          }
        }
      }
    }
    super.onMeasure(k, j);
    m = getMeasuredWidth();
    k = View.MeasureSpec.makeMeasureSpec(m, 1073741824);
    if ((paramInt1 == 0) && (i1 == Integer.MIN_VALUE))
    {
      if (i != 0) {
        localObject = mMinWidthMinor;
      } else {
        localObject = mMinWidthMajor;
      }
      if (localObject != null)
      {
        paramInt1 = type;
        if (paramInt1 != 0)
        {
          if (paramInt1 == 5) {}
          for (f = ((TypedValue)localObject).getDimension(localDisplayMetrics);; f = ((TypedValue)localObject).getFraction(paramInt1, paramInt1))
          {
            paramInt1 = (int)f;
            break label470;
            if (paramInt1 != 6) {
              break;
            }
            paramInt1 = widthPixels;
          }
          paramInt1 = 0;
          label470:
          paramInt2 = paramInt1;
          if (paramInt1 > 0)
          {
            localObject = mDecorPadding;
            paramInt2 = paramInt1 - (left + right);
          }
          if (m < paramInt2)
          {
            paramInt1 = View.MeasureSpec.makeMeasureSpec(paramInt2, 1073741824);
            paramInt2 = n;
            break label520;
          }
        }
      }
    }
    paramInt2 = 0;
    paramInt1 = k;
    label520:
    if (paramInt2 != 0) {
      super.onMeasure(paramInt1, j);
    }
  }
  
  public void setAttachListener(a paramA)
  {
    mAttachListener = paramA;
  }
  
  public void setDecorPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    mDecorPadding.set(paramInt1, paramInt2, paramInt3, paramInt4);
    if (ViewCompat.show(this)) {
      requestLayout();
    }
  }
  
  public static abstract interface a
  {
    public abstract void onAttachedFromWindow();
    
    public abstract void onDetachedFromWindow();
  }
}
