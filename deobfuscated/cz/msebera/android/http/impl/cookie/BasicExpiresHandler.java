package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.client.ssl.DateUtils;
import cz.msebera.android.http.cookie.MalformedCookieException;
import cz.msebera.android.http.cookie.Object;
import cz.msebera.android.http.cookie.SetCookie;
import cz.msebera.android.http.mime.Args;
import java.util.Date;

public class BasicExpiresHandler
  extends AbstractCookieAttributeHandler
  implements Object
{
  private final String[] datepatterns;
  
  public BasicExpiresHandler(String[] paramArrayOfString)
  {
    Args.notNull(paramArrayOfString, "Array of date patterns");
    datepatterns = paramArrayOfString;
  }
  
  public String getAttributeName()
  {
    return "expires";
  }
  
  public void parse(SetCookie paramSetCookie, String paramString)
  {
    Args.notNull(paramSetCookie, "Cookie");
    if (paramString != null)
    {
      Date localDate = DateUtils.parseDate(paramString, datepatterns);
      if (localDate != null)
      {
        paramSetCookie.setExpiryDate(localDate);
        return;
      }
      paramSetCookie = new StringBuilder();
      paramSetCookie.append("Invalid 'expires' attribute: ");
      paramSetCookie.append(paramString);
      throw new MalformedCookieException(paramSetCookie.toString());
    }
    throw new MalformedCookieException("Missing value for 'expires' attribute");
  }
}
