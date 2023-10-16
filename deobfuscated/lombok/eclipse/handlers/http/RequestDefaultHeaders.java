package lombok.eclipse.handlers.http;

import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpRequestInterceptor;
import cz.msebera.android.http.auth.AuthScope;
import cz.msebera.android.http.auth.AuthState;
import cz.msebera.android.http.client.CredentialsProvider;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.impl.auth.BasicScheme;

class RequestDefaultHeaders
  implements HttpRequestInterceptor
{
  RequestDefaultHeaders(AsyncHttpClient paramAsyncHttpClient) {}
  
  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
  {
    paramHttpRequest = (AuthState)paramHttpContext.getAttribute("http.auth.target-scope");
    CredentialsProvider localCredentialsProvider = (CredentialsProvider)paramHttpContext.getAttribute("http.auth.credentials-provider");
    paramHttpContext = (HttpHost)paramHttpContext.getAttribute("http.target_host");
    if (paramHttpRequest.getAuthScheme() == null)
    {
      paramHttpContext = localCredentialsProvider.getCredentials(new AuthScope(paramHttpContext.getHostName(), paramHttpContext.getPort()));
      if (paramHttpContext != null)
      {
        paramHttpRequest.setAuthScheme(new BasicScheme());
        paramHttpRequest.update(paramHttpContext);
      }
    }
  }
}
