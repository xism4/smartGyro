package cz.msebera.android.http.impl.client;

import cz.msebera.android.http.HeaderElement;
import cz.msebera.android.http.HeaderElementIterator;
import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.conn.ConnectionKeepAliveStrategy;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.message.BasicHeaderElementIterator;
import cz.msebera.android.http.mime.Args;

public class DefaultConnectionKeepAliveStrategy
  implements ConnectionKeepAliveStrategy
{
  public static final DefaultConnectionKeepAliveStrategy INSTANCE = new DefaultConnectionKeepAliveStrategy();
  
  public DefaultConnectionKeepAliveStrategy() {}
  
  public long getKeepAliveDuration(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    Args.notNull(paramHttpResponse, "HTTP response");
    paramHttpResponse = new BasicHeaderElementIterator(paramHttpResponse.headerIterator("Keep-Alive"));
    for (;;)
    {
      Object localObject;
      if (paramHttpResponse.hasNext())
      {
        localObject = paramHttpResponse.nextElement();
        paramHttpContext = ((HeaderElement)localObject).getName();
        localObject = ((HeaderElement)localObject).getValue();
        if ((localObject == null) || (!paramHttpContext.equalsIgnoreCase("timeout"))) {}
      }
      else
      {
        try
        {
          long l = Long.parseLong((String)localObject);
          return l * 1000L;
        }
        catch (NumberFormatException paramHttpContext) {}
        return -1L;
      }
    }
  }
}
