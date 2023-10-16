package p036c.p037a.p038a.p039a.p050e;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import p036c.p037a.p038a.p039a.C0867o;
import p036c.p037a.p038a.p039a.p074p.C0870a;

@Deprecated
/* renamed from: c.a.a.a.e.m */
public class C0632m extends InetSocketAddress {

    /* renamed from: a */
    private final C0867o f1943a;

    public C0632m(C0867o oVar, InetAddress inetAddress, int i) {
        super(inetAddress, i);
        C0870a.m3042a(oVar, "HTTP host");
        this.f1943a = oVar;
    }

    /* renamed from: a */
    public C0867o mo2716a() {
        return this.f1943a;
    }

    public String toString() {
        return this.f1943a.mo3271b() + ":" + getPort();
    }
}
