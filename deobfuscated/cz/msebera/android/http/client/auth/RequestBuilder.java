package cz.msebera.android.http.client.auth;

import c.a.a.a.z;
import cz.msebera.android.http.Consts;
import cz.msebera.android.http.HttpEntity;
import cz.msebera.android.http.HttpEntityEnclosingRequest;
import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.ProtocolVersion;
import cz.msebera.android.http.RequestLine;
import cz.msebera.android.http.client.methods.RequestConfig;
import cz.msebera.android.http.client.ssl.URIBuilder;
import cz.msebera.android.http.client.ssl.URLEncodedUtils;
import cz.msebera.android.http.entity.ContentType;
import cz.msebera.android.http.message.HeaderGroup;
import cz.msebera.android.http.mime.Args;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.List;

public class RequestBuilder
{
  private Charset charset = Consts.UTF_8;
  private RequestConfig config;
  private HttpEntity entity;
  private HeaderGroup headergroup;
  private String method;
  private List<z> parameters;
  private URI uri;
  private ProtocolVersion version;
  
  RequestBuilder()
  {
    this(null);
  }
  
  RequestBuilder(String paramString)
  {
    method = paramString;
  }
  
  public static RequestBuilder copy(HttpRequest paramHttpRequest)
  {
    Args.notNull(paramHttpRequest, "HTTP request");
    RequestBuilder localRequestBuilder = new RequestBuilder();
    localRequestBuilder.doCopy(paramHttpRequest);
    return localRequestBuilder;
  }
  
  private RequestBuilder doCopy(HttpRequest paramHttpRequest)
  {
    if (paramHttpRequest == null) {
      return this;
    }
    method = paramHttpRequest.getRequestLine().getMethod();
    version = paramHttpRequest.getRequestLine().getProtocolVersion();
    if (headergroup == null) {
      headergroup = new HeaderGroup();
    }
    headergroup.clear();
    headergroup.setHeaders(paramHttpRequest.getAllHeaders());
    parameters = null;
    entity = null;
    Object localObject1;
    if ((paramHttpRequest instanceof HttpEntityEnclosingRequest))
    {
      localObject1 = ((HttpEntityEnclosingRequest)paramHttpRequest).getEntity();
      localObject2 = ContentType.get((HttpEntity)localObject1);
      if ((localObject2 == null) || (!((ContentType)localObject2).getMimeType().equals(ContentType.APPLICATION_FORM_URLENCODED.getMimeType()))) {}
    }
    try
    {
      localObject1 = URLEncodedUtils.parse((HttpEntity)localObject1);
      boolean bool = ((List)localObject1).isEmpty();
      if (bool) {
        break label161;
      }
      parameters = ((List)localObject1);
    }
    catch (IOException localIOException)
    {
      label161:
      List localList;
      for (;;) {}
    }
    break label161;
    entity = ((HttpEntity)localObject1);
    if ((paramHttpRequest instanceof HttpUriRequest)) {
      localObject1 = ((HttpUriRequest)paramHttpRequest).getURI();
    } else {
      localObject1 = URI.create(paramHttpRequest.getRequestLine().getUri());
    }
    Object localObject2 = new URIBuilder((URI)localObject1);
    if (parameters == null)
    {
      localList = ((URIBuilder)localObject2).getQueryParams();
      if (!localList.isEmpty())
      {
        parameters = localList;
        ((URIBuilder)localObject2).clearParameters();
      }
      else
      {
        parameters = null;
      }
    }
    try
    {
      localObject2 = ((URIBuilder)localObject2).build();
      uri = ((URI)localObject2);
    }
    catch (URISyntaxException localURISyntaxException)
    {
      for (;;) {}
    }
    uri = ((URI)localObject1);
    if ((paramHttpRequest instanceof Configurable))
    {
      config = ((Configurable)paramHttpRequest).getConfig();
      return this;
    }
    config = null;
    return this;
  }
  
  public HttpUriRequest build()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a7 = a6\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n");
  }
  
  public RequestBuilder setUri(URI paramURI)
  {
    uri = paramURI;
    return this;
  }
  
  class InternalEntityEclosingRequest
    extends HttpEntityEnclosingRequestBase
  {
    InternalEntityEclosingRequest() {}
    
    public String getMethod()
    {
      return RequestBuilder.this;
    }
  }
  
  class InternalRequest
    extends HttpRequestBase
  {
    InternalRequest() {}
    
    public String getMethod()
    {
      return RequestBuilder.this;
    }
  }
}
