package cz.msebera.android.http.auth;

import java.io.Serializable;
import java.security.Principal;

public class NTUserPrincipal implements Principal, Serializable {
    public String getDomain() {
        throw new NullPointerException("Null throw statement replaced by Soot");
    }

    public String getUsername() {
        throw new NullPointerException("Null throw statement replaced by Soot");
    }
}
