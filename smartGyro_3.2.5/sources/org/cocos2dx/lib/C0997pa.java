package org.cocos2dx.lib;

import android.app.AlertDialog;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.util.Log;

/* renamed from: org.cocos2dx.lib.pa */
class C0997pa implements MediaPlayer.OnErrorListener {

    /* renamed from: a */
    final /* synthetic */ Cocos2dxVideoView f2685a;

    C0997pa(Cocos2dxVideoView cocos2dxVideoView) {
        this.f2685a = cocos2dxVideoView;
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        String access$1000 = this.f2685a.TAG;
        Log.d(access$1000, "Error: " + i + "," + i2);
        int unused = this.f2685a.mCurrentState = -1;
        int unused2 = this.f2685a.mTargetState = -1;
        if ((this.f2685a.mOnErrorListener == null || !this.f2685a.mOnErrorListener.onError(this.f2685a.mMediaPlayer, i, i2)) && this.f2685a.getWindowToken() != null) {
            Resources resources = this.f2685a.mCocos2dxActivity.getResources();
            new AlertDialog.Builder(this.f2685a.mCocos2dxActivity).setTitle(resources.getString(resources.getIdentifier("VideoView_error_title", "string", "android"))).setMessage(resources.getIdentifier(i == 200 ? "VideoView_error_text_invalid_progressive_playback" : "VideoView_error_text_unknown", "string", "android")).setPositiveButton(resources.getString(resources.getIdentifier("VideoView_error_button", "string", "android")), new C0995oa(this)).setCancelable(false).show();
        }
        return true;
    }
}
