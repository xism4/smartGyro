package cz.msebera.android.http.client;

import cz.msebera.android.http.HttpResponse;

public interface ConnectionBackoffStrategy {
    boolean shouldBackoff(HttpResponse httpResponse);

    boolean shouldBackoff(Throwable th);
}
