package cz.msebera.android.http.message;

import cz.msebera.android.http.HttpEntity;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.HttpVersion;
import cz.msebera.android.http.ProtocolVersion;
import cz.msebera.android.http.ReasonPhraseCatalog;
import cz.msebera.android.http.StatusLine;
import cz.msebera.android.http.mime.Args;
import java.util.Locale;

public class BasicHttpResponse
  extends AbstractHttpMessage
  implements HttpResponse
{
  private int code;
  private HttpEntity entity;
  private Locale locale;
  private final ReasonPhraseCatalog reasonCatalog;
  private String reasonPhrase;
  private StatusLine statusline;
  private ProtocolVersion ver;
  
  public BasicHttpResponse(StatusLine paramStatusLine, ReasonPhraseCatalog paramReasonPhraseCatalog, Locale paramLocale)
  {
    Args.notNull(paramStatusLine, "Status line");
    statusline = ((StatusLine)paramStatusLine);
    ver = paramStatusLine.getProtocolVersion();
    code = paramStatusLine.getStatusCode();
    reasonPhrase = paramStatusLine.getReasonPhrase();
    reasonCatalog = paramReasonPhraseCatalog;
    locale = paramLocale;
  }
  
  public HttpEntity getEntity()
  {
    return entity;
  }
  
  public ProtocolVersion getProtocolVersion()
  {
    return ver;
  }
  
  protected String getReason(int paramInt)
  {
    ReasonPhraseCatalog localReasonPhraseCatalog = reasonCatalog;
    if (localReasonPhraseCatalog != null)
    {
      Locale localLocale = locale;
      if (localLocale == null) {
        localLocale = Locale.getDefault();
      }
      return localReasonPhraseCatalog.getReason(paramInt, localLocale);
    }
    return null;
  }
  
  public StatusLine getStatusLine()
  {
    if (statusline == null)
    {
      Object localObject = ver;
      if (localObject == null) {
        localObject = HttpVersion.HTTP_1_1;
      }
      int i = code;
      String str = reasonPhrase;
      if (str == null) {
        str = getReason(i);
      }
      statusline = new BasicStatusLine((ProtocolVersion)localObject, i, str);
    }
    return statusline;
  }
  
  public void setEntity(HttpEntity paramHttpEntity)
  {
    entity = paramHttpEntity;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getStatusLine());
    localStringBuilder.append(' ');
    localStringBuilder.append(headergroup);
    if (entity != null)
    {
      localStringBuilder.append(' ');
      localStringBuilder.append(entity);
    }
    return localStringBuilder.toString();
  }
}
