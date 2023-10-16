package org.cocos2dx.package_1;

import android.opengl.GLSurfaceView.Renderer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class Cocos2dxRenderer
  implements GLSurfaceView.Renderer
{
  private static final long NANOSECONDSPERMICROSECOND = 1000000L;
  private static final long NANOSECONDSPERSECOND = 1000000000L;
  private static long sAnimationInterval;
  private long mLastTickInNanoSeconds;
  private boolean mNativeInitCompleted = false;
  private int mScreenHeight;
  private int mScreenWidth;
  
  public Cocos2dxRenderer() {}
  
  private static native void nativeDeleteBackward();
  
  private static native String nativeGetContentText();
  
  private static native void nativeInit(int paramInt1, int paramInt2);
  
  private static native void nativeInsertText(String paramString);
  
  private static native boolean nativeKeyEvent(int paramInt, boolean paramBoolean);
  
  private static native void nativeOnPause();
  
  private static native void nativeOnResume();
  
  private static native void nativeOnSurfaceChanged(int paramInt1, int paramInt2);
  
  private static native void nativeRender();
  
  private static native void nativeTouchesBegin(int paramInt, float paramFloat1, float paramFloat2);
  
  private static native void nativeTouchesCancel(int[] paramArrayOfInt, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2);
  
  private static native void nativeTouchesEnd(int paramInt, float paramFloat1, float paramFloat2);
  
  private static native void nativeTouchesMove(int[] paramArrayOfInt, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2);
  
  public static void setAnimationInterval(float paramFloat)
  {
    sAnimationInterval = (paramFloat * 1.0E9F);
  }
  
  public String getContentText()
  {
    return nativeGetContentText();
  }
  
  public void handleActionCancel(int[] paramArrayOfInt, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2)
  {
    nativeTouchesCancel(paramArrayOfInt, paramArrayOfFloat1, paramArrayOfFloat2);
  }
  
  public void handleActionDown(int paramInt, float paramFloat1, float paramFloat2)
  {
    nativeTouchesBegin(paramInt, paramFloat1, paramFloat2);
  }
  
  public void handleActionMove(int[] paramArrayOfInt, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2)
  {
    nativeTouchesMove(paramArrayOfInt, paramArrayOfFloat1, paramArrayOfFloat2);
  }
  
  public void handleActionUp(int paramInt, float paramFloat1, float paramFloat2)
  {
    nativeTouchesEnd(paramInt, paramFloat1, paramFloat2);
  }
  
  public void handleDeleteBackward() {}
  
  public void handleInsertText(String paramString)
  {
    nativeInsertText(paramString);
  }
  
  public void handleKeyDown(int paramInt)
  {
    nativeKeyEvent(paramInt, true);
  }
  
  public void handleKeyUp(int paramInt)
  {
    nativeKeyEvent(paramInt, false);
  }
  
  public void handleOnPause()
  {
    if (!mNativeInitCompleted) {
      return;
    }
    Cocos2dxHelper.onEnterBackground();
    nativeOnPause();
  }
  
  public void handleOnResume()
  {
    Cocos2dxHelper.onEnterForeground();
    nativeOnResume();
  }
  
  public void onDrawFrame(GL10 paramGL10)
  {
    if ((float)sAnimationInterval <= 1.6666668E7F) {}
    for (;;)
    {
      nativeRender();
      return;
      long l1 = System.nanoTime() - mLastTickInNanoSeconds;
      long l2 = sAnimationInterval;
      if (l1 < l2) {
        l1 = (l2 - l1) / 1000000L;
      }
      try
      {
        Thread.sleep(l1);
        mLastTickInNanoSeconds = System.nanoTime();
      }
      catch (Exception paramGL10)
      {
        for (;;) {}
      }
    }
  }
  
  public void onSurfaceChanged(GL10 paramGL10, int paramInt1, int paramInt2)
  {
    nativeOnSurfaceChanged(paramInt1, paramInt2);
  }
  
  public void onSurfaceCreated(GL10 paramGL10, EGLConfig paramEGLConfig)
  {
    nativeInit(mScreenWidth, mScreenHeight);
    mLastTickInNanoSeconds = System.nanoTime();
    mNativeInitCompleted = true;
  }
  
  public void setScreenWidthAndHeight(int paramInt1, int paramInt2)
  {
    mScreenWidth = paramInt1;
    mScreenHeight = paramInt2;
  }
}