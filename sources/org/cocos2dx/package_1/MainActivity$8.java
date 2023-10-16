package org.cocos2dx.package_1;

import android.media.MediaPlayer;

class MainActivity$8 implements MediaPlayer.OnPreparedListener {
    final /* synthetic */ Cocos2dxVideoView this$0;

    MainActivity$8(Cocos2dxVideoView cocos2dxVideoView) {
        this.this$0 = cocos2dxVideoView;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        int unused = this.this$0.mCurrentState = 2;
        if (this.this$0.mOnPreparedListener != null) {
            this.this$0.mOnPreparedListener.onPrepared(this.this$0.mMediaPlayer);
        }
        int unused2 = this.this$0.mVideoWidth = mediaPlayer.getVideoWidth();
        int unused3 = this.this$0.mVideoHeight = mediaPlayer.getVideoHeight();
        int $i0 = this.this$0.mSeekWhenPrepared;
        if ($i0 != 0) {
            this.this$0.seekTo($i0);
        }
        if (!(this.this$0.mVideoWidth == 0 || this.this$0.mVideoHeight == 0)) {
            this.this$0.fixSize();
        }
        if (this.this$0.mTargetState == 3) {
            this.this$0.start();
        }
    }
}
