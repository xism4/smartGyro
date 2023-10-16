package cz.msebera.android.http.mime;

public class VersionInfo {
    private final String infoClassloader;
    private final String infoModule;
    private final String infoPackage;
    private final String infoRelease;
    private final String infoTimestamp;

    protected VersionInfo(String str, String $r3, String $r4, String $r5, String str2) {
        Args.notNull(str, "Package identifier");
        this.infoRelease = str;
        String $r2 = "UNAVAILABLE";
        this.infoTimestamp = $r3 == null ? "UNAVAILABLE" : $r3;
        this.infoClassloader = $r4 == null ? "UNAVAILABLE" : $r4;
        this.infoPackage = $r5 == null ? "UNAVAILABLE" : $r5;
        this.infoModule = str2 != null ? str2 : $r2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v0, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v0, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v0, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static cz.msebera.android.http.mime.VersionInfo fromMap(java.lang.String r19, java.util.Map r20, java.lang.ClassLoader r21) {
        /*
            java.lang.String r6 = "Package identifier"
            r0 = r19
            cz.msebera.android.http.mime.Args.notNull(r0, r6)
            r7 = 0
            if (r20 == 0) goto L_0x0065
            java.lang.String r6 = "info.module"
            r0 = r20
            java.lang.Object r8 = r0.get(r6)
            r10 = r8
            java.lang.String r10 = (java.lang.String) r10
            r9 = r10
            if (r9 == 0) goto L_0x0020
            int r11 = r9.length()
            r12 = 1
            if (r11 >= r12) goto L_0x0020
            r9 = 0
        L_0x0020:
            java.lang.String r6 = "info.release"
            r0 = r20
            java.lang.Object r8 = r0.get(r6)
            r14 = r8
            java.lang.String r14 = (java.lang.String) r14
            r13 = r14
            if (r13 == 0) goto L_0x003e
            int r11 = r13.length()
            r12 = 1
            if (r11 < r12) goto L_0x003d
            java.lang.String r6 = "${pom.version}"
            boolean r15 = r13.equals(r6)
            if (r15 == 0) goto L_0x003e
        L_0x003d:
            r13 = 0
        L_0x003e:
            java.lang.String r6 = "info.timestamp"
            r0 = r20
            java.lang.Object r8 = r0.get(r6)
            r17 = r8
            java.lang.String r17 = (java.lang.String) r17
            r16 = r17
            if (r16 == 0) goto L_0x0064
            r0 = r16
            int r11 = r0.length()
            r12 = 1
            if (r11 < r12) goto L_0x0061
            java.lang.String r6 = "${mvn.timestamp}"
            r0 = r16
            boolean r15 = r0.equals(r6)
            if (r15 == 0) goto L_0x0064
        L_0x0061:
            r16 = 0
            goto L_0x0064
        L_0x0064:
            goto L_0x0069
        L_0x0065:
            r9 = 0
            r13 = 0
            r16 = 0
        L_0x0069:
            if (r21 == 0) goto L_0x0071
            r0 = r21
            java.lang.String r7 = r0.toString()
        L_0x0071:
            cz.msebera.android.http.mime.VersionInfo r18 = new cz.msebera.android.http.mime.VersionInfo
            r0 = r18
            r1 = r19
            r2 = r9
            r3 = r13
            r4 = r16
            r5 = r7
            r0.<init>(r1, r2, r3, r4, r5)
            return r18
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.http.mime.VersionInfo.fromMap(java.lang.String, java.util.Map, java.lang.ClassLoader):cz.msebera.android.http.mime.VersionInfo");
    }

    public static String getUserAgent(String str, String str2, Class cls) {
        VersionInfo $r4 = loadVersionInfo(str2, cls.getClassLoader());
        return String.format("%s/%s (Java/%s)", new Object[]{str, $r4 != null ? $r4.getClassloader() : "UNAVAILABLE", System.getProperty("java.version")});
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0053 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static cz.msebera.android.http.mime.VersionInfo loadVersionInfo(java.lang.String r14, java.lang.ClassLoader r15) {
        /*
            java.lang.String r0 = "Package identifier"
            cz.msebera.android.http.mime.Args.notNull(r14, r0)
            if (r15 == 0) goto L_0x0008
            goto L_0x0010
        L_0x0008:
            java.lang.Thread r1 = java.lang.Thread.currentThread()
            java.lang.ClassLoader r15 = r1.getContextClassLoader()
        L_0x0010:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()     // Catch:{ IOException -> 0x004d }
            r4 = 46
            r5 = 47
            java.lang.String r3 = r14.replace(r4, r5)     // Catch:{ IOException -> 0x004d }
            r2.append(r3)     // Catch:{ IOException -> 0x004d }
            java.lang.String r0 = "/"
            r2.append(r0)     // Catch:{ IOException -> 0x004d }
            java.lang.String r0 = "version.properties"
            r2.append(r0)     // Catch:{ IOException -> 0x004d }
            java.lang.String r3 = r2.toString()     // Catch:{ IOException -> 0x004d }
            java.io.InputStream r6 = r15.getResourceAsStream(r3)     // Catch:{ IOException -> 0x004d }
            if (r6 == 0) goto L_0x0045
            java.util.Properties r7 = new java.util.Properties     // Catch:{ Throwable -> 0x0040 }
            r7.<init>()     // Catch:{ Throwable -> 0x0040 }
            r7.load(r6)     // Catch:{ Throwable -> 0x0040 }
            r6.close()     // Catch:{ IOException -> 0x004f }
            goto L_0x0046
        L_0x0040:
            r8 = move-exception
            r6.close()     // Catch:{ IOException -> 0x0051 }
            throw r8     // Catch:{ IOException -> 0x0051 }
        L_0x0045:
            r7 = 0
        L_0x0046:
            if (r7 == 0) goto L_0x0053
            cz.msebera.android.http.mime.VersionInfo r9 = fromMap(r14, r7, r15)
            return r9
        L_0x004d:
            r10 = move-exception
            goto L_0x0045
        L_0x004f:
            r11 = move-exception
            goto L_0x0046
        L_0x0051:
            r12 = move-exception
            goto L_0x0045
        L_0x0053:
            r13 = 0
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.http.mime.VersionInfo.loadVersionInfo(java.lang.String, java.lang.ClassLoader):cz.msebera.android.http.mime.VersionInfo");
    }

    public final String getClassloader() {
        return this.infoClassloader;
    }

    public String toString() {
        StringBuilder $r2 = new StringBuilder(this.infoRelease.length() + 20 + this.infoTimestamp.length() + this.infoClassloader.length() + this.infoPackage.length() + this.infoModule.length());
        $r2.append("VersionInfo(");
        $r2.append(this.infoRelease);
        $r2.append(':');
        $r2.append(this.infoTimestamp);
        if (!"UNAVAILABLE".equals(this.infoClassloader)) {
            $r2.append(':');
            $r2.append(this.infoClassloader);
        }
        if (!"UNAVAILABLE".equals(this.infoPackage)) {
            $r2.append(':');
            $r2.append(this.infoPackage);
        }
        $r2.append(')');
        if (!"UNAVAILABLE".equals(this.infoModule)) {
            $r2.append('@');
            $r2.append(this.infoModule);
        }
        return $r2.toString();
    }
}
