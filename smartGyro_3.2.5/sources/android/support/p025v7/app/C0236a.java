package android.support.p025v7.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import p000a.p001a.p017d.p018a.C0145j;
import p000a.p001a.p017d.p023d.C0164b;

/* renamed from: android.support.v7.app.a */
public abstract class C0236a {

    /* renamed from: android.support.v7.app.a$a */
    public static class C0237a extends ViewGroup.MarginLayoutParams {

        /* renamed from: a */
        public int f728a;

        public C0237a(int i, int i2) {
            super(i, i2);
            this.f728a = 0;
            this.f728a = 8388627;
        }

        public C0237a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f728a = 0;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0145j.ActionBarLayout);
            this.f728a = obtainStyledAttributes.getInt(C0145j.ActionBarLayout_android_layout_gravity, 0);
            obtainStyledAttributes.recycle();
        }

        public C0237a(C0237a aVar) {
            super(aVar);
            this.f728a = 0;
            this.f728a = aVar.f728a;
        }

        public C0237a(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f728a = 0;
        }
    }

    /* renamed from: android.support.v7.app.a$b */
    public interface C0238b {
        void onMenuVisibilityChanged(boolean z);
    }

    @Deprecated
    /* renamed from: android.support.v7.app.a$c */
    public static abstract class C0239c {
        /* renamed from: a */
        public abstract CharSequence mo989a();

        /* renamed from: b */
        public abstract View mo990b();

        /* renamed from: c */
        public abstract Drawable mo991c();

        /* renamed from: d */
        public abstract CharSequence mo992d();

        /* renamed from: e */
        public abstract void mo993e();
    }

    /* renamed from: a */
    public abstract C0164b mo962a(C0164b.C0165a aVar);

    /* renamed from: a */
    public abstract void mo966a(CharSequence charSequence);

    /* renamed from: a */
    public abstract boolean mo968a(int i, KeyEvent keyEvent);

    /* renamed from: b */
    public abstract void mo970b(boolean z);

    /* renamed from: c */
    public abstract void mo972c(boolean z);

    /* renamed from: d */
    public abstract void mo974d(boolean z);

    /* renamed from: e */
    public abstract boolean mo976e();

    /* renamed from: f */
    public abstract Context mo977f();

    /* renamed from: g */
    public boolean mo987g() {
        return false;
    }
}
