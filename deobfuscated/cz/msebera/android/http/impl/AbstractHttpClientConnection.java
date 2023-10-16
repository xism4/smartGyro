package cz.msebera.android.http.impl;

import c.a.a.a.j.c;
import c.a.a.a.j.d;
import c.a.a.a.r;
import c.a.a.a.t;
import cz.msebera.android.http.HttpClientConnection;
import cz.msebera.android.http.HttpConnection;
import cz.msebera.android.http.HttpEntityEnclosingRequest;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.HttpResponseFactory;
import cz.msebera.android.http.StatusLine;
import cz.msebera.android.http.impl.entity.EntityDeserializer;
import cz.msebera.android.http.impl.entity.EntitySerializer;
import cz.msebera.android.http.impl.entity.LaxContentLengthStrategy;
import cz.msebera.android.http.impl.entity.StrictContentLengthStrategy;
import cz.msebera.android.http.impl.io.HttpRequestWriter;
import cz.msebera.android.http.io.EofSensor;
import cz.msebera.android.http.io.HttpMessageParser;
import cz.msebera.android.http.io.HttpMessageWriter;
import cz.msebera.android.http.io.HttpTransportMetrics;
import cz.msebera.android.http.io.SessionInputBuffer;
import cz.msebera.android.http.io.SessionOutputBuffer;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.util.HttpParams;
import java.io.IOException;
import java.net.SocketTimeoutException;

@Deprecated
public abstract class AbstractHttpClientConnection
  implements HttpClientConnection
{
  private final EntityDeserializer entitydeserializer = createEntityDeserializer();
  private final EntitySerializer entityserializer = createEntitySerializer();
  private EofSensor eofSensor = null;
  private SessionInputBuffer inbuffer = null;
  private HttpConnectionMetricsImpl metrics = null;
  private SessionOutputBuffer outbuffer = null;
  private d<r> requestWriter = null;
  private c<t> responseParser = null;
  
  public AbstractHttpClientConnection() {}
  
  protected abstract void assertOpen();
  
  protected HttpConnectionMetricsImpl createConnectionMetrics(HttpTransportMetrics paramHttpTransportMetrics1, HttpTransportMetrics paramHttpTransportMetrics2)
  {
    return new HttpConnectionMetricsImpl(paramHttpTransportMetrics1, paramHttpTransportMetrics2);
  }
  
  protected EntityDeserializer createEntityDeserializer()
  {
    return new EntityDeserializer(new LaxContentLengthStrategy());
  }
  
  protected EntitySerializer createEntitySerializer()
  {
    return new EntitySerializer(new StrictContentLengthStrategy());
  }
  
  protected HttpResponseFactory createHttpResponseFactory()
  {
    return DefaultHttpResponseFactory.INSTANCE;
  }
  
  protected HttpMessageWriter createRequestWriter(SessionOutputBuffer paramSessionOutputBuffer, HttpParams paramHttpParams)
  {
    return new HttpRequestWriter(paramSessionOutputBuffer, null, paramHttpParams);
  }
  
  protected abstract HttpMessageParser createResponseParser(SessionInputBuffer paramSessionInputBuffer, HttpResponseFactory paramHttpResponseFactory, HttpParams paramHttpParams);
  
  protected void doFlush()
  {
    outbuffer.flush();
  }
  
  public void flush()
  {
    assertOpen();
    doFlush();
  }
  
  protected void init(SessionInputBuffer paramSessionInputBuffer, SessionOutputBuffer paramSessionOutputBuffer, HttpParams paramHttpParams)
  {
    Args.notNull(paramSessionInputBuffer, "Input session buffer");
    inbuffer = ((SessionInputBuffer)paramSessionInputBuffer);
    Args.notNull(paramSessionOutputBuffer, "Output session buffer");
    outbuffer = ((SessionOutputBuffer)paramSessionOutputBuffer);
    if ((paramSessionInputBuffer instanceof EofSensor)) {
      eofSensor = ((EofSensor)paramSessionInputBuffer);
    }
    responseParser = createResponseParser(paramSessionInputBuffer, createHttpResponseFactory(), paramHttpParams);
    requestWriter = createRequestWriter(paramSessionOutputBuffer, paramHttpParams);
    metrics = createConnectionMetrics(paramSessionInputBuffer.getMetrics(), paramSessionOutputBuffer.getMetrics());
  }
  
  protected boolean isEof()
  {
    EofSensor localEofSensor = eofSensor;
    return (localEofSensor != null) && (localEofSensor.isEof());
  }
  
  public boolean isResponseAvailable(int paramInt)
  {
    assertOpen();
    SessionInputBuffer localSessionInputBuffer = inbuffer;
    try
    {
      boolean bool = localSessionInputBuffer.isDataAvailable(paramInt);
      return bool;
    }
    catch (SocketTimeoutException localSocketTimeoutException)
    {
      for (;;) {}
    }
    return false;
  }
  
  public boolean isStale()
  {
    if (!isOpen()) {
      return true;
    }
    if (isEof()) {
      return true;
    }
    SessionInputBuffer localSessionInputBuffer = inbuffer;
    try
    {
      localSessionInputBuffer.isDataAvailable(1);
      boolean bool = isEof();
      return bool;
    }
    catch (SocketTimeoutException localSocketTimeoutException)
    {
      for (;;) {}
    }
    catch (IOException localIOException) {}
    return false;
    return true;
  }
  
  public void receiveResponseEntity(HttpResponse paramHttpResponse)
  {
    Args.notNull(paramHttpResponse, "HTTP response");
    assertOpen();
    paramHttpResponse.setEntity(entitydeserializer.deserialize(inbuffer, paramHttpResponse));
  }
  
  public HttpResponse receiveResponseHeader()
  {
    assertOpen();
    HttpResponse localHttpResponse = (HttpResponse)responseParser.parse();
    if (localHttpResponse.getStatusLine().getStatusCode() >= 200) {
      metrics.incrementResponseCount();
    }
    return localHttpResponse;
  }
  
  public void sendRequestEntity(HttpEntityEnclosingRequest paramHttpEntityEnclosingRequest)
  {
    Args.notNull(paramHttpEntityEnclosingRequest, "HTTP request");
    assertOpen();
    if (paramHttpEntityEnclosingRequest.getEntity() == null) {
      return;
    }
    entityserializer.serialize(outbuffer, paramHttpEntityEnclosingRequest, paramHttpEntityEnclosingRequest.getEntity());
  }
  
  public void sendRequestHeader(HttpRequest paramHttpRequest)
  {
    Args.notNull(paramHttpRequest, "HTTP request");
    assertOpen();
    requestWriter.write(paramHttpRequest);
    metrics.incrementRequestCount();
  }
}
