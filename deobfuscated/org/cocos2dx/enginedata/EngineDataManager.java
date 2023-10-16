package org.cocos2dx.enginedata;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.cocos2dx.enginedata.magic.Shaker;

public class EngineDataManager
  implements IEngineDataManager
{
  private static final String ACTION_UPDATE_ALL = "1.0.0.0";
  private static final int CV_CAP_ANDROID = 1000;
  private static final String PAGE_KEY = "EngineDataManager";
  private List<IEngineDataManager> mStack = new ArrayList();
  
  public EngineDataManager()
  {
    mStack.add(new Shaker());
  }
  
  public void destroy()
  {
    Iterator localIterator = mStack.iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return;
      }
      ((IEngineDataManager)localIterator.next()).destroy();
    }
  }
  
  public String getVendorInfo()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    for (;;)
    {
      if (i >= mStack.size()) {
        return localStringBuilder.toString();
      }
      localStringBuilder.append(((IEngineDataManager)mStack.get(i)).getVendorInfo());
      if (i < mStack.size() - 1) {
        localStringBuilder.append(",");
      }
      i += 1;
    }
  }
  
  public int getVersionCode()
  {
    return 1000;
  }
  
  public String getVersionName()
  {
    return "1.0.0.0";
  }
  
  public boolean init(IEngineDataManager.OnSystemCommandListener paramOnSystemCommandListener)
  {
    Iterator localIterator = mStack.iterator();
    boolean bool = false;
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return bool;
      }
      if (((IEngineDataManager)localIterator.next()).init(paramOnSystemCommandListener)) {
        bool = true;
      }
    }
  }
  
  public void notifyContinuousFrameLost(int paramInt1, int paramInt2, int paramInt3)
  {
    Iterator localIterator = mStack.iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return;
      }
      ((IEngineDataManager)localIterator.next()).notifyContinuousFrameLost(paramInt1, paramInt2, paramInt3);
    }
  }
  
  public void notifyFpsChanged(float paramFloat1, float paramFloat2)
  {
    Iterator localIterator = mStack.iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return;
      }
      ((IEngineDataManager)localIterator.next()).notifyFpsChanged(paramFloat1, paramFloat2);
    }
  }
  
  public void notifyGameStatus(IEngineDataManager.GameStatus paramGameStatus, int paramInt1, int paramInt2)
  {
    Iterator localIterator = mStack.iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return;
      }
      ((IEngineDataManager)localIterator.next()).notifyGameStatus(paramGameStatus, paramInt1, paramInt2);
    }
  }
  
  public void notifyLowFps(int paramInt1, float paramFloat, int paramInt2)
  {
    Iterator localIterator = mStack.iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return;
      }
      ((IEngineDataManager)localIterator.next()).notifyLowFps(paramInt1, paramFloat, paramInt2);
    }
  }
  
  public void pause()
  {
    Iterator localIterator = mStack.iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return;
      }
      ((IEngineDataManager)localIterator.next()).pause();
    }
  }
  
  public void resume()
  {
    Iterator localIterator = mStack.iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return;
      }
      ((IEngineDataManager)localIterator.next()).resume();
    }
  }
}
