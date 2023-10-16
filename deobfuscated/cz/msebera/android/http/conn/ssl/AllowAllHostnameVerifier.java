package cz.msebera.android.http.conn.ssl;

@Deprecated
public class AllowAllHostnameVerifier
  extends AbstractVerifier
{
  public static final AllowAllHostnameVerifier INSTANCE = new AllowAllHostnameVerifier();
  
  public AllowAllHostnameVerifier() {}
  
  public final String toString()
  {
    return "STRICT";
  }
  
  public final void verify(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2)
  {
    verify(paramString, paramArrayOfString1, paramArrayOfString2, true);
  }
}
