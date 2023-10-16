package com.huawei.android.hwgamesdk;

import android.util.Log;

public class HwGameSDK {
    private static final String TAG = "HwGameSDK";
    private GameEngineCallBack mGameEngineCallback = null;

    public interface GameEngineCallBack {
        void changeContinuousFpsMissedRate(int i, int i2);

        void changeDxFpsRate(int i, float f);

        void changeFpsRate(int i);

        void changeMuteEnabled(boolean z);

        void changeSpecialEffects(int i);

        void queryExpectedFps(int[] iArr, int[] iArr2);
    }

    public enum GameScene {
        GAME_LAUNCH_BEGIN,
        GAME_LAUNCH_END,
        GAME_SCENECHANGE_BEGIN,
        GAME_SCENECHANGE_END,
        GAME_INSCENE
    }

    public int getApiLevel() {
        throw new NoExtAPIException("method not supported.");
    }

    public void notifyContinuousFpsMissed(int i, int i2, int i3) {
        Log.d(TAG, "notifyContinuousFpsMissed, cycle: " + i + ", maxFrameMissed:" + i2 + ", times: " + i3);
        throw new NoExtAPIException("notifyContinuousFpsMissed isn't supported.");
    }

    public void notifyFpsChanged(float f, float f2) {
        Log.d(TAG, "notifyFpsChanged, oldFps:" + f + ", newFps: " + f2);
        throw new NoExtAPIException("notifyFpsChanged isn't supported.");
    }

    public void notifyFpsDx(int i, float f, int i2) {
        Log.d(TAG, "notifyFpsDx, cycle: " + i + ", maxFrameDx:" + f + ", frame: " + i2);
        throw new NoExtAPIException("notifyFpsDx isn't supported.");
    }

    public void notifyGameScene(GameScene gameScene, int i, int i2) {
        Log.d(TAG, "gameScene:" + gameScene + ", cpuLevel:" + i + ", gpuLevel:" + i2);
        throw new NoExtAPIException("notifyGameScene isn't supported.");
    }

    public boolean registerGame(GameEngineCallBack gameEngineCallBack) {
        Log.d(TAG, "registerGame");
        this.mGameEngineCallback = gameEngineCallBack;
        throw new NoExtAPIException("registerGame isn't supported.");
    }
}
