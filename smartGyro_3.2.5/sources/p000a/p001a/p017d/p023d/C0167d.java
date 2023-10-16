package p000a.p001a.p017d.p023d;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.view.LayoutInflater;
import p000a.p001a.p017d.p018a.C0144i;

/* renamed from: a.a.d.d.d */
public class C0167d extends ContextWrapper {

    /* renamed from: a */
    private int f377a;

    /* renamed from: b */
    private Resources.Theme f378b;

    /* renamed from: c */
    private LayoutInflater f379c;

    /* renamed from: d */
    private Configuration f380d;

    /* renamed from: e */
    private Resources f381e;

    public C0167d() {
        super((Context) null);
    }

    public C0167d(Context context, int i) {
        super(context);
        this.f377a = i;
    }

    public C0167d(Context context, Resources.Theme theme) {
        super(context);
        this.f378b = theme;
    }

    /* renamed from: b */
    private Resources m606b() {
        Resources resources;
        if (this.f381e == null) {
            Configuration configuration = this.f380d;
            if (configuration == null) {
                resources = super.getResources();
            } else if (Build.VERSION.SDK_INT >= 17) {
                resources = createConfigurationContext(configuration).getResources();
            }
            this.f381e = resources;
        }
        return this.f381e;
    }

    /* renamed from: c */
    private void m607c() {
        boolean z = this.f378b == null;
        if (z) {
            this.f378b = getResources().newTheme();
            Resources.Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.f378b.setTo(theme);
            }
        }
        mo670a(this.f378b, this.f377a, z);
    }

    /* renamed from: a */
    public int mo669a() {
        return this.f377a;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo670a(Resources.Theme theme, int i, boolean z) {
        theme.applyStyle(i, true);
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    public AssetManager getAssets() {
        return getResources().getAssets();
    }

    public Resources getResources() {
        return m606b();
    }

    public Object getSystemService(String str) {
        if (!"layout_inflater".equals(str)) {
            return getBaseContext().getSystemService(str);
        }
        if (this.f379c == null) {
            this.f379c = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        }
        return this.f379c;
    }

    public Resources.Theme getTheme() {
        Resources.Theme theme = this.f378b;
        if (theme != null) {
            return theme;
        }
        if (this.f377a == 0) {
            this.f377a = C0144i.Theme_AppCompat_Light;
        }
        m607c();
        return this.f378b;
    }

    public void setTheme(int i) {
        if (this.f377a != i) {
            this.f377a = i;
            m607c();
        }
    }
}
