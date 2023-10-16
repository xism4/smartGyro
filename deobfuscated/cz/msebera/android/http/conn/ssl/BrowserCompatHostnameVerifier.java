package cz.msebera.android.http.conn.ssl;

@Deprecated
public class BrowserCompatHostnameVerifier
  extends AbstractVerifier
{
  public static final BrowserCompatHostnameVerifier INSTANCE = new BrowserCompatHostnameVerifier();
  
  public BrowserCompatHostnameVerifier() {}
  
  public final String toString()
  {
    return "BROWSER_COMPATIBLE";
  }
  
  public final void verify(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2)
  {
    verify(paramString, paramArrayOfString1, paramArrayOfString2, false);
  }
}
