package org.cocos2dx.package_1;

import android.media.MediaPlayer;

class DownloadServiceImpl$2 implements MediaPlayer.OnBufferingUpdateListener {
    final /* synthetic */ Cocos2dxVideoView this$0;

    DownloadServiceImpl$2(Cocos2dxVideoView cocos2dxVideoView) {
        this.this$0 = cocos2dxVideoView;
    }

    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        int unused = this.this$0.mCurrentBufferPercentage = i;
    }
}
