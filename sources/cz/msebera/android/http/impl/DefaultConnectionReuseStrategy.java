package cz.msebera.android.http.impl;

import cz.msebera.android.http.ConnectionReuseStrategy;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.HttpVersion;
import cz.msebera.android.http.ParseException;
import cz.msebera.android.http.ProtocolVersion;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.message.BasicHeaderIterator;
import cz.msebera.android.http.message.BasicTokenIterator;
import cz.msebera.android.http.mime.Args;

public class DefaultConnectionReuseStrategy implements ConnectionReuseStrategy {
    public static final DefaultConnectionReuseStrategy INSTANCE = new DefaultConnectionReuseStrategy();

    private boolean canResponseHaveBody(HttpResponse httpResponse) {
        int $i0 = httpResponse.getStatusLine().getStatusCode();
        return ($i0 < 200 || $i0 == 204 || $i0 == 304 || $i0 == 205) ? false : true;
    }

    public boolean keepAlive(HttpResponse httpResponse, HttpContext httpContext) {
        Args.notNull(httpResponse, "HTTP response");
        Args.notNull(httpContext, "HTTP context");
        ProtocolVersion $r6 = httpResponse.getStatusLine().getProtocolVersion();
        Header $r7 = httpResponse.getFirstHeader("Transfer-Encoding");
        if ($r7 != null) {
            if (!"chunked".equalsIgnoreCase($r7.getValue())) {
                return false;
            }
        } else if (canResponseHaveBody(httpResponse)) {
            Header[] $r10 = httpResponse.getHeaders("Content-Length");
            if ($r10.length != 1) {
                return false;
            }
            try {
                if (Integer.parseInt($r10[0].getValue()) < 0) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        Header[] $r102 = httpResponse.getHeaders("Connection");
        Header[] $r11 = $r102;
        if ($r102.length == 0) {
            $r11 = httpResponse.getHeaders("Proxy-Connection");
        }
        if ($r11.length != 0) {
            try {
                BasicTokenIterator $r12 = new BasicTokenIterator(new BasicHeaderIterator($r11, (String) null));
                boolean $z0 = false;
                while ($r12.hasNext()) {
                    String $r8 = $r12.nextToken();
                    if ("Close".equalsIgnoreCase($r8)) {
                        return false;
                    }
                    if ("Keep-Alive".equalsIgnoreCase($r8)) {
                        $z0 = true;
                    }
                }
                if ($z0) {
                    return true;
                }
            } catch (ParseException e2) {
                return false;
            }
        }
        return !$r6.lessEquals(HttpVersion.HTTP_1_0);
    }
}
