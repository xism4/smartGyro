package cz.msebera.android.http.impl.io;

import c.a.a.a.i.f.b;
import c.a.a.a.r;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.io.SessionOutputBuffer;
import cz.msebera.android.http.message.LineFormatter;
import cz.msebera.android.http.util.HttpParams;

@Deprecated
public class HttpRequestWriter
  extends b<r>
{
  public HttpRequestWriter(SessionOutputBuffer paramSessionOutputBuffer, LineFormatter paramLineFormatter, HttpParams paramHttpParams)
  {
    super(paramSessionOutputBuffer, paramLineFormatter, paramHttpParams);
  }
  
  protected void writeHeadLine(HttpRequest paramHttpRequest)
  {
    lineFormatter.formatRequestLine(lineBuf, paramHttpRequest.getRequestLine());
    sessionBuffer.writeLine(lineBuf);
  }
}
