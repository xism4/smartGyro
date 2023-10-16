package p000a.p001a.p005c.p011d;

import android.os.Handler;
import android.os.Message;

/* renamed from: a.a.c.d.g */
class C0068g implements Handler.Callback {

    /* renamed from: a */
    final /* synthetic */ C0072k f167a;

    C0068g(C0072k kVar) {
        this.f167a = kVar;
    }

    public boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 0) {
            this.f167a.mo242a();
            return true;
        } else if (i != 1) {
            return true;
        } else {
            this.f167a.mo243a((Runnable) message.obj);
            return true;
        }
    }
}
