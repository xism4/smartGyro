package cz.msebera.android.http.impl.client;

import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.util.AbstractHttpParams;
import cz.msebera.android.http.util.HttpParams;

@Deprecated
public class ClientParamsStack extends AbstractHttpParams {
    protected final HttpParams applicationParams;
    protected final HttpParams clientParams;
    protected final HttpParams overrideParams;
    protected final HttpParams requestParams;

    public ClientParamsStack(HttpParams httpParams, HttpParams httpParams2, HttpParams httpParams3, HttpParams httpParams4) {
        this.requestParams = httpParams;
        this.overrideParams = httpParams2;
        this.applicationParams = httpParams3;
        this.clientParams = httpParams4;
    }

    public Object getParameter(String str) {
        HttpParams $r2;
        HttpParams $r22;
        HttpParams $r23;
        Args.notNull(str, "Parameter name");
        HttpParams $r24 = this.clientParams;
        Object $r3 = $r24 != null ? $r24.getParameter(str) : null;
        if ($r3 == null && ($r23 = this.applicationParams) != null) {
            $r3 = $r23.getParameter(str);
        }
        if ($r3 == null && ($r22 = this.overrideParams) != null) {
            $r3 = $r22.getParameter(str);
        }
        return ($r3 != null || ($r2 = this.requestParams) == null) ? $r3 : $r2.getParameter(str);
    }

    public HttpParams setParameter(String str, Object obj) {
        throw new UnsupportedOperationException("Setting parameters in a stack is not supported.");
    }
}
