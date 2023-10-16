package org.cocos2dx.package_1;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class Cocos2dxTextInputWrapper
  implements TextWatcher, TextView.OnEditorActionListener
{
  private static final String PAGE_KEY = "Cocos2dxTextInputWrapper";
  private final Cocos2dxGLSurfaceView mCocos2dxGLSurfaceView;
  private String mOriginText;
  private String mText;
  
  public Cocos2dxTextInputWrapper(Cocos2dxGLSurfaceView paramCocos2dxGLSurfaceView)
  {
    mCocos2dxGLSurfaceView = paramCocos2dxGLSurfaceView;
  }
  
  private boolean isFullScreenEdit()
  {
    return ((InputMethodManager)mCocos2dxGLSurfaceView.getCocos2dxEditText().getContext().getSystemService("input_method")).isFullscreenMode();
  }
  
  public void afterTextChanged(Editable paramEditable)
  {
    if (isFullScreenEdit()) {
      return;
    }
    int i = 0;
    int j = 0;
    int k;
    for (;;)
    {
      k = i;
      if (i >= mText.length()) {
        break;
      }
      k = i;
      if (j >= paramEditable.length()) {
        break;
      }
      if (mText.charAt(i) != paramEditable.charAt(j))
      {
        k = i;
        break;
      }
      i += 1;
      j += 1;
    }
    while (k < mText.length())
    {
      mCocos2dxGLSurfaceView.deleteBackward();
      k += 1;
    }
    if (paramEditable.length() - j > 0)
    {
      String str = paramEditable.subSequence(j, paramEditable.length()).toString();
      mCocos2dxGLSurfaceView.insertText(str);
    }
    mText = paramEditable.toString();
  }
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    mText = paramCharSequence.toString();
  }
  
  public boolean onEditorAction(TextView paramTextView, int paramInt, KeyEvent paramKeyEvent)
  {
    if ((mCocos2dxGLSurfaceView.getCocos2dxEditText() == paramTextView) && (isFullScreenEdit()))
    {
      paramKeyEvent = mOriginText;
      if (paramKeyEvent != null)
      {
        int i = paramKeyEvent.length();
        while (i > 0)
        {
          mCocos2dxGLSurfaceView.deleteBackward();
          i -= 1;
        }
      }
      String str = paramTextView.getText().toString();
      paramTextView = str;
      paramKeyEvent = paramTextView;
      if (str != null)
      {
        if (str.compareTo("") == 0) {
          paramTextView = "\n";
        }
        paramKeyEvent = paramTextView;
        if ('\n' != paramTextView.charAt(paramTextView.length() - 1))
        {
          paramKeyEvent = new StringBuilder();
          paramKeyEvent.append(paramTextView);
          paramKeyEvent.append('\n');
          paramKeyEvent = paramKeyEvent.toString();
        }
      }
      mCocos2dxGLSurfaceView.insertText(paramKeyEvent);
    }
    if (paramInt == 6) {
      mCocos2dxGLSurfaceView.requestFocus();
    }
    return false;
  }
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void setOriginText(String paramString)
  {
    mOriginText = paramString;
  }
}
