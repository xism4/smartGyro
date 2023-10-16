package cz.msebera.android.http.client;

import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.auth.AuthScheme;
import cz.msebera.android.http.execchain.HttpContext;
import java.util.Map;

@Deprecated
public abstract interface AuthenticationHandler
{
  public abstract Map getChallenges(HttpResponse paramHttpResponse, HttpContext paramHttpContext);
  
  public abstract boolean isAuthenticationRequested(HttpResponse paramHttpResponse, HttpContext paramHttpContext);
  
  public abstract AuthScheme selectScheme(Map paramMap, HttpResponse paramHttpResponse, HttpContext paramHttpContext);
}
