package com.org.android.util;

import a.a.c.f.f;
import a.a.c.f.i;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class ArrayMap<K, V> extends i<K, V> implements Map<K, V> {
    f<K, V> mCollections;

    public ArrayMap() {
    }

    public ArrayMap(int i) {
        super(i);
    }

    private MapCollections getCollection() {
        if (this.mCollections == null) {
            this.mCollections = new f<K, V>() {
                /* JADX WARNING: type inference failed for: r0v0, types: [com.org.android.util.SimpleArrayMap, com.org.android.util.ArrayMap] */
                /* access modifiers changed from: protected */
                public void colClear() {
                    ArrayMap.this.clear();
                }

                /* JADX WARNING: type inference failed for: r0v0, types: [com.org.android.util.SimpleArrayMap, com.org.android.util.ArrayMap] */
                /* access modifiers changed from: protected */
                public Object colGetEntry(int i, int i2) {
                    return ArrayMap.this.b[(i << 1) + i2];
                }

                /* access modifiers changed from: protected */
                public Map colGetMap() {
                    return ArrayMap.this;
                }

                /* JADX WARNING: type inference failed for: r0v0, types: [com.org.android.util.SimpleArrayMap, com.org.android.util.ArrayMap] */
                /* access modifiers changed from: protected */
                public int colGetSize() {
                    return ArrayMap.this.n;
                }

                /* JADX WARNING: type inference failed for: r0v0, types: [com.org.android.util.SimpleArrayMap, com.org.android.util.ArrayMap] */
                /* access modifiers changed from: protected */
                public int colIndexOfKey(Object obj) {
                    return ArrayMap.this.indexOfKey(obj);
                }

                /* JADX WARNING: type inference failed for: r0v0, types: [com.org.android.util.SimpleArrayMap, com.org.android.util.ArrayMap] */
                /* access modifiers changed from: protected */
                public int colIndexOfValue(Object obj) {
                    return ArrayMap.this.indexOfValue(obj);
                }

                /* JADX WARNING: type inference failed for: r0v0, types: [com.org.android.util.SimpleArrayMap, com.org.android.util.ArrayMap] */
                /* access modifiers changed from: protected */
                public void colPut(Object obj, Object obj2) {
                    ArrayMap.this.put(obj, obj2);
                }

                /* JADX WARNING: type inference failed for: r0v0, types: [com.org.android.util.SimpleArrayMap, com.org.android.util.ArrayMap] */
                /* access modifiers changed from: protected */
                public void colRemoveAt(int i) {
                    ArrayMap.this.a(i);
                }

                /* JADX WARNING: type inference failed for: r0v0, types: [com.org.android.util.SimpleArrayMap, com.org.android.util.ArrayMap] */
                /* access modifiers changed from: protected */
                public Object colSetValue(int i, Object obj) {
                    return ArrayMap.this.setValueAt(i, obj);
                }
            };
        }
        return this.mCollections;
    }

    public Set entrySet() {
        return getCollection().getEntrySet();
    }

    public Set keySet() {
        return getCollection().getKeySet();
    }

    /* JADX WARNING: type inference failed for: r9v0, types: [com.org.android.util.SimpleArrayMap, com.org.android.util.ArrayMap] */
    public void putAll(Map map) {
        clear(this.n + map.size());
        for (Map.Entry $r5 : map.entrySet()) {
            put($r5.getKey(), $r5.getValue());
        }
    }

    public Collection values() {
        return getCollection().getValues();
    }
}
