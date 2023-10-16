package com.org.shortcuts.drawable;

import a.a.c.b.b;
import android.animation.TypeEvaluator;
import com.org.android.asm.PathParser;

class Label implements TypeEvaluator<b.b[]> {
    private PathParser.PathDataNode[] k;

    Label() {
    }

    /* renamed from: a */
    public PathParser.PathDataNode[] evaluate(float f, PathParser.PathDataNode[] pathDataNodeArr, PathParser.PathDataNode[] pathDataNodeArr2) {
        if (PathParser.canMorph(pathDataNodeArr, pathDataNodeArr2)) {
            PathParser.PathDataNode[] $r5 = this.k;
            if ($r5 == null || !PathParser.canMorph($r5, pathDataNodeArr)) {
                this.k = PathParser.deepCopyNodes(pathDataNodeArr);
            }
            for (int $i0 = 0; $i0 < pathDataNodeArr.length; $i0++) {
                this.k[$i0].interpolatePathDataNode(pathDataNodeArr[$i0], pathDataNodeArr2[$i0], f);
            }
            return this.k;
        }
        throw new IllegalArgumentException("Can't interpolate between two incompatible pathData");
    }
}
