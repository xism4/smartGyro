package p036c.p037a.p038a.p039a.p074p;

/* renamed from: c.a.a.a.p.i */
public final class C0878i {
    /* renamed from: a */
    public static boolean m3087a(CharSequence charSequence) {
        if (charSequence == null) {
            return false;
        }
        for (int i = 0; i < charSequence.length(); i++) {
            if (Character.isWhitespace(charSequence.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    public static boolean m3088b(CharSequence charSequence) {
        if (charSequence == null) {
            return true;
        }
        for (int i = 0; i < charSequence.length(); i++) {
            if (!Character.isWhitespace(charSequence.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: c */
    public static boolean m3089c(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }
}
