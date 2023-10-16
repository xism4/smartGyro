package cz.msebera.android.http.impl.client;

import c.a.a.a.a.h;
import c.a.a.a.a.n;
import cz.msebera.android.http.auth.AuthScope;
import cz.msebera.android.http.auth.Credentials;
import cz.msebera.android.http.client.CredentialsProvider;
import cz.msebera.android.http.mime.Args;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class BasicCredentialsProvider
  implements CredentialsProvider
{
  private final ConcurrentHashMap<h, n> credMap = new ConcurrentHashMap();
  
  public BasicCredentialsProvider() {}
  
  private static Credentials matchCredentials(Map paramMap, AuthScope paramAuthScope)
  {
    Credentials localCredentials = (Credentials)paramMap.get(paramAuthScope);
    Object localObject2 = localCredentials;
    if (localCredentials == null)
    {
      int i = -1;
      Object localObject1 = null;
      Iterator localIterator = paramMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        localObject2 = (AuthScope)localIterator.next();
        int j = paramAuthScope.match((AuthScope)localObject2);
        if (j > i)
        {
          localObject1 = localObject2;
          i = j;
        }
      }
      localObject2 = localCredentials;
      if (localObject1 != null) {
        localObject2 = (Credentials)paramMap.get(localObject1);
      }
    }
    return localObject2;
  }
  
  public Credentials getCredentials(AuthScope paramAuthScope)
  {
    Args.notNull(paramAuthScope, "Authentication scope");
    return matchCredentials(credMap, paramAuthScope);
  }
  
  public String toString()
  {
    return credMap.toString();
  }
}
