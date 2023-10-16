package cz.msebera.android.http.client.ssl;

import c.a.a.a.z;
import cz.msebera.android.http.Consts;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class URIBuilder {
    private Charset charset;
    private String encodedAuthority;
    private String encodedFragment;
    private String encodedPath;
    private String encodedQuery;
    private String encodedSchemeSpecificPart;
    private String encodedUserInfo;
    private String fragment;
    private String host;
    private String path;
    private int port;
    private String query;
    private List<z> queryParams;
    private String scheme;
    private String userInfo;

    public URIBuilder(URI uri) {
        digestURI(uri);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00c0  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00cb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String buildString() {
        /*
            r7 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r7.scheme
            if (r1 == 0) goto L_0x0011
            r0.append(r1)
            r2 = 58
            r0.append(r2)
        L_0x0011:
            java.lang.String r1 = r7.encodedSchemeSpecificPart
            if (r1 == 0) goto L_0x001b
        L_0x0015:
            r0.append(r1)
            goto L_0x00bc
        L_0x001b:
            java.lang.String r1 = r7.encodedAuthority
            if (r1 == 0) goto L_0x002a
            java.lang.String r3 = "//"
            r0.append(r3)
            java.lang.String r1 = r7.encodedAuthority
            r0.append(r1)
            goto L_0x0071
        L_0x002a:
            java.lang.String r1 = r7.host
            if (r1 == 0) goto L_0x0071
            java.lang.String r3 = "//"
            r0.append(r3)
            java.lang.String r1 = r7.encodedUserInfo
            if (r1 == 0) goto L_0x0040
        L_0x0037:
            r0.append(r1)
            java.lang.String r3 = "@"
            r0.append(r3)
            goto L_0x0049
        L_0x0040:
            java.lang.String r1 = r7.userInfo
            if (r1 == 0) goto L_0x0049
            java.lang.String r1 = r7.encodePath(r1)
            goto L_0x0037
        L_0x0049:
            java.lang.String r1 = r7.host
            boolean r4 = cz.msebera.android.http.conn.socket.InetAddressUtils.isIPv6Address(r1)
            if (r4 == 0) goto L_0x005e
            java.lang.String r3 = "["
            r0.append(r3)
            java.lang.String r1 = r7.host
            r0.append(r1)
            java.lang.String r1 = "]"
            goto L_0x0060
        L_0x005e:
            java.lang.String r1 = r7.host
        L_0x0060:
            r0.append(r1)
            int r5 = r7.port
            if (r5 < 0) goto L_0x0071
            java.lang.String r3 = ":"
            r0.append(r3)
            int r5 = r7.port
            r0.append(r5)
        L_0x0071:
            java.lang.String r1 = r7.encodedPath
            if (r1 == 0) goto L_0x007d
            java.lang.String r1 = normalizePath(r1)
        L_0x0079:
            r0.append(r1)
            goto L_0x008a
        L_0x007d:
            java.lang.String r1 = r7.path
            if (r1 == 0) goto L_0x008a
            java.lang.String r1 = normalizePath(r1)
            java.lang.String r1 = r7.encodeUserInfo(r1)
            goto L_0x0079
        L_0x008a:
            java.lang.String r1 = r7.encodedQuery
            if (r1 == 0) goto L_0x0098
            java.lang.String r3 = "?"
            r0.append(r3)
            java.lang.String r1 = r7.encodedQuery
            goto L_0x0015
        L_0x0098:
            java.util.List<c.a.a.a.z> r6 = r7.queryParams
            if (r6 == 0) goto L_0x00aa
            java.lang.String r3 = "?"
            r0.append(r3)
            java.util.List<c.a.a.a.z> r6 = r7.queryParams
            java.lang.String r1 = r7.encodeUrlForm(r6)
            goto L_0x0015
        L_0x00aa:
            java.lang.String r1 = r7.query
            if (r1 == 0) goto L_0x00bc
            java.lang.String r3 = "?"
            r0.append(r3)
            java.lang.String r1 = r7.query
            java.lang.String r1 = r7.encodeUric(r1)
            goto L_0x0015
        L_0x00bc:
            java.lang.String r1 = r7.encodedFragment
            if (r1 == 0) goto L_0x00cb
            java.lang.String r3 = "#"
            r0.append(r3)
            java.lang.String r1 = r7.encodedFragment
        L_0x00c7:
            r0.append(r1)
            goto L_0x00db
        L_0x00cb:
            java.lang.String r1 = r7.fragment
            if (r1 == 0) goto L_0x00db
            java.lang.String r3 = "#"
            r0.append(r3)
            java.lang.String r1 = r7.fragment
            java.lang.String r1 = r7.encodeUric(r1)
            goto L_0x00c7
        L_0x00db:
            java.lang.String r1 = r0.toString()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.http.client.ssl.URIBuilder.buildString():java.lang.String");
    }

    private void digestURI(URI uri) {
        this.scheme = uri.getScheme();
        this.encodedSchemeSpecificPart = uri.getRawSchemeSpecificPart();
        this.encodedAuthority = uri.getRawAuthority();
        this.host = uri.getHost();
        this.port = uri.getPort();
        this.encodedUserInfo = uri.getRawUserInfo();
        this.userInfo = uri.getUserInfo();
        this.encodedPath = uri.getRawPath();
        this.path = uri.getPath();
        this.encodedQuery = uri.getRawQuery();
        String $r2 = uri.getRawQuery();
        Charset $r3 = this.charset;
        if ($r3 == null) {
            $r3 = Consts.UTF_8;
        }
        this.queryParams = parseQuery($r2, $r3);
        this.encodedFragment = uri.getRawFragment();
        this.fragment = uri.getFragment();
    }

    private String encodePath(String str) {
        Charset $r2 = this.charset;
        if ($r2 == null) {
            $r2 = Consts.UTF_8;
        }
        return URLEncodedUtils.encPath(str, $r2);
    }

    private String encodeUric(String str) {
        Charset $r2 = this.charset;
        if ($r2 == null) {
            $r2 = Consts.UTF_8;
        }
        return URLEncodedUtils.encUric(str, $r2);
    }

    private String encodeUrlForm(List list) {
        Charset $r3 = this.charset;
        if ($r3 == null) {
            $r3 = Consts.UTF_8;
        }
        return URLEncodedUtils.format(list, $r3);
    }

    private String encodeUserInfo(String str) {
        Charset $r2 = this.charset;
        if ($r2 == null) {
            $r2 = Consts.UTF_8;
        }
        return URLEncodedUtils.encUserInfo(str, $r2);
    }

    private static String normalizePath(String $r0) {
        if ($r0 == null) {
            return null;
        }
        int $i0 = 0;
        while ($i0 < $r0.length() && $r0.charAt($i0) == '/') {
            $i0++;
        }
        return $i0 > 1 ? $r0.substring($i0 - 1) : $r0;
    }

    private List parseQuery(String str, Charset charset2) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        return URLEncodedUtils.parse(str, charset2);
    }

    public URIBuilder addParameters(List list) {
        if (this.queryParams == null) {
            this.queryParams = new ArrayList();
        }
        this.queryParams.addAll(list);
        this.encodedQuery = null;
        this.encodedSchemeSpecificPart = null;
        this.query = null;
        return this;
    }

    public URI build() {
        return new URI(buildString());
    }

    public URIBuilder clearParameters() {
        this.queryParams = null;
        this.encodedQuery = null;
        this.encodedSchemeSpecificPart = null;
        return this;
    }

    public String getHost() {
        return this.host;
    }

    public String getPath() {
        return this.path;
    }

    public List getQueryParams() {
        List $r2 = this.queryParams;
        return $r2 != null ? new ArrayList($r2) : new ArrayList();
    }

    public String getUserInfo() {
        return this.userInfo;
    }

    public URIBuilder setCharset(Charset charset2) {
        this.charset = charset2;
        return this;
    }

    public URIBuilder setFragment(String str) {
        this.fragment = str;
        this.encodedFragment = null;
        return this;
    }

    public URIBuilder setHost(String str) {
        this.host = str;
        this.encodedSchemeSpecificPart = null;
        this.encodedAuthority = null;
        return this;
    }

    public URIBuilder setPath(String str) {
        this.path = str;
        this.encodedSchemeSpecificPart = null;
        this.encodedPath = null;
        return this;
    }

    public URIBuilder setPort(int $i0) {
        if ($i0 < 0) {
            $i0 = -1;
        }
        this.port = $i0;
        this.encodedSchemeSpecificPart = null;
        this.encodedAuthority = null;
        return this;
    }

    public URIBuilder setScheme(String str) {
        this.scheme = str;
        return this;
    }

    public URIBuilder setUserInfo(String str) {
        this.userInfo = str;
        this.encodedSchemeSpecificPart = null;
        this.encodedAuthority = null;
        this.encodedUserInfo = null;
        return this;
    }

    public String toString() {
        return buildString();
    }
}
