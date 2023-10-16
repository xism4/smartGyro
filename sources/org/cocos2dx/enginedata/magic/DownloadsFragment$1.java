package org.cocos2dx.enginedata.magic;

import group.huawei.android.hwgamesdk.HwGameSDK;

class DownloadsFragment$1 implements HwGameSDK.GameEngineCallBack {
    final /* synthetic */ Shaker orient;

    DownloadsFragment$1(Shaker shaker) {
        this.orient = shaker;
    }

    public void changeContinuousFpsMissedRate(int i, int i2) {
        if (this.orient.x != null) {
            this.orient.x.onChangeContinuousFrameLostConfig(i, i2);
        }
    }

    public void changeDxFpsRate(int i, float f) {
        if (this.orient.x != null) {
            this.orient.x.onChangeLowFpsConfig(i, f);
        }
    }

    public void changeFpsRate(int i) {
        if (this.orient.e && this.orient.x != null) {
            this.orient.x.onChangeExpectedFps(i);
        }
    }

    public void changeMuteEnabled(boolean z) {
        if (this.orient.x != null) {
            this.orient.x.onChangeMuteEnabled(z);
        }
    }

    public void changeSpecialEffects(int i) {
        if (this.orient.x != null) {
            this.orient.x.onChangeSpecialEffectLevel(i);
        }
    }

    public void queryExpectedFps(int[] iArr, int[] iArr2) {
        if (this.orient.x != null) {
            this.orient.x.onQueryFps(iArr, iArr2);
        }
    }
}
