package android.support.p025v7.view.menu;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.p024v4.graphics.drawable.C0190a;
import android.support.p025v7.view.menu.C0312w;
import android.util.Log;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewDebug;
import android.widget.LinearLayout;
import p000a.p001a.p005c.p009c.p010a.C0057b;
import p000a.p001a.p005c.p014g.C0108e;
import p000a.p001a.p017d.p018a.C0143h;
import p000a.p001a.p017d.p019b.p020a.C0146a;

/* renamed from: android.support.v7.view.menu.p */
public final class C0299p implements C0057b {

    /* renamed from: A */
    private View f1026A;

    /* renamed from: B */
    private C0108e f1027B;

    /* renamed from: C */
    private MenuItem.OnActionExpandListener f1028C;

    /* renamed from: D */
    private boolean f1029D = false;

    /* renamed from: E */
    private ContextMenu.ContextMenuInfo f1030E;

    /* renamed from: a */
    private final int f1031a;

    /* renamed from: b */
    private final int f1032b;

    /* renamed from: c */
    private final int f1033c;

    /* renamed from: d */
    private final int f1034d;

    /* renamed from: e */
    private CharSequence f1035e;

    /* renamed from: f */
    private CharSequence f1036f;

    /* renamed from: g */
    private Intent f1037g;

    /* renamed from: h */
    private char f1038h;

    /* renamed from: i */
    private int f1039i = 4096;

    /* renamed from: j */
    private char f1040j;

    /* renamed from: k */
    private int f1041k = 4096;

    /* renamed from: l */
    private Drawable f1042l;

    /* renamed from: m */
    private int f1043m = 0;

    /* renamed from: n */
    C0293l f1044n;

    /* renamed from: o */
    private C0278D f1045o;

    /* renamed from: p */
    private Runnable f1046p;

    /* renamed from: q */
    private MenuItem.OnMenuItemClickListener f1047q;

    /* renamed from: r */
    private CharSequence f1048r;

    /* renamed from: s */
    private CharSequence f1049s;

    /* renamed from: t */
    private ColorStateList f1050t = null;

    /* renamed from: u */
    private PorterDuff.Mode f1051u = null;

    /* renamed from: v */
    private boolean f1052v = false;

    /* renamed from: w */
    private boolean f1053w = false;

    /* renamed from: x */
    private boolean f1054x = false;

    /* renamed from: y */
    private int f1055y = 16;

    /* renamed from: z */
    private int f1056z = 0;

    C0299p(C0293l lVar, int i, int i2, int i3, int i4, CharSequence charSequence, int i5) {
        this.f1044n = lVar;
        this.f1031a = i2;
        this.f1032b = i;
        this.f1033c = i3;
        this.f1034d = i4;
        this.f1035e = charSequence;
        this.f1056z = i5;
    }

    /* renamed from: a */
    private Drawable m1256a(Drawable drawable) {
        if (drawable != null && this.f1054x && (this.f1052v || this.f1053w)) {
            drawable = C0190a.m687g(drawable).mutate();
            if (this.f1052v) {
                C0190a.m674a(drawable, this.f1050t);
            }
            if (this.f1053w) {
                C0190a.m677a(drawable, this.f1051u);
            }
            this.f1054x = false;
        }
        return drawable;
    }

    /* renamed from: a */
    private static void m1257a(StringBuilder sb, int i, int i2, String str) {
        if ((i & i2) == i2) {
            sb.append(str);
        }
    }

    /* renamed from: a */
    public C0057b mo194a(C0108e eVar) {
        C0108e eVar2 = this.f1027B;
        if (eVar2 != null) {
            eVar2.mo461f();
        }
        this.f1026A = null;
        this.f1027B = eVar;
        this.f1044n.mo1295b(true);
        C0108e eVar3 = this.f1027B;
        if (eVar3 != null) {
            eVar3.mo454a((C0108e.C0110b) new C0298o(this));
        }
        return this;
    }

    /* renamed from: a */
    public C0108e mo195a() {
        return this.f1027B;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public CharSequence mo1334a(C0312w.C0313a aVar) {
        return (aVar == null || !aVar.mo1104c()) ? getTitle() : getTitleCondensed();
    }

    /* renamed from: a */
    public void mo1335a(C0278D d) {
        this.f1045o = d;
        d.setHeaderTitle(getTitle());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1336a(ContextMenu.ContextMenuInfo contextMenuInfo) {
        this.f1030E = contextMenuInfo;
    }

    /* renamed from: a */
    public void mo1337a(boolean z) {
        this.f1029D = z;
        this.f1044n.mo1295b(false);
    }

    /* renamed from: b */
    public void mo1338b() {
        this.f1044n.mo1298c(this);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo1339b(boolean z) {
        int i = this.f1055y;
        this.f1055y = (z ? 2 : 0) | (i & -3);
        if (i != this.f1055y) {
            this.f1044n.mo1295b(false);
        }
    }

    /* renamed from: c */
    public int mo1340c() {
        return this.f1034d;
    }

    /* renamed from: c */
    public void mo1341c(boolean z) {
        this.f1055y = (z ? 4 : 0) | (this.f1055y & -5);
    }

    public boolean collapseActionView() {
        if ((this.f1056z & 8) == 0) {
            return false;
        }
        if (this.f1026A == null) {
            return true;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.f1028C;
        if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionCollapse(this)) {
            return this.f1044n.mo1146a(this);
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public char mo1342d() {
        return this.f1044n.mo1152p() ? this.f1040j : this.f1038h;
    }

    /* renamed from: d */
    public void mo1343d(boolean z) {
        this.f1055y = z ? this.f1055y | 32 : this.f1055y & -33;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public String mo1344e() {
        int i;
        char d = mo1342d();
        if (d == 0) {
            return "";
        }
        Resources resources = this.f1044n.mo1305e().getResources();
        StringBuilder sb = new StringBuilder();
        if (ViewConfiguration.get(this.f1044n.mo1305e()).hasPermanentMenuKey()) {
            sb.append(resources.getString(C0143h.abc_prepend_shortcut_label));
        }
        int i2 = this.f1044n.mo1152p() ? this.f1041k : this.f1039i;
        m1257a(sb, i2, 65536, resources.getString(C0143h.abc_menu_meta_shortcut_label));
        m1257a(sb, i2, 4096, resources.getString(C0143h.abc_menu_ctrl_shortcut_label));
        m1257a(sb, i2, 2, resources.getString(C0143h.abc_menu_alt_shortcut_label));
        m1257a(sb, i2, 1, resources.getString(C0143h.abc_menu_shift_shortcut_label));
        m1257a(sb, i2, 4, resources.getString(C0143h.abc_menu_sym_shortcut_label));
        m1257a(sb, i2, 8, resources.getString(C0143h.abc_menu_function_shortcut_label));
        if (d == 8) {
            i = C0143h.abc_menu_delete_shortcut_label;
        } else if (d == 10) {
            i = C0143h.abc_menu_enter_shortcut_label;
        } else if (d != ' ') {
            sb.append(d);
            return sb.toString();
        } else {
            i = C0143h.abc_menu_space_shortcut_label;
        }
        sb.append(resources.getString(i));
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public boolean mo1345e(boolean z) {
        int i = this.f1055y;
        this.f1055y = (z ? 0 : 8) | (i & -9);
        return i != this.f1055y;
    }

    public boolean expandActionView() {
        if (!mo1346f()) {
            return false;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.f1028C;
        if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionExpand(this)) {
            return this.f1044n.mo1147b(this);
        }
        return false;
    }

    /* renamed from: f */
    public boolean mo1346f() {
        C0108e eVar;
        if ((this.f1056z & 8) == 0) {
            return false;
        }
        if (this.f1026A == null && (eVar = this.f1027B) != null) {
            this.f1026A = eVar.mo452a((MenuItem) this);
        }
        return this.f1026A != null;
    }

    /* renamed from: g */
    public boolean mo1347g() {
        MenuItem.OnMenuItemClickListener onMenuItemClickListener = this.f1047q;
        if (onMenuItemClickListener != null && onMenuItemClickListener.onMenuItemClick(this)) {
            return true;
        }
        C0293l lVar = this.f1044n;
        if (lVar.mo1145a(lVar, (MenuItem) this)) {
            return true;
        }
        Runnable runnable = this.f1046p;
        if (runnable != null) {
            runnable.run();
            return true;
        }
        if (this.f1037g != null) {
            try {
                this.f1044n.mo1305e().startActivity(this.f1037g);
                return true;
            } catch (ActivityNotFoundException e) {
                Log.e("MenuItemImpl", "Can't find activity to handle intent; ignoring", e);
            }
        }
        C0108e eVar = this.f1027B;
        return eVar != null && eVar.mo459d();
    }

    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }

    public View getActionView() {
        View view = this.f1026A;
        if (view != null) {
            return view;
        }
        C0108e eVar = this.f1027B;
        if (eVar == null) {
            return null;
        }
        this.f1026A = eVar.mo452a((MenuItem) this);
        return this.f1026A;
    }

    public int getAlphabeticModifiers() {
        return this.f1041k;
    }

    public char getAlphabeticShortcut() {
        return this.f1040j;
    }

    public CharSequence getContentDescription() {
        return this.f1048r;
    }

    public int getGroupId() {
        return this.f1032b;
    }

    public Drawable getIcon() {
        Drawable drawable = this.f1042l;
        if (drawable != null) {
            return m1256a(drawable);
        }
        if (this.f1043m == 0) {
            return null;
        }
        Drawable b = C0146a.m492b(this.f1044n.mo1305e(), this.f1043m);
        this.f1043m = 0;
        this.f1042l = b;
        return m1256a(b);
    }

    public ColorStateList getIconTintList() {
        return this.f1050t;
    }

    public PorterDuff.Mode getIconTintMode() {
        return this.f1051u;
    }

    public Intent getIntent() {
        return this.f1037g;
    }

    @ViewDebug.CapturedViewProperty
    public int getItemId() {
        return this.f1031a;
    }

    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return this.f1030E;
    }

    public int getNumericModifiers() {
        return this.f1039i;
    }

    public char getNumericShortcut() {
        return this.f1038h;
    }

    public int getOrder() {
        return this.f1033c;
    }

    public SubMenu getSubMenu() {
        return this.f1045o;
    }

    @ViewDebug.CapturedViewProperty
    public CharSequence getTitle() {
        return this.f1035e;
    }

    public CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f1036f;
        if (charSequence == null) {
            charSequence = this.f1035e;
        }
        return (Build.VERSION.SDK_INT >= 18 || charSequence == null || (charSequence instanceof String)) ? charSequence : charSequence.toString();
    }

    public CharSequence getTooltipText() {
        return this.f1049s;
    }

    /* renamed from: h */
    public boolean mo1360h() {
        return (this.f1055y & 32) == 32;
    }

    public boolean hasSubMenu() {
        return this.f1045o != null;
    }

    /* renamed from: i */
    public boolean mo1362i() {
        return (this.f1055y & 4) != 0;
    }

    public boolean isActionViewExpanded() {
        return this.f1029D;
    }

    public boolean isCheckable() {
        return (this.f1055y & 1) == 1;
    }

    public boolean isChecked() {
        return (this.f1055y & 2) == 2;
    }

    public boolean isEnabled() {
        return (this.f1055y & 16) != 0;
    }

    public boolean isVisible() {
        C0108e eVar = this.f1027B;
        return (eVar == null || !eVar.mo460e()) ? (this.f1055y & 8) == 0 : (this.f1055y & 8) == 0 && this.f1027B.mo457b();
    }

    /* renamed from: j */
    public boolean mo1367j() {
        return (this.f1056z & 1) == 1;
    }

    /* renamed from: k */
    public boolean mo1368k() {
        return (this.f1056z & 2) == 2;
    }

    /* renamed from: l */
    public boolean mo1369l() {
        return this.f1044n.mo1316k();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: m */
    public boolean mo1370m() {
        return this.f1044n.mo1153q() && mo1342d() != 0;
    }

    /* renamed from: n */
    public boolean mo1371n() {
        return (this.f1056z & 4) == 4;
    }

    public MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }

    public C0057b setActionView(int i) {
        Context e = this.f1044n.mo1305e();
        setActionView(LayoutInflater.from(e).inflate(i, new LinearLayout(e), false));
        return this;
    }

    public C0057b setActionView(View view) {
        int i;
        this.f1026A = view;
        this.f1027B = null;
        if (view != null && view.getId() == -1 && (i = this.f1031a) > 0) {
            view.setId(i);
        }
        this.f1044n.mo1298c(this);
        return this;
    }

    public MenuItem setAlphabeticShortcut(char c) {
        if (this.f1040j == c) {
            return this;
        }
        this.f1040j = Character.toLowerCase(c);
        this.f1044n.mo1295b(false);
        return this;
    }

    public MenuItem setAlphabeticShortcut(char c, int i) {
        if (this.f1040j == c && this.f1041k == i) {
            return this;
        }
        this.f1040j = Character.toLowerCase(c);
        this.f1041k = KeyEvent.normalizeMetaState(i);
        this.f1044n.mo1295b(false);
        return this;
    }

    public MenuItem setCheckable(boolean z) {
        int i = this.f1055y;
        this.f1055y = z | (i & true) ? 1 : 0;
        if (i != this.f1055y) {
            this.f1044n.mo1295b(false);
        }
        return this;
    }

    public MenuItem setChecked(boolean z) {
        if ((this.f1055y & 4) != 0) {
            this.f1044n.mo1277a((MenuItem) this);
        } else {
            mo1339b(z);
        }
        return this;
    }

    public C0057b setContentDescription(CharSequence charSequence) {
        this.f1048r = charSequence;
        this.f1044n.mo1295b(false);
        return this;
    }

    public MenuItem setEnabled(boolean z) {
        this.f1055y = z ? this.f1055y | 16 : this.f1055y & -17;
        this.f1044n.mo1295b(false);
        return this;
    }

    public MenuItem setIcon(int i) {
        this.f1042l = null;
        this.f1043m = i;
        this.f1054x = true;
        this.f1044n.mo1295b(false);
        return this;
    }

    public MenuItem setIcon(Drawable drawable) {
        this.f1043m = 0;
        this.f1042l = drawable;
        this.f1054x = true;
        this.f1044n.mo1295b(false);
        return this;
    }

    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.f1050t = colorStateList;
        this.f1052v = true;
        this.f1054x = true;
        this.f1044n.mo1295b(false);
        return this;
    }

    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.f1051u = mode;
        this.f1053w = true;
        this.f1054x = true;
        this.f1044n.mo1295b(false);
        return this;
    }

    public MenuItem setIntent(Intent intent) {
        this.f1037g = intent;
        return this;
    }

    public MenuItem setNumericShortcut(char c) {
        if (this.f1038h == c) {
            return this;
        }
        this.f1038h = c;
        this.f1044n.mo1295b(false);
        return this;
    }

    public MenuItem setNumericShortcut(char c, int i) {
        if (this.f1038h == c && this.f1039i == i) {
            return this;
        }
        this.f1038h = c;
        this.f1039i = KeyEvent.normalizeMetaState(i);
        this.f1044n.mo1295b(false);
        return this;
    }

    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        this.f1028C = onActionExpandListener;
        return this;
    }

    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.f1047q = onMenuItemClickListener;
        return this;
    }

    public MenuItem setShortcut(char c, char c2) {
        this.f1038h = c;
        this.f1040j = Character.toLowerCase(c2);
        this.f1044n.mo1295b(false);
        return this;
    }

    public MenuItem setShortcut(char c, char c2, int i, int i2) {
        this.f1038h = c;
        this.f1039i = KeyEvent.normalizeMetaState(i);
        this.f1040j = Character.toLowerCase(c2);
        this.f1041k = KeyEvent.normalizeMetaState(i2);
        this.f1044n.mo1295b(false);
        return this;
    }

    public void setShowAsAction(int i) {
        int i2 = i & 3;
        if (i2 == 0 || i2 == 1 || i2 == 2) {
            this.f1056z = i;
            this.f1044n.mo1298c(this);
            return;
        }
        throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
    }

    public C0057b setShowAsActionFlags(int i) {
        setShowAsAction(i);
        return this;
    }

    public MenuItem setTitle(int i) {
        setTitle((CharSequence) this.f1044n.mo1305e().getString(i));
        return this;
    }

    public MenuItem setTitle(CharSequence charSequence) {
        this.f1035e = charSequence;
        this.f1044n.mo1295b(false);
        C0278D d = this.f1045o;
        if (d != null) {
            d.setHeaderTitle(charSequence);
        }
        return this;
    }

    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f1036f = charSequence;
        if (charSequence == null) {
            CharSequence charSequence2 = this.f1035e;
        }
        this.f1044n.mo1295b(false);
        return this;
    }

    public C0057b setTooltipText(CharSequence charSequence) {
        this.f1049s = charSequence;
        this.f1044n.mo1295b(false);
        return this;
    }

    public MenuItem setVisible(boolean z) {
        if (mo1345e(z)) {
            this.f1044n.mo1304d(this);
        }
        return this;
    }

    public String toString() {
        CharSequence charSequence = this.f1035e;
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }
}
