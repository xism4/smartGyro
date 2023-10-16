package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.MenuItemImpl;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.org.v4.util.R$styleable;

public class ActionMenuItemView extends AppCompatTextView implements MenuView.ItemView, View.OnClickListener, ActionMenuView.a {
    b g;
    private boolean mAllowTextWithIcon;
    private boolean mExpandedFormat;
    private ListPopupWindow.ForwardingListener mForwardingListener;
    private Drawable mIcon;
    MenuItemImpl mItemData;
    MenuBuilder.ItemInvoker mItemInvoker;
    private int mMaxIconSize;
    private int mMinWidth;
    private int mSavedPaddingLeft;
    private CharSequence mTitle;

    private class a extends ListPopupWindow.ForwardingListener {
        public a() {
            super(ActionMenuItemView.this);
        }

        public ListPopupWindow getPopup() {
            b $r3 = ActionMenuItemView.this.g;
            if ($r3 != null) {
                return $r3.getPopup();
            }
            return null;
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Code restructure failed: missing block: B:4:0x000e, code lost:
            r4 = getPopup();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onForwardingStarted() {
            /*
                r6 = this;
                android.support.v7.view.menu.ActionMenuItemView r0 = android.support.v7.view.menu.ActionMenuItemView.this
                android.support.v7.view.menu.MenuBuilder$ItemInvoker r1 = r0.mItemInvoker
                if (r1 == 0) goto L_0x001c
                android.support.v7.view.menu.MenuItemImpl r2 = r0.mItemData
                boolean r3 = r1.invokeItem(r2)
                if (r3 == 0) goto L_0x001c
                android.support.v7.view.menu.ListPopupWindow r4 = r6.getPopup()
                if (r4 == 0) goto L_0x001c
                boolean r3 = r4.isShowing()
                if (r3 == 0) goto L_0x001c
                r5 = 1
                return r5
            L_0x001c:
                r5 = 0
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.view.menu.ActionMenuItemView.a.onForwardingStarted():boolean");
        }
    }

    public static abstract class b {
        public abstract ListPopupWindow getPopup();
    }

    public ActionMenuItemView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Resources $r3 = context.getResources();
        this.mAllowTextWithIcon = a();
        TypedArray $r5 = context.obtainStyledAttributes(attributeSet, R$styleable.ActionMenuItemView, i, 0);
        this.mMinWidth = $r5.getDimensionPixelSize(R$styleable.ActionMenuItemView_android_minWidth, 0);
        $r5.recycle();
        this.mMaxIconSize = (int) (($r3.getDisplayMetrics().density * 32.0f) + 0.5f);
        setOnClickListener(this);
        this.mSavedPaddingLeft = -1;
        setSaveEnabled(false);
    }

    private boolean a() {
        Configuration $r3 = getContext().getResources().getConfiguration();
        int $i1 = $r3.screenWidthDp;
        int $i0 = $r3.screenHeightDp;
        if ($i1 < 480) {
            return ($i1 >= 640 && $i0 >= 480) || $r3.orientation == 2;
        }
        return true;
    }

    private void updateTextButtonVisibility() {
        boolean $z1 = true;
        boolean $z0 = !TextUtils.isEmpty(this.mTitle);
        if (this.mIcon != null && (!this.mItemData.showsTextAsAction() || (!this.mAllowTextWithIcon && !this.mExpandedFormat))) {
            $z1 = false;
        }
        boolean $z12 = $z0 & $z1;
        CharSequence $r1 = null;
        setText($z12 ? this.mTitle : null);
        CharSequence $r4 = this.mItemData.getContentDescription();
        CharSequence $r5 = $r4;
        if (TextUtils.isEmpty($r4)) {
            $r5 = $z12 ? null : this.mItemData.getTitle();
        }
        setContentDescription($r5);
        CharSequence $r42 = this.mItemData.getTooltipText();
        if (TextUtils.isEmpty($r42)) {
            if (!$z12) {
                $r1 = this.mItemData.getTitle();
            }
            MenuItemImpl.add(this, $r1);
            return;
        }
        MenuItemImpl.add(this, $r42);
    }

    public MenuItemImpl getItemData() {
        return this.mItemData;
    }

    public boolean hasText() {
        return !TextUtils.isEmpty(getText());
    }

    public void initialize(MenuItemImpl menuItemImpl, int i) {
        this.mItemData = menuItemImpl;
        setIcon(menuItemImpl.getIcon());
        setTitle(menuItemImpl.getTitleForItemView(this));
        setId(menuItemImpl.getItemId());
        setVisibility(menuItemImpl.isVisible() ? (byte) 0 : 8);
        setEnabled(menuItemImpl.isEnabled());
        if (menuItemImpl.hasSubMenu() && this.mForwardingListener == null) {
            this.mForwardingListener = new a();
        }
    }

    public boolean needsDividerAfter() {
        return hasText();
    }

    public boolean needsDividerBefore() {
        return hasText() && this.mItemData.getIcon() == null;
    }

    public void onClick(View view) {
        MenuBuilder.ItemInvoker $r3 = this.mItemInvoker;
        if ($r3 != null) {
            $r3.invokeItem(this.mItemData);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mAllowTextWithIcon = a();
        updateTextButtonVisibility();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int $i2;
        boolean $z0 = hasText();
        if ($z0 && ($i2 = this.mSavedPaddingLeft) >= 0) {
            super.setPadding($i2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        super.onMeasure(i, i2);
        int $i22 = View.MeasureSpec.getMode(i);
        int $i3 = View.MeasureSpec.getSize(i);
        int $i0 = getMeasuredWidth();
        int $i32 = $i22 == Integer.MIN_VALUE ? Math.min($i3, this.mMinWidth) : this.mMinWidth;
        if ($i22 != 1073741824 && this.mMinWidth > 0 && $i0 < $i32) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec($i32, 1073741824), i2);
        }
        if (!$z0 && this.mIcon != null) {
            super.setPadding((getMeasuredWidth() - this.mIcon.getBounds().width()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState((Parcelable) null);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        ListPopupWindow.ForwardingListener $r3;
        if (!this.mItemData.hasSubMenu() || ($r3 = this.mForwardingListener) == null || !$r3.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    public boolean prefersCondensedTitle() {
        return true;
    }

    public void setCheckable(boolean z) {
    }

    public void setChecked(boolean z) {
    }

    public void setExpandedFormat(boolean z) {
        if (this.mExpandedFormat != z) {
            this.mExpandedFormat = z;
            MenuItemImpl $r1 = this.mItemData;
            if ($r1 != null) {
                $r1.actionFormatChanged();
            }
        }
    }

    public void setIcon(Drawable drawable) {
        this.mIcon = drawable;
        if (drawable != null) {
            int $i0 = drawable.getIntrinsicWidth();
            int $i1 = $i0;
            int $i2 = drawable.getIntrinsicHeight();
            int $i3 = $i2;
            int $i4 = this.mMaxIconSize;
            if ($i0 > $i4) {
                $i3 = (int) (((float) $i2) * (((float) $i4) / ((float) $i0)));
                $i1 = $i4;
            }
            int $i02 = this.mMaxIconSize;
            if ($i3 > $i02) {
                $i1 = (int) (((float) $i1) * (((float) $i02) / ((float) $i3)));
                $i3 = $i02;
            }
            drawable.setBounds(0, 0, $i1, $i3);
        }
        setCompoundDrawables(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
        updateTextButtonVisibility();
    }

    public void setItemInvoker(MenuBuilder.ItemInvoker itemInvoker) {
        this.mItemInvoker = itemInvoker;
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        this.mSavedPaddingLeft = i;
        super.setPadding(i, i2, i3, i4);
    }

    public void setPopupCallback(b bVar) {
        this.g = bVar;
    }

    public void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        updateTextButtonVisibility();
    }
}
