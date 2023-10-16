package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.l$a;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

public class ActionMenuView extends LinearLayoutCompat implements MenuBuilder.ItemInvoker, MenuView {
    private l$a mActionMenuPresenterCallback;
    private boolean mFormatItems;
    private int mFormatItemsWidth;
    private int mGeneratedItemPadding;
    private MenuBuilder mMenu;
    MenuBuilder.Callback mMenuBuilderCallback;
    e mMenuItemClickListener;
    private int mMinCellSize;
    private Context mPopupContext;
    private int mPopupTheme;
    private ActionMenuPresenter mPresenter;
    private boolean mReserveOverflow;

    public interface a {
        boolean needsDividerAfter();

        boolean needsDividerBefore();
    }

    private static class b implements l$a {
        b() {
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        }

        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            return false;
        }
    }

    public static class c extends LinearLayoutCompat.LayoutParams {
        @ViewDebug.ExportedProperty
        public int cellsUsed;
        @ViewDebug.ExportedProperty
        public boolean expandable;
        boolean expanded;
        @ViewDebug.ExportedProperty
        public int extraPixels;
        @ViewDebug.ExportedProperty
        public boolean isOverflowButton;
        @ViewDebug.ExportedProperty
        public boolean preventEdgeOffset;

        public c(int i, int i2) {
            super(i, i2);
            this.isOverflowButton = false;
        }

        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public c(c cVar) {
            super(cVar);
            this.isOverflowButton = cVar.isOverflowButton;
        }

        public c(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    private class d implements MenuBuilder.Callback {
        d() {
        }

        public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
            e $r4 = ActionMenuView.this.mMenuItemClickListener;
            return $r4 != null && $r4.onMenuItemClick(menuItem);
        }

        public void onMenuModeChange(MenuBuilder menuBuilder) {
            MenuBuilder.Callback $r3 = ActionMenuView.this.mMenuBuilderCallback;
            if ($r3 != null) {
                $r3.onMenuModeChange(menuBuilder);
            }
        }
    }

    public interface e {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public ActionMenuView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBaselineAligned(false);
        float $f0 = context.getResources().getDisplayMetrics().density;
        this.mMinCellSize = (int) (56.0f * $f0);
        this.mGeneratedItemPadding = (int) ($f0 * 4.0f);
        this.mPopupContext = context;
        this.mPopupTheme = 0;
    }

    static int measureChildForCells(View view, int i, int i2, int i3, int i4) {
        c $r2 = (c) view.getLayoutParams();
        int $i2 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i3) - i4, View.MeasureSpec.getMode(i3));
        ActionMenuItemView $r3 = view instanceof ActionMenuItemView ? (ActionMenuItemView) view : null;
        boolean $z0 = true;
        boolean $z1 = $r3 != null && $r3.hasText();
        int $i3 = 2;
        if (i2 <= 0 || ($z1 && i2 < 2)) {
            $i3 = 0;
        } else {
            view.measure(View.MeasureSpec.makeMeasureSpec(i2 * i, Integer.MIN_VALUE), $i2);
            int $i4 = view.getMeasuredWidth();
            int $i1 = $i4 / i;
            if ($i4 % i != 0) {
                $i1++;
            }
            if (!$z1 || $i1 >= 2) {
                $i3 = $i1;
            }
        }
        if ($r2.isOverflowButton || !$z1) {
            $z0 = false;
        }
        $r2.expandable = $z0;
        $r2.cellsUsed = $i3;
        view.measure(View.MeasureSpec.makeMeasureSpec(i * $i3, 1073741824), $i2);
        return $i3;
    }

    private void onMeasureExactFormat(int i, int i2) {
        int $i12;
        int $i11;
        int $i3 = View.MeasureSpec.getMode(i2);
        int $i2 = View.MeasureSpec.getSize(i);
        int $i4 = View.MeasureSpec.getSize(i2);
        int $i6 = getPaddingLeft() + getPaddingRight();
        int $i5 = getPaddingTop() + getPaddingBottom();
        int $i0 = ViewGroup.getChildMeasureSpec(i2, $i5, -2);
        int $i7 = $i2 - $i6;
        int $i1 = this.mMinCellSize;
        int $i62 = $i7 / $i1;
        int $i22 = $i7 % $i1;
        if ($i62 == 0) {
            setMeasuredDimension($i7, 0);
            return;
        }
        int i3 = $i1 + ($i22 / $i62);
        int $i23 = getChildCount();
        int $i9 = 0;
        boolean $z0 = false;
        int $i10 = 0;
        int $i112 = 0;
        int $i122 = 0;
        long $l13 = 0;
        for (int $i8 = 0; $i8 < $i23; $i8++) {
            View $r1 = getChildAt($i8);
            if ($r1.getVisibility() != 8) {
                boolean $z1 = $r1 instanceof ActionMenuItemView;
                $i10++;
                if ($z1) {
                    int $i14 = this.mGeneratedItemPadding;
                    $r1.setPadding($i14, 0, $i14, 0);
                }
                c $r3 = (c) $r1.getLayoutParams();
                $r3.expanded = false;
                $r3.extraPixels = 0;
                $r3.cellsUsed = 0;
                $r3.expandable = false;
                $r3.leftMargin = 0;
                $r3.rightMargin = 0;
                $r3.preventEdgeOffset = $z1 && ((ActionMenuItemView) $r1).hasText();
                int $i142 = measureChildForCells($r1, i3, $r3.isOverflowButton ? 1 : $i62, $i0, $i5);
                $i112 = Math.max($i112, $i142);
                if ($r3.expandable) {
                    $i122++;
                }
                if ($r3.isOverflowButton) {
                    $z0 = true;
                }
                $i62 -= $i142;
                int $i15 = Math.max($i9, $r1.getMeasuredHeight());
                $i9 = $i15;
                if ($i142 == 1) {
                    $l13 |= (long) (1 << $i8);
                    $i9 = $i15;
                }
            }
        }
        int $i52 = $i4;
        boolean z = $z0 && $i10 == 2;
        boolean $z12 = false;
        while (true) {
            if ($i122 <= 0 || $i62 <= 0) {
                $i12 = $i7;
            } else {
                int $i42 = Integer.MAX_VALUE;
                int $i82 = 0;
                long $l16 = 0;
                for (int $i143 = 0; $i143 < $i23; $i143++) {
                    c $r32 = (c) getChildAt($i143).getLayoutParams();
                    if ($r32.expandable) {
                        int $i152 = $r32.cellsUsed;
                        int $i153 = $i152;
                        if ($i152 < $i42) {
                            $i42 = $i153;
                            $l16 = 1 << $i143;
                            $i82 = 1;
                        } else if ($i153 == $i42) {
                            $l16 |= 1 << $i143;
                            $i82++;
                        }
                    }
                }
                $l13 |= $l16;
                if ($i82 > $i62) {
                    $i12 = $i7;
                    break;
                }
                int $i83 = $i42 + 1;
                for (int $i43 = 0; $i43 < $i23; $i43++) {
                    View $r12 = getChildAt($i43);
                    c $r33 = (c) $r12.getLayoutParams();
                    long $l17 = (long) (1 << $i43);
                    if (($l16 & $l17) == 0) {
                        int $i144 = $r33.cellsUsed;
                        int i4 = $i144;
                        if ($i144 == $i83) {
                            $l13 |= $l17;
                        }
                    } else {
                        if (z && $r33.preventEdgeOffset && $i62 == 1) {
                            int $i145 = this.mGeneratedItemPadding;
                            $r12.setPadding($i145 + i3, 0, $i145, 0);
                        }
                        $r33.cellsUsed++;
                        $r33.expanded = true;
                        $i62--;
                    }
                }
                $z12 = true;
            }
        }
        boolean $z02 = !$z0 && $i10 == 1;
        if ($i62 <= 0 || $l13 == 0 || ($i62 >= $i10 - 1 && !$z02 && $i112 <= 1)) {
            $i11 = 0;
        } else {
            float $f0 = (float) Long.bitCount($l13);
            if (!$z02) {
                if (($l13 & 1) != 0) {
                    $i11 = 0;
                    if (!((c) getChildAt(0).getLayoutParams()).preventEdgeOffset) {
                        $f0 -= 0.5f;
                    }
                } else {
                    $i11 = 0;
                }
                int $i72 = $i23 - 1;
                if (($l13 & ((long) (1 << $i72))) != 0 && !((c) getChildAt($i72).getLayoutParams()).preventEdgeOffset) {
                    $f0 -= 0.5f;
                }
            } else {
                $i11 = 0;
            }
            int $i63 = $f0 > 0.0f ? (int) (((float) ($i62 * i3)) / $f0) : 0;
            for (int $i73 = 0; $i73 < $i23; $i73++) {
                if (($l13 & ((long) (1 << $i73))) != 0) {
                    View $r13 = getChildAt($i73);
                    c $r34 = (c) $r13.getLayoutParams();
                    if ($r13 instanceof ActionMenuItemView) {
                        $r34.extraPixels = $i63;
                        $r34.expanded = true;
                        if ($i73 == 0 && !$r34.preventEdgeOffset) {
                            $r34.leftMargin = (-$i63) / 2;
                        }
                    } else if ($r34.isOverflowButton) {
                        $r34.extraPixels = $i63;
                        $r34.expanded = true;
                        $r34.rightMargin = (-$i63) / 2;
                    } else {
                        if ($i73 != 0) {
                            $r34.leftMargin = $i63 / 2;
                        }
                        if ($i73 != $i23 - 1) {
                            $r34.rightMargin = $i63 / 2;
                        }
                    }
                    $z12 = true;
                }
            }
        }
        if ($z12) {
            while ($i11 < $i23) {
                View $r14 = getChildAt($i11);
                c $r35 = (c) $r14.getLayoutParams();
                if ($r35.expanded) {
                    $r14.measure(View.MeasureSpec.makeMeasureSpec(($r35.cellsUsed * i3) + $r35.extraPixels, 1073741824), $i0);
                }
                $i11++;
            }
        }
        setMeasuredDimension($i12, $i3 != 1073741824 ? $i9 : $i52);
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams != null && (layoutParams instanceof c);
    }

    public void dismissPopupMenus() {
        ActionMenuPresenter $r1 = this.mPresenter;
        if ($r1 != null) {
            $r1.dismissPopupMenus();
        }
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    /* access modifiers changed from: protected */
    public c generateDefaultLayoutParams() {
        c $r1 = new c(-2, -2);
        $r1.gravity = 16;
        return $r1;
    }

    public c generateLayoutParams(AttributeSet attributeSet) {
        return new c(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public c generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams == null) {
            return generateDefaultLayoutParams();
        }
        c $r1 = layoutParams instanceof c ? new c((c) layoutParams) : new c(layoutParams);
        if ($r1.gravity > 0) {
            return $r1;
        }
        $r1.gravity = 16;
        return $r1;
    }

    public c generateOverflowButtonLayoutParams() {
        c $r1 = generateDefaultLayoutParams();
        $r1.isOverflowButton = true;
        return $r1;
    }

    public Menu getMenu() {
        if (this.mMenu == null) {
            Context $r1 = getContext();
            this.mMenu = new MenuBuilder($r1);
            this.mMenu.setCallback(new d());
            this.mPresenter = new ActionMenuPresenter($r1);
            this.mPresenter.setReserveOverflow(true);
            ActionMenuPresenter $r4 = this.mPresenter;
            l$a $r5 = this.mActionMenuPresenterCallback;
            if ($r5 == null) {
                $r5 = r9;
                b r9 = new b();
            }
            $r4.a($r5);
            this.mMenu.addMenuPresenter(this.mPresenter, this.mPopupContext);
            this.mPresenter.setMenuView(this);
        }
        return this.mMenu;
    }

    public Drawable getOverflowIcon() {
        getMenu();
        return this.mPresenter.getOverflowIcon();
    }

    public int getPopupTheme() {
        return this.mPopupTheme;
    }

    public int getWindowAnimations() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public boolean hasSupportDividerBeforeChildAt(int i) {
        boolean $z0 = false;
        if (i == 0) {
            return false;
        }
        View $r1 = getChildAt(i - 1);
        View $r2 = getChildAt(i);
        if (i < getChildCount() && ($r1 instanceof a)) {
            $z0 = false | ((a) $r1).needsDividerAfter();
        }
        return (i <= 0 || !($r2 instanceof a)) ? $z0 : $z0 | ((a) $r2).needsDividerBefore();
    }

    public boolean hideOverflowMenu() {
        ActionMenuPresenter $r1 = this.mPresenter;
        return $r1 != null && $r1.hideOverflowMenu();
    }

    public void initialize(MenuBuilder menuBuilder) {
        this.mMenu = menuBuilder;
    }

    public boolean invokeItem(MenuItemImpl menuItemImpl) {
        return this.mMenu.performItemAction(menuItemImpl, 0);
    }

    public boolean isOverflowMenuShowPending() {
        ActionMenuPresenter $r1 = this.mPresenter;
        return $r1 != null && $r1.isOverflowMenuShowPending();
    }

    public boolean isOverflowMenuShowing() {
        ActionMenuPresenter $r1 = this.mPresenter;
        return $r1 != null && $r1.isOverflowMenuShowing();
    }

    public boolean isOverflowReserved() {
        return this.mReserveOverflow;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ActionMenuPresenter $r2 = this.mPresenter;
        if ($r2 != null) {
            $r2.updateMenuView(false);
            if (this.mPresenter.isOverflowMenuShowing()) {
                this.mPresenter.hideOverflowMenu();
                this.mPresenter.showOverflowMenu();
            }
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dismissPopupMenus();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int $i0;
        int $i11;
        int $i10;
        if (!this.mFormatItems) {
            super.onLayout(z, i, i2, i3, i4);
            return;
        }
        int $i4 = getChildCount();
        int $i1 = (i4 - i2) / 2;
        int $i3 = getDividerWidth();
        int $i02 = i3 - i;
        int $i5 = getPaddingLeft();
        boolean $z0 = ViewUtils.isLayoutRtl(this);
        int paddingRight = ($i02 - getPaddingRight()) - $i5;
        byte $b6 = 0;
        int $i7 = 0;
        for (int $i52 = 0; $i52 < $i4; $i52++) {
            View $r1 = getChildAt($i52);
            if ($r1.getVisibility() != 8) {
                c $r3 = (c) $r1.getLayoutParams();
                if ($r3.isOverflowButton) {
                    int $i9 = $r1.getMeasuredWidth();
                    int $i8 = $i9;
                    if (hasSupportDividerBeforeChildAt($i52)) {
                        $i8 = $i9 + $i3;
                    }
                    int $i92 = $r1.getMeasuredHeight();
                    if ($z0) {
                        $i10 = getPaddingLeft() + $r3.leftMargin;
                        $i11 = $i10 + $i8;
                    } else {
                        $i11 = (getWidth() - getPaddingRight()) - $r3.rightMargin;
                        $i10 = $i11 - $i8;
                    }
                    int $i12 = $i1 - ($i92 / 2);
                    $r1.layout($i10, $i12, $i11, $i92 + $i12);
                    paddingRight -= $i8;
                    $b6 = 1;
                } else {
                    paddingRight -= ($r1.getMeasuredWidth() + $r3.leftMargin) + $r3.rightMargin;
                    hasSupportDividerBeforeChildAt($i52);
                    $i7++;
                }
            }
        }
        if ($i4 == 1 && $b6 == 0) {
            View $r12 = getChildAt(0);
            int $i32 = $r12.getMeasuredWidth();
            int $i2 = $r12.getMeasuredHeight();
            int $i22 = ($i02 / 2) - ($i32 / 2);
            int i6 = $i1 - ($i2 / 2);
            $r12.layout($i22, i6, $i32 + $i22, $i2 + i6);
            return;
        }
        int $i03 = $i7 - ($b6 ^ 1);
        if ($i03 > 0) {
            i5 = paddingRight / $i03;
            $i0 = 0;
        } else {
            $i0 = 0;
            i5 = 0;
        }
        int $i23 = Math.max(0, i5);
        if ($z0) {
            int width = getWidth() - getPaddingRight();
            while ($i0 < $i4) {
                View $r13 = getChildAt($i0);
                c $r32 = (c) $r13.getLayoutParams();
                if ($r13.getVisibility() != 8 && !$r32.isOverflowButton) {
                    int i7 = width - $r32.rightMargin;
                    int $i53 = $r13.getMeasuredWidth();
                    int $i72 = $r13.getMeasuredHeight();
                    int $i82 = $i1 - ($i72 / 2);
                    $r13.layout(i7 - $i53, $i82, i7, $i72 + $i82);
                    width = i7 - (($i53 + $r32.leftMargin) + $i23);
                }
                $i0++;
            }
            return;
        }
        int $i33 = getPaddingLeft();
        while ($i0 < $i4) {
            View $r14 = getChildAt($i0);
            c $r33 = (c) $r14.getLayoutParams();
            if ($r14.getVisibility() != 8 && !$r33.isOverflowButton) {
                int $i34 = $i33 + $r33.leftMargin;
                int $i54 = $r14.getMeasuredWidth();
                int $i73 = $r14.getMeasuredHeight();
                int $i83 = $i1 - ($i73 / 2);
                $r14.layout($i34, $i83, $i34 + $i54, $i73 + $i83);
                $i33 = $i34 + $i54 + $r33.rightMargin + $i23;
            }
            $i0++;
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        MenuBuilder $r1;
        boolean $z0 = this.mFormatItems;
        this.mFormatItems = View.MeasureSpec.getMode(i) == 1073741824;
        if ($z0 != this.mFormatItems) {
            this.mFormatItemsWidth = 0;
        }
        int $i2 = View.MeasureSpec.getSize(i);
        if (!(!this.mFormatItems || ($r1 = this.mMenu) == null || $i2 == this.mFormatItemsWidth)) {
            this.mFormatItemsWidth = $i2;
            $r1.onItemsChanged(true);
        }
        int $i22 = getChildCount();
        if (!this.mFormatItems || $i22 <= 0) {
            for (int $i3 = 0; $i3 < $i22; $i3++) {
                c $r4 = (c) getChildAt($i3).getLayoutParams();
                $r4.rightMargin = 0;
                $r4.leftMargin = 0;
            }
            super.onMeasure(i, i2);
            return;
        }
        onMeasureExactFormat(i, i2);
    }

    public MenuBuilder peekMenu() {
        return this.mMenu;
    }

    public void setExpandedActionViewsExclusive(boolean z) {
        this.mPresenter.setExpandedActionViewsExclusive(z);
    }

    public void setMenuCallbacks(l$a l_a, MenuBuilder.Callback callback) {
        this.mActionMenuPresenterCallback = l_a;
        this.mMenuBuilderCallback = callback;
    }

    public void setOnMenuItemClickListener(e eVar) {
        this.mMenuItemClickListener = eVar;
    }

    public void setOverflowIcon(Drawable drawable) {
        getMenu();
        this.mPresenter.setOverflowIcon(drawable);
    }

    public void setOverflowReserved(boolean z) {
        this.mReserveOverflow = z;
    }

    public void setPopupTheme(int i) {
        if (this.mPopupTheme != i) {
            this.mPopupTheme = i;
            if (i == 0) {
                this.mPopupContext = getContext();
            } else {
                this.mPopupContext = new ContextThemeWrapper(getContext(), i);
            }
        }
    }

    public void setPresenter(ActionMenuPresenter actionMenuPresenter) {
        this.mPresenter = actionMenuPresenter;
        this.mPresenter.setMenuView(this);
    }

    public boolean showOverflowMenu() {
        ActionMenuPresenter $r1 = this.mPresenter;
        return $r1 != null && $r1.showOverflowMenu();
    }
}
