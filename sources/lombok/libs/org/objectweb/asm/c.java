package lombok.libs.org.objectweb.asm;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.util.Vector;

public class c {
    public static ByteVector a(Context context, int i, int i2) {
        return a(export(context, i, i2));
    }

    public static ByteVector a(String[] strArr) {
        ByteVector $r2 = null;
        for (String $r1 : strArr) {
            if ($r2 == null) {
                $r2 = new ByteVector($r1);
            } else {
                $r2.read($r1);
            }
        }
        return $r2;
    }

    static String[] export(Context context, int i, int i2) {
        String $r2 = context.getPackageName();
        Vector $r0 = new Vector();
        if (Environment.getExternalStorageState().equals("mounted")) {
            File $r4 = Environment.getExternalStorageDirectory();
            File $r5 = new File($r4.toString() + "/Android/obb/" + $r2);
            if ($r5.exists()) {
                if (i > 0) {
                    String $r3 = $r5 + File.separator + "main." + i + "." + $r2 + ".obb";
                    if (new File($r3).isFile()) {
                        $r0.add($r3);
                    }
                }
                if (i2 > 0) {
                    String $r22 = $r5 + File.separator + "patch." + i + "." + $r2 + ".obb";
                    if (new File($r22).isFile()) {
                        $r0.add($r22);
                    }
                }
            }
        }
        String[] $r7 = new String[$r0.size()];
        $r0.toArray($r7);
        return $r7;
    }
}
