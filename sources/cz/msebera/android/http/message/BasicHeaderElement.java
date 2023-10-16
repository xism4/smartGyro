package cz.msebera.android.http.message;

import cz.msebera.android.http.HeaderElement;
import cz.msebera.android.http.NameValuePair;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.LangUtils;

public class BasicHeaderElement implements HeaderElement, Cloneable {
    private final String name;
    private final NameValuePair[] parameters;
    private final String value;

    public BasicHeaderElement(String str, String str2) {
        this(str, str2, (NameValuePair[]) null);
    }

    public BasicHeaderElement(String $r3, String str, NameValuePair[] nameValuePairArr) {
        Args.notNull($r3, "Name");
        this.name = $r3;
        this.value = str;
        if (nameValuePairArr != null) {
            this.parameters = nameValuePairArr;
        } else {
            this.parameters = new NameValuePair[0];
        }
    }

    public Object clone() {
        return super.clone();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HeaderElement)) {
            return false;
        }
        BasicHeaderElement $r2 = (BasicHeaderElement) obj;
        return this.name.equals($r2.name) && LangUtils.equals((Object) this.value, (Object) $r2.value) && LangUtils.equals((Object[]) this.parameters, (Object[]) $r2.parameters);
    }

    public String getName() {
        return this.name;
    }

    public NameValuePair getParameter(int i) {
        return this.parameters[i];
    }

    public NameValuePair getParameterByName(String str) {
        Args.notNull(str, "Name");
        for (NameValuePair $r3 : this.parameters) {
            if ($r3.getName().equalsIgnoreCase(str)) {
                return $r3;
            }
        }
        return null;
    }

    public int getParameterCount() {
        return this.parameters.length;
    }

    public NameValuePair[] getParameters() {
        return (NameValuePair[]) this.parameters.clone();
    }

    public String getValue() {
        return this.value;
    }

    public int hashCode() {
        int $i1 = LangUtils.hashCode(LangUtils.hashCode(17, (Object) this.name), (Object) this.value);
        for (NameValuePair $r1 : this.parameters) {
            $i1 = LangUtils.hashCode($i1, (Object) $r1);
        }
        return $i1;
    }

    public String toString() {
        StringBuilder $r3 = new StringBuilder();
        $r3.append(this.name);
        if (this.value != null) {
            $r3.append("=");
            $r3.append(this.value);
        }
        for (NameValuePair $r1 : this.parameters) {
            $r3.append("; ");
            $r3.append($r1);
        }
        return $r3.toString();
    }
}
