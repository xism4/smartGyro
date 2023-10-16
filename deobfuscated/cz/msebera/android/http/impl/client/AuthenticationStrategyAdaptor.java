package cz.msebera.android.http.impl.client;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.auth.AuthOption;
import cz.msebera.android.http.auth.AuthScheme;
import cz.msebera.android.http.auth.AuthScope;
import cz.msebera.android.http.auth.AuthenticationException;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.client.AuthCache;
import cz.msebera.android.http.client.AuthenticationHandler;
import cz.msebera.android.http.client.AuthenticationStrategy;
import cz.msebera.android.http.client.CredentialsProvider;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.mime.Args;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;

@Deprecated
class AuthenticationStrategyAdaptor
  implements AuthenticationStrategy
{
  private final AuthenticationHandler handler;
  public HttpClientAndroidLog log;
  
  private boolean isCachable(AuthScheme paramAuthScheme)
  {
    if (paramAuthScheme != null)
    {
      if (!paramAuthScheme.isComplete()) {
        return false;
      }
      paramAuthScheme = paramAuthScheme.getSchemeName();
      if ((paramAuthScheme.equalsIgnoreCase("Basic")) || (paramAuthScheme.equalsIgnoreCase("Digest"))) {
        return true;
      }
    }
    return false;
  }
  
  public void authFailed(HttpHost paramHttpHost, AuthScheme paramAuthScheme, HttpContext paramHttpContext)
  {
    paramHttpContext = (AuthCache)paramHttpContext.getAttribute("http.auth.auth-cache");
    if (paramHttpContext == null) {
      return;
    }
    if (log.isDebugEnabled())
    {
      HttpClientAndroidLog localHttpClientAndroidLog = log;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Removing from cache '");
      localStringBuilder.append(paramAuthScheme.getSchemeName());
      localStringBuilder.append("' auth scheme for ");
      localStringBuilder.append(paramHttpHost);
      localHttpClientAndroidLog.debug(localStringBuilder.toString());
    }
    paramHttpContext.remove(paramHttpHost);
  }
  
  public void authSucceeded(HttpHost paramHttpHost, AuthScheme paramAuthScheme, HttpContext paramHttpContext)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a6 = a5\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n");
  }
  
  public Map getChallenges(HttpHost paramHttpHost, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    return handler.getChallenges(paramHttpResponse, paramHttpContext);
  }
  
  public AuthenticationHandler getHandler()
  {
    return handler;
  }
  
  public boolean isAuthenticationRequested(HttpHost paramHttpHost, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    return handler.isAuthenticationRequested(paramHttpResponse, paramHttpContext);
  }
  
  public Queue select(Map paramMap, HttpHost paramHttpHost, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    Args.notNull(paramMap, "Map of auth challenges");
    Args.notNull(paramHttpHost, "Host");
    Args.notNull(paramHttpResponse, "HTTP response");
    Args.notNull(paramHttpContext, "HTTP context");
    LinkedList localLinkedList = new LinkedList();
    CredentialsProvider localCredentialsProvider = (CredentialsProvider)paramHttpContext.getAttribute("http.auth.credentials-provider");
    if (localCredentialsProvider == null)
    {
      log.debug("Credentials provider not set in the context");
      return localLinkedList;
    }
    AuthenticationHandler localAuthenticationHandler = handler;
    try
    {
      paramHttpResponse = localAuthenticationHandler.selectScheme(paramMap, paramHttpResponse, paramHttpContext);
      paramHttpResponse.processChallenge((Header)paramMap.get(paramHttpResponse.getSchemeName().toLowerCase(Locale.ROOT)));
      paramMap = localCredentialsProvider.getCredentials(new AuthScope(paramHttpHost.getHostName(), paramHttpHost.getPort(), paramHttpResponse.getRealm(), paramHttpResponse.getSchemeName()));
      if (paramMap != null)
      {
        localLinkedList.add(new AuthOption(paramHttpResponse, paramMap));
        return localLinkedList;
      }
    }
    catch (AuthenticationException paramMap)
    {
      if (log.isWarnEnabled()) {
        log.warn(paramMap.getMessage(), paramMap);
      }
    }
    return localLinkedList;
  }
}
