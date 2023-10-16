package org.cocos2dx.package_1;

import android.util.Log;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

public class Cocos2dxHttpURLConnection {
    private static final String POST_METHOD = "POST";
    private static final String PUT_METHOD = "PUT";

    static void addRequestHeader(HttpURLConnection httpURLConnection, String str, String str2) {
        httpURLConnection.setRequestProperty(str, str2);
    }

    public static String combinCookies(List list, String str) {
        StringBuilder $r2 = new StringBuilder();
        Iterator $r3 = list.iterator();
        String $r4 = "/";
        String $r5 = "FALSE";
        String $r6 = null;
        String $r7 = null;
        String $r8 = null;
        String $r9 = str;
        while ($r3.hasNext()) {
            String[] $r11 = ((String) $r3.next()).split(";");
            int $i0 = $r11.length;
            String $r1 = $r7;
            for (int i = 0; i < $i0; i++) {
                String $r72 = $r11[i];
                int $i2 = $r72.indexOf("=");
                if (-1 != $i2) {
                    String[] $r12 = {$r72.substring(0, $i2), $r72.substring($i2 + 1)};
                    if ("expires".equalsIgnoreCase($r12[0].trim())) {
                        $r8 = str2Seconds($r12[1].trim());
                    } else {
                        if ("path".equalsIgnoreCase($r12[0].trim())) {
                            $r4 = $r12[1];
                        } else {
                            if ("secure".equalsIgnoreCase($r12[0].trim())) {
                                $r5 = $r12[1];
                            } else {
                                if ("domain".equalsIgnoreCase($r12[0].trim())) {
                                    $r9 = $r12[1];
                                } else {
                                    if (!"version".equalsIgnoreCase($r12[0].trim())) {
                                        if (!"max-age".equalsIgnoreCase($r12[0].trim())) {
                                            $r6 = $r12[0];
                                            $r1 = $r12[1];
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if ($r9 == null) {
                $r9 = "none";
            }
            $r2.append($r9);
            $r2.append(9);
            $r2.append("FALSE");
            $r2.append(9);
            $r2.append($r4);
            $r2.append(9);
            $r2.append($r5);
            $r2.append(9);
            $r2.append($r8);
            $r2.append("\t");
            $r2.append($r6);
            $r2.append("\t");
            $r2.append($r1);
            $r2.append(10);
            $r7 = $r1;
        }
        return $r2.toString();
    }

    static int connect(HttpURLConnection httpURLConnection) {
        try {
            httpURLConnection.connect();
            return 0;
        } catch (IOException $r2) {
            Log.e("cocos2d-x debug info", "come in connect");
            Log.e("cocos2d-x debug info", $r2.toString());
            return 1;
        }
    }

    static HttpURLConnection createHttpURLConnection(String str) {
        try {
            HttpURLConnection $r3 = (HttpURLConnection) new URL(str).openConnection();
            $r3.setRequestProperty("Accept-Encoding", "identity");
            $r3.setDoInput(true);
            return $r3;
        } catch (Exception $r4) {
            Log.e("URLConnection exception", $r4.toString());
            return null;
        }
    }

    static void disconnect(HttpURLConnection httpURLConnection) {
        httpURLConnection.disconnect();
    }

    static int getResponseCode(HttpURLConnection httpURLConnection) {
        try {
            return httpURLConnection.getResponseCode();
        } catch (IOException $r1) {
            Log.e("URLConnection exception", $r1.toString());
            return 0;
        }
    }

    static byte[] getResponseContent(HttpURLConnection httpURLConnection) {
        Object $r3;
        ByteArrayOutputStream r10;
        InflaterInputStream r9;
        GZIPInputStream r8;
        try {
            $r3 = httpURLConnection.getInputStream();
            String $r4 = httpURLConnection.getContentEncoding();
            if ($r4 != null) {
                if ($r4.equalsIgnoreCase("gzip")) {
                    $r3 = r8;
                    r8 = new GZIPInputStream(httpURLConnection.getInputStream());
                } else if ($r4.equalsIgnoreCase("deflate")) {
                    $r3 = r9;
                    r9 = new InflaterInputStream(httpURLConnection.getInputStream());
                }
            }
        } catch (IOException e) {
            $r3 = httpURLConnection.getErrorStream();
        }
        byte[] $r6 = new byte[1024];
        ByteArrayOutputStream $r7 = r10;
        try {
            r10 = new ByteArrayOutputStream();
            while (true) {
                int $i0 = ((InputStream) $r3).read($r6, 0, 1024);
                if ($i0 != -1) {
                    $r7.write($r6, 0, $i0);
                } else {
                    byte[] $r62 = $r7.toByteArray();
                    $r7.close();
                    return $r62;
                }
            }
        } catch (Exception e2) {
            Log.e("URLConnection exception", e2.toString());
            return null;
        }
    }

    static String getResponseHeaderByIdx(HttpURLConnection httpURLConnection, int i) {
        Map $r1 = httpURLConnection.getHeaderFields();
        if ($r1 == null) {
            return null;
        }
        int $i1 = 0;
        for (Map.Entry $r5 : $r1.entrySet()) {
            if ($i1 == i) {
                String $r6 = (String) $r5.getKey();
                if ($r6 == null) {
                    return listToString((List) $r5.getValue(), ",") + "\n";
                }
                return $r6 + ":" + listToString((List) $r5.getValue(), ",") + "\n";
            }
            $i1++;
        }
        return null;
    }

    static String getResponseHeaderByKey(HttpURLConnection httpURLConnection, String str) {
        Map $r2;
        if (str == null || ($r2 = httpURLConnection.getHeaderFields()) == null) {
            return null;
        }
        for (Map.Entry $r6 : $r2.entrySet()) {
            if (str.equalsIgnoreCase((String) $r6.getKey())) {
                return "set-cookie".equalsIgnoreCase(str) ? combinCookies((List) $r6.getValue(), httpURLConnection.getURL().getHost()) : listToString((List) $r6.getValue(), ",");
            }
        }
        return null;
    }

    static int getResponseHeaderByKeyInt(HttpURLConnection httpURLConnection, String str) {
        String $r0 = httpURLConnection.getHeaderField(str);
        if ($r0 == null) {
            return 0;
        }
        return Integer.parseInt($r0);
    }

    static String getResponseHeaders(HttpURLConnection httpURLConnection) {
        Map $r2 = httpURLConnection.getHeaderFields();
        if ($r2 == null) {
            return null;
        }
        String $r5 = "";
        for (Map.Entry $r7 : $r2.entrySet()) {
            String $r8 = (String) $r7.getKey();
            if ($r8 == null) {
                $r5 = $r5 + listToString((List) $r7.getValue(), ",") + "\n";
            } else {
                $r5 = $r5 + $r8 + ":" + listToString((List) $r7.getValue(), ",") + "\n";
            }
        }
        return $r5;
    }

    static String getResponseMessage(HttpURLConnection httpURLConnection) {
        try {
            return httpURLConnection.getResponseMessage();
        } catch (IOException $r2) {
            String $r1 = $r2.toString();
            Log.e("URLConnection exception", $r1);
            return $r1;
        }
    }

    public static String listToString(List list, String str) {
        if (list == null) {
            return null;
        }
        StringBuilder $r1 = new StringBuilder();
        boolean $z0 = false;
        Iterator $r3 = list.iterator();
        while ($r3.hasNext()) {
            String $r5 = (String) $r3.next();
            if ($z0) {
                $r1.append(str);
            }
            if ($r5 == null) {
                $r5 = "";
            }
            $r1.append($r5);
            $z0 = true;
        }
        return $r1.toString();
    }

    static void sendRequest(HttpURLConnection httpURLConnection, byte[] bArr) {
        try {
            OutputStream $r2 = httpURLConnection.getOutputStream();
            if (bArr != null) {
                $r2.write(bArr);
                $r2.flush();
            }
            $r2.close();
        } catch (IOException $r3) {
            Log.e("URLConnection exception", $r3.toString());
        }
    }

    static void setReadAndConnectTimeout(HttpURLConnection httpURLConnection, int i, int i2) {
        httpURLConnection.setReadTimeout(i);
        httpURLConnection.setConnectTimeout(i2);
    }

    static void setRequestMethod(HttpURLConnection httpURLConnection, String str) {
        try {
            httpURLConnection.setRequestMethod(str);
            if (!str.equalsIgnoreCase(POST_METHOD)) {
                if (!str.equalsIgnoreCase(PUT_METHOD)) {
                    return;
                }
            }
            httpURLConnection.setDoOutput(true);
        } catch (ProtocolException $r2) {
            Log.e("URLConnection exception", $r2.toString());
        }
    }

    static void setVerifySSL(HttpURLConnection httpURLConnection, String str) {
        BufferedInputStream $r3;
        if (httpURLConnection instanceof HttpsURLConnection) {
            HttpsURLConnection $r2 = (HttpsURLConnection) httpURLConnection;
            try {
                if (str.startsWith("/")) {
                    $r3 = new BufferedInputStream(new FileInputStream(str));
                } else {
                    $r3 = new BufferedInputStream(Cocos2dxHelper.getActivity().getAssets().open(str.substring(7)));
                }
                Certificate $r9 = CertificateFactory.getInstance("X.509").generateCertificate($r3);
                PrintStream $r10 = System.out;
                StringBuilder sb = new StringBuilder();
                sb.append("ca=");
                sb.append(((X509Certificate) $r9).getSubjectDN());
                $r10.println(sb.toString());
                $r3.close();
                KeyStore $r14 = KeyStore.getInstance(KeyStore.getDefaultType());
                $r14.load((InputStream) null, (char[]) null);
                $r14.setCertificateEntry("ca", $r9);
                TrustManagerFactory $r15 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                $r15.init($r14);
                SSLContext $r16 = SSLContext.getInstance("TLS");
                $r16.init((KeyManager[]) null, $r15.getTrustManagers(), (SecureRandom) null);
                $r2.setSSLSocketFactory($r16.getSocketFactory());
            } catch (Exception $r19) {
                Log.e("URLConnection exception", $r19.toString());
            }
        }
    }

    private static String str2Seconds(String str) {
        long $l0;
        Calendar $r3 = Calendar.getInstance();
        try {
            $r3.setTime(new SimpleDateFormat("EEE, dd-MMM-yy hh:mm:ss zzz", Locale.US).parse(str));
            $l0 = $r3.getTimeInMillis() / 1000;
        } catch (ParseException $r5) {
            Log.e("URLConnection exception", $r5.toString());
            $l0 = 0;
        }
        return Long.toString($l0);
    }
}
