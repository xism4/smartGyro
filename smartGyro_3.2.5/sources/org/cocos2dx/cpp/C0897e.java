package org.cocos2dx.cpp;

import android.content.DialogInterface;

/* renamed from: org.cocos2dx.cpp.e */
class C0897e implements DialogInterface.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ AppActivity f2455a;

    C0897e(AppActivity appActivity) {
        this.f2455a = appActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.f2455a.setLocationService();
    }
}
