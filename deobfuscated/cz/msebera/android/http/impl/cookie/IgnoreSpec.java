package cz.msebera.android.http.impl.cookie;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.cookie.CookieOrigin;
import java.util.Collections;
import java.util.List;

public class IgnoreSpec
  extends CookieSpecBase
{
  public IgnoreSpec() {}
  
  public List formatCookies(List paramList)
  {
    return Collections.emptyList();
  }
  
  public int getVersion()
  {
    return 0;
  }
  
  public Header getVersionHeader()
  {
    return null;
  }
  
  public List parse(Header paramHeader, CookieOrigin paramCookieOrigin)
  {
    return Collections.emptyList();
  }
}
