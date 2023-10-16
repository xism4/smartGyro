package android.support.p025v7.widget;

import android.support.p025v7.widget.C0328C;
import android.view.View;
import android.widget.AdapterView;

/* renamed from: android.support.v7.widget.D */
class C0333D implements AdapterView.OnItemClickListener {

    /* renamed from: a */
    final /* synthetic */ C0328C f1200a;

    /* renamed from: b */
    final /* synthetic */ C0328C.C0330b f1201b;

    C0333D(C0328C.C0330b bVar, C0328C c) {
        this.f1201b = bVar;
        this.f1200a = c;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        C0328C.this.setSelection(i);
        if (C0328C.this.getOnItemClickListener() != null) {
            C0328C.C0330b bVar = this.f1201b;
            C0328C.this.performItemClick(view, i, bVar.f1188L.getItemId(i));
        }
        this.f1201b.dismiss();
    }
}
