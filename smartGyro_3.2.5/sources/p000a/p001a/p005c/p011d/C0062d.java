package p000a.p001a.p005c.p011d;

import p000a.p001a.p005c.p011d.C0064f;
import p000a.p001a.p005c.p011d.C0072k;

/* renamed from: a.a.c.d.d */
class C0062d implements C0072k.C0073a<C0064f.C0067c> {

    /* renamed from: a */
    final /* synthetic */ String f152a;

    C0062d(String str) {
        this.f152a = str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
        if (r0 >= r1.size()) goto L_0x002c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0020, code lost:
        ((p000a.p001a.p005c.p011d.C0072k.C0073a) r1.get(r0)).mo226a(r5);
        r0 = r0 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002c, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0019, code lost:
        r0 = 0;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo226a(p000a.p001a.p005c.p011d.C0064f.C0067c r5) {
        /*
            r4 = this;
            java.lang.Object r0 = p000a.p001a.p005c.p011d.C0064f.f155c
            monitor-enter(r0)
            a.a.c.f.i<java.lang.String, java.util.ArrayList<a.a.c.d.k$a<a.a.c.d.f$c>>> r1 = p000a.p001a.p005c.p011d.C0064f.f156d     // Catch:{ all -> 0x002d }
            java.lang.String r2 = r4.f152a     // Catch:{ all -> 0x002d }
            java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x002d }
            java.util.ArrayList r1 = (java.util.ArrayList) r1     // Catch:{ all -> 0x002d }
            if (r1 != 0) goto L_0x0011
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return
        L_0x0011:
            a.a.c.f.i<java.lang.String, java.util.ArrayList<a.a.c.d.k$a<a.a.c.d.f$c>>> r2 = p000a.p001a.p005c.p011d.C0064f.f156d     // Catch:{ all -> 0x002d }
            java.lang.String r3 = r4.f152a     // Catch:{ all -> 0x002d }
            r2.remove(r3)     // Catch:{ all -> 0x002d }
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            r0 = 0
        L_0x001a:
            int r2 = r1.size()
            if (r0 >= r2) goto L_0x002c
            java.lang.Object r2 = r1.get(r0)
            a.a.c.d.k$a r2 = (p000a.p001a.p005c.p011d.C0072k.C0073a) r2
            r2.mo226a(r5)
            int r0 = r0 + 1
            goto L_0x001a
        L_0x002c:
            return
        L_0x002d:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            goto L_0x0031
        L_0x0030:
            throw r5
        L_0x0031:
            goto L_0x0030
        */
        throw new UnsupportedOperationException("Method not decompiled: p000a.p001a.p005c.p011d.C0062d.mo226a(a.a.c.d.f$c):void");
    }
}
