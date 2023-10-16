package p036c.p037a.p038a.p039a.p041b.p046e;

import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0664h;
import p036c.p037a.p038a.p039a.C0883t;
import p036c.p037a.p038a.p039a.C0885v;
import p036c.p037a.p038a.p039a.p041b.C0559h;
import p036c.p037a.p038a.p039a.p057f.C0642c;
import p036c.p037a.p038a.p039a.p057f.C0645f;
import p036c.p037a.p038a.p039a.p057f.C0648i;
import p036c.p037a.p038a.p039a.p057f.C0653n;
import p036c.p037a.p038a.p039a.p059h.C0668b;
import p036c.p037a.p038a.p039a.p072n.C0855e;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.b.e.j */
public class C0549j implements C0885v {

    /* renamed from: a */
    public C0668b f1837a = new C0668b(C0549j.class);

    /* renamed from: a */
    private static String m2223a(C0642c cVar) {
        StringBuilder sb = new StringBuilder();
        sb.append(cVar.getName());
        sb.append("=\"");
        String value = cVar.getValue();
        if (value != null) {
            if (value.length() > 100) {
                value = value.substring(0, 100) + "...";
            }
            sb.append(value);
        }
        sb.append("\"");
        sb.append(", version:");
        sb.append(Integer.toString(cVar.getVersion()));
        sb.append(", domain:");
        sb.append(cVar.getDomain());
        sb.append(", path:");
        sb.append(cVar.getPath());
        sb.append(", expiry:");
        sb.append(cVar.getExpiryDate());
        return sb.toString();
    }

    /* renamed from: a */
    private void m2224a(C0664h hVar, C0648i iVar, C0645f fVar, C0559h hVar2) {
        while (hVar.hasNext()) {
            C0576e nextHeader = hVar.nextHeader();
            try {
                for (C0642c next : iVar.mo2763a(nextHeader, fVar)) {
                    try {
                        iVar.mo2764a(next, fVar);
                        hVar2.mo2595a(next);
                        if (this.f1837a.mo2805a()) {
                            C0668b bVar = this.f1837a;
                            bVar.mo2803a("Cookie accepted [" + m2223a(next) + "]");
                        }
                    } catch (C0653n e) {
                        if (this.f1837a.mo2812d()) {
                            C0668b bVar2 = this.f1837a;
                            bVar2.mo2811d("Cookie rejected [" + m2223a(next) + "] " + e.getMessage());
                        }
                    }
                }
            } catch (C0653n e2) {
                if (this.f1837a.mo2812d()) {
                    C0668b bVar3 = this.f1837a;
                    bVar3.mo2811d("Invalid cookie header: \"" + nextHeader + "\". " + e2.getMessage());
                }
            }
        }
    }

    /* renamed from: a */
    public void mo2353a(C0883t tVar, C0855e eVar) {
        C0668b bVar;
        String str;
        C0870a.m3042a(tVar, "HTTP request");
        C0870a.m3042a(eVar, "HTTP context");
        C0540a a = C0540a.m2199a(eVar);
        C0648i h = a.mo2568h();
        if (h == null) {
            bVar = this.f1837a;
            str = "Cookie spec not specified in HTTP context";
        } else {
            C0559h j = a.mo2570j();
            if (j == null) {
                bVar = this.f1837a;
                str = "Cookie store not specified in HTTP context";
            } else {
                C0645f g = a.mo2567g();
                if (g == null) {
                    bVar = this.f1837a;
                    str = "Cookie origin not specified in HTTP context";
                } else {
                    m2224a(tVar.headerIterator("Set-Cookie"), h, g, j);
                    if (h.getVersion() > 0) {
                        m2224a(tVar.headerIterator("Set-Cookie2"), h, g, j);
                        return;
                    }
                    return;
                }
            }
        }
        bVar.mo2803a(str);
    }
}
