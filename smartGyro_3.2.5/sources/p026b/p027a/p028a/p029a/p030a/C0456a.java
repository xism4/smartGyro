package p026b.p027a.p028a.p029a.p030a;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.util.Vector;

/* renamed from: b.a.a.a.a.a */
public class C0456a {
    /* renamed from: a */
    public static C0457b m1973a(String[] strArr) {
        C0457b bVar = null;
        for (String str : strArr) {
            if (bVar == null) {
                bVar = new C0457b(str);
            } else {
                bVar.mo2341a(str);
            }
        }
        return bVar;
    }

    /* renamed from: a */
    static String[] m1974a(Context context, int i, int i2) {
        String packageName = context.getPackageName();
        Vector vector = new Vector();
        if (Environment.getExternalStorageState().equals("mounted")) {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            File file = new File(externalStorageDirectory.toString() + "/Android/obb/" + packageName);
            if (file.exists()) {
                if (i > 0) {
                    String str = file + File.separator + "main." + i + "." + packageName + ".obb";
                    if (new File(str).isFile()) {
                        vector.add(str);
                    }
                }
                if (i2 > 0) {
                    String str2 = file + File.separator + "patch." + i + "." + packageName + ".obb";
                    if (new File(str2).isFile()) {
                        vector.add(str2);
                    }
                }
            }
        }
        String[] strArr = new String[vector.size()];
        vector.toArray(strArr);
        return strArr;
    }

    /* renamed from: b */
    public static C0457b m1975b(Context context, int i, int i2) {
        return m1973a(m1974a(context, i, i2));
    }
}
