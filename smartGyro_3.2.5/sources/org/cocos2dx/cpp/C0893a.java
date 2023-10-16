package org.cocos2dx.cpp;

import android.content.DialogInterface;

/* renamed from: org.cocos2dx.cpp.a */
class C0893a implements DialogInterface.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ AppActivity f2451a;

    C0893a(AppActivity appActivity) {
        this.f2451a = appActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            this.f2451a.finish();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
