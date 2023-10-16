package org.cocos2dx.Actors;

import android.content.DialogInterface;

class ProfileActivity$2 implements DialogInterface.OnClickListener {
    final /* synthetic */ AppActivity this$0;

    ProfileActivity$2(AppActivity appActivity) {
        this.this$0 = appActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            this.this$0.finish();
        } catch (Throwable $r3) {
            $r3.printStackTrace();
        }
    }
}
