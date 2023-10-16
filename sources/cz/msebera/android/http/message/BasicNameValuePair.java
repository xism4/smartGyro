package cz.msebera.android.http.message;

import cz.msebera.android.http.NameValuePair;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.LangUtils;
import java.io.Serializable;

public class BasicNameValuePair implements NameValuePair, Cloneable, Serializable {
    private final String name;
    private final String value;

    public BasicNameValuePair(String $r2, String str) {
        Args.notNull($r2, "Name");
        this.name = $r2;
        this.value = str;
    }

    public Object clone() {
        return super.clone();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NameValuePair)) {
            return false;
        }
        BasicNameValuePair $r3 = (BasicNameValuePair) obj;
        return this.name.equals($r3.name) && LangUtils.equals((Object) this.value, (Object) $r3.value);
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

    public int hashCode() {
        return LangUtils.hashCode(LangUtils.hashCode(17, (Object) this.name), (Object) this.value);
    }

    public String toString() {
        if (this.value == null) {
            return this.name;
        }
        StringBuilder $r2 = new StringBuilder(this.name.length() + 1 + this.value.length());
        $r2.append(this.name);
        $r2.append("=");
        $r2.append(this.value);
        return $r2.toString();
    }
}
