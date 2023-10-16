package org.cocos2dx.package_1;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class Cocos2dxGLSurfaceView
  extends GLSurfaceView
{
  private static final int HANDLER_CLOSE_IME_KEYBOARD = 3;
  private static final int HANDLER_OPEN_IME_KEYBOARD = 2;
  private static final String logTag = "Cocos2dxGLSurfaceView";
  private static Cocos2dxGLSurfaceView mCocos2dxGLSurfaceView;
  private static Cocos2dxTextInputWrapper sCocos2dxTextInputWraper;
  private static Handler sHandler;
  private Cocos2dxEditBox mCocos2dxEditText;
  private Cocos2dxRenderer mCocos2dxRenderer;
  private boolean mMultipleTouchEnabled = true;
  private boolean mSoftKeyboardShown = false;
  
  public Cocos2dxGLSurfaceView(Context paramContext)
  {
    super(paramContext);
    initView();
  }
  
  public Cocos2dxGLSurfaceView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initView();
  }
  
  public static void closeIMEKeyboard()
  {
    Message localMessage = new Message();
    what = 3;
    sHandler.sendMessage(localMessage);
  }
  
  private static void dumpMotionEvent(MotionEvent paramMotionEvent)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = paramMotionEvent.getAction();
    int j = i & 0xFF;
    localStringBuilder.append("event ACTION_");
    localStringBuilder.append(new String[] { "DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE", "POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?" }[j]);
    if ((j == 5) || (j == 6))
    {
      localStringBuilder.append("(pid ");
      localStringBuilder.append(i >> 8);
      localStringBuilder.append(")");
    }
    localStringBuilder.append("[");
    i = 0;
    while (i < paramMotionEvent.getPointerCount())
    {
      localStringBuilder.append("#");
      localStringBuilder.append(i);
      localStringBuilder.append("(pid ");
      localStringBuilder.append(paramMotionEvent.getPointerId(i));
      localStringBuilder.append(")=");
      localStringBuilder.append((int)paramMotionEvent.getX(i));
      localStringBuilder.append(",");
      localStringBuilder.append((int)paramMotionEvent.getY(i));
      j = i + 1;
      i = j;
      if (j < paramMotionEvent.getPointerCount())
      {
        localStringBuilder.append(";");
        i = j;
      }
    }
    localStringBuilder.append("]");
    Log.d(logTag, localStringBuilder.toString());
  }
  
  private String getContentText()
  {
    return mCocos2dxRenderer.getContentText();
  }
  
  public static Cocos2dxGLSurfaceView getInstance()
  {
    return mCocos2dxGLSurfaceView;
  }
  
  public static void openIMEKeyboard()
  {
    Message localMessage = new Message();
    what = 2;
    obj = mCocos2dxGLSurfaceView.getContentText();
    sHandler.sendMessage(localMessage);
  }
  
  public static void queueAccelerometer(float paramFloat1, float paramFloat2, float paramFloat3, long paramLong)
  {
    mCocos2dxGLSurfaceView.queueEvent(new SimpleXYSeries(paramFloat1, paramFloat2, paramFloat3, paramLong));
  }
  
  public void deleteBackward()
  {
    queueEvent(new ImageLoader.3(this));
  }
  
  public Cocos2dxEditBox getCocos2dxEditText()
  {
    return mCocos2dxEditText;
  }
  
  protected void initView()
  {
    setEGLContextClientVersion(2);
    setFocusableInTouchMode(true);
    mCocos2dxGLSurfaceView = this;
    sCocos2dxTextInputWraper = new Cocos2dxTextInputWrapper(this);
    sHandler = new Launcher.17(this);
  }
  
  public void insertText(String paramString)
  {
    queueEvent(new XMPPService.4(this, paramString));
  }
  
  public boolean isMultipleTouchEnabled()
  {
    return mMultipleTouchEnabled;
  }
  
  public boolean isSoftKeyboardShown()
  {
    return mSoftKeyboardShown;
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt != 4)
    {
      if ((paramInt == 66) || (paramInt == 82) || (paramInt == 85)) {}
    }
    else {
      switch (paramInt)
      {
      default: 
        return super.onKeyDown(paramInt, paramKeyEvent);
        Cocos2dxVideoHelper.mVideoHandler.sendEmptyMessage(1000);
      }
    }
    queueEvent(new Session(this, paramInt));
    return true;
  }
  
  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt != 4) && (paramInt != 66) && (paramInt != 82) && (paramInt != 85)) {
      switch (paramInt)
      {
      default: 
        return super.onKeyUp(paramInt, paramKeyEvent);
      }
    }
    queueEvent(new MainActivity.14(this, paramInt));
    return true;
  }
  
  public void onPause()
  {
    queueEvent(new Replay(this));
    setRenderMode(0);
  }
  
  public void onResume()
  {
    super.onResume();
    setRenderMode(1);
    queueEvent(new WalletActivity.1(this));
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (!isInEditMode()) {
      mCocos2dxRenderer.setScreenWidthAndHeight(paramInt1, paramInt2);
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a15 = a14\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n");
  }
  
  public void setCocos2dxEditText(Cocos2dxEditBox paramCocos2dxEditBox)
  {
    mCocos2dxEditText = paramCocos2dxEditBox;
    paramCocos2dxEditBox = mCocos2dxEditText;
    if (paramCocos2dxEditBox != null)
    {
      Cocos2dxTextInputWrapper localCocos2dxTextInputWrapper = sCocos2dxTextInputWraper;
      if (localCocos2dxTextInputWrapper != null)
      {
        paramCocos2dxEditBox.setOnEditorActionListener(localCocos2dxTextInputWrapper);
        requestFocus();
      }
    }
  }
  
  public void setCocos2dxRenderer(Cocos2dxRenderer paramCocos2dxRenderer)
  {
    mCocos2dxRenderer = paramCocos2dxRenderer;
    setRenderer(mCocos2dxRenderer);
  }
  
  public void setMultipleTouchEnabled(boolean paramBoolean)
  {
    mMultipleTouchEnabled = paramBoolean;
  }
  
  public void setSoftKeyboardShown(boolean paramBoolean)
  {
    mSoftKeyboardShown = paramBoolean;
  }
}
