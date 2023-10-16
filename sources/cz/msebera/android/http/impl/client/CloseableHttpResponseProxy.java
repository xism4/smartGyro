package cz.msebera.android.http.impl.client;

import c.a.a.a.b.c.e;
import c.a.a.a.i.b.i;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.client.auth.CloseableHttpResponse;
import cz.msebera.android.http.mime.EntityUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Deprecated
class CloseableHttpResponseProxy implements InvocationHandler {
    private static final Constructor<?> CONSTRUCTOR = Proxy.getProxyClass(i.class.getClassLoader(), new Class[]{e.class}).getConstructor(new Class[]{InvocationHandler.class});
    private final HttpResponse original;

    static {
        try {
        } catch (NoSuchMethodException $r4) {
            throw new IllegalStateException($r4);
        }
    }

    CloseableHttpResponseProxy(HttpResponse httpResponse) {
        this.original = httpResponse;
    }

    public static CloseableHttpResponse newProxy(HttpResponse httpResponse) {
        Constructor $r3 = CONSTRUCTOR;
        Object[] $r4 = new Object[1];
        try {
            $r4[0] = new CloseableHttpResponseProxy(httpResponse);
            return (CloseableHttpResponse) $r3.newInstance($r4);
        } catch (InstantiationException $r9) {
            throw new IllegalStateException($r9);
        } catch (InvocationTargetException $r8) {
            throw new IllegalStateException($r8);
        } catch (IllegalAccessException $r6) {
            throw new IllegalStateException($r6);
        }
    }

    public void close() {
        EntityUtils.consume(this.original.getEntity());
    }

    public Object invoke(Object obj, Method method, Object[] objArr) {
        if (method.getName().equals("close")) {
            close();
            return null;
        }
        try {
            return method.invoke(this.original, objArr);
        } catch (InvocationTargetException $r6) {
            Throwable $r7 = $r6.getCause();
            if ($r7 != null) {
                throw $r7;
            }
            throw $r6;
        }
    }
}
