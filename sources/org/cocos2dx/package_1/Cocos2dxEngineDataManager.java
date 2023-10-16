package org.cocos2dx.package_1;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import org.cocos2dx.enginedata.EngineDataManager;
import org.cocos2dx.enginedata.IEngineDataManager;

public class Cocos2dxEngineDataManager {
    private static final String PAGE_KEY = "CCEngineDataManager";
    private static boolean sIsEnabled = true;
    private static boolean sIsInited = false;
    private static EngineDataManager sManager = new EngineDataManager();

    private Cocos2dxEngineDataManager() {
    }

    private static IEngineDataManager.GameStatus convertIntegerToGameStatus(int i) {
        for (IEngineDataManager.GameStatus $r0 : IEngineDataManager.GameStatus.values()) {
            if (i == $r0.ordinal()) {
                return $r0;
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
        String $r2;
        if (context == null) {
            $r2 = "Context is null";
        } else if (gLSurfaceView == null) {
            $r2 = "glSurfaceView is null";
        } else {
            DownloadsFragment$1 $r3 = new DownloadsFragment$1(gLSurfaceView);
            if (sIsEnabled) {
                sIsInited = sManager.init($r3);
            }
            nativeSetSupportOptimization(sIsInited);
            return sIsInited;
        }
        Log.e(PAGE_KEY, $r2);
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
        IEngineDataManager.GameStatus $r1 = convertIntegerToGameStatus(i);
        if ($r1 == IEngineDataManager.GameStatus.INVALID) {
            Log.e(PAGE_KEY, "Invalid game status: " + i);
            return;
        }
        sManager.notifyGameStatus($r1, i2, i3);
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
