package org.cocos2dx.enginedata;

import java.util.ArrayList;
import java.util.List;
import org.cocos2dx.enginedata.IEngineDataManager;
import org.cocos2dx.enginedata.magic.Shaker;

public class EngineDataManager implements IEngineDataManager {
    private static final String ACTION_UPDATE_ALL = "1.0.0.0";
    private static final int CV_CAP_ANDROID = 1000;
    private static final String PAGE_KEY = "EngineDataManager";
    private List<IEngineDataManager> mStack = new ArrayList();

    public EngineDataManager() {
        this.mStack.add(new Shaker());
    }

    public void destroy() {
        for (IEngineDataManager $r4 : this.mStack) {
            $r4.destroy();
        }
    }

    public String getVendorInfo() {
        StringBuilder $r1 = new StringBuilder();
        for (int $i0 = 0; $i0 < this.mStack.size(); $i0++) {
            $r1.append(this.mStack.get($i0).getVendorInfo());
            if ($i0 < this.mStack.size() - 1) {
                $r1.append(",");
            }
        }
        return $r1.toString();
    }

    public int getVersionCode() {
        return 1000;
    }

    public String getVersionName() {
        return ACTION_UPDATE_ALL;
    }

    public boolean init(IEngineDataManager.OnSystemCommandListener onSystemCommandListener) {
        boolean $z0 = false;
        for (IEngineDataManager $r5 : this.mStack) {
            if ($r5.init(onSystemCommandListener)) {
                $z0 = true;
            }
        }
        return $z0;
    }

    public void notifyContinuousFrameLost(int i, int i2, int i3) {
        for (IEngineDataManager $r4 : this.mStack) {
            $r4.notifyContinuousFrameLost(i, i2, i3);
        }
    }

    public void notifyFpsChanged(float f, float f2) {
        for (IEngineDataManager $r4 : this.mStack) {
            $r4.notifyFpsChanged(f, f2);
        }
    }

    public void notifyGameStatus(IEngineDataManager.GameStatus gameStatus, int i, int i2) {
        for (IEngineDataManager $r5 : this.mStack) {
            $r5.notifyGameStatus(gameStatus, i, i2);
        }
    }

    public void notifyLowFps(int i, float f, int i2) {
        for (IEngineDataManager $r4 : this.mStack) {
            $r4.notifyLowFps(i, f, i2);
        }
    }

    public void pause() {
        for (IEngineDataManager $r4 : this.mStack) {
            $r4.pause();
        }
    }

    public void resume() {
        for (IEngineDataManager $r4 : this.mStack) {
            $r4.resume();
        }
    }
}
