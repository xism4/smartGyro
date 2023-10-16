package p036c.p037a.p038a.p039a.p060i.p062b;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import p036c.p037a.p038a.p039a.C0883t;
import p036c.p037a.p038a.p039a.p041b.p044c.C0525e;
import p036c.p037a.p038a.p039a.p074p.C0876g;

@Deprecated
/* renamed from: c.a.a.a.i.b.i */
class C0697i implements InvocationHandler {

    /* renamed from: a */
    private static final Constructor<?> f2076a;

    /* renamed from: b */
    private final C0883t f2077b;

    static {
        try {
            f2076a = Proxy.getProxyClass(C0697i.class.getClassLoader(), new Class[]{C0525e.class}).getConstructor(new Class[]{InvocationHandler.class});
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException(e);
        }
    }

    C0697i(C0883t tVar) {
        this.f2077b = tVar;
    }

    /* renamed from: a */
    public static C0525e m2556a(C0883t tVar) {
        try {
            return (C0525e) f2076a.newInstance(new Object[]{new C0697i(tVar)});
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        } catch (InvocationTargetException e2) {
            throw new IllegalStateException(e2);
        } catch (IllegalAccessException e3) {
            throw new IllegalStateException(e3);
        }
    }

    /* renamed from: a */
    public void mo2896a() {
        C0876g.m3080a(this.f2077b.getEntity());
    }

    public Object invoke(Object obj, Method method, Object[] objArr) {
        if (method.getName().equals("close")) {
            mo2896a();
            return null;
        }
        try {
            return method.invoke(this.f2077b, objArr);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause != null) {
                throw cause;
            }
            throw e;
        }
    }
}
