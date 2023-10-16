package org.cocos2dx.Actors;

import android.content.DialogInterface;

class WhitelistFragment$3 implements DialogInterface.OnClickListener {
    final /* synthetic */ AppActivity this$0;

    WhitelistFragment$3(AppActivity appActivity) {
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
