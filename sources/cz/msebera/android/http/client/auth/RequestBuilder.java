package cz.msebera.android.http.client.auth;

import c.a.a.a.z;
import cz.msebera.android.http.Consts;
import cz.msebera.android.http.HttpEntity;
import cz.msebera.android.http.HttpEntityEnclosingRequest;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.ProtocolVersion;
import cz.msebera.android.http.client.methods.RequestConfig;
import cz.msebera.android.http.client.ssl.URIBuilder;
import cz.msebera.android.http.client.ssl.URLEncodedUtils;
import cz.msebera.android.http.client.utils.AddFieldScript;
import cz.msebera.android.http.entity.ContentType;
import cz.msebera.android.http.execchain.HTTP;
import cz.msebera.android.http.message.HeaderGroup;
import cz.msebera.android.http.mime.Args;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.List;

public class RequestBuilder {
    private Charset charset;
    private RequestConfig config;
    private HttpEntity entity;
    private HeaderGroup headergroup;
    private String method;
    private List<z> parameters;
    private URI uri;
    private ProtocolVersion version;

    class InternalEntityEclosingRequest extends HttpEntityEnclosingRequestBase {
        private final String method;

        InternalEntityEclosingRequest(String str) {
            this.method = str;
        }

        public String getMethod() {
            return this.method;
        }
    }

    class InternalRequest extends HttpRequestBase {
        private final String method;

        InternalRequest(String str) {
            this.method = str;
        }

        public String getMethod() {
            return this.method;
        }
    }

    RequestBuilder() {
        this((String) null);
    }

    RequestBuilder(String str) {
        this.charset = Consts.UTF_8;
        this.method = str;
    }

    public static RequestBuilder copy(HttpRequest httpRequest) {
        Args.notNull(httpRequest, "HTTP request");
        RequestBuilder $r1 = new RequestBuilder();
        $r1.doCopy(httpRequest);
        return $r1;
    }

    private RequestBuilder doCopy(HttpRequest httpRequest) {
        if (httpRequest == null) {
            return this;
        }
        this.method = httpRequest.getRequestLine().getMethod();
        this.version = httpRequest.getRequestLine().getProtocolVersion();
        if (this.headergroup == null) {
            this.headergroup = new HeaderGroup();
        }
        this.headergroup.clear();
        this.headergroup.setHeaders(httpRequest.getAllHeaders());
        this.parameters = null;
        this.entity = null;
        if (httpRequest instanceof HttpEntityEnclosingRequest) {
            HttpEntity $r10 = ((HttpEntityEnclosingRequest) httpRequest).getEntity();
            ContentType $r11 = ContentType.get($r10);
            if ($r11 == null || !$r11.getMimeType().equals(ContentType.APPLICATION_FORM_URLENCODED.getMimeType())) {
                this.entity = $r10;
            } else {
                try {
                    List $r13 = URLEncodedUtils.parse($r10);
                    if (!$r13.isEmpty()) {
                        this.parameters = $r13;
                    }
                } catch (IOException e) {
                }
            }
        }
        URI $r15 = httpRequest instanceof HttpUriRequest ? ((HttpUriRequest) httpRequest).getURI() : URI.create(httpRequest.getRequestLine().getUri());
        URIBuilder uRIBuilder = new URIBuilder($r15);
        if (this.parameters == null) {
            List $r132 = uRIBuilder.getQueryParams();
            if (!$r132.isEmpty()) {
                this.parameters = $r132;
                uRIBuilder.clearParameters();
            } else {
                this.parameters = null;
            }
        }
        try {
            this.uri = uRIBuilder.build();
        } catch (URISyntaxException e2) {
            this.uri = $r15;
        }
        if (httpRequest instanceof Configurable) {
            this.config = ((Configurable) httpRequest).getConfig();
            return this;
        }
        this.config = null;
        return this;
    }

    public HttpUriRequest build() {
        HttpRequestBase $r9;
        URIBuilder r16;
        URI $r2 = this.uri;
        if ($r2 == null) {
            $r2 = URI.create("/");
        }
        HttpEntity $r3 = this.entity;
        List $r4 = this.parameters;
        if ($r4 != null && !$r4.isEmpty()) {
            if ($r3 != null || (!"POST".equalsIgnoreCase(this.method) && !"PUT".equalsIgnoreCase(this.method))) {
                URIBuilder $r8 = r16;
                try {
                    r16 = new URIBuilder($r2);
                    $r8.setCharset(this.charset);
                    $r8.addParameters(this.parameters);
                    $r2 = $r8.build();
                } catch (URISyntaxException e) {
                }
            } else {
                $r3 = r15;
                AddFieldScript r15 = new AddFieldScript(this.parameters, HTTP.DEF_CONTENT_CHARSET);
            }
        }
        if ($r3 == null) {
            $r9 = r17;
            HttpRequestBase r17 = new InternalRequest(this.method);
        } else {
            InternalEntityEclosingRequest $r10 = r18;
            InternalEntityEclosingRequest r18 = new InternalEntityEclosingRequest(this.method);
            $r10.setEntity($r3);
            $r9 = $r10;
        }
        ProtocolVersion $r11 = this.version;
        ProtocolVersion protocolVersion = $r11;
        $r9.setProtocolVersion($r11);
        $r9.setURI($r2);
        HeaderGroup $r12 = this.headergroup;
        if ($r12 != null) {
            $r9.setHeaders($r12.getAllHeaders());
        }
        RequestConfig $r14 = this.config;
        RequestConfig requestConfig = $r14;
        $r9.setConfig($r14);
        return $r9;
    }

    public RequestBuilder setUri(URI uri2) {
        this.uri = uri2;
        return this;
    }
}
