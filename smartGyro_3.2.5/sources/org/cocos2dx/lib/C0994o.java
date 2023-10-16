package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.o */
class C0994o implements Runnable {

    /* renamed from: a */
    final /* synthetic */ C0998q f2681a;

    C0994o(C0998q qVar) {
        this.f2681a = qVar;
    }

    public void run() {
        C0998q qVar = this.f2681a;
        qVar.f2686a.endAction = 0;
        Cocos2dxEditBoxHelper.__editBoxEditingDidBegin(qVar.f2687b.f2481f);
    }
}
