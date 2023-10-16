package p000a.p001a.p005c.p013f;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* renamed from: a.a.c.f.b */
public class C0078b<K, V> extends C0090i<K, V> implements Map<K, V> {

    /* renamed from: h */
    C0082f<K, V> f203h;

    public C0078b() {
    }

    public C0078b(int i) {
        super(i);
    }

    /* renamed from: b */
    private C0082f<K, V> m269b() {
        if (this.f203h == null) {
            this.f203h = new C0077a(this);
        }
        return this.f203h;
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return m269b().mo301d();
    }

    public Set<K> keySet() {
        return m269b().mo302e();
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        mo364a(this.f241g + map.size());
        for (Map.Entry next : map.entrySet()) {
            put(next.getKey(), next.getValue());
        }
    }

    public Collection<V> values() {
        return m269b().mo303f();
    }
}
