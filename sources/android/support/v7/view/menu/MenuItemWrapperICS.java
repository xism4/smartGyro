package android.support.v7.view.menu;

import a.a.c.c.a.b;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.FrameLayout;
import com.org.android.impl.cookie.SupportMenuItem;
import com.org.android.view.ActionProvider;
import com.org.v4.view.CollapsibleActionView;
import java.lang.reflect.Method;

public class MenuItemWrapperICS extends c<b> implements MenuItem {
    private Method mSetExclusiveCheckableMethod;

    class ActionProviderWrapper extends ActionProvider {
        final android.view.ActionProvider mInner;

        public ActionProviderWrapper(Context context, android.view.ActionProvider actionProvider) {
            super(context);
            this.mInner = actionProvider;
        }

        public boolean hasSubMenu() {
            return this.mInner.hasSubMenu();
        }

        public View onCreateActionView() {
            return this.mInner.onCreateActionView();
        }

        public boolean onPerformDefaultAction() {
            return this.mInner.onPerformDefaultAction();
        }

        /* JADX WARNING: type inference failed for: r1v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseMenuWrapper] */
        public void onPrepareSubMenu(SubMenu subMenu) {
            this.mInner.onPrepareSubMenu(MenuItemWrapperICS.this.getSubMenuWrapper(subMenu));
        }
    }

    class CollapsibleActionViewWrapper extends FrameLayout implements CollapsibleActionView {
        final android.view.CollapsibleActionView mWrappedView;

        CollapsibleActionViewWrapper(View view) {
            super(view.getContext());
            this.mWrappedView = (android.view.CollapsibleActionView) view;
            addView(view);
        }

        /* access modifiers changed from: package-private */
        public View getWrappedView() {
            return (View) this.mWrappedView;
        }

        public void onActionViewCollapsed() {
            this.mWrappedView.onActionViewCollapsed();
        }

        public void onActionViewExpanded() {
            this.mWrappedView.onActionViewExpanded();
        }
    }

    class OnActionExpandListenerWrapper extends d<MenuItem.OnActionExpandListener> implements MenuItem.OnActionExpandListener {
        OnActionExpandListenerWrapper(MenuItem.OnActionExpandListener onActionExpandListener) {
            super(onActionExpandListener);
        }

        /* JADX WARNING: type inference failed for: r3v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseMenuWrapper] */
        public boolean onMenuItemActionCollapse(MenuItem menuItem) {
            return this.mWrappedObject.onMenuItemActionCollapse(MenuItemWrapperICS.this.getMenuItemWrapper(menuItem));
        }

        /* JADX WARNING: type inference failed for: r3v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseMenuWrapper] */
        public boolean onMenuItemActionExpand(MenuItem menuItem) {
            return this.mWrappedObject.onMenuItemActionExpand(MenuItemWrapperICS.this.getMenuItemWrapper(menuItem));
        }
    }

    class OnMenuItemClickListenerWrapper extends d<MenuItem.OnMenuItemClickListener> implements MenuItem.OnMenuItemClickListener {
        OnMenuItemClickListenerWrapper(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
            super(onMenuItemClickListener);
        }

        /* JADX WARNING: type inference failed for: r3v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseMenuWrapper] */
        public boolean onMenuItemClick(MenuItem menuItem) {
            return this.mWrappedObject.onMenuItemClick(MenuItemWrapperICS.this.getMenuItemWrapper(menuItem));
        }
    }

    MenuItemWrapperICS(Context context, SupportMenuItem supportMenuItem) {
        super(context, supportMenuItem);
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public boolean collapseActionView() {
        return ((SupportMenuItem) this.mWrappedObject).collapseActionView();
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseMenuWrapper] */
    /* access modifiers changed from: package-private */
    public ActionProviderWrapper createActionProviderWrapper(android.view.ActionProvider actionProvider) {
        return new ActionProviderWrapper(this.mContext, actionProvider);
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public boolean expandActionView() {
        return ((SupportMenuItem) this.mWrappedObject).expandActionView();
    }

    /* JADX WARNING: type inference failed for: r9v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public android.view.ActionProvider getActionProvider() {
        ActionProvider $r1 = ((SupportMenuItem) this.mWrappedObject).getSupportActionProvider();
        if ($r1 instanceof ActionProviderWrapper) {
            return ((ActionProviderWrapper) $r1).mInner;
        }
        return null;
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public View getActionView() {
        View $r3 = ((SupportMenuItem) this.mWrappedObject).getActionView();
        return $r3 instanceof CollapsibleActionViewWrapper ? ((CollapsibleActionViewWrapper) $r3).getWrappedView() : $r3;
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public int getAlphabeticModifiers() {
        return ((SupportMenuItem) this.mWrappedObject).getAlphabeticModifiers();
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public char getAlphabeticShortcut() {
        return ((SupportMenuItem) this.mWrappedObject).getAlphabeticShortcut();
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public CharSequence getContentDescription() {
        return ((SupportMenuItem) this.mWrappedObject).getContentDescription();
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public int getGroupId() {
        return ((SupportMenuItem) this.mWrappedObject).getGroupId();
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public Drawable getIcon() {
        return ((SupportMenuItem) this.mWrappedObject).getIcon();
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public ColorStateList getIconTintList() {
        return ((SupportMenuItem) this.mWrappedObject).getIconTintList();
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public PorterDuff.Mode getIconTintMode() {
        return ((SupportMenuItem) this.mWrappedObject).getIconTintMode();
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public Intent getIntent() {
        return ((SupportMenuItem) this.mWrappedObject).getIntent();
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public int getItemId() {
        return ((SupportMenuItem) this.mWrappedObject).getItemId();
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return ((SupportMenuItem) this.mWrappedObject).getMenuInfo();
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public int getNumericModifiers() {
        return ((SupportMenuItem) this.mWrappedObject).getNumericModifiers();
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public char getNumericShortcut() {
        return ((SupportMenuItem) this.mWrappedObject).getNumericShortcut();
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public int getOrder() {
        return ((SupportMenuItem) this.mWrappedObject).getOrder();
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseMenuWrapper, android.support.v7.view.menu.BaseWrapper] */
    public SubMenu getSubMenu() {
        return getSubMenuWrapper(((SupportMenuItem) this.mWrappedObject).getSubMenu());
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public CharSequence getTitle() {
        return ((SupportMenuItem) this.mWrappedObject).getTitle();
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public CharSequence getTitleCondensed() {
        return ((SupportMenuItem) this.mWrappedObject).getTitleCondensed();
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public CharSequence getTooltipText() {
        return ((SupportMenuItem) this.mWrappedObject).getTooltipText();
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public boolean hasSubMenu() {
        return ((SupportMenuItem) this.mWrappedObject).hasSubMenu();
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public boolean isActionViewExpanded() {
        return ((SupportMenuItem) this.mWrappedObject).isActionViewExpanded();
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public boolean isCheckable() {
        return ((SupportMenuItem) this.mWrappedObject).isCheckable();
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public boolean isChecked() {
        return ((SupportMenuItem) this.mWrappedObject).isChecked();
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public boolean isEnabled() {
        return ((SupportMenuItem) this.mWrappedObject).isEnabled();
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public boolean isVisible() {
        return ((SupportMenuItem) this.mWrappedObject).isVisible();
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper, android.view.MenuItem] */
    public MenuItem setActionProvider(android.view.ActionProvider actionProvider) {
        ((SupportMenuItem) this.mWrappedObject).setSupportActionProvider(actionProvider != null ? createActionProviderWrapper(actionProvider) : null);
        return this;
    }

    /* JADX WARNING: type inference failed for: r8v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper, android.view.MenuItem] */
    public MenuItem setActionView(int i) {
        ((SupportMenuItem) this.mWrappedObject).setActionView(i);
        View $r2 = ((SupportMenuItem) this.mWrappedObject).getActionView();
        if ($r2 instanceof android.view.CollapsibleActionView) {
            ((SupportMenuItem) this.mWrappedObject).setActionView((View) new CollapsibleActionViewWrapper($r2));
        }
        return this;
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper, android.view.MenuItem] */
    public MenuItem setActionView(View $r1) {
        if ($r1 instanceof android.view.CollapsibleActionView) {
            $r1 = new CollapsibleActionViewWrapper($r1);
        }
        ((SupportMenuItem) this.mWrappedObject).setActionView($r1);
        return this;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper, android.view.MenuItem] */
    public MenuItem setAlphabeticShortcut(char c) {
        ((SupportMenuItem) this.mWrappedObject).setAlphabeticShortcut(c);
        return this;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper, android.view.MenuItem] */
    public MenuItem setAlphabeticShortcut(char c, int i) {
        ((SupportMenuItem) this.mWrappedObject).setAlphabeticShortcut(c, i);
        return this;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper, android.view.MenuItem] */
    public MenuItem setCheckable(boolean z) {
        ((SupportMenuItem) this.mWrappedObject).setCheckable(z);
        return this;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper, android.view.MenuItem] */
    public MenuItem setChecked(boolean z) {
        ((SupportMenuItem) this.mWrappedObject).setChecked(z);
        return this;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper, android.view.MenuItem] */
    public MenuItem setContentDescription(CharSequence charSequence) {
        ((SupportMenuItem) this.mWrappedObject).setContentDescription(charSequence);
        return this;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper, android.view.MenuItem] */
    public MenuItem setEnabled(boolean z) {
        ((SupportMenuItem) this.mWrappedObject).setEnabled(z);
        return this;
    }

    /* JADX WARNING: type inference failed for: r13v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public void setExclusiveCheckable(boolean z) {
        if (this.mSetExclusiveCheckableMethod == null) {
            try {
                this.mSetExclusiveCheckableMethod = ((SupportMenuItem) this.mWrappedObject).getClass().getDeclaredMethod("setExclusiveCheckable", new Class[]{Boolean.TYPE});
            } catch (Exception $r9) {
                Log.w("MenuItemWrapper", "Error while calling setExclusiveCheckable", $r9);
                return;
            }
        }
        Method $r3 = this.mSetExclusiveCheckableMethod;
        Object $r4 = this.mWrappedObject;
        Object[] $r7 = new Object[1];
        $r7[0] = Boolean.valueOf(z);
        $r3.invoke($r4, $r7);
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper, android.view.MenuItem] */
    public MenuItem setIcon(int i) {
        ((SupportMenuItem) this.mWrappedObject).setIcon(i);
        return this;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper, android.view.MenuItem] */
    public MenuItem setIcon(Drawable drawable) {
        ((SupportMenuItem) this.mWrappedObject).setIcon(drawable);
        return this;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper, android.view.MenuItem] */
    public MenuItem setIconTintList(ColorStateList colorStateList) {
        ((SupportMenuItem) this.mWrappedObject).setIconTintList(colorStateList);
        return this;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper, android.view.MenuItem] */
    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        ((SupportMenuItem) this.mWrappedObject).setIconTintMode(mode);
        return this;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper, android.view.MenuItem] */
    public MenuItem setIntent(Intent intent) {
        ((SupportMenuItem) this.mWrappedObject).setIntent(intent);
        return this;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper, android.view.MenuItem] */
    public MenuItem setNumericShortcut(char c) {
        ((SupportMenuItem) this.mWrappedObject).setNumericShortcut(c);
        return this;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper, android.view.MenuItem] */
    public MenuItem setNumericShortcut(char c, int i) {
        ((SupportMenuItem) this.mWrappedObject).setNumericShortcut(c, i);
        return this;
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper, android.view.MenuItem] */
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        ((SupportMenuItem) this.mWrappedObject).setOnActionExpandListener(onActionExpandListener != null ? new OnActionExpandListenerWrapper(onActionExpandListener) : null);
        return this;
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper, android.view.MenuItem] */
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        ((SupportMenuItem) this.mWrappedObject).setOnMenuItemClickListener(onMenuItemClickListener != null ? new OnMenuItemClickListenerWrapper(onMenuItemClickListener) : null);
        return this;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper, android.view.MenuItem] */
    public MenuItem setShortcut(char c, char c2) {
        ((SupportMenuItem) this.mWrappedObject).setShortcut(c, c2);
        return this;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper, android.view.MenuItem] */
    public MenuItem setShortcut(char c, char c2, int i, int i2) {
        ((SupportMenuItem) this.mWrappedObject).setShortcut(c, c2, i, i2);
        return this;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public void setShowAsAction(int i) {
        ((SupportMenuItem) this.mWrappedObject).setShowAsAction(i);
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper, android.view.MenuItem] */
    public MenuItem setShowAsActionFlags(int i) {
        ((SupportMenuItem) this.mWrappedObject).setShowAsActionFlags(i);
        return this;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper, android.view.MenuItem] */
    public MenuItem setTitle(int i) {
        ((SupportMenuItem) this.mWrappedObject).setTitle(i);
        return this;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper, android.view.MenuItem] */
    public MenuItem setTitle(CharSequence charSequence) {
        ((SupportMenuItem) this.mWrappedObject).setTitle(charSequence);
        return this;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper, android.view.MenuItem] */
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        ((SupportMenuItem) this.mWrappedObject).setTitleCondensed(charSequence);
        return this;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper, android.view.MenuItem] */
    public MenuItem setTooltipText(CharSequence charSequence) {
        ((SupportMenuItem) this.mWrappedObject).setTooltipText(charSequence);
        return this;
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuItemWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public MenuItem setVisible(boolean z) {
        return ((SupportMenuItem) this.mWrappedObject).setVisible(z);
    }
}
