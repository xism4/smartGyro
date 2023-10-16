package cz.msebera.android.http;

import java.util.Iterator;

public abstract interface HeaderElementIterator
  extends Iterator<Object>
{
  public abstract boolean hasNext();
  
  public abstract HeaderElement nextElement();
}
