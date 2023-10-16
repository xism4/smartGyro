package cz.msebera.android.http.client;

import cz.msebera.android.http.execchain.HttpContext;

public interface UserTokenHandler {
    Object getUserToken(HttpContext httpContext);
}
