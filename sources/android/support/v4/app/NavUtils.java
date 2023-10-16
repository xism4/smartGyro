package android.support.v4.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;

public final class NavUtils {
    public static String getParentActivityName(Activity activity) {
        try {
            return getParentActivityName(activity, activity.getComponentName());
        } catch (PackageManager.NameNotFoundException $r3) {
            throw new IllegalArgumentException($r3);
        }
    }

    public static String getParentActivityName(Context context, ComponentName componentName) {
        String $r4;
        String $r42;
        ActivityInfo $r3 = context.getPackageManager().getActivityInfo(componentName, 128);
        if (Build.VERSION.SDK_INT >= 16 && ($r42 = $r3.parentActivityName) != null) {
            return $r42;
        }
        if ($r3.metaData == null || ($r4 = $r3.metaData.getString("android.support.PARENT_ACTIVITY")) == null) {
            return null;
        }
        if ($r4.charAt(0) != '.') {
            return $r4;
        }
        return context.getPackageName() + $r4;
    }
}
