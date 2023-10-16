package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.LayoutManager;
import android.util.AttributeSet;
import android.view.ActionMode.Callback;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityRecord;
import android.widget.Button;
import android.widget.TextView;
import com.org.android.view.TintableBackgroundView;
import com.org.v4.util.R.attr;

public class AppCompatButton
  extends Button
  implements TintableBackgroundView, android.support.v4.widget.TimePicker
{
  private final AppCompatBackgroundHelper mBackgroundTintHelper = new AppCompatBackgroundHelper(this);
  private final TimePicker mTimePicker;
  
  public AppCompatButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.buttonStyle);
  }
  
  public AppCompatButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(TintContextWrapper.wrap(paramContext), paramAttributeSet, paramInt);
    mBackgroundTintHelper.loadFromAttributes(paramAttributeSet, paramInt);
    mTimePicker = new TimePicker(this);
    mTimePicker.applyStyle(paramAttributeSet, paramInt);
    mTimePicker.applyCompoundDrawablesTints();
  }
  
  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    Object localObject = mBackgroundTintHelper;
    if (localObject != null) {
      ((AppCompatBackgroundHelper)localObject).applySupportBackgroundTint();
    }
    localObject = mTimePicker;
    if (localObject != null) {
      ((TimePicker)localObject).applyCompoundDrawablesTints();
    }
  }
  
  public int getAutoSizeMaxTextSize()
  {
    if (android.support.v4.widget.TimePicker.mIs24HourMode) {
      return super.getAutoSizeMaxTextSize();
    }
    TimePicker localTimePicker = mTimePicker;
    if (localTimePicker != null) {
      return localTimePicker.getMinute();
    }
    return -1;
  }
  
  public int getAutoSizeMinTextSize()
  {
    if (android.support.v4.widget.TimePicker.mIs24HourMode) {
      return super.getAutoSizeMinTextSize();
    }
    TimePicker localTimePicker = mTimePicker;
    if (localTimePicker != null) {
      return localTimePicker.getTypeface();
    }
    return -1;
  }
  
  public int getAutoSizeStepGranularity()
  {
    if (android.support.v4.widget.TimePicker.mIs24HourMode) {
      return super.getAutoSizeStepGranularity();
    }
    TimePicker localTimePicker = mTimePicker;
    if (localTimePicker != null) {
      return localTimePicker.setTime();
    }
    return -1;
  }
  
  public int[] getAutoSizeTextAvailableSizes()
  {
    if (android.support.v4.widget.TimePicker.mIs24HourMode) {
      return super.getAutoSizeTextAvailableSizes();
    }
    TimePicker localTimePicker = mTimePicker;
    if (localTimePicker != null) {
      return localTimePicker.getHour();
    }
    return new int[0];
  }
  
  public int getAutoSizeTextType()
  {
    if (android.support.v4.widget.TimePicker.mIs24HourMode)
    {
      if (super.getAutoSizeTextType() == 1) {
        return 1;
      }
    }
    else
    {
      TimePicker localTimePicker = mTimePicker;
      if (localTimePicker != null) {
        return localTimePicker.getTextSize();
      }
    }
    return 0;
  }
  
  public ColorStateList getSupportBackgroundTintList()
  {
    AppCompatBackgroundHelper localAppCompatBackgroundHelper = mBackgroundTintHelper;
    if (localAppCompatBackgroundHelper != null) {
      return localAppCompatBackgroundHelper.getSupportBackgroundTintList();
    }
    return null;
  }
  
  public PorterDuff.Mode getSupportBackgroundTintMode()
  {
    AppCompatBackgroundHelper localAppCompatBackgroundHelper = mBackgroundTintHelper;
    if (localAppCompatBackgroundHelper != null) {
      return localAppCompatBackgroundHelper.getSupportBackgroundTintMode();
    }
    return null;
  }
  
  public void onInitializeAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    super.onInitializeAccessibilityEvent(paramAccessibilityEvent);
    paramAccessibilityEvent.setClassName(Button.class.getName());
  }
  
  public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    super.onInitializeAccessibilityNodeInfo(paramAccessibilityNodeInfo);
    paramAccessibilityNodeInfo.setClassName(Button.class.getName());
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    TimePicker localTimePicker = mTimePicker;
    if (localTimePicker != null) {
      localTimePicker.setTime(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    }
  }
  
  protected void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    super.onTextChanged(paramCharSequence, paramInt1, paramInt2, paramInt3);
    paramCharSequence = mTimePicker;
    if ((paramCharSequence != null) && (!android.support.v4.widget.TimePicker.mIs24HourMode) && (paramCharSequence.setEnabled())) {
      mTimePicker.onSaveInstanceState();
    }
  }
  
  public void setAutoSizeTextTypeUniformWithConfiguration(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (android.support.v4.widget.TimePicker.mIs24HourMode)
    {
      super.setAutoSizeTextTypeUniformWithConfiguration(paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    }
    TimePicker localTimePicker = mTimePicker;
    if (localTimePicker != null) {
      localTimePicker.onSaveInstanceState(paramInt1, paramInt2, paramInt3, paramInt4);
    }
  }
  
  public void setAutoSizeTextTypeUniformWithPresetSizes(int[] paramArrayOfInt, int paramInt)
  {
    if (android.support.v4.widget.TimePicker.mIs24HourMode)
    {
      super.setAutoSizeTextTypeUniformWithPresetSizes(paramArrayOfInt, paramInt);
      return;
    }
    TimePicker localTimePicker = mTimePicker;
    if (localTimePicker != null) {
      localTimePicker.setEnabled(paramArrayOfInt, paramInt);
    }
  }
  
  public void setAutoSizeTextTypeWithDefaults(int paramInt)
  {
    if (android.support.v4.widget.TimePicker.mIs24HourMode)
    {
      super.setAutoSizeTextTypeWithDefaults(paramInt);
      return;
    }
    TimePicker localTimePicker = mTimePicker;
    if (localTimePicker != null) {
      localTimePicker.setEnabled(paramInt);
    }
  }
  
  public void setBackgroundDrawable(Drawable paramDrawable)
  {
    super.setBackgroundDrawable(paramDrawable);
    AppCompatBackgroundHelper localAppCompatBackgroundHelper = mBackgroundTintHelper;
    if (localAppCompatBackgroundHelper != null) {
      localAppCompatBackgroundHelper.onSetBackgroundDrawable(paramDrawable);
    }
  }
  
  public void setBackgroundResource(int paramInt)
  {
    super.setBackgroundResource(paramInt);
    AppCompatBackgroundHelper localAppCompatBackgroundHelper = mBackgroundTintHelper;
    if (localAppCompatBackgroundHelper != null) {
      localAppCompatBackgroundHelper.onSetBackgroundResource(paramInt);
    }
  }
  
  public void setCustomSelectionActionModeCallback(ActionMode.Callback paramCallback)
  {
    super.setCustomSelectionActionModeCallback(LayoutManager.a(this, paramCallback));
  }
  
  public void setSupportAllCaps(boolean paramBoolean)
  {
    TimePicker localTimePicker = mTimePicker;
    if (localTimePicker != null) {
      localTimePicker.setAllCaps(paramBoolean);
    }
  }
  
  public void setSupportBackgroundTintList(ColorStateList paramColorStateList)
  {
    AppCompatBackgroundHelper localAppCompatBackgroundHelper = mBackgroundTintHelper;
    if (localAppCompatBackgroundHelper != null) {
      localAppCompatBackgroundHelper.setSupportBackgroundTintList(paramColorStateList);
    }
  }
  
  public void setSupportBackgroundTintMode(PorterDuff.Mode paramMode)
  {
    AppCompatBackgroundHelper localAppCompatBackgroundHelper = mBackgroundTintHelper;
    if (localAppCompatBackgroundHelper != null) {
      localAppCompatBackgroundHelper.setSupportBackgroundTintMode(paramMode);
    }
  }
  
  public void setTextAppearance(Context paramContext, int paramInt)
  {
    super.setTextAppearance(paramContext, paramInt);
    TimePicker localTimePicker = mTimePicker;
    if (localTimePicker != null) {
      localTimePicker.applyStyle(paramContext, paramInt);
    }
  }
  
  public void setTextSize(int paramInt, float paramFloat)
  {
    if (android.support.v4.widget.TimePicker.mIs24HourMode)
    {
      super.setTextSize(paramInt, paramFloat);
      return;
    }
    TimePicker localTimePicker = mTimePicker;
    if (localTimePicker != null) {
      localTimePicker.setTime(paramInt, paramFloat);
    }
  }
}
