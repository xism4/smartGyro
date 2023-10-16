package cz.msebera.android.http.execchain;

@Deprecated
public class SyncBasicHttpContext
  extends BasicHttpContext
{
  public SyncBasicHttpContext(HttpContext paramHttpContext)
  {
    super(paramHttpContext);
  }
  
  public Object getAttribute(String paramString)
  {
    try
    {
      paramString = super.getAttribute(paramString);
      return paramString;
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
  
  public void setAttribute(String paramString, Object paramObject)
  {
    try
    {
      super.setAttribute(paramString, paramObject);
      return;
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
}
