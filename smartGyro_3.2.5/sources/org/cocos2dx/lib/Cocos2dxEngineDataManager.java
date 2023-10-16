package org.cocos2dx.lib;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import org.cocos2dx.enginedata.EngineDataManager;
import org.cocos2dx.enginedata.IEngineDataManager;

public class Cocos2dxEngineDataManager {
    private static final String TAG = "CCEngineDataManager";
    private static boolean sIsEnabled = true;
    private static boolean sIsInited = false;
    private static EngineDataManager sManager = new EngineDataManager();

    private Cocos2dxEngineDataManager() {
    }

    private static IEngineDataManager.GameStatus convertIntegerToGameStatus(int i) {
        for (IEngineDataManager.GameStatus gameStatus : IEngineDataManager.GameStatus.values()) {
            if (i == gameStatus.ordinal()) {
                return gameStatus;
            }
        }
        return IEngineDataManager.GameStatus.INVALID;
    }

    public static void destroy() {
        if (sIsEnabled) {
            sManager.destroy();
        }
    }

    public static void disable() {
        sIsEnabled = false;
    }

    public static String getVendorInfo() {
        return sIsEnabled ? sManager.getVendorInfo() : "";
    }

    public static boolean init(Context context, GLSurfaceView gLSurfaceView) {
        String str;
        if (context == null) {
            str = "Context is null";
        } else if (gLSurfaceView == null) {
            str = "glSurfaceView is null";
        } else {
            C0942O o = new C0942O(gLSurfaceView);
            if (sIsEnabled) {
                sIsInited = sManager.init(o);
            }
            nativeSetSupportOptimization(sIsInited);
            return sIsInited;
        }
        Log.e(TAG, str);
        return false;
    }

    public static boolean isInited() {
        return sIsInited;
    }

    /* access modifiers changed from: private */
    public static native void nativeOnChangeContinuousFrameLostConfig(int i, int i2);

    /* access modifiers changed from: private */
    public static native void nativeOnChangeExpectedFps(int i);

    /* access modifiers changed from: private */
    public static native void nativeOnChangeLowFpsConfig(int i, float f);

    /* access modifiers changed from: private */
    public static native void nativeOnChangeMuteEnabled(boolean z);

    /* access modifiers changed from: private */
    public static native void nativeOnChangeSpecialEffectLevel(int i);

    /* access modifiers changed from: private */
    public static native void nativeOnQueryFps(int[] iArr, int[] iArr2);

    private static native void nativeSetSupportOptimization(boolean z);

    public static void notifyContinuousFrameLost(int i, int i2, int i3) {
        if (!sIsEnabled) {
            nativeSetSupportOptimization(false);
        } else {
            sManager.notifyContinuousFrameLost(i, i2, i3);
        }
    }

    public static void notifyFpsChanged(float f, float f2) {
        if (!sIsEnabled) {
            nativeSetSupportOptimization(false);
        } else {
            sManager.notifyFpsChanged(f, f2);
        }
    }

    public static void notifyGameStatus(int i, int i2, int i3) {
        if (!sIsEnabled) {
            nativeSetSupportOptimization(false);
            return;
        }
        IEngineDataManager.GameStatus convertIntegerToGameStatus = convertIntegerToGameStatus(i);
        if (convertIntegerToGameStatus == IEngineDataManager.GameStatus.INVALID) {
            Log.e(TAG, "Invalid game status: " + i);
            return;
        }
        sManager.notifyGameStatus(convertIntegerToGameStatus, i2, i3);
    }

    public static void notifyLowFps(int i, float f, int i2) {
        if (!sIsEnabled) {
            nativeSetSupportOptimization(false);
        } else {
            sManager.notifyLowFps(i, f, i2);
        }
    }

    public static void pause() {
        if (sIsEnabled) {
            sManager.pause();
        }
    }

    public static void resume() {
        if (sIsEnabled) {
            sManager.resume();
        }
    }
}
