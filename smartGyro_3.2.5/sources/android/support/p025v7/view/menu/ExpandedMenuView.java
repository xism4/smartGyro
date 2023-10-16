package android.support.p025v7.view.menu;

import android.content.Context;
import android.support.p025v7.view.menu.C0293l;
import android.support.p025v7.widget.C0439ta;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/* renamed from: android.support.v7.view.menu.ExpandedMenuView */
public final class ExpandedMenuView extends ListView implements C0293l.C0295b, C0312w, AdapterView.OnItemClickListener {

    /* renamed from: a */
    private static final int[] f883a = {16842964, 16843049};

    /* renamed from: b */
    private C0293l f884b;

    /* renamed from: c */
    private int f885c;

    public ExpandedMenuView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842868);
    }

    public ExpandedMenuView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        setOnItemClickListener(this);
        C0439ta a = C0439ta.m1902a(context, attributeSet, f883a, i, 0);
        if (a.mo2287g(0)) {
            setBackgroundDrawable(a.mo2277b(0));
        }
        if (a.mo2287g(1)) {
            setDivider(a.mo2277b(1));
        }
        a.mo2274a();
    }

    /* renamed from: a */
    public void mo1174a(C0293l lVar) {
        this.f884b = lVar;
    }

    /* renamed from: a */
    public boolean mo1175a(C0299p pVar) {
        return this.f884b.mo1280a((MenuItem) pVar, 0);
    }

    public int getWindowAnimations() {
        return this.f885c;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setChildrenDrawingCacheEnabled(false);
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        mo1175a((C0299p) getAdapter().getItem(i));
    }
}
