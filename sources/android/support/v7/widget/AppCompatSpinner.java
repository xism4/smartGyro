package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.ListPopupWindow;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import com.org.android.view.TintableBackgroundView;
import com.org.android.view.ViewCompat;
import com.org.v4.util.R$attr;

public class AppCompatSpinner extends Spinner implements TintableBackgroundView {
    private static final int[] ATTRS_ANDROID_SPINNERMODE = {16843505};
    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    int mDropDownWidth;
    private ListPopupWindow.ForwardingListener mForwardingListener;
    DropdownPopup mPopup;
    private final Context mPopupContext;
    private final boolean mPopupSet;
    private SpinnerAdapter mTempAdapter;
    final Rect mTempRect;

    class DropdownPopup extends ListPopupWindow {
        ListAdapter mAdapter;
        private CharSequence mHintText;
        private final Rect mVisibleRect = new Rect();

        public DropdownPopup(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            a((View) AppCompatSpinner.this);
            dismiss(true);
            setPromptPosition(0);
            setOnItemClickListener(new AdapterView.OnItemClickListener(AppCompatSpinner.this) {
                public void onItemClick(AdapterView adapterView, View view, int i, long j) {
                    AppCompatSpinner.this.setSelection(i);
                    if (AppCompatSpinner.this.getOnItemClickListener() != null) {
                        DropdownPopup $r3 = DropdownPopup.this;
                        AppCompatSpinner.this.performItemClick(view, i, $r3.mAdapter.getItemId(i));
                    }
                    DropdownPopup.this.dismiss();
                }
            });
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x00c7  */
        /* JADX WARNING: Removed duplicated region for block: B:21:0x00d2  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void computeContentWidth() {
            /*
                r21 = this;
                r0 = r21
                android.graphics.drawable.Drawable r2 = r0.getBackground()
                r3 = 0
                if (r2 == 0) goto L_0x002f
                r0 = r21
                android.support.v7.widget.AppCompatSpinner r4 = android.support.v7.widget.AppCompatSpinner.this
                android.graphics.Rect r5 = r4.mTempRect
                r2.getPadding(r5)
                r0 = r21
                android.support.v7.widget.AppCompatSpinner r4 = android.support.v7.widget.AppCompatSpinner.this
                boolean r6 = android.support.v7.widget.ViewUtils.isLayoutRtl(r4)
                if (r6 == 0) goto L_0x0025
                r0 = r21
                android.support.v7.widget.AppCompatSpinner r4 = android.support.v7.widget.AppCompatSpinner.this
                android.graphics.Rect r5 = r4.mTempRect
                int r3 = r5.right
                goto L_0x002e
            L_0x0025:
                r0 = r21
                android.support.v7.widget.AppCompatSpinner r4 = android.support.v7.widget.AppCompatSpinner.this
                android.graphics.Rect r5 = r4.mTempRect
                int r3 = r5.left
                int r3 = -r3
            L_0x002e:
                goto L_0x003b
            L_0x002f:
                r0 = r21
                android.support.v7.widget.AppCompatSpinner r4 = android.support.v7.widget.AppCompatSpinner.this
                android.graphics.Rect r5 = r4.mTempRect
                r7 = 0
                r5.right = r7
                r7 = 0
                r5.left = r7
            L_0x003b:
                r0 = r21
                android.support.v7.widget.AppCompatSpinner r4 = android.support.v7.widget.AppCompatSpinner.this
                int r8 = r4.getPaddingLeft()
                r0 = r21
                android.support.v7.widget.AppCompatSpinner r4 = android.support.v7.widget.AppCompatSpinner.this
                int r9 = r4.getPaddingRight()
                r0 = r21
                android.support.v7.widget.AppCompatSpinner r4 = android.support.v7.widget.AppCompatSpinner.this
                int r10 = r4.getWidth()
                r0 = r21
                android.support.v7.widget.AppCompatSpinner r4 = android.support.v7.widget.AppCompatSpinner.this
                int r11 = r4.mDropDownWidth
                r7 = -2
                if (r11 != r7) goto L_0x00ac
                r0 = r21
                android.widget.ListAdapter r12 = r0.mAdapter
                r14 = r12
                android.widget.SpinnerAdapter r14 = (android.widget.SpinnerAdapter) r14
                r13 = r14
                r0 = r21
                android.graphics.drawable.Drawable r2 = r0.getBackground()
                int r15 = r4.compatMeasureContentWidth(r13, r2)
                r11 = r15
                r0 = r21
                android.support.v7.widget.AppCompatSpinner r4 = android.support.v7.widget.AppCompatSpinner.this
                android.content.Context r16 = r4.getContext()
                r0 = r16
                android.content.res.Resources r17 = r0.getResources()
                r0 = r17
                android.util.DisplayMetrics r18 = r0.getDisplayMetrics()
                r0 = r18
                int r0 = r0.widthPixels
                r19 = r0
                r0 = r21
                android.support.v7.widget.AppCompatSpinner r4 = android.support.v7.widget.AppCompatSpinner.this
                android.graphics.Rect r5 = r4.mTempRect
                int r0 = r5.left
                r20 = r0
                int r20 = r19 - r20
                int r0 = r5.right
                r19 = r0
                r0 = r20
                r1 = r19
                int r0 = r0 - r1
                r20 = r0
                if (r15 <= r0) goto L_0x00a4
                r11 = r20
            L_0x00a4:
                int r15 = r10 - r8
                int r15 = r15 - r9
                int r11 = java.lang.Math.max(r11, r15)
                goto L_0x00b2
            L_0x00ac:
                r7 = -1
                if (r11 != r7) goto L_0x00b8
                int r11 = r10 - r8
                int r11 = r11 - r9
            L_0x00b2:
                r0 = r21
                r0.setContentWidth(r11)
                goto L_0x00bd
            L_0x00b8:
                r0 = r21
                r0.setContentWidth(r11)
            L_0x00bd:
                r0 = r21
                android.support.v7.widget.AppCompatSpinner r4 = android.support.v7.widget.AppCompatSpinner.this
                boolean r6 = android.support.v7.widget.ViewUtils.isLayoutRtl(r4)
                if (r6 == 0) goto L_0x00d2
                int r8 = r10 - r9
                r0 = r21
                int r9 = r0.getWidth()
                int r8 = r8 - r9
                int r3 = r3 + r8
                goto L_0x00d3
            L_0x00d2:
                int r3 = r3 + r8
            L_0x00d3:
                r0 = r21
                r0.setAdapter((int) r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.AppCompatSpinner.DropdownPopup.computeContentWidth():void");
        }

        public CharSequence getHintText() {
            return this.mHintText;
        }

        /* access modifiers changed from: package-private */
        public boolean isVisibleToUser(View view) {
            return ViewCompat.isAttachedToWindow(view) && view.getGlobalVisibleRect(this.mVisibleRect);
        }

        public void setAdapter(ListAdapter listAdapter) {
            super.setAdapter(listAdapter);
            this.mAdapter = listAdapter;
        }

        public void setPromptText(CharSequence charSequence) {
            this.mHintText = charSequence;
        }

        public void show() {
            ViewTreeObserver $r3;
            boolean $z0 = isShowing();
            computeContentWidth();
            show(2);
            super.show();
            add().setChoiceMode(1);
            setSelection(AppCompatSpinner.this.getSelectedItemPosition());
            if (!$z0 && ($r3 = AppCompatSpinner.this.getViewTreeObserver()) != null) {
                final AnonymousClass2 $r4 = new ViewTreeObserver.OnGlobalLayoutListener() {
                    public void onGlobalLayout() {
                        DropdownPopup $r2 = DropdownPopup.this;
                        if (!$r2.isVisibleToUser(AppCompatSpinner.this)) {
                            DropdownPopup.this.dismiss();
                            return;
                        }
                        DropdownPopup.this.computeContentWidth();
                        DropdownPopup.super.show();
                    }
                };
                $r3.addOnGlobalLayoutListener($r4);
                setOnDismissListener(new PopupWindow.OnDismissListener() {
                    public void onDismiss() {
                        ViewTreeObserver $r2 = AppCompatSpinner.this.getViewTreeObserver();
                        if ($r2 != null) {
                            $r2.removeGlobalOnLayoutListener($r4);
                        }
                    }
                });
            }
        }
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.spinnerStyle);
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, -1);
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet, int i, int i2) {
        this(context, attributeSet, i, i2, (Resources.Theme) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0081, code lost:
        if (r12 != null) goto L_0x0083;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0083, code lost:
        r13.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x009b, code lost:
        if (r13 != null) goto L_0x0083;
     */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00a1  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0123  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0151  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AppCompatSpinner(android.content.Context r30, android.util.AttributeSet r31, int r32, int r33, android.content.res.Resources.Theme r34) {
        /*
            r29 = this;
            r0 = r29
            r1 = r30
            r2 = r31
            r3 = r32
            r0.<init>(r1, r2, r3)
            android.graphics.Rect r4 = new android.graphics.Rect
            r4.<init>()
            r0 = r29
            r0.mTempRect = r4
            int[] r5 = com.org.v4.util.R$styleable.Spinner
            r7 = 0
            r0 = r30
            r1 = r31
            r2 = r32
            android.support.v7.widget.TintTypedArray r6 = android.support.v7.widget.TintTypedArray.obtainStyledAttributes(r0, r1, r5, r2, r7)
            android.support.v7.widget.AppCompatBackgroundHelper r8 = new android.support.v7.widget.AppCompatBackgroundHelper
            r0 = r29
            r8.<init>(r0)
            r0 = r29
            r0.mBackgroundTintHelper = r8
            if (r34 == 0) goto L_0x003c
            com.org.v4.view.ContextThemeWrapper r9 = new com.org.v4.view.ContextThemeWrapper
            r0 = r30
            r1 = r34
            r9.<init>((android.content.Context) r0, (android.content.res.Resources.Theme) r1)
        L_0x0037:
            r0 = r29
            r0.mPopupContext = r9
            goto L_0x005b
        L_0x003c:
            int r10 = com.org.v4.util.R$styleable.Spinner_popupTheme
            r7 = 0
            int r10 = r6.getResourceId(r10, r7)
            if (r10 == 0) goto L_0x004d
            com.org.v4.view.ContextThemeWrapper r9 = new com.org.v4.view.ContextThemeWrapper
            r0 = r30
            r9.<init>((android.content.Context) r0, (int) r10)
            goto L_0x0037
        L_0x004d:
            int r10 = android.os.Build.VERSION.SDK_INT
            r7 = 23
            if (r10 >= r7) goto L_0x0056
            r11 = r30
            goto L_0x0057
        L_0x0056:
            r11 = 0
        L_0x0057:
            r0 = r29
            r0.mPopupContext = r11
        L_0x005b:
            r0 = r29
            android.content.Context r11 = r0.mPopupContext
            if (r11 == 0) goto L_0x0119
            r7 = -1
            r0 = r33
            if (r0 != r7) goto L_0x00a5
            int[] r5 = ATTRS_ANDROID_SPINNERMODE
            r7 = 0
            r0 = r30
            r1 = r31
            r2 = r32
            android.content.res.TypedArray r12 = r0.obtainStyledAttributes(r1, r5, r2, r7)     // Catch:{ Exception -> 0x008c, Throwable -> 0x0089 }
            r13 = r12
            r7 = 0
            boolean r14 = r12.hasValue(r7)     // Catch:{ Exception -> 0x0087 }
            if (r14 == 0) goto L_0x0081
            r7 = 0
            r15 = 0
            int r33 = r12.getInt(r7, r15)     // Catch:{ Exception -> 0x0087 }
        L_0x0081:
            if (r12 == 0) goto L_0x00a5
        L_0x0083:
            r13.recycle()
            goto L_0x00a5
        L_0x0087:
            r16 = move-exception
            goto L_0x008e
        L_0x0089:
            r17 = move-exception
            r13 = 0
            goto L_0x009f
        L_0x008c:
            r16 = move-exception
            r13 = 0
        L_0x008e:
            java.lang.String r18 = "AppCompatSpinner"
            java.lang.String r19 = "Could not read android:spinnerMode"
            r0 = r18
            r1 = r19
            r2 = r16
            android.util.Log.i(r0, r1, r2)     // Catch:{ Throwable -> 0x009e }
            if (r13 == 0) goto L_0x00a5
            goto L_0x0083
        L_0x009e:
            r17 = move-exception
        L_0x009f:
            if (r13 == 0) goto L_0x00a4
            r13.recycle()
        L_0x00a4:
            throw r17
        L_0x00a5:
            r7 = 1
            r0 = r33
            if (r0 != r7) goto L_0x0119
            android.support.v7.widget.AppCompatSpinner$DropdownPopup r20 = new android.support.v7.widget.AppCompatSpinner$DropdownPopup
            r0 = r29
            android.content.Context r11 = r0.mPopupContext
            r0 = r20
            r1 = r29
            r2 = r31
            r3 = r32
            r0.<init>(r11, r2, r3)
            r0 = r29
            android.content.Context r11 = r0.mPopupContext
            int[] r5 = com.org.v4.util.R$styleable.Spinner
            r7 = 0
            r0 = r31
            r1 = r32
            android.support.v7.widget.TintTypedArray r21 = android.support.v7.widget.TintTypedArray.obtainStyledAttributes(r11, r0, r5, r1, r7)
            int r33 = com.org.v4.util.R$styleable.Spinner_android_dropDownWidth
            r7 = -2
            r0 = r21
            r1 = r33
            int r33 = r0.getLayoutDimension(r1, r7)
            r0 = r33
            r1 = r29
            r1.mDropDownWidth = r0
            int r33 = com.org.v4.util.R$styleable.Spinner_android_popupBackground
            r0 = r21
            r1 = r33
            android.graphics.drawable.Drawable r22 = r0.getDrawable(r1)
            r0 = r20
            r1 = r22
            r0.setBackgroundDrawable(r1)
            int r33 = com.org.v4.util.R$styleable.Spinner_android_prompt
            r0 = r33
            java.lang.String r23 = r6.getString(r0)
            r0 = r20
            r1 = r23
            r0.setPromptText(r1)
            r0 = r21
            r0.recycle()
            r0 = r20
            r1 = r29
            r1.mPopup = r0
            android.support.v7.widget.ActivityChooserView$3 r24 = new android.support.v7.widget.ActivityChooserView$3
            r0 = r24
            r1 = r29
            r2 = r29
            r3 = r20
            r0.<init>(r1, r2, r3)
            r0 = r24
            r1 = r29
            r1.mForwardingListener = r0
        L_0x0119:
            int r33 = com.org.v4.util.R$styleable.Spinner_android_entries
            r0 = r33
            java.lang.CharSequence[] r25 = r6.getTextArray(r0)
            if (r25 == 0) goto L_0x0141
            android.widget.ArrayAdapter r26 = new android.widget.ArrayAdapter
            r7 = 17367048(0x1090008, float:2.5162948E-38)
            r0 = r26
            r1 = r30
            r2 = r25
            r0.<init>(r1, r7, r2)
            int r33 = com.org.v4.util.R$layout.support_simple_spinner_dropdown_item
            r0 = r26
            r1 = r33
            r0.setDropDownViewResource(r1)
            r0 = r29
            r1 = r26
            r0.setAdapter((android.widget.SpinnerAdapter) r1)
        L_0x0141:
            r6.recycle()
            r7 = 1
            r0 = r29
            r0.mPopupSet = r7
            r0 = r29
            android.widget.SpinnerAdapter r0 = r0.mTempAdapter
            r27 = r0
            if (r27 == 0) goto L_0x0160
            r0 = r29
            r1 = r27
            r0.setAdapter((android.widget.SpinnerAdapter) r1)
            r28 = 0
            r0 = r28
            r1 = r29
            r1.mTempAdapter = r0
        L_0x0160:
            r0 = r29
            android.support.v7.widget.AppCompatBackgroundHelper r8 = r0.mBackgroundTintHelper
            r0 = r31
            r1 = r32
            r8.loadFromAttributes(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.AppCompatSpinner.<init>(android.content.Context, android.util.AttributeSet, int, int, android.content.res.Resources$Theme):void");
    }

    /* access modifiers changed from: package-private */
    public int compatMeasureContentWidth(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        int $i0 = 0;
        if (spinnerAdapter == null) {
            return 0;
        }
        int $i1 = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int $i2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int $i4 = Math.max(0, getSelectedItemPosition());
        int $i3 = Math.min(spinnerAdapter.getCount(), $i4 + 15);
        View $r3 = null;
        int $i42 = 0;
        for (int $i5 = Math.max(0, $i4 - (15 - ($i3 - $i4))); $i5 < $i3; $i5++) {
            int $i6 = spinnerAdapter.getItemViewType($i5);
            if ($i6 != $i0) {
                $r3 = null;
                $i0 = $i6;
            }
            View $r4 = spinnerAdapter.getView($i5, $r3, this);
            $r3 = $r4;
            if ($r4.getLayoutParams() == null) {
                $r4.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            }
            $r4.measure($i1, $i2);
            $i42 = Math.max($i42, $r4.getMeasuredWidth());
        }
        if (drawable == null) {
            return $i42;
        }
        drawable.getPadding(this.mTempRect);
        Rect $r6 = this.mTempRect;
        return $i42 + $r6.left + $r6.right;
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        AppCompatBackgroundHelper $r1 = this.mBackgroundTintHelper;
        if ($r1 != null) {
            $r1.applySupportBackgroundTint();
        }
    }

    public int getDropDownHorizontalOffset() {
        DropdownPopup $r1 = this.mPopup;
        if ($r1 != null) {
            return $r1.getHorizontalOffset();
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return super.getDropDownHorizontalOffset();
        }
        return 0;
    }

    public int getDropDownVerticalOffset() {
        DropdownPopup $r1 = this.mPopup;
        if ($r1 != null) {
            return $r1.getVerticalOffset();
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return super.getDropDownVerticalOffset();
        }
        return 0;
    }

    public int getDropDownWidth() {
        if (this.mPopup != null) {
            return this.mDropDownWidth;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return super.getDropDownWidth();
        }
        return 0;
    }

    public Drawable getPopupBackground() {
        DropdownPopup $r1 = this.mPopup;
        if ($r1 != null) {
            return $r1.getBackground();
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return super.getPopupBackground();
        }
        return null;
    }

    public Context getPopupContext() {
        if (this.mPopup != null) {
            return this.mPopupContext;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            return super.getPopupContext();
        }
        return null;
    }

    public CharSequence getPrompt() {
        DropdownPopup $r1 = this.mPopup;
        return $r1 != null ? $r1.getHintText() : super.getPrompt();
    }

    public ColorStateList getSupportBackgroundTintList() {
        AppCompatBackgroundHelper $r2 = this.mBackgroundTintHelper;
        if ($r2 != null) {
            return $r2.getSupportBackgroundTintList();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        AppCompatBackgroundHelper $r2 = this.mBackgroundTintHelper;
        if ($r2 != null) {
            return $r2.getSupportBackgroundTintMode();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        DropdownPopup $r1 = this.mPopup;
        if ($r1 != null && $r1.isShowing()) {
            this.mPopup.dismiss();
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.mPopup != null && View.MeasureSpec.getMode(i) == Integer.MIN_VALUE) {
            setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), compatMeasureContentWidth(getAdapter(), getBackground())), View.MeasureSpec.getSize(i)), getMeasuredHeight());
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        ListPopupWindow.ForwardingListener $r2 = this.mForwardingListener;
        if ($r2 == null || !$r2.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    public boolean performClick() {
        DropdownPopup $r1 = this.mPopup;
        if ($r1 == null) {
            return super.performClick();
        }
        if ($r1.isShowing()) {
            return true;
        }
        this.mPopup.show();
        return true;
    }

    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (!this.mPopupSet) {
            this.mTempAdapter = spinnerAdapter;
            return;
        }
        super.setAdapter(spinnerAdapter);
        if (this.mPopup != null) {
            Context $r4 = this.mPopupContext;
            if ($r4 == null) {
                $r4 = getContext();
            }
            this.mPopup.setAdapter(new SpinnerCompat$DropDownAdapter(spinnerAdapter, $r4.getTheme()));
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        AppCompatBackgroundHelper $r2 = this.mBackgroundTintHelper;
        if ($r2 != null) {
            $r2.onSetBackgroundDrawable(drawable);
        }
    }

    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        AppCompatBackgroundHelper $r1 = this.mBackgroundTintHelper;
        if ($r1 != null) {
            $r1.onSetBackgroundResource(i);
        }
    }

    public void setDropDownHorizontalOffset(int i) {
        DropdownPopup $r1 = this.mPopup;
        if ($r1 != null) {
            $r1.setAdapter(i);
        } else if (Build.VERSION.SDK_INT >= 16) {
            super.setDropDownHorizontalOffset(i);
        }
    }

    public void setDropDownVerticalOffset(int i) {
        DropdownPopup $r1 = this.mPopup;
        if ($r1 != null) {
            $r1.setVerticalOffset(i);
        } else if (Build.VERSION.SDK_INT >= 16) {
            super.setDropDownVerticalOffset(i);
        }
    }

    public void setDropDownWidth(int i) {
        if (this.mPopup != null) {
            this.mDropDownWidth = i;
        } else if (Build.VERSION.SDK_INT >= 16) {
            super.setDropDownWidth(i);
        }
    }

    public void setPopupBackgroundDrawable(Drawable drawable) {
        DropdownPopup $r2 = this.mPopup;
        if ($r2 != null) {
            $r2.setBackgroundDrawable(drawable);
        } else if (Build.VERSION.SDK_INT >= 16) {
            super.setPopupBackgroundDrawable(drawable);
        }
    }

    public void setPopupBackgroundResource(int i) {
        setPopupBackgroundDrawable(com.org.v4.text.view.Resources.getDrawable(getPopupContext(), i));
    }

    public void setPrompt(CharSequence charSequence) {
        DropdownPopup $r2 = this.mPopup;
        if ($r2 != null) {
            $r2.setPromptText(charSequence);
        } else {
            super.setPrompt(charSequence);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        AppCompatBackgroundHelper $r2 = this.mBackgroundTintHelper;
        if ($r2 != null) {
            $r2.setSupportBackgroundTintList(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        AppCompatBackgroundHelper $r2 = this.mBackgroundTintHelper;
        if ($r2 != null) {
            $r2.setSupportBackgroundTintMode(mode);
        }
    }
}
