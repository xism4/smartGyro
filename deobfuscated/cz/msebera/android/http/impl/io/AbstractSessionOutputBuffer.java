package cz.msebera.android.http.impl.io;

import cz.msebera.android.http.Consts;
import cz.msebera.android.http.io.BufferInfo;
import cz.msebera.android.http.io.HttpTransportMetrics;
import cz.msebera.android.http.io.SessionOutputBuffer;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.ByteArrayBuffer;
import cz.msebera.android.http.mime.CharArrayBuffer;
import cz.msebera.android.http.util.HttpParams;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;

@Deprecated
public abstract class AbstractSessionOutputBuffer
  implements SessionOutputBuffer, BufferInfo
{
  private static final byte[] CRLF = { 13, 10 };
  private boolean ascii;
  private ByteBuffer bbuf;
  private ByteArrayBuffer buffer;
  private Charset charset;
  private CharsetEncoder encoder;
  private HttpTransportMetricsImpl metrics;
  private int minChunkLimit;
  private CodingErrorAction onMalformedCharAction;
  private CodingErrorAction onUnmappableCharAction;
  private OutputStream outstream;
  
  public AbstractSessionOutputBuffer() {}
  
  private void handleEncodingResult(CoderResult paramCoderResult)
  {
    if (paramCoderResult.isError()) {
      paramCoderResult.throwException();
    }
    bbuf.flip();
    while (bbuf.hasRemaining()) {
      write(bbuf.get());
    }
    bbuf.compact();
  }
  
  private void writeEncoded(CharBuffer paramCharBuffer)
  {
    if (!paramCharBuffer.hasRemaining()) {
      return;
    }
    if (encoder == null)
    {
      encoder = charset.newEncoder();
      encoder.onMalformedInput(onMalformedCharAction);
      encoder.onUnmappableCharacter(onUnmappableCharAction);
    }
    if (bbuf == null) {
      bbuf = ByteBuffer.allocate(1024);
    }
    encoder.reset();
    while (paramCharBuffer.hasRemaining()) {
      handleEncodingResult(encoder.encode(paramCharBuffer, bbuf, true));
    }
    handleEncodingResult(encoder.flush(bbuf));
    bbuf.clear();
  }
  
  protected HttpTransportMetricsImpl createTransportMetrics()
  {
    return new HttpTransportMetricsImpl();
  }
  
  public void flush()
  {
    flushBuffer();
    outstream.flush();
  }
  
  protected void flushBuffer()
  {
    int i = buffer.length();
    if (i > 0)
    {
      outstream.write(buffer.buffer(), 0, i);
      buffer.clear();
      metrics.incrementBytesTransferred(i);
    }
  }
  
  public HttpTransportMetrics getMetrics()
  {
    return metrics;
  }
  
  protected void init(OutputStream paramOutputStream, int paramInt, HttpParams paramHttpParams)
  {
    Args.notNull(paramOutputStream, "Input stream");
    Args.notNegative(paramInt, "Buffer size");
    Args.notNull(paramHttpParams, "HTTP parameters");
    outstream = paramOutputStream;
    buffer = new ByteArrayBuffer(paramInt);
    paramOutputStream = (String)paramHttpParams.getParameter("http.protocol.element-charset");
    if (paramOutputStream != null) {
      paramOutputStream = Charset.forName(paramOutputStream);
    } else {
      paramOutputStream = Consts.ASCII;
    }
    charset = paramOutputStream;
    ascii = charset.equals(Consts.ASCII);
    encoder = null;
    minChunkLimit = paramHttpParams.getIntParameter("http.connection.min-chunk-limit", 512);
    metrics = createTransportMetrics();
    paramOutputStream = (CodingErrorAction)paramHttpParams.getParameter("http.malformed.input.action");
    if (paramOutputStream == null) {
      paramOutputStream = CodingErrorAction.REPORT;
    }
    onMalformedCharAction = paramOutputStream;
    paramOutputStream = (CodingErrorAction)paramHttpParams.getParameter("http.unmappable.input.action");
    if (paramOutputStream == null) {
      paramOutputStream = CodingErrorAction.REPORT;
    }
    onUnmappableCharAction = paramOutputStream;
  }
  
  public int length()
  {
    return buffer.length();
  }
  
  public void write(int paramInt)
  {
    if (buffer.isFull()) {
      flushBuffer();
    }
    buffer.append(paramInt);
  }
  
  public void write(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      return;
    }
    write(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramArrayOfByte == null) {
      return;
    }
    if ((paramInt2 <= minChunkLimit) && (paramInt2 <= buffer.capacity()))
    {
      if (paramInt2 > buffer.capacity() - buffer.length()) {
        flushBuffer();
      }
      buffer.append(paramArrayOfByte, paramInt1, paramInt2);
      return;
    }
    flushBuffer();
    outstream.write(paramArrayOfByte, paramInt1, paramInt2);
    metrics.incrementBytesTransferred(paramInt2);
  }
  
  public void writeLine(CharArrayBuffer paramCharArrayBuffer)
  {
    if (paramCharArrayBuffer == null) {
      return;
    }
    boolean bool = ascii;
    int j = 0;
    if (bool)
    {
      int i = paramCharArrayBuffer.length();
      while (i > 0)
      {
        int k = Math.min(buffer.capacity() - buffer.length(), i);
        if (k > 0) {
          buffer.append(paramCharArrayBuffer, j, k);
        }
        if (buffer.isFull()) {
          flushBuffer();
        }
        j += k;
        i -= k;
      }
    }
    writeEncoded(CharBuffer.wrap(paramCharArrayBuffer.buffer(), 0, paramCharArrayBuffer.length()));
    write(CRLF);
  }
  
  public void writeLine(String paramString)
  {
    if (paramString == null) {
      return;
    }
    if (paramString.length() > 0)
    {
      if (ascii)
      {
        int i = 0;
        while (i < paramString.length())
        {
          write(paramString.charAt(i));
          i += 1;
        }
      }
      writeEncoded(CharBuffer.wrap(paramString));
    }
    write(CRLF);
  }
}
