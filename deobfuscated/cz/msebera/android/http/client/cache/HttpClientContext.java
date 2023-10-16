package cz.msebera.android.http.client.cache;

import c.a.a.a.a.e;
import c.a.a.a.b.h;
import c.a.a.a.e.b.b;
import c.a.a.a.f.f;
import c.a.a.a.f.k;
import cz.msebera.android.http.auth.AuthState;
import cz.msebera.android.http.client.AuthCache;
import cz.msebera.android.http.client.CookieStore;
import cz.msebera.android.http.client.CredentialsProvider;
import cz.msebera.android.http.client.methods.RequestConfig;
import cz.msebera.android.http.conn.routing.RouteInfo;
import cz.msebera.android.http.cookie.CookieOrigin;
import cz.msebera.android.http.cookie.CookieSpec;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.execchain.HttpCoreContext;
import cz.msebera.android.http.protocol.Lookup;

public class HttpClientContext
  extends HttpCoreContext
{
  public HttpClientContext() {}
  
  public HttpClientContext(HttpContext paramHttpContext)
  {
    super(paramHttpContext);
  }
  
  public static HttpClientContext adapt(HttpContext paramHttpContext)
  {
    if ((paramHttpContext instanceof HttpClientContext)) {
      return (HttpClientContext)paramHttpContext;
    }
    return new HttpClientContext(paramHttpContext);
  }
  
  private Lookup getLookup(String paramString, Class paramClass)
  {
    return (Lookup)getAttribute(paramString, c.a.a.a.d.a.class);
  }
  
  public AuthCache getAuthCache()
  {
    return (AuthCache)getAttribute("http.auth.auth-cache", c.a.a.a.b.a.class);
  }
  
  public Lookup getAuthSchemeRegistry()
  {
    return getLookup("http.authscheme-registry", e.class);
  }
  
  public CookieOrigin getCookieOrigin()
  {
    return (CookieOrigin)getAttribute("http.cookie-origin", f.class);
  }
  
  public CookieSpec getCookieSpec()
  {
    return (CookieSpec)getAttribute("http.cookie-spec", c.a.a.a.f.i.class);
  }
  
  public Lookup getCookieSpecRegistry()
  {
    return getLookup("http.cookiespec-registry", k.class);
  }
  
  public CookieStore getCookieStore()
  {
    return (CookieStore)getAttribute("http.cookie-store", h.class);
  }
  
  public CredentialsProvider getCredentialsProvider()
  {
    return (CredentialsProvider)getAttribute("http.auth.credentials-provider", c.a.a.a.b.i.class);
  }
  
  public RouteInfo getHttpRoute()
  {
    return (RouteInfo)getAttribute("http.route", b.class);
  }
  
  public AuthState getProxyAuthState()
  {
    return (AuthState)getAttribute("http.auth.proxy-scope", c.a.a.a.a.i.class);
  }
  
  public RequestConfig getRequestConfig()
  {
    RequestConfig localRequestConfig = (RequestConfig)getAttribute("http.request-config", c.a.a.a.b.a.a.class);
    if (localRequestConfig != null) {
      return localRequestConfig;
    }
    return RequestConfig.DEFAULT;
  }
  
  public AuthState getTargetAuthState()
  {
    return (AuthState)getAttribute("http.auth.target-scope", c.a.a.a.a.i.class);
  }
  
  public void setAuthCache(AuthCache paramAuthCache)
  {
    setAttribute("http.auth.auth-cache", paramAuthCache);
  }
}
