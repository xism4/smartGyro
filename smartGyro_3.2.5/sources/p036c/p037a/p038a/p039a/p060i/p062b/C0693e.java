package p036c.p037a.p038a.p039a.p060i.p062b;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import p036c.p037a.p038a.p039a.p041b.C0559h;
import p036c.p037a.p038a.p039a.p057f.C0642c;
import p036c.p037a.p038a.p039a.p057f.C0644e;

/* renamed from: c.a.a.a.i.b.e */
public class C0693e implements C0559h, Serializable {

    /* renamed from: a */
    private final TreeSet<C0642c> f2069a = new TreeSet<>(new C0644e());

    /* renamed from: a */
    public synchronized void mo2595a(C0642c cVar) {
        if (cVar != null) {
            this.f2069a.remove(cVar);
            if (!cVar.isExpired(new Date())) {
                this.f2069a.add(cVar);
            }
        }
    }

    public synchronized boolean clearExpired(Date date) {
        boolean z = false;
        if (date == null) {
            return false;
        }
        Iterator<C0642c> it = this.f2069a.iterator();
        while (it.hasNext()) {
            if (it.next().isExpired(date)) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    public synchronized List<C0642c> getCookies() {
        return new ArrayList(this.f2069a);
    }

    public synchronized String toString() {
        return this.f2069a.toString();
    }
}
