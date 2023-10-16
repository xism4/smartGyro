package org.cocos2dx.lib;

import android.media.MediaPlayer;

/* renamed from: org.cocos2dx.lib.ma */
class C0991ma implements MediaPlayer.OnPreparedListener {

    /* renamed from: a */
    final /* synthetic */ Cocos2dxVideoView f2677a;

    C0991ma(Cocos2dxVideoView cocos2dxVideoView) {
        this.f2677a = cocos2dxVideoView;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        int unused = this.f2677a.mCurrentState = 2;
        if (this.f2677a.mOnPreparedListener != null) {
            this.f2677a.mOnPreparedListener.onPrepared(this.f2677a.mMediaPlayer);
        }
        int unused2 = this.f2677a.mVideoWidth = mediaPlayer.getVideoWidth();
        int unused3 = this.f2677a.mVideoHeight = mediaPlayer.getVideoHeight();
        int access$500 = this.f2677a.mSeekWhenPrepared;
        if (access$500 != 0) {
            this.f2677a.seekTo(access$500);
        }
        if (!(this.f2677a.mVideoWidth == 0 || this.f2677a.mVideoHeight == 0)) {
            this.f2677a.fixSize();
        }
        if (this.f2677a.mTargetState == 3) {
            this.f2677a.start();
        }
    }
}
