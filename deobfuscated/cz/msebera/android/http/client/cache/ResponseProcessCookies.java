package cz.msebera.android.http.client.cache;

import c.a.a.a.b.e.j;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HeaderIterator;
import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.HttpResponseInterceptor;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.client.CookieStore;
import cz.msebera.android.http.cookie.Cookie;
import cz.msebera.android.http.cookie.CookieOrigin;
import cz.msebera.android.http.cookie.CookieSpec;
import cz.msebera.android.http.cookie.MalformedCookieException;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.mime.Args;
import java.util.Iterator;
import java.util.List;

public class ResponseProcessCookies
  implements HttpResponseInterceptor
{
  public HttpClientAndroidLog log = new HttpClientAndroidLog(j.class);
  
  public ResponseProcessCookies() {}
  
  private static String formatCooke(Cookie paramCookie)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramCookie.getName());
    localStringBuilder.append("=\"");
    String str = paramCookie.getValue();
    Object localObject = str;
    if (str != null)
    {
      if (str.length() > 100)
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append(str.substring(0, 100));
        ((StringBuilder)localObject).append("...");
        localObject = ((StringBuilder)localObject).toString();
      }
      localStringBuilder.append((String)localObject);
    }
    localStringBuilder.append("\"");
    localStringBuilder.append(", version:");
    localStringBuilder.append(Integer.toString(paramCookie.getVersion()));
    localStringBuilder.append(", domain:");
    localStringBuilder.append(paramCookie.getDomain());
    localStringBuilder.append(", path:");
    localStringBuilder.append(paramCookie.getPath());
    localStringBuilder.append(", expiry:");
    localStringBuilder.append(paramCookie.getExpiryDate());
    return localStringBuilder.toString();
  }
  
  private void processCookies(HeaderIterator paramHeaderIterator, CookieSpec paramCookieSpec, CookieOrigin paramCookieOrigin, CookieStore paramCookieStore)
  {
    while (paramHeaderIterator.hasNext())
    {
      Header localHeader = paramHeaderIterator.nextHeader();
      try
      {
        Iterator localIterator = paramCookieSpec.parse(localHeader, paramCookieOrigin).iterator();
        for (;;)
        {
          boolean bool = localIterator.hasNext();
          if (!bool) {
            break;
          }
          localObject1 = localIterator.next();
          localObject1 = (Cookie)localObject1;
          Object localObject2;
          try
          {
            paramCookieSpec.validate((Cookie)localObject1, paramCookieOrigin);
            paramCookieStore.addCookie((Cookie)localObject1);
            HttpClientAndroidLog localHttpClientAndroidLog = log;
            bool = localHttpClientAndroidLog.isDebugEnabled();
            if (!bool) {
              continue;
            }
            localHttpClientAndroidLog = log;
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append("Cookie accepted [");
            ((StringBuilder)localObject2).append(formatCooke((Cookie)localObject1));
            ((StringBuilder)localObject2).append("]");
            localHttpClientAndroidLog.debug(((StringBuilder)localObject2).toString());
          }
          catch (MalformedCookieException localMalformedCookieException2)
          {
            localObject2 = log;
            bool = ((HttpClientAndroidLog)localObject2).isWarnEnabled();
          }
          if (bool)
          {
            localObject2 = log;
            StringBuilder localStringBuilder2 = new StringBuilder();
            localStringBuilder2.append("Cookie rejected [");
            localStringBuilder2.append(formatCooke((Cookie)localObject1));
            localStringBuilder2.append("] ");
            localStringBuilder2.append(localMalformedCookieException2.getMessage());
            ((HttpClientAndroidLog)localObject2).warn(localStringBuilder2.toString());
          }
        }
        if (!log.isWarnEnabled()) {
          continue;
        }
      }
      catch (MalformedCookieException localMalformedCookieException1) {}
      Object localObject1 = log;
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("Invalid cookie header: \"");
      localStringBuilder1.append(localHeader);
      localStringBuilder1.append("\". ");
      localStringBuilder1.append(localMalformedCookieException1.getMessage());
      ((HttpClientAndroidLog)localObject1).warn(localStringBuilder1.toString());
    }
  }
  
  public void process(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    Args.notNull(paramHttpResponse, "HTTP request");
    Args.notNull(paramHttpContext, "HTTP context");
    Object localObject = HttpClientContext.adapt(paramHttpContext);
    paramHttpContext = ((HttpClientContext)localObject).getCookieSpec();
    if (paramHttpContext == null)
    {
      paramHttpResponse = log;
      paramHttpContext = "Cookie spec not specified in HTTP context";
    }
    CookieStore localCookieStore;
    for (;;)
    {
      paramHttpResponse.debug(paramHttpContext);
      return;
      localCookieStore = ((HttpClientContext)localObject).getCookieStore();
      if (localCookieStore == null)
      {
        paramHttpResponse = log;
        paramHttpContext = "Cookie store not specified in HTTP context";
      }
      else
      {
        localObject = ((HttpClientContext)localObject).getCookieOrigin();
        if (localObject != null) {
          break;
        }
        paramHttpResponse = log;
        paramHttpContext = "Cookie origin not specified in HTTP context";
      }
    }
    processCookies(paramHttpResponse.headerIterator("Set-Cookie"), paramHttpContext, (CookieOrigin)localObject, localCookieStore);
    if (paramHttpContext.getVersion() > 0) {
      processCookies(paramHttpResponse.headerIterator("Set-Cookie2"), paramHttpContext, (CookieOrigin)localObject, localCookieStore);
    }
  }
}
