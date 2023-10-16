package cz.msebera.android.http.auth;

import java.security.Principal;

public interface Credentials {
    String getPassword();

    Principal getUserPrincipal();
}
