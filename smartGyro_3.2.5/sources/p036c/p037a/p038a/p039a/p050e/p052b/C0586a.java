package p036c.p037a.p038a.p039a.p050e.p052b;

import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.e.b.a */
public class C0586a implements C0588c {
    /* access modifiers changed from: protected */
    /* renamed from: a */
    public int mo2629a(C0590e eVar) {
        return eVar.getHopCount() > 1 ? 2 : 1;
    }

    /* renamed from: a */
    public int mo2630a(C0590e eVar, C0590e eVar2) {
        C0870a.m3042a(eVar, "Planned route");
        return (eVar2 == null || eVar2.getHopCount() < 1) ? mo2629a(eVar) : eVar.getHopCount() > 1 ? mo2632c(eVar, eVar2) : mo2631b(eVar, eVar2);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public int mo2631b(C0590e eVar, C0590e eVar2) {
        if (eVar2.getHopCount() <= 1 && eVar.getTargetHost().equals(eVar2.getTargetHost()) && eVar.isSecure() == eVar2.isSecure()) {
            return (eVar.getLocalAddress() == null || eVar.getLocalAddress().equals(eVar2.getLocalAddress())) ? 0 : -1;
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public int mo2632c(C0590e eVar, C0590e eVar2) {
        int hopCount;
        int hopCount2;
        if (eVar2.getHopCount() <= 1 || !eVar.getTargetHost().equals(eVar2.getTargetHost()) || (hopCount = eVar.getHopCount()) < (hopCount2 = eVar2.getHopCount())) {
            return -1;
        }
        for (int i = 0; i < hopCount2 - 1; i++) {
            if (!eVar.getHopTarget(i).equals(eVar2.getHopTarget(i))) {
                return -1;
            }
        }
        if (hopCount > hopCount2) {
            return 4;
        }
        if ((eVar2.isTunnelled() && !eVar.isTunnelled()) || (eVar2.isLayered() && !eVar.isLayered())) {
            return -1;
        }
        if (eVar.isTunnelled() && !eVar2.isTunnelled()) {
            return 3;
        }
        if (!eVar.isLayered() || eVar2.isLayered()) {
            return eVar.isSecure() != eVar2.isSecure() ? -1 : 0;
        }
        return 5;
    }
}
