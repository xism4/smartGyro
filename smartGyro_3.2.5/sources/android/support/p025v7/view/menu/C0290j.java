package android.support.p025v7.view.menu;

import android.content.Context;
import android.os.IBinder;
import android.support.p025v7.view.menu.C0310v;
import android.support.p025v7.view.menu.C0312w;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import java.util.ArrayList;
import p000a.p001a.p017d.p018a.C0142g;

/* renamed from: android.support.v7.view.menu.j */
public class C0290j implements C0310v, AdapterView.OnItemClickListener {

    /* renamed from: a */
    Context f977a;

    /* renamed from: b */
    LayoutInflater f978b;

    /* renamed from: c */
    C0293l f979c;

    /* renamed from: d */
    ExpandedMenuView f980d;

    /* renamed from: e */
    int f981e;

    /* renamed from: f */
    int f982f;

    /* renamed from: g */
    int f983g;

    /* renamed from: h */
    private C0310v.C0311a f984h;

    /* renamed from: i */
    C0291a f985i;

    /* renamed from: android.support.v7.view.menu.j$a */
    private class C0291a extends BaseAdapter {

        /* renamed from: a */
        private int f986a = -1;

        public C0291a() {
            mo1252a();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo1252a() {
            C0299p f = C0290j.this.f979c.mo1307f();
            if (f != null) {
                ArrayList<C0299p> j = C0290j.this.f979c.mo1315j();
                int size = j.size();
                for (int i = 0; i < size; i++) {
                    if (j.get(i) == f) {
                        this.f986a = i;
                        return;
                    }
                }
            }
            this.f986a = -1;
        }

        public int getCount() {
            int size = C0290j.this.f979c.mo1315j().size() - C0290j.this.f981e;
            return this.f986a < 0 ? size : size - 1;
        }

        public C0299p getItem(int i) {
            ArrayList<C0299p> j = C0290j.this.f979c.mo1315j();
            int i2 = i + C0290j.this.f981e;
            int i3 = this.f986a;
            if (i3 >= 0 && i2 >= i3) {
                i2++;
            }
            return j.get(i2);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                C0290j jVar = C0290j.this;
                view = jVar.f978b.inflate(jVar.f983g, viewGroup, false);
            }
            ((C0312w.C0313a) view).mo1101a(getItem(i), 0);
            return view;
        }

        public void notifyDataSetChanged() {
            mo1252a();
            super.notifyDataSetChanged();
        }
    }

    public C0290j(int i, int i2) {
        this.f983g = i;
        this.f982f = i2;
    }

    public C0290j(Context context, int i) {
        this(i, 0);
        this.f977a = context;
        this.f978b = LayoutInflater.from(this.f977a);
    }

    /* renamed from: a */
    public C0312w mo1249a(ViewGroup viewGroup) {
        if (this.f980d == null) {
            this.f980d = (ExpandedMenuView) this.f978b.inflate(C0142g.abc_expanded_menu_layout, viewGroup, false);
            if (this.f985i == null) {
                this.f985i = new C0291a();
            }
            this.f980d.setAdapter(this.f985i);
            this.f980d.setOnItemClickListener(this);
        }
        return this.f980d;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001c, code lost:
        if (r2.f978b == null) goto L_0x000b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARNING: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo1225a(android.content.Context r3, android.support.p025v7.view.menu.C0293l r4) {
        /*
            r2 = this;
            int r0 = r2.f982f
            if (r0 == 0) goto L_0x0014
            android.view.ContextThemeWrapper r1 = new android.view.ContextThemeWrapper
            r1.<init>(r3, r0)
            r2.f977a = r1
        L_0x000b:
            android.content.Context r3 = r2.f977a
            android.view.LayoutInflater r3 = android.view.LayoutInflater.from(r3)
            r2.f978b = r3
            goto L_0x001f
        L_0x0014:
            android.content.Context r0 = r2.f977a
            if (r0 == 0) goto L_0x001f
            r2.f977a = r3
            android.view.LayoutInflater r3 = r2.f978b
            if (r3 != 0) goto L_0x001f
            goto L_0x000b
        L_0x001f:
            r2.f979c = r4
            android.support.v7.view.menu.j$a r3 = r2.f985i
            if (r3 == 0) goto L_0x0028
            r3.notifyDataSetChanged()
        L_0x0028:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p025v7.view.menu.C0290j.mo1225a(android.content.Context, android.support.v7.view.menu.l):void");
    }

    /* renamed from: a */
    public void mo1127a(C0293l lVar, boolean z) {
        C0310v.C0311a aVar = this.f984h;
        if (aVar != null) {
            aVar.mo1072a(lVar, z);
        }
    }

    /* renamed from: a */
    public void mo1128a(C0310v.C0311a aVar) {
        this.f984h = aVar;
    }

    /* renamed from: a */
    public void mo1131a(boolean z) {
        C0291a aVar = this.f985i;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    /* renamed from: a */
    public boolean mo1132a() {
        return false;
    }

    /* renamed from: a */
    public boolean mo1133a(C0278D d) {
        if (!d.hasVisibleItems()) {
            return false;
        }
        new C0296m(d).mo1330a((IBinder) null);
        C0310v.C0311a aVar = this.f984h;
        if (aVar == null) {
            return true;
        }
        aVar.mo1073a(d);
        return true;
    }

    /* renamed from: a */
    public boolean mo1229a(C0293l lVar, C0299p pVar) {
        return false;
    }

    /* renamed from: b */
    public ListAdapter mo1250b() {
        if (this.f985i == null) {
            this.f985i = new C0291a();
        }
        return this.f985i;
    }

    /* renamed from: b */
    public boolean mo1233b(C0293l lVar, C0299p pVar) {
        return false;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.f979c.mo1281a((MenuItem) this.f985i.getItem(i), (C0310v) this, 0);
    }
}
