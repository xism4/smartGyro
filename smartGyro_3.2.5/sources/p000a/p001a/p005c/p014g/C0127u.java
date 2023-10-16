package p000a.p001a.p005c.p014g;

import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import p000a.p001a.p002a.C0001b;

/* renamed from: a.a.c.g.u */
public class C0127u {

    /* renamed from: a */
    private static final AtomicInteger f278a = new AtomicInteger(1);

    /* renamed from: b */
    private static Field f279b;

    /* renamed from: c */
    private static boolean f280c;

    /* renamed from: d */
    private static WeakHashMap<View, C0134z> f281d = null;

    /* renamed from: e */
    private static boolean f282e = false;

    /* renamed from: a.a.c.g.u$a */
    public interface C0128a {
        boolean onUnhandledKeyEvent(View view, KeyEvent keyEvent);
    }

    /* renamed from: a.a.c.g.u$b */
    static class C0129b {

        /* renamed from: a */
        private static final ArrayList<WeakReference<View>> f283a = new ArrayList<>();

        /* renamed from: b */
        private WeakHashMap<View, Boolean> f284b = null;

        /* renamed from: c */
        private SparseArray<WeakReference<View>> f285c = null;

        /* renamed from: d */
        private WeakReference<KeyEvent> f286d = null;

        C0129b() {
        }

        /* renamed from: a */
        static C0129b m459a(View view) {
            C0129b bVar = (C0129b) view.getTag(C0001b.tag_unhandled_key_event_manager);
            if (bVar != null) {
                return bVar;
            }
            C0129b bVar2 = new C0129b();
            view.setTag(C0001b.tag_unhandled_key_event_manager, bVar2);
            return bVar2;
        }

        /* renamed from: a */
        private SparseArray<WeakReference<View>> m460a() {
            if (this.f285c == null) {
                this.f285c = new SparseArray<>();
            }
            return this.f285c;
        }

        /* renamed from: b */
        private View m461b(View view, KeyEvent keyEvent) {
            WeakHashMap<View, Boolean> weakHashMap = this.f284b;
            if (weakHashMap != null && weakHashMap.containsKey(view)) {
                if (view instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view;
                    for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                        View b = m461b(viewGroup.getChildAt(childCount), keyEvent);
                        if (b != null) {
                            return b;
                        }
                    }
                }
                if (m463c(view, keyEvent)) {
                    return view;
                }
            }
            return null;
        }

        /* renamed from: b */
        private void m462b() {
            WeakHashMap<View, Boolean> weakHashMap = this.f284b;
            if (weakHashMap != null) {
                weakHashMap.clear();
            }
            if (!f283a.isEmpty()) {
                synchronized (f283a) {
                    if (this.f284b == null) {
                        this.f284b = new WeakHashMap<>();
                    }
                    for (int size = f283a.size() - 1; size >= 0; size--) {
                        View view = (View) f283a.get(size).get();
                        if (view == null) {
                            f283a.remove(size);
                        } else {
                            this.f284b.put(view, Boolean.TRUE);
                            for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
                                this.f284b.put((View) parent, Boolean.TRUE);
                            }
                        }
                    }
                }
            }
        }

        /* renamed from: c */
        private boolean m463c(View view, KeyEvent keyEvent) {
            ArrayList arrayList = (ArrayList) view.getTag(C0001b.tag_unhandled_key_listeners);
            if (arrayList == null) {
                return false;
            }
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                if (((C0128a) arrayList.get(size)).onUnhandledKeyEvent(view, keyEvent)) {
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public boolean mo497a(KeyEvent keyEvent) {
            int indexOfKey;
            WeakReference<KeyEvent> weakReference = this.f286d;
            if (weakReference != null && weakReference.get() == keyEvent) {
                return false;
            }
            this.f286d = new WeakReference<>(keyEvent);
            WeakReference weakReference2 = null;
            SparseArray<WeakReference<View>> a = m460a();
            if (keyEvent.getAction() == 1 && (indexOfKey = a.indexOfKey(keyEvent.getKeyCode())) >= 0) {
                weakReference2 = a.valueAt(indexOfKey);
                a.removeAt(indexOfKey);
            }
            if (weakReference2 == null) {
                weakReference2 = a.get(keyEvent.getKeyCode());
            }
            if (weakReference2 == null) {
                return false;
            }
            View view = (View) weakReference2.get();
            if (view != null && C0127u.m454h(view)) {
                m463c(view, keyEvent);
            }
            return true;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public boolean mo498a(View view, KeyEvent keyEvent) {
            if (keyEvent.getAction() == 0) {
                m462b();
            }
            View b = m461b(view, keyEvent);
            if (keyEvent.getAction() == 0) {
                int keyCode = keyEvent.getKeyCode();
                if (b != null && !KeyEvent.isModifierKey(keyCode)) {
                    m460a().put(keyCode, new WeakReference(b));
                }
            }
            return b != null;
        }
    }

    /* renamed from: a */
    public static C0095D m435a(View view, C0095D d) {
        if (Build.VERSION.SDK_INT < 21) {
            return d;
        }
        WindowInsets windowInsets = (WindowInsets) C0095D.m341a(d);
        WindowInsets onApplyWindowInsets = view.onApplyWindowInsets(windowInsets);
        if (onApplyWindowInsets != windowInsets) {
            windowInsets = new WindowInsets(onApplyWindowInsets);
        }
        return C0095D.m340a((Object) windowInsets);
    }

    /* renamed from: a */
    public static C0134z m436a(View view) {
        if (f281d == null) {
            f281d = new WeakHashMap<>();
        }
        C0134z zVar = f281d.get(view);
        if (zVar != null) {
            return zVar;
        }
        C0134z zVar2 = new C0134z(view);
        f281d.put(view, zVar2);
        return zVar2;
    }

    /* renamed from: a */
    public static void m437a(View view, float f) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setElevation(f);
        }
    }

    /* renamed from: a */
    public static void m438a(View view, int i, int i2) {
        if (Build.VERSION.SDK_INT >= 23) {
            view.setScrollIndicators(i, i2);
        }
    }

    /* renamed from: a */
    public static void m439a(View view, C0106d dVar) {
        view.setAccessibilityDelegate(dVar == null ? null : dVar.mo434a());
    }

    /* renamed from: a */
    public static void m440a(View view, C0123q qVar) {
        if (Build.VERSION.SDK_INT < 21) {
            return;
        }
        if (qVar == null) {
            view.setOnApplyWindowInsetsListener((View.OnApplyWindowInsetsListener) null);
        } else {
            view.setOnApplyWindowInsetsListener(new C0126t(qVar));
        }
    }

    /* renamed from: a */
    public static void m441a(View view, ColorStateList colorStateList) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setBackgroundTintList(colorStateList);
            if (Build.VERSION.SDK_INT == 21) {
                Drawable background = view.getBackground();
                boolean z = (view.getBackgroundTintList() == null && view.getBackgroundTintMode() == null) ? false : true;
                if (background != null && z) {
                    if (background.isStateful()) {
                        background.setState(view.getDrawableState());
                    }
                    view.setBackground(background);
                }
            }
        } else if (view instanceof C0125s) {
            ((C0125s) view).setSupportBackgroundTintList(colorStateList);
        }
    }

    /* renamed from: a */
    public static void m442a(View view, PorterDuff.Mode mode) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setBackgroundTintMode(mode);
            if (Build.VERSION.SDK_INT == 21) {
                Drawable background = view.getBackground();
                boolean z = (view.getBackgroundTintList() == null && view.getBackgroundTintMode() == null) ? false : true;
                if (background != null && z) {
                    if (background.isStateful()) {
                        background.setState(view.getDrawableState());
                    }
                    view.setBackground(background);
                }
            }
        } else if (view instanceof C0125s) {
            ((C0125s) view).setSupportBackgroundTintMode(mode);
        }
    }

    /* renamed from: a */
    public static void m443a(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    /* renamed from: a */
    public static void m444a(View view, Runnable runnable) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.postOnAnimation(runnable);
        } else {
            view.postDelayed(runnable, ValueAnimator.getFrameDelay());
        }
    }

    /* renamed from: a */
    public static void m445a(View view, Runnable runnable, long j) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.postOnAnimationDelayed(runnable, j);
        } else {
            view.postDelayed(runnable, ValueAnimator.getFrameDelay() + j);
        }
    }

    /* renamed from: a */
    static boolean m446a(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return C0129b.m459a(view).mo498a(view, keyEvent);
    }

    /* renamed from: b */
    public static ColorStateList m447b(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getBackgroundTintList();
        }
        if (view instanceof C0125s) {
            return ((C0125s) view).getSupportBackgroundTintList();
        }
        return null;
    }

    /* renamed from: b */
    static boolean m448b(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return C0129b.m459a(view).mo497a(keyEvent);
    }

    /* renamed from: c */
    public static PorterDuff.Mode m449c(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getBackgroundTintMode();
        }
        if (view instanceof C0125s) {
            return ((C0125s) view).getSupportBackgroundTintMode();
        }
        return null;
    }

    /* renamed from: d */
    public static int m450d(View view) {
        if (Build.VERSION.SDK_INT >= 17) {
            return view.getLayoutDirection();
        }
        return 0;
    }

    /* renamed from: e */
    public static int m451e(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getMinimumHeight();
        }
        if (!f280c) {
            try {
                f279b = View.class.getDeclaredField("mMinHeight");
                f279b.setAccessible(true);
            } catch (NoSuchFieldException unused) {
            }
            f280c = true;
        }
        Field field = f279b;
        if (field == null) {
            return 0;
        }
        try {
            return ((Integer) field.get(view)).intValue();
        } catch (Exception unused2) {
            return 0;
        }
    }

    /* renamed from: f */
    public static int m452f(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getWindowSystemUiVisibility();
        }
        return 0;
    }

    /* renamed from: g */
    public static boolean m453g(View view) {
        if (Build.VERSION.SDK_INT >= 15) {
            return view.hasOnClickListeners();
        }
        return false;
    }

    /* renamed from: h */
    public static boolean m454h(View view) {
        return Build.VERSION.SDK_INT >= 19 ? view.isAttachedToWindow() : view.getWindowToken() != null;
    }

    /* renamed from: i */
    public static boolean m455i(View view) {
        return Build.VERSION.SDK_INT >= 19 ? view.isLaidOut() : view.getWidth() > 0 && view.getHeight() > 0;
    }

    /* renamed from: j */
    public static void m456j(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.postInvalidateOnAnimation();
        } else {
            view.postInvalidate();
        }
    }

    /* renamed from: k */
    public static void m457k(View view) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 20) {
            view.requestApplyInsets();
        } else if (i >= 16) {
            view.requestFitSystemWindows();
        }
    }

    /* renamed from: l */
    public static void m458l(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.stopNestedScroll();
        } else if (view instanceof C0118l) {
            ((C0118l) view).stopNestedScroll();
        }
    }
}
