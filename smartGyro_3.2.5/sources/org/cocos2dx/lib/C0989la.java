package org.cocos2dx.lib;

import android.media.MediaPlayer;

/* renamed from: org.cocos2dx.lib.la */
class C0989la implements MediaPlayer.OnVideoSizeChangedListener {

    /* renamed from: a */
    final /* synthetic */ Cocos2dxVideoView f2674a;

    C0989la(Cocos2dxVideoView cocos2dxVideoView) {
        this.f2674a = cocos2dxVideoView;
    }

    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        int unused = this.f2674a.mVideoWidth = mediaPlayer.getVideoWidth();
        int unused2 = this.f2674a.mVideoHeight = mediaPlayer.getVideoHeight();
        if (this.f2674a.mVideoWidth != 0 && this.f2674a.mVideoHeight != 0) {
            this.f2674a.getHolder().setFixedSize(this.f2674a.mVideoWidth, this.f2674a.mVideoHeight);
        }
    }
}
