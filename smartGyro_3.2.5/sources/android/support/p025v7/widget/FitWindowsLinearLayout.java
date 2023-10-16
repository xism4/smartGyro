package android.support.p025v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.p025v7.widget.C0352O;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/* renamed from: android.support.v7.widget.FitWindowsLinearLayout */
public class FitWindowsLinearLayout extends LinearLayout implements C0352O {

    /* renamed from: a */
    private C0352O.C0353a f1225a;

    public FitWindowsLinearLayout(Context context) {
        super(context);
    }

    public FitWindowsLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public boolean fitSystemWindows(Rect rect) {
        C0352O.C0353a aVar = this.f1225a;
        if (aVar != null) {
            aVar.mo1037a(rect);
        }
        return super.fitSystemWindows(rect);
    }

    public void setOnFitSystemWindowsListener(C0352O.C0353a aVar) {
        this.f1225a = aVar;
    }
}
