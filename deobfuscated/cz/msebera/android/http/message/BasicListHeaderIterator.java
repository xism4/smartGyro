package cz.msebera.android.http.message;

import c.a.a.a.e;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HeaderIterator;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.Asserts;
import java.util.List;
import java.util.NoSuchElementException;

public class BasicListHeaderIterator
  implements HeaderIterator
{
  protected final List<e> allHeaders;
  protected int currentIndex;
  protected String headerName;
  protected int lastIndex;
  
  public BasicListHeaderIterator(List paramList, String paramString)
  {
    Args.notNull(paramList, "Header list");
    allHeaders = ((List)paramList);
    headerName = paramString;
    currentIndex = findNext(-1);
    lastIndex = -1;
  }
  
  protected boolean filterHeader(int paramInt)
  {
    if (headerName == null) {
      return true;
    }
    String str = ((Header)allHeaders.get(paramInt)).getName();
    return headerName.equalsIgnoreCase(str);
  }
  
  protected int findNext(int paramInt)
  {
    if (paramInt < -1) {
      return -1;
    }
    int i = allHeaders.size();
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
      lastIndex = i;
      currentIndex = findNext(i);
      return (Header)allHeaders.get(i);
    }
    throw new NoSuchElementException("Iteration already finished.");
  }
  
  public void remove()
  {
    boolean bool;
    if (lastIndex >= 0) {
      bool = true;
    } else {
      bool = false;
    }
    Asserts.check(bool, "No header to remove");
    allHeaders.remove(lastIndex);
    lastIndex = -1;
    currentIndex -= 1;
  }
}
