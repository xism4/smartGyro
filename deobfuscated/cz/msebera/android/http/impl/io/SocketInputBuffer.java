package cz.msebera.android.http.impl.io;

import cz.msebera.android.http.io.EofSensor;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.util.HttpParams;
import java.net.Socket;

@Deprecated
public class SocketInputBuffer
  extends AbstractSessionInputBuffer
  implements EofSensor
{
  private boolean eof;
  private final Socket socket;
  
  public SocketInputBuffer(Socket paramSocket, int paramInt, HttpParams paramHttpParams)
  {
    Args.notNull(paramSocket, "Socket");
    socket = paramSocket;
    eof = false;
    int i = paramInt;
    if (paramInt < 0) {
      i = paramSocket.getReceiveBufferSize();
    }
    paramInt = i;
    if (i < 1024) {
      paramInt = 1024;
    }
    init(paramSocket.getInputStream(), paramInt, paramHttpParams);
  }
  
  protected int fillBuffer()
  {
    int i = super.fillBuffer();
    boolean bool;
    if (i == -1) {
      bool = true;
    } else {
      bool = false;
    }
    eof = bool;
    return i;
  }
  
  public boolean isDataAvailable(int paramInt)
  {
    boolean bool = hasBufferedData();
    if (!bool)
    {
      int i = socket.getSoTimeout();
      try
      {
        socket.setSoTimeout(paramInt);
        fillBuffer();
        bool = hasBufferedData();
        socket.setSoTimeout(i);
        return bool;
      }
      catch (Throwable localThrowable)
      {
        socket.setSoTimeout(i);
        throw localThrowable;
      }
    }
    return bool;
  }
  
  public boolean isEof()
  {
    return eof;
  }
}
