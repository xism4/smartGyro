package cz.msebera.android.http.impl.cookie;

@Deprecated
public class OCFile extends BestMatchSpec {
    public OCFile() {
        this((String[]) null, false);
    }

    public OCFile(String[] strArr, boolean z) {
        super(strArr, z);
    }

    public String toString() {
        return "best-match";
    }
}
