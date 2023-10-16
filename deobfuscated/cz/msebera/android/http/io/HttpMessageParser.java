package cz.msebera.android.http.io;

import c.a.a.a.q;
import cz.msebera.android.http.HttpMessage;

public abstract interface HttpMessageParser<T extends q>
{
  public abstract HttpMessage parse();
}
