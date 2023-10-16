package cz.msebera.android.http.impl.cookie;

@Deprecated
public class OCFile
  extends BestMatchSpec
{
  public OCFile()
  {
    this(null, false);
  }
  
  public OCFile(String[] paramArrayOfString, boolean paramBoolean)
  {
    super(paramArrayOfString, paramBoolean);
  }
  
  public String toString()
  {
    return "best-match";
  }
}
