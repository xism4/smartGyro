package cz.msebera.android.http.auth;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.execchain.HttpContext;

public interface ContextAwareAuthScheme extends AuthScheme {
    Header authenticate(Credentials credentials, HttpRequest httpRequest, HttpContext httpContext);
}
