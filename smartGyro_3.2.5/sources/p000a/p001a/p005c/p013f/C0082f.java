package p000a.p001a.p005c.p013f;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* renamed from: a.a.c.f.f */
abstract class C0082f<K, V> {

    /* renamed from: a */
    C0082f<K, V>.b f220a;

    /* renamed from: b */
    C0082f<K, V>.c f221b;

    /* renamed from: c */
    C0082f<K, V>.e f222c;

    /* renamed from: a.a.c.f.f$a */
    final class C0083a<T> implements Iterator<T> {

        /* renamed from: a */
        final int f223a;

        /* renamed from: b */
        int f224b;

        /* renamed from: c */
        int f225c;

        /* renamed from: d */
        boolean f226d = false;

        C0083a(int i) {
            this.f223a = i;
            this.f224b = C0082f.this.mo277c();
        }

        public boolean hasNext() {
            return this.f225c < this.f224b;
        }

        public T next() {
            if (hasNext()) {
                T a = C0082f.this.mo270a(this.f225c, this.f223a);
                this.f225c++;
                this.f226d = true;
                return a;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            if (this.f226d) {
                this.f225c--;
                this.f224b--;
                this.f226d = false;
                C0082f.this.mo273a(this.f225c);
                return;
            }
            throw new IllegalStateException();
        }
    }

    /* renamed from: a.a.c.f.f$b */
    final class C0084b implements Set<Map.Entry<K, V>> {
        C0084b() {
        }

        /* renamed from: a */
        public boolean mo307a(Map.Entry<K, V> entry) {
            throw new UnsupportedOperationException();
        }

        public /* bridge */ /* synthetic */ boolean add(Object obj) {
            mo307a((Map.Entry) obj);
            throw null;
        }

        public boolean addAll(Collection<? extends Map.Entry<K, V>> collection) {
            int c = C0082f.this.mo277c();
            for (Map.Entry entry : collection) {
                C0082f.this.mo274a(entry.getKey(), entry.getValue());
            }
            return c != C0082f.this.mo277c();
        }

        public void clear() {
            C0082f.this.mo272a();
        }

        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            int a = C0082f.this.mo269a(entry.getKey());
            if (a < 0) {
                return false;
            }
            return C0079c.m273a(C0082f.this.mo270a(a, 1), entry.getValue());
        }

        public boolean containsAll(Collection<?> collection) {
            for (Object contains : collection) {
                if (!contains(contains)) {
                    return false;
                }
            }
            return true;
        }

        public boolean equals(Object obj) {
            return C0082f.m293a(this, obj);
        }

        public int hashCode() {
            int i = 0;
            for (int c = C0082f.this.mo277c() - 1; c >= 0; c--) {
                Object a = C0082f.this.mo270a(c, 0);
                Object a2 = C0082f.this.mo270a(c, 1);
                i += (a == null ? 0 : a.hashCode()) ^ (a2 == null ? 0 : a2.hashCode());
            }
            return i;
        }

        public boolean isEmpty() {
            return C0082f.this.mo277c() == 0;
        }

        public Iterator<Map.Entry<K, V>> iterator() {
            return new C0086d();
        }

        public boolean remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        public boolean removeAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        public boolean retainAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        public int size() {
            return C0082f.this.mo277c();
        }

        public Object[] toArray() {
            throw new UnsupportedOperationException();
        }

        public <T> T[] toArray(T[] tArr) {
            throw new UnsupportedOperationException();
        }
    }

    /* renamed from: a.a.c.f.f$c */
    final class C0085c implements Set<K> {
        C0085c() {
        }

        public boolean add(K k) {
            throw new UnsupportedOperationException();
        }

        public boolean addAll(Collection<? extends K> collection) {
            throw new UnsupportedOperationException();
        }

        public void clear() {
            C0082f.this.mo272a();
        }

        public boolean contains(Object obj) {
            return C0082f.this.mo269a(obj) >= 0;
        }

        public boolean containsAll(Collection<?> collection) {
            return C0082f.m292a(C0082f.this.mo276b(), collection);
        }

        public boolean equals(Object obj) {
            return C0082f.m293a(this, obj);
        }

        public int hashCode() {
            int i = 0;
            for (int c = C0082f.this.mo277c() - 1; c >= 0; c--) {
                Object a = C0082f.this.mo270a(c, 0);
                i += a == null ? 0 : a.hashCode();
            }
            return i;
        }

        public boolean isEmpty() {
            return C0082f.this.mo277c() == 0;
        }

        public Iterator<K> iterator() {
            return new C0083a(0);
        }

        public boolean remove(Object obj) {
            int a = C0082f.this.mo269a(obj);
            if (a < 0) {
                return false;
            }
            C0082f.this.mo273a(a);
            return true;
        }

        public boolean removeAll(Collection<?> collection) {
            return C0082f.m294b(C0082f.this.mo276b(), collection);
        }

        public boolean retainAll(Collection<?> collection) {
            return C0082f.m295c(C0082f.this.mo276b(), collection);
        }

        public int size() {
            return C0082f.this.mo277c();
        }

        public Object[] toArray() {
            return C0082f.this.mo300b(0);
        }

        public <T> T[] toArray(T[] tArr) {
            return C0082f.this.mo299a(tArr, 0);
        }
    }

    /* renamed from: a.a.c.f.f$d */
    final class C0086d implements Iterator<Map.Entry<K, V>>, Map.Entry<K, V> {

        /* renamed from: a */
        int f230a;

        /* renamed from: b */
        int f231b;

        /* renamed from: c */
        boolean f232c = false;

        C0086d() {
            this.f230a = C0082f.this.mo277c() - 1;
            this.f231b = -1;
        }

        public boolean equals(Object obj) {
            if (!this.f232c) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            } else if (!(obj instanceof Map.Entry)) {
                return false;
            } else {
                Map.Entry entry = (Map.Entry) obj;
                return C0079c.m273a(entry.getKey(), C0082f.this.mo270a(this.f231b, 0)) && C0079c.m273a(entry.getValue(), C0082f.this.mo270a(this.f231b, 1));
            }
        }

        public K getKey() {
            if (this.f232c) {
                return C0082f.this.mo270a(this.f231b, 0);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public V getValue() {
            if (this.f232c) {
                return C0082f.this.mo270a(this.f231b, 1);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public boolean hasNext() {
            return this.f231b < this.f230a;
        }

        public int hashCode() {
            if (this.f232c) {
                int i = 0;
                Object a = C0082f.this.mo270a(this.f231b, 0);
                Object a2 = C0082f.this.mo270a(this.f231b, 1);
                int hashCode = a == null ? 0 : a.hashCode();
                if (a2 != null) {
                    i = a2.hashCode();
                }
                return hashCode ^ i;
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public Map.Entry<K, V> next() {
            if (hasNext()) {
                this.f231b++;
                this.f232c = true;
                return this;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            if (this.f232c) {
                C0082f.this.mo273a(this.f231b);
                this.f231b--;
                this.f230a--;
                this.f232c = false;
                return;
            }
            throw new IllegalStateException();
        }

        public V setValue(V v) {
            if (this.f232c) {
                return C0082f.this.mo271a(this.f231b, v);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public String toString() {
            return getKey() + "=" + getValue();
        }
    }

    /* renamed from: a.a.c.f.f$e */
    final class C0087e implements Collection<V> {
        C0087e() {
        }

        public boolean add(V v) {
            throw new UnsupportedOperationException();
        }

        public boolean addAll(Collection<? extends V> collection) {
            throw new UnsupportedOperationException();
        }

        public void clear() {
            C0082f.this.mo272a();
        }

        public boolean contains(Object obj) {
            return C0082f.this.mo275b(obj) >= 0;
        }

        public boolean containsAll(Collection<?> collection) {
            for (Object contains : collection) {
                if (!contains(contains)) {
                    return false;
                }
            }
            return true;
        }

        public boolean isEmpty() {
            return C0082f.this.mo277c() == 0;
        }

        public Iterator<V> iterator() {
            return new C0083a(1);
        }

        public boolean remove(Object obj) {
            int b = C0082f.this.mo275b(obj);
            if (b < 0) {
                return false;
            }
            C0082f.this.mo273a(b);
            return true;
        }

        public boolean removeAll(Collection<?> collection) {
            int c = C0082f.this.mo277c();
            int i = 0;
            boolean z = false;
            while (i < c) {
                if (collection.contains(C0082f.this.mo270a(i, 1))) {
                    C0082f.this.mo273a(i);
                    i--;
                    c--;
                    z = true;
                }
                i++;
            }
            return z;
        }

        public boolean retainAll(Collection<?> collection) {
            int c = C0082f.this.mo277c();
            int i = 0;
            boolean z = false;
            while (i < c) {
                if (!collection.contains(C0082f.this.mo270a(i, 1))) {
                    C0082f.this.mo273a(i);
                    i--;
                    c--;
                    z = true;
                }
                i++;
            }
            return z;
        }

        public int size() {
            return C0082f.this.mo277c();
        }

        public Object[] toArray() {
            return C0082f.this.mo300b(1);
        }

        public <T> T[] toArray(T[] tArr) {
            return C0082f.this.mo299a(tArr, 1);
        }
    }

    C0082f() {
    }

    /* renamed from: a */
    public static <K, V> boolean m292a(Map<K, V> map, Collection<?> collection) {
        for (Object containsKey : collection) {
            if (!map.containsKey(containsKey)) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public static <T> boolean m293a(Set<T> set, Object obj) {
        if (set == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set2 = (Set) obj;
            try {
                return set.size() == set2.size() && set.containsAll(set2);
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    /* renamed from: b */
    public static <K, V> boolean m294b(Map<K, V> map, Collection<?> collection) {
        int size = map.size();
        for (Object remove : collection) {
            map.remove(remove);
        }
        return size != map.size();
    }

    /* renamed from: c */
    public static <K, V> boolean m295c(Map<K, V> map, Collection<?> collection) {
        int size = map.size();
        Iterator<K> it = map.keySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(it.next())) {
                it.remove();
            }
        }
        return size != map.size();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract int mo269a(Object obj);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract Object mo270a(int i, int i2);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract V mo271a(int i, V v);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo272a();

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo273a(int i);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo274a(K k, V v);

    /* renamed from: a */
    public <T> T[] mo299a(T[] tArr, int i) {
        int c = mo277c();
        if (tArr.length < c) {
            tArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), c);
        }
        for (int i2 = 0; i2 < c; i2++) {
            tArr[i2] = mo270a(i2, i);
        }
        if (tArr.length > c) {
            tArr[c] = null;
        }
        return tArr;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract int mo275b(Object obj);

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract Map<K, V> mo276b();

    /* renamed from: b */
    public Object[] mo300b(int i) {
        int c = mo277c();
        Object[] objArr = new Object[c];
        for (int i2 = 0; i2 < c; i2++) {
            objArr[i2] = mo270a(i2, i);
        }
        return objArr;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public abstract int mo277c();

    /* renamed from: d */
    public Set<Map.Entry<K, V>> mo301d() {
        if (this.f220a == null) {
            this.f220a = new C0084b();
        }
        return this.f220a;
    }

    /* renamed from: e */
    public Set<K> mo302e() {
        if (this.f221b == null) {
            this.f221b = new C0085c();
        }
        return this.f221b;
    }

    /* renamed from: f */
    public Collection<V> mo303f() {
        if (this.f222c == null) {
            this.f222c = new C0087e();
        }
        return this.f222c;
    }
}
