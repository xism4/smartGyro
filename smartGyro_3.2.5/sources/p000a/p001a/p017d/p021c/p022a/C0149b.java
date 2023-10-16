package p000a.p001a.p017d.p021c.p022a;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.StateSet;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p000a.p001a.p003b.p004a.C0006d;
import p000a.p001a.p003b.p004a.C0016k;
import p000a.p001a.p005c.p006a.p007a.C0040i;
import p000a.p001a.p005c.p013f.C0080d;
import p000a.p001a.p005c.p013f.C0091j;
import p000a.p001a.p017d.p018a.C0145j;
import p000a.p001a.p017d.p019b.p020a.C0146a;
import p000a.p001a.p017d.p021c.p022a.C0157d;
import p000a.p001a.p017d.p021c.p022a.C0161f;

/* renamed from: a.a.d.c.a.b */
public class C0149b extends C0161f {

    /* renamed from: p */
    private C0151b f306p;

    /* renamed from: q */
    private C0155f f307q;

    /* renamed from: r */
    private int f308r;

    /* renamed from: s */
    private int f309s;

    /* renamed from: t */
    private boolean f310t;

    /* renamed from: a.a.d.c.a.b$a */
    private static class C0150a extends C0155f {

        /* renamed from: a */
        private final Animatable f311a;

        C0150a(Animatable animatable) {
            super();
            this.f311a = animatable;
        }

        /* renamed from: c */
        public void mo548c() {
            this.f311a.start();
        }

        /* renamed from: d */
        public void mo549d() {
            this.f311a.stop();
        }
    }

    /* renamed from: a.a.d.c.a.b$b */
    static class C0151b extends C0161f.C0162a {

        /* renamed from: K */
        C0080d<Long> f312K;

        /* renamed from: L */
        C0091j<Integer> f313L;

        C0151b(C0151b bVar, C0149b bVar2, Resources resources) {
            super(bVar, bVar2, resources);
            C0091j<Integer> jVar;
            if (bVar != null) {
                this.f312K = bVar.f312K;
                jVar = bVar.f313L;
            } else {
                this.f312K = new C0080d<>();
                jVar = new C0091j<>();
            }
            this.f313L = jVar;
        }

        /* renamed from: f */
        private static long m510f(int i, int i2) {
            return ((long) i2) | (((long) i) << 32);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public int mo550a(int i, int i2, Drawable drawable, boolean z) {
            int a = super.mo578a(drawable);
            long f = m510f(i, i2);
            long j = z ? 8589934592L : 0;
            long j2 = (long) a;
            this.f312K.mo285a(f, Long.valueOf(j2 | j));
            if (z) {
                this.f312K.mo285a(m510f(i2, i), Long.valueOf(4294967296L | j2 | j));
            }
            return a;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public int mo551a(int[] iArr, Drawable drawable, int i) {
            int a = super.mo638a(iArr, drawable);
            this.f313L.mo386c(a, Integer.valueOf(i));
            return a;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public int mo552b(int[] iArr) {
            int a = super.mo637a(iArr);
            return a >= 0 ? a : super.mo637a(StateSet.WILD_CARD);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: c */
        public int mo553c(int i, int i2) {
            return (int) this.f312K.mo288b(m510f(i, i2), -1L).longValue();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: d */
        public int mo554d(int i) {
            if (i < 0) {
                return 0;
            }
            return this.f313L.mo384b(i, 0).intValue();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: d */
        public boolean mo555d(int i, int i2) {
            return (this.f312K.mo288b(m510f(i, i2), -1L).longValue() & 4294967296L) != 0;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: e */
        public boolean mo556e(int i, int i2) {
            return (this.f312K.mo288b(m510f(i, i2), -1L).longValue() & 8589934592L) != 0;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: m */
        public void mo557m() {
            this.f312K = this.f312K.clone();
            this.f313L = this.f313L.clone();
        }

        public Drawable newDrawable() {
            return new C0149b(this, (Resources) null);
        }

        public Drawable newDrawable(Resources resources) {
            return new C0149b(this, resources);
        }
    }

    /* renamed from: a.a.d.c.a.b$c */
    private static class C0152c extends C0155f {

        /* renamed from: a */
        private final C0006d f314a;

        C0152c(C0006d dVar) {
            super();
            this.f314a = dVar;
        }

        /* renamed from: c */
        public void mo548c() {
            this.f314a.start();
        }

        /* renamed from: d */
        public void mo549d() {
            this.f314a.stop();
        }
    }

    /* renamed from: a.a.d.c.a.b$d */
    private static class C0153d extends C0155f {

        /* renamed from: a */
        private final ObjectAnimator f315a;

        /* renamed from: b */
        private final boolean f316b;

        C0153d(AnimationDrawable animationDrawable, boolean z, boolean z2) {
            super();
            int numberOfFrames = animationDrawable.getNumberOfFrames();
            int i = z ? numberOfFrames - 1 : 0;
            int i2 = z ? 0 : numberOfFrames - 1;
            C0154e eVar = new C0154e(animationDrawable, z);
            ObjectAnimator ofInt = ObjectAnimator.ofInt(animationDrawable, "currentIndex", new int[]{i, i2});
            if (Build.VERSION.SDK_INT >= 18) {
                ofInt.setAutoCancel(true);
            }
            ofInt.setDuration((long) eVar.mo562a());
            ofInt.setInterpolator(eVar);
            this.f316b = z2;
            this.f315a = ofInt;
        }

        /* renamed from: a */
        public boolean mo560a() {
            return this.f316b;
        }

        /* renamed from: b */
        public void mo561b() {
            this.f315a.reverse();
        }

        /* renamed from: c */
        public void mo548c() {
            this.f315a.start();
        }

        /* renamed from: d */
        public void mo549d() {
            this.f315a.cancel();
        }
    }

    /* renamed from: a.a.d.c.a.b$e */
    private static class C0154e implements TimeInterpolator {

        /* renamed from: a */
        private int[] f317a;

        /* renamed from: b */
        private int f318b;

        /* renamed from: c */
        private int f319c;

        C0154e(AnimationDrawable animationDrawable, boolean z) {
            mo563a(animationDrawable, z);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public int mo562a() {
            return this.f319c;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public int mo563a(AnimationDrawable animationDrawable, boolean z) {
            int numberOfFrames = animationDrawable.getNumberOfFrames();
            this.f318b = numberOfFrames;
            int[] iArr = this.f317a;
            if (iArr == null || iArr.length < numberOfFrames) {
                this.f317a = new int[numberOfFrames];
            }
            int[] iArr2 = this.f317a;
            int i = 0;
            for (int i2 = 0; i2 < numberOfFrames; i2++) {
                int duration = animationDrawable.getDuration(z ? (numberOfFrames - i2) - 1 : i2);
                iArr2[i2] = duration;
                i += duration;
            }
            this.f319c = i;
            return i;
        }

        public float getInterpolation(float f) {
            int i = (int) ((f * ((float) this.f319c)) + 0.5f);
            int i2 = this.f318b;
            int[] iArr = this.f317a;
            int i3 = 0;
            while (i3 < i2 && i >= iArr[i3]) {
                i -= iArr[i3];
                i3++;
            }
            return (((float) i3) / ((float) i2)) + (i3 < i2 ? ((float) i) / ((float) this.f319c) : 0.0f);
        }
    }

    /* renamed from: a.a.d.c.a.b$f */
    private static abstract class C0155f {
        private C0155f() {
        }

        /* renamed from: a */
        public boolean mo560a() {
            return false;
        }

        /* renamed from: b */
        public void mo561b() {
        }

        /* renamed from: c */
        public abstract void mo548c();

        /* renamed from: d */
        public abstract void mo549d();
    }

    public C0149b() {
        this((C0151b) null, (Resources) null);
    }

    C0149b(C0151b bVar, Resources resources) {
        super((C0161f.C0162a) null);
        this.f308r = -1;
        this.f309s = -1;
        mo514a((C0157d.C0159b) new C0151b(bVar, this, resources));
        onStateChange(getState());
        jumpToCurrentState();
    }

    /* renamed from: a */
    public static C0149b m496a(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        String name = xmlPullParser.getName();
        if (name.equals("animated-selector")) {
            C0149b bVar = new C0149b();
            bVar.mo516b(context, resources, xmlPullParser, attributeSet, theme);
            return bVar;
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": invalid animated-selector tag " + name);
    }

    /* renamed from: a */
    private void m497a(TypedArray typedArray) {
        C0151b bVar = this.f306p;
        if (Build.VERSION.SDK_INT >= 21) {
            bVar.f347d |= typedArray.getChangingConfigurations();
        }
        bVar.mo587b(typedArray.getBoolean(C0145j.AnimatedStateListDrawableCompat_android_variablePadding, bVar.f352i));
        bVar.mo583a(typedArray.getBoolean(C0145j.AnimatedStateListDrawableCompat_android_constantSize, bVar.f355l));
        bVar.mo586b(typedArray.getInt(C0145j.AnimatedStateListDrawableCompat_android_enterFadeDuration, bVar.f335A));
        bVar.mo590c(typedArray.getInt(C0145j.AnimatedStateListDrawableCompat_android_exitFadeDuration, bVar.f336B));
        setDither(typedArray.getBoolean(C0145j.AnimatedStateListDrawableCompat_android_dither, bVar.f367x));
    }

    /* renamed from: b */
    private boolean m498b(int i) {
        int i2;
        int c;
        C0155f fVar;
        C0155f fVar2 = this.f307q;
        if (fVar2 == null) {
            i2 = mo569b();
        } else if (i == this.f308r) {
            return true;
        } else {
            if (i != this.f309s || !fVar2.mo560a()) {
                i2 = this.f308r;
                fVar2.mo549d();
            } else {
                fVar2.mo561b();
                this.f308r = this.f309s;
                this.f309s = i;
                return true;
            }
        }
        this.f307q = null;
        this.f309s = -1;
        this.f308r = -1;
        C0151b bVar = this.f306p;
        int d = bVar.mo554d(i2);
        int d2 = bVar.mo554d(i);
        if (d2 == 0 || d == 0 || (c = bVar.mo553c(d, d2)) < 0) {
            return false;
        }
        boolean e = bVar.mo556e(d, d2);
        mo568a(c);
        Drawable current = getCurrent();
        if (current instanceof AnimationDrawable) {
            fVar = new C0153d((AnimationDrawable) current, bVar.mo555d(d, d2), e);
        } else if (current instanceof C0006d) {
            fVar = new C0152c((C0006d) current);
        } else {
            if (current instanceof Animatable) {
                fVar = new C0150a((Animatable) current);
            }
            return false;
        }
        fVar.mo548c();
        this.f307q = fVar;
        this.f309s = i2;
        this.f308r = i;
        return true;
    }

    /* renamed from: c */
    private void m499c(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        int depth = xmlPullParser.getDepth() + 1;
        while (true) {
            int next = xmlPullParser.next();
            if (next != 1) {
                int depth2 = xmlPullParser.getDepth();
                if (depth2 < depth && next == 3) {
                    return;
                }
                if (next == 2 && depth2 <= depth) {
                    if (xmlPullParser.getName().equals("item")) {
                        m500d(context, resources, xmlPullParser, attributeSet, theme);
                    } else if (xmlPullParser.getName().equals("transition")) {
                        m502e(context, resources, xmlPullParser, attributeSet, theme);
                    }
                }
            } else {
                return;
            }
        }
    }

    /* renamed from: d */
    private int m500d(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        int next;
        TypedArray a = C0040i.m131a(resources, theme, attributeSet, C0145j.AnimatedStateListDrawableItem);
        int resourceId = a.getResourceId(C0145j.AnimatedStateListDrawableItem_android_id, 0);
        int resourceId2 = a.getResourceId(C0145j.AnimatedStateListDrawableItem_android_drawable, -1);
        Drawable b = resourceId2 > 0 ? C0146a.m492b(context, resourceId2) : null;
        a.recycle();
        int[] a2 = mo636a(attributeSet);
        if (b == null) {
            do {
                next = xmlPullParser.next();
            } while (next == 4);
            if (next == 2) {
                b = xmlPullParser.getName().equals("vector") ? C0016k.createFromXmlInner(resources, xmlPullParser, attributeSet, theme) : Build.VERSION.SDK_INT >= 21 ? Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet, theme) : Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet);
            } else {
                throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <item> tag requires a 'drawable' attribute or child tag defining a drawable");
            }
        }
        if (b != null) {
            return this.f306p.mo551a(a2, b, resourceId);
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <item> tag requires a 'drawable' attribute or child tag defining a drawable");
    }

    /* renamed from: d */
    private void m501d() {
        onStateChange(getState());
    }

    /* renamed from: e */
    private int m502e(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        int next;
        TypedArray a = C0040i.m131a(resources, theme, attributeSet, C0145j.AnimatedStateListDrawableTransition);
        int resourceId = a.getResourceId(C0145j.AnimatedStateListDrawableTransition_android_fromId, -1);
        int resourceId2 = a.getResourceId(C0145j.AnimatedStateListDrawableTransition_android_toId, -1);
        int resourceId3 = a.getResourceId(C0145j.AnimatedStateListDrawableTransition_android_drawable, -1);
        Drawable b = resourceId3 > 0 ? C0146a.m492b(context, resourceId3) : null;
        boolean z = a.getBoolean(C0145j.AnimatedStateListDrawableTransition_android_reversible, false);
        a.recycle();
        if (b == null) {
            do {
                next = xmlPullParser.next();
            } while (next == 4);
            if (next == 2) {
                b = xmlPullParser.getName().equals("animated-vector") ? C0006d.m0a(context, resources, xmlPullParser, attributeSet, theme) : Build.VERSION.SDK_INT >= 21 ? Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet, theme) : Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet);
            } else {
                throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <transition> tag requires a 'drawable' attribute or child tag defining a drawable");
            }
        }
        if (b == null) {
            throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <transition> tag requires a 'drawable' attribute or child tag defining a drawable");
        } else if (resourceId != -1 && resourceId2 != -1) {
            return this.f306p.mo550a(resourceId, resourceId2, b, z);
        } else {
            throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <transition> tag requires 'fromId' & 'toId' attributes");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public C0151b m505a() {
        return new C0151b(this.f306p, this, (Resources) null);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo514a(C0157d.C0159b bVar) {
        super.mo514a(bVar);
        if (bVar instanceof C0151b) {
            this.f306p = (C0151b) bVar;
        }
    }

    public /* bridge */ /* synthetic */ void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
    }

    /* renamed from: b */
    public void mo516b(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        TypedArray a = C0040i.m131a(resources, theme, attributeSet, C0145j.AnimatedStateListDrawableCompat);
        setVisible(a.getBoolean(C0145j.AnimatedStateListDrawableCompat_android_visible, true), true);
        m497a(a);
        mo566a(resources);
        a.recycle();
        m499c(context, resources, xmlPullParser, attributeSet, theme);
        m501d();
    }

    public /* bridge */ /* synthetic */ boolean canApplyTheme() {
        return super.canApplyTheme();
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
        C0155f fVar = this.f307q;
        if (fVar != null) {
            fVar.mo549d();
            this.f307q = null;
            mo568a(this.f308r);
            this.f308r = -1;
            this.f309s = -1;
        }
    }

    public Drawable mutate() {
        if (!this.f310t) {
            super.mutate();
            if (this == this) {
                this.f306p.mo557m();
                this.f310t = true;
            }
        }
        return this;
    }

    public /* bridge */ /* synthetic */ boolean onLayoutDirectionChanged(int i) {
        return super.onLayoutDirectionChanged(i);
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        int b = this.f306p.mo552b(iArr);
        boolean z = b != mo569b() && (m498b(b) || mo568a(b));
        Drawable current = getCurrent();
        return current != null ? z | current.setState(iArr) : z;
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
        boolean visible = super.setVisible(z, z2);
        if (this.f307q != null && (visible || z2)) {
            if (z) {
                this.f307q.mo548c();
            } else {
                jumpToCurrentState();
            }
        }
        return visible;
    }

    public /* bridge */ /* synthetic */ void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        super.unscheduleDrawable(drawable, runnable);
    }
}
