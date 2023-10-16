package cz.msebera.android.http.conn.ssl;

import javax.security.auth.x500.X500Principal;

public final class DistinguishedNameParser
{
  private int beg;
  private char[] chars;
  private int cur;
  private final String dn;
  private int end;
  private final int length;
  private int pos;
  
  public DistinguishedNameParser(X500Principal paramX500Principal)
  {
    dn = paramX500Principal.getName("RFC2253");
    length = dn.length();
  }
  
  private String escapedAV()
  {
    int i = pos;
    beg = i;
    end = i;
    do
    {
      i = pos;
      if (i >= length)
      {
        arrayOfChar = chars;
        i = beg;
        return new String(arrayOfChar, i, end - i);
      }
      arrayOfChar = chars;
      int j = arrayOfChar[i];
      if (j != 32)
      {
        if (j != 59) {
          if (j != 92)
          {
            if ((j != 43) && (j != 44))
            {
              j = end;
              end = (j + 1);
              arrayOfChar[j] = arrayOfChar[i];
            }
          }
          else {
            for (;;)
            {
              pos = (i + 1);
              break;
              i = end;
              end = (i + 1);
              arrayOfChar[i] = getEscaped();
              i = pos;
            }
          }
        }
        arrayOfChar = chars;
        i = beg;
        return new String(arrayOfChar, i, end - i);
      }
      j = end;
      cur = j;
      pos = (i + 1);
      end = (j + 1);
      arrayOfChar[j] = ' ';
      for (;;)
      {
        i = pos;
        if (i >= length) {
          break;
        }
        arrayOfChar = chars;
        if (arrayOfChar[i] != ' ') {
          break;
        }
        j = end;
        end = (j + 1);
        arrayOfChar[j] = ' ';
        pos = (i + 1);
      }
      i = pos;
      if (i == length) {
        break;
      }
      arrayOfChar = chars;
    } while ((arrayOfChar[i] != ',') && (arrayOfChar[i] != '+') && (arrayOfChar[i] != ';'));
    char[] arrayOfChar = chars;
    i = beg;
    return new String(arrayOfChar, i, cur - i);
  }
  
  private int getByte(int paramInt)
  {
    int i = paramInt + 1;
    if (i < length)
    {
      paramInt = chars[paramInt];
      if ((paramInt >= 48) && (paramInt <= 57))
      {
        paramInt -= 48;
      }
      else if ((paramInt >= 97) && (paramInt <= 102))
      {
        paramInt -= 87;
      }
      else
      {
        if ((paramInt < 65) || (paramInt > 70)) {
          break label182;
        }
        paramInt -= 55;
      }
      i = chars[i];
      if ((i >= 48) && (i <= 57))
      {
        i -= 48;
      }
      else if ((i >= 97) && (i <= 102))
      {
        i -= 87;
      }
      else
      {
        if ((i < 65) || (i > 70)) {
          break label146;
        }
        i -= 55;
      }
      return (paramInt << 4) + i;
      label146:
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Malformed DN: ");
      localStringBuilder.append(dn);
      throw new IllegalStateException(localStringBuilder.toString());
      label182:
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Malformed DN: ");
      localStringBuilder.append(dn);
      throw new IllegalStateException(localStringBuilder.toString());
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Malformed DN: ");
    localStringBuilder.append(dn);
    throw new IllegalStateException(localStringBuilder.toString());
  }
  
  private char getEscaped()
  {
    pos += 1;
    int i = pos;
    if (i != length)
    {
      i = chars[i];
      if ((i != 32) && (i != 37) && (i != 92) && (i != 95) && (i != 34) && (i != 35)) {
        switch (i)
        {
        default: 
          switch (i)
          {
          default: 
            return getUTF8();
          }
          break;
        }
      }
      return chars[pos];
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Unexpected end of DN: ");
    localStringBuilder.append(dn);
    throw new IllegalStateException(localStringBuilder.toString());
  }
  
  private char getUTF8()
  {
    int i = getByte(pos);
    pos += 1;
    if (i < 128) {
      return (char)i;
    }
    if ((i >= 192) && (i <= 247))
    {
      int j;
      if (i <= 223)
      {
        i &= 0x1F;
        j = 1;
      }
      else if (i <= 239)
      {
        j = 2;
        i &= 0xF;
      }
      else
      {
        j = 3;
        i &= 0x7;
      }
      int m = 0;
      int k = i;
      i = m;
      while (i < j)
      {
        pos += 1;
        m = pos;
        if (m != length)
        {
          if (chars[m] != '\\') {
            return '?';
          }
          pos = (m + 1);
          m = getByte(pos);
          pos += 1;
          if ((m & 0xC0) != 128) {
            return '?';
          }
          k = (k << 6) + (m & 0x3F);
          i += 1;
        }
        else
        {
          return '?';
        }
      }
      return (char)k;
    }
    return '?';
  }
  
  private String hexAV()
  {
    int i = pos;
    if (i + 4 < length)
    {
      beg = i;
      for (;;)
      {
        pos = (i + 1);
        i = pos;
        if (i == length) {
          break;
        }
        localObject = chars;
        if ((localObject[i] == '+') || (localObject[i] == ',') || (localObject[i] == ';')) {
          break;
        }
        if (localObject[i] == ' ')
        {
          end = i;
          for (;;)
          {
            pos = (i + 1);
            i = pos;
            if ((i >= length) || (chars[i] != ' ')) {
              break;
            }
          }
        }
        if ((localObject[i] >= 'A') && (localObject[i] <= 'F')) {
          localObject[i] = ((char)(localObject[i] + ' '));
        }
        i = pos;
      }
      end = pos;
      i = end;
      int j = beg;
      int k = i - j;
      if ((k >= 5) && ((k & 0x1) != 0))
      {
        localObject = new byte[k / 2];
        i = 0;
        j += 1;
        while (i < localObject.length)
        {
          localObject[i] = ((byte)getByte(j));
          j += 2;
          i += 1;
        }
        return new String(chars, beg, k);
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Unexpected end of DN: ");
      ((StringBuilder)localObject).append(dn);
      throw new IllegalStateException(((StringBuilder)localObject).toString());
    }
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Unexpected end of DN: ");
    ((StringBuilder)localObject).append(dn);
    localObject = new IllegalStateException(((StringBuilder)localObject).toString());
    throw ((Throwable)localObject);
  }
  
  private String nextAT()
  {
    for (;;)
    {
      i = pos;
      if ((i >= length) || (chars[i] != ' ')) {
        break;
      }
      pos = (i + 1);
    }
    int i = pos;
    if (i == length) {
      return null;
    }
    beg = i;
    do
    {
      pos = (i + 1);
      i = pos;
      if (i >= length) {
        break;
      }
      localObject = chars;
    } while ((localObject[i] != '=') && (localObject[i] != ' '));
    i = pos;
    if (i < length)
    {
      end = i;
      if (chars[i] == ' ')
      {
        for (;;)
        {
          i = pos;
          if (i >= length) {
            break;
          }
          localObject = chars;
          if ((localObject[i] == '=') || (localObject[i] != ' ')) {
            break;
          }
          pos = (i + 1);
        }
        localObject = chars;
        i = pos;
        if ((localObject[i] != '=') || (i == length))
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("Unexpected end of DN: ");
          ((StringBuilder)localObject).append(dn);
          throw new IllegalStateException(((StringBuilder)localObject).toString());
        }
      }
      i = pos;
      do
      {
        pos = (i + 1);
        i = pos;
      } while ((i < length) && (chars[i] == ' '));
      i = end;
      int j = beg;
      if (i - j > 4)
      {
        localObject = chars;
        if ((localObject[(j + 3)] == '.') && ((localObject[j] == 'O') || (localObject[j] == 'o')))
        {
          localObject = chars;
          i = beg;
          if ((localObject[(i + 1)] == 'I') || (localObject[(i + 1)] == 'i'))
          {
            localObject = chars;
            i = beg;
            if ((localObject[(i + 2)] == 'D') || (localObject[(i + 2)] == 'd')) {
              beg += 4;
            }
          }
        }
      }
      localObject = chars;
      i = beg;
      return new String((char[])localObject, i, end - i);
    }
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Unexpected end of DN: ");
    ((StringBuilder)localObject).append(dn);
    localObject = new IllegalStateException(((StringBuilder)localObject).toString());
    throw ((Throwable)localObject);
  }
  
  private String quotedAV()
  {
    pos += 1;
    beg = pos;
    for (int i = beg;; i = end + 1)
    {
      end = i;
      i = pos;
      if (i == length) {
        break;
      }
      localObject = chars;
      if (localObject[i] == '"')
      {
        do
        {
          pos = (i + 1);
          i = pos;
        } while ((i < length) && (chars[i] == ' '));
        localObject = chars;
        i = beg;
        return new String((char[])localObject, i, end - i);
      }
      if (localObject[i] == '\\') {
        localObject[end] = getEscaped();
      } else {
        localObject[end] = localObject[i];
      }
      pos += 1;
    }
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Unexpected end of DN: ");
    ((StringBuilder)localObject).append(dn);
    localObject = new IllegalStateException(((StringBuilder)localObject).toString());
    throw ((Throwable)localObject);
  }
  
  public String findMostSpecific(String paramString)
  {
    pos = 0;
    beg = 0;
    end = 0;
    cur = 0;
    chars = dn.toCharArray();
    Object localObject1 = nextAT();
    Object localObject2 = localObject1;
    if (localObject1 == null) {
      return null;
    }
    do
    {
      int i = pos;
      if (i == length) {
        return null;
      }
      i = chars[i];
      if (i != 34)
      {
        if (i != 35)
        {
          if ((i != 43) && (i != 44) && (i != 59)) {
            localObject1 = escapedAV();
          } else {
            localObject1 = "";
          }
        }
        else {
          localObject1 = hexAV();
        }
      }
      else {
        localObject1 = quotedAV();
      }
      if (paramString.equalsIgnoreCase(localObject2)) {
        return localObject1;
      }
      i = pos;
      if (i >= length) {
        return null;
      }
      localObject1 = chars;
      if ((localObject1[i] != ',') && (localObject1[i] != ';') && (localObject1[i] != '+'))
      {
        paramString = new StringBuilder();
        paramString.append("Malformed DN: ");
        paramString.append(dn);
        throw new IllegalStateException(paramString.toString());
      }
      pos += 1;
      localObject1 = nextAT();
      localObject2 = localObject1;
    } while (localObject1 != null);
    paramString = new StringBuilder();
    paramString.append("Malformed DN: ");
    paramString.append(dn);
    paramString = new IllegalStateException(paramString.toString());
    throw paramString;
  }
}
