package com.org.android.core;

import android.os.Build;
import android.text.PrecomputedText;
import android.text.TextDirectionHeuristic;
import android.text.TextPaint;
import android.text.TextUtils;
import com.org.android.util.X509LDAPCertStoreParameters;

public final class Label {
    private final TextPaint a;
    private final TextDirectionHeuristic b;
    private final int c;
    private final int f;
    final PrecomputedText.Params i;

    public Label(PrecomputedText.Params params) {
        this.a = params.getTextPaint();
        this.b = params.getTextDirection();
        this.c = params.getBreakStrategy();
        this.f = params.getHyphenationFrequency();
        this.i = params;
    }

    Label(TextPaint textPaint, TextDirectionHeuristic textDirectionHeuristic, int i2, int i3) {
        this.i = Build.VERSION.SDK_INT >= 28 ? new PrecomputedText.Params.Builder(textPaint).setBreakStrategy(i2).setHyphenationFrequency(i3).setTextDirection(textDirectionHeuristic).build() : null;
        this.a = textPaint;
        this.b = textDirectionHeuristic;
        this.c = i2;
        this.f = i3;
    }

    public TextDirectionHeuristic a() {
        return this.b;
    }

    public int d() {
        return this.f;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == 0 || !(obj instanceof Label)) {
            return false;
        }
        Label $r2 = obj;
        PrecomputedText.Params $r3 = this.i;
        if ($r3 != null) {
            return $r3.equals($r2.i);
        }
        if (Build.VERSION.SDK_INT >= 23 && (this.c != $r2.getOffset() || this.f != $r2.d())) {
            return false;
        }
        if ((Build.VERSION.SDK_INT >= 18 && this.b != $r2.a()) || this.a.getTextSize() != $r2.getColor().getTextSize() || this.a.getTextScaleX() != $r2.getColor().getTextScaleX() || this.a.getTextSkewX() != $r2.getColor().getTextSkewX()) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            if (this.a.getLetterSpacing() != $r2.getColor().getLetterSpacing()) {
                return false;
            }
            if (!TextUtils.equals(this.a.getFontFeatureSettings(), $r2.getColor().getFontFeatureSettings())) {
                return false;
            }
        }
        if (this.a.getFlags() != $r2.getColor().getFlags()) {
            return false;
        }
        int $i0 = Build.VERSION.SDK_INT;
        if ($i0 >= 24) {
            if (!this.a.getTextLocales().equals($r2.getColor().getTextLocales())) {
                return false;
            }
        } else if ($i0 >= 17) {
            if (!this.a.getTextLocale().equals($r2.getColor().getTextLocale())) {
                return false;
            }
        }
        if (this.a.getTypeface() == null) {
            return $r2.getColor().getTypeface() == null;
        }
        return this.a.getTypeface().equals($r2.getColor().getTypeface());
    }

    public TextPaint getColor() {
        return this.a;
    }

    public int getOffset() {
        return this.c;
    }

    public int hashCode() {
        int $i0 = Build.VERSION.SDK_INT;
        if ($i0 >= 24) {
            return X509LDAPCertStoreParameters.hashCode(Float.valueOf(this.a.getTextSize()), Float.valueOf(this.a.getTextScaleX()), Float.valueOf(this.a.getTextSkewX()), Float.valueOf(this.a.getLetterSpacing()), Integer.valueOf(this.a.getFlags()), this.a.getTextLocales(), this.a.getTypeface(), Boolean.valueOf(this.a.isElegantTextHeight()), this.b, Integer.valueOf(this.c), Integer.valueOf(this.f));
        } else if ($i0 >= 21) {
            return X509LDAPCertStoreParameters.hashCode(Float.valueOf(this.a.getTextSize()), Float.valueOf(this.a.getTextScaleX()), Float.valueOf(this.a.getTextSkewX()), Float.valueOf(this.a.getLetterSpacing()), Integer.valueOf(this.a.getFlags()), this.a.getTextLocale(), this.a.getTypeface(), Boolean.valueOf(this.a.isElegantTextHeight()), this.b, Integer.valueOf(this.c), Integer.valueOf(this.f));
        } else if ($i0 >= 18) {
            return X509LDAPCertStoreParameters.hashCode(Float.valueOf(this.a.getTextSize()), Float.valueOf(this.a.getTextScaleX()), Float.valueOf(this.a.getTextSkewX()), Integer.valueOf(this.a.getFlags()), this.a.getTextLocale(), this.a.getTypeface(), this.b, Integer.valueOf(this.c), Integer.valueOf(this.f));
        } else if ($i0 >= 17) {
            return X509LDAPCertStoreParameters.hashCode(Float.valueOf(this.a.getTextSize()), Float.valueOf(this.a.getTextScaleX()), Float.valueOf(this.a.getTextSkewX()), Integer.valueOf(this.a.getFlags()), this.a.getTextLocale(), this.a.getTypeface(), this.b, Integer.valueOf(this.c), Integer.valueOf(this.f));
        } else {
            return X509LDAPCertStoreParameters.hashCode(Float.valueOf(this.a.getTextSize()), Float.valueOf(this.a.getTextScaleX()), Float.valueOf(this.a.getTextSkewX()), Integer.valueOf(this.a.getFlags()), this.a.getTypeface(), this.b, Integer.valueOf(this.c), Integer.valueOf(this.f));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x00e5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String toString() {
        /*
            r12 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "{"
            r0.<init>(r1)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r1 = "textSize="
            r2.append(r1)
            android.text.TextPaint r3 = r12.a
            float r4 = r3.getTextSize()
            r2.append(r4)
            java.lang.String r5 = r2.toString()
            r0.append(r5)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r1 = ", textScaleX="
            r2.append(r1)
            android.text.TextPaint r3 = r12.a
            float r4 = r3.getTextScaleX()
            r2.append(r4)
            java.lang.String r5 = r2.toString()
            r0.append(r5)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r1 = ", textSkewX="
            r2.append(r1)
            android.text.TextPaint r3 = r12.a
            float r4 = r3.getTextSkewX()
            r2.append(r4)
            java.lang.String r5 = r2.toString()
            r0.append(r5)
            int r6 = android.os.Build.VERSION.SDK_INT
            r7 = 21
            if (r6 < r7) goto L_0x008f
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r1 = ", letterSpacing="
            r2.append(r1)
            android.text.TextPaint r3 = r12.a
            float r4 = r3.getLetterSpacing()
            r2.append(r4)
            java.lang.String r5 = r2.toString()
            r0.append(r5)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r1 = ", elegantTextHeight="
            r2.append(r1)
            android.text.TextPaint r3 = r12.a
            boolean r8 = r3.isElegantTextHeight()
            r2.append(r8)
            java.lang.String r5 = r2.toString()
            r0.append(r5)
        L_0x008f:
            int r6 = android.os.Build.VERSION.SDK_INT
            r7 = 24
            if (r6 < r7) goto L_0x00b0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r1 = ", textLocale="
            r2.append(r1)
            android.text.TextPaint r3 = r12.a
            android.os.LocaleList r9 = r3.getTextLocales()
        L_0x00a5:
            r2.append(r9)
            java.lang.String r5 = r2.toString()
            r0.append(r5)
            goto L_0x00c5
        L_0x00b0:
            r7 = 17
            if (r6 < r7) goto L_0x00c5
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r1 = ", textLocale="
            r2.append(r1)
            android.text.TextPaint r3 = r12.a
            java.util.Locale r9 = r3.getTextLocale()
            goto L_0x00a5
        L_0x00c5:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r1 = ", typeface="
            r2.append(r1)
            android.text.TextPaint r3 = r12.a
            android.graphics.Typeface r10 = r3.getTypeface()
            r2.append(r10)
            java.lang.String r5 = r2.toString()
            r0.append(r5)
            int r6 = android.os.Build.VERSION.SDK_INT
            r7 = 26
            if (r6 < r7) goto L_0x00ff
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r1 = ", variationSettings="
            r2.append(r1)
            android.text.TextPaint r3 = r12.a
            java.lang.String r5 = r3.getFontVariationSettings()
            r2.append(r5)
            java.lang.String r5 = r2.toString()
            r0.append(r5)
        L_0x00ff:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r1 = ", textDir="
            r2.append(r1)
            android.text.TextDirectionHeuristic r11 = r12.b
            r2.append(r11)
            java.lang.String r5 = r2.toString()
            r0.append(r5)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r1 = ", breakStrategy="
            r2.append(r1)
            int r6 = r12.c
            r2.append(r6)
            java.lang.String r5 = r2.toString()
            r0.append(r5)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r1 = ", hyphenationFrequency="
            r2.append(r1)
            int r6 = r12.f
            r2.append(r6)
            java.lang.String r5 = r2.toString()
            r0.append(r5)
            java.lang.String r1 = "}"
            r0.append(r1)
            java.lang.String r5 = r0.toString()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.org.android.core.Label.toString():java.lang.String");
    }
}
