package android.support.v7.widget;

import android.content.Context;
import android.support.v4.widget.LayoutManager;
import android.util.AttributeSet;
import android.view.ActionMode.Callback;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;
import android.widget.TextView;

public class AppCompatCheckedTextView
  extends CheckedTextView
{
  private static final int[] TINT_ATTRS = { 16843016 };
  private final TimePicker mTextHelper = new TimePicker(this);
  
  public AppCompatCheckedTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 16843720);
  }
  
  public AppCompatCheckedTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(TintContextWrapper.wrap(paramContext), paramAttributeSet, paramInt);
    mTextHelper.applyStyle(paramAttributeSet, paramInt);
    mTextHelper.applyCompoundDrawablesTints();
    paramContext = TintTypedArray.obtainStyledAttributes(getContext(), paramAttributeSet, TINT_ATTRS, paramInt, 0);
    setCheckMarkDrawable(paramContext.getDrawable(0));
    paramContext.recycle();
  }
  
  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    TimePicker localTimePicker = mTextHelper;
    if (localTimePicker != null) {
      localTimePicker.applyCompoundDrawablesTints();
    }
  }
  
  public InputConnection onCreateInputConnection(EditorInfo paramEditorInfo)
  {
    InputConnection localInputConnection = super.onCreateInputConnection(paramEditorInfo);
    Resources.validate(localInputConnection, paramEditorInfo, this);
    return localInputConnection;
  }
  
  public void setCheckMarkDrawable(int paramInt)
  {
    setCheckMarkDrawable(com.org.v4.text.view.Resources.getDrawable(getContext(), paramInt));
  }
  
  public void setCustomSelectionActionModeCallback(ActionMode.Callback paramCallback)
  {
    super.setCustomSelectionActionModeCallback(LayoutManager.a(this, paramCallback));
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
