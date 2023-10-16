package cz.msebera.android.http.cookie;

public abstract interface ClientCookie
  extends Cookie
{
  public abstract boolean containsAttribute(String paramString);
  
  public abstract String getAttribute(String paramString);
}
