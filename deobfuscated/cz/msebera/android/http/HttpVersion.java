package cz.msebera.android.http;

import java.io.Serializable;

public final class HttpVersion
  extends ProtocolVersion
  implements Serializable
{
  public static final HttpVersion HTTP_0_9 = new HttpVersion(0, 9);
  public static final HttpVersion HTTP_1_0 = new HttpVersion(1, 0);
  public static final HttpVersion HTTP_1_1 = new HttpVersion(1, 1);
  
  public HttpVersion(int paramInt1, int paramInt2)
  {
    super("HTTP", paramInt1, paramInt2);
  }
  
  public ProtocolVersion forVersion(int paramInt1, int paramInt2)
  {
    if ((paramInt1 == major) && (paramInt2 == minor)) {
      return this;
    }
    if (paramInt1 == 1)
    {
      if (paramInt2 == 0) {
        return HTTP_1_0;
      }
      if (paramInt2 == 1) {
        return HTTP_1_1;
      }
    }
    if ((paramInt1 == 0) && (paramInt2 == 9)) {
      return HTTP_0_9;
    }
    return new HttpVersion(paramInt1, paramInt2);
  }
}
