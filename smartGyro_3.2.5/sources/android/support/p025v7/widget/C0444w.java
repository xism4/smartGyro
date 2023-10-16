package android.support.p025v7.widget;

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
import android.support.p024v4.graphics.drawable.C0192c;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/* renamed from: android.support.v7.widget.w */
class C0444w {

    /* renamed from: a */
    private static final int[] f1651a = {16843067, 16843068};

    /* renamed from: b */
    private final ProgressBar f1652b;

    /* renamed from: c */
    private Bitmap f1653c;

    C0444w(ProgressBar progressBar) {
        this.f1652b = progressBar;
    }

    /* renamed from: a */
    private Drawable mo1491a(Drawable drawable) {
        if (!(drawable instanceof AnimationDrawable)) {
            return drawable;
        }
        AnimationDrawable animationDrawable = (AnimationDrawable) drawable;
        int numberOfFrames = animationDrawable.getNumberOfFrames();
        AnimationDrawable animationDrawable2 = new AnimationDrawable();
        animationDrawable2.setOneShot(animationDrawable.isOneShot());
        for (int i = 0; i < numberOfFrames; i++) {
            Drawable a = m1924a(animationDrawable.getFrame(i), true);
            a.setLevel(10000);
            animationDrawable2.addFrame(a, animationDrawable.getDuration(i));
        }
        animationDrawable2.setLevel(10000);
        return animationDrawable2;
    }

    /* renamed from: a */
    private Drawable m1924a(Drawable drawable, boolean z) {
        if (drawable instanceof C0192c) {
            C0192c cVar = (C0192c) drawable;
            Drawable a = cVar.mo754a();
            if (a == null) {
                return drawable;
            }
            cVar.mo755a(m1924a(a, z));
            return drawable;
        } else if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            int numberOfLayers = layerDrawable.getNumberOfLayers();
            Drawable[] drawableArr = new Drawable[numberOfLayers];
            for (int i = 0; i < numberOfLayers; i++) {
                int id = layerDrawable.getId(i);
                drawableArr[i] = m1924a(layerDrawable.getDrawable(i), id == 16908301 || id == 16908303);
            }
            LayerDrawable layerDrawable2 = new LayerDrawable(drawableArr);
            for (int i2 = 0; i2 < numberOfLayers; i2++) {
                layerDrawable2.setId(i2, layerDrawable.getId(i2));
            }
            return layerDrawable2;
        } else if (!(drawable instanceof BitmapDrawable)) {
            return drawable;
        } else {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = bitmapDrawable.getBitmap();
            if (this.f1653c == null) {
                this.f1653c = bitmap;
            }
            ShapeDrawable shapeDrawable = new ShapeDrawable(mo1493b());
            shapeDrawable.getPaint().setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP));
            shapeDrawable.getPaint().setColorFilter(bitmapDrawable.getPaint().getColorFilter());
            return z ? new ClipDrawable(shapeDrawable, 3, 1) : shapeDrawable;
        }
    }

    /* renamed from: b */
    private Shape mo1493b() {
        return new RoundRectShape(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f}, (RectF) null, (float[]) null);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public Bitmap mo2298a() {
        return this.f1653c;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1492a(AttributeSet attributeSet, int i) {
        C0439ta a = C0439ta.m1902a(this.f1652b.getContext(), attributeSet, f1651a, i, 0);
        Drawable c = a.mo2279c(0);
        if (c != null) {
            this.f1652b.setIndeterminateDrawable(mo1491a(c));
        }
        Drawable c2 = a.mo2279c(1);
        if (c2 != null) {
            this.f1652b.setProgressDrawable(m1924a(c2, false));
        }
        a.mo2274a();
    }
}
