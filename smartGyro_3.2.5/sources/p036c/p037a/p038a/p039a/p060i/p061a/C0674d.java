package p036c.p037a.p038a.p039a.p060i.p061a;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashSet;
import java.util.Locale;
import java.util.StringTokenizer;
import p036c.p037a.p038a.p039a.C0570c;
import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0837l;
import p036c.p037a.p038a.p039a.C0848m;
import p036c.p037a.p038a.p039a.C0881r;
import p036c.p037a.p038a.p039a.C0889z;
import p036c.p037a.p038a.p039a.p040a.C0504j;
import p036c.p037a.p038a.p039a.p040a.C0508n;
import p036c.p037a.p038a.p039a.p040a.C0510p;
import p036c.p037a.p038a.p039a.p069k.C0818f;
import p036c.p037a.p038a.p039a.p069k.C0825m;
import p036c.p037a.p038a.p039a.p069k.C0829q;
import p036c.p037a.p038a.p039a.p072n.C0851a;
import p036c.p037a.p038a.p039a.p072n.C0855e;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0873d;
import p036c.p037a.p038a.p039a.p074p.C0875f;

/* renamed from: c.a.a.a.i.a.d */
public class C0674d extends C0686m {

    /* renamed from: d */
    private static final char[] f2005d = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* renamed from: e */
    private boolean f2006e;

    /* renamed from: f */
    private String f2007f;

    /* renamed from: g */
    private long f2008g;

    /* renamed from: h */
    private String f2009h;

    /* renamed from: i */
    private String f2010i;

    /* renamed from: j */
    private String f2011j;

    public C0674d() {
        this(C0570c.f1866b);
    }

    public C0674d(Charset charset) {
        super(charset);
        this.f2006e = false;
    }

    /* renamed from: a */
    static String m2466a(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[(length * 2)];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            char[] cArr2 = f2005d;
            cArr[i2] = cArr2[(bArr[i] & 240) >> 4];
            cArr[i2 + 1] = cArr2[bArr[i] & 15];
        }
        return new String(cArr);
    }

    /* renamed from: b */
    private C0576e m2467b(C0508n nVar, C0881r rVar) {
        String str;
        char c;
        String str2;
        String str3;
        char c2;
        String str4;
        StringBuilder sb;
        C0881r rVar2 = rVar;
        String a = mo2840a("uri");
        String a2 = mo2840a("realm");
        String a3 = mo2840a("nonce");
        String a4 = mo2840a("opaque");
        String a5 = mo2840a("methodname");
        String a6 = mo2840a("algorithm");
        if (a6 == null) {
            a6 = "MD5";
        }
        HashSet hashSet = new HashSet(8);
        String str5 = "MD5";
        String a7 = mo2840a("qop");
        String str6 = "opaque";
        String str7 = a4;
        String str8 = "algorithm";
        if (a7 != null) {
            str = "qop";
            for (StringTokenizer stringTokenizer = new StringTokenizer(a7, ","); stringTokenizer.hasMoreTokens(); stringTokenizer = stringTokenizer) {
                hashSet.add(stringTokenizer.nextToken().trim().toLowerCase(Locale.ROOT));
            }
            c = (!(rVar2 instanceof C0848m) || !hashSet.contains("auth-int")) ? hashSet.contains("auth") ? (char) 2 : 65535 : 1;
        } else {
            str = "qop";
            c = 0;
        }
        if (c != 65535) {
            String a8 = mo2840a("charset");
            if (a8 == null) {
                a8 = "ISO-8859-1";
            }
            String str9 = a6.equalsIgnoreCase("MD5-sess") ? str5 : a6;
            try {
                MessageDigest b = m2468b(str9);
                String str10 = "auth-int";
                String name = nVar.getUserPrincipal().getName();
                String str11 = "uri";
                String password = nVar.getPassword();
                String str12 = "nonce";
                String str13 = "realm";
                if (a3.equals(this.f2007f)) {
                    str2 = a2;
                    this.f2008g++;
                } else {
                    str2 = a2;
                    this.f2008g = 1;
                    this.f2009h = null;
                    this.f2007f = a3;
                }
                StringBuilder sb2 = new StringBuilder(256);
                String str14 = "auth";
                Formatter formatter = new Formatter(sb2, Locale.US);
                HashSet hashSet2 = hashSet;
                String str15 = a5;
                formatter.format("%08x", new Object[]{Long.valueOf(this.f2008g)});
                formatter.close();
                String sb3 = sb2.toString();
                if (this.f2009h == null) {
                    this.f2009h = m2469d();
                }
                this.f2010i = null;
                this.f2011j = null;
                if (a6.equalsIgnoreCase("MD5-sess")) {
                    sb2.setLength(0);
                    sb2.append(name);
                    sb2.append(':');
                    sb2.append(str2);
                    sb2.append(':');
                    sb2.append(password);
                    String a9 = m2466a(b.digest(C0875f.m3079a(sb2.toString(), a8)));
                    sb2.setLength(0);
                    sb2.append(a9);
                    sb2.append(':');
                    sb2.append(a3);
                    sb2.append(':');
                    password = this.f2009h;
                } else {
                    sb2.setLength(0);
                    sb2.append(name);
                    sb2.append(':');
                    sb2.append(str2);
                    sb2.append(':');
                }
                sb2.append(password);
                this.f2010i = sb2.toString();
                String a10 = m2466a(b.digest(C0875f.m3079a(this.f2010i, a8)));
                if (c == 2) {
                    this.f2011j = str15 + ':' + a;
                    str3 = str14;
                } else {
                    String str16 = str15;
                    if (c == 1) {
                        C0837l entity = rVar2 instanceof C0848m ? ((C0848m) rVar2).getEntity() : null;
                        if (entity == null || entity.isRepeatable()) {
                            str3 = str14;
                            C0678h hVar = new C0678h(b);
                            if (entity != null) {
                                try {
                                    entity.writeTo(hVar);
                                } catch (IOException e) {
                                    throw new C0504j("I/O error reading entity content", e);
                                }
                            }
                            hVar.close();
                            sb = new StringBuilder();
                            sb.append(str16);
                            sb.append(':');
                            sb.append(a);
                            sb.append(':');
                            sb.append(m2466a(hVar.mo2835a()));
                        } else {
                            str3 = str14;
                            if (hashSet2.contains(str3)) {
                                this.f2011j = str16 + ':' + a;
                                c = 2;
                            } else {
                                throw new C0504j("Qop auth-int cannot be used with a non-repeatable entity");
                            }
                        }
                    } else {
                        str3 = str14;
                        sb = new StringBuilder();
                        sb.append(str16);
                        sb.append(':');
                        sb.append(a);
                    }
                    this.f2011j = sb.toString();
                }
                String a11 = m2466a(b.digest(C0875f.m3079a(this.f2011j, a8)));
                if (c == 0) {
                    sb2.setLength(0);
                    sb2.append(a10);
                    c2 = ':';
                    sb2.append(':');
                    sb2.append(a3);
                } else {
                    c2 = ':';
                    sb2.setLength(0);
                    sb2.append(a10);
                    sb2.append(':');
                    sb2.append(a3);
                    sb2.append(':');
                    sb2.append(sb3);
                    sb2.append(':');
                    sb2.append(this.f2009h);
                    sb2.append(':');
                    sb2.append(c == 1 ? str10 : str3);
                }
                sb2.append(c2);
                sb2.append(a11);
                String a12 = m2466a(b.digest(C0875f.m3078a(sb2.toString())));
                C0873d dVar = new C0873d(128);
                dVar.mo3298a(mo2831a() ? "Proxy-Authorization" : "Authorization");
                dVar.mo3298a(": Digest ");
                ArrayList arrayList = new ArrayList(20);
                arrayList.add(new C0825m("username", name));
                arrayList.add(new C0825m(str13, str2));
                arrayList.add(new C0825m(str12, a3));
                arrayList.add(new C0825m(str11, a));
                arrayList.add(new C0825m("response", a12));
                if (c != 0) {
                    if (c == 1) {
                        str3 = str10;
                    }
                    str4 = str;
                    arrayList.add(new C0825m(str4, str3));
                    arrayList.add(new C0825m("nc", sb3));
                    arrayList.add(new C0825m("cnonce", this.f2009h));
                } else {
                    str4 = str;
                }
                String str17 = str8;
                arrayList.add(new C0825m(str17, a6));
                if (str7 != null) {
                    arrayList.add(new C0825m(str6, str7));
                }
                for (int i = 0; i < arrayList.size(); i++) {
                    C0825m mVar = (C0825m) arrayList.get(i);
                    if (i > 0) {
                        dVar.mo3298a(", ");
                    }
                    String name2 = mVar.getName();
                    C0818f.f2360b.mo3145a(dVar, (C0889z) mVar, !("nc".equals(name2) || str4.equals(name2) || str17.equals(name2)));
                }
                return new C0829q(dVar);
            } catch (C0687n unused) {
                throw new C0504j("Unsuppported digest algorithm: " + str9);
            }
        } else {
            throw new C0504j("None of the qop methods is supported: " + a7);
        }
    }

    /* renamed from: b */
    private static MessageDigest m2468b(String str) {
        try {
            return MessageDigest.getInstance(str);
        } catch (Exception unused) {
            throw new C0687n("Unsupported algorithm in HTTP Digest authentication: " + str);
        }
    }

    /* renamed from: d */
    public static String m2469d() {
        byte[] bArr = new byte[8];
        new SecureRandom().nextBytes(bArr);
        return m2466a(bArr);
    }

    @Deprecated
    /* renamed from: a */
    public C0576e mo2462a(C0508n nVar, C0881r rVar) {
        return mo2492a(nVar, rVar, new C0851a());
    }

    /* renamed from: a */
    public C0576e mo2492a(C0508n nVar, C0881r rVar, C0855e eVar) {
        C0870a.m3042a(nVar, "Credentials");
        C0870a.m3042a(rVar, "HTTP request");
        if (mo2840a("realm") == null) {
            throw new C0504j("missing realm in challenge");
        } else if (mo2840a("nonce") != null) {
            mo2842c().put("methodname", rVar.getRequestLine().getMethod());
            mo2842c().put("uri", rVar.getRequestLine().getUri());
            if (mo2840a("charset") == null) {
                mo2842c().put("charset", mo2839a(rVar));
            }
            return m2467b(nVar, rVar);
        } else {
            throw new C0504j("missing nonce in challenge");
        }
    }

    /* renamed from: a */
    public void mo2463a(C0576e eVar) {
        super.mo2463a(eVar);
        this.f2006e = true;
        if (mo2842c().isEmpty()) {
            throw new C0510p("Authentication challenge is empty");
        }
    }

    public String getSchemeName() {
        return "digest";
    }

    public boolean isComplete() {
        if ("true".equalsIgnoreCase(mo2840a("stale"))) {
            return false;
        }
        return this.f2006e;
    }

    public boolean isConnectionBased() {
        return false;
    }

    public String toString() {
        return "DIGEST [complete=" + this.f2006e + ", nonce=" + this.f2007f + ", nc=" + this.f2008g + "]";
    }
}
