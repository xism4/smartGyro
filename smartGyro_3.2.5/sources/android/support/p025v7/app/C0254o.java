package android.support.p025v7.app;

/* renamed from: android.support.v7.app.o */
class C0254o implements Runnable {

    /* renamed from: a */
    final /* synthetic */ C0261v f759a;

    C0254o(C0261v vVar) {
        this.f759a = vVar;
    }

    public void run() {
        C0261v vVar = this.f759a;
        if ((vVar.f783O & 1) != 0) {
            vVar.mo1055e(0);
        }
        C0261v vVar2 = this.f759a;
        if ((vVar2.f783O & 4096) != 0) {
            vVar2.mo1055e(108);
        }
        C0261v vVar3 = this.f759a;
        vVar3.f782N = false;
        vVar3.f783O = 0;
    }
}
