package p036c.p037a.p038a.p039a.p060i.p062b;

import java.io.InputStream;
import java.io.OutputStream;
import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0837l;
import p036c.p037a.p038a.p039a.C0848m;
import p036c.p037a.p038a.p039a.p058g.C0662f;

@Deprecated
/* renamed from: c.a.a.a.i.b.r */
public class C0706r extends C0711v implements C0848m {

    /* renamed from: h */
    private C0837l f2113h;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public boolean f2114i;

    /* renamed from: c.a.a.a.i.b.r$a */
    class C0707a extends C0662f {
        C0707a(C0837l lVar) {
            super(lVar);
        }

        public void consumeContent() {
            boolean unused = C0706r.this.f2114i = true;
            super.consumeContent();
        }

        public InputStream getContent() {
            boolean unused = C0706r.this.f2114i = true;
            return super.getContent();
        }

        public void writeTo(OutputStream outputStream) {
            boolean unused = C0706r.this.f2114i = true;
            super.writeTo(outputStream);
        }
    }

    public C0706r(C0848m mVar) {
        super(mVar);
        mo2912a(mVar.getEntity());
    }

    /* renamed from: a */
    public void mo2912a(C0837l lVar) {
        this.f2113h = lVar != null ? new C0707a(lVar) : null;
        this.f2114i = false;
    }

    /* renamed from: e */
    public boolean mo2913e() {
        C0837l lVar = this.f2113h;
        return lVar == null || lVar.isRepeatable() || !this.f2114i;
    }

    public boolean expectContinue() {
        C0576e firstHeader = getFirstHeader("Expect");
        return firstHeader != null && "100-continue".equalsIgnoreCase(firstHeader.getValue());
    }

    public C0837l getEntity() {
        return this.f2113h;
    }
}
