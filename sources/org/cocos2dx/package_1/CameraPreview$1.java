package org.cocos2dx.package_1;

import android.view.SurfaceHolder;

class CameraPreview$1 implements SurfaceHolder.Callback {
    final /* synthetic */ Cocos2dxVideoView this$0;

    CameraPreview$1(Cocos2dxVideoView cocos2dxVideoView) {
        this.this$0 = cocos2dxVideoView;
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        boolean $z0 = true;
        boolean $z1 = this.this$0.mTargetState == 3;
        if (!(this.this$0.mVideoWidth == i2 && this.this$0.mVideoHeight == i3)) {
            $z0 = false;
        }
        if (this.this$0.mMediaPlayer != null && $z1 && $z0) {
            if (this.this$0.mSeekWhenPrepared != 0) {
                Cocos2dxVideoView $r2 = this.this$0;
                $r2.seekTo($r2.mSeekWhenPrepared);
            }
            this.this$0.start();
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        SurfaceHolder unused = this.this$0.mSurfaceHolder = surfaceHolder;
        this.this$0.openVideo();
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        SurfaceHolder unused = this.this$0.mSurfaceHolder = null;
        this.this$0.release(true);
    }
}
