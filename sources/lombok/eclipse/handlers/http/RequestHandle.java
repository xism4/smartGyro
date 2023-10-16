package lombok.eclipse.handlers.http;

import android.os.Looper;
import b.c.a.a.e;
import java.lang.ref.WeakReference;

public class RequestHandle {
    private final WeakReference<e> request;

    public RequestHandle(AsyncHttpRequest asyncHttpRequest) {
        this.request = new WeakReference(asyncHttpRequest);
    }

    public boolean cancel(boolean z) {
        AsyncHttpRequest $r3 = (AsyncHttpRequest) this.request.get();
        if ($r3 == null) {
            return false;
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            return $r3.cancel(z);
        }
        new Thread(new EventInfoFragment$1(this, $r3, z)).start();
        return true;
    }

    public boolean isCancelled() {
        AsyncHttpRequest $r3 = (AsyncHttpRequest) this.request.get();
        return $r3 == null || $r3.isCancelled();
    }

    public boolean isFinished() {
        AsyncHttpRequest $r3 = (AsyncHttpRequest) this.request.get();
        return $r3 == null || $r3.isDone();
    }

    public boolean shouldBeGarbageCollected() {
        boolean $z0 = isCancelled() || isFinished();
        if ($z0) {
            this.request.clear();
        }
        return $z0;
    }
}
