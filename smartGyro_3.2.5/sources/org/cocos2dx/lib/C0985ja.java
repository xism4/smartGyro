package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.ja */
class C0985ja implements Runnable {

    /* renamed from: a */
    final /* synthetic */ byte[] f2667a;

    C0985ja(byte[] bArr) {
        this.f2667a = bArr;
    }

    public void run() {
        Cocos2dxHelper.nativeSetEditTextDialogResult(this.f2667a);
    }
}
