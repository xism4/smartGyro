package org.cocos2dx.package_1;

import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.util.SparseArray;
import android.widget.FrameLayout;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import org.cocos2dx.lib.Cocos2dxWebView;

public class Cocos2dxWebViewHelper
{
  private static final String PAGE_KEY = "Cocos2dxWebViewHelper";
  private static Cocos2dxActivity sCocos2dxActivity;
  private static Handler sHandler;
  private static FrameLayout sLayout;
  private static int viewTag;
  private static SparseArray<Cocos2dxWebView> webViews;
  
  public Cocos2dxWebViewHelper(FrameLayout paramFrameLayout)
  {
    sLayout = paramFrameLayout;
    sHandler = new Handler(Looper.myLooper());
    sCocos2dxActivity = (Cocos2dxActivity)Cocos2dxActivity.getContext();
    webViews = new SparseArray();
  }
  
  public static void _didFailLoading(int paramInt, String paramString)
  {
    didFailLoading(paramInt, paramString);
  }
  
  public static void _didFinishLoading(int paramInt, String paramString)
  {
    didFinishLoading(paramInt, paramString);
  }
  
  public static void _onJsCallback(int paramInt, String paramString)
  {
    onJsCallback(paramInt, paramString);
  }
  
  public static boolean _shouldStartLoading(int paramInt, String paramString)
  {
    return shouldStartLoading(paramInt, paramString) ^ true;
  }
  
  public static Object callInMainThread(Callable paramCallable)
  {
    paramCallable = new FutureTask(paramCallable);
    sHandler.post(paramCallable);
    return paramCallable.get();
  }
  
  public static boolean canGoBack(int paramInt)
  {
    Object localObject = new Observable.21(paramInt);
    try
    {
      localObject = callInMainThread((Callable)localObject);
      localObject = (Boolean)localObject;
      boolean bool = ((Boolean)localObject).booleanValue();
      return bool;
    }
    catch (ExecutionException localExecutionException)
    {
      return false;
    }
    catch (InterruptedException localInterruptedException) {}
    return false;
  }
  
  public static boolean canGoForward(int paramInt)
  {
    Object localObject = new StreamClientImpl.4(paramInt);
    try
    {
      localObject = callInMainThread((Callable)localObject);
      localObject = (Boolean)localObject;
      boolean bool = ((Boolean)localObject).booleanValue();
      return bool;
    }
    catch (ExecutionException localExecutionException)
    {
      return false;
    }
    catch (InterruptedException localInterruptedException) {}
    return false;
  }
  
  public static int createWebView()
  {
    int i = viewTag;
    sCocos2dxActivity.runOnUiThread(new AsyncAppender.Dispatcher(i));
    i = viewTag;
    viewTag = i + 1;
    return i;
  }
  
  private static native void didFailLoading(int paramInt, String paramString);
  
  private static native void didFinishLoading(int paramInt, String paramString);
  
  public static void evaluateJS(int paramInt, String paramString)
  {
    sCocos2dxActivity.runOnUiThread(new Server(paramInt, paramString));
  }
  
  public static float getOpacityWebView(int paramInt)
  {
    if (Build.VERSION.SDK_INT > 10)
    {
      Object localObject = new FutureTask(new DatabaseHelper.1(paramInt));
      sCocos2dxActivity.runOnUiThread((Runnable)localObject);
      try
      {
        localObject = ((FutureTask)localObject).get();
        localObject = (Float)localObject;
        float f = ((Float)localObject).floatValue();
        return f;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    return 1.0F;
  }
  
  public static void goBack(int paramInt)
  {
    sCocos2dxActivity.runOnUiThread(new Sleeper(paramInt));
  }
  
  public static void goForward(int paramInt)
  {
    sCocos2dxActivity.runOnUiThread(new NumberPicker.BeginSoftInputOnLongPressCommand(paramInt));
  }
  
  public static void loadData(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    sCocos2dxActivity.runOnUiThread(new e(paramInt, paramString4, paramString1, paramString2, paramString3));
  }
  
  public static void loadFile(int paramInt, String paramString)
  {
    sCocos2dxActivity.runOnUiThread(new Task(paramInt, paramString));
  }
  
  public static void loadHTMLString(int paramInt, String paramString1, String paramString2)
  {
    sCocos2dxActivity.runOnUiThread(new b(paramInt, paramString2, paramString1));
  }
  
  public static void loadUrl(int paramInt, String paramString, boolean paramBoolean)
  {
    sCocos2dxActivity.runOnUiThread(new Label(paramInt, paramBoolean, paramString));
  }
  
  private static native void onJsCallback(int paramInt, String paramString);
  
  public static void reload(int paramInt)
  {
    sCocos2dxActivity.runOnUiThread(new ThreadHelper(paramInt));
  }
  
  public static void removeWebView(int paramInt)
  {
    sCocos2dxActivity.runOnUiThread(new MainActivity.4(paramInt));
  }
  
  public static void setBackgroundTransparent(int paramInt)
  {
    if (Build.VERSION.SDK_INT > 10) {
      sCocos2dxActivity.runOnUiThread(new InitiationListener.1(paramInt));
    }
  }
  
  public static void setJavascriptInterfaceScheme(int paramInt, String paramString)
  {
    sCocos2dxActivity.runOnUiThread(new PlayActivity.7(paramInt, paramString));
  }
  
  public static void setOpacityWebView(int paramInt, float paramFloat)
  {
    if (Build.VERSION.SDK_INT > 10) {
      sCocos2dxActivity.runOnUiThread(new FileBrowser.10(paramInt, paramFloat));
    }
  }
  
  public static void setScalesPageToFit(int paramInt, boolean paramBoolean)
  {
    sCocos2dxActivity.runOnUiThread(new OpenFileActivity.Finder(paramInt, paramBoolean));
  }
  
  public static void setVisible(int paramInt, boolean paramBoolean)
  {
    sCocos2dxActivity.runOnUiThread(new Message(paramInt, paramBoolean));
  }
  
  public static void setWebViewRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    sCocos2dxActivity.runOnUiThread(new ExtensionData(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5));
  }
  
  private static native boolean shouldStartLoading(int paramInt, String paramString);
  
  public static void stopLoading(int paramInt)
  {
    sCocos2dxActivity.runOnUiThread(new Model(paramInt));
  }
}
