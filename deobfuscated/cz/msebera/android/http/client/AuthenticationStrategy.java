package cz.msebera.android.http.client;

import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.auth.AuthScheme;
import cz.msebera.android.http.execchain.HttpContext;
import java.util.Map;
import java.util.Queue;

public abstract interface AuthenticationStrategy
{
  public abstract void authFailed(HttpHost paramHttpHost, AuthScheme paramAuthScheme, HttpContext paramHttpContext);
  
  public abstract void authSucceeded(HttpHost paramHttpHost, AuthScheme paramAuthScheme, HttpContext paramHttpContext);
  
  public abstract Map getChallenges(HttpHost paramHttpHost, HttpResponse paramHttpResponse, HttpContext paramHttpContext);
  
  public abstract boolean isAuthenticationRequested(HttpHost paramHttpHost, HttpResponse paramHttpResponse, HttpContext paramHttpContext);
  
  public abstract Queue select(Map paramMap, HttpHost paramHttpHost, HttpResponse paramHttpResponse, HttpContext paramHttpContext);
}
