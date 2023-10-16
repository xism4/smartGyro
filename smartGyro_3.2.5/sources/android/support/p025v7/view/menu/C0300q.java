package android.support.p025v7.view.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ActionProvider;
import android.view.CollapsibleActionView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.FrameLayout;
import java.lang.reflect.Method;
import p000a.p001a.p005c.p009c.p010a.C0057b;
import p000a.p001a.p005c.p014g.C0108e;
import p000a.p001a.p017d.p023d.C0166c;

/* renamed from: android.support.v7.view.menu.q */
public class C0300q extends C0282c<C0057b> implements MenuItem {

    /* renamed from: e */
    private Method f1057e;

    /* renamed from: android.support.v7.view.menu.q$a */
    class C0301a extends C0108e {

        /* renamed from: d */
        final ActionProvider f1058d;

        public C0301a(Context context, ActionProvider actionProvider) {
            super(context);
            this.f1058d = actionProvider;
        }

        /* renamed from: a */
        public void mo455a(SubMenu subMenu) {
            this.f1058d.onPrepareSubMenu(C0300q.this.mo1235a(subMenu));
        }

        /* renamed from: a */
        public boolean mo456a() {
            return this.f1058d.hasSubMenu();
        }

        /* renamed from: c */
        public View mo458c() {
            return this.f1058d.onCreateActionView();
        }

        /* renamed from: d */
        public boolean mo459d() {
            return this.f1058d.onPerformDefaultAction();
        }
    }

    /* renamed from: android.support.v7.view.menu.q$b */
    static class C0302b extends FrameLayout implements C0166c {

        /* renamed from: a */
        final CollapsibleActionView f1060a;

        C0302b(View view) {
            super(view.getContext());
            this.f1060a = (CollapsibleActionView) view;
            addView(view);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public View mo1445a() {
            return (View) this.f1060a;
        }

        public void onActionViewCollapsed() {
            this.f1060a.onActionViewCollapsed();
        }

        public void onActionViewExpanded() {
            this.f1060a.onActionViewExpanded();
        }
    }

    /* renamed from: android.support.v7.view.menu.q$c */
    private class C0303c extends C0283d<MenuItem.OnActionExpandListener> implements MenuItem.OnActionExpandListener {
        C0303c(MenuItem.OnActionExpandListener onActionExpandListener) {
            super(onActionExpandListener);
        }

        public boolean onMenuItemActionCollapse(MenuItem menuItem) {
            return ((MenuItem.OnActionExpandListener) this.f939a).onMenuItemActionCollapse(C0300q.this.mo1234a(menuItem));
        }

        public boolean onMenuItemActionExpand(MenuItem menuItem) {
            return ((MenuItem.OnActionExpandListener) this.f939a).onMenuItemActionExpand(C0300q.this.mo1234a(menuItem));
        }
    }

    /* renamed from: android.support.v7.view.menu.q$d */
    private class C0304d extends C0283d<MenuItem.OnMenuItemClickListener> implements MenuItem.OnMenuItemClickListener {
        C0304d(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
            super(onMenuItemClickListener);
        }

        public boolean onMenuItemClick(MenuItem menuItem) {
            return ((MenuItem.OnMenuItemClickListener) this.f939a).onMenuItemClick(C0300q.this.mo1234a(menuItem));
        }
    }

    C0300q(Context context, C0057b bVar) {
        super(context, bVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public C0301a mo1389a(ActionProvider actionProvider) {
        return new C0301a(this.f936b, actionProvider);
    }

    /* renamed from: a */
    public void mo1390a(boolean z) {
        try {
            if (this.f1057e == null) {
                this.f1057e = ((C0057b) this.f939a).getClass().getDeclaredMethod("setExclusiveCheckable", new Class[]{Boolean.TYPE});
            }
            this.f1057e.invoke(this.f939a, new Object[]{Boolean.valueOf(z)});
        } catch (Exception e) {
            Log.w("MenuItemWrapper", "Error while calling setExclusiveCheckable", e);
        }
    }

    public boolean collapseActionView() {
        return ((C0057b) this.f939a).collapseActionView();
    }

    public boolean expandActionView() {
        return ((C0057b) this.f939a).expandActionView();
    }

    public ActionProvider getActionProvider() {
        C0108e a = ((C0057b) this.f939a).mo195a();
        if (a instanceof C0301a) {
            return ((C0301a) a).f1058d;
        }
        return null;
    }

    public View getActionView() {
        View actionView = ((C0057b) this.f939a).getActionView();
        return actionView instanceof C0302b ? ((C0302b) actionView).mo1445a() : actionView;
    }

    public int getAlphabeticModifiers() {
        return ((C0057b) this.f939a).getAlphabeticModifiers();
    }

    public char getAlphabeticShortcut() {
        return ((C0057b) this.f939a).getAlphabeticShortcut();
    }

    public CharSequence getContentDescription() {
        return ((C0057b) this.f939a).getContentDescription();
    }

    public int getGroupId() {
        return ((C0057b) this.f939a).getGroupId();
    }

    public Drawable getIcon() {
        return ((C0057b) this.f939a).getIcon();
    }

    public ColorStateList getIconTintList() {
        return ((C0057b) this.f939a).getIconTintList();
    }

    public PorterDuff.Mode getIconTintMode() {
        return ((C0057b) this.f939a).getIconTintMode();
    }

    public Intent getIntent() {
        return ((C0057b) this.f939a).getIntent();
    }

    public int getItemId() {
        return ((C0057b) this.f939a).getItemId();
    }

    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return ((C0057b) this.f939a).getMenuInfo();
    }

    public int getNumericModifiers() {
        return ((C0057b) this.f939a).getNumericModifiers();
    }

    public char getNumericShortcut() {
        return ((C0057b) this.f939a).getNumericShortcut();
    }

    public int getOrder() {
        return ((C0057b) this.f939a).getOrder();
    }

    public SubMenu getSubMenu() {
        return mo1235a(((C0057b) this.f939a).getSubMenu());
    }

    public CharSequence getTitle() {
        return ((C0057b) this.f939a).getTitle();
    }

    public CharSequence getTitleCondensed() {
        return ((C0057b) this.f939a).getTitleCondensed();
    }

    public CharSequence getTooltipText() {
        return ((C0057b) this.f939a).getTooltipText();
    }

    public boolean hasSubMenu() {
        return ((C0057b) this.f939a).hasSubMenu();
    }

    public boolean isActionViewExpanded() {
        return ((C0057b) this.f939a).isActionViewExpanded();
    }

    public boolean isCheckable() {
        return ((C0057b) this.f939a).isCheckable();
    }

    public boolean isChecked() {
        return ((C0057b) this.f939a).isChecked();
    }

    public boolean isEnabled() {
        return ((C0057b) this.f939a).isEnabled();
    }

    public boolean isVisible() {
        return ((C0057b) this.f939a).isVisible();
    }

    public MenuItem setActionProvider(ActionProvider actionProvider) {
        ((C0057b) this.f939a).mo194a(actionProvider != null ? mo1389a(actionProvider) : null);
        return this;
    }

    public MenuItem setActionView(int i) {
        ((C0057b) this.f939a).setActionView(i);
        View actionView = ((C0057b) this.f939a).getActionView();
        if (actionView instanceof CollapsibleActionView) {
            ((C0057b) this.f939a).setActionView((View) new C0302b(actionView));
        }
        return this;
    }

    public MenuItem setActionView(View view) {
        if (view instanceof CollapsibleActionView) {
            view = new C0302b(view);
        }
        ((C0057b) this.f939a).setActionView(view);
        return this;
    }

    public MenuItem setAlphabeticShortcut(char c) {
        ((C0057b) this.f939a).setAlphabeticShortcut(c);
        return this;
    }

    public MenuItem setAlphabeticShortcut(char c, int i) {
        ((C0057b) this.f939a).setAlphabeticShortcut(c, i);
        return this;
    }

    public MenuItem setCheckable(boolean z) {
        ((C0057b) this.f939a).setCheckable(z);
        return this;
    }

    public MenuItem setChecked(boolean z) {
        ((C0057b) this.f939a).setChecked(z);
        return this;
    }

    public MenuItem setContentDescription(CharSequence charSequence) {
        ((C0057b) this.f939a).setContentDescription(charSequence);
        return this;
    }

    public MenuItem setEnabled(boolean z) {
        ((C0057b) this.f939a).setEnabled(z);
        return this;
    }

    public MenuItem setIcon(int i) {
        ((C0057b) this.f939a).setIcon(i);
        return this;
    }

    public MenuItem setIcon(Drawable drawable) {
        ((C0057b) this.f939a).setIcon(drawable);
        return this;
    }

    public MenuItem setIconTintList(ColorStateList colorStateList) {
        ((C0057b) this.f939a).setIconTintList(colorStateList);
        return this;
    }

    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        ((C0057b) this.f939a).setIconTintMode(mode);
        return this;
    }

    public MenuItem setIntent(Intent intent) {
        ((C0057b) this.f939a).setIntent(intent);
        return this;
    }

    public MenuItem setNumericShortcut(char c) {
        ((C0057b) this.f939a).setNumericShortcut(c);
        return this;
    }

    public MenuItem setNumericShortcut(char c, int i) {
        ((C0057b) this.f939a).setNumericShortcut(c, i);
        return this;
    }

    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        ((C0057b) this.f939a).setOnActionExpandListener(onActionExpandListener != null ? new C0303c(onActionExpandListener) : null);
        return this;
    }

    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        ((C0057b) this.f939a).setOnMenuItemClickListener(onMenuItemClickListener != null ? new C0304d(onMenuItemClickListener) : null);
        return this;
    }

    public MenuItem setShortcut(char c, char c2) {
        ((C0057b) this.f939a).setShortcut(c, c2);
        return this;
    }

    public MenuItem setShortcut(char c, char c2, int i, int i2) {
        ((C0057b) this.f939a).setShortcut(c, c2, i, i2);
        return this;
    }

    public void setShowAsAction(int i) {
        ((C0057b) this.f939a).setShowAsAction(i);
    }

    public MenuItem setShowAsActionFlags(int i) {
        ((C0057b) this.f939a).setShowAsActionFlags(i);
        return this;
    }

    public MenuItem setTitle(int i) {
        ((C0057b) this.f939a).setTitle(i);
        return this;
    }

    public MenuItem setTitle(CharSequence charSequence) {
        ((C0057b) this.f939a).setTitle(charSequence);
        return this;
    }

    public MenuItem setTitleCondensed(CharSequence charSequence) {
        ((C0057b) this.f939a).setTitleCondensed(charSequence);
        return this;
    }

    public MenuItem setTooltipText(CharSequence charSequence) {
        ((C0057b) this.f939a).setTooltipText(charSequence);
        return this;
    }

    public MenuItem setVisible(boolean z) {
        return ((C0057b) this.f939a).setVisible(z);
    }
}
