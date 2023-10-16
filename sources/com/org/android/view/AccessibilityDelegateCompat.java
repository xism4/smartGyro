package com.org.android.view;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeProvider;
import com.org.android.view.accessibility.AccessibilityNodeInfoCompat;
import com.org.android.view.accessibility.AccessibilityNodeProviderCompat;

public class AccessibilityDelegateCompat {
    private static final View.AccessibilityDelegate IMPL = new View.AccessibilityDelegate();
    private final View.AccessibilityDelegate mBridge = new AccessibilityDelegateCompatJellyBean$1(this);

    public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        return IMPL.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view) {
        AccessibilityNodeProvider $r1;
        if (Build.VERSION.SDK_INT < 16 || ($r1 = IMPL.getAccessibilityNodeProvider(view)) == null) {
            return null;
        }
        return new AccessibilityNodeProviderCompat($r1);
    }

    /* access modifiers changed from: package-private */
    public View.AccessibilityDelegate getBridge() {
        return this.mBridge;
    }

    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        IMPL.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        IMPL.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat.obtain());
    }

    public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        IMPL.onPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return IMPL.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 16) {
            return IMPL.performAccessibilityAction(view, i, bundle);
        }
        return false;
    }

    public void sendAccessibilityEvent(View view, int i) {
        IMPL.sendAccessibilityEvent(view, i);
    }

    public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
        IMPL.sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }
}
