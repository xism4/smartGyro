package com.org.v4.view;

import a.a.c.f.i;
import a.a.d.d.f;
import android.content.Context;
import android.support.v7.view.menu.MenuWrapperFactory;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import com.org.android.impl.cookie.SupportMenu;
import com.org.android.impl.cookie.SupportMenuItem;
import com.org.android.util.SimpleArrayMap;
import com.org.v4.view.ActionMode;
import java.util.ArrayList;

public class SupportActionModeWrapper extends ActionMode {
    final Context mContext;
    final ActionMode mWrappedObject;

    public class CallbackWrapper implements ActionMode.Callback {
        final ArrayList<f> mActionModes = new ArrayList();
        final Context mContext;
        final i<Menu, Menu> mMenus = new SimpleArrayMap();
        final ActionMode.Callback mWrappedCallback;

        public CallbackWrapper(Context context, ActionMode.Callback callback) {
            this.mContext = context;
            this.mWrappedCallback = callback;
        }

        private Menu getMenuWrapper(Menu menu) {
            Menu $r4 = (Menu) this.mMenus.get(menu);
            if ($r4 != null) {
                return $r4;
            }
            Menu $r42 = MenuWrapperFactory.wrapSupportMenu(this.mContext, (SupportMenu) menu);
            this.mMenus.put(menu, $r42);
            return $r42;
        }

        public android.view.ActionMode getActionModeWrapper(ActionMode actionMode) {
            int $i0 = this.mActionModes.size();
            for (int $i1 = 0; $i1 < $i0; $i1++) {
                SupportActionModeWrapper $r5 = this.mActionModes.get($i1);
                if ($r5 != null && $r5.mWrappedObject == actionMode) {
                    return $r5;
                }
            }
            SupportActionModeWrapper $r52 = new SupportActionModeWrapper(this.mContext, actionMode);
            this.mActionModes.add($r52);
            return $r52;
        }

        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return this.mWrappedCallback.onActionItemClicked(getActionModeWrapper(actionMode), MenuWrapperFactory.wrapSupportMenuItem(this.mContext, (SupportMenuItem) menuItem));
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            return this.mWrappedCallback.onCreateActionMode(getActionModeWrapper(actionMode), getMenuWrapper(menu));
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            this.mWrappedCallback.onDestroyActionMode(getActionModeWrapper(actionMode));
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return this.mWrappedCallback.onPrepareActionMode(getActionModeWrapper(actionMode), getMenuWrapper(menu));
        }
    }

    public SupportActionModeWrapper(Context context, ActionMode actionMode) {
        this.mContext = context;
        this.mWrappedObject = actionMode;
    }

    public void finish() {
        this.mWrappedObject.finish();
    }

    public View getCustomView() {
        return this.mWrappedObject.getCustomView();
    }

    public Menu getMenu() {
        return MenuWrapperFactory.wrapSupportMenu(this.mContext, (SupportMenu) this.mWrappedObject.getMenu());
    }

    public MenuInflater getMenuInflater() {
        return this.mWrappedObject.getMenuInflater();
    }

    public CharSequence getSubtitle() {
        return this.mWrappedObject.getSubtitle();
    }

    public Object getTag() {
        return this.mWrappedObject.getTag();
    }

    public CharSequence getTitle() {
        return this.mWrappedObject.getTitle();
    }

    public boolean getTitleOptionalHint() {
        return this.mWrappedObject.getTitleOptionalHint();
    }

    public void invalidate() {
        this.mWrappedObject.invalidate();
    }

    public boolean isTitleOptional() {
        return this.mWrappedObject.isTitleOptional();
    }

    public void setCustomView(View view) {
        this.mWrappedObject.setCustomView(view);
    }

    public void setSubtitle(int i) {
        this.mWrappedObject.setSubtitle(i);
    }

    public void setSubtitle(CharSequence charSequence) {
        this.mWrappedObject.setSubtitle(charSequence);
    }

    public void setTag(Object obj) {
        this.mWrappedObject.setTag(obj);
    }

    public void setTitle(int i) {
        this.mWrappedObject.setTitle(i);
    }

    public void setTitle(CharSequence charSequence) {
        this.mWrappedObject.setTitle(charSequence);
    }

    public void setTitleOptionalHint(boolean z) {
        this.mWrappedObject.setTitleOptionalHint(z);
    }
}
