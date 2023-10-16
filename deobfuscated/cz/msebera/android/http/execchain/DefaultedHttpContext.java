package cz.msebera.android.http.execchain;

import cz.msebera.android.http.mime.Args;

@Deprecated
public final class DefaultedHttpContext
  implements HttpContext
{
  private final HttpContext defaults;
  private final HttpContext local;
  
  public DefaultedHttpContext(HttpContext paramHttpContext1, HttpContext paramHttpContext2)
  {
    Args.notNull(paramHttpContext1, "HTTP context");
    local = ((HttpContext)paramHttpContext1);
    defaults = paramHttpContext2;
  }
  
  public Object getAttribute(String paramString)
  {
    Object localObject2 = local.getAttribute(paramString);
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = defaults.getAttribute(paramString);
    }
    return localObject1;
  }
  
  public void setAttribute(String paramString, Object paramObject)
  {
    local.setAttribute(paramString, paramObject);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[local: ");
    localStringBuilder.append(local);
    localStringBuilder.append("defaults: ");
    localStringBuilder.append(defaults);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}
