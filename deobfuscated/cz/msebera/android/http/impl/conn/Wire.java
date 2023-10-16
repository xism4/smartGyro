package cz.msebera.android.http.impl.conn;

import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.mime.Args;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Wire
{
  private final String id;
  public HttpClientAndroidLog log;
  
  public Wire(HttpClientAndroidLog paramHttpClientAndroidLog)
  {
    this(paramHttpClientAndroidLog, "");
  }
  
  public Wire(HttpClientAndroidLog paramHttpClientAndroidLog, String paramString)
  {
    log = paramHttpClientAndroidLog;
    id = paramString;
  }
  
  private void wire(String paramString, InputStream paramInputStream)
  {
    StringBuilder localStringBuilder1 = new StringBuilder();
    int i = paramInputStream.read();
    if (i != -1)
    {
      if (i == 13) {}
      for (Object localObject = "[\\r]";; localObject = "]")
      {
        localStringBuilder1.append((String)localObject);
        break;
        if (i == 10)
        {
          localStringBuilder1.append("[\\n]\"");
          localStringBuilder1.insert(0, "\"");
          localStringBuilder1.insert(0, paramString);
          localObject = log;
          StringBuilder localStringBuilder2 = new StringBuilder();
          localStringBuilder2.append(id);
          localStringBuilder2.append(" ");
          localStringBuilder2.append(localStringBuilder1.toString());
          ((HttpClientAndroidLog)localObject).debug(localStringBuilder2.toString());
          localStringBuilder1.setLength(0);
          break;
        }
        if ((i >= 32) && (i <= 127))
        {
          localStringBuilder1.append((char)i);
          break;
        }
        localStringBuilder1.append("[0x");
        localStringBuilder1.append(Integer.toHexString(i));
      }
    }
    if (localStringBuilder1.length() > 0)
    {
      localStringBuilder1.append('"');
      localStringBuilder1.insert(0, '"');
      localStringBuilder1.insert(0, paramString);
      paramString = log;
      paramInputStream = new StringBuilder();
      paramInputStream.append(id);
      paramInputStream.append(" ");
      paramInputStream.append(localStringBuilder1.toString());
      paramString.debug(paramInputStream.toString());
    }
  }
  
  public boolean enabled()
  {
    return log.isDebugEnabled();
  }
  
  public void input(int paramInt)
  {
    input(new byte[] { (byte)paramInt });
  }
  
  public void input(byte[] paramArrayOfByte)
  {
    Args.notNull(paramArrayOfByte, "Input");
    wire("<< ", new ByteArrayInputStream(paramArrayOfByte));
  }
  
  public void input(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    Args.notNull(paramArrayOfByte, "Input");
    wire("<< ", new ByteArrayInputStream(paramArrayOfByte, paramInt1, paramInt2));
  }
  
  public void output(int paramInt)
  {
    output(new byte[] { (byte)paramInt });
  }
  
  public void output(byte[] paramArrayOfByte)
  {
    Args.notNull(paramArrayOfByte, "Output");
    wire(">> ", new ByteArrayInputStream(paramArrayOfByte));
  }
  
  public void output(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    Args.notNull(paramArrayOfByte, "Output");
    wire(">> ", new ByteArrayInputStream(paramArrayOfByte, paramInt1, paramInt2));
  }
}
