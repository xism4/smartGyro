package cz.msebera.android.http.execchain;

import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpRequestInterceptor;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.HttpResponseInterceptor;

public final class ImmutableHttpProcessor
  implements HttpProcessor
{
  private final HttpRequestInterceptor[] requestInterceptors;
  private final HttpResponseInterceptor[] responseInterceptors;
  
  public ImmutableHttpProcessor(HttpRequestInterceptor[] paramArrayOfHttpRequestInterceptor, HttpResponseInterceptor[] paramArrayOfHttpResponseInterceptor)
  {
    int i;
    if (paramArrayOfHttpRequestInterceptor != null)
    {
      i = paramArrayOfHttpRequestInterceptor.length;
      requestInterceptors = new HttpRequestInterceptor[i];
      System.arraycopy(paramArrayOfHttpRequestInterceptor, 0, requestInterceptors, 0, i);
    }
    else
    {
      requestInterceptors = new HttpRequestInterceptor[0];
    }
    if (paramArrayOfHttpResponseInterceptor != null)
    {
      i = paramArrayOfHttpResponseInterceptor.length;
      responseInterceptors = new HttpResponseInterceptor[i];
      System.arraycopy(paramArrayOfHttpResponseInterceptor, 0, responseInterceptors, 0, i);
      return;
    }
    responseInterceptors = new HttpResponseInterceptor[0];
  }
  
  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
  {
    HttpRequestInterceptor[] arrayOfHttpRequestInterceptor = requestInterceptors;
    int j = arrayOfHttpRequestInterceptor.length;
    int i = 0;
    while (i < j)
    {
      arrayOfHttpRequestInterceptor[i].process(paramHttpRequest, paramHttpContext);
      i += 1;
    }
  }
  
  public void process(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    HttpResponseInterceptor[] arrayOfHttpResponseInterceptor = responseInterceptors;
    int j = arrayOfHttpResponseInterceptor.length;
    int i = 0;
    while (i < j)
    {
      arrayOfHttpResponseInterceptor[i].process(paramHttpResponse, paramHttpContext);
      i += 1;
    }
  }
}
