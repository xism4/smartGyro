package cz.msebera.android.http.util;

import cz.msebera.android.http.HttpVersion;
import cz.msebera.android.http.ProtocolVersion;
import cz.msebera.android.http.execchain.HTTP;
import cz.msebera.android.http.mime.Args;

@Deprecated
public final class HttpProtocolParams implements CoreProtocolPNames {
    public static String getHttpElementCharset(HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        String $r2 = (String) httpParams.getParameter("http.protocol.element-charset");
        return $r2 == null ? HTTP.DEF_PROTOCOL_CHARSET.name() : $r2;
    }

    public static ProtocolVersion getVersion(HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        Object $r0 = httpParams.getParameter("http.protocol.version");
        return $r0 == null ? HttpVersion.HTTP_1_1 : (ProtocolVersion) $r0;
    }

    public static void setContentCharset(HttpParams httpParams, String str) {
        Args.notNull(httpParams, "HTTP parameters");
        httpParams.setParameter("http.protocol.content-charset", str);
    }

    public static void setUserAgent(HttpParams httpParams, String str) {
        Args.notNull(httpParams, "HTTP parameters");
        httpParams.setParameter("http.useragent", str);
    }

    public static void setVersion(HttpParams httpParams, ProtocolVersion protocolVersion) {
        Args.notNull(httpParams, "HTTP parameters");
        httpParams.setParameter("http.protocol.version", protocolVersion);
    }
}
