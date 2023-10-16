package p036c.p037a.p038a.p039a.p060i.p065d;

import java.util.Collection;
import p036c.p037a.p038a.p039a.p057f.C0648i;
import p036c.p037a.p038a.p039a.p057f.C0649j;
import p036c.p037a.p038a.p039a.p057f.C0650k;
import p036c.p037a.p038a.p039a.p070l.C0844g;
import p036c.p037a.p038a.p039a.p072n.C0855e;

@Deprecated
/* renamed from: c.a.a.a.i.d.A */
public class C0747A implements C0649j, C0650k {

    /* renamed from: a */
    private final C0648i f2235a;

    public C0747A() {
        this((String[]) null, false);
    }

    public C0747A(String[] strArr, boolean z) {
        this.f2235a = new C0782z(strArr, z);
    }

    /* renamed from: a */
    public C0648i mo2769a(C0844g gVar) {
        if (gVar == null) {
            return new C0782z();
        }
        String[] strArr = null;
        Collection collection = (Collection) gVar.getParameter("http.protocol.cookie-datepatterns");
        if (collection != null) {
            strArr = (String[]) collection.toArray(new String[collection.size()]);
        }
        return new C0782z(strArr, gVar.getBooleanParameter("http.protocol.single-cookie-header", false));
    }

    /* renamed from: a */
    public C0648i mo2770a(C0855e eVar) {
        return this.f2235a;
    }
}
