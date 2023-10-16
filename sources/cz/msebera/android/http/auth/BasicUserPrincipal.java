package cz.msebera.android.http.auth;

import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.LangUtils;
import java.io.Serializable;
import java.security.Principal;

public final class BasicUserPrincipal implements Principal, Serializable {
    private final String username;

    public BasicUserPrincipal(String str) {
        Args.notNull(str, "User name");
        this.username = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof BasicUserPrincipal) && LangUtils.equals((Object) this.username, (Object) ((BasicUserPrincipal) obj).username);
    }

    public String getName() {
        return this.username;
    }

    public int hashCode() {
        return LangUtils.hashCode(17, (Object) this.username);
    }

    public String toString() {
        return "[principal: " + this.username + "]";
    }
}
