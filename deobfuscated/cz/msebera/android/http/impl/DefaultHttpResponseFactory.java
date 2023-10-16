package cz.msebera.android.http.impl;

import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.HttpResponseFactory;
import cz.msebera.android.http.ReasonPhraseCatalog;
import cz.msebera.android.http.StatusLine;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.message.BasicHttpResponse;
import cz.msebera.android.http.mime.Args;
import java.util.Locale;

public class DefaultHttpResponseFactory
  implements HttpResponseFactory
{
  public static final DefaultHttpResponseFactory INSTANCE = new DefaultHttpResponseFactory();
  protected final ReasonPhraseCatalog reasonCatalog;
  
  public DefaultHttpResponseFactory()
  {
    this(EnglishReasonPhraseCatalog.INSTANCE);
  }
  
  public DefaultHttpResponseFactory(ReasonPhraseCatalog paramReasonPhraseCatalog)
  {
    Args.notNull(paramReasonPhraseCatalog, "Reason phrase catalog");
    reasonCatalog = ((ReasonPhraseCatalog)paramReasonPhraseCatalog);
  }
  
  protected Locale determineLocale(HttpContext paramHttpContext)
  {
    return Locale.getDefault();
  }
  
  public HttpResponse newHttpResponse(StatusLine paramStatusLine, HttpContext paramHttpContext)
  {
    Args.notNull(paramStatusLine, "Status line");
    return new BasicHttpResponse(paramStatusLine, reasonCatalog, determineLocale(paramHttpContext));
  }
}
