package android.support.p025v7.widget;

import android.content.Context;
import android.support.p024v4.widget.C0218m;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;
import android.widget.TextView;
import p000a.p001a.p017d.p019b.p020a.C0146a;

/* renamed from: android.support.v7.widget.m */
public class C0418m extends CheckedTextView {

    /* renamed from: a */
    private static final int[] f1568a = {16843016};

    /* renamed from: b */
    private final C0341H f1569b;

    public C0418m(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16843720);
    }

    public C0418m(Context context, AttributeSet attributeSet, int i) {
        super(C0433qa.m1887a(context), attributeSet, i);
        this.f1569b = new C0341H(this);
        this.f1569b.mo1725a(attributeSet, i);
        this.f1569b.mo1720a();
        C0439ta a = C0439ta.m1902a(getContext(), attributeSet, f1568a, i, 0);
        setCheckMarkDrawable(a.mo2277b(0));
        a.mo2274a();
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        C0341H h = this.f1569b;
        if (h != null) {
            h.mo1720a();
        }
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        C0432q.m1886a(onCreateInputConnection, editorInfo, this);
        return onCreateInputConnection;
    }

    public void setCheckMarkDrawable(int i) {
        setCheckMarkDrawable(C0146a.m492b(getContext(), i));
    }

    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(C0218m.m807a((TextView) this, callback));
    }

    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        C0341H h = this.f1569b;
        if (h != null) {
            h.mo1724a(context, i);
        }
    }
}
