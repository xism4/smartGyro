package org.cocos2dx.enginedata.magic;

import com.huawei.android.hwgamesdk.HwGameSDK;

/* renamed from: org.cocos2dx.enginedata.magic.b */
class C0907b implements HwGameSDK.GameEngineCallBack {

    /* renamed from: a */
    final /* synthetic */ C0906a f2475a;

    C0907b(C0906a aVar) {
        this.f2475a = aVar;
    }

    public void changeContinuousFpsMissedRate(int i, int i2) {
        if (this.f2475a.f2471b != null) {
            this.f2475a.f2471b.onChangeContinuousFrameLostConfig(i, i2);
        }
    }

    public void changeDxFpsRate(int i, float f) {
        if (this.f2475a.f2471b != null) {
            this.f2475a.f2471b.onChangeLowFpsConfig(i, f);
        }
    }

    public void changeFpsRate(int i) {
        if (this.f2475a.f2472c && this.f2475a.f2471b != null) {
            this.f2475a.f2471b.onChangeExpectedFps(i);
        }
    }

    public void changeMuteEnabled(boolean z) {
        if (this.f2475a.f2471b != null) {
            this.f2475a.f2471b.onChangeMuteEnabled(z);
        }
    }

    public void changeSpecialEffects(int i) {
        if (this.f2475a.f2471b != null) {
            this.f2475a.f2471b.onChangeSpecialEffectLevel(i);
        }
    }

    public void queryExpectedFps(int[] iArr, int[] iArr2) {
        if (this.f2475a.f2471b != null) {
            this.f2475a.f2471b.onQueryFps(iArr, iArr2);
        }
    }
}
