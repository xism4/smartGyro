package cz.msebera.android.http.util;

@Deprecated
public abstract class AbstractHttpParams
  implements HttpParams, HttpParamsNames
{
  protected AbstractHttpParams() {}
  
  public boolean getBooleanParameter(String paramString, boolean paramBoolean)
  {
    paramString = getParameter(paramString);
    if (paramString == null) {
      return paramBoolean;
    }
    return ((Boolean)paramString).booleanValue();
  }
  
  public int getIntParameter(String paramString, int paramInt)
  {
    paramString = getParameter(paramString);
    if (paramString == null) {
      return paramInt;
    }
    return ((Integer)paramString).intValue();
  }
  
  public long getLongParameter(String paramString, long paramLong)
  {
    paramString = getParameter(paramString);
    if (paramString == null) {
      return paramLong;
    }
    return ((Long)paramString).longValue();
  }
  
  public boolean isParameterFalse(String paramString)
  {
    return getBooleanParameter(paramString, false) ^ true;
  }
  
  public boolean isParameterTrue(String paramString)
  {
    return getBooleanParameter(paramString, false);
  }
  
  public HttpParams setBooleanParameter(String paramString, boolean paramBoolean)
  {
    Boolean localBoolean;
    if (paramBoolean) {
      localBoolean = Boolean.TRUE;
    } else {
      localBoolean = Boolean.FALSE;
    }
    setParameter(paramString, localBoolean);
    return this;
  }
  
  public HttpParams setIntParameter(String paramString, int paramInt)
  {
    setParameter(paramString, Integer.valueOf(paramInt));
    return this;
  }
  
  public HttpParams setLongParameter(String paramString, long paramLong)
  {
    setParameter(paramString, Long.valueOf(paramLong));
    return this;
  }
}
