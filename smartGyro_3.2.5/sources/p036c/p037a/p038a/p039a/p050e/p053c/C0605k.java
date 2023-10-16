package p036c.p037a.p038a.p039a.p050e.p053c;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import p036c.p037a.p038a.p039a.p070l.C0844g;

@Deprecated
/* renamed from: c.a.a.a.e.c.k */
class C0605k implements C0604j {

    /* renamed from: a */
    private final C0606l f1908a;

    C0605k(C0606l lVar) {
        this.f1908a = lVar;
    }

    /* renamed from: a */
    public Socket mo2660a(C0844g gVar) {
        return this.f1908a.createSocket();
    }

    /* renamed from: a */
    public Socket mo2662a(Socket socket, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, C0844g gVar) {
        int i;
        InetAddress inetAddress;
        String hostName = inetSocketAddress.getHostName();
        int port = inetSocketAddress.getPort();
        if (inetSocketAddress2 != null) {
            inetAddress = inetSocketAddress2.getAddress();
            i = inetSocketAddress2.getPort();
        } else {
            inetAddress = null;
            i = 0;
        }
        return this.f1908a.mo2661a(socket, hostName, port, inetAddress, i, gVar);
    }

    public boolean equals(Object obj) {
        C0606l lVar;
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof C0605k) {
            lVar = this.f1908a;
            obj = ((C0605k) obj).f1908a;
        } else {
            lVar = this.f1908a;
        }
        return lVar.equals(obj);
    }

    public int hashCode() {
        return this.f1908a.hashCode();
    }

    public boolean isSecure(Socket socket) {
        return this.f1908a.isSecure(socket);
    }
}
