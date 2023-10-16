package cz.msebera.android.http.execchain;

import c.a.a.a.s;
import c.a.a.a.v;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpRequestInterceptor;
import cz.msebera.android.http.HttpResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Deprecated
public final class BasicHttpProcessor
  implements HttpProcessor, HttpResponseInterceptor, HttpClientConnectionManager, Cloneable
{
  protected final List<s> requestInterceptors = new ArrayList();
  protected final List<v> responseInterceptors = new ArrayList();
  
  public BasicHttpProcessor() {}
  
  public final void addInterceptor(HttpRequestInterceptor paramHttpRequestInterceptor)
  {
    addRequestInterceptor(paramHttpRequestInterceptor);
  }
  
  public final void addInterceptor(HttpRequestInterceptor paramHttpRequestInterceptor, int paramInt)
  {
    addRequestInterceptor(paramHttpRequestInterceptor, paramInt);
  }
  
  public final void addInterceptor(cz.msebera.android.http.HttpResponseInterceptor paramHttpResponseInterceptor)
  {
    addResponseInterceptor(paramHttpResponseInterceptor);
  }
  
  public void addRequestInterceptor(HttpRequestInterceptor paramHttpRequestInterceptor)
  {
    if (paramHttpRequestInterceptor == null) {
      return;
    }
    requestInterceptors.add(paramHttpRequestInterceptor);
  }
  
  public void addRequestInterceptor(HttpRequestInterceptor paramHttpRequestInterceptor, int paramInt)
  {
    if (paramHttpRequestInterceptor == null) {
      return;
    }
    requestInterceptors.add(paramInt, paramHttpRequestInterceptor);
  }
  
  public void addResponseInterceptor(cz.msebera.android.http.HttpResponseInterceptor paramHttpResponseInterceptor)
  {
    if (paramHttpResponseInterceptor == null) {
      return;
    }
    responseInterceptors.add(paramHttpResponseInterceptor);
  }
  
  public Object clone()
  {
    BasicHttpProcessor localBasicHttpProcessor = (BasicHttpProcessor)super.clone();
    copyInterceptors(localBasicHttpProcessor);
    return localBasicHttpProcessor;
  }
  
  protected void copyInterceptors(BasicHttpProcessor paramBasicHttpProcessor)
  {
    requestInterceptors.clear();
    requestInterceptors.addAll(requestInterceptors);
    responseInterceptors.clear();
    responseInterceptors.addAll(responseInterceptors);
  }
  
  public HttpRequestInterceptor getRequestInterceptor(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < requestInterceptors.size())) {
      return (HttpRequestInterceptor)requestInterceptors.get(paramInt);
    }
    return null;
  }
  
  public int getRequestInterceptorCount()
  {
    return requestInterceptors.size();
  }
  
  public cz.msebera.android.http.HttpResponseInterceptor getResponseInterceptor(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < responseInterceptors.size())) {
      return (cz.msebera.android.http.HttpResponseInterceptor)responseInterceptors.get(paramInt);
    }
    return null;
  }
  
  public int getResponseInterceptorCount()
  {
    return responseInterceptors.size();
  }
  
  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
  {
    Iterator localIterator = requestInterceptors.iterator();
    while (localIterator.hasNext()) {
      ((HttpRequestInterceptor)localIterator.next()).process(paramHttpRequest, paramHttpContext);
    }
  }
  
  public void process(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    Iterator localIterator = responseInterceptors.iterator();
    while (localIterator.hasNext()) {
      ((cz.msebera.android.http.HttpResponseInterceptor)localIterator.next()).process(paramHttpResponse, paramHttpContext);
    }
  }
}
