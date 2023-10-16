package android.support.v7.view.menu;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.view.menu.MenuView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import com.org.android.impl.cookie.SupportMenuItem;
import com.org.android.view.ActionProvider;
import com.org.v4.util.R$string;

public final class MenuItemImpl implements SupportMenuItem {
    private boolean c = false;
    private CharSequence currentName;
    private int k = 4096;
    private int l = 4096;
    private ActionProvider mActionProvider;
    private View mActionView;
    private ColorStateList mButtonTintList = null;
    private PorterDuff.Mode mButtonTintMode = null;
    private final int mCategoryOrder;
    private MenuItem.OnMenuItemClickListener mClickListener;
    private CharSequence mContentDesc;
    private int mFlags = 16;
    private final int mGroup;
    private boolean mHasButtonTint = false;
    private boolean mHasButtonTintMode = false;
    private Drawable mIconDrawable;
    private int mIconResId = 0;
    private final int mId;
    private Intent mIntent;
    private boolean mIsActionViewExpanded = false;
    private Runnable mItemCallback;
    MenuBuilder mMenu;
    private ContextMenu.ContextMenuInfo mMenuInfo;
    private MenuItem.OnActionExpandListener mOnActionExpandListener;
    private final int mOrdering;
    private char mShortcutAlphabeticChar;
    private char mShortcutNumericChar;
    private int mShowAsAction = 0;
    private SubMenuBuilder mSubMenu;
    private CharSequence mTitle;
    private CharSequence mTitleCondensed;

    MenuItemImpl(MenuBuilder menuBuilder, int i, int i2, int i3, int i4, CharSequence charSequence, int i5) {
        this.mMenu = menuBuilder;
        this.mId = i2;
        this.mGroup = i;
        this.mCategoryOrder = i3;
        this.mOrdering = i4;
        this.mTitle = charSequence;
        this.mShowAsAction = i5;
    }

    private static void add(StringBuilder sb, int i, int i2, String str) {
        if ((i & i2) == i2) {
            sb.append(str);
        }
    }

    private Drawable getDrawable(Drawable $r1) {
        if ($r1 != null && this.c && (this.mHasButtonTint || this.mHasButtonTintMode)) {
            $r1 = DrawableCompat.wrap($r1).mutate();
            if (this.mHasButtonTint) {
                DrawableCompat.setTintList($r1, this.mButtonTintList);
            }
            if (this.mHasButtonTintMode) {
                DrawableCompat.setTintMode($r1, this.mButtonTintMode);
            }
            this.c = false;
        }
        return $r1;
    }

    /* access modifiers changed from: package-private */
    public String a() {
        int $i1;
        char $c0 = getShortcut();
        if ($c0 == 0) {
            return "";
        }
        Resources $r4 = this.mMenu.getContext().getResources();
        StringBuilder $r1 = new StringBuilder();
        if (ViewConfiguration.get(this.mMenu.getContext()).hasPermanentMenuKey()) {
            $r1.append($r4.getString(R$string.abc_prepend_shortcut_label));
        }
        int $i12 = this.mMenu.isQwertyMode() ? this.l : this.k;
        add($r1, $i12, 65536, $r4.getString(R$string.abc_menu_meta_shortcut_label));
        add($r1, $i12, 4096, $r4.getString(R$string.abc_menu_ctrl_shortcut_label));
        add($r1, $i12, 2, $r4.getString(R$string.abc_menu_alt_shortcut_label));
        add($r1, $i12, 1, $r4.getString(R$string.abc_menu_shift_shortcut_label));
        add($r1, $i12, 4, $r4.getString(R$string.abc_menu_sym_shortcut_label));
        add($r1, $i12, 8, $r4.getString(R$string.abc_menu_function_shortcut_label));
        if ($c0 == 8) {
            $i1 = R$string.abc_menu_delete_shortcut_label;
        } else if ($c0 == 10) {
            $i1 = R$string.abc_menu_enter_shortcut_label;
        } else if ($c0 != ' ') {
            $r1.append($c0);
            return $r1.toString();
        } else {
            $i1 = R$string.abc_menu_space_shortcut_label;
        }
        $r1.append($r4.getString($i1));
        return $r1.toString();
    }

    public void actionFormatChanged() {
        this.mMenu.onItemActionRequestChanged(this);
    }

    public boolean collapseActionView() {
        if ((this.mShowAsAction & 8) == 0) {
            return false;
        }
        if (this.mActionView == null) {
            return true;
        }
        MenuItem.OnActionExpandListener $r2 = this.mOnActionExpandListener;
        if ($r2 == null || $r2.onMenuItemActionCollapse(this)) {
            return this.mMenu.collapseItemActionView(this);
        }
        return false;
    }

    public boolean expandActionView() {
        if (!hasCollapsibleActionView()) {
            return false;
        }
        MenuItem.OnActionExpandListener $r1 = this.mOnActionExpandListener;
        if ($r1 == null || $r1.onMenuItemActionExpand(this)) {
            return this.mMenu.expandItemActionView(this);
        }
        return false;
    }

    public android.view.ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }

    public View getActionView() {
        View $r1 = this.mActionView;
        if ($r1 != null) {
            return $r1;
        }
        ActionProvider $r2 = this.mActionProvider;
        if ($r2 == null) {
            return null;
        }
        this.mActionView = $r2.onCreateActionView(this);
        return this.mActionView;
    }

    public int getAlphabeticModifiers() {
        return this.l;
    }

    public char getAlphabeticShortcut() {
        return this.mShortcutAlphabeticChar;
    }

    public CharSequence getContentDescription() {
        return this.mContentDesc;
    }

    public int getGroupId() {
        return this.mGroup;
    }

    public Drawable getIcon() {
        Drawable $r1 = this.mIconDrawable;
        if ($r1 != null) {
            return getDrawable($r1);
        }
        if (this.mIconResId == 0) {
            return null;
        }
        Drawable $r12 = com.org.v4.text.view.Resources.getDrawable(this.mMenu.getContext(), this.mIconResId);
        this.mIconResId = 0;
        this.mIconDrawable = $r12;
        return getDrawable($r12);
    }

    public ColorStateList getIconTintList() {
        return this.mButtonTintList;
    }

    public PorterDuff.Mode getIconTintMode() {
        return this.mButtonTintMode;
    }

    public Intent getIntent() {
        return this.mIntent;
    }

    public int getItemId() {
        return this.mId;
    }

    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return this.mMenuInfo;
    }

    public int getNumericModifiers() {
        return this.k;
    }

    public char getNumericShortcut() {
        return this.mShortcutNumericChar;
    }

    public int getOrder() {
        return this.mCategoryOrder;
    }

    public int getOrdering() {
        return this.mOrdering;
    }

    /* access modifiers changed from: package-private */
    public char getShortcut() {
        return this.mMenu.isQwertyMode() ? this.mShortcutAlphabeticChar : this.mShortcutNumericChar;
    }

    public SubMenu getSubMenu() {
        return this.mSubMenu;
    }

    public ActionProvider getSupportActionProvider() {
        return this.mActionProvider;
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    public CharSequence getTitleCondensed() {
        CharSequence $r2 = this.mTitleCondensed;
        if ($r2 == null) {
            $r2 = this.mTitle;
        }
        return (Build.VERSION.SDK_INT >= 18 || $r2 == null || ($r2 instanceof String)) ? $r2 : $r2.toString();
    }

    /* access modifiers changed from: package-private */
    public CharSequence getTitleForItemView(MenuView.ItemView itemView) {
        return (itemView == null || !itemView.prefersCondensedTitle()) ? getTitle() : getTitleCondensed();
    }

    public CharSequence getTooltipText() {
        return this.currentName;
    }

    public boolean hasCollapsibleActionView() {
        ActionProvider $r2;
        if ((this.mShowAsAction & 8) == 0) {
            return false;
        }
        if (this.mActionView == null && ($r2 = this.mActionProvider) != null) {
            this.mActionView = $r2.onCreateActionView(this);
        }
        return this.mActionView != null;
    }

    public boolean hasSubMenu() {
        return this.mSubMenu != null;
    }

    public boolean invoke() {
        MenuItem.OnMenuItemClickListener $r1 = this.mClickListener;
        if ($r1 != null && $r1.onMenuItemClick(this)) {
            return true;
        }
        MenuBuilder $r2 = this.mMenu;
        if ($r2.dispatchMenuItemSelected($r2, this)) {
            return true;
        }
        Runnable $r3 = this.mItemCallback;
        if ($r3 != null) {
            $r3.run();
            return true;
        }
        if (this.mIntent != null) {
            try {
                this.mMenu.getContext().startActivity(this.mIntent);
                return true;
            } catch (ActivityNotFoundException $r6) {
                Log.e("MenuItemImpl", "Can't find activity to handle intent; ignoring", $r6);
            }
        }
        ActionProvider $r7 = this.mActionProvider;
        return $r7 != null && $r7.onPerformDefaultAction();
    }

    public boolean isActionButton() {
        return (this.mFlags & 32) == 32;
    }

    public boolean isActionViewExpanded() {
        return this.mIsActionViewExpanded;
    }

    public boolean isCheckable() {
        return (this.mFlags & 1) == 1;
    }

    public boolean isChecked() {
        return (this.mFlags & 2) == 2;
    }

    public boolean isEnabled() {
        return (this.mFlags & 16) != 0;
    }

    public boolean isExclusiveCheckable() {
        return (this.mFlags & 4) != 0;
    }

    public boolean isVisible() {
        ActionProvider $r1 = this.mActionProvider;
        return ($r1 == null || !$r1.overridesItemVisibility()) ? (this.mFlags & 8) == 0 : (this.mFlags & 8) == 0 && this.mActionProvider.isVisible();
    }

    public boolean requestsActionButton() {
        return (this.mShowAsAction & 1) == 1;
    }

    public boolean requiresActionButton() {
        return (this.mShowAsAction & 2) == 2;
    }

    public MenuItem setActionProvider(android.view.ActionProvider actionProvider) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }

    public SupportMenuItem setActionView(int i) {
        Context $r3 = this.mMenu.getContext();
        setActionView(LayoutInflater.from($r3).inflate(i, new LinearLayout($r3), false));
        return this;
    }

    public SupportMenuItem setActionView(View view) {
        int $i0;
        this.mActionView = view;
        this.mActionProvider = null;
        if (view != null && view.getId() == -1 && ($i0 = this.mId) > 0) {
            view.setId($i0);
        }
        this.mMenu.onItemActionRequestChanged(this);
        return this;
    }

    public void setActionViewExpanded(boolean z) {
        this.mIsActionViewExpanded = z;
        this.mMenu.onItemsChanged(false);
    }

    public MenuItem setAlphabeticShortcut(char c2) {
        if (this.mShortcutAlphabeticChar == c2) {
            return this;
        }
        this.mShortcutAlphabeticChar = Character.toLowerCase(c2);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setAlphabeticShortcut(char c2, int i) {
        if (this.mShortcutAlphabeticChar == c2 && this.l == i) {
            return this;
        }
        this.mShortcutAlphabeticChar = Character.toLowerCase(c2);
        this.l = KeyEvent.normalizeMetaState(i);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setCheckable(boolean i2) {
        int $i1 = this.mFlags;
        this.mFlags = (int) (i2 | ($i1 & -2));
        if ($i1 != this.mFlags) {
            this.mMenu.onItemsChanged(false);
        }
        return this;
    }

    public MenuItem setChecked(boolean z) {
        if ((this.mFlags & 4) != 0) {
            this.mMenu.setExclusiveItemChecked(this);
            return this;
        }
        setCheckedInt(z);
        return this;
    }

    /* access modifiers changed from: package-private */
    public void setCheckedInt(boolean z) {
        int $i0 = this.mFlags;
        this.mFlags = (z ? (byte) 2 : 0) | ($i0 & -3);
        if ($i0 != this.mFlags) {
            this.mMenu.onItemsChanged(false);
        }
    }

    public SupportMenuItem setContentDescription(CharSequence charSequence) {
        this.mContentDesc = charSequence;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setEnabled(boolean z) {
        this.mFlags = z ? this.mFlags | 16 : this.mFlags & -17;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public void setExclusiveCheckable(boolean z) {
        this.mFlags = (z ? (byte) 4 : 0) | (this.mFlags & -5);
    }

    public MenuItem setIcon(int i) {
        this.mIconDrawable = null;
        this.mIconResId = i;
        this.c = true;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setIcon(Drawable drawable) {
        this.mIconResId = 0;
        this.mIconDrawable = drawable;
        this.c = true;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.mButtonTintList = colorStateList;
        this.mHasButtonTint = true;
        this.c = true;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.mButtonTintMode = mode;
        this.mHasButtonTintMode = true;
        this.c = true;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setIntent(Intent intent) {
        this.mIntent = intent;
        return this;
    }

    public void setIsActionButton(boolean z) {
        this.mFlags = z ? this.mFlags | 32 : this.mFlags & -33;
    }

    /* access modifiers changed from: package-private */
    public void setMenuInfo(ContextMenu.ContextMenuInfo contextMenuInfo) {
        this.mMenuInfo = contextMenuInfo;
    }

    public MenuItem setNumericShortcut(char c2) {
        if (this.mShortcutNumericChar == c2) {
            return this;
        }
        this.mShortcutNumericChar = c2;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setNumericShortcut(char c2, int i) {
        if (this.mShortcutNumericChar == c2 && this.k == i) {
            return this;
        }
        this.mShortcutNumericChar = c2;
        this.k = KeyEvent.normalizeMetaState(i);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        this.mOnActionExpandListener = onActionExpandListener;
        return this;
    }

    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.mClickListener = onMenuItemClickListener;
        return this;
    }

    public MenuItem setShortcut(char c2, char c3) {
        this.mShortcutNumericChar = c2;
        this.mShortcutAlphabeticChar = Character.toLowerCase(c3);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setShortcut(char c2, char c3, int i, int i2) {
        this.mShortcutNumericChar = c2;
        this.k = KeyEvent.normalizeMetaState(i);
        this.mShortcutAlphabeticChar = Character.toLowerCase(c3);
        this.l = KeyEvent.normalizeMetaState(i2);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public void setShowAsAction(int i) {
        int $i1 = i & 3;
        if ($i1 == 0 || $i1 == 1 || $i1 == 2) {
            this.mShowAsAction = i;
            this.mMenu.onItemActionRequestChanged(this);
            return;
        }
        throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
    }

    public SupportMenuItem setShowAsActionFlags(int i) {
        setShowAsAction(i);
        return this;
    }

    public void setSubMenu(SubMenuBuilder subMenuBuilder) {
        this.mSubMenu = subMenuBuilder;
        subMenuBuilder.setHeaderTitle(getTitle());
    }

    public SupportMenuItem setSupportActionProvider(ActionProvider actionProvider) {
        ActionProvider $r2 = this.mActionProvider;
        if ($r2 != null) {
            $r2.reset();
        }
        this.mActionView = null;
        this.mActionProvider = actionProvider;
        this.mMenu.onItemsChanged(true);
        ActionProvider $r1 = this.mActionProvider;
        if ($r1 != null) {
            $r1.setVisibilityListener(new ActionProvider.VisibilityListener() {
                public void onActionProviderVisibilityChanged(boolean z) {
                    MenuItemImpl $r2 = MenuItemImpl.this;
                    $r2.mMenu.onItemVisibleChanged($r2);
                }
            });
        }
        return this;
    }

    public MenuItem setTitle(int i) {
        setTitle((CharSequence) this.mMenu.getContext().getString(i));
        return this;
    }

    public MenuItem setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        this.mMenu.onItemsChanged(false);
        SubMenuBuilder $r3 = this.mSubMenu;
        if ($r3 != null) {
            $r3.setHeaderTitle(charSequence);
        }
        return this;
    }

    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.mTitleCondensed = charSequence;
        if (charSequence == null) {
        }
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public SupportMenuItem setTooltipText(CharSequence charSequence) {
        this.currentName = charSequence;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setVisible(boolean z) {
        if (setVisibleInt(z)) {
            this.mMenu.onItemVisibleChanged(this);
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean setVisibleInt(boolean z) {
        int $i0 = this.mFlags;
        this.mFlags = (z ? (byte) 0 : 8) | ($i0 & -9);
        return $i0 != this.mFlags;
    }

    public boolean shouldShowIcon() {
        return this.mMenu.getOptionalIconsVisible();
    }

    /* access modifiers changed from: package-private */
    public boolean shouldShowShortcut() {
        return this.mMenu.isShortcutsVisible() && getShortcut() != 0;
    }

    public boolean showsTextAsAction() {
        return (this.mShowAsAction & 4) == 4;
    }

    public String toString() {
        CharSequence $r2 = this.mTitle;
        if ($r2 != null) {
            return $r2.toString();
        }
        return null;
    }
}
