package cz.msebera.android.http.impl.client;

import cz.msebera.android.http.HeaderElement;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.conn.ConnectionKeepAliveStrategy;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.message.BasicHeaderElementIterator;
import cz.msebera.android.http.mime.Args;

public class DefaultConnectionKeepAliveStrategy implements ConnectionKeepAliveStrategy {
    public static final DefaultConnectionKeepAliveStrategy INSTANCE = new DefaultConnectionKeepAliveStrategy();

    public long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext) {
        Args.notNull(httpResponse, "HTTP response");
        BasicHeaderElementIterator $r4 = new BasicHeaderElementIterator(httpResponse.headerIterator("Keep-Alive"));
        while ($r4.hasNext()) {
            HeaderElement $r6 = $r4.nextElement();
            String $r7 = $r6.getName();
            String $r8 = $r6.getValue();
            if ($r8 != null && $r7.equalsIgnoreCase("timeout")) {
                try {
                    return Long.parseLong($r8) * 1000;
                } catch (NumberFormatException e) {
                }
            }
        }
        return -1;
    }
}
