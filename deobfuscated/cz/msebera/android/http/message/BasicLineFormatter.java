package cz.msebera.android.http.message;

import cz.msebera.android.http.FormattedHeader;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.ProtocolVersion;
import cz.msebera.android.http.RequestLine;
import cz.msebera.android.http.StatusLine;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;

public class BasicLineFormatter
  implements LineFormatter
{
  @Deprecated
  public static final BasicLineFormatter DEFAULT = new BasicLineFormatter();
  public static final BasicLineFormatter INSTANCE = new BasicLineFormatter();
  
  public BasicLineFormatter() {}
  
  public CharArrayBuffer appendProtocolVersion(CharArrayBuffer paramCharArrayBuffer, ProtocolVersion paramProtocolVersion)
  {
    Args.notNull(paramProtocolVersion, "Protocol version");
    int i = estimateProtocolVersionLen(paramProtocolVersion);
    if (paramCharArrayBuffer == null) {
      paramCharArrayBuffer = new CharArrayBuffer(i);
    } else {
      paramCharArrayBuffer.ensureCapacity(i);
    }
    paramCharArrayBuffer.append(paramProtocolVersion.getProtocol());
    paramCharArrayBuffer.append('/');
    paramCharArrayBuffer.append(Integer.toString(paramProtocolVersion.getMajor()));
    paramCharArrayBuffer.append('.');
    paramCharArrayBuffer.append(Integer.toString(paramProtocolVersion.getMinor()));
    return paramCharArrayBuffer;
  }
  
  protected void doFormatHeader(CharArrayBuffer paramCharArrayBuffer, Header paramHeader)
  {
    String str = paramHeader.getName();
    paramHeader = paramHeader.getValue();
    int j = str.length() + 2;
    int i = j;
    if (paramHeader != null) {
      i = j + paramHeader.length();
    }
    paramCharArrayBuffer.ensureCapacity(i);
    paramCharArrayBuffer.append(str);
    paramCharArrayBuffer.append(": ");
    if (paramHeader != null) {
      paramCharArrayBuffer.append(paramHeader);
    }
  }
  
  protected void doFormatRequestLine(CharArrayBuffer paramCharArrayBuffer, RequestLine paramRequestLine)
  {
    String str1 = paramRequestLine.getMethod();
    String str2 = paramRequestLine.getUri();
    paramCharArrayBuffer.ensureCapacity(str1.length() + 1 + str2.length() + 1 + estimateProtocolVersionLen(paramRequestLine.getProtocolVersion()));
    paramCharArrayBuffer.append(str1);
    paramCharArrayBuffer.append(' ');
    paramCharArrayBuffer.append(str2);
    paramCharArrayBuffer.append(' ');
    appendProtocolVersion(paramCharArrayBuffer, paramRequestLine.getProtocolVersion());
  }
  
  protected void doFormatStatusLine(CharArrayBuffer paramCharArrayBuffer, StatusLine paramStatusLine)
  {
    int j = estimateProtocolVersionLen(paramStatusLine.getProtocolVersion()) + 1 + 3 + 1;
    String str = paramStatusLine.getReasonPhrase();
    int i = j;
    if (str != null) {
      i = j + str.length();
    }
    paramCharArrayBuffer.ensureCapacity(i);
    appendProtocolVersion(paramCharArrayBuffer, paramStatusLine.getProtocolVersion());
    paramCharArrayBuffer.append(' ');
    paramCharArrayBuffer.append(Integer.toString(paramStatusLine.getStatusCode()));
    paramCharArrayBuffer.append(' ');
    if (str != null) {
      paramCharArrayBuffer.append(str);
    }
  }
  
  protected int estimateProtocolVersionLen(ProtocolVersion paramProtocolVersion)
  {
    return paramProtocolVersion.getProtocol().length() + 4;
  }
  
  public CharArrayBuffer formatHeader(CharArrayBuffer paramCharArrayBuffer, Header paramHeader)
  {
    Args.notNull(paramHeader, "Header");
    if ((paramHeader instanceof FormattedHeader)) {
      return ((FormattedHeader)paramHeader).getBuffer();
    }
    paramCharArrayBuffer = initBuffer(paramCharArrayBuffer);
    doFormatHeader(paramCharArrayBuffer, paramHeader);
    return paramCharArrayBuffer;
  }
  
  public CharArrayBuffer formatRequestLine(CharArrayBuffer paramCharArrayBuffer, RequestLine paramRequestLine)
  {
    Args.notNull(paramRequestLine, "Request line");
    paramCharArrayBuffer = initBuffer(paramCharArrayBuffer);
    doFormatRequestLine(paramCharArrayBuffer, paramRequestLine);
    return paramCharArrayBuffer;
  }
  
  public CharArrayBuffer formatStatusLine(CharArrayBuffer paramCharArrayBuffer, StatusLine paramStatusLine)
  {
    Args.notNull(paramStatusLine, "Status line");
    paramCharArrayBuffer = initBuffer(paramCharArrayBuffer);
    doFormatStatusLine(paramCharArrayBuffer, paramStatusLine);
    return paramCharArrayBuffer;
  }
  
  protected CharArrayBuffer initBuffer(CharArrayBuffer paramCharArrayBuffer)
  {
    if (paramCharArrayBuffer != null)
    {
      paramCharArrayBuffer.clear();
      return paramCharArrayBuffer;
    }
    return new CharArrayBuffer(64);
  }
}
