package cz.msebera.android.http.execchain;

import cz.msebera.android.http.mime.Args;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BasicHttpContext
  implements HttpContext
{
  private final Map<String, Object> map = new ConcurrentHashMap();
  private final HttpContext parentContext;
  
  public BasicHttpContext()
  {
    this(null);
  }
  
  public BasicHttpContext(HttpContext paramHttpContext)
  {
    parentContext = paramHttpContext;
  }
  
  public Object getAttribute(String paramString)
  {
    Args.notNull(paramString, "Id");
    Object localObject2 = map.get(paramString);
    Object localObject1 = localObject2;
    if (localObject2 == null)
    {
      HttpContext localHttpContext = parentContext;
      localObject1 = localObject2;
      if (localHttpContext != null) {
        localObject1 = localHttpContext.getAttribute(paramString);
      }
    }
    return localObject1;
  }
  
  public void setAttribute(String paramString, Object paramObject)
  {
    Args.notNull(paramString, "Id");
    if (paramObject != null)
    {
      map.put(paramString, paramObject);
      return;
    }
    map.remove(paramString);
  }
  
  public String toString()
  {
    return map.toString();
  }
}
