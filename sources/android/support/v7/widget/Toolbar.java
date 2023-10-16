package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.ActionBar;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.SubMenuBuilder;
import android.support.v7.view.menu.l$a;
import android.support.v7.widget.ActionMenuView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.org.android.view.MarginLayoutParamsCompat;
import com.org.android.view.Settings;
import com.org.android.view.ViewCompat;
import com.org.v4.text.view.Resources;
import com.org.v4.util.R$attr;
import com.org.v4.util.R$styleable;
import com.org.v4.view.CollapsibleActionView;
import com.org.v4.view.SupportMenuInflater;
import java.util.ArrayList;
import java.util.List;

public class Toolbar extends ViewGroup {
    private int after;
    private int before;
    private l$a mActionMenuPresenterCallback;
    int mButtonGravity;
    ImageButton mCollapseButtonView;
    private CharSequence mCollapseDescription;
    private Drawable mCollapseIcon;
    private boolean mCollapsible;
    private RtlSpacingHelper mContentInsets;
    private boolean mEatingHover;
    private boolean mEatingTouch;
    View mExpandedActionView;
    private a mExpandedMenuPresenter;
    private int mGravity;
    private final ArrayList<View> mHiddenViews;
    private ImageView mLogoView;
    private int mMaxButtonHeight;
    private MenuBuilder.Callback mMenuBuilderCallback;
    c mMenuItemClickListener;
    private ActionMenuView mMenuView;
    private final ActionMenuView.e mMenuViewItemClickListener;
    private ImageButton mNavButtonView;
    private ActionMenuPresenter mOuterActionMenuPresenter;
    private Context mPopupContext;
    private int mPopupTheme;
    private final Runnable mShowOverflowMenuRunnable;
    private CharSequence mSubtitleText;
    private int mSubtitleTextAppearance;
    private int mSubtitleTextColor;
    private TextView mSubtitleTextView;
    private final int[] mTempMargins;
    private final ArrayList<View> mTempViews;
    private int mTitleMarginBottom;
    private int mTitleMarginEnd;
    private int mTitleMarginStart;
    private int mTitleMarginTop;
    private CharSequence mTitleText;
    private int mTitleTextAppearance;
    private int mTitleTextColor;
    private TextView mTitleTextView;
    private ToolbarWidgetWrapper mWrapper;

    private class a implements MenuPresenter {
        MenuItemImpl mCurrentExpandedItem;
        MenuBuilder mMenu;

        a() {
        }

        public void a(MenuBuilder menuBuilder, boolean z) {
        }

        public boolean a(SubMenuBuilder subMenuBuilder) {
            return false;
        }

        public boolean collapseItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
            View $r4 = Toolbar.this.mExpandedActionView;
            if ($r4 instanceof CollapsibleActionView) {
                ((CollapsibleActionView) $r4).onActionViewCollapsed();
            }
            Toolbar $r3 = Toolbar.this;
            $r3.removeView($r3.mExpandedActionView);
            Toolbar $r32 = Toolbar.this;
            $r32.removeView($r32.mCollapseButtonView);
            Toolbar $r33 = Toolbar.this;
            $r33.mExpandedActionView = null;
            $r33.addChildrenForExpandedActionView();
            this.mCurrentExpandedItem = null;
            Toolbar.this.requestLayout();
            menuItemImpl.setActionViewExpanded(false);
            return true;
        }

        public boolean expandItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
            Toolbar.this.ensureCollapseButtonView();
            ViewParent $r5 = Toolbar.this.mCollapseButtonView.getParent();
            Toolbar $r3 = Toolbar.this;
            if ($r5 != $r3) {
                if ($r5 instanceof ViewGroup) {
                    ((ViewGroup) $r5).removeView($r3.mCollapseButtonView);
                }
                Toolbar $r32 = Toolbar.this;
                $r32.addView($r32.mCollapseButtonView);
            }
            Toolbar.this.mExpandedActionView = menuItemImpl.getActionView();
            this.mCurrentExpandedItem = menuItemImpl;
            ViewParent $r52 = Toolbar.this.mExpandedActionView.getParent();
            Toolbar $r33 = Toolbar.this;
            if ($r52 != $r33) {
                if ($r52 instanceof ViewGroup) {
                    ((ViewGroup) $r52).removeView($r33.mExpandedActionView);
                }
                b $r8 = Toolbar.this.generateDefaultLayoutParams();
                Toolbar $r34 = Toolbar.this;
                $r8.gravity = 8388611 | ($r34.mButtonGravity & 112);
                $r8.mViewType = 2;
                $r34.mExpandedActionView.setLayoutParams($r8);
                Toolbar $r35 = Toolbar.this;
                $r35.addView($r35.mExpandedActionView);
            }
            Toolbar.this.removeChildrenForExpandedActionView();
            Toolbar.this.requestLayout();
            menuItemImpl.setActionViewExpanded(true);
            View $r7 = Toolbar.this.mExpandedActionView;
            if (!($r7 instanceof CollapsibleActionView)) {
                return true;
            }
            ((CollapsibleActionView) $r7).onActionViewExpanded();
            return true;
        }

        public boolean flagActionItems() {
            return false;
        }

        public void initForMenu(Context context, MenuBuilder menuBuilder) {
            MenuItemImpl $r2;
            MenuBuilder $r4 = this.mMenu;
            if (!($r4 == null || ($r2 = this.mCurrentExpandedItem) == null)) {
                $r4.collapseItemActionView($r2);
            }
            this.mMenu = menuBuilder;
        }

        public void updateMenuView(boolean z) {
            if (this.mCurrentExpandedItem != null) {
                MenuBuilder $r2 = this.mMenu;
                boolean $z0 = false;
                if ($r2 != null) {
                    int $i0 = $r2.size();
                    int $i1 = 0;
                    while (true) {
                        if ($i1 >= $i0) {
                            break;
                        } else if (this.mMenu.getItem($i1) == this.mCurrentExpandedItem) {
                            $z0 = true;
                            break;
                        } else {
                            $i1++;
                        }
                    }
                }
                if (!$z0) {
                    collapseItemActionView(this.mMenu, this.mCurrentExpandedItem);
                }
            }
        }
    }

    public static class b extends ActionBar.LayoutParams {
        int mViewType = 0;

        public b(int i, int i2) {
            super(i, i2);
            this.gravity = 8388627;
        }

        public b(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public b(ActionBar.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public b(b bVar) {
            super((ActionBar.LayoutParams) bVar);
            this.mViewType = bVar.mViewType;
        }

        public b(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public b(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super((ViewGroup.LayoutParams) marginLayoutParams);
            copyMarginsFromCompat(marginLayoutParams);
        }

        /* access modifiers changed from: package-private */
        public void copyMarginsFromCompat(ViewGroup.MarginLayoutParams marginLayoutParams) {
            this.leftMargin = marginLayoutParams.leftMargin;
            this.topMargin = marginLayoutParams.topMargin;
            this.rightMargin = marginLayoutParams.rightMargin;
            this.bottomMargin = marginLayoutParams.bottomMargin;
        }
    }

    public interface c {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public static class d extends Settings {
        public static final Parcelable.Creator<d> CREATOR = new DiscreteSeekBar$CustomState$1();
        int expandedMenuItemId;
        boolean isOverflowOpen;

        public d(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.expandedMenuItemId = parcel.readInt();
            this.isOverflowOpen = parcel.readInt() != 0;
        }

        public d(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.expandedMenuItemId);
            parcel.writeInt((int) this.isOverflowOpen);
        }
    }

    public Toolbar(Context context) {
        this(context, (AttributeSet) null);
    }

    public Toolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.toolbarStyle);
    }

    public Toolbar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mGravity = 8388627;
        this.mTempViews = new ArrayList();
        this.mHiddenViews = new ArrayList();
        this.mTempMargins = new int[2];
        this.mMenuViewItemClickListener = new ShareActionProvider$ShareMenuItemOnMenuItemClickListener(this);
        this.mShowOverflowMenuRunnable = new DayFragment$1(this);
        TintTypedArray $r8 = TintTypedArray.obtainStyledAttributes(getContext(), attributeSet, R$styleable.Toolbar, i, 0);
        this.mTitleTextAppearance = $r8.getResourceId(R$styleable.Toolbar_titleTextAppearance, 0);
        this.mSubtitleTextAppearance = $r8.getResourceId(R$styleable.Toolbar_subtitleTextAppearance, 0);
        this.mGravity = $r8.getInteger(R$styleable.Toolbar_android_gravity, this.mGravity);
        this.mButtonGravity = $r8.getInteger(R$styleable.Toolbar_buttonGravity, 48);
        int $i0 = $r8.getDimensionPixelOffset(R$styleable.Toolbar_titleMargin, 0);
        int $i1 = $r8.hasValue(R$styleable.Toolbar_titleMargins) ? $r8.getDimensionPixelOffset(R$styleable.Toolbar_titleMargins, $i0) : $i0;
        this.mTitleMarginBottom = $i1;
        this.mTitleMarginTop = $i1;
        this.mTitleMarginEnd = $i1;
        this.mTitleMarginStart = $i1;
        int $i02 = $r8.getDimensionPixelOffset(R$styleable.Toolbar_titleMarginStart, -1);
        if ($i02 >= 0) {
            this.mTitleMarginStart = $i02;
        }
        int $i03 = $r8.getDimensionPixelOffset(R$styleable.Toolbar_titleMarginEnd, -1);
        if ($i03 >= 0) {
            this.mTitleMarginEnd = $i03;
        }
        int $i04 = $r8.getDimensionPixelOffset(R$styleable.Toolbar_titleMarginTop, -1);
        if ($i04 >= 0) {
            this.mTitleMarginTop = $i04;
        }
        int $i05 = $r8.getDimensionPixelOffset(R$styleable.Toolbar_titleMarginBottom, -1);
        if ($i05 >= 0) {
            this.mTitleMarginBottom = $i05;
        }
        this.mMaxButtonHeight = $r8.getDimensionPixelSize(R$styleable.Toolbar_maxButtonHeight, -1);
        int $i06 = $r8.getDimensionPixelOffset(R$styleable.Toolbar_contentInsetStart, Integer.MIN_VALUE);
        int $i12 = $r8.getDimensionPixelOffset(R$styleable.Toolbar_contentInsetEnd, Integer.MIN_VALUE);
        int $i2 = $r8.getDimensionPixelSize(R$styleable.Toolbar_contentInsetLeft, 0);
        int $i3 = $r8.getDimensionPixelSize(R$styleable.Toolbar_contentInsetRight, 0);
        setTitle();
        this.mContentInsets.setAbsolute($i2, $i3);
        if (!($i06 == Integer.MIN_VALUE && $i12 == Integer.MIN_VALUE)) {
            this.mContentInsets.setRelative($i06, $i12);
        }
        this.after = $r8.getDimensionPixelOffset(R$styleable.Toolbar_contentInsetStartWithNavigation, Integer.MIN_VALUE);
        this.before = $r8.getDimensionPixelOffset(R$styleable.Toolbar_contentInsetEndWithActions, Integer.MIN_VALUE);
        this.mCollapseIcon = $r8.getDrawable(R$styleable.Toolbar_collapseIcon);
        this.mCollapseDescription = $r8.getText(R$styleable.Toolbar_collapseContentDescription);
        CharSequence $r10 = $r8.getText(R$styleable.Toolbar_title);
        if (!TextUtils.isEmpty($r10)) {
            setTitle($r10);
        }
        CharSequence $r102 = $r8.getText(R$styleable.Toolbar_subtitle);
        if (!TextUtils.isEmpty($r102)) {
            setSubtitle($r102);
        }
        this.mPopupContext = getContext();
        setPopupTheme($r8.getResourceId(R$styleable.Toolbar_popupTheme, 0));
        Drawable $r9 = $r8.getDrawable(R$styleable.Toolbar_navigationIcon);
        if ($r9 != null) {
            setNavigationIcon($r9);
        }
        CharSequence $r103 = $r8.getText(R$styleable.Toolbar_navigationContentDescription);
        if (!TextUtils.isEmpty($r103)) {
            setNavigationContentDescription($r103);
        }
        Drawable $r92 = $r8.getDrawable(R$styleable.Toolbar_logo);
        if ($r92 != null) {
            setLogo($r92);
        }
        CharSequence $r104 = $r8.getText(R$styleable.Toolbar_logoDescription);
        if (!TextUtils.isEmpty($r104)) {
            setLogoDescription($r104);
        }
        if ($r8.hasValue(R$styleable.Toolbar_titleTextColor)) {
            setTitleTextColor($r8.getColor(R$styleable.Toolbar_titleTextColor, -1));
        }
        if ($r8.hasValue(R$styleable.Toolbar_subtitleTextColor)) {
            setSubtitleTextColor($r8.getColor(R$styleable.Toolbar_subtitleTextColor, -1));
        }
        $r8.recycle();
    }

    private void addCustomViewsWithGravity(List list, int i) {
        boolean $z0 = ViewCompat.getLayoutDirection(this) == 1;
        int $i1 = getChildCount();
        int $i0 = com.org.android.view.View.getAbsoluteGravity(i, ViewCompat.getLayoutDirection(this));
        list.clear();
        if ($z0) {
            for (int $i2 = $i1 - 1; $i2 >= 0; $i2--) {
                View $r2 = getChildAt($i2);
                b $r4 = (b) $r2.getLayoutParams();
                if ($r4.mViewType == 0 && shouldLayout($r2) && getChildHorizontalGravity($r4.gravity) == $i0) {
                    list.add($r2);
                }
            }
            return;
        }
        for (int $i22 = 0; $i22 < $i1; $i22++) {
            View $r22 = getChildAt($i22);
            b $r42 = (b) $r22.getLayoutParams();
            if ($r42.mViewType == 0 && shouldLayout($r22) && getChildHorizontalGravity($r42.gravity) == $i0) {
                list.add($r22);
            }
        }
    }

    private void addSystemView(View view, boolean z) {
        ViewGroup.LayoutParams $r2 = view.getLayoutParams();
        b $r3 = $r2 == null ? generateDefaultLayoutParams() : !checkLayoutParams($r2) ? generateLayoutParams($r2) : (b) $r2;
        $r3.mViewType = 1;
        if (!z || this.mExpandedActionView == null) {
            addView(view, $r3);
            return;
        }
        view.setLayoutParams($r3);
        this.mHiddenViews.add(view);
    }

    private void ensureLogoView() {
        if (this.mLogoView == null) {
            this.mLogoView = new AppCompatImageView(getContext());
        }
    }

    private void ensureMenu() {
        ensureMenuView();
        if (this.mMenuView.peekMenu() == null) {
            MenuBuilder $r2 = (MenuBuilder) this.mMenuView.getMenu();
            if (this.mExpandedMenuPresenter == null) {
                this.mExpandedMenuPresenter = new a();
            }
            this.mMenuView.setExpandedActionViewsExclusive(true);
            $r2.addMenuPresenter(this.mExpandedMenuPresenter, this.mPopupContext);
        }
    }

    private void ensureMenuView() {
        if (this.mMenuView == null) {
            this.mMenuView = new ActionMenuView(getContext());
            this.mMenuView.setPopupTheme(this.mPopupTheme);
            this.mMenuView.setOnMenuItemClickListener(this.mMenuViewItemClickListener);
            this.mMenuView.setMenuCallbacks(this.mActionMenuPresenterCallback, this.mMenuBuilderCallback);
            b $r6 = generateDefaultLayoutParams();
            $r6.gravity = 8388613 | (this.mButtonGravity & 112);
            this.mMenuView.setLayoutParams($r6);
            addSystemView(this.mMenuView, false);
        }
    }

    private void ensureNavButtonView() {
        if (this.mNavButtonView == null) {
            this.mNavButtonView = new AppCompatImageButton(getContext(), (AttributeSet) null, R$attr.toolbarNavigationButtonStyle);
            b $r4 = generateDefaultLayoutParams();
            $r4.gravity = 8388611 | (this.mButtonGravity & 112);
            this.mNavButtonView.setLayoutParams($r4);
        }
    }

    private int getChildHorizontalGravity(int i) {
        int $i1 = ViewCompat.getLayoutDirection(this);
        int $i0 = com.org.android.view.View.getAbsoluteGravity(i, $i1) & 7;
        return ($i0 == 1 || $i0 == 3 || $i0 == 5) ? $i0 : $i1 == 1 ? 5 : 3;
    }

    private int getChildTop(View view, int i) {
        b $r3 = (b) view.getLayoutParams();
        int $i2 = view.getMeasuredHeight();
        int $i1 = i > 0 ? ($i2 - i) / 2 : 0;
        int $i3 = getChildVerticalGravity($r3.gravity);
        if ($i3 == 48) {
            return getPaddingTop() - $i1;
        }
        if ($i3 == 80) {
            return (((getHeight() - getPaddingBottom()) - $i2) - $r3.bottomMargin) - $i1;
        }
        int $i12 = getPaddingTop();
        int $i4 = getPaddingBottom();
        int $i5 = getHeight();
        int $i32 = ((($i5 - $i12) - $i4) - $i2) / 2;
        int $i0 = $r3.topMargin;
        if ($i32 < $i0) {
            $i32 = $i0;
        } else {
            int $i22 = ((($i5 - $i4) - $i2) - $i32) - $i12;
            int $i42 = $r3.bottomMargin;
            if ($i22 < $i42) {
                $i32 = Math.max(0, $i32 - ($i42 - $i22));
            }
        }
        return $i12 + $i32;
    }

    private int getChildVerticalGravity(int i) {
        int $i0 = i & 112;
        return ($i0 == 16 || $i0 == 48 || $i0 == 80) ? $i0 : this.mGravity & 112;
    }

    private int getHorizontalMargins(View view) {
        ViewGroup.MarginLayoutParams $r3 = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return MarginLayoutParamsCompat.getMarginStart($r3) + MarginLayoutParamsCompat.getMarginEnd($r3);
    }

    private MenuInflater getMenuInflater() {
        return new SupportMenuInflater(getContext());
    }

    private int getVerticalMargins(View view) {
        ViewGroup.MarginLayoutParams $r3 = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return $r3.topMargin + $r3.bottomMargin;
    }

    private int getViewListMeasuredWidth(List list, int[] iArr) {
        int $i0 = iArr[0];
        int $i1 = iArr[1];
        int $i2 = list.size();
        int $i3 = $i1;
        int $i4 = $i0;
        int $i02 = 0;
        int $i12 = 0;
        while ($i02 < $i2) {
            View $r4 = (View) list.get($i02);
            b $r6 = (b) $r4.getLayoutParams();
            int $i42 = $r6.leftMargin - $i4;
            int $i6 = $r6.rightMargin - $i3;
            int $i5 = Math.max(0, $i42);
            int $i32 = Math.max(0, $i6);
            $i4 = Math.max(0, -$i42);
            int $i62 = Math.max(0, -$i6);
            $i12 += $i5 + $r4.getMeasuredWidth() + $i32;
            $i02++;
            $i3 = $i62;
        }
        return $i12;
    }

    private boolean isChildOrHidden(View view) {
        return view.getParent() == this || this.mHiddenViews.contains(view);
    }

    private int layoutChildLeft(View view, int i, int[] iArr, int i2) {
        b $r4 = (b) view.getLayoutParams();
        int $i2 = $r4.leftMargin - iArr[0];
        int $i0 = i + Math.max(0, $i2);
        iArr[0] = Math.max(0, -$i2);
        int $i22 = getChildTop(view, i2);
        int $i1 = view.getMeasuredWidth();
        view.layout($i0, $i22, $i0 + $i1, view.getMeasuredHeight() + $i22);
        return $i0 + $i1 + $r4.rightMargin;
    }

    private int layoutChildRight(View view, int i, int[] iArr, int i2) {
        b $r4 = (b) view.getLayoutParams();
        int $i2 = $r4.rightMargin - iArr[1];
        int $i0 = i - Math.max(0, $i2);
        iArr[1] = Math.max(0, -$i2);
        int $i22 = getChildTop(view, i2);
        int $i1 = view.getMeasuredWidth();
        view.layout($i0 - $i1, $i22, $i0, view.getMeasuredHeight() + $i22);
        return $i0 - ($i1 + $r4.leftMargin);
    }

    private int measureChildCollapseMargins(View view, int i, int i2, int i3, int i4, int[] iArr) {
        ViewGroup.MarginLayoutParams $r4 = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int $i5 = $r4.leftMargin - iArr[0];
        int $i6 = $r4.rightMargin - iArr[1];
        int $i4 = Math.max(0, $i5) + Math.max(0, $i6);
        iArr[0] = Math.max(0, -$i5);
        iArr[1] = Math.max(0, -$i6);
        view.measure(ViewGroup.getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + $i4 + i2, $r4.width), ViewGroup.getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom() + $r4.topMargin + $r4.bottomMargin + i4, $r4.height));
        return view.getMeasuredWidth() + $i4;
    }

    private void measureChildConstrained(View view, int i, int i2, int i3, int i4, int $i4) {
        ViewGroup.MarginLayoutParams $r3 = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int $i0 = ViewGroup.getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + $r3.leftMargin + $r3.rightMargin + i2, $r3.width);
        int $i1 = ViewGroup.getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom() + $r3.topMargin + $r3.bottomMargin + i4, $r3.height);
        int $i2 = $i1;
        int $i3 = View.MeasureSpec.getMode($i1);
        if ($i3 != 1073741824 && $i4 >= 0) {
            if ($i3 != 0) {
                $i4 = Math.min(View.MeasureSpec.getSize($i1), $i4);
            }
            $i2 = View.MeasureSpec.makeMeasureSpec($i4, 1073741824);
        }
        view.measure($i0, $i2);
    }

    private void postShowOverflowMenu() {
        removeCallbacks(this.mShowOverflowMenuRunnable);
        post(this.mShowOverflowMenuRunnable);
    }

    private void setTitle() {
        if (this.mContentInsets == null) {
            this.mContentInsets = new RtlSpacingHelper();
        }
    }

    private boolean shouldCollapse() {
        if (!this.mCollapsible) {
            return false;
        }
        int $i0 = getChildCount();
        for (int $i1 = 0; $i1 < $i0; $i1++) {
            View $r1 = getChildAt($i1);
            if (shouldLayout($r1) && $r1.getMeasuredWidth() > 0 && $r1.getMeasuredHeight() > 0) {
                return false;
            }
        }
        return true;
    }

    private boolean shouldLayout(View view) {
        return (view == null || view.getParent() != this || view.getVisibility() == 8) ? false : true;
    }

    /* access modifiers changed from: package-private */
    public void addChildrenForExpandedActionView() {
        for (int $i0 = this.mHiddenViews.size() - 1; $i0 >= 0; $i0--) {
            addView(this.mHiddenViews.get($i0));
        }
        this.mHiddenViews.clear();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r1 = r4.mMenuView;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean canShowOverflowMenu() {
        /*
            r4 = this;
            int r0 = r4.getVisibility()
            if (r0 != 0) goto L_0x0012
            android.support.v7.widget.ActionMenuView r1 = r4.mMenuView
            if (r1 == 0) goto L_0x0012
            boolean r2 = r1.isOverflowReserved()
            if (r2 == 0) goto L_0x0012
            r3 = 1
            return r3
        L_0x0012:
            r3 = 0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.Toolbar.canShowOverflowMenu():boolean");
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof b);
    }

    public void collapseActionView() {
        a $r1 = this.mExpandedMenuPresenter;
        MenuItemImpl $r2 = $r1 == null ? null : $r1.mCurrentExpandedItem;
        if ($r2 != null) {
            $r2.collapseActionView();
        }
    }

    public void dismissPopupMenus() {
        ActionMenuView $r1 = this.mMenuView;
        if ($r1 != null) {
            $r1.dismissPopupMenus();
        }
    }

    /* access modifiers changed from: package-private */
    public void ensureCollapseButtonView() {
        if (this.mCollapseButtonView == null) {
            this.mCollapseButtonView = new AppCompatImageButton(getContext(), (AttributeSet) null, R$attr.toolbarNavigationButtonStyle);
            this.mCollapseButtonView.setImageDrawable(this.mCollapseIcon);
            this.mCollapseButtonView.setContentDescription(this.mCollapseDescription);
            b $r6 = generateDefaultLayoutParams();
            $r6.gravity = 8388611 | (this.mButtonGravity & 112);
            $r6.mViewType = 2;
            this.mCollapseButtonView.setLayoutParams($r6);
            this.mCollapseButtonView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Toolbar.this.collapseActionView();
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public b generateDefaultLayoutParams() {
        return new b(-2, -2);
    }

    public b generateLayoutParams(AttributeSet attributeSet) {
        return new b(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public b generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof b ? new b((b) layoutParams) : layoutParams instanceof ActionBar.LayoutParams ? new b((ActionBar.LayoutParams) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new b((ViewGroup.MarginLayoutParams) layoutParams) : new b(layoutParams);
    }

    public int getContentInsetEnd() {
        RtlSpacingHelper $r1 = this.mContentInsets;
        if ($r1 != null) {
            return $r1.getEnd();
        }
        return 0;
    }

    public int getContentInsetEndWithActions() {
        int $i0 = this.before;
        return $i0 != Integer.MIN_VALUE ? $i0 : getContentInsetEnd();
    }

    public int getContentInsetLeft() {
        RtlSpacingHelper $r1 = this.mContentInsets;
        if ($r1 != null) {
            return $r1.getLeft();
        }
        return 0;
    }

    public int getContentInsetRight() {
        RtlSpacingHelper $r1 = this.mContentInsets;
        if ($r1 != null) {
            return $r1.getRight();
        }
        return 0;
    }

    public int getContentInsetStart() {
        RtlSpacingHelper $r1 = this.mContentInsets;
        if ($r1 != null) {
            return $r1.getStart();
        }
        return 0;
    }

    public int getContentInsetStartWithNavigation() {
        int $i0 = this.after;
        return $i0 != Integer.MIN_VALUE ? $i0 : getContentInsetStart();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r1 = r0.peekMenu();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getCurrentContentInsetEnd() {
        /*
            r6 = this;
            android.support.v7.widget.ActionMenuView r0 = r6.mMenuView
            if (r0 == 0) goto L_0x0012
            android.support.v7.view.menu.MenuBuilder r1 = r0.peekMenu()
            if (r1 == 0) goto L_0x0012
            boolean r2 = r1.hasVisibleItems()
            if (r2 == 0) goto L_0x0012
            r2 = 1
            goto L_0x0013
        L_0x0012:
            r2 = 0
        L_0x0013:
            if (r2 == 0) goto L_0x0025
            int r3 = r6.getContentInsetEnd()
            int r4 = r6.before
            r5 = 0
            int r4 = java.lang.Math.max(r4, r5)
            int r3 = java.lang.Math.max(r3, r4)
            return r3
        L_0x0025:
            int r3 = r6.getContentInsetEnd()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.Toolbar.getCurrentContentInsetEnd():int");
    }

    public int getCurrentContentInsetLeft() {
        return ViewCompat.getLayoutDirection(this) == 1 ? getCurrentContentInsetEnd() : getCurrentContentInsetStart();
    }

    public int getCurrentContentInsetRight() {
        return ViewCompat.getLayoutDirection(this) == 1 ? getCurrentContentInsetStart() : getCurrentContentInsetEnd();
    }

    public int getCurrentContentInsetStart() {
        return getNavigationIcon() != null ? Math.max(getContentInsetStart(), Math.max(this.after, 0)) : getContentInsetStart();
    }

    public Drawable getLogo() {
        ImageView $r2 = this.mLogoView;
        if ($r2 != null) {
            return $r2.getDrawable();
        }
        return null;
    }

    public CharSequence getLogoDescription() {
        ImageView $r2 = this.mLogoView;
        if ($r2 != null) {
            return $r2.getContentDescription();
        }
        return null;
    }

    public Menu getMenu() {
        ensureMenu();
        return this.mMenuView.getMenu();
    }

    public CharSequence getNavigationContentDescription() {
        ImageButton $r2 = this.mNavButtonView;
        if ($r2 != null) {
            return $r2.getContentDescription();
        }
        return null;
    }

    public Drawable getNavigationIcon() {
        ImageButton $r2 = this.mNavButtonView;
        if ($r2 != null) {
            return $r2.getDrawable();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public ActionMenuPresenter getOuterActionMenuPresenter() {
        return this.mOuterActionMenuPresenter;
    }

    public Drawable getOverflowIcon() {
        ensureMenu();
        return this.mMenuView.getOverflowIcon();
    }

    /* access modifiers changed from: package-private */
    public Context getPopupContext() {
        return this.mPopupContext;
    }

    public int getPopupTheme() {
        return this.mPopupTheme;
    }

    public CharSequence getSubtitle() {
        return this.mSubtitleText;
    }

    public CharSequence getTitle() {
        return this.mTitleText;
    }

    public int getTitleMarginBottom() {
        return this.mTitleMarginBottom;
    }

    public int getTitleMarginEnd() {
        return this.mTitleMarginEnd;
    }

    public int getTitleMarginStart() {
        return this.mTitleMarginStart;
    }

    public int getTitleMarginTop() {
        return this.mTitleMarginTop;
    }

    public DecorToolbar getWrapper() {
        if (this.mWrapper == null) {
            this.mWrapper = new ToolbarWidgetWrapper(this, true);
        }
        return this.mWrapper;
    }

    public boolean hasExpandedActionView() {
        a $r1 = this.mExpandedMenuPresenter;
        return ($r1 == null || $r1.mCurrentExpandedItem == null) ? false : true;
    }

    public boolean hideOverflowMenu() {
        ActionMenuView $r1 = this.mMenuView;
        return $r1 != null && $r1.hideOverflowMenu();
    }

    public boolean isOverflowMenuShowPending() {
        ActionMenuView $r1 = this.mMenuView;
        return $r1 != null && $r1.isOverflowMenuShowPending();
    }

    public boolean isOverflowMenuShowing() {
        ActionMenuView $r1 = this.mMenuView;
        return $r1 != null && $r1.isOverflowMenuShowing();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.mShowOverflowMenuRunnable);
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        int $i0 = motionEvent.getActionMasked();
        if ($i0 == 9) {
            this.mEatingHover = false;
        }
        if (!this.mEatingHover) {
            boolean $z0 = super.onHoverEvent(motionEvent);
            if ($i0 == 9 && !$z0) {
                this.mEatingHover = true;
            }
        }
        if ($i0 != 10 && $i0 != 3) {
            return true;
        }
        this.mEatingHover = false;
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x024b, code lost:
        if (r0.getMeasuredWidth() <= 0) goto L_0x024d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0259, code lost:
        if (r0.getMeasuredWidth() > 0) goto L_0x025b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x025b, code lost:
        r46 = r5;
        r30 = true;
     */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x0443 A[LOOP:0: B:99:0x0441->B:100:0x0443, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x0479 A[LOOP:1: B:102:0x0477->B:103:0x0479, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x04cf  */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x04ea A[LOOP:2: B:111:0x04e6->B:113:0x04ea, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x011d  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0150  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x018f  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x01c2  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x01c7  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x020b  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0212  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x021a  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0221  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0241  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x024f  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0271  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x02c2  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x02d5  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0385  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onLayout(boolean r42, int r43, int r44, int r45, int r46) {
        /*
            r41 = this;
            r0 = r41
            int r43 = com.org.android.view.ViewCompat.getLayoutDirection(r0)
            r3 = 1
            r0 = r43
            if (r0 != r3) goto L_0x000e
            r42 = 1
            goto L_0x0010
        L_0x000e:
            r42 = 0
        L_0x0010:
            r0 = r41
            int r45 = r0.getWidth()
            r0 = r41
            int r4 = r0.getHeight()
            r0 = r41
            int r5 = r0.getPaddingLeft()
            r0 = r41
            int r6 = r0.getPaddingRight()
            r0 = r41
            int r7 = r0.getPaddingTop()
            r0 = r41
            int r8 = r0.getPaddingBottom()
            int r43 = r45 - r6
            r0 = r41
            int[] r9 = r0.mTempMargins
            r3 = 1
            r10 = 0
            r9[r3] = r10
            r3 = 0
            r10 = 0
            r9[r3] = r10
            r0 = r41
            int r11 = com.org.android.view.ViewCompat.getMinimumHeight(r0)
            if (r11 < 0) goto L_0x0053
            int r44 = r46 - r44
            r0 = r44
            int r12 = java.lang.Math.min(r11, r0)
            goto L_0x0054
        L_0x0053:
            r12 = 0
        L_0x0054:
            r0 = r41
            android.widget.ImageButton r13 = r0.mNavButtonView
            r0 = r41
            boolean r14 = r0.shouldLayout(r13)
            if (r14 == 0) goto L_0x007c
            if (r42 == 0) goto L_0x0071
            r0 = r41
            android.widget.ImageButton r13 = r0.mNavButtonView
            r0 = r41
            r1 = r43
            int r44 = r0.layoutChildRight(r13, r1, r9, r12)
            r46 = r5
            goto L_0x0080
        L_0x0071:
            r0 = r41
            android.widget.ImageButton r13 = r0.mNavButtonView
            r0 = r41
            int r46 = r0.layoutChildLeft(r13, r5, r9, r12)
            goto L_0x007e
        L_0x007c:
            r46 = r5
        L_0x007e:
            r44 = r43
        L_0x0080:
            r0 = r41
            android.widget.ImageButton r13 = r0.mCollapseButtonView
            r0 = r41
            boolean r14 = r0.shouldLayout(r13)
            if (r14 == 0) goto L_0x00a7
            if (r42 == 0) goto L_0x009b
            r0 = r41
            android.widget.ImageButton r13 = r0.mCollapseButtonView
            r0 = r41
            r1 = r44
            int r44 = r0.layoutChildRight(r13, r1, r9, r12)
            goto L_0x00a7
        L_0x009b:
            r0 = r41
            android.widget.ImageButton r13 = r0.mCollapseButtonView
            r0 = r41
            r1 = r46
            int r46 = r0.layoutChildLeft(r13, r1, r9, r12)
        L_0x00a7:
            r0 = r41
            android.support.v7.widget.ActionMenuView r15 = r0.mMenuView
            r0 = r41
            boolean r14 = r0.shouldLayout(r15)
            if (r14 == 0) goto L_0x00ce
            if (r42 == 0) goto L_0x00c2
            r0 = r41
            android.support.v7.widget.ActionMenuView r15 = r0.mMenuView
            r0 = r41
            r1 = r46
            int r46 = r0.layoutChildLeft(r15, r1, r9, r12)
            goto L_0x00ce
        L_0x00c2:
            r0 = r41
            android.support.v7.widget.ActionMenuView r15 = r0.mMenuView
            r0 = r41
            r1 = r44
            int r44 = r0.layoutChildRight(r15, r1, r9, r12)
        L_0x00ce:
            r0 = r41
            int r11 = r0.getCurrentContentInsetLeft()
            r0 = r41
            int r16 = r0.getCurrentContentInsetRight()
            int r17 = r11 - r46
            r3 = 0
            r0 = r17
            int r17 = java.lang.Math.max(r3, r0)
            r3 = 0
            r9[r3] = r17
            int r17 = r43 - r44
            int r17 = r16 - r17
            r3 = 0
            r0 = r17
            int r17 = java.lang.Math.max(r3, r0)
            r3 = 1
            r9[r3] = r17
            r0 = r46
            int r46 = java.lang.Math.max(r0, r11)
            r11 = r46
            r0 = r43
            r1 = r16
            int r0 = r0 - r1
            r43 = r0
            r0 = r44
            r1 = r43
            int r44 = java.lang.Math.min(r0, r1)
            r43 = r44
            r0 = r41
            android.view.View r0 = r0.mExpandedActionView
            r18 = r0
            r0 = r41
            r1 = r18
            boolean r14 = r0.shouldLayout(r1)
            if (r14 == 0) goto L_0x0140
            if (r42 == 0) goto L_0x0130
            r0 = r41
            android.view.View r0 = r0.mExpandedActionView
            r18 = r0
            r0 = r41
            r1 = r18
            r2 = r44
            int r43 = r0.layoutChildRight(r1, r2, r9, r12)
            goto L_0x0140
        L_0x0130:
            r0 = r41
            android.view.View r0 = r0.mExpandedActionView
            r18 = r0
            r0 = r41
            r1 = r18
            r2 = r46
            int r11 = r0.layoutChildLeft(r1, r2, r9, r12)
        L_0x0140:
            r0 = r41
            android.widget.ImageView r0 = r0.mLogoView
            r19 = r0
            r0 = r41
            r1 = r19
            boolean r14 = r0.shouldLayout(r1)
            if (r14 == 0) goto L_0x0171
            if (r42 == 0) goto L_0x0163
            r0 = r41
            android.widget.ImageView r0 = r0.mLogoView
            r19 = r0
            r0 = r41
            r1 = r19
            r2 = r43
            int r43 = r0.layoutChildRight(r1, r2, r9, r12)
            goto L_0x0171
        L_0x0163:
            r0 = r41
            android.widget.ImageView r0 = r0.mLogoView
            r19 = r0
            r0 = r41
            r1 = r19
            int r11 = r0.layoutChildLeft(r1, r11, r9, r12)
        L_0x0171:
            r0 = r41
            android.widget.TextView r0 = r0.mTitleTextView
            r20 = r0
            r0 = r41
            r1 = r20
            boolean r14 = r0.shouldLayout(r1)
            r0 = r41
            android.widget.TextView r0 = r0.mSubtitleTextView
            r20 = r0
            r0 = r41
            r1 = r20
            boolean r21 = r0.shouldLayout(r1)
            if (r14 == 0) goto L_0x01c2
            r0 = r41
            android.widget.TextView r0 = r0.mTitleTextView
            r20 = r0
            android.view.ViewGroup$LayoutParams r22 = r0.getLayoutParams()
            r24 = r22
            android.support.v7.widget.Toolbar$b r24 = (android.support.v7.widget.Toolbar.b) r24
            r23 = r24
            r0 = r23
            int r0 = r0.topMargin
            r46 = r0
            r44 = r6
            r0 = r41
            android.widget.TextView r0 = r0.mTitleTextView
            r20 = r0
            int r6 = r0.getMeasuredHeight()
            r0 = r46
            int r0 = r0 + r6
            r46 = r0
            r0 = r23
            int r6 = r0.bottomMargin
            r0 = r46
            int r0 = r0 + r6
            r46 = r0
            int r6 = r46 + 0
            goto L_0x01c5
        L_0x01c2:
            r44 = r6
            r6 = 0
        L_0x01c5:
            if (r21 == 0) goto L_0x01fd
            r0 = r41
            android.widget.TextView r0 = r0.mSubtitleTextView
            r20 = r0
            android.view.ViewGroup$LayoutParams r22 = r0.getLayoutParams()
            r25 = r22
            android.support.v7.widget.Toolbar$b r25 = (android.support.v7.widget.Toolbar.b) r25
            r23 = r25
            r0 = r23
            int r0 = r0.topMargin
            r46 = r0
            r0 = r41
            android.widget.TextView r0 = r0.mSubtitleTextView
            r20 = r0
            int r16 = r0.getMeasuredHeight()
            r0 = r46
            r1 = r16
            int r0 = r0 + r1
            r46 = r0
            r0 = r23
            int r0 = r0.bottomMargin
            r16 = r0
            r0 = r46
            r1 = r16
            int r0 = r0 + r1
            r46 = r0
            int r6 = r6 + r0
            goto L_0x01fd
        L_0x01fd:
            if (r14 != 0) goto L_0x0209
            if (r21 == 0) goto L_0x0202
            goto L_0x0209
        L_0x0202:
            r46 = r5
            r5 = r12
        L_0x0205:
            r7 = 0
            goto L_0x0427
        L_0x0209:
            if (r14 == 0) goto L_0x0212
            r0 = r41
            android.widget.TextView r0 = r0.mTitleTextView
            r20 = r0
            goto L_0x0218
        L_0x0212:
            r0 = r41
            android.widget.TextView r0 = r0.mSubtitleTextView
            r20 = r0
        L_0x0218:
            if (r21 == 0) goto L_0x0221
            r0 = r41
            android.widget.TextView r0 = r0.mSubtitleTextView
            r26 = r0
            goto L_0x0227
        L_0x0221:
            r0 = r41
            android.widget.TextView r0 = r0.mTitleTextView
            r26 = r0
        L_0x0227:
            r0 = r20
            android.view.ViewGroup$LayoutParams r22 = r0.getLayoutParams()
            r27 = r22
            android.support.v7.widget.Toolbar$b r27 = (android.support.v7.widget.Toolbar.b) r27
            r23 = r27
            r0 = r26
            android.view.ViewGroup$LayoutParams r22 = r0.getLayoutParams()
            r29 = r22
            android.support.v7.widget.Toolbar$b r29 = (android.support.v7.widget.Toolbar.b) r29
            r28 = r29
            if (r14 == 0) goto L_0x024d
            r0 = r41
            android.widget.TextView r0 = r0.mTitleTextView
            r20 = r0
            int r46 = r0.getMeasuredWidth()
            if (r46 > 0) goto L_0x025b
        L_0x024d:
            if (r21 == 0) goto L_0x0260
            r0 = r41
            android.widget.TextView r0 = r0.mSubtitleTextView
            r20 = r0
            int r46 = r0.getMeasuredWidth()
            if (r46 <= 0) goto L_0x0260
        L_0x025b:
            r46 = r5
            r30 = 1
            goto L_0x0264
        L_0x0260:
            r46 = r5
            r30 = 0
        L_0x0264:
            r0 = r41
            int r5 = r0.mGravity
            r16 = r5 & 112(0x70, float:1.57E-43)
            r5 = r12
            r3 = 48
            r0 = r16
            if (r0 == r3) goto L_0x02c2
            r3 = 80
            r0 = r16
            if (r0 == r3) goto L_0x02b3
            int r12 = r4 - r7
            int r12 = r12 - r8
            int r12 = r12 - r6
            int r12 = r12 / 2
            r0 = r23
            int r0 = r0.topMargin
            r16 = r0
            r0 = r41
            int r0 = r0.mTitleMarginTop
            r17 = r0
            int r31 = r16 + r17
            r0 = r31
            if (r12 >= r0) goto L_0x0292
            int r12 = r16 + r17
            goto L_0x02b0
        L_0x0292:
            int r4 = r4 - r8
            int r4 = r4 - r6
            int r4 = r4 - r12
            int r4 = r4 - r7
            r0 = r23
            int r6 = r0.bottomMargin
            r0 = r41
            int r8 = r0.mTitleMarginBottom
            int r6 = r6 + r8
            if (r4 >= r6) goto L_0x02b0
            r0 = r28
            int r6 = r0.bottomMargin
            int r8 = r6 + r8
            int r4 = r8 - r4
            int r4 = r12 - r4
            r3 = 0
            int r12 = java.lang.Math.max(r3, r4)
        L_0x02b0:
            int r4 = r7 + r12
            goto L_0x02d3
        L_0x02b3:
            int r7 = r4 - r8
            r0 = r28
            int r4 = r0.bottomMargin
            int r7 = r7 - r4
            r0 = r41
            int r4 = r0.mTitleMarginBottom
            int r7 = r7 - r4
            int r4 = r7 - r6
            goto L_0x02d3
        L_0x02c2:
            r0 = r41
            int r7 = r0.getPaddingTop()
            r0 = r23
            int r4 = r0.topMargin
            int r7 = r7 + r4
            r0 = r41
            int r4 = r0.mTitleMarginTop
            int r4 = r7 + r4
        L_0x02d3:
            if (r42 == 0) goto L_0x0385
            if (r30 == 0) goto L_0x02dc
            r0 = r41
            int r7 = r0.mTitleMarginStart
            goto L_0x02dd
        L_0x02dc:
            r7 = 0
        L_0x02dd:
            r3 = 1
            r8 = r9[r3]
            int r7 = r7 - r8
            r3 = 0
            int r8 = java.lang.Math.max(r3, r7)
            r0 = r43
            int r0 = r0 - r8
            r43 = r0
            int r7 = -r7
            r3 = 0
            int r7 = java.lang.Math.max(r3, r7)
            r3 = 1
            r9[r3] = r7
            if (r14 == 0) goto L_0x0334
            r0 = r41
            android.widget.TextView r0 = r0.mTitleTextView
            r20 = r0
            android.view.ViewGroup$LayoutParams r22 = r0.getLayoutParams()
            r32 = r22
            android.support.v7.widget.Toolbar$b r32 = (android.support.v7.widget.Toolbar.b) r32
            r23 = r32
            r0 = r41
            android.widget.TextView r0 = r0.mTitleTextView
            r20 = r0
            int r7 = r0.getMeasuredWidth()
            int r7 = r43 - r7
            r0 = r41
            android.widget.TextView r0 = r0.mTitleTextView
            r20 = r0
            int r8 = r0.getMeasuredHeight()
            int r8 = r8 + r4
            r0 = r41
            android.widget.TextView r0 = r0.mTitleTextView
            r20 = r0
            r1 = r43
            r0.layout(r7, r4, r1, r8)
            r0 = r41
            int r4 = r0.mTitleMarginEnd
            int r7 = r7 - r4
            r0 = r23
            int r4 = r0.bottomMargin
            int r4 = r8 + r4
            goto L_0x0336
        L_0x0334:
            r7 = r43
        L_0x0336:
            if (r21 == 0) goto L_0x037a
            r0 = r41
            android.widget.TextView r0 = r0.mSubtitleTextView
            r20 = r0
            android.view.ViewGroup$LayoutParams r22 = r0.getLayoutParams()
            r33 = r22
            android.support.v7.widget.Toolbar$b r33 = (android.support.v7.widget.Toolbar.b) r33
            r23 = r33
            r0 = r23
            int r8 = r0.topMargin
            int r4 = r4 + r8
            r0 = r41
            android.widget.TextView r0 = r0.mSubtitleTextView
            r20 = r0
            int r8 = r0.getMeasuredWidth()
            int r8 = r43 - r8
            r0 = r41
            android.widget.TextView r0 = r0.mSubtitleTextView
            r20 = r0
            int r12 = r0.getMeasuredHeight()
            int r12 = r12 + r4
            r0 = r41
            android.widget.TextView r0 = r0.mSubtitleTextView
            r20 = r0
            r1 = r43
            r0.layout(r8, r4, r1, r12)
            r0 = r41
            int r4 = r0.mTitleMarginEnd
            int r4 = r43 - r4
            r0 = r23
            int r8 = r0.bottomMargin
            goto L_0x037c
        L_0x037a:
            r4 = r43
        L_0x037c:
            if (r30 == 0) goto L_0x0382
            int r43 = java.lang.Math.min(r7, r4)
        L_0x0382:
            goto L_0x0205
        L_0x0385:
            if (r30 == 0) goto L_0x038c
            r0 = r41
            int r8 = r0.mTitleMarginStart
            goto L_0x038d
        L_0x038c:
            r8 = 0
        L_0x038d:
            r7 = 0
            r3 = 0
            r12 = r9[r3]
            int r8 = r8 - r12
            r3 = 0
            int r12 = java.lang.Math.max(r3, r8)
            int r11 = r11 + r12
            int r8 = -r8
            r3 = 0
            int r8 = java.lang.Math.max(r3, r8)
            r3 = 0
            r9[r3] = r8
            if (r14 == 0) goto L_0x03de
            r0 = r41
            android.widget.TextView r0 = r0.mTitleTextView
            r20 = r0
            android.view.ViewGroup$LayoutParams r22 = r0.getLayoutParams()
            r34 = r22
            android.support.v7.widget.Toolbar$b r34 = (android.support.v7.widget.Toolbar.b) r34
            r23 = r34
            r0 = r41
            android.widget.TextView r0 = r0.mTitleTextView
            r20 = r0
            int r8 = r0.getMeasuredWidth()
            int r8 = r8 + r11
            r0 = r41
            android.widget.TextView r0 = r0.mTitleTextView
            r20 = r0
            int r12 = r0.getMeasuredHeight()
            int r12 = r12 + r4
            r0 = r41
            android.widget.TextView r0 = r0.mTitleTextView
            r20 = r0
            r0.layout(r11, r4, r8, r12)
            r0 = r41
            int r4 = r0.mTitleMarginEnd
            int r8 = r8 + r4
            r0 = r23
            int r4 = r0.bottomMargin
            int r4 = r12 + r4
            goto L_0x03df
        L_0x03de:
            r8 = r11
        L_0x03df:
            if (r21 == 0) goto L_0x0420
            r0 = r41
            android.widget.TextView r0 = r0.mSubtitleTextView
            r20 = r0
            android.view.ViewGroup$LayoutParams r22 = r0.getLayoutParams()
            r35 = r22
            android.support.v7.widget.Toolbar$b r35 = (android.support.v7.widget.Toolbar.b) r35
            r23 = r35
            r0 = r23
            int r12 = r0.topMargin
            int r4 = r4 + r12
            r0 = r41
            android.widget.TextView r0 = r0.mSubtitleTextView
            r20 = r0
            int r12 = r0.getMeasuredWidth()
            int r12 = r12 + r11
            r0 = r41
            android.widget.TextView r0 = r0.mSubtitleTextView
            r20 = r0
            int r6 = r0.getMeasuredHeight()
            int r6 = r6 + r4
            r0 = r41
            android.widget.TextView r0 = r0.mSubtitleTextView
            r20 = r0
            r0.layout(r11, r4, r12, r6)
            r0 = r41
            int r4 = r0.mTitleMarginEnd
            int r4 = r12 + r4
            r0 = r23
            int r12 = r0.bottomMargin
            goto L_0x0421
        L_0x0420:
            r4 = r11
        L_0x0421:
            if (r30 == 0) goto L_0x0427
            int r11 = java.lang.Math.max(r8, r4)
        L_0x0427:
            r0 = r41
            java.util.ArrayList<android.view.View> r0 = r0.mTempViews
            r36 = r0
            r3 = 3
            r0 = r41
            r1 = r36
            r0.addCustomViewsWithGravity(r1, r3)
            r0 = r41
            java.util.ArrayList<android.view.View> r0 = r0.mTempViews
            r36 = r0
            int r8 = r0.size()
            r4 = r11
            r11 = 0
        L_0x0441:
            if (r11 >= r8) goto L_0x045e
            r0 = r41
            java.util.ArrayList<android.view.View> r0 = r0.mTempViews
            r36 = r0
            java.lang.Object r37 = r0.get(r11)
            r38 = r37
            android.view.View r38 = (android.view.View) r38
            r18 = r38
            r0 = r41
            r1 = r18
            int r4 = r0.layoutChildLeft(r1, r4, r9, r5)
            int r11 = r11 + 1
            goto L_0x0441
        L_0x045e:
            r0 = r41
            java.util.ArrayList<android.view.View> r0 = r0.mTempViews
            r36 = r0
            r3 = 5
            r0 = r41
            r1 = r36
            r0.addCustomViewsWithGravity(r1, r3)
            r0 = r41
            java.util.ArrayList<android.view.View> r0 = r0.mTempViews
            r36 = r0
            int r8 = r0.size()
            r11 = 0
        L_0x0477:
            if (r11 >= r8) goto L_0x0496
            r0 = r41
            java.util.ArrayList<android.view.View> r0 = r0.mTempViews
            r36 = r0
            java.lang.Object r37 = r0.get(r11)
            r39 = r37
            android.view.View r39 = (android.view.View) r39
            r18 = r39
            r0 = r41
            r1 = r18
            r2 = r43
            int r43 = r0.layoutChildRight(r1, r2, r9, r5)
            int r11 = r11 + 1
            goto L_0x0477
        L_0x0496:
            r0 = r41
            java.util.ArrayList<android.view.View> r0 = r0.mTempViews
            r36 = r0
            r3 = 1
            r0 = r41
            r1 = r36
            r0.addCustomViewsWithGravity(r1, r3)
            r0 = r41
            java.util.ArrayList<android.view.View> r0 = r0.mTempViews
            r36 = r0
            r0 = r41
            r1 = r36
            int r8 = r0.getViewListMeasuredWidth(r1, r9)
            r0 = r45
            r1 = r46
            int r0 = r0 - r1
            r45 = r0
            int r44 = r45 - r44
            int r44 = r44 / 2
            int r44 = r46 + r44
            int r45 = r8 / 2
            r0 = r44
            r1 = r45
            int r0 = r0 - r1
            r44 = r0
            int r45 = r8 + r44
            r0 = r44
            if (r0 >= r4) goto L_0x04cf
            goto L_0x04dc
        L_0x04cf:
            r0 = r45
            r1 = r43
            if (r0 <= r1) goto L_0x04da
            int r43 = r45 - r43
            int r4 = r44 - r43
            goto L_0x04dc
        L_0x04da:
            r4 = r44
        L_0x04dc:
            r0 = r41
            java.util.ArrayList<android.view.View> r0 = r0.mTempViews
            r36 = r0
            int r43 = r0.size()
        L_0x04e6:
            r0 = r43
            if (r7 >= r0) goto L_0x0505
            r0 = r41
            java.util.ArrayList<android.view.View> r0 = r0.mTempViews
            r36 = r0
            java.lang.Object r37 = r0.get(r7)
            r40 = r37
            android.view.View r40 = (android.view.View) r40
            r18 = r40
            r0 = r41
            r1 = r18
            int r4 = r0.layoutChildLeft(r1, r4, r9, r5)
            int r7 = r7 + 1
            goto L_0x04e6
        L_0x0505:
            r0 = r41
            java.util.ArrayList<android.view.View> r0 = r0.mTempViews
            r36 = r0
            r0.clear()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.Toolbar.onLayout(boolean, int, int, int, int):void");
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        byte $b3;
        byte $b2;
        int $i6;
        int $i5;
        int $i4;
        int i3;
        int $i10;
        int $i62;
        int[] $r1 = this.mTempMargins;
        if (ViewUtils.isLayoutRtl(this)) {
            $b2 = 1;
            $b3 = 0;
        } else {
            $b2 = 0;
            $b3 = 1;
        }
        if (shouldLayout(this.mNavButtonView)) {
            measureChildConstrained(this.mNavButtonView, i, 0, i2, 0, this.mMaxButtonHeight);
            $i6 = this.mNavButtonView.getMeasuredWidth() + getHorizontalMargins(this.mNavButtonView);
            int $i42 = Math.max(0, this.mNavButtonView.getMeasuredHeight() + getVerticalMargins(this.mNavButtonView));
            $i5 = $i42;
            $i4 = View.combineMeasuredStates(0, this.mNavButtonView.getMeasuredState());
        } else {
            $i6 = 0;
            $i5 = 0;
            $i4 = 0;
        }
        if (shouldLayout(this.mCollapseButtonView)) {
            measureChildConstrained(this.mCollapseButtonView, i, 0, i2, 0, this.mMaxButtonHeight);
            $i6 = this.mCollapseButtonView.getMeasuredWidth() + getHorizontalMargins(this.mCollapseButtonView);
            int $i7 = this.mCollapseButtonView.getMeasuredHeight() + getVerticalMargins(this.mCollapseButtonView);
            int i4 = $i7;
            $i5 = Math.max($i5, $i7);
            $i4 = View.combineMeasuredStates($i4, this.mCollapseButtonView.getMeasuredState());
        }
        int $i8 = getCurrentContentInsetStart();
        int $i72 = 0 + Math.max($i8, $i6);
        $r1[$b2] = Math.max(0, $i8 - $i6);
        if (shouldLayout(this.mMenuView)) {
            measureChildConstrained(this.mMenuView, i, $i72, i2, 0, this.mMaxButtonHeight);
            ActionMenuView $r3 = this.mMenuView;
            ActionMenuView actionMenuView = $r3;
            i3 = $r3.getMeasuredWidth() + getHorizontalMargins(this.mMenuView);
            ActionMenuView $r32 = this.mMenuView;
            ActionMenuView actionMenuView2 = $r32;
            int $i82 = $r32.getMeasuredHeight() + getVerticalMargins(this.mMenuView);
            int i5 = $i82;
            $i5 = Math.max($i5, $i82);
            ActionMenuView $r33 = this.mMenuView;
            ActionMenuView actionMenuView3 = $r33;
            $i4 = View.combineMeasuredStates($i4, $r33.getMeasuredState());
        } else {
            i3 = 0;
        }
        int $i83 = getCurrentContentInsetEnd();
        int max = $i72 + Math.max($i83, i3);
        $r1[$b3] = Math.max(0, $i83 - i3);
        if (shouldLayout(this.mExpandedActionView)) {
            max += measureChildCollapseMargins(this.mExpandedActionView, i, max, i2, 0, $r1);
            View $r4 = this.mExpandedActionView;
            View view = $r4;
            int $i63 = $r4.getMeasuredHeight() + getVerticalMargins(this.mExpandedActionView);
            int i6 = $i63;
            $i5 = Math.max($i5, $i63);
            View $r42 = this.mExpandedActionView;
            View view2 = $r42;
            $i4 = View.combineMeasuredStates($i4, $r42.getMeasuredState());
        }
        if (shouldLayout(this.mLogoView)) {
            max += measureChildCollapseMargins(this.mLogoView, i, max, i2, 0, $r1);
            ImageView $r5 = this.mLogoView;
            ImageView imageView = $r5;
            int $i64 = $r5.getMeasuredHeight() + getVerticalMargins(this.mLogoView);
            int i7 = $i64;
            $i5 = Math.max($i5, $i64);
            ImageView $r52 = this.mLogoView;
            ImageView imageView2 = $r52;
            $i4 = View.combineMeasuredStates($i4, $r52.getMeasuredState());
        }
        int $i65 = getChildCount();
        for (int $i84 = 0; $i84 < $i65; $i84++) {
            View $r43 = getChildAt($i84);
            if (((b) $r43.getLayoutParams()).mViewType == 0 && shouldLayout($r43)) {
                max += measureChildCollapseMargins($r43, i, max, i2, 0, $r1);
                int $i9 = $r43.getMeasuredHeight() + getVerticalMargins($r43);
                int i8 = $i9;
                $i5 = Math.max($i5, $i9);
                $i4 = View.combineMeasuredStates($i4, $r43.getMeasuredState());
            }
        }
        int i9 = this.mTitleMarginTop + this.mTitleMarginBottom;
        int $i92 = this.mTitleMarginStart + this.mTitleMarginEnd;
        if (shouldLayout(this.mTitleTextView)) {
            measureChildCollapseMargins(this.mTitleTextView, i, max + $i92, i2, i9, $r1);
            TextView $r8 = this.mTitleTextView;
            TextView textView = $r8;
            $i10 = $r8.getMeasuredWidth() + getHorizontalMargins(this.mTitleTextView);
            TextView $r82 = this.mTitleTextView;
            TextView textView2 = $r82;
            $i62 = $r82.getMeasuredHeight() + getVerticalMargins(this.mTitleTextView);
            TextView $r83 = this.mTitleTextView;
            TextView textView3 = $r83;
            $i4 = View.combineMeasuredStates($i4, $r83.getMeasuredState());
        } else {
            $i10 = 0;
            $i62 = 0;
        }
        if (shouldLayout(this.mSubtitleTextView)) {
            $i10 = Math.max($i10, measureChildCollapseMargins(this.mSubtitleTextView, i, max + $i92, i2, $i62 + i9, $r1));
            TextView $r84 = this.mSubtitleTextView;
            TextView textView4 = $r84;
            $i62 += $r84.getMeasuredHeight() + getVerticalMargins(this.mSubtitleTextView);
            TextView $r85 = this.mSubtitleTextView;
            TextView textView5 = $r85;
            $i4 = View.combineMeasuredStates($i4, $r85.getMeasuredState());
        }
        int $i52 = Math.max($i5, $i62);
        int $i66 = max + $i10 + getPaddingLeft() + getPaddingRight();
        int $i67 = getPaddingTop() + getPaddingBottom();
        int i10 = $i67;
        int $i53 = $i52 + $i67;
        int $i0 = View.resolveSizeAndState(Math.max($i66, getSuggestedMinimumWidth()), i, -16777216 & $i4);
        int $i1 = View.resolveSizeAndState(Math.max($i53, getSuggestedMinimumHeight()), i2, $i4 << 16);
        if (shouldCollapse()) {
            $i1 = 0;
        }
        setMeasuredDimension($i0, $i1);
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        MenuItem $r6;
        if (!(parcelable instanceof d)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        d $r3 = (d) parcelable;
        super.onRestoreInstanceState($r3.getToken());
        ActionMenuView $r4 = this.mMenuView;
        MenuBuilder $r5 = $r4 != null ? $r4.peekMenu() : null;
        int $i0 = $r3.expandedMenuItemId;
        if (!($i0 == 0 || this.mExpandedMenuPresenter == null || $r5 == null || ($r6 = $r5.findItem($i0)) == null)) {
            $r6.expandActionView();
        }
        if ($r3.isOverflowOpen) {
            postShowOverflowMenu();
        }
    }

    public void onRtlPropertiesChanged(int i) {
        if (Build.VERSION.SDK_INT >= 17) {
            super.onRtlPropertiesChanged(i);
        }
        setTitle();
        RtlSpacingHelper $r1 = this.mContentInsets;
        boolean $z0 = true;
        if (i != 1) {
            $z0 = false;
        }
        $r1.setDirection($z0);
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        MenuItemImpl $r4;
        d $r1 = new d(super.onSaveInstanceState());
        a $r3 = this.mExpandedMenuPresenter;
        if (!($r3 == null || ($r4 = $r3.mCurrentExpandedItem) == null)) {
            $r1.expandedMenuItemId = $r4.getItemId();
        }
        $r1.isOverflowOpen = isOverflowMenuShowing();
        return $r1;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int $i0 = motionEvent.getActionMasked();
        if ($i0 == 0) {
            this.mEatingTouch = false;
        }
        if (!this.mEatingTouch) {
            boolean $z0 = super.onTouchEvent(motionEvent);
            if ($i0 == 0 && !$z0) {
                this.mEatingTouch = true;
            }
        }
        if ($i0 != 1 && $i0 != 3) {
            return true;
        }
        this.mEatingTouch = false;
        return true;
    }

    /* access modifiers changed from: package-private */
    public void removeChildrenForExpandedActionView() {
        for (int $i0 = getChildCount() - 1; $i0 >= 0; $i0--) {
            View $r1 = getChildAt($i0);
            if (!(((b) $r1.getLayoutParams()).mViewType == 2 || $r1 == this.mMenuView)) {
                removeViewAt($i0);
                this.mHiddenViews.add($r1);
            }
        }
    }

    public void setCollapsible(boolean z) {
        this.mCollapsible = z;
        requestLayout();
    }

    public void setContentInsetEndWithActions(int $i1) {
        if ($i1 < 0) {
            $i1 = Integer.MIN_VALUE;
        }
        if ($i1 != this.before) {
            this.before = $i1;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setContentInsetStartWithNavigation(int $i1) {
        if ($i1 < 0) {
            $i1 = Integer.MIN_VALUE;
        }
        if ($i1 != this.after) {
            this.after = $i1;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setContentInsetsRelative(int i, int i2) {
        setTitle();
        this.mContentInsets.setRelative(i, i2);
    }

    public void setLogo(int i) {
        setLogo(Resources.getDrawable(getContext(), i));
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            ensureLogoView();
            if (!isChildOrHidden(this.mLogoView)) {
                addSystemView(this.mLogoView, true);
            }
        } else {
            ImageView $r2 = this.mLogoView;
            if ($r2 != null && isChildOrHidden($r2)) {
                removeView(this.mLogoView);
                this.mHiddenViews.remove(this.mLogoView);
            }
        }
        ImageView $r22 = this.mLogoView;
        if ($r22 != null) {
            $r22.setImageDrawable(drawable);
        }
    }

    public void setLogoDescription(int i) {
        setLogoDescription(getContext().getText(i));
    }

    public void setLogoDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            ensureLogoView();
        }
        ImageView $r2 = this.mLogoView;
        if ($r2 != null) {
            $r2.setContentDescription(charSequence);
        }
    }

    public void setMenu(MenuBuilder menuBuilder, ActionMenuPresenter actionMenuPresenter) {
        if (menuBuilder != null || this.mMenuView != null) {
            ensureMenuView();
            MenuBuilder $r3 = this.mMenuView.peekMenu();
            if ($r3 != menuBuilder) {
                if ($r3 != null) {
                    $r3.removeMenuPresenter(this.mOuterActionMenuPresenter);
                    $r3.removeMenuPresenter(this.mExpandedMenuPresenter);
                }
                if (this.mExpandedMenuPresenter == null) {
                    this.mExpandedMenuPresenter = new a();
                }
                actionMenuPresenter.setExpandedActionViewsExclusive(true);
                if (menuBuilder != null) {
                    menuBuilder.addMenuPresenter(actionMenuPresenter, this.mPopupContext);
                    menuBuilder.addMenuPresenter(this.mExpandedMenuPresenter, this.mPopupContext);
                } else {
                    actionMenuPresenter.initForMenu(this.mPopupContext, (MenuBuilder) null);
                    this.mExpandedMenuPresenter.initForMenu(this.mPopupContext, (MenuBuilder) null);
                    actionMenuPresenter.updateMenuView(true);
                    this.mExpandedMenuPresenter.updateMenuView(true);
                }
                this.mMenuView.setPopupTheme(this.mPopupTheme);
                this.mMenuView.setPresenter(actionMenuPresenter);
                this.mOuterActionMenuPresenter = actionMenuPresenter;
            }
        }
    }

    public void setNavigationContentDescription(int i) {
        setNavigationContentDescription(i != 0 ? getContext().getText(i) : null);
    }

    public void setNavigationContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            ensureNavButtonView();
        }
        ImageButton $r2 = this.mNavButtonView;
        if ($r2 != null) {
            $r2.setContentDescription(charSequence);
        }
    }

    public void setNavigationIcon(int i) {
        setNavigationIcon(Resources.getDrawable(getContext(), i));
    }

    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null) {
            ensureNavButtonView();
            if (!isChildOrHidden(this.mNavButtonView)) {
                addSystemView(this.mNavButtonView, true);
            }
        } else {
            ImageButton $r2 = this.mNavButtonView;
            if ($r2 != null && isChildOrHidden($r2)) {
                removeView(this.mNavButtonView);
                this.mHiddenViews.remove(this.mNavButtonView);
            }
        }
        ImageButton $r22 = this.mNavButtonView;
        if ($r22 != null) {
            $r22.setImageDrawable(drawable);
        }
    }

    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        ensureNavButtonView();
        this.mNavButtonView.setOnClickListener(onClickListener);
    }

    public void setOnMenuItemClickListener(c cVar) {
        this.mMenuItemClickListener = cVar;
    }

    public void setOverflowIcon(Drawable drawable) {
        ensureMenu();
        this.mMenuView.setOverflowIcon(drawable);
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

    public void setSubtitle(int i) {
        setSubtitle(getContext().getText(i));
    }

    public void setSubtitle(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (this.mSubtitleTextView == null) {
                Context $r3 = getContext();
                this.mSubtitleTextView = new AppCompatTextView($r3);
                this.mSubtitleTextView.setSingleLine();
                this.mSubtitleTextView.setEllipsize(TextUtils.TruncateAt.END);
                int $i0 = this.mSubtitleTextAppearance;
                if ($i0 != 0) {
                    this.mSubtitleTextView.setTextAppearance($r3, $i0);
                }
                int $i02 = this.mSubtitleTextColor;
                if ($i02 != 0) {
                    this.mSubtitleTextView.setTextColor($i02);
                }
            }
            if (!isChildOrHidden(this.mSubtitleTextView)) {
                addSystemView(this.mSubtitleTextView, true);
            }
        } else {
            TextView $r2 = this.mSubtitleTextView;
            if ($r2 != null && isChildOrHidden($r2)) {
                removeView(this.mSubtitleTextView);
                this.mHiddenViews.remove(this.mSubtitleTextView);
            }
        }
        TextView $r22 = this.mSubtitleTextView;
        if ($r22 != null) {
            $r22.setText(charSequence);
        }
        this.mSubtitleText = charSequence;
    }

    public void setSubtitleTextAppearance(Context context, int i) {
        this.mSubtitleTextAppearance = i;
        TextView $r2 = this.mSubtitleTextView;
        if ($r2 != null) {
            $r2.setTextAppearance(context, i);
        }
    }

    public void setSubtitleTextColor(int i) {
        this.mSubtitleTextColor = i;
        TextView $r1 = this.mSubtitleTextView;
        if ($r1 != null) {
            $r1.setTextColor(i);
        }
    }

    public void setTitle(int i) {
        setTitle(getContext().getText(i));
    }

    public void setTitle(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (this.mTitleTextView == null) {
                Context $r3 = getContext();
                this.mTitleTextView = new AppCompatTextView($r3);
                this.mTitleTextView.setSingleLine();
                this.mTitleTextView.setEllipsize(TextUtils.TruncateAt.END);
                int $i0 = this.mTitleTextAppearance;
                if ($i0 != 0) {
                    this.mTitleTextView.setTextAppearance($r3, $i0);
                }
                int $i02 = this.mTitleTextColor;
                if ($i02 != 0) {
                    this.mTitleTextView.setTextColor($i02);
                }
            }
            if (!isChildOrHidden(this.mTitleTextView)) {
                addSystemView(this.mTitleTextView, true);
            }
        } else {
            TextView $r2 = this.mTitleTextView;
            if ($r2 != null && isChildOrHidden($r2)) {
                removeView(this.mTitleTextView);
                this.mHiddenViews.remove(this.mTitleTextView);
            }
        }
        TextView $r22 = this.mTitleTextView;
        if ($r22 != null) {
            $r22.setText(charSequence);
        }
        this.mTitleText = charSequence;
    }

    public void setTitleMarginBottom(int i) {
        this.mTitleMarginBottom = i;
        requestLayout();
    }

    public void setTitleMarginEnd(int i) {
        this.mTitleMarginEnd = i;
        requestLayout();
    }

    public void setTitleMarginStart(int i) {
        this.mTitleMarginStart = i;
        requestLayout();
    }

    public void setTitleMarginTop(int i) {
        this.mTitleMarginTop = i;
        requestLayout();
    }

    public void setTitleTextAppearance(Context context, int i) {
        this.mTitleTextAppearance = i;
        TextView $r2 = this.mTitleTextView;
        if ($r2 != null) {
            $r2.setTextAppearance(context, i);
        }
    }

    public void setTitleTextColor(int i) {
        this.mTitleTextColor = i;
        TextView $r1 = this.mTitleTextView;
        if ($r1 != null) {
            $r1.setTextColor(i);
        }
    }

    public boolean showOverflowMenu() {
        ActionMenuView $r1 = this.mMenuView;
        return $r1 != null && $r1.showOverflowMenu();
    }
}
