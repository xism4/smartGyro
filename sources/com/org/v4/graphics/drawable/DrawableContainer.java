package com.org.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.SparseArray;

class DrawableContainer extends Drawable implements Drawable.Callback {
    private long a;
    private int c = -1;
    private int mAlpha = 255;
    private Runnable mAnimationRunnable;
    private Drawable mCurrDrawable;
    private Drawable mDrawable;
    private boolean mMutated;
    private Rect mRect;
    private DrawableContainerState mState;
    private boolean r;
    private MaterialProgressDrawable$3 this$0;
    private int x = -1;
    private long y;

    abstract class DrawableContainerState extends Drawable.ConstantState {
        boolean a;
        boolean b;
        int g;
        int h;
        int height;
        boolean i;
        boolean left;
        int mAlpha;
        boolean mCanConstantState;
        boolean mChangingConfigurations;
        boolean mCheckedConstantState;
        int mChildren;
        SparseArray<Drawable.ConstantState> mData;
        Drawable[] mDrawable;
        boolean mHaveOpacity;
        boolean mHaveStateful;
        int mOpacity;
        ColorFilter mPaint;
        boolean mState;
        boolean mStateful;
        ColorStateList mTint;
        PorterDuff.Mode mTintMode;
        boolean mUseColor;
        int mWidth;
        Resources next;
        boolean p;
        int pointCount;
        Rect r;
        final DrawableContainer this$0;
        int type;
        int value = 160;
        boolean w;
        int width;
        boolean x;
        int y;

        DrawableContainerState(DrawableContainerState drawableContainerState, DrawableContainer drawableContainer, Resources resources) {
            this.b = false;
            this.a = false;
            this.i = true;
            this.g = 0;
            this.mAlpha = 0;
            this.this$0 = drawableContainer;
            this.next = resources != null ? resources : drawableContainerState != null ? drawableContainerState.next : null;
            this.value = DrawableContainer.init(resources, drawableContainerState != null ? drawableContainerState.value : 0);
            if (drawableContainerState != null) {
                this.type = drawableContainerState.type;
                this.pointCount = drawableContainerState.pointCount;
                this.mCanConstantState = true;
                this.mCheckedConstantState = true;
                this.b = drawableContainerState.b;
                this.a = drawableContainerState.a;
                this.i = drawableContainerState.i;
                this.w = drawableContainerState.w;
                this.h = drawableContainerState.h;
                this.g = drawableContainerState.g;
                this.mAlpha = drawableContainerState.mAlpha;
                this.left = drawableContainerState.left;
                this.mPaint = drawableContainerState.mPaint;
                this.mState = drawableContainerState.mState;
                this.mTint = drawableContainerState.mTint;
                this.mTintMode = drawableContainerState.mTintMode;
                this.mUseColor = drawableContainerState.mUseColor;
                this.mChangingConfigurations = drawableContainerState.mChangingConfigurations;
                if (drawableContainerState.value == this.value) {
                    if (drawableContainerState.p) {
                        this.r = new Rect(drawableContainerState.r);
                        this.p = true;
                    }
                    if (drawableContainerState.x) {
                        this.mWidth = drawableContainerState.mWidth;
                        this.y = drawableContainerState.y;
                        this.width = drawableContainerState.width;
                        this.height = drawableContainerState.height;
                        this.x = true;
                    }
                }
                if (drawableContainerState.mHaveOpacity) {
                    this.mOpacity = drawableContainerState.mOpacity;
                    this.mHaveOpacity = true;
                }
                if (drawableContainerState.mHaveStateful) {
                    this.mStateful = drawableContainerState.mStateful;
                    this.mHaveStateful = true;
                }
                Drawable[] $r10 = drawableContainerState.mDrawable;
                this.mDrawable = new Drawable[$r10.length];
                this.mChildren = drawableContainerState.mChildren;
                SparseArray $r12 = drawableContainerState.mData;
                this.mData = $r12 != null ? $r12.clone() : new SparseArray(this.mChildren);
                int $i1 = this.mChildren;
                for (int $i0 = 0; $i0 < $i1; $i0++) {
                    if ($r10[$i0] != null) {
                        Drawable.ConstantState $r14 = $r10[$i0].getConstantState();
                        if ($r14 != null) {
                            this.mData.put($i0, $r14);
                        } else {
                            this.mDrawable[$i0] = $r10[$i0];
                        }
                    }
                }
                return;
            }
            this.mDrawable = new Drawable[10];
            this.mChildren = 0;
        }

        private Drawable apply(Drawable drawable) {
            if (Build.VERSION.SDK_INT >= 23) {
                drawable.setLayoutDirection(this.h);
            }
            Drawable $r1 = drawable.mutate();
            $r1.setCallback(this.this$0);
            return $r1;
        }

        private void remove() {
            SparseArray $r3 = this.mData;
            if ($r3 != null) {
                int $i0 = $r3.size();
                for (int $i1 = 0; $i1 < $i0; $i1++) {
                    this.mDrawable[this.mData.keyAt($i1)] = apply(this.mData.valueAt($i1).newDrawable(this.next));
                }
                this.mData = null;
            }
        }

        public final int a() {
            if (!this.x) {
                draw();
            }
            return this.y;
        }

        public final Rect add() {
            if (this.b) {
                return null;
            }
            if (this.r != null || this.p) {
                return this.r;
            }
            remove();
            Rect $r2 = new Rect();
            int $i0 = this.mChildren;
            Drawable[] $r1 = this.mDrawable;
            Rect $r3 = null;
            for (int $i1 = 0; $i1 < $i0; $i1++) {
                if ($r1[$i1].getPadding($r2)) {
                    if ($r3 == null) {
                        $r3 = new Rect(0, 0, 0, 0);
                    }
                    int $i2 = $r2.left;
                    if ($i2 > $r3.left) {
                        $r3.left = $i2;
                    }
                    int $i22 = $r2.top;
                    if ($i22 > $r3.top) {
                        $r3.top = $i22;
                    }
                    int $i23 = $r2.right;
                    if ($i23 > $r3.right) {
                        $r3.right = $i23;
                    }
                    int $i24 = $r2.bottom;
                    if ($i24 > $r3.bottom) {
                        $r3.bottom = $i24;
                    }
                }
            }
            this.p = true;
            this.r = $r3;
            return $r3;
        }

        /* access modifiers changed from: package-private */
        public void addChild() {
            this.mHaveOpacity = false;
            this.mHaveStateful = false;
        }

        public void addChild(int i2, int i3) {
            Drawable[] $r2 = new Drawable[i3];
            System.arraycopy(this.mDrawable, 0, $r2, 0, i2);
            this.mDrawable = $r2;
        }

        public final void addChild(boolean z) {
            this.a = z;
        }

        public boolean canApplyTheme() {
            int $i0 = this.mChildren;
            Drawable[] $r1 = this.mDrawable;
            for (int $i1 = 0; $i1 < $i0; $i1++) {
                Drawable $r2 = $r1[$i1];
                if ($r2 == null) {
                    Drawable.ConstantState $r5 = this.mData.get($i1);
                    if ($r5 != null && $r5.canApplyTheme()) {
                        return true;
                    }
                } else if ($r2.canApplyTheme()) {
                    return true;
                }
            }
            return false;
        }

        public synchronized boolean canConstantState() {
            if (this.mCanConstantState) {
                return this.mCheckedConstantState;
            }
            remove();
            this.mCanConstantState = true;
            int $i0 = this.mChildren;
            Drawable[] $r1 = this.mDrawable;
            for (int $i1 = 0; $i1 < $i0; $i1++) {
                if ($r1[$i1].getConstantState() == null) {
                    this.mCheckedConstantState = false;
                    return false;
                }
            }
            this.mCheckedConstantState = true;
            return true;
        }

        /* access modifiers changed from: protected */
        public void draw() {
            this.x = true;
            remove();
            int $i0 = this.mChildren;
            Drawable[] $r1 = this.mDrawable;
            this.y = -1;
            this.mWidth = -1;
            this.height = 0;
            this.width = 0;
            for (int $i1 = 0; $i1 < $i0; $i1++) {
                Drawable $r2 = $r1[$i1];
                int $i2 = $r2.getIntrinsicWidth();
                if ($i2 > this.mWidth) {
                    this.mWidth = $i2;
                }
                int $i22 = $r2.getIntrinsicHeight();
                if ($i22 > this.y) {
                    this.y = $i22;
                }
                int $i23 = $r2.getMinimumWidth();
                if ($i23 > this.width) {
                    this.width = $i23;
                }
                int $i24 = $r2.getMinimumHeight();
                if ($i24 > this.height) {
                    this.height = $i24;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public final boolean draw(int i2, int i3) {
            int $i2 = this.mChildren;
            Drawable[] $r1 = this.mDrawable;
            boolean $z1 = false;
            for (int $i3 = 0; $i3 < $i2; $i3++) {
                if ($r1[$i3] != null) {
                    boolean $z0 = Build.VERSION.SDK_INT >= 23 ? $r1[$i3].setLayoutDirection(i2) : false;
                    if ($i3 == i3) {
                        $z1 = $z0;
                    }
                }
            }
            this.h = i2;
            return $z1;
        }

        public final Drawable get(int i2) {
            int $i1;
            Drawable $r2 = this.mDrawable[i2];
            if ($r2 != null) {
                return $r2;
            }
            SparseArray $r3 = this.mData;
            if ($r3 == null || ($i1 = $r3.indexOfKey(i2)) < 0) {
                return null;
            }
            Drawable $r22 = apply(this.mData.valueAt($i1).newDrawable(this.next));
            this.mDrawable[i2] = $r22;
            this.mData.removeAt($i1);
            if (this.mData.size() != 0) {
                return $r22;
            }
            this.mData = null;
            return $r22;
        }

        public final boolean get() {
            return this.a;
        }

        public int getChangingConfigurations() {
            return this.type | this.pointCount;
        }

        /* access modifiers changed from: package-private */
        public final int getChildCount() {
            return this.mDrawable.length;
        }

        public final int getChildren() {
            return this.mChildren;
        }

        public final int getMinimumHeight() {
            if (!this.x) {
                draw();
            }
            return this.height;
        }

        public final int getMinimumWidth() {
            if (!this.x) {
                draw();
            }
            return this.width;
        }

        public final int getOpacity() {
            if (this.mHaveOpacity) {
                return this.mOpacity;
            }
            remove();
            int $i0 = this.mChildren;
            Drawable[] $r1 = this.mDrawable;
            int $i2 = $i0 > 0 ? $r1[0].getOpacity() : -2;
            for (int $i1 = 1; $i1 < $i0; $i1++) {
                $i2 = Drawable.resolveOpacity($i2, $r1[$i1].getOpacity());
            }
            this.mOpacity = $i2;
            this.mHaveOpacity = true;
            return $i2;
        }

        public final int getWidth() {
            if (!this.x) {
                draw();
            }
            return this.mWidth;
        }

        public final int init(Drawable drawable) {
            int $i0 = this.mChildren;
            if ($i0 >= this.mDrawable.length) {
                addChild($i0, $i0 + 10);
            }
            drawable.mutate();
            drawable.setVisible(false, true);
            drawable.setCallback(this.this$0);
            this.mDrawable[$i0] = drawable;
            this.mChildren++;
            this.pointCount = drawable.getChangingConfigurations() | this.pointCount;
            addChild();
            this.r = null;
            this.p = false;
            this.x = false;
            this.mCanConstantState = false;
            return $i0;
        }

        /* access modifiers changed from: package-private */
        public abstract void init();

        /* access modifiers changed from: package-private */
        public final void init(Resources.Theme theme) {
            if (theme != null) {
                remove();
                int $i0 = this.mChildren;
                Drawable[] $r1 = this.mDrawable;
                for (int $i1 = 0; $i1 < $i0; $i1++) {
                    if ($r1[$i1] != null && $r1[$i1].canApplyTheme()) {
                        $r1[$i1].applyTheme(theme);
                        this.pointCount |= $r1[$i1].getChangingConfigurations();
                    }
                }
                init(theme.getResources());
            }
        }

        /* access modifiers changed from: package-private */
        public final void init(Resources resources) {
            if (resources != null) {
                this.next = resources;
                int $i0 = DrawableContainer.init(resources, this.value);
                int $i1 = this.value;
                this.value = $i0;
                if ($i1 != $i0) {
                    this.x = false;
                    this.p = false;
                }
            }
        }

        public final void read(boolean z) {
            this.b = z;
        }

        public final void set(int i2) {
            this.g = i2;
        }

        public final void setColor(int i2) {
            this.mAlpha = i2;
        }
    }

    DrawableContainer() {
    }

    private void draw(Drawable drawable) {
        if (this.this$0 == null) {
            this.this$0 = new MaterialProgressDrawable$3();
        }
        MaterialProgressDrawable$3 $r2 = this.this$0;
        $r2.put(drawable.getCallback());
        drawable.setCallback($r2);
        try {
            if (this.mState.g <= 0) {
                if (this.r) {
                    drawable.setAlpha(this.mAlpha);
                }
            }
            if (this.mState.mState) {
                drawable.setColorFilter(this.mState.mPaint);
            } else {
                if (this.mState.mUseColor) {
                    DrawableCompat.setTintList(drawable, this.mState.mTint);
                }
                if (this.mState.mChangingConfigurations) {
                    DrawableCompat.setTintMode(drawable, this.mState.mTintMode);
                }
            }
            drawable.setVisible(isVisible(), true);
            drawable.setDither(this.mState.i);
            drawable.setState(getState());
            drawable.setLevel(getLevel());
            drawable.setBounds(getBounds());
            if (Build.VERSION.SDK_INT >= 23) {
                drawable.setLayoutDirection(getLayoutDirection());
            }
            if (Build.VERSION.SDK_INT >= 19) {
                drawable.setAutoMirrored(this.mState.left);
            }
            Rect $r9 = this.mRect;
            if (Build.VERSION.SDK_INT >= 21 && $r9 != null) {
                drawable.setHotspotBounds($r9.left, $r9.top, $r9.right, $r9.bottom);
            }
        } finally {
            drawable.setCallback(this.this$0.apply());
        }
    }

    static int init(Resources resources, int $i0) {
        if (resources != null) {
            $i0 = resources.getDisplayMetrics().densityDpi;
        }
        if ($i0 == 0) {
            return 160;
        }
        return $i0;
    }

    private boolean selectDrawable() {
        return isAutoMirrored() && getLayoutDirection() == 1;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0093 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:24:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void animate(boolean r20) {
        /*
            r19 = this;
            r2 = 1
            r3 = 1
            r0 = r19
            r0.r = r3
            long r4 = android.os.SystemClock.uptimeMillis()
            r0 = r19
            android.graphics.drawable.Drawable r6 = r0.mDrawable
            if (r6 == 0) goto L_0x0042
            r0 = r19
            long r7 = r0.a
            r10 = 0
            int r9 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
            if (r9 == 0) goto L_0x0048
            int r9 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r9 > 0) goto L_0x0026
            r0 = r19
            int r12 = r0.mAlpha
            r6.setAlpha(r12)
            goto L_0x0042
        L_0x0026:
            long r7 = r7 - r4
            r10 = 255(0xff, double:1.26E-321)
            long r7 = r7 * r10
            int r12 = (int) r7
            r0 = r19
            com.org.v4.graphics.drawable.DrawableContainer$DrawableContainerState r13 = r0.mState
            int r14 = r13.g
            int r12 = r12 / r14
            r3 = 255(0xff, float:3.57E-43)
            int r12 = r3 - r12
            r0 = r19
            int r14 = r0.mAlpha
            int r12 = r12 * r14
            int r12 = r12 / 255
            r6.setAlpha(r12)
            r15 = 1
            goto L_0x0049
        L_0x0042:
            r10 = 0
            r0 = r19
            r0.a = r10
        L_0x0048:
            r15 = 0
        L_0x0049:
            r0 = r19
            android.graphics.drawable.Drawable r6 = r0.mCurrDrawable
            if (r6 == 0) goto L_0x008a
            r0 = r19
            long r7 = r0.y
            r10 = 0
            int r9 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
            if (r9 == 0) goto L_0x0090
            int r9 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r9 > 0) goto L_0x0073
            r3 = 0
            r16 = 0
            r0 = r16
            r6.setVisible(r3, r0)
            r17 = 0
            r0 = r17
            r1 = r19
            r1.mCurrDrawable = r0
            r3 = -1
            r0 = r19
            r0.c = r3
            goto L_0x008a
        L_0x0073:
            long r7 = r7 - r4
            r10 = 255(0xff, double:1.26E-321)
            long r7 = r7 * r10
            int r12 = (int) r7
            r0 = r19
            com.org.v4.graphics.drawable.DrawableContainer$DrawableContainerState r13 = r0.mState
            int r14 = r13.mAlpha
            int r12 = r12 / r14
            r0 = r19
            int r14 = r0.mAlpha
            int r12 = r12 * r14
            int r12 = r12 / 255
            r6.setAlpha(r12)
            goto L_0x0091
        L_0x008a:
            r10 = 0
            r0 = r19
            r0.y = r10
        L_0x0090:
            r2 = r15
        L_0x0091:
            if (r20 == 0) goto L_0x00a5
            if (r2 == 0) goto L_0x00a5
            r0 = r19
            java.lang.Runnable r0 = r0.mAnimationRunnable
            r18 = r0
            r10 = 16
            long r4 = r4 + r10
            r0 = r19
            r1 = r18
            r0.scheduleSelf(r1, r4)
        L_0x00a5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.org.v4.graphics.drawable.DrawableContainer.animate(boolean):void");
    }

    public void applyTheme(Resources.Theme theme) {
        this.mState.init(theme);
    }

    public boolean canApplyTheme() {
        return this.mState.canApplyTheme();
    }

    /* access modifiers changed from: package-private */
    public DrawableContainerState draw() {
        throw new NullPointerException("Null throw statement replaced by Soot");
    }

    public void draw(Canvas canvas) {
        Drawable $r2 = this.mDrawable;
        if ($r2 != null) {
            $r2.draw(canvas);
        }
        Drawable $r22 = this.mCurrDrawable;
        if ($r22 != null) {
            $r22.draw(canvas);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00c6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean draw(int r18) {
        /*
            r17 = this;
            r0 = r17
            int r2 = r0.x
            r0 = r18
            if (r0 != r2) goto L_0x000a
            r3 = 0
            return r3
        L_0x000a:
            long r4 = android.os.SystemClock.uptimeMillis()
            r0 = r17
            com.org.v4.graphics.drawable.DrawableContainer$DrawableContainerState r6 = r0.mState
            int r2 = r6.mAlpha
            if (r2 <= 0) goto L_0x0051
            r0 = r17
            android.graphics.drawable.Drawable r7 = r0.mCurrDrawable
            if (r7 == 0) goto L_0x0021
            r3 = 0
            r8 = 0
            r7.setVisible(r3, r8)
        L_0x0021:
            r0 = r17
            android.graphics.drawable.Drawable r7 = r0.mDrawable
            if (r7 == 0) goto L_0x0040
            r0 = r17
            r0.mCurrDrawable = r7
            r0 = r17
            int r2 = r0.x
            r0 = r17
            r0.c = r2
            r0 = r17
            com.org.v4.graphics.drawable.DrawableContainer$DrawableContainerState r6 = r0.mState
            int r2 = r6.mAlpha
            long r9 = (long) r2
            long r9 = r9 + r4
            r0 = r17
            r0.y = r9
            goto L_0x005c
        L_0x0040:
            r11 = 0
            r0 = r17
            r0.mCurrDrawable = r11
            r3 = -1
            r0 = r17
            r0.c = r3
            r12 = 0
            r0 = r17
            r0.y = r12
            goto L_0x005c
        L_0x0051:
            r0 = r17
            android.graphics.drawable.Drawable r7 = r0.mDrawable
            if (r7 == 0) goto L_0x005c
            r3 = 0
            r8 = 0
            r7.setVisible(r3, r8)
        L_0x005c:
            if (r18 < 0) goto L_0x0092
            r0 = r17
            com.org.v4.graphics.drawable.DrawableContainer$DrawableContainerState r6 = r0.mState
            int r2 = r6.mChildren
            r0 = r18
            if (r0 >= r2) goto L_0x0092
            r0 = r18
            android.graphics.drawable.Drawable r7 = r6.get(r0)
            r0 = r17
            r0.mDrawable = r7
            r0 = r18
            r1 = r17
            r1.x = r0
            if (r7 == 0) goto L_0x009c
            r0 = r17
            com.org.v4.graphics.drawable.DrawableContainer$DrawableContainerState r6 = r0.mState
            int r0 = r6.g
            r18 = r0
            if (r18 <= 0) goto L_0x008c
            r0 = r18
            long r9 = (long) r0
            long r4 = r4 + r9
            r0 = r17
            r0.a = r4
        L_0x008c:
            r0 = r17
            r0.draw((android.graphics.drawable.Drawable) r7)
            goto L_0x009c
        L_0x0092:
            r11 = 0
            r0 = r17
            r0.mDrawable = r11
            r3 = -1
            r0 = r17
            r0.x = r3
        L_0x009c:
            r0 = r17
            long r4 = r0.a
            r12 = 0
            int r14 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
            if (r14 != 0) goto L_0x00b0
            r0 = r17
            long r4 = r0.y
            r12 = 0
            int r14 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
            if (r14 == 0) goto L_0x00d1
        L_0x00b0:
            r0 = r17
            java.lang.Runnable r15 = r0.mAnimationRunnable
            if (r15 != 0) goto L_0x00c6
            com.org.v4.graphics.drawable.MonthByWeekFragment$2 r16 = new com.org.v4.graphics.drawable.MonthByWeekFragment$2
            r0 = r16
            r1 = r17
            r0.<init>(r1)
            r0 = r16
            r1 = r17
            r1.mAnimationRunnable = r0
            goto L_0x00cb
        L_0x00c6:
            r0 = r17
            r0.unscheduleSelf(r15)
        L_0x00cb:
            r3 = 1
            r0 = r17
            r0.animate(r3)
        L_0x00d1:
            r0 = r17
            r0.invalidateSelf()
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.org.v4.graphics.drawable.DrawableContainer.draw(int):boolean");
    }

    public int getAlpha() {
        return this.mAlpha;
    }

    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.mState.getChangingConfigurations();
    }

    public final Drawable.ConstantState getConstantState() {
        if (!this.mState.canConstantState()) {
            return null;
        }
        this.mState.type = getChangingConfigurations();
        return this.mState;
    }

    public Drawable getCurrent() {
        return this.mDrawable;
    }

    public void getHotspotBounds(Rect rect) {
        Rect $r2 = this.mRect;
        if ($r2 != null) {
            rect.set($r2);
        } else {
            super.getHotspotBounds(rect);
        }
    }

    public int getIntrinsicHeight() {
        if (this.mState.get()) {
            return this.mState.a();
        }
        Drawable $r2 = this.mDrawable;
        if ($r2 != null) {
            return $r2.getIntrinsicHeight();
        }
        return -1;
    }

    public int getIntrinsicWidth() {
        if (this.mState.get()) {
            return this.mState.getWidth();
        }
        Drawable $r2 = this.mDrawable;
        if ($r2 != null) {
            return $r2.getIntrinsicWidth();
        }
        return -1;
    }

    public int getMinimumHeight() {
        if (this.mState.get()) {
            return this.mState.getMinimumHeight();
        }
        Drawable $r2 = this.mDrawable;
        if ($r2 != null) {
            return $r2.getMinimumHeight();
        }
        return 0;
    }

    public int getMinimumWidth() {
        if (this.mState.get()) {
            return this.mState.getMinimumWidth();
        }
        Drawable $r2 = this.mDrawable;
        if ($r2 != null) {
            return $r2.getMinimumWidth();
        }
        return 0;
    }

    public int getOpacity() {
        Drawable $r1 = this.mDrawable;
        if ($r1 == null || !$r1.isVisible()) {
            return -2;
        }
        return this.mState.getOpacity();
    }

    public void getOutline(Outline outline) {
        Drawable $r2 = this.mDrawable;
        if ($r2 != null) {
            $r2.getOutline(outline);
        }
    }

    public boolean getPadding(Rect rect) {
        boolean $z0;
        Rect $r3 = this.mState.add();
        if ($r3 != null) {
            rect.set($r3);
            $z0 = ($r3.right | (($r3.left | $r3.top) | $r3.bottom)) != 0;
        } else {
            Drawable $r4 = this.mDrawable;
            $z0 = $r4 != null ? $r4.getPadding(rect) : super.getPadding(rect);
        }
        if (selectDrawable()) {
            int $i0 = rect.left;
            rect.left = rect.right;
            rect.right = $i0;
        }
        return $z0;
    }

    /* access modifiers changed from: package-private */
    public int getWidth() {
        return this.x;
    }

    /* access modifiers changed from: package-private */
    public final void inflate(Resources resources) {
        this.mState.init(resources);
    }

    public void invalidateDrawable(Drawable drawable) {
        DrawableContainerState $r2 = this.mState;
        if ($r2 != null) {
            $r2.addChild();
        }
        if (drawable == this.mDrawable && getCallback() != null) {
            getCallback().invalidateDrawable(this);
        }
    }

    public boolean isAutoMirrored() {
        return this.mState.left;
    }

    public void jumpToCurrentState() {
        boolean $z0;
        Drawable $r1 = this.mCurrDrawable;
        if ($r1 != null) {
            $r1.jumpToCurrentState();
            this.mCurrDrawable = null;
            this.c = -1;
            $z0 = true;
        } else {
            $z0 = false;
        }
        Drawable $r12 = this.mDrawable;
        if ($r12 != null) {
            $r12.jumpToCurrentState();
            if (this.r) {
                this.mDrawable.setAlpha(this.mAlpha);
            }
        }
        if (this.y != 0) {
            this.y = 0;
            $z0 = true;
        }
        if (this.a != 0) {
            this.a = 0;
            $z0 = true;
        }
        if ($z0) {
            invalidateSelf();
        }
    }

    public Drawable mutate() {
        if (!this.mMutated && super.mutate() == this) {
            DrawableContainerState $r2 = draw();
            $r2.init();
            mutate($r2);
            this.mMutated = true;
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public void mutate(DrawableContainerState drawableContainerState) {
        this.mState = drawableContainerState;
        int $i0 = this.x;
        if ($i0 >= 0) {
            this.mDrawable = drawableContainerState.get($i0);
            Drawable $r1 = this.mDrawable;
            if ($r1 != null) {
                draw($r1);
            }
        }
        this.c = -1;
        this.mCurrDrawable = null;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        Drawable $r2 = this.mCurrDrawable;
        if ($r2 != null) {
            $r2.setBounds(rect);
        }
        Drawable $r22 = this.mDrawable;
        if ($r22 != null) {
            $r22.setBounds(rect);
        }
    }

    public boolean onLayoutDirectionChanged(int i) {
        return this.mState.draw(i, getWidth());
    }

    /* access modifiers changed from: protected */
    public boolean onLevelChange(int i) {
        Drawable $r1 = this.mCurrDrawable;
        if ($r1 != null) {
            return $r1.setLevel(i);
        }
        Drawable $r12 = this.mDrawable;
        if ($r12 != null) {
            return $r12.setLevel(i);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        Drawable $r2 = this.mCurrDrawable;
        if ($r2 != null) {
            return $r2.setState(iArr);
        }
        Drawable $r22 = this.mDrawable;
        if ($r22 != null) {
            return $r22.setState(iArr);
        }
        return false;
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        if (drawable == this.mDrawable && getCallback() != null) {
            getCallback().scheduleDrawable(this, runnable, j);
        }
    }

    public void setAlpha(int i) {
        if (!this.r || this.mAlpha != i) {
            this.r = true;
            this.mAlpha = i;
            Drawable $r1 = this.mDrawable;
            if ($r1 == null) {
                return;
            }
            if (this.a == 0) {
                $r1.setAlpha(i);
            } else {
                animate(false);
            }
        }
    }

    public void setAutoMirrored(boolean z) {
        DrawableContainerState $r1 = this.mState;
        if ($r1.left != z) {
            $r1.left = z;
            Drawable $r2 = this.mDrawable;
            if ($r2 != null) {
                DrawableCompat.setHotspotBounds($r2, $r1.left);
            }
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        DrawableContainerState $r2 = this.mState;
        $r2.mState = true;
        if ($r2.mPaint != colorFilter) {
            $r2.mPaint = colorFilter;
            Drawable $r4 = this.mDrawable;
            if ($r4 != null) {
                $r4.setColorFilter(colorFilter);
            }
        }
    }

    public void setDither(boolean z) {
        DrawableContainerState $r1 = this.mState;
        if ($r1.i != z) {
            $r1.i = z;
            Drawable $r2 = this.mDrawable;
            if ($r2 != null) {
                $r2.setDither($r1.i);
            }
        }
    }

    public void setHotspot(float f, float f2) {
        Drawable $r1 = this.mDrawable;
        if ($r1 != null) {
            DrawableCompat.setHotspot($r1, f, f2);
        }
    }

    public void setHotspotBounds(int i, int i2, int i3, int i4) {
        Rect $r1 = this.mRect;
        if ($r1 == null) {
            this.mRect = new Rect(i, i2, i3, i4);
        } else {
            $r1.set(i, i2, i3, i4);
        }
        Drawable $r2 = this.mDrawable;
        if ($r2 != null) {
            DrawableCompat.setHotspotBounds($r2, i, i2, i3, i4);
        }
    }

    public void setTintList(ColorStateList colorStateList) {
        DrawableContainerState $r2 = this.mState;
        $r2.mUseColor = true;
        if ($r2.mTint != colorStateList) {
            $r2.mTint = colorStateList;
            DrawableCompat.setTintList(this.mDrawable, colorStateList);
        }
    }

    public void setTintMode(PorterDuff.Mode mode) {
        DrawableContainerState $r2 = this.mState;
        $r2.mChangingConfigurations = true;
        if ($r2.mTintMode != mode) {
            $r2.mTintMode = mode;
            DrawableCompat.setTintMode(this.mDrawable, mode);
        }
    }

    public boolean setVisible(boolean z, boolean z2) {
        boolean $z2 = super.setVisible(z, z2);
        Drawable $r1 = this.mCurrDrawable;
        if ($r1 != null) {
            $r1.setVisible(z, z2);
        }
        Drawable $r12 = this.mDrawable;
        if ($r12 != null) {
            $r12.setVisible(z, z2);
        }
        return $z2;
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        if (drawable == this.mDrawable && getCallback() != null) {
            getCallback().unscheduleDrawable(this, runnable);
        }
    }
}
