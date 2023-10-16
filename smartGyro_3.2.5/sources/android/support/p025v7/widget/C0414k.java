package android.support.p025v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.p024v4.widget.C0204b;
import android.support.p024v4.widget.C0218m;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.TextView;
import p000a.p001a.p005c.p014g.C0125s;
import p000a.p001a.p017d.p018a.C0136a;

/* renamed from: android.support.v7.widget.k */
public class C0414k extends Button implements C0125s, C0204b {

    /* renamed from: a */
    private final C0412j f1564a;

    /* renamed from: b */
    private final C0341H f1565b;

    public C0414k(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0136a.buttonStyle);
    }

    public C0414k(Context context, AttributeSet attributeSet, int i) {
        super(C0433qa.m1887a(context), attributeSet, i);
        this.f1564a = new C0412j(this);
        this.f1564a.mo2170a(attributeSet, i);
        this.f1565b = new C0341H(this);
        this.f1565b.mo1725a(attributeSet, i);
        this.f1565b.mo1720a();
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        C0412j jVar = this.f1564a;
        if (jVar != null) {
            jVar.mo2165a();
        }
        C0341H h = this.f1565b;
        if (h != null) {
            h.mo1720a();
        }
    }

    public int getAutoSizeMaxTextSize() {
        if (C0204b.f538a) {
            return super.getAutoSizeMaxTextSize();
        }
        C0341H h = this.f1565b;
        if (h != null) {
            return h.mo1731c();
        }
        return -1;
    }

    public int getAutoSizeMinTextSize() {
        if (C0204b.f538a) {
            return super.getAutoSizeMinTextSize();
        }
        C0341H h = this.f1565b;
        if (h != null) {
            return h.mo1732d();
        }
        return -1;
    }

    public int getAutoSizeStepGranularity() {
        if (C0204b.f538a) {
            return super.getAutoSizeStepGranularity();
        }
        C0341H h = this.f1565b;
        if (h != null) {
            return h.mo1733e();
        }
        return -1;
    }

    public int[] getAutoSizeTextAvailableSizes() {
        if (C0204b.f538a) {
            return super.getAutoSizeTextAvailableSizes();
        }
        C0341H h = this.f1565b;
        return h != null ? h.mo1734f() : new int[0];
    }

    public int getAutoSizeTextType() {
        if (C0204b.f538a) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        C0341H h = this.f1565b;
        if (h != null) {
            return h.mo1735g();
        }
        return 0;
    }

    public ColorStateList getSupportBackgroundTintList() {
        C0412j jVar = this.f1564a;
        if (jVar != null) {
            return jVar.mo2171b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C0412j jVar = this.f1564a;
        if (jVar != null) {
            return jVar.mo2173c();
        }
        return null;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(Button.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(Button.class.getName());
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        C0341H h = this.f1565b;
        if (h != null) {
            h.mo1728a(z, i, i2, i3, i4);
        }
    }

    /* access modifiers changed from: protected */
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        C0341H h = this.f1565b;
        if (h != null && !C0204b.f538a && h.mo1736h()) {
            this.f1565b.mo1730b();
        }
    }

    public void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) {
        if (C0204b.f538a) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
            return;
        }
        C0341H h = this.f1565b;
        if (h != null) {
            h.mo1723a(i, i2, i3, i4);
        }
    }

    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i) {
        if (C0204b.f538a) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i);
            return;
        }
        C0341H h = this.f1565b;
        if (h != null) {
            h.mo1729a(iArr, i);
        }
    }

    public void setAutoSizeTextTypeWithDefaults(int i) {
        if (C0204b.f538a) {
            super.setAutoSizeTextTypeWithDefaults(i);
            return;
        }
        C0341H h = this.f1565b;
        if (h != null) {
            h.mo1721a(i);
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0412j jVar = this.f1564a;
        if (jVar != null) {
            jVar.mo2169a(drawable);
        }
    }

    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        C0412j jVar = this.f1564a;
        if (jVar != null) {
            jVar.mo2166a(i);
        }
    }

    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(C0218m.m807a((TextView) this, callback));
    }

    public void setSupportAllCaps(boolean z) {
        C0341H h = this.f1565b;
        if (h != null) {
            h.mo1727a(z);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        C0412j jVar = this.f1564a;
        if (jVar != null) {
            jVar.mo2172b(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        C0412j jVar = this.f1564a;
        if (jVar != null) {
            jVar.mo2168a(mode);
        }
    }

    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        C0341H h = this.f1565b;
        if (h != null) {
            h.mo1724a(context, i);
        }
    }

    public void setTextSize(int i, float f) {
        if (C0204b.f538a) {
            super.setTextSize(i, f);
            return;
        }
        C0341H h = this.f1565b;
        if (h != null) {
            h.mo1722a(i, f);
        }
    }
}
