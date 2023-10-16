package cz.msebera.android.http.auth;

import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.LangUtils;
import java.io.Serializable;
import java.security.Principal;

public class UsernamePasswordCredentials implements Credentials, Serializable {
    private final String password;
    private final BasicUserPrincipal principal;

    public UsernamePasswordCredentials(String $r2) {
        String $r22;
        Args.notNull($r2, "Username:password string");
        int $i0 = $r2.indexOf(58);
        if ($i0 >= 0) {
            this.principal = new BasicUserPrincipal($r2.substring(0, $i0));
            $r22 = $r2.substring($i0 + 1);
        } else {
            this.principal = new BasicUserPrincipal($r2);
            $r22 = null;
        }
        this.password = $r22;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof UsernamePasswordCredentials) && LangUtils.equals((Object) this.principal, (Object) ((UsernamePasswordCredentials) obj).principal);
    }

    public String getPassword() {
        return this.password;
    }

    public Principal getUserPrincipal() {
        return this.principal;
    }

    public int hashCode() {
        return this.principal.hashCode();
    }

    public String toString() {
        return this.principal.toString();
    }
}
