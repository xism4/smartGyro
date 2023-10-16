package com.org.android.util;

import a.a.c.f.f;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

abstract class MapCollections<K, V> {
    f<K, V>.b mEntrySet;
    f<K, V>.c mKeySet;
    f<K, V>.e mValues;

    final class ArrayIterator<T> implements Iterator<T> {
        boolean mCanRemove = false;
        int mIndex;
        final int mOffset;
        int mSize;

        ArrayIterator(int i) {
            this.mOffset = i;
            this.mSize = MapCollections.this.colGetSize();
        }

        public boolean hasNext() {
            return this.mIndex < this.mSize;
        }

        public Object next() {
            if (hasNext()) {
                Object $r2 = MapCollections.this.colGetEntry(this.mIndex, this.mOffset);
                this.mIndex++;
                this.mCanRemove = true;
                return $r2;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            if (this.mCanRemove) {
                this.mIndex--;
                this.mSize--;
                this.mCanRemove = false;
                MapCollections.this.colRemoveAt(this.mIndex);
                return;
            }
            throw new IllegalStateException();
        }
    }

    final class EntrySet implements Set<Map.Entry<K, V>> {
        EntrySet() {
        }

        public /* bridge */ /* synthetic */ boolean add(Object obj) {
            add((Map.Entry) obj);
            throw new NullPointerException("Null throw statement replaced by Soot");
        }

        public boolean add(Map.Entry entry) {
            throw new UnsupportedOperationException();
        }

        public boolean addAll(Collection collection) {
            int $i0 = MapCollections.this.colGetSize();
            Iterator $r3 = collection.iterator();
            while ($r3.hasNext()) {
                Map.Entry $r5 = (Map.Entry) $r3.next();
                MapCollections.this.colPut($r5.getKey(), $r5.getValue());
            }
            return $i0 != MapCollections.this.colGetSize();
        }

        public void clear() {
            MapCollections.this.colClear();
        }

        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry $r2 = (Map.Entry) obj;
            int $i0 = MapCollections.this.colIndexOfKey($r2.getKey());
            if ($i0 < 0) {
                return false;
            }
            return ContainerHelpers.equal(MapCollections.this.colGetEntry($i0, 1), $r2.getValue());
        }

        public boolean containsAll(Collection collection) {
            for (Object $r3 : collection) {
                if (!contains($r3)) {
                    return false;
                }
            }
            return true;
        }

        public boolean equals(Object obj) {
            return MapCollections.equalsSetHelper(this, obj);
        }

        public int hashCode() {
            int $i1 = 0;
            for (int $i0 = MapCollections.this.colGetSize() - 1; $i0 >= 0; $i0--) {
                Object $r2 = MapCollections.this.colGetEntry($i0, 0);
                Object $r3 = MapCollections.this.colGetEntry($i0, 1);
                $i1 += ($r2 == null ? 0 : $r2.hashCode()) ^ ($r3 == null ? 0 : $r3.hashCode());
            }
            return $i1;
        }

        public boolean isEmpty() {
            return MapCollections.this.colGetSize() == 0;
        }

        public Iterator iterator() {
            return new MapIterator();
        }

        public boolean remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        public boolean removeAll(Collection collection) {
            throw new UnsupportedOperationException();
        }

        public boolean retainAll(Collection collection) {
            throw new UnsupportedOperationException();
        }

        public int size() {
            return MapCollections.this.colGetSize();
        }

        public Object[] toArray() {
            throw new UnsupportedOperationException();
        }

        public Object[] toArray(Object[] objArr) {
            throw new UnsupportedOperationException();
        }
    }

    final class KeySet implements Set<K> {
        KeySet() {
        }

        public boolean add(Object obj) {
            throw new UnsupportedOperationException();
        }

        public boolean addAll(Collection collection) {
            throw new UnsupportedOperationException();
        }

        public void clear() {
            MapCollections.this.colClear();
        }

        public boolean contains(Object obj) {
            return MapCollections.this.colIndexOfKey(obj) >= 0;
        }

        public boolean containsAll(Collection collection) {
            return MapCollections.containsAllHelper(MapCollections.this.colGetMap(), collection);
        }

        public boolean equals(Object obj) {
            return MapCollections.equalsSetHelper(this, obj);
        }

        public int hashCode() {
            int $i1 = 0;
            for (int $i0 = MapCollections.this.colGetSize() - 1; $i0 >= 0; $i0--) {
                Object $r2 = MapCollections.this.colGetEntry($i0, 0);
                $i1 += $r2 == null ? 0 : $r2.hashCode();
            }
            return $i1;
        }

        public boolean isEmpty() {
            return MapCollections.this.colGetSize() == 0;
        }

        public Iterator iterator() {
            return new ArrayIterator(0);
        }

        public boolean remove(Object obj) {
            int $i0 = MapCollections.this.colIndexOfKey(obj);
            if ($i0 < 0) {
                return false;
            }
            MapCollections.this.colRemoveAt($i0);
            return true;
        }

        public boolean removeAll(Collection collection) {
            return MapCollections.removeAllHelper(MapCollections.this.colGetMap(), collection);
        }

        public boolean retainAll(Collection collection) {
            return MapCollections.retainAllHelper(MapCollections.this.colGetMap(), collection);
        }

        public int size() {
            return MapCollections.this.colGetSize();
        }

        public Object[] toArray() {
            return MapCollections.this.toArrayHelper(0);
        }

        public Object[] toArray(Object[] objArr) {
            return MapCollections.this.toArrayHelper(objArr, 0);
        }
    }

    final class MapIterator implements Iterator<Map.Entry<K, V>>, Map.Entry<K, V> {
        int mEnd;
        boolean mEntryValid = false;
        int mIndex;

        MapIterator() {
            this.mEnd = MapCollections.this.colGetSize() - 1;
            this.mIndex = -1;
        }

        public boolean equals(Object obj) {
            if (!this.mEntryValid) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            } else if (!(obj instanceof Map.Entry)) {
                return false;
            } else {
                Map.Entry $r2 = (Map.Entry) obj;
                return ContainerHelpers.equal($r2.getKey(), MapCollections.this.colGetEntry(this.mIndex, 0)) && ContainerHelpers.equal($r2.getValue(), MapCollections.this.colGetEntry(this.mIndex, 1));
            }
        }

        public Object getKey() {
            if (this.mEntryValid) {
                return MapCollections.this.colGetEntry(this.mIndex, 0);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public Object getValue() {
            if (this.mEntryValid) {
                return MapCollections.this.colGetEntry(this.mIndex, 1);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public boolean hasNext() {
            return this.mIndex < this.mEnd;
        }

        public int hashCode() {
            if (this.mEntryValid) {
                int $i1 = 0;
                Object $r2 = MapCollections.this.colGetEntry(this.mIndex, 0);
                Object $r3 = MapCollections.this.colGetEntry(this.mIndex, 1);
                int $i0 = $r2 == null ? 0 : $r2.hashCode();
                if ($r3 != null) {
                    $i1 = $r3.hashCode();
                }
                return $i0 ^ $i1;
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public Map.Entry next() {
            if (hasNext()) {
                this.mIndex++;
                this.mEntryValid = true;
                return this;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            if (this.mEntryValid) {
                MapCollections.this.colRemoveAt(this.mIndex);
                this.mIndex--;
                this.mEnd--;
                this.mEntryValid = false;
                return;
            }
            throw new IllegalStateException();
        }

        public Object setValue(Object obj) {
            if (this.mEntryValid) {
                return MapCollections.this.colSetValue(this.mIndex, obj);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public String toString() {
            return getKey() + "=" + getValue();
        }
    }

    final class ValuesCollection implements Collection<V> {
        ValuesCollection() {
        }

        public boolean add(Object obj) {
            throw new UnsupportedOperationException();
        }

        public boolean addAll(Collection collection) {
            throw new UnsupportedOperationException();
        }

        public void clear() {
            MapCollections.this.colClear();
        }

        public boolean contains(Object obj) {
            return MapCollections.this.colIndexOfValue(obj) >= 0;
        }

        public boolean containsAll(Collection collection) {
            for (Object $r3 : collection) {
                if (!contains($r3)) {
                    return false;
                }
            }
            return true;
        }

        public boolean isEmpty() {
            return MapCollections.this.colGetSize() == 0;
        }

        public Iterator iterator() {
            return new ArrayIterator(1);
        }

        public boolean remove(Object obj) {
            int $i0 = MapCollections.this.colIndexOfValue(obj);
            if ($i0 < 0) {
                return false;
            }
            MapCollections.this.colRemoveAt($i0);
            return true;
        }

        public boolean removeAll(Collection collection) {
            int $i0 = MapCollections.this.colGetSize();
            int $i1 = 0;
            boolean $z0 = false;
            while ($i1 < $i0) {
                if (collection.contains(MapCollections.this.colGetEntry($i1, 1))) {
                    MapCollections.this.colRemoveAt($i1);
                    $i1--;
                    $i0--;
                    $z0 = true;
                }
                $i1++;
            }
            return $z0;
        }

        public boolean retainAll(Collection collection) {
            int $i0 = MapCollections.this.colGetSize();
            int $i1 = 0;
            boolean $z0 = false;
            while ($i1 < $i0) {
                if (!collection.contains(MapCollections.this.colGetEntry($i1, 1))) {
                    MapCollections.this.colRemoveAt($i1);
                    $i1--;
                    $i0--;
                    $z0 = true;
                }
                $i1++;
            }
            return $z0;
        }

        public int size() {
            return MapCollections.this.colGetSize();
        }

        public Object[] toArray() {
            return MapCollections.this.toArrayHelper(1);
        }

        public Object[] toArray(Object[] objArr) {
            return MapCollections.this.toArrayHelper(objArr, 1);
        }
    }

    MapCollections() {
    }

    public static boolean containsAllHelper(Map map, Collection collection) {
        for (Object $r3 : collection) {
            if (!map.containsKey($r3)) {
                return false;
            }
        }
        return true;
    }

    public static boolean equalsSetHelper(Set set, Object obj) {
        if (set == obj) {
            return true;
        }
        if (!(obj instanceof Set)) {
            return false;
        }
        Set $r4 = (Set) obj;
        try {
            if (set.size() == $r4.size()) {
                return set.containsAll($r4);
            }
            return false;
        } catch (NullPointerException e) {
            return false;
        } catch (ClassCastException e2) {
            return false;
        }
    }

    public static boolean removeAllHelper(Map map, Collection collection) {
        int $i0 = map.size();
        for (Object $r3 : collection) {
            map.remove($r3);
        }
        return $i0 != map.size();
    }

    public static boolean retainAllHelper(Map map, Collection collection) {
        int $i0 = map.size();
        Iterator $r3 = map.keySet().iterator();
        while ($r3.hasNext()) {
            if (!collection.contains($r3.next())) {
                $r3.remove();
            }
        }
        return $i0 != map.size();
    }

    /* access modifiers changed from: protected */
    public abstract void colClear();

    /* access modifiers changed from: protected */
    public abstract Object colGetEntry(int i, int i2);

    /* access modifiers changed from: protected */
    public abstract Map colGetMap();

    /* access modifiers changed from: protected */
    public abstract int colGetSize();

    /* access modifiers changed from: protected */
    public abstract int colIndexOfKey(Object obj);

    /* access modifiers changed from: protected */
    public abstract int colIndexOfValue(Object obj);

    /* access modifiers changed from: protected */
    public abstract void colPut(Object obj, Object obj2);

    /* access modifiers changed from: protected */
    public abstract void colRemoveAt(int i);

    /* access modifiers changed from: protected */
    public abstract Object colSetValue(int i, Object obj);

    public Set getEntrySet() {
        if (this.mEntrySet == null) {
            this.mEntrySet = new EntrySet();
        }
        return this.mEntrySet;
    }

    public Set getKeySet() {
        if (this.mKeySet == null) {
            this.mKeySet = new KeySet();
        }
        return this.mKeySet;
    }

    public Collection getValues() {
        if (this.mValues == null) {
            this.mValues = new ValuesCollection();
        }
        return this.mValues;
    }

    public Object[] toArrayHelper(int i) {
        int $i1 = colGetSize();
        Object[] $r1 = new Object[$i1];
        for (int $i2 = 0; $i2 < $i1; $i2++) {
            $r1[$i2] = colGetEntry($i2, i);
        }
        return $r1;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: java.lang.Object[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object[] toArrayHelper(java.lang.Object[] r7, int r8) {
        /*
            r6 = this;
            int r0 = r6.colGetSize()
            int r1 = r7.length
            if (r1 >= r0) goto L_0x0017
            java.lang.Class r2 = r7.getClass()
            java.lang.Class r2 = r2.getComponentType()
            java.lang.Object r3 = java.lang.reflect.Array.newInstance(r2, r0)
            r4 = r3
            java.lang.Object[] r4 = (java.lang.Object[]) r4
            r7 = r4
        L_0x0017:
            r1 = 0
        L_0x0018:
            if (r1 >= r0) goto L_0x0023
            java.lang.Object r3 = r6.colGetEntry(r1, r8)
            r7[r1] = r3
            int r1 = r1 + 1
            goto L_0x0018
        L_0x0023:
            int r8 = r7.length
            if (r8 <= r0) goto L_0x0029
            r5 = 0
            r7[r0] = r5
        L_0x0029:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.org.android.util.MapCollections.toArrayHelper(java.lang.Object[], int):java.lang.Object[]");
    }
}
