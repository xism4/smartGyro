package android.support.p025v7.view.menu;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import java.util.Iterator;
import java.util.Map;
import p000a.p001a.p005c.p009c.p010a.C0057b;
import p000a.p001a.p005c.p009c.p010a.C0058c;
import p000a.p001a.p005c.p013f.C0078b;

/* renamed from: android.support.v7.view.menu.c */
abstract class C0282c<T> extends C0283d<T> {

    /* renamed from: b */
    final Context f936b;

    /* renamed from: c */
    private Map<C0057b, MenuItem> f937c;

    /* renamed from: d */
    private Map<C0058c, SubMenu> f938d;

    C0282c(Context context, T t) {
        super(t);
        this.f936b = context;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final MenuItem mo1234a(MenuItem menuItem) {
        if (!(menuItem instanceof C0057b)) {
            return menuItem;
        }
        C0057b bVar = (C0057b) menuItem;
        if (this.f937c == null) {
            this.f937c = new C0078b();
        }
        MenuItem menuItem2 = this.f937c.get(menuItem);
        if (menuItem2 != null) {
            return menuItem2;
        }
        MenuItem a = C0314x.m1338a(this.f936b, bVar);
        this.f937c.put(bVar, a);
        return a;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final SubMenu mo1235a(SubMenu subMenu) {
        if (!(subMenu instanceof C0058c)) {
            return subMenu;
        }
        C0058c cVar = (C0058c) subMenu;
        if (this.f938d == null) {
            this.f938d = new C0078b();
        }
        SubMenu subMenu2 = this.f938d.get(cVar);
        if (subMenu2 != null) {
            return subMenu2;
        }
        SubMenu a = C0314x.m1339a(this.f936b, cVar);
        this.f938d.put(cVar, a);
        return a;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo1236a(int i) {
        Map<C0057b, MenuItem> map = this.f937c;
        if (map != null) {
            Iterator<C0057b> it = map.keySet().iterator();
            while (it.hasNext()) {
                if (i == it.next().getGroupId()) {
                    it.remove();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final void mo1237b() {
        Map<C0057b, MenuItem> map = this.f937c;
        if (map != null) {
            map.clear();
        }
        Map<C0058c, SubMenu> map2 = this.f938d;
        if (map2 != null) {
            map2.clear();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final void mo1238b(int i) {
        Map<C0057b, MenuItem> map = this.f937c;
        if (map != null) {
            Iterator<C0057b> it = map.keySet().iterator();
            while (it.hasNext()) {
                if (i == it.next().getItemId()) {
                    it.remove();
                    return;
                }
            }
        }
    }
}
