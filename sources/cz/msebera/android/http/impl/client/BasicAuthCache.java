package cz.msebera.android.http.impl.client;

import c.a.a.a.i.b.d;
import c.a.a.a.o;
import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.auth.AuthScheme;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.client.AuthCache;
import cz.msebera.android.http.conn.SchemePortResolver;
import cz.msebera.android.http.conn.UnsupportedSchemeException;
import cz.msebera.android.http.impl.conn.DefaultSchemePortResolver;
import cz.msebera.android.http.mime.Args;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BasicAuthCache implements AuthCache {
    public HttpClientAndroidLog log;
    private final Map<o, byte[]> map;
    private final SchemePortResolver schemePortResolver;

    public BasicAuthCache() {
        this((SchemePortResolver) null);
    }

    public BasicAuthCache(SchemePortResolver $r1) {
        this.log = new HttpClientAndroidLog(d.class);
        this.map = new ConcurrentHashMap();
        this.schemePortResolver = $r1 == null ? DefaultSchemePortResolver.INSTANCE : $r1;
    }

    public AuthScheme get(HttpHost httpHost) {
        Args.notNull(httpHost, "HTTP host");
        byte[] $r4 = this.map.get(getKey(httpHost));
        if ($r4 == null) {
            return null;
        }
        try {
            ObjectInputStream $r6 = new ObjectInputStream(new ByteArrayInputStream($r4));
            AuthScheme $r7 = (AuthScheme) $r6.readObject();
            $r6.close();
            return $r7;
        } catch (IOException $r10) {
            if (!this.log.isWarnEnabled()) {
                return null;
            }
            this.log.warn("Unexpected I/O error while de-serializing auth scheme", $r10);
            return null;
        } catch (ClassNotFoundException $r8) {
            if (!this.log.isWarnEnabled()) {
                return null;
            }
            this.log.warn("Unexpected error while de-serializing auth scheme", $r8);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public HttpHost getKey(HttpHost httpHost) {
        if (httpHost.getPort() > 0) {
            return httpHost;
        }
        try {
            return new HttpHost(httpHost.getHostName(), this.schemePortResolver.resolve(httpHost), httpHost.getSchemeName());
        } catch (UnsupportedSchemeException e) {
            return httpHost;
        }
    }

    public void put(HttpHost httpHost, AuthScheme authScheme) {
        Args.notNull(httpHost, "HTTP host");
        if (authScheme != null) {
            if (authScheme instanceof Serializable) {
                try {
                    ByteArrayOutputStream $r3 = new ByteArrayOutputStream();
                    ObjectOutputStream $r4 = new ObjectOutputStream($r3);
                    $r4.writeObject(authScheme);
                    $r4.close();
                    this.map.put(getKey(httpHost), $r3.toByteArray());
                } catch (IOException $r7) {
                    if (this.log.isWarnEnabled()) {
                        this.log.warn("Unexpected I/O error while serializing auth scheme", $r7);
                    }
                }
            } else if (this.log.isDebugEnabled()) {
                HttpClientAndroidLog $r8 = this.log;
                $r8.debug("Auth scheme " + authScheme.getClass() + " is not serializable");
            }
        }
    }

    public void remove(HttpHost httpHost) {
        Args.notNull(httpHost, "HTTP host");
        this.map.remove(getKey(httpHost));
    }

    public String toString() {
        return this.map.toString();
    }
}
