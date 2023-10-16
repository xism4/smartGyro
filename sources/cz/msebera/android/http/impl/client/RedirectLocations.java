package cz.msebera.android.http.impl.client;

import java.net.URI;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RedirectLocations extends AbstractList<Object> {
    private final List<URI> all = new ArrayList();
    private final Set<URI> unique = new HashSet();

    public void add(int i, Object obj) {
        URI $r3 = (URI) obj;
        this.all.add(i, $r3);
        this.unique.add($r3);
    }

    public void add(URI uri) {
        this.unique.add(uri);
        this.all.add(uri);
    }

    public boolean contains(Object obj) {
        return this.unique.contains(obj);
    }

    public boolean contains(URI uri) {
        return this.unique.contains(uri);
    }

    public URI get(int i) {
        return this.all.get(i);
    }

    public URI remove(int i) {
        URI $r3 = this.all.remove(i);
        this.unique.remove($r3);
        if (this.all.size() != this.unique.size()) {
            this.unique.addAll(this.all);
        }
        return $r3;
    }

    public Object set(int i, Object obj) {
        URI $r3 = (URI) obj;
        URI $r4 = this.all.set(i, $r3);
        this.unique.remove($r4);
        this.unique.add($r3);
        if (this.all.size() != this.unique.size()) {
            this.unique.addAll(this.all);
        }
        return $r4;
    }

    public int size() {
        return this.all.size();
    }
}
