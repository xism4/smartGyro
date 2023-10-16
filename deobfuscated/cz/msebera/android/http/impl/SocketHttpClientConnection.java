package cz.msebera.android.http.impl;

import cz.msebera.android.http.HttpInetConnection;
import cz.msebera.android.http.impl.io.SocketInputBuffer;
import cz.msebera.android.http.impl.io.SocketOutputBuffer;
import cz.msebera.android.http.io.SessionInputBuffer;
import cz.msebera.android.http.io.SessionOutputBuffer;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.Asserts;
import cz.msebera.android.http.util.HttpParams;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;

@Deprecated
public class SocketHttpClientConnection
  extends AbstractHttpClientConnection
  implements HttpInetConnection
{
  private volatile boolean open;
  private volatile Socket socket = null;
  
  public SocketHttpClientConnection() {}
  
  private static void formatAddress(StringBuilder paramStringBuilder, SocketAddress paramSocketAddress)
  {
    if ((paramSocketAddress instanceof InetSocketAddress))
    {
      InetSocketAddress localInetSocketAddress = (InetSocketAddress)paramSocketAddress;
      if (localInetSocketAddress.getAddress() != null) {
        paramSocketAddress = localInetSocketAddress.getAddress().getHostAddress();
      } else {
        paramSocketAddress = localInetSocketAddress.getAddress();
      }
      paramStringBuilder.append(paramSocketAddress);
      paramStringBuilder.append(':');
      paramStringBuilder.append(localInetSocketAddress.getPort());
      return;
    }
    paramStringBuilder.append(paramSocketAddress);
  }
  
  protected void assertNotOpen()
  {
    Asserts.check(open ^ true, "Connection is already open");
  }
  
  protected void assertOpen()
  {
    Asserts.check(open, "Connection is not open");
  }
  
  protected void bind(Socket paramSocket, HttpParams paramHttpParams)
  {
    Args.notNull(paramSocket, "Socket");
    Args.notNull(paramHttpParams, "HTTP parameters");
    socket = paramSocket;
    int i = paramHttpParams.getIntParameter("http.socket.buffer-size", -1);
    init(createSessionInputBuffer(paramSocket, i, paramHttpParams), createSessionOutputBuffer(paramSocket, i, paramHttpParams), paramHttpParams);
    open = true;
  }
  
  /* Error */
  public void close()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 51	cz/msebera/android/http/impl/SocketHttpClientConnection:open	Z
    //   4: ifne +4 -> 8
    //   7: return
    //   8: aload_0
    //   9: iconst_0
    //   10: putfield 51	cz/msebera/android/http/impl/SocketHttpClientConnection:open	Z
    //   13: aload_0
    //   14: getfield 17	cz/msebera/android/http/impl/SocketHttpClientConnection:socket	Ljava/net/Socket;
    //   17: astore_1
    //   18: aload_0
    //   19: invokevirtual 104	cz/msebera/android/http/impl/AbstractHttpClientConnection:doFlush	()V
    //   22: aload_1
    //   23: invokevirtual 109	java/net/Socket:shutdownOutput	()V
    //   26: aload_1
    //   27: invokevirtual 112	java/net/Socket:shutdownInput	()V
    //   30: aload_1
    //   31: invokevirtual 114	java/net/Socket:close	()V
    //   34: return
    //   35: astore_2
    //   36: aload_1
    //   37: invokevirtual 114	java/net/Socket:close	()V
    //   40: aload_2
    //   41: athrow
    //   42: astore_2
    //   43: goto -17 -> 26
    //   46: astore_2
    //   47: goto -17 -> 30
    //   50: astore_2
    //   51: goto -21 -> 30
    //   54: astore_2
    //   55: goto -25 -> 30
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	58	0	this	SocketHttpClientConnection
    //   17	20	1	localSocket	Socket
    //   35	6	2	localThrowable	Throwable
    //   42	1	2	localIOException1	java.io.IOException
    //   46	1	2	localUnsupportedOperationException1	UnsupportedOperationException
    //   50	1	2	localIOException2	java.io.IOException
    //   54	1	2	localUnsupportedOperationException2	UnsupportedOperationException
    // Exception table:
    //   from	to	target	type
    //   18	22	35	java/lang/Throwable
    //   22	26	35	java/lang/Throwable
    //   26	30	35	java/lang/Throwable
    //   22	26	42	java/io/IOException
    //   22	26	46	java/lang/UnsupportedOperationException
    //   26	30	50	java/io/IOException
    //   26	30	54	java/lang/UnsupportedOperationException
  }
  
  protected SessionInputBuffer createSessionInputBuffer(Socket paramSocket, int paramInt, HttpParams paramHttpParams)
  {
    return new SocketInputBuffer(paramSocket, paramInt, paramHttpParams);
  }
  
  protected SessionOutputBuffer createSessionOutputBuffer(Socket paramSocket, int paramInt, HttpParams paramHttpParams)
  {
    return new SocketOutputBuffer(paramSocket, paramInt, paramHttpParams);
  }
  
  public InetAddress getRemoteAddress()
  {
    if (socket != null) {
      return socket.getInetAddress();
    }
    return null;
  }
  
  public int getRemotePort()
  {
    if (socket != null) {
      return socket.getPort();
    }
    return -1;
  }
  
  public boolean isOpen()
  {
    return open;
  }
  
  public void setSocketTimeout(int paramInt)
  {
    assertOpen();
    if (socket != null)
    {
      Socket localSocket = socket;
      try
      {
        localSocket.setSoTimeout(paramInt);
        return;
      }
      catch (SocketException localSocketException) {}
    }
  }
  
  public void shutdown()
  {
    open = false;
    Socket localSocket = socket;
    if (localSocket != null) {
      localSocket.close();
    }
  }
  
  public String toString()
  {
    if (socket != null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      SocketAddress localSocketAddress1 = socket.getRemoteSocketAddress();
      SocketAddress localSocketAddress2 = socket.getLocalSocketAddress();
      if ((localSocketAddress1 != null) && (localSocketAddress2 != null))
      {
        formatAddress(localStringBuilder, localSocketAddress2);
        localStringBuilder.append("<->");
        formatAddress(localStringBuilder, localSocketAddress1);
      }
      return localStringBuilder.toString();
    }
    return super.toString();
  }
}
