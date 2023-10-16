package org.cocos2dx.enginedata;

import java.util.ArrayList;
import java.util.List;
import org.cocos2dx.enginedata.IEngineDataManager;
import org.cocos2dx.enginedata.magic.C0906a;

public class EngineDataManager implements IEngineDataManager {

    /* renamed from: a */
    private static final String f2465a = "EngineDataManager";

    /* renamed from: b */
    private static final int f2466b = 1000;

    /* renamed from: c */
    private static final String f2467c = "1.0.0.0";

    /* renamed from: d */
    private List<IEngineDataManager> f2468d = new ArrayList();

    public EngineDataManager() {
        this.f2468d.add(new C0906a());
    }

    public void destroy() {
        for (IEngineDataManager destroy : this.f2468d) {
            destroy.destroy();
        }
    }

    public String getVendorInfo() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.f2468d.size(); i++) {
            sb.append(this.f2468d.get(i).getVendorInfo());
            if (i < this.f2468d.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public int getVersionCode() {
        return 1000;
    }

    public String getVersionName() {
        return f2467c;
    }

    public boolean init(IEngineDataManager.OnSystemCommandListener onSystemCommandListener) {
        boolean z = false;
        for (IEngineDataManager init : this.f2468d) {
            if (init.init(onSystemCommandListener)) {
                z = true;
            }
        }
        return z;
    }

    public void notifyContinuousFrameLost(int i, int i2, int i3) {
        for (IEngineDataManager notifyContinuousFrameLost : this.f2468d) {
            notifyContinuousFrameLost.notifyContinuousFrameLost(i, i2, i3);
        }
    }

    public void notifyFpsChanged(float f, float f2) {
        for (IEngineDataManager notifyFpsChanged : this.f2468d) {
            notifyFpsChanged.notifyFpsChanged(f, f2);
        }
    }

    public void notifyGameStatus(IEngineDataManager.GameStatus gameStatus, int i, int i2) {
        for (IEngineDataManager notifyGameStatus : this.f2468d) {
            notifyGameStatus.notifyGameStatus(gameStatus, i, i2);
        }
    }

    public void notifyLowFps(int i, float f, int i2) {
        for (IEngineDataManager notifyLowFps : this.f2468d) {
            notifyLowFps.notifyLowFps(i, f, i2);
        }
    }

    public void pause() {
        for (IEngineDataManager pause : this.f2468d) {
            pause.pause();
        }
    }

    public void resume() {
        for (IEngineDataManager resume : this.f2468d) {
            resume.resume();
        }
    }
}
