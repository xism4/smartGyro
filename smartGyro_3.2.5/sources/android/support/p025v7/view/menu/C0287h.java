package android.support.p025v7.view.menu;

import android.os.SystemClock;
import android.support.p025v7.view.menu.C0288i;
import android.support.p025v7.widget.C0377V;
import android.view.MenuItem;

/* renamed from: android.support.v7.view.menu.h */
class C0287h implements C0377V {

    /* renamed from: a */
    final /* synthetic */ C0288i f946a;

    C0287h(C0288i iVar) {
        this.f946a = iVar;
    }

    /* renamed from: a */
    public void mo1243a(C0293l lVar, MenuItem menuItem) {
        C0288i.C0289a aVar = null;
        this.f946a.f955h.removeCallbacksAndMessages((Object) null);
        int size = this.f946a.f957j.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                i = -1;
                break;
            } else if (lVar == this.f946a.f957j.get(i).f975b) {
                break;
            } else {
                i++;
            }
        }
        if (i != -1) {
            int i2 = i + 1;
            if (i2 < this.f946a.f957j.size()) {
                aVar = this.f946a.f957j.get(i2);
            }
            this.f946a.f955h.postAtTime(new C0286g(this, aVar, menuItem, lVar), lVar, SystemClock.uptimeMillis() + 200);
        }
    }

    /* renamed from: b */
    public void mo1244b(C0293l lVar, MenuItem menuItem) {
        this.f946a.f955h.removeCallbacksAndMessages(lVar);
    }
}
