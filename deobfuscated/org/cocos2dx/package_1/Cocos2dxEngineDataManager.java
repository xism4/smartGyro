package org.cocos2dx.package_1;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import org.cocos2dx.enginedata.EngineDataManager;
import org.cocos2dx.enginedata.IEngineDataManager.GameStatus;

public class Cocos2dxEngineDataManager
{
  private static final String PAGE_KEY = "CCEngineDataManager";
  private static boolean sIsEnabled = true;
  private static boolean sIsInited = false;
  private static EngineDataManager sManager = new EngineDataManager();
  
  private Cocos2dxEngineDataManager() {}
  
  private static IEngineDataManager.GameStatus convertIntegerToGameStatus(int paramInt)
  {
    IEngineDataManager.GameStatus[] arrayOfGameStatus = IEngineDataManager.GameStatus.values();
    int j = arrayOfGameStatus.length;
    int i = 0;
    while (i < j)
    {
      IEngineDataManager.GameStatus localGameStatus = arrayOfGameStatus[i];
      if (paramInt == localGameStatus.ordinal()) {
        return localGameStatus;
      }
      i += 1;
    }
    return IEngineDataManager.GameStatus.INVALID;
  }
  
  public static void destroy()
  {
    if (sIsEnabled) {
      sManager.destroy();
    }
  }
  
  public static void disable()
  {
    sIsEnabled = false;
  }
  
  public static String getVendorInfo()
  {
    if (sIsEnabled) {
      return sManager.getVendorInfo();
    }
    return "";
  }
  
  public static boolean init(Context paramContext, GLSurfaceView paramGLSurfaceView)
  {
    if (paramContext == null) {}
    for (paramContext = "Context is null";; paramContext = "glSurfaceView is null")
    {
      Log.e("CCEngineDataManager", paramContext);
      return false;
      if (paramGLSurfaceView != null) {
        break;
      }
    }
    paramContext = new DownloadsFragment.1(paramGLSurfaceView);
    if (sIsEnabled) {
      sIsInited = sManager.init(paramContext);
    }
    nativeSetSupportOptimization(sIsInited);
    return sIsInited;
  }
  
  public static boolean isInited()
  {
    return sIsInited;
  }
  
  private static native void nativeOnChangeContinuousFrameLostConfig(int paramInt1, int paramInt2);
  
  private static native void nativeOnChangeExpectedFps(int paramInt);
  
  private static native void nativeOnChangeLowFpsConfig(int paramInt, float paramFloat);
  
  private static native void nativeOnChangeMuteEnabled(boolean paramBoolean);
  
  private static native void nativeOnChangeSpecialEffectLevel(int paramInt);
  
  private static native void nativeOnQueryFps(int[] paramArrayOfInt1, int[] paramArrayOfInt2);
  
  private static native void nativeSetSupportOptimization(boolean paramBoolean);
  
  public static void notifyContinuousFrameLost(int paramInt1, int paramInt2, int paramInt3)
  {
    if (!sIsEnabled)
    {
      nativeSetSupportOptimization(false);
      return;
    }
    sManager.notifyContinuousFrameLost(paramInt1, paramInt2, paramInt3);
  }
  
  public static void notifyFpsChanged(float paramFloat1, float paramFloat2)
  {
    if (!sIsEnabled)
    {
      nativeSetSupportOptimization(false);
      return;
    }
    sManager.notifyFpsChanged(paramFloat1, paramFloat2);
  }
  
  public static void notifyGameStatus(int paramInt1, int paramInt2, int paramInt3)
  {
    if (!sIsEnabled)
    {
      nativeSetSupportOptimization(false);
      return;
    }
    Object localObject = convertIntegerToGameStatus(paramInt1);
    if (localObject == IEngineDataManager.GameStatus.INVALID)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Invalid game status: ");
      ((StringBuilder)localObject).append(paramInt1);
      Log.e("CCEngineDataManager", ((StringBuilder)localObject).toString());
      return;
    }
    sManager.notifyGameStatus((IEngineDataManager.GameStatus)localObject, paramInt2, paramInt3);
  }
  
  public static void notifyLowFps(int paramInt1, float paramFloat, int paramInt2)
  {
    if (!sIsEnabled)
    {
      nativeSetSupportOptimization(false);
      return;
    }
    sManager.notifyLowFps(paramInt1, paramFloat, paramInt2);
  }
  
  public static void pause()
  {
    if (sIsEnabled) {
      sManager.pause();
    }
  }
  
  public static void resume()
  {
    if (sIsEnabled) {
      sManager.resume();
    }
  }
}