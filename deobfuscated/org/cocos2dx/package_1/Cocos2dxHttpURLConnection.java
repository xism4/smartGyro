package org.cocos2dx.package_1;

import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

public class Cocos2dxHttpURLConnection
{
  private static final String POST_METHOD = "POST";
  private static final String PUT_METHOD = "PUT";
  
  public Cocos2dxHttpURLConnection() {}
  
  static void addRequestHeader(HttpURLConnection paramHttpURLConnection, String paramString1, String paramString2)
  {
    paramHttpURLConnection.setRequestProperty(paramString1, paramString2);
  }
  
  public static String combinCookies(List paramList, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = paramList.iterator();
    Object localObject3 = "/";
    Object localObject2 = "FALSE";
    Object localObject5 = null;
    Object localObject4 = null;
    Object localObject1 = null;
    paramList = paramString;
    paramString = (String)localObject4;
    while (localIterator.hasNext())
    {
      String[] arrayOfString1 = ((String)localIterator.next()).split(";");
      int j = arrayOfString1.length;
      int i = 0;
      while (i < j)
      {
        localObject4 = arrayOfString1[i];
        int k = ((String)localObject4).indexOf("=");
        Object localObject6;
        Object localObject7;
        Object localObject8;
        List localList;
        String str;
        if (-1 == k)
        {
          localObject4 = localObject3;
          localObject6 = localObject2;
          localObject7 = localObject5;
          localObject8 = localObject1;
          localList = paramList;
          str = paramString;
        }
        else
        {
          String[] arrayOfString2 = new String[2];
          arrayOfString2[0] = ((String)localObject4).substring(0, k);
          arrayOfString2[1] = ((String)localObject4).substring(k + 1);
          if ("expires".equalsIgnoreCase(arrayOfString2[0].trim())) {
            localObject1 = str2Seconds(arrayOfString2[1].trim());
          }
          for (;;)
          {
            localObject4 = localObject3;
            localObject6 = localObject2;
            localObject7 = localObject5;
            localObject8 = localObject1;
            localList = paramList;
            str = paramString;
            break label381;
            if ("path".equalsIgnoreCase(arrayOfString2[0].trim()))
            {
              localObject3 = arrayOfString2[1];
            }
            else if ("secure".equalsIgnoreCase(arrayOfString2[0].trim()))
            {
              localObject2 = arrayOfString2[1];
            }
            else
            {
              if (!"domain".equalsIgnoreCase(arrayOfString2[0].trim())) {
                break;
              }
              paramList = arrayOfString2[1];
            }
          }
          localObject4 = localObject3;
          localObject6 = localObject2;
          localObject7 = localObject5;
          localObject8 = localObject1;
          localList = paramList;
          str = paramString;
          if (!"version".equalsIgnoreCase(arrayOfString2[0].trim())) {
            if ("max-age".equalsIgnoreCase(arrayOfString2[0].trim()))
            {
              localObject4 = localObject3;
              localObject6 = localObject2;
              localObject7 = localObject5;
              localObject8 = localObject1;
              localList = paramList;
              str = paramString;
            }
            else
            {
              localObject7 = arrayOfString2[0];
              str = arrayOfString2[1];
              localList = paramList;
              localObject8 = localObject1;
              localObject6 = localObject2;
              localObject4 = localObject3;
            }
          }
        }
        label381:
        i += 1;
        localObject3 = localObject4;
        localObject2 = localObject6;
        localObject5 = localObject7;
        localObject1 = localObject8;
        paramList = localList;
        paramString = str;
      }
      localObject4 = paramList;
      if (paramList == null) {
        localObject4 = "none";
      }
      localStringBuilder.append((String)localObject4);
      localStringBuilder.append('\t');
      localStringBuilder.append("FALSE");
      localStringBuilder.append('\t');
      localStringBuilder.append((String)localObject3);
      localStringBuilder.append('\t');
      localStringBuilder.append((String)localObject2);
      localStringBuilder.append('\t');
      localStringBuilder.append((String)localObject1);
      localStringBuilder.append("\t");
      localStringBuilder.append(localObject5);
      localStringBuilder.append("\t");
      localStringBuilder.append(paramString);
      localStringBuilder.append('\n');
      paramList = (List)localObject4;
    }
    return localStringBuilder.toString();
  }
  
  static int connect(HttpURLConnection paramHttpURLConnection)
  {
    try
    {
      paramHttpURLConnection.connect();
      return 0;
    }
    catch (IOException paramHttpURLConnection)
    {
      Log.e("cocos2d-x debug info", "come in connect");
      Log.e("cocos2d-x debug info", paramHttpURLConnection.toString());
    }
    return 1;
  }
  
  static HttpURLConnection createHttpURLConnection(String paramString)
  {
    try
    {
      paramString = new URL(paramString).openConnection();
      paramString = (HttpURLConnection)paramString;
      paramString.setRequestProperty("Accept-Encoding", "identity");
      paramString.setDoInput(true);
      return paramString;
    }
    catch (Exception paramString)
    {
      Log.e("URLConnection exception", paramString.toString());
    }
    return null;
  }
  
  static void disconnect(HttpURLConnection paramHttpURLConnection)
  {
    paramHttpURLConnection.disconnect();
  }
  
  static int getResponseCode(HttpURLConnection paramHttpURLConnection)
  {
    try
    {
      int i = paramHttpURLConnection.getResponseCode();
      return i;
    }
    catch (IOException paramHttpURLConnection)
    {
      Log.e("URLConnection exception", paramHttpURLConnection.toString());
    }
    return 0;
  }
  
  static byte[] getResponseContent(HttpURLConnection paramHttpURLConnection)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a6 = a5\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n");
  }
  
  static String getResponseHeaderByIdx(HttpURLConnection paramHttpURLConnection, int paramInt)
  {
    paramHttpURLConnection = paramHttpURLConnection.getHeaderFields();
    if (paramHttpURLConnection == null) {
      return null;
    }
    int i = 0;
    Object localObject = paramHttpURLConnection.entrySet().iterator();
    while (((Iterator)localObject).hasNext())
    {
      paramHttpURLConnection = (Map.Entry)((Iterator)localObject).next();
      if (i == paramInt)
      {
        localObject = (String)paramHttpURLConnection.getKey();
        if (localObject == null)
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append(listToString((List)paramHttpURLConnection.getValue(), ","));
          ((StringBuilder)localObject).append("\n");
          return ((StringBuilder)localObject).toString();
        }
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append((String)localObject);
        localStringBuilder.append(":");
        localStringBuilder.append(listToString((List)paramHttpURLConnection.getValue(), ","));
        localStringBuilder.append("\n");
        return localStringBuilder.toString();
      }
      i += 1;
    }
    return null;
  }
  
  static String getResponseHeaderByKey(HttpURLConnection paramHttpURLConnection, String paramString)
  {
    if (paramString == null) {
      return null;
    }
    Object localObject = paramHttpURLConnection.getHeaderFields();
    if (localObject == null) {
      return null;
    }
    localObject = ((Map)localObject).entrySet().iterator();
    while (((Iterator)localObject).hasNext())
    {
      Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
      if (paramString.equalsIgnoreCase((String)localEntry.getKey()))
      {
        if ("set-cookie".equalsIgnoreCase(paramString)) {
          return combinCookies((List)localEntry.getValue(), paramHttpURLConnection.getURL().getHost());
        }
        return listToString((List)localEntry.getValue(), ",");
      }
    }
    return null;
  }
  
  static int getResponseHeaderByKeyInt(HttpURLConnection paramHttpURLConnection, String paramString)
  {
    paramHttpURLConnection = paramHttpURLConnection.getHeaderField(paramString);
    if (paramHttpURLConnection == null) {
      return 0;
    }
    return Integer.parseInt(paramHttpURLConnection);
  }
  
  static String getResponseHeaders(HttpURLConnection paramHttpURLConnection)
  {
    paramHttpURLConnection = paramHttpURLConnection.getHeaderFields();
    if (paramHttpURLConnection == null) {
      return null;
    }
    Iterator localIterator = paramHttpURLConnection.entrySet().iterator();
    paramHttpURLConnection = "";
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      Object localObject = (String)localEntry.getKey();
      if (localObject == null)
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append(paramHttpURLConnection);
        ((StringBuilder)localObject).append(listToString((List)localEntry.getValue(), ","));
        ((StringBuilder)localObject).append("\n");
        paramHttpURLConnection = ((StringBuilder)localObject).toString();
      }
      else
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramHttpURLConnection);
        localStringBuilder.append((String)localObject);
        localStringBuilder.append(":");
        localStringBuilder.append(listToString((List)localEntry.getValue(), ","));
        localStringBuilder.append("\n");
        paramHttpURLConnection = localStringBuilder.toString();
      }
    }
    return paramHttpURLConnection;
  }
  
  static String getResponseMessage(HttpURLConnection paramHttpURLConnection)
  {
    try
    {
      paramHttpURLConnection = paramHttpURLConnection.getResponseMessage();
      return paramHttpURLConnection;
    }
    catch (IOException paramHttpURLConnection)
    {
      paramHttpURLConnection = paramHttpURLConnection.toString();
      Log.e("URLConnection exception", paramHttpURLConnection);
    }
    return paramHttpURLConnection;
  }
  
  public static String listToString(List paramList, String paramString)
  {
    if (paramList == null) {
      return null;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (i != 0) {
        localStringBuilder.append(paramString);
      }
      paramList = str;
      if (str == null) {
        paramList = "";
      }
      localStringBuilder.append(paramList);
      i = 1;
    }
    return localStringBuilder.toString();
  }
  
  static void sendRequest(HttpURLConnection paramHttpURLConnection, byte[] paramArrayOfByte)
  {
    try
    {
      paramHttpURLConnection = paramHttpURLConnection.getOutputStream();
      if (paramArrayOfByte != null)
      {
        paramHttpURLConnection.write(paramArrayOfByte);
        paramHttpURLConnection.flush();
      }
      paramHttpURLConnection.close();
      return;
    }
    catch (IOException paramHttpURLConnection)
    {
      Log.e("URLConnection exception", paramHttpURLConnection.toString());
    }
  }
  
  static void setReadAndConnectTimeout(HttpURLConnection paramHttpURLConnection, int paramInt1, int paramInt2)
  {
    paramHttpURLConnection.setReadTimeout(paramInt1);
    paramHttpURLConnection.setConnectTimeout(paramInt2);
  }
  
  static void setRequestMethod(HttpURLConnection paramHttpURLConnection, String paramString)
  {
    try
    {
      paramHttpURLConnection.setRequestMethod(paramString);
      boolean bool = paramString.equalsIgnoreCase("POST");
      if (!bool)
      {
        bool = paramString.equalsIgnoreCase("PUT");
        if (!bool) {}
      }
      else
      {
        paramHttpURLConnection.setDoOutput(true);
        return;
      }
    }
    catch (ProtocolException paramHttpURLConnection)
    {
      Log.e("URLConnection exception", paramHttpURLConnection.toString());
    }
  }
  
  static void setVerifySSL(HttpURLConnection paramHttpURLConnection, String paramString)
  {
    if (!(paramHttpURLConnection instanceof HttpsURLConnection)) {
      return;
    }
    HttpsURLConnection localHttpsURLConnection = (HttpsURLConnection)paramHttpURLConnection;
    try
    {
      boolean bool = paramString.startsWith("/");
      if (bool)
      {
        paramHttpURLConnection = new BufferedInputStream(new FileInputStream(paramString));
      }
      else
      {
        paramHttpURLConnection = paramString.substring(7);
        paramHttpURLConnection = new BufferedInputStream(Cocos2dxHelper.getActivity().getAssets().open(paramHttpURLConnection));
      }
      paramString = CertificateFactory.getInstance("X.509").generateCertificate(paramHttpURLConnection);
      PrintStream localPrintStream = System.out;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("ca=");
      X509Certificate localX509Certificate = (X509Certificate)paramString;
      localStringBuilder.append(localX509Certificate.getSubjectDN());
      localPrintStream.println(localStringBuilder.toString());
      paramHttpURLConnection.close();
      paramHttpURLConnection = KeyStore.getInstance(KeyStore.getDefaultType());
      paramHttpURLConnection.load(null, null);
      paramHttpURLConnection.setCertificateEntry("ca", paramString);
      paramString = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
      paramString.init(paramHttpURLConnection);
      paramHttpURLConnection = SSLContext.getInstance("TLS");
      paramHttpURLConnection.init(null, paramString.getTrustManagers(), null);
      localHttpsURLConnection.setSSLSocketFactory(paramHttpURLConnection.getSocketFactory());
      return;
    }
    catch (Exception paramHttpURLConnection)
    {
      Log.e("URLConnection exception", paramHttpURLConnection.toString());
    }
  }
  
  private static String str2Seconds(String paramString)
  {
    Calendar localCalendar = Calendar.getInstance();
    Locale localLocale = Locale.US;
    long l;
    try
    {
      localCalendar.setTime(new SimpleDateFormat("EEE, dd-MMM-yy hh:mm:ss zzz", localLocale).parse(paramString));
      l = localCalendar.getTimeInMillis();
      l /= 1000L;
    }
    catch (ParseException paramString)
    {
      Log.e("URLConnection exception", paramString.toString());
      l = 0L;
    }
    return Long.toString(l);
  }
}
