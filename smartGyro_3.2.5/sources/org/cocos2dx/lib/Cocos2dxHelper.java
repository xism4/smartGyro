package org.cocos2dx.lib;

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
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;
import p026b.p027a.p028a.p029a.p030a.C0456a;
import p026b.p027a.p028a.p029a.p030a.C0457b;
import p026b.p031b.p032a.C0459a;

public class Cocos2dxHelper {
    private static final int BOOST_TIME = 7;
    private static final String PREFS_NAME = "Cocos2dxPrefsFile";
    private static final int RUNNABLES_PER_FRAME = 5;
    private static final String TAG = "Cocos2dxHelper";
    /* access modifiers changed from: private */
    public static ServiceConnection connection = new C0983ia();
    /* access modifiers changed from: private */
    public static C0459a mGameServiceBinder = null;
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
    private static C0457b sOBBFile = null;
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
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void deleteValueForKey(String str) {
        SharedPreferences.Editor edit = sActivity.getSharedPreferences(PREFS_NAME, 0).edit();
        edit.remove(str);
        edit.apply();
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

    public static void end() {
        sCocos2dMusic.end();
        getSound().end();
    }

    public static int fastLoading(int i) {
        try {
            if (mGameServiceBinder != null) {
                return mGameServiceBinder.mo2350c(i);
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
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
        String str = "";
        if (sAssetsPath.equals(str)) {
            String str2 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/obb/" + sPackageName;
            String[] list = new File(str2).list(new C0981ha());
            if (list != null && list.length > 0) {
                str = str2 + "/" + list[0];
            }
            if (new File(str).exists()) {
                sAssetsPath = str;
            } else {
                sAssetsPath = sActivity.getApplicationInfo().sourceDir;
            }
        }
        return sAssetsPath;
    }

    public static float getBackgroundMusicVolume() {
        return sCocos2dMusic.getBackgroundVolume();
    }

    public static boolean getBoolForKey(String str, boolean z) {
        SharedPreferences sharedPreferences = sActivity.getSharedPreferences(PREFS_NAME, 0);
        try {
            return sharedPreferences.getBoolean(str, z);
        } catch (Exception e) {
            e.printStackTrace();
            Object obj = sharedPreferences.getAll().get(str);
            return obj instanceof String ? Boolean.parseBoolean(obj.toString()) : obj instanceof Integer ? ((Integer) obj).intValue() != 0 : obj instanceof Float ? ((Float) obj).floatValue() != 0.0f : z;
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
        Display defaultDisplay;
        if (sActivity == null) {
            return -1;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = sActivity.getWindowManager();
        if (windowManager == null || (defaultDisplay = windowManager.getDefaultDisplay()) == null) {
            return -1;
        }
        defaultDisplay.getMetrics(displayMetrics);
        return (int) (displayMetrics.density * 160.0f);
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

    public static float getFloatForKey(String str, float f) {
        SharedPreferences sharedPreferences = sActivity.getSharedPreferences(PREFS_NAME, 0);
        try {
            return sharedPreferences.getFloat(str, f);
        } catch (Exception e) {
            e.printStackTrace();
            Object obj = sharedPreferences.getAll().get(str);
            if (obj instanceof String) {
                return Float.parseFloat(obj.toString());
            }
            if (obj instanceof Integer) {
                return ((Integer) obj).floatValue();
            }
            if (!(obj instanceof Boolean) || !((Boolean) obj).booleanValue()) {
                return f;
            }
            return 1.0f;
        }
    }

    public static int getIntegerForKey(String str, int i) {
        SharedPreferences sharedPreferences = sActivity.getSharedPreferences(PREFS_NAME, 0);
        try {
            return sharedPreferences.getInt(str, i);
        } catch (Exception e) {
            e.printStackTrace();
            Object obj = sharedPreferences.getAll().get(str);
            if (obj instanceof String) {
                return Integer.parseInt(obj.toString());
            }
            if (obj instanceof Float) {
                return ((Float) obj).intValue();
            }
            if (!(obj instanceof Boolean) || !((Boolean) obj).booleanValue()) {
                return i;
            }
            return 1;
        }
    }

    public static long[] getObbAssetFileDescriptor(String str) {
        AssetFileDescriptor b;
        String str2;
        String str3;
        long[] jArr = new long[3];
        if (!(getObbFile() == null || (b = getObbFile().mo2342b(str)) == null)) {
            try {
                ParcelFileDescriptor parcelFileDescriptor = b.getParcelFileDescriptor();
                jArr[0] = (long) ((Integer) parcelFileDescriptor.getClass().getMethod("getFd", new Class[0]).invoke(parcelFileDescriptor, new Object[0])).intValue();
                jArr[1] = b.getStartOffset();
                jArr[2] = b.getLength();
            } catch (NoSuchMethodException unused) {
                Log.e(TAG, "Accessing file descriptor directly from the OBB is only supported from Android 3.1 (API level 12) and above.");
            } catch (IllegalAccessException e) {
                str3 = TAG;
                str2 = e.toString();
                Log.e(str3, str2);
            } catch (InvocationTargetException e2) {
                str3 = TAG;
                str2 = e2.toString();
                Log.e(str3, str2);
            }
        }
        return jArr;
    }

    public static C0457b getObbFile() {
        if (sOBBFile == null) {
            int i = 1;
            try {
                i = Cocos2dxActivity.getContext().getPackageManager().getPackageInfo(getCocos2dxPackageName(), 0).versionCode;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            try {
                sOBBFile = C0456a.m1975b(Cocos2dxActivity.getContext(), i, 0);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return sOBBFile;
    }

    public static Set<PreferenceManager.OnActivityResultListener> getOnActivityResultListeners() {
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

    public static String getStringForKey(String str, String str2) {
        SharedPreferences sharedPreferences = sActivity.getSharedPreferences(PREFS_NAME, 0);
        try {
            return sharedPreferences.getString(str, str2);
        } catch (Exception e) {
            e.printStackTrace();
            return sharedPreferences.getAll().get(str).toString();
        }
    }

    public static int getTemperature() {
        try {
            if (mGameServiceBinder != null) {
                return mGameServiceBinder.mo2346a();
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static String getVersion() {
        try {
            return Cocos2dxActivity.getContext().getPackageManager().getPackageInfo(Cocos2dxActivity.getContext().getPackageName(), 0).versionName;
        } catch (Exception unused) {
            return "";
        }
    }

    public static void init(Activity activity) {
        String str;
        String str2;
        sActivity = activity;
        sCocos2dxHelperListener = (Cocos2dxHelperListener) activity;
        if (!sInited) {
            boolean hasSystemFeature = activity.getPackageManager().hasSystemFeature("android.hardware.audio.low_latency");
            Log.d(TAG, "isSupportLowLatency:" + hasSystemFeature);
            int i = 44100;
            int i2 = 192;
            if (Build.VERSION.SDK_INT >= 17) {
                AudioManager audioManager = (AudioManager) activity.getSystemService("audio");
                Class cls = AudioManager.class;
                String str3 = (String) Cocos2dxReflectionHelper.invokeInstanceMethod(audioManager, "getProperty", new Class[]{String.class}, new Object[]{Cocos2dxReflectionHelper.getConstantValue(cls, "PROPERTY_OUTPUT_SAMPLE_RATE")});
                String str4 = (String) Cocos2dxReflectionHelper.invokeInstanceMethod(audioManager, "getProperty", new Class[]{String.class}, new Object[]{Cocos2dxReflectionHelper.getConstantValue(cls, "PROPERTY_OUTPUT_FRAMES_PER_BUFFER")});
                try {
                    i = Integer.parseInt(str3);
                    i2 = Integer.parseInt(str4);
                } catch (NumberFormatException e) {
                    Log.e(TAG, "parseInt failed", e);
                }
                str2 = TAG;
                str = "sampleRate: " + i + ", framesPerBuffer: " + i2;
            } else {
                str2 = TAG;
                str = "android version is lower than 17";
            }
            Log.d(str2, str);
            nativeSetAudioDeviceInfo(hasSystemFeature, i, i2);
            sPackageName = activity.getApplicationInfo().packageName;
            sCocos2dMusic = new Cocos2dxMusic(activity);
            sAssetManager = activity.getAssets();
            nativeSetContext(activity, sAssetManager);
            Cocos2dxBitmap.setContext(activity);
            sVibrateService = (Vibrator) activity.getSystemService("vibrator");
            sInited = true;
            Intent intent = new Intent(C0459a.class.getName());
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
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            sActivity.startActivity(intent);
            return true;
        } catch (Exception unused) {
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
        SharedPreferences.Editor edit = sActivity.getSharedPreferences(PREFS_NAME, 0).edit();
        edit.putBoolean(str, z);
        edit.apply();
    }

    public static void setDoubleForKey(String str, double d) {
        SharedPreferences.Editor edit = sActivity.getSharedPreferences(PREFS_NAME, 0).edit();
        edit.putFloat(str, (float) d);
        edit.apply();
    }

    public static void setEditTextDialogResult(String str) {
        try {
            sCocos2dxHelperListener.runOnGLThread(new C0985ja(str.getBytes("UTF8")));
        } catch (UnsupportedEncodingException unused) {
        }
    }

    public static void setEffectsVolume(float f) {
        getSound().setEffectsVolume(f);
    }

    public static int setFPS(int i) {
        try {
            if (mGameServiceBinder != null) {
                return mGameServiceBinder.mo2349b(i);
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void setFloatForKey(String str, float f) {
        SharedPreferences.Editor edit = sActivity.getSharedPreferences(PREFS_NAME, 0).edit();
        edit.putFloat(str, f);
        edit.apply();
    }

    public static void setIntegerForKey(String str, int i) {
        SharedPreferences.Editor edit = sActivity.getSharedPreferences(PREFS_NAME, 0).edit();
        edit.putInt(str, i);
        edit.apply();
    }

    public static void setKeepScreenOn(boolean z) {
        ((Cocos2dxActivity) sActivity).setKeepScreenOn(z);
    }

    public static int setLowPowerMode(boolean z) {
        try {
            if (mGameServiceBinder != null) {
                return mGameServiceBinder.mo2348a(z);
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int setResolutionPercent(int i) {
        try {
            if (mGameServiceBinder != null) {
                return mGameServiceBinder.mo2347a(i);
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void setStringForKey(String str, String str2) {
        SharedPreferences.Editor edit = sActivity.getSharedPreferences(PREFS_NAME, 0).edit();
        edit.putString(str, str2);
        edit.apply();
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
