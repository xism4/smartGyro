package p036c.p037a.p038a.p039a.p057f;

import java.io.Serializable;
import java.util.Comparator;

/* renamed from: c.a.a.a.f.g */
public class C0646g implements Serializable, Comparator<C0642c> {

    /* renamed from: a */
    public static final C0646g f1948a = new C0646g();

    /* renamed from: a */
    private String m2404a(C0642c cVar) {
        String path = cVar.getPath();
        if (path == null) {
            path = "/";
        }
        if (path.endsWith("/")) {
            return path;
        }
        return path + '/';
    }

    /* renamed from: a */
    public int compare(C0642c cVar, C0642c cVar2) {
        String a = m2404a(cVar);
        String a2 = m2404a(cVar2);
        if (a.equals(a2)) {
            return 0;
        }
        if (a.startsWith(a2)) {
            return -1;
        }
        return a2.startsWith(a) ? 1 : 0;
    }
}
