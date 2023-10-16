package cz.msebera.android.http.util;

@Deprecated
public abstract class AbstractHttpParams implements HttpParams, HttpParamsNames {
    protected AbstractHttpParams() {
    }

    public boolean getBooleanParameter(String str, boolean z) {
        Object $r2 = getParameter(str);
        return $r2 == null ? z : ((Boolean) $r2).booleanValue();
    }

    public int getIntParameter(String str, int i) {
        Object $r2 = getParameter(str);
        return $r2 == null ? i : ((Integer) $r2).intValue();
    }

    public long getLongParameter(String str, long j) {
        Object $r2 = getParameter(str);
        return $r2 == null ? j : ((Long) $r2).longValue();
    }

    public boolean isParameterFalse(String str) {
        return !getBooleanParameter(str, false);
    }

    public boolean isParameterTrue(String str) {
        return getBooleanParameter(str, false);
    }

    public HttpParams setBooleanParameter(String str, boolean z) {
        setParameter(str, z ? Boolean.TRUE : Boolean.FALSE);
        return this;
    }

    public HttpParams setIntParameter(String str, int i) {
        setParameter(str, Integer.valueOf(i));
        return this;
    }

    public HttpParams setLongParameter(String str, long j) {
        setParameter(str, Long.valueOf(j));
        return this;
    }
}
