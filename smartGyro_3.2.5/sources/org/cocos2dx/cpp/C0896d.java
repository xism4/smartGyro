package org.cocos2dx.cpp;

import android.content.DialogInterface;

/* renamed from: org.cocos2dx.cpp.d */
class C0896d implements DialogInterface.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ AppActivity f2454a;

    C0896d(AppActivity appActivity) {
        this.f2454a = appActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            this.f2454a.startAppSettings();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
