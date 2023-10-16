package p000a.p001a.p005c.p012e;

import android.os.Build;
import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.MetricAffectingSpan;
import java.util.concurrent.Executor;
import p000a.p001a.p005c.p013f.C0088g;

/* renamed from: a.a.c.e.a */
public class C0074a implements Spannable {

    /* renamed from: a */
    private static final Object f188a = new Object();

    /* renamed from: b */
    private static Executor f189b = null;

    /* renamed from: c */
    private final Spannable f190c;

    /* renamed from: d */
    private final C0075a f191d;

    /* renamed from: e */
    private final PrecomputedText f192e;

    /* renamed from: a.a.c.e.a$a */
    public static final class C0075a {

        /* renamed from: a */
        private final TextPaint f193a;

        /* renamed from: b */
        private final TextDirectionHeuristic f194b;

        /* renamed from: c */
        private final int f195c;

        /* renamed from: d */
        private final int f196d;

        /* renamed from: e */
        final PrecomputedText.Params f197e;

        /* renamed from: a.a.c.e.a$a$a */
        public static class C0076a {

            /* renamed from: a */
            private final TextPaint f198a;

            /* renamed from: b */
            private TextDirectionHeuristic f199b;

            /* renamed from: c */
            private int f200c;

            /* renamed from: d */
            private int f201d;

            public C0076a(TextPaint textPaint) {
                this.f198a = textPaint;
                if (Build.VERSION.SDK_INT >= 23) {
                    this.f200c = 1;
                    this.f201d = 1;
                } else {
                    this.f201d = 0;
                    this.f200c = 0;
                }
                this.f199b = Build.VERSION.SDK_INT >= 18 ? TextDirectionHeuristics.FIRSTSTRONG_LTR : null;
            }

            /* renamed from: a */
            public C0076a mo265a(int i) {
                this.f200c = i;
                return this;
            }

            /* renamed from: a */
            public C0076a mo266a(TextDirectionHeuristic textDirectionHeuristic) {
                this.f199b = textDirectionHeuristic;
                return this;
            }

            /* renamed from: a */
            public C0075a mo267a() {
                return new C0075a(this.f198a, this.f199b, this.f200c, this.f201d);
            }

            /* renamed from: b */
            public C0076a mo268b(int i) {
                this.f201d = i;
                return this;
            }
        }

        public C0075a(PrecomputedText.Params params) {
            this.f193a = params.getTextPaint();
            this.f194b = params.getTextDirection();
            this.f195c = params.getBreakStrategy();
            this.f196d = params.getHyphenationFrequency();
            this.f197e = params;
        }

        C0075a(TextPaint textPaint, TextDirectionHeuristic textDirectionHeuristic, int i, int i2) {
            this.f197e = Build.VERSION.SDK_INT >= 28 ? new PrecomputedText.Params.Builder(textPaint).setBreakStrategy(i).setHyphenationFrequency(i2).setTextDirection(textDirectionHeuristic).build() : null;
            this.f193a = textPaint;
            this.f194b = textDirectionHeuristic;
            this.f195c = i;
            this.f196d = i2;
        }

        /* renamed from: a */
        public int mo258a() {
            return this.f195c;
        }

        /* renamed from: b */
        public int mo259b() {
            return this.f196d;
        }

        /* renamed from: c */
        public TextDirectionHeuristic mo260c() {
            return this.f194b;
        }

        /* renamed from: d */
        public TextPaint mo261d() {
            return this.f193a;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || !(obj instanceof C0075a)) {
                return false;
            }
            C0075a aVar = (C0075a) obj;
            PrecomputedText.Params params = this.f197e;
            if (params != null) {
                return params.equals(aVar.f197e);
            }
            if (Build.VERSION.SDK_INT >= 23 && (this.f195c != aVar.mo258a() || this.f196d != aVar.mo259b())) {
                return false;
            }
            if ((Build.VERSION.SDK_INT >= 18 && this.f194b != aVar.mo260c()) || this.f193a.getTextSize() != aVar.mo261d().getTextSize() || this.f193a.getTextScaleX() != aVar.mo261d().getTextScaleX() || this.f193a.getTextSkewX() != aVar.mo261d().getTextSkewX()) {
                return false;
            }
            if ((Build.VERSION.SDK_INT >= 21 && (this.f193a.getLetterSpacing() != aVar.mo261d().getLetterSpacing() || !TextUtils.equals(this.f193a.getFontFeatureSettings(), aVar.mo261d().getFontFeatureSettings()))) || this.f193a.getFlags() != aVar.mo261d().getFlags()) {
                return false;
            }
            int i = Build.VERSION.SDK_INT;
            if (i >= 24) {
                if (!this.f193a.getTextLocales().equals(aVar.mo261d().getTextLocales())) {
                    return false;
                }
            } else if (i >= 17 && !this.f193a.getTextLocale().equals(aVar.mo261d().getTextLocale())) {
                return false;
            }
            if (this.f193a.getTypeface() == null) {
                if (aVar.mo261d().getTypeface() != null) {
                    return false;
                }
            } else if (!this.f193a.getTypeface().equals(aVar.mo261d().getTypeface())) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int i = Build.VERSION.SDK_INT;
            if (i >= 24) {
                return C0088g.m311a(Float.valueOf(this.f193a.getTextSize()), Float.valueOf(this.f193a.getTextScaleX()), Float.valueOf(this.f193a.getTextSkewX()), Float.valueOf(this.f193a.getLetterSpacing()), Integer.valueOf(this.f193a.getFlags()), this.f193a.getTextLocales(), this.f193a.getTypeface(), Boolean.valueOf(this.f193a.isElegantTextHeight()), this.f194b, Integer.valueOf(this.f195c), Integer.valueOf(this.f196d));
            } else if (i >= 21) {
                return C0088g.m311a(Float.valueOf(this.f193a.getTextSize()), Float.valueOf(this.f193a.getTextScaleX()), Float.valueOf(this.f193a.getTextSkewX()), Float.valueOf(this.f193a.getLetterSpacing()), Integer.valueOf(this.f193a.getFlags()), this.f193a.getTextLocale(), this.f193a.getTypeface(), Boolean.valueOf(this.f193a.isElegantTextHeight()), this.f194b, Integer.valueOf(this.f195c), Integer.valueOf(this.f196d));
            } else if (i >= 18) {
                return C0088g.m311a(Float.valueOf(this.f193a.getTextSize()), Float.valueOf(this.f193a.getTextScaleX()), Float.valueOf(this.f193a.getTextSkewX()), Integer.valueOf(this.f193a.getFlags()), this.f193a.getTextLocale(), this.f193a.getTypeface(), this.f194b, Integer.valueOf(this.f195c), Integer.valueOf(this.f196d));
            } else if (i >= 17) {
                return C0088g.m311a(Float.valueOf(this.f193a.getTextSize()), Float.valueOf(this.f193a.getTextScaleX()), Float.valueOf(this.f193a.getTextSkewX()), Integer.valueOf(this.f193a.getFlags()), this.f193a.getTextLocale(), this.f193a.getTypeface(), this.f194b, Integer.valueOf(this.f195c), Integer.valueOf(this.f196d));
            } else {
                return C0088g.m311a(Float.valueOf(this.f193a.getTextSize()), Float.valueOf(this.f193a.getTextScaleX()), Float.valueOf(this.f193a.getTextSkewX()), Integer.valueOf(this.f193a.getFlags()), this.f193a.getTypeface(), this.f194b, Integer.valueOf(this.f195c), Integer.valueOf(this.f196d));
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x00e3  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.String toString() {
            /*
                r4 = this;
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                java.lang.String r1 = "{"
                r0.<init>(r1)
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "textSize="
                r1.append(r2)
                android.text.TextPaint r2 = r4.f193a
                float r2 = r2.getTextSize()
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                r0.append(r1)
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = ", textScaleX="
                r1.append(r2)
                android.text.TextPaint r2 = r4.f193a
                float r2 = r2.getTextScaleX()
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                r0.append(r1)
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = ", textSkewX="
                r1.append(r2)
                android.text.TextPaint r2 = r4.f193a
                float r2 = r2.getTextSkewX()
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                r0.append(r1)
                int r1 = android.os.Build.VERSION.SDK_INT
                r2 = 21
                if (r1 < r2) goto L_0x008f
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = ", letterSpacing="
                r1.append(r2)
                android.text.TextPaint r2 = r4.f193a
                float r2 = r2.getLetterSpacing()
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                r0.append(r1)
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = ", elegantTextHeight="
                r1.append(r2)
                android.text.TextPaint r2 = r4.f193a
                boolean r2 = r2.isElegantTextHeight()
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                r0.append(r1)
            L_0x008f:
                int r1 = android.os.Build.VERSION.SDK_INT
                r2 = 24
                java.lang.String r3 = ", textLocale="
                if (r1 < r2) goto L_0x00b0
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                r1.append(r3)
                android.text.TextPaint r2 = r4.f193a
                android.os.LocaleList r2 = r2.getTextLocales()
            L_0x00a5:
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                r0.append(r1)
                goto L_0x00c3
            L_0x00b0:
                r2 = 17
                if (r1 < r2) goto L_0x00c3
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                r1.append(r3)
                android.text.TextPaint r2 = r4.f193a
                java.util.Locale r2 = r2.getTextLocale()
                goto L_0x00a5
            L_0x00c3:
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = ", typeface="
                r1.append(r2)
                android.text.TextPaint r2 = r4.f193a
                android.graphics.Typeface r2 = r2.getTypeface()
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                r0.append(r1)
                int r1 = android.os.Build.VERSION.SDK_INT
                r2 = 26
                if (r1 < r2) goto L_0x00fd
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = ", variationSettings="
                r1.append(r2)
                android.text.TextPaint r2 = r4.f193a
                java.lang.String r2 = r2.getFontVariationSettings()
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                r0.append(r1)
            L_0x00fd:
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = ", textDir="
                r1.append(r2)
                android.text.TextDirectionHeuristic r2 = r4.f194b
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                r0.append(r1)
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = ", breakStrategy="
                r1.append(r2)
                int r2 = r4.f195c
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                r0.append(r1)
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = ", hyphenationFrequency="
                r1.append(r2)
                int r2 = r4.f196d
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                r0.append(r1)
                java.lang.String r1 = "}"
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: p000a.p001a.p005c.p012e.C0074a.C0075a.toString():java.lang.String");
        }
    }

    /* renamed from: a */
    public C0075a mo245a() {
        return this.f191d;
    }

    /* renamed from: b */
    public PrecomputedText mo246b() {
        Spannable spannable = this.f190c;
        if (spannable instanceof PrecomputedText) {
            return (PrecomputedText) spannable;
        }
        return null;
    }

    public char charAt(int i) {
        return this.f190c.charAt(i);
    }

    public int getSpanEnd(Object obj) {
        return this.f190c.getSpanEnd(obj);
    }

    public int getSpanFlags(Object obj) {
        return this.f190c.getSpanFlags(obj);
    }

    public int getSpanStart(Object obj) {
        return this.f190c.getSpanStart(obj);
    }

    public <T> T[] getSpans(int i, int i2, Class<T> cls) {
        return Build.VERSION.SDK_INT >= 28 ? this.f192e.getSpans(i, i2, cls) : this.f190c.getSpans(i, i2, cls);
    }

    public int length() {
        return this.f190c.length();
    }

    public int nextSpanTransition(int i, int i2, Class cls) {
        return this.f190c.nextSpanTransition(i, i2, cls);
    }

    public void removeSpan(Object obj) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be removed from PrecomputedText.");
        } else if (Build.VERSION.SDK_INT >= 28) {
            this.f192e.removeSpan(obj);
        } else {
            this.f190c.removeSpan(obj);
        }
    }

    public void setSpan(Object obj, int i, int i2, int i3) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be set to PrecomputedText.");
        } else if (Build.VERSION.SDK_INT >= 28) {
            this.f192e.setSpan(obj, i, i2, i3);
        } else {
            this.f190c.setSpan(obj, i, i2, i3);
        }
    }

    public CharSequence subSequence(int i, int i2) {
        return this.f190c.subSequence(i, i2);
    }

    public String toString() {
        return this.f190c.toString();
    }
}
