package lombok.eclipse.handlers.http;

import android.os.SystemClock;
import c.a.a.a.A;
import cz.msebera.android.http.client.HttpRequestRetryHandler;
import cz.msebera.android.http.client.auth.HttpUriRequest;
import cz.msebera.android.http.execchain.HttpContext;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Iterator;
import javax.net.ssl.SSLException;

class RetryHandler implements HttpRequestRetryHandler {
    private static final HashSet<Class<?>> exceptionBlacklist = new HashSet();
    private static final HashSet<Class<?>> exceptionWhitelist = new HashSet();
    private final int maxRetries;
    private final int retrySleepTimeMS;

    static {
        exceptionWhitelist.add(A.class);
        exceptionWhitelist.add(UnknownHostException.class);
        exceptionWhitelist.add(SocketException.class);
        exceptionBlacklist.add(InterruptedIOException.class);
        exceptionBlacklist.add(SSLException.class);
    }

    public RetryHandler(int i, int i2) {
        this.maxRetries = i;
        this.retrySleepTimeMS = i2;
    }

    static void addClassToWhitelist(Class cls) {
        exceptionWhitelist.add(cls);
    }

    /* access modifiers changed from: protected */
    public boolean isInList(HashSet hashSet, Throwable th) {
        Iterator $r3 = hashSet.iterator();
        while ($r3.hasNext()) {
            if (((Class) $r3.next()).isInstance(th)) {
                return true;
            }
        }
        return false;
    }

    public boolean retryRequest(IOException iOException, int i, HttpContext httpContext) {
        Boolean $r4 = (Boolean) httpContext.getAttribute("http.request_sent");
        boolean $z0 = true;
        if ($r4 == null || $r4.booleanValue()) {
        }
        if (i > this.maxRetries || (!isInList(exceptionWhitelist, iOException) && isInList(exceptionBlacklist, iOException))) {
            $z0 = false;
        }
        if ($z0 && ((HttpUriRequest) httpContext.getAttribute("http.request")) == null) {
            return false;
        }
        if ($z0) {
            int $i1 = this.retrySleepTimeMS;
            int i2 = $i1;
            SystemClock.sleep((long) $i1);
            return $z0;
        }
        iOException.printStackTrace();
        return $z0;
    }
}
