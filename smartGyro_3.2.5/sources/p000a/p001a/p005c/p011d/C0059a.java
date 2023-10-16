package p000a.p001a.p005c.p011d;

import android.util.Base64;
import java.util.List;
import p000a.p001a.p005c.p013f.C0089h;

/* renamed from: a.a.c.d.a */
public final class C0059a {

    /* renamed from: a */
    private final String f140a;

    /* renamed from: b */
    private final String f141b;

    /* renamed from: c */
    private final String f142c;

    /* renamed from: d */
    private final List<List<byte[]>> f143d;

    /* renamed from: e */
    private final int f144e = 0;

    /* renamed from: f */
    private final String f145f = (this.f140a + "-" + this.f141b + "-" + this.f142c);

    public C0059a(String str, String str2, String str3, List<List<byte[]>> list) {
        C0089h.m313a(str);
        this.f140a = str;
        C0089h.m313a(str2);
        this.f141b = str2;
        C0089h.m313a(str3);
        this.f142c = str3;
        C0089h.m313a(list);
        this.f143d = list;
    }

    /* renamed from: a */
    public List<List<byte[]>> mo217a() {
        return this.f143d;
    }

    /* renamed from: b */
    public int mo218b() {
        return this.f144e;
    }

    /* renamed from: c */
    public String mo219c() {
        return this.f145f;
    }

    /* renamed from: d */
    public String mo220d() {
        return this.f140a;
    }

    /* renamed from: e */
    public String mo221e() {
        return this.f141b;
    }

    /* renamed from: f */
    public String mo222f() {
        return this.f142c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FontRequest {mProviderAuthority: " + this.f140a + ", mProviderPackage: " + this.f141b + ", mQuery: " + this.f142c + ", mCertificates:");
        for (int i = 0; i < this.f143d.size(); i++) {
            sb.append(" [");
            List list = this.f143d.get(i);
            for (int i2 = 0; i2 < list.size(); i2++) {
                sb.append(" \"");
                sb.append(Base64.encodeToString((byte[]) list.get(i2), 0));
                sb.append("\"");
            }
            sb.append(" ]");
        }
        sb.append("}");
        sb.append("mCertificatesArray: " + this.f144e);
        return sb.toString();
    }
}
