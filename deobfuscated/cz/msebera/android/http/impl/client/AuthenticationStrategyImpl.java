package cz.msebera.android.http.impl.client;

import cz.msebera.android.http.FormattedHeader;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.StatusLine;
import cz.msebera.android.http.auth.AuthOption;
import cz.msebera.android.http.auth.AuthScheme;
import cz.msebera.android.http.auth.AuthSchemeProvider;
import cz.msebera.android.http.auth.AuthScope;
import cz.msebera.android.http.auth.Credentials;
import cz.msebera.android.http.auth.MalformedChallengeException;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.client.AuthCache;
import cz.msebera.android.http.client.AuthenticationStrategy;
import cz.msebera.android.http.client.CredentialsProvider;
import cz.msebera.android.http.client.cache.HttpClientContext;
import cz.msebera.android.http.client.methods.RequestConfig;
import cz.msebera.android.http.execchain.HTTP;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;
import cz.msebera.android.http.protocol.Lookup;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;

abstract class AuthenticationStrategyImpl
  implements AuthenticationStrategy
{
  private static final List<String> DEFAULT_SCHEME_PRIORITY = Collections.unmodifiableList(Arrays.asList(new String[] { "Negotiate", "Kerberos", "NTLM", "Digest", "Basic" }));
  private final int challengeCode;
  private final String headerName;
  public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());
  
  AuthenticationStrategyImpl(int paramInt, String paramString)
  {
    challengeCode = paramInt;
    headerName = paramString;
  }
  
  public void authFailed(HttpHost paramHttpHost, AuthScheme paramAuthScheme, HttpContext paramHttpContext)
  {
    Args.notNull(paramHttpHost, "Host");
    Args.notNull(paramHttpContext, "HTTP context");
    paramAuthScheme = HttpClientContext.adapt(paramHttpContext).getAuthCache();
    if (paramAuthScheme != null)
    {
      if (log.isDebugEnabled())
      {
        paramHttpContext = log;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Clearing cached auth scheme for ");
        localStringBuilder.append(paramHttpHost);
        paramHttpContext.debug(localStringBuilder.toString());
      }
      paramAuthScheme.remove(paramHttpHost);
    }
  }
  
  public void authSucceeded(HttpHost paramHttpHost, AuthScheme paramAuthScheme, HttpContext paramHttpContext)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a8 = a7\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n");
  }
  
  public Map getChallenges(HttpHost paramHttpHost, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    Args.notNull(paramHttpResponse, "HTTP response");
    paramHttpResponse = paramHttpResponse.getHeaders(headerName);
    paramHttpContext = new HashMap(paramHttpResponse.length);
    int m = paramHttpResponse.length;
    int j = 0;
    while (j < m)
    {
      Object localObject1 = paramHttpResponse[j];
      Object localObject2;
      int i;
      if ((localObject1 instanceof FormattedHeader))
      {
        localObject2 = (FormattedHeader)localObject1;
        paramHttpHost = ((FormattedHeader)localObject2).getBuffer();
        i = ((FormattedHeader)localObject2).getValuePos();
      }
      else
      {
        localObject2 = localObject1.getValue();
        if (localObject2 == null) {
          break label215;
        }
        paramHttpHost = new CharArrayBuffer(((String)localObject2).length());
        paramHttpHost.append((String)localObject2);
        i = 0;
      }
      while ((i < paramHttpHost.length()) && (HTTP.isWhitespace(paramHttpHost.charAt(i)))) {
        i += 1;
      }
      int k = i;
      while ((k < paramHttpHost.length()) && (!HTTP.isWhitespace(paramHttpHost.charAt(k)))) {
        k += 1;
      }
      paramHttpContext.put(paramHttpHost.substring(i, k).toLowerCase(Locale.ROOT), localObject1);
      j += 1;
      continue;
      label215:
      throw new MalformedChallengeException("Header value is null");
    }
    return paramHttpContext;
  }
  
  abstract Collection getPreferredAuthSchemes(RequestConfig paramRequestConfig);
  
  public boolean isAuthenticationRequested(HttpHost paramHttpHost, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    Args.notNull(paramHttpResponse, "HTTP response");
    return paramHttpResponse.getStatusLine().getStatusCode() == challengeCode;
  }
  
  protected boolean isCachable(AuthScheme paramAuthScheme)
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
  
  public Queue select(Map paramMap, HttpHost paramHttpHost, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    Args.notNull(paramMap, "Map of auth challenges");
    Args.notNull(paramHttpHost, "Host");
    Args.notNull(paramHttpResponse, "HTTP response");
    Args.notNull(paramHttpContext, "HTTP context");
    paramHttpResponse = HttpClientContext.adapt(paramHttpContext);
    LinkedList localLinkedList = new LinkedList();
    Lookup localLookup = paramHttpResponse.getAuthSchemeRegistry();
    if (localLookup == null) {
      paramMap = log;
    }
    CredentialsProvider localCredentialsProvider;
    for (paramHttpHost = "Auth scheme registry not set in the context";; paramHttpHost = "Credentials provider not set in the context")
    {
      paramMap.debug(paramHttpHost);
      return localLinkedList;
      localCredentialsProvider = paramHttpResponse.getCredentialsProvider();
      if (localCredentialsProvider != null) {
        break;
      }
      paramMap = log;
    }
    Object localObject1 = getPreferredAuthSchemes(paramHttpResponse.getRequestConfig());
    paramHttpResponse = (HttpResponse)localObject1;
    if (localObject1 == null) {
      paramHttpResponse = DEFAULT_SCHEME_PRIORITY;
    }
    Object localObject2;
    if (log.isDebugEnabled())
    {
      localObject1 = log;
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("Authentication schemes in the order of preference: ");
      ((StringBuilder)localObject2).append(paramHttpResponse);
      ((HttpClientAndroidLog)localObject1).debug(((StringBuilder)localObject2).toString());
    }
    paramHttpResponse = ((Collection)paramHttpResponse).iterator();
    while (paramHttpResponse.hasNext())
    {
      localObject1 = (String)paramHttpResponse.next();
      localObject2 = (Header)paramMap.get(((String)localObject1).toLowerCase(Locale.ROOT));
      Object localObject3;
      if (localObject2 != null)
      {
        localObject3 = (AuthSchemeProvider)localLookup.lookup((String)localObject1);
        if (localObject3 == null)
        {
          if (log.isWarnEnabled())
          {
            localObject2 = log;
            localObject3 = new StringBuilder();
            ((StringBuilder)localObject3).append("Authentication scheme ");
            ((StringBuilder)localObject3).append((String)localObject1);
            ((StringBuilder)localObject3).append(" not supported");
            ((HttpClientAndroidLog)localObject2).warn(((StringBuilder)localObject3).toString());
          }
        }
        else
        {
          localObject1 = ((AuthSchemeProvider)localObject3).create(paramHttpContext);
          ((AuthScheme)localObject1).processChallenge((Header)localObject2);
          localObject2 = localCredentialsProvider.getCredentials(new AuthScope(paramHttpHost.getHostName(), paramHttpHost.getPort(), ((AuthScheme)localObject1).getRealm(), ((AuthScheme)localObject1).getSchemeName()));
          if (localObject2 != null) {
            localLinkedList.add(new AuthOption((AuthScheme)localObject1, (Credentials)localObject2));
          }
        }
      }
      else if (log.isDebugEnabled())
      {
        localObject2 = log;
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append("Challenge for ");
        ((StringBuilder)localObject3).append((String)localObject1);
        ((StringBuilder)localObject3).append(" authentication scheme not available");
        ((HttpClientAndroidLog)localObject2).debug(((StringBuilder)localObject3).toString());
      }
    }
    return localLinkedList;
  }
}
