package cz.msebera.android.http.execchain;

import cz.msebera.android.http.HttpConnection;
import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.HttpInetConnection;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpRequestInterceptor;
import cz.msebera.android.http.HttpVersion;
import cz.msebera.android.http.ProtocolException;
import cz.msebera.android.http.ProtocolVersion;
import cz.msebera.android.http.mime.Args;
import java.net.InetAddress;

public class RequestDefaultHeaders implements HttpRequestInterceptor {
    public void process(HttpRequest httpRequest, HttpContext httpContext) {
        Args.notNull(httpRequest, "HTTP request");
        HttpCoreContext $r3 = HttpCoreContext.adapt(httpContext);
        ProtocolVersion $r5 = httpRequest.getRequestLine().getProtocolVersion();
        if ((!httpRequest.getRequestLine().getMethod().equalsIgnoreCase("CONNECT") || !$r5.lessEquals(HttpVersion.HTTP_1_0)) && !httpRequest.containsHeader("Host")) {
            HttpHost $r8 = $r3.getTargetHost();
            HttpHost $r9 = $r8;
            if ($r8 == null) {
                HttpConnection $r10 = $r3.getConnection();
                if ($r10 instanceof HttpInetConnection) {
                    HttpInetConnection $r11 = (HttpInetConnection) $r10;
                    InetAddress $r12 = $r11.getRemoteAddress();
                    int $i0 = $r11.getRemotePort();
                    if ($r12 != null) {
                        $r9 = new HttpHost($r12.getHostName(), $i0);
                    }
                }
                if ($r9 == null) {
                    if (!$r5.lessEquals(HttpVersion.HTTP_1_0)) {
                        throw new ProtocolException("Target host missing");
                    }
                    return;
                }
            }
            httpRequest.addHeader("Host", $r9.toHostString());
        }
    }
}
