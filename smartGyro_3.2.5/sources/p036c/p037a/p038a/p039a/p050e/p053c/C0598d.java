package p036c.p037a.p038a.p039a.p050e.p053c;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import p036c.p037a.p038a.p039a.p050e.C0624f;
import p036c.p037a.p038a.p039a.p070l.C0842e;
import p036c.p037a.p038a.p039a.p070l.C0844g;
import p036c.p037a.p038a.p039a.p074p.C0870a;

@Deprecated
/* renamed from: c.a.a.a.e.c.d */
public class C0598d implements C0606l, C0604j {

    /* renamed from: a */
    private final C0595a f1899a = null;

    /* renamed from: a */
    public static C0598d m2320a() {
        return new C0598d();
    }

    /* renamed from: a */
    public Socket mo2660a(C0844g gVar) {
        return new Socket();
    }

    @Deprecated
    /* renamed from: a */
    public Socket mo2661a(Socket socket, String str, int i, InetAddress inetAddress, int i2, C0844g gVar) {
        InetSocketAddress inetSocketAddress;
        if (inetAddress != null || i2 > 0) {
            if (i2 <= 0) {
                i2 = 0;
            }
            inetSocketAddress = new InetSocketAddress(inetAddress, i2);
        } else {
            inetSocketAddress = null;
        }
        C0595a aVar = this.f1899a;
        return mo2662a(socket, new InetSocketAddress(aVar != null ? aVar.resolve(str) : InetAddress.getByName(str), i), inetSocketAddress, gVar);
    }

    /* renamed from: a */
    public Socket mo2662a(Socket socket, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, C0844g gVar) {
        C0870a.m3042a(inetSocketAddress, "Remote address");
        C0870a.m3042a(gVar, "HTTP parameters");
        if (socket == null) {
            socket = createSocket();
        }
        if (inetSocketAddress2 != null) {
            socket.setReuseAddress(C0842e.m2983c(gVar));
            socket.bind(inetSocketAddress2);
        }
        int a = C0842e.m2977a(gVar);
        try {
            socket.setSoTimeout(C0842e.m2984d(gVar));
            socket.connect(inetSocketAddress, a);
            return socket;
        } catch (SocketTimeoutException unused) {
            throw new C0624f("Connect to " + inetSocketAddress + " timed out");
        }
    }

    public Socket createSocket() {
        return new Socket();
    }

    public final boolean isSecure(Socket socket) {
        return false;
    }
}
