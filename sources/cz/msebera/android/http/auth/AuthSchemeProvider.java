package cz.msebera.android.http.auth;

import cz.msebera.android.http.execchain.HttpContext;

public interface AuthSchemeProvider {
    AuthScheme create(HttpContext httpContext);
}
