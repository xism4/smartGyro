package cz.msebera.android.http.impl.io;

import cz.msebera.android.http.Consts;
import cz.msebera.android.http.io.BufferInfo;
import cz.msebera.android.http.io.HttpTransportMetrics;
import cz.msebera.android.http.io.SessionInputBuffer;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.ByteArrayBuffer;
import cz.msebera.android.http.mime.CharArrayBuffer;
import cz.msebera.android.http.util.HttpParams;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;

@Deprecated
public abstract class AbstractSessionInputBuffer
  implements SessionInputBuffer, BufferInfo
{
  private boolean ascii;
  private byte[] buffer;
  private int bufferlen;
  private int bufferpos;
  private CharBuffer cbuf;
  private Charset charset;
  private CharsetDecoder decoder;
  private InputStream instream;
  private ByteArrayBuffer linebuffer;
  private int maxLineLen;
  private HttpTransportMetricsImpl metrics;
  private int minChunkLimit;
  private CodingErrorAction onMalformedCharAction;
  private CodingErrorAction onUnmappableCharAction;
  
  public AbstractSessionInputBuffer() {}
  
  private int appendDecoded(CharArrayBuffer paramCharArrayBuffer, ByteBuffer paramByteBuffer)
  {
    boolean bool = paramByteBuffer.hasRemaining();
    int i = 0;
    if (!bool) {
      return 0;
    }
    if (decoder == null)
    {
      decoder = charset.newDecoder();
      decoder.onMalformedInput(onMalformedCharAction);
      decoder.onUnmappableCharacter(onUnmappableCharAction);
    }
    if (cbuf == null) {
      cbuf = CharBuffer.allocate(1024);
    }
    decoder.reset();
    while (paramByteBuffer.hasRemaining()) {
      i += handleDecodingResult(decoder.decode(paramByteBuffer, cbuf, true), paramCharArrayBuffer, paramByteBuffer);
    }
    int j = handleDecodingResult(decoder.flush(cbuf), paramCharArrayBuffer, paramByteBuffer);
    cbuf.clear();
    return i + j;
  }
  
  private int handleDecodingResult(CoderResult paramCoderResult, CharArrayBuffer paramCharArrayBuffer, ByteBuffer paramByteBuffer)
  {
    if (paramCoderResult.isError()) {
      paramCoderResult.throwException();
    }
    cbuf.flip();
    int i = cbuf.remaining();
    while (cbuf.hasRemaining()) {
      paramCharArrayBuffer.append(cbuf.get());
    }
    cbuf.compact();
    return i;
  }
  
  private int lineFromLineBuffer(CharArrayBuffer paramCharArrayBuffer)
  {
    int k = linebuffer.length();
    int j = k;
    int i = j;
    if (k > 0)
    {
      if (linebuffer.byteAt(k - 1) == 10) {
        j = k - 1;
      }
      i = j;
      if (j > 0)
      {
        i = j;
        if (linebuffer.byteAt(j - 1) == 13) {
          i = j - 1;
        }
      }
    }
    if (ascii) {
      paramCharArrayBuffer.append(linebuffer, 0, i);
    } else {
      i = appendDecoded(paramCharArrayBuffer, ByteBuffer.wrap(linebuffer.buffer(), 0, i));
    }
    linebuffer.clear();
    return i;
  }
  
  private int lineFromReadBuffer(CharArrayBuffer paramCharArrayBuffer, int paramInt)
  {
    int j = bufferpos;
    bufferpos = (paramInt + 1);
    int i = paramInt;
    if (paramInt > j)
    {
      i = paramInt;
      if (buffer[(paramInt - 1)] == 13) {
        i = paramInt - 1;
      }
    }
    paramInt = i - j;
    if (ascii)
    {
      paramCharArrayBuffer.append(buffer, j, paramInt);
      return paramInt;
    }
    return appendDecoded(paramCharArrayBuffer, ByteBuffer.wrap(buffer, j, paramInt));
  }
  
  private int locateLF()
  {
    int i = bufferpos;
    while (i < bufferlen)
    {
      if (buffer[i] == 10) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  protected HttpTransportMetricsImpl createTransportMetrics()
  {
    return new HttpTransportMetricsImpl();
  }
  
  protected int fillBuffer()
  {
    int i = bufferpos;
    if (i > 0)
    {
      j = bufferlen - i;
      if (j > 0)
      {
        arrayOfByte = buffer;
        System.arraycopy(arrayOfByte, i, arrayOfByte, 0, j);
      }
      bufferpos = 0;
      bufferlen = j;
    }
    i = bufferlen;
    byte[] arrayOfByte = buffer;
    int j = arrayOfByte.length;
    j = instream.read(arrayOfByte, i, j - i);
    if (j == -1) {
      return -1;
    }
    bufferlen = (i + j);
    metrics.incrementBytesTransferred(j);
    return j;
  }
  
  public HttpTransportMetrics getMetrics()
  {
    return metrics;
  }
  
  protected boolean hasBufferedData()
  {
    return bufferpos < bufferlen;
  }
  
  protected void init(InputStream paramInputStream, int paramInt, HttpParams paramHttpParams)
  {
    Args.notNull(paramInputStream, "Input stream");
    Args.notNegative(paramInt, "Buffer size");
    Args.notNull(paramHttpParams, "HTTP parameters");
    instream = paramInputStream;
    buffer = new byte[paramInt];
    bufferpos = 0;
    bufferlen = 0;
    linebuffer = new ByteArrayBuffer(paramInt);
    paramInputStream = (String)paramHttpParams.getParameter("http.protocol.element-charset");
    if (paramInputStream != null) {
      paramInputStream = Charset.forName(paramInputStream);
    } else {
      paramInputStream = Consts.ASCII;
    }
    charset = paramInputStream;
    ascii = charset.equals(Consts.ASCII);
    decoder = null;
    maxLineLen = paramHttpParams.getIntParameter("http.connection.max-line-length", -1);
    minChunkLimit = paramHttpParams.getIntParameter("http.connection.min-chunk-limit", 512);
    metrics = createTransportMetrics();
    paramInputStream = (CodingErrorAction)paramHttpParams.getParameter("http.malformed.input.action");
    if (paramInputStream == null) {
      paramInputStream = CodingErrorAction.REPORT;
    }
    onMalformedCharAction = paramInputStream;
    paramInputStream = (CodingErrorAction)paramHttpParams.getParameter("http.unmappable.input.action");
    if (paramInputStream == null) {
      paramInputStream = CodingErrorAction.REPORT;
    }
    onUnmappableCharAction = paramInputStream;
  }
  
  public int length()
  {
    return bufferlen - bufferpos;
  }
  
  public int read()
  {
    while (!hasBufferedData()) {
      if (fillBuffer() == -1) {
        return -1;
      }
    }
    byte[] arrayOfByte = buffer;
    int i = bufferpos;
    bufferpos = (i + 1);
    return arrayOfByte[i] & 0xFF;
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramArrayOfByte == null) {
      return 0;
    }
    int i;
    if (hasBufferedData())
    {
      i = Math.min(paramInt2, bufferlen - bufferpos);
      paramInt2 = i;
      System.arraycopy(buffer, bufferpos, paramArrayOfByte, paramInt1, i);
    }
    for (;;)
    {
      bufferpos += paramInt2;
      return paramInt2;
      if (paramInt2 > minChunkLimit)
      {
        paramInt1 = instream.read(paramArrayOfByte, paramInt1, paramInt2);
        if (paramInt1 <= 0) {
          break;
        }
        metrics.incrementBytesTransferred(paramInt1);
        return paramInt1;
      }
      while (!hasBufferedData()) {
        if (fillBuffer() == -1) {
          return -1;
        }
      }
      i = Math.min(paramInt2, bufferlen - bufferpos);
      paramInt2 = i;
      System.arraycopy(buffer, bufferpos, paramArrayOfByte, paramInt1, i);
    }
    return paramInt1;
  }
  
  public int readLine(CharArrayBuffer paramCharArrayBuffer)
  {
    Args.notNull(paramCharArrayBuffer, "Char array buffer");
    int k = 1;
    int i = 0;
    while (k != 0)
    {
      int j = locateLF();
      if (j != -1)
      {
        if (linebuffer.isEmpty()) {
          return lineFromReadBuffer(paramCharArrayBuffer, j);
        }
        j += 1;
        k = bufferpos;
        linebuffer.append(buffer, k, j - k);
        bufferpos = j;
      }
      int m;
      int n;
      do
      {
        m = 0;
        j = i;
        break;
        if (hasBufferedData())
        {
          i = bufferlen;
          j = bufferpos;
          linebuffer.append(buffer, j, i - j);
          bufferpos = bufferlen;
        }
        n = fillBuffer();
        i = n;
        m = k;
        j = i;
      } while (n == -1);
      k = m;
      i = j;
      if (maxLineLen > 0) {
        if (linebuffer.length() < maxLineLen)
        {
          k = m;
          i = j;
        }
        else
        {
          throw new IOException("Maximum line length limit exceeded");
        }
      }
    }
    if ((i == -1) && (linebuffer.isEmpty())) {
      return -1;
    }
    return lineFromLineBuffer(paramCharArrayBuffer);
  }
}
