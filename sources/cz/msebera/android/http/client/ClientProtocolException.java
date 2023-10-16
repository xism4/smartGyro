package cz.msebera.android.http.client;

import java.io.IOException;

public class ClientProtocolException extends IOException {
    public ClientProtocolException(String str) {
        super(str);
    }

    public ClientProtocolException(Throwable th) {
        initCause(th);
    }
}
