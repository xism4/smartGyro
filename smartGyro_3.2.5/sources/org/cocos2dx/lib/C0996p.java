package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.p */
class C0996p implements Runnable {

    /* renamed from: a */
    final /* synthetic */ String f2683a;

    /* renamed from: b */
    final /* synthetic */ C0998q f2684b;

    C0996p(C0998q qVar, String str) {
        this.f2684b = qVar;
        this.f2683a = str;
    }

    public void run() {
        C0998q qVar = this.f2684b;
        Cocos2dxEditBoxHelper.__editBoxEditingDidEnd(qVar.f2687b.f2481f, this.f2683a, qVar.f2686a.endAction);
    }
}
