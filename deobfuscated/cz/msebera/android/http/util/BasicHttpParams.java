package cz.msebera.android.http.util;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Deprecated
public class BasicHttpParams
  extends AbstractHttpParams
  implements Serializable, Cloneable
{
  private final Map<String, Object> parameters = new ConcurrentHashMap();
  
  public BasicHttpParams() {}
  
  public Object clone()
  {
    BasicHttpParams localBasicHttpParams = (BasicHttpParams)super.clone();
    copyParams(localBasicHttpParams);
    return localBasicHttpParams;
  }
  
  public void copyParams(HttpParams paramHttpParams)
  {
    Iterator localIterator = parameters.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      paramHttpParams.setParameter((String)localEntry.getKey(), localEntry.getValue());
    }
  }
  
  public Object getParameter(String paramString)
  {
    return parameters.get(paramString);
  }
  
  public HttpParams setParameter(String paramString, Object paramObject)
  {
    if (paramString == null) {
      return this;
    }
    if (paramObject != null)
    {
      parameters.put(paramString, paramObject);
      return this;
    }
    parameters.remove(paramString);
    return this;
  }
}
