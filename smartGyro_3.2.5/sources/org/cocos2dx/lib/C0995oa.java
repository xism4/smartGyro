package org.cocos2dx.lib;

import android.content.DialogInterface;

/* renamed from: org.cocos2dx.lib.oa */
class C0995oa implements DialogInterface.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ C0997pa f2682a;

    C0995oa(C0997pa paVar) {
        this.f2682a = paVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (this.f2682a.f2685a.mOnVideoEventListener != null) {
            this.f2682a.f2685a.mOnVideoEventListener.onVideoEvent(this.f2682a.f2685a.mViewTag, 3);
        }
    }
}
