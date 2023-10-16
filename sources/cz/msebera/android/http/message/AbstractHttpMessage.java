package cz.msebera.android.http.message;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HeaderIterator;
import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.util.BasicHttpParams;
import cz.msebera.android.http.util.HttpParams;

public abstract class AbstractHttpMessage implements HttpMessage {
    protected HeaderGroup headergroup;
    @Deprecated
    protected HttpParams params;

    protected AbstractHttpMessage() {
        this((HttpParams) null);
    }

    protected AbstractHttpMessage(HttpParams httpParams) {
        this.headergroup = new HeaderGroup();
        this.params = httpParams;
    }

    public void addHeader(Header header) {
        this.headergroup.addHeader(header);
    }

    public void addHeader(String str, String str2) {
        Args.notNull(str, "Header name");
        this.headergroup.addHeader(new BasicHeader(str, str2));
    }

    public boolean containsHeader(String str) {
        return this.headergroup.containsHeader(str);
    }

    public Header[] getAllHeaders() {
        return this.headergroup.getAllHeaders();
    }

    public Header getFirstHeader(String str) {
        return this.headergroup.getFirstHeader(str);
    }

    public Header[] getHeaders(String str) {
        return this.headergroup.getHeaders(str);
    }

    public HttpParams getParams() {
        if (this.params == null) {
            this.params = new BasicHttpParams();
        }
        return this.params;
    }

    public HeaderIterator headerIterator() {
        return this.headergroup.iterator();
    }

    public HeaderIterator headerIterator(String str) {
        return this.headergroup.iterator(str);
    }

    public void removeHeader(Header header) {
        this.headergroup.removeHeader(header);
    }

    public void removeHeaders(String str) {
        if (str != null) {
            HeaderIterator $r3 = this.headergroup.iterator();
            while ($r3.hasNext()) {
                if (str.equalsIgnoreCase($r3.nextHeader().getName())) {
                    $r3.remove();
                }
            }
        }
    }

    public void setHeader(String str, String str2) {
        Args.notNull(str, "Header name");
        this.headergroup.updateHeader(new BasicHeader(str, str2));
    }

    public void setHeaders(Header[] headerArr) {
        this.headergroup.setHeaders(headerArr);
    }

    public void setParams(HttpParams $r1) {
        Args.notNull($r1, "HTTP parameters");
        this.params = $r1;
    }
}
