package cz.msebera.android.http.mime;

import java.util.Collection;

public class Args {
    public static void check(boolean z, String str) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }

    public static void check(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(String.format(str, objArr));
        }
    }

    public static CharSequence containsNoBlanks(CharSequence charSequence, String $r1) {
        if (charSequence == null) {
            throw new IllegalArgumentException($r1 + " may not be null");
        } else if (!TextUtils.containsBlanks(charSequence)) {
            return charSequence;
        } else {
            throw new IllegalArgumentException($r1 + " may not contain blanks");
        }
    }

    public static CharSequence notBlank(CharSequence charSequence, String $r1) {
        if (charSequence == null) {
            throw new IllegalArgumentException($r1 + " may not be null");
        } else if (!TextUtils.isBlank(charSequence)) {
            return charSequence;
        } else {
            throw new IllegalArgumentException($r1 + " may not be blank");
        }
    }

    public static CharSequence notEmpty(CharSequence charSequence, String $r1) {
        if (charSequence == null) {
            throw new IllegalArgumentException($r1 + " may not be null");
        } else if (!TextUtils.isEmpty(charSequence)) {
            return charSequence;
        } else {
            throw new IllegalArgumentException($r1 + " may not be empty");
        }
    }

    public static Collection notEmpty(Collection collection, String $r1) {
        if (collection == null) {
            throw new IllegalArgumentException($r1 + " may not be null");
        } else if (!collection.isEmpty()) {
            return collection;
        } else {
            throw new IllegalArgumentException($r1 + " may not be empty");
        }
    }

    public static int notNegative(int i, String str) {
        if (i >= 0) {
            return i;
        }
        throw new IllegalArgumentException(str + " may not be negative");
    }

    public static long notNegative(long j, String str) {
        if (j >= 0) {
            return j;
        }
        throw new IllegalArgumentException(str + " may not be negative");
    }

    public static Object notNull(Object obj, String str) {
        if (obj != null) {
            return obj;
        }
        throw new IllegalArgumentException(str + " may not be null");
    }

    public static int positive(int i, String str) {
        if (i > 0) {
            return i;
        }
        throw new IllegalArgumentException(str + " may not be negative or zero");
    }
}
