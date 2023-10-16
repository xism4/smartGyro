package p036c.p037a.p038a.p039a.p050e.p053c;

import java.net.Socket;
import p036c.p037a.p038a.p039a.p070l.C0844g;

@Deprecated
/* renamed from: c.a.a.a.e.c.h */
class C0602h extends C0605k implements C0600f {

    /* renamed from: b */
    private final C0597c f1906b;

    C0602h(C0597c cVar) {
        super(cVar);
        this.f1906b = cVar;
    }

    /* renamed from: a */
    public Socket mo2672a(Socket socket, String str, int i, C0844g gVar) {
        return this.f1906b.createSocket(socket, str, i, true);
    }
}
