package org.cocos2dx.package_1;

import android.media.MediaPlayer;

class AllInOneActivity$3 implements MediaPlayer.OnVideoSizeChangedListener {
    final /* synthetic */ Cocos2dxVideoView this$0;

    AllInOneActivity$3(Cocos2dxVideoView cocos2dxVideoView) {
        this.this$0 = cocos2dxVideoView;
    }

    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        int unused = this.this$0.mVideoWidth = mediaPlayer.getVideoWidth();
        int unused2 = this.this$0.mVideoHeight = mediaPlayer.getVideoHeight();
        if (this.this$0.mVideoWidth != 0 && this.this$0.mVideoHeight != 0) {
            this.this$0.getHolder().setFixedSize(this.this$0.mVideoWidth, this.this$0.mVideoHeight);
        }
    }
}
