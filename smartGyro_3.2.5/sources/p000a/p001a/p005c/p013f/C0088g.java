package p000a.p001a.p005c.p013f;

import android.os.Build;
import java.util.Arrays;
import java.util.Objects;

/* renamed from: a.a.c.f.g */
public class C0088g {
    /* renamed from: a */
    public static int m311a(Object... objArr) {
        return Build.VERSION.SDK_INT >= 19 ? Objects.hash(objArr) : Arrays.hashCode(objArr);
    }
}
