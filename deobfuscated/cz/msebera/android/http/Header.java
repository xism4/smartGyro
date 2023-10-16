package cz.msebera.android.http;

public abstract interface Header
{
  public abstract HeaderElement[] getElements();
  
  public abstract String getName();
  
  public abstract String getValue();
}
