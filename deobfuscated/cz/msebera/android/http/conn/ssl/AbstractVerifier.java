package cz.msebera.android.http.conn.ssl;

import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.conn.socket.InetAddressUtils;
import cz.msebera.android.http.mime.Args;
import java.io.InputStream;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

@Deprecated
public abstract class AbstractVerifier
  implements X509HostnameVerifier
{
  static final String[] BAD_COUNTRY_2LDS = { "ac", "co", "com", "ed", "edu", "go", "gouv", "gov", "info", "lg", "ne", "net", "or", "org" };
  public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());
  
  static
  {
    Arrays.sort(BAD_COUNTRY_2LDS);
  }
  
  public AbstractVerifier() {}
  
  public static int countDots(String paramString)
  {
    int i = 0;
    int k;
    for (int j = 0; i < paramString.length(); j = k)
    {
      k = j;
      if (paramString.charAt(i) == '.') {
        k = j + 1;
      }
      i += 1;
    }
    return j;
  }
  
  private static boolean validCountryWildcard(String[] paramArrayOfString)
  {
    if (paramArrayOfString.length == 3)
    {
      if (paramArrayOfString[2].length() != 2) {
        return true;
      }
      return Arrays.binarySearch(BAD_COUNTRY_2LDS, paramArrayOfString[1]) < 0;
    }
    return true;
  }
  
  private static boolean verify(String paramString1, String paramString2, boolean paramBoolean)
  {
    if (paramString1 == null) {
      return false;
    }
    paramString1 = paramString1.toLowerCase(Locale.ROOT);
    paramString2 = paramString2.toLowerCase(Locale.ROOT);
    Object localObject = paramString2.split("\\.");
    int i;
    if ((localObject.length >= 3) && (localObject[0].endsWith("*")) && ((!paramBoolean) || (validCountryWildcard((String[])localObject)))) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0)
    {
      String str1 = localObject[0];
      boolean bool;
      if (str1.length() > 1)
      {
        localObject = str1.substring(0, str1.length() - 1);
        str1 = paramString2.substring(str1.length());
        String str2 = paramString1.substring(((String)localObject).length());
        if ((paramString1.startsWith((String)localObject)) && (str2.endsWith(str1))) {
          bool = true;
        } else {
          bool = false;
        }
      }
      else
      {
        bool = paramString1.endsWith(paramString2.substring(1));
      }
      if ((bool) && ((!paramBoolean) || (countDots(paramString1) == countDots(paramString2)))) {
        return true;
      }
    }
    else
    {
      return paramString1.equals(paramString2);
    }
    return false;
  }
  
  public final void verify(String paramString, X509Certificate paramX509Certificate)
  {
    boolean bool1 = InetAddressUtils.isIPv4Address(paramString);
    boolean bool2 = InetAddressUtils.isIPv6Address(paramString);
    int i;
    if ((!bool1) && (!bool2)) {
      i = 2;
    } else {
      i = 7;
    }
    List localList = DefaultHostnameVerifier.getSubjectAltNames(paramX509Certificate, i);
    Object localObject1 = new DistinguishedNameParser(paramX509Certificate.getSubjectX500Principal()).findMostSpecific("cn");
    Object localObject2 = null;
    if (localObject1 != null)
    {
      paramX509Certificate = new String[1];
      paramX509Certificate[0] = localObject1;
    }
    else
    {
      paramX509Certificate = null;
    }
    localObject1 = localObject2;
    if (localList != null)
    {
      localObject1 = localObject2;
      if (!localList.isEmpty()) {
        localObject1 = (String[])localList.toArray(new String[localList.size()]);
      }
    }
    verify(paramString, paramX509Certificate, (String[])localObject1);
  }
  
  public final void verify(String paramString, SSLSocket paramSSLSocket)
  {
    Args.notNull(paramString, "Host");
    SSLSession localSSLSession2 = paramSSLSocket.getSession();
    SSLSession localSSLSession1 = localSSLSession2;
    if (localSSLSession2 == null)
    {
      paramSSLSocket.getInputStream().available();
      localSSLSession2 = paramSSLSocket.getSession();
      localSSLSession1 = localSSLSession2;
      if (localSSLSession2 == null)
      {
        paramSSLSocket.startHandshake();
        localSSLSession1 = paramSSLSocket.getSession();
      }
    }
    verify(paramString, (X509Certificate)localSSLSession1.getPeerCertificates()[0]);
  }
  
  public final void verify(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2, boolean paramBoolean)
  {
    String str = null;
    if ((paramArrayOfString1 != null) && (paramArrayOfString1.length > 0)) {
      paramArrayOfString1 = paramArrayOfString1[0];
    } else {
      paramArrayOfString1 = null;
    }
    Object localObject = str;
    if (paramArrayOfString2 != null)
    {
      localObject = str;
      if (paramArrayOfString2.length > 0) {
        localObject = Arrays.asList(paramArrayOfString2);
      }
    }
    if (InetAddressUtils.isIPv6Address(paramString)) {
      paramArrayOfString2 = DefaultHostnameVerifier.normaliseAddress(paramString.toLowerCase(Locale.ROOT));
    } else {
      paramArrayOfString2 = paramString;
    }
    if (localObject != null)
    {
      Iterator localIterator = ((List)localObject).iterator();
      while (localIterator.hasNext())
      {
        str = (String)localIterator.next();
        paramArrayOfString1 = str;
        if (InetAddressUtils.isIPv6Address(str)) {
          paramArrayOfString1 = DefaultHostnameVerifier.normaliseAddress(str);
        }
        if (verify(paramArrayOfString2, paramArrayOfString1, paramBoolean)) {
          return;
        }
      }
      paramArrayOfString1 = new StringBuilder();
      paramArrayOfString1.append("Certificate for <");
      paramArrayOfString1.append(paramString);
      paramArrayOfString1.append("> doesn't match any ");
      paramArrayOfString1.append("of the subject alternative names: ");
      paramArrayOfString1.append(localObject);
      throw new SSLException(paramArrayOfString1.toString());
    }
    if (paramArrayOfString1 != null)
    {
      if (InetAddressUtils.isIPv6Address(paramArrayOfString1)) {
        localObject = DefaultHostnameVerifier.normaliseAddress(paramArrayOfString1);
      } else {
        localObject = paramArrayOfString1;
      }
      if (verify(paramArrayOfString2, (String)localObject, paramBoolean)) {
        return;
      }
      paramArrayOfString2 = new StringBuilder();
      paramArrayOfString2.append("Certificate for <");
      paramArrayOfString2.append(paramString);
      paramArrayOfString2.append("> doesn't match ");
      paramArrayOfString2.append("common name of the certificate subject: ");
      paramArrayOfString2.append(paramArrayOfString1);
      throw new SSLException(paramArrayOfString2.toString());
    }
    paramArrayOfString1 = new StringBuilder();
    paramArrayOfString1.append("Certificate subject for <");
    paramArrayOfString1.append(paramString);
    paramArrayOfString1.append("> doesn't contain ");
    paramArrayOfString1.append("a common name and does not have alternative names");
    paramString = new SSLException(paramArrayOfString1.toString());
    throw paramString;
  }
  
  public final boolean verify(String paramString, SSLSession paramSSLSession)
  {
    try
    {
      paramSSLSession = paramSSLSession.getPeerCertificates();
      paramSSLSession = (X509Certificate)paramSSLSession[0];
      verify(paramString, paramSSLSession);
      return true;
    }
    catch (SSLException paramString)
    {
      if (log.isDebugEnabled()) {
        log.debug(paramString.getMessage(), paramString);
      }
    }
    return false;
  }
}
