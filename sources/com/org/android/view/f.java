package com.org.android.view;

import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.org.provider.R$id;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.WeakHashMap;

class f {
    private static final ArrayList<WeakReference<View>> m = new ArrayList();
    private WeakReference<KeyEvent> a = null;
    private WeakHashMap<View, Boolean> b = null;
    private SparseArray<WeakReference<View>> f = null;

    f() {
    }

    static f a(View view) {
        f $r2 = (f) view.getTag(R$id.tag_unhandled_key_event_manager);
        if ($r2 != null) {
            return $r2;
        }
        f $r22 = new f();
        view.setTag(R$id.tag_unhandled_key_event_manager, $r22);
        return $r22;
    }

    private void a() {
        WeakHashMap $r2 = this.b;
        if ($r2 != null) {
            $r2.clear();
        }
        if (!m.isEmpty()) {
            synchronized (m) {
                if (this.b == null) {
                    this.b = new WeakHashMap();
                }
                for (int $i0 = m.size() - 1; $i0 >= 0; $i0--) {
                    View $r7 = (View) m.get($i0).get();
                    if ($r7 == null) {
                        m.remove($i0);
                    } else {
                        this.b.put($r7, Boolean.TRUE);
                        for (ViewParent $r8 = $r7.getParent(); $r8 instanceof View; $r8 = $r8.getParent()) {
                            this.b.put((View) $r8, Boolean.TRUE);
                        }
                    }
                }
            }
        }
    }

    private View b(View view, KeyEvent keyEvent) {
        WeakHashMap $r3 = this.b;
        if ($r3 == null || !$r3.containsKey(view)) {
            return null;
        }
        if (view instanceof ViewGroup) {
            ViewGroup $r4 = (ViewGroup) view;
            for (int $i0 = $r4.getChildCount() - 1; $i0 >= 0; $i0--) {
                View $r5 = b($r4.getChildAt($i0), keyEvent);
                if ($r5 != null) {
                    return $r5;
                }
            }
        }
        if (c(view, keyEvent)) {
            return view;
        }
        return null;
    }

    private boolean c(View view, KeyEvent keyEvent) {
        ArrayList $r4 = (ArrayList) view.getTag(R$id.tag_unhandled_key_listeners);
        if ($r4 == null) {
            return false;
        }
        for (int $i0 = $r4.size() - 1; $i0 >= 0; $i0--) {
            if (((l) $r4.get($i0)).onUnhandledKeyEvent(view, keyEvent)) {
                return true;
            }
        }
        return false;
    }

    private SparseArray d() {
        if (this.f == null) {
            this.f = new SparseArray();
        }
        return this.f;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: java.lang.ref.WeakReference} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v0, resolved type: java.lang.ref.WeakReference} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(android.view.KeyEvent r11) {
        /*
            r10 = this;
            java.lang.ref.WeakReference<android.view.KeyEvent> r0 = r10.a
            if (r0 == 0) goto L_0x000c
            java.lang.Object r1 = r0.get()
            if (r1 != r11) goto L_0x000c
            r2 = 0
            return r2
        L_0x000c:
            java.lang.ref.WeakReference r0 = new java.lang.ref.WeakReference
            r0.<init>(r11)
            r10.a = r0
            r0 = 0
            android.util.SparseArray r3 = r10.d()
            int r4 = r11.getAction()
            r2 = 1
            if (r4 != r2) goto L_0x0034
            int r4 = r11.getKeyCode()
            int r4 = r3.indexOfKey(r4)
            if (r4 < 0) goto L_0x0034
            java.lang.Object r1 = r3.valueAt(r4)
            r5 = r1
            java.lang.ref.WeakReference r5 = (java.lang.ref.WeakReference) r5
            r0 = r5
            r3.removeAt(r4)
        L_0x0034:
            if (r0 != 0) goto L_0x0042
            int r4 = r11.getKeyCode()
            java.lang.Object r1 = r3.get(r4)
            r6 = r1
            java.lang.ref.WeakReference r6 = (java.lang.ref.WeakReference) r6
            r0 = r6
        L_0x0042:
            if (r0 == 0) goto L_0x0059
            java.lang.Object r1 = r0.get()
            r8 = r1
            android.view.View r8 = (android.view.View) r8
            r7 = r8
            if (r7 == 0) goto L_0x005b
            boolean r9 = com.org.android.view.ViewCompat.isAttachedToWindow(r7)
            if (r9 == 0) goto L_0x005b
            r10.c(r7, r11)
            r2 = 1
            return r2
        L_0x0059:
            r2 = 0
            return r2
        L_0x005b:
            r2 = 1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.org.android.view.f.a(android.view.KeyEvent):boolean");
    }

    /* access modifiers changed from: package-private */
    public boolean a(View view, KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            a();
        }
        View $r2 = b(view, keyEvent);
        if (keyEvent.getAction() == 0) {
            int $i0 = keyEvent.getKeyCode();
            if ($r2 != null && !KeyEvent.isModifierKey($i0)) {
                d().put($i0, new WeakReference($r2));
            }
        }
        return $r2 != null;
    }
}
