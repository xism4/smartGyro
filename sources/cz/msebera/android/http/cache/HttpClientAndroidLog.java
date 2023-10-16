package cz.msebera.android.http.cache;

import android.util.Log;

public class HttpClientAndroidLog {
    private boolean debugEnabled = false;
    private boolean errorEnabled = false;
    private boolean infoEnabled = false;
    private String logTag;
    private boolean traceEnabled = false;
    private boolean warnEnabled = false;

    public HttpClientAndroidLog(Object obj) {
        this.logTag = obj.toString();
    }

    public void debug(Object obj) {
        if (isDebugEnabled()) {
            Log.d(this.logTag, obj.toString());
        }
    }

    public void debug(Object obj, Throwable th) {
        if (isDebugEnabled()) {
            Log.d(this.logTag, obj.toString(), th);
        }
    }

    public void error(Object obj) {
        if (isErrorEnabled()) {
            Log.e(this.logTag, obj.toString());
        }
    }

    public void info(Object obj) {
        if (isInfoEnabled()) {
            Log.i(this.logTag, obj.toString());
        }
    }

    public boolean isDebugEnabled() {
        return this.debugEnabled;
    }

    public boolean isErrorEnabled() {
        return this.errorEnabled;
    }

    public boolean isInfoEnabled() {
        return this.infoEnabled;
    }

    public boolean isWarnEnabled() {
        return this.warnEnabled;
    }

    public void warn(Object obj) {
        if (isWarnEnabled()) {
            Log.w(this.logTag, obj.toString());
        }
    }

    public void warn(Object obj, Throwable th) {
        if (isWarnEnabled()) {
            Log.w(this.logTag, obj.toString(), th);
        }
    }
}
