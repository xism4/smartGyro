package p000a.p001a.p017d.p021c.p022a;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.StateSet;
import p000a.p001a.p017d.p021c.p022a.C0157d;

/* renamed from: a.a.d.c.a.f */
class C0161f extends C0157d {

    /* renamed from: n */
    private C0162a f371n;

    /* renamed from: o */
    private boolean f372o;

    /* renamed from: a.a.d.c.a.f$a */
    static class C0162a extends C0157d.C0159b {

        /* renamed from: J */
        int[][] f373J;

        C0162a(C0162a aVar, C0161f fVar, Resources resources) {
            super(aVar, fVar, resources);
            if (aVar != null) {
                this.f373J = aVar.f373J;
            } else {
                this.f373J = new int[mo589c()][];
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public int mo637a(int[] iArr) {
            int[][] iArr2 = this.f373J;
            int d = mo592d();
            for (int i = 0; i < d; i++) {
                if (StateSet.stateSetMatches(iArr2[i], iArr)) {
                    return i;
                }
            }
            return -1;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public int mo638a(int[] iArr, Drawable drawable) {
            int a = mo578a(drawable);
            this.f373J[a] = iArr;
            return a;
        }

        /* renamed from: a */
        public void mo580a(int i, int i2) {
            super.mo580a(i, i2);
            int[][] iArr = new int[i2][];
            System.arraycopy(this.f373J, 0, iArr, 0, i);
            this.f373J = iArr;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: m */
        public void mo557m() {
            int[][] iArr = this.f373J;
            int[][] iArr2 = new int[iArr.length][];
            for (int length = iArr.length - 1; length >= 0; length--) {
                int[][] iArr3 = this.f373J;
                iArr2[length] = iArr3[length] != null ? (int[]) iArr3[length].clone() : null;
            }
            this.f373J = iArr2;
        }

        public Drawable newDrawable() {
            return new C0161f(this, (Resources) null);
        }

        public Drawable newDrawable(Resources resources) {
            return new C0161f(this, resources);
        }
    }

    C0161f(C0162a aVar) {
        if (aVar != null) {
            mo514a((C0157d.C0159b) aVar);
        }
    }

    C0161f(C0162a aVar, Resources resources) {
        mo514a((C0157d.C0159b) new C0162a(aVar, this, resources));
        onStateChange(getState());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public C0162a m570a() {
        return new C0162a(this.f371n, this, (Resources) null);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo514a(C0157d.C0159b bVar) {
        super.mo514a(bVar);
        if (bVar instanceof C0162a) {
            this.f371n = (C0162a) bVar;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public int[] mo636a(AttributeSet attributeSet) {
        int attributeCount = attributeSet.getAttributeCount();
        int[] iArr = new int[attributeCount];
        int i = 0;
        for (int i2 = 0; i2 < attributeCount; i2++) {
            int attributeNameResource = attributeSet.getAttributeNameResource(i2);
            if (!(attributeNameResource == 0 || attributeNameResource == 16842960 || attributeNameResource == 16843161)) {
                int i3 = i + 1;
                if (!attributeSet.getAttributeBooleanValue(i2, false)) {
                    attributeNameResource = -attributeNameResource;
                }
                iArr[i] = attributeNameResource;
                i = i3;
            }
        }
        return StateSet.trimStateSet(iArr, i);
    }

    public void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
        onStateChange(getState());
    }

    public boolean isStateful() {
        return true;
    }

    public Drawable mutate() {
        if (!this.f372o) {
            super.mutate();
            if (this == this) {
                this.f371n.mo557m();
                this.f372o = true;
            }
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        boolean onStateChange = super.onStateChange(iArr);
        int a = this.f371n.mo637a(iArr);
        if (a < 0) {
            a = this.f371n.mo637a(StateSet.WILD_CARD);
        }
        return mo568a(a) || onStateChange;
    }
}
