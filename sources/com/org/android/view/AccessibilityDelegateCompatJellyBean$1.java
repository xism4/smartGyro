package com.org.android.view;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import com.org.android.view.accessibility.AccessibilityNodeInfoCompat;
import com.org.android.view.accessibility.AccessibilityNodeProviderCompat;

final class AccessibilityDelegateCompatJellyBean$1 extends View.AccessibilityDelegate {
    private final AccessibilityDelegateCompat val$bridge;

    AccessibilityDelegateCompatJellyBean$1(AccessibilityDelegateCompat accessibilityDelegateCompat) {
        this.val$bridge = accessibilityDelegateCompat;
    }

    public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        return this.val$bridge.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public AccessibilityNodeProvider getAccessibilityNodeProvider(View view) {
        AccessibilityNodeProviderCompat $r3 = this.val$bridge.getAccessibilityNodeProvider(view);
        if ($r3 != null) {
            return (AccessibilityNodeProvider) $r3.getProvider();
        }
        return null;
    }

    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        this.val$bridge.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
        this.val$bridge.onInitializeAccessibilityNodeInfo(view, AccessibilityNodeInfoCompat.obtain(accessibilityNodeInfo));
    }

    public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        this.val$bridge.onPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return this.val$bridge.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
        return this.val$bridge.performAccessibilityAction(view, i, bundle);
    }

    public void sendAccessibilityEvent(View view, int i) {
        this.val$bridge.sendAccessibilityEvent(view, i);
    }

    public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
        this.val$bridge.sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }
}
