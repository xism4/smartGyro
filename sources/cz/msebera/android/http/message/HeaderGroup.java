package cz.msebera.android.http.message;

import c.a.a.a.e;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HeaderIterator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HeaderGroup implements Cloneable, Serializable {
    private final Header[] EMPTY = new Header[0];
    private final List<e> headers = new ArrayList(16);

    public void addHeader(Header header) {
        if (header != null) {
            this.headers.add(header);
        }
    }

    public void clear() {
        this.headers.clear();
    }

    public Object clone() {
        return super.clone();
    }

    public boolean containsHeader(String str) {
        for (int $i0 = 0; $i0 < this.headers.size(); $i0++) {
            if (this.headers.get($i0).getName().equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    public Header[] getAllHeaders() {
        List $r1 = this.headers;
        return (Header[]) $r1.toArray(new Header[$r1.size()]);
    }

    public Header getFirstHeader(String str) {
        for (int $i0 = 0; $i0 < this.headers.size(); $i0++) {
            Header $r4 = this.headers.get($i0);
            if ($r4.getName().equalsIgnoreCase(str)) {
                return $r4;
            }
        }
        return null;
    }

    public Header[] getHeaders(String str) {
        ArrayList $r2 = null;
        for (int $i0 = 0; $i0 < this.headers.size(); $i0++) {
            Header $r5 = this.headers.get($i0);
            if ($r5.getName().equalsIgnoreCase(str)) {
                if ($r2 == null) {
                    $r2 = new ArrayList();
                }
                $r2.add($r5);
            }
        }
        return $r2 != null ? (Header[]) $r2.toArray(new Header[$r2.size()]) : this.EMPTY;
    }

    public HeaderIterator iterator() {
        return new BasicListHeaderIterator(this.headers, (String) null);
    }

    public HeaderIterator iterator(String str) {
        return new BasicListHeaderIterator(this.headers, str);
    }

    public void removeHeader(Header header) {
        if (header != null) {
            this.headers.remove(header);
        }
    }

    public void setHeaders(Header[] headerArr) {
        clear();
        if (headerArr != null) {
            Collections.addAll(this.headers, headerArr);
        }
    }

    public String toString() {
        return this.headers.toString();
    }

    public void updateHeader(Header header) {
        if (header != null) {
            for (int $i0 = 0; $i0 < this.headers.size(); $i0++) {
                if (this.headers.get($i0).getName().equalsIgnoreCase(header.getName())) {
                    this.headers.set($i0, header);
                    return;
                }
            }
            this.headers.add(header);
        }
    }
}
