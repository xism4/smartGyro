package org.cocos2dx.package_1;

import android.app.Activity;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import javax.net.ssl.SSLException;
import lombok.eclipse.handlers.http.AsyncHttpClient;

public class Cocos2dxDownloader
{
  private static HashMap<String, Boolean> _resumingSupport = new HashMap();
  private int _countOfMaxProcessingTasks;
  private AsyncHttpClient _httpClient = new AsyncHttpClient();
  private int _runningTaskCount = 0;
  private HashMap _taskMap = new HashMap();
  private Queue<Runnable> _taskQueue = new LinkedList();
  private String _tempFileNameSufix;
  private int events;
  
  public Cocos2dxDownloader() {}
  
  public static void cancelAllRequests(Cocos2dxDownloader paramCocos2dxDownloader)
  {
    Cocos2dxHelper.getActivity().runOnUiThread(new SpiceManager.7(paramCocos2dxDownloader));
  }
  
  public static Cocos2dxDownloader createDownloader(int paramInt1, int paramInt2, String paramString, int paramInt3)
  {
    Cocos2dxDownloader localCocos2dxDownloader = new Cocos2dxDownloader();
    events = paramInt1;
    _httpClient.setEnableRedirects(true);
    if (paramInt2 > 0) {
      _httpClient.setTimeout(paramInt2 * 1000);
    }
    AsyncHttpClient localAsyncHttpClient = _httpClient;
    AsyncHttpClient.allowRetryExceptionClass(SSLException.class);
    _tempFileNameSufix = paramString;
    _countOfMaxProcessingTasks = paramInt3;
    return localCocos2dxDownloader;
  }
  
  public static void createTask(Cocos2dxDownloader paramCocos2dxDownloader, int paramInt, String paramString1, String paramString2)
  {
    paramCocos2dxDownloader.enqueueTask(new Channel(paramString2, paramCocos2dxDownloader, paramInt, paramString1));
  }
  
  public static void setResumingSupport(String paramString, Boolean paramBoolean)
  {
    _resumingSupport.put(paramString, paramBoolean);
  }
  
  public void enqueueTask(Runnable paramRunnable)
  {
    Queue localQueue = _taskQueue;
    try
    {
      if (_runningTaskCount < _countOfMaxProcessingTasks)
      {
        Cocos2dxHelper.getActivity().runOnUiThread(paramRunnable);
        _runningTaskCount += 1;
      }
      else
      {
        _taskQueue.add(paramRunnable);
      }
      return;
    }
    catch (Throwable paramRunnable)
    {
      throw paramRunnable;
    }
  }
  
  native void nativeOnFinish(int paramInt1, int paramInt2, int paramInt3, String paramString, byte[] paramArrayOfByte);
  
  native void nativeOnProgress(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
  
  public void onFinish(int paramInt1, int paramInt2, String paramString, byte[] paramArrayOfByte)
  {
    if ((Page)_taskMap.get(Integer.valueOf(paramInt1)) == null) {
      return;
    }
    _taskMap.remove(Integer.valueOf(paramInt1));
    Cocos2dxHelper.runOnGLThread(new Download(this, paramInt1, paramInt2, paramString, paramArrayOfByte));
  }
  
  void onProgress(int paramInt, long paramLong1, long paramLong2, long paramLong3)
  {
    Page localPage = (Page)_taskMap.get(Integer.valueOf(paramInt));
    if (localPage != null)
    {
      b = paramLong1;
      base = paramLong2;
      index = paramLong3;
    }
    Cocos2dxHelper.runOnGLThread(new IonRequestBuilder.2.1(this, paramInt, paramLong1, paramLong2, paramLong3));
  }
  
  public void onStart(int paramInt)
  {
    Page localPage = (Page)_taskMap.get(Integer.valueOf(paramInt));
    if (localPage != null) {
      localPage.reset();
    }
  }
  
  public void runNextTaskIfExists()
  {
    Queue localQueue = _taskQueue;
    try
    {
      Runnable localRunnable = (Runnable)_taskQueue.poll();
      if (localRunnable != null) {
        Cocos2dxHelper.getActivity().runOnUiThread(localRunnable);
      } else {
        _runningTaskCount -= 1;
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
}
