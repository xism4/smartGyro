package p036c.p037a.p038a.p039a.p060i.p065d;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import p036c.p037a.p038a.p039a.p057f.C0641b;
import p036c.p037a.p038a.p039a.p057f.C0643d;
import p036c.p037a.p038a.p039a.p057f.C0648i;

/* renamed from: c.a.a.a.i.d.b */
public abstract class C0757b implements C0648i {

    /* renamed from: a */
    private final Map<String, C0643d> f2237a;

    public C0757b() {
        this.f2237a = new ConcurrentHashMap(10);
    }

    protected C0757b(C0641b... bVarArr) {
        this.f2237a = new ConcurrentHashMap(bVarArr.length);
        for (C0641b bVar : bVarArr) {
            this.f2237a.put(bVar.mo2741a(), bVar);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C0643d mo3039a(String str) {
        return this.f2237a.get(str);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Collection<C0643d> mo3040a() {
        return this.f2237a.values();
    }
}
