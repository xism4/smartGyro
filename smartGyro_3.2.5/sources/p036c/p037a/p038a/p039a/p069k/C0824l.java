package p036c.p037a.p038a.p039a.p069k;

import java.util.List;
import java.util.NoSuchElementException;
import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0664h;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0871b;

/* renamed from: c.a.a.a.k.l */
public class C0824l implements C0664h {

    /* renamed from: a */
    protected final List<C0576e> f2381a;

    /* renamed from: b */
    protected int f2382b = mo3179b(-1);

    /* renamed from: c */
    protected int f2383c = -1;

    /* renamed from: d */
    protected String f2384d;

    public C0824l(List<C0576e> list, String str) {
        C0870a.m3042a(list, "Header list");
        this.f2381a = list;
        this.f2384d = str;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo3178a(int i) {
        if (this.f2384d == null) {
            return true;
        }
        return this.f2384d.equalsIgnoreCase(this.f2381a.get(i).getName());
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public int mo3179b(int i) {
        if (i < -1) {
            return -1;
        }
        int size = this.f2381a.size() - 1;
        boolean z = false;
        while (!z && i < size) {
            i++;
            z = mo3178a(i);
        }
        if (z) {
            return i;
        }
        return -1;
    }

    public boolean hasNext() {
        return this.f2382b >= 0;
    }

    public final Object next() {
        return nextHeader();
    }

    public C0576e nextHeader() {
        int i = this.f2382b;
        if (i >= 0) {
            this.f2383c = i;
            this.f2382b = mo3179b(i);
            return this.f2381a.get(i);
        }
        throw new NoSuchElementException("Iteration already finished.");
    }

    public void remove() {
        C0871b.m3050a(this.f2383c >= 0, "No header to remove");
        this.f2381a.remove(this.f2383c);
        this.f2383c = -1;
        this.f2382b--;
    }
}
