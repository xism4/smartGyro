package cz.msebera.android.http.ssl;

import java.security.cert.X509Certificate;

public abstract interface TrustStrategy
{
  public abstract boolean isTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString);
}
