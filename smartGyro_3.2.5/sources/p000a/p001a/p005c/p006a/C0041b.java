package p000a.p001a.p005c.p006a;

import android.content.Context;
import android.os.Process;
import android.support.p024v4.app.C0187c;

/* renamed from: a.a.c.a.b */
public final class C0041b {
    /* renamed from: a */
    public static int m138a(Context context, String str) {
        return m139a(context, str, Process.myPid(), Process.myUid(), context.getPackageName());
    }

    /* renamed from: a */
    public static int m139a(Context context, String str, int i, int i2, String str2) {
        if (context.checkPermission(str, i, i2) == -1) {
            return -1;
        }
        String a = C0187c.m662a(str);
        if (a == null) {
            return 0;
        }
        if (str2 == null) {
            String[] packagesForUid = context.getPackageManager().getPackagesForUid(i2);
            if (packagesForUid == null || packagesForUid.length <= 0) {
                return -1;
            }
            str2 = packagesForUid[0];
        }
        return C0187c.m661a(context, a, str2) != 0 ? -2 : 0;
    }
}
