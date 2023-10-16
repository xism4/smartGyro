package cz.msebera.android.http.mime;

public final class TextUtils {
    public static boolean containsBlanks(CharSequence charSequence) {
        if (charSequence == null) {
            return false;
        }
        for (int $i0 = 0; $i0 < charSequence.length(); $i0++) {
            if (Character.isWhitespace(charSequence.charAt($i0))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isBlank(CharSequence charSequence) {
        if (charSequence == null) {
            return true;
        }
        for (int $i0 = 0; $i0 < charSequence.length(); $i0++) {
            if (!Character.isWhitespace(charSequence.charAt($i0))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }
}
