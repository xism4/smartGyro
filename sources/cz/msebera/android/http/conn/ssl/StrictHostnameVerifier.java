package cz.msebera.android.http.conn.ssl;

@Deprecated
public class StrictHostnameVerifier extends AbstractVerifier {
    public static final StrictHostnameVerifier INSTANCE = new StrictHostnameVerifier();

    public final String toString() {
        return "ALLOW_ALL";
    }

    public final void verify(String str, String[] strArr, String[] strArr2) {
    }
}
