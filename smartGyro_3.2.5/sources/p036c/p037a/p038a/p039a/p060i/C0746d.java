package p036c.p037a.p038a.p039a.p060i;

import java.util.Locale;
import p036c.p037a.p038a.p039a.C0489E;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.i.d */
public class C0746d implements C0489E {

    /* renamed from: a */
    public static final C0746d f2233a = new C0746d();

    /* renamed from: b */
    private static final String[][] f2234b = {null, new String[3], new String[8], new String[8], new String[25], new String[8]};

    static {
        m2739a(200, "OK");
        m2739a(201, "Created");
        m2739a(202, "Accepted");
        m2739a(204, "No Content");
        m2739a(301, "Moved Permanently");
        m2739a(302, "Moved Temporarily");
        m2739a(304, "Not Modified");
        m2739a(400, "Bad Request");
        m2739a(401, "Unauthorized");
        m2739a(403, "Forbidden");
        m2739a(404, "Not Found");
        m2739a(500, "Internal Server Error");
        m2739a(501, "Not Implemented");
        m2739a(502, "Bad Gateway");
        m2739a(503, "Service Unavailable");
        m2739a(100, "Continue");
        m2739a(307, "Temporary Redirect");
        m2739a(405, "Method Not Allowed");
        m2739a(409, "Conflict");
        m2739a(412, "Precondition Failed");
        m2739a(413, "Request Too Long");
        m2739a(414, "Request-URI Too Long");
        m2739a(415, "Unsupported Media Type");
        m2739a(300, "Multiple Choices");
        m2739a(303, "See Other");
        m2739a(305, "Use Proxy");
        m2739a(402, "Payment Required");
        m2739a(406, "Not Acceptable");
        m2739a(407, "Proxy Authentication Required");
        m2739a(408, "Request Timeout");
        m2739a(101, "Switching Protocols");
        m2739a(203, "Non Authoritative Information");
        m2739a(205, "Reset Content");
        m2739a(206, "Partial Content");
        m2739a(504, "Gateway Timeout");
        m2739a(505, "Http Version Not Supported");
        m2739a(410, "Gone");
        m2739a(411, "Length Required");
        m2739a(416, "Requested Range Not Satisfiable");
        m2739a(417, "Expectation Failed");
        m2739a(102, "Processing");
        m2739a(207, "Multi-Status");
        m2739a(422, "Unprocessable Entity");
        m2739a(419, "Insufficient Space On Resource");
        m2739a(420, "Method Failure");
        m2739a(423, "Locked");
        m2739a(507, "Insufficient Storage");
        m2739a(424, "Failed Dependency");
    }

    protected C0746d() {
    }

    /* renamed from: a */
    private static void m2739a(int i, String str) {
        int i2 = i / 100;
        f2234b[i2][i - (i2 * 100)] = str;
    }

    public String getReason(int i, Locale locale) {
        boolean z = i >= 100 && i < 600;
        C0870a.m3044a(z, "Unknown category for status code " + i);
        int i2 = i / 100;
        int i3 = i - (i2 * 100);
        String[][] strArr = f2234b;
        if (strArr[i2].length > i3) {
            return strArr[i2][i3];
        }
        return null;
    }
}
