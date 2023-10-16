package cz.msebera.android.http.protocol;

public abstract interface Lookup<I>
{
  public abstract Object lookup(String paramString);
}
