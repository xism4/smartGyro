package cz.msebera.android.http.message;

import cz.msebera.android.http.ProtocolVersion;
import cz.msebera.android.http.RequestLine;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;
import java.io.Serializable;

public class BasicRequestLine
  implements RequestLine, Cloneable, Serializable
{
  private final String method;
  private final ProtocolVersion protoversion;
  private final String uri;
  
  public BasicRequestLine(String paramString1, String paramString2, ProtocolVersion paramProtocolVersion)
  {
    Args.notNull(paramString1, "Method");
    method = ((String)paramString1);
    Args.notNull(paramString2, "URI");
    uri = ((String)paramString2);
    Args.notNull(paramProtocolVersion, "Version");
    protoversion = ((ProtocolVersion)paramProtocolVersion);
  }
  
  public Object clone()
  {
    return super.clone();
  }
  
  public String getMethod()
  {
    return method;
  }
  
  public ProtocolVersion getProtocolVersion()
  {
    return protoversion;
  }
  
  public String getUri()
  {
    return uri;
  }
  
  public String toString()
  {
    return BasicLineFormatter.INSTANCE.formatRequestLine(null, this).toString();
  }
}
