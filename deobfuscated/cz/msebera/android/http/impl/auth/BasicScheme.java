package cz.msebera.android.http.impl.auth;

import cz.msebera.android.http.Consts;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.auth.Credentials;
import cz.msebera.android.http.cache.Packet;
import cz.msebera.android.http.execchain.BasicHttpContext;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.message.BufferedHeader;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;
import cz.msebera.android.http.mime.EncodingUtils;
import java.nio.charset.Charset;
import java.security.Principal;

public class BasicScheme
  extends RFC2617Scheme
{
  private boolean complete = false;
  
  public BasicScheme()
  {
    this(Consts.ASCII);
  }
  
  public BasicScheme(Charset paramCharset)
  {
    super(paramCharset);
  }
  
  public Header authenticate(Credentials paramCredentials, HttpRequest paramHttpRequest)
  {
    return authenticate(paramCredentials, paramHttpRequest, new BasicHttpContext());
  }
  
  public Header authenticate(Credentials paramCredentials, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
  {
    Args.notNull(paramCredentials, "Credentials");
    Args.notNull(paramHttpRequest, "HTTP request");
    paramHttpContext = new StringBuilder();
    paramHttpContext.append(paramCredentials.getUserPrincipal().getName());
    paramHttpContext.append(":");
    if (paramCredentials.getPassword() == null) {
      paramCredentials = "null";
    } else {
      paramCredentials = paramCredentials.getPassword();
    }
    paramHttpContext.append(paramCredentials);
    paramHttpRequest = Packet.encode(EncodingUtils.getBytes(paramHttpContext.toString(), getCredentialsCharset(paramHttpRequest)), 2);
    paramHttpContext = new CharArrayBuffer(32);
    if (isProxy()) {
      paramCredentials = "Proxy-Authorization";
    } else {
      paramCredentials = "Authorization";
    }
    paramHttpContext.append(paramCredentials);
    paramHttpContext.append(": Basic ");
    paramHttpContext.append(paramHttpRequest, 0, paramHttpRequest.length);
    return new BufferedHeader(paramHttpContext);
  }
  
  public String getSchemeName()
  {
    return "basic";
  }
  
  public boolean isComplete()
  {
    return complete;
  }
  
  public boolean isConnectionBased()
  {
    return false;
  }
  
  public void processChallenge(Header paramHeader)
  {
    super.processChallenge(paramHeader);
    complete = true;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("BASIC [complete=");
    localStringBuilder.append(complete);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}
