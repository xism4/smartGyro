package cz.msebera.android.http.client;

import cz.msebera.android.http.auth.AuthScope;
import cz.msebera.android.http.auth.Credentials;

public interface CredentialsProvider {
    Credentials getCredentials(AuthScope authScope);
}
