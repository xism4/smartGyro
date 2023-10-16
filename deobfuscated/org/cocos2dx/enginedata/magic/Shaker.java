package org.cocos2dx.enginedata.magic;

import android.util.Log;
import group.huawei.android.hwgamesdk.HwGameSDK;
import group.huawei.android.hwgamesdk.HwGameSDK.GameEngineCallBack;
import group.huawei.android.hwgamesdk.HwGameSDK.GameScene;
import group.huawei.android.hwgamesdk.NoExtAPIException;
import org.cocos2dx.enginedata.IEngineDataManager;
import org.cocos2dx.enginedata.IEngineDataManager.GameStatus;
import org.cocos2dx.enginedata.IEngineDataManager.OnSystemCommandListener;

public class Shaker
  implements IEngineDataManager
{
  private static final String threshold = "EngineDataManagerHuawei";
  private boolean e = false;
  private HwGameSDK mGestureDetector = new HwGameSDK();
  private HwGameSDK.GameEngineCallBack matrix = new DownloadsFragment.1(this);
  private IEngineDataManager.OnSystemCommandListener x;
  
  public Shaker() {}
  
  public void destroy() {}
  
  public String getVendorInfo()
  {
    StringBuilder localStringBuilder = new StringBuilder("HuaWei:");
    localStringBuilder.append(mGestureDetector.getApiLevel());
    return localStringBuilder.toString();
  }
  
  public boolean init(IEngineDataManager.OnSystemCommandListener paramOnSystemCommandListener)
  {
    if (paramOnSystemCommandListener == null) {
      return false;
    }
    x = paramOnSystemCommandListener;
    paramOnSystemCommandListener = mGestureDetector;
    try
    {
      int i = paramOnSystemCommandListener.getApiLevel();
      if (i < 1)
      {
        paramOnSystemCommandListener = new StringBuilder("Unsupported function: HwGameSDK.getApiLevel: ");
        paramOnSystemCommandListener.append(i);
        paramOnSystemCommandListener.append(", min: ");
        paramOnSystemCommandListener.append(1);
        Log.d("EngineDataManagerHuawei", paramOnSystemCommandListener.toString());
        return false;
      }
      if (i >= 2) {
        e = true;
      }
      paramOnSystemCommandListener = mGestureDetector;
      HwGameSDK.GameEngineCallBack localGameEngineCallBack = matrix;
      boolean bool = paramOnSystemCommandListener.registerGame(localGameEngineCallBack);
      return bool;
    }
    catch (NoExtAPIException paramOnSystemCommandListener)
    {
      return false;
    }
    catch (NoSuchMethodError paramOnSystemCommandListener) {}
    return false;
  }
  
  public void notifyContinuousFrameLost(int paramInt1, int paramInt2, int paramInt3)
  {
    HwGameSDK localHwGameSDK = mGestureDetector;
    try
    {
      localHwGameSDK.notifyContinuousFpsMissed(paramInt1, paramInt2, paramInt3);
      return;
    }
    catch (NoExtAPIException localNoExtAPIException)
    {
      for (;;) {}
    }
    Log.d("EngineDataManagerHuawei", "Unsupported function: notifyGameStatus");
  }
  
  public void notifyFpsChanged(float paramFloat1, float paramFloat2)
  {
    HwGameSDK localHwGameSDK = mGestureDetector;
    try
    {
      localHwGameSDK.notifyFpsChanged(paramFloat1, paramFloat2);
      return;
    }
    catch (NoExtAPIException localNoExtAPIException)
    {
      for (;;) {}
    }
    Log.d("EngineDataManagerHuawei", "Unsupported function: notifyFpsChanged");
  }
  
  public void notifyGameStatus(IEngineDataManager.GameStatus paramGameStatus, int paramInt1, int paramInt2)
  {
    try
    {
      Object localObject = close();
      int i = paramGameStatus.ordinal();
      i = localObject[i];
      if (i != 1)
      {
        if (i != 2)
        {
          if (i != 3)
          {
            if (i != 4)
            {
              if (i != 5)
              {
                localObject = new StringBuilder("error type: ");
                ((StringBuilder)localObject).append(paramGameStatus);
                Log.e("EngineDataManagerHuawei", ((StringBuilder)localObject).toString());
                return;
              }
              paramGameStatus = HwGameSDK.GameScene.GAME_INSCENE;
            }
            else
            {
              paramGameStatus = HwGameSDK.GameScene.GAME_SCENECHANGE_END;
            }
          }
          else {
            paramGameStatus = HwGameSDK.GameScene.GAME_SCENECHANGE_BEGIN;
          }
        }
        else {
          paramGameStatus = HwGameSDK.GameScene.GAME_LAUNCH_END;
        }
      }
      else {
        paramGameStatus = HwGameSDK.GameScene.GAME_LAUNCH_BEGIN;
      }
      localObject = mGestureDetector;
      ((HwGameSDK)localObject).notifyGameScene(paramGameStatus, paramInt1, paramInt2);
      return;
    }
    catch (NoExtAPIException paramGameStatus)
    {
      for (;;) {}
    }
    Log.d("EngineDataManagerHuawei", "Unsupported function: notifyGameStatus");
  }
  
  public void notifyLowFps(int paramInt1, float paramFloat, int paramInt2)
  {
    HwGameSDK localHwGameSDK = mGestureDetector;
    try
    {
      localHwGameSDK.notifyFpsDx(paramInt1, paramFloat, paramInt2);
      return;
    }
    catch (NoExtAPIException localNoExtAPIException)
    {
      for (;;) {}
    }
    Log.d("EngineDataManagerHuawei", "Unsupported function: notifyGameStatus");
  }
  
  public void pause() {}
  
  public void resume() {}
}
