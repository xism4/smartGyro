package cz.msebera.android.http.impl.auth;

import cz.msebera.android.http.Consts;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpEntity;
import cz.msebera.android.http.HttpEntityEnclosingRequest;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.RequestLine;
import cz.msebera.android.http.auth.AuthenticationException;
import cz.msebera.android.http.auth.Credentials;
import cz.msebera.android.http.auth.MalformedChallengeException;
import cz.msebera.android.http.execchain.BasicHttpContext;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.message.BasicHeaderValueFormatter;
import cz.msebera.android.http.message.BasicNameValuePair;
import cz.msebera.android.http.message.BufferedHeader;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;
import cz.msebera.android.http.mime.EncodingUtils;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.Principal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class DigestScheme
  extends RFC2617Scheme
{
  private static final char[] HEXADECIMAL = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
  private String a1;
  private String a2;
  private String cnonce;
  private boolean complete = false;
  private String lastNonce;
  private long nounceCount;
  
  public DigestScheme()
  {
    this(Consts.ASCII);
  }
  
  public DigestScheme(Charset paramCharset)
  {
    super(paramCharset);
  }
  
  public static String createCnonce()
  {
    SecureRandom localSecureRandom = new SecureRandom();
    byte[] arrayOfByte = new byte[8];
    localSecureRandom.nextBytes(arrayOfByte);
    return encode(arrayOfByte);
  }
  
  private Header createDigestHeader(Credentials paramCredentials, HttpRequest paramHttpRequest)
  {
    String str2 = getParameter("uri");
    Object localObject4 = getParameter("realm");
    String str3 = getParameter("nonce");
    String str1 = getParameter("opaque");
    String str5 = getParameter("methodname");
    Object localObject2 = getParameter("algorithm");
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = "MD5";
    }
    HashSet localHashSet = new HashSet(8);
    localObject2 = getParameter("qop");
    Object localObject3;
    int i;
    if (localObject2 != null)
    {
      localObject3 = new StringTokenizer((String)localObject2, ",");
      while (((StringTokenizer)localObject3).hasMoreTokens()) {
        localHashSet.add(((StringTokenizer)localObject3).nextToken().trim().toLowerCase(Locale.ROOT));
      }
      if (((paramHttpRequest instanceof HttpEntityEnclosingRequest)) && (localHashSet.contains("auth-int"))) {
        i = 1;
      } else if (localHashSet.contains("auth")) {
        i = 2;
      } else {
        i = -1;
      }
    }
    else
    {
      i = 0;
    }
    if (i != -1)
    {
      localObject3 = getParameter("charset");
      localObject2 = localObject3;
      if (localObject3 == null) {
        localObject2 = "ISO-8859-1";
      }
      if (((String)localObject1).equalsIgnoreCase("MD5-sess")) {
        localObject3 = "MD5";
      } else {
        localObject3 = localObject1;
      }
    }
    try
    {
      MessageDigest localMessageDigest = createMessageDigest((String)localObject3);
      String str4 = paramCredentials.getUserPrincipal().getName();
      Object localObject5 = paramCredentials.getPassword();
      paramCredentials = (Credentials)localObject5;
      if (str3.equals(lastNonce))
      {
        nounceCount += 1L;
      }
      else
      {
        nounceCount = 1L;
        cnonce = null;
        lastNonce = str3;
      }
      localObject3 = localObject4;
      StringBuilder localStringBuilder = new StringBuilder(256);
      localObject4 = new Formatter(localStringBuilder, Locale.US);
      ((Formatter)localObject4).format("%08x", new Object[] { Long.valueOf(nounceCount) });
      ((Formatter)localObject4).close();
      localObject4 = localStringBuilder.toString();
      if (cnonce == null) {
        cnonce = createCnonce();
      }
      a1 = null;
      a2 = null;
      if (((String)localObject1).equalsIgnoreCase("MD5-sess"))
      {
        localStringBuilder.setLength(0);
        localStringBuilder.append(str4);
        localStringBuilder.append(':');
        localStringBuilder.append((String)localObject3);
        localStringBuilder.append(':');
        localStringBuilder.append((String)localObject5);
        paramCredentials = encode(localMessageDigest.digest(EncodingUtils.getBytes(localStringBuilder.toString(), (String)localObject2)));
        localStringBuilder.setLength(0);
        localStringBuilder.append(paramCredentials);
        localStringBuilder.append(':');
        localStringBuilder.append(str3);
        localStringBuilder.append(':');
        paramCredentials = cnonce;
      }
      else
      {
        localStringBuilder.setLength(0);
        localStringBuilder.append(str4);
        localStringBuilder.append(':');
        localStringBuilder.append((String)localObject3);
        localStringBuilder.append(':');
      }
      localStringBuilder.append(paramCredentials);
      a1 = localStringBuilder.toString();
      localObject5 = encode(localMessageDigest.digest(EncodingUtils.getBytes(a1, (String)localObject2)));
      if (i == 2)
      {
        paramCredentials = new StringBuilder();
        paramCredentials.append(str5);
        paramCredentials.append(':');
        paramCredentials.append(str2);
        a2 = paramCredentials.toString();
      }
      else
      {
        if (i == 1)
        {
          if ((paramHttpRequest instanceof HttpEntityEnclosingRequest)) {
            paramCredentials = ((HttpEntityEnclosingRequest)paramHttpRequest).getEntity();
          } else {
            paramCredentials = null;
          }
          if ((paramCredentials != null) && (!paramCredentials.isRepeatable()))
          {
            if (localHashSet.contains("auth"))
            {
              paramCredentials = new StringBuilder();
              paramCredentials.append(str5);
              paramCredentials.append(':');
              paramCredentials.append(str2);
              a2 = paramCredentials.toString();
              i = 2;
              break label857;
            }
            throw new AuthenticationException("Qop auth-int cannot be used with a non-repeatable entity");
          }
          paramHttpRequest = new HttpEntityDigester(localMessageDigest);
          if (paramCredentials == null) {}
        }
        for (;;)
        {
          try
          {
            paramCredentials.writeTo(paramHttpRequest);
            paramHttpRequest.close();
            paramCredentials = new StringBuilder();
            paramCredentials.append(str5);
            paramCredentials.append(':');
            paramCredentials.append(str2);
            paramCredentials.append(':');
            paramCredentials.append(encode(paramHttpRequest.getDigest()));
            a2 = paramCredentials.toString();
          }
          catch (IOException paramCredentials)
          {
            throw new AuthenticationException("I/O error reading entity content", paramCredentials);
          }
          paramCredentials = new StringBuilder();
          paramCredentials.append(str5);
          paramCredentials.append(':');
          paramCredentials.append(str2);
        }
      }
      label857:
      paramHttpRequest = "auth";
      localObject2 = encode(localMessageDigest.digest(EncodingUtils.getBytes(a2, (String)localObject2)));
      if (i == 0)
      {
        localStringBuilder.setLength(0);
        localStringBuilder.append((String)localObject5);
        localStringBuilder.append(':');
        localStringBuilder.append(str3);
      }
      for (;;)
      {
        localStringBuilder.append(':');
        localStringBuilder.append((String)localObject2);
        paramCredentials = localStringBuilder.toString();
        break;
        localStringBuilder.setLength(0);
        localStringBuilder.append((String)localObject5);
        localStringBuilder.append(':');
        localStringBuilder.append(str3);
        localStringBuilder.append(':');
        localStringBuilder.append((String)localObject4);
        localStringBuilder.append(':');
        localStringBuilder.append(cnonce);
        localStringBuilder.append(':');
        if (i == 1) {
          paramCredentials = "auth-int";
        } else {
          paramCredentials = "auth";
        }
        localStringBuilder.append(paramCredentials);
      }
      str5 = encode(localMessageDigest.digest(EncodingUtils.getAsciiBytes(paramCredentials)));
      localObject2 = new CharArrayBuffer(128);
      if (isProxy()) {
        paramCredentials = "Proxy-Authorization";
      } else {
        paramCredentials = "Authorization";
      }
      ((CharArrayBuffer)localObject2).append(paramCredentials);
      ((CharArrayBuffer)localObject2).append(": Digest ");
      localObject5 = new ArrayList(20);
      ((List)localObject5).add(new BasicNameValuePair("username", str4));
      ((List)localObject5).add(new BasicNameValuePair("realm", (String)localObject3));
      ((List)localObject5).add(new BasicNameValuePair("nonce", str3));
      ((List)localObject5).add(new BasicNameValuePair("uri", str2));
      ((List)localObject5).add(new BasicNameValuePair("response", str5));
      if (i != 0)
      {
        paramCredentials = paramHttpRequest;
        if (i == 1) {
          paramCredentials = "auth-int";
        }
        ((List)localObject5).add(new BasicNameValuePair("qop", paramCredentials));
        ((List)localObject5).add(new BasicNameValuePair("nc", (String)localObject4));
        ((List)localObject5).add(new BasicNameValuePair("cnonce", cnonce));
      }
      ((List)localObject5).add(new BasicNameValuePair("algorithm", (String)localObject1));
      if (str1 != null) {
        ((List)localObject5).add(new BasicNameValuePair("opaque", str1));
      }
      i = 0;
      while (i < ((List)localObject5).size())
      {
        paramCredentials = (BasicNameValuePair)((List)localObject5).get(i);
        if (i > 0) {
          ((CharArrayBuffer)localObject2).append(", ");
        }
        paramHttpRequest = paramCredentials.getName();
        int j;
        if ((!"nc".equals(paramHttpRequest)) && (!"qop".equals(paramHttpRequest)) && (!"algorithm".equals(paramHttpRequest))) {
          j = 0;
        } else {
          j = 1;
        }
        BasicHeaderValueFormatter.INSTANCE.formatNameValuePair((CharArrayBuffer)localObject2, paramCredentials, j ^ 0x1);
        i += 1;
      }
      return new BufferedHeader((CharArrayBuffer)localObject2);
    }
    catch (UnsupportedDigestAlgorithmException paramCredentials)
    {
      for (;;) {}
    }
    paramCredentials = new StringBuilder();
    paramCredentials.append("Unsuppported digest algorithm: ");
    paramCredentials.append((String)localObject3);
    throw new AuthenticationException(paramCredentials.toString());
    paramCredentials = new StringBuilder();
    paramCredentials.append("None of the qop methods is supported: ");
    paramCredentials.append((String)localObject2);
    paramCredentials = new AuthenticationException(paramCredentials.toString());
    throw paramCredentials;
  }
  
  private static MessageDigest createMessageDigest(String paramString)
  {
    try
    {
      localObject = MessageDigest.getInstance(paramString);
      return localObject;
    }
    catch (Exception localException)
    {
      Object localObject;
      for (;;) {}
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Unsupported algorithm in HTTP Digest authentication: ");
    ((StringBuilder)localObject).append(paramString);
    throw new UnsupportedDigestAlgorithmException(((StringBuilder)localObject).toString());
  }
  
  static String encode(byte[] paramArrayOfByte)
  {
    int j = paramArrayOfByte.length;
    char[] arrayOfChar1 = new char[j * 2];
    int i = 0;
    while (i < j)
    {
      int k = paramArrayOfByte[i];
      int m = paramArrayOfByte[i];
      int n = i * 2;
      char[] arrayOfChar2 = HEXADECIMAL;
      arrayOfChar1[n] = arrayOfChar2[((m & 0xF0) >> 4)];
      arrayOfChar1[(n + 1)] = arrayOfChar2[(k & 0xF)];
      i += 1;
    }
    return new String(arrayOfChar1);
  }
  
  public Header authenticate(Credentials paramCredentials, HttpRequest paramHttpRequest)
  {
    return authenticate(paramCredentials, paramHttpRequest, new BasicHttpContext());
  }
  
  public Header authenticate(Credentials paramCredentials, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
  {
    Args.notNull(paramCredentials, "Credentials");
    Args.notNull(paramHttpRequest, "HTTP request");
    if (getParameter("realm") != null)
    {
      if (getParameter("nonce") != null)
      {
        getParameters().put("methodname", paramHttpRequest.getRequestLine().getMethod());
        getParameters().put("uri", paramHttpRequest.getRequestLine().getUri());
        if (getParameter("charset") == null) {
          getParameters().put("charset", getCredentialsCharset(paramHttpRequest));
        }
        return createDigestHeader(paramCredentials, paramHttpRequest);
      }
      throw new AuthenticationException("missing nonce in challenge");
    }
    throw new AuthenticationException("missing realm in challenge");
  }
  
  public String getSchemeName()
  {
    return "digest";
  }
  
  public boolean isComplete()
  {
    if ("true".equalsIgnoreCase(getParameter("stale"))) {
      return false;
    }
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
    if (!getParameters().isEmpty()) {
      return;
    }
    throw new MalformedChallengeException("Authentication challenge is empty");
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("DIGEST [complete=");
    localStringBuilder.append(complete);
    localStringBuilder.append(", nonce=");
    localStringBuilder.append(lastNonce);
    localStringBuilder.append(", nc=");
    localStringBuilder.append(nounceCount);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}
