package cz.msebera.android.http.impl.client;

import c.a.a.a.i.b.d;
import c.a.a.a.o;
import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.auth.AuthScheme;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.client.AuthCache;
import cz.msebera.android.http.conn.SchemePortResolver;
import cz.msebera.android.http.conn.UnsupportedSchemeException;
import cz.msebera.android.http.impl.conn.DefaultSchemePortResolver;
import cz.msebera.android.http.mime.Args;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BasicAuthCache
  implements AuthCache
{
  public HttpClientAndroidLog log = new HttpClientAndroidLog(d.class);
  private final Map<o, byte[]> map = new ConcurrentHashMap();
  private final SchemePortResolver schemePortResolver;
  
  public BasicAuthCache()
  {
    this(null);
  }
  
  public BasicAuthCache(SchemePortResolver paramSchemePortResolver)
  {
    if (paramSchemePortResolver == null) {
      paramSchemePortResolver = DefaultSchemePortResolver.INSTANCE;
    }
    schemePortResolver = paramSchemePortResolver;
  }
  
  public AuthScheme get(HttpHost paramHttpHost)
  {
    Args.notNull(paramHttpHost, "HTTP host");
    paramHttpHost = (byte[])map.get(getKey(paramHttpHost));
    if (paramHttpHost != null) {
      try
      {
        paramHttpHost = new ByteArrayInputStream(paramHttpHost);
        paramHttpHost = new ObjectInputStream(paramHttpHost);
        Object localObject = paramHttpHost.readObject();
        localObject = (AuthScheme)localObject;
        paramHttpHost.close();
        return localObject;
      }
      catch (ClassNotFoundException paramHttpHost)
      {
        if (log.isWarnEnabled())
        {
          log.warn("Unexpected error while de-serializing auth scheme", paramHttpHost);
          return null;
        }
      }
      catch (IOException paramHttpHost)
      {
        if (log.isWarnEnabled()) {
          log.warn("Unexpected I/O error while de-serializing auth scheme", paramHttpHost);
        }
      }
    }
    return null;
  }
  
  protected HttpHost getKey(HttpHost paramHttpHost)
  {
    SchemePortResolver localSchemePortResolver;
    if (paramHttpHost.getPort() <= 0) {
      localSchemePortResolver = schemePortResolver;
    }
    try
    {
      int i = localSchemePortResolver.resolve(paramHttpHost);
      return new HttpHost(paramHttpHost.getHostName(), i, paramHttpHost.getSchemeName());
    }
    catch (UnsupportedSchemeException localUnsupportedSchemeException) {}
    return paramHttpHost;
    return paramHttpHost;
  }
  
  public void put(HttpHost paramHttpHost, AuthScheme paramAuthScheme)
  {
    Args.notNull(paramHttpHost, "HTTP host");
    if (paramAuthScheme == null) {
      return;
    }
    Object localObject;
    if ((paramAuthScheme instanceof Serializable))
    {
      try
      {
        localObject = new ByteArrayOutputStream();
        ObjectOutputStream localObjectOutputStream = new ObjectOutputStream((OutputStream)localObject);
        localObjectOutputStream.writeObject(paramAuthScheme);
        localObjectOutputStream.close();
        paramAuthScheme = map;
        paramAuthScheme.put(getKey(paramHttpHost), ((ByteArrayOutputStream)localObject).toByteArray());
        return;
      }
      catch (IOException paramHttpHost)
      {
        if (!log.isWarnEnabled()) {
          return;
        }
      }
      log.warn("Unexpected I/O error while serializing auth scheme", paramHttpHost);
    }
    else if (log.isDebugEnabled())
    {
      paramHttpHost = log;
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Auth scheme ");
      ((StringBuilder)localObject).append(paramAuthScheme.getClass());
      ((StringBuilder)localObject).append(" is not serializable");
      paramHttpHost.debug(((StringBuilder)localObject).toString());
    }
  }
  
  public void remove(HttpHost paramHttpHost)
  {
    Args.notNull(paramHttpHost, "HTTP host");
    map.remove(getKey(paramHttpHost));
  }
  
  public String toString()
  {
    return map.toString();
  }
}
