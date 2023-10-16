package cz.msebera.android.http.impl.auth;

import cz.msebera.android.http.Consts;
import cz.msebera.android.http.HeaderElement;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.message.BasicHeaderValueParser;
import cz.msebera.android.http.mime.CharArrayBuffer;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public abstract class RFC2617Scheme extends AuthSchemeBase implements Serializable {
    private transient Charset credentialsCharset;
    private final Map<String, String> params = new HashMap();

    public RFC2617Scheme(Charset $r2) {
        this.credentialsCharset = $r2 == null ? Consts.ASCII : $r2;
    }

    /* access modifiers changed from: package-private */
    public String getCredentialsCharset(HttpRequest httpRequest) {
        String $r4 = (String) httpRequest.getParams().getParameter("http.auth.credential-charset");
        return $r4 == null ? getCredentialsCharset().name() : $r4;
    }

    public Charset getCredentialsCharset() {
        Charset $r1 = this.credentialsCharset;
        return $r1 != null ? $r1 : Consts.ASCII;
    }

    public String getParameter(String str) {
        if (str == null) {
            return null;
        }
        return this.params.get(str.toLowerCase(Locale.ROOT));
    }

    /* access modifiers changed from: protected */
    public Map getParameters() {
        return this.params;
    }

    public String getRealm() {
        return getParameter("realm");
    }

    /* access modifiers changed from: protected */
    public void parseChallenge(CharArrayBuffer charArrayBuffer, int i, int i2) {
        HeaderElement[] $r5 = BasicHeaderValueParser.INSTANCE.parseElements(charArrayBuffer, new cz.msebera.android.http.message.CharArrayBuffer(i, charArrayBuffer.length()));
        this.params.clear();
        for (HeaderElement $r7 : $r5) {
            this.params.put($r7.getName().toLowerCase(Locale.ROOT), $r7.getValue());
        }
    }
}
