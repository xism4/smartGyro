package cz.msebera.android.http;

import java.util.Iterator;

public abstract interface HeaderIterator
  extends Iterator<Object>
{
  public abstract boolean hasNext();
  
  public abstract Header nextHeader();
}
