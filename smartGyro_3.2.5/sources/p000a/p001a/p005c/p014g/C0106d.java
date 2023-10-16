package p000a.p001a.p005c.p014g;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import p000a.p001a.p005c.p014g.p015a.C0097a;
import p000a.p001a.p005c.p014g.p015a.C0098b;

/* renamed from: a.a.c.g.d */
public class C0106d {

    /* renamed from: a */
    private static final View.AccessibilityDelegate f258a = new View.AccessibilityDelegate();

    /* renamed from: b */
    private final View.AccessibilityDelegate f259b = new C0107a(this);

    /* renamed from: a.a.c.g.d$a */
    private static final class C0107a extends View.AccessibilityDelegate {

        /* renamed from: a */
        private final C0106d f260a;

        C0107a(C0106d dVar) {
            this.f260a = dVar;
        }

        public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            return this.f260a.mo438a(view, accessibilityEvent);
        }

        public AccessibilityNodeProvider getAccessibilityNodeProvider(View view) {
            C0098b a = this.f260a.mo433a(view);
            if (a != null) {
                return (AccessibilityNodeProvider) a.mo425a();
            }
            return null;
        }

        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.f260a.mo440b(view, accessibilityEvent);
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            this.f260a.mo436a(view, C0097a.m347a(accessibilityNodeInfo));
        }

        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.f260a.mo441c(view, accessibilityEvent);
        }

        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return this.f260a.mo439a(viewGroup, view, accessibilityEvent);
        }

        public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            return this.f260a.mo437a(view, i, bundle);
        }

        public void sendAccessibilityEvent(View view, int i) {
            this.f260a.mo435a(view, i);
        }

        public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
            this.f260a.mo442d(view, accessibilityEvent);
        }
    }

    /* renamed from: a */
    public C0098b mo433a(View view) {
        AccessibilityNodeProvider accessibilityNodeProvider;
        if (Build.VERSION.SDK_INT < 16 || (accessibilityNodeProvider = f258a.getAccessibilityNodeProvider(view)) == null) {
            return null;
        }
        return new C0098b(accessibilityNodeProvider);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public View.AccessibilityDelegate mo434a() {
        return this.f259b;
    }

    /* renamed from: a */
    public void mo435a(View view, int i) {
        f258a.sendAccessibilityEvent(view, i);
    }

    /* renamed from: a */
    public void mo436a(View view, C0097a aVar) {
        f258a.onInitializeAccessibilityNodeInfo(view, aVar.mo423q());
    }

    /* renamed from: a */
    public boolean mo437a(View view, int i, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 16) {
            return f258a.performAccessibilityAction(view, i, bundle);
        }
        return false;
    }

    /* renamed from: a */
    public boolean mo438a(View view, AccessibilityEvent accessibilityEvent) {
        return f258a.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    /* renamed from: a */
    public boolean mo439a(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return f258a.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    /* renamed from: b */
    public void mo440b(View view, AccessibilityEvent accessibilityEvent) {
        f258a.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    /* renamed from: c */
    public void mo441c(View view, AccessibilityEvent accessibilityEvent) {
        f258a.onPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    /* renamed from: d */
    public void mo442d(View view, AccessibilityEvent accessibilityEvent) {
        f258a.sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }
}
