package android.support.p025v7.view.menu;

import android.content.Context;
import android.graphics.Rect;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;

/* renamed from: android.support.v7.view.menu.s */
abstract class C0307s implements C0316z, C0310v, AdapterView.OnItemClickListener {

    /* renamed from: a */
    private Rect f1065a;

    C0307s() {
    }

    /* renamed from: a */
    protected static int m1293a(ListAdapter listAdapter, ViewGroup viewGroup, Context context, int i) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        int count = listAdapter.getCount();
        ViewGroup viewGroup2 = viewGroup;
        View view = null;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < count; i4++) {
            int itemViewType = listAdapter.getItemViewType(i4);
            if (itemViewType != i3) {
                view = null;
                i3 = itemViewType;
            }
            if (viewGroup2 == null) {
                viewGroup2 = new FrameLayout(context);
            }
            view = listAdapter.getView(i4, view, viewGroup2);
            view.measure(makeMeasureSpec, makeMeasureSpec2);
            int measuredWidth = view.getMeasuredWidth();
            if (measuredWidth >= i) {
                return i;
            }
            if (measuredWidth > i2) {
                i2 = measuredWidth;
            }
        }
        return i2;
    }

    /* renamed from: a */
    protected static C0292k m1294a(ListAdapter listAdapter) {
        return listAdapter instanceof HeaderViewListAdapter ? (C0292k) ((HeaderViewListAdapter) listAdapter).getWrappedAdapter() : (C0292k) listAdapter;
    }

    /* renamed from: b */
    protected static boolean m1295b(C0293l lVar) {
        int size = lVar.size();
        for (int i = 0; i < size; i++) {
            MenuItem item = lVar.getItem(i);
            if (item.isVisible() && item.getIcon() != null) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public abstract void mo1125a(int i);

    /* renamed from: a */
    public void mo1225a(Context context, C0293l lVar) {
    }

    /* renamed from: a */
    public void mo1450a(Rect rect) {
        this.f1065a = rect;
    }

    /* renamed from: a */
    public abstract void mo1126a(C0293l lVar);

    /* renamed from: a */
    public abstract void mo1129a(View view);

    /* renamed from: a */
    public abstract void mo1130a(PopupWindow.OnDismissListener onDismissListener);

    /* renamed from: a */
    public boolean mo1229a(C0293l lVar, C0299p pVar) {
        return false;
    }

    /* renamed from: b */
    public abstract void mo1134b(int i);

    /* renamed from: b */
    public abstract void mo1135b(boolean z);

    /* renamed from: b */
    public boolean mo1233b(C0293l lVar, C0299p pVar) {
        return false;
    }

    /* renamed from: c */
    public abstract void mo1138c(int i);

    /* renamed from: c */
    public abstract void mo1139c(boolean z);

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public boolean mo1245e() {
        return true;
    }

    /* renamed from: f */
    public Rect mo1451f() {
        return this.f1065a;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        ListAdapter listAdapter = (ListAdapter) adapterView.getAdapter();
        m1294a(listAdapter).f988a.mo1281a((MenuItem) listAdapter.getItem(i), (C0310v) this, mo1245e() ? 0 : 4);
    }
}
