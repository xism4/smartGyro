package cz.msebera.android.http.conn.ssl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.HostnameVerifier;

public final class DefaultHostnameVerifier
  implements HostnameVerifier
{
  static List getSubjectAltNames(X509Certificate paramX509Certificate, int paramInt)
  {
    Object localObject = null;
    try
    {
      paramX509Certificate = paramX509Certificate.getSubjectAlternativeNames();
    }
    catch (CertificateParsingException paramX509Certificate)
    {
      Iterator localIterator;
      for (;;) {}
    }
    paramX509Certificate = null;
    if (paramX509Certificate != null)
    {
      localIterator = paramX509Certificate.iterator();
      paramX509Certificate = (X509Certificate)localObject;
      while (localIterator.hasNext())
      {
        localObject = (List)localIterator.next();
        if (((Integer)((List)localObject).get(0)).intValue() == paramInt)
        {
          String str = (String)((List)localObject).get(1);
          localObject = paramX509Certificate;
          if (paramX509Certificate == null) {
            localObject = new ArrayList();
          }
          ((List)localObject).add(str);
          paramX509Certificate = (X509Certificate)localObject;
        }
      }
    }
    return null;
    return paramX509Certificate;
  }
  
  static String normaliseAddress(String paramString)
  {
    if (paramString == null) {
      return paramString;
    }
    try
    {
      String str = InetAddress.getByName(paramString).getHostAddress();
      return str;
    }
    catch (UnknownHostException localUnknownHostException) {}
    return paramString;
  }
}
