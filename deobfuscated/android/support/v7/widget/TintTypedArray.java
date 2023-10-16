package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import com.org.android.ui.asm.ClassReader;
import com.org.android.ui.asm.k;
import com.org.v4.text.view.Resources;

public class TintTypedArray
{
  private final Context mContext;
  private final TypedArray mWrapped;
  private TypedValue this$0;
  
  private TintTypedArray(Context paramContext, TypedArray paramTypedArray)
  {
    mContext = paramContext;
    mWrapped = paramTypedArray;
  }
  
  public static TintTypedArray obtainStyledAttributes(Context paramContext, int paramInt, int[] paramArrayOfInt)
  {
    return new TintTypedArray(paramContext, paramContext.obtainStyledAttributes(paramInt, paramArrayOfInt));
  }
  
  public static TintTypedArray obtainStyledAttributes(Context paramContext, AttributeSet paramAttributeSet, int[] paramArrayOfInt)
  {
    return new TintTypedArray(paramContext, paramContext.obtainStyledAttributes(paramAttributeSet, paramArrayOfInt));
  }
  
  public static TintTypedArray obtainStyledAttributes(Context paramContext, AttributeSet paramAttributeSet, int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    return new TintTypedArray(paramContext, paramContext.obtainStyledAttributes(paramAttributeSet, paramArrayOfInt, paramInt1, paramInt2));
  }
  
  public boolean getBoolean(int paramInt, boolean paramBoolean)
  {
    return mWrapped.getBoolean(paramInt, paramBoolean);
  }
  
  public int getColor(int paramInt1, int paramInt2)
  {
    return mWrapped.getColor(paramInt1, paramInt2);
  }
  
  public ColorStateList getColorStateList(int paramInt)
  {
    if (mWrapped.hasValue(paramInt))
    {
      int i = mWrapped.getResourceId(paramInt, 0);
      if (i != 0)
      {
        ColorStateList localColorStateList = Resources.show(mContext, i);
        if (localColorStateList != null) {
          return localColorStateList;
        }
      }
    }
    return mWrapped.getColorStateList(paramInt);
  }
  
  public int getDimensionPixelOffset(int paramInt1, int paramInt2)
  {
    return mWrapped.getDimensionPixelOffset(paramInt1, paramInt2);
  }
  
  public int getDimensionPixelSize(int paramInt1, int paramInt2)
  {
    return mWrapped.getDimensionPixelSize(paramInt1, paramInt2);
  }
  
  public Typeface getDrawable(int paramInt1, int paramInt2, k paramK)
  {
    paramInt1 = mWrapped.getResourceId(paramInt1, 0);
    if (paramInt1 == 0) {
      return null;
    }
    if (this$0 == null) {
      this$0 = new TypedValue();
    }
    return ClassReader.a(mContext, paramInt1, this$0, paramInt2, paramK);
  }
  
  public Drawable getDrawable(int paramInt)
  {
    if (mWrapped.hasValue(paramInt))
    {
      int i = mWrapped.getResourceId(paramInt, 0);
      if (i != 0) {
        return Resources.getDrawable(mContext, i);
      }
    }
    return mWrapped.getDrawable(paramInt);
  }
  
  public Drawable getDrawableIfKnown(int paramInt)
  {
    if (mWrapped.hasValue(paramInt))
    {
      paramInt = mWrapped.getResourceId(paramInt, 0);
      if (paramInt != 0) {
        return AppCompatDrawableManager.get().getDrawable(mContext, paramInt, true);
      }
    }
    return null;
  }
  
  public float getFloat(int paramInt, float paramFloat)
  {
    return mWrapped.getFloat(paramInt, paramFloat);
  }
  
  public int getInt(int paramInt1, int paramInt2)
  {
    return mWrapped.getInt(paramInt1, paramInt2);
  }
  
  public int getInteger(int paramInt1, int paramInt2)
  {
    return mWrapped.getInteger(paramInt1, paramInt2);
  }
  
  public int getLayoutDimension(int paramInt1, int paramInt2)
  {
    return mWrapped.getLayoutDimension(paramInt1, paramInt2);
  }
  
  public int getResourceId(int paramInt1, int paramInt2)
  {
    return mWrapped.getResourceId(paramInt1, paramInt2);
  }
  
  public String getString(int paramInt)
  {
    return mWrapped.getString(paramInt);
  }
  
  public CharSequence getText(int paramInt)
  {
    return mWrapped.getText(paramInt);
  }
  
  public CharSequence[] getTextArray(int paramInt)
  {
    return mWrapped.getTextArray(paramInt);
  }
  
  public boolean hasValue(int paramInt)
  {
    return mWrapped.hasValue(paramInt);
  }
  
  public void recycle()
  {
    mWrapped.recycle();
  }
}
