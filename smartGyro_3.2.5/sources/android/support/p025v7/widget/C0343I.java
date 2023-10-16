package android.support.p025v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.p024v4.widget.C0204b;
import android.support.p024v4.widget.C0218m;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import p000a.p001a.p005c.p012e.C0074a;
import p000a.p001a.p005c.p014g.C0125s;

/* renamed from: android.support.v7.widget.I */
public class C0343I extends TextView implements C0125s, C0204b {

    /* renamed from: a */
    private final C0412j f1242a;

    /* renamed from: b */
    private final C0341H f1243b;

    /* renamed from: c */
    private Future<C0074a> f1244c;

    public C0343I(Context context) {
        this(context, (AttributeSet) null);
    }

    public C0343I(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842884);
    }

    public C0343I(Context context, AttributeSet attributeSet, int i) {
        super(C0433qa.m1887a(context), attributeSet, i);
        this.f1242a = new C0412j(this);
        this.f1242a.mo2170a(attributeSet, i);
        this.f1243b = new C0341H(this);
        this.f1243b.mo1725a(attributeSet, i);
        this.f1243b.mo1720a();
    }

    /* renamed from: d */
    private void mo1105d() {
        Future<C0074a> future = this.f1244c;
        if (future != null) {
            try {
                this.f1244c = null;
                C0218m.m810a((TextView) this, future.get());
            } catch (InterruptedException | ExecutionException unused) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        C0412j jVar = this.f1242a;
        if (jVar != null) {
            jVar.mo2165a();
        }
        C0341H h = this.f1243b;
        if (h != null) {
            h.mo1720a();
        }
    }

    public int getAutoSizeMaxTextSize() {
        if (C0204b.f538a) {
            return super.getAutoSizeMaxTextSize();
        }
        C0341H h = this.f1243b;
        if (h != null) {
            return h.mo1731c();
        }
        return -1;
    }

    public int getAutoSizeMinTextSize() {
        if (C0204b.f538a) {
            return super.getAutoSizeMinTextSize();
        }
        C0341H h = this.f1243b;
        if (h != null) {
            return h.mo1732d();
        }
        return -1;
    }

    public int getAutoSizeStepGranularity() {
        if (C0204b.f538a) {
            return super.getAutoSizeStepGranularity();
        }
        C0341H h = this.f1243b;
        if (h != null) {
            return h.mo1733e();
        }
        return -1;
    }

    public int[] getAutoSizeTextAvailableSizes() {
        if (C0204b.f538a) {
            return super.getAutoSizeTextAvailableSizes();
        }
        C0341H h = this.f1243b;
        return h != null ? h.mo1734f() : new int[0];
    }

    public int getAutoSizeTextType() {
        if (C0204b.f538a) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        C0341H h = this.f1243b;
        if (h != null) {
            return h.mo1735g();
        }
        return 0;
    }

    public int getFirstBaselineToTopHeight() {
        return C0218m.m806a((TextView) this);
    }

    public int getLastBaselineToBottomHeight() {
        return C0218m.m811b(this);
    }

    public ColorStateList getSupportBackgroundTintList() {
        C0412j jVar = this.f1242a;
        if (jVar != null) {
            return jVar.mo2171b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C0412j jVar = this.f1242a;
        if (jVar != null) {
            return jVar.mo2173c();
        }
        return null;
    }

    public CharSequence getText() {
        mo1105d();
        return super.getText();
    }

    public C0074a.C0075a getTextMetricsParamsCompat() {
        return C0218m.m813c(this);
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        C0432q.m1886a(onCreateInputConnection, editorInfo, this);
        return onCreateInputConnection;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        C0341H h = this.f1243b;
        if (h != null) {
            h.mo1728a(z, i, i2, i3, i4);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        mo1105d();
        super.onMeasure(i, i2);
    }

    /* access modifiers changed from: protected */
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        C0341H h = this.f1243b;
        if (h != null && !C0204b.f538a && h.mo1736h()) {
            this.f1243b.mo1730b();
        }
    }

    public void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) {
        if (C0204b.f538a) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
            return;
        }
        C0341H h = this.f1243b;
        if (h != null) {
            h.mo1723a(i, i2, i3, i4);
        }
    }

    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i) {
        if (C0204b.f538a) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i);
            return;
        }
        C0341H h = this.f1243b;
        if (h != null) {
            h.mo1729a(iArr, i);
        }
    }

    public void setAutoSizeTextTypeWithDefaults(int i) {
        if (C0204b.f538a) {
            super.setAutoSizeTextTypeWithDefaults(i);
            return;
        }
        C0341H h = this.f1243b;
        if (h != null) {
            h.mo1721a(i);
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0412j jVar = this.f1242a;
        if (jVar != null) {
            jVar.mo2169a(drawable);
        }
    }

    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        C0412j jVar = this.f1242a;
        if (jVar != null) {
            jVar.mo2166a(i);
        }
    }

    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(C0218m.m807a((TextView) this, callback));
    }

    public void setFirstBaselineToTopHeight(int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setFirstBaselineToTopHeight(i);
        } else {
            C0218m.m808a((TextView) this, i);
        }
    }

    public void setLastBaselineToBottomHeight(int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setLastBaselineToBottomHeight(i);
        } else {
            C0218m.m812b(this, i);
        }
    }

    public void setLineHeight(int i) {
        C0218m.m814c(this, i);
    }

    public void setPrecomputedText(C0074a aVar) {
        C0218m.m810a((TextView) this, aVar);
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        C0412j jVar = this.f1242a;
        if (jVar != null) {
            jVar.mo2172b(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        C0412j jVar = this.f1242a;
        if (jVar != null) {
            jVar.mo2168a(mode);
        }
    }

    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        C0341H h = this.f1243b;
        if (h != null) {
            h.mo1724a(context, i);
        }
    }

    public void setTextFuture(Future<C0074a> future) {
        this.f1244c = future;
        requestLayout();
    }

    public void setTextMetricsParamsCompat(C0074a.C0075a aVar) {
        C0218m.m809a((TextView) this, aVar);
    }

    public void setTextSize(int i, float f) {
        if (C0204b.f538a) {
            super.setTextSize(i, f);
            return;
        }
        C0341H h = this.f1243b;
        if (h != null) {
            h.mo1722a(i, f);
        }
    }
}
