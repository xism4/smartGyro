package cz.msebera.android.http.impl.auth;

import cz.msebera.android.http.Consts;
import cz.msebera.android.http.mime.LimitedQueue;
import java.nio.charset.Charset;
import java.security.SecureRandom;

final class NTLMEngineImpl
  implements NTLMEngine
{
  private static final RemoteAcceptThread $VALUES = new RemoteAcceptThread();
  private static final Charset DEFAULT_CHARSET;
  private static final SecureRandom RND_GEN;
  private static final byte[] SIGNATURE;
  private static final Charset UTF_8 = LimitedQueue.lookup("UnicodeLittleUnmarked");
  
  static
  {
    DEFAULT_CHARSET = Consts.ASCII;
    try
    {
      localObject = SecureRandom.getInstance("SHA1PRNG");
    }
    catch (Exception localException)
    {
      Object localObject;
      for (;;) {}
    }
    localObject = null;
    RND_GEN = (SecureRandom)localObject;
    localObject = "NTLMSSP".getBytes(Consts.ASCII);
    SIGNATURE = new byte[localObject.length + 1];
    System.arraycopy(localObject, 0, SIGNATURE, 0, localObject.length);
    SIGNATURE[localObject.length] = 0;
  }
  
  NTLMEngineImpl() {}
}
