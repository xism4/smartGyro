package android.support.p025v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.p025v7.view.menu.C0292k;
import android.support.p025v7.view.menu.C0293l;
import android.support.p025v7.view.menu.C0299p;
import android.support.p025v7.view.menu.ListMenuItemView;
import android.transition.Transition;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import java.lang.reflect.Method;

/* renamed from: android.support.v7.widget.W */
public class C0379W extends C0371U implements C0377V {

    /* renamed from: K */
    private static Method f1463K;

    /* renamed from: L */
    private C0377V f1464L;

    /* renamed from: android.support.v7.widget.W$a */
    public static class C0380a extends C0349N {

        /* renamed from: o */
        final int f1465o;

        /* renamed from: p */
        final int f1466p;

        /* renamed from: q */
        private C0377V f1467q;

        /* renamed from: r */
        private MenuItem f1468r;

        public C0380a(Context context, boolean z) {
            super(context, z);
            Configuration configuration = context.getResources().getConfiguration();
            if (Build.VERSION.SDK_INT < 17 || 1 != configuration.getLayoutDirection()) {
                this.f1465o = 22;
                this.f1466p = 21;
                return;
            }
            this.f1465o = 21;
            this.f1466p = 22;
        }

        /* renamed from: a */
        public /* bridge */ /* synthetic */ int mo1777a(int i, int i2, int i3, int i4, int i5) {
            return super.mo1777a(i, i2, i3, i4, i5);
        }

        /* renamed from: a */
        public /* bridge */ /* synthetic */ boolean mo1778a(MotionEvent motionEvent, int i) {
            return super.mo1778a(motionEvent, i);
        }

        public /* bridge */ /* synthetic */ boolean hasFocus() {
            return super.hasFocus();
        }

        public /* bridge */ /* synthetic */ boolean hasWindowFocus() {
            return super.hasWindowFocus();
        }

        public /* bridge */ /* synthetic */ boolean isFocused() {
            return super.isFocused();
        }

        public /* bridge */ /* synthetic */ boolean isInTouchMode() {
            return super.isInTouchMode();
        }

        public boolean onHoverEvent(MotionEvent motionEvent) {
            int i;
            int pointToPosition;
            int i2;
            if (this.f1467q != null) {
                ListAdapter adapter = getAdapter();
                if (adapter instanceof HeaderViewListAdapter) {
                    HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
                    i = headerViewListAdapter.getHeadersCount();
                    adapter = headerViewListAdapter.getWrappedAdapter();
                } else {
                    i = 0;
                }
                C0292k kVar = (C0292k) adapter;
                C0299p pVar = null;
                if (motionEvent.getAction() != 10 && (pointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY())) != -1 && (i2 = pointToPosition - i) >= 0 && i2 < kVar.getCount()) {
                    pVar = kVar.getItem(i2);
                }
                MenuItem menuItem = this.f1468r;
                if (menuItem != pVar) {
                    C0293l b = kVar.mo1260b();
                    if (menuItem != null) {
                        this.f1467q.mo1244b(b, menuItem);
                    }
                    this.f1468r = pVar;
                    if (pVar != null) {
                        this.f1467q.mo1243a(b, pVar);
                    }
                }
            }
            return super.onHoverEvent(motionEvent);
        }

        public boolean onKeyDown(int i, KeyEvent keyEvent) {
            ListMenuItemView listMenuItemView = (ListMenuItemView) getSelectedView();
            if (listMenuItemView != null && i == this.f1465o) {
                if (listMenuItemView.isEnabled() && listMenuItemView.getItemData().hasSubMenu()) {
                    performItemClick(listMenuItemView, getSelectedItemPosition(), getSelectedItemId());
                }
                return true;
            } else if (listMenuItemView == null || i != this.f1466p) {
                return super.onKeyDown(i, keyEvent);
            } else {
                setSelection(-1);
                ((C0292k) getAdapter()).mo1260b().mo1279a(false);
                return true;
            }
        }

        public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
            return super.onTouchEvent(motionEvent);
        }

        public void setHoverListener(C0377V v) {
            this.f1467q = v;
        }

        public /* bridge */ /* synthetic */ void setSelector(Drawable drawable) {
            super.setSelector(drawable);
        }
    }

    static {
        try {
            f1463K = PopupWindow.class.getDeclaredMethod("setTouchModal", new Class[]{Boolean.TYPE});
        } catch (NoSuchMethodException unused) {
            Log.i("MenuPopupWindow", "Could not find method setTouchModal() on PopupWindow. Oh well.");
        }
    }

    public C0379W(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public C0349N mo1994a(Context context, boolean z) {
        C0380a aVar = new C0380a(context, z);
        aVar.setHoverListener(this);
        return aVar;
    }

    /* renamed from: a */
    public void mo1243a(C0293l lVar, MenuItem menuItem) {
        C0377V v = this.f1464L;
        if (v != null) {
            v.mo1243a(lVar, menuItem);
        }
    }

    /* renamed from: a */
    public void mo2039a(C0377V v) {
        this.f1464L = v;
    }

    /* renamed from: a */
    public void mo2040a(Object obj) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.f1429J.setEnterTransition((Transition) obj);
        }
    }

    /* renamed from: b */
    public void mo1244b(C0293l lVar, MenuItem menuItem) {
        C0377V v = this.f1464L;
        if (v != null) {
            v.mo1244b(lVar, menuItem);
        }
    }

    /* renamed from: b */
    public void mo2041b(Object obj) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.f1429J.setExitTransition((Transition) obj);
        }
    }

    /* renamed from: c */
    public void mo2042c(boolean z) {
        Method method = f1463K;
        if (method != null) {
            try {
                method.invoke(this.f1429J, new Object[]{Boolean.valueOf(z)});
            } catch (Exception unused) {
                Log.i("MenuPopupWindow", "Could not invoke setTouchModal() on PopupWindow. Oh well.");
            }
        }
    }
}
