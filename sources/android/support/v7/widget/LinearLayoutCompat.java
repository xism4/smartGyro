package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.org.android.view.ViewCompat;
import com.org.v4.util.R$styleable;

public class LinearLayoutCompat extends ViewGroup {
    private boolean mBaselineAligned;
    private int mBaselineAlignedChildIndex;
    private int mBaselineChildTop;
    private Drawable mDivider;
    private int mDividerHeight;
    private int mDividerPadding;
    private int mDividerWidth;
    private int mGravity;
    private int[] mMaxAscent;
    private int[] mMaxDescent;
    private int mOrientation;
    private int mShowDividers;
    private int mTotalLength;
    private boolean mUseLargestChild;
    private float mWeightSum;

    public class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int gravity;
        public float weight;

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.gravity = -1;
            this.weight = 0.0f;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.gravity = -1;
            TypedArray $r4 = context.obtainStyledAttributes(attributeSet, R$styleable.LinearLayoutCompat_Layout);
            this.weight = $r4.getFloat(R$styleable.LinearLayoutCompat_Layout_android_layout_weight, 0.0f);
            this.gravity = $r4.getInt(R$styleable.LinearLayoutCompat_Layout_android_layout_gravity, -1);
            $r4.recycle();
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.gravity = -1;
        }
    }

    public LinearLayoutCompat(Context context) {
        this(context, (AttributeSet) null);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mBaselineAligned = true;
        this.mBaselineAlignedChildIndex = -1;
        this.mBaselineChildTop = 0;
        this.mGravity = 8388659;
        TintTypedArray $r4 = TintTypedArray.obtainStyledAttributes(context, attributeSet, R$styleable.LinearLayoutCompat, i, 0);
        int $i0 = $r4.getInt(R$styleable.LinearLayoutCompat_android_orientation, -1);
        if ($i0 >= 0) {
            setOrientation($i0);
        }
        int $i02 = $r4.getInt(R$styleable.LinearLayoutCompat_android_gravity, -1);
        if ($i02 >= 0) {
            setGravity($i02);
        }
        boolean $z0 = $r4.getBoolean(R$styleable.LinearLayoutCompat_android_baselineAligned, true);
        if (!$z0) {
            setBaselineAligned($z0);
        }
        this.mWeightSum = $r4.getFloat(R$styleable.LinearLayoutCompat_android_weightSum, -1.0f);
        this.mBaselineAlignedChildIndex = $r4.getInt(R$styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.mUseLargestChild = $r4.getBoolean(R$styleable.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable($r4.getDrawable(R$styleable.LinearLayoutCompat_divider));
        this.mShowDividers = $r4.getInt(R$styleable.LinearLayoutCompat_showDividers, 0);
        this.mDividerPadding = $r4.getDimensionPixelSize(R$styleable.LinearLayoutCompat_dividerPadding, 0);
        $r4.recycle();
    }

    private void forceUniformHeight(int i, int i2) {
        int $i3 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
        for (int $i4 = 0; $i4 < i; $i4++) {
            View $r1 = getVirtualChildAt($i4);
            if ($r1.getVisibility() != 8) {
                LayoutParams $r3 = (LayoutParams) $r1.getLayoutParams();
                if ($r3.height == -1) {
                    int $i2 = $r3.width;
                    $r3.width = $r1.getMeasuredWidth();
                    measureChildWithMargins($r1, i2, 0, $i3, 0);
                    $r3.width = $i2;
                }
            }
        }
    }

    private void forceUniformWidth(int i, int i2) {
        int $i3 = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int $i4 = 0; $i4 < i; $i4++) {
            View $r1 = getVirtualChildAt($i4);
            if ($r1.getVisibility() != 8) {
                LayoutParams $r3 = (LayoutParams) $r1.getLayoutParams();
                if ($r3.width == -1) {
                    int $i2 = $r3.height;
                    $r3.height = $r1.getMeasuredHeight();
                    measureChildWithMargins($r1, $i3, 0, i2, 0);
                    $r3.height = $i2;
                }
            }
        }
    }

    private void setChildFrame(View view, int i, int i2, int i3, int i4) {
        view.layout(i, i2, i3 + i, i4 + i2);
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    /* access modifiers changed from: package-private */
    public void drawDividersHorizontal(Canvas canvas) {
        int $i0;
        int $i1;
        int $i02;
        int $i03 = getVirtualChildCount();
        boolean $z0 = ViewUtils.isLayoutRtl(this);
        for (int $i12 = 0; $i12 < $i03; $i12++) {
            View $r2 = getVirtualChildAt($i12);
            if (!($r2 == null || $r2.getVisibility() == 8 || !hasDividerBeforeChildAt($i12))) {
                LayoutParams $r4 = (LayoutParams) $r2.getLayoutParams();
                drawVerticalDivider(canvas, $z0 ? $r2.getRight() + $r4.rightMargin : ($r2.getLeft() - $r4.leftMargin) - this.mDividerWidth);
            }
        }
        if (hasDividerBeforeChildAt($i03)) {
            View $r22 = getVirtualChildAt($i03 - 1);
            if ($r22 != null) {
                LayoutParams $r42 = (LayoutParams) $r22.getLayoutParams();
                if ($z0) {
                    $i02 = $r22.getLeft();
                    $i1 = $r42.leftMargin;
                } else {
                    $i0 = $r22.getRight() + $r42.rightMargin;
                    drawVerticalDivider(canvas, $i0);
                }
            } else if ($z0) {
                $i0 = getPaddingLeft();
                drawVerticalDivider(canvas, $i0);
            } else {
                $i02 = getWidth();
                $i1 = getPaddingRight();
            }
            $i0 = ($i02 - $i1) - this.mDividerWidth;
            drawVerticalDivider(canvas, $i0);
        }
    }

    /* access modifiers changed from: package-private */
    public void drawDividersVertical(Canvas canvas) {
        int $i0 = getVirtualChildCount();
        for (int $i1 = 0; $i1 < $i0; $i1++) {
            View $r2 = getVirtualChildAt($i1);
            if (!($r2 == null || $r2.getVisibility() == 8 || !hasDividerBeforeChildAt($i1))) {
                drawHorizontalDivider(canvas, ($r2.getTop() - ((LayoutParams) $r2.getLayoutParams()).topMargin) - this.mDividerHeight);
            }
        }
        if (hasDividerBeforeChildAt($i0)) {
            View $r22 = getVirtualChildAt($i0 - 1);
            drawHorizontalDivider(canvas, $r22 == null ? (getHeight() - getPaddingBottom()) - this.mDividerHeight : $r22.getBottom() + ((LayoutParams) $r22.getLayoutParams()).bottomMargin);
        }
    }

    /* access modifiers changed from: package-private */
    public void drawHorizontalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(getPaddingLeft() + this.mDividerPadding, i, (getWidth() - getPaddingRight()) - this.mDividerPadding, this.mDividerHeight + i);
        this.mDivider.draw(canvas);
    }

    /* access modifiers changed from: package-private */
    public void drawVerticalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(i, getPaddingTop() + this.mDividerPadding, this.mDividerWidth + i, (getHeight() - getPaddingBottom()) - this.mDividerPadding);
        this.mDivider.draw(canvas);
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateDefaultLayoutParams() {
        int $i0 = this.mOrientation;
        if ($i0 == 0) {
            return new LayoutParams(-2, -2);
        }
        if ($i0 == 1) {
            return new LayoutParams(-1, -2);
        }
        return null;
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public int getBaseline() {
        int $i2;
        if (this.mBaselineAlignedChildIndex < 0) {
            return super.getBaseline();
        }
        int $i0 = getChildCount();
        int $i1 = this.mBaselineAlignedChildIndex;
        if ($i0 > $i1) {
            View $r1 = getChildAt($i1);
            int $i02 = $r1.getBaseline();
            if ($i02 != -1) {
                int $i12 = this.mBaselineChildTop;
                if (this.mOrientation == 1 && ($i2 = this.mGravity & 112) != 48) {
                    if ($i2 == 16) {
                        $i12 += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.mTotalLength) / 2;
                    } else if ($i2 == 80) {
                        $i12 = ((getBottom() - getTop()) - getPaddingBottom()) - this.mTotalLength;
                    }
                }
                return $i12 + ((LayoutParams) $r1.getLayoutParams()).topMargin + $i02;
            } else if (this.mBaselineAlignedChildIndex == 0) {
                return -1;
            } else {
                throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
            }
        } else {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
    }

    public int getBaselineAlignedChildIndex() {
        return this.mBaselineAlignedChildIndex;
    }

    /* access modifiers changed from: package-private */
    public int getChildrenSkipCount(View view, int i) {
        return 0;
    }

    public Drawable getDividerDrawable() {
        return this.mDivider;
    }

    public int getDividerPadding() {
        return this.mDividerPadding;
    }

    public int getDividerWidth() {
        return this.mDividerWidth;
    }

    public int getGravity() {
        return this.mGravity;
    }

    /* access modifiers changed from: package-private */
    public int getLocationOffset(View view) {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public int getNextLocationOffset(View view) {
        return 0;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public int getShowDividers() {
        return this.mShowDividers;
    }

    /* access modifiers changed from: package-private */
    public View getVirtualChildAt(int i) {
        return getChildAt(i);
    }

    /* access modifiers changed from: package-private */
    public int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.mWeightSum;
    }

    /* access modifiers changed from: protected */
    public boolean hasDividerBeforeChildAt(int $i0) {
        if ($i0 == 0) {
            return (this.mShowDividers & 1) != 0;
        }
        if ($i0 == getChildCount()) {
            return (this.mShowDividers & 4) != 0;
        }
        if ((this.mShowDividers & 2) == 0) {
            return false;
        }
        for (int $i02 = $i0 - 1; $i02 >= 0; $i02--) {
            if (getChildAt($i02).getVisibility() != 8) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void layoutHorizontal(int $i3, int i, int $i1, int $i2) {
        int $i32;
        int i2;
        int $i12;
        boolean $z1 = ViewUtils.isLayoutRtl(this);
        int $i4 = getPaddingTop();
        int $i5 = $i4;
        int $i22 = $i2 - i;
        int $i0 = $i22 - getPaddingBottom();
        int paddingBottom = ($i22 - $i4) - getPaddingBottom();
        int $i42 = getVirtualChildCount();
        int $i6 = $i42;
        int $i7 = this.mGravity;
        int $i8 = 8388615 & $i7;
        int $i72 = $i7 & 112;
        boolean $z0 = this.mBaselineAligned;
        int[] $r1 = this.mMaxAscent;
        int[] $r2 = this.mMaxDescent;
        int $i82 = com.org.android.view.View.getAbsoluteGravity($i8, ViewCompat.getLayoutDirection(this));
        int $i83 = $i82 != 1 ? $i82 != 5 ? getPaddingLeft() : ((getPaddingLeft() + $i1) - $i3) - this.mTotalLength : getPaddingLeft() + ((($i1 - $i3) - this.mTotalLength) / 2);
        if ($z1) {
            $i32 = $i42 - 1;
            i2 = -1;
        } else {
            $i32 = 0;
            i2 = 1;
        }
        int $i43 = 0;
        while ($i43 < $i6) {
            int $i13 = $i32 + (i2 * $i43);
            View $r3 = getVirtualChildAt($i13);
            if ($r3 == null) {
                $i83 += measureNullChild($i13);
            } else if ($r3.getVisibility() != 8) {
                int $i9 = $r3.getMeasuredWidth();
                int $i11 = $r3.getMeasuredHeight();
                LayoutParams $r5 = (LayoutParams) $r3.getLayoutParams();
                int $i122 = (!$z0 || $r5.height == -1) ? -1 : $r3.getBaseline();
                int $i132 = $r5.gravity;
                if ($i132 < 0) {
                    $i132 = $i72;
                }
                int $i133 = $i132 & 112;
                if ($i133 == 16) {
                    $i12 = ((((paddingBottom - $i11) / 2) + $i5) + $r5.topMargin) - $r5.bottomMargin;
                } else if ($i133 == 48) {
                    int $i134 = $r5.topMargin;
                    int i3 = $i134;
                    int $i135 = $i134 + $i5;
                    if ($i122 != -1) {
                        $i135 += $r1[1] - $i122;
                    }
                    $i12 = $i135;
                } else if ($i133 != 80) {
                    $i12 = $i5;
                } else {
                    int $i136 = ($i0 - $i11) - $r5.bottomMargin;
                    if ($i122 != -1) {
                        $i136 -= $r2[2] - ($r3.getMeasuredHeight() - $i122);
                    }
                    $i12 = $i136;
                }
                if (hasDividerBeforeChildAt($i13)) {
                    int $i137 = this.mDividerWidth;
                    int i4 = $i137;
                    $i83 += $i137;
                }
                int $i84 = $r5.leftMargin + $i83;
                setChildFrame($r3, $i84 + getLocationOffset($r3), $i12, $i9, $i11);
                $i43 += getChildrenSkipCount($r3, $i13);
                $i83 = $i84 + $i9 + $r5.rightMargin + getNextLocationOffset($r3);
            }
            $i43++;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00ff  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void layoutVertical(int r20, int r21, int r22, int r23) {
        /*
            r19 = this;
            r0 = r19
            int r6 = r0.getPaddingLeft()
            r0 = r22
            r1 = r20
            int r0 = r0 - r1
            r22 = r0
            r0 = r19
            int r20 = r0.getPaddingRight()
            int r20 = r22 - r20
            r0 = r22
            int r0 = r0 - r6
            r22 = r0
            r0 = r19
            int r7 = r0.getPaddingRight()
            r0 = r22
            int r0 = r0 - r7
            r22 = r0
            r0 = r19
            int r7 = r0.getVirtualChildCount()
            r0 = r19
            int r8 = r0.mGravity
            r9 = r8 & 112(0x70, float:1.57E-43)
            r10 = 8388615(0x800007, float:1.1754953E-38)
            r8 = r8 & r10
            r10 = 16
            if (r9 == r10) goto L_0x0057
            r10 = 80
            if (r9 == r10) goto L_0x0044
            r0 = r19
            int r11 = r0.getPaddingTop()
            goto L_0x0070
        L_0x0044:
            r0 = r19
            int r9 = r0.getPaddingTop()
            int r23 = r9 + r23
            int r21 = r23 - r21
            r0 = r19
            int r0 = r0.mTotalLength
            r23 = r0
            int r11 = r21 - r23
            goto L_0x0070
        L_0x0057:
            r0 = r19
            int r9 = r0.getPaddingTop()
            int r21 = r23 - r21
            r0 = r19
            int r0 = r0.mTotalLength
            r23 = r0
            r0 = r21
            r1 = r23
            int r0 = r0 - r1
            r21 = r0
            int r21 = r21 / 2
            int r11 = r9 + r21
        L_0x0070:
            r21 = 0
        L_0x0072:
            r0 = r21
            if (r0 >= r7) goto L_0x0147
            r0 = r19
            r1 = r21
            android.view.View r12 = r0.getVirtualChildAt(r1)
            if (r12 != 0) goto L_0x008e
            r0 = r19
            r1 = r21
            int r23 = r0.measureNullChild(r1)
            r0 = r23
            int r11 = r11 + r0
            goto L_0x0142
        L_0x008e:
            int r23 = r12.getVisibility()
            r10 = 8
            r0 = r23
            if (r0 == r10) goto L_0x0142
            int r9 = r12.getMeasuredWidth()
            int r23 = r12.getMeasuredHeight()
            android.view.ViewGroup$LayoutParams r13 = r12.getLayoutParams()
            r15 = r13
            android.support.v7.widget.LinearLayoutCompat$LayoutParams r15 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r15
            r14 = r15
            int r0 = r14.gravity
            r16 = r0
            if (r16 >= 0) goto L_0x00b0
            r16 = r8
        L_0x00b0:
            r0 = r19
            int r17 = com.org.android.view.ViewCompat.getLayoutDirection(r0)
            r0 = r16
            r1 = r17
            int r16 = com.org.android.view.View.getAbsoluteGravity(r0, r1)
            r16 = r16 & 7
            r10 = 1
            r0 = r16
            if (r0 == r10) goto L_0x00d5
            r10 = 5
            r0 = r16
            if (r0 == r10) goto L_0x00d2
            int r0 = r14.leftMargin
            r16 = r0
            int r0 = r0 + r6
            r16 = r0
        L_0x00d1:
            goto L_0x00f5
        L_0x00d2:
            int r16 = r20 - r9
            goto L_0x00e9
        L_0x00d5:
            int r16 = r22 - r9
            int r16 = r16 / 2
            r0 = r16
            int r0 = r0 + r6
            r16 = r0
            int r0 = r14.leftMargin
            r17 = r0
            r0 = r16
            r1 = r17
            int r0 = r0 + r1
            r16 = r0
        L_0x00e9:
            int r0 = r14.rightMargin
            r17 = r0
            r0 = r16
            r1 = r17
            int r0 = r0 - r1
            r16 = r0
            goto L_0x00d1
        L_0x00f5:
            r0 = r19
            r1 = r21
            boolean r18 = r0.hasDividerBeforeChildAt(r1)
            if (r18 == 0) goto L_0x0106
            r0 = r19
            int r0 = r0.mDividerHeight
            r17 = r0
            int r11 = r11 + r0
        L_0x0106:
            int r0 = r14.topMargin
            r17 = r0
            int r11 = r11 + r0
            r0 = r19
            int r17 = r0.getLocationOffset(r12)
            int r17 = r11 + r17
            r0 = r19
            r1 = r12
            r2 = r16
            r3 = r17
            r4 = r9
            r5 = r23
            r0.setChildFrame(r1, r2, r3, r4, r5)
            int r9 = r14.bottomMargin
            r0 = r23
            int r0 = r0 + r9
            r23 = r0
            r0 = r19
            int r9 = r0.getNextLocationOffset(r12)
            r0 = r23
            int r0 = r0 + r9
            r23 = r0
            r0 = r19
            r1 = r21
            int r9 = r0.getChildrenSkipCount(r12, r1)
            r0 = r21
            int r0 = r0 + r9
            r21 = r0
            r0 = r23
            int r11 = r11 + r0
        L_0x0142:
            int r21 = r21 + 1
            goto L_0x0072
        L_0x0147:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.LinearLayoutCompat.layoutVertical(int, int, int, int):void");
    }

    /* access modifiers changed from: package-private */
    public void measureChildBeforeLayout(View view, int i, int i2, int i3, int i4, int i5) {
        measureChildWithMargins(view, i2, i3, i4, i5);
    }

    /* access modifiers changed from: package-private */
    public void measureHorizontal(int i, int $i13) {
        int $i8;
        int $i7;
        int $i4;
        int $i3;
        int $i12;
        int $i6;
        int $i11;
        View $r6;
        int $i122;
        boolean $z6;
        int $i132;
        int $i133;
        int $i123;
        this.mTotalLength = 0;
        int $i2 = getVirtualChildCount();
        int $i32 = $i2;
        int $i42 = View.MeasureSpec.getMode(i);
        int $i5 = View.MeasureSpec.getMode($i13);
        if (this.mMaxAscent == null || this.mMaxDescent == null) {
            this.mMaxAscent = new int[4];
            this.mMaxDescent = new int[4];
        }
        int[] $r1 = this.mMaxAscent;
        int[] $r2 = this.mMaxDescent;
        $r1[3] = -1;
        $r1[2] = -1;
        $r1[1] = -1;
        $r1[0] = -1;
        $r2[3] = -1;
        $r2[2] = -1;
        $r2[1] = -1;
        $r2[0] = -1;
        boolean $z0 = this.mBaselineAligned;
        boolean $z1 = this.mUseLargestChild;
        boolean z = $i42 == 1073741824;
        float $f0 = 0.0f;
        int $i62 = 0;
        int $i72 = 0;
        int $i82 = 0;
        int $i9 = 0;
        int $i10 = 0;
        boolean z2 = false;
        int $i112 = 0;
        boolean $z4 = true;
        boolean z3 = false;
        while ($i62 < $i2) {
            View $r3 = getVirtualChildAt($i62);
            if ($r3 == null) {
                int $i124 = this.mTotalLength + measureNullChild($i62);
                int i2 = $i124;
                this.mTotalLength = $i124;
            } else if ($r3.getVisibility() == 8) {
                $i62 += getChildrenSkipCount($r3, $i62);
            } else {
                if (hasDividerBeforeChildAt($i62)) {
                    int $i125 = this.mTotalLength + this.mDividerWidth;
                    int i3 = $i125;
                    this.mTotalLength = $i125;
                }
                LayoutParams $r5 = (LayoutParams) $r3.getLayoutParams();
                $f0 += $r5.weight;
                if ($i42 == 1073741824 && $r5.width == 0 && $r5.weight > 0.0f) {
                    if (z) {
                        $i123 = this.mTotalLength + $r5.leftMargin + $r5.rightMargin;
                    } else {
                        int $i126 = this.mTotalLength;
                        int $i134 = $r5.leftMargin;
                        int i4 = $i134;
                        $i123 = Math.max($i126, $i134 + $i126 + $r5.rightMargin);
                    }
                    this.mTotalLength = $i123;
                    if ($z0) {
                        int $i127 = View.MeasureSpec.makeMeasureSpec(0, 0);
                        $r3.measure($i127, $i127);
                        $i122 = $i62;
                        $r6 = $r3;
                    } else {
                        $i122 = $i62;
                        $r6 = $r3;
                        z2 = true;
                    }
                } else {
                    if ($r5.width != 0 || $r5.weight <= 0.0f) {
                        $i132 = Integer.MIN_VALUE;
                    } else {
                        $r5.width = -2;
                        $i132 = 0;
                    }
                    $i122 = $i62;
                    measureChildBeforeLayout($r3, $i62, i, $f0 == 0.0f ? this.mTotalLength : 0, $i13, 0);
                    if ($i132 != Integer.MIN_VALUE) {
                        $r5.width = $i132;
                    }
                    int $i63 = $r3.getMeasuredWidth();
                    if (z) {
                        int $i135 = this.mTotalLength;
                        int $i15 = $r5.leftMargin;
                        int i5 = $i15;
                        $r6 = $r3;
                        $i133 = $i135 + $i15 + $i63 + $r5.rightMargin + getNextLocationOffset($r3);
                    } else {
                        $r6 = $r3;
                        int $i136 = this.mTotalLength;
                        $i133 = Math.max($i136, $i136 + $i63 + $r5.leftMargin + $r5.rightMargin + getNextLocationOffset($r3));
                    }
                    this.mTotalLength = $i133;
                    if ($z1) {
                        $i72 = Math.max($i63, $i72);
                    }
                }
                if ($i5 == 1073741824 || $r5.height != -1) {
                    $z6 = false;
                } else {
                    $z6 = true;
                    z3 = true;
                }
                int $i64 = $r5.topMargin + $r5.bottomMargin;
                int $i137 = $r6.getMeasuredHeight() + $i64;
                $i112 = View.combineMeasuredStates($i112, $r6.getMeasuredState());
                if ($z0) {
                    int $i152 = $r6.getBaseline();
                    if ($i152 != -1) {
                        int $i16 = $r5.gravity;
                        if ($i16 < 0) {
                            $i16 = this.mGravity;
                        }
                        int $i162 = ((($i16 & 112) >> 4) & -2) >> 1;
                        $r1[$i162] = Math.max($r1[$i162], $i152);
                        $r2[$i162] = Math.max($r2[$i162], $i137 - $i152);
                    }
                }
                $i82 = Math.max($i82, $i137);
                $z4 = $z4 && $r5.height == -1;
                if ($r5.weight > 0.0f) {
                    if (!$z6) {
                        $i64 = $i137;
                    }
                    $i10 = Math.max($i10, $i64);
                } else {
                    if ($z6) {
                        $i137 = $i64;
                    }
                    $i9 = Math.max($i9, $i137);
                }
                $i62 = getChildrenSkipCount($r6, $i122) + $i122;
            }
            $i62++;
        }
        int $i65 = $i82;
        int $i128 = $i9;
        int $i92 = $i112;
        if (this.mTotalLength > 0 && hasDividerBeforeChildAt($i2)) {
            int $i83 = this.mTotalLength + this.mDividerWidth;
            int i6 = $i83;
            this.mTotalLength = $i83;
        }
        if ($r1[1] == -1 && $r1[0] == -1 && $r1[2] == -1 && $r1[3] == -1) {
            $i8 = $i92;
        } else {
            $i8 = $i92;
            $i65 = Math.max($i65, Math.max($r1[3], Math.max($r1[0], Math.max($r1[1], $r1[2]))) + Math.max($r2[3], Math.max($r2[0], Math.max($r2[1], $r2[2]))));
        }
        if ($z1 && ($i42 == Integer.MIN_VALUE || $i42 == 0)) {
            this.mTotalLength = 0;
            int $i93 = 0;
            while ($i93 < $i2) {
                View $r32 = getVirtualChildAt($i93);
                if ($r32 == null) {
                    int $i113 = this.mTotalLength + measureNullChild($i93);
                    int i7 = $i113;
                    this.mTotalLength = $i113;
                } else if ($r32.getVisibility() == 8) {
                    $i93 += getChildrenSkipCount($r32, $i93);
                } else {
                    LayoutParams $r52 = (LayoutParams) $r32.getLayoutParams();
                    if (z) {
                        int $i114 = this.mTotalLength;
                        int $i138 = $r52.leftMargin;
                        int i8 = $i138;
                        int $i115 = $i114 + $i138 + $i72 + $r52.rightMargin + getNextLocationOffset($r32);
                        int i9 = $i115;
                        this.mTotalLength = $i115;
                    } else {
                        int $i139 = this.mTotalLength;
                        $i11 = $i65;
                        this.mTotalLength = Math.max($i139, $i139 + $i72 + $r52.leftMargin + $r52.rightMargin + getNextLocationOffset($r32));
                        $i93++;
                        $i65 = $i11;
                    }
                }
                $i11 = $i65;
                $i93++;
                $i65 = $i11;
            }
        }
        int $i116 = $i65;
        int $i94 = this.mTotalLength + getPaddingLeft() + getPaddingRight();
        int i10 = $i94;
        this.mTotalLength = $i94;
        int $i95 = View.resolveSizeAndState(Math.max(this.mTotalLength, getSuggestedMinimumWidth()), i, 0);
        int $i66 = (16777215 & $i95) - this.mTotalLength;
        if (z2 || ($i66 != 0 && $f0 > 0.0f)) {
            float $f1 = this.mWeightSum;
            if ($f1 > 0.0f) {
                $f0 = $f1;
            }
            $r1[3] = -1;
            $r1[2] = -1;
            $r1[1] = -1;
            $r1[0] = -1;
            $r2[3] = -1;
            $r2[2] = -1;
            $r2[1] = -1;
            $r2[0] = -1;
            this.mTotalLength = 0;
            int $i102 = $i128;
            int $i22 = $i8;
            int $i73 = -1;
            int $i84 = 0;
            while ($i84 < $i32) {
                View $r33 = getVirtualChildAt($i84);
                if ($r33 == null || $r33.getVisibility() == 8) {
                    $i12 = $i66;
                } else {
                    LayoutParams $r53 = (LayoutParams) $r33.getLayoutParams();
                    float $f12 = $r53.weight;
                    if ($f12 > 0.0f) {
                        float $f2 = (float) $i66;
                        float f = $f2;
                        float $f22 = $f2 * $f12;
                        float f2 = $f22;
                        float $f23 = $f22 / $f0;
                        float f3 = $f23;
                        int $i117 = (int) $f23;
                        $f0 -= $f12;
                        $i12 = $i66 - $i117;
                        int $i67 = ViewGroup.getChildMeasureSpec($i13, getPaddingTop() + getPaddingBottom() + $r53.topMargin + $r53.bottomMargin, $r53.height);
                        if (!($r53.width == 0 && $i42 == 1073741824) ? ($i117 = $r33.getMeasuredWidth() + $i117) >= 0 : $i117 > 0) {
                            $i117 = 0;
                        }
                        $r33.measure(View.MeasureSpec.makeMeasureSpec($i117, 1073741824), $i67);
                        int $i118 = $r33.getMeasuredState() & -16777216;
                        int i11 = $i118;
                        $i22 = View.combineMeasuredStates($i22, $i118);
                    } else {
                        $i12 = $i66;
                    }
                    if (z) {
                        int $i119 = this.mTotalLength + $r33.getMeasuredWidth() + $r53.leftMargin + $r53.rightMargin + getNextLocationOffset($r33);
                        int i12 = $i119;
                        this.mTotalLength = $i119;
                    } else {
                        int $i1110 = this.mTotalLength;
                        this.mTotalLength = Math.max($i1110, $r33.getMeasuredWidth() + $i1110 + $r53.leftMargin + $r53.rightMargin + getNextLocationOffset($r33));
                    }
                    boolean z4 = $i5 != 1073741824 && $r53.height == -1;
                    int $i68 = $r53.topMargin + $r53.bottomMargin;
                    int measuredHeight = $r33.getMeasuredHeight() + $i68;
                    $i73 = Math.max($i73, measuredHeight);
                    if (!z4) {
                        $i68 = measuredHeight;
                    }
                    $i102 = Math.max($i102, $i68);
                    boolean z5 = $z4 && $r53.height == -1;
                    if ($z0 && ($i6 = $r33.getBaseline()) != -1) {
                        int $i1310 = $r53.gravity;
                        if ($i1310 < 0) {
                            $i1310 = this.mGravity;
                        }
                        int $i1311 = ((($i1310 & 112) >> 4) & -2) >> 1;
                        $r1[$i1311] = Math.max($r1[$i1311], $i6);
                        $r2[$i1311] = Math.max($r2[$i1311], measuredHeight - $i6);
                    }
                    $z4 = z5;
                }
                $i84++;
                $i66 = $i12;
            }
            $i4 = $i32;
            int $i33 = this.mTotalLength;
            int $i85 = getPaddingLeft() + getPaddingRight();
            int i13 = $i85;
            this.mTotalLength = $i33 + $i85;
            $i7 = ($r1[1] == -1 && $r1[0] == -1 && $r1[2] == -1 && $r1[3] == -1) ? $i73 : Math.max($i73, Math.max($r1[3], Math.max($r1[0], Math.max($r1[1], $r1[2]))) + Math.max($r2[3], Math.max($r2[0], Math.max($r2[1], $r2[2]))));
            $i8 = $i22;
            $i3 = $i102;
        } else {
            $i3 = Math.max($i128, $i10);
            if ($z1 && $i42 != 1073741824) {
                for (int $i43 = 0; $i43 < $i2; $i43++) {
                    View $r34 = getVirtualChildAt($i43);
                    if (!($r34 == null || $r34.getVisibility() == 8 || ((LayoutParams) $r34.getLayoutParams()).weight <= 0.0f)) {
                        $r34.measure(View.MeasureSpec.makeMeasureSpec($i72, 1073741824), View.MeasureSpec.makeMeasureSpec($r34.getMeasuredHeight(), 1073741824));
                    }
                }
            }
            $i4 = $i2;
            $i7 = $i116;
        }
        if ($z4 || $i5 == 1073741824) {
            $i3 = $i7;
        }
        setMeasuredDimension($i95 | ($i8 & -16777216), View.resolveSizeAndState(Math.max($i3 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), $i13, $i8 << 16));
        if (z3) {
            forceUniformHeight($i4, i);
        }
    }

    /* access modifiers changed from: package-private */
    public int measureNullChild(int i) {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public void measureVertical(int $i9, int i) {
        int $i4;
        int $i3;
        int $i5;
        int $i8;
        int $i11;
        int $i13;
        int $i32;
        View $r4;
        int $i14;
        int $i92;
        boolean $z4;
        int $i6;
        int $i132;
        this.mTotalLength = 0;
        int $i33 = getVirtualChildCount();
        int $i2 = View.MeasureSpec.getMode($i9);
        int $i52 = View.MeasureSpec.getMode(i);
        int $i42 = this.mBaselineAlignedChildIndex;
        boolean $z0 = this.mUseLargestChild;
        float $f0 = 0.0f;
        int $i62 = 0;
        int $i7 = 0;
        int $i82 = 0;
        int $i93 = 0;
        int $i10 = 0;
        int $i112 = 0;
        boolean z = false;
        boolean $z2 = true;
        boolean z2 = false;
        while (true) {
            int $i12 = $i93;
            if ($i112 < $i33) {
                View $r1 = getVirtualChildAt($i112);
                if ($r1 == null) {
                    int $i133 = this.mTotalLength + measureNullChild($i112);
                    int i2 = $i133;
                    this.mTotalLength = $i133;
                    $i13 = $i33;
                } else {
                    int $i142 = $i62;
                    if ($r1.getVisibility() == 8) {
                        $i112 += getChildrenSkipCount($r1, $i112);
                        $i13 = $i33;
                    } else {
                        if (hasDividerBeforeChildAt($i112)) {
                            int $i134 = this.mTotalLength + this.mDividerHeight;
                            int i3 = $i134;
                            this.mTotalLength = $i134;
                        }
                        LayoutParams $r3 = (LayoutParams) $r1.getLayoutParams();
                        float $f1 = $r3.weight;
                        float f = $f1;
                        $f0 += $f1;
                        if ($i52 == 1073741824 && $r3.height == 0 && $r3.weight > 0.0f) {
                            int $i135 = this.mTotalLength;
                            int $i94 = $r3.topMargin;
                            int i4 = $i94;
                            this.mTotalLength = Math.max($i135, $i94 + $i135 + $r3.bottomMargin);
                            $r4 = $r1;
                            $i132 = $i33;
                            $i92 = $i142;
                            z = true;
                            $i14 = $i112;
                            $i32 = $i52;
                        } else {
                            int $i136 = $i7;
                            if ($r3.height != 0 || $r3.weight <= 0.0f) {
                                $i6 = Integer.MIN_VALUE;
                            } else {
                                $r3.height = -2;
                                $i6 = 0;
                            }
                            $i92 = $i142;
                            $i7 = $i136;
                            $i132 = $i33;
                            $i32 = $i52;
                            $i14 = $i112;
                            measureChildBeforeLayout($r1, $i112, $i9, 0, i, $f0 == 0.0f ? this.mTotalLength : 0);
                            if ($i6 != Integer.MIN_VALUE) {
                                $r3.height = $i6;
                            }
                            int $i53 = $r1.getMeasuredHeight();
                            int $i113 = this.mTotalLength;
                            int $i16 = $r3.topMargin;
                            int i5 = $i16;
                            int $i63 = $i113 + $i53 + $i16;
                            int $i162 = $r3.bottomMargin;
                            int i6 = $i162;
                            $r4 = $r1;
                            this.mTotalLength = Math.max($i113, $i63 + $i162 + getNextLocationOffset($r1));
                            if ($z0) {
                                $i82 = Math.max($i53, $i82);
                            }
                        }
                        if ($i42 >= 0 && $i42 == $i14 + 1) {
                            this.mBaselineChildTop = this.mTotalLength;
                        }
                        if ($i14 >= $i42 || $r3.weight <= 0.0f) {
                            if ($i2 == 1073741824 || $r3.width != -1) {
                                $z4 = false;
                            } else {
                                $z4 = true;
                                z2 = true;
                            }
                            int $i64 = $r3.leftMargin + $r3.rightMargin;
                            int $i163 = $r4.getMeasuredWidth() + $i64;
                            int $i54 = Math.max($i7, $i163);
                            int $i114 = View.combineMeasuredStates($i92, $r4.getMeasuredState());
                            $z2 = $z2 && $r3.width == -1;
                            if ($r3.weight > 0.0f) {
                                if (!$z4) {
                                    $i64 = $i163;
                                }
                                $i12 = Math.max($i12, $i64);
                            } else {
                                if (!$z4) {
                                    $i64 = $i163;
                                }
                                $i10 = Math.max($i10, $i64);
                            }
                            $i93 = $i12;
                            $i62 = $i114;
                            $i112 = getChildrenSkipCount($r4, $i14) + $i14;
                            $i7 = $i54;
                            $i112++;
                            $i52 = $i32;
                            $i33 = $i13;
                        } else {
                            throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.");
                        }
                    }
                }
                $i32 = $i52;
                $i112++;
                $i52 = $i32;
                $i33 = $i13;
            } else {
                int $i143 = $i62;
                int $i122 = $i82;
                int $i83 = $i10;
                int $i137 = $i7;
                int $i102 = $i52;
                int $i55 = $i93;
                if (this.mTotalLength > 0) {
                    $i4 = $i33;
                    if (hasDividerBeforeChildAt($i33)) {
                        int $i34 = this.mTotalLength;
                        int $i115 = this.mDividerHeight;
                        int i7 = $i115;
                        this.mTotalLength = $i34 + $i115;
                    }
                } else {
                    $i4 = $i33;
                }
                if ($z0) {
                    $i3 = $i102;
                    if ($i102 == Integer.MIN_VALUE || $i102 == 0) {
                        this.mTotalLength = 0;
                        int $i103 = 0;
                        while ($i103 < $i4) {
                            View $r12 = getVirtualChildAt($i103);
                            if ($r12 == null) {
                                $i11 = this.mTotalLength + measureNullChild($i103);
                            } else if ($r12.getVisibility() == 8) {
                                $i103 += getChildrenSkipCount($r12, $i103);
                                $i103++;
                            } else {
                                LayoutParams $r32 = (LayoutParams) $r12.getLayoutParams();
                                int $i116 = this.mTotalLength;
                                $i11 = Math.max($i116, $i116 + $i122 + $r32.topMargin + $r32.bottomMargin + getNextLocationOffset($r12));
                            }
                            this.mTotalLength = $i11;
                            $i103++;
                        }
                    }
                } else {
                    $i3 = $i102;
                }
                int $i104 = this.mTotalLength + getPaddingTop() + getPaddingBottom();
                int i8 = $i104;
                this.mTotalLength = $i104;
                int $i105 = View.resolveSizeAndState(Math.max(this.mTotalLength, getSuggestedMinimumHeight()), i, 0);
                int $i95 = (16777215 & $i105) - this.mTotalLength;
                if (z || ($i95 != 0 && $f0 > 0.0f)) {
                    float $f12 = this.mWeightSum;
                    if ($f12 > 0.0f) {
                        $f0 = $f12;
                    }
                    this.mTotalLength = 0;
                    int $i123 = $i83;
                    for (int $i56 = 0; $i56 < $i4; $i56++) {
                        View $r13 = getVirtualChildAt($i56);
                        if ($r13.getVisibility() != 8) {
                            LayoutParams $r33 = (LayoutParams) $r13.getLayoutParams();
                            float $f13 = $r33.weight;
                            if ($f13 > 0.0f) {
                                float $f2 = (float) $i95;
                                float f2 = $f2;
                                float $f22 = $f2 * $f13;
                                float f3 = $f22;
                                float $f23 = $f22 / $f0;
                                float f4 = $f23;
                                int $i117 = (int) $f23;
                                $i8 = $i95 - $i117;
                                $f0 -= $f13;
                                int $i96 = ViewGroup.getChildMeasureSpec($i9, getPaddingLeft() + getPaddingRight() + $r33.leftMargin + $r33.rightMargin, $r33.width);
                                if (!($r33.height == 0 && $i3 == 1073741824) ? ($i117 = $r13.getMeasuredHeight() + $i117) >= 0 : $i117 > 0) {
                                    $i117 = 0;
                                }
                                $r13.measure($i96, View.MeasureSpec.makeMeasureSpec($i117, 1073741824));
                                $i143 = View.combineMeasuredStates($i143, $r13.getMeasuredState() & -256);
                            } else {
                                $i8 = $i95;
                            }
                            int i9 = $r33.leftMargin + $r33.rightMargin;
                            int measuredWidth = $r13.getMeasuredWidth() + i9;
                            $i137 = Math.max($i137, measuredWidth);
                            if (!($i2 != 1073741824 && $r33.width == -1)) {
                                i9 = measuredWidth;
                            }
                            $i123 = Math.max($i123, i9);
                            boolean $z02 = $z2 && $r33.width == -1;
                            int $i118 = this.mTotalLength;
                            this.mTotalLength = Math.max($i118, $r13.getMeasuredHeight() + $i118 + $r33.topMargin + $r33.bottomMargin + getNextLocationOffset($r13));
                            $z2 = $z02;
                            $i95 = $i8;
                        }
                    }
                    this.mTotalLength += getPaddingTop() + getPaddingBottom();
                    $i5 = $i123;
                } else {
                    $i5 = Math.max($i83, $i55);
                    if ($z0 && $i3 != 1073741824) {
                        for (int $i35 = 0; $i35 < $i4; $i35++) {
                            View $r14 = getVirtualChildAt($i35);
                            if (!($r14 == null || $r14.getVisibility() == 8 || ((LayoutParams) $r14.getLayoutParams()).weight <= 0.0f)) {
                                $r14.measure(View.MeasureSpec.makeMeasureSpec($r14.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec($i122, 1073741824));
                            }
                        }
                    }
                }
                if ($z2 || $i2 == 1073741824) {
                    $i5 = $i137;
                }
                setMeasuredDimension(View.resolveSizeAndState(Math.max($i5 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), $i9, $i143), $i105);
                if (z2) {
                    forceUniformWidth($i4, i);
                    return;
                }
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.mDivider != null) {
            if (this.mOrientation == 1) {
                drawDividersVertical(canvas);
            } else {
                drawDividersHorizontal(canvas);
            }
        }
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(Q.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(Q.class.getName());
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.mOrientation == 1) {
            layoutVertical(i, i2, i3, i4);
        } else {
            layoutHorizontal(i, i2, i3, i4);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (this.mOrientation == 1) {
            measureVertical(i, i2);
        } else {
            measureHorizontal(i, i2);
        }
    }

    public void setBaselineAligned(boolean z) {
        this.mBaselineAligned = z;
    }

    public void setBaselineAlignedChildIndex(int i) {
        if (i < 0 || i >= getChildCount()) {
            throw new IllegalArgumentException("base aligned child index out of range (0, " + getChildCount() + ")");
        }
        this.mBaselineAlignedChildIndex = i;
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable != this.mDivider) {
            this.mDivider = drawable;
            boolean $z0 = false;
            if (drawable != null) {
                this.mDividerWidth = drawable.getIntrinsicWidth();
                this.mDividerHeight = drawable.getIntrinsicHeight();
            } else {
                this.mDividerWidth = 0;
                this.mDividerHeight = 0;
            }
            if (drawable == null) {
                $z0 = true;
            }
            setWillNotDraw($z0);
            requestLayout();
        }
    }

    public void setDividerPadding(int i) {
        this.mDividerPadding = i;
    }

    public void setGravity(int $i0) {
        if (this.mGravity != $i0) {
            if ((8388615 & $i0) == 0) {
                $i0 |= 8388611;
            }
            if (($i0 & 112) == 0) {
                $i0 |= 48;
            }
            this.mGravity = $i0;
            requestLayout();
        }
    }

    public void setHorizontalGravity(int i) {
        int $i1 = i & 8388615;
        int $i0 = this.mGravity;
        if ((8388615 & $i0) != $i1) {
            this.mGravity = $i1 | (-8388616 & $i0);
            requestLayout();
        }
    }

    public void setMeasureWithLargestChildEnabled(boolean z) {
        this.mUseLargestChild = z;
    }

    public void setOrientation(int i) {
        if (this.mOrientation != i) {
            this.mOrientation = i;
            requestLayout();
        }
    }

    public void setShowDividers(int i) {
        if (i != this.mShowDividers) {
            requestLayout();
        }
        this.mShowDividers = i;
    }

    public void setVerticalGravity(int i) {
        int $i1 = i & 112;
        int $i2 = this.mGravity;
        if (($i2 & 112) != $i1) {
            this.mGravity = $i1 | ($i2 & -113);
            requestLayout();
        }
    }

    public void setWeightSum(float f) {
        this.mWeightSum = Math.max(0.0f, f);
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }
}
