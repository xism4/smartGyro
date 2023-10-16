package org.cocos2dx.lib;

import android.view.SurfaceHolder;

/* renamed from: org.cocos2dx.lib.ra */
class C1001ra implements SurfaceHolder.Callback {

    /* renamed from: a */
    final /* synthetic */ Cocos2dxVideoView f2691a;

    C1001ra(Cocos2dxVideoView cocos2dxVideoView) {
        this.f2691a = cocos2dxVideoView;
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        boolean z = true;
        boolean z2 = this.f2691a.mTargetState == 3;
        if (!(this.f2691a.mVideoWidth == i2 && this.f2691a.mVideoHeight == i3)) {
            z = false;
        }
        if (this.f2691a.mMediaPlayer != null && z2 && z) {
            if (this.f2691a.mSeekWhenPrepared != 0) {
                Cocos2dxVideoView cocos2dxVideoView = this.f2691a;
                cocos2dxVideoView.seekTo(cocos2dxVideoView.mSeekWhenPrepared);
            }
            this.f2691a.start();
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        SurfaceHolder unused = this.f2691a.mSurfaceHolder = surfaceHolder;
        this.f2691a.openVideo();
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        SurfaceHolder unused = this.f2691a.mSurfaceHolder = null;
        this.f2691a.release(true);
    }
}
