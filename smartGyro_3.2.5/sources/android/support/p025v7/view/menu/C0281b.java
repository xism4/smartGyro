package android.support.p025v7.view.menu;

import android.content.Context;
import android.support.p025v7.view.menu.C0310v;
import android.support.p025v7.view.menu.C0312w;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/* renamed from: android.support.v7.view.menu.b */
public abstract class C0281b implements C0310v {

    /* renamed from: a */
    protected Context f926a;

    /* renamed from: b */
    protected Context f927b;

    /* renamed from: c */
    protected C0293l f928c;

    /* renamed from: d */
    protected LayoutInflater f929d;

    /* renamed from: e */
    protected LayoutInflater f930e;

    /* renamed from: f */
    private C0310v.C0311a f931f;

    /* renamed from: g */
    private int f932g;

    /* renamed from: h */
    private int f933h;

    /* renamed from: i */
    protected C0312w f934i;

    /* renamed from: j */
    private int f935j;

    public C0281b(Context context, int i, int i2) {
        this.f926a = context;
        this.f929d = LayoutInflater.from(context);
        this.f932g = i;
        this.f933h = i2;
    }

    /* renamed from: a */
    public C0312w.C0313a mo1222a(ViewGroup viewGroup) {
        return (C0312w.C0313a) this.f929d.inflate(this.f933h, viewGroup, false);
    }

    /* renamed from: a */
    public View mo1223a(C0299p pVar, View view, ViewGroup viewGroup) {
        C0312w.C0313a a = view instanceof C0312w.C0313a ? (C0312w.C0313a) view : mo1222a(viewGroup);
        mo1226a(pVar, a);
        return (View) a;
    }

    /* renamed from: a */
    public void mo1224a(int i) {
        this.f935j = i;
    }

    /* renamed from: a */
    public void mo1225a(Context context, C0293l lVar) {
        this.f927b = context;
        this.f930e = LayoutInflater.from(this.f927b);
        this.f928c = lVar;
    }

    /* renamed from: a */
    public void mo1127a(C0293l lVar, boolean z) {
        C0310v.C0311a aVar = this.f931f;
        if (aVar != null) {
            aVar.mo1072a(lVar, z);
        }
    }

    /* renamed from: a */
    public abstract void mo1226a(C0299p pVar, C0312w.C0313a aVar);

    /* renamed from: a */
    public void mo1128a(C0310v.C0311a aVar) {
        this.f931f = aVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo1227a(View view, int i) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        ((ViewGroup) this.f934i).addView(view, i);
    }

    /* renamed from: a */
    public void mo1131a(boolean z) {
        ViewGroup viewGroup = (ViewGroup) this.f934i;
        if (viewGroup != null) {
            C0293l lVar = this.f928c;
            int i = 0;
            if (lVar != null) {
                lVar.mo1292b();
                ArrayList<C0299p> n = this.f928c.mo1318n();
                int size = n.size();
                int i2 = 0;
                for (int i3 = 0; i3 < size; i3++) {
                    C0299p pVar = n.get(i3);
                    if (mo1228a(i2, pVar)) {
                        View childAt = viewGroup.getChildAt(i2);
                        C0299p itemData = childAt instanceof C0312w.C0313a ? ((C0312w.C0313a) childAt).getItemData() : null;
                        View a = mo1223a(pVar, childAt, viewGroup);
                        if (pVar != itemData) {
                            a.setPressed(false);
                            a.jumpDrawablesToCurrentState();
                        }
                        if (a != childAt) {
                            mo1227a(a, i2);
                        }
                        i2++;
                    }
                }
                i = i2;
            }
            while (i < viewGroup.getChildCount()) {
                if (!mo1230a(viewGroup, i)) {
                    i++;
                }
            }
        }
    }

    /* renamed from: a */
    public abstract boolean mo1228a(int i, C0299p pVar);

    /* renamed from: a */
    public boolean mo1133a(C0278D d) {
        C0310v.C0311a aVar = this.f931f;
        if (aVar != null) {
            return aVar.mo1073a(d);
        }
        return false;
    }

    /* renamed from: a */
    public boolean mo1229a(C0293l lVar, C0299p pVar) {
        return false;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo1230a(ViewGroup viewGroup, int i) {
        viewGroup.removeViewAt(i);
        return true;
    }

    /* renamed from: b */
    public C0310v.C0311a mo1231b() {
        return this.f931f;
    }

    /* renamed from: b */
    public C0312w mo1232b(ViewGroup viewGroup) {
        if (this.f934i == null) {
            this.f934i = (C0312w) this.f929d.inflate(this.f932g, viewGroup, false);
            this.f934i.mo1174a(this.f928c);
            mo1131a(true);
        }
        return this.f934i;
    }

    /* renamed from: b */
    public boolean mo1233b(C0293l lVar, C0299p pVar) {
        return false;
    }
}
