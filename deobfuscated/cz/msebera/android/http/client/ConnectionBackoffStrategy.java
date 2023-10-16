package cz.msebera.android.http.client;

import cz.msebera.android.http.HttpResponse;

public abstract interface ConnectionBackoffStrategy
{
  public abstract boolean shouldBackoff(HttpResponse paramHttpResponse);
  
  public abstract boolean shouldBackoff(Throwable paramThrowable);
}
