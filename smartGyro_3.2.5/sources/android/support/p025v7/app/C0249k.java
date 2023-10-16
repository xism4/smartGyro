package android.support.p025v7.app;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.p025v7.app.AlertController;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListAdapter;
import p000a.p001a.p017d.p018a.C0136a;

/* renamed from: android.support.v7.app.k */
public class C0249k extends C0272z implements DialogInterface {

    /* renamed from: c */
    final AlertController f754c = new AlertController(getContext(), this, getWindow());

    /* renamed from: android.support.v7.app.k$a */
    public static class C0250a {

        /* renamed from: a */
        private final AlertController.C0223a f755a;

        /* renamed from: b */
        private final int f756b;

        public C0250a(Context context) {
            this(context, C0249k.m951a(context, 0));
        }

        public C0250a(Context context, int i) {
            this.f755a = new AlertController.C0223a(new ContextThemeWrapper(context, C0249k.m951a(context, i)));
            this.f756b = i;
        }

        /* renamed from: a */
        public C0250a mo1008a(DialogInterface.OnKeyListener onKeyListener) {
            this.f755a.f657u = onKeyListener;
            return this;
        }

        /* renamed from: a */
        public C0250a mo1009a(Drawable drawable) {
            this.f755a.f640d = drawable;
            return this;
        }

        /* renamed from: a */
        public C0250a mo1010a(View view) {
            this.f755a.f643g = view;
            return this;
        }

        /* renamed from: a */
        public C0250a mo1011a(ListAdapter listAdapter, DialogInterface.OnClickListener onClickListener) {
            AlertController.C0223a aVar = this.f755a;
            aVar.f659w = listAdapter;
            aVar.f660x = onClickListener;
            return this;
        }

        /* renamed from: a */
        public C0250a mo1012a(CharSequence charSequence) {
            this.f755a.f644h = charSequence;
            return this;
        }

        /* renamed from: a */
        public C0250a mo1013a(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            AlertController.C0223a aVar = this.f755a;
            aVar.f648l = charSequence;
            aVar.f650n = onClickListener;
            return this;
        }

        /* renamed from: a */
        public C0250a mo1014a(boolean z) {
            this.f755a.f654r = z;
            return this;
        }

        /* renamed from: a */
        public C0249k mo1015a() {
            C0249k kVar = new C0249k(this.f755a.f637a, this.f756b);
            this.f755a.mo939a(kVar.f754c);
            kVar.setCancelable(this.f755a.f654r);
            if (this.f755a.f654r) {
                kVar.setCanceledOnTouchOutside(true);
            }
            kVar.setOnCancelListener(this.f755a.f655s);
            kVar.setOnDismissListener(this.f755a.f656t);
            DialogInterface.OnKeyListener onKeyListener = this.f755a.f657u;
            if (onKeyListener != null) {
                kVar.setOnKeyListener(onKeyListener);
            }
            return kVar;
        }

        /* renamed from: b */
        public Context mo1016b() {
            return this.f755a.f637a;
        }

        /* renamed from: b */
        public C0250a mo1017b(CharSequence charSequence) {
            this.f755a.f642f = charSequence;
            return this;
        }

        /* renamed from: b */
        public C0250a mo1018b(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            AlertController.C0223a aVar = this.f755a;
            aVar.f645i = charSequence;
            aVar.f647k = onClickListener;
            return this;
        }

        /* renamed from: c */
        public C0249k mo1019c() {
            C0249k a = mo1015a();
            a.show();
            return a;
        }
    }

    protected C0249k(Context context, int i) {
        super(context, m951a(context, i));
    }

    /* renamed from: a */
    static int m951a(Context context, int i) {
        if (((i >>> 24) & 255) >= 1) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(C0136a.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f754c.mo926a();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.f754c.mo931a(i, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (this.f754c.mo935b(i, keyEvent)) {
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }

    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.f754c.mo934b(charSequence);
    }
}
