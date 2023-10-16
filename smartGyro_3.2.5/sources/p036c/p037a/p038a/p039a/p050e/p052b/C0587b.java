package p036c.p037a.p038a.p039a.p050e.p052b;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import p036c.p037a.p038a.p039a.C0867o;
import p036c.p037a.p038a.p039a.p050e.p052b.C0590e;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0877h;

/* renamed from: c.a.a.a.e.b.b */
public final class C0587b implements C0590e, Cloneable {

    /* renamed from: a */
    private final C0867o f1880a;

    /* renamed from: b */
    private final InetAddress f1881b;

    /* renamed from: c */
    private final List<C0867o> f1882c;

    /* renamed from: d */
    private final C0590e.C0592b f1883d;

    /* renamed from: e */
    private final C0590e.C0591a f1884e;

    /* renamed from: f */
    private final boolean f1885f;

    public C0587b(C0867o oVar) {
        this(oVar, (InetAddress) null, (List<C0867o>) Collections.emptyList(), false, C0590e.C0592b.PLAIN, C0590e.C0591a.PLAIN);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public C0587b(C0867o oVar, InetAddress inetAddress, C0867o oVar2, boolean z) {
        this(oVar, inetAddress, (List<C0867o>) Collections.singletonList(oVar2), z, z ? C0590e.C0592b.TUNNELLED : C0590e.C0592b.PLAIN, z ? C0590e.C0591a.LAYERED : C0590e.C0591a.PLAIN);
        C0870a.m3042a(oVar2, "Proxy host");
    }

    private C0587b(C0867o oVar, InetAddress inetAddress, List<C0867o> list, boolean z, C0590e.C0592b bVar, C0590e.C0591a aVar) {
        C0870a.m3042a(oVar, "Target host");
        this.f1880a = m2308a(oVar);
        this.f1881b = inetAddress;
        this.f1882c = (list == null || list.isEmpty()) ? null : new ArrayList(list);
        if (bVar == C0590e.C0592b.TUNNELLED) {
            C0870a.m3044a(this.f1882c != null, "Proxy required if tunnelled");
        }
        this.f1885f = z;
        this.f1883d = bVar == null ? C0590e.C0592b.PLAIN : bVar;
        this.f1884e = aVar == null ? C0590e.C0591a.PLAIN : aVar;
    }

    public C0587b(C0867o oVar, InetAddress inetAddress, boolean z) {
        this(oVar, inetAddress, (List<C0867o>) Collections.emptyList(), z, C0590e.C0592b.PLAIN, C0590e.C0591a.PLAIN);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public C0587b(C0867o oVar, InetAddress inetAddress, C0867o[] oVarArr, boolean z, C0590e.C0592b bVar, C0590e.C0591a aVar) {
        this(oVar, inetAddress, (List<C0867o>) oVarArr != null ? Arrays.asList(oVarArr) : null, z, bVar, aVar);
    }

    /* renamed from: a */
    private static int m2307a(String str) {
        if ("http".equalsIgnoreCase(str)) {
            return 80;
        }
        return "https".equalsIgnoreCase(str) ? 443 : -1;
    }

    /* renamed from: a */
    private static C0867o m2308a(C0867o oVar) {
        if (oVar.mo3272c() >= 0) {
            return oVar;
        }
        InetAddress a = oVar.mo3270a();
        String d = oVar.mo3274d();
        return a != null ? new C0867o(a, m2307a(d), d) : new C0867o(oVar.mo3271b(), m2307a(d), d);
    }

    public Object clone() {
        return super.clone();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0587b)) {
            return false;
        }
        C0587b bVar = (C0587b) obj;
        return this.f1885f == bVar.f1885f && this.f1883d == bVar.f1883d && this.f1884e == bVar.f1884e && C0877h.m3085a((Object) this.f1880a, (Object) bVar.f1880a) && C0877h.m3085a((Object) this.f1881b, (Object) bVar.f1881b) && C0877h.m3085a((Object) this.f1882c, (Object) bVar.f1882c);
    }

    public final int getHopCount() {
        List<C0867o> list = this.f1882c;
        if (list != null) {
            return 1 + list.size();
        }
        return 1;
    }

    public final C0867o getHopTarget(int i) {
        C0870a.m3039a(i, "Hop index");
        int hopCount = getHopCount();
        C0870a.m3044a(i < hopCount, "Hop index exceeds tracked route length");
        return i < hopCount - 1 ? this.f1882c.get(i) : this.f1880a;
    }

    public final InetAddress getLocalAddress() {
        return this.f1881b;
    }

    public final C0867o getProxyHost() {
        List<C0867o> list = this.f1882c;
        if (list == null || list.isEmpty()) {
            return null;
        }
        return this.f1882c.get(0);
    }

    public final C0867o getTargetHost() {
        return this.f1880a;
    }

    public final int hashCode() {
        int a = C0877h.m3083a(C0877h.m3083a(17, (Object) this.f1880a), (Object) this.f1881b);
        List<C0867o> list = this.f1882c;
        if (list != null) {
            for (C0867o a2 : list) {
                a = C0877h.m3083a(a, (Object) a2);
            }
        }
        return C0877h.m3083a(C0877h.m3083a(C0877h.m3084a(a, this.f1885f), (Object) this.f1883d), (Object) this.f1884e);
    }

    public final boolean isLayered() {
        return this.f1884e == C0590e.C0591a.LAYERED;
    }

    public final boolean isSecure() {
        return this.f1885f;
    }

    public final boolean isTunnelled() {
        return this.f1883d == C0590e.C0592b.TUNNELLED;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder((getHopCount() * 30) + 50);
        InetAddress inetAddress = this.f1881b;
        if (inetAddress != null) {
            sb.append(inetAddress);
            sb.append("->");
        }
        sb.append('{');
        if (this.f1883d == C0590e.C0592b.TUNNELLED) {
            sb.append('t');
        }
        if (this.f1884e == C0590e.C0591a.LAYERED) {
            sb.append('l');
        }
        if (this.f1885f) {
            sb.append('s');
        }
        sb.append("}->");
        List<C0867o> list = this.f1882c;
        if (list != null) {
            for (C0867o append : list) {
                sb.append(append);
                sb.append("->");
            }
        }
        sb.append(this.f1880a);
        return sb.toString();
    }
}
