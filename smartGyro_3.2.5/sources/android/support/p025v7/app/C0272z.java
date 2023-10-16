package android.support.p025v7.app;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import p000a.p001a.p005c.p014g.C0112g;
import p000a.p001a.p017d.p018a.C0136a;
import p000a.p001a.p017d.p023d.C0164b;

/* renamed from: android.support.v7.app.z */
public class C0272z extends Dialog implements C0251l {

    /* renamed from: a */
    private C0252m f844a;

    /* renamed from: b */
    private final C0112g.C0113a f845b = new C0271y(this);

    public C0272z(Context context, int i) {
        super(context, m1073a(context, i));
        mo1088a().mo1024a((Bundle) null);
        mo1088a().mo1028a();
    }

    /* renamed from: a */
    private static int m1073a(Context context, int i) {
        if (i != 0) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(C0136a.dialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    /* renamed from: a */
    public C0164b mo1020a(C0164b.C0165a aVar) {
        return null;
    }

    /* renamed from: a */
    public C0252m mo1088a() {
        if (this.f844a == null) {
            this.f844a = C0252m.m967a((Dialog) this, (C0251l) this);
        }
        return this.f844a;
    }

    /* renamed from: a */
    public void mo1021a(C0164b bVar) {
    }

    /* renamed from: a */
    public boolean mo1089a(int i) {
        return mo1088a().mo1030b(i);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo1090a(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        mo1088a().mo1026a(view, layoutParams);
    }

    /* renamed from: b */
    public void mo1022b(C0164b bVar) {
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return C0112g.m397a(this.f845b, getWindow().getDecorView(), this, keyEvent);
    }

    public <T extends View> T findViewById(int i) {
        return mo1088a().mo1023a(i);
    }

    public void invalidateOptionsMenu() {
        mo1088a().mo1033d();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        mo1088a().mo1031c();
        super.onCreate(bundle);
        mo1088a().mo1024a(bundle);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        mo1088a().mo1034e();
    }

    public void setContentView(int i) {
        mo1088a().mo1032c(i);
    }

    public void setContentView(View view) {
        mo1088a().mo1025a(view);
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        mo1088a().mo1029b(view, layoutParams);
    }

    public void setTitle(int i) {
        super.setTitle(i);
        mo1088a().mo1027a((CharSequence) getContext().getString(i));
    }

    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        mo1088a().mo1027a(charSequence);
    }
}
