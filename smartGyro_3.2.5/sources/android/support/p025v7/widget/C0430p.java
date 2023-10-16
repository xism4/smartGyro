package android.support.p025v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.p024v4.widget.C0218m;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.widget.TextView;
import p000a.p001a.p005c.p014g.C0125s;
import p000a.p001a.p017d.p018a.C0136a;

/* renamed from: android.support.v7.widget.p */
public class C0430p extends EditText implements C0125s {

    /* renamed from: a */
    private final C0412j f1622a;

    /* renamed from: b */
    private final C0341H f1623b;

    public C0430p(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0136a.editTextStyle);
    }

    public C0430p(Context context, AttributeSet attributeSet, int i) {
        super(C0433qa.m1887a(context), attributeSet, i);
        this.f1622a = new C0412j(this);
        this.f1622a.mo2170a(attributeSet, i);
        this.f1623b = new C0341H(this);
        this.f1623b.mo1725a(attributeSet, i);
        this.f1623b.mo1720a();
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        C0412j jVar = this.f1622a;
        if (jVar != null) {
            jVar.mo2165a();
        }
        C0341H h = this.f1623b;
        if (h != null) {
            h.mo1720a();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        C0412j jVar = this.f1622a;
        if (jVar != null) {
            return jVar.mo2171b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C0412j jVar = this.f1622a;
        if (jVar != null) {
            return jVar.mo2173c();
        }
        return null;
    }

    public Editable getText() {
        return Build.VERSION.SDK_INT >= 28 ? super.getText() : super.getEditableText();
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        C0432q.m1886a(onCreateInputConnection, editorInfo, this);
        return onCreateInputConnection;
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0412j jVar = this.f1622a;
        if (jVar != null) {
            jVar.mo2169a(drawable);
        }
    }

    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        C0412j jVar = this.f1622a;
        if (jVar != null) {
            jVar.mo2166a(i);
        }
    }

    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(C0218m.m807a((TextView) this, callback));
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        C0412j jVar = this.f1622a;
        if (jVar != null) {
            jVar.mo2172b(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        C0412j jVar = this.f1622a;
        if (jVar != null) {
            jVar.mo2168a(mode);
        }
    }

    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        C0341H h = this.f1623b;
        if (h != null) {
            h.mo1724a(context, i);
        }
    }
}
