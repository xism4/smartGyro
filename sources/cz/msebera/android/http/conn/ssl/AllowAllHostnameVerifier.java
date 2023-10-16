package cz.msebera.android.http.conn.ssl;

@Deprecated
public class AllowAllHostnameVerifier extends AbstractVerifier {
    public static final AllowAllHostnameVerifier INSTANCE = new AllowAllHostnameVerifier();

    public final String toString() {
        return "STRICT";
    }

    public final void verify(String str, String[] strArr, String[] strArr2) {
        verify(str, strArr, strArr2, true);
    }
}
