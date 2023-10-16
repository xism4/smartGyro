package org.cocos2dx.enginedata.magic;

import android.util.Log;
import group.huawei.android.hwgamesdk.HwGameSDK;
import group.huawei.android.hwgamesdk.NoExtAPIException;
import org.cocos2dx.enginedata.IEngineDataManager;

public class Shaker implements IEngineDataManager {
    private static /* synthetic */ int[] db = null;
    private static final String threshold = "EngineDataManagerHuawei";
    /* access modifiers changed from: private */
    public boolean e = false;
    private HwGameSDK mGestureDetector = new HwGameSDK();
    private HwGameSDK.GameEngineCallBack matrix = new DownloadsFragment$1(this);
    /* access modifiers changed from: private */
    public IEngineDataManager.OnSystemCommandListener x;

    static /* synthetic */ int[] close() {
        int[] $r6 = db;
        if ($r6 != null) {
            return $r6;
        }
        int[] $r62 = new int[IEngineDataManager.GameStatus.values().length];
        try {
            $r62[IEngineDataManager.GameStatus.INVALID.ordinal()] = 6;
        } catch (NoSuchFieldError e2) {
        }
        try {
            $r62[IEngineDataManager.GameStatus.IN_SCENE.ordinal()] = 5;
        } catch (NoSuchFieldError e3) {
        }
        try {
            $r62[IEngineDataManager.GameStatus.LAUNCH_BEGIN.ordinal()] = 1;
        } catch (NoSuchFieldError e4) {
        }
        try {
            $r62[IEngineDataManager.GameStatus.LAUNCH_END.ordinal()] = 2;
        } catch (NoSuchFieldError e5) {
        }
        try {
            $r62[IEngineDataManager.GameStatus.SCENE_CHANGE_BEGIN.ordinal()] = 3;
        } catch (NoSuchFieldError e6) {
        }
        try {
            $r62[IEngineDataManager.GameStatus.SCENE_CHANGE_END.ordinal()] = 4;
        } catch (NoSuchFieldError e7) {
        }
        db = $r62;
        return $r62;
    }

    public void destroy() {
    }

    public String getVendorInfo() {
        return "HuaWei:" + this.mGestureDetector.getApiLevel();
    }

    public boolean init(IEngineDataManager.OnSystemCommandListener onSystemCommandListener) {
        if (onSystemCommandListener == null) {
            return false;
        }
        this.x = onSystemCommandListener;
        try {
            int $i0 = this.mGestureDetector.getApiLevel();
            if ($i0 < 1) {
                Log.d(threshold, "Unsupported function: HwGameSDK.getApiLevel: " + $i0 + ", min: " + 1);
                return false;
            }
            if ($i0 >= 2) {
                this.e = true;
            }
            return this.mGestureDetector.registerGame(this.matrix);
        } catch (NoExtAPIException e2) {
            return false;
        } catch (NoSuchMethodError e3) {
            return false;
        }
    }

    public void notifyContinuousFrameLost(int i, int i2, int i3) {
        try {
            this.mGestureDetector.notifyContinuousFpsMissed(i, i2, i3);
        } catch (NoExtAPIException e2) {
            Log.d(threshold, "Unsupported function: notifyGameStatus");
        }
    }

    public void notifyFpsChanged(float f, float f2) {
        try {
            this.mGestureDetector.notifyFpsChanged(f, f2);
        } catch (NoExtAPIException e2) {
            Log.d(threshold, "Unsupported function: notifyFpsChanged");
        }
    }

    public void notifyGameStatus(IEngineDataManager.GameStatus gameStatus, int i, int i2) {
        HwGameSDK.GameScene $r6;
        try {
            int $i2 = close()[gameStatus.ordinal()];
            if ($i2 == 1) {
                $r6 = HwGameSDK.GameScene.GAME_LAUNCH_BEGIN;
            } else if ($i2 == 2) {
                $r6 = HwGameSDK.GameScene.GAME_LAUNCH_END;
            } else if ($i2 == 3) {
                $r6 = HwGameSDK.GameScene.GAME_SCENECHANGE_BEGIN;
            } else if ($i2 == 4) {
                $r6 = HwGameSDK.GameScene.GAME_SCENECHANGE_END;
            } else if ($i2 != 5) {
                Log.e(threshold, "error type: " + gameStatus);
                return;
            } else {
                $r6 = HwGameSDK.GameScene.GAME_INSCENE;
            }
            this.mGestureDetector.notifyGameScene($r6, i, i2);
        } catch (NoExtAPIException e2) {
            Log.d(threshold, "Unsupported function: notifyGameStatus");
        }
    }

    public void notifyLowFps(int i, float f, int i2) {
        try {
            this.mGestureDetector.notifyFpsDx(i, f, i2);
        } catch (NoExtAPIException e2) {
            Log.d(threshold, "Unsupported function: notifyGameStatus");
        }
    }

    public void pause() {
    }

    public void resume() {
    }
}
