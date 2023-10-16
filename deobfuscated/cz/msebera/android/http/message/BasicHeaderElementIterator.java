package cz.msebera.android.http.message;

import cz.msebera.android.http.FormattedHeader;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HeaderElement;
import cz.msebera.android.http.HeaderElementIterator;
import cz.msebera.android.http.HeaderIterator;
import cz.msebera.android.http.mime.Args;
import java.util.NoSuchElementException;

public class BasicHeaderElementIterator
  implements HeaderElementIterator
{
  private cz.msebera.android.http.mime.CharArrayBuffer buffer = null;
  private HeaderElement currentElement = null;
  private CharArrayBuffer cursor = null;
  private final HeaderIterator headerIt;
  private final HeaderValueParser parser;
  
  public BasicHeaderElementIterator(HeaderIterator paramHeaderIterator)
  {
    this(paramHeaderIterator, BasicHeaderValueParser.INSTANCE);
  }
  
  public BasicHeaderElementIterator(HeaderIterator paramHeaderIterator, HeaderValueParser paramHeaderValueParser)
  {
    Args.notNull(paramHeaderIterator, "Header iterator");
    headerIt = ((HeaderIterator)paramHeaderIterator);
    Args.notNull(paramHeaderValueParser, "Parser");
    parser = ((HeaderValueParser)paramHeaderValueParser);
  }
  
  private void bufferHeaderValue()
  {
    cursor = null;
    buffer = null;
    while (headerIt.hasNext())
    {
      Object localObject = headerIt.nextHeader();
      if ((localObject instanceof FormattedHeader))
      {
        localObject = (FormattedHeader)localObject;
        buffer = ((FormattedHeader)localObject).getBuffer();
        cursor = new CharArrayBuffer(0, buffer.length());
        cursor.append(((FormattedHeader)localObject).getValuePos());
        return;
      }
      localObject = ((Header)localObject).getValue();
      if (localObject != null)
      {
        buffer = new cz.msebera.android.http.mime.CharArrayBuffer(((String)localObject).length());
        buffer.append((String)localObject);
        cursor = new CharArrayBuffer(0, buffer.length());
      }
    }
  }
  
  private void parseNextElement()
  {
    for (;;)
    {
      if ((!headerIt.hasNext()) && (cursor == null)) {
        return;
      }
      Object localObject = cursor;
      if ((localObject == null) || (((CharArrayBuffer)localObject).atEnd())) {
        bufferHeaderValue();
      }
      if (cursor != null)
      {
        while (!cursor.atEnd())
        {
          localObject = parser.parseHeaderElement(buffer, cursor);
          if ((((HeaderElement)localObject).getName().length() != 0) || (((HeaderElement)localObject).getValue() != null))
          {
            currentElement = ((HeaderElement)localObject);
            return;
          }
        }
        if (cursor.atEnd())
        {
          cursor = null;
          buffer = null;
        }
      }
    }
  }
  
  public boolean hasNext()
  {
    if (currentElement == null) {
      parseNextElement();
    }
    return currentElement != null;
  }
  
  public final Object next()
  {
    return nextElement();
  }
  
  public HeaderElement nextElement()
  {
    if (currentElement == null) {
      parseNextElement();
    }
    HeaderElement localHeaderElement = currentElement;
    if (localHeaderElement != null)
    {
      currentElement = null;
      return localHeaderElement;
    }
    throw new NoSuchElementException("No more header elements available");
  }
  
  public void remove()
  {
    throw new UnsupportedOperationException("Remove not supported");
  }
}
