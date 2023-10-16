package cz.msebera.android.http.impl.cookie;

import c.a.a.a.f.d;
import cz.msebera.android.http.cookie.CookieAttributeHandler;
import cz.msebera.android.http.cookie.CookieSpec;
import cz.msebera.android.http.cookie.Object;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractCookieSpec implements CookieSpec {
    private final Map<String, d> attribHandlerMap;

    public AbstractCookieSpec() {
        this.attribHandlerMap = new ConcurrentHashMap(10);
    }

    protected AbstractCookieSpec(Object... objectArr) {
        this.attribHandlerMap = new ConcurrentHashMap(objectArr.length);
        for (Object $r2 : objectArr) {
            this.attribHandlerMap.put($r2.getAttributeName(), $r2);
        }
    }

    /* access modifiers changed from: protected */
    public CookieAttributeHandler findAttribHandler(String str) {
        return this.attribHandlerMap.get(str);
    }

    /* access modifiers changed from: protected */
    public Collection getAttribHandlers() {
        return this.attribHandlerMap.values();
    }
}
