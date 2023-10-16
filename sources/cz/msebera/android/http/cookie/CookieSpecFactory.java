package cz.msebera.android.http.cookie;

import cz.msebera.android.http.util.HttpParams;

@Deprecated
public interface CookieSpecFactory {
    CookieSpec newInstance(HttpParams httpParams);
}
