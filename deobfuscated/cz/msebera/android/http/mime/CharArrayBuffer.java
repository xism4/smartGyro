package cz.msebera.android.http.mime;

import cz.msebera.android.http.execchain.HTTP;
import java.io.Serializable;
import java.nio.CharBuffer;

public final class CharArrayBuffer
  implements CharSequence, Serializable
{
  private char[] buffer;
  private int len;
  
  public CharArrayBuffer(int paramInt)
  {
    Args.notNegative(paramInt, "Buffer capacity");
    buffer = new char[paramInt];
  }
  
  private void expand(int paramInt)
  {
    char[] arrayOfChar = new char[Math.max(buffer.length << 1, paramInt)];
    System.arraycopy(buffer, 0, arrayOfChar, 0, len);
    buffer = arrayOfChar;
  }
  
  public void append(char paramChar)
  {
    int i = len + 1;
    if (i > buffer.length) {
      expand(i);
    }
    buffer[len] = paramChar;
    len = i;
  }
  
  public void append(ByteArrayBuffer paramByteArrayBuffer, int paramInt1, int paramInt2)
  {
    if (paramByteArrayBuffer == null) {
      return;
    }
    append(paramByteArrayBuffer.buffer(), paramInt1, paramInt2);
  }
  
  public void append(CharArrayBuffer paramCharArrayBuffer, int paramInt1, int paramInt2)
  {
    if (paramCharArrayBuffer == null) {
      return;
    }
    append(buffer, paramInt1, paramInt2);
  }
  
  public void append(String paramString)
  {
    if (paramString == null) {
      paramString = "null";
    }
    int i = paramString.length();
    int j = len + i;
    if (j > buffer.length) {
      expand(j);
    }
    paramString.getChars(0, i, buffer, len);
    len = j;
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
          buffer[paramInt2] = ((char)(paramArrayOfByte[i] & 0xFF));
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
    localStringBuilder.append(paramArrayOfByte.length);
    paramArrayOfByte = new IndexOutOfBoundsException(localStringBuilder.toString());
    throw paramArrayOfByte;
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
        i = len + paramInt2;
        if (i > buffer.length) {
          expand(i);
        }
        System.arraycopy(paramArrayOfChar, paramInt1, buffer, len, paramInt2);
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
    localStringBuilder.append(paramArrayOfChar.length);
    throw new IndexOutOfBoundsException(localStringBuilder.toString());
  }
  
  public char[] buffer()
  {
    return buffer;
  }
  
  public char charAt(int paramInt)
  {
    return buffer[paramInt];
  }
  
  public void clear()
  {
    len = 0;
  }
  
  public void ensureCapacity(int paramInt)
  {
    if (paramInt <= 0) {
      return;
    }
    int i = buffer.length;
    int j = len;
    if (paramInt > i - j) {
      expand(j + paramInt);
    }
  }
  
  public int indexOf(int paramInt)
  {
    return indexOf(paramInt, 0, len);
  }
  
  public int indexOf(int paramInt1, int paramInt2, int paramInt3)
  {
    int i = paramInt2;
    if (paramInt2 < 0) {
      i = 0;
    }
    int j = len;
    paramInt2 = paramInt3;
    if (paramInt3 > j) {
      paramInt2 = j;
    }
    paramInt3 = i;
    if (i > paramInt2) {
      return -1;
    }
    while (paramInt3 < paramInt2)
    {
      if (buffer[paramInt3] == paramInt1) {
        return paramInt3;
      }
      paramInt3 += 1;
    }
    return -1;
  }
  
  public boolean isEmpty()
  {
    return len == 0;
  }
  
  public int length()
  {
    return len;
  }
  
  public CharSequence subSequence(int paramInt1, int paramInt2)
  {
    if (paramInt1 >= 0)
    {
      if (paramInt2 <= len)
      {
        if (paramInt1 <= paramInt2) {
          return CharBuffer.wrap(buffer, paramInt1, paramInt2);
        }
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("beginIndex: ");
        localStringBuilder.append(paramInt1);
        localStringBuilder.append(" > endIndex: ");
        localStringBuilder.append(paramInt2);
        throw new IndexOutOfBoundsException(localStringBuilder.toString());
      }
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("endIndex: ");
      localStringBuilder.append(paramInt2);
      localStringBuilder.append(" > length: ");
      localStringBuilder.append(len);
      throw new IndexOutOfBoundsException(localStringBuilder.toString());
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Negative beginIndex: ");
    localStringBuilder.append(paramInt1);
    throw new IndexOutOfBoundsException(localStringBuilder.toString());
  }
  
  public String substring(int paramInt1, int paramInt2)
  {
    if (paramInt1 >= 0)
    {
      if (paramInt2 <= len)
      {
        if (paramInt1 <= paramInt2) {
          return new String(buffer, paramInt1, paramInt2 - paramInt1);
        }
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("beginIndex: ");
        localStringBuilder.append(paramInt1);
        localStringBuilder.append(" > endIndex: ");
        localStringBuilder.append(paramInt2);
        throw new IndexOutOfBoundsException(localStringBuilder.toString());
      }
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("endIndex: ");
      localStringBuilder.append(paramInt2);
      localStringBuilder.append(" > length: ");
      localStringBuilder.append(len);
      throw new IndexOutOfBoundsException(localStringBuilder.toString());
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Negative beginIndex: ");
    localStringBuilder.append(paramInt1);
    throw new IndexOutOfBoundsException(localStringBuilder.toString());
  }
  
  public String substringTrimmed(int paramInt1, int paramInt2)
  {
    if (paramInt1 >= 0)
    {
      if (paramInt2 <= len)
      {
        if (paramInt1 <= paramInt2)
        {
          int i;
          for (;;)
          {
            i = paramInt2;
            if (paramInt1 >= paramInt2) {
              break;
            }
            i = paramInt2;
            if (!HTTP.isWhitespace(buffer[paramInt1])) {
              break;
            }
            paramInt1 += 1;
          }
          while ((i > paramInt1) && (HTTP.isWhitespace(buffer[(i - 1)]))) {
            i -= 1;
          }
          return new String(buffer, paramInt1, i - paramInt1);
        }
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("beginIndex: ");
        ((StringBuilder)localObject).append(paramInt1);
        ((StringBuilder)localObject).append(" > endIndex: ");
        ((StringBuilder)localObject).append(paramInt2);
        throw new IndexOutOfBoundsException(((StringBuilder)localObject).toString());
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("endIndex: ");
      ((StringBuilder)localObject).append(paramInt2);
      ((StringBuilder)localObject).append(" > length: ");
      ((StringBuilder)localObject).append(len);
      throw new IndexOutOfBoundsException(((StringBuilder)localObject).toString());
    }
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Negative beginIndex: ");
    ((StringBuilder)localObject).append(paramInt1);
    localObject = new IndexOutOfBoundsException(((StringBuilder)localObject).toString());
    throw ((Throwable)localObject);
  }
  
  public String toString()
  {
    return new String(buffer, 0, len);
  }
}
