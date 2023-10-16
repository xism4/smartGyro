package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.cookie.MalformedCookieException;
import cz.msebera.android.http.cookie.Object;
import cz.msebera.android.http.cookie.SetCookie;
import cz.msebera.android.http.mime.Args;
import java.util.Date;

public class BasicCommentHandler
  extends AbstractCookieAttributeHandler
  implements Object
{
  public BasicCommentHandler() {}
  
  public String getAttributeName()
  {
    return "max-age";
  }
  
  public void parse(SetCookie paramSetCookie, String paramString)
  {
    Args.notNull(paramSetCookie, "Cookie");
    if (paramString != null) {}
    try
    {
      int i = Integer.parseInt(paramString);
      if (i >= 0)
      {
        paramSetCookie.setExpiryDate(new Date(System.currentTimeMillis() + i * 1000L));
        return;
      }
      paramSetCookie = new StringBuilder();
      paramSetCookie.append("Negative 'max-age' attribute: ");
      paramSetCookie.append(paramString);
      throw new MalformedCookieException(paramSetCookie.toString());
    }
    catch (NumberFormatException paramSetCookie)
    {
      for (;;) {}
    }
    paramSetCookie = new StringBuilder();
    paramSetCookie.append("Invalid 'max-age' attribute: ");
    paramSetCookie.append(paramString);
    throw new MalformedCookieException(paramSetCookie.toString());
    throw new MalformedCookieException("Missing value for 'max-age' attribute");
  }
}
