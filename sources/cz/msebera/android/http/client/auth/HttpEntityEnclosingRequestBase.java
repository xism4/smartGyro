package cz.msebera.android.http.client.auth;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpEntity;
import cz.msebera.android.http.HttpEntityEnclosingRequest;
import cz.msebera.android.http.client.ssl.CloneUtils;

public abstract class HttpEntityEnclosingRequestBase extends HttpRequestBase implements HttpEntityEnclosingRequest {
    private HttpEntity entity;

    public Object clone() {
        HttpEntityEnclosingRequestBase $r2 = (HttpEntityEnclosingRequestBase) super.clone();
        HttpEntity $r3 = this.entity;
        if ($r3 != null) {
            $r2.entity = (HttpEntity) CloneUtils.cloneObject($r3);
        }
        return $r2;
    }

    public boolean expectContinue() {
        Header $r2 = getFirstHeader("Expect");
        return $r2 != null && "100-continue".equalsIgnoreCase($r2.getValue());
    }

    public HttpEntity getEntity() {
        return this.entity;
    }

    public void setEntity(HttpEntity httpEntity) {
        this.entity = httpEntity;
    }
}
