package cz.msebera.android.http.impl.cookie;

import c.a.a.a.f.d;
import cz.msebera.android.http.cookie.CookieAttributeHandler;
import cz.msebera.android.http.cookie.CookieSpec;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractCookieSpec
  implements CookieSpec
{
  private final Map<String, d> attribHandlerMap;
  
  public AbstractCookieSpec()
  {
    attribHandlerMap = new ConcurrentHashMap(10);
  }
  
  protected AbstractCookieSpec(cz.msebera.android.http.cookie.Object... paramVarArgs)
  {
    attribHandlerMap = new ConcurrentHashMap(paramVarArgs.length);
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      cz.msebera.android.http.cookie.Object localObject = paramVarArgs[i];
      attribHandlerMap.put(localObject.getAttributeName(), localObject);
      i += 1;
    }
  }
  
  protected CookieAttributeHandler findAttribHandler(String paramString)
  {
    return (CookieAttributeHandler)attribHandlerMap.get(paramString);
  }
  
  protected Collection getAttribHandlers()
  {
    return attribHandlerMap.values();
  }
}
