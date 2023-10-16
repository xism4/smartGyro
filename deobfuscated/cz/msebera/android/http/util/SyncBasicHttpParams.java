package cz.msebera.android.http.util;

@Deprecated
public class SyncBasicHttpParams
  extends BasicHttpParams
{
  public SyncBasicHttpParams() {}
  
  public Object clone()
  {
    try
    {
      Object localObject = super.clone();
      return localObject;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public Object getParameter(String paramString)
  {
    try
    {
      paramString = super.getParameter(paramString);
      return paramString;
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
  
  public HttpParams setParameter(String paramString, Object paramObject)
  {
    try
    {
      super.setParameter(paramString, paramObject);
      return this;
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
}
