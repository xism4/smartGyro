package cz.msebera.android.http.cookie;

import c.a.a.a.d.a;
import c.a.a.a.f.j;
import c.a.a.a.f.k;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.util.HttpParams;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

@Deprecated
public final class CookieSpecRegistry implements a<k> {
    private final ConcurrentHashMap<String, j> registeredSpecs = new ConcurrentHashMap();

    public CookieSpec getCookieSpec(String str, HttpParams httpParams) {
        Args.notNull(str, "Name");
        CookieSpecFactory $r7 = this.registeredSpecs.get(str.toLowerCase(Locale.ENGLISH));
        if ($r7 != null) {
            return $r7.newInstance(httpParams);
        }
        throw new IllegalStateException("Unsupported cookie spec: " + str);
    }

    public CookieSpecProvider lookup(final String str) {
        return new CookieSpecProvider() {
            public CookieSpec create(HttpContext httpContext) {
                return CookieSpecRegistry.this.getCookieSpec(str, ((HttpRequest) httpContext.getAttribute("http.request")).getParams());
            }
        };
    }

    public void register(String str, CookieSpecFactory cookieSpecFactory) {
        Args.notNull(str, "Name");
        Args.notNull(cookieSpecFactory, "Cookie spec factory");
        this.registeredSpecs.put(str.toLowerCase(Locale.ENGLISH), cookieSpecFactory);
    }
}
