package cz.msebera.android.http.impl.conn;

import cz.msebera.android.http.Consts;
import cz.msebera.android.http.io.EofSensor;
import cz.msebera.android.http.io.HttpTransportMetrics;
import cz.msebera.android.http.io.SessionInputBuffer;
import cz.msebera.android.http.mime.CharArrayBuffer;
import java.nio.charset.Charset;

@Deprecated
public class LoggingSessionInputBuffer
  implements SessionInputBuffer, EofSensor
{
  private final String charset;
  private final EofSensor eofSensor;
  private final SessionInputBuffer in;
  private final Wire wire;
  
  public LoggingSessionInputBuffer(SessionInputBuffer paramSessionInputBuffer, Wire paramWire, String paramString)
  {
    in = paramSessionInputBuffer;
    if ((paramSessionInputBuffer instanceof EofSensor)) {
      paramSessionInputBuffer = (EofSensor)paramSessionInputBuffer;
    } else {
      paramSessionInputBuffer = null;
    }
    eofSensor = paramSessionInputBuffer;
    wire = paramWire;
    if (paramString == null) {
      paramString = Consts.ASCII.name();
    }
    charset = paramString;
  }
  
  public HttpTransportMetrics getMetrics()
  {
    return in.getMetrics();
  }
  
  public boolean isDataAvailable(int paramInt)
  {
    return in.isDataAvailable(paramInt);
  }
  
  public boolean isEof()
  {
    EofSensor localEofSensor = eofSensor;
    if (localEofSensor != null) {
      return localEofSensor.isEof();
    }
    return false;
  }
  
  public int read()
  {
    int i = in.read();
    if ((wire.enabled()) && (i != -1)) {
      wire.input(i);
    }
    return i;
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    paramInt2 = in.read(paramArrayOfByte, paramInt1, paramInt2);
    if ((wire.enabled()) && (paramInt2 > 0)) {
      wire.input(paramArrayOfByte, paramInt1, paramInt2);
    }
    return paramInt2;
  }
  
  public int readLine(CharArrayBuffer paramCharArrayBuffer)
  {
    int i = in.readLine(paramCharArrayBuffer);
    if ((wire.enabled()) && (i >= 0))
    {
      int j = paramCharArrayBuffer.length();
      paramCharArrayBuffer = new String(paramCharArrayBuffer.buffer(), j - i, i);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramCharArrayBuffer);
      localStringBuilder.append("\r\n");
      paramCharArrayBuffer = localStringBuilder.toString();
      wire.input(paramCharArrayBuffer.getBytes(charset));
    }
    return i;
  }
}
