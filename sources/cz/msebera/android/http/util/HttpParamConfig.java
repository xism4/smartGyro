package cz.msebera.android.http.util;

import cz.msebera.android.http.protocol.MessageConstraints;

@Deprecated
public final class HttpParamConfig {
    public static MessageConstraints getMessageConstraints(HttpParams httpParams) {
        MessageConstraints.Builder $r1 = MessageConstraints.custom();
        $r1.setMaxHeaderCount(httpParams.getIntParameter("http.connection.max-header-count", -1));
        $r1.setMaxLineLength(httpParams.getIntParameter("http.connection.max-line-length", -1));
        return $r1.build();
    }
}
