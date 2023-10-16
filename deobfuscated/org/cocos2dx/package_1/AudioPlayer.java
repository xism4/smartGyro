package org.cocos2dx.package_1;

import android.app.AlertDialog.Builder;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;

class AudioPlayer
  implements MediaPlayer.OnErrorListener
{
  AudioPlayer(Cocos2dxVideoView paramCocos2dxVideoView) {}
  
  public boolean onError(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2)
  {
    paramMediaPlayer = Cocos2dxVideoView.access$1000(this$0);
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Error: ");
    ((StringBuilder)localObject).append(paramInt1);
    ((StringBuilder)localObject).append(",");
    ((StringBuilder)localObject).append(paramInt2);
    Log.d(paramMediaPlayer, ((StringBuilder)localObject).toString());
    Cocos2dxVideoView.access$202(this$0, -1);
    Cocos2dxVideoView.access$602(this$0, -1);
    if ((Cocos2dxVideoView.access$1100(this$0) != null) && (Cocos2dxVideoView.access$1100(this$0).onError(Cocos2dxVideoView.access$400(this$0), paramInt1, paramInt2))) {
      return true;
    }
    if (this$0.getWindowToken() != null)
    {
      localObject = this$0.mCocos2dxActivity.getResources();
      if (paramInt1 == 200) {
        paramMediaPlayer = "VideoView_error_text_invalid_progressive_playback";
      } else {
        paramMediaPlayer = "VideoView_error_text_unknown";
      }
      paramInt1 = ((Resources)localObject).getIdentifier(paramMediaPlayer, "string", "android");
      paramInt2 = ((Resources)localObject).getIdentifier("VideoView_error_title", "string", "android");
      int i = ((Resources)localObject).getIdentifier("VideoView_error_button", "string", "android");
      new AlertDialog.Builder(this$0.mCocos2dxActivity).setTitle(((Resources)localObject).getString(paramInt2)).setMessage(paramInt1).setPositiveButton(((Resources)localObject).getString(i), new EditStyledText.StyledTextDialog.10(this)).setCancelable(false).show();
    }
    return true;
  }
}
