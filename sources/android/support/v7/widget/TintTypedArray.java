package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import com.org.android.ui.asm.ClassReader;
import com.org.android.ui.asm.k;

public class TintTypedArray {
    private final Context mContext;
    private final TypedArray mWrapped;
    private TypedValue this$0;

    private TintTypedArray(Context context, TypedArray typedArray) {
        this.mContext = context;
        this.mWrapped = typedArray;
    }

    public static TintTypedArray obtainStyledAttributes(Context context, int i, int[] iArr) {
        return new TintTypedArray(context, context.obtainStyledAttributes(i, iArr));
    }

    public static TintTypedArray obtainStyledAttributes(Context context, AttributeSet attributeSet, int[] iArr) {
        return new TintTypedArray(context, context.obtainStyledAttributes(attributeSet, iArr));
    }

    public static TintTypedArray obtainStyledAttributes(Context context, AttributeSet attributeSet, int[] iArr, int i, int i2) {
        return new TintTypedArray(context, context.obtainStyledAttributes(attributeSet, iArr, i, i2));
    }

    public boolean getBoolean(int i, boolean z) {
        return this.mWrapped.getBoolean(i, z);
    }

    public int getColor(int i, int i2) {
        return this.mWrapped.getColor(i, i2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0011, code lost:
        r5 = com.org.v4.text.view.Resources.show(r6.mContext, (r2 = r6.mWrapped.getResourceId(r7, 0)));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.content.res.ColorStateList getColorStateList(int r7) {
        /*
            r6 = this;
            android.content.res.TypedArray r0 = r6.mWrapped
            boolean r1 = r0.hasValue(r7)
            if (r1 == 0) goto L_0x001a
            android.content.res.TypedArray r0 = r6.mWrapped
            r3 = 0
            int r2 = r0.getResourceId(r7, r3)
            if (r2 == 0) goto L_0x001a
            android.content.Context r4 = r6.mContext
            android.content.res.ColorStateList r5 = com.org.v4.text.view.Resources.show(r4, r2)
            if (r5 == 0) goto L_0x001a
            return r5
        L_0x001a:
            android.content.res.TypedArray r0 = r6.mWrapped
            android.content.res.ColorStateList r5 = r0.getColorStateList(r7)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.TintTypedArray.getColorStateList(int):android.content.res.ColorStateList");
    }

    public int getDimensionPixelOffset(int i, int i2) {
        return this.mWrapped.getDimensionPixelOffset(i, i2);
    }

    public int getDimensionPixelSize(int i, int i2) {
        return this.mWrapped.getDimensionPixelSize(i, i2);
    }

    public Typeface getDrawable(int i, int i2, k kVar) {
        int $i1 = this.mWrapped.getResourceId(i, 0);
        if ($i1 == 0) {
            return null;
        }
        if (this.this$0 == null) {
            this.this$0 = new TypedValue();
        }
        return ClassReader.a(this.mContext, $i1, this.this$0, i2, kVar);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0008, code lost:
        r2 = r6.mWrapped.getResourceId(r7, 0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.drawable.Drawable getDrawable(int r7) {
        /*
            r6 = this;
            android.content.res.TypedArray r0 = r6.mWrapped
            boolean r1 = r0.hasValue(r7)
            if (r1 == 0) goto L_0x0018
            android.content.res.TypedArray r0 = r6.mWrapped
            r3 = 0
            int r2 = r0.getResourceId(r7, r3)
            if (r2 == 0) goto L_0x0018
            android.content.Context r4 = r6.mContext
            android.graphics.drawable.Drawable r5 = com.org.v4.text.view.Resources.getDrawable(r4, r2)
            return r5
        L_0x0018:
            android.content.res.TypedArray r0 = r6.mWrapped
            android.graphics.drawable.Drawable r5 = r0.getDrawable(r7)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.TintTypedArray.getDrawable(int):android.graphics.drawable.Drawable");
    }

    public Drawable getDrawableIfKnown(int i) {
        int $i0;
        if (!this.mWrapped.hasValue(i) || ($i0 = this.mWrapped.getResourceId(i, 0)) == 0) {
            return null;
        }
        return AppCompatDrawableManager.get().getDrawable(this.mContext, $i0, true);
    }

    public float getFloat(int i, float f) {
        return this.mWrapped.getFloat(i, f);
    }

    public int getInt(int i, int i2) {
        return this.mWrapped.getInt(i, i2);
    }

    public int getInteger(int i, int i2) {
        return this.mWrapped.getInteger(i, i2);
    }

    public int getLayoutDimension(int i, int i2) {
        return this.mWrapped.getLayoutDimension(i, i2);
    }

    public int getResourceId(int i, int i2) {
        return this.mWrapped.getResourceId(i, i2);
    }

    public String getString(int i) {
        return this.mWrapped.getString(i);
    }

    public CharSequence getText(int i) {
        return this.mWrapped.getText(i);
    }

    public CharSequence[] getTextArray(int i) {
        return this.mWrapped.getTextArray(i);
    }

    public boolean hasValue(int i) {
        return this.mWrapped.hasValue(i);
    }

    public void recycle() {
        this.mWrapped.recycle();
    }
}
