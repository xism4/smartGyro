package p036c.p037a.p038a.p039a.p050e.p052b;

import java.net.InetAddress;
import p036c.p037a.p038a.p039a.C0867o;
import p036c.p037a.p038a.p039a.p050e.p052b.C0590e;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0871b;
import p036c.p037a.p038a.p039a.p074p.C0877h;

/* renamed from: c.a.a.a.e.b.f */
public final class C0593f implements C0590e, Cloneable {

    /* renamed from: a */
    private final C0867o f1892a;

    /* renamed from: b */
    private final InetAddress f1893b;

    /* renamed from: c */
    private boolean f1894c;

    /* renamed from: d */
    private C0867o[] f1895d;

    /* renamed from: e */
    private C0590e.C0592b f1896e;

    /* renamed from: f */
    private C0590e.C0591a f1897f;

    /* renamed from: g */
    private boolean f1898g;

    public C0593f(C0587b bVar) {
        this(bVar.getTargetHost(), bVar.getLocalAddress());
    }

    public C0593f(C0867o oVar, InetAddress inetAddress) {
        C0870a.m3042a(oVar, "Target host");
        this.f1892a = oVar;
        this.f1893b = inetAddress;
        this.f1896e = C0590e.C0592b.PLAIN;
        this.f1897f = C0590e.C0591a.PLAIN;
    }

    /* renamed from: a */
    public final void mo2646a(C0867o oVar, boolean z) {
        C0870a.m3042a(oVar, "Proxy host");
        C0871b.m3050a(!this.f1894c, "Already connected");
        this.f1894c = true;
        this.f1895d = new C0867o[]{oVar};
        this.f1898g = z;
    }

    /* renamed from: a */
    public final void mo2647a(boolean z) {
        C0871b.m3050a(!this.f1894c, "Already connected");
        this.f1894c = true;
        this.f1898g = z;
    }

    /* renamed from: a */
    public final boolean mo2648a() {
        return this.f1894c;
    }

    /* renamed from: b */
    public void mo2649b() {
        this.f1894c = false;
        this.f1895d = null;
        this.f1896e = C0590e.C0592b.PLAIN;
        this.f1897f = C0590e.C0591a.PLAIN;
        this.f1898g = false;
    }

    /* renamed from: b */
    public final void mo2650b(boolean z) {
        C0871b.m3050a(this.f1894c, "No layered protocol unless connected");
        this.f1897f = C0590e.C0591a.LAYERED;
        this.f1898g = z;
    }

    /* renamed from: c */
    public final C0587b mo2651c() {
        if (!this.f1894c) {
            return null;
        }
        return new C0587b(this.f1892a, this.f1893b, this.f1895d, this.f1898g, this.f1896e, this.f1897f);
    }

    /* renamed from: c */
    public final void mo2652c(boolean z) {
        C0871b.m3050a(this.f1894c, "No tunnel unless connected");
        C0871b.m3049a((Object) this.f1895d, "No tunnel without proxy");
        this.f1896e = C0590e.C0592b.TUNNELLED;
        this.f1898g = z;
    }

    public Object clone() {
        return super.clone();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C0593f)) {
            return false;
        }
        C0593f fVar = (C0593f) obj;
        return this.f1894c == fVar.f1894c && this.f1898g == fVar.f1898g && this.f1896e == fVar.f1896e && this.f1897f == fVar.f1897f && C0877h.m3085a((Object) this.f1892a, (Object) fVar.f1892a) && C0877h.m3085a((Object) this.f1893b, (Object) fVar.f1893b) && C0877h.m3086a((Object[]) this.f1895d, (Object[]) fVar.f1895d);
    }

    public final int getHopCount() {
        if (!this.f1894c) {
            return 0;
        }
        C0867o[] oVarArr = this.f1895d;
        if (oVarArr == null) {
            return 1;
        }
        return 1 + oVarArr.length;
    }

    public final C0867o getHopTarget(int i) {
        C0870a.m3039a(i, "Hop index");
        int hopCount = getHopCount();
        C0870a.m3044a(i < hopCount, "Hop index exceeds tracked route length");
        return i < hopCount - 1 ? this.f1895d[i] : this.f1892a;
    }

    public final InetAddress getLocalAddress() {
        return this.f1893b;
    }

    public final C0867o getProxyHost() {
        C0867o[] oVarArr = this.f1895d;
        if (oVarArr == null) {
            return null;
        }
        return oVarArr[0];
    }

    public final C0867o getTargetHost() {
        return this.f1892a;
    }

    public final int hashCode() {
        int a = C0877h.m3083a(C0877h.m3083a(17, (Object) this.f1892a), (Object) this.f1893b);
        C0867o[] oVarArr = this.f1895d;
        if (oVarArr != null) {
            for (C0867o a2 : oVarArr) {
                a = C0877h.m3083a(a, (Object) a2);
            }
        }
        return C0877h.m3083a(C0877h.m3083a(C0877h.m3084a(C0877h.m3084a(a, this.f1894c), this.f1898g), (Object) this.f1896e), (Object) this.f1897f);
    }

    public final boolean isLayered() {
        return this.f1897f == C0590e.C0591a.LAYERED;
    }

    public final boolean isSecure() {
        return this.f1898g;
    }

    public final boolean isTunnelled() {
        return this.f1896e == C0590e.C0592b.TUNNELLED;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder((getHopCount() * 30) + 50);
        sb.append("RouteTracker[");
        InetAddress inetAddress = this.f1893b;
        if (inetAddress != null) {
            sb.append(inetAddress);
            sb.append("->");
        }
        sb.append('{');
        if (this.f1894c) {
            sb.append('c');
        }
        if (this.f1896e == C0590e.C0592b.TUNNELLED) {
            sb.append('t');
        }
        if (this.f1897f == C0590e.C0591a.LAYERED) {
            sb.append('l');
        }
        if (this.f1898g) {
            sb.append('s');
        }
        sb.append("}->");
        C0867o[] oVarArr = this.f1895d;
        if (oVarArr != null) {
            for (C0867o append : oVarArr) {
                sb.append(append);
                sb.append("->");
            }
        }
        sb.append(this.f1892a);
        sb.append(']');
        return sb.toString();
    }
}
