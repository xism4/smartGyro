package p036c.p037a.p038a.p039a.p060i;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import p036c.p037a.p038a.p039a.C0869p;
import p036c.p037a.p038a.p039a.p060i.p067f.C0802n;
import p036c.p037a.p038a.p039a.p060i.p067f.C0803o;
import p036c.p037a.p038a.p039a.p068j.C0810f;
import p036c.p037a.p038a.p039a.p068j.C0811g;
import p036c.p037a.p038a.p039a.p070l.C0844g;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0871b;

@Deprecated
/* renamed from: c.a.a.a.i.f */
public class C0788f extends C0670a implements C0869p {

    /* renamed from: i */
    private volatile boolean f2278i;

    /* renamed from: j */
    private volatile Socket f2279j = null;

    /* renamed from: a */
    private static void m2849a(StringBuilder sb, SocketAddress socketAddress) {
        if (socketAddress instanceof InetSocketAddress) {
            InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddress;
            sb.append(inetSocketAddress.getAddress() != null ? inetSocketAddress.getAddress().getHostAddress() : inetSocketAddress.getAddress());
            sb.append(':');
            sb.append(inetSocketAddress.getPort());
            return;
        }
        sb.append(socketAddress);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C0810f mo3000a(Socket socket, int i, C0844g gVar) {
        return new C0802n(socket, i, gVar);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo2822a() {
        C0871b.m3050a(this.f2278i, "Connection is not open");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo3055a(Socket socket, C0844g gVar) {
        C0870a.m3042a(socket, "Socket");
        C0870a.m3042a(gVar, "HTTP parameters");
        this.f2279j = socket;
        int intParameter = gVar.getIntParameter("http.socket.buffer-size", -1);
        mo2823a(mo3000a(socket, intParameter, gVar), mo3001b(socket, intParameter, gVar), gVar);
        this.f2278i = true;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public C0811g mo3001b(Socket socket, int i, C0844g gVar) {
        return new C0803o(socket, i, gVar);
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0010 */
    /* JADX WARNING: Removed duplicated region for block: B:11:? A[ExcHandler: UnsupportedOperationException (unused java.lang.UnsupportedOperationException), SYNTHETIC, Splitter:B:8:0x0010] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void close() {
        /*
            r2 = this;
            boolean r0 = r2.f2278i
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            r0 = 0
            r2.f2278i = r0
            java.net.Socket r0 = r2.f2279j
            r2.mo2827e()     // Catch:{ all -> 0x0017 }
            r0.shutdownOutput()     // Catch:{ IOException -> 0x0010 }
        L_0x0010:
            r0.shutdownInput()     // Catch:{ UnsupportedOperationException -> 0x0013, UnsupportedOperationException -> 0x0013 }
        L_0x0013:
            r0.close()
            return
        L_0x0017:
            r1 = move-exception
            r0.close()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p036c.p037a.p038a.p039a.p060i.C0788f.close():void");
    }

    /* access modifiers changed from: protected */
    /* renamed from: g */
    public void mo3056g() {
        C0871b.m3050a(!this.f2278i, "Connection is already open");
    }

    public InetAddress getRemoteAddress() {
        if (this.f2279j != null) {
            return this.f2279j.getInetAddress();
        }
        return null;
    }

    public int getRemotePort() {
        if (this.f2279j != null) {
            return this.f2279j.getPort();
        }
        return -1;
    }

    public boolean isOpen() {
        return this.f2278i;
    }

    public void setSocketTimeout(int i) {
        mo2822a();
        if (this.f2279j != null) {
            try {
                this.f2279j.setSoTimeout(i);
            } catch (SocketException unused) {
            }
        }
    }

    public void shutdown() {
        this.f2278i = false;
        Socket socket = this.f2279j;
        if (socket != null) {
            socket.close();
        }
    }

    public String toString() {
        if (this.f2279j == null) {
            return super.toString();
        }
        StringBuilder sb = new StringBuilder();
        SocketAddress remoteSocketAddress = this.f2279j.getRemoteSocketAddress();
        SocketAddress localSocketAddress = this.f2279j.getLocalSocketAddress();
        if (!(remoteSocketAddress == null || localSocketAddress == null)) {
            m2849a(sb, localSocketAddress);
            sb.append("<->");
            m2849a(sb, remoteSocketAddress);
        }
        return sb.toString();
    }
}
