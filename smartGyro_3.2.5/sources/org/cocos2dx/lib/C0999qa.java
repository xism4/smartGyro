package org.cocos2dx.lib;

import android.media.MediaPlayer;

/* renamed from: org.cocos2dx.lib.qa */
class C0999qa implements MediaPlayer.OnBufferingUpdateListener {

    /* renamed from: a */
    final /* synthetic */ Cocos2dxVideoView f2688a;

    C0999qa(Cocos2dxVideoView cocos2dxVideoView) {
        this.f2688a = cocos2dxVideoView;
    }

    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        int unused = this.f2688a.mCurrentBufferPercentage = i;
    }
}
