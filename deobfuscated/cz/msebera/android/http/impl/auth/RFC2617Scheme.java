package cz.msebera.android.http.impl.auth;

import cz.msebera.android.http.Consts;
import cz.msebera.android.http.HeaderElement;
import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.message.BasicHeaderValueParser;
import cz.msebera.android.http.message.HeaderValueParser;
import cz.msebera.android.http.util.HttpParams;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public abstract class RFC2617Scheme
  extends AuthSchemeBase
  implements Serializable
{
  private transient Charset credentialsCharset;
  private final Map<String, String> params = new HashMap();
  
  public RFC2617Scheme(Charset paramCharset)
  {
    if (paramCharset == null) {
      paramCharset = Consts.ASCII;
    }
    credentialsCharset = paramCharset;
  }
  
  String getCredentialsCharset(HttpRequest paramHttpRequest)
  {
    String str = (String)paramHttpRequest.getParams().getParameter("http.auth.credential-charset");
    paramHttpRequest = str;
    if (str == null) {
      paramHttpRequest = getCredentialsCharset().name();
    }
    return paramHttpRequest;
  }
  
  public Charset getCredentialsCharset()
  {
    Charset localCharset = credentialsCharset;
    if (localCharset != null) {
      return localCharset;
    }
    return Consts.ASCII;
  }
  
  public String getParameter(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return (String)params.get(paramString.toLowerCase(Locale.ROOT));
  }
  
  protected Map getParameters()
  {
    return params;
  }
  
  public String getRealm()
  {
    return getParameter("realm");
  }
  
  protected void parseChallenge(cz.msebera.android.http.mime.CharArrayBuffer paramCharArrayBuffer, int paramInt1, int paramInt2)
  {
    paramCharArrayBuffer = BasicHeaderValueParser.INSTANCE.parseElements(paramCharArrayBuffer, new cz.msebera.android.http.message.CharArrayBuffer(paramInt1, paramCharArrayBuffer.length()));
    params.clear();
    paramInt2 = paramCharArrayBuffer.length;
    paramInt1 = 0;
    while (paramInt1 < paramInt2)
    {
      Object localObject = paramCharArrayBuffer[paramInt1];
      params.put(localObject.getName().toLowerCase(Locale.ROOT), localObject.getValue());
      paramInt1 += 1;
    }
  }
}
