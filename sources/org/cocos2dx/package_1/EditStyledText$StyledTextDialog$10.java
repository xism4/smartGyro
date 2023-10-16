package org.cocos2dx.package_1;

import android.content.DialogInterface;

class EditStyledText$StyledTextDialog$10 implements DialogInterface.OnClickListener {
    final /* synthetic */ AudioPlayer this$0;

    EditStyledText$StyledTextDialog$10(AudioPlayer audioPlayer) {
        this.this$0 = audioPlayer;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (this.this$0.this$0.mOnVideoEventListener != null) {
            this.this$0.this$0.mOnVideoEventListener.onVideoEvent(this.this$0.this$0.mViewTag, 3);
        }
    }
}
