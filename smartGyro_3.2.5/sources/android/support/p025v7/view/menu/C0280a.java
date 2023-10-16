package android.support.p025v7.view.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.p024v4.graphics.drawable.C0190a;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import p000a.p001a.p005c.p006a.C0025a;
import p000a.p001a.p005c.p009c.p010a.C0057b;
import p000a.p001a.p005c.p014g.C0108e;

/* renamed from: android.support.v7.view.menu.a */
public class C0280a implements C0057b {

    /* renamed from: a */
    private final int f904a;

    /* renamed from: b */
    private final int f905b;

    /* renamed from: c */
    private final int f906c;

    /* renamed from: d */
    private final int f907d;

    /* renamed from: e */
    private CharSequence f908e;

    /* renamed from: f */
    private CharSequence f909f;

    /* renamed from: g */
    private Intent f910g;

    /* renamed from: h */
    private char f911h;

    /* renamed from: i */
    private int f912i = 4096;

    /* renamed from: j */
    private char f913j;

    /* renamed from: k */
    private int f914k = 4096;

    /* renamed from: l */
    private Drawable f915l;

    /* renamed from: m */
    private int f916m = 0;

    /* renamed from: n */
    private Context f917n;

    /* renamed from: o */
    private MenuItem.OnMenuItemClickListener f918o;

    /* renamed from: p */
    private CharSequence f919p;

    /* renamed from: q */
    private CharSequence f920q;

    /* renamed from: r */
    private ColorStateList f921r = null;

    /* renamed from: s */
    private PorterDuff.Mode f922s = null;

    /* renamed from: t */
    private boolean f923t = false;

    /* renamed from: u */
    private boolean f924u = false;

    /* renamed from: v */
    private int f925v = 16;

    public C0280a(Context context, int i, int i2, int i3, int i4, CharSequence charSequence) {
        this.f917n = context;
        this.f904a = i2;
        this.f905b = i;
        this.f906c = i3;
        this.f907d = i4;
        this.f908e = charSequence;
    }

    /* renamed from: b */
    private void m1128b() {
        if (this.f915l == null) {
            return;
        }
        if (this.f923t || this.f924u) {
            this.f915l = C0190a.m687g(this.f915l);
            this.f915l = this.f915l.mutate();
            if (this.f923t) {
                C0190a.m674a(this.f915l, this.f921r);
            }
            if (this.f924u) {
                C0190a.m677a(this.f915l, this.f922s);
            }
        }
    }

    /* renamed from: a */
    public C0057b mo194a(C0108e eVar) {
        throw new UnsupportedOperationException();
    }

    /* renamed from: a */
    public C0108e mo195a() {
        return null;
    }

    public boolean collapseActionView() {
        return false;
    }

    public boolean expandActionView() {
        return false;
    }

    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }

    public View getActionView() {
        return null;
    }

    public int getAlphabeticModifiers() {
        return this.f914k;
    }

    public char getAlphabeticShortcut() {
        return this.f913j;
    }

    public CharSequence getContentDescription() {
        return this.f919p;
    }

    public int getGroupId() {
        return this.f905b;
    }

    public Drawable getIcon() {
        return this.f915l;
    }

    public ColorStateList getIconTintList() {
        return this.f921r;
    }

    public PorterDuff.Mode getIconTintMode() {
        return this.f922s;
    }

    public Intent getIntent() {
        return this.f910g;
    }

    public int getItemId() {
        return this.f904a;
    }

    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    public int getNumericModifiers() {
        return this.f912i;
    }

    public char getNumericShortcut() {
        return this.f911h;
    }

    public int getOrder() {
        return this.f907d;
    }

    public SubMenu getSubMenu() {
        return null;
    }

    public CharSequence getTitle() {
        return this.f908e;
    }

    public CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f909f;
        return charSequence != null ? charSequence : this.f908e;
    }

    public CharSequence getTooltipText() {
        return this.f920q;
    }

    public boolean hasSubMenu() {
        return false;
    }

    public boolean isActionViewExpanded() {
        return false;
    }

    public boolean isCheckable() {
        return (this.f925v & 1) != 0;
    }

    public boolean isChecked() {
        return (this.f925v & 2) != 0;
    }

    public boolean isEnabled() {
        return (this.f925v & 16) != 0;
    }

    public boolean isVisible() {
        return (this.f925v & 8) == 0;
    }

    public MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    public C0057b setActionView(int i) {
        throw new UnsupportedOperationException();
    }

    public C0057b setActionView(View view) {
        throw new UnsupportedOperationException();
    }

    public MenuItem setAlphabeticShortcut(char c) {
        this.f913j = Character.toLowerCase(c);
        return this;
    }

    public MenuItem setAlphabeticShortcut(char c, int i) {
        this.f913j = Character.toLowerCase(c);
        this.f914k = KeyEvent.normalizeMetaState(i);
        return this;
    }

    public MenuItem setCheckable(boolean z) {
        this.f925v = z | (this.f925v & true) ? 1 : 0;
        return this;
    }

    public MenuItem setChecked(boolean z) {
        this.f925v = (z ? 2 : 0) | (this.f925v & -3);
        return this;
    }

    public C0057b setContentDescription(CharSequence charSequence) {
        this.f919p = charSequence;
        return this;
    }

    public MenuItem setEnabled(boolean z) {
        this.f925v = (z ? 16 : 0) | (this.f925v & -17);
        return this;
    }

    public MenuItem setIcon(int i) {
        this.f916m = i;
        this.f915l = C0025a.m77b(this.f917n, i);
        m1128b();
        return this;
    }

    public MenuItem setIcon(Drawable drawable) {
        this.f915l = drawable;
        this.f916m = 0;
        m1128b();
        return this;
    }

    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.f921r = colorStateList;
        this.f923t = true;
        m1128b();
        return this;
    }

    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.f922s = mode;
        this.f924u = true;
        m1128b();
        return this;
    }

    public MenuItem setIntent(Intent intent) {
        this.f910g = intent;
        return this;
    }

    public MenuItem setNumericShortcut(char c) {
        this.f911h = c;
        return this;
    }

    public MenuItem setNumericShortcut(char c, int i) {
        this.f911h = c;
        this.f912i = KeyEvent.normalizeMetaState(i);
        return this;
    }

    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException();
    }

    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.f918o = onMenuItemClickListener;
        return this;
    }

    public MenuItem setShortcut(char c, char c2) {
        this.f911h = c;
        this.f913j = Character.toLowerCase(c2);
        return this;
    }

    public MenuItem setShortcut(char c, char c2, int i, int i2) {
        this.f911h = c;
        this.f912i = KeyEvent.normalizeMetaState(i);
        this.f913j = Character.toLowerCase(c2);
        this.f914k = KeyEvent.normalizeMetaState(i2);
        return this;
    }

    public void setShowAsAction(int i) {
    }

    public C0057b setShowAsActionFlags(int i) {
        setShowAsAction(i);
        return this;
    }

    public MenuItem setTitle(int i) {
        this.f908e = this.f917n.getResources().getString(i);
        return this;
    }

    public MenuItem setTitle(CharSequence charSequence) {
        this.f908e = charSequence;
        return this;
    }

    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f909f = charSequence;
        return this;
    }

    public C0057b setTooltipText(CharSequence charSequence) {
        this.f920q = charSequence;
        return this;
    }

    public MenuItem setVisible(boolean z) {
        int i = 8;
        int i2 = this.f925v & 8;
        if (z) {
            i = 0;
        }
        this.f925v = i2 | i;
        return this;
    }
}
