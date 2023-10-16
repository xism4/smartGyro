package cz.msebera.android.http.impl;

import cz.msebera.android.http.ConnectionReuseStrategy;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.HttpVersion;
import cz.msebera.android.http.ParseException;
import cz.msebera.android.http.ProtocolVersion;
import cz.msebera.android.http.StatusLine;
import cz.msebera.android.http.TokenIterator;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.message.BasicHeaderIterator;
import cz.msebera.android.http.message.BasicTokenIterator;
import cz.msebera.android.http.mime.Args;

public class DefaultConnectionReuseStrategy
  implements ConnectionReuseStrategy
{
  public static final DefaultConnectionReuseStrategy INSTANCE = new DefaultConnectionReuseStrategy();
  
  public DefaultConnectionReuseStrategy() {}
  
  private boolean canResponseHaveBody(HttpResponse paramHttpResponse)
  {
    int i = paramHttpResponse.getStatusLine().getStatusCode();
    return (i >= 200) && (i != 204) && (i != 304) && (i != 205);
  }
  
  public boolean keepAlive(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    Args.notNull(paramHttpResponse, "HTTP response");
    Args.notNull(paramHttpContext, "HTTP context");
    ProtocolVersion localProtocolVersion = paramHttpResponse.getStatusLine().getProtocolVersion();
    paramHttpContext = paramHttpResponse.getFirstHeader("Transfer-Encoding");
    if (paramHttpContext != null)
    {
      if (!"chunked".equalsIgnoreCase(paramHttpContext.getValue())) {
        return false;
      }
    }
    else if (canResponseHaveBody(paramHttpResponse))
    {
      paramHttpContext = paramHttpResponse.getHeaders("Content-Length");
      if (paramHttpContext.length != 1) {
        break label225;
      }
      paramHttpContext = paramHttpContext[0];
    }
    try
    {
      int i = Integer.parseInt(paramHttpContext.getValue());
      if (i < 0) {
        return false;
      }
      Header[] arrayOfHeader = paramHttpResponse.getHeaders("Connection");
      paramHttpContext = arrayOfHeader;
      if (arrayOfHeader.length == 0) {
        paramHttpContext = paramHttpResponse.getHeaders("Proxy-Connection");
      }
      if (paramHttpContext.length != 0) {}
      boolean bool;
      return false;
    }
    catch (NumberFormatException paramHttpResponse)
    {
      try
      {
        paramHttpResponse = new BasicTokenIterator(new BasicHeaderIterator(paramHttpContext, null));
        i = 0;
        for (;;)
        {
          bool = paramHttpResponse.hasNext();
          if (!bool) {
            break;
          }
          paramHttpContext = paramHttpResponse.nextToken();
          bool = "Close".equalsIgnoreCase(paramHttpContext);
          if (bool) {
            return false;
          }
          bool = "Keep-Alive".equalsIgnoreCase(paramHttpContext);
          if (bool) {
            i = 1;
          }
        }
        if (i != 0) {
          return true;
        }
        return localProtocolVersion.lessEquals(HttpVersion.HTTP_1_0) ^ true;
      }
      catch (ParseException paramHttpResponse) {}
      paramHttpResponse = paramHttpResponse;
      return false;
    }
  }
}
