package cz.msebera.android.http.conn.scheme;

import c.a.a.a.e.c.e;
import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.mime.Args;
import java.util.concurrent.ConcurrentHashMap;

@Deprecated
public final class SchemeRegistry
{
  private final ConcurrentHashMap<String, e> registeredSchemes = new ConcurrentHashMap();
  
  public SchemeRegistry() {}
  
  public final Scheme get(String paramString)
  {
    Args.notNull(paramString, "Scheme name");
    return (Scheme)registeredSchemes.get(paramString);
  }
  
  public final Scheme getScheme(HttpHost paramHttpHost)
  {
    Args.notNull(paramHttpHost, "Host");
    return getScheme(paramHttpHost.getSchemeName());
  }
  
  public final Scheme getScheme(String paramString)
  {
    Object localObject = get(paramString);
    if (localObject != null) {
      return localObject;
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Scheme '");
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append("' not registered.");
    throw new IllegalStateException(((StringBuilder)localObject).toString());
  }
  
  public final Scheme register(Scheme paramScheme)
  {
    Args.notNull(paramScheme, "Scheme");
    return (Scheme)registeredSchemes.put(paramScheme.getName(), paramScheme);
  }
}
