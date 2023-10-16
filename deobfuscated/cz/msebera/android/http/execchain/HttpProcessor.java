package cz.msebera.android.http.execchain;

import cz.msebera.android.http.HttpRequestInterceptor;
import cz.msebera.android.http.HttpResponseInterceptor;

public abstract interface HttpProcessor
  extends HttpRequestInterceptor, HttpResponseInterceptor
{}
