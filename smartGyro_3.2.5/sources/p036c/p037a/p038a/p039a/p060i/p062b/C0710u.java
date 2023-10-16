package p036c.p037a.p038a.p039a.p060i.p062b;

import java.net.URI;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* renamed from: c.a.a.a.i.b.u */
public class C0710u extends AbstractList<Object> {

    /* renamed from: a */
    private final Set<URI> f2117a = new HashSet();

    /* renamed from: b */
    private final List<URI> f2118b = new ArrayList();

    /* renamed from: a */
    public void mo2915a(URI uri) {
        this.f2117a.add(uri);
        this.f2118b.add(uri);
    }

    public void add(int i, Object obj) {
        URI uri = (URI) obj;
        this.f2118b.add(i, uri);
        this.f2117a.add(uri);
    }

    /* renamed from: b */
    public boolean mo2917b(URI uri) {
        return this.f2117a.contains(uri);
    }

    public boolean contains(Object obj) {
        return this.f2117a.contains(obj);
    }

    public URI get(int i) {
        return this.f2118b.get(i);
    }

    public URI remove(int i) {
        URI remove = this.f2118b.remove(i);
        this.f2117a.remove(remove);
        if (this.f2118b.size() != this.f2117a.size()) {
            this.f2117a.addAll(this.f2118b);
        }
        return remove;
    }

    public Object set(int i, Object obj) {
        URI uri = (URI) obj;
        URI uri2 = this.f2118b.set(i, uri);
        this.f2117a.remove(uri2);
        this.f2117a.add(uri);
        if (this.f2118b.size() != this.f2117a.size()) {
            this.f2117a.addAll(this.f2118b);
        }
        return uri2;
    }

    public int size() {
        return this.f2118b.size();
    }
}
