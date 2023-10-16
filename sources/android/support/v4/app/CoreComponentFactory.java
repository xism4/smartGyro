package android.support.v4.app;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.app.Application;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Intent;

public class CoreComponentFactory extends AppComponentFactory {

    public interface a {
        Object f();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r3 = ((android.support.v4.app.CoreComponentFactory.a) r4).f();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.Object f(java.lang.Object r4) {
        /*
            boolean r0 = r4 instanceof android.support.v4.app.CoreComponentFactory.a
            if (r0 == 0) goto L_0x000f
            r2 = r4
            android.support.v4.app.CoreComponentFactory$a r2 = (android.support.v4.app.CoreComponentFactory.a) r2
            r1 = r2
            java.lang.Object r3 = r1.f()
            if (r3 == 0) goto L_0x000f
            return r3
        L_0x000f:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.CoreComponentFactory.f(java.lang.Object):java.lang.Object");
    }

    public Activity instantiateActivity(ClassLoader classLoader, String str, Intent intent) {
        return (Activity) f(super.instantiateActivity(classLoader, str, intent));
    }

    public Application instantiateApplication(ClassLoader classLoader, String str) {
        return (Application) f(super.instantiateApplication(classLoader, str));
    }

    public ContentProvider instantiateProvider(ClassLoader classLoader, String str) {
        return (ContentProvider) f(super.instantiateProvider(classLoader, str));
    }

    public BroadcastReceiver instantiateReceiver(ClassLoader classLoader, String str, Intent intent) {
        return (BroadcastReceiver) f(super.instantiateReceiver(classLoader, str, intent));
    }

    public Service instantiateService(ClassLoader classLoader, String str, Intent intent) {
        return (Service) f(super.instantiateService(classLoader, str, intent));
    }
}
