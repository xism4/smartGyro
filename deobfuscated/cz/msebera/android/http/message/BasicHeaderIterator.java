package cz.msebera.android.http.message;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HeaderIterator;
import cz.msebera.android.http.mime.Args;
import java.util.NoSuchElementException;

public class BasicHeaderIterator
  implements HeaderIterator
{
  protected final Header[] allHeaders;
  protected int currentIndex;
  protected String headerName;
  
  public BasicHeaderIterator(Header[] paramArrayOfHeader, String paramString)
  {
    Args.notNull(paramArrayOfHeader, "Header array");
    allHeaders = ((Header[])paramArrayOfHeader);
    headerName = paramString;
    currentIndex = findNext(-1);
  }
  
  protected boolean filterHeader(int paramInt)
  {
    String str = headerName;
    return (str == null) || (str.equalsIgnoreCase(allHeaders[paramInt].getName()));
  }
  
  protected int findNext(int paramInt)
  {
    if (paramInt < -1) {
      return -1;
    }
    int i = allHeaders.length;
    for (boolean bool = false; (!bool) && (paramInt < i - 1); bool = filterHeader(paramInt)) {
      paramInt += 1;
    }
    if (bool) {
      return paramInt;
    }
    return -1;
  }
  
  public boolean hasNext()
  {
    return currentIndex >= 0;
  }
  
  public final Object next()
  {
    return nextHeader();
  }
  
  public Header nextHeader()
  {
    int i = currentIndex;
    if (i >= 0)
    {
      currentIndex = findNext(i);
      return allHeaders[i];
    }
    throw new NoSuchElementException("Iteration already finished.");
  }
  
  public void remove()
  {
    throw new UnsupportedOperationException("Removing headers is not supported.");
  }
}
