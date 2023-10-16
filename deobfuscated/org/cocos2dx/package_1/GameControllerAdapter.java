package org.cocos2dx.package_1;

import java.util.ArrayList;

public class GameControllerAdapter
{
  private static ArrayList<Runnable> sRunnableFrameStartList;
  
  public GameControllerAdapter() {}
  
  public static void addRunnableToFrameStartList(Runnable paramRunnable)
  {
    if (sRunnableFrameStartList == null) {
      sRunnableFrameStartList = new ArrayList();
    }
    sRunnableFrameStartList.add(paramRunnable);
  }
  
  private static native void nativeControllerAxisEvent(String paramString, int paramInt1, int paramInt2, float paramFloat, boolean paramBoolean);
  
  private static native void nativeControllerButtonEvent(String paramString, int paramInt1, int paramInt2, boolean paramBoolean1, float paramFloat, boolean paramBoolean2);
  
  private static native void nativeControllerConnected(String paramString, int paramInt);
  
  private static native void nativeControllerDisconnected(String paramString, int paramInt);
  
  public static void onAxisEvent(String paramString, int paramInt1, int paramInt2, float paramFloat, boolean paramBoolean)
  {
    Cocos2dxHelper.runOnGLThread(new Notification.1(paramString, paramInt1, paramInt2, paramFloat, paramBoolean));
  }
  
  public static void onButtonEvent(String paramString, int paramInt1, int paramInt2, boolean paramBoolean1, float paramFloat, boolean paramBoolean2)
  {
    Cocos2dxHelper.runOnGLThread(new Item(paramString, paramInt1, paramInt2, paramBoolean1, paramFloat, paramBoolean2));
  }
  
  public static void onConnected(String paramString, int paramInt)
  {
    Cocos2dxHelper.runOnGLThread(new Relay(paramString, paramInt));
  }
  
  public static void onDisconnected(String paramString, int paramInt)
  {
    Cocos2dxHelper.runOnGLThread(new Client(paramString, paramInt));
  }
  
  public static void onDrawFrameStart()
  {
    ArrayList localArrayList = sRunnableFrameStartList;
    if (localArrayList != null)
    {
      int j = localArrayList.size();
      int i = 0;
      while (i < j)
      {
        ((Runnable)sRunnableFrameStartList.get(i)).run();
        i += 1;
      }
    }
  }
  
  public static void removeRunnableFromFrameStartList(Runnable paramRunnable)
  {
    ArrayList localArrayList = sRunnableFrameStartList;
    if (localArrayList != null) {
      localArrayList.remove(paramRunnable);
    }
  }
}
