package cz.msebera.android.http.client.ssl;

import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.TextUtils;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Stack;

public class URIUtils {
    public static HttpHost extractHost(URI uri) {
        int $i2;
        if (uri == null || !uri.isAbsolute()) {
            return null;
        }
        int $i0 = uri.getPort();
        String $r3 = uri.getHost();
        String $r4 = $r3;
        if ($r3 == null) {
            String $r32 = uri.getAuthority();
            $r4 = $r32;
            if ($r32 != null) {
                int $i1 = $r32.indexOf(64);
                if ($i1 >= 0) {
                    int $i12 = $i1 + 1;
                    $r4 = $r32.length() > $i12 ? $r32.substring($i12) : null;
                }
                if ($r4 != null && ($i2 = $r4.indexOf(58)) >= 0) {
                    int $i13 = $i2 + 1;
                    int $i3 = $i13;
                    int $i4 = 0;
                    while ($i3 < $r4.length() && Character.isDigit($r4.charAt($i3))) {
                        $i4++;
                        $i3++;
                    }
                    if ($i4 > 0) {
                        try {
                            $i0 = Integer.parseInt($r4.substring($i13, $i4 + $i13));
                        } catch (NumberFormatException e) {
                        }
                    }
                    $r4 = $r4.substring(0, $i2);
                }
            }
        }
        String $r33 = uri.getScheme();
        if (TextUtils.isBlank($r4)) {
            return null;
        }
        try {
            return new HttpHost($r4, $i0, $r33);
        } catch (IllegalArgumentException e2) {
            return null;
        }
    }

    private static URI normalizeSyntax(URI $r0) {
        if ($r0.isOpaque() || $r0.getAuthority() == null) {
            return $r0;
        }
        Args.check($r0.isAbsolute(), "Base URI must be absolute");
        String $r1 = $r0.getPath() == null ? "" : $r0.getPath();
        String[] $r2 = $r1.split("/");
        Stack $r3 = new Stack();
        for (String $r4 : $r2) {
            if (!$r4.isEmpty() && !".".equals($r4)) {
                if (!"..".equals($r4)) {
                    $r3.push($r4);
                } else if (!$r3.isEmpty()) {
                    $r3.pop();
                }
            }
        }
        StringBuilder $r6 = new StringBuilder();
        Iterator $r7 = $r3.iterator();
        while ($r7.hasNext()) {
            $r6.append('/');
            $r6.append((String) $r7.next());
        }
        if ($r1.lastIndexOf(47) == $r1.length() - 1) {
            $r6.append('/');
        }
        try {
            URI uri = new URI($r0.getScheme().toLowerCase(Locale.ROOT), $r0.getAuthority().toLowerCase(Locale.ROOT), $r6.toString(), (String) null, (String) null);
            if ($r0.getQuery() == null) {
                if ($r0.getFragment() == null) {
                    return uri;
                }
            }
            StringBuilder $r62 = new StringBuilder(uri.toASCIIString());
            if ($r0.getQuery() != null) {
                $r62.append('?');
                $r62.append($r0.getRawQuery());
            }
            if ($r0.getFragment() != null) {
                $r62.append('#');
                $r62.append($r0.getRawFragment());
            }
            return URI.create($r62.toString());
        } catch (URISyntaxException $r11) {
            throw new IllegalArgumentException($r11);
        }
    }

    public static URI resolve(URI $r0, URI $r1) {
        Args.notNull($r0, "Base URI");
        Args.notNull($r1, "Reference URI");
        String $r2 = $r1.toString();
        if ($r2.startsWith("?")) {
            return resolveReferenceStartingWithQueryString($r0, $r1);
        }
        boolean $z0 = $r2.isEmpty();
        if ($z0) {
            $r1 = URI.create("#");
        }
        URI $r02 = $r0.resolve($r1);
        URI $r12 = $r02;
        if ($z0) {
            String $r22 = $r02.toString();
            $r12 = URI.create($r22.substring(0, $r22.indexOf(35)));
        }
        return normalizeSyntax($r12);
    }

    private static URI resolveReferenceStartingWithQueryString(URI uri, URI uri2) {
        String $r2 = uri.toString();
        String $r3 = $r2;
        if ($r2.indexOf(63) > -1) {
            $r3 = $r2.substring(0, $r2.indexOf(63));
        }
        return URI.create($r3 + uri2.toString());
    }

    public static URI rewriteURI(URI uri) {
        Args.notNull(uri, "URI");
        if (uri.isOpaque()) {
            return uri;
        }
        URIBuilder $r2 = new URIBuilder(uri);
        if ($r2.getUserInfo() != null) {
            $r2.setUserInfo((String) null);
        }
        if (TextUtils.isEmpty($r2.getPath())) {
            $r2.setPath("/");
        }
        if ($r2.getHost() != null) {
            $r2.setHost($r2.getHost().toLowerCase(Locale.ROOT));
        }
        $r2.setFragment((String) null);
        return $r2.build();
    }

    public static URI rewriteURI(URI uri, HttpHost httpHost, boolean z) {
        int $i0;
        Args.notNull(uri, "URI");
        if (uri.isOpaque()) {
            return uri;
        }
        URIBuilder $r2 = new URIBuilder(uri);
        if (httpHost != null) {
            $r2.setScheme(httpHost.getSchemeName());
            $r2.setHost(httpHost.getHostName());
            $i0 = httpHost.getPort();
        } else {
            $r2.setScheme((String) null);
            $r2.setHost((String) null);
            $i0 = -1;
        }
        $r2.setPort($i0);
        if (z) {
            $r2.setFragment((String) null);
        }
        if (TextUtils.isEmpty($r2.getPath())) {
            $r2.setPath("/");
        }
        return $r2.build();
    }
}
