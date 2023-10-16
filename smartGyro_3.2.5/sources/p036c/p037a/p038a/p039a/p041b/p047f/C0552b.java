package p036c.p037a.p038a.p039a.p041b.p047f;

import java.lang.ref.SoftReference;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/* renamed from: c.a.a.a.b.f.b */
class C0552b extends ThreadLocal<SoftReference<Map<String, SimpleDateFormat>>> {
    C0552b() {
    }

    /* access modifiers changed from: protected */
    public SoftReference<Map<String, SimpleDateFormat>> initialValue() {
        return new SoftReference<>(new HashMap());
    }
}
