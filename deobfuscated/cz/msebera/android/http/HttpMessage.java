package cz.msebera.android.http;

import cz.msebera.android.http.util.HttpParams;

public abstract interface HttpMessage
{
  public abstract void addHeader(Header paramHeader);
  
  public abstract void addHeader(String paramString1, String paramString2);
  
  public abstract boolean containsHeader(String paramString);
  
  public abstract Header[] getAllHeaders();
  
  public abstract Header getFirstHeader(String paramString);
  
  public abstract Header[] getHeaders(String paramString);
  
  public abstract HttpParams getParams();
  
  public abstract ProtocolVersion getProtocolVersion();
  
  public abstract HeaderIterator headerIterator();
  
  public abstract HeaderIterator headerIterator(String paramString);
  
  public abstract void removeHeader(Header paramHeader);
  
  public abstract void removeHeaders(String paramString);
  
  public abstract void setHeader(String paramString1, String paramString2);
  
  public abstract void setHeaders(Header[] paramArrayOfHeader);
  
  public abstract void setParams(HttpParams paramHttpParams);
}
