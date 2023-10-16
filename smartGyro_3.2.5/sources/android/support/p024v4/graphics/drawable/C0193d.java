package android.support.p024v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;

/* renamed from: android.support.v4.graphics.drawable.d */
class C0193d extends Drawable implements Drawable.Callback, C0192c, C0191b {

    /* renamed from: a */
    static final PorterDuff.Mode f467a = PorterDuff.Mode.SRC_IN;

    /* renamed from: b */
    private int f468b;

    /* renamed from: c */
    private PorterDuff.Mode f469c;

    /* renamed from: d */
    private boolean f470d;

    /* renamed from: e */
    C0194a f471e;

    /* renamed from: f */
    private boolean f472f;

    /* renamed from: g */
    Drawable f473g;

    /* renamed from: android.support.v4.graphics.drawable.d$a */
    protected static abstract class C0194a extends Drawable.ConstantState {

        /* renamed from: a */
        int f474a;

        /* renamed from: b */
        Drawable.ConstantState f475b;

        /* renamed from: c */
        ColorStateList f476c = null;

        /* renamed from: d */
        PorterDuff.Mode f477d = C0193d.f467a;

        C0194a(C0194a aVar, Resources resources) {
            if (aVar != null) {
                this.f474a = aVar.f474a;
                this.f475b = aVar.f475b;
                this.f476c = aVar.f476c;
                this.f477d = aVar.f477d;
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public boolean mo787a() {
            return this.f475b != null;
        }

        public int getChangingConfigurations() {
            int i = this.f474a;
            Drawable.ConstantState constantState = this.f475b;
            return i | (constantState != null ? constantState.getChangingConfigurations() : 0);
        }

        public Drawable newDrawable() {
            return newDrawable((Resources) null);
        }

        public abstract Drawable newDrawable(Resources resources);
    }

    /* renamed from: android.support.v4.graphics.drawable.d$b */
    private static class C0195b extends C0194a {
        C0195b(C0194a aVar, Resources resources) {
            super(aVar, resources);
        }

        public Drawable newDrawable(Resources resources) {
            return new C0193d(this, resources);
        }
    }

    C0193d(Drawable drawable) {
        this.f471e = mo757c();
        mo755a(drawable);
    }

    C0193d(C0194a aVar, Resources resources) {
        this.f471e = aVar;
        m690a(resources);
    }

    /* renamed from: a */
    private void m690a(Resources resources) {
        Drawable.ConstantState constantState;
        C0194a aVar = this.f471e;
        if (aVar != null && (constantState = aVar.f475b) != null) {
            mo755a(constantState.newDrawable(resources));
        }
    }

    /* renamed from: a */
    private boolean m691a(int[] iArr) {
        if (!mo756b()) {
            return false;
        }
        C0194a aVar = this.f471e;
        ColorStateList colorStateList = aVar.f476c;
        PorterDuff.Mode mode = aVar.f477d;
        if (colorStateList == null || mode == null) {
            this.f470d = false;
            clearColorFilter();
        } else {
            int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
            if (!(this.f470d && colorForState == this.f468b && mode == this.f469c)) {
                setColorFilter(colorForState, mode);
                this.f468b = colorForState;
                this.f469c = mode;
                this.f470d = true;
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public final Drawable mo754a() {
        return this.f473g;
    }

    /* renamed from: a */
    public final void mo755a(Drawable drawable) {
        Drawable drawable2 = this.f473g;
        if (drawable2 != null) {
            drawable2.setCallback((Drawable.Callback) null);
        }
        this.f473g = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            setVisible(drawable.isVisible(), true);
            setState(drawable.getState());
            setLevel(drawable.getLevel());
            setBounds(drawable.getBounds());
            C0194a aVar = this.f471e;
            if (aVar != null) {
                aVar.f475b = drawable.getConstantState();
            }
        }
        invalidateSelf();
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public boolean mo756b() {
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public C0194a mo757c() {
        return new C0195b(this.f471e, (Resources) null);
    }

    public void draw(Canvas canvas) {
        this.f473g.draw(canvas);
    }

    public int getChangingConfigurations() {
        int changingConfigurations = super.getChangingConfigurations();
        C0194a aVar = this.f471e;
        return changingConfigurations | (aVar != null ? aVar.getChangingConfigurations() : 0) | this.f473g.getChangingConfigurations();
    }

    public Drawable.ConstantState getConstantState() {
        C0194a aVar = this.f471e;
        if (aVar == null || !aVar.mo787a()) {
            return null;
        }
        this.f471e.f474a = getChangingConfigurations();
        return this.f471e;
    }

    public Drawable getCurrent() {
        return this.f473g.getCurrent();
    }

    public int getIntrinsicHeight() {
        return this.f473g.getIntrinsicHeight();
    }

    public int getIntrinsicWidth() {
        return this.f473g.getIntrinsicWidth();
    }

    public int getMinimumHeight() {
        return this.f473g.getMinimumHeight();
    }

    public int getMinimumWidth() {
        return this.f473g.getMinimumWidth();
    }

    public int getOpacity() {
        return this.f473g.getOpacity();
    }

    public boolean getPadding(Rect rect) {
        return this.f473g.getPadding(rect);
    }

    public int[] getState() {
        return this.f473g.getState();
    }

    public Region getTransparentRegion() {
        return this.f473g.getTransparentRegion();
    }

    public void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    public boolean isAutoMirrored() {
        return this.f473g.isAutoMirrored();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = r1.f471e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isStateful() {
        /*
            r1 = this;
            boolean r0 = r1.mo756b()
            if (r0 == 0) goto L_0x000d
            android.support.v4.graphics.drawable.d$a r0 = r1.f471e
            if (r0 == 0) goto L_0x000d
            android.content.res.ColorStateList r0 = r0.f476c
            goto L_0x000e
        L_0x000d:
            r0 = 0
        L_0x000e:
            if (r0 == 0) goto L_0x0016
            boolean r0 = r0.isStateful()
            if (r0 != 0) goto L_0x001e
        L_0x0016:
            android.graphics.drawable.Drawable r0 = r1.f473g
            boolean r0 = r0.isStateful()
            if (r0 == 0) goto L_0x0020
        L_0x001e:
            r0 = 1
            goto L_0x0021
        L_0x0020:
            r0 = 0
        L_0x0021:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p024v4.graphics.drawable.C0193d.isStateful():boolean");
    }

    public void jumpToCurrentState() {
        this.f473g.jumpToCurrentState();
    }

    public Drawable mutate() {
        if (!this.f472f && super.mutate() == this) {
            this.f471e = mo757c();
            Drawable drawable = this.f473g;
            if (drawable != null) {
                drawable.mutate();
            }
            C0194a aVar = this.f471e;
            if (aVar != null) {
                Drawable drawable2 = this.f473g;
                aVar.f475b = drawable2 != null ? drawable2.getConstantState() : null;
            }
            this.f472f = true;
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.f473g;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    /* access modifiers changed from: protected */
    public boolean onLevelChange(int i) {
        return this.f473g.setLevel(i);
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }

    public void setAlpha(int i) {
        this.f473g.setAlpha(i);
    }

    public void setAutoMirrored(boolean z) {
        this.f473g.setAutoMirrored(z);
    }

    public void setChangingConfigurations(int i) {
        this.f473g.setChangingConfigurations(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.f473g.setColorFilter(colorFilter);
    }

    public void setDither(boolean z) {
        this.f473g.setDither(z);
    }

    public void setFilterBitmap(boolean z) {
        this.f473g.setFilterBitmap(z);
    }

    public boolean setState(int[] iArr) {
        return m691a(iArr) || this.f473g.setState(iArr);
    }

    public void setTint(int i) {
        setTintList(ColorStateList.valueOf(i));
    }

    public void setTintList(ColorStateList colorStateList) {
        this.f471e.f476c = colorStateList;
        m691a(getState());
    }

    public void setTintMode(PorterDuff.Mode mode) {
        this.f471e.f477d = mode;
        m691a(getState());
    }

    public boolean setVisible(boolean z, boolean z2) {
        return super.setVisible(z, z2) || this.f473g.setVisible(z, z2);
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }
}
