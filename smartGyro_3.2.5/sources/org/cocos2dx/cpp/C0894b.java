package org.cocos2dx.cpp;

import android.content.DialogInterface;

/* renamed from: org.cocos2dx.cpp.b */
class C0894b implements DialogInterface.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ AppActivity f2452a;

    C0894b(AppActivity appActivity) {
        this.f2452a = appActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            this.f2452a.startAppSettings();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
