package cz.msebera.android.http.message;

import cz.msebera.android.http.HttpEntity;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.HttpVersion;
import cz.msebera.android.http.ProtocolVersion;
import cz.msebera.android.http.ReasonPhraseCatalog;
import cz.msebera.android.http.StatusLine;
import cz.msebera.android.http.mime.Args;
import java.util.Locale;

public class BasicHttpResponse extends AbstractHttpMessage implements HttpResponse {
    private int code;
    private HttpEntity entity;
    private Locale locale;
    private final ReasonPhraseCatalog reasonCatalog;
    private String reasonPhrase;
    private StatusLine statusline;
    private ProtocolVersion ver;

    public BasicHttpResponse(StatusLine $r4, ReasonPhraseCatalog reasonPhraseCatalog, Locale locale2) {
        Args.notNull($r4, "Status line");
        this.statusline = $r4;
        this.ver = $r4.getProtocolVersion();
        this.code = $r4.getStatusCode();
        this.reasonPhrase = $r4.getReasonPhrase();
        this.reasonCatalog = reasonPhraseCatalog;
        this.locale = locale2;
    }

    public HttpEntity getEntity() {
        return this.entity;
    }

    public ProtocolVersion getProtocolVersion() {
        return this.ver;
    }

    /* access modifiers changed from: protected */
    public String getReason(int i) {
        ReasonPhraseCatalog $r1 = this.reasonCatalog;
        if ($r1 == null) {
            return null;
        }
        Locale $r2 = this.locale;
        if ($r2 == null) {
            $r2 = Locale.getDefault();
        }
        return $r1.getReason(i, $r2);
    }

    public StatusLine getStatusLine() {
        if (this.statusline == null) {
            ProtocolVersion $r4 = this.ver;
            if ($r4 == null) {
                $r4 = HttpVersion.HTTP_1_1;
            }
            int $i0 = this.code;
            String $r1 = this.reasonPhrase;
            if ($r1 == null) {
                $r1 = getReason($i0);
            }
            this.statusline = new BasicStatusLine($r4, $i0, $r1);
        }
        return this.statusline;
    }

    public void setEntity(HttpEntity httpEntity) {
        this.entity = httpEntity;
    }

    public String toString() {
        StringBuilder $r1 = new StringBuilder();
        $r1.append(getStatusLine());
        $r1.append(' ');
        $r1.append(this.headergroup);
        if (this.entity != null) {
            $r1.append(' ');
            $r1.append(this.entity);
        }
        return $r1.toString();
    }
}
