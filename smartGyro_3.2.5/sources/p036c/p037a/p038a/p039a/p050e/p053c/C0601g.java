package p036c.p037a.p038a.p039a.p050e.p053c;

import java.net.InetSocketAddress;
import java.net.Socket;
import p036c.p037a.p038a.p039a.p070l.C0844g;

@Deprecated
/* renamed from: c.a.a.a.e.c.g */
class C0601g implements C0600f {

    /* renamed from: a */
    private final C0596b f1905a;

    C0601g(C0596b bVar) {
        this.f1905a = bVar;
    }

    /* renamed from: a */
    public Socket mo2660a(C0844g gVar) {
        return this.f1905a.mo2660a(gVar);
    }

    /* renamed from: a */
    public Socket mo2672a(Socket socket, String str, int i, C0844g gVar) {
        return this.f1905a.mo2659a(socket, str, i, true);
    }

    /* renamed from: a */
    public Socket mo2662a(Socket socket, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, C0844g gVar) {
        return this.f1905a.mo2662a(socket, inetSocketAddress, inetSocketAddress2, gVar);
    }

    public boolean isSecure(Socket socket) {
        return this.f1905a.isSecure(socket);
    }
}
