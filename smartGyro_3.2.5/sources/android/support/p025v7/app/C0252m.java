package android.support.p025v7.app;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: android.support.v7.app.m */
public abstract class C0252m {

    /* renamed from: a */
    private static int f757a = -1;

    C0252m() {
    }

    /* renamed from: a */
    public static C0252m m967a(Dialog dialog, C0251l lVar) {
        return new C0261v(dialog.getContext(), dialog.getWindow(), lVar);
    }

    /* renamed from: b */
    public static int m968b() {
        return f757a;
    }

    /* renamed from: a */
    public abstract <T extends View> T mo1023a(int i);

    /* renamed from: a */
    public abstract void mo1024a(Bundle bundle);

    /* renamed from: a */
    public abstract void mo1025a(View view);

    /* renamed from: a */
    public abstract void mo1026a(View view, ViewGroup.LayoutParams layoutParams);

    /* renamed from: a */
    public abstract void mo1027a(CharSequence charSequence);

    /* renamed from: a */
    public abstract boolean mo1028a();

    /* renamed from: b */
    public abstract void mo1029b(View view, ViewGroup.LayoutParams layoutParams);

    /* renamed from: b */
    public abstract boolean mo1030b(int i);

    /* renamed from: c */
    public abstract void mo1031c();

    /* renamed from: c */
    public abstract void mo1032c(int i);

    /* renamed from: d */
    public abstract void mo1033d();

    /* renamed from: e */
    public abstract void mo1034e();
}
