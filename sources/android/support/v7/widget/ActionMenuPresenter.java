package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.ListPopupWindow;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.SubMenuBuilder;
import android.support.v7.view.menu.b;
import android.support.v7.view.menu.l$a;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.ListPopupWindow;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.org.android.view.ActionProvider;
import com.org.v4.util.R$attr;
import com.org.v4.util.R$layout;
import com.org.v4.view.ActionBarPolicy;
import java.util.ArrayList;

class ActionMenuPresenter extends b implements ActionProvider.SubUiVisibilityListener {
    XYPlot a;
    int e;
    private final SparseBooleanArray mActionButtonGroups = new SparseBooleanArray();
    private int mActionItemWidthLimit;
    private boolean mExpandedActionViewsExclusive;
    private int mMaxItems;
    private boolean mMaxItemsSet;
    private int mMinCellSize;
    OverflowMenuButton mOverflowButton;
    i mOverflowPopup;
    private Drawable mPendingOverflowIcon;
    private boolean mPendingOverflowIconSet;
    private ActionMenuPopupCallback mPopupCallback;
    final PopupPresenterCallback mPopupPresenterCallback = new PopupPresenterCallback();
    OpenOverflowRunnable mPostedOpenRunnable;
    private boolean mReserveOverflow;
    private boolean mReserveOverflowSet;
    private View mScrapActionButtonView;
    private boolean mStrictWidthLimit;
    private int mWidthLimit;
    private boolean mWidthLimitSet;

    class ActionMenuPopupCallback extends ActionMenuItemView.b {
        ActionMenuPopupCallback() {
        }

        public ListPopupWindow getPopup() {
            XYPlot $r3 = ActionMenuPresenter.this.a;
            if ($r3 != null) {
                return $r3.a();
            }
            return null;
        }
    }

    class OpenOverflowRunnable implements Runnable {
        private i mPopup;

        public OpenOverflowRunnable(i iVar) {
            this.mPopup = iVar;
        }

        public void run() {
            if (ActionMenuPresenter.this.mMenu != null) {
                ActionMenuPresenter.this.mMenu.changeMenuMode();
            }
            View $r4 = (View) ActionMenuPresenter.this.mMenuView;
            if (!($r4 == null || $r4.getWindowToken() == null || !this.mPopup.c())) {
                ActionMenuPresenter.this.mOverflowPopup = this.mPopup;
            }
            ActionMenuPresenter.this.mPostedOpenRunnable = null;
        }
    }

    class OverflowMenuButton extends AppCompatImageView implements ActionMenuView.a {
        private final float[] mTempPts = new float[2];

        public OverflowMenuButton(Context context) {
            super(context, (AttributeSet) null, R$attr.actionOverflowButtonStyle);
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
            MenuItemImpl.add(this, getContentDescription());
            setOnTouchListener(new ListPopupWindow.ForwardingListener(this, ActionMenuPresenter.this) {
                public android.support.v7.view.menu.ListPopupWindow getPopup() {
                    i $r4 = ActionMenuPresenter.this.mOverflowPopup;
                    if ($r4 == null) {
                        return null;
                    }
                    return $r4.a();
                }

                public boolean onForwardingStarted() {
                    ActionMenuPresenter.this.showOverflowMenu();
                    return true;
                }

                public boolean onForwardingStopped() {
                    ActionMenuPresenter $r3 = ActionMenuPresenter.this;
                    if ($r3.mPostedOpenRunnable != null) {
                        return false;
                    }
                    $r3.hideOverflowMenu();
                    return true;
                }
            });
        }

        public boolean needsDividerAfter() {
            return false;
        }

        public boolean needsDividerBefore() {
            return false;
        }

        public boolean performClick() {
            if (super.performClick()) {
                return true;
            }
            playSoundEffect(0);
            ActionMenuPresenter.this.showOverflowMenu();
            return true;
        }

        /* access modifiers changed from: protected */
        public boolean setFrame(int i, int i2, int i3, int i4) {
            boolean $z0 = super.setFrame(i, i2, i3, i4);
            Drawable $r1 = getDrawable();
            Drawable $r2 = getBackground();
            if (!($r1 == null || $r2 == null)) {
                int $i2 = getWidth();
                int $i1 = getHeight();
                int $i0 = Math.max($i2, $i1) / 2;
                int $i22 = ($i2 + (getPaddingLeft() - getPaddingRight())) / 2;
                int $i4 = ($i1 + (getPaddingTop() - getPaddingBottom())) / 2;
                DrawableCompat.setHotspotBounds($r2, $i22 - $i0, $i4 - $i0, $i22 + $i0, $i4 + $i0);
            }
            return $z0;
        }
    }

    class PopupPresenterCallback implements l$a {
        PopupPresenterCallback() {
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
            if (menuBuilder instanceof SubMenuBuilder) {
                menuBuilder.getRootMenu().close(false);
            }
            l$a $r4 = ActionMenuPresenter.this.getCallback();
            if ($r4 != null) {
                $r4.onCloseMenu(menuBuilder, z);
            }
        }

        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            if (menuBuilder == null) {
                return false;
            }
            ActionMenuPresenter.this.e = ((SubMenuBuilder) menuBuilder).getItem().getItemId();
            l$a $r5 = ActionMenuPresenter.this.getCallback();
            if ($r5 != null) {
                return $r5.onOpenSubMenu(menuBuilder);
            }
            return false;
        }
    }

    public ActionMenuPresenter(Context context) {
        super(context, R$layout.abc_action_menu_layout, R$layout.abc_action_menu_item_layout);
    }

    private View findViewForItem(MenuItem menuItem) {
        ViewGroup $r3 = (ViewGroup) this.mMenuView;
        if ($r3 == null) {
            return null;
        }
        int $i0 = $r3.getChildCount();
        for (int $i1 = 0; $i1 < $i0; $i1++) {
            View $r4 = $r3.getChildAt($i1);
            if (($r4 instanceof MenuView.ItemView) && ((MenuView.ItemView) $r4).getItemData() == menuItem) {
                return $r4;
            }
        }
        return null;
    }

    public void a(MenuBuilder menuBuilder, boolean z) {
        dismissPopupMenus();
        super.a(menuBuilder, z);
    }

    /* JADX WARNING: type inference failed for: r4v1, types: [android.view.Menu] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(android.support.v7.view.menu.SubMenuBuilder r15) {
        /*
            r14 = this;
            boolean r0 = r15.hasVisibleItems()
            r1 = 0
            if (r0 != 0) goto L_0x0009
            r2 = 0
            return r2
        L_0x0009:
            r3 = r15
        L_0x000a:
            android.view.Menu r4 = r3.getParentMenu()
            android.support.v7.view.menu.MenuBuilder r5 = r14.mMenu
            if (r4 == r5) goto L_0x001b
            android.view.Menu r4 = r3.getParentMenu()
            r6 = r4
            android.support.v7.view.menu.SubMenuBuilder r6 = (android.support.v7.view.menu.SubMenuBuilder) r6
            r3 = r6
            goto L_0x000a
        L_0x001b:
            android.view.MenuItem r7 = r3.getItem()
            android.view.View r8 = r14.findViewForItem(r7)
            if (r8 != 0) goto L_0x0027
            r2 = 0
            return r2
        L_0x0027:
            android.view.MenuItem r7 = r15.getItem()
            int r9 = r7.getItemId()
            r14.e = r9
            int r9 = r15.size()
            r10 = 0
        L_0x0036:
            if (r10 >= r9) goto L_0x004d
            android.view.MenuItem r7 = r15.getItem(r10)
            boolean r0 = r7.isVisible()
            if (r0 == 0) goto L_0x004a
            android.graphics.drawable.Drawable r11 = r7.getIcon()
            if (r11 == 0) goto L_0x004a
            r1 = 1
            goto L_0x004d
        L_0x004a:
            int r10 = r10 + 1
            goto L_0x0036
        L_0x004d:
            android.support.v7.widget.XYPlot r12 = new android.support.v7.widget.XYPlot
            android.content.Context r13 = r14.mContext
            r12.<init>(r14, r13, r15, r8)
            r14.a = r12
            android.support.v7.widget.XYPlot r12 = r14.a
            r12.a((boolean) r1)
            android.support.v7.widget.XYPlot r12 = r14.a
            r12.setTitle()
            super.a((android.support.v7.view.menu.SubMenuBuilder) r15)
            r2 = 1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ActionMenuPresenter.a(android.support.v7.view.menu.SubMenuBuilder):boolean");
    }

    public void bindItemView(MenuItemImpl menuItemImpl, MenuView.ItemView itemView) {
        itemView.initialize(menuItemImpl, 0);
        ActionMenuItemView $r5 = (ActionMenuItemView) itemView;
        $r5.setItemInvoker((ActionMenuView) this.mMenuView);
        if (this.mPopupCallback == null) {
            this.mPopupCallback = new ActionMenuPopupCallback();
        }
        $r5.setPopupCallback(this.mPopupCallback);
    }

    public boolean dismissPopupMenus() {
        return hideOverflowMenu() | hideSubMenus();
    }

    public boolean filterLeftoverView(ViewGroup viewGroup, int i) {
        if (viewGroup.getChildAt(i) == this.mOverflowButton) {
            return false;
        }
        return super.filterLeftoverView(viewGroup, i);
    }

    public boolean flagActionItems() {
        int $i1;
        ArrayList $r4;
        int $i5;
        int $i4;
        ActionMenuPresenter actionMenuPresenter = this;
        MenuBuilder $r2 = this.mMenu;
        if ($r2 != null) {
            ArrayList $r3 = $r2.getVisibleItems();
            $r4 = $r3;
            $i1 = $r3.size();
        } else {
            $r4 = null;
            $i1 = 0;
        }
        int $i2 = this.mMaxItems;
        int $i3 = this.mActionItemWidthLimit;
        int $i0 = View.MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup $r6 = (ViewGroup) this.mMenuView;
        boolean z = false;
        int $i52 = 0;
        int $i6 = 0;
        for (int $i42 = 0; $i42 < $i1; $i42++) {
            MenuItemImpl $r8 = (MenuItemImpl) $r4.get($i42);
            if ($r8.requiresActionButton()) {
                $i52++;
            } else if ($r8.requestsActionButton()) {
                $i6++;
            } else {
                z = true;
            }
            if (this.mExpandedActionViewsExclusive && $r8.isActionViewExpanded()) {
                $i2 = 0;
            }
        }
        if (this.mReserveOverflow && (z || $i6 + $i52 > $i2)) {
            $i2--;
        }
        int $i22 = $i2 - $i52;
        SparseBooleanArray $r9 = this.mActionButtonGroups;
        SparseBooleanArray $r92 = $r9;
        $r9.clear();
        if (this.mStrictWidthLimit) {
            int $i43 = this.mMinCellSize;
            $i5 = $i3 / $i43;
            int $i62 = ($i3 % $i43) / $i5;
            int i = $i62;
            $i4 = $i43 + $i62;
        } else {
            $i4 = 0;
            $i5 = 0;
        }
        int $i63 = 0;
        int $i7 = 0;
        while ($i63 < $i1) {
            MenuItemImpl $r82 = (MenuItemImpl) $r4.get($i63);
            if ($r82.requiresActionButton()) {
                View $r10 = actionMenuPresenter.getItemView($r82, actionMenuPresenter.mScrapActionButtonView, $r6);
                if (actionMenuPresenter.mScrapActionButtonView == null) {
                    actionMenuPresenter.mScrapActionButtonView = $r10;
                }
                if (actionMenuPresenter.mStrictWidthLimit) {
                    $i5 -= ActionMenuView.measureChildForCells($r10, $i4, $i5, $i0, 0);
                } else {
                    $r10.measure($i0, $i0);
                }
                int $i9 = $r10.getMeasuredWidth();
                int $i8 = $i9;
                $i3 -= $i9;
                if ($i7 != 0) {
                    $i8 = $i7;
                }
                int $i72 = $r82.getGroupId();
                if ($i72 != 0) {
                    $r92.put($i72, true);
                }
                $r82.setIsActionButton(true);
                $i7 = $i8;
            } else if ($r82.requestsActionButton()) {
                int $i82 = $r82.getGroupId();
                boolean $z0 = $r92.get($i82);
                boolean $z1 = ($i22 > 0 || $z0) && $i3 > 0 && (!actionMenuPresenter.mStrictWidthLimit || $i5 > 0);
                if ($z1) {
                    View $r102 = actionMenuPresenter.getItemView($r82, actionMenuPresenter.mScrapActionButtonView, $r6);
                    if (actionMenuPresenter.mScrapActionButtonView == null) {
                        actionMenuPresenter.mScrapActionButtonView = $r102;
                    }
                    if (actionMenuPresenter.mStrictWidthLimit) {
                        int $i92 = ActionMenuView.measureChildForCells($r102, $i4, $i5, $i0, 0);
                        $i5 -= $i92;
                        if ($i92 == 0) {
                            $z1 = false;
                        }
                    } else {
                        $r102.measure($i0, $i0);
                    }
                    int $i93 = $r102.getMeasuredWidth();
                    $i3 -= $i93;
                    if ($i7 == 0) {
                        $i7 = $i93;
                    }
                    $z1 &= !actionMenuPresenter.mStrictWidthLimit ? $i3 + $i7 > 0 : $i3 >= 0;
                }
                if ($z1 && $i82 != 0) {
                    $r92.put($i82, true);
                } else if ($z0) {
                    $r92.put($i82, false);
                    for (int $i94 = 0; $i94 < $i63; $i94++) {
                        MenuItemImpl $r12 = (MenuItemImpl) $r4.get($i94);
                        if ($r12.getGroupId() == $i82) {
                            if ($r12.isActionButton()) {
                                $i22++;
                            }
                            $r12.setIsActionButton(false);
                        }
                    }
                }
                if ($z1) {
                    $i22--;
                }
                $r82.setIsActionButton($z1);
            } else {
                $r82.setIsActionButton(false);
            }
            $i63++;
            actionMenuPresenter = this;
        }
        return true;
    }

    public View getItemView(MenuItemImpl menuItemImpl, View view, ViewGroup viewGroup) {
        View $r4 = menuItemImpl.getActionView();
        View $r5 = $r4;
        if ($r4 == null || menuItemImpl.hasCollapsibleActionView()) {
            $r5 = super.getItemView(menuItemImpl, view, viewGroup);
        }
        $r5.setVisibility(menuItemImpl.isActionViewExpanded() ? (byte) 8 : 0);
        ActionMenuView $r6 = (ActionMenuView) viewGroup;
        ViewGroup.LayoutParams $r7 = $r5.getLayoutParams();
        if (!$r6.checkLayoutParams($r7)) {
            $r5.setLayoutParams($r6.generateLayoutParams($r7));
        }
        return $r5;
    }

    public MenuView getMenuView(ViewGroup viewGroup) {
        MenuView $r3 = this.mMenuView;
        MenuView $r1 = super.getMenuView(viewGroup);
        if ($r3 != $r1) {
            ((ActionMenuView) $r1).setPresenter(this);
        }
        return $r1;
    }

    public Drawable getOverflowIcon() {
        OverflowMenuButton $r2 = this.mOverflowButton;
        if ($r2 != null) {
            return $r2.getDrawable();
        }
        if (this.mPendingOverflowIconSet) {
            return this.mPendingOverflowIcon;
        }
        return null;
    }

    public boolean hideOverflowMenu() {
        MenuView $r2;
        OpenOverflowRunnable $r1 = this.mPostedOpenRunnable;
        if ($r1 == null || ($r2 = this.mMenuView) == null) {
            i $r4 = this.mOverflowPopup;
            if ($r4 == null) {
                return false;
            }
            $r4.dismiss();
            return true;
        }
        ((View) $r2).removeCallbacks($r1);
        this.mPostedOpenRunnable = null;
        return true;
    }

    public boolean hideSubMenus() {
        XYPlot $r1 = this.a;
        if ($r1 == null) {
            return false;
        }
        $r1.dismiss();
        return true;
    }

    public void initForMenu(Context context, MenuBuilder menuBuilder) {
        super.initForMenu(context, menuBuilder);
        Resources $r4 = context.getResources();
        ActionBarPolicy $r5 = ActionBarPolicy.get(context);
        if (!this.mReserveOverflowSet) {
            this.mReserveOverflow = $r5.showsOverflowMenuButton();
        }
        if (!this.mWidthLimitSet) {
            this.mWidthLimit = $r5.getEmbeddedMenuWidthLimit();
        }
        if (!this.mMaxItemsSet) {
            this.mMaxItems = $r5.init();
        }
        int $i0 = this.mWidthLimit;
        if (this.mReserveOverflow) {
            if (this.mOverflowButton == null) {
                this.mOverflowButton = new OverflowMenuButton(this.mSystemContext);
                if (this.mPendingOverflowIconSet) {
                    this.mOverflowButton.setImageDrawable(this.mPendingOverflowIcon);
                    this.mPendingOverflowIcon = null;
                    this.mPendingOverflowIconSet = false;
                }
                int $i1 = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.mOverflowButton.measure($i1, $i1);
            }
            $i0 -= this.mOverflowButton.getMeasuredWidth();
        } else {
            this.mOverflowButton = null;
        }
        this.mActionItemWidthLimit = $i0;
        this.mMinCellSize = (int) ($r4.getDisplayMetrics().density * 56.0f);
        this.mScrapActionButtonView = null;
    }

    public boolean isOverflowMenuShowPending() {
        return this.mPostedOpenRunnable != null || isOverflowMenuShowing();
    }

    public boolean isOverflowMenuShowing() {
        i $r1 = this.mOverflowPopup;
        return $r1 != null && $r1.isShowing();
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (!this.mMaxItemsSet) {
            this.mMaxItems = ActionBarPolicy.get(this.mContext).init();
        }
        MenuBuilder $r4 = this.mMenu;
        if ($r4 != null) {
            $r4.onItemsChanged(true);
        }
    }

    public void setExpandedActionViewsExclusive(boolean z) {
        this.mExpandedActionViewsExclusive = z;
    }

    public void setMenuView(ActionMenuView actionMenuView) {
        this.mMenuView = actionMenuView;
        actionMenuView.initialize(this.mMenu);
    }

    public void setOverflowIcon(Drawable drawable) {
        OverflowMenuButton $r2 = this.mOverflowButton;
        if ($r2 != null) {
            $r2.setImageDrawable(drawable);
            return;
        }
        this.mPendingOverflowIconSet = true;
        this.mPendingOverflowIcon = drawable;
    }

    public void setReserveOverflow(boolean z) {
        this.mReserveOverflow = z;
        this.mReserveOverflowSet = true;
    }

    public boolean shouldIncludeItem(int i, MenuItemImpl menuItemImpl) {
        return menuItemImpl.isActionButton();
    }

    public boolean showOverflowMenu() {
        MenuBuilder $r2;
        if (!this.mReserveOverflow || isOverflowMenuShowing() || ($r2 = this.mMenu) == null || this.mMenuView == null || this.mPostedOpenRunnable != null || $r2.getNonActionItems().isEmpty()) {
            return false;
        }
        this.mPostedOpenRunnable = new OpenOverflowRunnable(new i(this, this.mContext, this.mMenu, this.mOverflowButton, true));
        ((View) this.mMenuView).post(this.mPostedOpenRunnable);
        super.a((SubMenuBuilder) null);
        return true;
    }

    public void updateMenuView(boolean z) {
        MenuView $r1;
        super.updateMenuView(z);
        ((View) this.mMenuView).requestLayout();
        MenuBuilder $r3 = this.mMenu;
        boolean z2 = false;
        if ($r3 != null) {
            ArrayList $r4 = $r3.getActionItems();
            int $i0 = $r4.size();
            for (int $i1 = 0; $i1 < $i0; $i1++) {
                ActionProvider $r7 = ((MenuItemImpl) $r4.get($i1)).getSupportActionProvider();
                if ($r7 != null) {
                    $r7.setSubUiVisibilityListener(this);
                }
            }
        }
        MenuBuilder $r32 = this.mMenu;
        ArrayList $r42 = $r32 != null ? $r32.getNonActionItems() : null;
        if (this.mReserveOverflow && $r42 != null) {
            int $i02 = $r42.size();
            if ($i02 == 1) {
                z2 = !((MenuItemImpl) $r42.get(0)).isActionViewExpanded();
            } else if ($i02 > 0) {
                z2 = true;
            }
        }
        if (z2) {
            if (this.mOverflowButton == null) {
                this.mOverflowButton = new OverflowMenuButton(this.mSystemContext);
            }
            OverflowMenuButton $r8 = this.mOverflowButton;
            OverflowMenuButton overflowMenuButton = $r8;
            ViewGroup $r11 = (ViewGroup) $r8.getParent();
            if ($r11 != this.mMenuView) {
                if ($r11 != null) {
                    $r11.removeView(this.mOverflowButton);
                }
                ActionMenuView $r12 = (ActionMenuView) this.mMenuView;
                $r12.addView(this.mOverflowButton, $r12.generateOverflowButtonLayoutParams());
            }
        } else {
            OverflowMenuButton $r82 = this.mOverflowButton;
            if ($r82 != null && $r82.getParent() == ($r1 = this.mMenuView)) {
                ((ViewGroup) $r1).removeView(this.mOverflowButton);
            }
        }
        ((ActionMenuView) this.mMenuView).setOverflowReserved(this.mReserveOverflow);
    }
}
