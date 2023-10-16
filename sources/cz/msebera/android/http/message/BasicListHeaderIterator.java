package cz.msebera.android.http.message;

import c.a.a.a.e;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HeaderIterator;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.Asserts;
import java.util.List;
import java.util.NoSuchElementException;

public class BasicListHeaderIterator implements HeaderIterator {
    protected final List<e> allHeaders;
    protected int currentIndex = findNext(-1);
    protected String headerName;
    protected int lastIndex = -1;

    public BasicListHeaderIterator(List $r1, String str) {
        Args.notNull($r1, "Header list");
        this.allHeaders = $r1;
        this.headerName = str;
    }

    /* access modifiers changed from: protected */
    public boolean filterHeader(int i) {
        if (this.headerName == null) {
            return true;
        }
        return this.headerName.equalsIgnoreCase(this.allHeaders.get(i).getName());
    }

    /* access modifiers changed from: protected */
    public int findNext(int $i0) {
        if ($i0 < -1) {
            return -1;
        }
        int $i1 = this.allHeaders.size() - 1;
        boolean $z0 = false;
        while (!$z0 && $i0 < $i1) {
            $i0++;
            $z0 = filterHeader($i0);
        }
        if ($z0) {
            return $i0;
        }
        return -1;
    }

    public boolean hasNext() {
        return this.currentIndex >= 0;
    }

    public final Object next() {
        return nextHeader();
    }

    public Header nextHeader() {
        int $i0 = this.currentIndex;
        if ($i0 >= 0) {
            this.lastIndex = $i0;
            this.currentIndex = findNext($i0);
            return this.allHeaders.get($i0);
        }
        throw new NoSuchElementException("Iteration already finished.");
    }

    public void remove() {
        Asserts.check(this.lastIndex >= 0, "No header to remove");
        this.allHeaders.remove(this.lastIndex);
        this.lastIndex = -1;
        this.currentIndex--;
    }
}
