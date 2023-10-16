package android.support.p025v7.view.menu;

import android.support.p025v7.view.menu.C0288i;
import android.view.MenuItem;

/* renamed from: android.support.v7.view.menu.g */
class C0286g implements Runnable {

    /* renamed from: a */
    final /* synthetic */ C0288i.C0289a f942a;

    /* renamed from: b */
    final /* synthetic */ MenuItem f943b;

    /* renamed from: c */
    final /* synthetic */ C0293l f944c;

    /* renamed from: d */
    final /* synthetic */ C0287h f945d;

    C0286g(C0287h hVar, C0288i.C0289a aVar, MenuItem menuItem, C0293l lVar) {
        this.f945d = hVar;
        this.f942a = aVar;
        this.f943b = menuItem;
        this.f944c = lVar;
    }

    public void run() {
        C0288i.C0289a aVar = this.f942a;
        if (aVar != null) {
            this.f945d.f946a.f949B = true;
            aVar.f975b.mo1279a(false);
            this.f945d.f946a.f949B = false;
        }
        if (this.f943b.isEnabled() && this.f943b.hasSubMenu()) {
            this.f944c.mo1280a(this.f943b, 4);
        }
    }
}
