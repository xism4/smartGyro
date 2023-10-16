package cz.msebera.android.http.conn;

import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.util.HttpParams;
import java.net.InetAddress;

@Deprecated
public interface ClientConnectionOperator {
    OperatedClientConnection createConnection();

    void openConnection(OperatedClientConnection operatedClientConnection, HttpHost httpHost, InetAddress inetAddress, HttpContext httpContext, HttpParams httpParams);

    void updateSecureConnection(OperatedClientConnection operatedClientConnection, HttpHost httpHost, HttpContext httpContext, HttpParams httpParams);
}
