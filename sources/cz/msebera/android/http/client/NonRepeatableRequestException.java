package cz.msebera.android.http.client;

import cz.msebera.android.http.ProtocolException;

public class NonRepeatableRequestException extends ProtocolException {
    public NonRepeatableRequestException(String str) {
        super(str);
    }

    public NonRepeatableRequestException(String str, Throwable th) {
        super(str, th);
    }
}
