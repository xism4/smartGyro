package p000a.p001a.p017d.p023d;

import android.content.Context;
import android.support.p025v7.view.menu.C0314x;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.util.ArrayList;
import p000a.p001a.p005c.p009c.p010a.C0056a;
import p000a.p001a.p005c.p009c.p010a.C0057b;
import p000a.p001a.p005c.p013f.C0090i;
import p000a.p001a.p017d.p023d.C0164b;

/* renamed from: a.a.d.d.f */
public class C0169f extends ActionMode {

    /* renamed from: a */
    final Context f389a;

    /* renamed from: b */
    final C0164b f390b;

    /* renamed from: a.a.d.d.f$a */
    public static class C0170a implements C0164b.C0165a {

        /* renamed from: a */
        final ActionMode.Callback f391a;

        /* renamed from: b */
        final Context f392b;

        /* renamed from: c */
        final ArrayList<C0169f> f393c = new ArrayList<>();

        /* renamed from: d */
        final C0090i<Menu, Menu> f394d = new C0090i<>();

        public C0170a(Context context, ActionMode.Callback callback) {
            this.f392b = context;
            this.f391a = callback;
        }

        /* renamed from: a */
        private Menu m626a(Menu menu) {
            Menu menu2 = this.f394d.get(menu);
            if (menu2 != null) {
                return menu2;
            }
            Menu a = C0314x.m1337a(this.f392b, (C0056a) menu);
            this.f394d.put(menu, a);
            return a;
        }

        /* renamed from: a */
        public void mo663a(C0164b bVar) {
            this.f391a.onDestroyActionMode(mo696b(bVar));
        }

        /* renamed from: a */
        public boolean mo664a(C0164b bVar, Menu menu) {
            return this.f391a.onCreateActionMode(mo696b(bVar), m626a(menu));
        }

        /* renamed from: a */
        public boolean mo665a(C0164b bVar, MenuItem menuItem) {
            return this.f391a.onActionItemClicked(mo696b(bVar), C0314x.m1338a(this.f392b, (C0057b) menuItem));
        }

        /* renamed from: b */
        public ActionMode mo696b(C0164b bVar) {
            int size = this.f393c.size();
            for (int i = 0; i < size; i++) {
                C0169f fVar = this.f393c.get(i);
                if (fVar != null && fVar.f390b == bVar) {
                    return fVar;
                }
            }
            C0169f fVar2 = new C0169f(this.f392b, bVar);
            this.f393c.add(fVar2);
            return fVar2;
        }

        /* renamed from: b */
        public boolean mo666b(C0164b bVar, Menu menu) {
            return this.f391a.onPrepareActionMode(mo696b(bVar), m626a(menu));
        }
    }

    public C0169f(Context context, C0164b bVar) {
        this.f389a = context;
        this.f390b = bVar;
    }

    public void finish() {
        this.f390b.mo646a();
    }

    public View getCustomView() {
        return this.f390b.mo652b();
    }

    public Menu getMenu() {
        return C0314x.m1337a(this.f389a, (C0056a) this.f390b.mo655c());
    }

    public MenuInflater getMenuInflater() {
        return this.f390b.mo656d();
    }

    public CharSequence getSubtitle() {
        return this.f390b.mo657e();
    }

    public Object getTag() {
        return this.f390b.mo658f();
    }

    public CharSequence getTitle() {
        return this.f390b.mo659g();
    }

    public boolean getTitleOptionalHint() {
        return this.f390b.mo660h();
    }

    public void invalidate() {
        this.f390b.mo661i();
    }

    public boolean isTitleOptional() {
        return this.f390b.mo662j();
    }

    public void setCustomView(View view) {
        this.f390b.mo648a(view);
    }

    public void setSubtitle(int i) {
        this.f390b.mo647a(i);
    }

    public void setSubtitle(CharSequence charSequence) {
        this.f390b.mo649a(charSequence);
    }

    public void setTag(Object obj) {
        this.f390b.mo650a(obj);
    }

    public void setTitle(int i) {
        this.f390b.mo653b(i);
    }

    public void setTitle(CharSequence charSequence) {
        this.f390b.mo654b(charSequence);
    }

    public void setTitleOptionalHint(boolean z) {
        this.f390b.mo651a(z);
    }
}
