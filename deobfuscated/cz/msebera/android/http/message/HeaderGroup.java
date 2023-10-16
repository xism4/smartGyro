package cz.msebera.android.http.message;

import c.a.a.a.e;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HeaderIterator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HeaderGroup
  implements Cloneable, Serializable
{
  private final Header[] EMPTY = new Header[0];
  private final List<e> headers = new ArrayList(16);
  
  public HeaderGroup() {}
  
  public void addHeader(Header paramHeader)
  {
    if (paramHeader == null) {
      return;
    }
    headers.add(paramHeader);
  }
  
  public void clear()
  {
    headers.clear();
  }
  
  public Object clone()
  {
    return super.clone();
  }
  
  public boolean containsHeader(String paramString)
  {
    int i = 0;
    while (i < headers.size())
    {
      if (((Header)headers.get(i)).getName().equalsIgnoreCase(paramString)) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public Header[] getAllHeaders()
  {
    List localList = headers;
    return (Header[])localList.toArray(new Header[localList.size()]);
  }
  
  public Header getFirstHeader(String paramString)
  {
    int i = 0;
    while (i < headers.size())
    {
      Header localHeader = (Header)headers.get(i);
      if (localHeader.getName().equalsIgnoreCase(paramString)) {
        return localHeader;
      }
      i += 1;
    }
    return null;
  }
  
  public Header[] getHeaders(String paramString)
  {
    Object localObject1 = null;
    int i = 0;
    while (i < headers.size())
    {
      Header localHeader = (Header)headers.get(i);
      Object localObject2 = localObject1;
      if (localHeader.getName().equalsIgnoreCase(paramString))
      {
        localObject2 = localObject1;
        if (localObject1 == null) {
          localObject2 = new ArrayList();
        }
        ((List)localObject2).add(localHeader);
      }
      i += 1;
      localObject1 = localObject2;
    }
    if (localObject1 != null) {
      return (Header[])localObject1.toArray(new Header[localObject1.size()]);
    }
    return EMPTY;
  }
  
  public HeaderIterator iterator()
  {
    return new BasicListHeaderIterator(headers, null);
  }
  
  public HeaderIterator iterator(String paramString)
  {
    return new BasicListHeaderIterator(headers, paramString);
  }
  
  public void removeHeader(Header paramHeader)
  {
    if (paramHeader == null) {
      return;
    }
    headers.remove(paramHeader);
  }
  
  public void setHeaders(Header[] paramArrayOfHeader)
  {
    clear();
    if (paramArrayOfHeader == null) {
      return;
    }
    Collections.addAll(headers, paramArrayOfHeader);
  }
  
  public String toString()
  {
    return headers.toString();
  }
  
  public void updateHeader(Header paramHeader)
  {
    if (paramHeader == null) {
      return;
    }
    int i = 0;
    while (i < headers.size())
    {
      if (((Header)headers.get(i)).getName().equalsIgnoreCase(paramHeader.getName()))
      {
        headers.set(i, paramHeader);
        return;
      }
      i += 1;
    }
    headers.add(paramHeader);
  }
}
