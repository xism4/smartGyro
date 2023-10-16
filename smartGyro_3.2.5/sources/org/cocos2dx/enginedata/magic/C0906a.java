package org.cocos2dx.enginedata.magic;

import android.util.Log;
import com.huawei.android.hwgamesdk.HwGameSDK;
import com.huawei.android.hwgamesdk.NoExtAPIException;
import org.cocos2dx.enginedata.IEngineDataManager;

/* renamed from: org.cocos2dx.enginedata.magic.a */
public class C0906a implements IEngineDataManager {

    /* renamed from: a */
    private static final String f2469a = "EngineDataManagerHuawei";

    /* renamed from: f */
    private static /* synthetic */ int[] f2470f;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public IEngineDataManager.OnSystemCommandListener f2471b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public boolean f2472c = false;

    /* renamed from: d */
    private HwGameSDK f2473d = new HwGameSDK();

    /* renamed from: e */
    private HwGameSDK.GameEngineCallBack f2474e = new C0907b(this);

    /* JADX WARNING: Can't wrap try/catch for region: R(14:3|4|5|6|7|8|9|10|11|12|13|(2:14|15)|16|18) */
    /* JADX WARNING: Can't wrap try/catch for region: R(15:3|4|5|6|7|8|9|10|11|12|13|14|15|16|18) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0027 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0030 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x0039 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0015 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x001e */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ int[] m3105a() {
        /*
            int[] r0 = f2470f
            if (r0 == 0) goto L_0x0005
            return r0
        L_0x0005:
            org.cocos2dx.enginedata.IEngineDataManager$GameStatus[] r0 = org.cocos2dx.enginedata.IEngineDataManager.GameStatus.values()
            int r0 = r0.length
            int[] r0 = new int[r0]
            org.cocos2dx.enginedata.IEngineDataManager$GameStatus r1 = org.cocos2dx.enginedata.IEngineDataManager.GameStatus.INVALID     // Catch:{ NoSuchFieldError -> 0x0015 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0015 }
            r2 = 6
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0015 }
        L_0x0015:
            org.cocos2dx.enginedata.IEngineDataManager$GameStatus r1 = org.cocos2dx.enginedata.IEngineDataManager.GameStatus.IN_SCENE     // Catch:{ NoSuchFieldError -> 0x001e }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001e }
            r2 = 5
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001e }
        L_0x001e:
            org.cocos2dx.enginedata.IEngineDataManager$GameStatus r1 = org.cocos2dx.enginedata.IEngineDataManager.GameStatus.LAUNCH_BEGIN     // Catch:{ NoSuchFieldError -> 0x0027 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0027 }
            r2 = 1
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0027 }
        L_0x0027:
            org.cocos2dx.enginedata.IEngineDataManager$GameStatus r1 = org.cocos2dx.enginedata.IEngineDataManager.GameStatus.LAUNCH_END     // Catch:{ NoSuchFieldError -> 0x0030 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0030 }
            r2 = 2
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0030 }
        L_0x0030:
            org.cocos2dx.enginedata.IEngineDataManager$GameStatus r1 = org.cocos2dx.enginedata.IEngineDataManager.GameStatus.SCENE_CHANGE_BEGIN     // Catch:{ NoSuchFieldError -> 0x0039 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0039 }
            r2 = 3
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0039 }
        L_0x0039:
            org.cocos2dx.enginedata.IEngineDataManager$GameStatus r1 = org.cocos2dx.enginedata.IEngineDataManager.GameStatus.SCENE_CHANGE_END     // Catch:{ NoSuchFieldError -> 0x0042 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0042 }
            r2 = 4
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0042 }
        L_0x0042:
            f2470f = r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.cocos2dx.enginedata.magic.C0906a.m3105a():int[]");
    }

    public void destroy() {
    }

    public String getVendorInfo() {
        return "HuaWei:" + this.f2473d.getApiLevel();
    }

    public boolean init(IEngineDataManager.OnSystemCommandListener onSystemCommandListener) {
        if (onSystemCommandListener == null) {
            return false;
        }
        this.f2471b = onSystemCommandListener;
        try {
            int apiLevel = this.f2473d.getApiLevel();
            if (apiLevel < 1) {
                Log.d(f2469a, "Unsupported function: HwGameSDK.getApiLevel: " + apiLevel + ", min: " + 1);
                return false;
            }
            if (apiLevel >= 2) {
                this.f2472c = true;
            }
            return this.f2473d.registerGame(this.f2474e);
        } catch (NoExtAPIException | NoSuchMethodError unused) {
            return false;
        }
    }

    public void notifyContinuousFrameLost(int i, int i2, int i3) {
        try {
            this.f2473d.notifyContinuousFpsMissed(i, i2, i3);
        } catch (NoExtAPIException unused) {
            Log.d(f2469a, "Unsupported function: notifyGameStatus");
        }
    }

    public void notifyFpsChanged(float f, float f2) {
        try {
            this.f2473d.notifyFpsChanged(f, f2);
        } catch (NoExtAPIException unused) {
            Log.d(f2469a, "Unsupported function: notifyFpsChanged");
        }
    }

    public void notifyGameStatus(IEngineDataManager.GameStatus gameStatus, int i, int i2) {
        HwGameSDK.GameScene gameScene;
        try {
            int i3 = m3105a()[gameStatus.ordinal()];
            if (i3 == 1) {
                gameScene = HwGameSDK.GameScene.GAME_LAUNCH_BEGIN;
            } else if (i3 == 2) {
                gameScene = HwGameSDK.GameScene.GAME_LAUNCH_END;
            } else if (i3 == 3) {
                gameScene = HwGameSDK.GameScene.GAME_SCENECHANGE_BEGIN;
            } else if (i3 == 4) {
                gameScene = HwGameSDK.GameScene.GAME_SCENECHANGE_END;
            } else if (i3 != 5) {
                Log.e(f2469a, "error type: " + gameStatus);
                return;
            } else {
                gameScene = HwGameSDK.GameScene.GAME_INSCENE;
            }
            this.f2473d.notifyGameScene(gameScene, i, i2);
        } catch (NoExtAPIException unused) {
            Log.d(f2469a, "Unsupported function: notifyGameStatus");
        }
    }

    public void notifyLowFps(int i, float f, int i2) {
        try {
            this.f2473d.notifyFpsDx(i, f, i2);
        } catch (NoExtAPIException unused) {
            Log.d(f2469a, "Unsupported function: notifyGameStatus");
        }
    }

    public void pause() {
    }

    public void resume() {
    }
}
