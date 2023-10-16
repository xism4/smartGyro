package cz.msebera.android.http.mime;

import cz.msebera.android.http.HttpEntity;
import java.io.InputStream;

public final class EntityUtils {
    public static void consume(HttpEntity httpEntity) {
        InputStream $r1;
        if (httpEntity != null && httpEntity.isStreaming() && ($r1 = httpEntity.getContent()) != null) {
            $r1.close();
        }
    }

    public static byte[] toByteArray(HttpEntity httpEntity) {
        Args.notNull(httpEntity, "Entity");
        InputStream $r1 = httpEntity.getContent();
        if ($r1 == null) {
            return null;
        }
        try {
            Args.check(httpEntity.getContentLength() <= 2147483647L, "HTTP entity too large to be buffered in memory");
            int $i2 = (int) httpEntity.getContentLength();
            if ($i2 < 0) {
                $i2 = 4096;
            }
            ByteArrayBuffer $r2 = new ByteArrayBuffer($i2);
            byte[] $r3 = new byte[4096];
            while (true) {
                int $i22 = $r1.read($r3);
                if ($i22 == -1) {
                    return $r2.toByteArray();
                }
                $r2.append($r3, 0, $i22);
            }
        } finally {
            $r1.close();
        }
    }
}
