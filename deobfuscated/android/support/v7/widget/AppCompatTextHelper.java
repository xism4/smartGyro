package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsSeekBar;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import com.org.android.view.ViewCompat;
import com.org.v4.util.R.styleable;

class AppCompatTextHelper
  extends AppCompatProgressBarHelper
{
  private ColorStateList mButtonTintList = null;
  private PorterDuff.Mode mButtonTintMode = null;
  private Drawable mDrawable;
  private boolean mHasButtonTint = false;
  private boolean mHasButtonTintMode = false;
  private final SeekBar mView;
  
  AppCompatTextHelper(SeekBar paramSeekBar)
  {
    super(paramSeekBar);
    mView = paramSeekBar;
  }
  
  private void applyButtonTint()
  {
    if ((mDrawable != null) && ((mHasButtonTint) || (mHasButtonTintMode)))
    {
      mDrawable = DrawableCompat.wrap(mDrawable.mutate());
      if (mHasButtonTint) {
        DrawableCompat.setTintList(mDrawable, mButtonTintList);
      }
      if (mHasButtonTintMode) {
        DrawableCompat.setTintMode(mDrawable, mButtonTintMode);
      }
      if (mDrawable.isStateful()) {
        mDrawable.setState(mView.getDrawableState());
      }
    }
  }
  
  void draw(Canvas paramCanvas)
  {
    if (mDrawable != null)
    {
      int k = mView.getMax();
      int j = 1;
      if (k > 1)
      {
        int i = mDrawable.getIntrinsicWidth();
        int m = mDrawable.getIntrinsicHeight();
        if (i >= 0) {
          i /= 2;
        } else {
          i = 1;
        }
        if (m >= 0) {
          j = m / 2;
        }
        mDrawable.setBounds(-i, -j, i, j);
        float f = (mView.getWidth() - mView.getPaddingLeft() - mView.getPaddingRight()) / k;
        j = paramCanvas.save();
        paramCanvas.translate(mView.getPaddingLeft(), mView.getHeight() / 2);
        i = 0;
        while (i <= k)
        {
          mDrawable.draw(paramCanvas);
          paramCanvas.translate(f, 0.0F);
          i += 1;
        }
        paramCanvas.restoreToCount(j);
      }
    }
  }
  
  void jumpToCurrentState()
  {
    Drawable localDrawable = mDrawable;
    if (localDrawable != null) {
      localDrawable.jumpToCurrentState();
    }
  }
  
  void loadFromAttributes(AttributeSet paramAttributeSet, int paramInt)
  {
    super.loadFromAttributes(paramAttributeSet, paramInt);
    paramAttributeSet = TintTypedArray.obtainStyledAttributes(mView.getContext(), paramAttributeSet, R.styleable.AppCompatSeekBar, paramInt, 0);
    Drawable localDrawable = paramAttributeSet.getDrawableIfKnown(R.styleable.AppCompatSeekBar_android_thumb);
    if (localDrawable != null) {
      mView.setThumb(localDrawable);
    }
    setStatusBarBackground(paramAttributeSet.getDrawable(R.styleable.AppCompatSeekBar_tickMark));
    if (paramAttributeSet.hasValue(R.styleable.AppCompatSeekBar_tickMarkTintMode))
    {
      mButtonTintMode = DrawableUtils.parseTintMode(paramAttributeSet.getInt(R.styleable.AppCompatSeekBar_tickMarkTintMode, -1), mButtonTintMode);
      mHasButtonTintMode = true;
    }
    if (paramAttributeSet.hasValue(R.styleable.AppCompatSeekBar_tickMarkTint))
    {
      mButtonTintList = paramAttributeSet.getColorStateList(R.styleable.AppCompatSeekBar_tickMarkTint);
      mHasButtonTint = true;
    }
    paramAttributeSet.recycle();
    applyButtonTint();
  }
  
  void setState()
  {
    Drawable localDrawable = mDrawable;
    if ((localDrawable != null) && (localDrawable.isStateful()) && (localDrawable.setState(mView.getDrawableState()))) {
      mView.invalidateDrawable(localDrawable);
    }
  }
  
  void setStatusBarBackground(Drawable paramDrawable)
  {
    Drawable localDrawable = mDrawable;
    if (localDrawable != null) {
      localDrawable.setCallback(null);
    }
    mDrawable = paramDrawable;
    if (paramDrawable != null)
    {
      paramDrawable.setCallback(mView);
      DrawableCompat.a(paramDrawable, ViewCompat.getLayoutDirection(mView));
      if (paramDrawable.isStateful()) {
        paramDrawable.setState(mView.getDrawableState());
      }
      applyButtonTint();
    }
    mView.invalidate();
  }
}
