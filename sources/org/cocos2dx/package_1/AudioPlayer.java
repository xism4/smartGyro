package org.cocos2dx.package_1;

import android.app.AlertDialog;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.util.Log;

class AudioPlayer implements MediaPlayer.OnErrorListener {
    final /* synthetic */ Cocos2dxVideoView this$0;

    AudioPlayer(Cocos2dxVideoView cocos2dxVideoView) {
        this.this$0 = cocos2dxVideoView;
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        String $r3 = this.this$0.TAG;
        Log.d($r3, "Error: " + i + "," + i2);
        int unused = this.this$0.mCurrentState = -1;
        int unused2 = this.this$0.mTargetState = -1;
        if ((this.this$0.mOnErrorListener != null && this.this$0.mOnErrorListener.onError(this.this$0.mMediaPlayer, i, i2)) || this.this$0.getWindowToken() == null) {
            return true;
        }
        Resources $r9 = this.this$0.mCocos2dxActivity.getResources();
        new AlertDialog.Builder(this.this$0.mCocos2dxActivity).setTitle($r9.getString($r9.getIdentifier("VideoView_error_title", "string", "android"))).setMessage($r9.getIdentifier(i == 200 ? "VideoView_error_text_invalid_progressive_playback" : "VideoView_error_text_unknown", "string", "android")).setPositiveButton($r9.getString($r9.getIdentifier("VideoView_error_button", "string", "android")), new EditStyledText$StyledTextDialog$10(this)).setCancelable(false).show();
        return true;
    }
}
