package cz.msebera.android.http.impl.auth;

import cz.msebera.android.http.auth.AuthScheme;
import cz.msebera.android.http.auth.AuthSchemeFactory;
import cz.msebera.android.http.auth.AuthSchemeProvider;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.util.HttpParams;
import java.nio.charset.Charset;

public class BasicSchemeFactory implements AuthSchemeFactory, AuthSchemeProvider {
    private final Charset charset;

    public BasicSchemeFactory() {
        this((Charset) null);
    }

    public BasicSchemeFactory(Charset charset2) {
        this.charset = charset2;
    }

    public AuthScheme create(HttpContext httpContext) {
        return new DigestScheme(this.charset);
    }

    public AuthScheme newInstance(HttpParams httpParams) {
        return new DigestScheme();
    }
}
