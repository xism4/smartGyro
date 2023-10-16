package org.cocos2dx.enginedata;

public interface IEngineDataManager {

    public enum GameStatus {
        LAUNCH_BEGIN(0),
        LAUNCH_END(1),
        SCENE_CHANGE_BEGIN(2),
        SCENE_CHANGE_END(3),
        IN_SCENE(4),
        INVALID(5000);
        
        private int status;

        private GameStatus(int i) {
            this.status = i;
        }
    }

    public interface OnSystemCommandListener {
        void onChangeContinuousFrameLostConfig(int i, int i2);

        void onChangeExpectedFps(int i);

        void onChangeLowFpsConfig(int i, float f);

        void onChangeMuteEnabled(boolean z);

        void onChangeSpecialEffectLevel(int i);

        void onQueryFps(int[] iArr, int[] iArr2);
    }

    void destroy();

    String getVendorInfo();

    boolean init(OnSystemCommandListener onSystemCommandListener);

    void notifyContinuousFrameLost(int i, int i2, int i3);

    void notifyFpsChanged(float f, float f2);

    void notifyGameStatus(GameStatus gameStatus, int i, int i2);

    void notifyLowFps(int i, float f, int i2);

    void pause();

    void resume();
}
