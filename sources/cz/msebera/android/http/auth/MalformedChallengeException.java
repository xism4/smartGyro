package cz.msebera.android.http.auth;

import cz.msebera.android.http.ProtocolException;

public class MalformedChallengeException extends ProtocolException {
    public MalformedChallengeException(String str) {
        super(str);
    }
}
