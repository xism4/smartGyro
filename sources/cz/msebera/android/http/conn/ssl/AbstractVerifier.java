package cz.msebera.android.http.conn.ssl;

import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.conn.socket.InetAddressUtils;
import cz.msebera.android.http.mime.Args;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

@Deprecated
public abstract class AbstractVerifier implements X509HostnameVerifier {
    static final String[] BAD_COUNTRY_2LDS = {"ac", "co", "com", "ed", "edu", "go", "gouv", "gov", "info", "lg", "ne", "net", "or", "org"};
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());

    static {
        Arrays.sort(BAD_COUNTRY_2LDS);
    }

    public static int countDots(String str) {
        int $i1 = 0;
        for (int $i0 = 0; $i0 < str.length(); $i0++) {
            if (str.charAt($i0) == '.') {
                $i1++;
            }
        }
        return $i1;
    }

    private static boolean validCountryWildcard(String[] strArr) {
        return (strArr.length == 3 && strArr[2].length() == 2 && Arrays.binarySearch(BAD_COUNTRY_2LDS, strArr[1]) >= 0) ? false : true;
    }

    private static boolean verify(String str, String str2, boolean z) {
        boolean $z1;
        if (str == null) {
            return false;
        }
        String $r0 = str.toLowerCase(Locale.ROOT);
        String $r1 = str2.toLowerCase(Locale.ROOT);
        String[] $r3 = $r1.split("\\.");
        if (!($r3.length >= 3 && $r3[0].endsWith("*") && (!z || validCountryWildcard($r3)))) {
            return $r0.equals($r1);
        }
        String $r5 = $r3[0];
        if ($r5.length() > 1) {
            String $r4 = $r5.substring(0, $r5.length() - 1);
            $z1 = $r0.startsWith($r4) && $r0.substring($r4.length()).endsWith($r1.substring($r5.length()));
        } else {
            $z1 = $r0.endsWith($r1.substring(1));
        }
        if ($z1) {
            return !z || countDots($r0) == countDots($r1);
        }
        return false;
    }

    /* JADX WARNING: type inference failed for: r13v0, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void verify(java.lang.String r16, java.security.cert.X509Certificate r17) {
        /*
            r15 = this;
            r0 = r16
            boolean r1 = cz.msebera.android.http.conn.socket.InetAddressUtils.isIPv4Address(r0)
            r0 = r16
            boolean r2 = cz.msebera.android.http.conn.socket.InetAddressUtils.isIPv6Address(r0)
            if (r1 != 0) goto L_0x0013
            if (r2 == 0) goto L_0x0011
            goto L_0x0013
        L_0x0011:
            r3 = 2
            goto L_0x0014
        L_0x0013:
            r3 = 7
        L_0x0014:
            r0 = r17
            java.util.List r4 = cz.msebera.android.http.conn.ssl.DefaultHostnameVerifier.getSubjectAltNames(r0, r3)
            r0 = r17
            javax.security.auth.x500.X500Principal r5 = r0.getSubjectX500Principal()
            cz.msebera.android.http.conn.ssl.DistinguishedNameParser r6 = new cz.msebera.android.http.conn.ssl.DistinguishedNameParser
            r6.<init>(r5)
            java.lang.String r8 = "cn"
            java.lang.String r7 = r6.findMostSpecific(r8)
            r9 = 0
            if (r7 == 0) goto L_0x0035
            r11 = 1
            java.lang.String[] r10 = new java.lang.String[r11]
            r11 = 0
            r10[r11] = r7
            goto L_0x0036
        L_0x0035:
            r10 = 0
        L_0x0036:
            if (r4 == 0) goto L_0x004e
            boolean r1 = r4.isEmpty()
            if (r1 != 0) goto L_0x004e
            int r12 = r4.size()
            java.lang.String[] r9 = new java.lang.String[r12]
            java.lang.Object[] r13 = r4.toArray(r9)
            r14 = r13
            java.lang.String[] r14 = (java.lang.String[]) r14
            r9 = r14
        L_0x004e:
            r0 = r16
            r15.verify(r0, r10, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.http.conn.ssl.AbstractVerifier.verify(java.lang.String, java.security.cert.X509Certificate):void");
    }

    public final void verify(String str, SSLSocket sSLSocket) {
        Args.notNull(str, "Host");
        SSLSession $r3 = sSLSocket.getSession();
        SSLSession $r4 = $r3;
        if ($r3 == null) {
            sSLSocket.getInputStream().available();
            SSLSession $r32 = sSLSocket.getSession();
            $r4 = $r32;
            if ($r32 == null) {
                sSLSocket.startHandshake();
                $r4 = sSLSocket.getSession();
            }
        }
        verify(str, (X509Certificate) $r4.getPeerCertificates()[0]);
    }

    public final void verify(String $r1, String[] strArr, String[] strArr2, boolean z) {
        List<String> $r4 = null;
        String $r5 = (strArr == null || strArr.length <= 0) ? null : strArr[0];
        if (strArr2 != null && strArr2.length > 0) {
            $r4 = Arrays.asList(strArr2);
        }
        String $r7 = InetAddressUtils.isIPv6Address($r1) ? DefaultHostnameVerifier.normaliseAddress($r1.toLowerCase(Locale.ROOT)) : $r1;
        if ($r4 != null) {
            for (String $r52 : $r4) {
                if (InetAddressUtils.isIPv6Address($r52)) {
                    $r52 = DefaultHostnameVerifier.normaliseAddress($r52);
                }
                if (verify($r7, $r52, z)) {
                    return;
                }
            }
            throw new SSLException("Certificate for <" + $r1 + "> doesn't match any " + "of the subject alternative names: " + $r4);
        } else if ($r5 != null) {
            if (!verify($r7, InetAddressUtils.isIPv6Address($r5) ? DefaultHostnameVerifier.normaliseAddress($r5) : $r5, z)) {
                throw new SSLException("Certificate for <" + $r1 + "> doesn't match " + "common name of the certificate subject: " + $r5);
            }
        } else {
            throw new SSLException("Certificate subject for <" + $r1 + "> doesn't contain " + "a common name and does not have alternative names");
        }
    }

    public final boolean verify(String str, SSLSession sSLSession) {
        try {
            verify(str, (X509Certificate) sSLSession.getPeerCertificates()[0]);
            return true;
        } catch (SSLException $r6) {
            if (!this.log.isDebugEnabled()) {
                return false;
            }
            this.log.debug($r6.getMessage(), $r6);
            return false;
        }
    }
}
