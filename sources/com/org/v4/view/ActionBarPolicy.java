package com.org.v4.view;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewConfiguration;
import com.org.v4.util.R$attr;
import com.org.v4.util.R$bool;
import com.org.v4.util.R$dimen;
import com.org.v4.util.R$styleable;

public class ActionBarPolicy {
    private Context mContext;

    private ActionBarPolicy(Context context) {
        this.mContext = context;
    }

    public static ActionBarPolicy get(Context context) {
        return new ActionBarPolicy(context);
    }

    public boolean enableHomeButtonByDefault() {
        return this.mContext.getApplicationInfo().targetSdkVersion < 14;
    }

    public int getEmbeddedMenuWidthLimit() {
        return this.mContext.getResources().getDisplayMetrics().widthPixels / 2;
    }

    public int getStackedTabMaxWidth() {
        return this.mContext.getResources().getDimensionPixelSize(R$dimen.abc_action_bar_stacked_tab_max_width);
    }

    public int getTabContainerHeight() {
        TypedArray $r3 = this.mContext.obtainStyledAttributes((AttributeSet) null, R$styleable.ActionBar, R$attr.actionBarStyle, 0);
        int $i0 = $r3.getLayoutDimension(R$styleable.ActionBar_height, 0);
        int $i1 = $i0;
        Resources $r4 = this.mContext.getResources();
        if (!hasEmbeddedTabs()) {
            $i1 = Math.min($i0, $r4.getDimensionPixelSize(R$dimen.abc_action_bar_stacked_max_height));
        }
        $r3.recycle();
        return $i1;
    }

    public boolean hasEmbeddedTabs() {
        return this.mContext.getResources().getBoolean(R$bool.abc_action_bar_embed_tabs);
    }

    public int init() {
        Configuration $r3 = this.mContext.getResources().getConfiguration();
        int $i0 = $r3.screenWidthDp;
        int $i1 = $r3.screenHeightDp;
        if ($r3.smallestScreenWidthDp > 600 || $i0 > 600) {
            return 5;
        }
        if ($i0 > 960 && $i1 > 720) {
            return 5;
        }
        if ($i0 > 720 && $i1 > 960) {
            return 5;
        }
        if ($i0 >= 500) {
            return 4;
        }
        if ($i0 > 640 && $i1 > 480) {
            return 4;
        }
        if ($i0 <= 480 || $i1 <= 640) {
            return $i0 >= 360 ? 3 : 2;
        }
        return 4;
    }

    public boolean showsOverflowMenuButton() {
        if (Build.VERSION.SDK_INT >= 19) {
            return true;
        }
        return !ViewConfiguration.get(this.mContext).hasPermanentMenuKey();
    }
}
