package cz.msebera.android.http.message;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HeaderElement;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;
import java.io.Serializable;

public class BasicHeader implements Header, Cloneable, Serializable {
    private final String name;
    private final String value;

    public BasicHeader(String $r2, String str) {
        Args.notNull($r2, "Name");
        this.name = $r2;
        this.value = str;
    }

    public Object clone() {
        return super.clone();
    }

    public HeaderElement[] getElements() {
        String $r2 = this.value;
        return $r2 != null ? BasicHeaderValueParser.parseElements($r2, (HeaderValueParser) null) : new HeaderElement[0];
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

    public String toString() {
        return BasicLineFormatter.INSTANCE.formatHeader((CharArrayBuffer) null, this).toString();
    }
}
