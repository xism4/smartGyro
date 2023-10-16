package cz.msebera.android.http.execchain;

import cz.msebera.android.http.HttpClientConnection;
import cz.msebera.android.http.HttpConnection;
import cz.msebera.android.http.HttpEntityEnclosingRequest;
import cz.msebera.android.http.HttpException;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpRequestInterceptor;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.HttpResponseInterceptor;
import cz.msebera.android.http.HttpVersion;
import cz.msebera.android.http.ProtocolException;
import cz.msebera.android.http.ProtocolVersion;
import cz.msebera.android.http.RequestLine;
import cz.msebera.android.http.StatusLine;
import cz.msebera.android.http.mime.Args;
import java.io.IOException;

public class HttpRequestExecutor
{
  private final int waitForContinue;
  
  public HttpRequestExecutor()
  {
    this(3000);
  }
  
  public HttpRequestExecutor(int paramInt)
  {
    Args.positive(paramInt, "Wait for continue time");
    waitForContinue = paramInt;
  }
  
  private static void closeConnection(HttpClientConnection paramHttpClientConnection)
  {
    try
    {
      paramHttpClientConnection.close();
      return;
    }
    catch (IOException paramHttpClientConnection) {}
  }
  
  protected boolean canResponseHaveBody(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse)
  {
    if ("HEAD".equalsIgnoreCase(paramHttpRequest.getRequestLine().getMethod())) {
      return false;
    }
    int i = paramHttpResponse.getStatusLine().getStatusCode();
    return (i >= 200) && (i != 204) && (i != 304) && (i != 205);
  }
  
  protected HttpResponse doReceiveResponse(HttpRequest paramHttpRequest, HttpClientConnection paramHttpClientConnection, HttpContext paramHttpContext)
  {
    Args.notNull(paramHttpRequest, "HTTP request");
    Args.notNull(paramHttpClientConnection, "Client connection");
    Args.notNull(paramHttpContext, "HTTP context");
    paramHttpContext = null;
    HttpResponse localHttpResponse;
    for (int i = 0;; i = localHttpResponse.getStatusLine().getStatusCode())
    {
      if ((paramHttpContext != null) && (i >= 200)) {
        return paramHttpContext;
      }
      localHttpResponse = paramHttpClientConnection.receiveResponseHeader();
      paramHttpContext = localHttpResponse;
      if (canResponseHaveBody(paramHttpRequest, localHttpResponse)) {
        paramHttpClientConnection.receiveResponseEntity(localHttpResponse);
      }
    }
  }
  
  protected HttpResponse doSendRequest(HttpRequest paramHttpRequest, HttpClientConnection paramHttpClientConnection, HttpContext paramHttpContext)
  {
    Args.notNull(paramHttpRequest, "HTTP request");
    Args.notNull(paramHttpClientConnection, "Client connection");
    Args.notNull(paramHttpContext, "HTTP context");
    paramHttpContext.setAttribute("http.connection", paramHttpClientConnection);
    paramHttpContext.setAttribute("http.request_sent", Boolean.FALSE);
    paramHttpClientConnection.sendRequestHeader(paramHttpRequest);
    boolean bool = paramHttpRequest instanceof HttpEntityEnclosingRequest;
    Object localObject2 = null;
    Object localObject3 = null;
    if (bool)
    {
      int j = 1;
      localObject2 = paramHttpRequest.getRequestLine().getProtocolVersion();
      HttpEntityEnclosingRequest localHttpEntityEnclosingRequest = (HttpEntityEnclosingRequest)paramHttpRequest;
      int i = j;
      Object localObject1 = localObject3;
      if (localHttpEntityEnclosingRequest.expectContinue())
      {
        i = j;
        localObject1 = localObject3;
        if (!((ProtocolVersion)localObject2).lessEquals(HttpVersion.HTTP_1_0))
        {
          paramHttpClientConnection.flush();
          i = j;
          localObject1 = localObject3;
          if (paramHttpClientConnection.isResponseAvailable(waitForContinue))
          {
            localObject1 = paramHttpClientConnection.receiveResponseHeader();
            if (canResponseHaveBody(paramHttpRequest, (HttpResponse)localObject1)) {
              paramHttpClientConnection.receiveResponseEntity((HttpResponse)localObject1);
            }
            i = ((HttpResponse)localObject1).getStatusLine().getStatusCode();
            if (i < 200)
            {
              if (i == 100)
              {
                i = j;
                localObject1 = localObject3;
              }
              else
              {
                paramHttpRequest = new StringBuilder();
                paramHttpRequest.append("Unexpected response: ");
                paramHttpRequest.append(((HttpResponse)localObject1).getStatusLine());
                throw new ProtocolException(paramHttpRequest.toString());
              }
            }
            else {
              i = 0;
            }
          }
        }
      }
      localObject2 = localObject1;
      if (i != 0)
      {
        paramHttpClientConnection.sendRequestEntity(localHttpEntityEnclosingRequest);
        localObject2 = localObject1;
      }
    }
    paramHttpClientConnection.flush();
    paramHttpContext.setAttribute("http.request_sent", Boolean.TRUE);
    return localObject2;
  }
  
  public HttpResponse execute(HttpRequest paramHttpRequest, HttpClientConnection paramHttpClientConnection, HttpContext paramHttpContext)
  {
    Args.notNull(paramHttpRequest, "HTTP request");
    Args.notNull(paramHttpClientConnection, "Client connection");
    Args.notNull(paramHttpContext, "HTTP context");
    HttpResponse localHttpResponse;
    try
    {
      localHttpResponse = doSendRequest(paramHttpRequest, paramHttpClientConnection, paramHttpContext);
      if (localHttpResponse == null)
      {
        paramHttpRequest = doReceiveResponse(paramHttpRequest, paramHttpClientConnection, paramHttpContext);
        return paramHttpRequest;
      }
    }
    catch (RuntimeException paramHttpRequest)
    {
      closeConnection(paramHttpClientConnection);
      throw paramHttpRequest;
    }
    catch (HttpException paramHttpRequest)
    {
      closeConnection(paramHttpClientConnection);
      throw paramHttpRequest;
    }
    catch (IOException paramHttpRequest)
    {
      closeConnection(paramHttpClientConnection);
      throw paramHttpRequest;
    }
    return localHttpResponse;
  }
  
  public void postProcess(HttpResponse paramHttpResponse, HttpProcessor paramHttpProcessor, HttpContext paramHttpContext)
  {
    Args.notNull(paramHttpResponse, "HTTP response");
    Args.notNull(paramHttpProcessor, "HTTP processor");
    Args.notNull(paramHttpContext, "HTTP context");
    paramHttpContext.setAttribute("http.response", paramHttpResponse);
    paramHttpProcessor.process(paramHttpResponse, paramHttpContext);
  }
  
  public void preProcess(HttpRequest paramHttpRequest, HttpProcessor paramHttpProcessor, HttpContext paramHttpContext)
  {
    Args.notNull(paramHttpRequest, "HTTP request");
    Args.notNull(paramHttpProcessor, "HTTP processor");
    Args.notNull(paramHttpContext, "HTTP context");
    paramHttpContext.setAttribute("http.request", paramHttpRequest);
    paramHttpProcessor.process(paramHttpRequest, paramHttpContext);
  }
}
