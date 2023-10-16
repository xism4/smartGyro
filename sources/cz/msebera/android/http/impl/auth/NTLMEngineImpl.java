package cz.msebera.android.http.impl.auth;

import cz.msebera.android.http.Consts;
import cz.msebera.android.http.mime.LimitedQueue;
import java.nio.charset.Charset;
import java.security.SecureRandom;

final class NTLMEngineImpl implements NTLMEngine {
    private static final RemoteAcceptThread $VALUES = new RemoteAcceptThread();
    private static final Charset DEFAULT_CHARSET = Consts.ASCII;
    private static final SecureRandom RND_GEN;
    private static final byte[] SIGNATURE;
    private static final Charset UTF_8 = LimitedQueue.lookup("UnicodeLittleUnmarked");

    static {
        SecureRandom $r2;
        try {
            $r2 = SecureRandom.getInstance("SHA1PRNG");
        } catch (Exception e) {
            $r2 = null;
        }
        RND_GEN = $r2;
        byte[] $r4 = "NTLMSSP".getBytes(Consts.ASCII);
        SIGNATURE = new byte[($r4.length + 1)];
        System.arraycopy($r4, 0, SIGNATURE, 0, $r4.length);
        SIGNATURE[$r4.length] = 0;
    }

    NTLMEngineImpl() {
    }
}
