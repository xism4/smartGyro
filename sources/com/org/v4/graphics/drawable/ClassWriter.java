package com.org.v4.graphics.drawable;

import a.a.c.f.d;
import a.a.c.f.j;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.StateSet;
import com.org.android.util.ByteVector;
import com.org.android.util.LongSparseArray;

class ClassWriter extends Document {
    d<Long> a;
    j<Integer> x;

    ClassWriter(ClassWriter classWriter, VectorDrawableCompat vectorDrawableCompat, Resources resources) {
        super(classWriter, vectorDrawableCompat, resources);
        ByteVector $r5;
        if (classWriter != null) {
            this.a = classWriter.a;
            $r5 = classWriter.x;
        } else {
            this.a = new LongSparseArray();
            $r5 = new ByteVector();
        }
        this.x = $r5;
    }

    private static long add(int i, int i2) {
        return ((long) i2) | (((long) i) << 32);
    }

    /* access modifiers changed from: package-private */
    public int a(int i) {
        if (i < 0) {
            return 0;
        }
        return ((Integer) this.x.add(i, 0)).intValue();
    }

    /* access modifiers changed from: package-private */
    public int a(int i, int i2) {
        return (int) ((Long) this.a.get(add(i, i2), -1L)).longValue();
    }

    /* access modifiers changed from: package-private */
    public int a(int[] iArr, Drawable drawable, int i) {
        int $i1 = super.write(iArr, drawable);
        this.x.a($i1, Integer.valueOf(i));
        return $i1;
    }

    /* access modifiers changed from: package-private */
    public int get(int i, int i2, Drawable drawable, boolean z) {
        int $i3 = super.init(drawable);
        long $l4 = add(i, i2);
        long $l5 = z ? 8589934592L : 0;
        long $l1 = (long) $i3;
        this.a.append($l4, Long.valueOf($l1 | $l5));
        if (z) {
            this.a.append(add(i2, i), Long.valueOf(4294967296L | $l1 | $l5));
        }
        return $i3;
    }

    /* access modifiers changed from: package-private */
    public int get(int[] iArr) {
        int $i0 = super.indexOf(iArr);
        return $i0 >= 0 ? $i0 : super.indexOf(StateSet.WILD_CARD);
    }

    /* access modifiers changed from: package-private */
    public boolean get(int i, int i2) {
        return (((Long) this.a.get(add(i, i2), -1L)).longValue() & 8589934592L) != 0;
    }

    /* access modifiers changed from: package-private */
    public void init() {
        this.a = this.a.clone();
        this.x = this.x.clone();
    }

    public Drawable newDrawable() {
        return new VectorDrawableCompat(this, (Resources) null);
    }

    public Drawable newDrawable(Resources resources) {
        return new VectorDrawableCompat(this, resources);
    }

    /* access modifiers changed from: package-private */
    public boolean put(int i, int i2) {
        return (((Long) this.a.get(add(i, i2), -1L)).longValue() & 4294967296L) != 0;
    }
}
