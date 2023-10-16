package cz.msebera.android.http.conn.ssl;

@Deprecated
public class StrictHostnameVerifier
  extends AbstractVerifier
{
  public static final StrictHostnameVerifier INSTANCE = new StrictHostnameVerifier();
  
  public StrictHostnameVerifier() {}
  
  public final String toString()
  {
    return "ALLOW_ALL";
  }
  
  public final void verify(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2) {}
}
