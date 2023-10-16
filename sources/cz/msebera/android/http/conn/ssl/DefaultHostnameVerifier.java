package cz.msebera.android.http.conn.ssl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.net.ssl.HostnameVerifier;

public final class DefaultHostnameVerifier implements HostnameVerifier {
    static List getSubjectAltNames(X509Certificate x509Certificate, int i) {
        Collection $r3;
        ArrayList $r2 = null;
        try {
            $r3 = x509Certificate.getSubjectAlternativeNames();
        } catch (CertificateParsingException e) {
            $r3 = null;
        }
        if ($r3 == null) {
            return null;
        }
        for (List $r6 : $r3) {
            if (((Integer) $r6.get(0)).intValue() == i) {
                String $r8 = (String) $r6.get(1);
                if ($r2 == null) {
                    $r2 = new ArrayList();
                }
                $r2.add($r8);
            }
        }
        return $r2;
    }

    static String normaliseAddress(String $r1) {
        if ($r1 == null) {
            return $r1;
        }
        try {
            return InetAddress.getByName($r1).getHostAddress();
        } catch (UnknownHostException e) {
            return $r1;
        }
    }
}
