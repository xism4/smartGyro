package cz.msebera.android.http.client.methods;

import cz.msebera.android.http.HttpHost;
import java.net.InetAddress;
import java.util.Collection;

public class RequestConfig implements Cloneable {
    public static final RequestConfig DEFAULT = new Builder().build();
    private final int N;
    private final boolean authenticationEnabled;
    private final boolean c;
    private final InetAddress circularRedirectsAllowed;
    private final int connectTimeout;
    private final boolean connectionRequestTimeout;
    private final int cookieSpec;
    private final boolean expectContinueEnabled;
    private final Collection<String> localAddress;
    private final String maxRedirects;
    private final Collection<String> proxyPreferredAuthSchemes;
    private final boolean redirectsEnabled;
    private final HttpHost relativeRedirectsAllowed;
    private final boolean socketTimeout;
    private final int staleConnectionCheckEnabled;
    private final boolean targetPreferredAuthSchemes;

    public class Builder {
        private boolean authenticationEnabled = true;
        private boolean circularRedirectsAllowed;
        private int connectTimeout = -1;
        private int connectionRequestTimeout = -1;
        private String cookieSpec;
        private boolean expectContinueEnabled;
        private InetAddress localAddress;
        private int maxRedirects = 50;
        private HttpHost proxy;
        private Collection<String> proxyPreferredAuthSchemes;
        private boolean redirectsEnabled = true;
        private boolean relativeRedirectsAllowed = true;
        private int socketTimeout = -1;
        private boolean staleConnectionCheckEnabled = false;
        private Collection<String> targetPreferredAuthSchemes;
        private boolean textAlignment = true;

        Builder() {
        }

        public RequestConfig build() {
            return new RequestConfig(this.expectContinueEnabled, this.proxy, this.localAddress, this.staleConnectionCheckEnabled, this.cookieSpec, this.redirectsEnabled, this.relativeRedirectsAllowed, this.circularRedirectsAllowed, this.maxRedirects, this.authenticationEnabled, this.targetPreferredAuthSchemes, this.proxyPreferredAuthSchemes, this.connectionRequestTimeout, this.connectTimeout, this.socketTimeout, this.textAlignment);
        }

        public Builder setAuthenticationEnabled(boolean z) {
            this.authenticationEnabled = z;
            return this;
        }

        public Builder setCircularRedirectsAllowed(boolean z) {
            this.circularRedirectsAllowed = z;
            return this;
        }

        public Builder setConnectTimeout(int i) {
            this.connectTimeout = i;
            return this;
        }

        public Builder setConnectionRequestTimeout(int i) {
            this.connectionRequestTimeout = i;
            return this;
        }

        public Builder setCookieSpec(String str) {
            this.cookieSpec = str;
            return this;
        }

        public Builder setExpectContinueEnabled(boolean z) {
            this.expectContinueEnabled = z;
            return this;
        }

        public Builder setLocalAddress(InetAddress inetAddress) {
            this.localAddress = inetAddress;
            return this;
        }

        public Builder setMaxRedirects(int i) {
            this.maxRedirects = i;
            return this;
        }

        public Builder setProxy(HttpHost httpHost) {
            this.proxy = httpHost;
            return this;
        }

        public Builder setProxyPreferredAuthSchemes(Collection collection) {
            this.proxyPreferredAuthSchemes = collection;
            return this;
        }

        public Builder setRedirectsEnabled(boolean z) {
            this.redirectsEnabled = z;
            return this;
        }

        public Builder setRelativeRedirectsAllowed(boolean z) {
            this.relativeRedirectsAllowed = z;
            return this;
        }

        public Builder setSocketTimeout(int i) {
            this.socketTimeout = i;
            return this;
        }

        public Builder setStaleConnectionCheckEnabled(boolean z) {
            this.staleConnectionCheckEnabled = z;
            return this;
        }

        public Builder setTargetPreferredAuthSchemes(Collection collection) {
            this.targetPreferredAuthSchemes = collection;
            return this;
        }
    }

    RequestConfig(boolean z, HttpHost httpHost, InetAddress inetAddress, boolean z2, String str, boolean z3, boolean z4, boolean z5, int i, boolean z6, Collection collection, Collection collection2, int i2, int i3, int i4, boolean z7) {
        this.redirectsEnabled = z;
        this.relativeRedirectsAllowed = httpHost;
        this.circularRedirectsAllowed = inetAddress;
        this.socketTimeout = z2;
        this.maxRedirects = str;
        this.authenticationEnabled = z3;
        this.targetPreferredAuthSchemes = z4;
        this.connectionRequestTimeout = z5;
        this.connectTimeout = i;
        this.expectContinueEnabled = z6;
        this.proxyPreferredAuthSchemes = collection;
        this.localAddress = collection2;
        this.staleConnectionCheckEnabled = i2;
        this.cookieSpec = i3;
        this.N = i4;
        this.c = z7;
    }

    public static Builder custom() {
        return new Builder();
    }

    /* access modifiers changed from: protected */
    public RequestConfig clone() {
        return (RequestConfig) super.clone();
    }

    public boolean getConnectionRequestTimeout() {
        return this.connectionRequestTimeout;
    }

    public String getMaxRedirects() {
        return this.maxRedirects;
    }

    public Collection getProxyPreferredAuthSchemes() {
        return this.proxyPreferredAuthSchemes;
    }

    public Collection getTargetPreferredAuthSchemes() {
        return this.localAddress;
    }

    public boolean isCircularRedirectsAllowed() {
        return this.targetPreferredAuthSchemes;
    }

    public String toString() {
        return "[" + "expectContinueEnabled=" + this.redirectsEnabled + ", proxy=" + this.relativeRedirectsAllowed + ", localAddress=" + this.circularRedirectsAllowed + ", cookieSpec=" + this.maxRedirects + ", redirectsEnabled=" + this.authenticationEnabled + ", relativeRedirectsAllowed=" + this.targetPreferredAuthSchemes + ", maxRedirects=" + this.connectTimeout + ", circularRedirectsAllowed=" + this.connectionRequestTimeout + ", authenticationEnabled=" + this.expectContinueEnabled + ", targetPreferredAuthSchemes=" + this.proxyPreferredAuthSchemes + ", proxyPreferredAuthSchemes=" + this.localAddress + ", connectionRequestTimeout=" + this.staleConnectionCheckEnabled + ", connectTimeout=" + this.cookieSpec + ", socketTimeout=" + this.N + ", decompressionEnabled=" + this.c + "]";
    }
}
