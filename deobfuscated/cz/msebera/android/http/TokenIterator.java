package cz.msebera.android.http;

import java.util.Iterator;

public abstract interface TokenIterator
  extends Iterator<Object>
{
  public abstract boolean hasNext();
  
  public abstract String nextToken();
}
