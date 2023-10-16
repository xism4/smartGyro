package cz.msebera.android.http.message;

public class CharArrayBuffer
{
  private final int buffer;
  private final int len;
  private int pos;
  
  public CharArrayBuffer(int paramInt1, int paramInt2)
  {
    if (paramInt1 >= 0)
    {
      if (paramInt1 <= paramInt2)
      {
        buffer = paramInt1;
        len = paramInt2;
        pos = paramInt1;
        return;
      }
      throw new IndexOutOfBoundsException("Lower bound cannot be greater then upper bound");
    }
    throw new IndexOutOfBoundsException("Lower bound cannot be negative");
  }
  
  public void append(int paramInt)
  {
    if (paramInt >= buffer)
    {
      if (paramInt <= len)
      {
        pos = paramInt;
        return;
      }
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("pos: ");
      localStringBuilder.append(paramInt);
      localStringBuilder.append(" > upperBound: ");
      localStringBuilder.append(len);
      throw new IndexOutOfBoundsException(localStringBuilder.toString());
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("pos: ");
    localStringBuilder.append(paramInt);
    localStringBuilder.append(" < lowerBound: ");
    localStringBuilder.append(buffer);
    throw new IndexOutOfBoundsException(localStringBuilder.toString());
  }
  
  public boolean atEnd()
  {
    return pos >= len;
  }
  
  public int getPos()
  {
    return pos;
  }
  
  public int length()
  {
    return len;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append('[');
    localStringBuilder.append(Integer.toString(buffer));
    localStringBuilder.append('>');
    localStringBuilder.append(Integer.toString(pos));
    localStringBuilder.append('>');
    localStringBuilder.append(Integer.toString(len));
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
}
