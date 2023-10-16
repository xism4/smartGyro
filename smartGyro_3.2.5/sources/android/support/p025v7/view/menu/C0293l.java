package android.support.p025v7.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import p000a.p001a.p005c.p006a.C0025a;
import p000a.p001a.p005c.p009c.p010a.C0056a;
import p000a.p001a.p005c.p014g.C0130v;

/* renamed from: android.support.v7.view.menu.l */
public class C0293l implements C0056a {

    /* renamed from: a */
    private static final int[] f994a = {1, 4, 5, 3, 2, 0};

    /* renamed from: A */
    private boolean f995A;

    /* renamed from: b */
    private final Context f996b;

    /* renamed from: c */
    private final Resources f997c;

    /* renamed from: d */
    private boolean f998d;

    /* renamed from: e */
    private boolean f999e;

    /* renamed from: f */
    private C0294a f1000f;

    /* renamed from: g */
    private ArrayList<C0299p> f1001g;

    /* renamed from: h */
    private ArrayList<C0299p> f1002h;

    /* renamed from: i */
    private boolean f1003i;

    /* renamed from: j */
    private ArrayList<C0299p> f1004j;

    /* renamed from: k */
    private ArrayList<C0299p> f1005k;

    /* renamed from: l */
    private boolean f1006l;

    /* renamed from: m */
    private int f1007m = 0;

    /* renamed from: n */
    private ContextMenu.ContextMenuInfo f1008n;

    /* renamed from: o */
    CharSequence f1009o;

    /* renamed from: p */
    Drawable f1010p;

    /* renamed from: q */
    View f1011q;

    /* renamed from: r */
    private boolean f1012r = false;

    /* renamed from: s */
    private boolean f1013s = false;

    /* renamed from: t */
    private boolean f1014t = false;

    /* renamed from: u */
    private boolean f1015u = false;

    /* renamed from: v */
    private boolean f1016v = false;

    /* renamed from: w */
    private ArrayList<C0299p> f1017w = new ArrayList<>();

    /* renamed from: x */
    private CopyOnWriteArrayList<WeakReference<C0310v>> f1018x = new CopyOnWriteArrayList<>();

    /* renamed from: y */
    private C0299p f1019y;

    /* renamed from: z */
    private boolean f1020z = false;

    /* renamed from: android.support.v7.view.menu.l$a */
    public interface C0294a {
        /* renamed from: a */
        void mo677a(C0293l lVar);

        /* renamed from: a */
        boolean mo678a(C0293l lVar, MenuItem menuItem);
    }

    /* renamed from: android.support.v7.view.menu.l$b */
    public interface C0295b {
        /* renamed from: a */
        boolean mo1175a(C0299p pVar);
    }

    public C0293l(Context context) {
        this.f996b = context;
        this.f997c = context.getResources();
        this.f1001g = new ArrayList<>();
        this.f1002h = new ArrayList<>();
        this.f1003i = true;
        this.f1004j = new ArrayList<>();
        this.f1005k = new ArrayList<>();
        this.f1006l = true;
        m1199e(true);
    }

    /* renamed from: a */
    private static int m1193a(ArrayList<C0299p> arrayList, int i) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (arrayList.get(size).mo1340c() <= i) {
                return size + 1;
            }
        }
        return 0;
    }

    /* renamed from: a */
    private C0299p m1194a(int i, int i2, int i3, int i4, CharSequence charSequence, int i5) {
        return new C0299p(this, i, i2, i3, i4, charSequence, i5);
    }

    /* renamed from: a */
    private void m1195a(int i, CharSequence charSequence, int i2, Drawable drawable, View view) {
        Resources l = mo1317l();
        if (view != null) {
            this.f1011q = view;
            this.f1009o = null;
            this.f1010p = null;
        } else {
            if (i > 0) {
                this.f1009o = l.getText(i);
            } else if (charSequence != null) {
                this.f1009o = charSequence;
            }
            if (i2 > 0) {
                this.f1010p = C0025a.m77b(mo1305e(), i2);
            } else if (drawable != null) {
                this.f1010p = drawable;
            }
            this.f1011q = null;
        }
        mo1295b(false);
    }

    /* renamed from: a */
    private void m1196a(int i, boolean z) {
        if (i >= 0 && i < this.f1001g.size()) {
            this.f1001g.remove(i);
            if (z) {
                mo1295b(true);
            }
        }
    }

    /* renamed from: a */
    private boolean m1197a(C0278D d, C0310v vVar) {
        boolean z = false;
        if (this.f1018x.isEmpty()) {
            return false;
        }
        if (vVar != null) {
            z = vVar.mo1133a(d);
        }
        Iterator<WeakReference<C0310v>> it = this.f1018x.iterator();
        while (it.hasNext()) {
            WeakReference next = it.next();
            C0310v vVar2 = (C0310v) next.get();
            if (vVar2 == null) {
                this.f1018x.remove(next);
            } else if (!z) {
                z = vVar2.mo1133a(d);
            }
        }
        return z;
    }

    /* renamed from: d */
    private void m1198d(boolean z) {
        if (!this.f1018x.isEmpty()) {
            mo1324s();
            Iterator<WeakReference<C0310v>> it = this.f1018x.iterator();
            while (it.hasNext()) {
                WeakReference next = it.next();
                C0310v vVar = (C0310v) next.get();
                if (vVar == null) {
                    this.f1018x.remove(next);
                } else {
                    vVar.mo1131a(z);
                }
            }
            mo1321r();
        }
    }

    /* renamed from: e */
    private void m1199e(boolean z) {
        boolean z2 = true;
        if (!z || this.f997c.getConfiguration().keyboard == 1 || !C0130v.m467a(ViewConfiguration.get(this.f996b), this.f996b)) {
            z2 = false;
        }
        this.f999e = z2;
    }

    /* renamed from: f */
    private static int m1200f(int i) {
        int i2 = (-65536 & i) >> 16;
        if (i2 >= 0) {
            int[] iArr = f994a;
            if (i2 < iArr.length) {
                return (i & 65535) | (iArr[i2] << 16);
            }
        }
        throw new IllegalArgumentException("order does not contain a valid category.");
    }

    /* renamed from: a */
    public int mo1266a(int i) {
        return mo1267a(i, 0);
    }

    /* renamed from: a */
    public int mo1267a(int i, int i2) {
        int size = size();
        if (i2 < 0) {
            i2 = 0;
        }
        while (i2 < size) {
            if (this.f1001g.get(i2).getGroupId() == i) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C0293l mo1268a(Drawable drawable) {
        m1195a(0, (CharSequence) null, 0, drawable, (View) null);
        return this;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C0293l mo1269a(View view) {
        m1195a(0, (CharSequence) null, 0, (Drawable) null, view);
        return this;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C0293l mo1270a(CharSequence charSequence) {
        m1195a(0, charSequence, 0, (Drawable) null, (View) null);
        return this;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public C0299p mo1271a(int i, KeyEvent keyEvent) {
        ArrayList<C0299p> arrayList = this.f1017w;
        arrayList.clear();
        mo1278a((List<C0299p>) arrayList, i, keyEvent);
        if (arrayList.isEmpty()) {
            return null;
        }
        int metaState = keyEvent.getMetaState();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        keyEvent.getKeyData(keyData);
        int size = arrayList.size();
        if (size == 1) {
            return arrayList.get(0);
        }
        boolean p = mo1152p();
        for (int i2 = 0; i2 < size; i2++) {
            C0299p pVar = arrayList.get(i2);
            char alphabeticShortcut = p ? pVar.getAlphabeticShortcut() : pVar.getNumericShortcut();
            if ((alphabeticShortcut == keyData.meta[0] && (metaState & 2) == 0) || ((alphabeticShortcut == keyData.meta[2] && (metaState & 2) != 0) || (p && alphabeticShortcut == 8 && i == 67))) {
                return pVar;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public MenuItem mo1272a(int i, int i2, int i3, CharSequence charSequence) {
        int f = m1200f(i3);
        C0299p a = m1194a(i, i2, i3, f, charSequence, this.f1007m);
        ContextMenu.ContextMenuInfo contextMenuInfo = this.f1008n;
        if (contextMenuInfo != null) {
            a.mo1336a(contextMenuInfo);
        }
        ArrayList<C0299p> arrayList = this.f1001g;
        arrayList.add(m1193a(arrayList, f), a);
        mo1295b(true);
        return a;
    }

    /* renamed from: a */
    public void mo1273a() {
        C0294a aVar = this.f1000f;
        if (aVar != null) {
            aVar.mo677a(this);
        }
    }

    /* renamed from: a */
    public void mo1274a(Bundle bundle) {
        MenuItem findItem;
        if (bundle != null) {
            SparseArray sparseParcelableArray = bundle.getSparseParcelableArray(mo1148d());
            int size = size();
            for (int i = 0; i < size; i++) {
                MenuItem item = getItem(i);
                View actionView = item.getActionView();
                if (!(actionView == null || actionView.getId() == -1)) {
                    actionView.restoreHierarchyState(sparseParcelableArray);
                }
                if (item.hasSubMenu()) {
                    ((C0278D) item.getSubMenu()).mo1274a(bundle);
                }
            }
            int i2 = bundle.getInt("android:menu:expandedactionview");
            if (i2 > 0 && (findItem = findItem(i2)) != null) {
                findItem.expandActionView();
            }
        }
    }

    /* renamed from: a */
    public void mo1144a(C0294a aVar) {
        this.f1000f = aVar;
    }

    /* renamed from: a */
    public void mo1275a(C0310v vVar) {
        mo1276a(vVar, this.f996b);
    }

    /* renamed from: a */
    public void mo1276a(C0310v vVar, Context context) {
        this.f1018x.add(new WeakReference(vVar));
        vVar.mo1225a(context, this);
        this.f1006l = true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1277a(MenuItem menuItem) {
        int groupId = menuItem.getGroupId();
        int size = this.f1001g.size();
        mo1324s();
        for (int i = 0; i < size; i++) {
            C0299p pVar = this.f1001g.get(i);
            if (pVar.getGroupId() == groupId && pVar.mo1362i() && pVar.isCheckable()) {
                pVar.mo1339b(pVar == menuItem);
            }
        }
        mo1321r();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1278a(List<C0299p> list, int i, KeyEvent keyEvent) {
        boolean p = mo1152p();
        int modifiers = keyEvent.getModifiers();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        if (keyEvent.getKeyData(keyData) || i == 67) {
            int size = this.f1001g.size();
            for (int i2 = 0; i2 < size; i2++) {
                C0299p pVar = this.f1001g.get(i2);
                if (pVar.hasSubMenu()) {
                    ((C0293l) pVar.getSubMenu()).mo1278a(list, i, keyEvent);
                }
                char alphabeticShortcut = p ? pVar.getAlphabeticShortcut() : pVar.getNumericShortcut();
                if (((modifiers & 69647) == ((p ? pVar.getAlphabeticModifiers() : pVar.getNumericModifiers()) & 69647)) && alphabeticShortcut != 0) {
                    char[] cArr = keyData.meta;
                    if ((alphabeticShortcut == cArr[0] || alphabeticShortcut == cArr[2] || (p && alphabeticShortcut == 8 && i == 67)) && pVar.isEnabled()) {
                        list.add(pVar);
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public final void mo1279a(boolean z) {
        if (!this.f1016v) {
            this.f1016v = true;
            Iterator<WeakReference<C0310v>> it = this.f1018x.iterator();
            while (it.hasNext()) {
                WeakReference next = it.next();
                C0310v vVar = (C0310v) next.get();
                if (vVar == null) {
                    this.f1018x.remove(next);
                } else {
                    vVar.mo1127a(this, z);
                }
            }
            this.f1016v = false;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo1145a(C0293l lVar, MenuItem menuItem) {
        C0294a aVar = this.f1000f;
        return aVar != null && aVar.mo678a(lVar, menuItem);
    }

    /* renamed from: a */
    public boolean mo1146a(C0299p pVar) {
        boolean z = false;
        if (!this.f1018x.isEmpty() && this.f1019y == pVar) {
            mo1324s();
            Iterator<WeakReference<C0310v>> it = this.f1018x.iterator();
            while (it.hasNext()) {
                WeakReference next = it.next();
                C0310v vVar = (C0310v) next.get();
                if (vVar == null) {
                    this.f1018x.remove(next);
                } else {
                    z = vVar.mo1233b(this, pVar);
                    if (z) {
                        break;
                    }
                }
            }
            mo1321r();
            if (z) {
                this.f1019y = null;
            }
        }
        return z;
    }

    /* renamed from: a */
    public boolean mo1280a(MenuItem menuItem, int i) {
        return mo1281a(menuItem, (C0310v) null, i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002b, code lost:
        if (r1 != false) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003c, code lost:
        if ((r9 & 1) == 0) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0068, code lost:
        if (r1 == false) goto L_0x002d;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo1281a(android.view.MenuItem r7, android.support.p025v7.view.menu.C0310v r8, int r9) {
        /*
            r6 = this;
            android.support.v7.view.menu.p r7 = (android.support.p025v7.view.menu.C0299p) r7
            r0 = 0
            if (r7 == 0) goto L_0x006c
            boolean r1 = r7.isEnabled()
            if (r1 != 0) goto L_0x000c
            goto L_0x006c
        L_0x000c:
            boolean r1 = r7.mo1347g()
            a.a.c.g.e r2 = r7.mo195a()
            r3 = 1
            if (r2 == 0) goto L_0x001f
            boolean r4 = r2.mo456a()
            if (r4 == 0) goto L_0x001f
            r4 = 1
            goto L_0x0020
        L_0x001f:
            r4 = 0
        L_0x0020:
            boolean r5 = r7.mo1346f()
            if (r5 == 0) goto L_0x0031
            boolean r7 = r7.expandActionView()
            r1 = r1 | r7
            if (r1 == 0) goto L_0x006b
        L_0x002d:
            r6.mo1279a((boolean) r3)
            goto L_0x006b
        L_0x0031:
            boolean r5 = r7.hasSubMenu()
            if (r5 != 0) goto L_0x003f
            if (r4 == 0) goto L_0x003a
            goto L_0x003f
        L_0x003a:
            r7 = r9 & 1
            if (r7 != 0) goto L_0x006b
            goto L_0x002d
        L_0x003f:
            r9 = r9 & 4
            if (r9 != 0) goto L_0x0046
            r6.mo1279a((boolean) r0)
        L_0x0046:
            boolean r9 = r7.hasSubMenu()
            if (r9 != 0) goto L_0x0058
            android.support.v7.view.menu.D r9 = new android.support.v7.view.menu.D
            android.content.Context r0 = r6.mo1305e()
            r9.<init>(r0, r6, r7)
            r7.mo1335a((android.support.p025v7.view.menu.C0278D) r9)
        L_0x0058:
            android.view.SubMenu r7 = r7.getSubMenu()
            android.support.v7.view.menu.D r7 = (android.support.p025v7.view.menu.C0278D) r7
            if (r4 == 0) goto L_0x0063
            r2.mo455a((android.view.SubMenu) r7)
        L_0x0063:
            boolean r7 = r6.m1197a((android.support.p025v7.view.menu.C0278D) r7, (android.support.p025v7.view.menu.C0310v) r8)
            r1 = r1 | r7
            if (r1 != 0) goto L_0x006b
            goto L_0x002d
        L_0x006b:
            return r1
        L_0x006c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p025v7.view.menu.C0293l.mo1281a(android.view.MenuItem, android.support.v7.view.menu.v, int):boolean");
    }

    public MenuItem add(int i) {
        return mo1272a(0, 0, 0, this.f997c.getString(i));
    }

    public MenuItem add(int i, int i2, int i3, int i4) {
        return mo1272a(i, i2, i3, this.f997c.getString(i4));
    }

    public MenuItem add(int i, int i2, int i3, CharSequence charSequence) {
        return mo1272a(i, i2, i3, charSequence);
    }

    public MenuItem add(CharSequence charSequence) {
        return mo1272a(0, 0, 0, charSequence);
    }

    public int addIntentOptions(int i, int i2, int i3, ComponentName componentName, Intent[] intentArr, Intent intent, int i4, MenuItem[] menuItemArr) {
        int i5;
        PackageManager packageManager = this.f996b.getPackageManager();
        List<ResolveInfo> queryIntentActivityOptions = packageManager.queryIntentActivityOptions(componentName, intentArr, intent, 0);
        int size = queryIntentActivityOptions != null ? queryIntentActivityOptions.size() : 0;
        if ((i4 & 1) == 0) {
            removeGroup(i);
        }
        for (int i6 = 0; i6 < size; i6++) {
            ResolveInfo resolveInfo = queryIntentActivityOptions.get(i6);
            int i7 = resolveInfo.specificIndex;
            Intent intent2 = new Intent(i7 < 0 ? intent : intentArr[i7]);
            intent2.setComponent(new ComponentName(resolveInfo.activityInfo.applicationInfo.packageName, resolveInfo.activityInfo.name));
            MenuItem intent3 = add(i, i2, i3, resolveInfo.loadLabel(packageManager)).setIcon(resolveInfo.loadIcon(packageManager)).setIntent(intent2);
            if (menuItemArr != null && (i5 = resolveInfo.specificIndex) >= 0) {
                menuItemArr[i5] = intent3;
            }
        }
        return size;
    }

    public SubMenu addSubMenu(int i) {
        return addSubMenu(0, 0, 0, (CharSequence) this.f997c.getString(i));
    }

    public SubMenu addSubMenu(int i, int i2, int i3, int i4) {
        return addSubMenu(i, i2, i3, (CharSequence) this.f997c.getString(i4));
    }

    public SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        C0299p pVar = (C0299p) mo1272a(i, i2, i3, charSequence);
        C0278D d = new C0278D(this.f996b, this, pVar);
        pVar.mo1335a(d);
        return d;
    }

    public SubMenu addSubMenu(CharSequence charSequence) {
        return addSubMenu(0, 0, 0, charSequence);
    }

    /* renamed from: b */
    public int mo1291b(int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            if (this.f1001g.get(i2).getItemId() == i) {
                return i2;
            }
        }
        return -1;
    }

    /* renamed from: b */
    public void mo1292b() {
        ArrayList<C0299p> n = mo1318n();
        if (this.f1006l) {
            Iterator<WeakReference<C0310v>> it = this.f1018x.iterator();
            boolean z = false;
            while (it.hasNext()) {
                WeakReference next = it.next();
                C0310v vVar = (C0310v) next.get();
                if (vVar == null) {
                    this.f1018x.remove(next);
                } else {
                    z |= vVar.mo1132a();
                }
            }
            if (z) {
                this.f1004j.clear();
                this.f1005k.clear();
                int size = n.size();
                for (int i = 0; i < size; i++) {
                    C0299p pVar = n.get(i);
                    (pVar.mo1360h() ? this.f1004j : this.f1005k).add(pVar);
                }
            } else {
                this.f1004j.clear();
                this.f1005k.clear();
                this.f1005k.addAll(mo1318n());
            }
            this.f1006l = false;
        }
    }

    /* renamed from: b */
    public void mo1293b(Bundle bundle) {
        int size = size();
        SparseArray sparseArray = null;
        for (int i = 0; i < size; i++) {
            MenuItem item = getItem(i);
            View actionView = item.getActionView();
            if (!(actionView == null || actionView.getId() == -1)) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray();
                }
                actionView.saveHierarchyState(sparseArray);
                if (item.isActionViewExpanded()) {
                    bundle.putInt("android:menu:expandedactionview", item.getItemId());
                }
            }
            if (item.hasSubMenu()) {
                ((C0278D) item.getSubMenu()).mo1293b(bundle);
            }
        }
        if (sparseArray != null) {
            bundle.putSparseParcelableArray(mo1148d(), sparseArray);
        }
    }

    /* renamed from: b */
    public void mo1294b(C0310v vVar) {
        Iterator<WeakReference<C0310v>> it = this.f1018x.iterator();
        while (it.hasNext()) {
            WeakReference next = it.next();
            C0310v vVar2 = (C0310v) next.get();
            if (vVar2 == null || vVar2 == vVar) {
                this.f1018x.remove(next);
            }
        }
    }

    /* renamed from: b */
    public void mo1295b(boolean z) {
        if (!this.f1012r) {
            if (z) {
                this.f1003i = true;
                this.f1006l = true;
            }
            m1198d(z);
            return;
        }
        this.f1013s = true;
        if (z) {
            this.f1014t = true;
        }
    }

    /* renamed from: b */
    public boolean mo1147b(C0299p pVar) {
        boolean z = false;
        if (this.f1018x.isEmpty()) {
            return false;
        }
        mo1324s();
        Iterator<WeakReference<C0310v>> it = this.f1018x.iterator();
        while (it.hasNext()) {
            WeakReference next = it.next();
            C0310v vVar = (C0310v) next.get();
            if (vVar == null) {
                this.f1018x.remove(next);
            } else {
                z = vVar.mo1229a(this, pVar);
                if (z) {
                    break;
                }
            }
        }
        mo1321r();
        if (z) {
            this.f1019y = pVar;
        }
        return z;
    }

    /* renamed from: c */
    public C0293l mo1296c(int i) {
        this.f1007m = i;
        return this;
    }

    /* renamed from: c */
    public ArrayList<C0299p> mo1297c() {
        mo1292b();
        return this.f1004j;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public void mo1298c(C0299p pVar) {
        this.f1006l = true;
        mo1295b(true);
    }

    /* renamed from: c */
    public void mo1299c(boolean z) {
        this.f995A = z;
    }

    public void clear() {
        C0299p pVar = this.f1019y;
        if (pVar != null) {
            mo1146a(pVar);
        }
        this.f1001g.clear();
        mo1295b(true);
    }

    public void clearHeader() {
        this.f1010p = null;
        this.f1009o = null;
        this.f1011q = null;
        mo1295b(false);
    }

    public void close() {
        mo1279a(true);
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public C0293l mo1303d(int i) {
        m1195a(0, (CharSequence) null, i, (Drawable) null, (View) null);
        return this;
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public String mo1148d() {
        return "android:menu:actionviewstates";
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public void mo1304d(C0299p pVar) {
        this.f1003i = true;
        mo1295b(true);
    }

    /* renamed from: e */
    public Context mo1305e() {
        return this.f996b;
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public C0293l mo1306e(int i) {
        m1195a(i, (CharSequence) null, 0, (Drawable) null, (View) null);
        return this;
    }

    /* renamed from: f */
    public C0299p mo1307f() {
        return this.f1019y;
    }

    public MenuItem findItem(int i) {
        MenuItem findItem;
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            C0299p pVar = this.f1001g.get(i2);
            if (pVar.getItemId() == i) {
                return pVar;
            }
            if (pVar.hasSubMenu() && (findItem = pVar.getSubMenu().findItem(i)) != null) {
                return findItem;
            }
        }
        return null;
    }

    /* renamed from: g */
    public Drawable mo1309g() {
        return this.f1010p;
    }

    public MenuItem getItem(int i) {
        return this.f1001g.get(i);
    }

    /* renamed from: h */
    public CharSequence mo1311h() {
        return this.f1009o;
    }

    public boolean hasVisibleItems() {
        if (this.f995A) {
            return true;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (this.f1001g.get(i).isVisible()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: i */
    public View mo1313i() {
        return this.f1011q;
    }

    public boolean isShortcutKey(int i, KeyEvent keyEvent) {
        return mo1271a(i, keyEvent) != null;
    }

    /* renamed from: j */
    public ArrayList<C0299p> mo1315j() {
        mo1292b();
        return this.f1005k;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: k */
    public boolean mo1316k() {
        return this.f1015u;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: l */
    public Resources mo1317l() {
        return this.f997c;
    }

    /* renamed from: m */
    public C0293l mo1150m() {
        return this;
    }

    /* renamed from: n */
    public ArrayList<C0299p> mo1318n() {
        if (!this.f1003i) {
            return this.f1002h;
        }
        this.f1002h.clear();
        int size = this.f1001g.size();
        for (int i = 0; i < size; i++) {
            C0299p pVar = this.f1001g.get(i);
            if (pVar.isVisible()) {
                this.f1002h.add(pVar);
            }
        }
        this.f1003i = false;
        this.f1006l = true;
        return this.f1002h;
    }

    /* renamed from: o */
    public boolean mo1151o() {
        return this.f1020z;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: p */
    public boolean mo1152p() {
        return this.f998d;
    }

    public boolean performIdentifierAction(int i, int i2) {
        return mo1280a(findItem(i), i2);
    }

    public boolean performShortcut(int i, KeyEvent keyEvent, int i2) {
        C0299p a = mo1271a(i, keyEvent);
        boolean a2 = a != null ? mo1280a((MenuItem) a, i2) : false;
        if ((i2 & 2) != 0) {
            mo1279a(true);
        }
        return a2;
    }

    /* renamed from: q */
    public boolean mo1153q() {
        return this.f999e;
    }

    /* renamed from: r */
    public void mo1321r() {
        this.f1012r = false;
        if (this.f1013s) {
            this.f1013s = false;
            mo1295b(this.f1014t);
        }
    }

    public void removeGroup(int i) {
        int a = mo1266a(i);
        if (a >= 0) {
            int size = this.f1001g.size() - a;
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                if (i2 >= size || this.f1001g.get(a).getGroupId() != i) {
                    mo1295b(true);
                } else {
                    m1196a(a, false);
                    i2 = i3;
                }
            }
            mo1295b(true);
        }
    }

    public void removeItem(int i) {
        m1196a(mo1291b(i), true);
    }

    /* renamed from: s */
    public void mo1324s() {
        if (!this.f1012r) {
            this.f1012r = true;
            this.f1013s = false;
            this.f1014t = false;
        }
    }

    public void setGroupCheckable(int i, boolean z, boolean z2) {
        int size = this.f1001g.size();
        for (int i2 = 0; i2 < size; i2++) {
            C0299p pVar = this.f1001g.get(i2);
            if (pVar.getGroupId() == i) {
                pVar.mo1341c(z2);
                pVar.setCheckable(z);
            }
        }
    }

    public void setGroupDividerEnabled(boolean z) {
        this.f1020z = z;
    }

    public void setGroupEnabled(int i, boolean z) {
        int size = this.f1001g.size();
        for (int i2 = 0; i2 < size; i2++) {
            C0299p pVar = this.f1001g.get(i2);
            if (pVar.getGroupId() == i) {
                pVar.setEnabled(z);
            }
        }
    }

    public void setGroupVisible(int i, boolean z) {
        int size = this.f1001g.size();
        boolean z2 = false;
        for (int i2 = 0; i2 < size; i2++) {
            C0299p pVar = this.f1001g.get(i2);
            if (pVar.getGroupId() == i && pVar.mo1345e(z)) {
                z2 = true;
            }
        }
        if (z2) {
            mo1295b(true);
        }
    }

    public void setQwertyMode(boolean z) {
        this.f998d = z;
        mo1295b(false);
    }

    public int size() {
        return this.f1001g.size();
    }
}
