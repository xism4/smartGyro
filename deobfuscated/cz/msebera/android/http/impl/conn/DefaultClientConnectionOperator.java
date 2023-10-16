package cz.msebera.android.http.impl.conn;

import c.a.a.a.i.c.h;
import cz.msebera.android.http.HttpConnection;
import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.conn.ClientConnectionOperator;
import cz.msebera.android.http.conn.ConnectTimeoutException;
import cz.msebera.android.http.conn.DnsResolver;
import cz.msebera.android.http.conn.HttpInetSocketAddress;
import cz.msebera.android.http.conn.OperatedClientConnection;
import cz.msebera.android.http.conn.scheme.Scheme;
import cz.msebera.android.http.conn.scheme.SchemeLayeredSocketFactory;
import cz.msebera.android.http.conn.scheme.SchemeRegistry;
import cz.msebera.android.http.conn.scheme.SchemeSocketFactory;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.Asserts;
import cz.msebera.android.http.util.HttpConnectionParams;
import cz.msebera.android.http.util.HttpParams;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

@Deprecated
public class DefaultClientConnectionOperator
  implements ClientConnectionOperator
{
  protected final DnsResolver dnsResolver;
  public HttpClientAndroidLog log = new HttpClientAndroidLog(h.class);
  protected final SchemeRegistry schemeRegistry;
  
  public DefaultClientConnectionOperator(SchemeRegistry paramSchemeRegistry)
  {
    Args.notNull(paramSchemeRegistry, "Scheme registry");
    schemeRegistry = paramSchemeRegistry;
    dnsResolver = new SystemDefaultDnsResolver();
  }
  
  private SchemeRegistry getSchemeRegistry(HttpContext paramHttpContext)
  {
    SchemeRegistry localSchemeRegistry = (SchemeRegistry)paramHttpContext.getAttribute("http.scheme-registry");
    paramHttpContext = localSchemeRegistry;
    if (localSchemeRegistry == null) {
      paramHttpContext = schemeRegistry;
    }
    return paramHttpContext;
  }
  
  public OperatedClientConnection createConnection()
  {
    return new DefaultClientConnection();
  }
  
  public void openConnection(OperatedClientConnection paramOperatedClientConnection, HttpHost paramHttpHost, InetAddress paramInetAddress, HttpContext paramHttpContext, HttpParams paramHttpParams)
  {
    Args.notNull(paramOperatedClientConnection, "Connection");
    Args.notNull(paramHttpHost, "Target host");
    Args.notNull(paramHttpParams, "HTTP parameters");
    boolean bool;
    if (!paramOperatedClientConnection.isOpen()) {
      bool = true;
    } else {
      bool = false;
    }
    Asserts.check(bool, "Connection must not be open");
    Object localObject1 = getSchemeRegistry(paramHttpContext).getScheme(paramHttpHost.getSchemeName());
    SchemeSocketFactory localSchemeSocketFactory = ((Scheme)localObject1).getSchemeSocketFactory();
    InetAddress[] arrayOfInetAddress = resolveHostname(paramHttpHost.getHostName());
    int k = ((Scheme)localObject1).resolvePort(paramHttpHost.getPort());
    int i = 0;
    for (;;)
    {
      Object localObject3 = paramInetAddress;
      if (i < arrayOfInetAddress.length)
      {
        Object localObject2 = arrayOfInetAddress[i];
        int j;
        if (i == arrayOfInetAddress.length - 1) {
          j = 1;
        } else {
          j = 0;
        }
        Socket localSocket = localSchemeSocketFactory.createSocket(paramHttpParams);
        localObject1 = localSocket;
        paramOperatedClientConnection.opening(localSocket, paramHttpHost);
        HttpInetSocketAddress localHttpInetSocketAddress = new HttpInetSocketAddress(paramHttpHost, (InetAddress)localObject2, k);
        localObject2 = null;
        if (localObject3 != null) {
          localObject2 = new InetSocketAddress((InetAddress)localObject3, 0);
        }
        if (log.isDebugEnabled())
        {
          localObject3 = log;
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Connecting to ");
          localStringBuilder.append(localHttpInetSocketAddress);
          ((HttpClientAndroidLog)localObject3).debug(localStringBuilder.toString());
        }
        try
        {
          localObject2 = localSchemeSocketFactory.connectSocket(localSocket, localHttpInetSocketAddress, (InetSocketAddress)localObject2, paramHttpParams);
          if (localSocket != localObject2)
          {
            paramOperatedClientConnection.opening((Socket)localObject2, paramHttpHost);
            localObject1 = localObject2;
          }
          prepareSocket((Socket)localObject1, paramHttpContext, paramHttpParams);
          paramOperatedClientConnection.openCompleted(localSchemeSocketFactory.isSecure((Socket)localObject1), paramHttpParams);
          return;
        }
        catch (ConnectTimeoutException localConnectTimeoutException)
        {
          if (j != 0) {
            throw localConnectTimeoutException;
          }
        }
        catch (ConnectException localConnectException)
        {
          HttpClientAndroidLog localHttpClientAndroidLog;
          if (j == 0)
          {
            if (log.isDebugEnabled())
            {
              localHttpClientAndroidLog = log;
              localObject2 = new StringBuilder();
              ((StringBuilder)localObject2).append("Connect to ");
              ((StringBuilder)localObject2).append(localHttpInetSocketAddress);
              ((StringBuilder)localObject2).append(" timed out. ");
              ((StringBuilder)localObject2).append("Connection will be retried using another IP address");
              localHttpClientAndroidLog.debug(((StringBuilder)localObject2).toString());
            }
            i += 1;
          }
          else
          {
            throw localHttpClientAndroidLog;
          }
        }
      }
    }
  }
  
  protected void prepareSocket(Socket paramSocket, HttpContext paramHttpContext, HttpParams paramHttpParams)
  {
    paramSocket.setTcpNoDelay(HttpConnectionParams.getTcpNoDelay(paramHttpParams));
    paramSocket.setSoTimeout(HttpConnectionParams.getSoTimeout(paramHttpParams));
    int i = HttpConnectionParams.getLinger(paramHttpParams);
    if (i >= 0)
    {
      boolean bool;
      if (i > 0) {
        bool = true;
      } else {
        bool = false;
      }
      paramSocket.setSoLinger(bool, i);
    }
  }
  
  protected InetAddress[] resolveHostname(String paramString)
  {
    return dnsResolver.resolve(paramString);
  }
  
  public void updateSecureConnection(OperatedClientConnection paramOperatedClientConnection, HttpHost paramHttpHost, HttpContext paramHttpContext, HttpParams paramHttpParams)
  {
    Args.notNull(paramOperatedClientConnection, "Connection");
    Args.notNull(paramHttpHost, "Target host");
    Args.notNull(paramHttpParams, "Parameters");
    Asserts.check(paramOperatedClientConnection.isOpen(), "Connection must be open");
    Object localObject = getSchemeRegistry(paramHttpContext).getScheme(paramHttpHost.getSchemeName());
    Asserts.check(((Scheme)localObject).getSchemeSocketFactory() instanceof SchemeLayeredSocketFactory, "Socket factory must implement SchemeLayeredSocketFactory");
    SchemeLayeredSocketFactory localSchemeLayeredSocketFactory = (SchemeLayeredSocketFactory)((Scheme)localObject).getSchemeSocketFactory();
    localObject = localSchemeLayeredSocketFactory.createLayeredSocket(paramOperatedClientConnection.getSocket(), paramHttpHost.getHostName(), ((Scheme)localObject).resolvePort(paramHttpHost.getPort()), paramHttpParams);
    prepareSocket((Socket)localObject, paramHttpContext, paramHttpParams);
    paramOperatedClientConnection.update((Socket)localObject, paramHttpHost, localSchemeLayeredSocketFactory.isSecure((Socket)localObject), paramHttpParams);
  }
}
