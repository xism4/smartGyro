package p000a.p001a.p005c.p014g.p015a;

import android.graphics.Rect;
import android.os.Build;
import android.view.accessibility.AccessibilityNodeInfo;

/* renamed from: a.a.c.g.a.a */
public class C0097a {

    /* renamed from: a */
    private final AccessibilityNodeInfo f248a;

    /* renamed from: b */
    public int f249b = -1;

    private C0097a(AccessibilityNodeInfo accessibilityNodeInfo) {
        this.f248a = accessibilityNodeInfo;
    }

    /* renamed from: a */
    public static C0097a m347a(AccessibilityNodeInfo accessibilityNodeInfo) {
        return new C0097a(accessibilityNodeInfo);
    }

    /* renamed from: b */
    private static String m348b(int i) {
        if (i == 1) {
            return "ACTION_FOCUS";
        }
        if (i == 2) {
            return "ACTION_CLEAR_FOCUS";
        }
        switch (i) {
            case 4:
                return "ACTION_SELECT";
            case 8:
                return "ACTION_CLEAR_SELECTION";
            case 16:
                return "ACTION_CLICK";
            case 32:
                return "ACTION_LONG_CLICK";
            case 64:
                return "ACTION_ACCESSIBILITY_FOCUS";
            case 128:
                return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
            case 256:
                return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
            case 512:
                return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
            case 1024:
                return "ACTION_NEXT_HTML_ELEMENT";
            case 2048:
                return "ACTION_PREVIOUS_HTML_ELEMENT";
            case 4096:
                return "ACTION_SCROLL_FORWARD";
            case 8192:
                return "ACTION_SCROLL_BACKWARD";
            case 16384:
                return "ACTION_COPY";
            case 32768:
                return "ACTION_PASTE";
            case 65536:
                return "ACTION_CUT";
            case 131072:
                return "ACTION_SET_SELECTION";
            default:
                return "ACTION_UNKNOWN";
        }
    }

    /* renamed from: a */
    public int mo400a() {
        return this.f248a.getActions();
    }

    /* renamed from: a */
    public void mo401a(int i) {
        this.f248a.addAction(i);
    }

    /* renamed from: a */
    public void mo402a(Rect rect) {
        this.f248a.getBoundsInParent(rect);
    }

    /* renamed from: a */
    public void mo403a(CharSequence charSequence) {
        this.f248a.setClassName(charSequence);
    }

    /* renamed from: a */
    public void mo404a(boolean z) {
        this.f248a.setScrollable(z);
    }

    /* renamed from: b */
    public CharSequence mo405b() {
        return this.f248a.getClassName();
    }

    /* renamed from: b */
    public void mo406b(Rect rect) {
        this.f248a.getBoundsInScreen(rect);
    }

    /* renamed from: c */
    public CharSequence mo407c() {
        return this.f248a.getContentDescription();
    }

    /* renamed from: d */
    public CharSequence mo408d() {
        return this.f248a.getPackageName();
    }

    /* renamed from: e */
    public CharSequence mo409e() {
        return this.f248a.getText();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C0097a.class != obj.getClass()) {
            return false;
        }
        C0097a aVar = (C0097a) obj;
        AccessibilityNodeInfo accessibilityNodeInfo = this.f248a;
        if (accessibilityNodeInfo == null) {
            if (aVar.f248a != null) {
                return false;
            }
        } else if (!accessibilityNodeInfo.equals(aVar.f248a)) {
            return false;
        }
        return true;
    }

    /* renamed from: f */
    public String mo411f() {
        if (Build.VERSION.SDK_INT >= 18) {
            return this.f248a.getViewIdResourceName();
        }
        return null;
    }

    /* renamed from: g */
    public boolean mo412g() {
        return this.f248a.isCheckable();
    }

    /* renamed from: h */
    public boolean mo413h() {
        return this.f248a.isChecked();
    }

    public int hashCode() {
        AccessibilityNodeInfo accessibilityNodeInfo = this.f248a;
        if (accessibilityNodeInfo == null) {
            return 0;
        }
        return accessibilityNodeInfo.hashCode();
    }

    /* renamed from: i */
    public boolean mo415i() {
        return this.f248a.isClickable();
    }

    /* renamed from: j */
    public boolean mo416j() {
        return this.f248a.isEnabled();
    }

    /* renamed from: k */
    public boolean mo417k() {
        return this.f248a.isFocusable();
    }

    /* renamed from: l */
    public boolean mo418l() {
        return this.f248a.isFocused();
    }

    /* renamed from: m */
    public boolean mo419m() {
        return this.f248a.isLongClickable();
    }

    /* renamed from: n */
    public boolean mo420n() {
        return this.f248a.isPassword();
    }

    /* renamed from: o */
    public boolean mo421o() {
        return this.f248a.isScrollable();
    }

    /* renamed from: p */
    public boolean mo422p() {
        return this.f248a.isSelected();
    }

    /* renamed from: q */
    public AccessibilityNodeInfo mo423q() {
        return this.f248a;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        Rect rect = new Rect();
        mo402a(rect);
        sb.append("; boundsInParent: " + rect);
        mo406b(rect);
        sb.append("; boundsInScreen: " + rect);
        sb.append("; packageName: ");
        sb.append(mo408d());
        sb.append("; className: ");
        sb.append(mo405b());
        sb.append("; text: ");
        sb.append(mo409e());
        sb.append("; contentDescription: ");
        sb.append(mo407c());
        sb.append("; viewId: ");
        sb.append(mo411f());
        sb.append("; checkable: ");
        sb.append(mo412g());
        sb.append("; checked: ");
        sb.append(mo413h());
        sb.append("; focusable: ");
        sb.append(mo417k());
        sb.append("; focused: ");
        sb.append(mo418l());
        sb.append("; selected: ");
        sb.append(mo422p());
        sb.append("; clickable: ");
        sb.append(mo415i());
        sb.append("; longClickable: ");
        sb.append(mo419m());
        sb.append("; enabled: ");
        sb.append(mo416j());
        sb.append("; password: ");
        sb.append(mo420n());
        sb.append("; scrollable: " + mo421o());
        sb.append("; [");
        int a = mo400a();
        while (a != 0) {
            int numberOfTrailingZeros = 1 << Integer.numberOfTrailingZeros(a);
            a &= numberOfTrailingZeros ^ -1;
            sb.append(m348b(numberOfTrailingZeros));
            if (a != 0) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
