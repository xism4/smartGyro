package cz.msebera.android.http.impl.io;

import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.util.HttpParams;
import java.net.Socket;

@Deprecated
public class SocketOutputBuffer
  extends AbstractSessionOutputBuffer
{
  public SocketOutputBuffer(Socket paramSocket, int paramInt, HttpParams paramHttpParams)
  {
    Args.notNull(paramSocket, "Socket");
    int i = paramInt;
    if (paramInt < 0) {
      i = paramSocket.getSendBufferSize();
    }
    paramInt = i;
    if (i < 1024) {
      paramInt = 1024;
    }
    init(paramSocket.getOutputStream(), paramInt, paramHttpParams);
  }
}
