package lombok.eclipse.handlers.http;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HeaderElement;
import cz.msebera.android.http.HttpEntity;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.HttpResponseInterceptor;
import cz.msebera.android.http.execchain.HttpContext;

class ImmutableHttpProcessor
  implements HttpResponseInterceptor
{
  ImmutableHttpProcessor(AsyncHttpClient paramAsyncHttpClient) {}
  
  public void process(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    paramHttpContext = paramHttpResponse.getEntity();
    if (paramHttpContext == null) {
      return;
    }
    Object localObject = paramHttpContext.getContentEncoding();
    if (localObject != null)
    {
      localObject = ((Header)localObject).getElements();
      int j = localObject.length;
      int i = 0;
      while (i < j)
      {
        if (localObject[i].getName().equalsIgnoreCase("gzip"))
        {
          paramHttpResponse.setEntity(new AsyncHttpClient.InflatingEntity(paramHttpContext));
          return;
        }
        i += 1;
      }
    }
  }
}
