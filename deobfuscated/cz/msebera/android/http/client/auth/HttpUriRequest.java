package cz.msebera.android.http.client.auth;

import cz.msebera.android.http.HttpRequest;
import java.net.URI;

public abstract interface HttpUriRequest
  extends HttpRequest
{
  public abstract void abort();
  
  public abstract String getMethod();
  
  public abstract URI getURI();
  
  public abstract boolean isAborted();
}
