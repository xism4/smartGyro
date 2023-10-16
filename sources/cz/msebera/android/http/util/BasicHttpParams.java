package cz.msebera.android.http.util;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Deprecated
public class BasicHttpParams extends AbstractHttpParams implements Serializable, Cloneable {
    private final Map<String, Object> parameters = new ConcurrentHashMap();

    public Object clone() {
        BasicHttpParams $r2 = (BasicHttpParams) super.clone();
        copyParams($r2);
        return $r2;
    }

    public void copyParams(HttpParams httpParams) {
        for (Map.Entry $r6 : this.parameters.entrySet()) {
            httpParams.setParameter((String) $r6.getKey(), $r6.getValue());
        }
    }

    public Object getParameter(String str) {
        return this.parameters.get(str);
    }

    public HttpParams setParameter(String str, Object obj) {
        if (str == null) {
            return this;
        }
        if (obj != null) {
            this.parameters.put(str, obj);
            return this;
        }
        this.parameters.remove(str);
        return this;
    }
}
