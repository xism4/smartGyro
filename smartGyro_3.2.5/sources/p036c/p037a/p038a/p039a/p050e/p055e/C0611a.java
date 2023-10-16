package p036c.p037a.p038a.p039a.p050e.p055e;

import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import p036c.p037a.p038a.p039a.p050e.p056f.C0625a;
import p036c.p037a.p038a.p039a.p059h.C0668b;
import p036c.p037a.p038a.p039a.p074p.C0870a;

@Deprecated
/* renamed from: c.a.a.a.e.e.a */
public abstract class C0611a implements C0623l {

    /* renamed from: a */
    static final String[] f1909a = {"ac", "co", "com", "ed", "edu", "go", "gouv", "gov", "info", "lg", "ne", "net", "or", "org"};

    /* renamed from: b */
    public C0668b f1910b = new C0668b(getClass());

    static {
        Arrays.sort(f1909a);
    }

    /* renamed from: a */
    public static int m2345a(String str) {
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (str.charAt(i2) == '.') {
                i++;
            }
        }
        return i;
    }

    /* renamed from: a */
    private static boolean m2346a(String str, String str2, boolean z) {
        boolean z2;
        if (str == null) {
            return false;
        }
        String lowerCase = str.toLowerCase(Locale.ROOT);
        String lowerCase2 = str2.toLowerCase(Locale.ROOT);
        String[] split = lowerCase2.split("\\.");
        if (!(split.length >= 3 && split[0].endsWith("*") && (!z || m2347a(split)))) {
            return lowerCase.equals(lowerCase2);
        }
        String str3 = split[0];
        if (str3.length() > 1) {
            String substring = str3.substring(0, str3.length() - 1);
            z2 = lowerCase.startsWith(substring) && lowerCase.substring(substring.length()).endsWith(lowerCase2.substring(str3.length()));
        } else {
            z2 = lowerCase.endsWith(lowerCase2.substring(1));
        }
        if (z2) {
            return !z || m2345a(lowerCase) == m2345a(lowerCase2);
        }
        return false;
    }

    /* renamed from: a */
    private static boolean m2347a(String[] strArr) {
        return (strArr.length == 3 && strArr[2].length() == 2 && Arrays.binarySearch(f1909a, strArr[1]) >= 0) ? false : true;
    }

    /* JADX WARNING: type inference failed for: r6v7, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo2684a(java.lang.String r5, java.security.cert.X509Certificate r6) {
        /*
            r4 = this;
            boolean r0 = p036c.p037a.p038a.p039a.p050e.p056f.C0625a.m2378a(r5)
            boolean r1 = p036c.p037a.p038a.p039a.p050e.p056f.C0625a.m2379b(r5)
            if (r0 != 0) goto L_0x000f
            if (r1 == 0) goto L_0x000d
            goto L_0x000f
        L_0x000d:
            r0 = 2
            goto L_0x0010
        L_0x000f:
            r0 = 7
        L_0x0010:
            java.util.List r0 = p036c.p037a.p038a.p039a.p050e.p055e.C0614d.m2351a(r6, r0)
            javax.security.auth.x500.X500Principal r6 = r6.getSubjectX500Principal()
            c.a.a.a.e.e.e r1 = new c.a.a.a.e.e.e
            r1.<init>(r6)
            java.lang.String r6 = "cn"
            java.lang.String r6 = r1.mo2691a((java.lang.String) r6)
            r1 = 0
            if (r6 == 0) goto L_0x002d
            r2 = 1
            java.lang.String[] r2 = new java.lang.String[r2]
            r3 = 0
            r2[r3] = r6
            goto L_0x002e
        L_0x002d:
            r2 = r1
        L_0x002e:
            if (r0 == 0) goto L_0x0043
            boolean r6 = r0.isEmpty()
            if (r6 != 0) goto L_0x0043
            int r6 = r0.size()
            java.lang.String[] r6 = new java.lang.String[r6]
            java.lang.Object[] r6 = r0.toArray(r6)
            r1 = r6
            java.lang.String[] r1 = (java.lang.String[]) r1
        L_0x0043:
            r4.verify(r5, r2, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p036c.p037a.p038a.p039a.p050e.p055e.C0611a.mo2684a(java.lang.String, java.security.cert.X509Certificate):void");
    }

    /* renamed from: a */
    public final void mo2685a(String str, String[] strArr, String[] strArr2, boolean z) {
        List<String> list = null;
        String str2 = (strArr == null || strArr.length <= 0) ? null : strArr[0];
        if (strArr2 != null && strArr2.length > 0) {
            list = Arrays.asList(strArr2);
        }
        String a = C0625a.m2379b(str) ? C0614d.m2350a(str.toLowerCase(Locale.ROOT)) : str;
        if (list != null) {
            for (String str3 : list) {
                if (C0625a.m2379b(str3)) {
                    str3 = C0614d.m2350a(str3);
                }
                if (m2346a(a, str3, z)) {
                    return;
                }
            }
            throw new SSLException("Certificate for <" + str + "> doesn't match any " + "of the subject alternative names: " + list);
        } else if (str2 != null) {
            if (!m2346a(a, C0625a.m2379b(str2) ? C0614d.m2350a(str2) : str2, z)) {
                throw new SSLException("Certificate for <" + str + "> doesn't match " + "common name of the certificate subject: " + str2);
            }
        } else {
            throw new SSLException("Certificate subject for <" + str + "> doesn't contain " + "a common name and does not have alternative names");
        }
    }

    public final void verify(String str, SSLSocket sSLSocket) {
        C0870a.m3042a(str, "Host");
        SSLSession session = sSLSocket.getSession();
        if (session == null) {
            sSLSocket.getInputStream().available();
            session = sSLSocket.getSession();
            if (session == null) {
                sSLSocket.startHandshake();
                session = sSLSocket.getSession();
            }
        }
        mo2684a(str, (X509Certificate) session.getPeerCertificates()[0]);
    }

    public final boolean verify(String str, SSLSession sSLSession) {
        try {
            mo2684a(str, (X509Certificate) sSLSession.getPeerCertificates()[0]);
            return true;
        } catch (SSLException e) {
            if (this.f1910b.mo2805a()) {
                this.f1910b.mo2804a(e.getMessage(), e);
            }
            return false;
        }
    }
}
