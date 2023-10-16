package lombok.libs.org.objectweb.asm;

import android.content.res.AssetFileDescriptor;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

public final class a
{
  public long a;
  public int b;
  public long count = -1L;
  public final String d;
  public final File f;
  public final String g;
  public long i;
  public long n;
  public long p;
  public long s;
  
  public a(String paramString1, File paramFile, String paramString2)
  {
    d = paramString2;
    g = paramString1;
    f = paramFile;
  }
  
  public AssetFileDescriptor a()
  {
    if (b == 0)
    {
      Object localObject = f;
      try
      {
        localObject = ParcelFileDescriptor.open((File)localObject, 268435456);
        long l1 = read();
        long l2 = a;
        localObject = new AssetFileDescriptor((ParcelFileDescriptor)localObject, l1, l2);
        return localObject;
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        localFileNotFoundException.printStackTrace();
      }
    }
    return null;
  }
  
  public long read()
  {
    return count;
  }
  
  public void read(RandomAccessFile paramRandomAccessFile, ByteBuffer paramByteBuffer)
  {
    long l = p;
    try
    {
      paramRandomAccessFile.seek(l);
      paramRandomAccessFile.readFully(paramByteBuffer.array());
      int j = paramByteBuffer.getInt(0);
      if (j == 67324752)
      {
        j = paramByteBuffer.getShort(26);
        int k = paramByteBuffer.getShort(28);
        count = (l + 30L + (j & 0xFFFF) + (k & 0xFFFF));
        return;
      }
      Log.w("zipro", "didn't find signature at start of lfh");
      paramRandomAccessFile = new IOException();
      throw paramRandomAccessFile;
    }
    catch (IOException paramRandomAccessFile)
    {
      paramRandomAccessFile.printStackTrace();
      return;
    }
    catch (FileNotFoundException paramRandomAccessFile)
    {
      paramRandomAccessFile.printStackTrace();
    }
  }
}
