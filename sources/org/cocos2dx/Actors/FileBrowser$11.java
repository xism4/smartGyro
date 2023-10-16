package org.cocos2dx.Actors;

import android.content.DialogInterface;

class FileBrowser$11 implements DialogInterface.OnClickListener {
    final /* synthetic */ AppActivity this$0;

    FileBrowser$11(AppActivity appActivity) {
        this.this$0 = appActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            this.this$0.startAppSettings();
        } catch (Throwable $r3) {
            $r3.printStackTrace();
        }
    }
}
