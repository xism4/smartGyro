package cz.msebera.android.http.message;

import java.util.BitSet;

public class TokenParser
{
  public static final TokenParser INSTANCE = new TokenParser();
  
  public TokenParser() {}
  
  public static BitSet INIT_BITSET(int... paramVarArgs)
  {
    BitSet localBitSet = new BitSet();
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      localBitSet.set(paramVarArgs[i]);
      i += 1;
    }
    return localBitSet;
  }
  
  public static boolean isWhitespace(char paramChar)
  {
    return (paramChar == ' ') || (paramChar == '\t') || (paramChar == '\r') || (paramChar == '\n');
  }
  
  public void copyContent(cz.msebera.android.http.mime.CharArrayBuffer paramCharArrayBuffer, CharArrayBuffer paramCharArrayBuffer1, BitSet paramBitSet, StringBuilder paramStringBuilder)
  {
    int j = paramCharArrayBuffer1.getPos();
    int i = paramCharArrayBuffer1.getPos();
    int k = paramCharArrayBuffer1.length();
    while (i < k)
    {
      char c = paramCharArrayBuffer.charAt(i);
      if (((paramBitSet != null) && (paramBitSet.get(c))) || (isWhitespace(c))) {
        break;
      }
      j += 1;
      paramStringBuilder.append(c);
      i += 1;
    }
    paramCharArrayBuffer1.append(j);
  }
  
  public void copyQuotedContent(cz.msebera.android.http.mime.CharArrayBuffer paramCharArrayBuffer, CharArrayBuffer paramCharArrayBuffer1, StringBuilder paramStringBuilder)
  {
    if (paramCharArrayBuffer1.atEnd()) {
      return;
    }
    int i = paramCharArrayBuffer1.getPos();
    int j = paramCharArrayBuffer1.getPos();
    int n = paramCharArrayBuffer1.length();
    if (paramCharArrayBuffer.charAt(i) != '"') {
      return;
    }
    int k = j + 1;
    i += 1;
    for (int m = 0;; m = j)
    {
      j = i;
      if (k >= n) {
        break;
      }
      char c = paramCharArrayBuffer.charAt(k);
      if (m != 0)
      {
        if ((c != '"') && (c != '\\')) {
          paramStringBuilder.append('\\');
        }
        paramStringBuilder.append(c);
        j = 0;
      }
      else
      {
        if (c == '"')
        {
          j = i + 1;
          break;
        }
        if (c == '\\')
        {
          j = 1;
        }
        else
        {
          j = m;
          if (c != '\r')
          {
            j = m;
            if (c != '\n')
            {
              paramStringBuilder.append(c);
              j = m;
            }
          }
        }
      }
      k += 1;
      i += 1;
    }
    paramCharArrayBuffer1.append(j);
  }
  
  public String parseToken(cz.msebera.android.http.mime.CharArrayBuffer paramCharArrayBuffer, CharArrayBuffer paramCharArrayBuffer1, BitSet paramBitSet)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (;;)
    {
      for (int i = 0;; i = 1)
      {
        if (paramCharArrayBuffer1.atEnd()) {
          break label98;
        }
        char c = paramCharArrayBuffer.charAt(paramCharArrayBuffer1.getPos());
        if ((paramBitSet != null) && (paramBitSet.get(c))) {
          break label98;
        }
        if (!isWhitespace(c)) {
          break;
        }
        skipWhiteSpace(paramCharArrayBuffer, paramCharArrayBuffer1);
      }
      if ((i != 0) && (localStringBuilder.length() > 0)) {
        localStringBuilder.append(' ');
      }
      copyContent(paramCharArrayBuffer, paramCharArrayBuffer1, paramBitSet, localStringBuilder);
    }
    label98:
    return localStringBuilder.toString();
  }
  
  public String parseValue(cz.msebera.android.http.mime.CharArrayBuffer paramCharArrayBuffer, CharArrayBuffer paramCharArrayBuffer1, BitSet paramBitSet)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (;;)
    {
      char c;
      for (int i = 0;; i = 1)
      {
        if (paramCharArrayBuffer1.atEnd()) {
          break label137;
        }
        c = paramCharArrayBuffer.charAt(paramCharArrayBuffer1.getPos());
        if ((paramBitSet != null) && (paramBitSet.get(c))) {
          break label137;
        }
        if (!isWhitespace(c)) {
          break;
        }
        skipWhiteSpace(paramCharArrayBuffer, paramCharArrayBuffer1);
      }
      if (c == '"')
      {
        if ((i != 0) && (localStringBuilder.length() > 0)) {
          localStringBuilder.append(' ');
        }
        copyQuotedContent(paramCharArrayBuffer, paramCharArrayBuffer1, localStringBuilder);
      }
      else
      {
        if ((i != 0) && (localStringBuilder.length() > 0)) {
          localStringBuilder.append(' ');
        }
        parseValue(paramCharArrayBuffer, paramCharArrayBuffer1, paramBitSet, localStringBuilder);
      }
    }
    label137:
    return localStringBuilder.toString();
  }
  
  public void parseValue(cz.msebera.android.http.mime.CharArrayBuffer paramCharArrayBuffer, CharArrayBuffer paramCharArrayBuffer1, BitSet paramBitSet, StringBuilder paramStringBuilder)
  {
    int j = paramCharArrayBuffer1.getPos();
    int i = paramCharArrayBuffer1.getPos();
    int k = paramCharArrayBuffer1.length();
    while (i < k)
    {
      char c = paramCharArrayBuffer.charAt(i);
      if (((paramBitSet != null) && (paramBitSet.get(c))) || (isWhitespace(c)) || (c == '"')) {
        break;
      }
      j += 1;
      paramStringBuilder.append(c);
      i += 1;
    }
    paramCharArrayBuffer1.append(j);
  }
  
  public void skipWhiteSpace(cz.msebera.android.http.mime.CharArrayBuffer paramCharArrayBuffer, CharArrayBuffer paramCharArrayBuffer1)
  {
    int j = paramCharArrayBuffer1.getPos();
    int i = paramCharArrayBuffer1.getPos();
    int k = paramCharArrayBuffer1.length();
    while ((i < k) && (isWhitespace(paramCharArrayBuffer.charAt(i))))
    {
      j += 1;
      i += 1;
    }
    paramCharArrayBuffer1.append(j);
  }
}
