package android.support.p025v7.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.SeekBar;
import p000a.p001a.p017d.p018a.C0136a;

/* renamed from: android.support.v7.widget.z */
public class C0450z extends SeekBar {

    /* renamed from: a */
    private final C0317A f1660a;

    public C0450z(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0136a.seekBarStyle);
    }

    public C0450z(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1660a = new C0317A(this);
        this.f1660a.mo1492a(attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        this.f1660a.mo1493b();
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        this.f1660a.mo1494c();
    }

    /* access modifiers changed from: protected */
    public synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f1660a.mo1490a(canvas);
    }
}
