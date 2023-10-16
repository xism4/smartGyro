package p000a.p001a.p005c.p014g;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* renamed from: a.a.c.g.e */
public abstract class C0108e {

    /* renamed from: a */
    private final Context f261a;

    /* renamed from: b */
    private C0109a f262b;

    /* renamed from: c */
    private C0110b f263c;

    /* renamed from: a.a.c.g.e$a */
    public interface C0109a {
    }

    /* renamed from: a.a.c.g.e$b */
    public interface C0110b {
        void onActionProviderVisibilityChanged(boolean z);
    }

    public C0108e(Context context) {
        this.f261a = context;
    }

    /* renamed from: a */
    public View mo452a(MenuItem menuItem) {
        return mo458c();
    }

    /* renamed from: a */
    public void mo453a(C0109a aVar) {
        this.f262b = aVar;
    }

    /* renamed from: a */
    public void mo454a(C0110b bVar) {
        if (!(this.f263c == null || bVar == null)) {
            Log.w("ActionProvider(support)", "setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this " + getClass().getSimpleName() + " instance while it is still in use somewhere else?");
        }
        this.f263c = bVar;
    }

    /* renamed from: a */
    public void mo455a(SubMenu subMenu) {
    }

    /* renamed from: a */
    public boolean mo456a() {
        return false;
    }

    /* renamed from: b */
    public boolean mo457b() {
        return true;
    }

    /* renamed from: c */
    public abstract View mo458c();

    /* renamed from: d */
    public boolean mo459d() {
        return false;
    }

    /* renamed from: e */
    public boolean mo460e() {
        return false;
    }

    /* renamed from: f */
    public void mo461f() {
        this.f263c = null;
        this.f262b = null;
    }
}
