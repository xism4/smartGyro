package cz.msebera.android.http.impl.auth;

import cz.msebera.android.http.Consts;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.auth.Credentials;
import cz.msebera.android.http.cache.Packet;
import cz.msebera.android.http.execchain.BasicHttpContext;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.message.BufferedHeader;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;
import cz.msebera.android.http.mime.EncodingUtils;
import java.nio.charset.Charset;

public class BasicScheme extends RFC2617Scheme {
    private boolean complete;

    public BasicScheme() {
        this(Consts.ASCII);
    }

    public BasicScheme(Charset charset) {
        super(charset);
        this.complete = false;
    }

    public Header authenticate(Credentials credentials, HttpRequest httpRequest) {
        return authenticate(credentials, httpRequest, new BasicHttpContext());
    }

    public Header authenticate(Credentials credentials, HttpRequest httpRequest, HttpContext httpContext) {
        Args.notNull(credentials, "Credentials");
        Args.notNull(httpRequest, "HTTP request");
        StringBuilder $r4 = new StringBuilder();
        $r4.append(credentials.getUserPrincipal().getName());
        $r4.append(":");
        $r4.append(credentials.getPassword() == null ? "null" : credentials.getPassword());
        byte[] $r8 = Packet.encode(EncodingUtils.getBytes($r4.toString(), getCredentialsCharset(httpRequest)), 2);
        CharArrayBuffer $r9 = new CharArrayBuffer(32);
        $r9.append(isProxy() ? "Proxy-Authorization" : "Authorization");
        $r9.append(": Basic ");
        $r9.append($r8, 0, $r8.length);
        return new BufferedHeader($r9);
    }

    public String getSchemeName() {
        return "basic";
    }

    public boolean isComplete() {
        return this.complete;
    }

    public boolean isConnectionBased() {
        return false;
    }

    public void processChallenge(Header header) {
        super.processChallenge(header);
        this.complete = true;
    }

    public String toString() {
        return "BASIC [complete=" + this.complete + "]";
    }
}
