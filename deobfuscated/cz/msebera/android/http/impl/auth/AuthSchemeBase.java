package cz.msebera.android.http.impl.auth;

import cz.msebera.android.http.FormattedHeader;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.auth.AuthScheme;
import cz.msebera.android.http.auth.ChallengeState;
import cz.msebera.android.http.auth.ContextAwareAuthScheme;
import cz.msebera.android.http.auth.Credentials;
import cz.msebera.android.http.auth.MalformedChallengeException;
import cz.msebera.android.http.execchain.HTTP;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;
import java.util.Locale;

public abstract class AuthSchemeBase
  implements ContextAwareAuthScheme
{
  private ChallengeState challengeState;
  
  public AuthSchemeBase() {}
  
  public Header authenticate(Credentials paramCredentials, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
  {
    return authenticate(paramCredentials, paramHttpRequest);
  }
  
  public boolean isProxy()
  {
    ChallengeState localChallengeState = challengeState;
    return (localChallengeState != null) && (localChallengeState == ChallengeState.PROXY);
  }
  
  protected abstract void parseChallenge(CharArrayBuffer paramCharArrayBuffer, int paramInt1, int paramInt2);
  
  public void processChallenge(Header paramHeader)
  {
    Args.notNull(paramHeader, "Header");
    Object localObject = paramHeader.getName();
    if (((String)localObject).equalsIgnoreCase("WWW-Authenticate")) {}
    for (localObject = ChallengeState.TARGET;; localObject = ChallengeState.PROXY)
    {
      challengeState = ((ChallengeState)localObject);
      break;
      if (!((String)localObject).equalsIgnoreCase("Proxy-Authenticate")) {
        break label254;
      }
    }
    int i;
    if ((paramHeader instanceof FormattedHeader))
    {
      localObject = (FormattedHeader)paramHeader;
      paramHeader = ((FormattedHeader)localObject).getBuffer();
      i = ((FormattedHeader)localObject).getValuePos();
    }
    else
    {
      localObject = paramHeader.getValue();
      if (localObject == null) {
        break label244;
      }
      paramHeader = new CharArrayBuffer(((String)localObject).length());
      paramHeader.append((String)localObject);
      i = 0;
    }
    while ((i < paramHeader.length()) && (HTTP.isWhitespace(paramHeader.charAt(i)))) {
      i += 1;
    }
    int j = i;
    while ((j < paramHeader.length()) && (!HTTP.isWhitespace(paramHeader.charAt(j)))) {
      j += 1;
    }
    localObject = paramHeader.substring(i, j);
    if (((String)localObject).equalsIgnoreCase(getSchemeName()))
    {
      parseChallenge(paramHeader, j, paramHeader.length());
      return;
    }
    paramHeader = new StringBuilder();
    paramHeader.append("Invalid scheme identifier: ");
    paramHeader.append((String)localObject);
    throw new MalformedChallengeException(paramHeader.toString());
    label244:
    throw new MalformedChallengeException("Header value is null");
    label254:
    paramHeader = new StringBuilder();
    paramHeader.append("Unexpected header name: ");
    paramHeader.append((String)localObject);
    paramHeader = new MalformedChallengeException(paramHeader.toString());
    throw paramHeader;
  }
  
  public String toString()
  {
    String str = getSchemeName();
    if (str != null) {
      return str.toUpperCase(Locale.ROOT);
    }
    return super.toString();
  }
}
