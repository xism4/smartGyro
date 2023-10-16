package cz.msebera.android.http.message;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HeaderIterator;
import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.util.BasicHttpParams;
import cz.msebera.android.http.util.HttpParams;
import java.util.Iterator;

public abstract class AbstractHttpMessage
  implements HttpMessage
{
  protected HeaderGroup headergroup = new HeaderGroup();
  @Deprecated
  protected HttpParams params;
  
  protected AbstractHttpMessage()
  {
    this(null);
  }
  
  protected AbstractHttpMessage(HttpParams paramHttpParams)
  {
    params = paramHttpParams;
  }
  
  public void addHeader(Header paramHeader)
  {
    headergroup.addHeader(paramHeader);
  }
  
  public void addHeader(String paramString1, String paramString2)
  {
    Args.notNull(paramString1, "Header name");
    headergroup.addHeader(new BasicHeader(paramString1, paramString2));
  }
  
  public boolean containsHeader(String paramString)
  {
    return headergroup.containsHeader(paramString);
  }
  
  public Header[] getAllHeaders()
  {
    return headergroup.getAllHeaders();
  }
  
  public Header getFirstHeader(String paramString)
  {
    return headergroup.getFirstHeader(paramString);
  }
  
  public Header[] getHeaders(String paramString)
  {
    return headergroup.getHeaders(paramString);
  }
  
  public HttpParams getParams()
  {
    if (params == null) {
      params = new BasicHttpParams();
    }
    return params;
  }
  
  public HeaderIterator headerIterator()
  {
    return headergroup.iterator();
  }
  
  public HeaderIterator headerIterator(String paramString)
  {
    return headergroup.iterator(paramString);
  }
  
  public void removeHeader(Header paramHeader)
  {
    headergroup.removeHeader(paramHeader);
  }
  
  public void removeHeaders(String paramString)
  {
    if (paramString == null) {
      return;
    }
    HeaderIterator localHeaderIterator = headergroup.iterator();
    while (localHeaderIterator.hasNext()) {
      if (paramString.equalsIgnoreCase(localHeaderIterator.nextHeader().getName())) {
        localHeaderIterator.remove();
      }
    }
  }
  
  public void setHeader(String paramString1, String paramString2)
  {
    Args.notNull(paramString1, "Header name");
    headergroup.updateHeader(new BasicHeader(paramString1, paramString2));
  }
  
  public void setHeaders(Header[] paramArrayOfHeader)
  {
    headergroup.setHeaders(paramArrayOfHeader);
  }
  
  public void setParams(HttpParams paramHttpParams)
  {
    Args.notNull(paramHttpParams, "HTTP parameters");
    params = ((HttpParams)paramHttpParams);
  }
}
