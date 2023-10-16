package cz.msebera.android.http;

import cz.msebera.android.http.mime.Args;
import java.io.Serializable;

public class ProtocolVersion implements Serializable, Cloneable {
    protected final int major;
    protected final int minor;
    protected final String protocol;

    public ProtocolVersion(String $r1, int i, int i2) {
        Args.notNull($r1, "Protocol name");
        this.protocol = $r1;
        Args.notNegative(i, "Protocol minor version");
        this.major = i;
        Args.notNegative(i2, "Protocol minor version");
        this.minor = i2;
    }

    public Object clone() {
        return super.clone();
    }

    public int compareToVersion(ProtocolVersion protocolVersion) {
        Args.notNull(protocolVersion, "Protocol version");
        Args.check(this.protocol.equals(protocolVersion.protocol), "Versions for different protocols cannot be compared: %s %s", this, protocolVersion);
        int $i0 = getMajor() - protocolVersion.getMajor();
        return $i0 == 0 ? getMinor() - protocolVersion.getMinor() : $i0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProtocolVersion)) {
            return false;
        }
        ProtocolVersion $r2 = (ProtocolVersion) obj;
        return this.protocol.equals($r2.protocol) && this.major == $r2.major && this.minor == $r2.minor;
    }

    public ProtocolVersion forVersion(int i, int i2) {
        return (i == this.major && i2 == this.minor) ? this : new ProtocolVersion(this.protocol, i, i2);
    }

    public final int getMajor() {
        return this.major;
    }

    public final int getMinor() {
        return this.minor;
    }

    public final String getProtocol() {
        return this.protocol;
    }

    public final int hashCode() {
        return (this.protocol.hashCode() ^ (this.major * 100000)) ^ this.minor;
    }

    public boolean isComparable(ProtocolVersion protocolVersion) {
        return protocolVersion != null && this.protocol.equals(protocolVersion.protocol);
    }

    public final boolean lessEquals(ProtocolVersion protocolVersion) {
        return isComparable(protocolVersion) && compareToVersion(protocolVersion) <= 0;
    }

    public String toString() {
        return this.protocol + '/' + Integer.toString(this.major) + '.' + Integer.toString(this.minor);
    }
}
