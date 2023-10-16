package com.org.shortcuts.drawable;

import a.a.c.f.b;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import com.org.android.ui.asm.TypedArrayUtils;
import com.org.android.util.ArrayMap;
import com.org.shortcuts.drawable.VectorDrawableCompat;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;

public class AnimatedVectorDrawableCompat extends VectorDrawableCommon implements Animatable {
    private AnimatedVectorDrawableCompatState mAnimatedVectorState;
    private ArgbEvaluator mArgbEvaluator;
    final Drawable.Callback mCallback;
    private Context mContext;
    ArrayList<Object> mData;
    private Animator.AnimatorListener mListener;

    class AnimatedVectorDrawableCompatState extends Drawable.ConstantState {
        AnimatorSet animator;
        ArrayList<Animator> mAnimators;
        int mChangingConfigurations;
        b<Animator, String> mTargetNameMap;
        VectorDrawableCompat mVectorDrawable;

        public AnimatedVectorDrawableCompatState(Context context, AnimatedVectorDrawableCompatState animatedVectorDrawableCompatState, Drawable.Callback callback, Resources resources) {
            if (animatedVectorDrawableCompatState != null) {
                AnimatedVectorDrawableCompatState animatedVectorDrawableCompatState2 = animatedVectorDrawableCompatState;
                AnimatedVectorDrawableCompatState animatedVectorDrawableCompatState3 = animatedVectorDrawableCompatState2;
                this.mChangingConfigurations = animatedVectorDrawableCompatState2.mChangingConfigurations;
                VectorDrawableCompat $r5 = animatedVectorDrawableCompatState3.mVectorDrawable;
                if ($r5 != null) {
                    Drawable.ConstantState $r6 = $r5.getConstantState();
                    this.mVectorDrawable = (VectorDrawableCompat) (resources != null ? $r6.newDrawable(resources) : $r6.newDrawable());
                    VectorDrawableCompat $r52 = this.mVectorDrawable;
                    $r52.mutate();
                    this.mVectorDrawable = $r52;
                    this.mVectorDrawable.setCallback(callback);
                    this.mVectorDrawable.setBounds(animatedVectorDrawableCompatState3.mVectorDrawable.getBounds());
                    this.mVectorDrawable.setAllowCaching(false);
                }
                ArrayList $r10 = animatedVectorDrawableCompatState3.mAnimators;
                if ($r10 != null) {
                    int $i1 = $r10.size();
                    this.mAnimators = new ArrayList($i1);
                    this.mTargetNameMap = new ArrayMap($i1);
                    for (int $i0 = 0; $i0 < $i1; $i0++) {
                        Animator $r13 = animatedVectorDrawableCompatState3.mAnimators.get($i0);
                        Animator $r14 = $r13.clone();
                        String $r15 = (String) animatedVectorDrawableCompatState3.mTargetNameMap.get($r13);
                        $r14.setTarget(this.mVectorDrawable.getTargetByName($r15));
                        this.mAnimators.add($r14);
                        this.mTargetNameMap.put($r14, $r15);
                    }
                    start();
                }
            }
        }

        public int getChangingConfigurations() {
            return this.mChangingConfigurations;
        }

        public Drawable newDrawable() {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }

        public Drawable newDrawable(Resources resources) {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }

        public void start() {
            if (this.animator == null) {
                this.animator = new AnimatorSet();
            }
            this.animator.playTogether(this.mAnimators);
        }
    }

    class AnimatedVectorDrawableDelegateState extends Drawable.ConstantState {
        private final Drawable.ConstantState mDelegateState;

        public AnimatedVectorDrawableDelegateState(Drawable.ConstantState constantState) {
            this.mDelegateState = constantState;
        }

        public boolean canApplyTheme() {
            return this.mDelegateState.canApplyTheme();
        }

        public int getChangingConfigurations() {
            return this.mDelegateState.getChangingConfigurations();
        }

        public Drawable newDrawable() {
            VectorDrawableCompat $r1 = r5;
            VectorDrawableCompat r5 = new VectorDrawableCompat();
            $r1.mDelegateDrawable = (VectorDrawable) this.mDelegateState.newDrawable();
            return $r1;
        }

        public Drawable newDrawable(Resources resources) {
            VectorDrawableCompat $r1 = r6;
            VectorDrawableCompat r6 = new VectorDrawableCompat();
            $r1.mDelegateDrawable = (VectorDrawable) this.mDelegateState.newDrawable(resources);
            return $r1;
        }

        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            VectorDrawableCompat $r2 = r7;
            VectorDrawableCompat r7 = new VectorDrawableCompat();
            $r2.mDelegateDrawable = (VectorDrawable) this.mDelegateState.newDrawable(resources, theme);
            return $r2;
        }
    }

    AnimatedVectorDrawableCompat() {
        this((Context) null, (AnimatedVectorDrawableCompatState) null, (Resources) null);
    }

    private AnimatedVectorDrawableCompat(Context context) {
        this(context, (AnimatedVectorDrawableCompatState) null, (Resources) null);
    }

    private AnimatedVectorDrawableCompat(Context context, AnimatedVectorDrawableCompatState animatedVectorDrawableCompatState, Resources resources) {
        this.mArgbEvaluator = null;
        this.mListener = null;
        this.mData = null;
        this.mCallback = new MaterialProgressDrawable$3(this);
        this.mContext = context;
        if (animatedVectorDrawableCompatState != null) {
            this.mAnimatedVectorState = animatedVectorDrawableCompatState;
        } else {
            this.mAnimatedVectorState = new AnimatedVectorDrawableCompatState(context, animatedVectorDrawableCompatState, this.mCallback, resources);
        }
    }

    public static AnimatedVectorDrawableCompat createFromXmlInner(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        AnimatedVectorDrawableCompat $r5 = new AnimatedVectorDrawableCompat(context);
        $r5.inflate(resources, xmlPullParser, attributeSet, theme);
        return $r5;
    }

    private void setupAnimatorsForTarget(String str, Animator animator) {
        animator.setTarget(this.mAnimatedVectorState.mVectorDrawable.getTargetByName(str));
        if (Build.VERSION.SDK_INT < 21) {
            setupColorAnimator(animator);
        }
        AnimatedVectorDrawableCompatState $r4 = this.mAnimatedVectorState;
        if ($r4.mAnimators == null) {
            $r4.mAnimators = new ArrayList();
            this.mAnimatedVectorState.mTargetNameMap = new ArrayMap();
        }
        this.mAnimatedVectorState.mAnimators.add(animator);
        this.mAnimatedVectorState.mTargetNameMap.put(animator, str);
    }

    private void setupColorAnimator(Animator animator) {
        ArrayList $r3;
        if ((animator instanceof AnimatorSet) && ($r3 = ((AnimatorSet) animator).getChildAnimations()) != null) {
            for (int $i0 = 0; $i0 < $r3.size(); $i0++) {
                setupColorAnimator($r3.get($i0));
            }
        }
        if (animator instanceof ObjectAnimator) {
            ObjectAnimator $r6 = (ObjectAnimator) animator;
            String $r7 = $r6.getPropertyName();
            if ("fillColor".equals($r7) || "strokeColor".equals($r7)) {
                if (this.mArgbEvaluator == null) {
                    this.mArgbEvaluator = new ArgbEvaluator();
                }
                $r6.setEvaluator(this.mArgbEvaluator);
            }
        }
    }

    public void applyTheme(Resources.Theme theme) {
        Drawable $r2 = this.mDelegateDrawable;
        if ($r2 != null) {
            DrawableCompat.applyTheme($r2, theme);
        }
    }

    public boolean canApplyTheme() {
        Drawable $r1 = this.mDelegateDrawable;
        if ($r1 != null) {
            return DrawableCompat.canApplyTheme($r1);
        }
        return false;
    }

    public /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    public void draw(Canvas canvas) {
        Drawable $r2 = this.mDelegateDrawable;
        if ($r2 != null) {
            $r2.draw(canvas);
            return;
        }
        this.mAnimatedVectorState.mVectorDrawable.draw(canvas);
        if (this.mAnimatedVectorState.animator.isStarted()) {
            invalidateSelf();
        }
    }

    public int getAlpha() {
        Drawable $r1 = this.mDelegateDrawable;
        return $r1 != null ? DrawableCompat.getAlpha($r1) : this.mAnimatedVectorState.mVectorDrawable.getAlpha();
    }

    public int getChangingConfigurations() {
        Drawable $r1 = this.mDelegateDrawable;
        return $r1 != null ? $r1.getChangingConfigurations() : super.getChangingConfigurations() | this.mAnimatedVectorState.mChangingConfigurations;
    }

    public /* bridge */ /* synthetic */ ColorFilter getColorFilter() {
        return super.getColorFilter();
    }

    public Drawable.ConstantState getConstantState() {
        Drawable $r2 = this.mDelegateDrawable;
        if ($r2 == null || Build.VERSION.SDK_INT < 24) {
            return null;
        }
        return new VectorDrawableCompat.VectorDrawableDelegateState($r2.getConstantState());
    }

    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    public int getIntrinsicHeight() {
        Drawable $r1 = this.mDelegateDrawable;
        return $r1 != null ? $r1.getIntrinsicHeight() : this.mAnimatedVectorState.mVectorDrawable.getIntrinsicHeight();
    }

    public int getIntrinsicWidth() {
        Drawable $r1 = this.mDelegateDrawable;
        return $r1 != null ? $r1.getIntrinsicWidth() : this.mAnimatedVectorState.mVectorDrawable.getIntrinsicWidth();
    }

    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    public int getOpacity() {
        Drawable $r1 = this.mDelegateDrawable;
        return $r1 != null ? $r1.getOpacity() : this.mAnimatedVectorState.mVectorDrawable.getOpacity();
    }

    public /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    public /* bridge */ /* synthetic */ int[] getState() {
        return super.getState();
    }

    public /* bridge */ /* synthetic */ Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) {
        inflate(resources, xmlPullParser, attributeSet, (Resources.Theme) null);
    }

    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        TypedArray $r10;
        Drawable $r5 = this.mDelegateDrawable;
        if ($r5 != null) {
            DrawableCompat.inflate($r5, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        int $i1 = xmlPullParser.getEventType();
        int $i0 = xmlPullParser.getDepth() + 1;
        while ($i1 != 1 && (xmlPullParser.getDepth() >= $i0 || $i1 != 3)) {
            if ($i1 == 2) {
                String $r6 = xmlPullParser.getName();
                if ("animated-vector".equals($r6)) {
                    TypedArray $r9 = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.styleable_AnimatedVectorDrawable);
                    $r10 = $r9;
                    int $i12 = $r9.getResourceId(0, 0);
                    if ($i12 != 0) {
                        VectorDrawableCompat $r11 = VectorDrawableCompat.create(resources, $i12, theme);
                        $r11.setAllowCaching(false);
                        $r11.setCallback(this.mCallback);
                        AnimatedVectorDrawableCompatState $r13 = this.mAnimatedVectorState;
                        AnimatedVectorDrawableCompatState animatedVectorDrawableCompatState = $r13;
                        VectorDrawableCompat $r14 = $r13.mVectorDrawable;
                        if ($r14 != null) {
                            $r14.setCallback((Drawable.Callback) null);
                        }
                        this.mAnimatedVectorState.mVectorDrawable = $r11;
                    }
                } else if ("target".equals($r6)) {
                    TypedArray $r92 = resources.obtainAttributes(attributeSet, AndroidResources.styleable_AnimatedVectorDrawableTarget);
                    $r10 = $r92;
                    String $r62 = $r92.getString(0);
                    int $i13 = $r92.getResourceId(1, 0);
                    if ($i13 != 0) {
                        Context $r15 = this.mContext;
                        if ($r15 != null) {
                            setupAnimatorsForTarget($r62, Context.getDrawable($r15, $i13));
                        } else {
                            $r92.recycle();
                            throw new IllegalStateException("Context can't be null when inflating animators");
                        }
                    }
                } else {
                    continue;
                }
                $r10.recycle();
            }
            $i1 = xmlPullParser.next();
        }
        AnimatedVectorDrawableCompatState $r132 = this.mAnimatedVectorState;
        AnimatedVectorDrawableCompatState animatedVectorDrawableCompatState2 = $r132;
        $r132.start();
    }

    public boolean isAutoMirrored() {
        Drawable $r1 = this.mDelegateDrawable;
        return $r1 != null ? DrawableCompat.isAutoMirrored($r1) : this.mAnimatedVectorState.mVectorDrawable.isAutoMirrored();
    }

    public boolean isRunning() {
        Drawable $r1 = this.mDelegateDrawable;
        return $r1 != null ? ((AnimatedVectorDrawable) $r1).isRunning() : this.mAnimatedVectorState.animator.isRunning();
    }

    public boolean isStateful() {
        Drawable $r1 = this.mDelegateDrawable;
        return $r1 != null ? $r1.isStateful() : this.mAnimatedVectorState.mVectorDrawable.isStateful();
    }

    public /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    public Drawable mutate() {
        Drawable $r1 = this.mDelegateDrawable;
        if ($r1 != null) {
            $r1.mutate();
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        Drawable $r2 = this.mDelegateDrawable;
        if ($r2 != null) {
            $r2.setBounds(rect);
        } else {
            this.mAnimatedVectorState.mVectorDrawable.setBounds(rect);
        }
    }

    /* access modifiers changed from: protected */
    public boolean onLevelChange(int i) {
        Drawable $r1 = this.mDelegateDrawable;
        return $r1 != null ? $r1.setLevel(i) : this.mAnimatedVectorState.mVectorDrawable.setLevel(i);
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        Drawable $r2 = this.mDelegateDrawable;
        return $r2 != null ? $r2.setState(iArr) : this.mAnimatedVectorState.mVectorDrawable.setState(iArr);
    }

    public void setAlpha(int i) {
        Drawable $r1 = this.mDelegateDrawable;
        if ($r1 != null) {
            $r1.setAlpha(i);
        } else {
            this.mAnimatedVectorState.mVectorDrawable.setAlpha(i);
        }
    }

    public void setAutoMirrored(boolean z) {
        Drawable $r1 = this.mDelegateDrawable;
        if ($r1 != null) {
            DrawableCompat.setHotspotBounds($r1, z);
        } else {
            this.mAnimatedVectorState.mVectorDrawable.setAutoMirrored(z);
        }
    }

    public /* bridge */ /* synthetic */ void setChangingConfigurations(int i) {
        super.setChangingConfigurations(i);
    }

    public /* bridge */ /* synthetic */ void setColorFilter(int i, PorterDuff.Mode mode) {
        super.setColorFilter(i, mode);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        Drawable $r2 = this.mDelegateDrawable;
        if ($r2 != null) {
            $r2.setColorFilter(colorFilter);
        } else {
            this.mAnimatedVectorState.mVectorDrawable.setColorFilter(colorFilter);
        }
    }

    public /* bridge */ /* synthetic */ void setFilterBitmap(boolean z) {
        super.setFilterBitmap(z);
    }

    public /* bridge */ /* synthetic */ void setHotspot(float f, float f2) {
        super.setHotspot(f, f2);
    }

    public /* bridge */ /* synthetic */ void setHotspotBounds(int i, int i2, int i3, int i4) {
        super.setHotspotBounds(i, i2, i3, i4);
    }

    public /* bridge */ /* synthetic */ boolean setState(int[] iArr) {
        return super.setState(iArr);
    }

    public void setTint(int i) {
        Drawable $r1 = this.mDelegateDrawable;
        if ($r1 != null) {
            DrawableCompat.setTint($r1, i);
        } else {
            this.mAnimatedVectorState.mVectorDrawable.setTint(i);
        }
    }

    public void setTintList(ColorStateList colorStateList) {
        Drawable $r2 = this.mDelegateDrawable;
        if ($r2 != null) {
            DrawableCompat.setTintList($r2, colorStateList);
        } else {
            this.mAnimatedVectorState.mVectorDrawable.setTintList(colorStateList);
        }
    }

    public void setTintMode(PorterDuff.Mode mode) {
        Drawable $r2 = this.mDelegateDrawable;
        if ($r2 != null) {
            DrawableCompat.setTintMode($r2, mode);
        } else {
            this.mAnimatedVectorState.mVectorDrawable.setTintMode(mode);
        }
    }

    public boolean setVisible(boolean $z1, boolean z) {
        Drawable $r1 = this.mDelegateDrawable;
        if ($r1 != null) {
            return $r1.setVisible($z1, z);
        }
        this.mAnimatedVectorState.mVectorDrawable.setVisible($z1, z);
        return super.setVisible($z1, z);
    }

    public void start() {
        Drawable $r1 = this.mDelegateDrawable;
        if ($r1 != null) {
            ((AnimatedVectorDrawable) $r1).start();
        } else if (!this.mAnimatedVectorState.animator.isStarted()) {
            this.mAnimatedVectorState.animator.start();
            invalidateSelf();
        }
    }

    public void stop() {
        Drawable $r1 = this.mDelegateDrawable;
        if ($r1 != null) {
            ((AnimatedVectorDrawable) $r1).stop();
        } else {
            this.mAnimatedVectorState.animator.end();
        }
    }
}
