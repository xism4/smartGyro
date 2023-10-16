package cz.msebera.android.http.message;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HeaderIterator;
import cz.msebera.android.http.mime.Args;
import java.util.NoSuchElementException;

public class BasicHeaderIterator implements HeaderIterator {
    protected final Header[] allHeaders;
    protected int currentIndex = findNext(-1);
    protected String headerName;

    public BasicHeaderIterator(Header[] $r2, String str) {
        Args.notNull($r2, "Header array");
        this.allHeaders = $r2;
        this.headerName = str;
    }

    /* access modifiers changed from: protected */
    public boolean filterHeader(int i) {
        String $r1 = this.headerName;
        return $r1 == null || $r1.equalsIgnoreCase(this.allHeaders[i].getName());
    }

    /* access modifiers changed from: protected */
    public int findNext(int $i0) {
        if ($i0 < -1) {
            return -1;
        }
        int $i1 = this.allHeaders.length - 1;
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
        int $i1 = this.currentIndex;
        if ($i1 >= 0) {
            this.currentIndex = findNext($i1);
            return this.allHeaders[$i1];
        }
        throw new NoSuchElementException("Iteration already finished.");
    }

    public void remove() {
        throw new UnsupportedOperationException("Removing headers is not supported.");
    }
}
