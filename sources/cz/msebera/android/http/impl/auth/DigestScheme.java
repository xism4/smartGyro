package cz.msebera.android.http.impl.auth;

import cz.msebera.android.http.Consts;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpEntity;
import cz.msebera.android.http.HttpEntityEnclosingRequest;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.auth.AuthenticationException;
import cz.msebera.android.http.auth.Credentials;
import cz.msebera.android.http.auth.MalformedChallengeException;
import cz.msebera.android.http.execchain.BasicHttpContext;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.message.BasicHeaderValueFormatter;
import cz.msebera.android.http.message.BasicNameValuePair;
import cz.msebera.android.http.message.BufferedHeader;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;
import cz.msebera.android.http.mime.EncodingUtils;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashSet;
import java.util.Locale;
import java.util.StringTokenizer;

public class DigestScheme extends RFC2617Scheme {
    private static final char[] HEXADECIMAL = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private String a1;
    private String a2;
    private String cnonce;
    private boolean complete;
    private String lastNonce;
    private long nounceCount;

    public DigestScheme() {
        this(Consts.ASCII);
    }

    public DigestScheme(Charset charset) {
        super(charset);
        this.complete = false;
    }

    public static String createCnonce() {
        byte[] $r2 = new byte[8];
        new SecureRandom().nextBytes($r2);
        return encode($r2);
    }

    private Header createDigestHeader(Credentials credentials, HttpRequest httpRequest) {
        char c;
        String $r13;
        String $r18;
        String $r4;
        StringBuilder $r26;
        String $r42 = getParameter("uri");
        String $r5 = getParameter("realm");
        String $r6 = getParameter("nonce");
        String $r7 = getParameter("opaque");
        String $r8 = getParameter("methodname");
        String $r9 = getParameter("algorithm");
        String $r10 = $r9;
        if ($r9 == null) {
            $r10 = "MD5";
        }
        HashSet $r11 = new HashSet(8);
        String $r92 = getParameter("qop");
        if ($r92 != null) {
            StringTokenizer $r12 = new StringTokenizer($r92, ",");
            while ($r12.hasMoreTokens()) {
                $r11.add($r12.nextToken().trim().toLowerCase(Locale.ROOT));
            }
            c = (!(httpRequest instanceof HttpEntityEnclosingRequest) || !$r11.contains("auth-int")) ? $r11.contains("auth") ? (char) 2 : 65535 : 1;
        } else {
            c = 0;
        }
        if (c != 65535) {
            String $r93 = getParameter("charset");
            String $r15 = $r93;
            if ($r93 == null) {
                $r15 = "ISO-8859-1";
            }
            String $r94 = $r10.equalsIgnoreCase("MD5-sess") ? "MD5" : $r10;
            try {
                MessageDigest $r16 = createMessageDigest($r94);
                String $r95 = credentials.getUserPrincipal().getName();
                String $r182 = credentials.getPassword();
                String $r19 = $r182;
                String $r132 = this.lastNonce;
                String str = $r132;
                if ($r6.equals($r132)) {
                    $r13 = $r5;
                    long $l1 = this.nounceCount + 1;
                    long j = $l1;
                    this.nounceCount = $l1;
                } else {
                    $r13 = $r5;
                    this.nounceCount = 1;
                    this.cnonce = null;
                    this.lastNonce = $r6;
                }
                StringBuilder sb = new StringBuilder(256);
                Formatter formatter = new Formatter(sb, Locale.US);
                long j2 = this.nounceCount;
                long j3 = j2;
                formatter.format("%08x", new Object[]{Long.valueOf(j2)});
                formatter.close();
                String $r52 = sb.toString();
                if (this.cnonce == null) {
                    this.cnonce = createCnonce();
                }
                this.a1 = null;
                this.a2 = null;
                if ($r10.equalsIgnoreCase("MD5-sess")) {
                    sb.setLength(0);
                    sb.append($r95);
                    sb.append(':');
                    sb.append($r13);
                    sb.append(':');
                    sb.append($r182);
                    String $r183 = encode($r16.digest(EncodingUtils.getBytes(sb.toString(), $r15)));
                    sb.setLength(0);
                    sb.append($r183);
                    sb.append(':');
                    sb.append($r6);
                    sb.append(':');
                    $r19 = this.cnonce;
                } else {
                    sb.setLength(0);
                    sb.append($r95);
                    sb.append(':');
                    sb.append($r13);
                    sb.append(':');
                }
                sb.append($r19);
                this.a1 = sb.toString();
                String $r184 = this.a1;
                String str2 = $r184;
                String $r192 = encode($r16.digest(EncodingUtils.getBytes($r184, $r15)));
                if (c == 2) {
                    this.a2 = $r8 + ':' + $r42;
                    $r18 = "auth";
                } else {
                    if (c == 1) {
                        HttpEntity $r28 = httpRequest instanceof HttpEntityEnclosingRequest ? ((HttpEntityEnclosingRequest) httpRequest).getEntity() : null;
                        if ($r28 == null || $r28.isRepeatable()) {
                            $r18 = "auth";
                            HttpEntityDigester httpEntityDigester = new HttpEntityDigester($r16);
                            if ($r28 != null) {
                                try {
                                    $r28.writeTo(httpEntityDigester);
                                } catch (IOException $r31) {
                                    throw new AuthenticationException("I/O error reading entity content", $r31);
                                }
                            }
                            httpEntityDigester.close();
                            $r26 = new StringBuilder();
                            $r26.append($r8);
                            $r26.append(':');
                            $r26.append($r42);
                            $r26.append(':');
                            $r26.append(encode(httpEntityDigester.getDigest()));
                        } else {
                            $r18 = "auth";
                            if ($r11.contains("auth")) {
                                this.a2 = $r8 + ':' + $r42;
                                c = 2;
                            } else {
                                throw new AuthenticationException("Qop auth-int cannot be used with a non-repeatable entity");
                            }
                        }
                    } else {
                        $r18 = "auth";
                        $r26 = new StringBuilder();
                        $r26.append($r8);
                        $r26.append(':');
                        $r26.append($r42);
                    }
                    this.a2 = $r26.toString();
                }
                String $r82 = encode($r16.digest(EncodingUtils.getBytes(this.a2, $r15)));
                if (c == 0) {
                    sb.setLength(0);
                    sb.append($r192);
                    sb.append(':');
                    sb.append($r6);
                } else {
                    sb.setLength(0);
                    sb.append($r192);
                    sb.append(':');
                    sb.append($r6);
                    sb.append(':');
                    sb.append($r52);
                    sb.append(':');
                    sb.append(this.cnonce);
                    sb.append(':');
                    sb.append(c == 1 ? "auth-int" : "auth");
                }
                sb.append(':');
                sb.append($r82);
                String $r83 = encode($r16.digest(EncodingUtils.getAsciiBytes(sb.toString())));
                CharArrayBuffer charArrayBuffer = new CharArrayBuffer(128);
                charArrayBuffer.append(isProxy() ? "Proxy-Authorization" : "Authorization");
                charArrayBuffer.append(": Digest ");
                ArrayList arrayList = new ArrayList(20);
                arrayList.add(new BasicNameValuePair("username", $r95));
                arrayList.add(new BasicNameValuePair("realm", $r13));
                arrayList.add(new BasicNameValuePair("nonce", $r6));
                arrayList.add(new BasicNameValuePair("uri", $r42));
                arrayList.add(new BasicNameValuePair("response", $r83));
                if (c != 0) {
                    if (c == 1) {
                        $r18 = "auth-int";
                    }
                    $r4 = "qop";
                    arrayList.add(new BasicNameValuePair("qop", $r18));
                    arrayList.add(new BasicNameValuePair("nc", $r52));
                    arrayList.add(new BasicNameValuePair("cnonce", this.cnonce));
                } else {
                    $r4 = "qop";
                }
                arrayList.add(new BasicNameValuePair("algorithm", $r10));
                if ($r7 != null) {
                    arrayList.add(new BasicNameValuePair("opaque", $r7));
                }
                for (int i = 0; i < arrayList.size(); i++) {
                    BasicNameValuePair $r34 = (BasicNameValuePair) arrayList.get(i);
                    if (i > 0) {
                        charArrayBuffer.append(", ");
                    }
                    String $r72 = $r34.getName();
                    BasicHeaderValueFormatter.INSTANCE.formatNameValuePair(charArrayBuffer, $r34, !("nc".equals($r72) || $r4.equals($r72) || "algorithm".equals($r72)));
                }
                return new BufferedHeader(charArrayBuffer);
            } catch (UnsupportedDigestAlgorithmException e) {
                throw new AuthenticationException("Unsuppported digest algorithm: " + $r94);
            }
        } else {
            throw new AuthenticationException("None of the qop methods is supported: " + $r92);
        }
    }

    private static MessageDigest createMessageDigest(String str) {
        try {
            return MessageDigest.getInstance(str);
        } catch (Exception e) {
            throw new UnsupportedDigestAlgorithmException("Unsupported algorithm in HTTP Digest authentication: " + str);
        }
    }

    static String encode(byte[] bArr) {
        int $i0 = bArr.length;
        char[] $r2 = new char[($i0 * 2)];
        for (int $i1 = 0; $i1 < $i0; $i1++) {
            int $i5 = $i1 * 2;
            char[] $r0 = HEXADECIMAL;
            $r2[$i5] = $r0[(bArr[$i1] & 240) >> 4];
            $r2[$i5 + 1] = $r0[bArr[$i1] & 15];
        }
        return new String($r2);
    }

    public Header authenticate(Credentials credentials, HttpRequest httpRequest) {
        return authenticate(credentials, httpRequest, new BasicHttpContext());
    }

    public Header authenticate(Credentials credentials, HttpRequest httpRequest, HttpContext httpContext) {
        Args.notNull(credentials, "Credentials");
        Args.notNull(httpRequest, "HTTP request");
        if (getParameter("realm") == null) {
            throw new AuthenticationException("missing realm in challenge");
        } else if (getParameter("nonce") != null) {
            getParameters().put("methodname", httpRequest.getRequestLine().getMethod());
            getParameters().put("uri", httpRequest.getRequestLine().getUri());
            if (getParameter("charset") == null) {
                getParameters().put("charset", getCredentialsCharset(httpRequest));
            }
            return createDigestHeader(credentials, httpRequest);
        } else {
            throw new AuthenticationException("missing nonce in challenge");
        }
    }

    public String getSchemeName() {
        return "digest";
    }

    public boolean isComplete() {
        if ("true".equalsIgnoreCase(getParameter("stale"))) {
            return false;
        }
        return this.complete;
    }

    public boolean isConnectionBased() {
        return false;
    }

    public void processChallenge(Header header) {
        super.processChallenge(header);
        this.complete = true;
        if (getParameters().isEmpty()) {
            throw new MalformedChallengeException("Authentication challenge is empty");
        }
    }

    public String toString() {
        return "DIGEST [complete=" + this.complete + ", nonce=" + this.lastNonce + ", nc=" + this.nounceCount + "]";
    }
}
