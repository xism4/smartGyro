package cz.msebera.android.http.util;

@Deprecated
public abstract interface HttpParams
{
  public abstract boolean getBooleanParameter(String paramString, boolean paramBoolean);
  
  public abstract int getIntParameter(String paramString, int paramInt);
  
  public abstract long getLongParameter(String paramString, long paramLong);
  
  public abstract Object getParameter(String paramString);
  
  public abstract boolean isParameterFalse(String paramString);
  
  public abstract boolean isParameterTrue(String paramString);
  
  public abstract HttpParams setBooleanParameter(String paramString, boolean paramBoolean);
  
  public abstract HttpParams setIntParameter(String paramString, int paramInt);
  
  public abstract HttpParams setLongParameter(String paramString, long paramLong);
  
  public abstract HttpParams setParameter(String paramString, Object paramObject);
}
