package cz.msebera.android.http.client.auth;

import cz.msebera.android.http.HttpResponse;
import java.io.Closeable;

public abstract interface CloseableHttpResponse
  extends HttpResponse, Closeable
{}
