package org.cocos2dx.package_1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import b.b.a.a;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;
import lombok.extern.asm.ByteVector;
import lombok.libs.org.objectweb.asm.c;

public class Cocos2dxHelper {
    private static final int BOOST_TIME = 7;
    private static final String PREFS_NAME = "Cocos2dxPrefsFile";
    private static final int RUNNABLES_PER_FRAME = 5;
    private static final String TAG = "Cocos2dxHelper";
    /* access modifiers changed from: private */
    public static ServiceConnection connection = new SettingsActivity$1();
    /* access modifiers changed from: private */
    public static ByteVector mGameServiceBinder = null;
    private static Set<PreferenceManager.OnActivityResultListener> onActivityResultListeners = new LinkedHashSet();
    private static boolean sAccelerometerEnabled;
    /* access modifiers changed from: private */
    public static Activity sActivity;
    private static boolean sActivityVisible;
    private static AssetManager sAssetManager;
    private static String sAssetsPath = "";
    private static Cocos2dxMusic sCocos2dMusic;
    private static Cocos2dxSound sCocos2dSound;
    private static Cocos2dxAccelerometer sCocos2dxAccelerometer;
    private static Cocos2dxHelperListener sCocos2dxHelperListener;
    private static boolean sCompassEnabled;
    private static boolean sInited = false;
    private static lombok.libs.org.objectweb.asm.ByteVector sOBBFile = null;
    private static String sPackageName;
    private static Vibrator sVibrateService = null;

    public interface Cocos2dxHelperListener {
        void runOnGLThread(Runnable runnable);

        void showDialog(String str, String str2);
    }

    public static void addOnActivityResultListener(PreferenceManager.OnActivityResultListener onActivityResultListener) {
        onActivityResultListeners.add(onActivityResultListener);
    }

    public static byte[] conversionEncoding(byte[] bArr, String str, String str2) {
        try {
            return new String(bArr, str).getBytes(str2);
        } catch (UnsupportedEncodingException $r4) {
            $r4.printStackTrace();
            return null;
        }
    }

    public static void deletePodcast() {
        sCocos2dMusic.fullReset();
        getSound().playSound();
    }

    public static void deleteValueForKey(String str) {
        SharedPreferences.Editor $r3 = sActivity.getSharedPreferences(PREFS_NAME, 0).edit();
        $r3.remove(str);
        $r3.apply();
    }

    public static void disableAccelerometer() {
        sAccelerometerEnabled = false;
        getAccelerometer().disable();
    }

    public static void enableAccelerometer() {
        sAccelerometerEnabled = true;
        getAccelerometer().enableAccel();
    }

    public static void enableCompass() {
        sCompassEnabled = true;
        getAccelerometer().enableCompass();
    }

    public static int fastLoading(int i) {
        if (mGameServiceBinder == null) {
            return -1;
        }
        try {
            return mGameServiceBinder.a(i);
        } catch (Exception $r1) {
            $r1.printStackTrace();
            return -1;
        }
    }

    public static float[] getAccelValue() {
        return getAccelerometer().accelerometerValues;
    }

    private static Cocos2dxAccelerometer getAccelerometer() {
        if (sCocos2dxAccelerometer == null) {
            sCocos2dxAccelerometer = new Cocos2dxAccelerometer(sActivity);
        }
        return sCocos2dxAccelerometer;
    }

    public static Activity getActivity() {
        return sActivity;
    }

    public static AssetManager getAssetManager() {
        return sAssetManager;
    }

    public static String getAssetsPath() {
        String $r1 = "";
        if (sAssetsPath.equals("")) {
            String $r0 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/obb/" + sPackageName;
            String[] $r5 = new File($r0).list(new PreferencesActivity$1());
            if ($r5 != null && $r5.length > 0) {
                $r1 = $r0 + "/" + $r5[0];
            }
            if (new File($r1).exists()) {
                sAssetsPath = $r1;
            } else {
                sAssetsPath = sActivity.getApplicationInfo().sourceDir;
            }
        }
        return sAssetsPath;
    }

    public static float getBackgroundMusicVolume() {
        return sCocos2dMusic.getBackgroundVolume();
    }

    public static boolean getBoolForKey(String str, boolean $z0) {
        SharedPreferences $r2 = sActivity.getSharedPreferences(PREFS_NAME, 0);
        try {
            return $r2.getBoolean(str, $z0);
        } catch (Exception $r3) {
            $r3.printStackTrace();
            Object obj = $r2.getAll().get(str);
            return obj instanceof String ? Boolean.parseBoolean(obj.toString()) : obj instanceof Integer ? ((Integer) obj).intValue() != 0 : obj instanceof Float ? ((Float) obj).floatValue() != 0.0f : $z0;
        }
    }

    public static String getCocos2dxPackageName() {
        return sPackageName;
    }

    public static String getCocos2dxWritablePath() {
        return sActivity.getFilesDir().getAbsolutePath();
    }

    public static float[] getCompassValue() {
        return getAccelerometer().compassFieldValues;
    }

    public static String getCurrentLanguage() {
        return Locale.getDefault().getLanguage();
    }

    public static int getDPI() {
        Display $r3;
        if (sActivity == null) {
            return -1;
        }
        DisplayMetrics $r1 = new DisplayMetrics();
        WindowManager $r2 = sActivity.getWindowManager();
        if ($r2 == null || ($r3 = $r2.getDefaultDisplay()) == null) {
            return -1;
        }
        $r3.getMetrics($r1);
        return (int) ($r1.density * 160.0f);
    }

    public static String getDeviceModel() {
        return Build.MODEL;
    }

    public static double getDoubleForKey(String str, double d) {
        return (double) getFloatForKey(str, (float) d);
    }

    public static float getEffectsVolume() {
        return getSound().getEffectsVolume();
    }

    public static float getFloatForKey(String str, float $f0) {
        SharedPreferences $r2 = sActivity.getSharedPreferences(PREFS_NAME, 0);
        try {
            return $r2.getFloat(str, $f0);
        } catch (Exception $r3) {
            $r3.printStackTrace();
            Object obj = $r2.getAll().get(str);
            if (obj instanceof String) {
                return Float.parseFloat(obj.toString());
            }
            if (obj instanceof Integer) {
                return ((Integer) obj).floatValue();
            }
            if (!(obj instanceof Boolean) || !((Boolean) obj).booleanValue()) {
                return $f0;
            }
            return 1.0f;
        }
    }

    public static int getIntegerForKey(String str, int $i0) {
        SharedPreferences $r2 = sActivity.getSharedPreferences(PREFS_NAME, 0);
        try {
            return $r2.getInt(str, $i0);
        } catch (Exception $r3) {
            $r3.printStackTrace();
            Object obj = $r2.getAll().get(str);
            if (obj instanceof String) {
                return Integer.parseInt(obj.toString());
            }
            if (obj instanceof Float) {
                return ((Float) obj).intValue();
            }
            if (!(obj instanceof Boolean) || !((Boolean) obj).booleanValue()) {
                return $i0;
            }
            return 1;
        }
    }

    public static long[] getObbAssetFileDescriptor(String str) {
        AssetFileDescriptor $r5;
        String $r2;
        String $r13;
        long[] $r3 = new long[3];
        if (getObbFile() == null || ($r5 = getObbFile().a(str)) == null) {
            return $r3;
        }
        try {
            ParcelFileDescriptor $r6 = $r5.getParcelFileDescriptor();
            $r3[0] = (long) ((Integer) $r6.getClass().getMethod("getFd", new Class[0]).invoke($r6, new Object[0])).intValue();
            $r3[1] = $r5.getStartOffset();
            $r3[2] = $r5.getLength();
            return $r3;
        } catch (NoSuchMethodException e) {
            Log.e(TAG, "Accessing file descriptor directly from the OBB is only supported from Android 3.1 (API level 12) and above.");
            return $r3;
        } catch (IllegalAccessException $r14) {
            $r2 = TAG;
            $r13 = $r14.toString();
            Log.e($r2, $r13);
            return $r3;
        } catch (InvocationTargetException $r12) {
            $r2 = TAG;
            $r13 = $r12.toString();
            Log.e($r2, $r13);
            return $r3;
        }
    }

    public static lombok.libs.org.objectweb.asm.ByteVector getObbFile() {
        if (sOBBFile == null) {
            int $i0 = 1;
            try {
                $i0 = Cocos2dxActivity.getContext().getPackageManager().getPackageInfo(getCocos2dxPackageName(), 0).versionCode;
            } catch (PackageManager.NameNotFoundException $r5) {
                $r5.printStackTrace();
            }
            try {
                sOBBFile = c.a(Cocos2dxActivity.getContext(), $i0, 0);
            } catch (IOException $r6) {
                $r6.printStackTrace();
            }
        }
        return sOBBFile;
    }

    public static Set getOnActivityResultListeners() {
        return onActivityResultListeners;
    }

    public static int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

    private static Cocos2dxSound getSound() {
        if (sCocos2dSound == null) {
            sCocos2dSound = new Cocos2dxSound(sActivity);
        }
        return sCocos2dSound;
    }

    public static String getStringForKey(String $r0, String str) {
        SharedPreferences $r3 = sActivity.getSharedPreferences(PREFS_NAME, 0);
        try {
            return $r3.getString($r0, str);
        } catch (Exception $r4) {
            $r4.printStackTrace();
            return $r3.getAll().get($r0).toString();
        }
    }

    public static int getTemperature() {
        if (mGameServiceBinder == null) {
            return -1;
        }
        try {
            return mGameServiceBinder.a();
        } catch (Exception $r1) {
            $r1.printStackTrace();
            return -1;
        }
    }

    public static String getVersion() {
        try {
            return Cocos2dxActivity.getContext().getPackageManager().getPackageInfo(Cocos2dxActivity.getContext().getPackageName(), 0).versionName;
        } catch (Exception e) {
            return "";
        }
    }

    public static void init(Activity activity) {
        String $r5;
        String $r3;
        sActivity = activity;
        sCocos2dxHelperListener = (Cocos2dxHelperListener) activity;
        if (!sInited) {
            boolean $z0 = activity.getPackageManager().hasSystemFeature("android.hardware.audio.low_latency");
            Log.d(TAG, "isSupportLowLatency:" + $z0);
            int $i0 = 44100;
            int $i1 = 192;
            if (Build.VERSION.SDK_INT >= 17) {
                AudioManager audioManager = (AudioManager) activity.getSystemService("audio");
                String $r32 = (String) Cocos2dxReflectionHelper.invokeInstanceMethod(audioManager, "getProperty", new Class[]{String.class}, new Object[]{Cocos2dxReflectionHelper.getConstantValue(AudioManager.class, "PROPERTY_OUTPUT_SAMPLE_RATE")});
                String $r52 = (String) Cocos2dxReflectionHelper.invokeInstanceMethod(audioManager, "getProperty", new Class[]{String.class}, new Object[]{Cocos2dxReflectionHelper.getConstantValue(AudioManager.class, "PROPERTY_OUTPUT_FRAMES_PER_BUFFER")});
                try {
                    $i0 = Integer.parseInt($r32);
                    $i1 = Integer.parseInt($r52);
                } catch (NumberFormatException $r10) {
                    Log.e(TAG, "parseInt failed", $r10);
                }
                $r3 = TAG;
                $r5 = "sampleRate: " + $i0 + ", framesPerBuffer: " + $i1;
            } else {
                $r3 = TAG;
                $r5 = "android version is lower than 17";
            }
            Log.d($r3, $r5);
            nativeSetAudioDeviceInfo($z0, $i0, $i1);
            sPackageName = activity.getApplicationInfo().packageName;
            sCocos2dMusic = new Cocos2dxMusic(activity);
            sAssetManager = activity.getAssets();
            nativeSetContext(activity, sAssetManager);
            Cocos2dxBitmap.setContext(activity);
            sVibrateService = (Vibrator) activity.getSystemService("vibrator");
            sInited = true;
            Intent intent = new Intent(a.class.getName());
            intent.setPackage("com.enhance.gameservice");
            activity.getApplicationContext().bindService(intent, connection, 1);
        }
    }

    public static boolean isActivityVisible() {
        return sActivityVisible;
    }

    public static boolean isBackgroundMusicPlaying() {
        return sCocos2dMusic.isBackgroundMusicPlaying();
    }

    private static native void nativeSetAudioDeviceInfo(boolean z, int i, int i2);

    private static native void nativeSetContext(Context context, AssetManager assetManager);

    /* access modifiers changed from: private */
    public static native void nativeSetEditTextDialogResult(byte[] bArr);

    public static void onEnterBackground() {
        getSound().onEnterBackground();
        sCocos2dMusic.onEnterBackground();
    }

    public static void onEnterForeground() {
        getSound().onEnterForeground();
        sCocos2dMusic.onEnterForeground();
    }

    public static void onPause() {
        sActivityVisible = false;
        if (sAccelerometerEnabled) {
            getAccelerometer().disable();
        }
    }

    public static void onResume() {
        sActivityVisible = true;
        if (sAccelerometerEnabled) {
            getAccelerometer().enableAccel();
        }
        if (sCompassEnabled) {
            getAccelerometer().enableCompass();
        }
    }

    public static boolean openURL(String str) {
        try {
            Intent $r0 = new Intent("android.intent.action.VIEW");
            $r0.setData(Uri.parse(str));
            sActivity.startActivity($r0);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void pauseAllEffects() {
        getSound().pauseAllEffects();
    }

    public static void pauseBackgroundMusic() {
        sCocos2dMusic.pauseBackgroundMusic();
    }

    public static void pauseEffect(int i) {
        getSound().pauseEffect(i);
    }

    public static void playBackgroundMusic(String str, boolean z) {
        sCocos2dMusic.playBackgroundMusic(str, z);
    }

    public static int playEffect(String str, boolean z, float f, float f2, float f3) {
        return getSound().playEffect(str, z, f, f2, f3);
    }

    public static void preloadBackgroundMusic(String str) {
        sCocos2dMusic.preloadBackgroundMusic(str);
    }

    public static void preloadEffect(String str) {
        getSound().preloadEffect(str);
    }

    public static void resumeAllEffects() {
        getSound().resumeAllEffects();
    }

    public static void resumeBackgroundMusic() {
        sCocos2dMusic.resumeBackgroundMusic();
    }

    public static void resumeEffect(int i) {
        getSound().resumeEffect(i);
    }

    public static void rewindBackgroundMusic() {
        sCocos2dMusic.rewindBackgroundMusic();
    }

    public static void runOnGLThread(Runnable runnable) {
        ((Cocos2dxActivity) sActivity).runOnGLThread(runnable);
    }

    public static void setAccelerometerInterval(float f) {
        getAccelerometer().setInterval(f);
    }

    static void setAudioFocus(boolean z) {
        sCocos2dMusic.setAudioFocus(z);
        getSound().setAudioFocus(z);
    }

    public static void setBackgroundMusicVolume(float f) {
        sCocos2dMusic.setBackgroundVolume(f);
    }

    public static void setBoolForKey(String str, boolean z) {
        SharedPreferences.Editor $r3 = sActivity.getSharedPreferences(PREFS_NAME, 0).edit();
        $r3.putBoolean(str, z);
        $r3.apply();
    }

    public static void setDoubleForKey(String str, double d) {
        SharedPreferences.Editor $r3 = sActivity.getSharedPreferences(PREFS_NAME, 0).edit();
        $r3.putFloat(str, (float) d);
        $r3.apply();
    }

    public static void setEditTextDialogResult(String str) {
        try {
            byte[] $r1 = str.getBytes("UTF8");
            sCocos2dxHelperListener.runOnGLThread(new Runnable($r1) {
                final /* synthetic */ byte[] a;

                {
                    this.a = r1;
                }

                public void run() {
                    Cocos2dxHelper.nativeSetEditTextDialogResult(this.a);
                }
            });
        } catch (UnsupportedEncodingException e) {
        }
    }

    public static void setEffectsVolume(float f) {
        getSound().setEffectsVolume(f);
    }

    public static int setFPS(int i) {
        if (mGameServiceBinder == null) {
            return -1;
        }
        try {
            return mGameServiceBinder.get(i);
        } catch (Exception $r1) {
            $r1.printStackTrace();
            return -1;
        }
    }

    public static void setFloatForKey(String str, float f) {
        SharedPreferences.Editor $r3 = sActivity.getSharedPreferences(PREFS_NAME, 0).edit();
        $r3.putFloat(str, f);
        $r3.apply();
    }

    public static void setIntegerForKey(String str, int i) {
        SharedPreferences.Editor $r3 = sActivity.getSharedPreferences(PREFS_NAME, 0).edit();
        $r3.putInt(str, i);
        $r3.apply();
    }

    public static void setKeepScreenOn(boolean z) {
        ((Cocos2dxActivity) sActivity).setKeepScreenOn(z);
    }

    public static int setLowPowerMode(boolean z) {
        if (mGameServiceBinder == null) {
            return -1;
        }
        try {
            return mGameServiceBinder.a(z);
        } catch (Exception $r1) {
            $r1.printStackTrace();
            return -1;
        }
    }

    public static int setResolutionPercent(int i) {
        if (mGameServiceBinder == null) {
            return -1;
        }
        try {
            return mGameServiceBinder.generateKey(i);
        } catch (Exception $r1) {
            $r1.printStackTrace();
            return -1;
        }
    }

    public static void setStringForKey(String str, String str2) {
        SharedPreferences.Editor $r4 = sActivity.getSharedPreferences(PREFS_NAME, 0).edit();
        $r4.putString(str, str2);
        $r4.apply();
    }

    private static void showDialog(String str, String str2) {
        sCocos2dxHelperListener.showDialog(str, str2);
    }

    public static void stopAllEffects() {
        getSound().stopAllEffects();
    }

    public static void stopBackgroundMusic() {
        sCocos2dMusic.stopBackgroundMusic();
    }

    public static void stopEffect(int i) {
        getSound().stopEffect(i);
    }

    public static void terminateProcess() {
        Process.killProcess(Process.myPid());
    }

    public static void unloadEffect(String str) {
        getSound().unloadEffect(str);
    }

    public static void vibrate(float f) {
        sVibrateService.vibrate((long) (f * 1000.0f));
    }

    public static boolean willPlayBackgroundMusic() {
        return sCocos2dMusic.willPlayBackgroundMusic();
    }
}
