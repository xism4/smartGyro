package cz.msebera.android.http.impl.io;

import c.a.a.a.j.c;
import c.a.a.a.p.d;
import c.a.a.a.q;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.MessageConstraintException;
import cz.msebera.android.http.ParseException;
import cz.msebera.android.http.ProtocolException;
import cz.msebera.android.http.io.SessionInputBuffer;
import cz.msebera.android.http.message.BasicLineParser;
import cz.msebera.android.http.message.LineParser;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;
import cz.msebera.android.http.protocol.MessageConstraints;
import cz.msebera.android.http.util.HttpParamConfig;
import cz.msebera.android.http.util.HttpParams;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMessageParser<T extends q>
  implements c<T>
{
  private final List<d> headerLines;
  protected final LineParser lineParser;
  private T message;
  private final MessageConstraints messageConstraints;
  private final SessionInputBuffer sessionBuffer;
  private int state;
  
  public AbstractMessageParser(SessionInputBuffer paramSessionInputBuffer, LineParser paramLineParser, HttpParams paramHttpParams)
  {
    Args.notNull(paramSessionInputBuffer, "Session input buffer");
    Args.notNull(paramHttpParams, "HTTP parameters");
    sessionBuffer = paramSessionInputBuffer;
    messageConstraints = HttpParamConfig.getMessageConstraints(paramHttpParams);
    if (paramLineParser == null) {
      paramLineParser = BasicLineParser.INSTANCE;
    }
    lineParser = paramLineParser;
    headerLines = new ArrayList();
    state = 0;
  }
  
  public static Header[] parseHeaders(SessionInputBuffer paramSessionInputBuffer, int paramInt1, int paramInt2, LineParser paramLineParser)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramLineParser == null) {
      paramLineParser = BasicLineParser.INSTANCE;
    }
    return parseHeaders(paramSessionInputBuffer, paramInt1, paramInt2, paramLineParser, localArrayList);
  }
  
  public static Header[] parseHeaders(SessionInputBuffer paramSessionInputBuffer, int paramInt1, int paramInt2, LineParser paramLineParser, List paramList)
  {
    Args.notNull(paramSessionInputBuffer, "Session input buffer");
    Args.notNull(paramLineParser, "Line parser");
    Args.notNull(paramList, "Header line list");
    Object localObject1 = null;
    Object localObject3 = null;
    int j;
    for (;;)
    {
      if (localObject1 == null) {
        localObject1 = new CharArrayBuffer(64);
      } else {
        ((CharArrayBuffer)localObject1).clear();
      }
      int k = paramSessionInputBuffer.readLine((CharArrayBuffer)localObject1);
      j = 0;
      int i = 0;
      if ((k == -1) || (((CharArrayBuffer)localObject1).length() < 1)) {
        break label291;
      }
      Object localObject2;
      Object localObject4;
      if (((((CharArrayBuffer)localObject1).charAt(0) == ' ') || (((CharArrayBuffer)localObject1).charAt(0) == '\t')) && (localObject3 != null))
      {
        while (i < ((CharArrayBuffer)localObject1).length())
        {
          j = ((CharArrayBuffer)localObject1).charAt(i);
          if ((j != 32) && (j != 9)) {
            break;
          }
          i += 1;
        }
        if ((paramInt2 > 0) && (localObject3.length() + 1 + ((CharArrayBuffer)localObject1).length() - i > paramInt2)) {
          throw new MessageConstraintException("Maximum line length limit exceeded");
        }
        localObject3.append(' ');
        localObject3.append((CharArrayBuffer)localObject1, i, ((CharArrayBuffer)localObject1).length() - i);
        localObject2 = localObject1;
        localObject4 = localObject3;
      }
      else
      {
        paramList.add(localObject1);
        localObject2 = null;
        localObject4 = localObject1;
      }
      localObject1 = localObject2;
      localObject3 = localObject4;
      if (paramInt1 > 0)
      {
        if (paramList.size() >= paramInt1) {
          break;
        }
        localObject1 = localObject2;
        localObject3 = localObject4;
      }
    }
    throw new MessageConstraintException("Maximum header count exceeded");
    label291:
    paramSessionInputBuffer = new Header[paramList.size()];
    paramInt1 = j;
    while (paramInt1 < paramList.size())
    {
      localObject1 = (CharArrayBuffer)paramList.get(paramInt1);
      try
      {
        localObject1 = paramLineParser.parseHeader((CharArrayBuffer)localObject1);
        paramSessionInputBuffer[paramInt1] = localObject1;
        paramInt1 += 1;
      }
      catch (ParseException paramSessionInputBuffer)
      {
        throw new ProtocolException(paramSessionInputBuffer.getMessage());
      }
    }
    return paramSessionInputBuffer;
  }
  
  public HttpMessage parse()
  {
    int i = state;
    Object localObject;
    if (i != 0)
    {
      if (i != 1) {
        throw new IllegalStateException("Inconsistent parser state");
      }
    }
    else {
      localObject = sessionBuffer;
    }
    try
    {
      localObject = parseHead((SessionInputBuffer)localObject);
      message = ((HttpMessage)localObject);
      state = 1;
      localObject = parseHeaders(sessionBuffer, messageConstraints.getMaxLineLength(), messageConstraints.getMaxHeaderCount(), lineParser, headerLines);
      message.setHeaders((Header[])localObject);
      localObject = message;
      message = null;
      headerLines.clear();
      state = 0;
      return localObject;
    }
    catch (ParseException localParseException)
    {
      throw new ProtocolException(localParseException.getMessage(), localParseException);
    }
  }
  
  protected abstract HttpMessage parseHead(SessionInputBuffer paramSessionInputBuffer);
}
