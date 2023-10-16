package org.cocos2dx.package_1;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.EGLConfigChooser;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager.OnActivityResultListener;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import java.util.Iterator;
import java.util.Set;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;

public abstract class Cocos2dxActivity
  extends Activity
  implements Cocos2dxHelper.Cocos2dxHelperListener
{
  private static final String TAG = "Cocos2dxActivity";
  private static boolean _stateBarIsBlack;
  private static Cocos2dxActivity sContext;
  private boolean hasFocus = false;
  private Cocos2dxEditBoxHelper mEditBoxHelper = null;
  protected ResizeLayout mFrameLayout = null;
  private int[] mGLContextAttrs = null;
  protected Cocos2dxGLSurfaceView mGLSurfaceView = null;
  private Cocos2dxHandler mHandler = null;
  private Cocos2dxVideoHelper mVideoHelper = null;
  private Cocos2dxWebViewHelper mWebViewHelper = null;
  private boolean showVirtualButton = false;
  
  public Cocos2dxActivity() {}
  
  public static Context getContext()
  {
    return sContext;
  }
  
  private static native int[] getGLContextAttrs();
  
  private static boolean isAndroidEmulator()
  {
    String str = Build.MODEL;
    Object localObject = TAG;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("model=");
    localStringBuilder.append(str);
    Log.d((String)localObject, localStringBuilder.toString());
    str = Build.PRODUCT;
    localObject = TAG;
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("product=");
    localStringBuilder.append(str);
    Log.d((String)localObject, localStringBuilder.toString());
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (str != null) {
      if ((!str.equals("sdk")) && (!str.contains("_sdk")))
      {
        bool1 = bool2;
        if (!str.contains("sdk_")) {}
      }
      else
      {
        bool1 = true;
      }
    }
    str = TAG;
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("isEmulator=");
    ((StringBuilder)localObject).append(bool1);
    Log.d(str, ((StringBuilder)localObject).toString());
    return bool1;
  }
  
  public static void setStateBarColor(boolean paramBoolean)
  {
    _stateBarIsBlack = paramBoolean;
  }
  
  public Cocos2dxGLSurfaceView getGLSurfaceView()
  {
    return mGLSurfaceView;
  }
  
  protected void hideVirtualButton()
  {
    if (showVirtualButton)
    {
      Log.d("hideVirtualButton", "001");
      return;
    }
    Log.d("hideVirtualButton", "002");
    if (Build.VERSION.SDK_INT >= 19) {
      try
      {
        Object localObject = Cocos2dxReflectionHelper.getConstantValue(View.class, "SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION");
        localObject = (Integer)localObject;
        int i = ((Integer)localObject).intValue();
        localObject = Cocos2dxReflectionHelper.getConstantValue(View.class, "SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN");
        localObject = (Integer)localObject;
        int j = ((Integer)localObject).intValue();
        localObject = Cocos2dxReflectionHelper.getConstantValue(View.class, "SYSTEM_UI_FLAG_HIDE_NAVIGATION");
        localObject = (Integer)localObject;
        int k = ((Integer)localObject).intValue();
        localObject = Cocos2dxReflectionHelper.getConstantValue(View.class, "SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN");
        localObject = (Integer)localObject;
        int m = ((Integer)localObject).intValue();
        localObject = Cocos2dxReflectionHelper.getConstantValue(View.class, "SYSTEM_UI_FLAG_IMMERSIVE_STICKY");
        localObject = (Integer)localObject;
        int n = ((Integer)localObject).intValue();
        localObject = Cocos2dxReflectionHelper.getConstantValue(View.class, "SYSTEM_UI_FLAG_LAYOUT_STABLE");
        localObject = (Integer)localObject;
        int i1 = ((Integer)localObject).intValue();
        Class localClass;
        if (_stateBarIsBlack)
        {
          localObject = getWindow().getDecorView();
          localClass = Integer.TYPE;
          Cocos2dxReflectionHelper.invokeInstanceMethod(localObject, "setSystemUiVisibility", new Class[] { localClass }, new Object[] { Integer.valueOf(m | i1 | i | j | k | 0x2000 | n) });
        }
        else
        {
          localObject = getWindow().getDecorView();
          localClass = Integer.TYPE;
          Cocos2dxReflectionHelper.invokeInstanceMethod(localObject, "setSystemUiVisibility", new Class[] { localClass }, new Object[] { Integer.valueOf(m | i1 | i | j | k | n) });
        }
      }
      catch (NullPointerException localNullPointerException)
      {
        Log.e(TAG, "hideVirtualButton", localNullPointerException);
      }
    }
    if (Build.VERSION.SDK_INT >= 21) {
      getWindow().setStatusBarColor(0);
    }
  }
  
  public void init()
  {
    ViewGroup.LayoutParams localLayoutParams = new ViewGroup.LayoutParams(-1, -1);
    mFrameLayout = new ResizeLayout(this);
    mFrameLayout.setLayoutParams(localLayoutParams);
    localLayoutParams = new ViewGroup.LayoutParams(-1, -2);
    Cocos2dxEditBox localCocos2dxEditBox = new Cocos2dxEditBox(this);
    localCocos2dxEditBox.setLayoutParams(localLayoutParams);
    mFrameLayout.addView(localCocos2dxEditBox);
    mGLSurfaceView = onCreateView();
    mFrameLayout.addView(mGLSurfaceView, 1);
    mGLSurfaceView.setCocos2dxRenderer(new Cocos2dxRenderer());
    mGLSurfaceView.setCocos2dxEditText(localCocos2dxEditBox);
    setContentView(mFrameLayout);
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    Iterator localIterator = Cocos2dxHelper.getOnActivityResultListeners().iterator();
    while (localIterator.hasNext()) {
      ((PreferenceManager.OnActivityResultListener)localIterator.next()).onActivityResult(paramInt1, paramInt2, paramIntent);
    }
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (!isTaskRoot())
    {
      finish();
      Log.w(TAG, "[Workaround] Ignore the activity started from icon!");
      return;
    }
    hideVirtualButton();
    onLoadNativeLibraries();
    sContext = this;
    mHandler = new Cocos2dxHandler(this);
    Cocos2dxHelper.init(this);
    mGLContextAttrs = getGLContextAttrs();
    init();
    if (mVideoHelper == null) {
      mVideoHelper = new Cocos2dxVideoHelper(this, mFrameLayout);
    }
    if (mWebViewHelper == null) {
      mWebViewHelper = new Cocos2dxWebViewHelper(mFrameLayout);
    }
    if (mEditBoxHelper == null) {
      mEditBoxHelper = new Cocos2dxEditBoxHelper(mFrameLayout);
    }
    getWindow().setSoftInputMode(32);
    setVolumeControlStream(3);
    Cocos2dxEngineDataManager.init(this, mGLSurfaceView);
  }
  
  public Cocos2dxGLSurfaceView onCreateView()
  {
    Cocos2dxGLSurfaceView localCocos2dxGLSurfaceView = new Cocos2dxGLSurfaceView(this);
    if (mGLContextAttrs[3] > 0) {
      localCocos2dxGLSurfaceView.getHolder().setFormat(-3);
    }
    localCocos2dxGLSurfaceView.setEGLConfigChooser(new a(mGLContextAttrs));
    return localCocos2dxGLSurfaceView;
  }
  
  protected void onDestroy()
  {
    Cocos2dxAudioFocusManager.unregister(this);
    super.onDestroy();
    Cocos2dxEngineDataManager.destroy();
  }
  
  protected void onLoadNativeLibraries()
  {
    try
    {
      Object localObject = getPackageManager().getApplicationInfo(getPackageName(), 128);
      localObject = metaData;
      System.loadLibrary(((Bundle)localObject).getString("android.app.lib_name"));
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  protected void onPause()
  {
    Log.d(TAG, "onPause()");
    super.onPause();
    Cocos2dxAudioFocusManager.unregister(this);
    Cocos2dxHelper.onPause();
    mGLSurfaceView.onPause();
    Cocos2dxEngineDataManager.pause();
  }
  
  protected void onResume()
  {
    Log.d(TAG, "onResume()");
    super.onResume();
    Cocos2dxAudioFocusManager.register(this);
    hideVirtualButton();
    resumeIfHasFocus();
    Cocos2dxEngineDataManager.resume();
  }
  
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    String str = TAG;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("onWindowFocusChanged() hasFocus=");
    localStringBuilder.append(paramBoolean);
    Log.d(str, localStringBuilder.toString());
    super.onWindowFocusChanged(paramBoolean);
    hasFocus = paramBoolean;
    resumeIfHasFocus();
  }
  
  protected void resumeIfHasFocus()
  {
    if (hasFocus)
    {
      hideVirtualButton();
      Cocos2dxHelper.onResume();
      mGLSurfaceView.onResume();
    }
  }
  
  public void runOnGLThread(Runnable paramRunnable)
  {
    mGLSurfaceView.queueEvent(paramRunnable);
  }
  
  public void setEnableVirtualButton(boolean paramBoolean)
  {
    showVirtualButton = paramBoolean;
  }
  
  public void setKeepScreenOn(boolean paramBoolean)
  {
    runOnUiThread(new Godot.1(this, paramBoolean));
  }
  
  public void showDialog(String paramString1, String paramString2)
  {
    Message localMessage = new Message();
    what = 1;
    obj = new Cocos2dxHandler.DialogMessage(paramString1, paramString2);
    mHandler.sendMessage(localMessage);
  }
  
  class a
    implements GLSurfaceView.EGLConfigChooser
  {
    private final int ScreenHeight = 64;
    private int[] mConfigSpec;
    private final int verticalAlignment = 4;
    
    public a(int[] paramArrayOfInt)
    {
      mConfigSpec = paramArrayOfInt;
    }
    
    private EGLConfig chooseConfig(EGL10 paramEGL10, EGLDisplay paramEGLDisplay, int[] paramArrayOfInt)
    {
      EGLConfig[] arrayOfEGLConfig = new EGLConfig[1];
      int[] arrayOfInt = new int[1];
      if ((paramEGL10.eglChooseConfig(paramEGLDisplay, paramArrayOfInt, arrayOfEGLConfig, 1, arrayOfInt)) && (arrayOfInt[0] > 0)) {
        return arrayOfEGLConfig[0];
      }
      return null;
    }
    
    public EGLConfig chooseConfig(EGL10 paramEGL10, EGLDisplay paramEGLDisplay)
    {
      int[][] arrayOfInt = new int[4][];
      int k = 0;
      Object localObject = mConfigSpec;
      int j = localObject[0];
      int m = localObject[1];
      int n = localObject[2];
      int i1 = localObject[3];
      int i2 = localObject[4];
      int i3 = localObject[5];
      if (localObject[6] > 0) {
        i = 1;
      } else {
        i = 0;
      }
      localObject = mConfigSpec;
      arrayOfInt[0] = { 12324, j, 12323, m, 12322, n, 12321, i1, 12325, i2, 12326, i3, 12338, i, 12337, localObject[6], 12352, 4, 12344 };
      m = localObject[0];
      n = localObject[1];
      i1 = localObject[2];
      i2 = localObject[3];
      if (localObject[4] >= 24) {
        i = 16;
      } else {
        i = localObject[4];
      }
      localObject = mConfigSpec;
      i3 = localObject[5];
      if (localObject[6] > 0) {
        j = 1;
      } else {
        j = 0;
      }
      localObject = mConfigSpec;
      arrayOfInt[1] = { 12324, m, 12323, n, 12322, i1, 12321, i2, 12325, i, 12326, i3, 12338, j, 12337, localObject[6], 12352, 4, 12344 };
      j = localObject[0];
      m = localObject[1];
      n = localObject[2];
      i1 = localObject[3];
      if (localObject[4] >= 24) {
        i = 16;
      } else {
        i = localObject[4];
      }
      arrayOfInt[2] = { 12324, j, 12323, m, 12322, n, 12321, i1, 12325, i, 12326, mConfigSpec[5], 12338, 0, 12337, 0, 12352, 4, 12344 };
      arrayOfInt[3] = { 12352, 4, 12344 };
      j = arrayOfInt.length;
      int i = k;
      while (i < j)
      {
        localObject = chooseConfig(paramEGL10, paramEGLDisplay, arrayOfInt[i]);
        if (localObject != null) {
          return localObject;
        }
        i += 1;
      }
      Log.e("device_policy", "Can not select an EGLConfig for rendering.");
      return null;
    }
  }
}