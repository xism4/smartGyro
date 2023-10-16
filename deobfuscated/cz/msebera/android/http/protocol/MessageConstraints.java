package cz.msebera.android.http.protocol;

public class MessageConstraints
  implements Cloneable
{
  public static final MessageConstraints DEFAULT = new Builder().build();
  private final int maxHeaderCount;
  private final int maxLineLength;
  
  MessageConstraints(int paramInt1, int paramInt2)
  {
    maxHeaderCount = paramInt1;
    maxLineLength = paramInt2;
  }
  
  public static Builder custom()
  {
    return new Builder();
  }
  
  protected MessageConstraints clone()
  {
    return (MessageConstraints)super.clone();
  }
  
  public int getMaxHeaderCount()
  {
    return maxHeaderCount;
  }
  
  public int getMaxLineLength()
  {
    return maxLineLength;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[maxLineLength=");
    localStringBuilder.append(maxHeaderCount);
    localStringBuilder.append(", maxHeaderCount=");
    localStringBuilder.append(maxLineLength);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
  
  public class Builder
  {
    private int maxHeaderCount = -1;
    private int maxLineLength = -1;
    
    Builder() {}
    
    public MessageConstraints build()
    {
      return new MessageConstraints(maxLineLength, maxHeaderCount);
    }
    
    public Builder setMaxHeaderCount(int paramInt)
    {
      maxHeaderCount = paramInt;
      return this;
    }
    
    public Builder setMaxLineLength(int paramInt)
    {
      maxLineLength = paramInt;
      return this;
    }
  }
}
