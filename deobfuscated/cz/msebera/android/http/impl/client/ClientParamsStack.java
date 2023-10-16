package cz.msebera.android.http.impl.client;

import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.util.AbstractHttpParams;
import cz.msebera.android.http.util.HttpParams;

@Deprecated
public class ClientParamsStack
  extends AbstractHttpParams
{
  protected final HttpParams applicationParams;
  protected final HttpParams clientParams;
  protected final HttpParams overrideParams;
  protected final HttpParams requestParams;
  
  public ClientParamsStack(HttpParams paramHttpParams1, HttpParams paramHttpParams2, HttpParams paramHttpParams3, HttpParams paramHttpParams4)
  {
    requestParams = paramHttpParams1;
    overrideParams = paramHttpParams2;
    applicationParams = paramHttpParams3;
    clientParams = paramHttpParams4;
  }
  
  public Object getParameter(String paramString)
  {
    Args.notNull(paramString, "Parameter name");
    Object localObject1 = clientParams;
    if (localObject1 != null) {
      localObject2 = ((HttpParams)localObject1).getParameter(paramString);
    } else {
      localObject2 = null;
    }
    localObject1 = localObject2;
    HttpParams localHttpParams;
    if (localObject2 == null)
    {
      localHttpParams = applicationParams;
      localObject1 = localObject2;
      if (localHttpParams != null) {
        localObject1 = localHttpParams.getParameter(paramString);
      }
    }
    Object localObject2 = localObject1;
    if (localObject1 == null)
    {
      localHttpParams = overrideParams;
      localObject2 = localObject1;
      if (localHttpParams != null) {
        localObject2 = localHttpParams.getParameter(paramString);
      }
    }
    localObject1 = localObject2;
    if (localObject2 == null)
    {
      localHttpParams = requestParams;
      localObject1 = localObject2;
      if (localHttpParams != null) {
        localObject1 = localHttpParams.getParameter(paramString);
      }
    }
    return localObject1;
  }
  
  public HttpParams setParameter(String paramString, Object paramObject)
  {
    throw new UnsupportedOperationException("Setting parameters in a stack is not supported.");
  }
}
