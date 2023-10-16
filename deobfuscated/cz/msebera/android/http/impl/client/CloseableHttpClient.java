package cz.msebera.android.http.impl.client;

import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.client.ClientProtocolException;
import cz.msebera.android.http.client.HttpClient;
import cz.msebera.android.http.client.auth.CloseableHttpResponse;
import cz.msebera.android.http.client.auth.HttpUriRequest;
import cz.msebera.android.http.client.ssl.URIUtils;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.mime.Args;
import java.io.Closeable;
import java.net.URI;

public abstract class CloseableHttpClient
  implements HttpClient, Closeable
{
  public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());
  
  public CloseableHttpClient() {}
  
  private static HttpHost determineTarget(HttpUriRequest paramHttpUriRequest)
  {
    paramHttpUriRequest = paramHttpUriRequest.getURI();
    if (paramHttpUriRequest.isAbsolute())
    {
      Object localObject = URIUtils.extractHost(paramHttpUriRequest);
      if (localObject != null) {
        return localObject;
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("URI does not specify a valid host name: ");
      ((StringBuilder)localObject).append(paramHttpUriRequest);
      throw new ClientProtocolException(((StringBuilder)localObject).toString());
    }
    return null;
  }
  
  protected abstract CloseableHttpResponse doExecute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext);
  
  public CloseableHttpResponse execute(HttpUriRequest paramHttpUriRequest, HttpContext paramHttpContext)
  {
    Args.notNull(paramHttpUriRequest, "HTTP request");
    return doExecute(determineTarget(paramHttpUriRequest), paramHttpUriRequest, paramHttpContext);
  }
}
