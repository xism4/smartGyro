package android.support.p025v7.view.menu;

import android.content.Context;
import android.support.p025v7.view.menu.C0300q;
import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.View;
import p000a.p001a.p005c.p009c.p010a.C0057b;
import p000a.p001a.p005c.p014g.C0108e;

/* renamed from: android.support.v7.view.menu.r */
class C0305r extends C0300q {

    /* renamed from: android.support.v7.view.menu.r$a */
    class C0306a extends C0300q.C0301a implements ActionProvider.VisibilityListener {

        /* renamed from: f */
        C0108e.C0110b f1063f;

        public C0306a(Context context, ActionProvider actionProvider) {
            super(context, actionProvider);
        }

        /* renamed from: a */
        public View mo452a(MenuItem menuItem) {
            return this.f1058d.onCreateActionView(menuItem);
        }

        /* renamed from: a */
        public void mo454a(C0108e.C0110b bVar) {
            this.f1063f = bVar;
            this.f1058d.setVisibilityListener(bVar != null ? this : null);
        }

        /* renamed from: b */
        public boolean mo457b() {
            return this.f1058d.isVisible();
        }

        /* renamed from: e */
        public boolean mo460e() {
            return this.f1058d.overridesItemVisibility();
        }

        public void onActionProviderVisibilityChanged(boolean z) {
            C0108e.C0110b bVar = this.f1063f;
            if (bVar != null) {
                bVar.onActionProviderVisibilityChanged(z);
            }
        }
    }

    C0305r(Context context, C0057b bVar) {
        super(context, bVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public C0300q.C0301a mo1389a(ActionProvider actionProvider) {
        return new C0306a(this.f936b, actionProvider);
    }
}
