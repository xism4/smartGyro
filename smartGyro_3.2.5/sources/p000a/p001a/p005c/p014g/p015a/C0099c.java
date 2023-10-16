package p000a.p001a.p005c.p014g.p015a;

import android.os.Build;
import android.view.accessibility.AccessibilityRecord;

/* renamed from: a.a.c.g.a.c */
public class C0099c {
    /* renamed from: a */
    public static void m372a(AccessibilityRecord accessibilityRecord, int i) {
        if (Build.VERSION.SDK_INT >= 15) {
            accessibilityRecord.setMaxScrollX(i);
        }
    }

    /* renamed from: b */
    public static void m373b(AccessibilityRecord accessibilityRecord, int i) {
        if (Build.VERSION.SDK_INT >= 15) {
            accessibilityRecord.setMaxScrollY(i);
        }
    }
}
