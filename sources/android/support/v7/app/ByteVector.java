package android.support.v7.app;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertController;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ListAdapter;

public class ByteVector {
    private final int a;
    private final AlertController.a g;

    public ByteVector(Context context) {
        this(context, e.a(context, 0));
    }

    public ByteVector(Context context, int i) {
        this.g = new AlertController.a(new ContextThemeWrapper(context, e.a(context, i)));
        this.a = i;
    }

    public ByteVector a(Drawable drawable) {
        this.g.mIcon = drawable;
        return this;
    }

    public ByteVector a(ListAdapter listAdapter, DialogInterface.OnClickListener onClickListener) {
        AlertController.a $r3 = this.g;
        $r3.mAdapter = listAdapter;
        $r3.mOnClickListener = onClickListener;
        return this;
    }

    public ByteVector a(CharSequence charSequence) {
        this.g.mMessage = charSequence;
        return this;
    }

    public ByteVector a(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        AlertController.a $r3 = this.g;
        $r3.mPositiveButtonText = charSequence;
        $r3.mPositiveButtonListener = onClickListener;
        return this;
    }

    public e a() {
        e $r1 = new e(this.g.this$0, this.a);
        this.g.apply($r1.mAlert);
        $r1.setCancelable(this.g.c);
        if (this.g.c) {
            $r1.setCanceledOnTouchOutside(true);
        }
        $r1.setOnCancelListener(this.g.p);
        $r1.setOnDismissListener(this.g.q);
        DialogInterface.OnKeyListener $r7 = this.g.r;
        if ($r7 != null) {
            $r1.setOnKeyListener($r7);
        }
        return $r1;
    }

    public ByteVector b(DialogInterface.OnKeyListener onKeyListener) {
        this.g.r = onKeyListener;
        return this;
    }

    public ByteVector b(View view) {
        this.g.g = view;
        return this;
    }

    public ByteVector b(CharSequence charSequence) {
        this.g.d = charSequence;
        return this;
    }

    public ByteVector b(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        AlertController.a $r3 = this.g;
        $r3.mNegativeButtonText = charSequence;
        $r3.mNegativeButtonListener = onClickListener;
        return this;
    }

    public ByteVector b(boolean z) {
        this.g.c = z;
        return this;
    }

    public e b() {
        e $r1 = a();
        $r1.show();
        return $r1;
    }

    public Context get() {
        return this.g.this$0;
    }
}
