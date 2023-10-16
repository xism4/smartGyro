package com.org.android.ui.asm;

import java.util.List;

final class Item {
    final float[] a;
    final int[] k;

    Item(int i, int i2) {
        this.k = new int[]{i, i2};
        this.a = new float[]{0.0f, 1.0f};
    }

    Item(int i, int i2, int i3) {
        this.k = new int[]{i, i2, i3};
        this.a = new float[]{0.0f, 0.5f, 1.0f};
    }

    Item(List list, List list2) {
        int $i0 = list.size();
        this.k = new int[$i0];
        this.a = new float[$i0];
        for (int $i1 = 0; $i1 < $i0; $i1++) {
            this.k[$i1] = ((Integer) list.get($i1)).intValue();
            this.a[$i1] = ((Float) list2.get($i1)).floatValue();
        }
    }
}
