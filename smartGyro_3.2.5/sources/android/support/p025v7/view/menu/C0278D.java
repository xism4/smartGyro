package android.support.p025v7.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.p025v7.view.menu.C0293l;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* renamed from: android.support.v7.view.menu.D */
public class C0278D extends C0293l implements SubMenu {

    /* renamed from: B */
    private C0293l f881B;

    /* renamed from: C */
    private C0299p f882C;

    public C0278D(Context context, C0293l lVar, C0299p pVar) {
        super(context);
        this.f881B = lVar;
        this.f882C = pVar;
    }

    /* renamed from: a */
    public void mo1144a(C0293l.C0294a aVar) {
        this.f881B.mo1144a(aVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo1145a(C0293l lVar, MenuItem menuItem) {
        return super.mo1145a(lVar, menuItem) || this.f881B.mo1145a(lVar, menuItem);
    }

    /* renamed from: a */
    public boolean mo1146a(C0299p pVar) {
        return this.f881B.mo1146a(pVar);
    }

    /* renamed from: b */
    public boolean mo1147b(C0299p pVar) {
        return this.f881B.mo1147b(pVar);
    }

    /* renamed from: d */
    public String mo1148d() {
        C0299p pVar = this.f882C;
        int itemId = pVar != null ? pVar.getItemId() : 0;
        if (itemId == 0) {
            return null;
        }
        return super.mo1148d() + ":" + itemId;
    }

    public MenuItem getItem() {
        return this.f882C;
    }

    /* renamed from: m */
    public C0293l mo1150m() {
        return this.f881B.mo1150m();
    }

    /* renamed from: o */
    public boolean mo1151o() {
        return this.f881B.mo1151o();
    }

    /* renamed from: p */
    public boolean mo1152p() {
        return this.f881B.mo1152p();
    }

    /* renamed from: q */
    public boolean mo1153q() {
        return this.f881B.mo1153q();
    }

    public void setGroupDividerEnabled(boolean z) {
        this.f881B.setGroupDividerEnabled(z);
    }

    public SubMenu setHeaderIcon(int i) {
        super.mo1303d(i);
        return this;
    }

    public SubMenu setHeaderIcon(Drawable drawable) {
        super.mo1268a(drawable);
        return this;
    }

    public SubMenu setHeaderTitle(int i) {
        super.mo1306e(i);
        return this;
    }

    public SubMenu setHeaderTitle(CharSequence charSequence) {
        super.mo1270a(charSequence);
        return this;
    }

    public SubMenu setHeaderView(View view) {
        super.mo1269a(view);
        return this;
    }

    public SubMenu setIcon(int i) {
        this.f882C.setIcon(i);
        return this;
    }

    public SubMenu setIcon(Drawable drawable) {
        this.f882C.setIcon(drawable);
        return this;
    }

    public void setQwertyMode(boolean z) {
        this.f881B.setQwertyMode(z);
    }

    /* renamed from: t */
    public Menu mo1163t() {
        return this.f881B;
    }
}
