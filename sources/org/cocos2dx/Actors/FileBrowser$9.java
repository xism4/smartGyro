package org.cocos2dx.Actors;

import android.content.DialogInterface;

class FileBrowser$9 implements DialogInterface.OnClickListener {
    final /* synthetic */ AppActivity this$0;

    FileBrowser$9(AppActivity appActivity) {
        this.this$0 = appActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.this$0.setLocationService();
    }
}
