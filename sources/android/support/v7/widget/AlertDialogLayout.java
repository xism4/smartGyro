package android.support.v7.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.org.android.view.ViewCompat;
import com.org.v4.util.R$id;

public class AlertDialogLayout extends LinearLayoutCompat {
    public AlertDialogLayout(Context context) {
        super(context);
    }

    public AlertDialogLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private static int a(View view) {
        int $i0 = ViewCompat.getMinimumHeight(view);
        if ($i0 > 0) {
            return $i0;
        }
        if (!(view instanceof ViewGroup)) {
            return 0;
        }
        ViewGroup $r1 = (ViewGroup) view;
        if ($r1.getChildCount() == 1) {
            return a($r1.getChildAt(0));
        }
        return 0;
    }

    private void forceUniformWidth(int i, int i2) {
        int $i3 = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int $i4 = 0; $i4 < i; $i4++) {
            View $r1 = getChildAt($i4);
            if ($r1.getVisibility() != 8) {
                LinearLayoutCompat.LayoutParams $r3 = (LinearLayoutCompat.LayoutParams) $r1.getLayoutParams();
                if ($r3.width == -1) {
                    int $i2 = $r3.height;
                    $r3.height = $r1.getMeasuredHeight();
                    measureChildWithMargins($r1, $i3, 0, i2, 0);
                    $r3.height = $i2;
                }
            }
        }
    }

    private boolean onMeasureExactFormat(int i, int i2) {
        int $i5;
        int $i10;
        int $i9;
        int $i8;
        int $i2 = getChildCount();
        View $r1 = null;
        View $r2 = null;
        View $r3 = null;
        for (int $i3 = 0; $i3 < $i2; $i3++) {
            View $r4 = getChildAt($i3);
            if ($r4.getVisibility() != 8) {
                int $i4 = $r4.getId();
                if ($i4 == R$id.topPanel) {
                    $r1 = $r4;
                } else if ($i4 == R$id.buttonPanel) {
                    $r2 = $r4;
                } else if (($i4 != R$id.contentPanel && $i4 != R$id.customPanel) || $r3 != null) {
                    return false;
                } else {
                    $r3 = $r4;
                }
            }
        }
        int $i6 = View.MeasureSpec.getMode(i2);
        int $i7 = View.MeasureSpec.getSize(i2);
        int $i32 = View.MeasureSpec.getMode(i);
        int $i42 = getPaddingTop() + getPaddingBottom();
        if ($r1 != null) {
            $r1.measure(i, 0);
            $i42 += $r1.getMeasuredHeight();
            $i5 = View.combineMeasuredStates(0, $r1.getMeasuredState());
        } else {
            $i5 = 0;
        }
        if ($r2 != null) {
            $r2.measure(i, 0);
            int $i82 = a($r2);
            $i9 = $i82;
            $i10 = $r2.getMeasuredHeight() - $i82;
            $i42 += $i82;
            $i5 = View.combineMeasuredStates($i5, $r2.getMeasuredState());
        } else {
            $i9 = 0;
            $i10 = 0;
        }
        if ($r3 != null) {
            $r3.measure(i, $i6 == 0 ? 0 : View.MeasureSpec.makeMeasureSpec(Math.max(0, $i7 - $i42), $i6));
            int $i11 = $r3.getMeasuredHeight();
            $i8 = $i11;
            $i42 += $i11;
            $i5 = View.combineMeasuredStates($i5, $r3.getMeasuredState());
        } else {
            $i8 = 0;
        }
        int $i72 = $i7 - $i42;
        if ($r2 != null) {
            int $i43 = $i42 - $i9;
            int $i102 = Math.min($i72, $i10);
            if ($i102 > 0) {
                $i72 -= $i102;
                $i9 += $i102;
            }
            $r2.measure(i, View.MeasureSpec.makeMeasureSpec($i9, 1073741824));
            $i42 = $i43 + $r2.getMeasuredHeight();
            $i5 = View.combineMeasuredStates($i5, $r2.getMeasuredState());
        }
        if ($r3 != null && $i72 > 0) {
            $r3.measure(i, View.MeasureSpec.makeMeasureSpec($i8 + $i72, $i6));
            $i42 = ($i42 - $i8) + $r3.getMeasuredHeight();
            $i5 = View.combineMeasuredStates($i5, $r3.getMeasuredState());
        }
        int $i62 = 0;
        for (int $i73 = 0; $i73 < $i2; $i73++) {
            View $r12 = getChildAt($i73);
            if ($r12.getVisibility() != 8) {
                $i62 = Math.max($i62, $r12.getMeasuredWidth());
            }
        }
        setMeasuredDimension(View.resolveSizeAndState($i62 + getPaddingLeft() + getPaddingRight(), i, $i5), View.resolveSizeAndState($i42, i2, 0));
        if ($i32 == 1073741824) {
            return true;
        }
        forceUniformWidth($i2, i2);
        return true;
    }

    private void setChildFrame(View view, int i, int i2, int i3, int i4) {
        view.layout(i, i2, i3 + i, i4 + i2);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0101  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onLayout(boolean r21, int r22, int r23, int r24, int r25) {
        /*
            r20 = this;
            r0 = r20
            int r6 = r0.getPaddingLeft()
            r0 = r24
            r1 = r22
            int r0 = r0 - r1
            r24 = r0
            r0 = r20
            int r22 = r0.getPaddingRight()
            int r22 = r24 - r22
            r0 = r24
            int r0 = r0 - r6
            r24 = r0
            r0 = r20
            int r7 = r0.getPaddingRight()
            r0 = r24
            int r0 = r0 - r7
            r24 = r0
            r0 = r20
            int r8 = r0.getMeasuredHeight()
            r0 = r20
            int r7 = r0.getChildCount()
            r0 = r20
            int r9 = r0.getGravity()
            r10 = r9 & 112(0x70, float:1.57E-43)
            r11 = 8388615(0x800007, float:1.1754953E-38)
            r9 = r9 & r11
            r11 = 16
            if (r10 == r11) goto L_0x0059
            r11 = 80
            if (r10 == r11) goto L_0x004c
            r0 = r20
            int r8 = r0.getPaddingTop()
            goto L_0x006a
        L_0x004c:
            r0 = r20
            int r10 = r0.getPaddingTop()
            int r25 = r10 + r25
            int r23 = r25 - r23
            int r8 = r23 - r8
            goto L_0x006a
        L_0x0059:
            r0 = r20
            int r10 = r0.getPaddingTop()
            int r23 = r25 - r23
            r0 = r23
            int r0 = r0 - r8
            r23 = r0
            int r23 = r23 / 2
            int r8 = r10 + r23
        L_0x006a:
            r0 = r20
            android.graphics.drawable.Drawable r12 = r0.getDividerDrawable()
            if (r12 != 0) goto L_0x0075
            r23 = 0
            goto L_0x0079
        L_0x0075:
            int r23 = r12.getIntrinsicHeight()
        L_0x0079:
            r25 = 0
        L_0x007b:
            r0 = r25
            if (r0 >= r7) goto L_0x0121
            r0 = r20
            r1 = r25
            android.view.View r13 = r0.getChildAt(r1)
            if (r13 == 0) goto L_0x011c
            int r10 = r13.getVisibility()
            r11 = 8
            if (r10 == r11) goto L_0x011c
            int r14 = r13.getMeasuredWidth()
            int r10 = r13.getMeasuredHeight()
            android.view.ViewGroup$LayoutParams r15 = r13.getLayoutParams()
            r17 = r15
            android.support.v7.widget.LinearLayoutCompat$LayoutParams r17 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r17
            r16 = r17
            r0 = r16
            int r0 = r0.gravity
            r18 = r0
            if (r18 >= 0) goto L_0x00ad
            r18 = r9
        L_0x00ad:
            r0 = r20
            int r19 = com.org.android.view.ViewCompat.getLayoutDirection(r0)
            r0 = r18
            r1 = r19
            int r18 = com.org.android.view.View.getAbsoluteGravity(r0, r1)
            r18 = r18 & 7
            r11 = 1
            r0 = r18
            if (r0 == r11) goto L_0x00d4
            r11 = 5
            r0 = r18
            if (r0 == r11) goto L_0x00d1
            r0 = r16
            int r0 = r0.leftMargin
            r18 = r0
            int r0 = r0 + r6
            r18 = r0
            goto L_0x00f7
        L_0x00d1:
            int r18 = r22 - r14
            goto L_0x00ea
        L_0x00d4:
            int r18 = r24 - r14
            int r18 = r18 / 2
            r0 = r18
            int r0 = r0 + r6
            r18 = r0
            r0 = r16
            int r0 = r0.leftMargin
            r19 = r0
            r0 = r18
            r1 = r19
            int r0 = r0 + r1
            r18 = r0
        L_0x00ea:
            r0 = r16
            int r0 = r0.rightMargin
            r19 = r0
            r0 = r18
            r1 = r19
            int r0 = r0 - r1
            r18 = r0
        L_0x00f7:
            r0 = r20
            r1 = r25
            boolean r21 = r0.hasDividerBeforeChildAt(r1)
            if (r21 == 0) goto L_0x0104
            r0 = r23
            int r8 = r8 + r0
        L_0x0104:
            r0 = r16
            int r0 = r0.topMargin
            r19 = r0
            int r8 = r8 + r0
            r0 = r20
            r1 = r13
            r2 = r18
            r3 = r8
            r4 = r14
            r5 = r10
            r0.setChildFrame(r1, r2, r3, r4, r5)
            r0 = r16
            int r14 = r0.bottomMargin
            int r10 = r10 + r14
            int r8 = r8 + r10
        L_0x011c:
            int r25 = r25 + 1
            goto L_0x007b
        L_0x0121:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.AlertDialogLayout.onLayout(boolean, int, int, int, int):void");
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (!onMeasureExactFormat(i, i2)) {
            super.onMeasure(i, i2);
        }
    }
}
