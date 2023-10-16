package cz.msebera.android.http.io;

import c.a.a.a.q;
import cz.msebera.android.http.HttpMessage;

public abstract interface HttpMessageWriter<T extends q>
{
  public abstract void write(HttpMessage paramHttpMessage);
}
