package cz.msebera.android.http.impl.conn;

import cz.msebera.android.http.Consts;
import cz.msebera.android.http.io.HttpTransportMetrics;
import cz.msebera.android.http.io.SessionOutputBuffer;
import cz.msebera.android.http.mime.CharArrayBuffer;
import java.nio.charset.Charset;

@Deprecated
public class LoggingSessionOutputBuffer
  implements SessionOutputBuffer
{
  private final String charset;
  private final SessionOutputBuffer out;
  private final Wire wire;
  
  public LoggingSessionOutputBuffer(SessionOutputBuffer paramSessionOutputBuffer, Wire paramWire, String paramString)
  {
    out = paramSessionOutputBuffer;
    wire = paramWire;
    if (paramString == null) {
      paramString = Consts.ASCII.name();
    }
    charset = paramString;
  }
  
  public void flush()
  {
    out.flush();
  }
  
  public HttpTransportMetrics getMetrics()
  {
    return out.getMetrics();
  }
  
  public void write(int paramInt)
  {
    out.write(paramInt);
    if (wire.enabled()) {
      wire.output(paramInt);
    }
  }
  
  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    out.write(paramArrayOfByte, paramInt1, paramInt2);
    if (wire.enabled()) {
      wire.output(paramArrayOfByte, paramInt1, paramInt2);
    }
  }
  
  public void writeLine(CharArrayBuffer paramCharArrayBuffer)
  {
    out.writeLine(paramCharArrayBuffer);
    if (wire.enabled())
    {
      paramCharArrayBuffer = new String(paramCharArrayBuffer.buffer(), 0, paramCharArrayBuffer.length());
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramCharArrayBuffer);
      localStringBuilder.append("\r\n");
      paramCharArrayBuffer = localStringBuilder.toString();
      wire.output(paramCharArrayBuffer.getBytes(charset));
    }
  }
  
  public void writeLine(String paramString)
  {
    out.writeLine(paramString);
    if (wire.enabled())
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      localStringBuilder.append("\r\n");
      paramString = localStringBuilder.toString();
      wire.output(paramString.getBytes(charset));
    }
  }
}
