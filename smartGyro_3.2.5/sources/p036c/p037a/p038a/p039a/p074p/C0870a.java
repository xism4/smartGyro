package p036c.p037a.p038a.p039a.p074p;

import java.util.Collection;

/* renamed from: c.a.a.a.p.a */
public class C0870a {
    /* renamed from: a */
    public static int m3039a(int i, String str) {
        if (i >= 0) {
            return i;
        }
        throw new IllegalArgumentException(str + " may not be negative");
    }

    /* renamed from: a */
    public static long m3040a(long j, String str) {
        if (j >= 0) {
            return j;
        }
        throw new IllegalArgumentException(str + " may not be negative");
    }

    /* renamed from: a */
    public static <T extends CharSequence> T m3041a(T t, String str) {
        if (t == null) {
            throw new IllegalArgumentException(str + " may not be null");
        } else if (!C0878i.m3087a(t)) {
            return t;
        } else {
            throw new IllegalArgumentException(str + " may not contain blanks");
        }
    }

    /* renamed from: a */
    public static <T> T m3042a(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new IllegalArgumentException(str + " may not be null");
    }

    /* renamed from: a */
    public static <E, T extends Collection<E>> T m3043a(T t, String str) {
        if (t == null) {
            throw new IllegalArgumentException(str + " may not be null");
        } else if (!t.isEmpty()) {
            return t;
        } else {
            throw new IllegalArgumentException(str + " may not be empty");
        }
    }

    /* renamed from: a */
    public static void m3044a(boolean z, String str) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }

    /* renamed from: a */
    public static void m3045a(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(String.format(str, objArr));
        }
    }

    /* renamed from: b */
    public static int m3046b(int i, String str) {
        if (i > 0) {
            return i;
        }
        throw new IllegalArgumentException(str + " may not be negative or zero");
    }

    /* renamed from: b */
    public static <T extends CharSequence> T m3047b(T t, String str) {
        if (t == null) {
            throw new IllegalArgumentException(str + " may not be null");
        } else if (!C0878i.m3088b(t)) {
            return t;
        } else {
            throw new IllegalArgumentException(str + " may not be blank");
        }
    }

    /* renamed from: c */
    public static <T extends CharSequence> T m3048c(T t, String str) {
        if (t == null) {
            throw new IllegalArgumentException(str + " may not be null");
        } else if (!C0878i.m3089c(t)) {
            return t;
        } else {
            throw new IllegalArgumentException(str + " may not be empty");
        }
    }
}
