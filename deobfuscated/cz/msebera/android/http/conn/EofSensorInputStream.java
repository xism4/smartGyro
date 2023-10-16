package cz.msebera.android.http.conn;

import cz.msebera.android.http.mime.Args;
import java.io.IOException;
import java.io.InputStream;

public class EofSensorInputStream
  extends InputStream
  implements ConnectionReleaseTrigger
{
  private final EofSensorWatcher eofWatcher;
  private boolean selfClosed;
  protected InputStream wrappedStream;
  
  public EofSensorInputStream(InputStream paramInputStream, EofSensorWatcher paramEofSensorWatcher)
  {
    Args.notNull(paramInputStream, "Wrapped stream");
    wrappedStream = paramInputStream;
    selfClosed = false;
    eofWatcher = paramEofSensorWatcher;
  }
  
  public void abortConnection()
  {
    selfClosed = true;
    checkAbort();
  }
  
  public int available()
  {
    if (isReadAllowed())
    {
      InputStream localInputStream = wrappedStream;
      try
      {
        int i = localInputStream.available();
        return i;
      }
      catch (IOException localIOException)
      {
        checkAbort();
        throw localIOException;
      }
    }
    return 0;
  }
  
  protected void checkAbort()
  {
    InputStream localInputStream = wrappedStream;
    if (localInputStream != null)
    {
      boolean bool = true;
      try
      {
        EofSensorWatcher localEofSensorWatcher = eofWatcher;
        if (localEofSensorWatcher != null) {
          bool = eofWatcher.streamAbort(localInputStream);
        }
        if (bool) {
          wrappedStream.close();
        }
        wrappedStream = null;
        return;
      }
      catch (Throwable localThrowable)
      {
        wrappedStream = null;
        throw localThrowable;
      }
    }
  }
  
  protected void checkClose()
  {
    InputStream localInputStream = wrappedStream;
    if (localInputStream != null)
    {
      boolean bool = true;
      try
      {
        EofSensorWatcher localEofSensorWatcher = eofWatcher;
        if (localEofSensorWatcher != null) {
          bool = eofWatcher.streamClosed(localInputStream);
        }
        if (bool) {
          wrappedStream.close();
        }
        wrappedStream = null;
        return;
      }
      catch (Throwable localThrowable)
      {
        wrappedStream = null;
        throw localThrowable;
      }
    }
  }
  
  protected void checkEOF(int paramInt)
  {
    InputStream localInputStream = wrappedStream;
    if ((localInputStream != null) && (paramInt < 0))
    {
      boolean bool = true;
      try
      {
        EofSensorWatcher localEofSensorWatcher = eofWatcher;
        if (localEofSensorWatcher != null) {
          bool = eofWatcher.eofDetected(localInputStream);
        }
        if (bool) {
          wrappedStream.close();
        }
        wrappedStream = null;
        return;
      }
      catch (Throwable localThrowable)
      {
        wrappedStream = null;
        throw localThrowable;
      }
    }
  }
  
  public void close()
  {
    selfClosed = true;
    checkClose();
  }
  
  protected boolean isReadAllowed()
  {
    if (!selfClosed) {
      return wrappedStream != null;
    }
    throw new IOException("Attempted read on closed stream.");
  }
  
  public int read()
  {
    if (isReadAllowed())
    {
      InputStream localInputStream = wrappedStream;
      try
      {
        int i = localInputStream.read();
        checkEOF(i);
        return i;
      }
      catch (IOException localIOException)
      {
        checkAbort();
        throw localIOException;
      }
    }
    return -1;
  }
  
  public int read(byte[] paramArrayOfByte)
  {
    return read(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (isReadAllowed())
    {
      InputStream localInputStream = wrappedStream;
      try
      {
        paramInt1 = localInputStream.read(paramArrayOfByte, paramInt1, paramInt2);
        checkEOF(paramInt1);
        return paramInt1;
      }
      catch (IOException paramArrayOfByte)
      {
        checkAbort();
        throw paramArrayOfByte;
      }
    }
    return -1;
  }
}
