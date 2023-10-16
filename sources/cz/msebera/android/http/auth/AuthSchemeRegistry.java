package cz.msebera.android.http.auth;

import c.a.a.a.a.d;
import c.a.a.a.a.e;
import c.a.a.a.d.a;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.util.HttpParams;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

@Deprecated
public final class AuthSchemeRegistry implements a<e> {
    private final ConcurrentHashMap<String, d> registeredSchemes = new ConcurrentHashMap();

    public AuthScheme getAuthScheme(String str, HttpParams httpParams) {
        Args.notNull(str, "Name");
        AuthSchemeFactory $r7 = this.registeredSchemes.get(str.toLowerCase(Locale.ENGLISH));
        if ($r7 != null) {
            return $r7.newInstance(httpParams);
        }
        throw new IllegalStateException("Unsupported authentication scheme: " + str);
    }

    public AuthSchemeProvider lookup(final String str) {
        return new AuthSchemeProvider() {
            public AuthScheme create(HttpContext httpContext) {
                return AuthSchemeRegistry.this.getAuthScheme(str, ((HttpRequest) httpContext.getAttribute("http.request")).getParams());
            }
        };
    }

    public void register(String str, AuthSchemeFactory authSchemeFactory) {
        Args.notNull(str, "Name");
        Args.notNull(authSchemeFactory, "Authentication scheme factory");
        this.registeredSchemes.put(str.toLowerCase(Locale.ENGLISH), authSchemeFactory);
    }
}
