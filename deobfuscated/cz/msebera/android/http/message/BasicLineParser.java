package cz.msebera.android.http.message;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpVersion;
import cz.msebera.android.http.ParseException;
import cz.msebera.android.http.ProtocolVersion;
import cz.msebera.android.http.StatusLine;
import cz.msebera.android.http.execchain.HTTP;
import cz.msebera.android.http.mime.Args;

public class BasicLineParser
  implements LineParser
{
  @Deprecated
  public static final BasicLineParser DEFAULT = new BasicLineParser();
  public static final BasicLineParser INSTANCE = new BasicLineParser();
  protected final ProtocolVersion protocol;
  
  public BasicLineParser()
  {
    this(null);
  }
  
  public BasicLineParser(ProtocolVersion paramProtocolVersion)
  {
    if (paramProtocolVersion == null) {
      paramProtocolVersion = HttpVersion.HTTP_1_1;
    }
    protocol = paramProtocolVersion;
  }
  
  protected ProtocolVersion createProtocolVersion(int paramInt1, int paramInt2)
  {
    return protocol.forVersion(paramInt1, paramInt2);
  }
  
  protected StatusLine createStatusLine(ProtocolVersion paramProtocolVersion, int paramInt, String paramString)
  {
    return new BasicStatusLine(paramProtocolVersion, paramInt, paramString);
  }
  
  public boolean hasProtocolVersion(cz.msebera.android.http.mime.CharArrayBuffer paramCharArrayBuffer, CharArrayBuffer paramCharArrayBuffer1)
  {
    Args.notNull(paramCharArrayBuffer, "Char array buffer");
    Args.notNull(paramCharArrayBuffer1, "Parser cursor");
    int k = paramCharArrayBuffer1.getPos();
    int j = k;
    paramCharArrayBuffer1 = protocol.getProtocol();
    int m = paramCharArrayBuffer1.length();
    if (paramCharArrayBuffer.length() < m + 4) {
      return false;
    }
    int i;
    if (k < 0)
    {
      i = paramCharArrayBuffer.length() - 4 - m;
    }
    else
    {
      i = j;
      if (k == 0) {
        for (;;)
        {
          i = j;
          if (j >= paramCharArrayBuffer.length()) {
            break;
          }
          i = j;
          if (!HTTP.isWhitespace(paramCharArrayBuffer.charAt(j))) {
            break;
          }
          j += 1;
        }
      }
    }
    k = i + m;
    if (k + 4 > paramCharArrayBuffer.length()) {
      return false;
    }
    boolean bool = true;
    j = 0;
    while ((bool) && (j < m))
    {
      if (paramCharArrayBuffer.charAt(i + j) == paramCharArrayBuffer1.charAt(j)) {
        bool = true;
      } else {
        bool = false;
      }
      j += 1;
    }
    if (bool) {
      return paramCharArrayBuffer.charAt(k) == '/';
    }
    return bool;
  }
  
  public Header parseHeader(cz.msebera.android.http.mime.CharArrayBuffer paramCharArrayBuffer)
  {
    return new BufferedHeader(paramCharArrayBuffer);
  }
  
  public ProtocolVersion parseProtocolVersion(cz.msebera.android.http.mime.CharArrayBuffer paramCharArrayBuffer, CharArrayBuffer paramCharArrayBuffer1)
  {
    Args.notNull(paramCharArrayBuffer, "Char array buffer");
    Args.notNull(paramCharArrayBuffer1, "Parser cursor");
    String str = protocol.getProtocol();
    int n = str.length();
    int m = paramCharArrayBuffer1.getPos();
    int k = paramCharArrayBuffer1.length();
    skipWhitespace(paramCharArrayBuffer, paramCharArrayBuffer1);
    int i1 = paramCharArrayBuffer1.getPos();
    int i2 = i1 + n;
    int i;
    int j;
    if (i2 + 4 <= k)
    {
      i = 1;
      j = 0;
      while ((i != 0) && (j < n))
      {
        if (paramCharArrayBuffer.charAt(i1 + j) == str.charAt(j)) {
          i = 1;
        } else {
          i = 0;
        }
        j += 1;
      }
      j = i;
      if (i != 0) {
        if (paramCharArrayBuffer.charAt(i2) == '/') {
          j = 1;
        } else {
          j = 0;
        }
      }
      if (j != 0)
      {
        j = i1 + (n + 1);
        i = paramCharArrayBuffer.indexOf(46, j, k);
        if (i == -1) {}
      }
    }
    try
    {
      n = Integer.parseInt(paramCharArrayBuffer.substringTrimmed(j, i));
      i1 = i + 1;
      j = paramCharArrayBuffer.indexOf(32, i1, k);
      i = j;
      if (j == -1) {
        i = k;
      }
    }
    catch (NumberFormatException paramCharArrayBuffer1)
    {
      label245:
      for (;;) {}
    }
    try
    {
      j = Integer.parseInt(paramCharArrayBuffer.substringTrimmed(i1, i));
      paramCharArrayBuffer1.append(i);
      return createProtocolVersion(n, j);
    }
    catch (NumberFormatException paramCharArrayBuffer1)
    {
      break label245;
    }
    paramCharArrayBuffer1 = new StringBuilder();
    paramCharArrayBuffer1.append("Invalid protocol minor version number: ");
    paramCharArrayBuffer1.append(paramCharArrayBuffer.substring(m, k));
    throw new ParseException(paramCharArrayBuffer1.toString());
    paramCharArrayBuffer1 = new StringBuilder();
    paramCharArrayBuffer1.append("Invalid protocol major version number: ");
    paramCharArrayBuffer1.append(paramCharArrayBuffer.substring(m, k));
    throw new ParseException(paramCharArrayBuffer1.toString());
    paramCharArrayBuffer1 = new StringBuilder();
    paramCharArrayBuffer1.append("Invalid protocol version number: ");
    paramCharArrayBuffer1.append(paramCharArrayBuffer.substring(m, k));
    throw new ParseException(paramCharArrayBuffer1.toString());
    paramCharArrayBuffer1 = new StringBuilder();
    paramCharArrayBuffer1.append("Not a valid protocol version: ");
    paramCharArrayBuffer1.append(paramCharArrayBuffer.substring(m, k));
    throw new ParseException(paramCharArrayBuffer1.toString());
    paramCharArrayBuffer1 = new StringBuilder();
    paramCharArrayBuffer1.append("Not a valid protocol version: ");
    paramCharArrayBuffer1.append(paramCharArrayBuffer.substring(m, k));
    paramCharArrayBuffer = new ParseException(paramCharArrayBuffer1.toString());
    throw paramCharArrayBuffer;
  }
  
  public StatusLine parseStatusLine(cz.msebera.android.http.mime.CharArrayBuffer paramCharArrayBuffer, CharArrayBuffer paramCharArrayBuffer1)
  {
    Args.notNull(paramCharArrayBuffer, "Char array buffer");
    Args.notNull(paramCharArrayBuffer1, "Parser cursor");
    int m = paramCharArrayBuffer1.getPos();
    int k = paramCharArrayBuffer1.length();
    for (;;)
    {
      try
      {
        localProtocolVersion = parseProtocolVersion(paramCharArrayBuffer, paramCharArrayBuffer1);
        skipWhitespace(paramCharArrayBuffer, paramCharArrayBuffer1);
        int n = paramCharArrayBuffer1.getPos();
        j = paramCharArrayBuffer.indexOf(32, n, k);
        i = j;
        if (j < 0) {
          i = k;
        }
        paramCharArrayBuffer1 = paramCharArrayBuffer.substringTrimmed(n, i);
        j = 0;
        n = paramCharArrayBuffer1.length();
        if (j >= n) {}
      }
      catch (IndexOutOfBoundsException paramCharArrayBuffer1)
      {
        ProtocolVersion localProtocolVersion;
        int j;
        int i;
        boolean bool;
        continue;
      }
      try
      {
        bool = Character.isDigit(paramCharArrayBuffer1.charAt(j));
        if (bool)
        {
          j += 1;
          continue;
        }
        paramCharArrayBuffer1 = new StringBuilder();
        paramCharArrayBuffer1.append("Status line contains invalid status code: ");
        paramCharArrayBuffer1.append(paramCharArrayBuffer.substring(m, k));
        paramCharArrayBuffer1 = new ParseException(paramCharArrayBuffer1.toString());
        throw paramCharArrayBuffer1;
      }
      catch (IndexOutOfBoundsException paramCharArrayBuffer1)
      {
        continue;
      }
      try
      {
        j = Integer.parseInt(paramCharArrayBuffer1);
        if (i >= k) {}
      }
      catch (NumberFormatException paramCharArrayBuffer1)
      {
        continue;
      }
      catch (IndexOutOfBoundsException paramCharArrayBuffer1)
      {
        continue;
      }
      try
      {
        paramCharArrayBuffer1 = paramCharArrayBuffer.substringTrimmed(i, k);
        continue;
        paramCharArrayBuffer1 = "";
        paramCharArrayBuffer1 = createStatusLine(localProtocolVersion, j, paramCharArrayBuffer1);
        return paramCharArrayBuffer1;
      }
      catch (IndexOutOfBoundsException paramCharArrayBuffer1) {}
    }
    paramCharArrayBuffer1 = new StringBuilder();
    paramCharArrayBuffer1.append("Status line contains invalid status code: ");
    paramCharArrayBuffer1.append(paramCharArrayBuffer.substring(m, k));
    paramCharArrayBuffer1 = new ParseException(paramCharArrayBuffer1.toString());
    throw paramCharArrayBuffer1;
    paramCharArrayBuffer1 = new StringBuilder();
    paramCharArrayBuffer1.append("Invalid status line: ");
    paramCharArrayBuffer1.append(paramCharArrayBuffer.substring(m, k));
    paramCharArrayBuffer = new ParseException(paramCharArrayBuffer1.toString());
    throw paramCharArrayBuffer;
  }
  
  protected void skipWhitespace(cz.msebera.android.http.mime.CharArrayBuffer paramCharArrayBuffer, CharArrayBuffer paramCharArrayBuffer1)
  {
    int i = paramCharArrayBuffer1.getPos();
    int j = paramCharArrayBuffer1.length();
    while ((i < j) && (HTTP.isWhitespace(paramCharArrayBuffer.charAt(i)))) {
      i += 1;
    }
    paramCharArrayBuffer1.append(i);
  }
}
