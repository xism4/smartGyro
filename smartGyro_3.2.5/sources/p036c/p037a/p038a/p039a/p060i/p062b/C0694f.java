package p036c.p037a.p038a.p039a.p060i.p062b;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import p036c.p037a.p038a.p039a.p040a.C0502h;
import p036c.p037a.p038a.p039a.p040a.C0508n;
import p036c.p037a.p038a.p039a.p041b.C0560i;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.i.b.f */
public class C0694f implements C0560i {

    /* renamed from: a */
    private final ConcurrentHashMap<C0502h, C0508n> f2070a = new ConcurrentHashMap<>();

    /* renamed from: a */
    private static C0508n m2551a(Map<C0502h, C0508n> map, C0502h hVar) {
        C0508n nVar = map.get(hVar);
        if (nVar != null) {
            return nVar;
        }
        int i = -1;
        C0502h hVar2 = null;
        for (C0502h next : map.keySet()) {
            int a = hVar.mo2473a(next);
            if (a > i) {
                hVar2 = next;
                i = a;
            }
        }
        return hVar2 != null ? map.get(hVar2) : nVar;
    }

    /* renamed from: a */
    public C0508n mo2598a(C0502h hVar) {
        C0870a.m3042a(hVar, "Authentication scope");
        return m2551a(this.f2070a, hVar);
    }

    public String toString() {
        return this.f2070a.toString();
    }
}
