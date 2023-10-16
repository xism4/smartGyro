package com.org.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.DisplayMetrics;
import android.util.SparseArray;

class DrawableContainer
  extends Drawable
  implements Drawable.Callback
{
  private long a;
  private int c = -1;
  private int mAlpha = 255;
  private Runnable mAnimationRunnable;
  private Drawable mCurrDrawable;
  private Drawable mDrawable;
  private boolean mMutated;
  private Rect mRect;
  private DrawableContainerState mState;
  private boolean r;
  private MaterialProgressDrawable.3 this$0;
  private int x = -1;
  private long y;
  
  DrawableContainer() {}
  
  private void draw(Drawable paramDrawable)
  {
    if (this$0 == null) {
      this$0 = new MaterialProgressDrawable.3();
    }
    Object localObject = this$0;
    ((MaterialProgressDrawable.3)localObject).put(paramDrawable.getCallback());
    paramDrawable.setCallback((Drawable.Callback)localObject);
    try
    {
      int i = mState.g;
      if (i <= 0)
      {
        bool = r;
        if (bool) {
          paramDrawable.setAlpha(mAlpha);
        }
      }
      boolean bool = mState.mState;
      if (bool)
      {
        paramDrawable.setColorFilter(mState.mPaint);
      }
      else
      {
        bool = mState.mUseColor;
        if (bool) {
          DrawableCompat.setTintList(paramDrawable, mState.mTint);
        }
        bool = mState.mChangingConfigurations;
        if (bool) {
          DrawableCompat.setTintMode(paramDrawable, mState.mTintMode);
        }
      }
      paramDrawable.setVisible(isVisible(), true);
      paramDrawable.setDither(mState.i);
      paramDrawable.setState(getState());
      paramDrawable.setLevel(getLevel());
      paramDrawable.setBounds(getBounds());
      i = Build.VERSION.SDK_INT;
      if (i >= 23) {
        paramDrawable.setLayoutDirection(getLayoutDirection());
      }
      i = Build.VERSION.SDK_INT;
      if (i >= 19) {
        paramDrawable.setAutoMirrored(mState.left);
      }
      localObject = mRect;
      i = Build.VERSION.SDK_INT;
      if ((i >= 21) && (localObject != null)) {
        paramDrawable.setHotspotBounds(left, top, right, bottom);
      }
      paramDrawable.setCallback(this$0.apply());
      return;
    }
    catch (Throwable localThrowable)
    {
      paramDrawable.setCallback(this$0.apply());
      throw localThrowable;
    }
  }
  
  static int init(Resources paramResources, int paramInt)
  {
    if (paramResources != null) {
      paramInt = getDisplayMetricsdensityDpi;
    }
    if (paramInt == 0) {
      return 160;
    }
    return paramInt;
  }
  
  private boolean selectDrawable()
  {
    return (isAutoMirrored()) && (getLayoutDirection() == 1);
  }
  
  void animate(boolean paramBoolean)
  {
    int j = 1;
    r = true;
    long l1 = SystemClock.uptimeMillis();
    Drawable localDrawable = mDrawable;
    long l2;
    if (localDrawable != null)
    {
      l2 = a;
      if (l2 == 0L) {
        break label102;
      }
      if (l2 <= l1)
      {
        localDrawable.setAlpha(mAlpha);
      }
      else
      {
        localDrawable.setAlpha((255 - (int)((l2 - l1) * 255L) / mState.g) * mAlpha / 255);
        i = 1;
        break label104;
      }
    }
    a = 0L;
    label102:
    int i = 0;
    label104:
    localDrawable = mCurrDrawable;
    if (localDrawable != null)
    {
      l2 = y;
      if (l2 == 0L) {
        break label199;
      }
      if (l2 <= l1)
      {
        localDrawable.setVisible(false, false);
        mCurrDrawable = null;
        c = -1;
      }
      else
      {
        localDrawable.setAlpha((int)((l2 - l1) * 255L) / mState.mAlpha * mAlpha / 255);
        i = j;
        break label199;
      }
    }
    y = 0L;
    label199:
    if ((paramBoolean) && (i != 0)) {
      scheduleSelf(mAnimationRunnable, l1 + 16L);
    }
  }
  
  public void applyTheme(Resources.Theme paramTheme)
  {
    mState.init(paramTheme);
  }
  
  public boolean canApplyTheme()
  {
    return mState.canApplyTheme();
  }
  
  DrawableContainerState draw()
  {
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  public void draw(Canvas paramCanvas)
  {
    Drawable localDrawable = mDrawable;
    if (localDrawable != null) {
      localDrawable.draw(paramCanvas);
    }
    localDrawable = mCurrDrawable;
    if (localDrawable != null) {
      localDrawable.draw(paramCanvas);
    }
  }
  
  boolean draw(int paramInt)
  {
    if (paramInt == x) {
      return false;
    }
    long l = SystemClock.uptimeMillis();
    Object localObject;
    if (mState.mAlpha > 0)
    {
      localObject = mCurrDrawable;
      if (localObject != null) {
        ((Drawable)localObject).setVisible(false, false);
      }
      localObject = mDrawable;
      if (localObject != null)
      {
        mCurrDrawable = ((Drawable)localObject);
        c = x;
        y = (mState.mAlpha + l);
      }
      else
      {
        mCurrDrawable = null;
        c = -1;
        y = 0L;
      }
    }
    else
    {
      localObject = mDrawable;
      if (localObject != null) {
        ((Drawable)localObject).setVisible(false, false);
      }
    }
    if (paramInt >= 0)
    {
      localObject = mState;
      if (paramInt < mChildren)
      {
        localObject = ((DrawableContainerState)localObject).get(paramInt);
        mDrawable = ((Drawable)localObject);
        x = paramInt;
        if (localObject == null) {
          break label204;
        }
        paramInt = mState.g;
        if (paramInt > 0) {
          a = (l + paramInt);
        }
        draw((Drawable)localObject);
        break label204;
      }
    }
    mDrawable = null;
    x = -1;
    label204:
    if ((a != 0L) || (y != 0L))
    {
      localObject = mAnimationRunnable;
      if (localObject == null) {
        mAnimationRunnable = new MonthByWeekFragment.2(this);
      } else {
        unscheduleSelf((Runnable)localObject);
      }
      animate(true);
    }
    invalidateSelf();
    return true;
  }
  
  public int getAlpha()
  {
    return mAlpha;
  }
  
  public int getChangingConfigurations()
  {
    return super.getChangingConfigurations() | mState.getChangingConfigurations();
  }
  
  public final Drawable.ConstantState getConstantState()
  {
    if (mState.canConstantState())
    {
      mState.type = getChangingConfigurations();
      return mState;
    }
    return null;
  }
  
  public Drawable getCurrent()
  {
    return mDrawable;
  }
  
  public void getHotspotBounds(Rect paramRect)
  {
    Rect localRect = mRect;
    if (localRect != null)
    {
      paramRect.set(localRect);
      return;
    }
    super.getHotspotBounds(paramRect);
  }
  
  public int getIntrinsicHeight()
  {
    if (mState.get()) {
      return mState.a();
    }
    Drawable localDrawable = mDrawable;
    if (localDrawable != null) {
      return localDrawable.getIntrinsicHeight();
    }
    return -1;
  }
  
  public int getIntrinsicWidth()
  {
    if (mState.get()) {
      return mState.getWidth();
    }
    Drawable localDrawable = mDrawable;
    if (localDrawable != null) {
      return localDrawable.getIntrinsicWidth();
    }
    return -1;
  }
  
  public int getMinimumHeight()
  {
    if (mState.get()) {
      return mState.getMinimumHeight();
    }
    Drawable localDrawable = mDrawable;
    if (localDrawable != null) {
      return localDrawable.getMinimumHeight();
    }
    return 0;
  }
  
  public int getMinimumWidth()
  {
    if (mState.get()) {
      return mState.getMinimumWidth();
    }
    Drawable localDrawable = mDrawable;
    if (localDrawable != null) {
      return localDrawable.getMinimumWidth();
    }
    return 0;
  }
  
  public int getOpacity()
  {
    Drawable localDrawable = mDrawable;
    if ((localDrawable != null) && (localDrawable.isVisible())) {
      return mState.getOpacity();
    }
    return -2;
  }
  
  public void getOutline(Outline paramOutline)
  {
    Drawable localDrawable = mDrawable;
    if (localDrawable != null) {
      localDrawable.getOutline(paramOutline);
    }
  }
  
  public boolean getPadding(Rect paramRect)
  {
    Object localObject = mState.add();
    int i;
    boolean bool;
    if (localObject != null)
    {
      paramRect.set((Rect)localObject);
      i = left;
      int j = top;
      int k = bottom;
      if ((right | i | j | k) != 0) {
        bool = true;
      } else {
        bool = false;
      }
    }
    else
    {
      localObject = mDrawable;
      if (localObject != null) {
        bool = ((Drawable)localObject).getPadding(paramRect);
      } else {
        bool = super.getPadding(paramRect);
      }
    }
    if (selectDrawable())
    {
      i = left;
      left = right;
      right = i;
    }
    return bool;
  }
  
  int getWidth()
  {
    return x;
  }
  
  final void inflate(Resources paramResources)
  {
    mState.init(paramResources);
  }
  
  public void invalidateDrawable(Drawable paramDrawable)
  {
    DrawableContainerState localDrawableContainerState = mState;
    if (localDrawableContainerState != null) {
      localDrawableContainerState.addChild();
    }
    if ((paramDrawable == mDrawable) && (getCallback() != null)) {
      getCallback().invalidateDrawable(this);
    }
  }
  
  public boolean isAutoMirrored()
  {
    return mState.left;
  }
  
  public void jumpToCurrentState()
  {
    Drawable localDrawable = mCurrDrawable;
    int i;
    if (localDrawable != null)
    {
      localDrawable.jumpToCurrentState();
      mCurrDrawable = null;
      c = -1;
      i = 1;
    }
    else
    {
      i = 0;
    }
    localDrawable = mDrawable;
    if (localDrawable != null)
    {
      localDrawable.jumpToCurrentState();
      if (r) {
        mDrawable.setAlpha(mAlpha);
      }
    }
    if (y != 0L)
    {
      y = 0L;
      i = 1;
    }
    if (a != 0L)
    {
      a = 0L;
      i = 1;
    }
    if (i != 0) {
      invalidateSelf();
    }
  }
  
  public Drawable mutate()
  {
    if ((!mMutated) && (super.mutate() == this))
    {
      DrawableContainerState localDrawableContainerState = draw();
      localDrawableContainerState.init();
      mutate(localDrawableContainerState);
      mMutated = true;
    }
    return this;
  }
  
  protected void mutate(DrawableContainerState paramDrawableContainerState)
  {
    mState = paramDrawableContainerState;
    int i = x;
    if (i >= 0)
    {
      mDrawable = paramDrawableContainerState.get(i);
      paramDrawableContainerState = mDrawable;
      if (paramDrawableContainerState != null) {
        draw(paramDrawableContainerState);
      }
    }
    c = -1;
    mCurrDrawable = null;
  }
  
  protected void onBoundsChange(Rect paramRect)
  {
    Drawable localDrawable = mCurrDrawable;
    if (localDrawable != null) {
      localDrawable.setBounds(paramRect);
    }
    localDrawable = mDrawable;
    if (localDrawable != null) {
      localDrawable.setBounds(paramRect);
    }
  }
  
  public boolean onLayoutDirectionChanged(int paramInt)
  {
    return mState.draw(paramInt, getWidth());
  }
  
  protected boolean onLevelChange(int paramInt)
  {
    Drawable localDrawable = mCurrDrawable;
    if (localDrawable != null) {
      return localDrawable.setLevel(paramInt);
    }
    localDrawable = mDrawable;
    if (localDrawable != null) {
      return localDrawable.setLevel(paramInt);
    }
    return false;
  }
  
  protected boolean onStateChange(int[] paramArrayOfInt)
  {
    Drawable localDrawable = mCurrDrawable;
    if (localDrawable != null) {
      return localDrawable.setState(paramArrayOfInt);
    }
    localDrawable = mDrawable;
    if (localDrawable != null) {
      return localDrawable.setState(paramArrayOfInt);
    }
    return false;
  }
  
  public void scheduleDrawable(Drawable paramDrawable, Runnable paramRunnable, long paramLong)
  {
    if ((paramDrawable == mDrawable) && (getCallback() != null)) {
      getCallback().scheduleDrawable(this, paramRunnable, paramLong);
    }
  }
  
  public void setAlpha(int paramInt)
  {
    if ((!r) || (mAlpha != paramInt))
    {
      r = true;
      mAlpha = paramInt;
      Drawable localDrawable = mDrawable;
      if (localDrawable != null)
      {
        if (a == 0L)
        {
          localDrawable.setAlpha(paramInt);
          return;
        }
        animate(false);
      }
    }
  }
  
  public void setAutoMirrored(boolean paramBoolean)
  {
    DrawableContainerState localDrawableContainerState = mState;
    if (left != paramBoolean)
    {
      left = paramBoolean;
      Drawable localDrawable = mDrawable;
      if (localDrawable != null) {
        DrawableCompat.setHotspotBounds(localDrawable, left);
      }
    }
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    Object localObject = mState;
    mState = true;
    if (mPaint != paramColorFilter)
    {
      mPaint = paramColorFilter;
      localObject = mDrawable;
      if (localObject != null) {
        ((Drawable)localObject).setColorFilter(paramColorFilter);
      }
    }
  }
  
  public void setDither(boolean paramBoolean)
  {
    DrawableContainerState localDrawableContainerState = mState;
    if (i != paramBoolean)
    {
      i = paramBoolean;
      Drawable localDrawable = mDrawable;
      if (localDrawable != null) {
        localDrawable.setDither(i);
      }
    }
  }
  
  public void setHotspot(float paramFloat1, float paramFloat2)
  {
    Drawable localDrawable = mDrawable;
    if (localDrawable != null) {
      DrawableCompat.setHotspot(localDrawable, paramFloat1, paramFloat2);
    }
  }
  
  public void setHotspotBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    Object localObject = mRect;
    if (localObject == null) {
      mRect = new Rect(paramInt1, paramInt2, paramInt3, paramInt4);
    } else {
      ((Rect)localObject).set(paramInt1, paramInt2, paramInt3, paramInt4);
    }
    localObject = mDrawable;
    if (localObject != null) {
      DrawableCompat.setHotspotBounds((Drawable)localObject, paramInt1, paramInt2, paramInt3, paramInt4);
    }
  }
  
  public void setTintList(ColorStateList paramColorStateList)
  {
    DrawableContainerState localDrawableContainerState = mState;
    mUseColor = true;
    if (mTint != paramColorStateList)
    {
      mTint = paramColorStateList;
      DrawableCompat.setTintList(mDrawable, paramColorStateList);
    }
  }
  
  public void setTintMode(PorterDuff.Mode paramMode)
  {
    DrawableContainerState localDrawableContainerState = mState;
    mChangingConfigurations = true;
    if (mTintMode != paramMode)
    {
      mTintMode = paramMode;
      DrawableCompat.setTintMode(mDrawable, paramMode);
    }
  }
  
  public boolean setVisible(boolean paramBoolean1, boolean paramBoolean2)
  {
    boolean bool = super.setVisible(paramBoolean1, paramBoolean2);
    Drawable localDrawable = mCurrDrawable;
    if (localDrawable != null) {
      localDrawable.setVisible(paramBoolean1, paramBoolean2);
    }
    localDrawable = mDrawable;
    if (localDrawable != null) {
      localDrawable.setVisible(paramBoolean1, paramBoolean2);
    }
    return bool;
  }
  
  public void unscheduleDrawable(Drawable paramDrawable, Runnable paramRunnable)
  {
    if ((paramDrawable == mDrawable) && (getCallback() != null)) {
      getCallback().unscheduleDrawable(this, paramRunnable);
    }
  }
  
  abstract class DrawableContainerState
    extends Drawable.ConstantState
  {
    boolean a;
    boolean b;
    int g;
    int h;
    int height;
    boolean i;
    boolean left;
    int mAlpha;
    boolean mCanConstantState;
    boolean mChangingConfigurations;
    boolean mCheckedConstantState;
    int mChildren;
    Drawable[] mDrawable;
    boolean mHaveOpacity;
    boolean mHaveStateful;
    int mOpacity;
    ColorFilter mPaint;
    boolean mState;
    boolean mStateful;
    ColorStateList mTint;
    PorterDuff.Mode mTintMode;
    boolean mUseColor;
    int mWidth;
    Resources next;
    boolean p;
    int pointCount;
    Rect r;
    final DrawableContainer this$0;
    int type;
    int value = 160;
    boolean w;
    int width;
    boolean x;
    int y;
    
    DrawableContainerState(DrawableContainer paramDrawableContainer, Resources paramResources)
    {
      int k = 0;
      b = false;
      a = false;
      i = true;
      g = 0;
      mAlpha = 0;
      this$0 = paramDrawableContainer;
      if (paramResources != null) {
        paramDrawableContainer = paramResources;
      } else if (DrawableContainer.this != null) {
        paramDrawableContainer = next;
      } else {
        paramDrawableContainer = null;
      }
      next = paramDrawableContainer;
      int j;
      if (DrawableContainer.this != null) {
        j = value;
      } else {
        j = 0;
      }
      value = DrawableContainer.init(paramResources, j);
      if (DrawableContainer.this != null)
      {
        type = type;
        pointCount = pointCount;
        mCanConstantState = true;
        mCheckedConstantState = true;
        b = b;
        a = a;
        i = i;
        w = w;
        h = h;
        g = g;
        mAlpha = mAlpha;
        left = left;
        mPaint = mPaint;
        mState = mState;
        mTint = mTint;
        mTintMode = mTintMode;
        mUseColor = mUseColor;
        mChangingConfigurations = mChangingConfigurations;
        if (value == value)
        {
          if (p)
          {
            r = new Rect(r);
            p = true;
          }
          if (x)
          {
            mWidth = mWidth;
            y = y;
            width = width;
            height = height;
            x = true;
          }
        }
        if (mHaveOpacity)
        {
          mOpacity = mOpacity;
          mHaveOpacity = true;
        }
        if (mHaveStateful)
        {
          mStateful = mStateful;
          mHaveStateful = true;
        }
        paramDrawableContainer = mDrawable;
        mDrawable = new Drawable[paramDrawableContainer.length];
        mChildren = mChildren;
        this$1 = mData;
        if (DrawableContainer.this != null) {
          this$1 = clone();
        } else {
          this$1 = new SparseArray(mChildren);
        }
        int m = mChildren;
        j = k;
        while (j < m)
        {
          if (paramDrawableContainer[j] != null)
          {
            this$1 = paramDrawableContainer[j].getConstantState();
            if (DrawableContainer.this != null) {
              put(j, DrawableContainer.this);
            } else {
              mDrawable[j] = paramDrawableContainer[j];
            }
          }
          j += 1;
        }
      }
      mDrawable = new Drawable[10];
      mChildren = 0;
    }
    
    private Drawable apply(Drawable paramDrawable)
    {
      if (Build.VERSION.SDK_INT >= 23) {
        paramDrawable.setLayoutDirection(h);
      }
      paramDrawable = paramDrawable.mutate();
      paramDrawable.setCallback(this$0);
      return paramDrawable;
    }
    
    private void remove()
    {
      Object localObject = DrawableContainer.this;
      if (localObject != null)
      {
        int k = ((SparseArray)localObject).size();
        int j = 0;
        while (j < k)
        {
          int m = keyAt(j);
          localObject = (Drawable.ConstantState)valueAt(j);
          mDrawable[m] = apply(((Drawable.ConstantState)localObject).newDrawable(next));
          j += 1;
        }
        mData = null;
      }
    }
    
    public final int a()
    {
      if (!x) {
        draw();
      }
      return y;
    }
    
    public final Rect add()
    {
      if (b) {
        return null;
      }
      if ((r == null) && (!p))
      {
        remove();
        Rect localRect = new Rect();
        int k = mChildren;
        Drawable[] arrayOfDrawable = mDrawable;
        Object localObject1 = null;
        int j = 0;
        while (j < k)
        {
          Object localObject3 = localObject1;
          if (arrayOfDrawable[j].getPadding(localRect))
          {
            Object localObject2 = localObject1;
            if (localObject1 == null) {
              localObject2 = new Rect(0, 0, 0, 0);
            }
            int m = left;
            if (m > left) {
              left = m;
            }
            m = top;
            if (m > top) {
              top = m;
            }
            m = right;
            if (m > right) {
              right = m;
            }
            m = bottom;
            localObject3 = localObject2;
            if (m > bottom)
            {
              bottom = m;
              localObject3 = localObject2;
            }
          }
          j += 1;
          localObject1 = localObject3;
        }
        p = true;
        r = localObject1;
        return localObject1;
      }
      return r;
    }
    
    void addChild()
    {
      mHaveOpacity = false;
      mHaveStateful = false;
    }
    
    public void addChild(int paramInt1, int paramInt2)
    {
      Drawable[] arrayOfDrawable = new Drawable[paramInt2];
      System.arraycopy(mDrawable, 0, arrayOfDrawable, 0, paramInt1);
      mDrawable = arrayOfDrawable;
    }
    
    public final void addChild(boolean paramBoolean)
    {
      a = paramBoolean;
    }
    
    public boolean canApplyTheme()
    {
      int k = mChildren;
      Drawable[] arrayOfDrawable = mDrawable;
      int j = 0;
      while (j < k)
      {
        Object localObject = arrayOfDrawable[j];
        if (localObject != null)
        {
          if (((Drawable)localObject).canApplyTheme()) {
            return true;
          }
        }
        else
        {
          localObject = (Drawable.ConstantState)get(j);
          if ((localObject != null) && (((Drawable.ConstantState)localObject).canApplyTheme())) {
            return true;
          }
        }
        j += 1;
      }
      return false;
    }
    
    public boolean canConstantState()
    {
      try
      {
        if (mCanConstantState)
        {
          boolean bool = mCheckedConstantState;
          return bool;
        }
        remove();
        mCanConstantState = true;
        int k = mChildren;
        Drawable[] arrayOfDrawable = mDrawable;
        int j = 0;
        while (j < k)
        {
          if (arrayOfDrawable[j].getConstantState() == null)
          {
            mCheckedConstantState = false;
            return false;
          }
          j += 1;
        }
        mCheckedConstantState = true;
        return true;
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    
    protected void draw()
    {
      x = true;
      remove();
      int k = mChildren;
      Drawable[] arrayOfDrawable = mDrawable;
      y = -1;
      mWidth = -1;
      int j = 0;
      height = 0;
      width = 0;
      while (j < k)
      {
        Drawable localDrawable = arrayOfDrawable[j];
        int m = localDrawable.getIntrinsicWidth();
        if (m > mWidth) {
          mWidth = m;
        }
        m = localDrawable.getIntrinsicHeight();
        if (m > y) {
          y = m;
        }
        m = localDrawable.getMinimumWidth();
        if (m > width) {
          width = m;
        }
        m = localDrawable.getMinimumHeight();
        if (m > height) {
          height = m;
        }
        j += 1;
      }
    }
    
    final boolean draw(int paramInt1, int paramInt2)
    {
      int k = mChildren;
      Drawable[] arrayOfDrawable = mDrawable;
      int j = 0;
      boolean bool3;
      for (boolean bool2 = false; j < k; bool2 = bool3)
      {
        bool3 = bool2;
        if (arrayOfDrawable[j] != null)
        {
          boolean bool1;
          if (Build.VERSION.SDK_INT >= 23) {
            bool1 = arrayOfDrawable[j].setLayoutDirection(paramInt1);
          } else {
            bool1 = false;
          }
          bool3 = bool2;
          if (j == paramInt2) {
            bool3 = bool1;
          }
        }
        j += 1;
      }
      h = paramInt1;
      return bool2;
    }
    
    public final Drawable get(int paramInt)
    {
      Object localObject = mDrawable[paramInt];
      if (localObject != null) {
        return localObject;
      }
      localObject = DrawableContainer.this;
      if (localObject != null)
      {
        int j = ((SparseArray)localObject).indexOfKey(paramInt);
        if (j >= 0)
        {
          localObject = apply(((Drawable.ConstantState)valueAt(j)).newDrawable(next));
          mDrawable[paramInt] = localObject;
          removeAt(j);
          if (size() != 0) {
            return localObject;
          }
          mData = null;
          return localObject;
        }
      }
      return null;
      return localObject;
    }
    
    public final boolean get()
    {
      return a;
    }
    
    public int getChangingConfigurations()
    {
      return type | pointCount;
    }
    
    final int getChildCount()
    {
      return mDrawable.length;
    }
    
    public final int getChildren()
    {
      return mChildren;
    }
    
    public final int getMinimumHeight()
    {
      if (!x) {
        draw();
      }
      return height;
    }
    
    public final int getMinimumWidth()
    {
      if (!x) {
        draw();
      }
      return width;
    }
    
    public final int getOpacity()
    {
      if (mHaveOpacity) {
        return mOpacity;
      }
      remove();
      int n = mChildren;
      Drawable[] arrayOfDrawable = mDrawable;
      if (n > 0) {
        j = arrayOfDrawable[0].getOpacity();
      } else {
        j = -2;
      }
      int m = 1;
      int k = j;
      int j = m;
      while (j < n)
      {
        k = Drawable.resolveOpacity(k, arrayOfDrawable[j].getOpacity());
        j += 1;
      }
      mOpacity = k;
      mHaveOpacity = true;
      return k;
    }
    
    public final int getWidth()
    {
      if (!x) {
        draw();
      }
      return mWidth;
    }
    
    public final int init(Drawable paramDrawable)
    {
      int j = mChildren;
      if (j >= mDrawable.length) {
        addChild(j, j + 10);
      }
      paramDrawable.mutate();
      paramDrawable.setVisible(false, true);
      paramDrawable.setCallback(this$0);
      mDrawable[j] = paramDrawable;
      mChildren += 1;
      int k = pointCount;
      pointCount = (paramDrawable.getChangingConfigurations() | k);
      addChild();
      r = null;
      p = false;
      x = false;
      mCanConstantState = false;
      return j;
    }
    
    abstract void init();
    
    final void init(Resources.Theme paramTheme)
    {
      if (paramTheme != null)
      {
        remove();
        int k = mChildren;
        Drawable[] arrayOfDrawable = mDrawable;
        int j = 0;
        while (j < k)
        {
          if ((arrayOfDrawable[j] != null) && (arrayOfDrawable[j].canApplyTheme()))
          {
            arrayOfDrawable[j].applyTheme(paramTheme);
            pointCount |= arrayOfDrawable[j].getChangingConfigurations();
          }
          j += 1;
        }
        init(paramTheme.getResources());
      }
    }
    
    final void init(Resources paramResources)
    {
      if (paramResources != null)
      {
        next = paramResources;
        int j = DrawableContainer.init(paramResources, value);
        int k = value;
        value = j;
        if (k != j)
        {
          x = false;
          p = false;
        }
      }
    }
    
    public final void read(boolean paramBoolean)
    {
      b = paramBoolean;
    }
    
    public final void set(int paramInt)
    {
      g = paramInt;
    }
    
    public final void setColor(int paramInt)
    {
      mAlpha = paramInt;
    }
  }
}
