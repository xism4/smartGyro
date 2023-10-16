package cz.msebera.android.http.execchain;

public abstract interface HttpContext
{
  public abstract Object getAttribute(String paramString);
  
  public abstract void setAttribute(String paramString, Object paramObject);
}
