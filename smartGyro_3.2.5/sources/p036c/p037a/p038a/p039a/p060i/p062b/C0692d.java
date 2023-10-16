package p036c.p037a.p038a.p039a.p060i.p062b;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import p036c.p037a.p038a.p039a.C0867o;
import p036c.p037a.p038a.p039a.p040a.C0497c;
import p036c.p037a.p038a.p039a.p041b.C0515a;
import p036c.p037a.p038a.p039a.p050e.C0637r;
import p036c.p037a.p038a.p039a.p050e.C0638s;
import p036c.p037a.p038a.p039a.p059h.C0668b;
import p036c.p037a.p038a.p039a.p060i.p063c.C0737k;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.i.b.d */
public class C0692d implements C0515a {

    /* renamed from: a */
    public C0668b f2066a;

    /* renamed from: b */
    private final Map<C0867o, byte[]> f2067b;

    /* renamed from: c */
    private final C0637r f2068c;

    public C0692d() {
        this((C0637r) null);
    }

    public C0692d(C0637r rVar) {
        this.f2066a = new C0668b(C0692d.class);
        this.f2067b = new ConcurrentHashMap();
        this.f2068c = rVar == null ? C0737k.f2213a : rVar;
    }

    /* renamed from: a */
    public void mo2506a(C0867o oVar) {
        C0870a.m3042a(oVar, "HTTP host");
        this.f2067b.remove(mo2889c(oVar));
    }

    /* renamed from: a */
    public void mo2507a(C0867o oVar, C0497c cVar) {
        C0870a.m3042a(oVar, "HTTP host");
        if (cVar != null) {
            if (cVar instanceof Serializable) {
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                    objectOutputStream.writeObject(cVar);
                    objectOutputStream.close();
                    this.f2067b.put(mo2889c(oVar), byteArrayOutputStream.toByteArray());
                } catch (IOException e) {
                    if (this.f2066a.mo2812d()) {
                        this.f2066a.mo2807b("Unexpected I/O error while serializing auth scheme", e);
                    }
                }
            } else if (this.f2066a.mo2805a()) {
                C0668b bVar = this.f2066a;
                bVar.mo2803a("Auth scheme " + cVar.getClass() + " is not serializable");
            }
        }
    }

    /* renamed from: b */
    public C0497c mo2508b(C0867o oVar) {
        C0870a.m3042a(oVar, "HTTP host");
        byte[] bArr = this.f2067b.get(mo2889c(oVar));
        if (bArr != null) {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bArr));
                C0497c cVar = (C0497c) objectInputStream.readObject();
                objectInputStream.close();
                return cVar;
            } catch (IOException e) {
                if (this.f2066a.mo2812d()) {
                    this.f2066a.mo2807b("Unexpected I/O error while de-serializing auth scheme", e);
                }
            } catch (ClassNotFoundException e2) {
                if (this.f2066a.mo2812d()) {
                    this.f2066a.mo2807b("Unexpected error while de-serializing auth scheme", e2);
                }
                return null;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public C0867o mo2889c(C0867o oVar) {
        if (oVar.mo3272c() <= 0) {
            try {
                return new C0867o(oVar.mo3271b(), this.f2068c.mo2732a(oVar), oVar.mo3274d());
            } catch (C0638s unused) {
            }
        }
        return oVar;
    }

    public String toString() {
        return this.f2067b.toString();
    }
}
