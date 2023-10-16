package com.org.android.view.accessibility;

import a.a.c.g.a.a;
import android.graphics.Rect;
import android.os.Build;
import android.view.accessibility.AccessibilityNodeInfo;

public class AccessibilityNodeInfoCompat {
    private final AccessibilityNodeInfo IMPL;
    public int b = -1;

    private AccessibilityNodeInfoCompat(AccessibilityNodeInfo accessibilityNodeInfo) {
        this.IMPL = accessibilityNodeInfo;
    }

    private static String getActionSymbolicName(int i) {
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

    public static AccessibilityNodeInfoCompat obtain(AccessibilityNodeInfo accessibilityNodeInfo) {
        return new AccessibilityNodeInfoCompat(accessibilityNodeInfo);
    }

    public void addAction(int i) {
        this.IMPL.addAction(i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || a.class != obj.getClass()) {
            return false;
        }
        AccessibilityNodeInfoCompat $r3 = (AccessibilityNodeInfoCompat) obj;
        AccessibilityNodeInfo $r4 = this.IMPL;
        return $r4 == null ? $r3.IMPL == null : $r4.equals($r3.IMPL);
    }

    public int getActions() {
        return this.IMPL.getActions();
    }

    public void getBoundsInParent(Rect rect) {
        this.IMPL.getBoundsInParent(rect);
    }

    public void getBoundsInScreen(Rect rect) {
        this.IMPL.getBoundsInScreen(rect);
    }

    public CharSequence getClassName() {
        return this.IMPL.getClassName();
    }

    public CharSequence getContentDescription() {
        return this.IMPL.getContentDescription();
    }

    public CharSequence getPackageName() {
        return this.IMPL.getPackageName();
    }

    public CharSequence getText() {
        return this.IMPL.getText();
    }

    public String getViewIdResourceName() {
        if (Build.VERSION.SDK_INT >= 18) {
            return this.IMPL.getViewIdResourceName();
        }
        return null;
    }

    public int hashCode() {
        AccessibilityNodeInfo $r1 = this.IMPL;
        if ($r1 == null) {
            return 0;
        }
        return $r1.hashCode();
    }

    public boolean isCheckable() {
        return this.IMPL.isCheckable();
    }

    public boolean isChecked() {
        return this.IMPL.isChecked();
    }

    public boolean isClickable() {
        return this.IMPL.isClickable();
    }

    public boolean isEnabled() {
        return this.IMPL.isEnabled();
    }

    public boolean isFocusable() {
        return this.IMPL.isFocusable();
    }

    public boolean isFocused() {
        return this.IMPL.isFocused();
    }

    public boolean isLongClickable() {
        return this.IMPL.isLongClickable();
    }

    public boolean isPassword() {
        return this.IMPL.isPassword();
    }

    public boolean isScrollable() {
        return this.IMPL.isScrollable();
    }

    public boolean isSelected() {
        return this.IMPL.isSelected();
    }

    public AccessibilityNodeInfo obtain() {
        return this.IMPL;
    }

    public void setClassName(CharSequence charSequence) {
        this.IMPL.setClassName(charSequence);
    }

    public void setScrollable(boolean z) {
        this.IMPL.setScrollable(z);
    }

    public String toString() {
        StringBuilder $r1 = new StringBuilder();
        $r1.append(super.toString());
        Rect $r3 = new Rect();
        getBoundsInParent($r3);
        $r1.append("; boundsInParent: " + $r3);
        getBoundsInScreen($r3);
        $r1.append("; boundsInScreen: " + $r3);
        $r1.append("; packageName: ");
        $r1.append(getPackageName());
        $r1.append("; className: ");
        $r1.append(getClassName());
        $r1.append("; text: ");
        $r1.append(getText());
        $r1.append("; contentDescription: ");
        $r1.append(getContentDescription());
        $r1.append("; viewId: ");
        $r1.append(getViewIdResourceName());
        $r1.append("; checkable: ");
        $r1.append(isCheckable());
        $r1.append("; checked: ");
        $r1.append(isChecked());
        $r1.append("; focusable: ");
        $r1.append(isFocusable());
        $r1.append("; focused: ");
        $r1.append(isFocused());
        $r1.append("; selected: ");
        $r1.append(isSelected());
        $r1.append("; clickable: ");
        $r1.append(isClickable());
        $r1.append("; longClickable: ");
        $r1.append(isLongClickable());
        $r1.append("; enabled: ");
        $r1.append(isEnabled());
        $r1.append("; password: ");
        $r1.append(isPassword());
        $r1.append("; scrollable: " + isScrollable());
        $r1.append("; [");
        int $i0 = getActions();
        while ($i0 != 0) {
            int $i1 = 1 << Integer.numberOfTrailingZeros($i0);
            $i0 &= ~$i1;
            $r1.append(getActionSymbolicName($i1));
            if ($i0 != 0) {
                $r1.append(", ");
            }
        }
        $r1.append("]");
        return $r1.toString();
    }
}
