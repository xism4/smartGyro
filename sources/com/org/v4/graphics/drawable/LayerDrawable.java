package com.org.v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.StateSet;
import com.org.v4.graphics.drawable.DrawableContainer;

class LayerDrawable extends DrawableContainer {
    private Document mLayerState;
    private boolean mMutated;

    LayerDrawable(Document document) {
        if (document != null) {
            mutate(document);
        }
    }

    LayerDrawable(Document document, Resources resources) {
        mutate(new Document(document, this, resources));
        onStateChange(getState());
    }

    /* access modifiers changed from: package-private */
    public int[] a(AttributeSet attributeSet) {
        int $i0 = attributeSet.getAttributeCount();
        int[] $r1 = new int[$i0];
        int $i2 = 0;
        for (int $i1 = 0; $i1 < $i0; $i1++) {
            int $i3 = attributeSet.getAttributeNameResource($i1);
            int $i4 = $i3;
            if (!($i3 == 0 || $i3 == 16842960 || $i3 == 16843161)) {
                int $i5 = $i2 + 1;
                if (!attributeSet.getAttributeBooleanValue($i1, false)) {
                    $i4 = -$i3;
                }
                $r1[$i2] = $i4;
                $i2 = $i5;
            }
        }
        return StateSet.trimStateSet($r1, $i2);
    }

    public void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
        onStateChange(getState());
    }

    /* access modifiers changed from: package-private */
    public Document draw() {
        return new Document(this.mLayerState, this, (Resources) null);
    }

    public boolean isStateful() {
        return true;
    }

    public Drawable mutate() {
        if (!this.mMutated) {
            super.mutate();
            if (this == this) {
                this.mLayerState.init();
                this.mMutated = true;
            }
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public void mutate(DrawableContainer.DrawableContainerState drawableContainerState) {
        super.mutate(drawableContainerState);
        if (drawableContainerState instanceof Document) {
            this.mLayerState = (Document) drawableContainerState;
        }
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        boolean $z0 = super.onStateChange(iArr);
        int $i0 = this.mLayerState.indexOf(iArr);
        int $i1 = $i0;
        if ($i0 < 0) {
            $i1 = this.mLayerState.indexOf(StateSet.WILD_CARD);
        }
        return draw($i1) || $z0;
    }
}
