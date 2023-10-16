package android.support.v7.app;

import android.content.res.Resources;
import java.lang.Thread;

final class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    final /* synthetic */ Thread.UncaughtExceptionHandler defaultExceptionHandler;

    ExceptionHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.defaultExceptionHandler = uncaughtExceptionHandler;
    }

    private boolean register(Throwable th) {
        String $r2;
        if (!(th instanceof Resources.NotFoundException) || ($r2 = th.getMessage()) == null) {
            return false;
        }
        return $r2.contains("drawable") || $r2.contains("Drawable");
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (register(th)) {
            Resources.NotFoundException $r3 = new Resources.NotFoundException(th.getMessage() + ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.");
            $r3.initCause(th.getCause());
            $r3.setStackTrace(th.getStackTrace());
            this.defaultExceptionHandler.uncaughtException(thread, $r3);
            return;
        }
        this.defaultExceptionHandler.uncaughtException(thread, th);
    }
}
