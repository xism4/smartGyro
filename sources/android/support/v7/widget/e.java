package android.support.v7.widget;

import android.graphics.Typeface;
import com.org.android.ui.asm.k;
import java.lang.ref.WeakReference;

class e extends k {
    final /* synthetic */ TimePicker a;
    final /* synthetic */ WeakReference l;

    e(TimePicker timePicker, WeakReference weakReference) {
        this.a = timePicker;
        this.l = weakReference;
    }

    public void a(Typeface typeface) {
        this.a.applyStyle(this.l, typeface);
    }

    public void setTitle(int i) {
    }
}
