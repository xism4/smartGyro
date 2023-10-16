package android.support.v7.app;

import android.view.View;
import com.org.android.view.Label;
import com.org.android.view.OnApplyWindowInsetsListener;
import com.org.android.view.ViewCompat;

class x implements OnApplyWindowInsetsListener {
    final /* synthetic */ AppCompatDelegateImplV7 a;

    x(AppCompatDelegateImplV7 appCompatDelegateImplV7) {
        this.a = appCompatDelegateImplV7;
    }

    public Label a(View view, Label $r2) {
        int $i0 = $r2.getColor();
        int $i1 = this.a.access$300($i0);
        if ($i0 != $i1) {
            $r2 = $r2.a($r2.b(), $i1, $r2.c(), $r2.a());
        }
        return ViewCompat.a(view, $r2);
    }
}
