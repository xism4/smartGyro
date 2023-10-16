package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.cookie.MalformedCookieException;
import cz.msebera.android.http.cookie.Object;
import cz.msebera.android.http.cookie.SetCookie;
import cz.msebera.android.http.mime.Args;

public class BrowserCompatVersionAttributeHandler
  extends AbstractCookieAttributeHandler
  implements Object
{
  public BrowserCompatVersionAttributeHandler() {}
  
  public String getAttributeName()
  {
    return "version";
  }
  
  public void parse(SetCookie paramSetCookie, String paramString)
  {
    Args.notNull(paramSetCookie, "Cookie");
    int i;
    if (paramString != null) {
      i = 0;
    }
    try
    {
      int j = Integer.parseInt(paramString);
      i = j;
    }
    catch (NumberFormatException paramString)
    {
      for (;;) {}
    }
    paramSetCookie.setVersion(i);
    return;
    throw new MalformedCookieException("Missing value for version attribute");
  }
}
