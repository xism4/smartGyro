package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.cookie.CookieSpec;
import cz.msebera.android.http.cookie.CookieSpecFactory;
import cz.msebera.android.http.cookie.CookieSpecProvider;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.util.HttpParams;

@Deprecated
public class IgnoreSpecFactory implements CookieSpecFactory, CookieSpecProvider {
    public CookieSpec create(HttpContext httpContext) {
        return new IgnoreSpec();
    }

    public CookieSpec newInstance(HttpParams httpParams) {
        return new IgnoreSpec();
    }
}
