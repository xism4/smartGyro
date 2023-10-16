package org.cocos2dx.lib;

import android.opengl.GLSurfaceView;
import org.cocos2dx.enginedata.IEngineDataManager;

/* renamed from: org.cocos2dx.lib.O */
class C0942O implements IEngineDataManager.OnSystemCommandListener {

    /* renamed from: a */
    final /* synthetic */ GLSurfaceView f2555a;

    C0942O(GLSurfaceView gLSurfaceView) {
        this.f2555a = gLSurfaceView;
    }

    public void onChangeContinuousFrameLostConfig(int i, int i2) {
        this.f2555a.queueEvent(new C0932J(this, i, i2));
    }

    public void onChangeExpectedFps(int i) {
        this.f2555a.queueEvent(new C0936L(this, i));
    }

    public void onChangeLowFpsConfig(int i, float f) {
        this.f2555a.queueEvent(new C0934K(this, i, f));
    }

    public void onChangeMuteEnabled(boolean z) {
        this.f2555a.queueEvent(new C0940N(this, z));
    }

    public void onChangeSpecialEffectLevel(int i) {
        this.f2555a.queueEvent(new C0938M(this, i));
    }

    public void onQueryFps(int[] iArr, int[] iArr2) {
        Cocos2dxEngineDataManager.nativeOnQueryFps(iArr, iArr2);
    }
}
