package lombok.libs.org.objectweb.asm;

import android.content.res.AssetFileDescriptor;
import android.util.Log;
import b.a.a.a.a.b.a;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.HashMap;
import java.util.zip.ZipFile;

public class ByteVector
{
  ByteBuffer buffer = ByteBuffer.allocate(4);
  public HashMap<File, ZipFile> data = new HashMap();
  private HashMap<String, b.a> header = new HashMap();
  
  public ByteVector(String paramString)
  {
    read(paramString);
  }
  
  private static int read(int paramInt)
  {
    return ((paramInt & 0xFF) << 24) + ((0xFF00 & paramInt) << 8) + ((0xFF0000 & paramInt) >>> 8) + (paramInt >>> 24 & 0xFF);
  }
  
  private static int read(RandomAccessFile paramRandomAccessFile)
  {
    return read(paramRandomAccessFile.readInt());
  }
  
  public AssetFileDescriptor a(String paramString)
  {
    paramString = (a)header.get(paramString);
    if (paramString != null) {
      return paramString.a();
    }
    return null;
  }
  
  void read(String paramString)
  {
    File localFile = new File(paramString);
    RandomAccessFile localRandomAccessFile = new RandomAccessFile(localFile, "r");
    long l2 = localRandomAccessFile.length();
    if (l2 >= 22L)
    {
      long l1 = 65557L;
      if (65557L > l2) {
        l1 = l2;
      }
      localRandomAccessFile.seek(0L);
      int i = read(localRandomAccessFile);
      if (i != 101010256)
      {
        if (i == 67324752)
        {
          localRandomAccessFile.seek(l2 - l1);
          Object localObject1 = ByteBuffer.allocate((int)l1);
          Object localObject2 = ((ByteBuffer)localObject1).array();
          localRandomAccessFile.readFully((byte[])localObject2);
          ((ByteBuffer)localObject1).order(ByteOrder.LITTLE_ENDIAN);
          i = localObject2.length - 22;
          while ((i >= 0) && ((localObject2[i] != 80) || (((ByteBuffer)localObject1).getInt(i) != 101010256))) {
            i -= 1;
          }
          if (i < 0)
          {
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append("Zip: EOCD not found, ");
            ((StringBuilder)localObject2).append(paramString);
            ((StringBuilder)localObject2).append(" is not zip");
            Log.d("zipro", ((StringBuilder)localObject2).toString());
          }
          int k = ((ByteBuffer)localObject1).getShort(i + 8);
          l1 = ((ByteBuffer)localObject1).getInt(i + 12) & 0xFFFFFFFF;
          long l3 = ((ByteBuffer)localObject1).getInt(i + 16) & 0xFFFFFFFF;
          if (l3 + l1 <= l2)
          {
            if (k != 0)
            {
              localObject1 = localRandomAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY, l3, l1);
              ((MappedByteBuffer)localObject1).order(ByteOrder.LITTLE_ENDIAN);
              localObject2 = new byte[65535];
              ByteBuffer localByteBuffer = ByteBuffer.allocate(30);
              localByteBuffer.order(ByteOrder.LITTLE_ENDIAN);
              i = 0;
              int j = 0;
              while (i < k) {
                if (((MappedByteBuffer)localObject1).getInt(j) == 33639248)
                {
                  int m = ((MappedByteBuffer)localObject1).getShort(j + 28) & 0xFFFF;
                  int n = ((MappedByteBuffer)localObject1).getShort(j + 30);
                  int i1 = ((MappedByteBuffer)localObject1).getShort(j + 32);
                  ((MappedByteBuffer)localObject1).position(j + 46);
                  ((MappedByteBuffer)localObject1).get((byte[])localObject2, 0, m);
                  ((MappedByteBuffer)localObject1).position(0);
                  String str = new String((byte[])localObject2, 0, m);
                  a localA = new a(paramString, localFile, str);
                  b = (((MappedByteBuffer)localObject1).getShort(j + 10) & 0xFFFF);
                  n = (((MappedByteBuffer)localObject1).getInt(j + 12) & 0xFFFFFFFF);
                  s = (((MappedByteBuffer)localObject1).getLong(j + 16) & 0xFFFFFFFF);
                  i = (((MappedByteBuffer)localObject1).getLong(j + 20) & 0xFFFFFFFF);
                  a = (((MappedByteBuffer)localObject1).getLong(j + 24) & 0xFFFFFFFF);
                  p = (((MappedByteBuffer)localObject1).getInt(j + 42) & 0xFFFFFFFF);
                  localByteBuffer.clear();
                  localA.read(localRandomAccessFile, localByteBuffer);
                  header.put(str, localA);
                  j += m + 46 + (n & 0xFFFF) + (i1 & 0xFFFF);
                  i += 1;
                }
                else
                {
                  paramString = new StringBuilder();
                  paramString.append("Missed a central dir sig (at ");
                  paramString.append(j);
                  paramString.append(")");
                  Log.w("zipro", paramString.toString());
                  throw new IOException();
                }
              }
              return;
            }
            Log.w("zipro", "empty archive?");
            throw new IOException();
          }
          paramString = new StringBuilder();
          paramString.append("bad offsets (dir ");
          paramString.append(l3);
          paramString.append(", size ");
          paramString.append(l1);
          paramString.append(", eocd ");
          paramString.append(i);
          paramString.append(")");
          Log.w("zipro", paramString.toString());
          throw new IOException();
        }
        Log.v("zipro", "Not a Zip archive");
        throw new IOException();
      }
      Log.i("zipro", "Found Zip archive, but it looks empty");
      throw new IOException();
    }
    paramString = new IOException();
    throw paramString;
  }
}
