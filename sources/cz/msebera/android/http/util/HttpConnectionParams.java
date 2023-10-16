package cz.msebera.android.http.util;

import cz.msebera.android.http.mime.Args;

@Deprecated
public final class HttpConnectionParams implements CoreConnectionPNames {
    public static int getConnectionTimeout(HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        return httpParams.getIntParameter("http.connection.timeout", 0);
    }

    public static int getLinger(HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        return httpParams.getIntParameter("http.socket.linger", -1);
    }

    public static boolean getSoReuseaddr(HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        return httpParams.getBooleanParameter("http.socket.reuseaddr", false);
    }

    public static int getSoTimeout(HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        return httpParams.getIntParameter("http.socket.timeout", 0);
    }

    public static boolean getTcpNoDelay(HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        return httpParams.getBooleanParameter("http.tcp.nodelay", true);
    }

    public static boolean isStaleCheckingEnabled(HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        return httpParams.getBooleanParameter("http.connection.stalecheck", true);
    }

    public static void setConnectionTimeout(HttpParams httpParams, int i) {
        Args.notNull(httpParams, "HTTP parameters");
        httpParams.setIntParameter("http.connection.timeout", i);
    }

    public static void setSoTimeout(HttpParams httpParams, int i) {
        Args.notNull(httpParams, "HTTP parameters");
        httpParams.setIntParameter("http.socket.timeout", i);
    }

    public static void setSocketBufferSize(HttpParams httpParams, int i) {
        Args.notNull(httpParams, "HTTP parameters");
        httpParams.setIntParameter("http.socket.buffer-size", i);
    }

    public static void setTcpNoDelay(HttpParams httpParams, boolean z) {
        Args.notNull(httpParams, "HTTP parameters");
        httpParams.setBooleanParameter("http.tcp.nodelay", z);
    }
}
