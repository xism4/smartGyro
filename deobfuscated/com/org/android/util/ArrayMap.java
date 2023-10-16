package com.org.android.util;

import a.a.c.f.f;
import a.a.c.f.i;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ArrayMap<K, V>
  extends i<K, V>
  implements Map<K, V>
{
  f<K, V> mCollections;
  
  public ArrayMap() {}
  
  public ArrayMap(int paramInt)
  {
    super(paramInt);
  }
  
  private MapCollections getCollection()
  {
    if (mCollections == null) {
      mCollections = new ArrayMap.1(this);
    }
    return mCollections;
  }
  
  public Set entrySet()
  {
    return getCollection().getEntrySet();
  }
  
  public Set keySet()
  {
    return getCollection().getKeySet();
  }
  
  public void putAll(Map paramMap)
  {
    clear(n + paramMap.size());
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      put(localEntry.getKey(), localEntry.getValue());
    }
  }
  
  public Collection values()
  {
    return getCollection().getValues();
  }
}
