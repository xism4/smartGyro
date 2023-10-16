package cz.msebera.android.http.auth;

import cz.msebera.android.http.mime.LangUtils;
import java.io.Serializable;
import java.security.Principal;

public class NTCredentials implements Credentials, Serializable {
    private final String password;
    private final NTUserPrincipal principal;
    private final String workstation;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NTCredentials)) {
            return false;
        }
        NTCredentials $r3 = (NTCredentials) obj;
        return LangUtils.equals((Object) this.principal, (Object) $r3.principal) && LangUtils.equals((Object) this.workstation, (Object) $r3.workstation);
    }

    public String getDomain() {
        this.principal.getDomain();
        throw new NullPointerException("Null throw statement replaced by Soot");
    }

    public String getPassword() {
        return this.password;
    }

    public String getUserName() {
        this.principal.getUsername();
        throw new NullPointerException("Null throw statement replaced by Soot");
    }

    public Principal getUserPrincipal() {
        return this.principal;
    }

    public int hashCode() {
        return LangUtils.hashCode(LangUtils.hashCode(17, (Object) this.principal), (Object) this.workstation);
    }

    public String toString() {
        return "[principal: " + this.principal + "][workstation: " + this.workstation + "]";
    }
}
