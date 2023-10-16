package p036c.p037a.p038a.p039a.p040a;

import java.util.Queue;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.a.i */
public class C0503i {

    /* renamed from: a */
    private C0496b f1764a = C0496b.UNCHALLENGED;

    /* renamed from: b */
    private C0497c f1765b;

    /* renamed from: c */
    private C0502h f1766c;

    /* renamed from: d */
    private C0508n f1767d;

    /* renamed from: e */
    private Queue<C0495a> f1768e;

    /* renamed from: a */
    public Queue<C0495a> mo2477a() {
        return this.f1768e;
    }

    /* renamed from: a */
    public void mo2478a(C0496b bVar) {
        if (bVar == null) {
            bVar = C0496b.UNCHALLENGED;
        }
        this.f1764a = bVar;
    }

    @Deprecated
    /* renamed from: a */
    public void mo2479a(C0497c cVar) {
        if (cVar == null) {
            mo2486e();
        } else {
            this.f1765b = cVar;
        }
    }

    /* renamed from: a */
    public void mo2480a(C0497c cVar, C0508n nVar) {
        C0870a.m3042a(cVar, "Auth scheme");
        C0870a.m3042a(nVar, "Credentials");
        this.f1765b = cVar;
        this.f1767d = nVar;
        this.f1768e = null;
    }

    @Deprecated
    /* renamed from: a */
    public void mo2481a(C0508n nVar) {
        this.f1767d = nVar;
    }

    /* renamed from: a */
    public void mo2482a(Queue<C0495a> queue) {
        C0870a.m3043a(queue, "Queue of auth options");
        this.f1768e = queue;
        this.f1765b = null;
        this.f1767d = null;
    }

    /* renamed from: b */
    public C0497c mo2483b() {
        return this.f1765b;
    }

    /* renamed from: c */
    public C0508n mo2484c() {
        return this.f1767d;
    }

    /* renamed from: d */
    public C0496b mo2485d() {
        return this.f1764a;
    }

    /* renamed from: e */
    public void mo2486e() {
        this.f1764a = C0496b.UNCHALLENGED;
        this.f1768e = null;
        this.f1765b = null;
        this.f1766c = null;
        this.f1767d = null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("state:");
        sb.append(this.f1764a);
        sb.append(";");
        if (this.f1765b != null) {
            sb.append("auth scheme:");
            sb.append(this.f1765b.getSchemeName());
            sb.append(";");
        }
        if (this.f1767d != null) {
            sb.append("credentials present");
        }
        return sb.toString();
    }
}
