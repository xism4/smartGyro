package cz.msebera.android.http;

import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.LangUtils;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.Locale;

public final class HttpHost implements Cloneable, Serializable {
    protected final InetAddress address;
    protected final String hostname;
    protected final String lcHostname;
    protected final int port;
    protected final String schemeName;

    public HttpHost(String str, int i) {
        this(str, i, (String) null);
    }

    public HttpHost(String $r3, int i, String str) {
        Args.containsNoBlanks($r3, "Host name");
        this.hostname = $r3;
        this.lcHostname = $r3.toLowerCase(Locale.ROOT);
        this.schemeName = str != null ? str.toLowerCase(Locale.ROOT) : "http";
        this.port = i;
        this.address = null;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public HttpHost(InetAddress $r4, int i, String str) {
        this($r4, $r4.getHostName(), i, str);
        Args.notNull($r4, "Inet address");
    }

    public HttpHost(InetAddress $r2, String $r3, int i, String str) {
        Args.notNull($r2, "Inet address");
        this.address = $r2;
        Args.notNull($r3, "Hostname");
        this.hostname = $r3;
        this.lcHostname = this.hostname.toLowerCase(Locale.ROOT);
        this.schemeName = str != null ? str.toLowerCase(Locale.ROOT) : "http";
        this.port = i;
    }

    public Object clone() {
        return super.clone();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HttpHost)) {
            return false;
        }
        HttpHost $r2 = (HttpHost) obj;
        if (!this.lcHostname.equals($r2.lcHostname) || this.port != $r2.port || !this.schemeName.equals($r2.schemeName)) {
            return false;
        }
        InetAddress $r5 = this.address;
        return $r5 == null ? $r2.address == null : $r5.equals($r2.address);
    }

    public InetAddress getAddress() {
        return this.address;
    }

    public String getHostName() {
        return this.hostname;
    }

    public int getPort() {
        return this.port;
    }

    public String getSchemeName() {
        return this.schemeName;
    }

    public int hashCode() {
        int $i0 = LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, (Object) this.lcHostname), this.port), (Object) this.schemeName);
        InetAddress $r2 = this.address;
        return $r2 != null ? LangUtils.hashCode($i0, (Object) $r2) : $i0;
    }

    public String toHostString() {
        if (this.port == -1) {
            return this.hostname;
        }
        StringBuilder $r1 = new StringBuilder(this.hostname.length() + 6);
        $r1.append(this.hostname);
        $r1.append(":");
        $r1.append(Integer.toString(this.port));
        return $r1.toString();
    }

    public String toString() {
        return toURI();
    }

    public String toURI() {
        StringBuilder $r1 = new StringBuilder();
        $r1.append(this.schemeName);
        $r1.append("://");
        $r1.append(this.hostname);
        if (this.port != -1) {
            $r1.append(':');
            $r1.append(Integer.toString(this.port));
        }
        return $r1.toString();
    }
}
