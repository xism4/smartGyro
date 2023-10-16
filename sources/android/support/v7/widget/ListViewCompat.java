package android.support.v7.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.org.android.view.ViewPropertyAnimatorCompat;
import com.org.v4.graphics.drawable.DrawableWrapper;
import com.org.v4.util.R$attr;
import java.lang.reflect.Field;

class ListViewCompat extends ListView {
    SnackBar b;
    private ViewPropertyAnimatorCompat mClickAnimation;
    private boolean mDrawsInPressedState;
    private boolean mHijackFocus;
    private Field mIsChildViewEnabled;
    private boolean mListSelectionHidden;
    private int mMotionPosition;
    private ListViewAutoScrollHelper mScrollHelper;
    private int mSelectionBottomPadding = 0;
    private int mSelectionLeftPadding = 0;
    private int mSelectionRightPadding = 0;
    private int mSelectionTopPadding = 0;
    private GateKeeperDrawable mSelector;
    private final Rect mSelectorRect = new Rect();

    class GateKeeperDrawable extends DrawableWrapper {
        private boolean mEnabled = true;

        GateKeeperDrawable(Drawable drawable) {
            super(drawable);
        }

        public void draw(Canvas canvas) {
            if (this.mEnabled) {
                super.draw(canvas);
            }
        }

        /* access modifiers changed from: package-private */
        public void setEnabled(boolean z) {
            this.mEnabled = z;
        }

        public void setHotspot(float f, float f2) {
            if (this.mEnabled) {
                super.setHotspot(f, f2);
            }
        }

        public void setHotspotBounds(int i, int i2, int i3, int i4) {
            if (this.mEnabled) {
                super.setHotspotBounds(i, i2, i3, i4);
            }
        }

        public boolean setState(int[] iArr) {
            if (this.mEnabled) {
                return super.setState(iArr);
            }
            return false;
        }

        public boolean setVisible(boolean z, boolean z2) {
            if (this.mEnabled) {
                return super.setVisible(z, z2);
            }
            return false;
        }
    }

    ListViewCompat(Context context, boolean z) {
        super(context, (AttributeSet) null, R$attr.dropDownListViewStyle);
        this.mHijackFocus = z;
        setCacheColorHint(0);
        try {
            this.mIsChildViewEnabled = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
            this.mIsChildViewEnabled.setAccessible(true);
        } catch (NoSuchFieldException $r5) {
            $r5.printStackTrace();
        }
    }

    private void clearPressedItem() {
        this.mDrawsInPressedState = false;
        setPressed(false);
        drawableStateChanged();
        View $r1 = getChildAt(this.mMotionPosition - getFirstVisiblePosition());
        if ($r1 != null) {
            $r1.setPressed(false);
        }
        ViewPropertyAnimatorCompat $r2 = this.mClickAnimation;
        if ($r2 != null) {
            $r2.cancel();
            this.mClickAnimation = null;
        }
    }

    private void clickPressedItem(View view, int i) {
        performItemClick(view, i, getItemIdAtPosition(i));
    }

    private void drawSelectorCompat(Canvas canvas) {
        Drawable $r3;
        if (!this.mSelectorRect.isEmpty() && ($r3 = getSelector()) != null) {
            $r3.setBounds(this.mSelectorRect);
            $r3.draw(canvas);
        }
    }

    private void positionSelectorCompat(int i, View view) {
        Rect $r2 = this.mSelectorRect;
        $r2.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        $r2.left -= this.mSelectionLeftPadding;
        $r2.top -= this.mSelectionTopPadding;
        $r2.right += this.mSelectionRightPadding;
        $r2.bottom += this.mSelectionBottomPadding;
        try {
            boolean $z0 = this.mIsChildViewEnabled.getBoolean(this);
            if (view.isEnabled() != $z0) {
                this.mIsChildViewEnabled.set(this, Boolean.valueOf(!$z0));
                if (i != -1) {
                    refreshDrawableState();
                }
            }
        } catch (IllegalAccessException $r5) {
            $r5.printStackTrace();
        }
    }

    private void positionSelectorLikeFocusCompat(int i, View view) {
        Drawable $r2 = getSelector();
        boolean $z0 = true;
        boolean $z1 = ($r2 == null || i == -1) ? false : true;
        if ($z1) {
            $r2.setVisible(false, false);
        }
        positionSelectorCompat(i, view);
        if ($z1) {
            Rect $r3 = this.mSelectorRect;
            float $f0 = $r3.exactCenterX();
            float $f1 = $r3.exactCenterY();
            if (getVisibility() != 0) {
                $z0 = false;
            }
            $r2.setVisible($z0, false);
            DrawableCompat.setHotspot($r2, $f0, $f1);
        }
    }

    private void positionSelectorLikeTouchCompat(int i, View view, float f, float f2) {
        positionSelectorLikeFocusCompat(i, view);
        Drawable $r1 = getSelector();
        if ($r1 != null && i != -1) {
            DrawableCompat.setHotspot($r1, f, f2);
        }
    }

    private void setPressedItem(View view, int i, float f, float f2) {
        View $r2;
        this.mDrawsInPressedState = true;
        if (Build.VERSION.SDK_INT >= 21) {
            drawableHotspotChanged(f, f2);
        }
        if (!isPressed()) {
            setPressed(true);
        }
        layoutChildren();
        int $i1 = this.mMotionPosition;
        if (!($i1 == -1 || ($r2 = getChildAt($i1 - getFirstVisiblePosition())) == null || $r2 == view || !$r2.isPressed())) {
            $r2.setPressed(false);
        }
        this.mMotionPosition = i;
        float $f2 = f - ((float) view.getLeft());
        float $f3 = f2 - ((float) view.getTop());
        if (Build.VERSION.SDK_INT >= 21) {
            view.drawableHotspotChanged($f2, $f3);
        }
        if (!view.isPressed()) {
            view.setPressed(true);
        }
        positionSelectorLikeTouchCompat(i, view, f, f2);
        setSelectorEnabled(false);
        refreshDrawableState();
    }

    private void setSelectorEnabled(boolean z) {
        GateKeeperDrawable $r1 = this.mSelector;
        if ($r1 != null) {
            $r1.setEnabled(z);
        }
    }

    private boolean shouldShowSelectorCompat() {
        return this.mDrawsInPressedState;
    }

    private void updateSelectorStateCompat() {
        Drawable $r1 = getSelector();
        if ($r1 != null && shouldShowSelectorCompat() && isPressed()) {
            $r1.setState(getDrawableState());
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        drawSelectorCompat(canvas);
        super.dispatchDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        if (this.b == null) {
            super.drawableStateChanged();
            setSelectorEnabled(true);
            updateSelectorStateCompat();
        }
    }

    public boolean hasFocus() {
        return this.mHijackFocus || super.hasFocus();
    }

    public boolean hasWindowFocus() {
        return this.mHijackFocus || super.hasWindowFocus();
    }

    public boolean isFocused() {
        return this.mHijackFocus || super.isFocused();
    }

    public boolean isInTouchMode() {
        return (this.mHijackFocus && this.mListSelectionHidden) || super.isInTouchMode();
    }

    public int measureHeightOfChildrenCompat(int $i0, int i, int i2, int $i5, int i3) {
        int $i52 = getListPaddingTop();
        int $i6 = getListPaddingBottom();
        getListPaddingLeft();
        getListPaddingRight();
        int $i3 = getDividerHeight();
        int i4 = $i3;
        Drawable $r1 = getDivider();
        ListAdapter $r2 = getAdapter();
        if ($r2 == null) {
            return $i52 + $i6;
        }
        int $i53 = $i52 + $i6;
        if ($i3 <= 0 || $r1 == null) {
            i4 = 0;
        }
        int $i32 = $r2.getCount();
        View $r3 = null;
        int $i62 = 0;
        int $i7 = 0;
        int $i8 = 0;
        while ($i62 < $i32) {
            int $i9 = $r2.getItemViewType($i62);
            if ($i9 != $i7) {
                $r3 = null;
                $i7 = $i9;
            }
            View $r4 = $r2.getView($i62, $r3, this);
            $r3 = $r4;
            ViewGroup.LayoutParams $r5 = $r4.getLayoutParams();
            ViewGroup.LayoutParams $r6 = $r5;
            if ($r5 == null) {
                ViewGroup.LayoutParams $r52 = generateDefaultLayoutParams();
                $r6 = $r52;
                $r4.setLayoutParams($r52);
            }
            int $i92 = $r6.height;
            $r4.measure($i0, $i92 > 0 ? View.MeasureSpec.makeMeasureSpec($i92, 1073741824) : View.MeasureSpec.makeMeasureSpec(0, 0));
            $r4.forceLayout();
            if ($i62 > 0) {
                int i5 = i4;
                $i53 += i5;
                i4 = i5;
            }
            $i53 += $r4.getMeasuredHeight();
            if ($i53 >= $i5) {
                return (i3 < 0 || $i62 <= i3 || $i8 <= 0 || $i53 == $i5) ? $i5 : $i8;
            }
            if (i3 >= 0 && $i62 >= i3) {
                $i8 = $i53;
            }
            $i62++;
        }
        return $i53;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        this.b = null;
        super.onDetachedFromWindow();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000b, code lost:
        if (r0 != 3) goto L_0x000d;
     */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x001d  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0066  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onForwardedEvent(android.view.MotionEvent r12, int r13) {
        /*
            r11 = this;
            int r0 = r12.getActionMasked()
            r1 = 1
            if (r0 == r1) goto L_0x0015
            r1 = 2
            if (r0 == r1) goto L_0x0013
            r1 = 3
            if (r0 == r1) goto L_0x0010
        L_0x000d:
            r2 = 0
            r3 = 1
            goto L_0x0046
        L_0x0010:
            r2 = 0
            r3 = 0
            goto L_0x0046
        L_0x0013:
            r3 = 1
            goto L_0x0016
        L_0x0015:
            r3 = 0
        L_0x0016:
            int r4 = r12.findPointerIndex(r13)
            if (r4 >= 0) goto L_0x001d
            goto L_0x0010
        L_0x001d:
            float r5 = r12.getX(r4)
            int r13 = (int) r5
            float r5 = r12.getY(r4)
            int r4 = (int) r5
            int r6 = r11.pointToPosition(r13, r4)
            r1 = -1
            if (r6 != r1) goto L_0x0030
            r2 = 1
            goto L_0x0046
        L_0x0030:
            int r7 = r11.getFirstVisiblePosition()
            int r7 = r6 - r7
            android.view.View r8 = r11.getChildAt(r7)
            float r5 = (float) r13
            float r9 = (float) r4
            r11.setPressedItem(r8, r6, r5, r9)
            r1 = 1
            if (r0 != r1) goto L_0x000d
            r11.clickPressedItem(r8, r6)
            goto L_0x000d
        L_0x0046:
            if (r3 == 0) goto L_0x004a
            if (r2 == 0) goto L_0x004d
        L_0x004a:
            r11.clearPressedItem()
        L_0x004d:
            if (r3 == 0) goto L_0x0066
            android.support.v4.widget.ListViewAutoScrollHelper r10 = r11.mScrollHelper
            if (r10 != 0) goto L_0x005a
            android.support.v4.widget.ListViewAutoScrollHelper r10 = new android.support.v4.widget.ListViewAutoScrollHelper
            r10.<init>(r11)
            r11.mScrollHelper = r10
        L_0x005a:
            android.support.v4.widget.ListViewAutoScrollHelper r10 = r11.mScrollHelper
            r1 = 1
            r10.setEnabled(r1)
            android.support.v4.widget.ListViewAutoScrollHelper r10 = r11.mScrollHelper
            r10.onTouch(r11, r12)
            return r3
        L_0x0066:
            android.support.v4.widget.ListViewAutoScrollHelper r10 = r11.mScrollHelper
            if (r10 == 0) goto L_0x006e
            r1 = 0
            r10.setEnabled(r1)
        L_0x006e:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ListViewCompat.onForwardedEvent(android.view.MotionEvent, int):boolean");
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        if (Build.VERSION.SDK_INT < 26) {
            return super.onHoverEvent(motionEvent);
        }
        int $i0 = motionEvent.getActionMasked();
        if ($i0 == 10 && this.b == null) {
            this.b = new SnackBar(this);
            this.b.clear();
        }
        boolean $z0 = super.onHoverEvent(motionEvent);
        if ($i0 == 9 || $i0 == 7) {
            int $i02 = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
            if (!($i02 == -1 || $i02 == getSelectedItemPosition())) {
                View $r3 = getChildAt($i02 - getFirstVisiblePosition());
                if ($r3.isEnabled()) {
                    setSelectionFromTop($i02, $r3.getTop() - getTop());
                }
                updateSelectorStateCompat();
            }
            return $z0;
        }
        setSelection(-1);
        return $z0;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.mMotionPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        SnackBar $r2 = this.b;
        if ($r2 != null) {
            $r2.onTouchEvent();
        }
        return super.onTouchEvent(motionEvent);
    }

    /* access modifiers changed from: package-private */
    public void setListSelectionHidden(boolean z) {
        this.mListSelectionHidden = z;
    }

    public void setSelector(Drawable drawable) {
        this.mSelector = drawable != null ? new GateKeeperDrawable(drawable) : null;
        super.setSelector(this.mSelector);
        Rect $r3 = new Rect();
        if (drawable != null) {
            drawable.getPadding($r3);
        }
        this.mSelectionLeftPadding = $r3.left;
        this.mSelectionTopPadding = $r3.top;
        this.mSelectionRightPadding = $r3.right;
        this.mSelectionBottomPadding = $r3.bottom;
    }
}
