package com.org.v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.StateSet;
import com.org.v4.graphics.drawable.DrawableContainer;

class Document extends DrawableContainer.DrawableContainerState {
    int[][] key;

    Document(Document document, LayerDrawable layerDrawable, Resources resources) {
        super(document, layerDrawable, resources);
        if (document != null) {
            this.key = document.key;
        } else {
            this.key = new int[getChildCount()][];
        }
    }

    public void addChild(int i, int i2) {
        super.addChild(i, i2);
        int[][] $r2 = new int[i2][];
        System.arraycopy(this.key, 0, $r2, 0, i);
        this.key = $r2;
    }

    /* access modifiers changed from: package-private */
    public int indexOf(int[] iArr) {
        int[][] $r1 = this.key;
        int $i0 = getChildren();
        for (int $i1 = 0; $i1 < $i0; $i1++) {
            if (StateSet.stateSetMatches($r1[$i1], iArr)) {
                return $i1;
            }
        }
        return -1;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v0, resolved type: int[]} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void init() {
        /*
            r6 = this;
            int[][] r0 = r6.key
            int r1 = r0.length
            int[][] r2 = new int[r1][]
            int r1 = r0.length
            int r1 = r1 + -1
        L_0x0008:
            if (r1 < 0) goto L_0x0021
            int[][] r0 = r6.key
            r3 = r0[r1]
            if (r3 == 0) goto L_0x001b
            r3 = r0[r1]
            java.lang.Object r4 = r3.clone()
            r5 = r4
            int[] r5 = (int[]) r5
            r3 = r5
            goto L_0x001c
        L_0x001b:
            r3 = 0
        L_0x001c:
            r2[r1] = r3
            int r1 = r1 + -1
            goto L_0x0008
        L_0x0021:
            r6.key = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.org.v4.graphics.drawable.Document.init():void");
    }

    public Drawable newDrawable() {
        return new LayerDrawable(this, (Resources) null);
    }

    public Drawable newDrawable(Resources resources) {
        return new LayerDrawable(this, resources);
    }

    /* access modifiers changed from: package-private */
    public int write(int[] iArr, Drawable drawable) {
        int $i0 = init(drawable);
        this.key[$i0] = iArr;
        return $i0;
    }
}
