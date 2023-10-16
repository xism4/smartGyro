package android.support.p025v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.p024v4.widget.C0218m;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import p000a.p001a.p005c.p014g.C0125s;
import p000a.p001a.p017d.p018a.C0136a;
import p000a.p001a.p017d.p019b.p020a.C0146a;

/* renamed from: android.support.v7.widget.i */
public class C0410i extends AutoCompleteTextView implements C0125s {

    /* renamed from: a */
    private static final int[] f1553a = {16843126};

    /* renamed from: b */
    private final C0412j f1554b;

    /* renamed from: c */
    private final C0341H f1555c;

    public C0410i(Context context) {
        this(context, (AttributeSet) null);
    }

    public C0410i(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0136a.autoCompleteTextViewStyle);
    }

    public C0410i(Context context, AttributeSet attributeSet, int i) {
        super(C0433qa.m1887a(context), attributeSet, i);
        C0439ta a = C0439ta.m1902a(getContext(), attributeSet, f1553a, i, 0);
        if (a.mo2287g(0)) {
            setDropDownBackgroundDrawable(a.mo2277b(0));
        }
        a.mo2274a();
        this.f1554b = new C0412j(this);
        this.f1554b.mo2170a(attributeSet, i);
        this.f1555c = new C0341H(this);
        this.f1555c.mo1725a(attributeSet, i);
        this.f1555c.mo1720a();
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        C0412j jVar = this.f1554b;
        if (jVar != null) {
            jVar.mo2165a();
        }
        C0341H h = this.f1555c;
        if (h != null) {
            h.mo1720a();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        C0412j jVar = this.f1554b;
        if (jVar != null) {
            return jVar.mo2171b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C0412j jVar = this.f1554b;
        if (jVar != null) {
            return jVar.mo2173c();
        }
        return null;
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        C0432q.m1886a(onCreateInputConnection, editorInfo, this);
        return onCreateInputConnection;
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0412j jVar = this.f1554b;
        if (jVar != null) {
            jVar.mo2169a(drawable);
        }
    }

    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        C0412j jVar = this.f1554b;
        if (jVar != null) {
            jVar.mo2166a(i);
        }
    }

    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(C0218m.m807a((TextView) this, callback));
    }

    public void setDropDownBackgroundResource(int i) {
        setDropDownBackgroundDrawable(C0146a.m492b(getContext(), i));
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        C0412j jVar = this.f1554b;
        if (jVar != null) {
            jVar.mo2172b(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        C0412j jVar = this.f1554b;
        if (jVar != null) {
            jVar.mo2168a(mode);
        }
    }

    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        C0341H h = this.f1555c;
        if (h != null) {
            h.mo1724a(context, i);
        }
    }
}
