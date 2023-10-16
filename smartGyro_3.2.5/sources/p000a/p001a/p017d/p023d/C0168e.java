package p000a.p001a.p017d.p023d;

import android.content.Context;
import android.support.p025v7.view.menu.C0293l;
import android.support.p025v7.widget.ActionBarContextView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.lang.ref.WeakReference;
import p000a.p001a.p017d.p023d.C0164b;

/* renamed from: a.a.d.d.e */
public class C0168e extends C0164b implements C0293l.C0294a {

    /* renamed from: c */
    private Context f382c;

    /* renamed from: d */
    private ActionBarContextView f383d;

    /* renamed from: e */
    private C0164b.C0165a f384e;

    /* renamed from: f */
    private WeakReference<View> f385f;

    /* renamed from: g */
    private boolean f386g;

    /* renamed from: h */
    private boolean f387h;

    /* renamed from: i */
    private C0293l f388i;

    public C0168e(Context context, ActionBarContextView actionBarContextView, C0164b.C0165a aVar, boolean z) {
        this.f382c = context;
        this.f383d = actionBarContextView;
        this.f384e = aVar;
        C0293l lVar = new C0293l(actionBarContextView.getContext());
        lVar.mo1296c(1);
        this.f388i = lVar;
        this.f388i.mo1144a((C0293l.C0294a) this);
        this.f387h = z;
    }

    /* renamed from: a */
    public void mo646a() {
        if (!this.f386g) {
            this.f386g = true;
            this.f383d.sendAccessibilityEvent(32);
            this.f384e.mo663a(this);
        }
    }

    /* renamed from: a */
    public void mo647a(int i) {
        mo649a((CharSequence) this.f382c.getString(i));
    }

    /* renamed from: a */
    public void mo677a(C0293l lVar) {
        mo661i();
        this.f383d.mo1554d();
    }

    /* renamed from: a */
    public void mo648a(View view) {
        this.f383d.setCustomView(view);
        this.f385f = view != null ? new WeakReference<>(view) : null;
    }

    /* renamed from: a */
    public void mo649a(CharSequence charSequence) {
        this.f383d.setSubtitle(charSequence);
    }

    /* renamed from: a */
    public void mo651a(boolean z) {
        super.mo651a(z);
        this.f383d.setTitleOptional(z);
    }

    /* renamed from: a */
    public boolean mo678a(C0293l lVar, MenuItem menuItem) {
        return this.f384e.mo665a((C0164b) this, menuItem);
    }

    /* renamed from: b */
    public View mo652b() {
        WeakReference<View> weakReference = this.f385f;
        if (weakReference != null) {
            return (View) weakReference.get();
        }
        return null;
    }

    /* renamed from: b */
    public void mo653b(int i) {
        mo654b((CharSequence) this.f382c.getString(i));
    }

    /* renamed from: b */
    public void mo654b(CharSequence charSequence) {
        this.f383d.setTitle(charSequence);
    }

    /* renamed from: c */
    public Menu mo655c() {
        return this.f388i;
    }

    /* renamed from: d */
    public MenuInflater mo656d() {
        return new C0171g(this.f383d.getContext());
    }

    /* renamed from: e */
    public CharSequence mo657e() {
        return this.f383d.getSubtitle();
    }

    /* renamed from: g */
    public CharSequence mo659g() {
        return this.f383d.getTitle();
    }

    /* renamed from: i */
    public void mo661i() {
        this.f384e.mo666b(this, this.f388i);
    }

    /* renamed from: j */
    public boolean mo662j() {
        return this.f383d.mo1552b();
    }
}
