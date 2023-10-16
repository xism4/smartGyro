package p036c.p037a.p038a.p039a.p069k;

import java.util.NoSuchElementException;
import p036c.p037a.p038a.p039a.C0572d;
import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0639f;
import p036c.p037a.p038a.p039a.C0656g;
import p036c.p037a.p038a.p039a.C0664h;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0873d;

/* renamed from: c.a.a.a.k.d */
public class C0816d implements C0656g {

    /* renamed from: a */
    private final C0664h f2351a;

    /* renamed from: b */
    private final C0832t f2352b;

    /* renamed from: c */
    private C0639f f2353c;

    /* renamed from: d */
    private C0873d f2354d;

    /* renamed from: e */
    private C0835w f2355e;

    public C0816d(C0664h hVar) {
        this(hVar, C0819g.f2362b);
    }

    public C0816d(C0664h hVar, C0832t tVar) {
        this.f2353c = null;
        this.f2354d = null;
        this.f2355e = null;
        C0870a.m3042a(hVar, "Header iterator");
        this.f2351a = hVar;
        C0870a.m3042a(tVar, "Parser");
        this.f2352b = tVar;
    }

    /* renamed from: a */
    private void m2897a() {
        this.f2355e = null;
        this.f2354d = null;
        while (this.f2351a.hasNext()) {
            C0576e nextHeader = this.f2351a.nextHeader();
            if (nextHeader instanceof C0572d) {
                C0572d dVar = (C0572d) nextHeader;
                this.f2354d = dVar.getBuffer();
                this.f2355e = new C0835w(0, this.f2354d.length());
                this.f2355e.mo3218a(dVar.getValuePos());
                return;
            }
            String value = nextHeader.getValue();
            if (value != null) {
                this.f2354d = new C0873d(value.length());
                this.f2354d.mo3298a(value);
                this.f2355e = new C0835w(0, this.f2354d.length());
                return;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0027  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m2898b() {
        /*
            r3 = this;
        L_0x0000:
            c.a.a.a.h r0 = r3.f2351a
            boolean r0 = r0.hasNext()
            if (r0 != 0) goto L_0x000e
            c.a.a.a.k.w r0 = r3.f2355e
            if (r0 == 0) goto L_0x000d
            goto L_0x000e
        L_0x000d:
            return
        L_0x000e:
            c.a.a.a.k.w r0 = r3.f2355e
            if (r0 == 0) goto L_0x0018
            boolean r0 = r0.mo3219a()
            if (r0 == 0) goto L_0x001b
        L_0x0018:
            r3.m2897a()
        L_0x001b:
            c.a.a.a.k.w r0 = r3.f2355e
            if (r0 == 0) goto L_0x0000
        L_0x001f:
            c.a.a.a.k.w r0 = r3.f2355e
            boolean r0 = r0.mo3219a()
            if (r0 != 0) goto L_0x0044
            c.a.a.a.k.t r0 = r3.f2352b
            c.a.a.a.p.d r1 = r3.f2354d
            c.a.a.a.k.w r2 = r3.f2355e
            c.a.a.a.f r0 = r0.mo3153b(r1, r2)
            java.lang.String r1 = r0.getName()
            int r1 = r1.length()
            if (r1 != 0) goto L_0x0041
            java.lang.String r1 = r0.getValue()
            if (r1 == 0) goto L_0x001f
        L_0x0041:
            r3.f2353c = r0
            return
        L_0x0044:
            c.a.a.a.k.w r0 = r3.f2355e
            boolean r0 = r0.mo3219a()
            if (r0 == 0) goto L_0x0000
            r0 = 0
            r3.f2355e = r0
            r3.f2354d = r0
            goto L_0x0000
        */
        throw new UnsupportedOperationException("Method not decompiled: p036c.p037a.p038a.p039a.p069k.C0816d.m2898b():void");
    }

    public boolean hasNext() {
        if (this.f2353c == null) {
            m2898b();
        }
        return this.f2353c != null;
    }

    public final Object next() {
        return nextElement();
    }

    public C0639f nextElement() {
        if (this.f2353c == null) {
            m2898b();
        }
        C0639f fVar = this.f2353c;
        if (fVar != null) {
            this.f2353c = null;
            return fVar;
        }
        throw new NoSuchElementException("No more header elements available");
    }

    public void remove() {
        throw new UnsupportedOperationException("Remove not supported");
    }
}
