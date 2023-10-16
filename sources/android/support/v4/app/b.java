package android.support.v4.app;

import android.app.Activity;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import com.org.android.ui.Resources;

public class b extends Resources {
    private static ByteVector l;

    public static void a(Activity activity, String[] strArr, int i) {
        ByteVector $r2 = l;
        if ($r2 != null && $r2.get(activity, strArr, i)) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            if (activity instanceof MenuItem) {
                ((MenuItem) activity).setTitle(i);
            }
            activity.requestPermissions(strArr, i);
        } else if (activity instanceof Item) {
            new Handler(Looper.getMainLooper()).post(new ActivityUtils$1(strArr, activity, i));
        }
    }
}
