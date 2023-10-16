package cz.msebera.android.http.util;

@Deprecated
public class SyncBasicHttpParams extends BasicHttpParams {
    public synchronized Object clone() {
        return super.clone();
    }

    public synchronized Object getParameter(String str) {
        return super.getParameter(str);
    }

    public synchronized HttpParams setParameter(String str, Object obj) {
        super.setParameter(str, obj);
        return this;
    }
}
