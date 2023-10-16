package cz.msebera.android.http.message;

import cz.msebera.android.http.ProtocolVersion;
import cz.msebera.android.http.StatusLine;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;
import java.io.Serializable;

public class BasicStatusLine implements StatusLine, Cloneable, Serializable {
    private final ProtocolVersion protoVersion;
    private final String reasonPhrase;
    private final int statusCode;

    public BasicStatusLine(ProtocolVersion $r2, int i, String str) {
        Args.notNull($r2, "Version");
        this.protoVersion = $r2;
        Args.notNegative(i, "Status code");
        this.statusCode = i;
        this.reasonPhrase = str;
    }

    public Object clone() {
        return super.clone();
    }

    public ProtocolVersion getProtocolVersion() {
        return this.protoVersion;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String toString() {
        return BasicLineFormatter.INSTANCE.formatStatusLine((CharArrayBuffer) null, this).toString();
    }
}
