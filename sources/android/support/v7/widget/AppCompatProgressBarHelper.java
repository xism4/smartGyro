package android.support.v7.widget;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.widget.ProgressBar;

class AppCompatProgressBarHelper {
    private static final int[] TINT_ATTRS = {16843067, 16843068};
    private Bitmap mSampleTile;
    private final ProgressBar mView;

    AppCompatProgressBarHelper(ProgressBar progressBar) {
        this.mView = progressBar;
    }

    private Shape getDrawableShape() {
        return new RoundRectShape(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f}, (RectF) null, (float[]) null);
    }

    private Drawable tileify(Drawable $r1, boolean z) {
        if ($r1 instanceof android.support.v4.graphics.drawable.Drawable) {
            android.support.v4.graphics.drawable.Drawable $r2 = (android.support.v4.graphics.drawable.Drawable) $r1;
            Drawable $r3 = $r2.getWrappedDrawable();
            if ($r3 != null) {
                $r2.setWrappedDrawable(tileify($r3, z));
                return $r1;
            }
        } else if ($r1 instanceof LayerDrawable) {
            LayerDrawable $r4 = (LayerDrawable) $r1;
            int $i0 = $r4.getNumberOfLayers();
            Drawable[] $r5 = new Drawable[$i0];
            for (int $i2 = 0; $i2 < $i0; $i2++) {
                int $i3 = $r4.getId($i2);
                $r5[$i2] = tileify($r4.getDrawable($i2), $i3 == 16908301 || $i3 == 16908303);
            }
            LayerDrawable layerDrawable = new LayerDrawable($r5);
            for (int $i1 = 0; $i1 < $i0; $i1++) {
                layerDrawable.setId($i1, $r4.getId($i1));
            }
            return layerDrawable;
        } else if ($r1 instanceof BitmapDrawable) {
            BitmapDrawable $r7 = (BitmapDrawable) $r1;
            Bitmap $r8 = $r7.getBitmap();
            if (this.mSampleTile == null) {
                this.mSampleTile = $r8;
            }
            ShapeDrawable shapeDrawable = new ShapeDrawable(getDrawableShape());
            shapeDrawable.getPaint().setShader(new BitmapShader($r8, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP));
            shapeDrawable.getPaint().setColorFilter($r7.getPaint().getColorFilter());
            return z ? new ClipDrawable(shapeDrawable, 3, 1) : shapeDrawable;
        }
        return $r1;
    }

    private Drawable tileifyIndeterminate(Drawable $r2) {
        if (!($r2 instanceof AnimationDrawable)) {
            return $r2;
        }
        AnimationDrawable $r3 = (AnimationDrawable) $r2;
        int $i0 = $r3.getNumberOfFrames();
        AnimationDrawable $r1 = new AnimationDrawable();
        $r1.setOneShot($r3.isOneShot());
        for (int $i1 = 0; $i1 < $i0; $i1++) {
            Drawable $r22 = tileify($r3.getFrame($i1), true);
            $r22.setLevel(10000);
            $r1.addFrame($r22, $r3.getDuration($i1));
        }
        $r1.setLevel(10000);
        return $r1;
    }

    /* access modifiers changed from: package-private */
    public Bitmap getSampleTime() {
        return this.mSampleTile;
    }

    /* access modifiers changed from: package-private */
    public void loadFromAttributes(AttributeSet attributeSet, int i) {
        TintTypedArray $r5 = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), attributeSet, TINT_ATTRS, i, 0);
        Drawable $r6 = $r5.getDrawableIfKnown(0);
        if ($r6 != null) {
            this.mView.setIndeterminateDrawable(tileifyIndeterminate($r6));
        }
        Drawable $r62 = $r5.getDrawableIfKnown(1);
        if ($r62 != null) {
            this.mView.setProgressDrawable(tileify($r62, false));
        }
        $r5.recycle();
    }
}
