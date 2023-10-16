package p036c.p037a.p038a.p039a.p060i.p067f;

import java.net.Socket;
import p036c.p037a.p038a.p039a.p068j.C0806b;
import p036c.p037a.p038a.p039a.p070l.C0844g;
import p036c.p037a.p038a.p039a.p074p.C0870a;

@Deprecated
/* renamed from: c.a.a.a.i.f.n */
public class C0802n extends C0791c implements C0806b {

    /* renamed from: o */
    private final Socket f2342o;

    /* renamed from: p */
    private boolean f2343p = false;

    public C0802n(Socket socket, int i, C0844g gVar) {
        C0870a.m3042a(socket, "Socket");
        this.f2342o = socket;
        i = i < 0 ? socket.getReceiveBufferSize() : i;
        mo3061a(socket.getInputStream(), i < 1024 ? 1024 : i, gVar);
    }

    /* renamed from: a */
    public boolean mo3013a() {
        return this.f2343p;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public int mo3063c() {
        int c = super.mo3063c();
        this.f2343p = c == -1;
        return c;
    }

    /* JADX INFO: finally extract failed */
    public boolean isDataAvailable(int i) {
        boolean d = mo3064d();
        if (d) {
            return d;
        }
        int soTimeout = this.f2342o.getSoTimeout();
        try {
            this.f2342o.setSoTimeout(i);
            mo3063c();
            boolean d2 = mo3064d();
            this.f2342o.setSoTimeout(soTimeout);
            return d2;
        } catch (Throwable th) {
            this.f2342o.setSoTimeout(soTimeout);
            throw th;
        }
    }
}
