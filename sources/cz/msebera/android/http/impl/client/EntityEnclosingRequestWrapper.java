package cz.msebera.android.http.impl.client;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpEntity;
import cz.msebera.android.http.HttpEntityEnclosingRequest;
import cz.msebera.android.http.entity.HttpEntityWrapper;
import java.io.InputStream;
import java.io.OutputStream;

@Deprecated
public class EntityEnclosingRequestWrapper extends RequestWrapper implements HttpEntityEnclosingRequest {
    /* access modifiers changed from: private */
    public boolean consumed;
    private HttpEntity entity;

    class EntityWrapper extends HttpEntityWrapper {
        EntityWrapper(HttpEntity httpEntity) {
            super(httpEntity);
        }

        public void consumeContent() {
            boolean unused = EntityEnclosingRequestWrapper.this.consumed = true;
            super.consumeContent();
        }

        public InputStream getContent() {
            boolean unused = EntityEnclosingRequestWrapper.this.consumed = true;
            return super.getContent();
        }

        public void writeTo(OutputStream outputStream) {
            boolean unused = EntityEnclosingRequestWrapper.this.consumed = true;
            super.writeTo(outputStream);
        }
    }

    public EntityEnclosingRequestWrapper(HttpEntityEnclosingRequest httpEntityEnclosingRequest) {
        super(httpEntityEnclosingRequest);
        setEntity(httpEntityEnclosingRequest.getEntity());
    }

    public boolean expectContinue() {
        Header $r2 = getFirstHeader("Expect");
        return $r2 != null && "100-continue".equalsIgnoreCase($r2.getValue());
    }

    public HttpEntity getEntity() {
        return this.entity;
    }

    public boolean isRepeatable() {
        HttpEntity $r1 = this.entity;
        return $r1 == null || $r1.isRepeatable() || !this.consumed;
    }

    public void setEntity(HttpEntity httpEntity) {
        this.entity = httpEntity != null ? new EntityWrapper(httpEntity) : null;
        this.consumed = false;
    }
}
