package cz.msebera.android.http.impl.auth;

import cz.msebera.android.http.auth.AuthScheme;
import cz.msebera.android.http.auth.AuthSchemeFactory;
import cz.msebera.android.http.auth.AuthSchemeProvider;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.util.HttpParams;
import java.nio.charset.Charset;

public class DigestSchemeFactory implements AuthSchemeFactory, AuthSchemeProvider {
    private final Charset charset;

    public DigestSchemeFactory() {
        this((Charset) null);
    }

    public DigestSchemeFactory(Charset charset2) {
        this.charset = charset2;
    }

    public AuthScheme create(HttpContext httpContext) {
        return new BasicScheme(this.charset);
    }

    public AuthScheme newInstance(HttpParams httpParams) {
        return new BasicScheme();
    }
}
