package cz.msebera.android.http.impl.io;

import c.a.a.a.j.d;
import c.a.a.a.q;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HeaderIterator;
import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.io.SessionOutputBuffer;
import cz.msebera.android.http.message.BasicLineFormatter;
import cz.msebera.android.http.message.LineFormatter;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;
import cz.msebera.android.http.util.HttpParams;

public abstract class AbstractMessageWriter<T extends q>
  implements d<T>
{
  protected final CharArrayBuffer lineBuf;
  protected final LineFormatter lineFormatter;
  protected final SessionOutputBuffer sessionBuffer;
  
  public AbstractMessageWriter(SessionOutputBuffer paramSessionOutputBuffer, LineFormatter paramLineFormatter, HttpParams paramHttpParams)
  {
    Args.notNull(paramSessionOutputBuffer, "Session input buffer");
    sessionBuffer = paramSessionOutputBuffer;
    lineBuf = new CharArrayBuffer(128);
    if (paramLineFormatter == null) {
      paramLineFormatter = BasicLineFormatter.INSTANCE;
    }
    lineFormatter = paramLineFormatter;
  }
  
  public void write(HttpMessage paramHttpMessage)
  {
    Args.notNull(paramHttpMessage, "HTTP message");
    writeHeadLine(paramHttpMessage);
    paramHttpMessage = paramHttpMessage.headerIterator();
    while (paramHttpMessage.hasNext())
    {
      Header localHeader = paramHttpMessage.nextHeader();
      sessionBuffer.writeLine(lineFormatter.formatHeader(lineBuf, localHeader));
    }
    lineBuf.clear();
    sessionBuffer.writeLine(lineBuf);
  }
  
  protected abstract void writeHeadLine(HttpMessage paramHttpMessage);
}
