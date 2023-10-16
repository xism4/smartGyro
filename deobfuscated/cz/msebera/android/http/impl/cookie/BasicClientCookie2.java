package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.cookie.SetCookie2;
import java.util.Date;

public class BasicClientCookie2
  extends BasicClientCookie
  implements SetCookie2
{
  private String commentURL;
  private boolean discard;
  private int[] ports;
  
  public BasicClientCookie2(String paramString1, String paramString2)
  {
    super(paramString1, paramString2);
  }
  
  public Object clone()
  {
    BasicClientCookie2 localBasicClientCookie2 = (BasicClientCookie2)super.clone();
    int[] arrayOfInt = ports;
    if (arrayOfInt != null) {
      ports = ((int[])arrayOfInt.clone());
    }
    return localBasicClientCookie2;
  }
  
  public int[] getPorts()
  {
    return ports;
  }
  
  public boolean isExpired(Date paramDate)
  {
    return (discard) || (super.isExpired(paramDate));
  }
  
  public void setCommentURL(String paramString)
  {
    commentURL = paramString;
  }
  
  public void setDiscard(boolean paramBoolean)
  {
    discard = paramBoolean;
  }
  
  public void setPorts(int[] paramArrayOfInt)
  {
    ports = paramArrayOfInt;
  }
}
