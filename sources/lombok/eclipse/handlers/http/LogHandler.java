package lombok.eclipse.handlers.http;

import android.os.Build;
import android.util.Log;

public class LogHandler implements LogInterface {
    boolean mLoggingEnabled = true;
    int mLoggingLevel = 2;

    private void log(String str, String str2, Throwable th) {
        Log.wtf(str, str2, th);
    }

    public void d(String str, String str2) {
        log(2, str, str2);
    }

    public void e(String str, String str2) {
        log(6, str, str2);
    }

    public void e(String str, String str2, Throwable th) {
        log(6, str, str2, th);
    }

    public boolean isLoggingEnabled() {
        return this.mLoggingEnabled;
    }

    public void log(int i, String str, String str2) {
        log(i, str, str2, (Throwable) null);
    }

    public void log(int i, String str, String str2, Throwable th) {
        if (isLoggingEnabled() && shouldLog(i)) {
            if (i == 2) {
                Log.v(str, str2, th);
            } else if (i == 3) {
                Log.d(str, str2, th);
            } else if (i == 4) {
                Log.i(str, str2, th);
            } else if (i != 5) {
                if (i != 6) {
                    if (i == 8) {
                        if (Integer.valueOf(Build.VERSION.SDK).intValue() > 8) {
                            log(str, str2, th);
                            return;
                        }
                    } else {
                        return;
                    }
                }
                Log.e(str, str2, th);
            } else {
                Log.w(str, str2, th);
            }
        }
    }

    public boolean shouldLog(int i) {
        return i >= this.mLoggingLevel;
    }

    public void v(String str, String str2) {
        log(2, str, str2);
    }

    public void w(String str, String str2) {
        log(5, str, str2);
    }

    public void w(String str, String str2, Throwable th) {
        log(5, str, str2, th);
    }
}
