package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import com.org.android.view.ViewCompat;

public class ContentFrameLayout extends FrameLayout {
    private a mAttachListener;
    private final Rect mDecorPadding;
    private TypedValue mFixedHeightMajor;
    private TypedValue mFixedHeightMinor;
    private TypedValue mFixedWidthMajor;
    private TypedValue mFixedWidthMinor;
    private TypedValue mMinWidthMajor;
    private TypedValue mMinWidthMinor;

    public interface a {
        void onAttachedFromWindow();

        void onDetachedFromWindow();
    }

    public ContentFrameLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mDecorPadding = new Rect();
    }

    public void dispatchFitSystemWindows(Rect rect) {
        fitSystemWindows(rect);
    }

    public TypedValue getFixedHeightMajor() {
        if (this.mFixedHeightMajor == null) {
            this.mFixedHeightMajor = new TypedValue();
        }
        return this.mFixedHeightMajor;
    }

    public TypedValue getFixedHeightMinor() {
        if (this.mFixedHeightMinor == null) {
            this.mFixedHeightMinor = new TypedValue();
        }
        return this.mFixedHeightMinor;
    }

    public TypedValue getFixedWidthMajor() {
        if (this.mFixedWidthMajor == null) {
            this.mFixedWidthMajor = new TypedValue();
        }
        return this.mFixedWidthMajor;
    }

    public TypedValue getFixedWidthMinor() {
        if (this.mFixedWidthMinor == null) {
            this.mFixedWidthMinor = new TypedValue();
        }
        return this.mFixedWidthMinor;
    }

    public TypedValue getMinWidthMajor() {
        if (this.mMinWidthMajor == null) {
            this.mMinWidthMajor = new TypedValue();
        }
        return this.mMinWidthMajor;
    }

    public TypedValue getMinWidthMinor() {
        if (this.mMinWidthMinor == null) {
            this.mMinWidthMinor = new TypedValue();
        }
        return this.mMinWidthMinor;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        a $r1 = this.mAttachListener;
        if ($r1 != null) {
            $r1.onAttachedFromWindow();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a $r1 = this.mAttachListener;
        if ($r1 != null) {
            $r1.onDetachedFromWindow();
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00ae  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00f0  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00f5  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0102  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0108  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0117  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0127  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0132  */
    /* JADX WARNING: Removed duplicated region for block: B:68:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r20, int r21) {
        /*
            r19 = this;
            r0 = r19
            android.content.Context r3 = r0.getContext()
            android.content.res.Resources r4 = r3.getResources()
            android.util.DisplayMetrics r5 = r4.getDisplayMetrics()
            int r6 = r5.widthPixels
            int r7 = r5.heightPixels
            r8 = 1
            if (r6 >= r7) goto L_0x0017
            r9 = 1
            goto L_0x0018
        L_0x0017:
            r9 = 0
        L_0x0018:
            r0 = r20
            int r6 = android.view.View.MeasureSpec.getMode(r0)
            r0 = r21
            int r7 = android.view.View.MeasureSpec.getMode(r0)
            r10 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r6 != r10) goto L_0x007e
            if (r9 == 0) goto L_0x0030
            r0 = r19
            android.util.TypedValue r11 = r0.mFixedWidthMinor
            goto L_0x0034
        L_0x0030:
            r0 = r19
            android.util.TypedValue r11 = r0.mFixedWidthMajor
        L_0x0034:
            if (r11 == 0) goto L_0x007e
            int r12 = r11.type
            if (r12 == 0) goto L_0x007e
            r10 = 5
            if (r12 != r10) goto L_0x0043
            float r13 = r11.getDimension(r5)
        L_0x0041:
            int r12 = (int) r13
            goto L_0x0050
        L_0x0043:
            r10 = 6
            if (r12 != r10) goto L_0x004f
            int r12 = r5.widthPixels
            float r13 = (float) r12
            float r14 = (float) r12
            float r13 = r11.getFraction(r13, r14)
            goto L_0x0041
        L_0x004f:
            r12 = 0
        L_0x0050:
            if (r12 <= 0) goto L_0x007e
            r0 = r19
            android.graphics.Rect r15 = r0.mDecorPadding
            int r0 = r15.left
            r16 = r0
            int r0 = r15.right
            r17 = r0
            r0 = r16
            r1 = r17
            int r0 = r0 + r1
            r16 = r0
            int r12 = r12 - r0
            r0 = r20
            int r20 = android.view.View.MeasureSpec.getSize(r0)
            r0 = r20
            int r20 = java.lang.Math.min(r12, r0)
            r10 = 1073741824(0x40000000, float:2.0)
            r0 = r20
            int r20 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r10)
            r18 = 1
            goto L_0x0080
        L_0x007e:
            r18 = 0
        L_0x0080:
            r10 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r7 != r10) goto L_0x00cf
            if (r9 == 0) goto L_0x008c
            r0 = r19
            android.util.TypedValue r11 = r0.mFixedHeightMajor
            goto L_0x0090
        L_0x008c:
            r0 = r19
            android.util.TypedValue r11 = r0.mFixedHeightMinor
        L_0x0090:
            if (r11 == 0) goto L_0x00cf
            int r7 = r11.type
            if (r7 == 0) goto L_0x00cf
            r10 = 5
            if (r7 != r10) goto L_0x009f
            float r13 = r11.getDimension(r5)
        L_0x009d:
            int r7 = (int) r13
            goto L_0x00ac
        L_0x009f:
            r10 = 6
            if (r7 != r10) goto L_0x00ab
            int r7 = r5.heightPixels
            float r13 = (float) r7
            float r14 = (float) r7
            float r13 = r11.getFraction(r13, r14)
            goto L_0x009d
        L_0x00ab:
            r7 = 0
        L_0x00ac:
            if (r7 <= 0) goto L_0x00cf
            r0 = r19
            android.graphics.Rect r15 = r0.mDecorPadding
            int r12 = r15.top
            int r0 = r15.bottom
            r16 = r0
            int r12 = r12 + r0
            int r7 = r7 - r12
            r0 = r21
            int r21 = android.view.View.MeasureSpec.getSize(r0)
            r0 = r21
            int r21 = java.lang.Math.min(r7, r0)
            r10 = 1073741824(0x40000000, float:2.0)
            r0 = r21
            int r21 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r10)
        L_0x00cf:
            r0 = r19
            r1 = r20
            r2 = r21
            super.onMeasure(r1, r2)
            r0 = r19
            int r20 = r0.getMeasuredWidth()
            r10 = 1073741824(0x40000000, float:2.0)
            r0 = r20
            int r7 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r10)
            if (r18 != 0) goto L_0x012f
            r10 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r6 != r10) goto L_0x012f
            if (r9 == 0) goto L_0x00f5
            r0 = r19
            android.util.TypedValue r11 = r0.mMinWidthMinor
            goto L_0x00f9
        L_0x00f5:
            r0 = r19
            android.util.TypedValue r11 = r0.mMinWidthMajor
        L_0x00f9:
            if (r11 == 0) goto L_0x012f
            int r6 = r11.type
            if (r6 == 0) goto L_0x012f
            r10 = 5
            if (r6 != r10) goto L_0x0108
            float r13 = r11.getDimension(r5)
        L_0x0106:
            int r6 = (int) r13
            goto L_0x0115
        L_0x0108:
            r10 = 6
            if (r6 != r10) goto L_0x0114
            int r6 = r5.widthPixels
            float r13 = (float) r6
            float r14 = (float) r6
            float r13 = r11.getFraction(r13, r14)
            goto L_0x0106
        L_0x0114:
            r6 = 0
        L_0x0115:
            if (r6 <= 0) goto L_0x0123
            r0 = r19
            android.graphics.Rect r15 = r0.mDecorPadding
            int r12 = r15.left
            int r0 = r15.right
            r16 = r0
            int r12 = r12 + r0
            int r6 = r6 - r12
        L_0x0123:
            r0 = r20
            if (r0 >= r6) goto L_0x012f
            r10 = 1073741824(0x40000000, float:2.0)
            int r7 = android.view.View.MeasureSpec.makeMeasureSpec(r6, r10)
            goto L_0x0130
        L_0x012f:
            r8 = 0
        L_0x0130:
            if (r8 == 0) goto L_0x0139
            r0 = r19
            r1 = r21
            super.onMeasure(r7, r1)
        L_0x0139:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ContentFrameLayout.onMeasure(int, int):void");
    }

    public void setAttachListener(a aVar) {
        this.mAttachListener = aVar;
    }

    public void setDecorPadding(int i, int i2, int i3, int i4) {
        this.mDecorPadding.set(i, i2, i3, i4);
        if (ViewCompat.show(this)) {
            requestLayout();
        }
    }
}
