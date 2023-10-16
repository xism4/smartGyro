package com.org.android.ui;

import android.content.Context;
import android.os.Process;
import android.support.v4.app.AppOpsManagerCompat;

public final class PermissionChecker {
    public static int checkPermission(Context context, String str, int i, int i2, String $r2) {
        if (context.checkPermission(str, i, i2) == -1) {
            return -1;
        }
        String $r1 = AppOpsManagerCompat.permissionToOp(str);
        if ($r1 == null) {
            return 0;
        }
        if ($r2 == null) {
            String[] $r4 = context.getPackageManager().getPackagesForUid(i2);
            if ($r4 == null || $r4.length <= 0) {
                return -1;
            }
            $r2 = $r4[0];
        }
        return AppOpsManagerCompat.noteProxyOp(context, $r1, $r2) != 0 ? -2 : 0;
    }

    public static int checkSelfPermission(Context context, String str) {
        return checkPermission(context, str, Process.myPid(), Process.myUid(), context.getPackageName());
    }
}
