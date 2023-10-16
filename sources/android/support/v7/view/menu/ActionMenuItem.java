package android.support.v7.view.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import com.org.android.impl.cookie.SupportMenuItem;
import com.org.android.ui.Resources;

public class ActionMenuItem implements SupportMenuItem {
    private CharSequence currentName;
    private int i = 4096;
    private int k = 4096;
    private ColorStateList mButtonTintList = null;
    private PorterDuff.Mode mButtonTintMode = null;
    private final int mCategoryOrder;
    private MenuItem.OnMenuItemClickListener mClickListener;
    private CharSequence mContentDesc;
    private Context mContext;
    private Drawable mDrawable;
    private int mFlags = 16;
    private final int mGroup;
    private boolean mHasButtonTint = false;
    private boolean mHasButtonTintMode = false;
    private int mIconResId = 0;
    private final int mId;
    private Intent mIntent;
    private final int mOrdering;
    private char mShortcutAlphabeticChar;
    private char mShortcutNumericChar;
    private CharSequence mTitle;
    private CharSequence mTitleCondensed;

    public ActionMenuItem(Context context, int i2, int i3, int i4, int i5, CharSequence charSequence) {
        this.mContext = context;
        this.mId = i3;
        this.mGroup = i2;
        this.mCategoryOrder = i4;
        this.mOrdering = i5;
        this.mTitle = charSequence;
    }

    private void setIcon() {
        if (this.mDrawable == null) {
            return;
        }
        if (this.mHasButtonTint || this.mHasButtonTintMode) {
            this.mDrawable = DrawableCompat.wrap(this.mDrawable);
            this.mDrawable = this.mDrawable.mutate();
            if (this.mHasButtonTint) {
                DrawableCompat.setTintList(this.mDrawable, this.mButtonTintList);
            }
            if (this.mHasButtonTintMode) {
                DrawableCompat.setTintMode(this.mDrawable, this.mButtonTintMode);
            }
        }
    }

    public boolean collapseActionView() {
        return false;
    }

    public boolean expandActionView() {
        return false;
    }

    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }

    public View getActionView() {
        return null;
    }

    public int getAlphabeticModifiers() {
        return this.i;
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
        return this.mDrawable;
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
        return null;
    }

    public int getNumericModifiers() {
        return this.k;
    }

    public char getNumericShortcut() {
        return this.mShortcutNumericChar;
    }

    public int getOrder() {
        return this.mOrdering;
    }

    public SubMenu getSubMenu() {
        return null;
    }

    public com.org.android.view.ActionProvider getSupportActionProvider() {
        return null;
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    public CharSequence getTitleCondensed() {
        CharSequence $r1 = this.mTitleCondensed;
        return $r1 != null ? $r1 : this.mTitle;
    }

    public CharSequence getTooltipText() {
        return this.currentName;
    }

    public boolean hasSubMenu() {
        return false;
    }

    public boolean isActionViewExpanded() {
        return false;
    }

    public boolean isCheckable() {
        return (this.mFlags & 1) != 0;
    }

    public boolean isChecked() {
        return (this.mFlags & 2) != 0;
    }

    public boolean isEnabled() {
        return (this.mFlags & 16) != 0;
    }

    public boolean isVisible() {
        return (this.mFlags & 8) == 0;
    }

    public MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    public SupportMenuItem setActionView(int i2) {
        throw new UnsupportedOperationException();
    }

    public SupportMenuItem setActionView(View view) {
        throw new UnsupportedOperationException();
    }

    public MenuItem setAlphabeticShortcut(char c) {
        this.mShortcutAlphabeticChar = Character.toLowerCase(c);
        return this;
    }

    public MenuItem setAlphabeticShortcut(char c, int i2) {
        this.mShortcutAlphabeticChar = Character.toLowerCase(c);
        this.i = KeyEvent.normalizeMetaState(i2);
        return this;
    }

    public MenuItem setCheckable(boolean i1) {
        this.mFlags = (int) (i1 | (this.mFlags & -2));
        return this;
    }

    public MenuItem setChecked(boolean z) {
        this.mFlags = (z ? (byte) 2 : 0) | (this.mFlags & -3);
        return this;
    }

    public SupportMenuItem setContentDescription(CharSequence charSequence) {
        this.mContentDesc = charSequence;
        return this;
    }

    public MenuItem setEnabled(boolean z) {
        this.mFlags = (z ? (byte) 16 : 0) | (this.mFlags & -17);
        return this;
    }

    public MenuItem setIcon(int i2) {
        this.mIconResId = i2;
        this.mDrawable = Resources.getDrawable(this.mContext, i2);
        setIcon();
        return this;
    }

    public MenuItem setIcon(Drawable drawable) {
        this.mDrawable = drawable;
        this.mIconResId = 0;
        setIcon();
        return this;
    }

    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.mButtonTintList = colorStateList;
        this.mHasButtonTint = true;
        setIcon();
        return this;
    }

    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.mButtonTintMode = mode;
        this.mHasButtonTintMode = true;
        setIcon();
        return this;
    }

    public MenuItem setIntent(Intent intent) {
        this.mIntent = intent;
        return this;
    }

    public MenuItem setNumericShortcut(char c) {
        this.mShortcutNumericChar = c;
        return this;
    }

    public MenuItem setNumericShortcut(char c, int i2) {
        this.mShortcutNumericChar = c;
        this.k = KeyEvent.normalizeMetaState(i2);
        return this;
    }

    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException();
    }

    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.mClickListener = onMenuItemClickListener;
        return this;
    }

    public MenuItem setShortcut(char c, char c2) {
        this.mShortcutNumericChar = c;
        this.mShortcutAlphabeticChar = Character.toLowerCase(c2);
        return this;
    }

    public MenuItem setShortcut(char c, char c2, int i2, int i3) {
        this.mShortcutNumericChar = c;
        this.k = KeyEvent.normalizeMetaState(i2);
        this.mShortcutAlphabeticChar = Character.toLowerCase(c2);
        this.i = KeyEvent.normalizeMetaState(i3);
        return this;
    }

    public void setShowAsAction(int i2) {
    }

    public SupportMenuItem setShowAsActionFlags(int i2) {
        setShowAsAction(i2);
        return this;
    }

    public SupportMenuItem setSupportActionProvider(com.org.android.view.ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    public MenuItem setTitle(int i2) {
        this.mTitle = this.mContext.getResources().getString(i2);
        return this;
    }

    public MenuItem setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        return this;
    }

    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.mTitleCondensed = charSequence;
        return this;
    }

    public SupportMenuItem setTooltipText(CharSequence charSequence) {
        this.currentName = charSequence;
        return this;
    }

    public MenuItem setVisible(boolean z) {
        byte $b1 = 8;
        byte $i0 = this.mFlags & 8;
        if (z) {
            $b1 = 0;
        }
        this.mFlags = $i0 | $b1;
        return this;
    }
}
