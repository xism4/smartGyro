package p036c.p037a.p038a.p039a.p060i.p062b;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.net.ssl.SSLException;
import p036c.p037a.p038a.p039a.C0848m;
import p036c.p037a.p038a.p039a.C0881r;
import p036c.p037a.p038a.p039a.p041b.C0562k;
import p036c.p037a.p038a.p039a.p041b.p044c.C0532l;
import p036c.p037a.p038a.p039a.p041b.p046e.C0540a;
import p036c.p037a.p038a.p039a.p072n.C0855e;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.i.b.l */
public class C0700l implements C0562k {

    /* renamed from: a */
    public static final C0700l f2079a = new C0700l();

    /* renamed from: b */
    private final int f2080b;

    /* renamed from: c */
    private final boolean f2081c;

    /* renamed from: d */
    private final Set<Class<? extends IOException>> f2082d;

    public C0700l() {
        this(3, false);
    }

    public C0700l(int i, boolean z) {
        this(i, z, Arrays.asList(new Class[]{InterruptedIOException.class, UnknownHostException.class, ConnectException.class, SSLException.class}));
    }

    protected C0700l(int i, boolean z, Collection<Class<? extends IOException>> collection) {
        this.f2080b = i;
        this.f2081c = z;
        this.f2082d = new HashSet();
        for (Class<? extends IOException> add : collection) {
            this.f2082d.add(add);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo2898a(C0881r rVar) {
        return !(rVar instanceof C0848m);
    }

    /* renamed from: a */
    public boolean mo2437a(IOException iOException, int i, C0855e eVar) {
        C0870a.m3042a(iOException, "Exception parameter");
        C0870a.m3042a(eVar, "HTTP context");
        if (i > this.f2080b || this.f2082d.contains(iOException.getClass())) {
            return false;
        }
        for (Class<? extends IOException> isInstance : this.f2082d) {
            if (isInstance.isInstance(iOException)) {
                return false;
            }
        }
        C0540a a = C0540a.m2199a(eVar);
        C0881r b = a.mo3261b();
        if (mo2899b(b)) {
            return false;
        }
        return mo2898a(b) || !a.mo3263d() || this.f2081c;
    }

    /* access modifiers changed from: protected */
    @Deprecated
    /* renamed from: b */
    public boolean mo2899b(C0881r rVar) {
        if (rVar instanceof C0711v) {
            rVar = ((C0711v) rVar).mo2925c();
        }
        return (rVar instanceof C0532l) && ((C0532l) rVar).isAborted();
    }
}
