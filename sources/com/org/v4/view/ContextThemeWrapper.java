package com.org.v4.view;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.view.LayoutInflater;
import com.org.v4.util.R$style;

public class ContextThemeWrapper extends ContextWrapper {
    private Configuration instance;
    private LayoutInflater mInflater;
    private Resources.Theme mTheme;
    private int mThemeResource;
    private Resources result;

    public ContextThemeWrapper() {
        super((Context) null);
    }

    public ContextThemeWrapper(Context context, int i) {
        super(context);
        this.mThemeResource = i;
    }

    public ContextThemeWrapper(Context context, Resources.Theme theme) {
        super(context);
        this.mTheme = theme;
    }

    private Resources get() {
        Resources $r1;
        if (this.result == null) {
            Configuration $r2 = this.instance;
            if ($r2 == null) {
                $r1 = super.getResources();
            } else if (Build.VERSION.SDK_INT >= 17) {
                $r1 = createConfigurationContext($r2).getResources();
            }
            this.result = $r1;
        }
        return this.result;
    }

    private void initializeTheme() {
        boolean $z0 = this.mTheme == null;
        if ($z0) {
            this.mTheme = getResources().newTheme();
            Resources.Theme $r1 = getBaseContext().getTheme();
            if ($r1 != null) {
                this.mTheme.setTo($r1);
            }
        }
        onApplyThemeResource(this.mTheme, this.mThemeResource, $z0);
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    public AssetManager getAssets() {
        return getResources().getAssets();
    }

    public Resources getResources() {
        return get();
    }

    public Object getSystemService(String str) {
        if (!"layout_inflater".equals(str)) {
            return getBaseContext().getSystemService(str);
        }
        if (this.mInflater == null) {
            this.mInflater = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        }
        return this.mInflater;
    }

    public Resources.Theme getTheme() {
        Resources.Theme $r1 = this.mTheme;
        if ($r1 != null) {
            return $r1;
        }
        if (this.mThemeResource == 0) {
            this.mThemeResource = R$style.Theme_AppCompat_Light;
        }
        initializeTheme();
        return this.mTheme;
    }

    public int getThemeResId() {
        return this.mThemeResource;
    }

    /* access modifiers changed from: protected */
    public void onApplyThemeResource(Resources.Theme theme, int i, boolean z) {
        theme.applyStyle(i, true);
    }

    public void setTheme(int i) {
        if (this.mThemeResource != i) {
            this.mThemeResource = i;
            initializeTheme();
        }
    }
}
