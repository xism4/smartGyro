package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.widget.LayoutManager;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.ActionMode.Callback;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.widget.TextView;
import com.org.android.view.TintableBackgroundView;
import com.org.v4.util.R.attr;

public class AppCompatEditText
  extends EditText
  implements TintableBackgroundView
{
  private final AppCompatBackgroundHelper mBackgroundTintHelper = new AppCompatBackgroundHelper(this);
  private final TimePicker mTextHelper;
  
  public AppCompatEditText(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.editTextStyle);
  }
  
  public AppCompatEditText(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(TintContextWrapper.wrap(paramContext), paramAttributeSet, paramInt);
    mBackgroundTintHelper.loadFromAttributes(paramAttributeSet, paramInt);
    mTextHelper = new TimePicker(this);
    mTextHelper.applyStyle(paramAttributeSet, paramInt);
    mTextHelper.applyCompoundDrawablesTints();
  }
  
  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    Object localObject = mBackgroundTintHelper;
    if (localObject != null) {
      ((AppCompatBackgroundHelper)localObject).applySupportBackgroundTint();
    }
    localObject = mTextHelper;
    if (localObject != null) {
      ((TimePicker)localObject).applyCompoundDrawablesTints();
    }
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
  
  public Editable getText()
  {
    if (Build.VERSION.SDK_INT >= 28) {
      return super.getText();
    }
    return super.getEditableText();
  }
  
  public InputConnection onCreateInputConnection(EditorInfo paramEditorInfo)
  {
    InputConnection localInputConnection = super.onCreateInputConnection(paramEditorInfo);
    Resources.validate(localInputConnection, paramEditorInfo, this);
    return localInputConnection;
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
    TimePicker localTimePicker = mTextHelper;
    if (localTimePicker != null) {
      localTimePicker.applyStyle(paramContext, paramInt);
    }
  }
}
