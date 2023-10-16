package p036c.p037a.p038a.p039a.p057f;

import java.io.Serializable;
import java.util.Comparator;

/* renamed from: c.a.a.a.f.e */
public class C0644e implements Serializable, Comparator<C0642c> {
    /* renamed from: a */
    public int compare(C0642c cVar, C0642c cVar2) {
        int compareTo = cVar.getName().compareTo(cVar2.getName());
        if (compareTo == 0) {
            String domain = cVar.getDomain();
            String str = "";
            if (domain == null) {
                domain = str;
            } else if (domain.indexOf(46) == -1) {
                domain = domain + ".local";
            }
            String domain2 = cVar2.getDomain();
            if (domain2 != null) {
                if (domain2.indexOf(46) == -1) {
                    str = domain2 + ".local";
                } else {
                    str = domain2;
                }
            }
            compareTo = domain.compareToIgnoreCase(str);
        }
        if (compareTo != 0) {
            return compareTo;
        }
        String path = cVar.getPath();
        if (path == null) {
            path = "/";
        }
        String path2 = cVar2.getPath();
        if (path2 == null) {
            path2 = "/";
        }
        return path.compareTo(path2);
    }
}
