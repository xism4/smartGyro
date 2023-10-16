package p036c.p037a.p038a.p039a.p050e.p052b;

import java.net.InetAddress;
import p036c.p037a.p038a.p039a.C0867o;

/* renamed from: c.a.a.a.e.b.e */
public interface C0590e {

    /* renamed from: c.a.a.a.e.b.e$a */
    public enum C0591a {
        PLAIN,
        LAYERED
    }

    /* renamed from: c.a.a.a.e.b.e$b */
    public enum C0592b {
        PLAIN,
        TUNNELLED
    }

    int getHopCount();

    C0867o getHopTarget(int i);

    InetAddress getLocalAddress();

    C0867o getProxyHost();

    C0867o getTargetHost();

    boolean isLayered();

    boolean isSecure();

    boolean isTunnelled();
}
