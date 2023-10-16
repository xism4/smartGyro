package cz.msebera.android.http.cookie;

import cz.msebera.android.http.execchain.HttpContext;

public interface CookieSpecProvider {
    CookieSpec create(HttpContext httpContext);
}
