package cz.msebera.android.http.entity;

import cz.msebera.android.http.Consts;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HeaderElement;
import cz.msebera.android.http.HttpEntity;
import cz.msebera.android.http.NameValuePair;
import cz.msebera.android.http.message.BasicHeaderValueFormatter;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;
import cz.msebera.android.http.mime.TextUtils;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Locale;

public final class ContentType implements Serializable {
    public static final ContentType APPLICATION_ATOM_XML = create("application/atom+xml", Consts.ISO_8859_1);
    public static final ContentType APPLICATION_FORM_URLENCODED = create("application/x-www-form-urlencoded", Consts.ISO_8859_1);
    public static final ContentType APPLICATION_JSON = create("application/json", Consts.UTF_8);
    public static final ContentType APPLICATION_OCTET_STREAM = create("application/octet-stream", (Charset) null);
    public static final ContentType APPLICATION_SVG_XML = create("application/svg+xml", Consts.ISO_8859_1);
    public static final ContentType APPLICATION_XHTML_XML = create("application/xhtml+xml", Consts.ISO_8859_1);
    public static final ContentType APPLICATION_XML = create("application/xml", Consts.ISO_8859_1);
    public static final ContentType DEFAULT_BINARY = APPLICATION_OCTET_STREAM;
    public static final ContentType DEFAULT_TEXT = TEXT_PLAIN;
    public static final ContentType MULTIPART_FORM_DATA = create("multipart/form-data", Consts.ISO_8859_1);
    public static final ContentType TEXT_HTML = create("text/html", Consts.ISO_8859_1);
    public static final ContentType TEXT_PLAIN = create("text/plain", Consts.ISO_8859_1);
    public static final ContentType TEXT_XML = create("text/xml", Consts.ISO_8859_1);
    public static final ContentType WILDCARD = create("*/*", (Charset) null);
    private final Charset charset;
    private final String mimeType;
    private final NameValuePair[] params;

    ContentType(String str, Charset charset2) {
        this.mimeType = str;
        this.charset = charset2;
        this.params = null;
    }

    ContentType(String str, Charset charset2, NameValuePair[] nameValuePairArr) {
        this.mimeType = str;
        this.charset = charset2;
        this.params = nameValuePairArr;
    }

    private static ContentType create(HeaderElement headerElement, boolean z) {
        return create(headerElement.getName(), headerElement.getParameters(), z);
    }

    public static ContentType create(String $r1, Charset charset2) {
        Args.notBlank($r1, "MIME type");
        String $r12 = $r1.toLowerCase(Locale.ROOT);
        Args.check(valid($r12), "MIME type may not contain reserved characters");
        return new ContentType($r12, charset2);
    }

    private static ContentType create(String str, NameValuePair[] $r2, boolean z) {
        Charset $r4;
        int $i0 = $r2.length;
        int $i1 = 0;
        while (true) {
            if ($i1 >= $i0) {
                break;
            }
            NameValuePair $r1 = $r2[$i1];
            if ($r1.getName().equalsIgnoreCase("charset")) {
                String $r3 = $r1.getValue();
                if (!TextUtils.isBlank($r3)) {
                    try {
                        $r4 = Charset.forName($r3);
                    } catch (UnsupportedCharsetException $r5) {
                        if (z) {
                            throw $r5;
                        }
                    }
                }
            } else {
                $i1++;
            }
        }
        $r4 = null;
        if ($r2 == null || $r2.length <= 0) {
            $r2 = null;
        }
        return new ContentType(str, $r4, $r2);
    }

    public static ContentType get(HttpEntity httpEntity) {
        Header $r1;
        if (httpEntity == null || ($r1 = httpEntity.getContentType()) == null) {
            return null;
        }
        HeaderElement[] $r2 = $r1.getElements();
        if ($r2.length > 0) {
            return create($r2[0], true);
        }
        return null;
    }

    private static boolean valid(String str) {
        for (int $i0 = 0; $i0 < str.length(); $i0++) {
            char $c2 = str.charAt($i0);
            if ($c2 == '\"' || $c2 == ',' || $c2 == ';') {
                return false;
            }
        }
        return true;
    }

    public Charset getCharset() {
        return this.charset;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public String toString() {
        CharArrayBuffer $r2 = new CharArrayBuffer(64);
        $r2.append(this.mimeType);
        if (this.params != null) {
            $r2.append("; ");
            BasicHeaderValueFormatter.INSTANCE.formatParameters($r2, this.params, false);
        } else if (this.charset != null) {
            $r2.append("; charset=");
            $r2.append(this.charset.name());
        }
        return $r2.toString();
    }
}
