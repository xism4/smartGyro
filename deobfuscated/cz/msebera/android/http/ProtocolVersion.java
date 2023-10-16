package cz.msebera.android.http;

import cz.msebera.android.http.mime.Args;
import java.io.Serializable;

public class ProtocolVersion
  implements Serializable, Cloneable
{
  protected final int major;
  protected final int minor;
  protected final String protocol;
  
  public ProtocolVersion(String paramString, int paramInt1, int paramInt2)
  {
    Args.notNull(paramString, "Protocol name");
    protocol = ((String)paramString);
    Args.notNegative(paramInt1, "Protocol minor version");
    major = paramInt1;
    Args.notNegative(paramInt2, "Protocol minor version");
    minor = paramInt2;
  }
  
  public Object clone()
  {
    return super.clone();
  }
  
  public int compareToVersion(ProtocolVersion paramProtocolVersion)
  {
    Args.notNull(paramProtocolVersion, "Protocol version");
    Args.check(protocol.equals(protocol), "Versions for different protocols cannot be compared: %s %s", new Object[] { this, paramProtocolVersion });
    int j = getMajor() - paramProtocolVersion.getMajor();
    int i = j;
    if (j == 0) {
      i = getMinor() - paramProtocolVersion.getMinor();
    }
    return i;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof ProtocolVersion)) {
      return false;
    }
    paramObject = (ProtocolVersion)paramObject;
    return (protocol.equals(protocol)) && (major == major) && (minor == minor);
  }
  
  public ProtocolVersion forVersion(int paramInt1, int paramInt2)
  {
    if ((paramInt1 == major) && (paramInt2 == minor)) {
      return this;
    }
    return new ProtocolVersion(protocol, paramInt1, paramInt2);
  }
  
  public final int getMajor()
  {
    return major;
  }
  
  public final int getMinor()
  {
    return minor;
  }
  
  public final String getProtocol()
  {
    return protocol;
  }
  
  public final int hashCode()
  {
    return protocol.hashCode() ^ major * 100000 ^ minor;
  }
  
  public boolean isComparable(ProtocolVersion paramProtocolVersion)
  {
    return (paramProtocolVersion != null) && (protocol.equals(protocol));
  }
  
  public final boolean lessEquals(ProtocolVersion paramProtocolVersion)
  {
    return (isComparable(paramProtocolVersion)) && (compareToVersion(paramProtocolVersion) <= 0);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(protocol);
    localStringBuilder.append('/');
    localStringBuilder.append(Integer.toString(major));
    localStringBuilder.append('.');
    localStringBuilder.append(Integer.toString(minor));
    return localStringBuilder.toString();
  }
}
