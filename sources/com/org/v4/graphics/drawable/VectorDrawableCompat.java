package com.org.v4.graphics.drawable;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import com.org.android.ui.asm.TypedArrayUtils;
import com.org.shortcuts.drawable.AnimatedVectorDrawableCompat;
import com.org.v4.graphics.drawable.DrawableContainer;
import com.org.v4.util.R$styleable;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class VectorDrawableCompat extends LayerDrawable {
    private ClassWriter a;
    private boolean mMutated;
    private RippleDrawable w;
    private int x;
    private int y;

    public VectorDrawableCompat() {
        this((ClassWriter) null, (Resources) null);
    }

    VectorDrawableCompat(ClassWriter classWriter, Resources resources) {
        super((Document) null);
        this.y = -1;
        this.x = -1;
        mutate(new ClassWriter(classWriter, this, resources));
        onStateChange(getState());
        jumpToCurrentState();
    }

    private int a(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        int $i1;
        TypedArray $r7 = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, R$styleable.AnimatedStateListDrawableItem);
        int $i0 = $r7.getResourceId(R$styleable.AnimatedStateListDrawableItem_android_id, 0);
        int $i12 = $r7.getResourceId(R$styleable.AnimatedStateListDrawableItem_android_drawable, -1);
        Drawable $r8 = $i12 > 0 ? com.org.v4.text.view.Resources.getDrawable(context, $i12) : null;
        $r7.recycle();
        int[] $r6 = a(attributeSet);
        if ($r8 == null) {
            do {
                $i1 = xmlPullParser.next();
            } while ($i1 == 4);
            if ($i1 == 2) {
                $r8 = xmlPullParser.getName().equals("vector") ? com.org.shortcuts.drawable.VectorDrawableCompat.createFromXmlInner(resources, xmlPullParser, attributeSet, theme) : Build.VERSION.SDK_INT >= 21 ? Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet, theme) : Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet);
            } else {
                throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <item> tag requires a 'drawable' attribute or child tag defining a drawable");
            }
        }
        if ($r8 != null) {
            return this.a.a($r6, $r8, $i0);
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <item> tag requires a 'drawable' attribute or child tag defining a drawable");
    }

    private int create(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        int $i2;
        TypedArray $r7 = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, R$styleable.AnimatedStateListDrawableTransition);
        int $i0 = $r7.getResourceId(R$styleable.AnimatedStateListDrawableTransition_android_fromId, -1);
        int $i1 = $r7.getResourceId(R$styleable.AnimatedStateListDrawableTransition_android_toId, -1);
        int $i22 = $r7.getResourceId(R$styleable.AnimatedStateListDrawableTransition_android_drawable, -1);
        Drawable $r8 = $i22 > 0 ? com.org.v4.text.view.Resources.getDrawable(context, $i22) : null;
        boolean $z0 = $r7.getBoolean(R$styleable.AnimatedStateListDrawableTransition_android_reversible, false);
        $r7.recycle();
        if ($r8 == null) {
            do {
                $i2 = xmlPullParser.next();
            } while ($i2 == 4);
            if ($i2 == 2) {
                $r8 = xmlPullParser.getName().equals("animated-vector") ? AnimatedVectorDrawableCompat.createFromXmlInner(context, resources, xmlPullParser, attributeSet, theme) : Build.VERSION.SDK_INT >= 21 ? Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet, theme) : Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet);
            } else {
                throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <transition> tag requires a 'drawable' attribute or child tag defining a drawable");
            }
        }
        if ($r8 == null) {
            throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <transition> tag requires a 'drawable' attribute or child tag defining a drawable");
        } else if ($i0 == -1 || $i1 == -1) {
            throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <transition> tag requires 'fromId' & 'toId' attributes");
        } else {
            ClassWriter $r13 = this.a;
            ClassWriter classWriter = $r13;
            return $r13.get($i0, $i1, $r8, $z0);
        }
    }

    public static VectorDrawableCompat createFromXmlInner(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        String $r5 = xmlPullParser.getName();
        if ($r5.equals("animated-selector")) {
            VectorDrawableCompat $r6 = new VectorDrawableCompat();
            $r6.inflate(context, resources, xmlPullParser, attributeSet, theme);
            return $r6;
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": invalid animated-selector tag " + $r5);
    }

    private void init(TypedArray typedArray) {
        ClassWriter $r2 = this.a;
        if (Build.VERSION.SDK_INT >= 21) {
            $r2.type |= typedArray.getChangingConfigurations();
        }
        $r2.read(typedArray.getBoolean(R$styleable.AnimatedStateListDrawableCompat_android_variablePadding, $r2.b));
        $r2.addChild(typedArray.getBoolean(R$styleable.AnimatedStateListDrawableCompat_android_constantSize, $r2.a));
        $r2.set(typedArray.getInt(R$styleable.AnimatedStateListDrawableCompat_android_enterFadeDuration, $r2.g));
        $r2.setColor(typedArray.getInt(R$styleable.AnimatedStateListDrawableCompat_android_exitFadeDuration, $r2.mAlpha));
        setDither(typedArray.getBoolean(R$styleable.AnimatedStateListDrawableCompat_android_dither, $r2.i));
    }

    private void process(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        int $i0 = xmlPullParser.getDepth() + 1;
        while (true) {
            int $i1 = xmlPullParser.next();
            if ($i1 != 1) {
                int $i2 = xmlPullParser.getDepth();
                if ($i2 < $i0 && $i1 == 3) {
                    return;
                }
                if ($i1 == 2 && $i2 <= $i0) {
                    if (xmlPullParser.getName().equals("item")) {
                        a(context, resources, xmlPullParser, attributeSet, theme);
                    } else if (xmlPullParser.getName().equals("transition")) {
                        create(context, resources, xmlPullParser, attributeSet, theme);
                    }
                }
            } else {
                return;
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: com.org.v4.graphics.drawable.ProgressBar} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: com.org.v4.graphics.drawable.ProgressBar} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: com.org.v4.graphics.drawable.ProgressBar} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v12, resolved type: com.org.v4.graphics.drawable.Main} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: com.org.v4.graphics.drawable.CircleDrawable} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean run(int r23) {
        /*
            r22 = this;
            r0 = r22
            com.org.v4.graphics.drawable.RippleDrawable r2 = r0.w
            if (r2 == 0) goto L_0x0039
            r0 = r22
            int r3 = r0.y
            r0 = r23
            if (r0 != r3) goto L_0x0010
            r4 = 1
            return r4
        L_0x0010:
            r0 = r22
            int r3 = r0.x
            r0 = r23
            if (r0 != r3) goto L_0x0031
            boolean r5 = r2.draw()
            if (r5 == 0) goto L_0x0031
            r2.setColor()
            r0 = r22
            int r3 = r0.x
            r0 = r22
            r0.y = r3
            r0 = r23
            r1 = r22
            r1.x = r0
            r4 = 1
            return r4
        L_0x0031:
            r0 = r22
            int r3 = r0.y
            r2.stopAnimation()
            goto L_0x003f
        L_0x0039:
            r0 = r22
            int r3 = r0.getWidth()
        L_0x003f:
            r6 = 0
            r0 = r22
            r0.w = r6
            r4 = -1
            r0 = r22
            r0.x = r4
            r4 = -1
            r0 = r22
            r0.y = r4
            r0 = r22
            com.org.v4.graphics.drawable.ClassWriter r7 = r0.a
            int r8 = r7.a(r3)
            r0 = r23
            int r9 = r7.a(r0)
            if (r9 == 0) goto L_0x00ca
            if (r8 != 0) goto L_0x0062
            r4 = 0
            return r4
        L_0x0062:
            int r10 = r7.a(r8, r9)
            if (r10 >= 0) goto L_0x006a
            r4 = 0
            return r4
        L_0x006a:
            boolean r5 = r7.get(r8, r9)
            r0 = r22
            r0.draw((int) r10)
            r0 = r22
            android.graphics.drawable.Drawable r11 = r0.getCurrent()
            boolean r12 = r11 instanceof android.graphics.drawable.AnimationDrawable
            if (r12 == 0) goto L_0x008c
            boolean r12 = r7.put(r8, r9)
            com.org.v4.graphics.drawable.CircleDrawable r13 = new com.org.v4.graphics.drawable.CircleDrawable
            r2 = r13
            r15 = r11
            android.graphics.drawable.AnimationDrawable r15 = (android.graphics.drawable.AnimationDrawable) r15
            r14 = r15
            r13.<init>(r14, r12, r5)
            goto L_0x00b7
        L_0x008c:
            boolean r5 = r11 instanceof com.org.shortcuts.drawable.AnimatedVectorDrawableCompat
            if (r5 == 0) goto L_0x00a2
            com.org.v4.graphics.drawable.Main r16 = new com.org.v4.graphics.drawable.Main
            r2 = r16
            r18 = r11
            com.org.shortcuts.drawable.AnimatedVectorDrawableCompat r18 = (com.org.shortcuts.drawable.AnimatedVectorDrawableCompat) r18
            r17 = r18
            r0 = r16
            r1 = r17
            r0.<init>(r1)
            goto L_0x00b7
        L_0x00a2:
            boolean r5 = r11 instanceof android.graphics.drawable.Animatable
            if (r5 == 0) goto L_0x00ca
            com.org.v4.graphics.drawable.ProgressBar r19 = new com.org.v4.graphics.drawable.ProgressBar
            r2 = r19
            r21 = r11
            android.graphics.drawable.Animatable r21 = (android.graphics.drawable.Animatable) r21
            r20 = r21
            r0 = r19
            r1 = r20
            r0.<init>(r1)
        L_0x00b7:
            r2.start()
            r0 = r22
            r0.w = r2
            r0 = r22
            r0.x = r3
            r0 = r23
            r1 = r22
            r1.y = r0
            r4 = 1
            return r4
        L_0x00ca:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.org.v4.graphics.drawable.VectorDrawableCompat.run(int):boolean");
    }

    private void updateTintFilter() {
        onStateChange(getState());
    }

    public /* bridge */ /* synthetic */ void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
    }

    public /* bridge */ /* synthetic */ boolean canApplyTheme() {
        return super.canApplyTheme();
    }

    /* access modifiers changed from: package-private */
    public ClassWriter draw() {
        return new ClassWriter(this.a, this, (Resources) null);
    }

    public /* bridge */ /* synthetic */ void draw(Canvas canvas) {
        super.draw(canvas);
    }

    public /* bridge */ /* synthetic */ int getAlpha() {
        return super.getAlpha();
    }

    public /* bridge */ /* synthetic */ int getChangingConfigurations() {
        return super.getChangingConfigurations();
    }

    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    public /* bridge */ /* synthetic */ void getHotspotBounds(Rect rect) {
        super.getHotspotBounds(rect);
    }

    public /* bridge */ /* synthetic */ int getIntrinsicHeight() {
        return super.getIntrinsicHeight();
    }

    public /* bridge */ /* synthetic */ int getIntrinsicWidth() {
        return super.getIntrinsicWidth();
    }

    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    public /* bridge */ /* synthetic */ int getOpacity() {
        return super.getOpacity();
    }

    public /* bridge */ /* synthetic */ void getOutline(Outline outline) {
        super.getOutline(outline);
    }

    public /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    public void inflate(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        TypedArray $r7 = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, R$styleable.AnimatedStateListDrawableCompat);
        setVisible($r7.getBoolean(R$styleable.AnimatedStateListDrawableCompat_android_visible, true), true);
        init($r7);
        inflate(resources);
        $r7.recycle();
        process(context, resources, xmlPullParser, attributeSet, theme);
        updateTintFilter();
    }

    public /* bridge */ /* synthetic */ void invalidateDrawable(Drawable drawable) {
        super.invalidateDrawable(drawable);
    }

    public /* bridge */ /* synthetic */ boolean isAutoMirrored() {
        return super.isAutoMirrored();
    }

    public boolean isStateful() {
        return true;
    }

    public void jumpToCurrentState() {
        super.jumpToCurrentState();
        RippleDrawable $r1 = this.w;
        if ($r1 != null) {
            $r1.stopAnimation();
            this.w = null;
            draw(this.y);
            this.y = -1;
            this.x = -1;
        }
    }

    public Drawable mutate() {
        if (!this.mMutated) {
            super.mutate();
            if (this == this) {
                this.a.init();
                this.mMutated = true;
            }
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public void mutate(DrawableContainer.DrawableContainerState drawableContainerState) {
        super.mutate(drawableContainerState);
        if (drawableContainerState instanceof ClassWriter) {
            this.a = (ClassWriter) drawableContainerState;
        }
    }

    public /* bridge */ /* synthetic */ boolean onLayoutDirectionChanged(int i) {
        return super.onLayoutDirectionChanged(i);
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        int $i0 = this.a.get(iArr);
        boolean $z0 = $i0 != getWidth() && (run($i0) || draw($i0));
        Drawable $r3 = getCurrent();
        return $r3 != null ? $z0 | $r3.setState(iArr) : $z0;
    }

    public /* bridge */ /* synthetic */ void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        super.scheduleDrawable(drawable, runnable, j);
    }

    public /* bridge */ /* synthetic */ void setAlpha(int i) {
        super.setAlpha(i);
    }

    public /* bridge */ /* synthetic */ void setAutoMirrored(boolean z) {
        super.setAutoMirrored(z);
    }

    public /* bridge */ /* synthetic */ void setColorFilter(ColorFilter colorFilter) {
        super.setColorFilter(colorFilter);
    }

    public /* bridge */ /* synthetic */ void setDither(boolean z) {
        super.setDither(z);
    }

    public /* bridge */ /* synthetic */ void setHotspot(float f, float f2) {
        super.setHotspot(f, f2);
    }

    public /* bridge */ /* synthetic */ void setHotspotBounds(int i, int i2, int i3, int i4) {
        super.setHotspotBounds(i, i2, i3, i4);
    }

    public /* bridge */ /* synthetic */ void setTintList(ColorStateList colorStateList) {
        super.setTintList(colorStateList);
    }

    public /* bridge */ /* synthetic */ void setTintMode(PorterDuff.Mode mode) {
        super.setTintMode(mode);
    }

    public boolean setVisible(boolean z, boolean z2) {
        boolean $z1 = super.setVisible(z, z2);
        if (this.w != null && ($z1 || z2)) {
            if (z) {
                this.w.start();
                return $z1;
            }
            jumpToCurrentState();
        }
        return $z1;
    }

    public /* bridge */ /* synthetic */ void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        super.unscheduleDrawable(drawable, runnable);
    }
}
