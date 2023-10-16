package cz.msebera.android.http.execchain;

import cz.msebera.android.http.HttpRequestInterceptor;
import cz.msebera.android.http.HttpResponseInterceptor;

public interface HttpProcessor extends HttpRequestInterceptor, HttpResponseInterceptor {
}
