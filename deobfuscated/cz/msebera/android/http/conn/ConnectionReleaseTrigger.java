package cz.msebera.android.http.conn;

public abstract interface ConnectionReleaseTrigger
{
  public abstract void abortConnection();
  
  public abstract void releaseConnection();
}
