package org.cocos2dx.package_1;

import android.media.MediaPlayer;

class AudioClip$1 implements MediaPlayer.OnCompletionListener {
    final /* synthetic */ Cocos2dxVideoView this$0;

    AudioClip$1(Cocos2dxVideoView cocos2dxVideoView) {
        this.this$0 = cocos2dxVideoView;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        int unused = this.this$0.mCurrentState = 5;
        int unused2 = this.this$0.mTargetState = 5;
        this.this$0.release(true);
        if (this.this$0.mOnVideoEventListener != null) {
            this.this$0.mOnVideoEventListener.onVideoEvent(this.this$0.mViewTag, 3);
        }
    }
}
