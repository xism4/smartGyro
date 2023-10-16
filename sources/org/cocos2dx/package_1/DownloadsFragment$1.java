package org.cocos2dx.package_1;

import android.opengl.GLSurfaceView;
import org.cocos2dx.enginedata.IEngineDataManager;

final class DownloadsFragment$1 implements IEngineDataManager.OnSystemCommandListener {
    final /* synthetic */ GLSurfaceView mSurface;

    DownloadsFragment$1(GLSurfaceView gLSurfaceView) {
        this.mSurface = gLSurfaceView;
    }

    public void onChangeContinuousFrameLostConfig(int i, int i2) {
        this.mSurface.queueEvent(new Cubic(this, i, i2));
    }

    public void onChangeExpectedFps(int i) {
        this.mSurface.queueEvent(new CaptureManager$1$1(this, i));
    }

    public void onChangeLowFpsConfig(int i, float f) {
        this.mSurface.queueEvent(new MainActivity$11$1(this, i, f));
    }

    public void onChangeMuteEnabled(boolean z) {
        this.mSurface.queueEvent(new Proxy(this, z));
    }

    public void onChangeSpecialEffectLevel(int i) {
        this.mSurface.queueEvent(new MainActivity$9(this, i));
    }

    public void onQueryFps(int[] iArr, int[] iArr2) {
        Cocos2dxEngineDataManager.nativeOnQueryFps(iArr, iArr2);
    }
}
