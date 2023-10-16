package p036c.p037a.p038a.p039a.p074p;

import java.util.Map;

/* renamed from: c.a.a.a.p.j */
public class C0879j {

    /* renamed from: a */
    private final String f2439a;

    /* renamed from: b */
    private final String f2440b;

    /* renamed from: c */
    private final String f2441c;

    /* renamed from: d */
    private final String f2442d;

    /* renamed from: e */
    private final String f2443e;

    protected C0879j(String str, String str2, String str3, String str4, String str5) {
        C0870a.m3042a(str, "Package identifier");
        this.f2439a = str;
        String str6 = "UNAVAILABLE";
        this.f2440b = str2 == null ? str6 : str2;
        this.f2441c = str3 == null ? str6 : str3;
        this.f2442d = str4 == null ? str6 : str4;
        this.f2443e = str5 != null ? str5 : str6;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static p036c.p037a.p038a.p039a.p074p.C0879j m3090a(java.lang.String r4, java.lang.ClassLoader r5) {
        /*
            java.lang.String r0 = "Package identifier"
            p036c.p037a.p038a.p039a.p074p.C0870a.m3042a(r4, (java.lang.String) r0)
            if (r5 == 0) goto L_0x0008
            goto L_0x0010
        L_0x0008:
            java.lang.Thread r5 = java.lang.Thread.currentThread()
            java.lang.ClassLoader r5 = r5.getContextClassLoader()
        L_0x0010:
            r0 = 0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0046 }
            r1.<init>()     // Catch:{ IOException -> 0x0046 }
            r2 = 46
            r3 = 47
            java.lang.String r2 = r4.replace(r2, r3)     // Catch:{ IOException -> 0x0046 }
            r1.append(r2)     // Catch:{ IOException -> 0x0046 }
            java.lang.String r2 = "/"
            r1.append(r2)     // Catch:{ IOException -> 0x0046 }
            java.lang.String r2 = "version.properties"
            r1.append(r2)     // Catch:{ IOException -> 0x0046 }
            java.lang.String r1 = r1.toString()     // Catch:{ IOException -> 0x0046 }
            java.io.InputStream r1 = r5.getResourceAsStream(r1)     // Catch:{ IOException -> 0x0046 }
            if (r1 == 0) goto L_0x0046
            java.util.Properties r2 = new java.util.Properties     // Catch:{ all -> 0x0041 }
            r2.<init>()     // Catch:{ all -> 0x0041 }
            r2.load(r1)     // Catch:{ all -> 0x0041 }
            r1.close()     // Catch:{ IOException -> 0x0047 }
            goto L_0x0047
        L_0x0041:
            r2 = move-exception
            r1.close()     // Catch:{ IOException -> 0x0046 }
            throw r2     // Catch:{ IOException -> 0x0046 }
        L_0x0046:
            r2 = r0
        L_0x0047:
            if (r2 == 0) goto L_0x004d
            c.a.a.a.p.j r0 = m3091a((java.lang.String) r4, (java.util.Map<?, ?>) r2, (java.lang.ClassLoader) r5)
        L_0x004d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p036c.p037a.p038a.p039a.p074p.C0879j.m3090a(java.lang.String, java.lang.ClassLoader):c.a.a.a.p.j");
    }

    /* renamed from: a */
    protected static C0879j m3091a(String str, Map<?, ?> map, ClassLoader classLoader) {
        String str2;
        String str3;
        String str4;
        C0870a.m3042a(str, "Package identifier");
        String str5 = null;
        if (map != null) {
            String str6 = (String) map.get("info.module");
            if (str6 != null && str6.length() < 1) {
                str6 = null;
            }
            String str7 = (String) map.get("info.release");
            if (str7 != null && (str7.length() < 1 || str7.equals("${pom.version}"))) {
                str7 = null;
            }
            String str8 = (String) map.get("info.timestamp");
            str2 = (str8 == null || (str8.length() >= 1 && !str8.equals("${mvn.timestamp}"))) ? str8 : null;
            str4 = str6;
            str3 = str7;
        } else {
            str4 = null;
            str3 = null;
            str2 = null;
        }
        if (classLoader != null) {
            str5 = classLoader.toString();
        }
        return new C0879j(str, str4, str3, str2, str5);
    }

    /* renamed from: a */
    public static String m3092a(String str, String str2, Class<?> cls) {
        C0879j a = m3090a(str2, cls.getClassLoader());
        return String.format("%s/%s (Java/%s)", new Object[]{str, a != null ? a.mo3311a() : "UNAVAILABLE", System.getProperty("java.version")});
    }

    /* renamed from: a */
    public final String mo3311a() {
        return this.f2441c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.f2439a.length() + 20 + this.f2440b.length() + this.f2441c.length() + this.f2442d.length() + this.f2443e.length());
        sb.append("VersionInfo(");
        sb.append(this.f2439a);
        sb.append(':');
        sb.append(this.f2440b);
        if (!"UNAVAILABLE".equals(this.f2441c)) {
            sb.append(':');
            sb.append(this.f2441c);
        }
        if (!"UNAVAILABLE".equals(this.f2442d)) {
            sb.append(':');
            sb.append(this.f2442d);
        }
        sb.append(')');
        if (!"UNAVAILABLE".equals(this.f2443e)) {
            sb.append('@');
            sb.append(this.f2443e);
        }
        return sb.toString();
    }
}
