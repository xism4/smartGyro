package p036c.p037a.p038a.p039a.p041b.p047f;

import java.lang.ref.SoftReference;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.b.f.c */
public final class C0553c {

    /* renamed from: a */
    private static final String[] f1838a = {"EEE, dd MMM yyyy HH:mm:ss zzz", "EEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy"};

    /* renamed from: b */
    private static final Date f1839b;

    /* renamed from: c */
    public static final TimeZone f1840c = TimeZone.getTimeZone("GMT");

    /* renamed from: c.a.a.a.b.f.c$a */
    static final class C0554a {

        /* renamed from: a */
        private static final ThreadLocal<SoftReference<Map<String, SimpleDateFormat>>> f1841a = new C0552b();

        /* renamed from: a */
        public static SimpleDateFormat m2229a(String str) {
            Map map = (Map) f1841a.get().get();
            if (map == null) {
                map = new HashMap();
                f1841a.set(new SoftReference(map));
            }
            SimpleDateFormat simpleDateFormat = (SimpleDateFormat) map.get(str);
            if (simpleDateFormat != null) {
                return simpleDateFormat;
            }
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(str, Locale.US);
            simpleDateFormat2.setTimeZone(TimeZone.getTimeZone("GMT"));
            map.put(str, simpleDateFormat2);
            return simpleDateFormat2;
        }
    }

    static {
        Calendar instance = Calendar.getInstance();
        instance.setTimeZone(f1840c);
        instance.set(2000, 0, 1, 0, 0, 0);
        instance.set(14, 0);
        f1839b = instance.getTime();
    }

    /* renamed from: a */
    public static Date m2227a(String str, String[] strArr) {
        return m2228a(str, strArr, (Date) null);
    }

    /* renamed from: a */
    public static Date m2228a(String str, String[] strArr, Date date) {
        C0870a.m3042a(str, "Date value");
        if (strArr == null) {
            strArr = f1838a;
        }
        if (date == null) {
            date = f1839b;
        }
        if (str.length() > 1 && str.startsWith("'") && str.endsWith("'")) {
            str = str.substring(1, str.length() - 1);
        }
        for (String a : strArr) {
            SimpleDateFormat a2 = C0554a.m2229a(a);
            a2.set2DigitYearStart(date);
            ParsePosition parsePosition = new ParsePosition(0);
            Date parse = a2.parse(str, parsePosition);
            if (parsePosition.getIndex() != 0) {
                return parse;
            }
        }
        return null;
    }
}
