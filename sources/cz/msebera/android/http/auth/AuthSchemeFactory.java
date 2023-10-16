package cz.msebera.android.http.auth;

import cz.msebera.android.http.util.HttpParams;

@Deprecated
public interface AuthSchemeFactory {
    AuthScheme newInstance(HttpParams httpParams);
}
