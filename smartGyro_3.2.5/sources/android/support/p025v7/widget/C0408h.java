package android.support.p025v7.widget;

import android.support.p025v7.view.menu.C0316z;
import android.support.p025v7.widget.C0400g;
import android.view.View;

/* renamed from: android.support.v7.widget.h */
class C0408h extends C0354P {

    /* renamed from: j */
    final /* synthetic */ C0400g f1550j;

    /* renamed from: k */
    final /* synthetic */ C0400g.C0404d f1551k;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0408h(C0400g.C0404d dVar, View view, C0400g gVar) {
        super(view);
        this.f1551k = dVar;
        this.f1550j = gVar;
    }

    /* renamed from: a */
    public C0316z mo1120a() {
        C0400g.C0405e eVar = C0400g.this.f1540z;
        if (eVar == null) {
            return null;
        }
        return eVar.mo1461b();
    }

    /* renamed from: b */
    public boolean mo1121b() {
        C0400g.this.mo2152i();
        return true;
    }

    /* renamed from: c */
    public boolean mo1794c() {
        C0400g gVar = C0400g.this;
        if (gVar.f1521B != null) {
            return false;
        }
        gVar.mo2148e();
        return true;
    }
}
