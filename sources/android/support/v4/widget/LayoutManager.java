package android.support.v4.widget;

import android.graphics.Paint;
import android.icu.text.DecimalFormatSymbols;
import android.os.Build;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.PasswordTransformationMethod;
import android.view.ActionMode;
import android.widget.TextView;
import com.org.android.core.Label;
import com.org.android.core.a$a$a;
import com.org.android.util.m;

public final class LayoutManager {
    public static ActionMode.Callback a(TextView textView, ActionMode.Callback callback) {
        int $i0 = Build.VERSION.SDK_INT;
        return ($i0 < 26 || $i0 > 27 || (callback instanceof i)) ? callback : new i(callback, textView);
    }

    public static Label a(TextView textView) {
        if (Build.VERSION.SDK_INT >= 28) {
            return new Label(textView.getTextMetricsParams());
        }
        a$a$a $r3 = new a$a$a(new TextPaint(textView.getPaint()));
        if (Build.VERSION.SDK_INT >= 23) {
            $r3.b(textView.getBreakStrategy());
            $r3.a(textView.getHyphenationFrequency());
        }
        if (Build.VERSION.SDK_INT >= 18) {
            $r3.b(init(textView));
        }
        return $r3.a();
    }

    public static void a(TextView textView, int i) {
        m.getTitle(i);
        if (Build.VERSION.SDK_INT >= 28) {
            textView.setFirstBaselineToTopHeight(i);
            return;
        }
        Paint.FontMetricsInt $r2 = textView.getPaint().getFontMetricsInt();
        int $i1 = (Build.VERSION.SDK_INT < 16 || textView.getIncludeFontPadding()) ? $r2.top : $r2.ascent;
        if (i > Math.abs($i1)) {
            textView.setPadding(textView.getPaddingLeft(), i - (-$i1), textView.getPaddingRight(), textView.getPaddingBottom());
        }
    }

    public static void a(TextView textView, Label label) {
        if (Build.VERSION.SDK_INT >= 18) {
            textView.setTextDirection(getItemViewType(label.a()));
        }
        if (Build.VERSION.SDK_INT < 23) {
            float $f0 = label.getColor().getTextScaleX();
            textView.getPaint().set(label.getColor());
            if ($f0 == textView.getTextScaleX()) {
                textView.setTextScaleX(($f0 / 2.0f) + 1.0f);
            }
            textView.setTextScaleX($f0);
            return;
        }
        textView.getPaint().set(label.getColor());
        textView.setBreakStrategy(label.getOffset());
        textView.setHyphenationFrequency(label.d());
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v3, resolved type: com.org.android.core.Segment} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v4, resolved type: com.org.android.core.Segment} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.widget.TextView r12, com.org.android.core.Segment r13) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 28
            if (r0 < r1) goto L_0x0016
            r3 = r13
            com.org.android.core.Segment r3 = (com.org.android.core.Segment) r3
            r2 = r3
            android.text.PrecomputedText r13 = r2.append()
        L_0x000e:
            r5 = r13
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            r4 = r5
            r12.setText(r4)
            return
        L_0x0016:
            com.org.android.core.Label r6 = a(r12)
            r7 = r13
            com.org.android.core.Segment r7 = (com.org.android.core.Segment) r7
            r2 = r7
            com.org.android.core.Label r8 = r2.getValue()
            boolean r9 = r6.equals(r8)
            if (r9 == 0) goto L_0x0029
            goto L_0x000e
        L_0x0029:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            java.lang.String r11 = "Given text can not be applied to TextView."
            r10.<init>(r11)
            goto L_0x0031
        L_0x0031:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.LayoutManager.a(android.widget.TextView, com.org.android.core.Segment):void");
    }

    private static int getItemViewType(TextDirectionHeuristic textDirectionHeuristic) {
        if (textDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_RTL || textDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_LTR) {
            return 1;
        }
        if (textDirectionHeuristic == TextDirectionHeuristics.ANYRTL_LTR) {
            return 2;
        }
        if (textDirectionHeuristic == TextDirectionHeuristics.LTR) {
            return 3;
        }
        if (textDirectionHeuristic == TextDirectionHeuristics.RTL) {
            return 4;
        }
        if (textDirectionHeuristic == TextDirectionHeuristics.LOCALE) {
            return 5;
        }
        if (textDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_LTR) {
            return 6;
        }
        return textDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_RTL ? 7 : 1;
    }

    private static TextDirectionHeuristic init(TextView textView) {
        if (textView.getTransformationMethod() instanceof PasswordTransformationMethod) {
            return TextDirectionHeuristics.LTR;
        }
        boolean $z0 = false;
        if (Build.VERSION.SDK_INT < 28 || (textView.getInputType() & 15) != 3) {
            if (textView.getLayoutDirection() == 1) {
                $z0 = true;
            }
            switch (textView.getTextDirection()) {
                case 2:
                    return TextDirectionHeuristics.ANYRTL_LTR;
                case 3:
                    return TextDirectionHeuristics.LTR;
                case 4:
                    return TextDirectionHeuristics.RTL;
                case 5:
                    return TextDirectionHeuristics.LOCALE;
                case 6:
                    return TextDirectionHeuristics.FIRSTSTRONG_LTR;
                case 7:
                    return TextDirectionHeuristics.FIRSTSTRONG_RTL;
                default:
                    return $z0 ? TextDirectionHeuristics.FIRSTSTRONG_RTL : TextDirectionHeuristics.FIRSTSTRONG_LTR;
            }
        } else {
            byte $b1 = Character.getDirectionality(DecimalFormatSymbols.getInstance(textView.getTextLocale()).getDigitStrings()[0].codePointAt(0));
            return ($b1 == 1 || $b1 == 2) ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR;
        }
    }

    public static void init(TextView textView, int i) {
        m.getTitle(i);
        int $i1 = textView.getPaint().getFontMetricsInt((Paint.FontMetricsInt) null);
        if (i != $i1) {
            textView.setLineSpacing((float) (i - $i1), 1.0f);
        }
    }

    public static void initialize(TextView textView, int i) {
        m.getTitle(i);
        Paint.FontMetricsInt $r2 = textView.getPaint().getFontMetricsInt();
        int $i1 = (Build.VERSION.SDK_INT < 16 || textView.getIncludeFontPadding()) ? $r2.bottom : $r2.descent;
        if (i > Math.abs($i1)) {
            textView.setPadding(textView.getPaddingLeft(), textView.getPaddingTop(), textView.getPaddingRight(), i - $i1);
        }
    }

    public static int setText(TextView textView) {
        return textView.getPaddingBottom() + textView.getPaint().getFontMetricsInt().bottom;
    }

    public static int updatePadding(TextView textView) {
        return textView.getPaddingTop() - textView.getPaint().getFontMetricsInt().top;
    }
}
