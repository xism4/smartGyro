package p036c.p037a.p038a.p039a.p070l;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Deprecated
/* renamed from: c.a.a.a.l.b */
public class C0839b extends C0838a implements Serializable, Cloneable {

    /* renamed from: a */
    private final Map<String, Object> f2406a = new ConcurrentHashMap();

    /* renamed from: a */
    public void mo3237a(C0844g gVar) {
        for (Map.Entry next : this.f2406a.entrySet()) {
            gVar.setParameter((String) next.getKey(), next.getValue());
        }
    }

    public Object clone() {
        C0839b bVar = (C0839b) super.clone();
        mo3237a(bVar);
        return bVar;
    }

    public Object getParameter(String str) {
        return this.f2406a.get(str);
    }

    public C0844g setParameter(String str, Object obj) {
        if (str == null) {
            return this;
        }
        if (obj != null) {
            this.f2406a.put(str, obj);
        } else {
            this.f2406a.remove(str);
        }
        return this;
    }
}
