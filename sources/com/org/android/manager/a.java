package com.org.android.manager;

import a.a.c.d.f;
import android.content.Context;
import android.graphics.Typeface;
import java.util.concurrent.Callable;

final class a implements Callable<f.c> {
    final /* synthetic */ h e;
    final /* synthetic */ Context i;
    final /* synthetic */ int p;
    final /* synthetic */ String t;

    a(Context context, h hVar, int i2, String str) {
        this.i = context;
        this.e = hVar;
        this.p = i2;
        this.t = str;
    }

    public f call() {
        f $r2 = i.a(this.i, this.e, this.p);
        Typeface $r5 = $r2.t;
        if ($r5 != null) {
            i.t.put(this.t, $r5);
        }
        return $r2;
    }
}
