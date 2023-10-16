package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources.NotFoundException;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.widget.LayoutManager;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.org.v4.util.R.styleable;
import java.lang.ref.WeakReference;

class TimePicker
{
  private int mContext = 0;
  private TintInfo mDrawableBottomTint;
  private TintInfo mDrawableEndTint;
  private TintInfo mDrawableLeftTint;
  private TintInfo mDrawableRightTint;
  private TintInfo mDrawableStartTint;
  private TintInfo mDrawableTopTint;
  private final f mMenu;
  private boolean mMode;
  private Typeface mTypeface;
  private final TextView mView;
  
  TimePicker(TextView paramTextView)
  {
    mView = paramTextView;
    mMenu = new f(mView);
  }
  
  private void applyCompoundDrawableTint(Drawable paramDrawable, TintInfo paramTintInfo)
  {
    if ((paramDrawable != null) && (paramTintInfo != null)) {
      AppCompatDrawableManager.tintDrawable(paramDrawable, paramTintInfo, mView.getDrawableState());
    }
  }
  
  private void applyStyle(Context paramContext, TintTypedArray paramTintTypedArray)
  {
    mContext = paramTintTypedArray.getInt(R.styleable.TextAppearance_android_textStyle, mContext);
    boolean bool2 = paramTintTypedArray.hasValue(R.styleable.TextAppearance_android_fontFamily);
    boolean bool1 = false;
    int i;
    if ((!bool2) && (!paramTintTypedArray.hasValue(R.styleable.TextAppearance_fontFamily)))
    {
      if (paramTintTypedArray.hasValue(R.styleable.TextAppearance_android_typeface))
      {
        mMode = false;
        i = paramTintTypedArray.getInt(R.styleable.TextAppearance_android_typeface, 1);
        if (i != 1)
        {
          if (i != 2)
          {
            if (i != 3) {
              return;
            }
            paramContext = Typeface.MONOSPACE;
          }
          else
          {
            paramContext = Typeface.SERIF;
          }
        }
        else {
          paramContext = Typeface.SANS_SERIF;
        }
        mTypeface = paramContext;
      }
    }
    else
    {
      mTypeface = null;
      if (paramTintTypedArray.hasValue(R.styleable.TextAppearance_fontFamily)) {
        i = R.styleable.TextAppearance_fontFamily;
      } else {
        i = R.styleable.TextAppearance_android_fontFamily;
      }
      int j;
      if (!paramContext.isRestricted())
      {
        paramContext = new e(this, new WeakReference(mView));
        j = mContext;
      }
      try
      {
        paramContext = paramTintTypedArray.getDrawable(i, j, paramContext);
        mTypeface = paramContext;
        if (mTypeface == null) {
          bool1 = true;
        }
        mMode = bool1;
      }
      catch (UnsupportedOperationException paramContext)
      {
        for (;;) {}
      }
      catch (Resources.NotFoundException paramContext)
      {
        for (;;) {}
      }
      if (mTypeface == null)
      {
        paramContext = paramTintTypedArray.getString(i);
        if (paramContext != null)
        {
          mTypeface = Typeface.create(paramContext, mContext);
          return;
        }
      }
    }
  }
  
  private static TintInfo createTintInfo(Context paramContext, AppCompatDrawableManager paramAppCompatDrawableManager, int paramInt)
  {
    paramContext = paramAppCompatDrawableManager.getTintList(paramContext, paramInt);
    if (paramContext != null)
    {
      paramAppCompatDrawableManager = new TintInfo();
      mHasTintList = true;
      mTintList = paramContext;
      return paramAppCompatDrawableManager;
    }
    return null;
  }
  
  private void setEnabled(int paramInt, float paramFloat)
  {
    mMenu.b(paramInt, paramFloat);
  }
  
  void applyCompoundDrawablesTints()
  {
    Drawable[] arrayOfDrawable;
    if ((mDrawableLeftTint != null) || (mDrawableBottomTint != null) || (mDrawableTopTint != null) || (mDrawableRightTint != null))
    {
      arrayOfDrawable = mView.getCompoundDrawables();
      applyCompoundDrawableTint(arrayOfDrawable[0], mDrawableLeftTint);
      applyCompoundDrawableTint(arrayOfDrawable[1], mDrawableBottomTint);
      applyCompoundDrawableTint(arrayOfDrawable[2], mDrawableTopTint);
      applyCompoundDrawableTint(arrayOfDrawable[3], mDrawableRightTint);
    }
    if ((Build.VERSION.SDK_INT >= 17) && ((mDrawableStartTint != null) || (mDrawableEndTint != null)))
    {
      arrayOfDrawable = mView.getCompoundDrawablesRelative();
      applyCompoundDrawableTint(arrayOfDrawable[0], mDrawableStartTint);
      applyCompoundDrawableTint(arrayOfDrawable[2], mDrawableEndTint);
    }
  }
  
  void applyStyle(Context paramContext, int paramInt)
  {
    TintTypedArray localTintTypedArray = TintTypedArray.obtainStyledAttributes(paramContext, paramInt, R.styleable.TextAppearance);
    if (localTintTypedArray.hasValue(R.styleable.TextAppearance_textAllCaps)) {
      setAllCaps(localTintTypedArray.getBoolean(R.styleable.TextAppearance_textAllCaps, false));
    }
    if ((Build.VERSION.SDK_INT < 23) && (localTintTypedArray.hasValue(R.styleable.TextAppearance_android_textColor)))
    {
      ColorStateList localColorStateList = localTintTypedArray.getColorStateList(R.styleable.TextAppearance_android_textColor);
      if (localColorStateList != null) {
        mView.setTextColor(localColorStateList);
      }
    }
    if ((localTintTypedArray.hasValue(R.styleable.TextAppearance_android_textSize)) && (localTintTypedArray.getDimensionPixelSize(R.styleable.TextAppearance_android_textSize, -1) == 0)) {
      mView.setTextSize(0, 0.0F);
    }
    applyStyle(paramContext, localTintTypedArray);
    localTintTypedArray.recycle();
    paramContext = mTypeface;
    if (paramContext != null) {
      mView.setTypeface(paramContext, mContext);
    }
  }
  
  void applyStyle(AttributeSet paramAttributeSet, int paramInt)
  {
    Context localContext = mView.getContext();
    Object localObject1 = AppCompatDrawableManager.get();
    Object localObject2 = TintTypedArray.obtainStyledAttributes(localContext, paramAttributeSet, R.styleable.AppCompatTextHelper, paramInt, 0);
    int i = ((TintTypedArray)localObject2).getResourceId(R.styleable.AppCompatTextHelper_android_textAppearance, -1);
    if (((TintTypedArray)localObject2).hasValue(R.styleable.AppCompatTextHelper_android_drawableLeft)) {
      mDrawableLeftTint = createTintInfo(localContext, (AppCompatDrawableManager)localObject1, ((TintTypedArray)localObject2).getResourceId(R.styleable.AppCompatTextHelper_android_drawableLeft, 0));
    }
    if (((TintTypedArray)localObject2).hasValue(R.styleable.AppCompatTextHelper_android_drawableTop)) {
      mDrawableBottomTint = createTintInfo(localContext, (AppCompatDrawableManager)localObject1, ((TintTypedArray)localObject2).getResourceId(R.styleable.AppCompatTextHelper_android_drawableTop, 0));
    }
    if (((TintTypedArray)localObject2).hasValue(R.styleable.AppCompatTextHelper_android_drawableRight)) {
      mDrawableTopTint = createTintInfo(localContext, (AppCompatDrawableManager)localObject1, ((TintTypedArray)localObject2).getResourceId(R.styleable.AppCompatTextHelper_android_drawableRight, 0));
    }
    if (((TintTypedArray)localObject2).hasValue(R.styleable.AppCompatTextHelper_android_drawableBottom)) {
      mDrawableRightTint = createTintInfo(localContext, (AppCompatDrawableManager)localObject1, ((TintTypedArray)localObject2).getResourceId(R.styleable.AppCompatTextHelper_android_drawableBottom, 0));
    }
    if (Build.VERSION.SDK_INT >= 17)
    {
      if (((TintTypedArray)localObject2).hasValue(R.styleable.AppCompatTextHelper_android_drawableStart)) {
        mDrawableStartTint = createTintInfo(localContext, (AppCompatDrawableManager)localObject1, ((TintTypedArray)localObject2).getResourceId(R.styleable.AppCompatTextHelper_android_drawableStart, 0));
      }
      if (((TintTypedArray)localObject2).hasValue(R.styleable.AppCompatTextHelper_android_drawableEnd)) {
        mDrawableEndTint = createTintInfo(localContext, (AppCompatDrawableManager)localObject1, ((TintTypedArray)localObject2).getResourceId(R.styleable.AppCompatTextHelper_android_drawableEnd, 0));
      }
    }
    ((TintTypedArray)localObject2).recycle();
    boolean bool2 = mView.getTransformationMethod() instanceof PasswordTransformationMethod;
    int j = 1;
    Object localObject3 = null;
    localObject2 = null;
    ColorStateList localColorStateList1 = null;
    boolean bool1;
    if (i != -1)
    {
      localObject4 = TintTypedArray.obtainStyledAttributes(localContext, i, R.styleable.TextAppearance);
      if ((!bool2) && (((TintTypedArray)localObject4).hasValue(R.styleable.TextAppearance_textAllCaps)))
      {
        bool1 = ((TintTypedArray)localObject4).getBoolean(R.styleable.TextAppearance_textAllCaps, false);
        i = 1;
      }
      else
      {
        i = 0;
        bool1 = false;
      }
      applyStyle(localContext, (TintTypedArray)localObject4);
      if (Build.VERSION.SDK_INT < 23)
      {
        if (((TintTypedArray)localObject4).hasValue(R.styleable.TextAppearance_android_textColor)) {
          localObject1 = ((TintTypedArray)localObject4).getColorStateList(R.styleable.TextAppearance_android_textColor);
        } else {
          localObject1 = null;
        }
        if (((TintTypedArray)localObject4).hasValue(R.styleable.TextAppearance_android_textColorHint)) {
          localObject2 = ((TintTypedArray)localObject4).getColorStateList(R.styleable.TextAppearance_android_textColorHint);
        } else {
          localObject2 = null;
        }
        if (((TintTypedArray)localObject4).hasValue(R.styleable.TextAppearance_android_textColorLink)) {
          localColorStateList1 = ((TintTypedArray)localObject4).getColorStateList(R.styleable.TextAppearance_android_textColorLink);
        }
        localObject3 = localObject1;
        localObject1 = localObject2;
      }
      else
      {
        localColorStateList1 = null;
        localObject1 = null;
      }
      ((TintTypedArray)localObject4).recycle();
      localObject2 = localObject3;
    }
    else
    {
      localColorStateList1 = null;
      localObject1 = null;
      i = 0;
      bool1 = false;
    }
    TintTypedArray localTintTypedArray = TintTypedArray.obtainStyledAttributes(localContext, paramAttributeSet, R.styleable.TextAppearance, paramInt, 0);
    if ((!bool2) && (localTintTypedArray.hasValue(R.styleable.TextAppearance_textAllCaps)))
    {
      bool1 = localTintTypedArray.getBoolean(R.styleable.TextAppearance_textAllCaps, false);
      i = j;
    }
    localObject3 = localObject2;
    Object localObject4 = localObject1;
    ColorStateList localColorStateList2 = localColorStateList1;
    if (Build.VERSION.SDK_INT < 23)
    {
      if (localTintTypedArray.hasValue(R.styleable.TextAppearance_android_textColor)) {
        localObject2 = localTintTypedArray.getColorStateList(R.styleable.TextAppearance_android_textColor);
      }
      if (localTintTypedArray.hasValue(R.styleable.TextAppearance_android_textColorHint)) {
        localObject1 = localTintTypedArray.getColorStateList(R.styleable.TextAppearance_android_textColorHint);
      }
      localObject3 = localObject2;
      localObject4 = localObject1;
      localColorStateList2 = localColorStateList1;
      if (localTintTypedArray.hasValue(R.styleable.TextAppearance_android_textColorLink))
      {
        localColorStateList2 = localTintTypedArray.getColorStateList(R.styleable.TextAppearance_android_textColorLink);
        localObject4 = localObject1;
        localObject3 = localObject2;
      }
    }
    if ((Build.VERSION.SDK_INT >= 28) && (localTintTypedArray.hasValue(R.styleable.TextAppearance_android_textSize)) && (localTintTypedArray.getDimensionPixelSize(R.styleable.TextAppearance_android_textSize, -1) == 0)) {
      mView.setTextSize(0, 0.0F);
    }
    applyStyle(localContext, localTintTypedArray);
    localTintTypedArray.recycle();
    if (localObject3 != null) {
      mView.setTextColor(localObject3);
    }
    if (localObject4 != null) {
      mView.setHintTextColor((ColorStateList)localObject4);
    }
    if (localColorStateList2 != null) {
      mView.setLinkTextColor(localColorStateList2);
    }
    if ((!bool2) && (i != 0)) {
      setAllCaps(bool1);
    }
    localObject1 = mTypeface;
    if (localObject1 != null) {
      mView.setTypeface((Typeface)localObject1, mContext);
    }
    mMenu.a(paramAttributeSet, paramInt);
    if ((android.support.v4.widget.TimePicker.mIs24HourMode) && (mMenu.k() != 0))
    {
      localObject1 = mMenu.close();
      if (localObject1.length > 0) {
        if (mView.getAutoSizeStepGranularity() != -1.0F) {
          mView.setAutoSizeTextTypeUniformWithConfiguration(mMenu.add(), mMenu.clear(), mMenu.getHeight(), 0);
        } else {
          mView.setAutoSizeTextTypeUniformWithPresetSizes((int[])localObject1, 0);
        }
      }
    }
    paramAttributeSet = TintTypedArray.obtainStyledAttributes(localContext, paramAttributeSet, R.styleable.AppCompatTextView);
    paramInt = paramAttributeSet.getDimensionPixelSize(R.styleable.AppCompatTextView_firstBaselineToTopHeight, -1);
    i = paramAttributeSet.getDimensionPixelSize(R.styleable.AppCompatTextView_lastBaselineToBottomHeight, -1);
    j = paramAttributeSet.getDimensionPixelSize(R.styleable.AppCompatTextView_lineHeight, -1);
    paramAttributeSet.recycle();
    if (paramInt != -1) {
      LayoutManager.a(mView, paramInt);
    }
    if (i != -1) {
      LayoutManager.initialize(mView, i);
    }
    if (j != -1) {
      LayoutManager.init(mView, j);
    }
  }
  
  void applyStyle(WeakReference paramWeakReference, Typeface paramTypeface)
  {
    if (mMode)
    {
      mTypeface = paramTypeface;
      paramWeakReference = (TextView)paramWeakReference.get();
      if (paramWeakReference != null) {
        paramWeakReference.setTypeface(paramTypeface, mContext);
      }
    }
  }
  
  int[] getHour()
  {
    return mMenu.close();
  }
  
  int getMinute()
  {
    return mMenu.clear();
  }
  
  int getTextSize()
  {
    return mMenu.k();
  }
  
  int getTypeface()
  {
    return mMenu.add();
  }
  
  void onSaveInstanceState()
  {
    mMenu.a();
  }
  
  void onSaveInstanceState(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    mMenu.a(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  void setAllCaps(boolean paramBoolean)
  {
    mView.setAllCaps(paramBoolean);
  }
  
  void setEnabled(int paramInt)
  {
    mMenu.b(paramInt);
  }
  
  void setEnabled(int[] paramArrayOfInt, int paramInt)
  {
    mMenu.a(paramArrayOfInt, paramInt);
  }
  
  boolean setEnabled()
  {
    return mMenu.i();
  }
  
  int setTime()
  {
    return mMenu.getHeight();
  }
  
  void setTime(int paramInt, float paramFloat)
  {
    if ((!android.support.v4.widget.TimePicker.mIs24HourMode) && (!setEnabled())) {
      setEnabled(paramInt, paramFloat);
    }
  }
  
  void setTime(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (!android.support.v4.widget.TimePicker.mIs24HourMode) {
      onSaveInstanceState();
    }
  }
}
