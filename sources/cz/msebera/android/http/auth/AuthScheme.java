package cz.msebera.android.http.auth;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpRequest;

public interface AuthScheme {
    Header authenticate(Credentials credentials, HttpRequest httpRequest);

    String getRealm();

    String getSchemeName();

    boolean isComplete();

    boolean isConnectionBased();

    void processChallenge(Header header);
}
