package org.cocos2dx.package_1;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Process;
import android.os.Vibrator;
import android.preference.PreferenceManager.OnActivityResultListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import b.b.a.a;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import lombok.libs.org.objectweb.asm.c;

public class Cocos2dxHelper
{
  private static final int BOOST_TIME = 7;
  private static final String PREFS_NAME = "Cocos2dxPrefsFile";
  private static final int RUNNABLES_PER_FRAME = 5;
  private static final String TAG = "Cocos2dxHelper";
  private static ServiceConnection connection = new SettingsActivity.1();
  private static lombok.extern.asm.ByteVector mGameServiceBinder;
  private static Set<PreferenceManager.OnActivityResultListener> onActivityResultListeners = new LinkedHashSet();
  private static boolean sAccelerometerEnabled;
  private static Activity sActivity;
  private static boolean sActivityVisible;
  private static AssetManager sAssetManager;
  private static String sAssetsPath;
  private static Cocos2dxMusic sCocos2dMusic;
  private static Cocos2dxSound sCocos2dSound;
  private static Cocos2dxAccelerometer sCocos2dxAccelerometer;
  private static Cocos2dxHelperListener sCocos2dxHelperListener;
  private static boolean sCompassEnabled;
  private static boolean sInited;
  private static lombok.libs.org.objectweb.asm.ByteVector sOBBFile;
  private static String sPackageName;
  private static Vibrator sVibrateService = null;
  
  static
  {
    mGameServiceBinder = null;
    sAssetsPath = "";
    sOBBFile = null;
    sInited = false;
  }
  
  public Cocos2dxHelper() {}
  
  public static void addOnActivityResultListener(PreferenceManager.OnActivityResultListener paramOnActivityResultListener)
  {
    onActivityResultListeners.add(paramOnActivityResultListener);
  }
  
  public static byte[] conversionEncoding(byte[] paramArrayOfByte, String paramString1, String paramString2)
  {
    try
    {
      paramArrayOfByte = new String(paramArrayOfByte, paramString1).getBytes(paramString2);
      return paramArrayOfByte;
    }
    catch (UnsupportedEncodingException paramArrayOfByte)
    {
      paramArrayOfByte.printStackTrace();
    }
    return null;
  }
  
  public static void deletePodcast()
  {
    sCocos2dMusic.fullReset();
    getSound().playSound();
  }
  
  public static void deleteValueForKey(String paramString)
  {
    SharedPreferences.Editor localEditor = sActivity.getSharedPreferences("Cocos2dxPrefsFile", 0).edit();
    localEditor.remove(paramString);
    localEditor.apply();
  }
  
  public static void disableAccelerometer()
  {
    sAccelerometerEnabled = false;
    getAccelerometer().disable();
  }
  
  public static void enableAccelerometer()
  {
    sAccelerometerEnabled = true;
    getAccelerometer().enableAccel();
  }
  
  public static void enableCompass()
  {
    sCompassEnabled = true;
    getAccelerometer().enableCompass();
  }
  
  public static int fastLoading(int paramInt)
  {
    lombok.extern.asm.ByteVector localByteVector;
    if (mGameServiceBinder != null) {
      localByteVector = mGameServiceBinder;
    }
    try
    {
      paramInt = localByteVector.a(paramInt);
      return paramInt;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return -1;
    return -1;
  }
  
  public static float[] getAccelValue()
  {
    return getAccelerometeraccelerometerValues;
  }
  
  private static Cocos2dxAccelerometer getAccelerometer()
  {
    if (sCocos2dxAccelerometer == null) {
      sCocos2dxAccelerometer = new Cocos2dxAccelerometer(sActivity);
    }
    return sCocos2dxAccelerometer;
  }
  
  public static Activity getActivity()
  {
    return sActivity;
  }
  
  public static AssetManager getAssetManager()
  {
    return sAssetManager;
  }
  
  public static String getAssetsPath()
  {
    Object localObject = sAssetsPath;
    String str1 = "";
    if (((String)localObject).equals(""))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(Environment.getExternalStorageDirectory().getAbsolutePath());
      ((StringBuilder)localObject).append("/Android/obb/");
      ((StringBuilder)localObject).append(sPackageName);
      String str2 = ((StringBuilder)localObject).toString();
      String[] arrayOfString = new File(str2).list(new PreferencesActivity.1());
      localObject = str1;
      if (arrayOfString != null)
      {
        localObject = str1;
        if (arrayOfString.length > 0)
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append(str2);
          ((StringBuilder)localObject).append("/");
          ((StringBuilder)localObject).append(arrayOfString[0]);
          localObject = ((StringBuilder)localObject).toString();
        }
      }
      if (new File((String)localObject).exists()) {
        sAssetsPath = (String)localObject;
      } else {
        sAssetsPath = sActivitygetApplicationInfosourceDir;
      }
    }
    return sAssetsPath;
  }
  
  public static float getBackgroundMusicVolume()
  {
    return sCocos2dMusic.getBackgroundVolume();
  }
  
  public static boolean getBoolForKey(String paramString, boolean paramBoolean)
  {
    SharedPreferences localSharedPreferences = sActivity.getSharedPreferences("Cocos2dxPrefsFile", 0);
    try
    {
      boolean bool = localSharedPreferences.getBoolean(paramString, paramBoolean);
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      paramString = localSharedPreferences.getAll().get(paramString);
      if ((paramString instanceof String)) {
        return Boolean.parseBoolean(paramString.toString());
      }
      if ((paramString instanceof Integer))
      {
        if (((Integer)paramString).intValue() != 0) {
          return true;
        }
      }
      else if ((paramString instanceof Float))
      {
        if (((Float)paramString).floatValue() != 0.0F) {
          return true;
        }
      }
      else {
        return paramBoolean;
      }
    }
    return false;
  }
  
  public static String getCocos2dxPackageName()
  {
    return sPackageName;
  }
  
  public static String getCocos2dxWritablePath()
  {
    return sActivity.getFilesDir().getAbsolutePath();
  }
  
  public static float[] getCompassValue()
  {
    return getAccelerometercompassFieldValues;
  }
  
  public static String getCurrentLanguage()
  {
    return Locale.getDefault().getLanguage();
  }
  
  public static int getDPI()
  {
    if (sActivity != null)
    {
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      Object localObject = sActivity.getWindowManager();
      if (localObject != null)
      {
        localObject = ((WindowManager)localObject).getDefaultDisplay();
        if (localObject != null)
        {
          ((Display)localObject).getMetrics(localDisplayMetrics);
          return (int)(density * 160.0F);
        }
      }
    }
    return -1;
  }
  
  public static String getDeviceModel()
  {
    return Build.MODEL;
  }
  
  public static double getDoubleForKey(String paramString, double paramDouble)
  {
    return getFloatForKey(paramString, (float)paramDouble);
  }
  
  public static float getEffectsVolume()
  {
    return getSound().getEffectsVolume();
  }
  
  public static float getFloatForKey(String paramString, float paramFloat)
  {
    SharedPreferences localSharedPreferences = sActivity.getSharedPreferences("Cocos2dxPrefsFile", 0);
    try
    {
      float f = localSharedPreferences.getFloat(paramString, paramFloat);
      return f;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      paramString = localSharedPreferences.getAll().get(paramString);
      if ((paramString instanceof String)) {
        return Float.parseFloat(paramString.toString());
      }
      if ((paramString instanceof Integer)) {
        return ((Integer)paramString).floatValue();
      }
      if (((paramString instanceof Boolean)) && (((Boolean)paramString).booleanValue())) {
        return 1.0F;
      }
    }
    return paramFloat;
  }
  
  public static int getIntegerForKey(String paramString, int paramInt)
  {
    SharedPreferences localSharedPreferences = sActivity.getSharedPreferences("Cocos2dxPrefsFile", 0);
    try
    {
      int i = localSharedPreferences.getInt(paramString, paramInt);
      return i;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      paramString = localSharedPreferences.getAll().get(paramString);
      if ((paramString instanceof String)) {
        return Integer.parseInt(paramString.toString());
      }
      if ((paramString instanceof Float)) {
        return ((Float)paramString).intValue();
      }
      if (((paramString instanceof Boolean)) && (((Boolean)paramString).booleanValue())) {
        return 1;
      }
    }
    return paramInt;
  }
  
  public static long[] getObbAssetFileDescriptor(String paramString)
  {
    long[] arrayOfLong = new long[3];
    if (getObbFile() != null)
    {
      paramString = getObbFile().a(paramString);
      if (paramString != null)
      {
        try
        {
          Object localObject1;
          try
          {
            localObject1 = paramString.getParcelFileDescriptor();
            Object localObject2 = localObject1.getClass();
            localObject2 = ((Class)localObject2).getMethod("getFd", new Class[0]);
            localObject1 = ((Method)localObject2).invoke(localObject1, new Object[0]);
            localObject1 = (Integer)localObject1;
            int i = ((Integer)localObject1).intValue();
            arrayOfLong[0] = i;
            long l = paramString.getStartOffset();
            arrayOfLong[1] = l;
            l = paramString.getLength();
            arrayOfLong[2] = l;
            return arrayOfLong;
          }
          catch (InvocationTargetException paramString)
          {
            localObject1 = TAG;
            paramString = paramString.toString();
          }
          catch (IllegalAccessException paramString)
          {
            localObject1 = TAG;
            paramString = paramString.toString();
          }
          Log.e((String)localObject1, paramString);
          return arrayOfLong;
        }
        catch (NoSuchMethodException paramString)
        {
          for (;;) {}
        }
        Log.e(TAG, "Accessing file descriptor directly from the OBB is only supported from Android 3.1 (API level 12) and above.");
        return arrayOfLong;
      }
    }
    return arrayOfLong;
  }
  
  public static lombok.libs.org.objectweb.asm.ByteVector getObbFile()
  {
    if (sOBBFile == null)
    {
      int i = 1;
      try
      {
        PackageInfo localPackageInfo = Cocos2dxActivity.getContext().getPackageManager().getPackageInfo(getCocos2dxPackageName(), 0);
        i = versionCode;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localNameNotFoundException.printStackTrace();
      }
      try
      {
        lombok.libs.org.objectweb.asm.ByteVector localByteVector = c.a(Cocos2dxActivity.getContext(), i, 0);
        sOBBFile = localByteVector;
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
      }
    }
    return sOBBFile;
  }
  
  public static Set getOnActivityResultListeners()
  {
    return onActivityResultListeners;
  }
  
  public static int getSDKVersion()
  {
    return Build.VERSION.SDK_INT;
  }
  
  private static Cocos2dxSound getSound()
  {
    if (sCocos2dSound == null) {
      sCocos2dSound = new Cocos2dxSound(sActivity);
    }
    return sCocos2dSound;
  }
  
  public static String getStringForKey(String paramString1, String paramString2)
  {
    SharedPreferences localSharedPreferences = sActivity.getSharedPreferences("Cocos2dxPrefsFile", 0);
    try
    {
      paramString2 = localSharedPreferences.getString(paramString1, paramString2);
      return paramString2;
    }
    catch (Exception paramString2)
    {
      paramString2.printStackTrace();
    }
    return localSharedPreferences.getAll().get(paramString1).toString();
  }
  
  public static int getTemperature()
  {
    if (mGameServiceBinder != null)
    {
      lombok.extern.asm.ByteVector localByteVector = mGameServiceBinder;
      try
      {
        int i = localByteVector.a();
        return i;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    return -1;
  }
  
  public static String getVersion()
  {
    try
    {
      PackageInfo localPackageInfo = Cocos2dxActivity.getContext().getPackageManager().getPackageInfo(Cocos2dxActivity.getContext().getPackageName(), 0);
      return versionName;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return "";
  }
  
  public static void init(Activity paramActivity)
  {
    sActivity = paramActivity;
    sCocos2dxHelperListener = (Cocos2dxHelperListener)paramActivity;
    if (!sInited)
    {
      boolean bool = paramActivity.getPackageManager().hasSystemFeature("android.hardware.audio.low_latency");
      Object localObject1 = TAG;
      Object localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append("isSupportLowLatency:");
      ((StringBuilder)localObject3).append(bool);
      Log.d((String)localObject1, ((StringBuilder)localObject3).toString());
      int i = 44100;
      int j = 192;
      if (Build.VERSION.SDK_INT >= 17)
      {
        localObject1 = (AudioManager)paramActivity.getSystemService("audio");
        localObject3 = Cocos2dxReflectionHelper.getConstantValue(AudioManager.class, "PROPERTY_OUTPUT_SAMPLE_RATE");
        localObject3 = (String)Cocos2dxReflectionHelper.invokeInstanceMethod(localObject1, "getProperty", new Class[] { String.class }, new Object[] { localObject3 });
        Object localObject4 = Cocos2dxReflectionHelper.getConstantValue(AudioManager.class, "PROPERTY_OUTPUT_FRAMES_PER_BUFFER");
        localObject1 = (String)Cocos2dxReflectionHelper.invokeInstanceMethod(localObject1, "getProperty", new Class[] { String.class }, new Object[] { localObject4 });
        try
        {
          int k = Integer.parseInt((String)localObject3);
          i = k;
          int m = Integer.parseInt((String)localObject1);
          i = k;
          j = m;
        }
        catch (NumberFormatException localNumberFormatException)
        {
          Log.e(TAG, "parseInt failed", localNumberFormatException);
        }
        localObject2 = TAG;
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append("sampleRate: ");
        ((StringBuilder)localObject3).append(i);
        ((StringBuilder)localObject3).append(", framesPerBuffer: ");
        ((StringBuilder)localObject3).append(j);
        localObject3 = ((StringBuilder)localObject3).toString();
      }
      else
      {
        localObject2 = TAG;
        localObject3 = "android version is lower than 17";
      }
      Log.d((String)localObject2, (String)localObject3);
      nativeSetAudioDeviceInfo(bool, i, j);
      sPackageName = getApplicationInfopackageName;
      sCocos2dMusic = new Cocos2dxMusic(paramActivity);
      sAssetManager = paramActivity.getAssets();
      nativeSetContext(paramActivity, sAssetManager);
      Cocos2dxBitmap.setContext(paramActivity);
      sVibrateService = (Vibrator)paramActivity.getSystemService("vibrator");
      sInited = true;
      Object localObject2 = new Intent(a.class.getName());
      ((Intent)localObject2).setPackage("com.enhance.gameservice");
      paramActivity.getApplicationContext().bindService((Intent)localObject2, connection, 1);
    }
  }
  
  public static boolean isActivityVisible()
  {
    return sActivityVisible;
  }
  
  public static boolean isBackgroundMusicPlaying()
  {
    return sCocos2dMusic.isBackgroundMusicPlaying();
  }
  
  private static native void nativeSetAudioDeviceInfo(boolean paramBoolean, int paramInt1, int paramInt2);
  
  private static native void nativeSetContext(Context paramContext, AssetManager paramAssetManager);
  
  private static native void nativeSetEditTextDialogResult(byte[] paramArrayOfByte);
  
  public static void onEnterBackground()
  {
    getSound().onEnterBackground();
    sCocos2dMusic.onEnterBackground();
  }
  
  public static void onEnterForeground()
  {
    getSound().onEnterForeground();
    sCocos2dMusic.onEnterForeground();
  }
  
  public static void onPause()
  {
    sActivityVisible = false;
    if (sAccelerometerEnabled) {
      getAccelerometer().disable();
    }
  }
  
  public static void onResume()
  {
    sActivityVisible = true;
    if (sAccelerometerEnabled) {
      getAccelerometer().enableAccel();
    }
    if (sCompassEnabled) {
      getAccelerometer().enableCompass();
    }
  }
  
  public static boolean openURL(String paramString)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setData(Uri.parse(paramString));
      paramString = sActivity;
      paramString.startActivity(localIntent);
      return true;
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    return false;
  }
  
  public static void pauseAllEffects()
  {
    getSound().pauseAllEffects();
  }
  
  public static void pauseBackgroundMusic()
  {
    sCocos2dMusic.pauseBackgroundMusic();
  }
  
  public static void pauseEffect(int paramInt)
  {
    getSound().pauseEffect(paramInt);
  }
  
  public static void playBackgroundMusic(String paramString, boolean paramBoolean)
  {
    sCocos2dMusic.playBackgroundMusic(paramString, paramBoolean);
  }
  
  public static int playEffect(String paramString, boolean paramBoolean, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    return getSound().playEffect(paramString, paramBoolean, paramFloat1, paramFloat2, paramFloat3);
  }
  
  public static void preloadBackgroundMusic(String paramString)
  {
    sCocos2dMusic.preloadBackgroundMusic(paramString);
  }
  
  public static void preloadEffect(String paramString)
  {
    getSound().preloadEffect(paramString);
  }
  
  public static void resumeAllEffects()
  {
    getSound().resumeAllEffects();
  }
  
  public static void resumeBackgroundMusic()
  {
    sCocos2dMusic.resumeBackgroundMusic();
  }
  
  public static void resumeEffect(int paramInt)
  {
    getSound().resumeEffect(paramInt);
  }
  
  public static void rewindBackgroundMusic()
  {
    sCocos2dMusic.rewindBackgroundMusic();
  }
  
  public static void runOnGLThread(Runnable paramRunnable)
  {
    ((Cocos2dxActivity)sActivity).runOnGLThread(paramRunnable);
  }
  
  public static void setAccelerometerInterval(float paramFloat)
  {
    getAccelerometer().setInterval(paramFloat);
  }
  
  static void setAudioFocus(boolean paramBoolean)
  {
    sCocos2dMusic.setAudioFocus(paramBoolean);
    getSound().setAudioFocus(paramBoolean);
  }
  
  public static void setBackgroundMusicVolume(float paramFloat)
  {
    sCocos2dMusic.setBackgroundVolume(paramFloat);
  }
  
  public static void setBoolForKey(String paramString, boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = sActivity.getSharedPreferences("Cocos2dxPrefsFile", 0).edit();
    localEditor.putBoolean(paramString, paramBoolean);
    localEditor.apply();
  }
  
  public static void setDoubleForKey(String paramString, double paramDouble)
  {
    SharedPreferences.Editor localEditor = sActivity.getSharedPreferences("Cocos2dxPrefsFile", 0).edit();
    localEditor.putFloat(paramString, (float)paramDouble);
    localEditor.apply();
  }
  
  public static void setEditTextDialogResult(String paramString)
  {
    try
    {
      paramString = paramString.getBytes("UTF8");
      Cocos2dxHelperListener localCocos2dxHelperListener = sCocos2dxHelperListener;
      localCocos2dxHelperListener.runOnGLThread(new NumberPicker.1(paramString));
      return;
    }
    catch (UnsupportedEncodingException paramString) {}
  }
  
  public static void setEffectsVolume(float paramFloat)
  {
    getSound().setEffectsVolume(paramFloat);
  }
  
  public static int setFPS(int paramInt)
  {
    lombok.extern.asm.ByteVector localByteVector;
    if (mGameServiceBinder != null) {
      localByteVector = mGameServiceBinder;
    }
    try
    {
      paramInt = localByteVector.get(paramInt);
      return paramInt;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return -1;
    return -1;
  }
  
  public static void setFloatForKey(String paramString, float paramFloat)
  {
    SharedPreferences.Editor localEditor = sActivity.getSharedPreferences("Cocos2dxPrefsFile", 0).edit();
    localEditor.putFloat(paramString, paramFloat);
    localEditor.apply();
  }
  
  public static void setIntegerForKey(String paramString, int paramInt)
  {
    SharedPreferences.Editor localEditor = sActivity.getSharedPreferences("Cocos2dxPrefsFile", 0).edit();
    localEditor.putInt(paramString, paramInt);
    localEditor.apply();
  }
  
  public static void setKeepScreenOn(boolean paramBoolean)
  {
    ((Cocos2dxActivity)sActivity).setKeepScreenOn(paramBoolean);
  }
  
  public static int setLowPowerMode(boolean paramBoolean)
  {
    lombok.extern.asm.ByteVector localByteVector;
    if (mGameServiceBinder != null) {
      localByteVector = mGameServiceBinder;
    }
    try
    {
      int i = localByteVector.a(paramBoolean);
      return i;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return -1;
    return -1;
  }
  
  public static int setResolutionPercent(int paramInt)
  {
    lombok.extern.asm.ByteVector localByteVector;
    if (mGameServiceBinder != null) {
      localByteVector = mGameServiceBinder;
    }
    try
    {
      paramInt = localByteVector.generateKey(paramInt);
      return paramInt;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return -1;
    return -1;
  }
  
  public static void setStringForKey(String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = sActivity.getSharedPreferences("Cocos2dxPrefsFile", 0).edit();
    localEditor.putString(paramString1, paramString2);
    localEditor.apply();
  }
  
  private static void showDialog(String paramString1, String paramString2)
  {
    sCocos2dxHelperListener.showDialog(paramString1, paramString2);
  }
  
  public static void stopAllEffects()
  {
    getSound().stopAllEffects();
  }
  
  public static void stopBackgroundMusic()
  {
    sCocos2dMusic.stopBackgroundMusic();
  }
  
  public static void stopEffect(int paramInt)
  {
    getSound().stopEffect(paramInt);
  }
  
  public static void terminateProcess()
  {
    Process.killProcess(Process.myPid());
  }
  
  public static void unloadEffect(String paramString)
  {
    getSound().unloadEffect(paramString);
  }
  
  public static void vibrate(float paramFloat)
  {
    sVibrateService.vibrate((paramFloat * 1000.0F));
  }
  
  public static boolean willPlayBackgroundMusic()
  {
    return sCocos2dMusic.willPlayBackgroundMusic();
  }
  
  public abstract interface Cocos2dxHelperListener
  {
    public abstract void runOnGLThread(Runnable paramRunnable);
    
    public abstract void showDialog(String paramString1, String paramString2);
  }
}
