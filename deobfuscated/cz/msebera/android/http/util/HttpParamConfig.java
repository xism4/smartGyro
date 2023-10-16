package cz.msebera.android.http.util;

import cz.msebera.android.http.protocol.MessageConstraints;
import cz.msebera.android.http.protocol.MessageConstraints.Builder;

@Deprecated
public final class HttpParamConfig
{
  public static MessageConstraints getMessageConstraints(HttpParams paramHttpParams)
  {
    MessageConstraints.Builder localBuilder = MessageConstraints.custom();
    localBuilder.setMaxHeaderCount(paramHttpParams.getIntParameter("http.connection.max-header-count", -1));
    localBuilder.setMaxLineLength(paramHttpParams.getIntParameter("http.connection.max-line-length", -1));
    return localBuilder.build();
  }
}
