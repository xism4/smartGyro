package org.cocos2dx.package_1;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class Cocos2dxEditBoxHelper
{
  private static final String CLASS_NAME = "Cocos2dxEditBoxHelper";
  private static Cocos2dxActivity mCocos2dxActivity;
  private static SparseArray<org.cocos2dx.lib.Cocos2dxEditBox> mEditBoxArray;
  private static ResizeLayout mFrameLayout;
  private static float mPadding;
  private static int mViewTag;
  
  public Cocos2dxEditBoxHelper(ResizeLayout paramResizeLayout)
  {
    mFrameLayout = paramResizeLayout;
    mCocos2dxActivity = (Cocos2dxActivity)Cocos2dxActivity.getContext();
    mEditBoxArray = new SparseArray();
  }
  
  public static void __editBoxEditingChanged(int paramInt, String paramString)
  {
    editBoxEditingChanged(paramInt, paramString);
  }
  
  public static void __editBoxEditingDidBegin(int paramInt)
  {
    editBoxEditingDidBegin(paramInt);
  }
  
  public static void __editBoxEditingDidEnd(int paramInt1, String paramString, int paramInt2)
  {
    editBoxEditingDidEnd(paramInt1, paramString, paramInt2);
  }
  
  public static void closeKeyboard(int paramInt)
  {
    mCocos2dxActivity.runOnUiThread(new AndroidCallableWrapper.2(paramInt));
  }
  
  private static void closeKeyboardOnUiThread(int paramInt)
  {
    if (Looper.myLooper() != Looper.getMainLooper())
    {
      Log.e(CLASS_NAME, "closeKeyboardOnUiThread doesn't run on UI thread!");
      return;
    }
    Object localObject = mCocos2dxActivity;
    localObject = (InputMethodManager)Cocos2dxActivity.getContext().getSystemService("input_method");
    Cocos2dxEditBox localCocos2dxEditBox = (Cocos2dxEditBox)mEditBoxArray.get(paramInt);
    if (localCocos2dxEditBox != null)
    {
      ((InputMethodManager)localObject).hideSoftInputFromWindow(localCocos2dxEditBox.getWindowToken(), 0);
      mCocos2dxActivity.getGLSurfaceView().setSoftKeyboardShown(false);
      mCocos2dxActivity.getGLSurfaceView().requestFocus();
      mCocos2dxActivity.hideVirtualButton();
    }
  }
  
  public static int createEditBox(int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat)
  {
    int i = mViewTag;
    mCocos2dxActivity.runOnUiThread(new Stream(paramFloat, paramInt1, paramInt2, paramInt3, paramInt4, i));
    paramInt1 = mViewTag;
    mViewTag = paramInt1 + 1;
    return paramInt1;
  }
  
  private static native void editBoxEditingChanged(int paramInt, String paramString);
  
  private static native void editBoxEditingDidBegin(int paramInt);
  
  private static native void editBoxEditingDidEnd(int paramInt1, String paramString, int paramInt2);
  
  public static int getPadding(float paramFloat)
  {
    return (int)(mPadding * paramFloat);
  }
  
  public static void openKeyboard(int paramInt)
  {
    mCocos2dxActivity.runOnUiThread(new Fragment(paramInt));
  }
  
  private static void openKeyboardOnUiThread(int paramInt)
  {
    if (Looper.myLooper() != Looper.getMainLooper())
    {
      Log.e(CLASS_NAME, "openKeyboardOnUiThread doesn't run on UI thread!");
      return;
    }
    Object localObject = mCocos2dxActivity;
    localObject = (InputMethodManager)Cocos2dxActivity.getContext().getSystemService("input_method");
    Cocos2dxEditBox localCocos2dxEditBox = (Cocos2dxEditBox)mEditBoxArray.get(paramInt);
    if (localCocos2dxEditBox != null)
    {
      localCocos2dxEditBox.requestFocus();
      mCocos2dxActivity.getGLSurfaceView().requestLayout();
      ((InputMethodManager)localObject).showSoftInput(localCocos2dxEditBox, 0);
      mCocos2dxActivity.getGLSurfaceView().setSoftKeyboardShown(true);
    }
  }
  
  public static void removeEditBox(int paramInt)
  {
    mCocos2dxActivity.runOnUiThread(new ShowcaseView.1(paramInt));
  }
  
  public static void setEditBoxViewRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    mCocos2dxActivity.runOnUiThread(new LayoutManager(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5));
  }
  
  public static void setFont(int paramInt, String paramString, float paramFloat)
  {
    mCocos2dxActivity.runOnUiThread(new BackgroundExecutor.Task(paramInt, paramString, paramFloat));
  }
  
  public static void setFontColor(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    mCocos2dxActivity.runOnUiThread(new ClassWriter(paramInt1, paramInt5, paramInt2, paramInt3, paramInt4));
  }
  
  public static void setInputFlag(int paramInt1, int paramInt2)
  {
    mCocos2dxActivity.runOnUiThread(new SweeperPool.Sweeper(paramInt1, paramInt2));
  }
  
  public static void setInputMode(int paramInt1, int paramInt2)
  {
    mCocos2dxActivity.runOnUiThread(new AgendaListView.2(paramInt1, paramInt2));
  }
  
  public static void setMaxLength(int paramInt1, int paramInt2)
  {
    mCocos2dxActivity.runOnUiThread(new AddSerie.2(paramInt1, paramInt2));
  }
  
  public static void setPlaceHolderText(int paramInt, String paramString)
  {
    mCocos2dxActivity.runOnUiThread(new Slider(paramInt, paramString));
  }
  
  public static void setPlaceHolderTextColor(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    mCocos2dxActivity.runOnUiThread(new MethodWriter(paramInt1, paramInt5, paramInt2, paramInt3, paramInt4));
  }
  
  public static void setReturnType(int paramInt1, int paramInt2)
  {
    mCocos2dxActivity.runOnUiThread(new RemoteAppenderStreamClient(paramInt1, paramInt2));
  }
  
  public static void setText(int paramInt, String paramString)
  {
    mCocos2dxActivity.runOnUiThread(new MainActivity.5(paramInt, paramString));
  }
  
  public static void setTextHorizontalAlignment(int paramInt1, int paramInt2)
  {
    mCocos2dxActivity.runOnUiThread(new FileBrowser.11(paramInt1, paramInt2));
  }
  
  public static void setVisible(int paramInt, boolean paramBoolean)
  {
    mCocos2dxActivity.runOnUiThread(new Overlay(paramInt, paramBoolean));
  }
}
