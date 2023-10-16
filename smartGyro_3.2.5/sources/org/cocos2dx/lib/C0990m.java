package org.cocos2dx.lib;

import android.text.Editable;

/* renamed from: org.cocos2dx.lib.m */
class C0990m implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Editable f2675a;

    /* renamed from: b */
    final /* synthetic */ C0992n f2676b;

    C0990m(C0992n nVar, Editable editable) {
        this.f2676b = nVar;
        this.f2675a = editable;
    }

    public void run() {
        Cocos2dxEditBoxHelper.__editBoxEditingChanged(this.f2676b.f2679b.f2481f, this.f2675a.toString());
    }
}
