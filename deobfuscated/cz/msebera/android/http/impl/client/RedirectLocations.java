package cz.msebera.android.http.impl.client;

import java.net.URI;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RedirectLocations
  extends AbstractList<Object>
{
  private final List<URI> all = new ArrayList();
  private final Set<URI> unique = new HashSet();
  
  public RedirectLocations() {}
  
  public void add(int paramInt, Object paramObject)
  {
    List localList = all;
    paramObject = (URI)paramObject;
    localList.add(paramInt, paramObject);
    unique.add(paramObject);
  }
  
  public void add(URI paramURI)
  {
    unique.add(paramURI);
    all.add(paramURI);
  }
  
  public boolean contains(Object paramObject)
  {
    return unique.contains(paramObject);
  }
  
  public boolean contains(URI paramURI)
  {
    return unique.contains(paramURI);
  }
  
  public URI get(int paramInt)
  {
    return (URI)all.get(paramInt);
  }
  
  public URI remove(int paramInt)
  {
    URI localURI = (URI)all.remove(paramInt);
    unique.remove(localURI);
    if (all.size() != unique.size()) {
      unique.addAll(all);
    }
    return localURI;
  }
  
  public Object set(int paramInt, Object paramObject)
  {
    Object localObject = all;
    paramObject = (URI)paramObject;
    localObject = (URI)((List)localObject).set(paramInt, paramObject);
    unique.remove(localObject);
    unique.add(paramObject);
    if (all.size() != unique.size()) {
      unique.addAll(all);
    }
    return localObject;
  }
  
  public int size()
  {
    return all.size();
  }
}
