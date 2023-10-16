package cz.msebera.android.http.impl.conn;

import c.a.a.a.i.c.i;
import c.a.a.a.i.f.a;
import c.a.a.a.t;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.HttpResponseFactory;
import cz.msebera.android.http.NoHttpResponseException;
import cz.msebera.android.http.ProtocolException;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.impl.io.AbstractMessageParser;
import cz.msebera.android.http.io.SessionInputBuffer;
import cz.msebera.android.http.message.LineParser;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.util.HttpParams;

public class DefaultHttpResponseParser
  extends a<t>
{
  private final cz.msebera.android.http.mime.CharArrayBuffer lineBuf;
  public HttpClientAndroidLog log = new HttpClientAndroidLog(i.class);
  private final HttpResponseFactory responseFactory;
  
  public DefaultHttpResponseParser(SessionInputBuffer paramSessionInputBuffer, LineParser paramLineParser, HttpResponseFactory paramHttpResponseFactory, HttpParams paramHttpParams)
  {
    super(paramSessionInputBuffer, paramLineParser, paramHttpParams);
    Args.notNull(paramHttpResponseFactory, "Response factory");
    responseFactory = paramHttpResponseFactory;
    lineBuf = new cz.msebera.android.http.mime.CharArrayBuffer(128);
  }
  
  protected HttpResponse parseHead(SessionInputBuffer paramSessionInputBuffer)
  {
    int i = 0;
    for (;;)
    {
      lineBuf.clear();
      int j = paramSessionInputBuffer.readLine(lineBuf);
      if ((j == -1) && (i == 0)) {
        throw new NoHttpResponseException("The target server failed to respond");
      }
      Object localObject = new cz.msebera.android.http.message.CharArrayBuffer(0, lineBuf.length());
      if (lineParser.hasProtocolVersion(lineBuf, (cz.msebera.android.http.message.CharArrayBuffer)localObject))
      {
        paramSessionInputBuffer = lineParser.parseStatusLine(lineBuf, (cz.msebera.android.http.message.CharArrayBuffer)localObject);
        return responseFactory.newHttpResponse(paramSessionInputBuffer, null);
      }
      if ((j == -1) || (reject(lineBuf, i))) {
        break;
      }
      if (log.isDebugEnabled())
      {
        localObject = log;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Garbage in response: ");
        localStringBuilder.append(lineBuf.toString());
        ((HttpClientAndroidLog)localObject).debug(localStringBuilder.toString());
      }
      i += 1;
    }
    paramSessionInputBuffer = new ProtocolException("The server failed to respond with a valid HTTP response");
    throw paramSessionInputBuffer;
  }
  
  protected boolean reject(cz.msebera.android.http.mime.CharArrayBuffer paramCharArrayBuffer, int paramInt)
  {
    return false;
  }
}
