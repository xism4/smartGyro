package p000a.p001a.p017d.p023d;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

/* renamed from: a.a.d.d.b */
public abstract class C0164b {

    /* renamed from: a */
    private Object f375a;

    /* renamed from: b */
    private boolean f376b;

    /* renamed from: a.a.d.d.b$a */
    public interface C0165a {
        /* renamed from: a */
        void mo663a(C0164b bVar);

        /* renamed from: a */
        boolean mo664a(C0164b bVar, Menu menu);

        /* renamed from: a */
        boolean mo665a(C0164b bVar, MenuItem menuItem);

        /* renamed from: b */
        boolean mo666b(C0164b bVar, Menu menu);
    }

    /* renamed from: a */
    public abstract void mo646a();

    /* renamed from: a */
    public abstract void mo647a(int i);

    /* renamed from: a */
    public abstract void mo648a(View view);

    /* renamed from: a */
    public abstract void mo649a(CharSequence charSequence);

    /* renamed from: a */
    public void mo650a(Object obj) {
        this.f375a = obj;
    }

    /* renamed from: a */
    public void mo651a(boolean z) {
        this.f376b = z;
    }

    /* renamed from: b */
    public abstract View mo652b();

    /* renamed from: b */
    public abstract void mo653b(int i);

    /* renamed from: b */
    public abstract void mo654b(CharSequence charSequence);

    /* renamed from: c */
    public abstract Menu mo655c();

    /* renamed from: d */
    public abstract MenuInflater mo656d();

    /* renamed from: e */
    public abstract CharSequence mo657e();

    /* renamed from: f */
    public Object mo658f() {
        return this.f375a;
    }

    /* renamed from: g */
    public abstract CharSequence mo659g();

    /* renamed from: h */
    public boolean mo660h() {
        return this.f376b;
    }

    /* renamed from: i */
    public abstract void mo661i();

    /* renamed from: j */
    public abstract boolean mo662j();
}
