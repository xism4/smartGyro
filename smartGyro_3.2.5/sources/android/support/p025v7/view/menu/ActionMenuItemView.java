package android.support.p025v7.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.p025v7.view.menu.C0293l;
import android.support.p025v7.view.menu.C0312w;
import android.support.p025v7.widget.ActionMenuView;
import android.support.p025v7.widget.C0327Ba;
import android.support.p025v7.widget.C0343I;
import android.support.p025v7.widget.C0354P;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import p000a.p001a.p017d.p018a.C0145j;

/* renamed from: android.support.v7.view.menu.ActionMenuItemView */
public class ActionMenuItemView extends C0343I implements C0312w.C0313a, View.OnClickListener, ActionMenuView.C0321a {

    /* renamed from: d */
    C0299p f847d;

    /* renamed from: e */
    private CharSequence f848e;

    /* renamed from: f */
    private Drawable f849f;

    /* renamed from: g */
    C0293l.C0295b f850g;

    /* renamed from: h */
    private C0354P f851h;

    /* renamed from: i */
    C0275b f852i;

    /* renamed from: j */
    private boolean f853j;

    /* renamed from: k */
    private boolean f854k;

    /* renamed from: l */
    private int f855l;

    /* renamed from: m */
    private int f856m;

    /* renamed from: n */
    private int f857n;

    /* renamed from: android.support.v7.view.menu.ActionMenuItemView$a */
    private class C0274a extends C0354P {
        public C0274a() {
            super(ActionMenuItemView.this);
        }

        /* renamed from: a */
        public C0316z mo1120a() {
            C0275b bVar = ActionMenuItemView.this.f852i;
            if (bVar != null) {
                return bVar.mo1122a();
            }
            return null;
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Code restructure failed: missing block: B:4:0x000f, code lost:
            r0 = mo1120a();
         */
        /* renamed from: b */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean mo1121b() {
            /*
                r3 = this;
                android.support.v7.view.menu.ActionMenuItemView r0 = android.support.p025v7.view.menu.ActionMenuItemView.this
                android.support.v7.view.menu.l$b r1 = r0.f850g
                r2 = 0
                if (r1 == 0) goto L_0x001c
                android.support.v7.view.menu.p r0 = r0.f847d
                boolean r0 = r1.mo1175a(r0)
                if (r0 == 0) goto L_0x001c
                android.support.v7.view.menu.z r0 = r3.mo1120a()
                if (r0 == 0) goto L_0x001c
                boolean r0 = r0.mo1136b()
                if (r0 == 0) goto L_0x001c
                r2 = 1
            L_0x001c:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p025v7.view.menu.ActionMenuItemView.C0274a.mo1121b():boolean");
        }
    }

    /* renamed from: android.support.v7.view.menu.ActionMenuItemView$b */
    public static abstract class C0275b {
        /* renamed from: a */
        public abstract C0316z mo1122a();
    }

    public ActionMenuItemView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Resources resources = context.getResources();
        this.f853j = m1080e();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0145j.ActionMenuItemView, i, 0);
        this.f855l = obtainStyledAttributes.getDimensionPixelSize(C0145j.ActionMenuItemView_android_minWidth, 0);
        obtainStyledAttributes.recycle();
        this.f857n = (int) ((resources.getDisplayMetrics().density * 32.0f) + 0.5f);
        setOnClickListener(this);
        this.f856m = -1;
        setSaveEnabled(false);
    }

    /* renamed from: e */
    private boolean m1080e() {
        Configuration configuration = getContext().getResources().getConfiguration();
        int i = configuration.screenWidthDp;
        return i >= 480 || (i >= 640 && configuration.screenHeightDp >= 480) || configuration.orientation == 2;
    }

    /* renamed from: f */
    private void m1081f() {
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(this.f848e);
        if (this.f849f != null && (!this.f847d.mo1371n() || (!this.f853j && !this.f854k))) {
            z = false;
        }
        boolean z3 = z2 & z;
        CharSequence charSequence = null;
        setText(z3 ? this.f848e : null);
        CharSequence contentDescription = this.f847d.getContentDescription();
        if (TextUtils.isEmpty(contentDescription)) {
            contentDescription = z3 ? null : this.f847d.getTitle();
        }
        setContentDescription(contentDescription);
        CharSequence tooltipText = this.f847d.getTooltipText();
        if (TextUtils.isEmpty(tooltipText)) {
            if (!z3) {
                charSequence = this.f847d.getTitle();
            }
            C0327Ba.m1444a(this, charSequence);
            return;
        }
        C0327Ba.m1444a(this, tooltipText);
    }

    /* renamed from: a */
    public void mo1101a(C0299p pVar, int i) {
        this.f847d = pVar;
        setIcon(pVar.getIcon());
        setTitle(pVar.mo1334a((C0312w.C0313a) this));
        setId(pVar.getItemId());
        setVisibility(pVar.isVisible() ? 0 : 8);
        setEnabled(pVar.isEnabled());
        if (pVar.hasSubMenu() && this.f851h == null) {
            this.f851h = new C0274a();
        }
    }

    /* renamed from: a */
    public boolean mo1102a() {
        return mo1105d();
    }

    /* renamed from: b */
    public boolean mo1103b() {
        return mo1105d() && this.f847d.getIcon() == null;
    }

    /* renamed from: c */
    public boolean mo1104c() {
        return true;
    }

    /* renamed from: d */
    public boolean mo1105d() {
        return !TextUtils.isEmpty(getText());
    }

    public C0299p getItemData() {
        return this.f847d;
    }

    public void onClick(View view) {
        C0293l.C0295b bVar = this.f850g;
        if (bVar != null) {
            bVar.mo1175a(this.f847d);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f853j = m1080e();
        m1081f();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        boolean d = mo1105d();
        if (d && (i3 = this.f856m) >= 0) {
            super.setPadding(i3, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int measuredWidth = getMeasuredWidth();
        int min = mode == Integer.MIN_VALUE ? Math.min(size, this.f855l) : this.f855l;
        if (mode != 1073741824 && this.f855l > 0 && measuredWidth < min) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(min, 1073741824), i2);
        }
        if (!d && this.f849f != null) {
            super.setPadding((getMeasuredWidth() - this.f849f.getBounds().width()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState((Parcelable) null);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        C0354P p;
        if (!this.f847d.hasSubMenu() || (p = this.f851h) == null || !p.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    public void setCheckable(boolean z) {
    }

    public void setChecked(boolean z) {
    }

    public void setExpandedFormat(boolean z) {
        if (this.f854k != z) {
            this.f854k = z;
            C0299p pVar = this.f847d;
            if (pVar != null) {
                pVar.mo1338b();
            }
        }
    }

    public void setIcon(Drawable drawable) {
        this.f849f = drawable;
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int i = this.f857n;
            if (intrinsicWidth > i) {
                intrinsicHeight = (int) (((float) intrinsicHeight) * (((float) i) / ((float) intrinsicWidth)));
                intrinsicWidth = i;
            }
            int i2 = this.f857n;
            if (intrinsicHeight > i2) {
                intrinsicWidth = (int) (((float) intrinsicWidth) * (((float) i2) / ((float) intrinsicHeight)));
                intrinsicHeight = i2;
            }
            drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        }
        setCompoundDrawables(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
        m1081f();
    }

    public void setItemInvoker(C0293l.C0295b bVar) {
        this.f850g = bVar;
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        this.f856m = i;
        super.setPadding(i, i2, i3, i4);
    }

    public void setPopupCallback(C0275b bVar) {
        this.f852i = bVar;
    }

    public void setTitle(CharSequence charSequence) {
        this.f848e = charSequence;
        m1081f();
    }
}
