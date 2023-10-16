package cz.msebera.android.http.message;

import cz.msebera.android.http.ProtocolVersion;
import cz.msebera.android.http.RequestLine;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;
import java.io.Serializable;

public class BasicRequestLine implements RequestLine, Cloneable, Serializable {
    private final String method;
    private final ProtocolVersion protoversion;
    private final String uri;

    public BasicRequestLine(String $r1, String $r12, ProtocolVersion $r3) {
        Args.notNull($r1, "Method");
        this.method = $r1;
        Args.notNull($r12, "URI");
        this.uri = $r12;
        Args.notNull($r3, "Version");
        this.protoversion = $r3;
    }

    public Object clone() {
        return super.clone();
    }

    public String getMethod() {
        return this.method;
    }

    public ProtocolVersion getProtocolVersion() {
        return this.protoversion;
    }

    public String getUri() {
        return this.uri;
    }

    public String toString() {
        return BasicLineFormatter.INSTANCE.formatRequestLine((CharArrayBuffer) null, this).toString();
    }
}
