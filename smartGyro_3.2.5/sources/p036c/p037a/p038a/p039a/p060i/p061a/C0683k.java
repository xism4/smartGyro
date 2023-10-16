package p036c.p037a.p038a.p039a.p060i.p061a;

import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0881r;
import p036c.p037a.p038a.p039a.p040a.C0504j;
import p036c.p037a.p038a.p039a.p040a.C0508n;
import p036c.p037a.p038a.p039a.p040a.C0509o;
import p036c.p037a.p038a.p039a.p040a.C0510p;
import p036c.p037a.p038a.p039a.p040a.C0511q;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0873d;

/* renamed from: c.a.a.a.i.a.k */
public class C0683k extends C0671a {

    /* renamed from: b */
    private final C0679i f2027b;

    /* renamed from: c */
    private C0684a f2028c;

    /* renamed from: d */
    private String f2029d;

    /* renamed from: c.a.a.a.i.a.k$a */
    enum C0684a {
        UNINITIATED,
        CHALLENGE_RECEIVED,
        MSG_TYPE1_GENERATED,
        MSG_TYPE2_RECEVIED,
        MSG_TYPE3_GENERATED,
        FAILED
    }

    public C0683k() {
        this(new C0680j());
    }

    public C0683k(C0679i iVar) {
        C0870a.m3042a(iVar, "NTLM engine");
        this.f2027b = iVar;
        this.f2028c = C0684a.UNINITIATED;
        this.f2029d = null;
    }

    /* renamed from: a */
    public C0576e mo2462a(C0508n nVar, C0881r rVar) {
        try {
            C0511q qVar = (C0511q) nVar;
            C0684a aVar = this.f2028c;
            if (aVar == C0684a.FAILED) {
                throw new C0504j("NTLM authentication failed");
            } else if (aVar == C0684a.CHALLENGE_RECEIVED) {
                C0679i iVar = this.f2027b;
                qVar.mo2495a();
                throw null;
            } else if (aVar == C0684a.MSG_TYPE2_RECEVIED) {
                C0679i iVar2 = this.f2027b;
                qVar.mo2496b();
                throw null;
            } else {
                throw new C0504j("Unexpected state: " + this.f2028c);
            }
        } catch (ClassCastException unused) {
            throw new C0509o("Credentials cannot be used for NTLM authentication: " + nVar.getClass().getName());
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo2830a(C0873d dVar, int i, int i2) {
        C0684a aVar;
        this.f2029d = dVar.mo3304b(i, i2);
        if (this.f2029d.isEmpty()) {
            aVar = this.f2028c == C0684a.UNINITIATED ? C0684a.CHALLENGE_RECEIVED : C0684a.FAILED;
        } else if (this.f2028c.compareTo(C0684a.MSG_TYPE1_GENERATED) < 0) {
            this.f2028c = C0684a.FAILED;
            throw new C0510p("Out of sequence NTLM response message");
        } else if (this.f2028c == C0684a.MSG_TYPE1_GENERATED) {
            aVar = C0684a.MSG_TYPE2_RECEVIED;
        } else {
            return;
        }
        this.f2028c = aVar;
    }

    public String getRealm() {
        return null;
    }

    public String getSchemeName() {
        return "ntlm";
    }

    public boolean isComplete() {
        C0684a aVar = this.f2028c;
        return aVar == C0684a.MSG_TYPE3_GENERATED || aVar == C0684a.FAILED;
    }

    public boolean isConnectionBased() {
        return true;
    }
}
