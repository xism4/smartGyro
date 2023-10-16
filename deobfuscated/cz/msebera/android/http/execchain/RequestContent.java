package cz.msebera.android.http.execchain;

import cz.msebera.android.http.HttpEntity;
import cz.msebera.android.http.HttpEntityEnclosingRequest;
import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpRequestInterceptor;
import cz.msebera.android.http.HttpVersion;
import cz.msebera.android.http.ProtocolException;
import cz.msebera.android.http.ProtocolVersion;
import cz.msebera.android.http.RequestLine;
import cz.msebera.android.http.mime.Args;

public class RequestContent
  implements HttpRequestInterceptor
{
  private final boolean overwrite;
  
  public RequestContent()
  {
    this(false);
  }
  
  public RequestContent(boolean paramBoolean)
  {
    overwrite = paramBoolean;
  }
  
  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
  {
    Args.notNull(paramHttpRequest, "HTTP request");
    if ((paramHttpRequest instanceof HttpEntityEnclosingRequest))
    {
      if (overwrite)
      {
        paramHttpRequest.removeHeaders("Transfer-Encoding");
        paramHttpRequest.removeHeaders("Content-Length");
      }
      else
      {
        if (paramHttpRequest.containsHeader("Transfer-Encoding")) {
          break label270;
        }
        if (paramHttpRequest.containsHeader("Content-Length")) {
          break label260;
        }
      }
      paramHttpContext = paramHttpRequest.getRequestLine().getProtocolVersion();
      HttpEntity localHttpEntity = ((HttpEntityEnclosingRequest)paramHttpRequest).getEntity();
      if (localHttpEntity == null)
      {
        paramHttpRequest.addHeader("Content-Length", "0");
        return;
      }
      if ((!localHttpEntity.isChunked()) && (localHttpEntity.getContentLength() >= 0L))
      {
        paramHttpRequest.addHeader("Content-Length", Long.toString(localHttpEntity.getContentLength()));
      }
      else
      {
        if (paramHttpContext.lessEquals(HttpVersion.HTTP_1_0)) {
          break label227;
        }
        paramHttpRequest.addHeader("Transfer-Encoding", "chunked");
      }
      if ((localHttpEntity.getContentType() != null) && (!paramHttpRequest.containsHeader("Content-Type"))) {
        paramHttpRequest.addHeader(localHttpEntity.getContentType());
      }
      if ((localHttpEntity.getContentEncoding() != null) && (!paramHttpRequest.containsHeader("Content-Encoding")))
      {
        paramHttpRequest.addHeader(localHttpEntity.getContentEncoding());
        return;
        label227:
        paramHttpRequest = new StringBuilder();
        paramHttpRequest.append("Chunked transfer encoding not allowed for ");
        paramHttpRequest.append(paramHttpContext);
        throw new ProtocolException(paramHttpRequest.toString());
        label260:
        throw new ProtocolException("Content-Length header already present");
        label270:
        throw new ProtocolException("Transfer-encoding header already present");
      }
    }
  }
}
