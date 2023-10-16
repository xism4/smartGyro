package p036c.p037a.p038a.p039a.p050e.p056f;

import java.util.regex.Pattern;

/* renamed from: c.a.a.a.e.f.a */
public class C0625a {

    /* renamed from: a */
    private static final Pattern f1936a = Pattern.compile("^(([1-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){1}(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){2}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$");

    /* renamed from: b */
    private static final Pattern f1937b = Pattern.compile("^::[fF]{4}:(([1-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){1}(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){2}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$");

    /* renamed from: c */
    private static final Pattern f1938c = Pattern.compile("^[0-9a-fA-F]{1,4}(:[0-9a-fA-F]{1,4}){7}$");

    /* renamed from: d */
    private static final Pattern f1939d = Pattern.compile("^(([0-9A-Fa-f]{1,4}(:[0-9A-Fa-f]{1,4}){0,5})?)::(([0-9A-Fa-f]{1,4}(:[0-9A-Fa-f]{1,4}){0,5})?)$");

    /* renamed from: a */
    public static boolean m2378a(String str) {
        return f1936a.matcher(str).matches();
    }

    /* renamed from: b */
    public static boolean m2379b(String str) {
        return m2381d(str) || m2380c(str);
    }

    /* renamed from: c */
    public static boolean m2380c(String str) {
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (str.charAt(i2) == ':') {
                i++;
            }
        }
        return i <= 7 && f1939d.matcher(str).matches();
    }

    /* renamed from: d */
    public static boolean m2381d(String str) {
        return f1938c.matcher(str).matches();
    }
}
