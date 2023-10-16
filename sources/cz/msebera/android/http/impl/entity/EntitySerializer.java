package cz.msebera.android.http.impl.entity;

import cz.msebera.android.http.HttpEntity;
import cz.msebera.android.http.HttpMessage;
import cz.msebera.android.http.entity.ContentLengthStrategy;
import cz.msebera.android.http.impl.io.ChunkedOutputStream;
import cz.msebera.android.http.impl.io.ContentLengthOutputStream;
import cz.msebera.android.http.impl.io.IdentityOutputStream;
import cz.msebera.android.http.io.SessionOutputBuffer;
import cz.msebera.android.http.mime.Args;
import java.io.OutputStream;

@Deprecated
public class EntitySerializer {
    private final ContentLengthStrategy lenStrategy;

    public EntitySerializer(ContentLengthStrategy $r1) {
        Args.notNull($r1, "Content length strategy");
        this.lenStrategy = $r1;
    }

    /* access modifiers changed from: protected */
    public OutputStream doSerialize(SessionOutputBuffer sessionOutputBuffer, HttpMessage httpMessage) {
        long $l0 = this.lenStrategy.determineLength(httpMessage);
        return $l0 == -2 ? new ChunkedOutputStream(sessionOutputBuffer) : $l0 == -1 ? new IdentityOutputStream(sessionOutputBuffer) : new ContentLengthOutputStream(sessionOutputBuffer, $l0);
    }

    public void serialize(SessionOutputBuffer sessionOutputBuffer, HttpMessage httpMessage, HttpEntity httpEntity) {
        Args.notNull(sessionOutputBuffer, "Session output buffer");
        Args.notNull(httpMessage, "HTTP message");
        Args.notNull(httpEntity, "HTTP entity");
        OutputStream $r3 = doSerialize(sessionOutputBuffer, httpMessage);
        httpEntity.writeTo($r3);
        $r3.close();
    }
}
