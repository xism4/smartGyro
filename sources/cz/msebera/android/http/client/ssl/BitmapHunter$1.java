package cz.msebera.android.http.client.ssl;

import java.lang.ref.SoftReference;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

final class BitmapHunter$1 extends ThreadLocal<SoftReference<Map<String, SimpleDateFormat>>> {
    BitmapHunter$1() {
    }

    /* access modifiers changed from: protected */
    public SoftReference initialValue() {
        return new SoftReference(new HashMap());
    }
}
