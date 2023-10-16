package cz.msebera.android.http.execchain;

import cz.msebera.android.http.HttpEntity;
import cz.msebera.android.http.HttpEntityEnclosingRequest;
import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpRequestInterceptor;
import cz.msebera.android.http.HttpVersion;
import cz.msebera.android.http.ProtocolVersion;
import cz.msebera.android.http.RequestLine;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.util.HttpParams;

public class RequestExpectContinue
  implements HttpRequestInterceptor
{
  private final boolean activeByDefault;
  
  public RequestExpectContinue()
  {
    this(false);
  }
  
  public RequestExpectContinue(boolean paramBoolean)
  {
    activeByDefault = paramBoolean;
  }
  
  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
  {
    Args.notNull(paramHttpRequest, "HTTP request");
    if ((!paramHttpRequest.containsHeader("Expect")) && ((paramHttpRequest instanceof HttpEntityEnclosingRequest)))
    {
      paramHttpContext = paramHttpRequest.getRequestLine().getProtocolVersion();
      HttpEntity localHttpEntity = ((HttpEntityEnclosingRequest)paramHttpRequest).getEntity();
      if ((localHttpEntity != null) && (localHttpEntity.getContentLength() != 0L) && (!paramHttpContext.lessEquals(HttpVersion.HTTP_1_0)) && (paramHttpRequest.getParams().getBooleanParameter("http.protocol.expect-continue", activeByDefault))) {
        paramHttpRequest.addHeader("Expect", "100-continue");
      }
    }
  }
}
