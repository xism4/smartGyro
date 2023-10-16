package p026b.p033c.p034a.p035a;

import android.os.SystemClock;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Iterator;
import javax.net.ssl.SSLException;
import p036c.p037a.p038a.p039a.C0485A;
import p036c.p037a.p038a.p039a.p041b.C0562k;
import p036c.p037a.p038a.p039a.p041b.p044c.C0532l;
import p036c.p037a.p038a.p039a.p072n.C0855e;

/* renamed from: b.c.a.a.t */
class C0483t implements C0562k {

    /* renamed from: a */
    private static final HashSet<Class<?>> f1737a = new HashSet<>();

    /* renamed from: b */
    private static final HashSet<Class<?>> f1738b = new HashSet<>();

    /* renamed from: c */
    private final int f1739c;

    /* renamed from: d */
    private final int f1740d;

    static {
        f1737a.add(C0485A.class);
        f1737a.add(UnknownHostException.class);
        f1737a.add(SocketException.class);
        f1738b.add(InterruptedIOException.class);
        f1738b.add(SSLException.class);
    }

    public C0483t(int i, int i2) {
        this.f1739c = i;
        this.f1740d = i2;
    }

    /* renamed from: a */
    static void m2107a(Class<?> cls) {
        f1737a.add(cls);
    }

    /* renamed from: a */
    public boolean mo2437a(IOException iOException, int i, C0855e eVar) {
        Boolean bool = (Boolean) eVar.getAttribute("http.request_sent");
        boolean z = true;
        if (bool == null || !bool.booleanValue()) {
        }
        if (i > this.f1739c || (!mo2438a(f1737a, iOException) && mo2438a(f1738b, iOException))) {
            z = false;
        }
        if (z && ((C0532l) eVar.getAttribute("http.request")) == null) {
            return false;
        }
        if (z) {
            SystemClock.sleep((long) this.f1740d);
        } else {
            iOException.printStackTrace();
        }
        return z;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo2438a(HashSet<Class<?>> hashSet, Throwable th) {
        Iterator<Class<?>> it = hashSet.iterator();
        while (it.hasNext()) {
            if (it.next().isInstance(th)) {
                return true;
            }
        }
        return false;
    }
}
