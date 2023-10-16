package p000a.p001a.p003b.p004a;

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
import android.os.Build;
import android.support.p024v4.graphics.drawable.C0190a;
import android.util.AttributeSet;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import p000a.p001a.p005c.p006a.p007a.C0040i;
import p000a.p001a.p005c.p013f.C0078b;

/* renamed from: a.a.b.a.d */
public class C0006d extends C0014i implements C0004b {

    /* renamed from: b */
    private C0007a f13b;

    /* renamed from: c */
    private Context f14c;

    /* renamed from: d */
    private ArgbEvaluator f15d;

    /* renamed from: e */
    private Animator.AnimatorListener f16e;

    /* renamed from: f */
    ArrayList<Object> f17f;

    /* renamed from: g */
    final Drawable.Callback f18g;

    /* renamed from: a.a.b.a.d$a */
    private static class C0007a extends Drawable.ConstantState {

        /* renamed from: a */
        int f19a;

        /* renamed from: b */
        C0016k f20b;

        /* renamed from: c */
        AnimatorSet f21c;

        /* renamed from: d */
        ArrayList<Animator> f22d;

        /* renamed from: e */
        C0078b<Animator, String> f23e;

        public C0007a(Context context, C0007a aVar, Drawable.Callback callback, Resources resources) {
            if (aVar != null) {
                this.f19a = aVar.f19a;
                C0016k kVar = aVar.f20b;
                if (kVar != null) {
                    Drawable.ConstantState constantState = kVar.getConstantState();
                    this.f20b = (C0016k) (resources != null ? constantState.newDrawable(resources) : constantState.newDrawable());
                    C0016k kVar2 = this.f20b;
                    kVar2.mutate();
                    this.f20b = kVar2;
                    this.f20b.setCallback(callback);
                    this.f20b.setBounds(aVar.f20b.getBounds());
                    this.f20b.mo61a(false);
                }
                ArrayList<Animator> arrayList = aVar.f22d;
                if (arrayList != null) {
                    int size = arrayList.size();
                    this.f22d = new ArrayList<>(size);
                    this.f23e = new C0078b<>(size);
                    for (int i = 0; i < size; i++) {
                        Animator animator = aVar.f22d.get(i);
                        Animator clone = animator.clone();
                        String str = aVar.f23e.get(animator);
                        clone.setTarget(this.f20b.mo60a(str));
                        this.f22d.add(clone);
                        this.f23e.put(clone, str);
                    }
                    mo46a();
                }
            }
        }

        /* renamed from: a */
        public void mo46a() {
            if (this.f21c == null) {
                this.f21c = new AnimatorSet();
            }
            this.f21c.playTogether(this.f22d);
        }

        public int getChangingConfigurations() {
            return this.f19a;
        }

        public Drawable newDrawable() {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }

        public Drawable newDrawable(Resources resources) {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }
    }

    /* renamed from: a.a.b.a.d$b */
    private static class C0008b extends Drawable.ConstantState {

        /* renamed from: a */
        private final Drawable.ConstantState f24a;

        public C0008b(Drawable.ConstantState constantState) {
            this.f24a = constantState;
        }

        public boolean canApplyTheme() {
            return this.f24a.canApplyTheme();
        }

        public int getChangingConfigurations() {
            return this.f24a.getChangingConfigurations();
        }

        public Drawable newDrawable() {
            C0006d dVar = new C0006d();
            dVar.f29a = this.f24a.newDrawable();
            dVar.f29a.setCallback(dVar.f18g);
            return dVar;
        }

        public Drawable newDrawable(Resources resources) {
            C0006d dVar = new C0006d();
            dVar.f29a = this.f24a.newDrawable(resources);
            dVar.f29a.setCallback(dVar.f18g);
            return dVar;
        }

        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            C0006d dVar = new C0006d();
            dVar.f29a = this.f24a.newDrawable(resources, theme);
            dVar.f29a.setCallback(dVar.f18g);
            return dVar;
        }
    }

    C0006d() {
        this((Context) null, (C0007a) null, (Resources) null);
    }

    private C0006d(Context context) {
        this(context, (C0007a) null, (Resources) null);
    }

    private C0006d(Context context, C0007a aVar, Resources resources) {
        this.f15d = null;
        this.f16e = null;
        this.f17f = null;
        this.f18g = new C0005c(this);
        this.f14c = context;
        if (aVar != null) {
            this.f13b = aVar;
        } else {
            this.f13b = new C0007a(context, aVar, this.f18g, resources);
        }
    }

    /* renamed from: a */
    public static C0006d m0a(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        C0006d dVar = new C0006d(context);
        dVar.inflate(resources, xmlPullParser, attributeSet, theme);
        return dVar;
    }

    /* renamed from: a */
    private void m1a(Animator animator) {
        ArrayList<Animator> childAnimations;
        if ((animator instanceof AnimatorSet) && (childAnimations = ((AnimatorSet) animator).getChildAnimations()) != null) {
            for (int i = 0; i < childAnimations.size(); i++) {
                m1a(childAnimations.get(i));
            }
        }
        if (animator instanceof ObjectAnimator) {
            ObjectAnimator objectAnimator = (ObjectAnimator) animator;
            String propertyName = objectAnimator.getPropertyName();
            if ("fillColor".equals(propertyName) || "strokeColor".equals(propertyName)) {
                if (this.f15d == null) {
                    this.f15d = new ArgbEvaluator();
                }
                objectAnimator.setEvaluator(this.f15d);
            }
        }
    }

    /* renamed from: a */
    private void m2a(String str, Animator animator) {
        animator.setTarget(this.f13b.f20b.mo60a(str));
        if (Build.VERSION.SDK_INT < 21) {
            m1a(animator);
        }
        C0007a aVar = this.f13b;
        if (aVar.f22d == null) {
            aVar.f22d = new ArrayList<>();
            this.f13b.f23e = new C0078b<>();
        }
        this.f13b.f22d.add(animator);
        this.f13b.f23e.put(animator, str);
    }

    public void applyTheme(Resources.Theme theme) {
        Drawable drawable = this.f29a;
        if (drawable != null) {
            C0190a.m675a(drawable, theme);
        }
    }

    public boolean canApplyTheme() {
        Drawable drawable = this.f29a;
        if (drawable != null) {
            return C0190a.m679a(drawable);
        }
        return false;
    }

    public /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    public void draw(Canvas canvas) {
        Drawable drawable = this.f29a;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        this.f13b.f20b.draw(canvas);
        if (this.f13b.f21c.isStarted()) {
            invalidateSelf();
        }
    }

    public int getAlpha() {
        Drawable drawable = this.f29a;
        return drawable != null ? C0190a.m681b(drawable) : this.f13b.f20b.getAlpha();
    }

    public int getChangingConfigurations() {
        Drawable drawable = this.f29a;
        return drawable != null ? drawable.getChangingConfigurations() : super.getChangingConfigurations() | this.f13b.f19a;
    }

    public /* bridge */ /* synthetic */ ColorFilter getColorFilter() {
        return super.getColorFilter();
    }

    public Drawable.ConstantState getConstantState() {
        Drawable drawable = this.f29a;
        if (drawable == null || Build.VERSION.SDK_INT < 24) {
            return null;
        }
        return new C0008b(drawable.getConstantState());
    }

    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    public int getIntrinsicHeight() {
        Drawable drawable = this.f29a;
        return drawable != null ? drawable.getIntrinsicHeight() : this.f13b.f20b.getIntrinsicHeight();
    }

    public int getIntrinsicWidth() {
        Drawable drawable = this.f29a;
        return drawable != null ? drawable.getIntrinsicWidth() : this.f13b.f20b.getIntrinsicWidth();
    }

    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    public int getOpacity() {
        Drawable drawable = this.f29a;
        return drawable != null ? drawable.getOpacity() : this.f13b.f20b.getOpacity();
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
        TypedArray obtainAttributes;
        Drawable drawable = this.f29a;
        if (drawable != null) {
            C0190a.m676a(drawable, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        int eventType = xmlPullParser.getEventType();
        int depth = xmlPullParser.getDepth() + 1;
        while (eventType != 1 && (xmlPullParser.getDepth() >= depth || eventType != 3)) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                if ("animated-vector".equals(name)) {
                    obtainAttributes = C0040i.m131a(resources, theme, attributeSet, C0003a.f4e);
                    int resourceId = obtainAttributes.getResourceId(0, 0);
                    if (resourceId != 0) {
                        C0016k a = C0016k.m32a(resources, resourceId, theme);
                        a.mo61a(false);
                        a.setCallback(this.f18g);
                        C0016k kVar = this.f13b.f20b;
                        if (kVar != null) {
                            kVar.setCallback((Drawable.Callback) null);
                        }
                        this.f13b.f20b = a;
                    }
                } else if ("target".equals(name)) {
                    obtainAttributes = resources.obtainAttributes(attributeSet, C0003a.f5f);
                    String string = obtainAttributes.getString(0);
                    int resourceId2 = obtainAttributes.getResourceId(1, 0);
                    if (resourceId2 != 0) {
                        Context context = this.f14c;
                        if (context != null) {
                            m2a(string, C0010f.m8a(context, resourceId2));
                        } else {
                            obtainAttributes.recycle();
                            throw new IllegalStateException("Context can't be null when inflating animators");
                        }
                    }
                } else {
                    continue;
                }
                obtainAttributes.recycle();
            }
            eventType = xmlPullParser.next();
        }
        this.f13b.mo46a();
    }

    public boolean isAutoMirrored() {
        Drawable drawable = this.f29a;
        return drawable != null ? C0190a.m685e(drawable) : this.f13b.f20b.isAutoMirrored();
    }

    public boolean isRunning() {
        Drawable drawable = this.f29a;
        return drawable != null ? ((AnimatedVectorDrawable) drawable).isRunning() : this.f13b.f21c.isRunning();
    }

    public boolean isStateful() {
        Drawable drawable = this.f29a;
        return drawable != null ? drawable.isStateful() : this.f13b.f20b.isStateful();
    }

    public /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    public Drawable mutate() {
        Drawable drawable = this.f29a;
        if (drawable != null) {
            drawable.mutate();
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.f29a;
        if (drawable != null) {
            drawable.setBounds(rect);
        } else {
            this.f13b.f20b.setBounds(rect);
        }
    }

    /* access modifiers changed from: protected */
    public boolean onLevelChange(int i) {
        Drawable drawable = this.f29a;
        return drawable != null ? drawable.setLevel(i) : this.f13b.f20b.setLevel(i);
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        Drawable drawable = this.f29a;
        return drawable != null ? drawable.setState(iArr) : this.f13b.f20b.setState(iArr);
    }

    public void setAlpha(int i) {
        Drawable drawable = this.f29a;
        if (drawable != null) {
            drawable.setAlpha(i);
        } else {
            this.f13b.f20b.setAlpha(i);
        }
    }

    public void setAutoMirrored(boolean z) {
        Drawable drawable = this.f29a;
        if (drawable != null) {
            C0190a.m678a(drawable, z);
        } else {
            this.f13b.f20b.setAutoMirrored(z);
        }
    }

    public /* bridge */ /* synthetic */ void setChangingConfigurations(int i) {
        super.setChangingConfigurations(i);
    }

    public /* bridge */ /* synthetic */ void setColorFilter(int i, PorterDuff.Mode mode) {
        super.setColorFilter(i, mode);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f29a;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.f13b.f20b.setColorFilter(colorFilter);
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
        Drawable drawable = this.f29a;
        if (drawable != null) {
            C0190a.m682b(drawable, i);
        } else {
            this.f13b.f20b.setTint(i);
        }
    }

    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f29a;
        if (drawable != null) {
            C0190a.m674a(drawable, colorStateList);
        } else {
            this.f13b.f20b.setTintList(colorStateList);
        }
    }

    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f29a;
        if (drawable != null) {
            C0190a.m677a(drawable, mode);
        } else {
            this.f13b.f20b.setTintMode(mode);
        }
    }

    public boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = this.f29a;
        if (drawable != null) {
            return drawable.setVisible(z, z2);
        }
        this.f13b.f20b.setVisible(z, z2);
        return super.setVisible(z, z2);
    }

    public void start() {
        Drawable drawable = this.f29a;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).start();
        } else if (!this.f13b.f21c.isStarted()) {
            this.f13b.f21c.start();
            invalidateSelf();
        }
    }

    public void stop() {
        Drawable drawable = this.f29a;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).stop();
        } else {
            this.f13b.f21c.end();
        }
    }
}
