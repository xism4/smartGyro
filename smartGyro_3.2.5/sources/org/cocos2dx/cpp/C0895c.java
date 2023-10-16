package org.cocos2dx.cpp;

import android.content.DialogInterface;

/* renamed from: org.cocos2dx.cpp.c */
class C0895c implements DialogInterface.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ AppActivity f2453a;

    C0895c(AppActivity appActivity) {
        this.f2453a = appActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            this.f2453a.finish();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
