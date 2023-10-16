package android.support.p025v7.app;

import android.content.res.Resources;
import java.lang.Thread;

/* renamed from: android.support.v7.app.n */
class C0253n implements Thread.UncaughtExceptionHandler {

    /* renamed from: a */
    final /* synthetic */ Thread.UncaughtExceptionHandler f758a;

    C0253n(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.f758a = uncaughtExceptionHandler;
    }

    /* renamed from: a */
    private boolean m981a(Throwable th) {
        String message;
        if (!(th instanceof Resources.NotFoundException) || (message = th.getMessage()) == null) {
            return false;
        }
        return message.contains("drawable") || message.contains("Drawable");
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (m981a(th)) {
            Resources.NotFoundException notFoundException = new Resources.NotFoundException(th.getMessage() + ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.");
            notFoundException.initCause(th.getCause());
            notFoundException.setStackTrace(th.getStackTrace());
            this.f758a.uncaughtException(thread, notFoundException);
            return;
        }
        this.f758a.uncaughtException(thread, th);
    }
}
