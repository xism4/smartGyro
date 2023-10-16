package p036c.p037a.p038a.p039a.p041b.p047f;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Stack;
import p036c.p037a.p038a.p039a.C0867o;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0878i;

/* renamed from: c.a.a.a.b.f.e */
public class C0556e {
    /* renamed from: a */
    public static C0867o m2252a(URI uri) {
        int indexOf;
        if (uri == null || !uri.isAbsolute()) {
            return null;
        }
        int port = uri.getPort();
        String host = uri.getHost();
        if (host == null && (host = uri.getAuthority()) != null) {
            int indexOf2 = host.indexOf(64);
            if (indexOf2 >= 0) {
                int i = indexOf2 + 1;
                host = host.length() > i ? host.substring(i) : null;
            }
            if (host != null && (indexOf = host.indexOf(58)) >= 0) {
                int i2 = indexOf + 1;
                int i3 = i2;
                int i4 = 0;
                while (i3 < host.length() && Character.isDigit(host.charAt(i3))) {
                    i4++;
                    i3++;
                }
                if (i4 > 0) {
                    try {
                        port = Integer.parseInt(host.substring(i2, i4 + i2));
                    } catch (NumberFormatException unused) {
                    }
                }
                host = host.substring(0, indexOf);
            }
        }
        String scheme = uri.getScheme();
        if (C0878i.m3088b(host)) {
            return null;
        }
        try {
            return new C0867o(host, port, scheme);
        } catch (IllegalArgumentException unused2) {
            return null;
        }
    }

    /* renamed from: a */
    public static URI m2253a(URI uri, C0867o oVar, boolean z) {
        int i;
        C0870a.m3042a(uri, "URI");
        if (uri.isOpaque()) {
            return uri;
        }
        C0555d dVar = new C0555d(uri);
        if (oVar != null) {
            dVar.mo2587d(oVar.mo3274d());
            dVar.mo2584b(oVar.mo3271b());
            i = oVar.mo3272c();
        } else {
            dVar.mo2587d((String) null);
            dVar.mo2584b((String) null);
            i = -1;
        }
        dVar.mo2578a(i);
        if (z) {
            dVar.mo2579a((String) null);
        }
        if (C0878i.m3089c(dVar.mo2588d())) {
            dVar.mo2585c("/");
        }
        return dVar.mo2582a();
    }

    /* renamed from: a */
    public static URI m2254a(URI uri, URI uri2) {
        C0870a.m3042a(uri, "Base URI");
        C0870a.m3042a(uri2, "Reference URI");
        String uri3 = uri2.toString();
        if (uri3.startsWith("?")) {
            return m2256b(uri, uri2);
        }
        boolean isEmpty = uri3.isEmpty();
        if (isEmpty) {
            uri2 = URI.create("#");
        }
        URI resolve = uri.resolve(uri2);
        if (isEmpty) {
            String uri4 = resolve.toString();
            resolve = URI.create(uri4.substring(0, uri4.indexOf(35)));
        }
        return m2257c(resolve);
    }

    /* renamed from: b */
    public static URI m2255b(URI uri) {
        C0870a.m3042a(uri, "URI");
        if (uri.isOpaque()) {
            return uri;
        }
        C0555d dVar = new C0555d(uri);
        if (dVar.mo2591f() != null) {
            dVar.mo2589e((String) null);
        }
        if (C0878i.m3089c(dVar.mo2588d())) {
            dVar.mo2585c("/");
        }
        if (dVar.mo2586c() != null) {
            dVar.mo2584b(dVar.mo2586c().toLowerCase(Locale.ROOT));
        }
        dVar.mo2579a((String) null);
        return dVar.mo2582a();
    }

    /* renamed from: b */
    private static URI m2256b(URI uri, URI uri2) {
        String uri3 = uri.toString();
        if (uri3.indexOf(63) > -1) {
            uri3 = uri3.substring(0, uri3.indexOf(63));
        }
        return URI.create(uri3 + uri2.toString());
    }

    /* renamed from: c */
    private static URI m2257c(URI uri) {
        if (uri.isOpaque() || uri.getAuthority() == null) {
            return uri;
        }
        C0870a.m3044a(uri.isAbsolute(), "Base URI must be absolute");
        String path = uri.getPath() == null ? "" : uri.getPath();
        String[] split = path.split("/");
        Stack stack = new Stack();
        for (String str : split) {
            if (!str.isEmpty() && !".".equals(str)) {
                if (!"..".equals(str)) {
                    stack.push(str);
                } else if (!stack.isEmpty()) {
                    stack.pop();
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = stack.iterator();
        while (it.hasNext()) {
            sb.append('/');
            sb.append((String) it.next());
        }
        if (path.lastIndexOf(47) == path.length() - 1) {
            sb.append('/');
        }
        try {
            URI uri2 = new URI(uri.getScheme().toLowerCase(Locale.ROOT), uri.getAuthority().toLowerCase(Locale.ROOT), sb.toString(), (String) null, (String) null);
            if (uri.getQuery() == null && uri.getFragment() == null) {
                return uri2;
            }
            StringBuilder sb2 = new StringBuilder(uri2.toASCIIString());
            if (uri.getQuery() != null) {
                sb2.append('?');
                sb2.append(uri.getRawQuery());
            }
            if (uri.getFragment() != null) {
                sb2.append('#');
                sb2.append(uri.getRawFragment());
            }
            return URI.create(sb2.toString());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
