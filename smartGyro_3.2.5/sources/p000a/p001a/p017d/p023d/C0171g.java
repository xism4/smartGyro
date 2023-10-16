package p000a.p001a.p017d.p023d;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.support.p025v7.view.menu.C0299p;
import android.support.p025v7.view.menu.C0300q;
import android.support.p025v7.widget.C0348M;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p000a.p001a.p005c.p009c.p010a.C0056a;
import p000a.p001a.p005c.p014g.C0108e;
import p000a.p001a.p005c.p014g.C0116j;
import p000a.p001a.p017d.p018a.C0145j;

/* renamed from: a.a.d.d.g */
public class C0171g extends MenuInflater {

    /* renamed from: a */
    static final Class<?>[] f395a = {Context.class};

    /* renamed from: b */
    static final Class<?>[] f396b = f395a;

    /* renamed from: c */
    final Object[] f397c;

    /* renamed from: d */
    final Object[] f398d = this.f397c;

    /* renamed from: e */
    Context f399e;

    /* renamed from: f */
    private Object f400f;

    /* renamed from: a.a.d.d.g$a */
    private static class C0172a implements MenuItem.OnMenuItemClickListener {

        /* renamed from: a */
        private static final Class<?>[] f401a = {MenuItem.class};

        /* renamed from: b */
        private Object f402b;

        /* renamed from: c */
        private Method f403c;

        public C0172a(Object obj, String str) {
            this.f402b = obj;
            Class<?> cls = obj.getClass();
            try {
                this.f403c = cls.getMethod(str, f401a);
            } catch (Exception e) {
                InflateException inflateException = new InflateException("Couldn't resolve menu item onClick handler " + str + " in class " + cls.getName());
                inflateException.initCause(e);
                throw inflateException;
            }
        }

        public boolean onMenuItemClick(MenuItem menuItem) {
            try {
                if (this.f403c.getReturnType() == Boolean.TYPE) {
                    return ((Boolean) this.f403c.invoke(this.f402b, new Object[]{menuItem})).booleanValue();
                }
                this.f403c.invoke(this.f402b, new Object[]{menuItem});
                return true;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /* renamed from: a.a.d.d.g$b */
    private class C0173b {

        /* renamed from: A */
        C0108e f404A;

        /* renamed from: B */
        private CharSequence f405B;

        /* renamed from: C */
        private CharSequence f406C;

        /* renamed from: D */
        private ColorStateList f407D = null;

        /* renamed from: E */
        private PorterDuff.Mode f408E = null;

        /* renamed from: a */
        private Menu f410a;

        /* renamed from: b */
        private int f411b;

        /* renamed from: c */
        private int f412c;

        /* renamed from: d */
        private int f413d;

        /* renamed from: e */
        private int f414e;

        /* renamed from: f */
        private boolean f415f;

        /* renamed from: g */
        private boolean f416g;

        /* renamed from: h */
        private boolean f417h;

        /* renamed from: i */
        private int f418i;

        /* renamed from: j */
        private int f419j;

        /* renamed from: k */
        private CharSequence f420k;

        /* renamed from: l */
        private CharSequence f421l;

        /* renamed from: m */
        private int f422m;

        /* renamed from: n */
        private char f423n;

        /* renamed from: o */
        private int f424o;

        /* renamed from: p */
        private char f425p;

        /* renamed from: q */
        private int f426q;

        /* renamed from: r */
        private int f427r;

        /* renamed from: s */
        private boolean f428s;

        /* renamed from: t */
        private boolean f429t;

        /* renamed from: u */
        private boolean f430u;

        /* renamed from: v */
        private int f431v;

        /* renamed from: w */
        private int f432w;

        /* renamed from: x */
        private String f433x;

        /* renamed from: y */
        private String f434y;

        /* renamed from: z */
        private String f435z;

        public C0173b(Menu menu) {
            this.f410a = menu;
            mo705d();
        }

        /* renamed from: a */
        private char m635a(String str) {
            if (str == null) {
                return 0;
            }
            return str.charAt(0);
        }

        /* renamed from: a */
        private <T> T m636a(String str, Class<?>[] clsArr, Object[] objArr) {
            try {
                Constructor<?> constructor = C0171g.this.f399e.getClassLoader().loadClass(str).getConstructor(clsArr);
                constructor.setAccessible(true);
                return constructor.newInstance(objArr);
            } catch (Exception e) {
                Log.w("SupportMenuInflater", "Cannot instantiate class: " + str, e);
                return null;
            }
        }

        /* renamed from: a */
        private void m637a(MenuItem menuItem) {
            boolean z = false;
            menuItem.setChecked(this.f428s).setVisible(this.f429t).setEnabled(this.f430u).setCheckable(this.f427r >= 1).setTitleCondensed(this.f421l).setIcon(this.f422m);
            int i = this.f431v;
            if (i >= 0) {
                menuItem.setShowAsAction(i);
            }
            if (this.f435z != null) {
                if (!C0171g.this.f399e.isRestricted()) {
                    menuItem.setOnMenuItemClickListener(new C0172a(C0171g.this.mo697a(), this.f435z));
                } else {
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                }
            }
            boolean z2 = menuItem instanceof C0299p;
            if (z2) {
                C0299p pVar = (C0299p) menuItem;
            }
            if (this.f427r >= 2) {
                if (z2) {
                    ((C0299p) menuItem).mo1341c(true);
                } else if (menuItem instanceof C0300q) {
                    ((C0300q) menuItem).mo1390a(true);
                }
            }
            String str = this.f433x;
            if (str != null) {
                menuItem.setActionView((View) m636a(str, C0171g.f395a, C0171g.this.f397c));
                z = true;
            }
            int i2 = this.f432w;
            if (i2 > 0) {
                if (!z) {
                    menuItem.setActionView(i2);
                } else {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
                }
            }
            C0108e eVar = this.f404A;
            if (eVar != null) {
                C0116j.m407a(menuItem, eVar);
            }
            C0116j.m411a(menuItem, this.f405B);
            C0116j.m413b(menuItem, this.f406C);
            C0116j.m408a(menuItem, this.f423n, this.f424o);
            C0116j.m412b(menuItem, this.f425p, this.f426q);
            PorterDuff.Mode mode = this.f408E;
            if (mode != null) {
                C0116j.m410a(menuItem, mode);
            }
            ColorStateList colorStateList = this.f407D;
            if (colorStateList != null) {
                C0116j.m409a(menuItem, colorStateList);
            }
        }

        /* renamed from: a */
        public void mo700a() {
            this.f417h = true;
            m637a(this.f410a.add(this.f411b, this.f418i, this.f419j, this.f420k));
        }

        /* renamed from: a */
        public void mo701a(AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = C0171g.this.f399e.obtainStyledAttributes(attributeSet, C0145j.MenuGroup);
            this.f411b = obtainStyledAttributes.getResourceId(C0145j.MenuGroup_android_id, 0);
            this.f412c = obtainStyledAttributes.getInt(C0145j.MenuGroup_android_menuCategory, 0);
            this.f413d = obtainStyledAttributes.getInt(C0145j.MenuGroup_android_orderInCategory, 0);
            this.f414e = obtainStyledAttributes.getInt(C0145j.MenuGroup_android_checkableBehavior, 0);
            this.f415f = obtainStyledAttributes.getBoolean(C0145j.MenuGroup_android_visible, true);
            this.f416g = obtainStyledAttributes.getBoolean(C0145j.MenuGroup_android_enabled, true);
            obtainStyledAttributes.recycle();
        }

        /* renamed from: b */
        public SubMenu mo702b() {
            this.f417h = true;
            SubMenu addSubMenu = this.f410a.addSubMenu(this.f411b, this.f418i, this.f419j, this.f420k);
            m637a(addSubMenu.getItem());
            return addSubMenu;
        }

        /* renamed from: b */
        public void mo703b(AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = C0171g.this.f399e.obtainStyledAttributes(attributeSet, C0145j.MenuItem);
            this.f418i = obtainStyledAttributes.getResourceId(C0145j.MenuItem_android_id, 0);
            this.f419j = (obtainStyledAttributes.getInt(C0145j.MenuItem_android_menuCategory, this.f412c) & -65536) | (obtainStyledAttributes.getInt(C0145j.MenuItem_android_orderInCategory, this.f413d) & 65535);
            this.f420k = obtainStyledAttributes.getText(C0145j.MenuItem_android_title);
            this.f421l = obtainStyledAttributes.getText(C0145j.MenuItem_android_titleCondensed);
            this.f422m = obtainStyledAttributes.getResourceId(C0145j.MenuItem_android_icon, 0);
            this.f423n = m635a(obtainStyledAttributes.getString(C0145j.MenuItem_android_alphabeticShortcut));
            this.f424o = obtainStyledAttributes.getInt(C0145j.MenuItem_alphabeticModifiers, 4096);
            this.f425p = m635a(obtainStyledAttributes.getString(C0145j.MenuItem_android_numericShortcut));
            this.f426q = obtainStyledAttributes.getInt(C0145j.MenuItem_numericModifiers, 4096);
            this.f427r = obtainStyledAttributes.hasValue(C0145j.MenuItem_android_checkable) ? obtainStyledAttributes.getBoolean(C0145j.MenuItem_android_checkable, false) : this.f414e;
            this.f428s = obtainStyledAttributes.getBoolean(C0145j.MenuItem_android_checked, false);
            this.f429t = obtainStyledAttributes.getBoolean(C0145j.MenuItem_android_visible, this.f415f);
            this.f430u = obtainStyledAttributes.getBoolean(C0145j.MenuItem_android_enabled, this.f416g);
            this.f431v = obtainStyledAttributes.getInt(C0145j.MenuItem_showAsAction, -1);
            this.f435z = obtainStyledAttributes.getString(C0145j.MenuItem_android_onClick);
            this.f432w = obtainStyledAttributes.getResourceId(C0145j.MenuItem_actionLayout, 0);
            this.f433x = obtainStyledAttributes.getString(C0145j.MenuItem_actionViewClass);
            this.f434y = obtainStyledAttributes.getString(C0145j.MenuItem_actionProviderClass);
            boolean z = this.f434y != null;
            if (z && this.f432w == 0 && this.f433x == null) {
                this.f404A = (C0108e) m636a(this.f434y, C0171g.f396b, C0171g.this.f398d);
            } else {
                if (z) {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                }
                this.f404A = null;
            }
            this.f405B = obtainStyledAttributes.getText(C0145j.MenuItem_contentDescription);
            this.f406C = obtainStyledAttributes.getText(C0145j.MenuItem_tooltipText);
            if (obtainStyledAttributes.hasValue(C0145j.MenuItem_iconTintMode)) {
                this.f408E = C0348M.m1559a(obtainStyledAttributes.getInt(C0145j.MenuItem_iconTintMode, -1), this.f408E);
            } else {
                this.f408E = null;
            }
            if (obtainStyledAttributes.hasValue(C0145j.MenuItem_iconTint)) {
                this.f407D = obtainStyledAttributes.getColorStateList(C0145j.MenuItem_iconTint);
            } else {
                this.f407D = null;
            }
            obtainStyledAttributes.recycle();
            this.f417h = false;
        }

        /* renamed from: c */
        public boolean mo704c() {
            return this.f417h;
        }

        /* renamed from: d */
        public void mo705d() {
            this.f411b = 0;
            this.f412c = 0;
            this.f413d = 0;
            this.f414e = 0;
            this.f415f = true;
            this.f416g = true;
        }
    }

    public C0171g(Context context) {
        super(context);
        this.f399e = context;
        this.f397c = new Object[]{context};
    }

    /* renamed from: a */
    private Object m632a(Object obj) {
        return (!(obj instanceof Activity) && (obj instanceof ContextWrapper)) ? m632a(((ContextWrapper) obj).getBaseContext()) : obj;
    }

    /* renamed from: a */
    private void m633a(XmlPullParser xmlPullParser, AttributeSet attributeSet, Menu menu) {
        C0173b bVar = new C0173b(menu);
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType != 2) {
                eventType = xmlPullParser.next();
                if (eventType == 1) {
                    break;
                }
            } else {
                String name = xmlPullParser.getName();
                if (name.equals("menu")) {
                    eventType = xmlPullParser.next();
                } else {
                    throw new RuntimeException("Expecting menu, got " + name);
                }
            }
        }
        int i = eventType;
        String str = null;
        boolean z = false;
        boolean z2 = false;
        while (!z) {
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        String name2 = xmlPullParser.getName();
                        if (z2 && name2.equals(str)) {
                            str = null;
                            z2 = false;
                        } else if (name2.equals("group")) {
                            bVar.mo705d();
                        } else if (name2.equals("item")) {
                            if (!bVar.mo704c()) {
                                C0108e eVar = bVar.f404A;
                                if (eVar == null || !eVar.mo456a()) {
                                    bVar.mo700a();
                                } else {
                                    bVar.mo702b();
                                }
                            }
                        } else if (name2.equals("menu")) {
                            z = true;
                        }
                    }
                } else if (!z2) {
                    String name3 = xmlPullParser.getName();
                    if (name3.equals("group")) {
                        bVar.mo701a(attributeSet);
                    } else if (name3.equals("item")) {
                        bVar.mo703b(attributeSet);
                    } else if (name3.equals("menu")) {
                        m633a(xmlPullParser, attributeSet, bVar.mo702b());
                    } else {
                        str = name3;
                        z2 = true;
                    }
                }
                i = xmlPullParser.next();
            } else {
                throw new RuntimeException("Unexpected end of document");
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public Object mo697a() {
        if (this.f400f == null) {
            this.f400f = m632a(this.f399e);
        }
        return this.f400f;
    }

    public void inflate(int i, Menu menu) {
        if (!(menu instanceof C0056a)) {
            super.inflate(i, menu);
            return;
        }
        XmlResourceParser xmlResourceParser = null;
        try {
            xmlResourceParser = this.f399e.getResources().getLayout(i);
            m633a(xmlResourceParser, Xml.asAttributeSet(xmlResourceParser), menu);
            if (xmlResourceParser != null) {
                xmlResourceParser.close();
            }
        } catch (XmlPullParserException e) {
            throw new InflateException("Error inflating menu XML", e);
        } catch (IOException e2) {
            throw new InflateException("Error inflating menu XML", e2);
        } catch (Throwable th) {
            if (xmlResourceParser != null) {
                xmlResourceParser.close();
            }
            throw th;
        }
    }
}
