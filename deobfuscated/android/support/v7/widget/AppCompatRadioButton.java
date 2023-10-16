package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.TintableCompoundButton;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import com.org.v4.text.view.Resources;
import com.org.v4.util.R.attr;

public class AppCompatRadioButton
  extends RadioButton
  implements TintableCompoundButton
{
  private final AppCompatCompoundButtonHelper mCompoundButtonHelper = new AppCompatCompoundButtonHelper(this);
  private final TimePicker mTimePicker;
  
  public AppCompatRadioButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.radioButtonStyle);
  }
  
  public AppCompatRadioButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(TintContextWrapper.wrap(paramContext), paramAttributeSet, paramInt);
    mCompoundButtonHelper.loadFromAttributes(paramAttributeSet, paramInt);
    mTimePicker = new TimePicker(this);
    mTimePicker.applyStyle(paramAttributeSet, paramInt);
  }
  
  public int getCompoundPaddingLeft()
  {
    int j = super.getCompoundPaddingLeft();
    AppCompatCompoundButtonHelper localAppCompatCompoundButtonHelper = mCompoundButtonHelper;
    int i = j;
    if (localAppCompatCompoundButtonHelper != null) {
      i = localAppCompatCompoundButtonHelper.getCompoundPaddingLeft(j);
    }
    return i;
  }
  
  public ColorStateList getSupportButtonTintList()
  {
    AppCompatCompoundButtonHelper localAppCompatCompoundButtonHelper = mCompoundButtonHelper;
    if (localAppCompatCompoundButtonHelper != null) {
      return localAppCompatCompoundButtonHelper.getSupportButtonTintList();
    }
    return null;
  }
  
  public PorterDuff.Mode getSupportButtonTintMode()
  {
    AppCompatCompoundButtonHelper localAppCompatCompoundButtonHelper = mCompoundButtonHelper;
    if (localAppCompatCompoundButtonHelper != null) {
      return localAppCompatCompoundButtonHelper.getSupportButtonTintMode();
    }
    return null;
  }
  
  public void setButtonDrawable(int paramInt)
  {
    setButtonDrawable(Resources.getDrawable(getContext(), paramInt));
  }
  
  public void setButtonDrawable(Drawable paramDrawable)
  {
    super.setButtonDrawable(paramDrawable);
    paramDrawable = mCompoundButtonHelper;
    if (paramDrawable != null) {
      paramDrawable.onSetButtonDrawable();
    }
  }
  
  public void setSupportButtonTintList(ColorStateList paramColorStateList)
  {
    AppCompatCompoundButtonHelper localAppCompatCompoundButtonHelper = mCompoundButtonHelper;
    if (localAppCompatCompoundButtonHelper != null) {
      localAppCompatCompoundButtonHelper.setSupportButtonTintList(paramColorStateList);
    }
  }
  
  public void setSupportButtonTintMode(PorterDuff.Mode paramMode)
  {
    AppCompatCompoundButtonHelper localAppCompatCompoundButtonHelper = mCompoundButtonHelper;
    if (localAppCompatCompoundButtonHelper != null) {
      localAppCompatCompoundButtonHelper.setSupportButtonTintMode(paramMode);
    }
  }
}
