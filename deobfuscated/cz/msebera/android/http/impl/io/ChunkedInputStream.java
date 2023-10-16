package cz.msebera.android.http.impl.io;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpException;
import cz.msebera.android.http.MalformedChunkCodingException;
import cz.msebera.android.http.TruncatedChunkException;
import cz.msebera.android.http.io.BufferInfo;
import cz.msebera.android.http.io.SessionInputBuffer;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;
import cz.msebera.android.http.protocol.MessageConstraints;
import java.io.InputStream;

public class ChunkedInputStream
  extends InputStream
{
  private final CharArrayBuffer buffer;
  private int chunkSize;
  private boolean closed = false;
  private final MessageConstraints constraints;
  private boolean eof = false;
  private Header[] footers = new Header[0];
  private final SessionInputBuffer in;
  private int pos;
  private int state;
  
  public ChunkedInputStream(SessionInputBuffer paramSessionInputBuffer)
  {
    this(paramSessionInputBuffer, null);
  }
  
  public ChunkedInputStream(SessionInputBuffer paramSessionInputBuffer, MessageConstraints paramMessageConstraints)
  {
    Args.notNull(paramSessionInputBuffer, "Session input buffer");
    in = ((SessionInputBuffer)paramSessionInputBuffer);
    pos = 0;
    buffer = new CharArrayBuffer(16);
    if (paramMessageConstraints == null) {
      paramMessageConstraints = MessageConstraints.DEFAULT;
    }
    constraints = paramMessageConstraints;
    state = 1;
  }
  
  private int getChunkSize()
  {
    int i = state;
    if (i != 1) {
      if (i == 3)
      {
        buffer.clear();
        if (in.readLine(buffer) != -1)
        {
          if (buffer.isEmpty()) {
            state = 1;
          } else {
            throw new MalformedChunkCodingException("Unexpected content at the end of chunk");
          }
        }
        else {
          throw new MalformedChunkCodingException("CRLF expected at end of chunk");
        }
      }
      else
      {
        throw new IllegalStateException("Inconsistent codec state");
      }
    }
    buffer.clear();
    CharArrayBuffer localCharArrayBuffer;
    if (in.readLine(buffer) != -1)
    {
      int j = buffer.indexOf(59);
      i = j;
      if (j < 0) {
        i = buffer.length();
      }
      localCharArrayBuffer = buffer;
    }
    try
    {
      i = Integer.parseInt(localCharArrayBuffer.substringTrimmed(0, i), 16);
      return i;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      for (;;) {}
    }
    throw new MalformedChunkCodingException("Bad chunk header");
    throw new cz.msebera.android.http.IOException("Premature end of chunk coded message body: closing chunk expected");
  }
  
  private void nextChunk()
  {
    if (state != Integer.MAX_VALUE) {
      try
      {
        int i = getChunkSize();
        chunkSize = i;
        if (chunkSize >= 0)
        {
          state = 2;
          pos = 0;
          if (chunkSize != 0) {
            return;
          }
          eof = true;
          parseTrailerHeaders();
          return;
        }
        throw new MalformedChunkCodingException("Negative chunk size");
      }
      catch (MalformedChunkCodingException localMalformedChunkCodingException)
      {
        state = Integer.MAX_VALUE;
        throw localMalformedChunkCodingException;
      }
    }
    throw new MalformedChunkCodingException("Corrupt data stream");
  }
  
  private void parseTrailerHeaders()
  {
    Object localObject1 = in;
    Object localObject2 = constraints;
    try
    {
      int i = ((MessageConstraints)localObject2).getMaxLineLength();
      localObject2 = constraints;
      localObject1 = AbstractMessageParser.parseHeaders((SessionInputBuffer)localObject1, i, ((MessageConstraints)localObject2).getMaxHeaderCount(), null);
      footers = ((Header[])localObject1);
      return;
    }
    catch (HttpException localHttpException)
    {
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("Invalid footer: ");
      ((StringBuilder)localObject2).append(localHttpException.getMessage());
      localObject2 = new MalformedChunkCodingException(((StringBuilder)localObject2).toString());
      ((java.io.IOException)localObject2).initCause(localHttpException);
      throw ((Throwable)localObject2);
    }
  }
  
  public int available()
  {
    SessionInputBuffer localSessionInputBuffer = in;
    if ((localSessionInputBuffer instanceof BufferInfo)) {
      return Math.min(((BufferInfo)localSessionInputBuffer).length(), chunkSize - pos);
    }
    return 0;
  }
  
  public void close()
  {
    if (!closed) {
      try
      {
        boolean bool = eof;
        if (!bool)
        {
          int i = state;
          if (i != Integer.MAX_VALUE)
          {
            byte[] arrayOfByte = new byte['?'];
            do
            {
              i = read(arrayOfByte);
            } while (i >= 0);
          }
        }
        eof = true;
        closed = true;
        return;
      }
      catch (Throwable localThrowable)
      {
        eof = true;
        closed = true;
        throw localThrowable;
      }
    }
  }
  
  public int read()
  {
    int i;
    if (!closed)
    {
      if (eof) {
        return -1;
      }
      if (state != 2)
      {
        nextChunk();
        if (eof) {
          return -1;
        }
      }
      i = in.read();
      if (i != -1)
      {
        pos += 1;
        if (pos >= chunkSize)
        {
          state = 3;
          return i;
        }
      }
    }
    else
    {
      throw new java.io.IOException("Attempted read from closed stream.");
    }
    return i;
  }
  
  public int read(byte[] paramArrayOfByte)
  {
    return read(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (!closed)
    {
      if (eof) {
        return -1;
      }
      if (state != 2)
      {
        nextChunk();
        if (eof) {
          return -1;
        }
      }
      paramInt1 = in.read(paramArrayOfByte, paramInt1, Math.min(paramInt2, chunkSize - pos));
      if (paramInt1 != -1)
      {
        pos += paramInt1;
        if (pos >= chunkSize)
        {
          state = 3;
          return paramInt1;
        }
      }
      else
      {
        eof = true;
        paramArrayOfByte = new StringBuilder();
        paramArrayOfByte.append("Truncated chunk ( expected size: ");
        paramArrayOfByte.append(chunkSize);
        paramArrayOfByte.append("; actual size: ");
        paramArrayOfByte.append(pos);
        paramArrayOfByte.append(")");
        throw new TruncatedChunkException(paramArrayOfByte.toString());
      }
    }
    else
    {
      throw new java.io.IOException("Attempted read from closed stream.");
    }
    return paramInt1;
  }
}
