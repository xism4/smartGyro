package org.cocos2dx.package_1;

import android.content.Context;
import android.graphics.Typeface;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;

public class Cocos2dxEditBox
  extends EditText
{
  public static final int kEndActionNext = 1;
  public static final int kEndActionReturn = 3;
  public static final int kEndActionUnknown = 0;
  private static final int kTextHorizontalAlignmentCenter = 1;
  private static final int kTextHorizontalAlignmentLeft = 0;
  private static final int kTextHorizontalAlignmentRight = 2;
  private static final int kTextVerticalAlignmentBottom = 2;
  private static final int kTextVerticalAlignmentCenter = 1;
  private static final int kTextVerticalAlignmentTop = 0;
  private Boolean changedTextProgrammatically = Boolean.valueOf(false);
  int endAction = 0;
  private final int kEditBoxInputFlagInitialCapsAllCharacters = 4;
  private final int kEditBoxInputFlagInitialCapsSentence = 3;
  private final int kEditBoxInputFlagInitialCapsWord = 2;
  private final int kEditBoxInputFlagLowercaseAllCharacters = 5;
  private final int kEditBoxInputFlagPassword = 0;
  private final int kEditBoxInputFlagSensitive = 1;
  private final int kEditBoxInputModeAny = 0;
  private final int kEditBoxInputModeDecimal = 5;
  private final int kEditBoxInputModeEmailAddr = 1;
  private final int kEditBoxInputModeNumeric = 2;
  private final int kEditBoxInputModePhoneNumber = 3;
  private final int kEditBoxInputModeSingleLine = 6;
  private final int kEditBoxInputModeUrl = 4;
  private final int kKeyboardReturnTypeDefault = 0;
  private final int kKeyboardReturnTypeDone = 1;
  private final int kKeyboardReturnTypeGo = 4;
  private final int kKeyboardReturnTypeNext = 5;
  private final int kKeyboardReturnTypeSearch = 3;
  private final int kKeyboardReturnTypeSend = 2;
  private int mInputFlagConstraints;
  private int mInputModeConstraints;
  private int mMaxLength;
  private float mScaleX;
  
  public Cocos2dxEditBox(Context paramContext)
  {
    super(paramContext);
  }
  
  public Boolean getChangedTextProgrammatically()
  {
    return changedTextProgrammatically;
  }
  
  public float getOpenGLViewScaleX()
  {
    return mScaleX;
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt != 4) {
      return super.onKeyDown(paramInt, paramKeyEvent);
    }
    ((Cocos2dxActivity)getContext()).getGLSurfaceView().requestFocus();
    return true;
  }
  
  public boolean onKeyPreIme(int paramInt, KeyEvent paramKeyEvent)
  {
    return super.onKeyPreIme(paramInt, paramKeyEvent);
  }
  
  public void setChangedTextProgrammatically(Boolean paramBoolean)
  {
    changedTextProgrammatically = paramBoolean;
  }
  
  public void setEditBoxViewRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(-2, -2);
    leftMargin = paramInt1;
    topMargin = paramInt2;
    width = paramInt3;
    height = paramInt4;
    gravity = 51;
    setLayoutParams(localLayoutParams);
  }
  
  public void setInputFlag(int paramInt)
  {
    if (paramInt != 0)
    {
      if (paramInt != 1)
      {
        if (paramInt != 2)
        {
          if (paramInt != 3)
          {
            if (paramInt != 4)
            {
              if (paramInt != 5) {
                break label97;
              }
              mInputFlagConstraints = 1;
              break label97;
            }
            paramInt = 4096;
          }
          else
          {
            paramInt = 16384;
          }
        }
        else {
          paramInt = 8192;
        }
      }
      else {
        paramInt = 524288;
      }
      mInputFlagConstraints = paramInt;
    }
    else
    {
      mInputFlagConstraints = 129;
      setTypeface(Typeface.DEFAULT);
      setTransformationMethod(new PasswordTransformationMethod());
    }
    label97:
    setInputType(mInputFlagConstraints | mInputModeConstraints);
  }
  
  public void setInputMode(int paramInt)
  {
    setTextHorizontalAlignment(0);
    setTextVerticalAlignment(1);
    switch (paramInt)
    {
    default: 
      break;
    case 6: 
      mInputModeConstraints = 1;
      break;
    case 5: 
      paramInt = 12290;
      break;
    case 4: 
      paramInt = 17;
      break;
    case 3: 
      paramInt = 3;
      break;
    case 2: 
      paramInt = 4098;
      break;
    case 1: 
      paramInt = 33;
      break;
    case 0: 
      setTextVerticalAlignment(0);
      paramInt = 131073;
    }
    mInputModeConstraints = paramInt;
    setInputType(mInputModeConstraints | mInputFlagConstraints);
  }
  
  public void setMaxLength(int paramInt)
  {
    mMaxLength = paramInt;
    setFilters(new InputFilter[] { new InputFilter.LengthFilter(mMaxLength) });
  }
  
  public void setMultilineEnabled(boolean paramBoolean)
  {
    mInputModeConstraints |= 0x20000;
  }
  
  public void setOpenGLViewScaleX(float paramFloat)
  {
    mScaleX = paramFloat;
  }
  
  public void setReturnType(int paramInt)
  {
    if (paramInt != 0)
    {
      if (paramInt == 1) {
        break label60;
      }
      if (paramInt == 2) {
        break label54;
      }
      if (paramInt == 3) {
        break label48;
      }
      if (paramInt == 4) {
        break label42;
      }
      if (paramInt == 5) {}
    }
    else
    {
      setImeOptions(268435457);
      return;
    }
    paramInt = 268435461;
    break label63;
    label42:
    paramInt = 268435458;
    break label63;
    label48:
    paramInt = 268435459;
    break label63;
    label54:
    paramInt = 268435460;
    break label63;
    label60:
    paramInt = 268435462;
    label63:
    setImeOptions(paramInt);
  }
  
  public void setTextHorizontalAlignment(int paramInt)
  {
    int i = getGravity();
    if (paramInt != 0)
    {
      if (paramInt == 1) {
        break label39;
      }
      if (paramInt == 2) {}
    }
    else
    {
      paramInt = i & 0xFFFFFFFA | 0x3;
      break label49;
    }
    paramInt = i & 0xFFFFFFFC | 0x5;
    break label49;
    label39:
    paramInt = i & 0xFFFFFFFA & 0xFFFFFFFC | 0x1;
    label49:
    setGravity(paramInt);
  }
  
  public void setTextVerticalAlignment(int paramInt)
  {
    int i = getGravity();
    int j = Cocos2dxEditBoxHelper.getPadding(mScaleX);
    if (paramInt != 0)
    {
      if ((paramInt == 1) || (paramInt != 2))
      {
        setPadding(j, 0, 0, j / 2);
        paramInt = i & 0xFFFFFFCF & 0xFFFFFFAF | 0x10;
      }
      else
      {
        paramInt = i & 0xFFFFFFCF | 0x50;
      }
    }
    else
    {
      setPadding(j, j * 3 / 4, 0, 0);
      paramInt = i & 0xFFFFFFAF | 0x30;
    }
    setGravity(paramInt);
  }
}
