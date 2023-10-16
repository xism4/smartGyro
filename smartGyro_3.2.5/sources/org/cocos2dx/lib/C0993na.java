package org.cocos2dx.lib;

import android.media.MediaPlayer;

/* renamed from: org.cocos2dx.lib.na */
class C0993na implements MediaPlayer.OnCompletionListener {

    /* renamed from: a */
    final /* synthetic */ Cocos2dxVideoView f2680a;

    C0993na(Cocos2dxVideoView cocos2dxVideoView) {
        this.f2680a = cocos2dxVideoView;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        int unused = this.f2680a.mCurrentState = 5;
        int unused2 = this.f2680a.mTargetState = 5;
        this.f2680a.release(true);
        if (this.f2680a.mOnVideoEventListener != null) {
            this.f2680a.mOnVideoEventListener.onVideoEvent(this.f2680a.mViewTag, 3);
        }
    }
}
