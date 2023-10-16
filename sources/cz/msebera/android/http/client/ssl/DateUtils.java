package cz.msebera.android.http.client.ssl;

import cz.msebera.android.http.mime.Args;
import java.lang.ref.SoftReference;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public final class DateUtils {
    private static final String[] DEFAULT_PATTERNS = {"EEE, dd MMM yyyy HH:mm:ss zzz", "EEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy"};
    private static final Date DEFAULT_TWO_DIGIT_YEAR_START;
    public static final TimeZone GMT = TimeZone.getTimeZone("GMT");

    final class DateFormatHolder {
        private static final ThreadLocal<SoftReference<Map<String, SimpleDateFormat>>> THREADLOCAL_FORMATS = new BitmapHunter$1();

        public static SimpleDateFormat formatFor(String str) {
            Object $r2 = (Map) THREADLOCAL_FORMATS.get().get();
            if ($r2 == null) {
                $r2 = r8;
                HashMap r8 = new HashMap();
                THREADLOCAL_FORMATS.set(new SoftReference($r2));
            }
            SimpleDateFormat $r5 = (SimpleDateFormat) ((Map) $r2).get(str);
            if ($r5 != null) {
                return $r5;
            }
            SimpleDateFormat $r52 = r10;
            SimpleDateFormat r10 = new SimpleDateFormat(str, Locale.US);
            $r52.setTimeZone(TimeZone.getTimeZone("GMT"));
            ((Map) $r2).put(str, $r52);
            return $r52;
        }
    }

    static {
        Calendar $r2 = Calendar.getInstance();
        $r2.setTimeZone(GMT);
        $r2.set(2000, 0, 1, 0, 0, 0);
        $r2.set(14, 0);
        DEFAULT_TWO_DIGIT_YEAR_START = $r2.getTime();
    }

    public static Date parseDate(String str, String[] strArr) {
        return parseDate(str, strArr, (Date) null);
    }

    public static Date parseDate(String $r0, String[] $r1, Date $r2) {
        Args.notNull($r0, "Date value");
        if ($r1 == null) {
            $r1 = DEFAULT_PATTERNS;
        }
        if ($r2 == null) {
            $r2 = DEFAULT_TWO_DIGIT_YEAR_START;
        }
        if ($r0.length() > 1 && $r0.startsWith("'") && $r0.endsWith("'")) {
            $r0 = $r0.substring(1, $r0.length() - 1);
        }
        for (String $r3 : $r1) {
            SimpleDateFormat $r4 = DateFormatHolder.formatFor($r3);
            $r4.set2DigitYearStart($r2);
            ParsePosition $r5 = new ParsePosition(0);
            Date $r6 = $r4.parse($r0, $r5);
            if ($r5.getIndex() != 0) {
                return $r6;
            }
        }
        return null;
    }
}
