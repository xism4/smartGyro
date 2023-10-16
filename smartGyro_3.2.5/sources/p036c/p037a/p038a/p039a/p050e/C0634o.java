package p036c.p037a.p038a.p039a.p050e;

import java.util.concurrent.TimeUnit;
import p036c.p037a.p038a.p039a.C0669i;
import p036c.p037a.p038a.p039a.p050e.p052b.C0587b;
import p036c.p037a.p038a.p039a.p070l.C0844g;
import p036c.p037a.p038a.p039a.p072n.C0855e;

@Deprecated
/* renamed from: c.a.a.a.e.o */
public interface C0634o extends C0669i, C0633n, C0635p, C0628i {
    /* renamed from: a */
    void mo2719a(C0587b bVar, C0855e eVar, C0844g gVar);

    /* renamed from: a */
    void mo2720a(C0855e eVar, C0844g gVar);

    /* renamed from: a */
    void mo2721a(boolean z, C0844g gVar);

    C0587b getRoute();

    void markReusable();

    void setIdleDuration(long j, TimeUnit timeUnit);

    void setState(Object obj);

    void unmarkReusable();
}
