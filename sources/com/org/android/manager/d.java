package com.org.android.manager;

import a.a.c.d.f;
import a.a.c.d.k;
import android.os.Handler;

final class d implements k.a<f.c> {
    final /* synthetic */ com.org.android.ui.asm.k b;
    final /* synthetic */ Handler f;

    d(com.org.android.ui.asm.k kVar, Handler handler) {
        this.b = kVar;
        this.f = handler;
    }

    public void a(f fVar) {
        int $i0;
        com.org.android.ui.asm.k $r2;
        if (fVar == null) {
            $r2 = this.b;
            $i0 = 1;
        } else {
            $i0 = fVar.f;
            if ($i0 == 0) {
                this.b.a(fVar.t, this.f);
                return;
            }
            $r2 = this.b;
        }
        $r2.a($i0, this.f);
    }
}
