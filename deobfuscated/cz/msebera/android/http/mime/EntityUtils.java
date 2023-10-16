package cz.msebera.android.http.mime;

import cz.msebera.android.http.HttpEntity;
import java.io.InputStream;

public final class EntityUtils
{
  public static void consume(HttpEntity paramHttpEntity)
  {
    if (paramHttpEntity == null) {
      return;
    }
    if (paramHttpEntity.isStreaming())
    {
      paramHttpEntity = paramHttpEntity.getContent();
      if (paramHttpEntity != null) {
        paramHttpEntity.close();
      }
    }
  }
  
  public static byte[] toByteArray(HttpEntity paramHttpEntity)
  {
    Args.notNull(paramHttpEntity, "Entity");
    InputStream localInputStream = paramHttpEntity.getContent();
    if (localInputStream == null) {
      return null;
    }
    try
    {
      long l = paramHttpEntity.getContentLength();
      boolean bool;
      if (l <= 2147483647L) {
        bool = true;
      } else {
        bool = false;
      }
      Args.check(bool, "HTTP entity too large to be buffered in memory");
      int j = (int)paramHttpEntity.getContentLength();
      int i = j;
      if (j < 0) {
        i = 4096;
      }
      paramHttpEntity = new ByteArrayBuffer(i);
      byte[] arrayOfByte = new byte['?'];
      for (;;)
      {
        i = localInputStream.read(arrayOfByte);
        if (i == -1) {
          break;
        }
        paramHttpEntity.append(arrayOfByte, 0, i);
      }
      paramHttpEntity = paramHttpEntity.toByteArray();
      localInputStream.close();
      return paramHttpEntity;
    }
    catch (Throwable paramHttpEntity)
    {
      localInputStream.close();
      throw paramHttpEntity;
    }
  }
}
