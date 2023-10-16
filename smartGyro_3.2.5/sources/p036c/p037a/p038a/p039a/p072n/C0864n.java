package p036c.p037a.p038a.p039a.p072n;

import java.net.InetAddress;
import p036c.p037a.p038a.p039a.C0487C;
import p036c.p037a.p038a.p039a.C0488D;
import p036c.p037a.p038a.p039a.C0804j;
import p036c.p037a.p038a.p039a.C0867o;
import p036c.p037a.p038a.p039a.C0869p;
import p036c.p037a.p038a.p039a.C0881r;
import p036c.p037a.p038a.p039a.C0882s;
import p036c.p037a.p038a.p039a.C0886w;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.n.n */
public class C0864n implements C0882s {
    /* renamed from: a */
    public void mo2352a(C0881r rVar, C0855e eVar) {
        C0870a.m3042a(rVar, "HTTP request");
        C0856f a = C0856f.m3013a(eVar);
        C0488D protocolVersion = rVar.getRequestLine().getProtocolVersion();
        if ((!rVar.getRequestLine().getMethod().equalsIgnoreCase("CONNECT") || !protocolVersion.mo2445c(C0886w.f2445e)) && !rVar.containsHeader("Host")) {
            C0867o c = a.mo3262c();
            if (c == null) {
                C0804j a2 = a.mo3259a();
                if (a2 instanceof C0869p) {
                    C0869p pVar = (C0869p) a2;
                    InetAddress remoteAddress = pVar.getRemoteAddress();
                    int remotePort = pVar.getRemotePort();
                    if (remoteAddress != null) {
                        c = new C0867o(remoteAddress.getHostName(), remotePort);
                    }
                }
                if (c == null) {
                    if (!protocolVersion.mo2445c(C0886w.f2445e)) {
                        throw new C0487C("Target host missing");
                    }
                    return;
                }
            }
            rVar.addHeader("Host", c.mo3275e());
        }
    }
}
