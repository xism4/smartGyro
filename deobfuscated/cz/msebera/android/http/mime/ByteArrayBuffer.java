package cz.msebera.android.http.mime;

import java.io.Serializable;

public final class ByteArrayBuffer
  implements Serializable
{
  private byte[] buffer;
  private int len;
  
  public ByteArrayBuffer(int paramInt)
  {
    Args.notNegative(paramInt, "Buffer capacity");
    buffer = new byte[paramInt];
  }
  
  private void expand(int paramInt)
  {
    byte[] arrayOfByte = new byte[Math.max(buffer.length << 1, paramInt)];
    System.arraycopy(buffer, 0, arrayOfByte, 0, len);
    buffer = arrayOfByte;
  }
  
  public void append(int paramInt)
  {
    int i = len + 1;
    if (i > buffer.length) {
      expand(i);
    }
    buffer[len] = ((byte)paramInt);
    len = i;
  }
  
  public void append(CharArrayBuffer paramCharArrayBuffer, int paramInt1, int paramInt2)
  {
    if (paramCharArrayBuffer == null) {
      return;
    }
    append(paramCharArrayBuffer.buffer(), paramInt1, paramInt2);
  }
  
  public void append(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramArrayOfByte == null) {
      return;
    }
    if ((paramInt1 >= 0) && (paramInt1 <= paramArrayOfByte.length) && (paramInt2 >= 0))
    {
      int i = paramInt1 + paramInt2;
      if ((i >= 0) && (i <= paramArrayOfByte.length))
      {
        if (paramInt2 == 0) {
          return;
        }
        i = len + paramInt2;
        if (i > buffer.length) {
          expand(i);
        }
        System.arraycopy(paramArrayOfByte, paramInt1, buffer, len, paramInt2);
        len = i;
        return;
      }
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("off: ");
    localStringBuilder.append(paramInt1);
    localStringBuilder.append(" len: ");
    localStringBuilder.append(paramInt2);
    localStringBuilder.append(" b.length: ");
    localStringBuilder.append(paramArrayOfByte.length);
    throw new IndexOutOfBoundsException(localStringBuilder.toString());
  }
  
  public void append(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    if (paramArrayOfChar == null) {
      return;
    }
    if ((paramInt1 >= 0) && (paramInt1 <= paramArrayOfChar.length) && (paramInt2 >= 0))
    {
      int i = paramInt1 + paramInt2;
      if ((i >= 0) && (i <= paramArrayOfChar.length))
      {
        if (paramInt2 == 0) {
          return;
        }
        int j = len;
        int k = paramInt2 + j;
        paramInt2 = j;
        i = paramInt1;
        if (k > buffer.length)
        {
          expand(k);
          i = paramInt1;
          paramInt2 = j;
        }
        while (paramInt2 < k)
        {
          buffer[paramInt2] = ((byte)paramArrayOfChar[i]);
          i += 1;
          paramInt2 += 1;
        }
        len = k;
        return;
      }
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("off: ");
    localStringBuilder.append(paramInt1);
    localStringBuilder.append(" len: ");
    localStringBuilder.append(paramInt2);
    localStringBuilder.append(" b.length: ");
    localStringBuilder.append(paramArrayOfChar.length);
    paramArrayOfChar = new IndexOutOfBoundsException(localStringBuilder.toString());
    throw paramArrayOfChar;
  }
  
  public byte[] buffer()
  {
    return buffer;
  }
  
  public int byteAt(int paramInt)
  {
    return buffer[paramInt];
  }
  
  public int capacity()
  {
    return buffer.length;
  }
  
  public void clear()
  {
    len = 0;
  }
  
  public boolean isEmpty()
  {
    return len == 0;
  }
  
  public boolean isFull()
  {
    return len == buffer.length;
  }
  
  public int length()
  {
    return len;
  }
  
  public byte[] toByteArray()
  {
    int i = len;
    byte[] arrayOfByte = new byte[i];
    if (i > 0) {
      System.arraycopy(buffer, 0, arrayOfByte, 0, i);
    }
    return arrayOfByte;
  }
}
