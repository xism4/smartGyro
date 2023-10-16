package android.support.p025v7.view.menu;

import android.support.p025v7.view.menu.C0312w;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

/* renamed from: android.support.v7.view.menu.k */
public class C0292k extends BaseAdapter {

    /* renamed from: a */
    C0293l f988a;

    /* renamed from: b */
    private int f989b = -1;

    /* renamed from: c */
    private boolean f990c;

    /* renamed from: d */
    private final boolean f991d;

    /* renamed from: e */
    private final LayoutInflater f992e;

    /* renamed from: f */
    private final int f993f;

    public C0292k(C0293l lVar, LayoutInflater layoutInflater, boolean z, int i) {
        this.f991d = z;
        this.f992e = layoutInflater;
        this.f988a = lVar;
        this.f993f = i;
        mo1258a();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1258a() {
        C0299p f = this.f988a.mo1307f();
        if (f != null) {
            ArrayList<C0299p> j = this.f988a.mo1315j();
            int size = j.size();
            for (int i = 0; i < size; i++) {
                if (j.get(i) == f) {
                    this.f989b = i;
                    return;
                }
            }
        }
        this.f989b = -1;
    }

    /* renamed from: a */
    public void mo1259a(boolean z) {
        this.f990c = z;
    }

    /* renamed from: b */
    public C0293l mo1260b() {
        return this.f988a;
    }

    public int getCount() {
        ArrayList<C0299p> j = this.f991d ? this.f988a.mo1315j() : this.f988a.mo1318n();
        return this.f989b < 0 ? j.size() : j.size() - 1;
    }

    public C0299p getItem(int i) {
        ArrayList<C0299p> j = this.f991d ? this.f988a.mo1315j() : this.f988a.mo1318n();
        int i2 = this.f989b;
        if (i2 >= 0 && i >= i2) {
            i++;
        }
        return j.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.f992e.inflate(this.f993f, viewGroup, false);
        }
        int groupId = getItem(i).getGroupId();
        int i2 = i - 1;
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        listMenuItemView.setGroupDividerEnabled(this.f988a.mo1151o() && groupId != (i2 >= 0 ? getItem(i2).getGroupId() : groupId));
        C0312w.C0313a aVar = (C0312w.C0313a) view;
        if (this.f990c) {
            listMenuItemView.setForceShowIcon(true);
        }
        aVar.mo1101a(getItem(i), 0);
        return view;
    }

    public void notifyDataSetChanged() {
        mo1258a();
        super.notifyDataSetChanged();
    }
}
