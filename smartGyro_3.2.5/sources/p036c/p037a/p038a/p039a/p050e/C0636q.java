package p036c.p037a.p038a.p039a.p050e;

import java.net.Socket;
import p036c.p037a.p038a.p039a.C0669i;
import p036c.p037a.p038a.p039a.C0867o;
import p036c.p037a.p038a.p039a.C0869p;
import p036c.p037a.p038a.p039a.p070l.C0844g;

@Deprecated
/* renamed from: c.a.a.a.e.q */
public interface C0636q extends C0669i, C0869p {
    /* renamed from: a */
    void mo2727a(Socket socket, C0867o oVar);

    /* renamed from: a */
    void mo2728a(Socket socket, C0867o oVar, boolean z, C0844g gVar);

    /* renamed from: b */
    void mo2729b(boolean z, C0844g gVar);

    Socket getSocket();

    boolean isSecure();
}
