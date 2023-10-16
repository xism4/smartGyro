package cz.msebera.android.http.message;

import cz.msebera.android.http.ProtocolVersion;
import cz.msebera.android.http.StatusLine;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;
import java.io.Serializable;

public class BasicStatusLine
  implements StatusLine, Cloneable, Serializable
{
  private final ProtocolVersion protoVersion;
  private final String reasonPhrase;
  private final int statusCode;
  
  public BasicStatusLine(ProtocolVersion paramProtocolVersion, int paramInt, String paramString)
  {
    Args.notNull(paramProtocolVersion, "Version");
    protoVersion = ((ProtocolVersion)paramProtocolVersion);
    Args.notNegative(paramInt, "Status code");
    statusCode = paramInt;
    reasonPhrase = paramString;
  }
  
  public Object clone()
  {
    return super.clone();
  }
  
  public ProtocolVersion getProtocolVersion()
  {
    return protoVersion;
  }
  
  public String getReasonPhrase()
  {
    return reasonPhrase;
  }
  
  public int getStatusCode()
  {
    return statusCode;
  }
  
  public String toString()
  {
    return BasicLineFormatter.INSTANCE.formatStatusLine(null, this).toString();
  }
}
